import { AfterViewInit, Component, OnInit, ViewChild, ChangeDetectorRef, OnDestroy } from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTable } from '@angular/material/table';
import { UnitsDataSource } from './units-datasource';
import { IUnit } from 'src/app/interfaces/unit';
import { UnitService } from 'src/app/services/unit.service';
import { IUnitFilter } from "src/app/filters/unit-filter";
import { MatDialog } from '@angular/material/dialog';
import { UnitFormComponent } from '../unit-form/unit-form.component';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ConfirmationDialogService } from '../../common/ui/confirmation-dialog/confirmation-dialog.service';
import { BaseComponent } from '../base-component/base-component.component';
import { NavigationService } from 'src/app/services/navigation.service';

@Component({
  selector: 'app-units',
  templateUrl: './units.component.html',
  styleUrls: ['./units.component.scss']
})
export class UnitsComponent extends BaseComponent implements OnInit, OnDestroy, AfterViewInit {

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatTable) table: MatTable<IUnit>;
  dataSource: UnitsDataSource;
  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['unit', 'actions', 'description'];
  filter: IUnitFilter = {
    entity: {
      id: undefined,
      active: undefined,
      description: undefined,
      unit: undefined,
    },
    startDate: undefined,
    endDate: undefined,
    hidden: true,
    page: 0,
    rows: 20
  }
  constructor(
    private unitService: UnitService,
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
    this.unitService.filter(this.filter).then(response => {
      this.dataSource = new UnitsDataSource(response.fields.data);
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
      description: undefined,
      unit: undefined,
    }
  }

  public addRow() {
    const entity: IUnit = {
      id: 0,
      active: true,
      description: undefined,
      unit: undefined
    }
    this.showForm(entity);
  }

  public showForm(entity: IUnit) {
    const dialogRef = this.dialog.open(UnitFormComponent, {
      width: '600px',
      disableClose: true,
      data: { entity: entity }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.unitService.save(result.entity).then(response => {
          this.setFilter();
        });
      }
    });
  }

  delete(entity: IUnit): void {
    const dialogRef = this.confirmationDialogService.showConfirmationDialog('¿Desea eliminar el registro?');
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.unitService.delete(entity).then(response => {
          const snacBarRef = this.snackBar.open(response.message, 'Deshacer', {
            duration: 3000
          });
          this.setFilter();

          snacBarRef.onAction().subscribe(() => {
            this.unitService.save(entity).then(response => {
              this.setFilter();
            });
          });
        });

      }
    });
  }

}
