import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkstationFormComponent } from './workstation-form.component';

describe('WorkstationFormComponent', () => {
  let component: WorkstationFormComponent;
  let fixture: ComponentFixture<WorkstationFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WorkstationFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WorkstationFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
