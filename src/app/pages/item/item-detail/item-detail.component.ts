import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ItemVariantEditComponent } from '../item-variant-edit/item-variant-edit.component';
import { ActivatedRoute, Router } from '@angular/router';
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
              public itemService: ItemService,
              private router: Router
  ) {
  }

  async ngOnInit() {
    this.routeId = parseInt(this.route.snapshot.paramMap.get('id'), 10);
    if (this.routeId) {
      this.item = await this.itemService.getItemById(this.routeId);
      this.variants = await this.itemService.getItemVariantsById(this.routeId);
      this.itemName.setValue(this.item.name);
      this.dataSource = this.variants;
    }
  }

  public async postNewItem() {
    const item = {name: this.itemName.value};
    await this.itemService.postNewItem(item);
    await this.router.navigate(['items']);
  }

  public async updateItem() {
    const item = {name: this.itemName.value};
    await this.itemService.updateItem(this.routeId, item);
    await this.router.navigate(['items']);
  }

  public async deleteItem(id) {
    await this.itemService.deleteItem(id);
    await this.router.navigate(['items']);
  }

  public async newItemVariant() {
    const dialogRef = this.dialog.open(ItemVariantEditComponent, {
      data: {id: this.routeId}
    });
    dialogRef.afterClosed().subscribe(result => {
      if (!result) {
        location.reload();
      }
    });
  }

  public async editItemVariant(itemVariant) {
    const dialogRef = this.dialog.open(ItemVariantEditComponent, {
      data: {id: this.routeId, content: itemVariant}
    });
    dialogRef.afterClosed().subscribe(result => {
      if (!result) {
        location.reload();
      }
    });
  }

  public async deleteItemVariant(variantId) {
    await this.itemService.deleteItemVariant(this.routeId, variantId);
    location.reload();
  }

}
