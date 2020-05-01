import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { SaleHeader } from '../models/saleHeader';
import { SaleLine } from '../models/saleLine';

@Injectable({
  providedIn: 'root'
})
export class SaleService {
  public saleLines: SaleLine[];

  constructor(private http: HttpClient) {
  }

  public async postNewSaleHeader(saleHeader) {
    return await this.http.post(`${environment.backendUrl}/salesheader`, saleHeader, {observe: 'response'}).toPromise();
  }

  public async getSaleHeaderById(id) {
    return await this.http.get<SaleHeader>(`${environment.backendUrl}/salesheader/${id}`).toPromise();
  }

  // TODO check urls
  public async updateSaleHeader(id, saleHeader) {
    await this.http.put(`${environment.backendUrl}/salesheader/${id}`, saleHeader).toPromise();
  }

  public async deleteSaleHeader(id) {
    await this.http.delete(`${environment.backendUrl}/salesheader/${id}`).toPromise();
  }

  public async postNewSaleLine(id, saleLine) {
    await this.http.post(`${environment.backendUrl}/salesheader/${id}/line`, saleLine).toPromise();
  }

  public async searchItemVariants(search) {
    return await this.http.post(`${environment.backendUrl}/itemvariants/search`, {query: search}).toPromise();
  }

  public async getAllSaleLinesByHeaderId(id) {
    // this.saleLines = await this.http.get<SaleLine[]>(`${environment.backendUrl}/salesheader/${id}`).toPromise();
  }

  public async updateSaleLine(id, saleLine) {
    // await this.http.put(`${environment.backendUrl}/salesheader/${id}`, saleLine).toPromise();
  }

  public async deleteSaleLine(id) {
    // await this.http.delete(`${environment.backendUrl}/salesheader/${id}`).toPromise();
  }
}
