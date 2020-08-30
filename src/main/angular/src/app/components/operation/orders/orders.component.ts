import { Component, OnInit } from '@angular/core';
import { IOrderViewFilter } from 'src/app/filters/order-filter';
import { IOrder } from 'src/app/interfaces/order';
import { Router } from '@angular/router';
import { SessionStorageService } from 'ngx-webstorage';
import { constants } from 'src/environments/environment';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.scss']
})
export class OrdersComponent implements OnInit {
  orders: IOrder[] = [];
  filter: IOrderViewFilter = {
    entity: {
      id: undefined,
      table: undefined,
      responsible: undefined,
      totalAmount: undefined
    },
    startDate: undefined,
    endDate: undefined,
    hidden: true,
    page: 0,
    rows: 20
  };
  constructor(private router: Router, private sessionStorageService: SessionStorageService,
    private orderService: OrderService) { }

  ngOnInit(): void {
    this.orderService.filter(this.filter).then(response => {
      this.orders = response.fields.data;
    });
  }

  newOrder() {
    const order: IOrder = {
      cashier: undefined,
      id: undefined,
      order_date: new Date(),
      payment_method: undefined,
      responsible: undefined,
      status: undefined,
      table: undefined,
      total_amount: 0,
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

  public open(order: IOrder) {
    this.sessionStorageService.store(constants.ORDER, order);
    this.router.navigate(['modules/orders/order']);
  }

  charge(order: IOrder) {

  }

  cancelOrder(order: IOrder) {

  }

}
