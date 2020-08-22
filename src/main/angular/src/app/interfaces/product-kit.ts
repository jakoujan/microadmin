import { IProduct } from "./product";
import { IItemKit } from "./item-kit";

export interface IProductKit {
    id: number;
    items: Array<IItemKit>;
}
