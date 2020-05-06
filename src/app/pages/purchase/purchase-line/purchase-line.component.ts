import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { PurchaseLineEditComponent } from '../purchase-line-edit/purchase-line-edit.component';
import { PurchaseService } from '../../../services/purchase.service';

@Component({
  selector: 'app-purchase-line',
  templateUrl: './purchase-line.component.html',
  styleUrls: ['./purchase-line.component.css']
})
export class PurchaseLineComponent implements OnInit {
  public displayedColumns = ['itemVariant', 'price', 'quantity', 'lineAmount', 'delivered', 'actions'];
  public routeId: number;

  constructor(public dialog: MatDialog,
              private route: ActivatedRoute,
              private router: Router,
              public purchaseService: PurchaseService
  ) {
  }

  async ngOnInit() {
    this.routeId = parseInt(this.route.snapshot.paramMap.get('id'), 10);
    await this.purchaseService.getAllPurchaseLinesByHeaderId(this.routeId);
  }

  public async onBack() {
    await this.router.navigate([`purchases`]);
  }

  public newPurchaseLine() {
    const dialogRef = this.dialog.open(PurchaseLineEditComponent, {
      data: {id: this.routeId}
    });
    dialogRef.afterClosed().subscribe(result => {
      if (!result) {
        window.location.reload();
      }
    });
  }

  public editPurchaseLine(purchaseLine) {
    const dialogRef = this.dialog.open(PurchaseLineEditComponent, {
      data: {id: this.routeId, content: purchaseLine}
    });
    dialogRef.afterClosed().subscribe(result => {
      if (!result) {
        window.location.reload();
      }
    });
  }

  public async deletePurchaseLine(id) {
    await this.purchaseService.deletePurchaseLine(this.routeId, id);
    window.location.reload();
  }

  public async onBookPurchase() {
    console.log('book purchase');
    await this.router.navigate(['purchases']);
  }
}
