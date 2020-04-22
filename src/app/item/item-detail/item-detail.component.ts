import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-item-detail',
  templateUrl: './item-detail.component.html',
  styleUrls: ['./item-detail.component.css']
})
export class ItemDetailComponent implements OnInit {
  public displayedColumns = ['name', 'price', 'size'];

  constructor() { }

  ngOnInit(): void {
  }

}
