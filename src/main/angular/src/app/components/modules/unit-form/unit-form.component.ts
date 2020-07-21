import { Component, Inject, OnInit, AfterViewInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup, FormControl } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DialogData } from 'src/app/interfaces/dialog-data';

@Component({
  selector: 'app-unit-form',
  templateUrl: './unit-form.component.html',
  styleUrls: ['./unit-form.component.scss']
})
export class UnitFormComponent implements OnInit, AfterViewInit {
  form: FormGroup;
  findersForm: FormGroup;
  constructor(private fb: FormBuilder, public dialogRef: MatDialogRef<UnitFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData) { }

  ngOnInit(): void {

    this.form = this.fb.group({
      unit: [this.data.entity.unit, Validators.required],
      description: [this.data.entity.description, Validators.required],
    });
  }

  ngAfterViewInit() {

  }

  save() {
    this.data.entity.unit = this.form.controls.unit.value;
    this.data.entity.description = this.form.controls.description.value;
    this.dialogRef.close(this.data);
  }

  close() {
    this.dialogRef.close();
  }

  public filterStates(event) {

  }
}