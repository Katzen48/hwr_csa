import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ItemService {
  public items = [];

  constructor() {
  }

  public getItems() {
    this.items = ['Jeans Gerda', 'T-Shirt Gerda'];
  }
}
