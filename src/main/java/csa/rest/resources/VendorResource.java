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

import csa.model.Vendor;
import csa.rest.server.RestServer;
import csa.service.contract.IVendorService;

@Path(VendorResource.PATH)
@Singleton
@Contract
public class VendorResource
{
	public static final String PATH = "vendor";
	
	@Inject
	private IVendorService vendorService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Vendor> getVendors()
	{
		return vendorService.listVendors();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Vendor getVendor(@PathParam("id") int vendorId)
	{
		return vendorService.getVendor(vendorId);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createVendor(Vendor vendor)
	{
		try
		{
			Vendor result = vendorService.createVendor(vendor);
			
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
	public Response updateVendor(@PathParam("id") int id, Vendor vendor)
	{
		vendor.setId(id);
		
		if(vendorService.updateVendor(vendor))
			return Response.noContent().build();
		
		return Response.notModified().build();
	}
	
	@DELETE
	@Path("{id}")
	public Response deleteVendor(@PathParam("id") int id)
	{
		Vendor vendor = vendorService.getVendor(id);
		
		if(vendor == null)
			return Response.status(Status.NOT_FOUND).build();
		
		if(!vendorService.deleteVendor(vendor))
			return Response.notModified().build();
		
		return Response.noContent().build();
	}
}
