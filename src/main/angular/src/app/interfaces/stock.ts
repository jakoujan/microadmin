import { IProduct } from './product';
import { IStore } from './configuration';

export interface IStock {
    id: number;
    product: IProduct;
    store: IStore;
    currentStock: number;
    lastAdded: Date;
}
