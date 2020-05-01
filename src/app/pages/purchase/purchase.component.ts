import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { VendorService } from '../../services/vendor.service';
import { ActivatedRoute, Router } from '@angular/router';
import { DateAdapter } from '@angular/material/core';

@Component({
  selector: 'app-order',
  templateUrl: './purchase.component.html',
  styleUrls: ['./purchase.component.css']
})
export class PurchaseComponent implements OnInit {
  public routeId: number;
  public vendor = new FormControl();
  public postingDate = new FormControl();
  public deliveryDate = new FormControl();

  constructor(public vendorService: VendorService,
              private route: ActivatedRoute,
              private router: Router,
              private dateAdapter: DateAdapter<Date>
  ) {
    this.dateAdapter.setLocale('de');
  }

  async ngOnInit() {
    this.routeId = parseInt(this.route.snapshot.paramMap.get('id'), 10);

    await this.vendorService.getVendors();
  }

  public async onCancel() {
    await this.router.navigate([`dashboard`]);
  }
}
