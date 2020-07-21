import { Pipe, PipeTransform } from '@angular/core';
import { ICustomer } from '../interfaces/customer';

@Pipe({
  name: 'customerType'
})
export class CustomerTypePipe implements PipeTransform {

  transform(value: ICustomer): string {
    return value.customerType === 1 ? 'Cliente' : 'Proveedor';
  }

}
