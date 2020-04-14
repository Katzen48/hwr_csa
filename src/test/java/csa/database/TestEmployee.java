package csa.database;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import csa.model.Employee;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestEmployee
{
	private static Employee[] EXPECTED_EMPLOYEES = { new Employee("Sandra", "Richard"), new Employee("Eduard", "Müller") };
	
	private static DatabaseAdapter dbAdapter;
	private static Employee generatedEmployee;
	
	@BeforeClass
	public static void setupDatabase()
	{
		// Connect
		dbAdapter = new MySQLAdapter("rdbms.katzen48.de", "hwr_csa", "hwr_csa", "TrashCareTM");
		dbAdapter.connect();
		
		// Prepare Temporary Table
		dbAdapter.copyToTemporaryTable("employee");
		List<Employee> tempEmployees = dbAdapter.listEmployees();
		int highestId = tempEmployees.get(tempEmployees.size() - 1).getId();
		dbAdapter.listEmployees().forEach(employee -> dbAdapter.deleteEmployee(employee));
		
		Employee[] employees = Arrays.copyOf(EXPECTED_EMPLOYEES, EXPECTED_EMPLOYEES.length);
		
		// Insert Test Data
		for(int i = 0; i < EXPECTED_EMPLOYEES.length; i++)
		{
			Employee employee = EXPECTED_EMPLOYEES[i];
			dbAdapter.createEmployee(employee);
			employees[i] = new Employee(highestId + i + 1, employee.getGivenName(), employee.getSurname());
		}
		
		EXPECTED_EMPLOYEES = employees;
	}
	
	@AfterClass
	public static void stripDownDatabase() throws IOException
	{
		dbAdapter.dropTemporaryTable("employee");
		dbAdapter.close();
	}
	
	@Test
	public void test1ListEmployees()
	{
		List<Employee> employees = dbAdapter.listEmployees();
		
		// Are 2 entries in database?
		assertEquals(EXPECTED_EMPLOYEES.length, employees.size());
		
		for(int i = 0 ; i < employees.size() ; i++)
		{
			Employee expectedEmployee = EXPECTED_EMPLOYEES[i];
			Employee actualEmployee = employees.get(i);
			
			assertEquals(expectedEmployee.getId(), actualEmployee.getId());
			assertEquals(expectedEmployee.getGivenName(), actualEmployee.getGivenName());
			assertEquals(expectedEmployee.getSurname(), actualEmployee.getSurname());
		}
	}
	
	@Test
	public void test2CreateEmployee()
	{
		generatedEmployee = new Employee(EXPECTED_EMPLOYEES[EXPECTED_EMPLOYEES.length - 1].getId() + 1, "Michael", "Bernhard");
		
		assertTrue(dbAdapter.createEmployee(generatedEmployee));
	}
	
	@Test
	public void test3GetEmployee()
	{
		Employee savedEmployee = dbAdapter.getEmployee(generatedEmployee.getId());
		
		assertEquals(generatedEmployee.getGivenName(), savedEmployee.getGivenName());
		assertEquals(generatedEmployee.getSurname(), savedEmployee.getSurname());
	}
	
	
	@Test
	public void test4UpdateEmployee()
	{
		Employee savedEmployee = dbAdapter.getEmployee(generatedEmployee.getId());
		
		savedEmployee.setGivenName("Steffen");
		savedEmployee.setSurname("Krause");
		
		assertTrue(dbAdapter.updateEmployee(savedEmployee));
		
		generatedEmployee = savedEmployee;
	}
	
	@Test
	public void test5DeleteEmployee()
	{
		assertTrue(dbAdapter.deleteEmployee(generatedEmployee));
	}
}
