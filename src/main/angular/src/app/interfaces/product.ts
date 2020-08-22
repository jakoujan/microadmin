import { IBrand } from './brand';
import { IUnit } from './unit';
import { ISection } from './section';
import { ITaxType } from './tax-type';
import { IProductType } from './product-type';
import { IProductKit } from "./product-kit";

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
    type: IProductType;
    kitProducts: Array<IProductKit>;
}
