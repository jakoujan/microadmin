import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductElaborationsComponent } from './product-elaborations.component';

describe('ProductElaborationsComponent', () => {
  let component: ProductElaborationsComponent;
  let fixture: ComponentFixture<ProductElaborationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProductElaborationsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductElaborationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
