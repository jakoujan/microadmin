import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent } from '@angular/common/http';
import { SpinnerService } from './spinner.service';
import { SessionStorageService } from 'ngx-webstorage';
import { Service } from './service';
import { IBrandFilter } from '../filters/brand-filter';
import { IResponse } from '../interfaces/response';
import { IBrand } from '../interfaces/brand';

@Injectable({
  providedIn: 'root'
})
export class BrandService extends Service {

  private static BRAND_LIST = 'api/brands';
  private static BRAND_SAVE = 'api/brands/save';
  private static BRAND_DELETE = 'api/brands/delete';



  constructor(protected http: HttpClient, protected spinner: SpinnerService, protected ps: SessionStorageService) {
    super(http, spinner, ps);
  }

  public filter(filter: IBrandFilter): Promise<IResponse> {
    return this.preparePromisePost(BrandService.BRAND_LIST, filter);
  }

  public save(brand: IBrand): Promise<IResponse> {
    return this.preparePromisePost(BrandService.BRAND_SAVE, brand);
  }

  public delete(brand: IBrand): Promise<IResponse> {
    return this.preparePromiseEntityPost(BrandService.BRAND_DELETE, brand);
  }

}
