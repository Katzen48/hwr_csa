import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ItemLedgerEntry } from '../models/itemLedgerEntry';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class StockService {
  public itemLedgerEntries: ItemLedgerEntry[];

  constructor(private http: HttpClient) { }

  public async getItemLedgerEntries() {
    this.itemLedgerEntries = await this.http.get<ItemLedgerEntry[]>(`${environment.backendUrl}/itemledgerentry/stock`).toPromise();
  }
}
