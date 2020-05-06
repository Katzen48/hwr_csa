package csa.database;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import csa.model.Item;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestItem
{
	private static Item[] EXPECTED_ITEMS = { new Item("Jeans 'Gerda'"), new Item("T-Shirt 'Gerda'") };
	
	private static DatabaseAdapter dbAdapter;
	private static Item generatedItem;
	
	@BeforeClass
	public static void setupDatabase()
	{
		// Connect
		dbAdapter = new MySQLAdapter("rdbms.katzen48.de", "hwr_csa", "hwr_csa", "TrashCareTM");
		dbAdapter.connect();
		
		// Prepare Temporary Table
		dbAdapter.copyToTemporaryTable("item");
		List<Item> tempItems = dbAdapter.listItems();
		int highestId = tempItems.get(tempItems.size() - 1).getId();
		dbAdapter.listItems().forEach(item -> dbAdapter.deleteItem(item));
		
		Item[] items = Arrays.copyOf(EXPECTED_ITEMS, EXPECTED_ITEMS.length);
		
		// Insert Test Data
		for(int i = 0; i < EXPECTED_ITEMS.length; i++)
		{
			Item item = EXPECTED_ITEMS[i];
			dbAdapter.createItem(item);
			items[i] = new Item(highestId + i + 1, item.getName());
		}
		
		EXPECTED_ITEMS = items;
	}
	
	@AfterClass
	public static void stripDownDatabase() throws IOException
	{
		dbAdapter.dropTemporaryTable("item");
		dbAdapter.close();
	}
	
	@Test
	public void test1ListItems()
	{
		List<Item> items = dbAdapter.listItems();
		
		// Are 2 entries in database?
		assertEquals(EXPECTED_ITEMS.length, items.size());
		
		for(int i = 0 ; i < items.size() ; i++)
		{
			Item expectedItem = EXPECTED_ITEMS[i];
			Item actualItem = items.get(i);
			
			assertEquals(expectedItem.getId(), actualItem.getId());
			assertEquals(expectedItem.getName(), actualItem.getName());
		}
	}
	
	@Test
	public void test2CreateItem()
	{
		generatedItem = new Item(EXPECTED_ITEMS[EXPECTED_ITEMS.length - 1].getId() + 1, "Pocky");
		
		assertNotNull(dbAdapter.createItem(generatedItem));
	}
	
	@Test
	public void test3GetItem()
	{
		Item savedItem = dbAdapter.getItem(generatedItem.getId());
		
		assertEquals(generatedItem.getName(), savedItem.getName());
	}
	
	
	@Test
	public void test4UpdateItem()
	{
		Item savedItem = dbAdapter.getItem(generatedItem.getId());
		
		savedItem.setName("Mikado");
		
		assertTrue(dbAdapter.updateItem(savedItem));
		
		generatedItem = savedItem;
	}
	
	@Test
	public void test5DeleteItem()
	{
		assertTrue(dbAdapter.deleteItem(generatedItem));
	}
}
