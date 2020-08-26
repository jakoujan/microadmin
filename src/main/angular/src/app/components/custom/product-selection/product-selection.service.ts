import { Injectable } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { ProductSelectionComponent } from "./product-selection.component";
@Injectable({
  providedIn: 'root'
})
export class ProductSelectionService {

  constructor(private dialog: MatDialog) { }

  public select(): MatDialogRef<ProductSelectionComponent> {
    return this.dialog.open(ProductSelectionComponent, {
      width: '400px',
      minHeight: '250px',
      disableClose: true,
      data: {
        
      }
    });
  }
}
