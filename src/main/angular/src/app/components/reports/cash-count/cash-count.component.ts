import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ReportService } from "src/app/services/report.service";
import { CatalogsService } from 'src/app/services/catalogs.service';
import { ICashCountFilter } from "src/app/filters/cash-count-filter";
import { IUser } from 'src/app/interfaces/user';
import { ICashCountView } from 'src/app/interfaces/view/cash-count-view';

@Component({
  selector: 'app-cash-count',
  templateUrl: './cash-count.component.html',
  styleUrls: ['./cash-count.component.scss']
})
export class CashCountComponent implements OnInit {

  filter: ICashCountFilter = {
    entity: {
      cashier: undefined,
      id: undefined,
      orderComand: undefined,
      quantity: undefined,
      saleDate: undefined,
      totalAmount: undefined
    },
    startDate: undefined,
    endDate: undefined,
    hidden: true,
    page: 0,
    rows: 20,

  }
  users: Array<IUser> = [];
  sales: Array<ICashCountView> = []
  totalAmount: number = 0;
  count: number = 0;
  constructor(private router: Router, private reportService: ReportService, private catalogsService: CatalogsService) { }

  ngOnInit(): void {
    this.catalogsService.users().then(users => this.users = users);
  }

  toggleSearchBar() {
    this.filter.hidden = !this.filter.hidden;
  }

  generate() {
    this.reportService.cashCount(this.filter).then(response => {
      this.sales = response.fields.data;
      this.totalAmount = response.fields.totalAmount;
      this.count = response.fields.count;
    });
  }

  clean() {
    this.filter = {
      entity: {
        cashier: undefined,
        id: undefined,
        orderComand: undefined,
        quantity: undefined,
        saleDate: undefined,
        totalAmount: undefined
      },
      startDate: undefined,
      endDate: undefined,
      hidden: true,
      page: 0,
      rows: 20,
    }
  }



  public compare(val1, val2) {
    return val1.id === val2.id;
  }

}
