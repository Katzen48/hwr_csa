import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { ItemComponent } from './pages/item/item.component';
import { ItemDetailComponent } from './pages/item/item-detail/item-detail.component';
import { EmployeeComponent } from './pages/employee/employee.component';
import { EmployeeDetailComponent } from './pages/employee/employee-detail/employee-detail.component';
import { VendorComponent } from './pages/vendor/vendor.component';
import { VendorEditComponent } from './pages/vendor/vendor-edit/vendor-edit.component';
import { SaleComponent } from './pages/sale/sale.component';
import { SaleLineComponent } from './pages/sale/sale-line/sale-line.component';


const routes: Routes = [
  {
    path: 'dashboard',
    component: DashboardComponent,
  },
  {
    path: 'items',
    component: ItemComponent,
  },
  {
    path: 'items/item-detail',
    component: ItemDetailComponent,
  },
  {
    path: 'items/item-detail/:id',
    component: ItemDetailComponent,
  },
  {
    path: 'employees',
    component: EmployeeComponent,
  },
  {
    path: 'employees/employee-detail',
    component: EmployeeDetailComponent,
  },
  {
    path: 'employees/employee-detail/:id',
    component: EmployeeDetailComponent,
  },
  {
    path: 'vendors',
    component: VendorComponent,
  },
  {
    path: 'vendors/vendor-edit',
    component: VendorEditComponent,
  },
  {
    path: 'vendors/vendor-edit/:id',
    component: VendorEditComponent,
  },
  {
    path: 'sales',
    component: SaleComponent,
  },
  {
    path: 'sales/:id',
    component: SaleComponent,
  },
  {
    path: 'sales/:id/lines',
    component: SaleLineComponent,
  },
  {path: '**', redirectTo: 'dashboard'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
