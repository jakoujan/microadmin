import { Component, OnInit, Inject } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DialogData } from 'src/app/interfaces/dialog-data';

@Component({
  selector: 'app-initial-stock',
  templateUrl: './initial-stock.component.html',
  styleUrls: ['./initial-stock.component.scss']
})
export class InitialStockComponent implements OnInit {

  constructor(private fb: FormBuilder, public dialogRef: MatDialogRef<InitialStockComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData) { }

  ngOnInit(): void {
  }

  save() {

  }

  close() {
    this.dialogRef.close();
  }

}
