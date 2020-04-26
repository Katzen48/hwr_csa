package csa.service.contract;

import java.util.List;

import org.jvnet.hk2.annotations.Contract;

import csa.model.Item;
import csa.model.ItemVariant;

@Contract
public interface IItemVariantService
{
	List<ItemVariant> listItemVariants();
	List<ItemVariant> listByItem(Item item);
	ItemVariant getItemVariant(int id);
	ItemVariant createItemVariant(ItemVariant itemVariant);
	boolean updateItemVariant(ItemVariant itemVariant);
	boolean deleteItemVariant(ItemVariant itemVariant);
	List<ItemVariant> searchByName(String query);
}
