import { Component, OnInit } from '@angular/core';
import { IOrderFilter } from 'src/app/filters/order-filter';
import { IOrder } from 'src/app/interfaces/order';
import { Router } from '@angular/router';
import { SessionStorageService } from 'ngx-webstorage';
import { constants } from 'src/environments/environment';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.scss']
})
export class OrdersComponent implements OnInit {
  orders: IOrder[] = [];
  filter: IOrderFilter = {
    entity: {
      id: undefined,
      orderDate: undefined,
      responsible: undefined,
      waiter: undefined,
      cashier: undefined,
      table: undefined,
      paymentMethod: undefined,
      status: undefined,
      totalAmount: undefined,
      serviceType: 1,
      products: undefined
    },
    startDate: undefined,
    endDate: undefined,
    hidden: true,
    page: 0,
    rows: 20
  };
  constructor(private router: Router, private sessionStorageService: SessionStorageService) { }

  ngOnInit(): void {
  }

  newOrder() {
    const order: IOrder = {
      cashier: undefined,
      id: undefined,
      orderDate: new Date(),
      paymentMethod: undefined,
      responsible: undefined,
      status: undefined,
      table: undefined,
      totalAmount: 0,
      waiter: undefined,
      serviceType: 1,
      products: []
    }
    this.sessionStorageService.store(constants.ORDER, order);
    this.router.navigate(['modules/orders/order']);
  }

  toggleSearchBar() {
    this.filter.hidden = !this.filter.hidden;
  }

  setFilter() {

  }

  public pay(order: IOrder) {
    console.log(order);
  }

  charge(order: IOrder) {

  }

  cancelOrder(order: IOrder) {

  }

}
