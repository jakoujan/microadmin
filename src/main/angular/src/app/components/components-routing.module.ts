import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DashboardComponent } from './common/dashboard/dashboard.component';
import { NavigationComponent } from './common/navigation/navigation.component';
import { UsersComponent } from './modules/users/users.component';
import { CustomersComponent } from './modules/customers/customers.component';
import { ConfigurationComponent } from './modules/configuration/configuration.component';
import { SessionGuard } from '../guards/session.guard';
import { SuppliersComponent } from './modules/suppliers/suppliers.component';
import { BrandsComponent } from './modules/brands/brands.component';
import { SectionsComponent } from './modules/sections/sections.component';
import { UnitsComponent } from './modules/units/units.component';
import { PaymentMethodsComponent } from './modules/payment-methods/payment-methods.component';
import { ProductsComponent } from './modules/products/products.component';
import { StoresComponent } from './modules/stores/stores.component';
import { WorkstationsComponent } from './modules/workstations/workstations.component';
import { StockComponent } from './operation/stock/stock.component';
import { CheckoutComponent } from './operation/checkout/checkout.component';
import { ProductTypesComponent } from './modules/product-types/product-types.component';


const routes: Routes = [
  {
    path: '', component: NavigationComponent,
    children: [
      { path: 'dashboard', component: DashboardComponent, data: { validate: false, module: 'dashboard', id: 'dashboard' }, canActivate: [SessionGuard] },
      { path: 'stores', component: StoresComponent, data: { validate: true, module: 'administration', id: 'stores' }, canActivate: [SessionGuard] },
      { path: 'workstations', component: WorkstationsComponent, data: { validate: true, module: 'administration', id: 'workstations' }, canActivate: [SessionGuard] },
      { path: 'users', component: UsersComponent, data: { validate: true, module: 'administration', id: 'users' }, canActivate: [SessionGuard] },
      { path: 'brands', component: BrandsComponent, data: { validate: true, module: 'administration', id: 'brands' }, canActivate: [SessionGuard] },
      { path: 'payment-methods', component: PaymentMethodsComponent, data: { validate: true, module: 'administration', id: 'payment-methods' }, canActivate: [SessionGuard] },
      { path: 'sections', component: SectionsComponent, data: { validate: true, module: 'administration', id: 'sections' }, canActivate: [SessionGuard] },
      { path: 'units', component: UnitsComponent, data: { validate: true, module: 'administration', id: 'units' }, canActivate: [SessionGuard] },
      { path: 'customers', component: CustomersComponent, data: { validate: true, module: 'administration', id: 'customers' }, canActivate: [SessionGuard] },
      { path: 'suppliers', component: SuppliersComponent, data: { validate: true, module: 'administration', id: 'suppliers' }, canActivate: [SessionGuard] },
      { path: 'product/types', component: ProductTypesComponent, data: { validate: true, module: 'administration', id: 'product-types' }, canActivate: [SessionGuard] },
      { path: 'products', component: ProductsComponent, data: { validate: true, module: 'administration', id: 'products' }, canActivate: [SessionGuard] },
      { path: 'configuration', component: ConfigurationComponent, data: { validate: true, module: 'administration', id: 'configuration' }, canActivate: [SessionGuard] },
      { path: 'checkout', component: CheckoutComponent, data: { validate: true, module: 'operation', id: 'checkout' }, canActivate: [SessionGuard] },
      
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ComponentsRoutingModule { }
