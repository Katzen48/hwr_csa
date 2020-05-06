package csa.database;

import java.io.IOException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import csa.model.Item;
import csa.model.ItemVariant;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestItemVariant
{
	private static ItemVariant[] EXPECTED_ITEM_VARIANTS = { new ItemVariant(1, new Item(1, "Jeans 'Gerda'"), "Jeans 'Gerda' Blau Gestreift S", 79.99F, "S"), 
															new ItemVariant(2, new Item(2, "T-Shirt 'Gerda'"), "TShirt 'Summertime' Rot S", 14.99F, "S"),
															new ItemVariant(3, new Item(2, "T-Shirt 'Gerda'"), "TShirt 'Summertime' Rot M", 14.99F, "M"),
															new ItemVariant(4, new Item(2, "T-Shirt 'Gerda'"), "TShirt 'Summertime' Rot L", 14.99F, "L"),
															new ItemVariant(5, new Item(2, "T-Shirt 'Gerda'"), "TShirt 'Summertime' Rot XL", 14.99F, "XL") };
	
	private static DatabaseAdapter dbAdapter;
	private static ItemVariant generatedItemVariant;
	
	@BeforeClass
	public static void setupDatabase()
	{
		// Connect
		dbAdapter = new MySQLAdapter("rdbms.katzen48.de", "hwr_csa", "hwr_csa", "TrashCareTM");
		dbAdapter.connect();
		
		// Prepare Temporary Table
		dbAdapter.createEmptyTemporaryTable("item_variant", "id");
		
		// Insert Test Data
		for(int i = 0; i < EXPECTED_ITEM_VARIANTS.length; i++)
		{
			ItemVariant itemVariant = EXPECTED_ITEM_VARIANTS[i];
			dbAdapter.createItemVariant(itemVariant);
		}
	}
	
	@AfterClass
	public static void stripDownDatabase() throws IOException
	{
		dbAdapter.dropTemporaryTable("item_variant");
		dbAdapter.close();
	}
	
	@Test
	public void test1ListItems()
	{
		List<ItemVariant> itemVariants = dbAdapter.listItemVariants();
		
		// Are 2 entries in database?
		assertEquals(EXPECTED_ITEM_VARIANTS.length, itemVariants.size());
		
		for(int i = 0 ; i < itemVariants.size() ; i++)
		{
			ItemVariant expectedItemVariant = EXPECTED_ITEM_VARIANTS[i];
			ItemVariant actualItemVariant = itemVariants.get(i);
			
			assertEquals(expectedItemVariant.getId(), actualItemVariant.getId());
			assertEquals(expectedItemVariant.getName(), actualItemVariant.getName());
		}
	}
	
	@Test
	public void test2CreateItem()
	{
		generatedItemVariant = new ItemVariant(EXPECTED_ITEM_VARIANTS[EXPECTED_ITEM_VARIANTS.length - 1].getId() + 1, new Item(1, "Jeans 'Gerda'"), "Jeans 'Gerda' Blau Gestreift M", 79.99F, "M");
		
		assertNotNull(dbAdapter.createItemVariant(generatedItemVariant));
	}
	
	@Test
	public void test3GetItem()
	{
		ItemVariant savedItemVariant = dbAdapter.getItemVariant(generatedItemVariant.getId());
		
		assertEquals(generatedItemVariant.getItem().getId(), savedItemVariant.getItem().getId());
		assertEquals(generatedItemVariant.getItem().getName(), savedItemVariant.getItem().getName());
		
		assertEquals(generatedItemVariant.getName(), savedItemVariant.getName());
		assertEquals(generatedItemVariant.getPrice(), savedItemVariant.getPrice(), 0);
		assertEquals(generatedItemVariant.getSize(), savedItemVariant.getSize());
		
	}
	
	
	@Test
	public void test4UpdateItem()
	{
		ItemVariant savedItemVariant = dbAdapter.getItemVariant(generatedItemVariant.getId());
		
		savedItemVariant.setName("Jeans 'Gerda' Blau Gestreift L");
		savedItemVariant.setSize("L");
		
		assertTrue(dbAdapter.updateItemVariant(savedItemVariant));
		
		generatedItemVariant = savedItemVariant;
	}
	
	@Test
	public void test5DeleteItem()
	{
		assertTrue(dbAdapter.deleteItemVariant(generatedItemVariant));
	}
}
