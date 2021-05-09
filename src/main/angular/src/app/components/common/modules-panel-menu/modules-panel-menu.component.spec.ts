import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { ModulesPanelMenuComponent } from './modules-panel-menu.component';

describe('ModulesPanelMenuComponent', () => {
  let component: ModulesPanelMenuComponent;
  let fixture: ComponentFixture<ModulesPanelMenuComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ ModulesPanelMenuComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModulesPanelMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
