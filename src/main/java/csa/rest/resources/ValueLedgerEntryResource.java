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

import csa.model.ValueLedgerEntry;
import csa.service.contract.IValueLedgerEntryService;

@Path(ValueLedgerEntryResource.PATH)
@Singleton
@Contract
public class ValueLedgerEntryResource
{
	public static final String PATH = "valueledgerentry";
	
	@Inject
	private IValueLedgerEntryService valueLedgerEntryService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ValueLedgerEntry> getValueLedgerEntries()
	{
		return valueLedgerEntryService.listValueLedgerEntries();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ValueLedgerEntry getValueLedgerEntry(@PathParam("id") int entryNo)
	{
		return valueLedgerEntryService.getValueLedgerEntry(entryNo);
	}
}
