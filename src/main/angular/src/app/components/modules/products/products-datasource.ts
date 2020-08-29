import { DataSource } from '@angular/cdk/collections';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { map } from 'rxjs/operators';
import { Observable, of as observableOf, merge } from 'rxjs';
import { IProduct } from 'src/app/interfaces/product';
import { IProductView } from 'src/app/interfaces/view/product-view';

/**
 * Data source for the Products view. This class should
 * encapsulate all logic for fetching and manipulating the displayed data
 * (including sorting, pagination, and filtering).
 */
export class ProductsDataSource extends DataSource<IProductView> {
  data: Array<IProductView> = [];
  paginator: MatPaginator;
  sort: MatSort;
  count: number;

  constructor(data: Array<IProductView>) {
    super();
    this.data = data;
  }

  /**
   * Connect this data source to the table. The table will only update when
   * the returned stream emits new items.
   * @returns A stream of the items to be rendered.
   */
  connect(): Observable<Array<IProductView>> {
    // Combine everything that affects the rendered data into one update
    // stream for the data-table to consume.
    const dataMutations = [
      observableOf(this.data),
      this.paginator.page,
      this.sort.sortChange
    ];

    return merge(...dataMutations).pipe(map(() => {
      return this.getPagedData(this.getSortedData([...this.data]));
    }));
  }

  /**
   *  Called when the table is being destroyed. Use this function, to clean up
   * any open connections or free any held resources that were set up during connect.
   */
  disconnect() { }

  /**
   * Paginate the data (client-side). If you're using server-side pagination,
   * this would be replaced by requesting the appropriate data from the server.
   */
  private getPagedData(data: Array<IProductView>) {
    return data;
  }

  /**
   * Sort the data (client-side). If you're using server-side sorting,
   * this would be replaced by requesting the appropriate data from the server.
   */
  private getSortedData(data: Array<IProductView>) {
    if (!this.sort.active || this.sort.direction === '') {
      return data;
    }

    return data.sort((a: IProductView, b: IProductView) => {
      const isAsc = this.sort.direction === 'asc';
      switch (this.sort.active) {
        case 'brand': return compare(a.brand, b.brand, isAsc);
        case 'barcode': return compare(a.barcode, b.barcode, isAsc);
        case 'description': return compare(a.description, b.description, isAsc);
        case 'minimumStock': return compare(a.minimumStock, b.minimumStock, isAsc);
        case 'retailPrice': return compare(a.retailPrice, b.retailPrice, isAsc);
        case 'section': return compare(a.section, b.section, isAsc);
        case 'supplierPrice': return compare(a.supplierPrice, b.supplierPrice, isAsc);
        case 'unit': return compare(a.unit, b.unit, isAsc);
        default: return 0;
      }
    });
  }
}

/** Simple sort comparator for example ID/Name columns (for client-side sorting). */
function compare(a: string | number, b: string | number, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}
