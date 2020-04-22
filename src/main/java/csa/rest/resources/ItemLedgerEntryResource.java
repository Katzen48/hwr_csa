package csa.rest.resources;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.spi.Contract;

import csa.model.ItemLedgerEntry;
import csa.service.contract.IItemLedgerEntryService;

@Path(ItemLedgerEntryResource.PATH)
@Singleton
@Contract
public class ItemLedgerEntryResource
{
	public static final String PATH = "itemledgerentry";
	
	@Inject
	private IItemLedgerEntryService itemLedgerEntryService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ItemLedgerEntry> getItemLedgerEntries()
	{
		return itemLedgerEntryService.listItemLedgerEntries();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ItemLedgerEntry getItemLedgerEntry(@PathParam("id") int entryNo)
	{
		return itemLedgerEntryService.getItemLedgerEntry(entryNo);
	}
}
