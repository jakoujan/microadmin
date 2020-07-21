import { IBrand } from './brand';
import { IUnit } from './unit';
import { ISection } from './section';
import { ITaxType } from './tax-type';

export interface IProduct {
    id: number;
    barcode: string;
    brand: IBrand;
    unit: IUnit;
    description: string;
    longDescription: string;
    section: ISection;
    taxType: ITaxType;
    retailPrice: number;
    supplierPrice: number;
    promoPrice: number;
    promotion: boolean;
    minimumStock: number;
    active: boolean;
}
