import { Injectable } from '@angular/core';
import { Service } from './service';
import { HttpClient } from '@angular/common/http';
import { SpinnerService } from './spinner.service';
import { SessionStorageService } from 'ngx-webstorage';
import { IResponse } from '../interfaces/response';
import { IConfiguration } from '../interfaces/configuration';

@Injectable({
  providedIn: 'root'
})
export class ConfigurationService extends Service {

  private static CONFIGURATION = '/api/configuration/';
  private static CONFIGURATION_SAVE = '/api/configuration/save';


  constructor(protected http: HttpClient, protected spinner: SpinnerService, protected ps: SessionStorageService) {
    super(http, spinner, ps);
  }

  public load(): Promise<IResponse> {
    return this.preparePromiseGet(ConfigurationService.CONFIGURATION);
  }

  public save(configuration: IConfiguration): Promise<IResponse> {
    return this.preparePromisePost(ConfigurationService.CONFIGURATION_SAVE, configuration);
  }
}
