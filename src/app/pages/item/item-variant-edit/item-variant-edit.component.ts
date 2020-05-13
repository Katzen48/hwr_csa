import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ItemVariant } from '../../../models/itemVariant';
import { ItemService } from '../../../services/item.service';
import { FormControl } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-item-variant-edit',
  templateUrl: './item-variant-edit.component.html',
  styleUrls: ['./item-variant-edit.component.css']
})
export class ItemVariantEditComponent implements OnInit {
  public price = new FormControl();

  constructor(public dialogRef: MatDialogRef<ItemVariantEditComponent>,
              @Inject(MAT_DIALOG_DATA) public data,
              public itemService: ItemService,
              private snackBar: MatSnackBar
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
    try {
      await this.itemService.postNewItemVariant(this.data.id, this.createItemVariantBody());
      this.dialogRef.close();
    } catch (e) {
      if (e.status === 404) {
        this.snackBar.open('Ein zugehöriger Artikel konnte nicht gefunden werden. Überprüfen Sie bitte, ob Sie bereits einen' +
          ' Artikel erstellt haben.', null, {verticalPosition: 'top'});
      }
    }
  }

  public async updateItemVariant() {
    await this.itemService.updateItemVariant(this.data.id, this.data.content.id, this.createItemVariantBody());
    this.dialogRef.close();
  }

  private createItemVariantBody() {
    return {
      name: this.data.content.name,
      price: this.formatPrice(this.price.value),
      size: this.data.content.size
    };
  }

  private formatPrice(price) {
    return price.replace(',', '.');
  }
}
