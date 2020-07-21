import { Component, Inject, OnInit, AfterViewInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup, FormControl } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DialogData } from 'src/app/interfaces/dialog-data';

@Component({
  selector: 'app-store-form',
  templateUrl: './store-form.component.html',
  styleUrls: ['./store-form.component.scss']
})
export class StoreFormComponent implements OnInit, AfterViewInit {
  form: FormGroup;
  findersForm: FormGroup;
  constructor(private fb: FormBuilder, public dialogRef: MatDialogRef<StoreFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData) { }

  ngOnInit(): void {

    this.form = this.fb.group({
      description: [this.data.entity.description, Validators.required],
      address: [this.data.entity.address, Validators.required],
    });
  }

  ngAfterViewInit() {

  }

  save() {
    this.data.entity.description = this.form.controls.description.value;
    this.data.entity.address = this.form.controls.address.value;
    this.dialogRef.close(this.data);
  }

  close() {
    this.dialogRef.close();
  }
}