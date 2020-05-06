package csa.service;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.jdbi.v3.core.transaction.TransactionIsolationLevel;
import org.jvnet.hk2.annotations.Service;

import csa.database.DatabaseAdapter;
import csa.model.Item;
import csa.model.ItemLedgerEntry;
import csa.model.ItemVariant;
import csa.model.SalesHeader;
import csa.model.SalesLine;
import csa.model.SourceDocType;
import csa.model.ValueLedgerEntry;
import csa.service.contract.ISalesHeaderService;

@Service
public class SalesHeaderService implements ISalesHeaderService
{
	@Inject
	private DatabaseAdapter dbAdapter;
	
	@Override
	public List<SalesHeader> listSalesHeaders()
	{
		return dbAdapter.listSalesHeaders();
	}

	@Override
	public SalesHeader getSalesHeader(int id)
	{
		return dbAdapter.getSalesHeader(id);
	}

	@Override
	public SalesHeader createSalesHeader(SalesHeader salesHeader)
	{
		return dbAdapter.createSalesHeader(salesHeader);
	}

	@Override
	public boolean updateSalesHeader(SalesHeader salesHeader)
	{
		return dbAdapter.updateSalesHeader(salesHeader);
	}

	@Override
	public boolean deleteSalesHeader(SalesHeader salesHeader)
	{
		return dbAdapter.deleteSalesHeader(salesHeader);
	}

	@Override
	public boolean post(SalesHeader salesHeader) {
		List<SalesLine> salesLines = dbAdapter.listBySalesHeader(salesHeader);
		
		dbAdapter.beginTransaction();
		
		try
		{
		
			for(SalesLine line : salesLines)
			{
				List<ItemLedgerEntry> entries = dbAdapter.getStockByItemLedgerEntries();
				Optional<ItemLedgerEntry> opt = entries.stream().filter(entry -> entry.getQuantity() >= line.getQuantity()).findFirst();
				
				if(!opt.isPresent())
					throw new Exception();
				
				ItemVariant itemVariant = line.getItemVariant();
				Item 		item 		= itemVariant.getItem();
				
				ItemLedgerEntry itemLedgerEntry = new ItemLedgerEntry(item.getId(), itemVariant.getId(), item.getName(), itemVariant.getName(), line.getItemPrice(), 
																		-line.getQuantity(), salesHeader.getPostingDate(), opt.get().getEntryNo(), SourceDocType.SALE, salesHeader.getId());
				
				dbAdapter.createItemLedgerEntry(itemLedgerEntry);
				
				ValueLedgerEntry valueLedgerEntry = new ValueLedgerEntry(-line.getLineAmount(), salesHeader.getPostingDate(), SourceDocType.SALE, salesHeader.getId());
				
				dbAdapter.createValueLedgerEntry(valueLedgerEntry);
			}
			
			dbAdapter.deleteSalesHeader(salesHeader);
			dbAdapter.commit();
		} catch(Exception e)
		{
			dbAdapter.rollback();
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
