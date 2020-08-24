import { Component, Inject, OnInit, AfterViewInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DialogData } from 'src/app/interfaces/dialog-data';

@Component({
  selector: 'app-table-form',
  templateUrl: './table-form.component.html',
  styleUrls: ['./table-form.component.scss']
})
export class TableFormComponent implements OnInit, AfterViewInit {
  form: FormGroup;
  findersForm: FormGroup;
  constructor(private fb: FormBuilder, public dialogRef: MatDialogRef<TableFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData) { }

  ngOnInit(): void {

    this.form = this.fb.group({
      name: [this.data.entity.name, Validators.required],
      places: [this.data.entity.places, Validators.required],
    });
  }

  ngAfterViewInit() {

  }

  save() {
    this.data.entity.name = this.form.controls.name.value;
    this.data.entity.places = this.form.controls.places.value;
    this.dialogRef.close(this.data);
  }

  close() {
    this.dialogRef.close();
  }

  public filterStates(event) {

  }
}