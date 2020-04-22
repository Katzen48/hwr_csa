package csa.database;

import java.io.Closeable;
import java.util.List;

import org.glassfish.jersey.spi.Contract;
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

@Contract
public interface DatabaseAdapter extends Closeable
{
	// Connection
	void connect();
	
	// Employee
	List<Employee> listEmployees();
	Employee getEmployee(int id);
	Employee createEmployee(Employee employee);
	boolean updateEmployee(Employee employee);
	boolean deleteEmployee(Employee employee);
	
	// Item
	List<Item> listItems();
	Item getItem(int id);
	Item createItem(Item item);
	boolean updateItem(Item item);
	boolean deleteItem(Item item);
	
	// Item Ledger Entry
	List<ItemLedgerEntry> listItemLedgerEntries();
	ItemLedgerEntry getItemLedgerEntry(int entryNo);
	ItemLedgerEntry createItemLedgerEntry(ItemLedgerEntry itemLedgerEntry);
	
	// Item Variant
	List<ItemVariant> listItemVariants();
	List<ItemVariant> listByItem(Item item);
	ItemVariant getItemVariant(int id);
	ItemVariant createItemVariant(ItemVariant itemVariant);
	boolean updateItemVariant(ItemVariant itemVariant);
	boolean deleteItemVariant(ItemVariant itemVariant);
	
	// Purchase Header
	List<PurchaseHeader> listPurchaseHeaders();
	PurchaseHeader getPurchaseHeader(int id);
	PurchaseHeader createPurchaseHeader(PurchaseHeader purchaseHeader);
	boolean updatePurchaseHeader(PurchaseHeader purchaseHeader);
	boolean deletePurchaseHeader(PurchaseHeader purchaseHeader);
	
	// Purchase Line
	List<PurchaseLine> listPurchaseLines();
	List<PurchaseLine> listByPurchaseHeader(PurchaseHeader purchaseHeader);
	PurchaseLine getPurchaseLine(int id);
	PurchaseLine createPurchaseLine(PurchaseLine purchaseLine);
	boolean updatePurchaseLine(PurchaseLine purchaseLine);
	boolean deletePurchaseLine(PurchaseLine purchaseLine);
	
	// Sales Header
	List<SalesHeader> listSalesHeaders();
	SalesHeader getSalesHeader(int id);
	SalesHeader createSalesHeader(SalesHeader salesHeader);
	boolean updateSalesHeader(SalesHeader salesHeader);
	boolean deleteSalesHeader(SalesHeader salesHeader);
	
	// Sales Line
	List<SalesLine> listSalesLines();
	List<SalesLine> listBySalesHeader(SalesHeader salesHeader);
	SalesLine getSalesLine(int id);
	SalesLine createSalesLine(SalesLine salesLine);
	boolean updateSalesLine(SalesLine salesLine);
	boolean deleteSalesLine(SalesLine salesLine);
	
	// Value Ledger Entry
	List<ValueLedgerEntry> listValueLedgerEntries();
	ValueLedgerEntry getValueLedgerEntry(int id);
	ValueLedgerEntry createValueLedgerEntry(ValueLedgerEntry valueLedgerEntry);
	
	// Vendor
	List<Vendor> listVendors();
	Vendor getVendor(int id);
	Vendor createVendor(Vendor vendor);
	boolean updateVendor(Vendor vendor);
	boolean deleteVendor(Vendor vendor);
	
	// Utils
	boolean createEmptyTemporaryTable(String table, String key);
	boolean copyToTemporaryTable(String table);
	boolean dropTemporaryTable(String table);
	void beginTransaction();
	void commit();
	void rollback();
	void setTransactionIsolation(TransactionIsolationLevel level);
}
