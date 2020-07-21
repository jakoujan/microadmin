import { IState } from './state';
import { ICountry } from './country';


export interface ISupplier {
  id: number;
  supplierType: number;
  business_name: string;
  tax_id: string;
  contact: string;
  telephone: string;
  email: string;
  street: string;
  internal_number: string;
  external_number: string;
  settlement: string;
  city: string;
  county: string;
  state: IState;
  postal_code: string;
  country: ICountry;
}
