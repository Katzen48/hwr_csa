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
import csa.model.SalesHeader;
import csa.rest.server.RestServer;
import csa.service.contract.IEmployeeService;
import csa.service.contract.ISalesHeaderService;

@Path(SalesHeaderResource.PATH)
@Singleton
@Contract
public class SalesHeaderResource
{
	public static final String PATH = "salesheader";
	
	@Inject
	private ISalesHeaderService salesHeaderService;
	
	@Inject
	private IEmployeeService employeeService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<SalesHeader> getSalesHeaders()
	{
		return salesHeaderService.listSalesHeaders();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public SalesHeader getSalesHeader(@PathParam("id") int salesHeaderId)
	{
		return salesHeaderService.getSalesHeader(salesHeaderId);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createSalesHeader(SalesHeader salesHeader)
	{
		try
		{
			Employee employee = employeeService.getEmployee(salesHeader.getEmployee_id());
			
			if(employee == null)
				return Response.status(Status.BAD_REQUEST).build();
			
			salesHeader.setEmployee(employee);
			
			SalesHeader result = salesHeaderService.createSalesHeader(salesHeader);
			
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
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateSalesHeader(@PathParam("id") int id, SalesHeader salesHeader)
	{
		Employee employee = employeeService.getEmployee(salesHeader.getEmployee_id());
		
		if(employee == null)
			return Response.status(Status.BAD_REQUEST).build();
		
		salesHeader.setEmployee(employee);
		salesHeader.setId(id);
		
		if(salesHeaderService.updateSalesHeader(salesHeader))
			return Response.noContent().build();
		
		return Response.notModified().build();
	}
	
	@DELETE
	@Path("{id}")
	public Response deleteSalesHeader(@PathParam("id") int id)
	{
		SalesHeader salesHeader = salesHeaderService.getSalesHeader(id);
		
		if(salesHeader == null)
			return Response.status(Status.NOT_FOUND).build();
		
		salesHeader.setId(id);
		
		if(!salesHeaderService.deleteSalesHeader(salesHeader))
			return Response.notModified().build();
		
		return Response.noContent().build();
	}
	
	@POST
	@Path("{id}/post")
	public Response postSalesHeader(@PathParam("id") int id)
	{
		SalesHeader salesHeader = salesHeaderService.getSalesHeader(id);
		
		if(salesHeader == null)
			return Response.status(Status.NOT_FOUND).build();
		
		if(salesHeaderService.post(salesHeader))
			return Response.noContent().build();
		
		return Response.notModified().build();
	}
}
