import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ComponentsRoutingModule } from './components-routing.module';
import { DashboardComponent } from './common/dashboard/dashboard.component';
import { NavigationComponent } from './common/navigation/navigation.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { UsersComponent } from './modules/users/users.component';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UserFormComponent } from './modules/user-form/user-form.component';
import { MatSelectModule } from '@angular/material/select';
import { MatRadioModule } from '@angular/material/radio';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { CustomMatErrorComponent } from './common/ui/custom-mat-error/custom-mat-error.component';
import { MatMenuModule } from '@angular/material/menu';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { CustomersComponent } from './modules/customers/customers.component';
import { CustomerFormComponent } from './modules/customer-form/customer-form.component';
import { MatStepperModule } from '@angular/material/stepper';
import { ModulesPanelMenuComponent } from './common/modules-panel-menu/modules-panel-menu.component';
import { MatRippleModule, MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { ConfigurationComponent } from './modules/configuration/configuration.component';
import { DirectivesModule } from '../directives/directives.module';
import { MatDialogModule } from '@angular/material/dialog';
import { SuppliersComponent } from './modules/suppliers/suppliers.component';
import { SupplierFormComponent } from './modules/supplier-form/supplier-form.component';
import { BrandsComponent } from './modules/brands/brands.component';
import { BrandFormComponent } from './modules/brand-form/brand-form.component';
import { SectionsComponent } from './modules/sections/sections.component';
import { SectionFormComponent } from './modules/section-form/section-form.component';
import { UnitsComponent } from './modules/units/units.component';
import { UnitFormComponent } from './modules/unit-form/unit-form.component';
import { PaymentMethodsComponent } from './modules/payment-methods/payment-methods.component';
import { PaymentMethodFormComponent } from './modules/payment-method-form/payment-method-form.component';
import { ProductsComponent } from './modules/products/products.component';
import { ProductFormComponent } from './modules/product-form/product-form.component';
import { MatTabsModule } from '@angular/material/tabs';
import { StoresComponent } from './modules/stores/stores.component';
import { StoreFormComponent } from './modules/store-form/store-form.component';
import { WorkstationsComponent } from './modules/workstations/workstations.component';
import { WorkstationFormComponent } from './modules/workstation-form/workstation-form.component';
import { StockComponent } from './operation/stock/stock.component';
import { InitialStockComponent } from './operation/initial-stock/initial-stock.component';
import { CheckoutComponent } from './operation/checkout/checkout.component';
import { PipesModule } from '../pipes/pipes.module';
import { FlavorsComponent } from './modules/flavors/flavors.component';

@NgModule({
  declarations: [
    DashboardComponent,
    NavigationComponent,
    UsersComponent,
    UserFormComponent,
    CustomMatErrorComponent,
    CustomersComponent,
    CustomerFormComponent,
    SuppliersComponent,
    SupplierFormComponent,
    ModulesPanelMenuComponent,
    ConfigurationComponent,
    BrandsComponent,
    BrandFormComponent,
    SectionsComponent,
    SectionFormComponent,
    UnitsComponent,
    UnitFormComponent,
    PaymentMethodsComponent,
    PaymentMethodFormComponent,
    ProductsComponent,
    ProductFormComponent,
    StoresComponent,
    StoreFormComponent,
    WorkstationsComponent,
    WorkstationFormComponent,
    StockComponent,
    InitialStockComponent,
    CheckoutComponent,
    FlavorsComponent
  ],
  imports: [
    CommonModule,
    ComponentsRoutingModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatFormFieldModule,
    MatInputModule,
    MatCardModule,
    MatProgressSpinnerModule,
    FormsModule,
    MatSelectModule,
    MatRadioModule,
    ReactiveFormsModule,
    MatExpansionModule,
    MatSlideToggleModule,
    MatCheckboxModule,
    MatMenuModule,
    MatSnackBarModule,
    MatStepperModule,
    MatRippleModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatDialogModule,
    DirectivesModule,
    MatTabsModule,
    PipesModule
  ]
})
export class ComponentsModule { }
