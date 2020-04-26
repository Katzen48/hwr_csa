import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormControl, FormGroup } from '@angular/forms';
import { EmployeeService } from '../../../services/employee.service';

@Component({
  selector: 'app-employee-detail',
  templateUrl: './employee-detail.component.html',
  styleUrls: ['./employee-detail.component.css']
})
export class EmployeeDetailComponent implements OnInit {
  public routeId: number;
  public employee;

  public employeeFormGroup: FormGroup = new FormGroup({
    given_name: new FormControl(),
    surname: new FormControl()
  });

  constructor(private route: ActivatedRoute,
              public employeeService: EmployeeService,
              private router: Router
  ) {
  }

  async ngOnInit() {
    this.routeId = parseInt(this.route.snapshot.paramMap.get('id'), 10);
    if (this.routeId) {
      this.employee = await this.employeeService.getEmployeeById(this.routeId);
      this.employeeFormGroup.controls.given_name.setValue(this.employee.given_name);
      this.employeeFormGroup.controls.surname.setValue(this.employee.surname);
    }
  }

  public async postNewEmployee() {
    const employee = {
      given_name: this.employeeFormGroup.controls.given_name.value,
      surname: this.employeeFormGroup.controls.surname.value
    };

    await this.employeeService.postNewEmployee(employee);
    await this.router.navigate([`employees`]);
  }

  public async updateNewEmployee() {
    const employee = {
      given_name: this.employeeFormGroup.controls.given_name.value,
      surname: this.employeeFormGroup.controls.surname.value
    };

    await this.employeeService.updateEmployee(this.routeId, employee);
    await this.router.navigate([`employees`]);
  }

  public async onBack() {
    await this.router.navigate([`employees`]);
  }

  public async deleteEmployee() {
    await this.employeeService.deleteEmployee(this.routeId);
    await this.router.navigate([`employees`]);
  }
}
