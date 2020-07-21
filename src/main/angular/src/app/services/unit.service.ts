import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent } from '@angular/common/http';
import { SpinnerService } from './spinner.service';
import { SessionStorageService } from 'ngx-webstorage';
import { Service } from './service';
import { IUnitFilter } from '../filters/unit-filter';
import { IResponse } from '../interfaces/response';
import { IUnit } from '../interfaces/unit';

@Injectable({
  providedIn: 'root'
})
export class UnitService extends Service {

  private static UNIT_LIST = 'api/units';
  private static UNIT_SAVE = 'api/units/save';
  private static UNIT_DELETE = 'api/units/delete';



  constructor(protected http: HttpClient, protected spinner: SpinnerService, protected ps: SessionStorageService) {
    super(http, spinner, ps);
  }

  public filter(filter: IUnitFilter): Promise<IResponse> {
    return this.preparePromisePost(UnitService.UNIT_LIST, filter);
  }

  public save(unit: IUnit): Promise<IResponse> {
    return this.preparePromisePost(UnitService.UNIT_SAVE, unit);
  }

  public delete(unit: IUnit): Promise<IResponse> {
    return this.preparePromiseEntityPost(UnitService.UNIT_DELETE, unit);
  }

}