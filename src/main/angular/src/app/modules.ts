export interface Module {
  title: string;
  icon: string;
  id: string;
  opened: boolean;
  submodules: Array<Submodule>;
}

export interface Submodule {
  name: string,
  id: string,
  uri: string,
  icon: string,
  default: boolean,
  active: boolean
}

export interface Permission {
  id: string;
  name: string;
  submodules: Array<Action>;
}

export interface Action {
  id: string;
  name: string;
  access: boolean;
  write: boolean;
}

export const MODULES: Array<Module> = [
  {
    title: 'Operación',
    icon: 'device_hub',
    id: 'operation',
    submodules: [
      {
        name: 'Caja',
        id: 'checkout',
        uri: '/modules/checkout',
        icon: '',
        default: true,
        active: false
      },
      {
        name: 'Comandas',
        id: 'orders',
        uri: '/modules/orders',
        icon: '',
        default: false,
        active: false
      }
    ],
    opened: false
  },
  {
    title: 'Administración',
    icon: 'settings',
    id: 'administration',
    submodules: [
      {
        name: 'Tiendas',
        id: 'stores',
        uri: '/modules/stores',
        icon: 'supervisor_account',
        default: true,
        active: false
      },
      {
        name: 'Cajas',
        id: 'workstations',
        uri: '/modules/workstations',
        icon: 'supervisor_account',
        default: false,
        active: false
      },
      {
        name: 'Mesas',
        id: 'tables',
        uri: '/modules/tables',
        icon: '',
        default: false,
        active: false
      },
      {
        name: 'Usuarios',
        id: 'users',
        uri: '/modules/users',
        icon: 'supervisor_account',
        default: false,
        active: false
      },
      {
        name: 'Clientes',
        id: 'customers',
        uri: '/modules/customers',
        icon: 'supervisor_account',
        default: false,
        active: false
      },
      {
        name: 'Formas de pago',
        id: 'payment-methods',
        uri: '/modules/payment-methods',
        icon: '',
        default: false,
        active: false
      },
      {
        name: 'Marcas',
        id: 'brands',
        uri: '/modules/brands',
        icon: '',
        default: false,
        active: false
      },
      {
        name: 'Secciones',
        id: 'sections',
        uri: '/modules/sections',
        icon: '',
        default: false,
        active: false
      },
      {
        name: 'Proveedores',
        id: 'suppliers',
        uri: '/modules/suppliers',
        icon: '',
        default: false,
        active: false
      },
      {
        name: 'Tipos de producto',
        id: 'product-types',
        uri: '/modules/product/types',
        icon: '',
        default: false,
        active: false
      },
      {
        name: 'Productos',
        id: 'products',
        uri: '/modules/products',
        icon: '',
        default: false,
        active: false
      },
      {
        name: 'Unidades de media',
        id: 'units',
        uri: '/modules/units',
        icon: '',
        default: false,
        active: false
      },
      {
        name: 'Configuración',
        id: 'configuration',
        uri: '/modules/configuration',
        icon: '',
        default: false,
        active: false
      }
    ],
    opened: false
  }

];
