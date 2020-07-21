import { IStore } from './configuration';

export interface IWorkstation {
    id: number;
    store: IStore;
    description: string;
    active: boolean;
}