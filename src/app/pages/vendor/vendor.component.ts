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

  constructor(public vendorService: VendorService, private router: Router) {
  }

  async ngOnInit() {
    await this.vendorService.getVendors();
  }

  public async newVendor() {
    await this.router.navigate(['vendors/vendor-edit']);
  }

  public async editVendor(id) {
    await this.router.navigate([`vendors/vendor-edit/${id}`]);
  }

  public async deleteVendor(id) {
    await this.vendorService.deleteVendor(id);
    location.reload();
  }
}
