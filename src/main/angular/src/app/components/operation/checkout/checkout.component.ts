import { Component, OnInit } from '@angular/core';
import { IBrandFilter } from 'src/app/filters/brand-filter';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.scss']
})
export class CheckoutComponent implements OnInit {

  filter: IBrandFilter = {
    entity: {
      id: undefined,
      active: undefined,
      brand: undefined
    },
    startDate: undefined,
    endDate: undefined,
    hidden: true,
    page: 0,
    rows: 20
  };
  constructor() { }

  ngOnInit(): void {
  }

  initialStock() {

  }

  toggleSearchBar() {

  }

  setFilter() {

  }

}
