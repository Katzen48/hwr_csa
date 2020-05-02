import { Vendor } from './vendor';

export class PurchaseHeader {
  id: number;
  vendor: Vendor;
  'posting_date': Date;
  'delivery_date': Date;
}
