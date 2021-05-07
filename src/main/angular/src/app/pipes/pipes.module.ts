import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CustomerPipe } from './customer.pipe';
import { CustomerTypePipe } from './customer-type.pipe';
import { LeadingZerosPipe } from './leading-zeros.pipe';
import { OrderStatusPipe } from './order-status.pipe';



@NgModule({
  declarations: [
    CustomerPipe,
    CustomerTypePipe,
    LeadingZerosPipe,
    OrderStatusPipe
  ],
  exports: [
    CustomerPipe,
    CustomerTypePipe,
    LeadingZerosPipe,
    OrderStatusPipe
  ],
  imports: [
    CommonModule
  ]
})
export class PipesModule { }
