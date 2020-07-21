import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { KeyboardDirective } from './keyboard.directive';



@NgModule({
  declarations: [
    KeyboardDirective
  ],
  imports: [
    CommonModule
  ],
  exports: [
    KeyboardDirective
  ]
})
export class DirectivesModule { }
