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

import csa.model.SalesHeader;
import csa.model.SalesLine;
import csa.rest.server.RestServer;
import csa.service.contract.ISalesHeaderService;
import csa.service.contract.ISalesLineService;

@Path("salesheader/{salesheader}/line")
@Singleton
@Contract
public class SalesLineResource
{
	@Inject
	private ISalesHeaderService salesHeaderService;
	
	@Inject
	private ISalesLineService salesLineService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSalesLines(@PathParam("salesheader") int salesHeaderId)
	{
		SalesHeader header = salesHeaderService.getSalesHeader(salesHeaderId);
		
		if(header == null)
			return Response.status(Status.NOT_FOUND).build();
		
		List<SalesLine> lines = salesLineService.listBySalesHeader(header);
		
		if(lines.isEmpty())
			return Response.status(Status.NOT_FOUND).build();
		
		return Response.ok(lines, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSalesLine(@PathParam("salesheader") int salesHeaderId, @PathParam("id") int salesLineId)
	{
		SalesHeader salesHeader = salesHeaderService.getSalesHeader(salesHeaderId);
		
		if(salesHeader == null)
			return Response.status(Status.NOT_FOUND).build();
				
		SalesLine line = salesLineService.getSalesLine(salesLineId);
		
		if(line == null || line.getSalesHeaderId() != salesHeaderId)
			return Response.status(Status.NOT_FOUND).build();
		
		return Response.ok(line, MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createSalesHeader(@PathParam("salesheader") int salesHeaderId, SalesLine salesLine)
	{
		try
		{
			SalesHeader salesHeader = salesHeaderService.getSalesHeader(salesHeaderId);
			
			if(salesHeader == null)
				return Response.status(Status.NOT_FOUND).build();
			
			SalesLine result = salesLineService.createSalesLine(salesLine);
			
			if(result != null)
				return Response.created(URI.create(RestServer.BASE_URI + "salesheader/" + salesHeader.getId() + "/line/" + result.getId())).build();
			
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
	public Response updateSalesLine(@PathParam("salesheader") int salesHeaderId, SalesLine salesLine)
	{
		SalesHeader salesHeader = salesHeaderService.getSalesHeader(salesHeaderId);
		
		if(salesHeader == null)
			return Response.status(Status.NOT_FOUND).build();
		
		if(salesLineService.updateSalesLine(salesLine))
			return Response.noContent().build();
		
		return Response.notModified().build();
	}
	
	@DELETE
	@Path("{id}")
	public Response deleteSalesLine(@PathParam("salesheader") int salesHeaderId, @PathParam("id") int id)
	{
		SalesHeader salesHeader = salesHeaderService.getSalesHeader(salesHeaderId);
		
		if(salesHeader == null)
			return Response.status(Status.NOT_FOUND).build();
		
		SalesLine line = salesLineService.getSalesLine(id);
		
		if(line == null || line.getSalesHeaderId() != salesHeaderId)
			return Response.status(Status.NOT_FOUND).build();
		
		if(!salesLineService.deleteSalesLine(line))
			return Response.notModified().build();
		
		return Response.noContent().build();
	}
}
