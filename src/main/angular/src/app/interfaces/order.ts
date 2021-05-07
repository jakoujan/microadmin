import { IUser } from './user';
import { IProductOrder } from "./product-order";
import { IPaymentMethod } from './payment-method';
import { ITable } from './table';
import { ISaleStatus } from "./sale-status";

export interface IOrder {
    id: number;
    order_date: Date;
    responsible: string;
    waiter: IUser;
    cashier: IUser;
    table: ITable;
    payment_method: IPaymentMethod;
    status: ISaleStatus;
    total_amount: number;
    serviceType: number;
    products: Array<IProductOrder>;
}
