import { Component, Inject, OnInit, AfterViewInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup, FormControl } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DialogData } from 'src/app/interfaces/dialog-data';

@Component({
  selector: 'app-brand-form',
  templateUrl: './brand-form.component.html',
  styleUrls: ['./brand-form.component.scss']
})
export class BrandFormComponent implements OnInit, AfterViewInit {
  form: FormGroup;
  findersForm: FormGroup;
  constructor(private fb: FormBuilder, public dialogRef: MatDialogRef<BrandFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData) { }

  ngOnInit(): void {

    this.form = this.fb.group({
      brand: [this.data.entity.brand, Validators.required],
    });
  }

  ngAfterViewInit() {

  }

  save() {
    this.data.entity.brand = this.form.controls.brand.value;
    this.dialogRef.close(this.data);
  }

  close() {
    this.dialogRef.close();
  }
}