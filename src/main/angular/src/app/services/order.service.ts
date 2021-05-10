import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SpinnerService } from './spinner.service';
import { SessionStorageService } from 'ngx-webstorage';
import { Service } from './service';
import { IOrderViewFilter } from '../filters/order-filter';
import { IResponse } from '../interfaces/response';
import { IOrder } from '../interfaces/order';
import { IProductPreparation } from '../interfaces/view/product-preparation';

@Injectable({
  providedIn: 'root'
})
export class OrderService extends Service {


  private static ORDER = 'api/orders/order';
  private static ORDER_LIST = 'api/orders';
  private static ORDER_SAVE = 'api/orders/save';
  private static ORDER_DELETE = 'api/orders/delete';
  private static PRODUCT_ELABORATION = 'api/orders/elaboration/products';
  private static PRODUCT_ELABORATION_DONE = 'api/orders/elaboration/products/done';



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

  public getOrder(id: number): Promise<IResponse> {
    const params = [
      {
        name: 'id',
        value: id
      }
    ];
    return this.preparePromiseGet(OrderService.ORDER, params);
  }

  public productElaboration(params: Array<any>): Promise<IResponse> {
    return this.preparePromiseGet(OrderService.PRODUCT_ELABORATION, params);
  }

  public productElaborationDone(item: IProductPreparation): Promise<IResponse> {
    const params = [
      {
        name: 'id',
        value: item.id
      }
    ];
    return this.preparePromiseGet(OrderService.PRODUCT_ELABORATION_DONE, params);
  }
}
