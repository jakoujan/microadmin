import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SpinnerService } from './spinner.service';
import { SessionStorageService } from 'ngx-webstorage';
import { Service } from './service';
import { IProductTypeFilter } from '../filters/product-type-filter';
import { IResponse } from '../interfaces/response';
import { IProductType } from '../interfaces/product-type';

@Injectable({
  providedIn: 'root'
})
export class ProductTypeService extends Service {

  private static PRODUCT_TYPE_LIST = 'api/product/types';
  private static PRODUCT_TYPE_SAVE = 'api/product/types/save';
  private static PRODUCT_TYPE_DELETE = 'api/product/types/delete';



  constructor(protected http: HttpClient, protected spinner: SpinnerService, protected ps: SessionStorageService) {
    super(http, spinner, ps);
  }

  public filter(filter: IProductTypeFilter): Promise<IResponse> {
    return this.preparePromisePost(ProductTypeService.PRODUCT_TYPE_LIST, filter);
  }

  public save(productType: IProductType): Promise<IResponse> {
    return this.preparePromisePost(ProductTypeService.PRODUCT_TYPE_SAVE, productType);
  }

  public delete(productType: IProductType): Promise<IResponse> {
    return this.preparePromisePost(ProductTypeService.PRODUCT_TYPE_DELETE, productType);
  }

}