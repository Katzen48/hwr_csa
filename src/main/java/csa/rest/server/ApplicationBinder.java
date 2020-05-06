package csa.rest.server;

import javax.ws.rs.ext.Provider;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import csa.database.*;
import csa.service.*;
import csa.service.contract.*;

@Provider
public class ApplicationBinder extends AbstractBinder
{
	@Override
	protected void configure()
	{
		bind(MySQLAdapter.class).to(DatabaseAdapter.class);
		
		bind(EmployeeService.class).to(IEmployeeService.class);
		bind(ItemLedgerEntryService.class).to(IItemLedgerEntryService.class);
		bind(ItemService.class).to(IItemService.class);
		bind(ItemVariantService.class).to(IItemVariantService.class);
		bind(PurchaseHeaderService.class).to(IPurchaseHeaderService.class);
		bind(PurchaseLineService.class).to(IPurchaseLineService.class);
		bind(SalesHeaderService.class).to(ISalesHeaderService.class);
		bind(SalesLineService.class).to(ISalesLineService.class);
		bind(ValueLedgerEntryService.class).to(IValueLedgerEntryService.class);
		bind(VendorService.class).to(IVendorService.class);
	}
}
