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
  public itemVariants;

  constructor(public dialogRef: MatDialogRef<SaleLineEditComponent>,
              private saleService: SaleService,
              public itemService: ItemService,
              @Inject(MAT_DIALOG_DATA) public data) {
  }

  async ngOnInit() {
    if (!this.data.content) {
      this.data.content = new SaleLine();
    }

    await this.itemService.getItems();

    this.item.valueChanges.subscribe(async () => {
      this.itemVariants = await this.itemService.getItemVariantsById(this.item.value.id);
    });
  }

  public async postNewSaleLine() {
    const saleLine = {
      item_variant_id: this.itemVariant.value.id,
      item_price: this.data.content.price,
      quantity: this.data.content.quantity,
    };
    await this.saleService.postNewSaleLine(this.data.id, saleLine);
    this.dialogRef.close();
  }

  public async updateSaleLine() {
    const saleLine = {
      item_variant_id: this.data.content.item_variant.id,
      item_price: this.data.content.price,
      quantity: this.data.content.quantity,
    };
    await this.saleService.updateSaleLine(this.data.id, saleLine);
    this.dialogRef.close();
  }
}