package csa.service.contract;

import java.util.List;

import org.jvnet.hk2.annotations.Contract;

import csa.model.Item;

@Contract
public interface IItemService
{
	List<Item> listItems();
	Item getItem(int id);
	Item createItem(Item item);
	boolean updateItem(Item item);
	boolean deleteItem(Item item);
}
