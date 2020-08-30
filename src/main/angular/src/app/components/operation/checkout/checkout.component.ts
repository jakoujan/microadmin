import { Component, OnInit } from '@angular/core';
import { IOrderFilter } from 'src/app/filters/order-filter';
import { IOrder } from 'src/app/interfaces/order';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.scss']
})
export class CheckoutComponent implements OnInit {
  orders: IOrder[] = [
    
  ];
  filter: IOrderFilter = {
    entity: {
      id: undefined,
      order_date: undefined,
      responsible: undefined,
      waiter: undefined,
      cashier: undefined,
      table: undefined,
      payment_method: undefined,
      status: undefined,
      total_amount: undefined,
      serviceType: 1,
      products: undefined
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
    this.filter.hidden = !this.filter.hidden;
  }

  setFilter() {

  }

  public pay(order: IOrder) {
    console.log(order);
  }

  charge(order: IOrder) {

  }

  cancelOrder(order: IOrder) {

  }

}
