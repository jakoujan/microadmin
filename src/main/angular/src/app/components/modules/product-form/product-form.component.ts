import { Component, Inject, OnInit, AfterViewInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup, FormControl, FormArray } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DialogData } from 'src/app/interfaces/dialog-data';
import { IBrand } from 'src/app/interfaces/brand';
import { ISection } from 'src/app/interfaces/section';
import { IUnit } from 'src/app/interfaces/unit';
import { ITaxType } from 'src/app/interfaces/tax-type';
import { ISupplier } from 'src/app/interfaces/supplier';
import { CatalogsService } from 'src/app/services/catalogs.service';
import { IProductType } from 'src/app/interfaces/product-type';
import { IProduct } from 'src/app/interfaces/product';
import { IProductKit } from 'src/app/interfaces/product-kit';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.scss']
})
export class ProductFormComponent implements OnInit, AfterViewInit {

  form: FormGroup;
  findersForm: FormGroup;
  product: IProduct;

  brands: Array<IBrand> = [];
  sections: Array<ISection> = [];
  units: Array<IUnit> = [];
  taxTypes: Array<ITaxType> = [];
  suppliers: Array<ISupplier> = [];
  productTypes: Array<IProductType> = [];
  products: Array<IProduct> = [];

  productFilter: FormControl;

  constructor(private fb: FormBuilder, public dialogRef: MatDialogRef<ProductFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData, private catalogService: CatalogsService) { }

  ngOnInit(): void {

    this.product = this.data.entity;

    this.catalogService.getBrands().then(brands => this.brands = brands);
    this.catalogService.getSections().then(sections => this.sections = sections);
    this.catalogService.getTaxTypes().then(taxTypes => this.taxTypes = taxTypes);
    this.catalogService.getUnits().then(units => this.units = units);
    this.catalogService.getProductTypes().then(productTypes => this.productTypes = productTypes);
    this.catalogService.getProducts().then(products => this.products = products);

    this.productFilter = this.fb.control('');

    this.form = this.fb.group({
      barcode: [this.product.barcode, Validators.required],
      brand: [this.product.brand, Validators.required],
      unit: [this.product.unit, Validators.required],
      description: [this.product.description, Validators.required],
      longDescription: [this.product.longDescription, Validators.required],
      section: [this.product.section, Validators.required],
      taxType: [this.product.taxType, Validators.required],
      retailPrice: [this.product.retailPrice, Validators.required],
      supplierPrice: [this.product.supplierPrice, Validators.required],
      promoPrice: [this.product.promoPrice, Validators.required],
      promotion: [this.product.promotion],
      minimumStock: [this.product.minimumStock, Validators.required],
      type: [this.product.type, Validators.required],
      kitProducts: this.fb.array([])
    });

    if (this.product.kitProducts.length >= 1) {
      this.product.kitProducts.forEach(productKit => {
        this.addComboProduct(productKit);
      });
    }
  }

  ngAfterViewInit() {

  }

  save() {
    this.product.brand = this.form.controls.brand.value;
    this.product.barcode = this.form.controls.barcode.value;
    this.product.brand = this.form.controls.brand.value;
    this.product.unit = this.form.controls.unit.value;
    this.product.description = this.form.controls.description.value;
    this.product.longDescription = this.form.controls.longDescription.value;
    this.product.section = this.form.controls.section.value;
    this.product.taxType = this.form.controls.taxType.value;
    this.product.retailPrice = this.form.controls.retailPrice.value;
    this.product.supplierPrice = this.form.controls.supplierPrice.value;
    this.product.promoPrice = this.form.controls.promoPrice.value;
    this.product.promotion = this.form.controls.promotion.value;
    this.product.minimumStock = this.form.controls.minimumStock.value;
    this.product.type = this.form.controls.type.value;

    this.product.kitProducts = [];
    this.kitProducts.controls.forEach((form: FormGroup) => {
      this.product.kitProducts.push({
        id: form.get('id').value,
        kit: form.get('kit').value,
        product: form.get('product').value,
        quantity: form.get('quantity').value
      });
    });

    this.dialogRef.close(this.data);
  }

  close() {
    this.dialogRef.close();
  }

  compare(val1, val2) {
    return val1.id === val2.id;
  }

  public addBrand() {
    console.log('Se agrega')
  }

  public get checkCombo(): boolean {
    return this.form.controls.type.value ? this.form.controls.type.value.name !== 'Combo/Kit' : false;
  }

  public productTypeChange(type: IProductType) {
    if (type.name === 'Combo/Kit') {
      this.catalogService.getProducts().then(products => { this.products = products });
    }
  }

  public get filteredProducts(): Array<IProduct> {
    const filter = (this.productFilter.value as string).toLowerCase();
    if (filter) {
      return this.products.filter(product => {
        if (product.description.toLowerCase().indexOf(filter) >= 0) {
          return true;
        }
      });
    } else {
      return this.products;
    }
  }

  public get kitProducts(): FormArray {
    return this.form.get('kitProducts') as FormArray;
  }

  public addComboProduct(productKit?: IProductKit) {
    if (!productKit) {
      productKit = {
        kit: undefined,
        product: undefined,
        quantity: 1,
        id: undefined
      }
    }
    this.kitProducts.push(this.fb.group({
      kit: [productKit.kit],
      product: [productKit.product, Validators.required],
      quantity: [productKit.quantity, Validators.required],
      id: [productKit.id]
    }));
  }

  public removeItem(index: number) {
    this.kitProducts.removeAt(index);
  }
}