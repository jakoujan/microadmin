import { Component, OnInit, ViewChild } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { Router } from '@angular/router';
import { SessionStorage } from 'ngx-webstorage';
import { constants } from 'src/environments/environment';
import { Session } from 'src/app/interfaces/session';
import { Module, Submodule } from 'src/app/modules';
import { NavigationService } from 'src/app/services/navigation.service';
import { MatIconRegistry } from '@angular/material/icon';
import { DomSanitizer } from '@angular/platform-browser';
import { MatSidenav } from '@angular/material/sidenav';
import { KeyboardService } from 'src/app/services/keyboard.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent implements OnInit {

  @SessionStorage(constants.SESSION)
  session: Session;

  @SessionStorage(constants.MODULE)
  submodule: Submodule;

  @ViewChild('drawer')
  drawer: MatSidenav;

  modules: Array<Module> = [];

  shk: boolean = false;

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(map(result => result.matches), shareReplay());

  constructor(private breakpointObserver: BreakpointObserver, private router: Router, private navigationService: NavigationService, 
    iconRegistry: MatIconRegistry, sanitizer: DomSanitizer, private keyboardService: KeyboardService) {
    iconRegistry.addSvgIcon('scale', sanitizer.bypassSecurityTrustResourceUrl('assets/images/scale.svg'));
    iconRegistry.addSvgIcon('logo', sanitizer.bypassSecurityTrustResourceUrl('assets/images/logo.svg'));
    iconRegistry.addSvgIcon('acc-m', sanitizer.bypassSecurityTrustResourceUrl('assets/images/account-multiple.svg'));
    iconRegistry.addSvgIcon('fill', sanitizer.bypassSecurityTrustResourceUrl('assets/images/format-color-fill.svg'));

    this.keyboardService.$controller.subscribe(value => {
      this.shk = value
    });
  }

  ngOnInit(): void {


    if (this.submodule) {
      this.navigationService.navigate(this.submodule);
    }
  }

  public goto(action: string) {
    this.router.navigate([action]);
  }

  public onNavigate() {
    this.drawer.toggle();
  }

  public activateKeyboard() {
    this.keyboardService.setVisible(!this.keyboardService.isVisible());
  }

  public priceVerifier() {
    
  }

  public get keyboard() {
    return this.keyboardService.isVisible() ? 'keyboard_hide' : 'keyboard';
  }

  public closeKeyboard() {
    this.keyboardService.change(false);
  }
}
