package csa.service.contract;

import java.util.List;

import org.jvnet.hk2.annotations.Contract;

import csa.model.ItemLedgerEntry;

@Contract
public interface IItemLedgerEntryService
{
	List<ItemLedgerEntry> listItemLedgerEntries();
	ItemLedgerEntry getItemLedgerEntry(int entryNo);
	ItemLedgerEntry createItemLedgerEntry(ItemLedgerEntry itemLedgerEntry);
	List<ItemLedgerEntry> getStock();
}
