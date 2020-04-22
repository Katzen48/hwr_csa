package csa.service.contract;

import java.util.List;

import org.jvnet.hk2.annotations.Contract;

import csa.model.ValueLedgerEntry;

@Contract
public interface IValueLedgerEntryService
{
	List<ValueLedgerEntry> listValueLedgerEntries();
	ValueLedgerEntry getValueLedgerEntry(int id);
	ValueLedgerEntry createValueLedgerEntry(ValueLedgerEntry valueLedgerEntry);
}
