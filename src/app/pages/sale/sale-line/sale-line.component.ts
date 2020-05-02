import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { SaleLineEditComponent } from '../sale-line-edit/sale-line-edit.component';
import { SaleService } from '../../../services/sale.service';

@Component({
  selector: 'app-sale-line',
  templateUrl: './sale-line.component.html',
  styleUrls: ['./sale-line.component.css']
})
export class SaleLineComponent implements OnInit {
  public displayedColumns = ['itemVariant', 'price', 'quantity', 'lineAmount', 'actions'];
  public routeId: number;

  constructor(public dialog: MatDialog,
              private route: ActivatedRoute,
              private router: Router,
              public saleService: SaleService
  ) {
  }

  async ngOnInit() {
    this.routeId = parseInt(this.route.snapshot.paramMap.get('id'), 10);
    await this.saleService.getAllSaleLinesByHeaderId(this.routeId);
  }

  public newSaleLine() {
    const dialogRef = this.dialog.open(SaleLineEditComponent, {
      data: {id: this.routeId}
    });
    dialogRef.afterClosed().subscribe(result => {
      if (!result) {
        window.location.reload();
      }
    });
  }

  public async onBack() {
    await this.router.navigate([`sales/${this.routeId}`]);
  }

  public async editSaleLine(saleLine) {
    const dialogRef = this.dialog.open(SaleLineEditComponent, {
      data: {id: this.routeId, content: saleLine}
    });
    dialogRef.afterClosed().subscribe(result => {
      if (!result) {
        window.location.reload();
      }
    });
  }

  public async deleteSaleLine(id) {
    await this.saleService.deleteSaleLine(id);
  }
}
