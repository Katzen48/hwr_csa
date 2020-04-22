import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DashboardComponent} from './dashboard/dashboard.component';
import { ItemComponent } from './item/item.component';
import { ItemDetailComponent } from './item/item-detail/item-detail.component';


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
  {path: '**', redirectTo: 'dashboard'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
