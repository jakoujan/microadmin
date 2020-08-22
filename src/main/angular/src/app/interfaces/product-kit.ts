import { IProductKitId } from "./product-kit-id";
import { IProduct } from "./product";

export interface IProductKit {
    id: number;
    kit: IProduct;
    product: IProduct;
    quantity: number;
}
