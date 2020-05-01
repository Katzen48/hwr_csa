import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-purchase-line',
  templateUrl: './purchase-line.component.html',
  styleUrls: ['./purchase-line.component.css']
})
export class PurchaseLineComponent implements OnInit {
  public displayedColumns = ['itemVariant', 'price', 'quantity', 'lineAmount', 'delivered', 'actions'];
  public dataSource;
  public routeId: number;

  constructor(public dialog: MatDialog,
              private route: ActivatedRoute,
              private router: Router,
  ) {
  }

  async ngOnInit() {
    this.routeId = parseInt(this.route.snapshot.paramMap.get('id'), 10);
  }

  public async onBack() {
    await this.router.navigate([`sales/${this.routeId}`]);
  }
}
