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
    {
      cashier: undefined,
      id: 3,
      orderDate: new Date('03/08/2020'),
      paymentMethod: undefined,
      responsible: "Jose Perez",
      status: undefined,
      table: 3,
      totalAmount: 320.92,
      waiter: undefined
    },
    {
      cashier: undefined,
      id: 4,
      orderDate: new Date('03/08/2020'),
      paymentMethod: undefined,
      responsible: "Alberto Murguia",
      status: undefined,
      table: 2,
      totalAmount: 234.98,
      waiter: undefined
    },
    {
      cashier: undefined,
      id: 4,
      orderDate: new Date('03/08/2020'),
      paymentMethod: undefined,
      responsible: "Jazmin Torres",
      status: undefined,
      table: 3,
      totalAmount: 120.67,
      waiter: undefined
    }
  ];
  filter: IOrderFilter = {
    entity: {
      id: undefined,
      orderDate: undefined,
      responsible: undefined,
      waiter: undefined,
      cashier: undefined,
      table: undefined,
      paymentMethod: undefined,
      status: undefined,
      totalAmount: undefined
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
