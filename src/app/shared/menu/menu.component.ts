import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  @Input()
  currentTab: string;

  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }

  public async onDashboard() {
    await this.router.navigate(['dashboard']);
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
