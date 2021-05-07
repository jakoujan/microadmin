import { IOrder } from "./order";
import { IProduct } from "./product";

export interface IProductOrder {
    id: number;
    order: IOrder;
    product: IProduct;
    quantity: number;
    comment: string;
}
