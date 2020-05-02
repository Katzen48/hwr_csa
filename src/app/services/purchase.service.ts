import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { PurchaseLine } from '../models/purchaseLine';
import { PurchaseHeader } from '../models/purchaseHeader';

@Injectable({
  providedIn: 'root'
})
export class PurchaseService {
  purchaseLines: PurchaseLine[];

  constructor(private http: HttpClient) {
  }

  public async postNewPurchaseHeader(purchaseHeader) {
    return await this.http.post(`${environment.backendUrl}/purchaseheader`, purchaseHeader, {observe: 'response'}).toPromise();
  }

  public async getPurchaseHeaderById(id) {
    return await this.http.get<PurchaseHeader>(`${environment.backendUrl}/purchaseheader/${id}`).toPromise();
  }

  public async updatePurchaseHeader(id, saleHeader) {
    await this.http.put(`${environment.backendUrl}/purchaseheader/${id}`, saleHeader).toPromise();
  }

  public async deletePurchaseHeader(id) {
    await this.http.delete(`${environment.backendUrl}/purchaseheader/${id}`).toPromise();
  }

  public async getAllPurchaseLinesByHeaderId(id) {
    this.purchaseLines = await this.http.get<PurchaseLine[]>(`${environment.backendUrl}/purchaseheader/${id}/line`).toPromise();
  }

  public async postNewPurchaseLine(id, purchaseLine) {
    await this.http.post(`${environment.backendUrl}/purchaseheader/${id}/line`, purchaseLine).toPromise();
  }

  public async updatePurchaseLine(id, purchaseLine) {
    await this.http.put(`${environment.backendUrl}/purchaseheader/${id}/line`, purchaseLine).toPromise();
  }

  public async deletePurchaseLine(id) {
    await this.http.delete(`${environment.backendUrl}/purchaseheader/${id}/line`).toPromise();
  }
}
