import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { VendorService } from '../../../services/vendor.service';
import { Vendor } from '../../../models/vendor';

@Component({
  selector: 'app-vendor-edit',
  templateUrl: './vendor-edit.component.html',
  styleUrls: ['./vendor-edit.component.css']
})
export class VendorEditComponent implements OnInit {
  public routeId: number;
  public vendor: Vendor;

  public vendorFormGroup: FormGroup = new FormGroup({
    name: new FormControl(),
    address: new FormControl(),
    postCode: new FormControl(),
    city: new FormControl(),
    country: new FormControl()
  });

  constructor(private route: ActivatedRoute,
              public vendorService: VendorService,
              private router: Router
  ) {
  }

  async ngOnInit() {
    this.routeId = parseInt(this.route.snapshot.paramMap.get('id'), 10);
    if (this.routeId) {
      this.vendor = await this.vendorService.getVendorById(this.routeId);
      this.vendorFormGroup.controls.name.setValue(this.vendor.name);
      this.vendorFormGroup.controls.address.setValue(this.vendor.address);
      this.vendorFormGroup.controls.postCode.setValue(this.vendor.post_code);
      this.vendorFormGroup.controls.city.setValue(this.vendor.city);
      this.vendorFormGroup.controls.country.setValue(this.vendor.country);
    }
  }

  public async postNewVendor() {
    await this.vendorService.postNewVendor(this.createVendorBody());
    await this.router.navigate(['vendors']);
  }

  public async updateVendor() {
    await this.vendorService.updateVendor(this.routeId, this.createVendorBody());
    await this.router.navigate(['vendors']);
  }

  public async deleteVendor() {
    await this.vendorService.deleteVendor(this.routeId);
    await this.router.navigate(['vendors']);
  }

  public async onBack() {
    await this.router.navigate([`vendors`]);
  }

  private createVendorBody() {
    return {
      name: this.vendorFormGroup.controls.name.value,
      address: this.vendorFormGroup.controls.address.value,
      post_code: this.vendorFormGroup.controls.postCode.value,
      city: this.vendorFormGroup.controls.city.value,
      country: this.vendorFormGroup.controls.country.value,
    };
  }
}
