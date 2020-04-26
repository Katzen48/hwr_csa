package csa.model;

import java.beans.ConstructorProperties;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import lombok.Data;
import lombok.NonNull;

@Data
@JsonIgnoreProperties(value={"id", "sales_header", "item_variant", "line_amount"}, allowGetters=true)
public class SalesLine 
{
	private int id;
	
	@NonNull
	private SalesHeader salesHeader;
	
	@NonNull
	private ItemVariant itemVariant;
	
	@NonNull
	private float itemPrice;
	
	@NonNull
	private int quantity;
	
	@NonNull
	private float lineAmount;
	
	// Only for JSON deserialization
	@JsonIgnoreProperties(allowSetters=true)
	private int item_variant_id;
	
	@JsonCreator(mode=Mode.DISABLED)
	@ConstructorProperties({"id", "sales_header_id", "item_variant_id", "item_price", "quantity", "line_amount"})
	public SalesLine(int id, @NonNull SalesHeader salesHeader, @NonNull ItemVariant itemVariant, float itemPrice, int quantity, float lineAmount)
	{
		this.id = id;
		this.salesHeader = salesHeader;
		this.itemVariant = itemVariant;
		this.itemPrice = itemPrice;
		this.quantity = quantity;
		this.lineAmount = lineAmount;
	}
	
	@JsonCreator
	public SalesLine(@JsonProperty("item_price") float itemPrice, @JsonProperty("quantity") int quantity)
	{
		this.itemPrice = itemPrice;
		this.quantity = quantity;
		this.lineAmount = itemPrice * quantity;
	}
	
	@JsonIgnore
	public int getSalesHeaderId()
	{
		return salesHeader.getId();
	}
	
	@JsonIgnore
	public int getItemVariantId()
	{
		return itemVariant.getId();
	}
}
