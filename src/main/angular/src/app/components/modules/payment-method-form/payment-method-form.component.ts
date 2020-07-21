import { Component, Inject, OnInit, AfterViewInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DialogData } from 'src/app/interfaces/dialog-data';

@Component({
  selector: 'app-payment-method-form',
  templateUrl: './payment-method-form.component.html',
  styleUrls: ['./payment-method-form.component.scss']
})
export class PaymentMethodFormComponent implements OnInit, AfterViewInit {
  form: FormGroup;
  findersForm: FormGroup;
  constructor(private fb: FormBuilder, public dialogRef: MatDialogRef<PaymentMethodFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData) { }

  ngOnInit(): void {
    this.form = this.fb.group({
      method: [this.data.entity.method, Validators.required],
      description: [this.data.entity.description, Validators.required],
    });
  }

  ngAfterViewInit() {

  }

  save() {
    this.data.entity.method = this.form.controls.method.value;
    this.data.entity.description = this.form.controls.description.value;
    this.dialogRef.close(this.data);
  }

  close() {
    this.dialogRef.close();
  }

  public filterStates(event) {

  }
}