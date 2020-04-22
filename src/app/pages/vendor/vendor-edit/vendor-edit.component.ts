import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { VendorService } from '../../../services/vendor.service';

@Component({
  selector: 'app-vendor-edit',
  templateUrl: './vendor-edit.component.html',
  styleUrls: ['./vendor-edit.component.css']
})
export class VendorEditComponent implements OnInit {
  public routeId: number;
  public vendor;

  public vendorFormGroup: FormGroup = new FormGroup({
    name: new FormControl(),
    address: new FormControl(),
    postCode: new FormControl(),
    city: new FormControl(),
    country: new FormControl()
  });

  constructor(private route: ActivatedRoute, public vendorService: VendorService) { }

  ngOnInit(): void {
    this.routeId = parseInt(this.route.snapshot.paramMap.get('id'), 10);
    if (this.routeId) {
      this.vendor = this.vendorService.getVendorById(this.routeId);
      this.vendorFormGroup.controls.name.setValue(this.vendor.name);
      this.vendorFormGroup.controls.address.setValue(this.vendor.address);
      this.vendorFormGroup.controls.postCode.setValue(this.vendor.postCode);
      this.vendorFormGroup.controls.city.setValue(this.vendor.city);
      this.vendorFormGroup.controls.country.setValue(this.vendor.country);
    }
  }

}
