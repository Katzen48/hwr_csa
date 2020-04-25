import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { SaleLineEditComponent } from '../sale-line-edit/sale-line-edit.component';

@Component({
  selector: 'app-sale-line',
  templateUrl: './sale-line.component.html',
  styleUrls: ['./sale-line.component.css']
})
export class SaleLineComponent implements OnInit {
  public displayedColumns = ['itemVariant', 'price', 'quantity', 'lineAmount', 'actions'];
  public dataSource;
  public routeId: number;

  constructor(public dialog: MatDialog,
              private route: ActivatedRoute,
              private router: Router
  ) {
  }

  ngOnInit(): void {
    this.routeId = parseInt(this.route.snapshot.paramMap.get('id'), 10);
  }

  public editSaleLine() {

  }

  public deleteSaleLine() {

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
}
