import { Component, OnInit } from '@angular/core';
import { VendorService } from '../../services/vendor.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-vendor',
  templateUrl: './vendor.component.html',
  styleUrls: ['./vendor.component.css']
})
export class VendorComponent implements OnInit {
  public displayedColumns = ['name', 'address', 'postCode', 'city', 'country', 'actions'];
  public dataSource;

  constructor(public vendorService: VendorService, private router: Router) {
  }

  ngOnInit(): void {
    this.vendorService.getVendors();
    this.dataSource = this.vendorService.vendors;
  }

  public async newVendor() {
    await this.router.navigate(['vendors/vendor-edit']);
  }

  public async editVendor(id) {
    await this.router.navigate([`vendors/vendor-edit/${id}`]);
  }

  public deleteVendor(id) {

  }
}
