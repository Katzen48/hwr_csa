package csa.database;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import csa.model.ItemLedgerEntry;
import csa.model.SourceDocType;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestItemLedgerEntry
{
	private static ItemLedgerEntry[] EXPECTED_ITEM_LEDGER_ENTRIES;
	
	private static DatabaseAdapter dbAdapter;
	private static ItemLedgerEntry generatedItemLedgerEntry;
	
	@BeforeClass
	public static void setupDatabase() throws ParseException
	{
		// Connect
		dbAdapter = new MySQLAdapter("rdbms.katzen48.de", "hwr_csa", "hwr_csa", "TrashCareTM");
		dbAdapter.connect();
		
		// Prepare Temporary Table
		dbAdapter.createEmptyTemporaryTable("item_ledger_entry", "entry_no");
		
		EXPECTED_ITEM_LEDGER_ENTRIES = new ItemLedgerEntry[] { 	new ItemLedgerEntry(1, 1, 1, "Jens 'Gerda'", "Jeans 'Gerda' Blau Gestreift S", 49.99F, 10, LocalDate.of(2020, 03, 30), SourceDocType.PURCHASE, 0),
			new ItemLedgerEntry(2, 1, 1, "Jens 'Gerda'", "Jeans 'Gerda' Blau Gestreift S", 79.99F, -1, LocalDate.of(2020, 03, 31), SourceDocType.SALE, 1),
			new ItemLedgerEntry(3, 1, 1, "Jens 'Gerda'", "Jeans 'Gerda' Blau Gestreift S", 79.99F, -1, LocalDate.of(2020, 03, 31), SourceDocType.SALE, 2)};
		
		// Insert Test Data
		for(ItemLedgerEntry entry : EXPECTED_ITEM_LEDGER_ENTRIES)
			dbAdapter.createItemLedgerEntry(entry);
	}
	
	@AfterClass
	public static void stripDownDatabase() throws IOException
	{
		dbAdapter.dropTemporaryTable("item_ledger_entry");
		dbAdapter.close();
	}
	
	@Test
	public void test1ListItemLedgerEntries()
	{
		List<ItemLedgerEntry> itemLedgerEntries = dbAdapter.listItemLedgerEntries();
		
		// Are 3 entries in database?
		assertEquals(EXPECTED_ITEM_LEDGER_ENTRIES.length, itemLedgerEntries.size());
		
		for(int i = 0 ; i < itemLedgerEntries.size() ; i++)
		{
			ItemLedgerEntry expectedItemLedgerEntry = EXPECTED_ITEM_LEDGER_ENTRIES[i];
			ItemLedgerEntry actualItemLedgerEntry = itemLedgerEntries.get(i);
			
			assertEquals(expectedItemLedgerEntry.getEntryNo(), actualItemLedgerEntry.getEntryNo());
			assertEquals(expectedItemLedgerEntry.getItemId(), actualItemLedgerEntry.getItemId());
			assertEquals(expectedItemLedgerEntry.getItemVariantId(), actualItemLedgerEntry.getItemVariantId());
			assertEquals(expectedItemLedgerEntry.getItemName(), actualItemLedgerEntry.getItemName());
			assertEquals(expectedItemLedgerEntry.getItemPrice(), actualItemLedgerEntry.getItemPrice(), 0);
			assertEquals(expectedItemLedgerEntry.getQuantity(), actualItemLedgerEntry.getQuantity());
			assertEquals(expectedItemLedgerEntry.getPostingDate(), actualItemLedgerEntry.getPostingDate());
			assertEquals(expectedItemLedgerEntry.getSourceDocType(), actualItemLedgerEntry.getSourceDocType());
			assertEquals(expectedItemLedgerEntry.getSourceDocNo(), actualItemLedgerEntry.getSourceDocNo());
		}
	}
	
	@Test
	public void test2CreateItemLedgerEntry() throws ParseException
	{
		generatedItemLedgerEntry = new ItemLedgerEntry(EXPECTED_ITEM_LEDGER_ENTRIES[EXPECTED_ITEM_LEDGER_ENTRIES.length - 1].getEntryNo() + 1, 1, 1, "Jens 'Gerda'", "Jeans 'Gerda' Blau Gestreift S", 79.99F, 10, LocalDate.of(2020, 04, 01), SourceDocType.PURCHASE, 2);
		
		assertTrue(dbAdapter.createItemLedgerEntry(generatedItemLedgerEntry));
	}
	
	@Test
	public void test3GetItemLedgerEntry()
	{
		ItemLedgerEntry savedItemLedgerEntry = dbAdapter.getItemLedgerEntry(generatedItemLedgerEntry.getEntryNo());
		
		assertEquals(generatedItemLedgerEntry.getEntryNo(), savedItemLedgerEntry.getEntryNo());
		assertEquals(generatedItemLedgerEntry.getItemId(), savedItemLedgerEntry.getItemId());
		assertEquals(generatedItemLedgerEntry.getItemVariantId(), savedItemLedgerEntry.getItemVariantId());
		assertEquals(generatedItemLedgerEntry.getItemName(), savedItemLedgerEntry.getItemName());
		assertEquals(generatedItemLedgerEntry.getItemPrice(), savedItemLedgerEntry.getItemPrice(), 0);
		assertEquals(generatedItemLedgerEntry.getQuantity(), savedItemLedgerEntry.getQuantity());
		assertEquals(generatedItemLedgerEntry.getPostingDate(), savedItemLedgerEntry.getPostingDate());
		assertEquals(generatedItemLedgerEntry.getSourceDocType(), savedItemLedgerEntry.getSourceDocType());
		assertEquals(generatedItemLedgerEntry.getSourceDocNo(), savedItemLedgerEntry.getSourceDocNo());
	}
}
