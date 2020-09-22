import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { SessionStorage } from 'ngx-webstorage';
import { constants, environment } from 'src/environments/environment';
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
import { MatSelectChange } from '@angular/material/select';
import { RxStompService } from '@stomp/ng2-stompjs';

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
    private orderService: OrderService, private confirmationDialogService: ConfirmationDialogService, 
    private rxStompService: RxStompService) { }

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

  public goBack() {
    this.router.navigate(['modules/orders']);
  }

  public typeSelected(event: MatSelectChange) {
    switch (event.value) {
      case 2:
        this.generalForm.get('table').setValue({ id: 1 });
        this.generalForm.get('table').disable();
        break;
      default:
        this.generalForm.get('table').enable();
    }
  }

  public addProduct() {
    const dialogRef = this.productSelectionService.select();
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        const product: IProduct = result;

        const added = this.order.products.some(p => {
          if (p.product.id === product.id) {
            p.quantity = p.quantity + 1;
            return true;
          }
        })
        if (!added) {
          this.order.products.push({
            id: undefined,
            order: undefined,
            product: product,
            quantity: 1
          });
        }
      }
    });
  }

  public add(product: IProductOrder) {
    product.quantity = product.quantity + 1;
  }

  public removeProduct(product: IProductOrder) {
    if (product.quantity >= 2) {
      product.quantity = product.quantity - 1;
    } else {
      this.order.products.splice(this.order.products.indexOf(product), 1);
    }
  }

  public checkOrderStatus(): boolean {
    return this.order.status ? this.form.invalid : true;
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

  public save(status: number) {
    this.order.responsible = this.generalForm.get('responsible').value;
    this.order.table = this.generalForm.get('table').value;
    this.order.serviceType = this.generalForm.get('serviceType').value;
    this.order.payment_method = this.generalForm.get('paymentMethod').value;
    this.order.cashier = this.session.user;
    this.order.waiter = this.session.user;
    this.order.order_date = new Date();
    this.order.status = {
      id: status,
      name: ''
    }

    if (this.order.status.id === 3) {
      this.confirmationDialogService.showConfirmationDialog("<p>Se va a cerrar la comanda</p><p>¿desea continuar?</p><p><b>Indicar el total de la orden: $" + this.order.total_amount + "</b></p>",
        '350px', 'Aceptar', "Cancelar").afterClosed().subscribe(result => {
          if (result) {
            this.orderService.save(this.order).then(response => {
              if (response.code === 0) {
                this.confirmationDialogService.showConfirmationDialog(response.message, '350px', 'Aceptar').afterClosed().subscribe(result => {
                  this.router.navigate(['modules/orders']);
                });
              }
            });
          }
        });
    } else {
      this.orderService.save(this.order).then(response => {
        if (response.code === 0) {
          let dialogResult = this.confirmationDialogService.showConfirmationDialog(response.message, '350px', 'Aceptar');
          dialogResult.afterClosed().subscribe(result => {
            this.router.navigate(['modules/orders']);
          });
        }
      });
    }
  }

  private refreshOrders() {
    const configurator = {
      topic: environment.websocket.topicPrefix
    };
    const v = JSON.stringify(configurator);
    this.rxStompService.publish({ destination: environment.websocket.destination, body: v });
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
