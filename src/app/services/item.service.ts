import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Item } from '../models/item';
import { ItemVariant } from '../models/itemVariant';

@Injectable({
  providedIn: 'root'
})
export class ItemService {
  public items: Item[];

  constructor(private http: HttpClient) {
  }

  public async getItems() {
    this.items = await this.http.get<Item[]>(`${environment.backendUrl}/item`).toPromise();
  }

  public async getItemById(id: number) {
    return await this.http.get<Item>(`${environment.backendUrl}/item/${id}`).toPromise();
  }

  public deleteItem(id) {
    console.log(`Item with id ${id} has been deleted`);
  }

  public async getItemVariantsById(id: number) {
    return await this.http.get<ItemVariant[]>(`${environment.backendUrl}/item/${id}/variant`).toPromise();
  }

  public deleteItemVariant(id) {
    console.log(`ItemVariant with id ${id} has been deleted`);
  }
}
