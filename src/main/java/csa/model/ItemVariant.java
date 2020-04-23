package csa.model;

import java.beans.ConstructorProperties;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NonNull;

@Data
public class ItemVariant {
	
	private int id;
	
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
	
	@JsonCreator
	public ItemVariant(@JsonProperty("name") String name, @JsonProperty("price") float price, @JsonProperty("size") String size)
	{
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
