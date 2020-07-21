import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent } from '@angular/common/http';
import { SpinnerService } from './spinner.service';
import { SessionStorageService } from 'ngx-webstorage';
import { Service } from './service';
import { IProductFilter, IProductViewFilter } from '../filters/product-filter';
import { IResponse } from '../interfaces/response';
import { IProduct } from '../interfaces/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService extends Service {

  private static PRODUCT_LIST = 'api/products';
  private static PRODUCT = 'api/products/product';
  private static PRODUCT_SAVE = 'api/products/save';
  private static PRODUCT_DELETE = 'api/products/delete';



  constructor(protected http: HttpClient, protected spinner: SpinnerService, protected ps: SessionStorageService) {
    super(http, spinner, ps);
  }

  public filter(filter: IProductViewFilter): Promise<IResponse> {
    return this.preparePromisePost(ProductService.PRODUCT_LIST, filter);
  }

  public save(product: IProduct): Promise<IResponse> {
    return this.preparePromisePost(ProductService.PRODUCT_SAVE, product);
  }

  public delete(product: IProduct): Promise<IResponse> {
    return this.preparePromiseEntityPost(ProductService.PRODUCT_DELETE, product);
  }

  public getProduct(id: number): Promise<IResponse> {
    const params = [
      {
        name: 'id',
        value: id
      }
    ];
    return this.preparePromiseGet(ProductService.PRODUCT, params);
  }

}