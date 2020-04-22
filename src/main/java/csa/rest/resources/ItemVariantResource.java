package csa.rest.resources;

import java.net.URI;

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

import csa.model.Item;
import csa.model.ItemVariant;
import csa.rest.server.RestServer;
import csa.service.contract.IItemService;
import csa.service.contract.IItemVariantService;

@Path("item/{item}/variant")
@Singleton
@Contract
public class ItemVariantResource
{
	@Inject
	private IItemService itemService;
	
	@Inject
	private IItemVariantService itemVariantService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getItems(@PathParam("item") int itemId)
	{
		Item item = itemService.getItem(itemId);
		
		if(item == null)
			return Response.status(Status.NOT_FOUND).build();
		
		return Response.ok(itemVariantService.listByItem(item), MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getItem(@PathParam("item") int itemId, @PathParam("id") int id)
	{
		Item item = itemService.getItem(itemId);
		
		if(item == null)
			return Response.status(Status.NOT_FOUND).build();
		
		return Response.ok(itemVariantService.getItemVariant(id), MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createItem(@PathParam("item") int itemId, ItemVariant itemVariant)
	{
		try
		{
			Item item = itemService.getItem(itemId);
			
			if(item == null)
				return Response.status(Status.NOT_FOUND).build();
			
			itemVariant.setItem(item);
			
			ItemVariant result = itemVariantService.createItemVariant(itemVariant);
			
			if(result != null)
				return Response.created(URI.create(RestServer.BASE_URI + "item/" + item.getId() + "/variant/" + result.getId())).build();
			
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
	public Response updateItem(@PathParam("item") int itemId, ItemVariant itemVariant)
	{
		Item item = itemService.getItem(itemId);
		
		if(item == null)
			return Response.status(Status.NOT_FOUND).build();
		
		if(itemVariantService.updateItemVariant(itemVariant))
			return Response.noContent().build();
		
		return Response.notModified().build();
	}
	
	@DELETE
	@Path("{id}")
	public Response deleteItem(@PathParam("item") int itemId, @PathParam("id") int id)
	{
		Item item = itemService.getItem(itemId);
		
		if(item == null)
			return Response.status(Status.NOT_FOUND).build();
		
		ItemVariant itemVariant = itemVariantService.getItemVariant(id);
		
		if(itemVariant == null)
			return Response.status(Status.NOT_FOUND).build();
		
		if(!itemVariantService.deleteItemVariant(itemVariant))
			return Response.notModified().build();
		
		return Response.noContent().build();
	}
}
