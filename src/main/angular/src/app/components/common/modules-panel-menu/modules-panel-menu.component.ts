import { Component, OnInit, Output, EventEmitter, ViewEncapsulation } from '@angular/core';
import { IModule } from 'src/app/interfaces/module';
import { Module, MODULES, Submodule } from 'src/app/modules';
import { SessionStorage } from 'ngx-webstorage';
import { constants } from 'src/environments/environment';
import { Session } from 'src/app/interfaces/session';
import { Router } from '@angular/router';
import { NavigationService } from 'src/app/services/navigation.service';

@Component({
  selector: 'modules-panel-menu',
  templateUrl: './modules-panel-menu.component.html',
  styleUrls: ['./modules-panel-menu.component.scss'],
})
export class ModulesPanelMenuComponent implements OnInit {

  @Output()
  onNavigate = new EventEmitter<void>();

  @SessionStorage(constants.SESSION)
  session: Session;

  modules: Array<Module> = [];

  constructor(private router: Router, private navigationService: NavigationService) { }

  ngOnInit(): void {
    this.modules = this.buildMenu(this.session.user.modules);
  }

  private buildMenu(permissions: Array<IModule>): Array<Module> {
    const modules: Array<Module> = [];
    const mods = MODULES.map(a => ({ ...a }));
    mods.forEach(module => {
      let permission: IModule;
      if (permissions.some(p => {
        permission = p;
        return p.id === module.id;
      })) {
        module.submodules = module.submodules.filter(sm => {
          return permission.submodules.some(s => sm.id === s.id);
        });
        modules.push(module);
      }
    });

    return modules;
  }

  public navigate(submodule: Submodule) {
    this.router.navigate([submodule.uri]).then(() => {
      this.onNavigate.emit();
      this.navigationService.navigate(submodule);
    });
  }
}
