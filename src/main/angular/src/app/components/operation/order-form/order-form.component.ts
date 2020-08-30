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

@Component({
  selector: 'app-order-form',
  templateUrl: './order-form.component.html',
  styleUrls: ['./order-form.component.scss']
})
export class OrderFormComponent implements OnInit {

  form: FormGroup;
  generalForm: FormGroup;

  @SessionStorage(constants.ORDER)
  order: IOrder;

  @SessionStorage(constants.SESSION)
  session: Session;

  servicesTypes: Array<IServiceType> = SERVICE_TYPES;
  tables: Array<ITable> = [];
  paymentMethods: Array<IPaymentMethod> = [];

  constructor(private formBuilder: FormBuilder, private catalogService: CatalogsService, 
    private router: Router, private productSelectionService: ProductSelectionService, 
    private orderService: OrderService, private confirmationDialogService: ConfirmationDialogService) { }

  ngOnInit(): void {
    this.catalogService.getTables().then(tables => this.tables = tables);
    this.catalogService.getPaymentMethods().then(paymentMethods => this.paymentMethods = paymentMethods);
    this.generalForm = this.formBuilder.group({
      responsible: [this.order.responsible],
      table: [this.order.table, Validators.required],
      serviceType: [this.order.serviceType, Validators.required],
      paymentMethod: [this.order.payment_method, Validators.required],
    });

    this.form = this.formBuilder.group({
      general: this.generalForm
    });
  }

  closeOrder() {

  }

  return() {
    this.router.navigate(['modules/orders']);
  }

  public addProduct() {
    const dialogRef = this.productSelectionService.select();
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        const product: IProduct = result;
        this.order.products.push({
          id: undefined,
          order: undefined,
          product: product,
          quantity: 1
        });
      }
    });
  }

  public add(product: IProductOrder) {
    product.quantity = product.quantity + 1;
  }

  public removeProduct(product: IProductOrder) {
    if(product.quantity >= 2) {
      product.quantity = product.quantity - 1;
    } else {
      this.order.products.splice(this.order.products.indexOf(product), 1);
    }
  }

  public save() {
    this.order.responsible = this.generalForm.get('responsible').value;
    this.order.table = this.generalForm.get('table').value;
    this.order.serviceType = this.generalForm.get('serviceType').value;
    this.order.payment_method = this.generalForm.get('paymentMethod').value;
    this.order.cashier = this.session.user;
    this.order.waiter = this.session.user;
    this.order.order_date = new Date();
    this.order.status = {
      id: 1,
      name: ''
    }


    this.orderService.save(this.order).then(response => {
      if(response.code === 0) {
        const dialogResult = this.confirmationDialogService.showConfirmationDialog(response.message, '350px', 'Aceptar');
        dialogResult.afterClosed().subscribe(result => {
          this.router.navigate(['modules/orders']);
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

}
