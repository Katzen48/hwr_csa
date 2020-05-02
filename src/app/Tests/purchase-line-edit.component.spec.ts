import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PurchaseLineEditComponent } from '../pages/purchase/purchase-line-edit/purchase-line-edit.component';

describe('PurchaseLineEditComponent', () => {
  let component: PurchaseLineEditComponent;
  let fixture: ComponentFixture<PurchaseLineEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PurchaseLineEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PurchaseLineEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
