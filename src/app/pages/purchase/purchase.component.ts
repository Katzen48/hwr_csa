import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { VendorService } from '../../services/vendor.service';
import { ActivatedRoute, Router } from '@angular/router';
import { DateAdapter } from '@angular/material/core';
import * as moment from 'moment';
import { PurchaseService } from '../../services/purchase.service';

@Component({
  selector: 'app-order',
  templateUrl: './purchase.component.html',
  styleUrls: ['./purchase.component.css']
})
export class PurchaseComponent implements OnInit {
  public routeId: number;
  public vendorId = new FormControl();
  public postingDate = new FormControl();
  public deliveryDate = new FormControl();

  constructor(public vendorService: VendorService,
              public purchaseService: PurchaseService,
              private route: ActivatedRoute,
              private router: Router,
              private dateAdapter: DateAdapter<Date>
  ) {
    this.dateAdapter.setLocale('de');
  }

  async ngOnInit() {
    this.routeId = parseInt(this.route.snapshot.paramMap.get('id'), 10);

    await this.vendorService.getAllVendors();

    if (this.routeId) {
      const purchaseHeader = await this.purchaseService.getPurchaseHeaderById(this.routeId);
      this.vendorId.setValue(purchaseHeader.vendor.id);
      this.postingDate.setValue(new Date(purchaseHeader.posting_date));
      this.deliveryDate.setValue(new Date(purchaseHeader.delivery_date));
    }
  }

  public async postNewPurchaseHeader() {
    const response = await this.purchaseService.postNewPurchaseHeader(this.createPurchaseHeaderBody());
    const locationSplit = response.headers.get('location').split('/');
    const id = locationSplit[locationSplit.length - 1];

    await this.router.navigate([`purchases/${id}/lines`]);
  }

  public async updatePurchaseHeader() {
    // TODO implement methode
  }

  public async deletePurchaseHeader() {
    await this.purchaseService.deletePurchaseHeader(this.routeId);
    await this.router.navigate([`purchases`]);
  }

  public async onCancel() {
    await this.router.navigate([`dashboard`]);
  }

  private createPurchaseHeaderBody() {
    return {
      vendor_id: this.vendorId.value,
      posting_date: moment(this.postingDate.value).add(1, 'day'),
      delivery_date: moment(this.deliveryDate.value).add(1, 'day')
    };
  }
}
