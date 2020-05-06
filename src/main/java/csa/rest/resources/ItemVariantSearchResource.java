package csa.rest.resources;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.spi.Contract;

import csa.model.ItemVariant;
import csa.rest.util.Query;
import csa.service.contract.IItemVariantService;

@Path("itemvariants/search")
@Singleton
@Contract
public class ItemVariantSearchResource
{
	@Inject
	private IItemVariantService itemVariantService;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public List<ItemVariant> searchItemVariants(Query query)
	{
		return itemVariantService.searchByName(query.getQuery());
	}
}
