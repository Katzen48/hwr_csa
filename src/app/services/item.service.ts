import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ItemService {
  public items = [];

  constructor() {
  }

  public getItems() {
    this.items = [{id: 1, name: 'Jeans Gerda'}, {id: 2, name: 'T-Shirt Gerda'}];
  }

  public getItemById(id: number) {
    return {id: 3, name: 'Beispielartikel'};
  }

  public deleteItem(id) {
    console.log(`Item with id ${id} has been deleted`);
  }

  public getItemVariantsById(id: number) {
    return [
      {
        id: 1,
        item_id: 4,
        name: 'Jeans \'Gerda\' Blau Gestreift M',
        price: 17.99,
        size: 'M'
      },
      {
        id: 2,
        item_id: 4,
        name: 'Jeans \'Gerda\' Rot Gestreift S',
        price: 17.99,
        size: 'S'
      }
    ];
  }

  public deleteItemVariant(id) {
    console.log(`ItemVariant with id ${id} has been deleted`);
  }
}
