import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { WorkstationFormComponent } from './workstation-form.component';

describe('WorkstationFormComponent', () => {
  let component: WorkstationFormComponent;
  let fixture: ComponentFixture<WorkstationFormComponent>;

  beforeEach(waitForAsync(() => {
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
