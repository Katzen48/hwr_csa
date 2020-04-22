import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ItemService {
  public items = [];

  constructor() {
  }

  public getItems() {
    this.items = [{id: 0, name: 'Jeans Gerda'}, {id: 1, name: 'T-Shirt Gerda'}];
  }
}
