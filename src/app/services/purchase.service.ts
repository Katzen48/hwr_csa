import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { PurchaseLine } from '../models/purchaseLine';
import { PurchaseHeader } from '../models/purchaseHeader';

@Injectable({
  providedIn: 'root'
})
export class PurchaseService {
  public purchaseHeaders: PurchaseHeader[];
  public purchaseLines: PurchaseLine[];

  constructor(private http: HttpClient) {
  }

  public async getAllPurchaseHeader() {
    this.purchaseHeaders = await this.http.get<PurchaseHeader[]>(`${environment.backendUrl}/purchaseheader`).toPromise();
  }

  public async postNewPurchaseHeader(purchaseHeader) {
    return await this.http.post(`${environment.backendUrl}/purchaseheader`, purchaseHeader, {observe: 'response'}).toPromise();
  }

  public async getPurchaseHeaderById(id) {
    return await this.http.get<PurchaseHeader>(`${environment.backendUrl}/purchaseheader/${id}`).toPromise();
  }

  public async updatePurchaseHeader(id, purchaseHeader) {
    await this.http.put(`${environment.backendUrl}/purchaseheader/${id}`, purchaseHeader).toPromise();
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

  public async updatePurchaseLine(headerId, lineId, purchaseLine) {
    await this.http.put(`${environment.backendUrl}/purchaseheader/${headerId}/line/${lineId}`, purchaseLine).toPromise();
  }

  public async deletePurchaseLine(headerId, lineId) {
    await this.http.delete(`${environment.backendUrl}/purchaseheader/${headerId}/line/${lineId}`).toPromise();
  }
}
