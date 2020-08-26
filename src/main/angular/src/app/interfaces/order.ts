import { IUser } from './user';
import { IProductOrder } from "./product-order";

export interface IOrder {
    id: number;
    orderDate: Date;
    responsible: string;
    waiter: IUser;
    cashier: IUser;
    table: number;
    paymentMethod: number;
    status: number;
    totalAmount: number;
    serviceType: number;
    products: Array<IProductOrder>;
}
