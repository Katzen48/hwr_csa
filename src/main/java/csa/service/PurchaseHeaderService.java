package csa.service;

import java.util.List;

import javax.inject.Inject;

import org.jdbi.v3.core.transaction.TransactionIsolationLevel;
import org.jvnet.hk2.annotations.Service;

import csa.database.DatabaseAdapter;
import csa.model.Item;
import csa.model.ItemLedgerEntry;
import csa.model.ItemVariant;
import csa.model.PurchaseHeader;
import csa.model.PurchaseLine;
import csa.model.SourceDocType;
import csa.model.ValueLedgerEntry;
import csa.service.contract.IPurchaseHeaderService;

@Service
public class PurchaseHeaderService implements IPurchaseHeaderService
{
	@Inject
	private DatabaseAdapter dbAdapter;
	
	@Override
	public List<PurchaseHeader> listPurchaseHeaders()
	{
		return dbAdapter.listPurchaseHeaders();
	}

	@Override
	public PurchaseHeader getPurchaseHeader(int id)
	{
		return dbAdapter.getPurchaseHeader(id);
	}

	@Override
	public PurchaseHeader createPurchaseHeader(PurchaseHeader purchaseHeader)
	{
		return dbAdapter.createPurchaseHeader(purchaseHeader);
	}

	@Override
	public boolean updatePurchaseHeader(PurchaseHeader purchaseHeader)
	{
		return dbAdapter.updatePurchaseHeader(purchaseHeader);
	}

	@Override
	public boolean deletePurchaseHeader(PurchaseHeader purchaseHeader)
	{
		return dbAdapter.deletePurchaseHeader(purchaseHeader);
	}

	@Override
	public boolean post(PurchaseHeader purchaseHeader) {
		List<PurchaseLine> purchaseLines = dbAdapter.listByPurchaseHeader(purchaseHeader);
		
		dbAdapter.beginTransaction();
		dbAdapter.setTransactionIsolation(TransactionIsolationLevel.READ_COMMITTED);
		
		try
		{		
			purchaseLines.stream().filter(line -> line.isDelivered()).forEach(line -> 
			{
				ItemVariant itemVariant = line.getItemVariant();
				Item item 				= itemVariant.getItem();
				
				ItemLedgerEntry itemLedgerEntry = new ItemLedgerEntry(item.getId(), itemVariant.getId(), item.getName(), itemVariant.getName(), 
													line.getPrice(), line.getQuantity(), purchaseHeader.getPostingDate(), 0, SourceDocType.PURCHASE, purchaseHeader.getId());
				
				dbAdapter.createItemLedgerEntry(itemLedgerEntry);
				
				ValueLedgerEntry valueLedgerEntry = new ValueLedgerEntry(line.getLineAmount(), purchaseHeader.getPostingDate(), SourceDocType.PURCHASE, purchaseHeader.getId());
				
				dbAdapter.createValueLedgerEntry(valueLedgerEntry);
				
				dbAdapter.deletePurchaseLine(line);
			});
			
			dbAdapter.commit();
			
			if(dbAdapter.listByPurchaseHeader(purchaseHeader).size() == 0)		
				dbAdapter.deletePurchaseHeader(purchaseHeader);				
			
		}
		catch(Exception e)
		{
			dbAdapter.rollback();
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
