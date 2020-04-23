import { Component, OnInit } from '@angular/core';
import { ItemService } from '../../services/item.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.css']
})
export class ItemComponent implements OnInit {

  constructor(public itemService: ItemService, private router: Router) {
  }

  async ngOnInit() {
    await this.itemService.getItems();
  }

  public async onNewItem() {
    await this.router.navigate(['items/item-detail']);
  }

  public async onShowItem(id: number) {
    await this.router.navigate([`items/item-detail/${id}`]);
  }
}
