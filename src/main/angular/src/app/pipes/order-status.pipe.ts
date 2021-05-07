import { Pipe, PipeTransform } from '@angular/core';
import { SALE_STATUS } from '../catalogs/catalogs';
import { ISaleStatus } from '../interfaces/sale-status';

@Pipe({
  name: 'orderStatus'
})
export class OrderStatusPipe implements PipeTransform {

  transform(value: ISaleStatus): string {
    SALE_STATUS.some(status => {
      
    });

    return
  }

}
