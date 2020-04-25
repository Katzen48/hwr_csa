import { SaleHeader } from './saleHeader';
import { ItemVariant } from './itemVariant';

export class SaleLine {
  id: number;
  'sale_header': SaleHeader;
  'item_variant': ItemVariant;
  price: number;
  quantity: number;
  'line_amount': number;
}
