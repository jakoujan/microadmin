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

  servicesTypes: Array<IServiceType> = SERVICE_TYPES;
  tables: Array<ITable> = [];
  constructor(private formBuilder: FormBuilder, private catalogService: CatalogsService, private router: Router, private productSelectionService: ProductSelectionService) { }

  ngOnInit(): void {
    this.catalogService.getTables().then(tables => this.tables = tables);
    this.generalForm = this.formBuilder.group({
      responsible: [this.order.responsible],
      table: [this.order.table, Validators.required],
      serviceType: [this.order.serviceType, Validators.required],
      paymentMethod: [this.order.paymentMethod, Validators.required],
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

  addProduct() {
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

  public save() {
    
  }

}
