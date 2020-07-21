import { Component, Inject, OnInit, AfterViewInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup, FormControl } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DialogData } from 'src/app/interfaces/dialog-data';
import { Item } from 'src/app/interfaces/item';
import { CatalogsService } from 'src/app/services/catalogs.service';
import { IState } from 'src/app/interfaces/state';
import { ICountry } from 'src/app/interfaces/country';
import { ItemFinder } from 'src/app/helpers/helpers';


@Component({
  selector: 'app-supplier-form',
  templateUrl: './supplier-form.component.html',
  styleUrls: ['./supplier-form.component.scss']
})
export class SupplierFormComponent implements OnInit, AfterViewInit {
  form: FormGroup;
  generalDataForm: FormGroup;
  addressForm: FormGroup;
  findersForm: FormGroup;

  states: Array<IState> = [];
  stateFinder: FormControl = new FormControl();
  countries: Array<ICountry> = [];
  supplierTypes: Array<Item> = [
    { id: 1, description: 'Cliente' },
    { id: 2, description: 'Proveedor' }
  ]

  constructor(private fb: FormBuilder, public dialogRef: MatDialogRef<SupplierFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData, private catalogsService: CatalogsService) { }

  ngOnInit(): void {

    this.catalogsService.states().then(states => this.states = states);
    this.catalogsService.countries().then(countries => this.countries = countries);

    this.generalDataForm = this.fb.group({
      business_name: [this.data.entity.business_name, Validators.required],
      telephone: [this.data.entity.telephone, Validators.required],
      contact: [this.data.entity.contact, Validators.required],
      email: [this.data.entity.email, Validators.compose([Validators.required, Validators.email])],
      tax_id: [this.data.entity.tax_id, Validators.required]
    });
    this.addressForm = this.fb.group({
      street: [this.data.entity.street, Validators.required],
      internal_number: [this.data.entity.internal_number],
      external_number: [this.data.entity.external_number, Validators.required],
      settlement: [this.data.entity.settlement, Validators.required],
      city: [this.data.entity.city, Validators.required],
      county: [this.data.entity.county, Validators.required],
      state: [this.data.entity.state, Validators.required],
      postal_code: [this.data.entity.postal_code, Validators.required],
      country: [this.data.entity.country, Validators.required]
    });
    this.form = this.fb.group({
      generalData: this.generalDataForm,
      addressData: this.addressForm
    });
  }

  ngAfterViewInit() {

  }

  save() {
    this.data.entity.business_name = this.generalDataForm.controls.business_name.value;
    this.data.entity.tax_id = this.generalDataForm.controls.tax_id.value;
    this.data.entity.contact = this.generalDataForm.controls.contact.value;
    this.data.entity.telephone = this.generalDataForm.controls.telephone.value;
    this.data.entity.email = this.generalDataForm.controls.email.value;
    this.data.entity.street = this.addressForm.controls.street.value;
    this.data.entity.internal_number = this.addressForm.controls.internal_number.value;
    this.data.entity.external_number = this.addressForm.controls.external_number.value;
    this.data.entity.settlement = this.addressForm.controls.settlement.value;
    this.data.entity.city = this.addressForm.controls.city.value;
    this.data.entity.county = this.addressForm.controls.county.value;
    this.data.entity.state = this.addressForm.controls.state.value;
    this.data.entity.postal_code = this.addressForm.controls.postal_code.value;
    this.data.entity.country = this.addressForm.controls.country.value;

    this.dialogRef.close(this.data);
  }

  close() {
    this.dialogRef.close();
  }

  compare(val1, val2) {
    return val1.id === val2.id;
  }

  public filterStates(event) {
    
  }
}

