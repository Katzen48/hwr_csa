import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { SaleLine } from '../../../models/saleLine';

@Component({
  selector: 'app-sale-line-edit',
  templateUrl: './sale-line-edit.component.html',
  styleUrls: ['./sale-line-edit.component.css']
})
export class SaleLineEditComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<SaleLineEditComponent>,
              @Inject(MAT_DIALOG_DATA) public data) {
  }

  ngOnInit(): void {
    if (!this.data.content) {
      this.data.content = new SaleLine();
    }
  }

}
