import { Component, Inject, OnInit, AfterViewInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DialogData } from 'src/app/interfaces/dialog-data';

@Component({
  selector: 'app-product-type-form',
  templateUrl: './product-type-form.component.html',
  styleUrls: ['./product-type-form.component.scss']
})
export class ProductTypeFormComponent implements OnInit, AfterViewInit {
  form: FormGroup;
  constructor(private fb: FormBuilder, public dialogRef: MatDialogRef<ProductTypeFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData) { }

  ngOnInit(): void {
    this.form = this.fb.group({
      name: [this.data.entity.name, Validators.required],
    });
  }

  ngAfterViewInit() {

  }

  save() {
    this.data.entity.name = this.form.controls.name.value;
    this.dialogRef.close(this.data);
  }

  close() {
    this.dialogRef.close();
  }

  public filterStates(event) {

  }
}