import { AfterViewInit, Component, OnInit, ViewChild, ChangeDetectorRef, OnDestroy } from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTable } from '@angular/material/table';
import { ProductsDataSource } from './products-datasource';
import { IProduct } from 'src/app/interfaces/product';
import { ProductService } from 'src/app/services/product.service';
import { IProductViewFilter } from "src/app/filters/product-filter";
import { MatDialog } from '@angular/material/dialog';
import { ProductFormComponent } from '../product-form/product-form.component';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ConfirmationDialogService } from '../../common/ui/confirmation-dialog/confirmation-dialog.service';
import { BaseComponent } from '../base-component/base-component.component';
import { NavigationService } from 'src/app/services/navigation.service';
import { IProductView } from 'src/app/interfaces/view/product-view';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent extends BaseComponent implements OnInit, OnDestroy, AfterViewInit {

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatTable) table: MatTable<IProductView>;
  dataSource: ProductsDataSource;
  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['description','actions', 'barcode', 'brand', 'minimumStock', 'supplierPrice', 'retailPrice', 'section', 'unit'];
  filter: IProductViewFilter = {
    entity: {
      id: undefined,
      brand: undefined,
      barcode: undefined,
      description: undefined,
      minimumStock: undefined,
      supplierPrice: undefined,
      retailPrice: undefined,
      section: undefined,
      unit: undefined,
      active: undefined
    },
    startDate: undefined,
    endDate: undefined,
    hidden: true,
    page: 0,
    rows: 20
  }
  constructor(
    private productService: ProductService,
    private changeDetectorRefs: ChangeDetectorRef,
    private dialog: MatDialog,
    private snackBar: MatSnackBar,
    private confirmationDialogService: ConfirmationDialogService,
    protected navigationService: NavigationService) {
    super(navigationService);
  }
  ngOnDestroy(): void {
    super.destroy();
  }

  ngOnInit() {

  }

  ngAfterViewInit() {
    this.setFilter();
  }

  public setFilter() {
    this.productService.filter(this.filter).then(response => {
      this.dataSource = new ProductsDataSource(response.fields.data);
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
      this.dataSource.count = response.fields.count;
      this.table.dataSource = this.dataSource;
      this.changeDetectorRefs.detectChanges();
    })
  }

  public pageChangeEvent(event: PageEvent) {
    this.filter.rows = event.pageSize;
    this.filter.page = event.pageIndex;
    this.setFilter();
  }

  toggleSearchBar() {
    this.filter.hidden = !this.filter.hidden;
    this.filter.entity = {
      id: undefined,
      brand: undefined,
      barcode: undefined,
      description: undefined,
      minimumStock: undefined,
      supplierPrice: undefined,
      retailPrice: undefined,
      section: undefined,
      unit: undefined,
      active: true
    }
  }

  public addRow() {
    const entity: IProduct = {
      id: undefined,
      barcode: undefined,
      brand: undefined,
      unit: undefined,
      description: undefined,
      longDescription: undefined,
      section: undefined,
      taxType: undefined,
      retailPrice: undefined,
      supplierPrice: undefined,
      promoPrice: undefined,
      promotion: undefined,
      minimumStock: undefined,
      active: true,
      type: undefined,
      kit: undefined
    }
    this.showForm(entity, false);
  }

  public showForm(entity: IProductView | IProduct, editing: boolean = true) {
    if (editing) {
      this.productService.getProduct(entity.id).then(response => {
        const dialogRef = this.dialog.open(ProductFormComponent, {
          width: '800px',
          disableClose: true,
          data: { entity: response.fields.entity }
        });

        dialogRef.afterClosed().subscribe(result => {
          if (result) {
            this.productService.save(result.entity).then(response => {
              this.setFilter();
            });
          }
        });
      });
    } else {
      const dialogRef = this.dialog.open(ProductFormComponent, {
        width: '800px',
        disableClose: true,
        data: { entity: entity }
      });

      dialogRef.afterClosed().subscribe(result => {
        if (result) {
          this.productService.save(result.entity).then(response => {
            this.setFilter();
          });
        }
      });
    }
  }

  delete(entity: IProduct): void {
    const dialogRef = this.confirmationDialogService.showConfirmationDialog('¿Desea eliminar el registro?');
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.productService.delete(entity).then(response => {
          const snacBarRef = this.snackBar.open(response.message, 'Deshacer', {
            duration: 3000
          });
          this.setFilter();

          snacBarRef.onAction().subscribe(() => {
            this.productService.save(entity).then(response => {
              this.setFilter();
            });
          });
        });

      }
    });
  }

}
