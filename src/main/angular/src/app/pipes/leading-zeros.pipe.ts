import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'leadingZeros'
})
export class LeadingZerosPipe implements PipeTransform {

  transform(value: number, places: number): string {
    places = places ? places : 4;
    return this.zeroPad(value, places);
  }

  public zeroPad(value: number, places: number): string {
    var zero = places - value.toString().length + 1;
    return Array(+(zero > 0 && zero)).join("0") + value;
  }




}
