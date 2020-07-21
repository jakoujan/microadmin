import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent } from '@angular/common/http';
import { SpinnerService } from './spinner.service';
import { SessionStorageService } from 'ngx-webstorage';
import { Service } from './service';
import { IWorkstationFilter } from '../filters/workstation-filter';
import { IResponse } from '../interfaces/response';
import { IWorkstation } from '../interfaces/workstation';

@Injectable({
  providedIn: 'root'
})
export class WorkstationService extends Service {

  private static WORKSTATION_LIST = 'api/workstations';
  private static WORKSTATION = 'api/workstations/workstation';
  private static WORKSTATION_SAVE = 'api/workstations/save';
  private static WORKSTATION_DELETE = 'api/workstations/delete';



  constructor(protected http: HttpClient, protected spinner: SpinnerService, protected ps: SessionStorageService) {
    super(http, spinner, ps);
  }

  public filter(filter: IWorkstationFilter): Promise<IResponse> {
    return this.preparePromisePost(WorkstationService.WORKSTATION_LIST, filter);
  }

  public save(workstation: IWorkstation): Promise<IResponse> {
    return this.preparePromisePost(WorkstationService.WORKSTATION_SAVE, workstation);
  }

  public delete(workstation: IWorkstation): Promise<IResponse> {
    return this.preparePromiseEntityPost(WorkstationService.WORKSTATION_DELETE, workstation);
  }

  public getWorkstation(id: number): Promise<IResponse> {
    const params = [
      {
        name: 'id',
        value: id
      }
    ];
    return this.preparePromiseGet(WorkstationService.WORKSTATION, params);
  }

}