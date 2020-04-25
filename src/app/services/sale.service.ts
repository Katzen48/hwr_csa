import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { SaleHeader } from '../models/saleHeader';

@Injectable({
  providedIn: 'root'
})
export class SaleService {

  constructor(private http: HttpClient) {
  }

  public async postNewSaleHeader(saleHeader) {
    return await this.http.post(`${environment.backendUrl}/salesheader`, saleHeader, {observe: 'response'}).toPromise();
  }

  public async getSaleHeaderById(id) {
    return await this.http.get<SaleHeader>(`${environment.backendUrl}/salesheader/${id}`).toPromise();
  }
}
