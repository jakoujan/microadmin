import { ISerialPortItem } from "src/app/components/serial/serial-port-item";

export interface IStore {
  name: string;
  bussinesName: string;
  taxId: string;
  street: string;
  external: string;
  internal: string;
  colony: string;
  city: string;
  county: string;
  state: string;
  country: string;
  postalCode: string;
  phoneNumber: string;
  email: string;
  webpage: string;
  taxRegime: string;
}

export interface ITicketData {
  printerName: string;
  header: string;
  footer: string;
  messageOne: string;
  messageTwo: string;
  logoPath: string;
}

export interface IPort {
  name: number;
  baudRate: number;
  parity: ISerialPortItem;
  dataBits: number;
  stopBits: ISerialPortItem;
}

export interface IConfiguration {
  store: IStore;
  ticket: ITicketData;
  port: IPort;
}
