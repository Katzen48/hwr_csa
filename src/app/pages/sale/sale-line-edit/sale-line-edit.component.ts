import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { SaleLine } from '../../../models/saleLine';
import { SaleService } from '../../../services/sale.service';
import { FormControl } from '@angular/forms';
import { ItemService } from '../../../services/item.service';

@Component({
  selector: 'app-sale-line-edit',
  templateUrl: './sale-line-edit.component.html',
  styleUrls: ['./sale-line-edit.component.css']
})
export class SaleLineEditComponent implements OnInit {
  public item = new FormControl();
  public itemVariant = new FormControl();
  public itemPrice = new FormControl();
  public itemVariants;

  constructor(public dialogRef: MatDialogRef<SaleLineEditComponent>,
              private saleService: SaleService,
              public itemService: ItemService,
              @Inject(MAT_DIALOG_DATA) public data) {
  }

  async ngOnInit() {
    if (!this.data.content) {
      this.data.content = new SaleLine();
    } else {
      this.itemVariant.setValue(this.data.content.itemVariant);
      this.itemPrice.setValue(this.data.content.itemPrice);
    }

    await this.itemService.getAllItems();

    this.item.valueChanges.subscribe(async () => {
      this.itemVariants = await this.itemService.getItemVariantsById(this.item.value.id);
    });

    this.itemVariant.valueChanges.subscribe(itemVariant => {
      this.data.content.itemPrice = itemVariant.price;
    });
  }

  public async postNewSaleLine() {
    await this.saleService.postNewSaleLine(this.data.id, this.createSaleLineBody());
    this.dialogRef.close();
  }

  public async updateSaleLine() {
    await this.saleService.updateSaleLine(this.data.id, this.data.content.id, this.createSaleLineBody());
    this.dialogRef.close();
  }

  private createSaleLineBody() {
    return {
      item_variant_id: this.itemVariant.value.id,
      item_price: this.itemPrice.value,
      quantity: this.data.content.quantity,
    };
  }
}
