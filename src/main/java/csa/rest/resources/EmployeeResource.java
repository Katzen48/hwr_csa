package csa.rest.resources;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.spi.Contract;

import csa.model.Employee;
import csa.rest.server.RestServer;
import csa.service.contract.IEmployeeService;

@Path(EmployeeResource.PATH)
@Singleton
@Contract
public class EmployeeResource
{
	public static final String PATH = "employee";
	
	@Inject
	private IEmployeeService employeeService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Employee> getEmployees()
	{
		return employeeService.listEmployees();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Employee getEmployee(@PathParam("id") int employeeId)
	{
		return employeeService.getEmployee(employeeId);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createEmployee(Employee employee)
	{
		try
		{
			Employee result = employeeService.createEmployee(employee);
			
			if(result != null)
				return Response.created(URI.create(RestServer.BASE_URI + PATH + "/" + result.getId())).build();
			
			return Response.status(Status.BAD_REQUEST).build();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			return Response.serverError().build();
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateEmployee(Employee employee)
	{
		if(employeeService.updateEmployee(employee))
			return Response.noContent().build();
		
		return Response.notModified().build();
	}
	
	@DELETE
	@Path("{id}")
	public Response deleteEmployee(@PathParam("id") int id)
	{
		Employee employee = employeeService.getEmployee(id);
		
		if(employee == null)
			return Response.status(Status.NOT_FOUND).build();
		
		if(!employeeService.deleteEmployee(employee))
			return Response.notModified().build();
		
		return Response.noContent().build();
	}
}
