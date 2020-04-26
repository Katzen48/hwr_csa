import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class VendorService {
  public vendors;

  constructor(private http: HttpClient) { }

  public async getVendors() {
    this.vendors = await this.http.get(`${environment.backendUrl}/vendor`).toPromise();
  }

  public async getVendorById(id) {
    return await this.http.get(`${environment.backendUrl}/vendor/${id}`).toPromise();
  }

  public async postNewVendor(vendor) {
    await this.http.post(`${environment.backendUrl}/vendor`, vendor).toPromise();
  }

  public async updateVendor(id, vendor) {
    await this.http.put(`${environment.backendUrl}/vendor/${id}`, vendor).toPromise();
  }

  public async deleteVendor(id) {
    await this.http.delete(`${environment.backendUrl}/vendor/${id}`).toPromise();
  }
}
