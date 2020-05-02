import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-stock',
  templateUrl: './stock.component.html',
  styleUrls: ['./stock.component.css']
})
export class StockComponent implements OnInit {
  public displayedColumns = ['itemVariant', 'quantity', 'price', 'lineAmount'];
  public total = 23;

  constructor() { }

  ngOnInit(): void {
  }

}
