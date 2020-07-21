import { AfterViewInit, Component, OnInit, ViewChild, ChangeDetectorRef, OnDestroy } from '@angular/core';
import { BaseComponent } from '../base-component/base-component.component';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTable } from '@angular/material/table';
import { IPaymentMethod } from 'src/app/interfaces/payment-method';
import { PaymentMethodsDataSource } from "./payment-methods-datasource";
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ConfirmationDialogService } from '../../common/ui/confirmation-dialog/confirmation-dialog.service';
import { NavigationService } from 'src/app/services/navigation.service';
import { PaymentMethodFormComponent } from '../payment-method-form/payment-method-form.component';
import { IPaymentMethodFilter } from "src/app/filters/payment-method-filter";
import { PaymentMethodService } from 'src/app/services/payment-method.service';
@Component({
  selector: 'app-payment-methods',
  templateUrl: './payment-methods.component.html',
  styleUrls: ['./payment-methods.component.scss']
})
export class PaymentMethodsComponent extends BaseComponent implements OnInit, OnDestroy, AfterViewInit {

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatTable) table: MatTable<IPaymentMethod>;
  dataSource: PaymentMethodsDataSource;
  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['method', 'actions', 'description'];
  filter: IPaymentMethodFilter = {
    entity: {
      id: undefined,
      active: undefined,
      method: undefined,
      description: undefined
    },
    startDate: undefined,
    endDate: undefined,
    hidden: true,
    page: 0,
    rows: 20
  }
  constructor(
    private changeDetectorRefs: ChangeDetectorRef,
    private dialog: MatDialog,
    private snackBar: MatSnackBar,
    private confirmationDialogService: ConfirmationDialogService,
    protected navigationService: NavigationService,
    private paymentMethodService: PaymentMethodService
    ) {
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
    this.paymentMethodService.filter(this.filter).then(response => {
      this.dataSource = new PaymentMethodsDataSource(response.fields.data);
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
      this.dataSource.count = response.fields.count;
      this.table.dataSource = this.dataSource;
      this.changeDetectorRefs.detectChanges();
    });
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
      active: true,
      method: undefined,
      description: undefined
    }
  }

  public addRow() {
    const entity: IPaymentMethod = {
      id: 0,
      active: true,
      method: undefined,
      description: undefined
    }
    this.showForm(entity);
  }

  public showForm(entity: IPaymentMethod) {
    const dialogRef = this.dialog.open(PaymentMethodFormComponent, {
      width: '600px',
      disableClose: true,
      data: { entity: entity }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.paymentMethodService.save(result.entity).then(response => {
          this.setFilter();
        });
      }
    });
  }

  delete(entity: IPaymentMethod): void {
    const dialogRef = this.confirmationDialogService.showConfirmationDialog('¿Desea eliminar el registro?');
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.paymentMethodService.delete(entity).then(response => {
          const snacBarRef = this.snackBar.open(response.message, 'Deshacer', {
            duration: 3000
          });
          this.setFilter();

          snacBarRef.onAction().subscribe(() => {
            this.paymentMethodService.save(entity).then(response => {
              this.setFilter();
            });
          });
        });

      }
    });
  }

}
