import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class VendorService {
  public vendors;

  constructor() { }

  public getVendors() {
    this.vendors = [{id: 1, name: 'Meyers', address: 'Hinter den Feldern 1', postCode: 12345, city: 'Bremen', country: 'Deutschland'}];
  }

  public getVendorById(id) {
    return {id: 1, name: 'Heinrich', address: 'Hinter den Feldern 1', postCode: 12345, city: 'Bremen', country: 'Deutschland'};
  }
}
