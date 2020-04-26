import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ItemVariant } from '../../../models/itemVariant';
import { ItemService } from '../../../services/item.service';

@Component({
  selector: 'app-item-variant-edit',
  templateUrl: './item-variant-edit.component.html',
  styleUrls: ['./item-variant-edit.component.css']
})
export class ItemVariantEditComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<ItemVariantEditComponent>,
              @Inject(MAT_DIALOG_DATA) public data,
              public itemService: ItemService
              ) {
  }

  ngOnInit(): void {
    if (!this.data.content) {
      this.data.content = new ItemVariant();
    }
  }

  public async postNewItemVariant() {
    const itemVariant = {
      name: this.data.content.name,
      price: this.data.content.price,
      size: this.data.content.size
    };
    await this.itemService.postNewItemVariant(this.data.id, itemVariant);
    this.dialogRef.close();
  }

  public async updateItemVariant() {
    const itemVariant = {
      name: this.data.content.name,
      price: this.data.content.price,
      size: this.data.content.size
    };
    await this.itemService.updateItemVariant(this.data.id, this.data.content.id, itemVariant);
    this.dialogRef.close();
  }
}
