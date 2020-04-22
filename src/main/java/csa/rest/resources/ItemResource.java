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

import csa.model.Item;
import csa.rest.server.RestServer;
import csa.service.contract.IItemService;

@Path("item")
@Singleton
@Contract
public class ItemResource
{	
	@Inject
	private IItemService itemService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Item> getItems()
	{
		return itemService.listItems();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Item getItem(@PathParam("id") int itemId)
	{
		return itemService.getItem(itemId);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createItem(Item item)
	{
		try
		{
			Item result = itemService.createItem(item);
			
			if(result != null)
				return Response.created(URI.create(RestServer.BASE_URI + "item/" + result.getId())).build();
			
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
	public Response updateItem(Item item)
	{
		if(itemService.updateItem(item))
			return Response.noContent().build();
		
		return Response.notModified().build();
	}
	
	@DELETE
	@Path("{id}")
	public Response deleteItem(@PathParam("id") int id)
	{
		Item item = itemService.getItem(id);
		
		if(item == null)
			return Response.status(Status.NOT_FOUND).build();
		
		if(!itemService.deleteItem(item))
			return Response.notModified().build();
		
		return Response.noContent().build();
	}
}
