import { Component, OnInit } from '@angular/core';
import { OrderService } from 'src/app/services/order.service';
import { IProductPreparation } from 'src/app/interfaces/view/product-preparation';
import { MatSnackBar } from '@angular/material/snack-bar';
import { RxStompService } from '@stomp/ng2-stompjs';
import { environment } from 'src/environments/environment';
import { Message } from '@stomp/stompjs';
import { Subscription } from 'rxjs';


@Component({
  selector: 'app-product-elaborations',
  templateUrl: './product-elaborations.component.html',
  styleUrls: ['./product-elaborations.component.scss']
})
export class ProductElaborationsComponent implements OnInit {

  products: Array<IProductPreparation> = [];
  subscription: Subscription;


  constructor(private orderService: OrderService, private snackBar: MatSnackBar, private rxStompService: RxStompService) { }

  ngOnInit(): void {
    this.orderService.productElaboration(1).then(response => {
      this.products = response.fields.products;
    });
    this.subscription = this.rxStompService.watch(environment.websocket.topicPrefix).subscribe((message: Message) => {
      this.orderService.productElaboration(1).then(response => {
        this.products = response.fields.products;
      });
    });
  }

  public productReady(item: IProductPreparation) {
    this.orderService.productElaborationDone(item).then(response => {
      this.products = response.fields.products;
      this.snackBar.open("Producto enviado", "Desechar", {
        duration: 3 * 1000,
      });
    });
  }
}
