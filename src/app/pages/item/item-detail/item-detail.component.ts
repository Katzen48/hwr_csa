import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ItemVariantEditComponent } from '../item-variant-edit/item-variant-edit.component';
import { ActivatedRoute } from '@angular/router';
import { FormControl } from '@angular/forms';
import { ItemService } from '../../../services/item.service';

@Component({
  selector: 'app-item-detail',
  templateUrl: './item-detail.component.html',
  styleUrls: ['./item-detail.component.css']
})
export class ItemDetailComponent implements OnInit {
  public routeId: number;
  public displayedColumns = ['name', 'price', 'size', 'actions'];
  public dataSource;
  public item;
  public variants;
  public itemName = new FormControl();

  constructor(public dialog: MatDialog,
              private route: ActivatedRoute,
              public itemService: ItemService
  ) {
  }

  ngOnInit(): void {
    this.routeId = parseInt(this.route.snapshot.paramMap.get('id'), 10);
    if (this.routeId) {
      this.item = this.itemService.getItemById(this.routeId);
      this.variants = this.itemService.getItemVariantsById(this.routeId);
      this.itemName.setValue(this.item.name);
      this.dataSource = this.variants;
    }
  }

  public newItemVariant() {
    const dialogRef = this.dialog.open(ItemVariantEditComponent);
  }

  public editItemVariant(itemVariant) {
    const dialogRef = this.dialog.open(ItemVariantEditComponent, {data: itemVariant});
  }

  public deleteItemVariant(id) {
    this.itemService.deleteItemVariant(id);
  }

  public deleteItem(id) {
    this.itemService.deleteItem(id);
  }
}
