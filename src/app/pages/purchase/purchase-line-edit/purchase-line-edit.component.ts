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
  public price = new FormControl();
  public itemVariants;

  public options = [
    {value: true, name: 'Ja'},
    {value: false, name: 'Nein'}
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
      this.data.content.delivered = false;
    } else {
      this.itemVariant.setValue(this.data.content.itemVariant);
      this.price.setValue(this.data.content.price);
    }

    await this.itemService.getAllItems();

    this.item.valueChanges.subscribe(async () => {
      this.itemVariants = await this.itemService.getItemVariantsById(this.item.value.id);
    });

    this.itemVariant.valueChanges.subscribe(itemVariant => {
      this.price.setValue(itemVariant.price);
    });
  }

  public async postNewPurchaseLine() {
    await this.purchaseService.postNewPurchaseLine(this.data.id, this.createPurchaseLineBody());
    this.dialogRef.close();
  }

  public async updatePurchaseLine() {
    await this.purchaseService.updatePurchaseLine(this.data.id, this.data.content.id, this.createPurchaseLineBody());
    this.dialogRef.close();
  }

  private createPurchaseLineBody() {
    return {
      item_variant_id: this.itemVariant.value.id,
      price: this.price.value,
      quantity: this.data.content.quantity,
      delivered: this.data.content.delivered
    };
  }
}
