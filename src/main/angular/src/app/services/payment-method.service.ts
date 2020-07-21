import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SpinnerService } from './spinner.service';
import { SessionStorageService } from 'ngx-webstorage';
import { Service } from './service';
import { IPaymentMethodFilter } from '../filters/payment-method-filter';
import { IResponse } from '../interfaces/response';
import { IPaymentMethod } from '../interfaces/payment-method';

@Injectable({
  providedIn: 'root'
})
export class PaymentMethodService extends Service {

  private static PAYMENT_METHOD_LIST = 'api/payment/method';
  private static PAYMENT_METHOD_SAVE = 'api/payment/method/save';
  private static PAYMENT_METHOD_DELETE = 'api/payment/method/delete';



  constructor(protected http: HttpClient, protected spinner: SpinnerService, protected ps: SessionStorageService) {
    super(http, spinner, ps);
  }

  public filter(filter: IPaymentMethodFilter): Promise<IResponse> {
    return this.preparePromisePost(PaymentMethodService.PAYMENT_METHOD_LIST, filter);
  }

  public save(paymentMethod: IPaymentMethod): Promise<IResponse> {
    return this.preparePromisePost(PaymentMethodService.PAYMENT_METHOD_SAVE, paymentMethod);
  }

  public delete(paymentMethod: IPaymentMethod): Promise<IResponse> {
    return this.preparePromiseEntityPost(PaymentMethodService.PAYMENT_METHOD_DELETE, paymentMethod);
  }

}