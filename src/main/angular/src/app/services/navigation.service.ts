import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { Submodule } from '../modules';
import { SessionStorageService } from 'ngx-webstorage';
import { constants } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class NavigationService {

  private navigator = new Subject<Submodule>();

  public $navigator = this.navigator.asObservable()

  constructor(private sessionStorage: SessionStorageService) {

  }

  public navigate(submodule: Submodule) {
    this.navigator.next(submodule);
    this.sessionStorage.store(constants.MODULE, submodule);
  }
}
