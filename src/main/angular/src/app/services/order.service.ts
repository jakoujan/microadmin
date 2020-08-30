import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SpinnerService } from './spinner.service';
import { SessionStorageService } from 'ngx-webstorage';
import { Service } from './service';
import { IOrderViewFilter } from '../filters/order-filter';
import { IResponse } from '../interfaces/response';
import { IOrder } from '../interfaces/order';

@Injectable({
  providedIn: 'root'
})
export class OrderService extends Service {

  private static ORDER_LIST = 'api/orders';
  private static ORDER_SAVE = 'api/orders/save';
  private static ORDER_DELETE = 'api/orders/delete';



  constructor(protected http: HttpClient, protected spinner: SpinnerService, protected ps: SessionStorageService) {
    super(http, spinner, ps);
  }

  public filter(filter: IOrderViewFilter): Promise<IResponse> {
    return this.preparePromisePost(OrderService.ORDER_LIST, filter);
  }

  public save(order: IOrder): Promise<IResponse> {
    return this.preparePromisePost(OrderService.ORDER_SAVE, order);
  }

  public delete(order: IOrder): Promise<IResponse> {
    return this.preparePromiseEntityPost(OrderService.ORDER_DELETE, order);
  }
}
