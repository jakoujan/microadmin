import { Component, Inject, OnInit, AfterViewInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup, FormControl } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DialogData } from 'src/app/interfaces/dialog-data';

@Component({
  selector: 'app-section-form',
  templateUrl: './section-form.component.html',
  styleUrls: ['./section-form.component.scss']
})
export class SectionFormComponent implements OnInit, AfterViewInit {
  form: FormGroup;
  findersForm: FormGroup;
  constructor(private fb: FormBuilder, public dialogRef: MatDialogRef<SectionFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData) { }

  ngOnInit(): void {

    this.form = this.fb.group({
      section: [this.data.entity.section, Validators.required],
      description: [this.data.entity.description, Validators.required],
    });
  }

  ngAfterViewInit() {

  }

  save() {
    this.data.entity.section = this.form.controls.section.value;
    this.data.entity.description = this.form.controls.description.value;
    this.dialogRef.close(this.data);
  }

  close() {
    this.dialogRef.close();
  }

  public filterStates(event) {

  }
}