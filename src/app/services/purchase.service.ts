import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PurchaseService {

  constructor(private http: HttpClient) { }

  public async postNewPurchaseHeader(purchaseHeader) {
    return await this.http.post(`${environment.backendUrl}/purchaseheader`, purchaseHeader, {observe: 'response'}).toPromise();
  }
}
