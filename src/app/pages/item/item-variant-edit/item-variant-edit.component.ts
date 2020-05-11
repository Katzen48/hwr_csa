import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ItemVariant } from '../../../models/itemVariant';
import { ItemService } from '../../../services/item.service';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-item-variant-edit',
  templateUrl: './item-variant-edit.component.html',
  styleUrls: ['./item-variant-edit.component.css']
})
export class ItemVariantEditComponent implements OnInit {
  public price = new FormControl();

  constructor(public dialogRef: MatDialogRef<ItemVariantEditComponent>,
              @Inject(MAT_DIALOG_DATA) public data,
              public itemService: ItemService
  ) {
  }

  ngOnInit(): void {
    if (!this.data.content) {
      this.data.content = new ItemVariant();
    } else {
      this.price.setValue(this.data.content.price);
    }
  }

  public async postNewItemVariant() {
    await this.itemService.postNewItemVariant(this.data.id, this.createItemVariantBody());
    this.dialogRef.close();
  }

  public async updateItemVariant() {
    await this.itemService.updateItemVariant(this.data.id, this.data.content.id, this.createItemVariantBody());
    this.dialogRef.close();
  }

  private createItemVariantBody() {
    return {
      name: this.data.content.name,
      price: this.price.value,
      size: this.data.content.size
    };
  }
}
