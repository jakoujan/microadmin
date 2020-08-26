import { IProduct } from "./product";
export interface IItemKit {
    id: number;
    product: IProduct;
    quantity: number;
}
