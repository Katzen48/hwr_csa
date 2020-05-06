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
import csa.model.Vendor;
import csa.rest.server.RestServer;
import csa.service.contract.IPurchaseHeaderService;
import csa.service.contract.IVendorService;

@Path(PurchaseHeaderResource.PATH)
@Singleton
@Contract
public class PurchaseHeaderResource
{
	public static final String PATH = "purchaseheader";
	
	@Inject
	private IPurchaseHeaderService purchaseHeaderService;
	
	@Inject
	private IVendorService vendorService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<PurchaseHeader> getPurchaseHeaders()
	{
		return purchaseHeaderService.listPurchaseHeaders();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public PurchaseHeader getPurchaseHeader(@PathParam("id") int purchaseHeaderId)
	{
		return purchaseHeaderService.getPurchaseHeader(purchaseHeaderId);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createPurchaseHeader(PurchaseHeader purchaseHeader)
	{
		try
		{
			Vendor vendor = vendorService.getVendor(purchaseHeader.getVendor_id());
			
			if(vendor == null)
				return Response.status(Status.BAD_REQUEST).build();
			
			purchaseHeader.setVendor(vendor);
			
			PurchaseHeader result = purchaseHeaderService.createPurchaseHeader(purchaseHeader);
			
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
	public Response updatePurchaseHeader(@PathParam("id") int id, PurchaseHeader purchaseHeader)
	{
		Vendor vendor = vendorService.getVendor(purchaseHeader.getVendor_id());
		
		if(vendor == null)
			return Response.status(Status.BAD_REQUEST).build();
		
		purchaseHeader.setId(id);
		purchaseHeader.setVendor(vendor);
		
		if(purchaseHeaderService.updatePurchaseHeader(purchaseHeader))
			return Response.noContent().build();
		
		return Response.notModified().build();
	}
	
	@DELETE
	@Path("{id}")
	public Response deletePurchaseHeader(@PathParam("id") int id)
	{
		PurchaseHeader purchaseHeader = purchaseHeaderService.getPurchaseHeader(id);
		
		if(purchaseHeader == null)
			return Response.status(Status.NOT_FOUND).build();
		
		if(!purchaseHeaderService.deletePurchaseHeader(purchaseHeader))
			return Response.notModified().build();
		
		return Response.noContent().build();
	}
}
