import { Injectable } from '@angular/core';
import { Service } from './service';
import { HttpClient } from '@angular/common/http';
import { SpinnerService } from './spinner.service';
import { SessionStorageService } from 'ngx-webstorage';
import { ISupplierFilter } from '../filters/supplier-filter';
import { IResponse } from '../interfaces/response';
import { ISupplier } from '../interfaces/supplier';

@Injectable({
  providedIn: 'root'
})
export class SupplierService extends Service {


  private static CUSTOMER_LIST = 'api/suppliers/';
  private static CUSTOMER_SAVE = '/api/suppliers/save';
  private static CUSTOMER_DELETE = '/api/suppliers/delete';
  private static CUSTOMER_ACTIVE = '/api/suppliers/active';



  constructor(protected http: HttpClient, protected spinner: SpinnerService, protected ps: SessionStorageService) {
    super(http, spinner, ps);
  }

  public filter(filter: ISupplierFilter): Promise<IResponse> {
    return this.preparePromisePost(SupplierService.CUSTOMER_LIST, filter);
  }

  public save(user: ISupplier): Promise<IResponse> {
    return this.preparePromisePost(SupplierService.CUSTOMER_SAVE, user);
  }

  public delete(user: ISupplier): Promise<IResponse> {
    return this.preparePromisePost(SupplierService.CUSTOMER_DELETE, user);
  }

  public actives(): Promise<IResponse> {
    return this.preparePromiseGet(SupplierService.CUSTOMER_ACTIVE);
  }

  public activesAndType(supplierType: number): Promise<IResponse> {
    const params = [
      { name: 'ct', value: supplierType }
    ]
    return this.preparePromiseGet(SupplierService.CUSTOMER_ACTIVE, params);
  }
}
