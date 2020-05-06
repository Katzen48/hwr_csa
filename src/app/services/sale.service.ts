import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { SaleHeader } from '../models/saleHeader';
import { SaleLine } from '../models/saleLine';

@Injectable({
  providedIn: 'root'
})
export class SaleService {
  public saleHeaders: SaleHeader[];
  public saleLines: SaleLine[];

  constructor(private http: HttpClient) {
  }

  public async getAllSaleHeaders() {
    this.saleHeaders = await this.http.get<SaleHeader[]>(`${environment.backendUrl}/salesheader`).toPromise();
  }

  public async postNewSaleHeader(saleHeader) {
    return await this.http.post(`${environment.backendUrl}/salesheader`, saleHeader, {observe: 'response'}).toPromise();
  }

  public async getSaleHeaderById(id) {
    return await this.http.get<SaleHeader>(`${environment.backendUrl}/salesheader/${id}`).toPromise();
  }

  public async updateSaleHeader(id, saleHeader) {
    await this.http.put(`${environment.backendUrl}/salesheader/${id}`, saleHeader).toPromise();
  }

  public async deleteSaleHeader(id) {
    await this.http.delete(`${environment.backendUrl}/salesheader/${id}`).toPromise();
  }

  public async postNewSaleLine(id, saleLine) {
    await this.http.post(`${environment.backendUrl}/salesheader/${id}/line`, saleLine).toPromise();
  }

  public async getAllSaleLinesByHeaderId(id) {
    this.saleLines = await this.http.get<SaleLine[]>(`${environment.backendUrl}/salesheader/${id}/line`).toPromise();
  }

  public async updateSaleLine(headerId, lineId, saleLine) {
    await this.http.put(`${environment.backendUrl}/salesheader/${headerId}/line/${lineId}`, saleLine).toPromise();
  }

  public async deleteSaleLine(headerId, lineId) {
    await this.http.delete(`${environment.backendUrl}/salesheader/${headerId}/line/${lineId}`).toPromise();
  }

  public async postSaleLines(id) {
    return await this.http.post(`${environment.backendUrl}/salesheader/${id}/post`, null, {observe: 'response'}).toPromise();
  }
}
