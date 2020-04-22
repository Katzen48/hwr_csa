package csa.model;

import java.beans.ConstructorProperties;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
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
	public SalesLine(float itemPrice, int quantity)
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
