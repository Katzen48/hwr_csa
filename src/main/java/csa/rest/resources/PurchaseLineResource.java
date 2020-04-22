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

import csa.model.PurchaseHeader;
import csa.model.PurchaseLine;
import csa.rest.server.RestServer;
import csa.service.contract.IPurchaseHeaderService;
import csa.service.contract.IPurchaseLineService;

@Path("purchaseheader/{purchaseheader}/line")
@Singleton
@Contract
public class PurchaseLineResource
{
	@Inject
	private IPurchaseHeaderService purchaseHeaderService;
	
	@Inject
	private IPurchaseLineService purchaseLineService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPurchaseLines(@PathParam("purchaseheader") int purchaseHeaderId)
	{
		PurchaseHeader header = purchaseHeaderService.getPurchaseHeader(purchaseHeaderId);
		
		if(header == null)
			return Response.status(Status.NOT_FOUND).build();
		
		List<PurchaseLine> lines = purchaseLineService.listByPurchaseHeader(header);
		
		if(lines.isEmpty())
			return Response.status(Status.NOT_FOUND).build();
		
		return Response.ok(lines, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPurchaseLine(@PathParam("purchaseheader") int purchaseHeaderId, @PathParam("id") int purchaseLineId)
	{
		PurchaseHeader purchaseHeader = purchaseHeaderService.getPurchaseHeader(purchaseHeaderId);
		
		if(purchaseHeader == null)
			return Response.status(Status.NOT_FOUND).build();
				
		PurchaseLine line = purchaseLineService.getPurchaseLine(purchaseLineId);
		
		if(line == null || line.getPurchaseHeaderId() != purchaseHeaderId)
			return Response.status(Status.NOT_FOUND).build();
		
		return Response.ok(line, MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createPurchaseHeader(@PathParam("purchaseheader") int purchaseHeaderId, PurchaseLine purchaseLine)
	{
		try
		{
			PurchaseHeader purchaseHeader = purchaseHeaderService.getPurchaseHeader(purchaseHeaderId);
			
			if(purchaseHeader == null)
				return Response.status(Status.NOT_FOUND).build();
			
			PurchaseLine result = purchaseLineService.createPurchaseLine(purchaseLine);
			
			if(result != null)
				return Response.created(URI.create(RestServer.BASE_URI + "purchaseheader/" + purchaseHeader.getId() + "/line/" + result.getId())).build();
			
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
	public Response updatePurchaseLine(@PathParam("purchaseheader") int purchaseHeaderId, PurchaseLine purchaseLine)
	{
		PurchaseHeader purchaseHeader = purchaseHeaderService.getPurchaseHeader(purchaseHeaderId);
		
		if(purchaseHeader == null)
			return Response.status(Status.NOT_FOUND).build();
		
		if(purchaseLineService.updatePurchaseLine(purchaseLine))
			return Response.noContent().build();
		
		return Response.notModified().build();
	}
	
	@DELETE
	@Path("{id}")
	public Response deletePurchaseLine(@PathParam("purchaseheader") int purchaseHeaderId, @PathParam("id") int id)
	{
		PurchaseHeader purchaseHeader = purchaseHeaderService.getPurchaseHeader(purchaseHeaderId);
		
		if(purchaseHeader == null)
			return Response.status(Status.NOT_FOUND).build();
		
		PurchaseLine line = purchaseLineService.getPurchaseLine(id);
		
		if(line == null || line.getPurchaseHeaderId() != purchaseHeaderId)
			return Response.status(Status.NOT_FOUND).build();
		
		if(!purchaseLineService.deletePurchaseLine(line))
			return Response.notModified().build();
		
		return Response.noContent().build();
	}
}
