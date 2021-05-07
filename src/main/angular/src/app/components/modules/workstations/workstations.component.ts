import { AfterViewInit, Component, OnInit, ViewChild, ChangeDetectorRef, OnDestroy } from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTable } from '@angular/material/table';
import { WorkstationsDataSource } from './workstations-datasource';
import { IWorkstation } from 'src/app/interfaces/workstation';
import { WorkstationService } from 'src/app/services/workstation.service';
import { IWorkstationFilter } from "src/app/filters/workstation-filter";
import { MatDialog } from '@angular/material/dialog';
import { WorkstationFormComponent } from '../workstation-form/workstation-form.component';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ConfirmationDialogService } from '../../common/ui/confirmation-dialog/confirmation-dialog.service';
import { BaseComponent } from '../base-component/base-component.component';
import { NavigationService } from 'src/app/services/navigation.service';
import { IWorkstationView } from 'src/app/interfaces/view/workstation-view';

@Component({
  selector: 'app-workstations',
  templateUrl: './workstations.component.html',
  styleUrls: ['./workstations.component.scss']
})
export class WorkstationsComponent extends BaseComponent implements OnInit, OnDestroy, AfterViewInit {

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatTable) table: MatTable<IWorkstationView>;
  dataSource: WorkstationsDataSource;
  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['store', 'actions', 'description'];
  filter: IWorkstationFilter = {
    entity: {
      id: undefined,
      active: undefined,
      description: undefined,
      store: undefined
    },
    startDate: undefined,
    endDate: undefined,
    hidden: true,
    page: 0,
    rows: 20
  }
  constructor(
    private workstationService: WorkstationService,
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

  public setFilter(searchable?: boolean) {
    if (searchable) {
      this.filter.page = 0;
    }
    this.workstationService.filter(this.filter).then(response => {
      this.dataSource = new WorkstationsDataSource(response.fields.data);
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
      store: undefined
    }
  }

  public addRow() {
    const entity: IWorkstation = {
      id: undefined,
      active: undefined,
      description: undefined,
      store: undefined
    }
    this.showForm(entity);
  }

  public showForm(entity: IWorkstation) {
    const dialogRef = this.dialog.open(WorkstationFormComponent, {
      width: '600px',
      disableClose: true,
      data: { entity: entity }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.workstationService.save(result.entity).then(response => {
          this.setFilter();
        });
      }
    });
  }

  delete(entity: IWorkstation): void {
    const dialogRef = this.confirmationDialogService.showConfirmationDialog('¿Desea eliminar el registro?');
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.workstationService.delete(entity).then(response => {
          const snacBarRef = this.snackBar.open(response.message, 'Deshacer', {
            duration: 3000
          });
          this.setFilter();

          snacBarRef.onAction().subscribe(() => {
            this.workstationService.save(entity).then(response => {
              this.setFilter();
            });
          });
        });

      }
    });
  }

}