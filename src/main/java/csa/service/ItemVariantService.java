package csa.service;

import java.util.List;

import javax.inject.Inject;

import org.jvnet.hk2.annotations.Service;

import csa.database.DatabaseAdapter;
import csa.model.Item;
import csa.model.ItemVariant;
import csa.service.contract.IItemVariantService;

@Service
public class ItemVariantService implements IItemVariantService
{
	@Inject
	private DatabaseAdapter dbAdapter;
	
	@Override
	public List<ItemVariant> listItemVariants()
	{
		return dbAdapter.listItemVariants();
	}
	
	@Override
	public List<ItemVariant> listByItem(Item item)
	{
		return dbAdapter.listByItem(item);
	}

	@Override
	public ItemVariant getItemVariant(int id)
	{
		return dbAdapter.getItemVariant(id);
	}

	@Override
	public ItemVariant createItemVariant(ItemVariant itemVariant)
	{
		return dbAdapter.createItemVariant(itemVariant);
	}

	@Override
	public boolean updateItemVariant(ItemVariant itemVariant)
	{
		return dbAdapter.updateItemVariant(itemVariant);
	}

	@Override
	public boolean deleteItemVariant(ItemVariant itemVariant)
	{
		return dbAdapter.deleteItemVariant(itemVariant);
	}

	@Override
	public List<ItemVariant> searchByName(String query)
	{
		return dbAdapter.searchItemVariantByName(query);
	}
}
