import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent } from '@angular/common/http';
import { SpinnerService } from './spinner.service';
import { SessionStorageService } from 'ngx-webstorage';
import { Service } from './service';
import { ITableFilter } from '../filters/table-filter';
import { IResponse } from '../interfaces/response';
import { ITable } from '../interfaces/table';

@Injectable({
  providedIn: 'root'
})
export class TableService extends Service {

  private static TABLE_LIST = 'api/tables';
  private static TABLE_SAVE = 'api/tables/save';
  private static TABLE_DELETE = 'api/tables/delete';



  constructor(protected http: HttpClient, protected spinner: SpinnerService, protected ps: SessionStorageService) {
    super(http, spinner, ps);
  }

  public filter(filter: ITableFilter): Promise<IResponse> {
    return this.preparePromisePost(TableService.TABLE_LIST, filter);
  }

  public save(table: ITable): Promise<IResponse> {
    return this.preparePromisePost(TableService.TABLE_SAVE, table);
  }

  public delete(table: ITable): Promise<IResponse> {
    return this.preparePromiseEntityPost(TableService.TABLE_DELETE, table);
  }

}