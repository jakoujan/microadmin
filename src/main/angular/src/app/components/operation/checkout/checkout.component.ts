import { Component, OnInit } from '@angular/core';
import { IOrderViewFilter } from 'src/app/filters/order-filter';
import { IOrder } from 'src/app/interfaces/order';
import { IOrderView } from 'src/app/interfaces/view/order-view';
import { OrderService } from 'src/app/services/order.service';
import { SessionStorageService } from 'ngx-webstorage';
import { constants, environment } from 'src/environments/environment';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Message } from '@stomp/stompjs';
import { RxStompService } from '@stomp/ng2-stompjs';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.scss']
})
export class CheckoutComponent implements OnInit {
  orders: Array<IOrderView> = [];
  filter: IOrderViewFilter = {
    entity: {
      id: undefined,
      table: undefined,
      responsible: undefined,
      totalAmount: undefined,
      status: 3
    },
    startDate: undefined,
    endDate: undefined,
    hidden: true,
    page: 0,
    rows: 20
  };
  subscription: Subscription;

  constructor(private orderService: OrderService, private sessionStorageService: SessionStorageService, private router: Router,
    private rxStompService: RxStompService) { }

  ngOnInit(): void {
    this.orderService.filter(this.filter).then(response => {
      this.orders = response.fields.data;
    });
    this.subscription = this.rxStompService.watch(environment.websocket.topicPrefix).subscribe((message: Message) => {
      // const response = JSON.parse(message.body);
      this.orderService.filter(this.filter).then(response => {
        this.orders = response.fields.data;
      });
    });
  }

  initialStock() {

  }

  toggleSearchBar() {
    this.filter.hidden = !this.filter.hidden;
  }

  setFilter() {

  }

  public pay(order: IOrder) {
    console.log(order);
  }

  open(order: IOrderView) {
    this.orderService.getOrder(order.id).then(response => {
      const order: IOrder = response.fields.entity;
      this.sessionStorageService.store(constants.ORDER, order);
      this.router.navigate(['modules/checkout/order']);
    });
  }

}
