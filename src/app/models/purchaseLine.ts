import { ItemVariant } from './itemVariant';
import { PurchaseHeader } from './purchaseHeader';

export class PurchaseLine {
  id: number;
  'purchase_header': PurchaseHeader;
  'item_variant': ItemVariant;
  price: number;
  quantity: number;
  'line_amount': number;
  delivered: boolean;
}
