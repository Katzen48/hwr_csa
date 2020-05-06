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
import { PurchaseComponent } from './pages/purchase/purchase.component';
import { PurchaseLineComponent } from './pages/purchase/purchase-line/purchase-line.component';
import { StockComponent } from './pages/stock/stock.component';
import { PurchaseEditComponent } from './pages/purchase/purchase-edit/purchase-edit.component';
import { SaleEditComponent } from './pages/sale/sale-edit/sale-edit.component';


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
    path: 'sales/edit',
    component: SaleEditComponent,
  },
  {
    path: 'sales/edit/:id',
    component: SaleEditComponent,
  },
  {
    path: 'sales/:id/lines',
    component: SaleLineComponent,
  },
  {
    path: 'purchases',
    component: PurchaseComponent,
  },
  {
    path: 'purchases/edit',
    component: PurchaseEditComponent,
  },
  {
    path: 'purchases/edit/:id',
    component: PurchaseEditComponent,
  },
  {
    path: 'purchases/:id/lines',
    component: PurchaseLineComponent,
  },
  {
    path: 'stocks',
    component: StockComponent,
  },
  {path: '**', redirectTo: 'dashboard'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
