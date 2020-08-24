import { AfterViewInit, Component, OnInit, ViewChild, ChangeDetectorRef, OnDestroy } from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTable } from '@angular/material/table';
import { TablesDataSource } from './tables-datasource';
import { ITable } from 'src/app/interfaces/table';
import { TableService } from 'src/app/services/table.service';
import { ITableFilter } from "src/app/filters/table-filter";
import { MatDialog } from '@angular/material/dialog';
import { TableFormComponent } from '../table-form/table-form.component';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ConfirmationDialogService } from '../../common/ui/confirmation-dialog/confirmation-dialog.service';
import { BaseComponent } from '../base-component/base-component.component';
import { NavigationService } from 'src/app/services/navigation.service';

@Component({
  selector: 'app-tables',
  templateUrl: './tables.component.html',
  styleUrls: ['./tables.component.scss']
})
export class TablesComponent extends BaseComponent implements OnInit, OnDestroy, AfterViewInit {

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatTable) table: MatTable<ITable>;
  dataSource: TablesDataSource;
  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['name', 'actions', 'places'];
  filter: ITableFilter = {
    entity: {
      id: undefined,
      active: undefined,
      name: undefined,
      places: undefined
    },
    startDate: undefined,
    endDate: undefined,
    hidden: true,
    page: 0,
    rows: 20
  }
  constructor(
    private tableService: TableService,
    private changeDetectorRefs: ChangeDetectorRef,
    private dialog: MatDialog,
    private snackBar: MatSnackBar,
    private confirmationDialogService: ConfirmationDialogService,
    protected navigationService: NavigationService) {
    super(navigationService);
  }
  ngOnDestroy(): void {
    super.destroy();
  }

  ngOnInit() {

  }

  ngAfterViewInit() {
    this.setFilter();
  }

  public setFilter() {
    this.tableService.filter(this.filter).then(response => {
      this.dataSource = new TablesDataSource(response.fields.data);
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
      this.dataSource.count = response.fields.count;
      this.table.dataSource = this.dataSource;
      this.changeDetectorRefs.detectChanges();
    })
  }

  public pageChangeEvent(event: PageEvent) {
    this.filter.rows = event.pageSize;
    this.filter.page = event.pageIndex;
    this.setFilter();
  }

  toggleSearchBar() {
    this.filter.hidden = !this.filter.hidden;
    this.filter.entity = {
      id: undefined,
      active: undefined,
      name: undefined,
      places: undefined
    }
  }

  public addRow() {
    const entity: ITable = {
      id: undefined,
      active: undefined,
      name: undefined,
      places: undefined
    }
    this.showForm(entity);
  }

  public showForm(entity: ITable) {
    const dialogRef = this.dialog.open(TableFormComponent, {
      width: '600px',
      disableClose: true,
      data: { entity: entity }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.tableService.save(result.entity).then(response => {
          this.setFilter();
        });
      }
    });
  }

  delete(entity: ITable): void {
    const dialogRef = this.confirmationDialogService.showConfirmationDialog('¿Desea eliminar el registro?');
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.tableService.delete(entity).then(response => {
          const snacBarRef = this.snackBar.open(response.message, 'Deshacer', {
            duration: 3000
          });
          this.setFilter();

          snacBarRef.onAction().subscribe(() => {
            this.tableService.save(entity).then(response => {
              this.setFilter();
            });
          });
        });

      }
    });
  }

}
