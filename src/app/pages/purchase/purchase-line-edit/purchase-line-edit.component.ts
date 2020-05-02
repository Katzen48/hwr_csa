import { Component, Inject, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ItemService } from '../../../services/item.service';
import { PurchaseLine } from '../../../models/purchaseLine';
import { PurchaseService } from '../../../services/purchase.service';

@Component({
  selector: 'app-purchase-line-edit',
  templateUrl: './purchase-line-edit.component.html',
  styleUrls: ['./purchase-line-edit.component.css']
})
export class PurchaseLineEditComponent implements OnInit {
  public item = new FormControl();
  public itemVariant = new FormControl();
  public itemVariants;

  public options = [
    {id: 1, name: 'Ja'},
    {id: 0, name: 'Nein'}
  ];

  constructor(public dialogRef: MatDialogRef<PurchaseLineEditComponent>,
              private purchaseService: PurchaseService,
              public itemService: ItemService,
              @Inject(MAT_DIALOG_DATA) public data
  ) {
  }

  async ngOnInit() {
    if (!this.data.content) {
      this.data.content = new PurchaseLine();
    }

    await this.itemService.getItems();

    this.item.valueChanges.subscribe(async () => {
      this.itemVariants = await this.itemService.getItemVariantsById(this.item.value.id);
    });
  }

  public async postNewPurchaseLine() {
    // await this.purchaseService.postNewPurchaseLine(this.data.id, this.createPurchaseLineBody());
    console.log('purchase line ', this.createPurchaseLineBody());
    this.dialogRef.close();
  }

  public async updatePurchaseLine() {
    // await this.purchaseService.updatePurchaseLine(this.data.id, this.createPurchaseLineBody());
    console.log('purchase line ', this.createPurchaseLineBody());
    this.dialogRef.close();
  }

  private createPurchaseLineBody() {
    return {
      item_variant_id: this.itemVariant.value.id,
      item_price: this.data.content.price,
      quantity: this.data.content.quantity,
      delivered: this.data.content.delivered.id
    };
  }
}
