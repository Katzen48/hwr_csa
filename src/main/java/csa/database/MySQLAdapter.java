package csa.database;

import java.io.IOException;
import java.util.List;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.reflect.ConstructorMapper;
import org.jdbi.v3.core.transaction.TransactionIsolationLevel;

import csa.model.Employee;
import csa.model.Item;
import csa.model.ItemLedgerEntry;
import csa.model.ItemVariant;
import csa.model.PurchaseHeader;
import csa.model.PurchaseLine;
import csa.model.SalesHeader;
import csa.model.SalesLine;
import csa.model.ValueLedgerEntry;
import csa.model.Vendor;

public class MySQLAdapter implements DatabaseAdapter
{
	private final Jdbi jdbi;
	private Handle handle;
	
	public MySQLAdapter(String host, String database, String username, String password)
	{
		jdbi = Jdbi.create(String.format("jdbc:mariadb://%s/%s?autoReconnect=true", host, database), username, password);
	}
	
	@Override
	public void connect()
	{
		handle = jdbi.open();
		
		registerMappers();
	}
	
	private void registerMappers()
	{
		handle.registerRowMapper(ConstructorMapper.factory(Employee.class));
		handle.registerRowMapper(ConstructorMapper.factory(Item.class));
		handle.registerRowMapper(ConstructorMapper.factory(ItemLedgerEntry.class));
		handle.registerRowMapper(ConstructorMapper.factory(ItemVariant.class));
		handle.registerRowMapper(ConstructorMapper.factory(PurchaseHeader.class));
		handle.registerRowMapper(ConstructorMapper.factory(PurchaseLine.class));
		handle.registerRowMapper(ConstructorMapper.factory(SalesHeader.class));
		handle.registerRowMapper(ConstructorMapper.factory(SalesLine.class));
		handle.registerRowMapper(ConstructorMapper.factory(ValueLedgerEntry.class));
		handle.registerRowMapper(ConstructorMapper.factory(Vendor.class));
	}

	@Override
	public List<Employee> listEmployees()
	{
		return handle.createQuery("SELECT * FROM employee")
				.mapTo(Employee.class)
				.list();
	}

	@Override
	public Employee getEmployee(int id)
	{
		return handle.createQuery("SELECT * FROM employee WHERE `id` = :id")
				.bind("id", id)
				.mapTo(Employee.class)
				.one();
	}

	@Override
	public boolean createEmployee(Employee employee)
	{
		return handle.createUpdate("INSERT INTO employee (`given_name`, `surname`) VALUES (:getGivenName, :getSurname)")
				.bindMethods(employee)
				.execute() == 1;
	}

	@Override
	public boolean updateEmployee(Employee employee)
	{
		return handle.createUpdate("UPDATE employee SET `given_name` = :getGivenName, `surname` = :getSurname WHERE `id` = :getId")
				.bindMethods(employee)
				.execute() == 1;
	}

	@Override
	public boolean deleteEmployee(Employee employee)
	{
		return handle.createUpdate("DELETE FROM employee WHERE `id` = :getId")
				.bindMethods(employee)
				.execute() == 1;
	}

	@Override
	public List<Item> listItems()
	{
		return handle.createQuery("SELECT * FROM item")
				.mapTo(Item.class)
				.list();
	}

	@Override
	public Item getItem(int id)
	{
		return handle.createQuery("SELECT * FROM item WHERE `id` = :id")
				.bind("id", id)
				.mapTo(Item.class)
				.one();
	}

	@Override
	public boolean createItem(Item item)
	{
		return handle.createUpdate("INSERT INTO item (`name`) VALUES (:getName)")
				.bindMethods(item)
				.execute() == 1;
	}

	@Override
	public boolean updateItem(Item item)
	{
		return handle.createUpdate("UPDATE item SET `name` = :getName WHERE `id` = :getId")
				.bindMethods(item)
				.execute() == 1;
	}

	@Override
	public boolean deleteItem(Item item)
	{
		return handle.createUpdate("DELETE FROM item WHERE `id` = :getId")
				.bindMethods(item)
				.execute() == 1;
	}

	@Override
	public List<ItemLedgerEntry> listItemLedgerEntries()
	{
		return handle.createQuery("SELECT * FROM item_ledger_entry")
				.mapTo(ItemLedgerEntry.class)
				.list();
	}

	@Override
	public ItemLedgerEntry getItemLedgerEntry(int entryNo)
	{
		return handle.createQuery("SELECT * FROM item_ledger_entry WHERE `entry_no` = :entryNo")
				.bind("entryNo", entryNo)
				.mapTo(ItemLedgerEntry.class)
				.one();
	}

	@Override
	public boolean createItemLedgerEntry(ItemLedgerEntry itemLedgerEntry)
	{
		return handle.createUpdate(	"INSERT INTO item_ledger_entry (`item_id`, `item_variant_id`, `item_name`, `item_variant_name`, `item_price`, `quantity`, `posting_date`, "
								+ 	"`source_doc_type`, `source_doc_no`) VALUES (:getItemId, :getItemVariantId, :getItemName, :getItemVariantName, :getItemPrice, :getQuantity, "
								+ 	":getPostingDate, :getSourceDocType, :getSourceDocNo)")
				.bindMethods(itemLedgerEntry)
				.execute() == 1;
	}

	@Override
	public List<ItemVariant> listItemVariants()
	{
		return handle.createQuery("SELECT * FROM item_variant")
				.mapTo(ItemVariant.class)
				.list();
	}

	@Override
	public List<ItemVariant> listByItem(Item item)
	{
		return handle.createQuery("SELECT * FROM item_variant WHERE `item_id` = :getId")
				.bindMethods(item)
				.mapTo(ItemVariant.class)
				.list();
	}

	@Override
	public ItemVariant getItemVariant(int id)
	{
		return handle.createQuery("SELECT * FROM item_variant WHERE `id` = :id")
				.bind("id", id)
				.mapTo(ItemVariant.class)
				.one();
	}

	@Override
	public boolean createItemVariant(ItemVariant itemVariant)
	{
		return handle.createUpdate("INSERT INTO item_variant (`item_id`, `name`, `price`, `size`) VALUES (:getItemId, :getName, :getPrice, :getSize)")
				.bindMethods(itemVariant)
				.execute() == 1;
	}

	@Override
	public boolean updateItemVariant(ItemVariant itemVariant)
	{
		return handle.createUpdate("UPDATE item_variant SET `item_id` = :getItemId, `name` = :getName, `price` = :getPrice, `size` = :getSize WHERE `id` = :getId")
				.bindMethods(itemVariant)
				.execute() == 1;
	}

	@Override
	public boolean deleteItemVariant(ItemVariant itemVariant)
	{
		return handle.createUpdate("DELETE FROM item_variant WHERE `id` = :getId")
				.bindMethods(itemVariant)
				.execute() == 1;
	}

	@Override
	public List<PurchaseHeader> listPurchaseHeaders()
	{
		return handle.createQuery("SELECT * FROM purchase_header")
				.mapTo(PurchaseHeader.class)
				.list();
	}

	@Override
	public PurchaseHeader getPurchaseHeader(int id)
	{
		return handle.createQuery("SELECT * FROM purchase_header WHERE `id` = :id")
				.bind("id", id)
				.mapTo(PurchaseHeader.class)
				.one();
	}

	@Override
	public boolean createPurchaseHeader(PurchaseHeader purchaseHeader)
	{
		return handle.createUpdate("INSERT INTO purchase_header (`vendor_id`, `posting_date`, `delivery_date`) VALUES (:getVendorId, :getPostingDate, :getDeliveryDate)")
				.bindMethods(purchaseHeader)
				.execute() == 1;
	}

	@Override
	public boolean updatePurchaseHeader(PurchaseHeader purchaseHeader)
	{
		return handle.createUpdate("UPDATE purchase_header SET `vendor_id` = :getVendorId, `posting_date` = :getPostingDate, `delivery_date` = :getDeliveryDate WHERE `id` = :getId")
				.bindMethods(purchaseHeader)
				.execute() == 1;
	}

	@Override
	public boolean deletePurchaseHeader(PurchaseHeader purchaseHeader)
	{
		return handle.createUpdate("DELETE FROM purchase_header WHERE `id` = :getId")
				.bindMethods(purchaseHeader)
				.execute() == 1;
	}

	@Override
	public List<PurchaseLine> listPurchaseLines()
	{
		return handle.createQuery("SELECT * FROM purchase_lines")
				.mapTo(PurchaseLine.class)
				.list();
	}

	@Override
	public List<PurchaseLine> listByPurchaseHeader(PurchaseHeader purchaseHeader)
	{
		return handle.createQuery("SELECT * FROM purchase_line WHERE `purchase_header_id` = :getId")
				.bindMethods(purchaseHeader)
				.mapTo(PurchaseLine.class)
				.list();
	}

	@Override
	public PurchaseLine getPurchaseLine(int id)
	{
		return handle.createQuery("SELECT * FROM purchase_line WHERE `id` = :id")
				.bind("id", id)
				.mapTo(PurchaseLine.class)
				.one();
	}

	@Override
	public boolean createPurchaseLine(PurchaseLine purchaseLine)
	{
		return handle.createUpdate(	"INSERT INTO purchase_line (`purchase_header_id`, `item_variant_id`, `price`, `quantity`, `line_amount`, `delivered`) "
								+ 	"VALUES (:getPurchaseHeaderId, :getItemVariantId, :getPrice, :getQuantity, :getLineAmount, :isDelivered)")
				.bindMethods(purchaseLine)
				.execute() == 1;
	}

	@Override
	public boolean updatePurchaseLine(PurchaseLine purchaseLine)
	{
		return handle.createUpdate(	"UPDATE purchase_line SET `purchase_header_id` = :getPurchaseHeaderId, `item_variant_id` = :getItemVariantId, `price` = :getPrice,"
								+ 	"`quantity` = :getQuantity, `line_amount` = :getLineAmount, `delivered` = :isDelivered WHERE `id` = :getId")
				.bindMethods(purchaseLine)
				.execute() == 1;
	}

	@Override
	public boolean deletePurchaseLine(PurchaseLine purchaseLine)
	{
		return handle.createUpdate("DELETE FRPM purchase_line WHERE `id` = :getId")
				.bindMethods(purchaseLine)
				.execute() == 1;
	}

	@Override
	public List<SalesHeader> listSalesHeaders()
	{
		return handle.createQuery("SELECT * FROM sales_header")
				.mapTo(SalesHeader.class)
				.list();
	}

	@Override
	public SalesHeader getSalesHeader(int id)
	{
		return handle.createQuery("SELECT * FROM sales_header WHERE `id` = :id")
				.bind("id", id)
				.mapTo(SalesHeader.class)
				.one();
	}

	@Override
	public boolean createSalesHeader(SalesHeader salesHeader)
	{
		return handle.createUpdate("INSERT INTO sales_header (`employee_id`, `posting_date`) VALUES (:getEmployeeId, :getPostingDate)")
				.bindMethods(salesHeader)
				.execute() == 1;
	}

	@Override
	public boolean updateSalesHeader(SalesHeader salesHeader)
	{
		return handle.createUpdate("UPDATE sales_header SET `employee_id` = :getEmployeeId, `posting_date` = :getPostingDate")
				.bindMethods(salesHeader)
				.execute() == 1;
	}

	@Override
	public boolean deleteSalesHeader(SalesHeader salesHeader)
	{
		return handle.createUpdate("DELETE FROM sales_header WHERE `id` = :getId")
				.bindMethods(salesHeader)
				.execute() == 1;
	}

	@Override
	public List<SalesLine> listSalesLines()
	{
		return handle.createQuery("SELECT * FROM sales_line")
				.mapTo(SalesLine.class)
				.list();
	}

	@Override
	public List<SalesLine> listBySalesHeader(SalesHeader salesHeader)
	{
		return handle.createQuery("SELECT * FROM sales_header WHERE `sales_header_id` = :getId")
				.bindMethods(salesHeader)
				.mapTo(SalesLine.class)
				.list();
	}

	@Override
	public SalesLine getSalesLine(int id)
	{
		return handle.createQuery("SELECT * FROM sales_line WHERE `id` = :id")
				.bind("id", id)
				.mapTo(SalesLine.class)
				.one();
	}

	@Override
	public boolean createSalesLine(SalesLine salesLine)
	{
		return handle.createUpdate(	"INSERT INTO sales_line (`sales_header_id`, `item_variant_id`, `item_price`, `quantity`, `line_amount`) "
								+ 	"VALUES (:getSalesHeaderId, :getItemVariantId, :getItemPrice, :getQuantity, :getLineAmount)")
				.bindMethods(salesLine)
				.execute() == 1;
	}

	@Override
	public boolean updateSalesLine(SalesLine salesLine)
	{
		return handle.createUpdate(	"UPDATE sales_line SET `sales_header_id` = :getSalesHeaderId, `item_variant_id` = :getItemVariantId, `item_price` = :getItemPrice,"
								+ 	"`quantity` = :getQuantity, `line_amount` = :getLineAmount WHERE `id` = :getId")
				.bindMethods(salesLine)
				.execute() == 1;
	}

	@Override
	public boolean deleteSalesLine(SalesLine salesLine)
	{
		return handle.createUpdate("DELETE FROM sales_line WHERE `id` = :getId")
				.bindMethods(salesLine)
				.execute() == 1;
	}

	@Override
	public List<ValueLedgerEntry> listValueLedgerEntries()
	{
		return handle.createQuery("SELECT * FROM value_ledger_entry")
				.mapTo(ValueLedgerEntry.class)
				.list();
	}

	@Override
	public ValueLedgerEntry getValueLedgerEntry(int id)
	{
		return handle.createQuery("SELECT * FROM value_ledger_entry WHERE `id` = :id")
				.bind("id", id)
				.mapTo(ValueLedgerEntry.class)
				.one();
	}

	@Override
	public boolean createValueLedgerEntry(ValueLedgerEntry valueLedgerEntry)
	{
		return handle.createUpdate("INSERT INTO value_ledger_entry (`employee_id`, `amount`, `posting_date`, `source_doc_type`, `source_doc_no`) "
				+ "VALUES (:getEmployeeId, :getAmount, :getPostingDate, :getSourceDocType, :getSourceDocNo)")
				.bindMethods(valueLedgerEntry)
				.execute() == 1;
	}

	@Override
	public List<Vendor> listVendors()
	{
		return handle.createQuery("SELECT * FROM vendor")
				.mapTo(Vendor.class)
				.list();
	}

	@Override
	public Vendor getVendor(int id)
	{
		return handle.createQuery("SELECT * FROM vendor WHERE `id` = :id")
				.bind("id", id)
				.mapTo(Vendor.class)
				.one();
	}

	@Override
	public boolean createVendor(Vendor vendor)
	{
		return handle.createUpdate("INSERT INTO vendor (`name`, `address`, `post_code`, `city`, `country`) VALUES (:getName, :getAddress, :getPostCode, :getCity, :getCountry)")
				.bindMethods(vendor)
				.execute() == 1;
	}

	@Override
	public boolean updateVendor(Vendor vendor)
	{
		return handle.createUpdate(	"UPDATE vendor SET `name` = :getName, `address` = :getAddress, `post_code` = :getPostCode, `city` = :getCity, `country` = :getCountry "
								+ 	"WHERE `id` = :getId")
				.bindMethods(vendor)
				.execute() == 1;
	}

	@Override
	public boolean deleteVendor(Vendor vendor)
	{
		return handle.createUpdate("DELETE FROM vendor WHERE `id` = :getId")
				.bindMethods(vendor)
				.execute() == 1;
	}
	

	@Override
	public boolean createEmptyTemporaryTable(String table)
	{
		return handle.createUpdate(String.format("CREATE TEMPORARY TABLE %s (entry_no INT AUTO_INCREMENT PRIMARY KEY) SELECT * FROM %s LIMIT 0", table, table))
				.execute() > 0;
	}
	
	@Override
	public boolean copyToTemporaryTable(String table)
	{
		return handle.createUpdate(String.format("CREATE TEMPORARY TABLE %s (id INT AUTO_INCREMENT PRIMARY KEY) SELECT * FROM %s", table, table))
				.execute() > 0;
	}

	@Override
	public boolean dropTemporaryTable(String table)
	{
		return handle.createUpdate(String.format("DROP TEMPORARY TABLE %s", table))
				.execute() > 0;
	}

	@Override
	public void close() throws IOException
	{
		handle.close();
	}

	@Override
	public void beginTransaction()
	{
		handle.begin();
	}

	@Override
	public void commit()
	{
		handle.commit();
	}

	@Override
	public void rollback()
	{
		handle.rollback();
	}

	@Override
	public void setTransactionIsolation(TransactionIsolationLevel level)
	{
		handle.setTransactionIsolation(level);
	}
}
