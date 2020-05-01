import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PurchaseLineComponent } from '../pages/purchase/purchase-line/purchase-line.component';

describe('PurchaseLineComponent', () => {
  let component: PurchaseLineComponent;
  let fixture: ComponentFixture<PurchaseLineComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PurchaseLineComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PurchaseLineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
