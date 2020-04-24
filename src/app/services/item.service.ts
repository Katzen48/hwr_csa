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

  public async postNewItem(item) {
    await this.http.post(`${environment.backendUrl}/item`, item).toPromise();
  }

  public async updateItem(item) {
    await this.http.put(`${environment.backendUrl}/item`, item).toPromise();
  }

  public async deleteItem(id) {
    await this.http.delete(`${environment.backendUrl}/item/${id}`).toPromise();
  }

  public async getItemVariantsById(id: number) {
    return await this.http.get<ItemVariant[]>(`${environment.backendUrl}/item/${id}/variant`).toPromise();
  }

  public async postNewItemVariant(id, itemVariant) {
    await this.http.post(`${environment.backendUrl}/item/${id}/variant`, itemVariant).toPromise();
  }

  public deleteItemVariant(id) {
    console.log(`ItemVariant with id ${id} has been deleted`);
  }
}
