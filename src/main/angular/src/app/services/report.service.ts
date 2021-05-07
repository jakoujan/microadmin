import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SessionStorageService } from 'ngx-webstorage';
import { ICashCountFilter } from '../filters/cash-count-filter';
import { IResponse } from '../interfaces/response';
import { Service } from './service';
import { SpinnerService } from './spinner.service';

@Injectable({
  providedIn: 'root'
})
export class ReportService extends Service {

  private static CASH_COUNT = 'api/reports/cashcount';
 
  constructor(protected http: HttpClient, protected spinner: SpinnerService, protected ps: SessionStorageService) {
    super(http, spinner, ps);
  }
  
  public cashCount(filter: ICashCountFilter): Promise<IResponse> {
    return this.preparePromisePost(ReportService.CASH_COUNT, filter);
  }
}
