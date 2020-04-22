package csa.service;

import java.util.List;

import javax.inject.Inject;

import org.jvnet.hk2.annotations.Service;

import csa.database.DatabaseAdapter;
import csa.model.Employee;
import csa.service.contract.IEmployeeService;

@Service
public class EmployeeService implements IEmployeeService
{
	@Inject
	private DatabaseAdapter dbAdapter;
	
	@Override
	public List<Employee> listEmployees()
	{
		return dbAdapter.listEmployees();
	}

	@Override
	public Employee getEmployee(int id)
	{
		return dbAdapter.getEmployee(id);
	}

	@Override
	public Employee createEmployee(Employee employee)
	{
		return dbAdapter.createEmployee(employee);
	}

	@Override
	public boolean updateEmployee(Employee employee)
	{
		return dbAdapter.updateEmployee(employee);
	}

	@Override
	public boolean deleteEmployee(Employee employee)
	{
		return dbAdapter.deleteEmployee(employee);
	}
}
