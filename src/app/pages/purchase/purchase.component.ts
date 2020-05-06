import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PurchaseService } from '../../services/purchase.service';
import * as moment from 'moment';

@Component({
  selector: 'app-order',
  templateUrl: './purchase.component.html',
  styleUrls: ['./purchase.component.css']
})
export class PurchaseComponent implements OnInit {
  public displayedColumns = ['vendor', 'postingDate', 'deliveryDate', 'actions'];

  constructor(public purchaseService: PurchaseService,
              private router: Router,
  ) {
  }

  async ngOnInit() {
    await this.purchaseService.getAllPurchaseHeader();
  }

  public formatDate(date) {
    const realDate = new Date(date);
    return moment(realDate).format('DD.MM.YYYY');
  }

  public async onShowLines(id) {
    await this.router.navigate([`purchases/${id}/lines`]);
  }

  public async onEditPurchaseHeader(id) {
    await this.router.navigate([`purchases/edit/${id}`]);
  }

  public async deletePurchaseHeader(id) {
    await this.purchaseService.deletePurchaseHeader(id);
    window.location.reload();
  }

  public async onNewPurchaseHeader() {
    await this.router.navigate([`purchases/edit`]);
  }
}
