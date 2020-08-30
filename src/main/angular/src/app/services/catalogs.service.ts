import { Injectable } from '@angular/core';
import { Service } from './service';
import { HttpClient } from '@angular/common/http';
import { SpinnerService } from './spinner.service';
import { SessionStorageService } from 'ngx-webstorage';

@Injectable({
  providedIn: 'root'
})
export class CatalogsService extends Service {
  
  

  private static COUNTRY_LIST = 'api/catalogs/countries';
  private static STATE_LIST = 'api/catalogs/states';
  private static SHIFT_LIST = 'api/catalogs/shifts';
  private static BRAND_LIST = 'api/catalogs/brands';
  private static UNIT_LIST = 'api/catalogs/units';
  private static TAX_TYPE_LIST = 'api/catalogs/tax/types';
  private static SECTION_LIST = 'api/catalogs/sections';
  private static STORE_LIST = 'api/catalogs/stores';
  private static PRODUCT_TYPE_LIST = 'api/catalogs/product/types';
  private static PAYMENT_METHOD_LIST = 'api/catalogs/methods';
  private static PRODUCT_LIST = 'api/catalogs/products';
  private static TABLE_LIST = 'api/catalogs/tables';
  private static PRINTER_LIST = 'api/catalogs/printers';
  

  constructor(protected http: HttpClient, protected spinner: SpinnerService, protected ps: SessionStorageService) {
    super(http, spinner, ps);
  }

  public countries(): Promise<any> {
    return this.prepareGet(CatalogsService.COUNTRY_LIST);
  }

  public states(): Promise<any> {
    return this.prepareGet(CatalogsService.STATE_LIST);
  }

  public printers(): Promise<any> {
    return this.prepareGet(CatalogsService.PRINTER_LIST);
  }

  public shifts(): Promise<any> {
    return this.prepareGet(CatalogsService.SHIFT_LIST);
  }

  public getBrands(): Promise<any> {
    return this.prepareGet(CatalogsService.BRAND_LIST);
  }

  public getUnits(): Promise<any> {
    return this.prepareGet(CatalogsService.UNIT_LIST);
  }
  public getTaxTypes(): Promise<any> {
    return this.prepareGet(CatalogsService.TAX_TYPE_LIST);
  }
  public getSections(): Promise<any> {
    return this.prepareGet(CatalogsService.SECTION_LIST);
  }

  public getStores(): Promise<any> {
    return this.prepareGet(CatalogsService.STORE_LIST);
  }

  public getProductTypes(): Promise<any> {
    return this.prepareGet(CatalogsService.PRODUCT_TYPE_LIST);
  }

  public getProducts(): Promise<any> {
    return this.prepareGet(CatalogsService.PRODUCT_LIST);
  }

  public getTables(): Promise<any> {
    return this.prepareGet(CatalogsService.TABLE_LIST);
  }

  public getPaymentMethods(): Promise<any> {
    return this.prepareGet(CatalogsService.PAYMENT_METHOD_LIST);
  }
  

}
