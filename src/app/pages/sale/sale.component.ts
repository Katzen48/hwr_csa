import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { EmployeeService } from '../../services/employee.service';
import { DateAdapter } from '@angular/material/core';
import { SaleService } from '../../services/sale.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-sale',
  templateUrl: './sale.component.html',
  styleUrls: ['./sale.component.css']
})
export class SaleComponent implements OnInit {
  public routeId;
  public saleHeader;

  public saleFormGroup: FormGroup = new FormGroup({
    employee: new FormControl(),
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
      this.saleFormGroup.controls.employee.setValue(this.saleHeader.employee);
      this.saleFormGroup.controls.posting_date.setValue(new Date(this.saleHeader.posting_date));
    }
  }

  public async createSalesHeader() {
    const salesHeader = {
      employee_id: this.saleFormGroup.controls.employee.value.id,
      posting_date: this.saleFormGroup.controls.posting_date.value
    };

    const response = await this.saleService.postNewSaleHeader(salesHeader);
    const locationSplit = response.headers.get('location').split('/');
    const id = locationSplit[locationSplit.length - 1];

    await this.router.navigate([`sales/${id}/lines`]);
  }
}
