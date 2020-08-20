import { Component, Inject, OnInit, AfterViewInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup, FormControl } from '@angular/forms';
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

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.scss']
})
export class ProductFormComponent implements OnInit, AfterViewInit {
  form: FormGroup;
  findersForm: FormGroup;

  brands: Array<IBrand> = [];
  sections: Array<ISection> = [];
  units: Array<IUnit> = [];
  taxTypes: Array<ITaxType> = [];
  suppliers: Array<ISupplier> = [];
  productTypes: Array<IProductType> = [];
  products: Array<IProduct> = [];

  constructor(private fb: FormBuilder, public dialogRef: MatDialogRef<ProductFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData, private catalogService: CatalogsService) { }

  ngOnInit(): void {

    this.catalogService.getBrands().then(brands => this.brands = brands);
    this.catalogService.getSections().then(sections => this.sections = sections);
    this.catalogService.getTaxTypes().then(taxTypes => this.taxTypes = taxTypes);
    this.catalogService.getUnits().then(units => this.units = units);
    this.catalogService.getProductTypes().then(productTypes => this.productTypes = productTypes);

    this.form = this.fb.group({
      barcode: [this.data.entity.barcode, Validators.required],
      brand: [this.data.entity.brand, Validators.required],
      unit: [this.data.entity.unit, Validators.required],
      description: [this.data.entity.description, Validators.required],
      longDescription: [this.data.entity.longDescription, Validators.required],
      section: [this.data.entity.section, Validators.required],
      taxType: [this.data.entity.taxType, Validators.required],
      retailPrice: [this.data.entity.retailPrice, Validators.required],
      supplierPrice: [this.data.entity.supplierPrice, Validators.required],
      promoPrice: [this.data.entity.promoPrice, Validators.required],
      promotion: [this.data.entity.promotion],
      minimumStock: [this.data.entity.minimumStock, Validators.required],
      type: [this.data.entity.type, Validators.required]
    });
  }

  ngAfterViewInit() {

  }

  save() {
    this.data.entity.brand = this.form.controls.brand.value;
    this.data.entity.barcode = this.form.controls.barcode.value;
    this.data.entity.brand = this.form.controls.brand.value;
    this.data.entity.unit = this.form.controls.unit.value;
    this.data.entity.description = this.form.controls.description.value;
    this.data.entity.longDescription = this.form.controls.longDescription.value;
    this.data.entity.section = this.form.controls.section.value;
    this.data.entity.taxType = this.form.controls.taxType.value;
    this.data.entity.retailPrice = this.form.controls.retailPrice.value;
    this.data.entity.supplierPrice = this.form.controls.supplierPrice.value;
    this.data.entity.promoPrice = this.form.controls.promoPrice.value;
    this.data.entity.promotion = this.form.controls.promotion.value;
    this.data.entity.minimumStock = this.form.controls.minimumStock.value;
    this.data.entity.type = this.form.controls.type.value;
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

  public productTypeChange(type: IProductType) {
    if (type.name === 'Combo/Kit') {
      this.catalogService.getProducts().then(products => { this.products = products });
    }
  }

  public addComboProduct() {

  }
}