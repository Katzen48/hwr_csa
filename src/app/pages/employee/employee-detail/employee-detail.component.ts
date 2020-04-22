import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
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
    givenName: new FormControl(),
    surname: new FormControl()
  });

  constructor(private route: ActivatedRoute, public employeeService: EmployeeService) {
  }

  ngOnInit(): void {
    this.routeId = parseInt(this.route.snapshot.paramMap.get('id'), 10);
    if (this.routeId) {
      this.employee = this.employeeService.getEmployeeById(this.routeId);
      this.employeeFormGroup.controls.givenName.setValue(this.employee.givenName);
      this.employeeFormGroup.controls.surname.setValue(this.employee.surname);
    }
  }

}
