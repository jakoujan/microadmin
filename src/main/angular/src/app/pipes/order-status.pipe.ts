import { Pipe, PipeTransform } from '@angular/core';
import { ISaleStatus } from '../interfaces/sale-status';

@Pipe({
  name: 'orderStatus'
})
export class OrderStatusPipe implements PipeTransform {

  transform(value: ISaleStatus): string {
    return value.name;
  }

}
