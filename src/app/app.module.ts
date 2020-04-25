import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { ItemComponent } from './pages/item/item.component';
import { MenuComponent } from './shared/menu/menu.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule } from '@angular/material/button';
import { ItemDetailComponent } from './pages/item/item-detail/item-detail.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatTableModule } from '@angular/material/table';
import { ItemVariantEditComponent } from './pages/item/item-variant-edit/item-variant-edit.component';
import { MatDialogModule } from '@angular/material/dialog';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { EmployeeComponent } from './pages/employee/employee.component';
import { EmployeeDetailComponent } from './pages/employee/employee-detail/employee-detail.component';
import { VendorComponent } from './pages/vendor/vendor.component';
import { VendorEditComponent } from './pages/vendor/vendor-edit/vendor-edit.component';
import { HttpClientModule } from '@angular/common/http';
import { SaleComponent } from './pages/sale/sale.component';
import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { SaleLineComponent } from './pages/sale/sale-line/sale-line.component';
import { SaleLineEditComponent } from './pages/sale/sale-line-edit/sale-line-edit.component';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    ItemComponent,
    MenuComponent,
    ItemDetailComponent,
    ItemVariantEditComponent,
    EmployeeComponent,
    EmployeeDetailComponent,
    VendorComponent,
    VendorEditComponent,
    SaleComponent,
    SaleLineComponent,
    SaleLineEditComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FlexLayoutModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatTableModule,
    MatDialogModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule
  ],
  providers: [MatDatepickerModule],
  bootstrap: [AppComponent]
})
export class AppModule {
}
