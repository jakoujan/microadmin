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
import { IUser } from 'src/app/interfaces/user';
import { ICashCount } from "src/app/interfaces/request/cash-count";
@Component({
  selector: 'app-cash-count',
  templateUrl: './cash-count.component.html',
  styleUrls: ['./cash-count.component.scss']
})
export class CashCountComponent implements OnInit {
  orders: Array<IOrderView> = [];
  user: Array<IUser> = [];
  filter: ICashCount;
  subscription: Subscription;

  constructor(private router: Router) { }
  
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

  

  public compare(val1, val2) {
    return val1.id === val2.id;
  }

}
