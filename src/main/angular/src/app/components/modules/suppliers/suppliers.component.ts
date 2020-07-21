import { AfterViewInit, Component, OnInit, ViewChild, ChangeDetectorRef, OnDestroy } from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTable } from '@angular/material/table';
import { SuppliersDataSource } from './suppliers-datasource';
import { ISupplier } from 'src/app/interfaces/supplier';
import { SupplierService } from 'src/app/services/supplier.service';
import { ISupplierFilter } from "src/app/filters/supplier-filter";
import { MatDialog } from '@angular/material/dialog';
import { SupplierFormComponent } from '../supplier-form/supplier-form.component';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ConfirmationDialogService } from '../../common/ui/confirmation-dialog/confirmation-dialog.service';
import { BaseComponent } from '../base-component/base-component.component';
import { NavigationService } from 'src/app/services/navigation.service';

@Component({
  selector: 'app-suppliers',
  templateUrl: './suppliers.component.html',
  styleUrls: ['./suppliers.component.scss']
})
export class SuppliersComponent extends BaseComponent implements OnInit, OnDestroy, AfterViewInit {

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatTable) table: MatTable<ISupplier>;
  dataSource: SuppliersDataSource;
  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['business_name', 'actions', 'tax_id', 'contact', 'telephone', 'email'];
  filter: ISupplierFilter = {
    entity: {
      id: undefined,
      supplierType: undefined,
      business_name: undefined,
      city: undefined,
      contact: undefined,
      country: undefined,
      external_number: undefined,
      county: undefined,
      internal_number: undefined,
      postal_code: undefined,
      settlement: undefined,
      state: undefined,
      street: undefined,
      tax_id: undefined,
      telephone: undefined,
      email: undefined,
    },
    startDate: undefined,
    endDate: undefined,
    hidden: true,
    page: 0,
    rows: 20
  }
  constructor(
    private supplierService: SupplierService,
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
    this.setFilter();

  }

  ngAfterViewInit() {
  }

  public setFilter() {
    this.supplierService.filter(this.filter).then(response => {
      this.dataSource = new SuppliersDataSource();
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
      this.dataSource.data = response.fields.data;
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
      supplierType: undefined,
      business_name: undefined,
      city: undefined,
      contact: undefined,
      country: undefined,
      external_number: undefined,
      county: undefined,
      internal_number: undefined,
      postal_code: undefined,
      settlement: undefined,
      state: undefined,
      street: undefined,
      tax_id: undefined,
      telephone: undefined,
      email: undefined,
    }
  }

  public addRow() {
    const entity: ISupplier = {
      id: 0,
      supplierType: undefined,
      business_name: '',
      city: '',
      contact: '',
      country: undefined,
      external_number: '',
      county: '',
      internal_number: '',
      postal_code: '',
      settlement: '',
      state: undefined,
      street: '',
      tax_id: '',
      telephone: '',
      email: '',
    }
    this.showForm(entity);
  }

  public showForm(entity: ISupplier) {
    const dialogRef = this.dialog.open(SupplierFormComponent, {
      width: '600px',
      disableClose: true,
      data: { entity: entity }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.supplierService.save(result.entity).then(response => {
          this.setFilter();
        });
      }
    });
  }

  delete(entity: ISupplier): void {
    const dialogRef = this.confirmationDialogService.showConfirmationDialog('¿Desea eliminar el registro?');
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.supplierService.delete(entity).then(response => {
          const snacBarRef = this.snackBar.open(response.message, 'Deshacer', {
            duration: 3000
          });
          this.setFilter();

          snacBarRef.onAction().subscribe(() => {
            this.supplierService.save(entity).then(response => {
              this.setFilter();
            });
          });
        });

      }
    });
  }

}
