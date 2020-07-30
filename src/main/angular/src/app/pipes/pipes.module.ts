import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CustomerPipe } from './customer.pipe';
import { CustomerTypePipe } from './customer-type.pipe';
import { LeadingZerosPipe } from './leading-zeros.pipe';



@NgModule({
  declarations: [
    CustomerPipe,
    CustomerTypePipe,
    LeadingZerosPipe
  ],
  exports: [
    CustomerPipe,
    CustomerTypePipe,
    LeadingZerosPipe
  ],
  imports: [
    CommonModule
  ]
})
export class PipesModule { }
