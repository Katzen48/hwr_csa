package csa.model;

import java.beans.ConstructorProperties;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ItemVariant {
	
	private int id;
	@NonNull
	private Item item;
	@NonNull
	private String name;
	@NonNull
	private float price;
	@NonNull
	private String size;

	@ConstructorProperties({"id", "item_id", "name", "price", "size"})
	public ItemVariant(int id, @NonNull Item item, @NonNull String name, float price, @NonNull String size)
	{
		this.id = id;
		this.item = item;
		this.name = name;
		this.price = price;
		this.size = size;
	}
	
	public int getItemId()
	{
		return item.getId();
	}
}
