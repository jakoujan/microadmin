import { IFilter } from './filter';
import { IOrder } from "src/app/interfaces/order";
import { IOrderView } from "src/app/interfaces/view/order-view";

export interface IOrderFilter extends IFilter<IOrder> {
}


export interface IOrderViewFilter extends IFilter<IOrderView> {
}

