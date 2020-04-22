import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  public employees;

  constructor() {
  }

  public getEmployees() {
    this.employees = [{id: 1, givenName: 'Sandra', surname: 'Richard'}];
  }

  public getEmployeeById(id) {
    return {id: 1, givenName: 'Sandra', surname: 'Richard'};
  }
}
