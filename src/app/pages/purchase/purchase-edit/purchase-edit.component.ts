import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { VendorService } from '../../../services/vendor.service';
import { PurchaseService } from '../../../services/purchase.service';
import { ActivatedRoute, Router } from '@angular/router';
import { DateAdapter } from '@angular/material/core';
import * as moment from 'moment';

@Component({
  selector: 'app-purchase-edit',
  templateUrl: './purchase-edit.component.html',
  styleUrls: ['./purchase-edit.component.css']
})
export class PurchaseEditComponent implements OnInit {
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
    } else {
      this.postingDate.setValue(new Date());
    }
  }

  public async postNewPurchaseHeader() {
    const response = await this.purchaseService.postNewPurchaseHeader(this.createPurchaseHeaderBody());
    const locationSplit = response.headers.get('location').split('/');
    const id = locationSplit[locationSplit.length - 1];

    await this.router.navigate([`purchases/${id}/lines`]);
  }

  public async updatePurchaseHeader() {
    await this.purchaseService.updatePurchaseHeader(this.routeId, this.createPurchaseHeaderBody());
    await this.router.navigate([`purchases`]);
  }

  public async deletePurchaseHeader() {
    await this.purchaseService.deletePurchaseHeader(this.routeId);
    await this.router.navigate([`purchases`]);
  }

  public async onCancel() {
    await this.router.navigate([`purchases`]);
  }

  private createPurchaseHeaderBody() {
    return {
      vendor_id: this.vendorId.value,
      posting_date: moment(this.postingDate.value).add(2, 'hours'),
      delivery_date: moment(this.deliveryDate.value).add(2, 'hours')
    };
  }
}