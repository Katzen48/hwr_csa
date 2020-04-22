import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ItemVariant } from '../../../models/itemVariant';

@Component({
  selector: 'app-item-variant-edit',
  templateUrl: './item-variant-edit.component.html',
  styleUrls: ['./item-variant-edit.component.css']
})
export class ItemVariantEditComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<ItemVariantEditComponent>,
              @Inject(MAT_DIALOG_DATA) public data) { }

  ngOnInit(): void {
    if (!this.data) {
      this.data = new ItemVariant();
    }
  }

  onCancelClick(): void {
    this.dialogRef.close();
  }
}
