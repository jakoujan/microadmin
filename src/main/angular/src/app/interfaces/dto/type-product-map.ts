import { IProductType } from "../product-type";
import { IProduct } from "../product";
export interface ITypeProductMap {
    type: IProductType;
    products: Array<IProduct>
}
