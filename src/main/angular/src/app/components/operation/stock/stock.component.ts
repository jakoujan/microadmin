import { AfterViewInit, Component, OnInit, ViewChild, ChangeDetectorRef, OnDestroy } from '@angular/core';
import { NavigationService } from 'src/app/services/navigation.service';
import { IStockFilter } from 'src/app/filters/stock-filter';
import { BaseComponent } from '../../modules/base-component/base-component.component';
import { MatDialog } from '@angular/material/dialog';
import { InitialStockComponent } from '../initial-stock/initial-stock.component';

@Component({
  selector: 'app-stock',
  templateUrl: './stock.component.html',
  styleUrls: ['./stock.component.scss']
})
export class StockComponent extends BaseComponent implements OnInit {

  filter: IStockFilter = {
    entity: {
      id: undefined,
      product: {
        id: undefined,
        brand: undefined,
        barcode: undefined,
        description: undefined,
        minimumStock: undefined,
        supplierPrice: undefined,
        retailPrice: undefined,
        section: undefined,
        unit: undefined,
        active: true,
        longDescription: undefined,
        promoPrice: undefined,
        promotion: undefined,
        taxType: undefined,
        type: undefined
      },
      store: undefined,
      currentStock: undefined,
      lastAdded: undefined
    },
    startDate: undefined,
    endDate: undefined,
    hidden: true,
    page: 0,
    rows: 20
  };
  constructor(protected navigationService: NavigationService, private dialog: MatDialog) {
    super(navigationService);
  }

  public ngOnInit(): void {
  }

  public initialStock() {
    const dialogRef = this.dialog.open(InitialStockComponent, {
      width: '1280px',
      disableClose: true,
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {

      }
    });
  }

  public toggleSearchBar() {
    this.filter.hidden = !this.filter.hidden;
    this.filter.entity = {
      id: undefined,
      product: undefined,
      store: undefined,
      currentStock: undefined,
      lastAdded: undefined
    }
  }

  public setFilter() {

  }

}
