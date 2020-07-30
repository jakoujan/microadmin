import { IUser } from './user';

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
}
