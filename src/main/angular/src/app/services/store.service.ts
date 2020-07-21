import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent } from '@angular/common/http';
import { SpinnerService } from './spinner.service';
import { SessionStorageService } from 'ngx-webstorage';
import { Service } from './service';
import { IStoreFilter } from '../filters/store-filter';
import { IResponse } from '../interfaces/response';
import { IStore } from '../interfaces/store';

@Injectable({
  providedIn: 'root'
})
export class StoreService extends Service {

  private static STORE_LIST = 'api/stores';
  private static STORE = 'api/stores/store';
  private static STORE_SAVE = 'api/stores/save';
  private static STORE_DELETE = 'api/stores/delete';



  constructor(protected http: HttpClient, protected spinner: SpinnerService, protected ps: SessionStorageService) {
    super(http, spinner, ps);
  }

  public filter(filter: IStoreFilter): Promise<IResponse> {
    return this.preparePromisePost(StoreService.STORE_LIST, filter);
  }

  public save(store: IStore): Promise<IResponse> {
    return this.preparePromisePost(StoreService.STORE_SAVE, store);
  }

  public delete(store: IStore): Promise<IResponse> {
    return this.preparePromiseEntityPost(StoreService.STORE_DELETE, store);
  }

  public getStore(id: number): Promise<IResponse> {
    const params = [
      {
        name: 'id',
        value: id
      }
    ];
    return this.preparePromiseGet(StoreService.STORE, params);
  }

}