package csa.model;

import java.beans.ConstructorProperties;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(value={"id", "item", "itemId"}, allowGetters=true)
public class ItemVariant 
{
	private int id;
	
	@NonNull
	private Item item;
	@NonNull
	private String name;
	@NonNull
	private float price;
	@NonNull
	private String size;
	
	@JsonCreator(mode=Mode.DISABLED)
	@ConstructorProperties({"id", "item_id", "name", "price", "size"})
	public ItemVariant(int id, @NonNull Item item, @NonNull String name, float price, @NonNull String size)
	{
		this.id = id;
		this.item = item;
		this.name = name;
		this.price = price;
		this.size = size;
	}
	
	@JsonIgnore
	public int getItemId()
	{
		return item.getId();
	}
}
