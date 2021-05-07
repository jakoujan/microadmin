import { Pipe, PipeTransform } from '@angular/core';
import { ICustomer } from '../interfaces/customer';

@Pipe({
  name: 'customer'
})
export class CustomerPipe implements PipeTransform {

  transform(value: ICustomer): string {
    return value ? value.business_name : '';
  }

}
