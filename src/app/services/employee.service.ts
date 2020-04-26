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

  public async postNewEmployee(employee) {
    await this.http.post(`${environment.backendUrl}/employee`, employee).toPromise();
  }

  public async updateEmployee(id, employee) {
    await this.http.put(`${environment.backendUrl}/employee/${id}`, employee).toPromise();
  }

  public async deleteEmployee(id) {
    await this.http.delete(`${environment.backendUrl}/employee/${id}`).toPromise();
  }
}
