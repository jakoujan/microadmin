import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { IProduct } from 'src/app/interfaces/product';
import { CatalogsService } from 'src/app/services/catalogs.service';
import { ITypeProductMap } from "src/app/interfaces/dto/type-product-map";
import { IProductType } from 'src/app/interfaces/product-type';
@Component({
  selector: 'app-product-selection',
  templateUrl: './product-selection.component.html',
  styleUrls: ['./product-selection.component.scss']
})
export class ProductSelectionComponent implements OnInit {

  product: IProduct;
  types: Array<ITypeProductMap> = [];
  constructor(public dialogRef: MatDialogRef<ProductSelectionComponent>, private catalogService: CatalogsService) { }

  ngOnInit(): void {
    this.catalogService.getProducts().then((products: Array<IProduct>) => {
      products.forEach(product => {
        const exist = this.types.some(type => {
          if (type.type.id === product.type.id) {
            type.products.push(product);
            return true;
          }
        });
        if (!exist) {
          this.types.push({
            type: product.type,
            products: [product]
          });
        }
      });
    });
  }

  public select(product: IProduct) {
    this.product = product;
  }

  public selected() {
    this.dialogRef.close(this.product);
  }

  public close() {
    this.dialogRef.close(false);
  }

}
