import { IProduct } from "./product";
import { IItemKit } from "./item-kit";

export interface IProductKit {
    id: number;
    product: IProduct;
    items: Array<IItemKit>
}
