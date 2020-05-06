import { Component, OnInit } from '@angular/core';
import { StockService } from '../../services/stock.service';

@Component({
  selector: 'app-stock',
  templateUrl: './stock.component.html',
  styleUrls: ['./stock.component.css']
})
export class StockComponent implements OnInit {
  public displayedColumns = ['itemVariant', 'quantity'];

  constructor(public stockService: StockService) { }

  async ngOnInit() {
    await this.stockService.getItemLedgerEntries();
  }

}
