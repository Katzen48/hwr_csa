import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SaleLineEditComponent } from '../pages/sale/sale-line-edit/sale-line-edit.component';

describe('SaleLineEditComponent', () => {
  let component: SaleLineEditComponent;
  let fixture: ComponentFixture<SaleLineEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SaleLineEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SaleLineEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
