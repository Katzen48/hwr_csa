package csa.service;

import java.util.List;

import javax.inject.Inject;

import org.jvnet.hk2.annotations.Service;

import csa.database.DatabaseAdapter;
import csa.model.ItemLedgerEntry;
import csa.service.contract.IItemLedgerEntryService;

@Service
public class ItemLedgerEntryService implements IItemLedgerEntryService
{
	@Inject
	private DatabaseAdapter dbAdapter;
	
	@Override
	public List<ItemLedgerEntry> listItemLedgerEntries()
	{
		return dbAdapter.listItemLedgerEntries();
	}

	@Override
	public ItemLedgerEntry getItemLedgerEntry(int entryNo)
	{
		return dbAdapter.getItemLedgerEntry(entryNo);
	}

	@Override
	public ItemLedgerEntry createItemLedgerEntry(ItemLedgerEntry itemLedgerEntry)
	{
		return dbAdapter.createItemLedgerEntry(itemLedgerEntry);
	}
}
