import { Component, OnInit } from '@angular/core';
import { SaleService } from '../../services/sale.service';
import { Router } from '@angular/router';
import * as moment from 'moment';

@Component({
  selector: 'app-sale',
  templateUrl: './sale.component.html',
  styleUrls: ['./sale.component.css']
})
export class SaleComponent implements OnInit {
  public displayedColumns = ['employee', 'postingDate', 'actions'];

  constructor(public saleService: SaleService,
              private router: Router,
  ) {
  }

  async ngOnInit() {
    await this.saleService.getAllSaleHeaders();
  }

  public formatDate(date) {
    const realDate = new Date(date);
    return moment(realDate).format('DD.MM.YYYY');
  }

  public async onShowLines(id) {
    await this.router.navigate([`sales/${id}/lines`]);
  }

  public async onEditSalesHeader(id) {
    await this.router.navigate([`sales/edit/${id}`]);
  }

  public async deleteSaleHeader(id) {
    await this.saleService.deleteSaleHeader(id);
    window.location.reload();
  }

  public async onNewSaleHeader() {
    await this.router.navigate([`sales/edit`]);
  }
}
