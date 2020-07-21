import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent } from '@angular/common/http';
import { SpinnerService } from './spinner.service';
import { SessionStorageService } from 'ngx-webstorage';
import { Service } from './service';
import { ISectionFilter } from '../filters/section-filter';
import { IResponse } from '../interfaces/response';
import { ISection } from '../interfaces/section';

@Injectable({
  providedIn: 'root'
})
export class SectionService extends Service {

  private static SECTION_LIST = 'api/sections';
  private static SECTION_SAVE = 'api/sections/save';
  private static SECTION_DELETE = 'api/sections/delete';



  constructor(protected http: HttpClient, protected spinner: SpinnerService, protected ps: SessionStorageService) {
    super(http, spinner, ps);
  }

  public filter(filter: ISectionFilter): Promise<IResponse> {
    return this.preparePromisePost(SectionService.SECTION_LIST, filter);
  }

  public save(section: ISection): Promise<IResponse> {
    return this.preparePromisePost(SectionService.SECTION_SAVE, section);
  }

  public delete(section: ISection): Promise<IResponse> {
    return this.preparePromiseEntityPost(SectionService.SECTION_DELETE, section);
  }

}