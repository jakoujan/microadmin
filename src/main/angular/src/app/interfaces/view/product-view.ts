export interface IProductView {
    id: number;
    brand: string;
    barcode: string;
    description: string;
    minimumStock: number;
    supplierPrice: number;
    retailPrice: number;
    section: string;
    unit: string;
    active: boolean;
}
