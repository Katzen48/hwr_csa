import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../../services/employee.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {

  constructor(public employeeService: EmployeeService, private router: Router) {
  }

  async ngOnInit() {
    await this.employeeService.getEmployees();
  }

  public async onNewEmployee() {
    await this.router.navigate(['employees/employee-detail']);
  }

  public async onShowEmployee(id) {
    await this.router.navigate([`employees/employee-detail/${id}`]);
  }

}
