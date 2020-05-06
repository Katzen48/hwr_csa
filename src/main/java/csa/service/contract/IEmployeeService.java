package csa.service.contract;

import java.util.List;

import org.jvnet.hk2.annotations.Contract;

import csa.model.Employee;

@Contract
public interface IEmployeeService
{
	List<Employee> listEmployees();
	Employee getEmployee(int id);
	Employee createEmployee(Employee employee);
	boolean updateEmployee(Employee employee);
	boolean deleteEmployee(Employee employee);
}
