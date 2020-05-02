import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }

  public async onStock() {
    await this.router.navigate(['stocks']);
  }

  public async onItems() {
    await this.router.navigate(['items']);
  }

  public async onEmployees() {
    await this.router.navigate(['employees']);
  }

  public async onVendors() {
    await this.router.navigate(['vendors']);
  }

  public async onSale() {
    await this.router.navigate(['sales']);
  }

  public async onPurchases() {
    await this.router.navigate(['purchases']);
  }
}
