import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { EmployeeService } from '../../services/employee.service';
import { DateAdapter } from '@angular/material/core';
import { SaleService } from '../../services/sale.service';
import { ActivatedRoute, Router } from '@angular/router';
import * as moment from 'moment';

@Component({
  selector: 'app-sale',
  templateUrl: './sale.component.html',
  styleUrls: ['./sale.component.css']
})
export class SaleComponent implements OnInit {
  public routeId;
  public saleHeader;

  public saleFormGroup: FormGroup = new FormGroup({
    employeeId: new FormControl(),
    posting_date: new FormControl(),
  });

  constructor(public employeeService: EmployeeService,
              public saleService: SaleService,
              private router: Router,
              private route: ActivatedRoute,
              private dateAdapter: DateAdapter<Date>
  ) {
    this.dateAdapter.setLocale('de');
  }

  async ngOnInit() {
    await this.employeeService.getEmployees();
    this.routeId = parseInt(this.route.snapshot.paramMap.get('id'), 10);

    if (this.routeId) {
      this.saleHeader = await this.saleService.getSaleHeaderById(this.routeId);
      this.saleFormGroup.controls.employeeId.setValue(this.saleHeader.employee.id);
      this.saleFormGroup.controls.posting_date.setValue(new Date(this.saleHeader.posting_date));
    }
  }

  public async createSaleHeader() {
    const response = await this.saleService.postNewSaleHeader(this.createSaleHeaderBody());
    const locationSplit = response.headers.get('location').split('/');
    const id = locationSplit[locationSplit.length - 1];

    await this.router.navigate([`sales/${id}/lines`]);
  }

  public async updateSaleHeader() {
    await this.saleService.updateSaleHeader(this.routeId, this.createSaleHeaderBody());
    await this.router.navigate([`sales/${this.routeId}/lines`]);
  }

  public async deleteSaleHeader() {
    await this.saleService.deleteSaleHeader(this.routeId);
    await this.router.navigate([`sales`]);
  }

  public async onCancel() {
    await this.router.navigate([`dashboard`]);
  }

  private createSaleHeaderBody() {
    return {
      employee_id: this.saleFormGroup.controls.employeeId.value,
      posting_date: moment(this.saleFormGroup.controls.posting_date.value).add(1, 'day')
    };
  }
}
