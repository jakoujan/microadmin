import { Injectable } from '@angular/core';
import { Subject, Observable } from 'rxjs';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class KeyboardService {

  private visible: boolean = false;

  private controller: Subject<boolean> = new Subject();

  public $controller: Observable<boolean> = this.controller.asObservable();

  constructor(private snackBar: MatSnackBar) { }

  public setVisible(value: boolean) {
    this.visible = value;

    if(this.visible) {
      const snacBarRef = this.snackBar.open('El teclado virtual esta activo', 'Cerrar', {
        duration: 4500
      });

      snacBarRef.onAction().subscribe(() => {
        snacBarRef.dismiss();
      });
    }
  }

  public isVisible(): boolean {
    return this.visible;
  }

  public change(visibility: boolean) {
    this.controller.next(this.visible && visibility);
  }
  
}
