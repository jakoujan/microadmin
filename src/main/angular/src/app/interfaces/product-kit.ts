import { IProduct } from "./product";
import { IItemKit } from "./item-kit";

export interface IProductKit {
    items: Array<IItemKit>;
}
