import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Employee } from '../models/employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  public employees: Employee[];

  constructor(private http: HttpClient) {
  }

  public async getEmployees() {
    this.employees = await this.http.get<Employee[]>(`${environment.backendUrl}/employee`).toPromise();
  }

  public async getEmployeeById(id) {
    return await this.http.get<Employee>(`${environment.backendUrl}/employee/${id}`).toPromise();
  }
}
