import { Injectable } from '@angular/core';
import { Service } from './service';
import { HttpClient } from '@angular/common/http';
import { SpinnerService } from './spinner.service';
import { SessionStorageService } from 'ngx-webstorage';
import { ICustomerFilter } from '../filters/customer-filter';
import { IResponse } from '../interfaces/response';
import { ICustomer } from '../interfaces/customer';

@Injectable({
  providedIn: 'root'
})
export class CustomerService extends Service {


  private static CUSTOMER_LIST = 'api/customers/';
  private static CUSTOMER_SAVE = '/api/customers/save';
  private static CUSTOMER_DELETE = '/api/customers/delete';
  private static CUSTOMER_ACTIVE = '/api/customers/active';



  constructor(protected http: HttpClient, protected spinner: SpinnerService, protected ps: SessionStorageService) {
    super(http, spinner, ps);
  }

  public filter(filter: ICustomerFilter): Promise<IResponse> {
    return this.preparePromisePost(CustomerService.CUSTOMER_LIST, filter);
  }

  public save(user: ICustomer): Promise<IResponse> {
    return this.preparePromisePost(CustomerService.CUSTOMER_SAVE, user);
  }

  public delete(user: ICustomer): Promise<IResponse> {
    return this.preparePromisePost(CustomerService.CUSTOMER_DELETE, user);
  }

  public actives(): Promise<IResponse> {
    return this.preparePromiseGet(CustomerService.CUSTOMER_ACTIVE);
  }

  public activesAndType(customerType: number): Promise<IResponse> {
    const params = [
      { name: 'ct', value: customerType }
    ]
    return this.preparePromiseGet(CustomerService.CUSTOMER_ACTIVE, params);
  }
}
