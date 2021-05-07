import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ConfigurationService } from 'src/app/services/configuration.service';
import { IConfiguration } from 'src/app/interfaces/configuration';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CatalogsService } from 'src/app/services/catalogs.service';
import { IState } from 'src/app/interfaces/state';
import { ICountry } from 'src/app/interfaces/country';
import { SerialPortData } from '../../serial/serial-port-data';
import { ISerialPortItem } from '../../serial/serial-port-item';

@Component({
  selector: 'app-configuration',
  templateUrl: './configuration.component.html',
  styleUrls: ['./configuration.component.scss']
})
export class ConfigurationComponent implements OnInit {

  generalForm: FormGroup;
  ticketForm: FormGroup;
  storeForm: FormGroup;
  portForm: FormGroup;
  states: Array<IState> = [];
  countries: Array<ICountry> = [];
  printers: Array<string> = [];

  baudrates: Array<number> = SerialPortData.BAUDRATES;
  databits: Array<number> = SerialPortData.DATABITS;
  parities: Array<ISerialPortItem> = SerialPortData.PARITY;
  stopbits: Array<ISerialPortItem> = SerialPortData.STOPBITS;

  constructor(private formBuilder: FormBuilder, private configurationServices: ConfigurationService, private snackBar: MatSnackBar,
    private catalogsService: CatalogsService) { }

  ngOnInit(): void {
    this.catalogsService.states().then(states => this.states = states);
    this.catalogsService.countries().then(countries => this.countries = countries);
    this.catalogsService.printers().then(printers => this.printers = printers);

    this.configurationServices.load().then(response => {
      this.ticketForm.controls.printerName.setValue(response.fields.ticket.printerName);
      this.ticketForm.controls.header.setValue(response.fields.ticket.header);
      this.ticketForm.controls.footer.setValue(response.fields.ticket.footer);
      this.ticketForm.controls.messageOne.setValue(response.fields.ticket.messageOne);
      this.ticketForm.controls.messageTwo.setValue(response.fields.ticket.messageTwo);
      this.ticketForm.controls.logoPath.setValue(response.fields.ticket.logoPath);

      this.storeForm.controls.name.setValue(response.fields.store.name);
      this.storeForm.controls.bussinesName.setValue(response.fields.store.bussinesName);
      this.storeForm.controls.taxId.setValue(response.fields.store.taxId);
      this.storeForm.controls.street.setValue(response.fields.store.street);
      this.storeForm.controls.external.setValue(response.fields.store.external);
      this.storeForm.controls.internal.setValue(response.fields.store.internal);
      this.storeForm.controls.colony.setValue(response.fields.store.colony);
      this.storeForm.controls.city.setValue(response.fields.store.city);
      this.storeForm.controls.county.setValue(response.fields.store.county);
      this.storeForm.controls.state.setValue(response.fields.store.state);
      this.storeForm.controls.country.setValue(response.fields.store.country);
      this.storeForm.controls.postalCode.setValue(response.fields.store.postalCode);
      this.storeForm.controls.phoneNumber.setValue(response.fields.store.phoneNumber);
      this.storeForm.controls.email.setValue(response.fields.store.email);
      this.storeForm.controls.webpage.setValue(response.fields.store.webpage);
      this.storeForm.controls.taxRegime.setValue(response.fields.store.taxRegime);

      this.portForm.controls.baudRate.setValue(response.fields.port.baudRate);
      this.portForm.controls.dataBits.setValue(response.fields.port.dataBits);
      this.portForm.controls.name.setValue(response.fields.port.name);
      this.portForm.controls.parity.setValue(response.fields.port.parity);
      this.portForm.controls.stopBits.setValue(response.fields.port.stopBits);

    });
    this.portForm = this.formBuilder.group({
      baudRate: ['', Validators.required],
      dataBits: ['', Validators.required],
      name: ['', Validators.required],
      parity: ['', Validators.required],
      stopBits: ['', Validators.required]
    });
    this.storeForm = this.formBuilder.group({
      name: ['', Validators.required],
      bussinesName: ['', Validators.required],
      taxId: [''],
      street: ['', Validators.required],
      external: ['', Validators.required],
      internal: [''],
      colony: ['', Validators.required],
      city: ['', Validators.required],
      county: ['', Validators.required],
      state: ['', Validators.required],
      country: ['', Validators.required],
      postalCode: ['', Validators.required],
      phoneNumber: ['',],
      email: ['', Validators.email],
      webpage: [''],
      taxRegime: ['']
    });
    this.ticketForm = this.formBuilder.group({
      printerName: ['', Validators.required],
      header: ['', Validators.required],
      footer: ['', Validators.required],
      messageOne: [''],
      messageTwo: [''],
      logoPath: ['', Validators.required]
    });


    this.generalForm = this.formBuilder.group({
      store: this.storeForm,
      ticket: this.ticketForm,
      port: this.portForm
    });
  }

  public save() {
    const configuration: IConfiguration = {
      ticket: {
        printerName: this.ticketForm.controls.printerName.value,
        header: this.ticketForm.controls.header.value,
        footer: this.ticketForm.controls.footer.value,
        messageOne: this.ticketForm.controls.messageOne.value,
        messageTwo: this.ticketForm.controls.messageTwo.value,
        logoPath: this.ticketForm.controls.logoPath.value
      },
      store: {
        name: this.storeForm.controls.name.value,
        bussinesName: this.storeForm.controls.bussinesName.value,
        taxId: this.storeForm.controls.taxId.value,
        street: this.storeForm.controls.street.value,
        external: this.storeForm.controls.external.value,
        internal: this.storeForm.controls.internal.value,
        colony: this.storeForm.controls.colony.value,
        city: this.storeForm.controls.city.value,
        county: this.storeForm.controls.county.value,
        state: this.storeForm.controls.state.value,
        country: this.storeForm.controls.country.value,
        postalCode: this.storeForm.controls.postalCode.value,
        phoneNumber: this.storeForm.controls.phoneNumber.value,
        email: this.storeForm.controls.email.value,
        webpage: this.storeForm.controls.webpage.value,
        taxRegime: this.storeForm.controls.taxRegime.value
      },
      port: {
        baudRate: this.portForm.controls.baudRate.value,
        dataBits: this.portForm.controls.dataBits.value,
        name: this.portForm.controls.name.value,
        parity: this.portForm.controls.parity.value,
        stopBits: this.portForm.controls.stopBits.value
      }
    }
    this.configurationServices.save(configuration).then(response => {
      if (response.status === 'OK') {
        const snacBarRef = this.snackBar.open(response.message, "CERRAR", {
          duration: 3500
        });
      }
    });
  }

  compare(val1, val2) {
    return val1.id === val2.id;
  }

}
