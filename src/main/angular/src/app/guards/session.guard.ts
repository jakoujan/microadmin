import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { constants } from 'src/environments/environment';
import { Session } from '../interfaces/session';
import { SecurityService } from '../services/security.service';
import { validateProfile } from '../helpers/helpers';
import { SessionStorage } from 'ngx-webstorage';

@Injectable({
  providedIn: 'root'
})
export class SessionGuard implements CanActivate {

  @SessionStorage(constants.SESSION)
  session: Session;

  constructor(private router: Router, private securityService: SecurityService) {

  }

  canActivate(
    next: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    return this.session ? validateProfile(next.data, this.session.user.modules) : false;
  }

}
