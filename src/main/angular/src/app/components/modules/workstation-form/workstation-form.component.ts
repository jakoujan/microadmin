import { Component, Inject, OnInit, AfterViewInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup, FormControl } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DialogData } from 'src/app/interfaces/dialog-data';
import { CatalogsService } from 'src/app/services/catalogs.service';
import { IStore } from 'src/app/interfaces/store';

@Component({
  selector: 'app-workstation-form',
  templateUrl: './workstation-form.component.html',
  styleUrls: ['./workstation-form.component.scss']
})
export class WorkstationFormComponent implements OnInit, AfterViewInit {

  stores: Array<IStore> = [];
  form: FormGroup;
  findersForm: FormGroup;
  constructor(private fb: FormBuilder, public dialogRef: MatDialogRef<WorkstationFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData, private catalogService: CatalogsService) { }

  ngOnInit(): void {
    this.catalogService.getStores().then(stores => this.stores = stores);
    this.form = this.fb.group({
      description: [this.data.entity.description, Validators.required],
      store: [this.data.entity.store, Validators.required],
    });
  }

  ngAfterViewInit() {

  }

  save() {
    this.data.entity.description = this.form.controls.description.value;
    this.data.entity.store = this.form.controls.store.value;
    this.dialogRef.close(this.data);
  }

  close() {
    this.dialogRef.close();
  }

  compare(val1, val2) {
    return val1.id === val2.id;
  }
}