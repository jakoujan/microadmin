import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { SessionStorage } from 'ngx-webstorage';
import { constants } from 'src/environments/environment';
import { IOrder } from 'src/app/interfaces/order';
import { IServiceType } from "src/app/interfaces/service-type";
import { SERVICE_TYPES } from "src/app/catalogs/catalogs";
import { ITable } from 'src/app/interfaces/table';
import { CatalogsService } from 'src/app/services/catalogs.service';
import { Router } from '@angular/router';
import { ProductSelectionService } from "../../custom/product-selection/product-selection.service";
import { IProduct } from 'src/app/interfaces/product';
import { IProductOrder } from 'src/app/interfaces/product-order';
import { IPaymentMethod } from 'src/app/interfaces/payment-method';
import { OrderService } from "src/app/services/order.service";
import { ConfirmationDialogService } from '../../common/ui/confirmation-dialog/confirmation-dialog.service';
import { Session } from 'src/app/interfaces/session';
import { onlyNumber } from 'src/app/helpers/validation-helpers';

@Component({
  selector: 'app-checkout-form',
  templateUrl: './checkout-form.component.html',
  styleUrls: ['./checkout-form.component.scss']
})
export class CheckoutFormComponent implements OnInit {

  form: FormGroup;

  @SessionStorage(constants.ORDER)
  order: IOrder;

  @SessionStorage(constants.SESSION)
  session: Session;


  constructor(private formBuilder: FormBuilder, private router: Router,
    private orderService: OrderService, private confirmationDialogService: ConfirmationDialogService) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      amount: [undefined, Validators.compose([Validators.required, onlyNumber, Validators.min(this.order.total_amount)])]
    })
  }

  public goBack() {
    this.router.navigate(['modules/checkout']);
  }

  public cancel() {
    let dialogResult = this.confirmationDialogService.showConfirmationDialog("¿Seguro desea cancelar la comanda?", '350px', 'Aceptar');
    dialogResult.afterClosed().subscribe(result => {
      if (result) {
        this.order.status = {
          id: 3,
          name: undefined,
        }
        this.orderService.save(this.order).then(response => {
          dialogResult = this.confirmationDialogService.showConfirmationDialog("La orden se cancelo correctamente", '350px', 'Aceptar');
          dialogResult.afterClosed().subscribe(result => {
            this.router.navigate(['modules/orders']);
          });
        });
      }
    });
  }

  public reopen() {

  }

  public checkout(status?: number) {
    this.order.cashier = this.session.user;
    this.order.status = {
      id: 4,
      name: undefined
    }
    this.orderService.save(this.order).then(response => {
      if (response.code === 0) {
        let dialogResult = this.confirmationDialogService.showConfirmationDialog(response.message, '350px', 'Aceptar');
        dialogResult.afterClosed().subscribe(result => {
          this.router.navigate(['modules/checkout']);
        });
      }
    });
  }

  public get totalAmount(): number {
    this.order.total_amount = 0;
    this.order.products.forEach((product: IProductOrder) => {
      this.order.total_amount = (product.quantity * product.product.retailPrice) + this.order.total_amount;
    });
    return this.order.total_amount;
  }

  public compare(val1, val2) {
    return val1.id === val2.id;
  }

  public orderTypeData(id: number): string {
    let type: IServiceType;
    SERVICE_TYPES.some(st => {
      if (st.id === id) {
        type = st;
        return true;
      }
    })
    return type.description;
  }

  public get change(): number {
    return this.form.get('amount').value - this.order.total_amount;
  }

}
