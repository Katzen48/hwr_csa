package csa.service;

import java.util.List;

import javax.inject.Inject;

import org.jvnet.hk2.annotations.Service;

import csa.database.DatabaseAdapter;
import csa.model.Item;
import csa.service.contract.IItemService;

@Service
public class ItemService implements IItemService
{
	@Inject
	private DatabaseAdapter dbAdapter;
	
	@Override
	public List<Item> listItems()
	{
		return dbAdapter.listItems();
	}

	@Override
	public Item getItem(int id)
	{
		return dbAdapter.getItem(id);
	}

	@Override
	public Item createItem(Item item)
	{
		return dbAdapter.createItem(item);
	}

	@Override
	public boolean updateItem(Item item)
	{
		return dbAdapter.updateItem(item);
	}

	@Override
	public boolean deleteItem(Item item)
	{
		return dbAdapter.deleteItem(item);
	}
}
