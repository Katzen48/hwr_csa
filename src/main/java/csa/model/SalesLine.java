package csa.model;

import java.beans.ConstructorProperties;

import org.jdbi.v3.core.mapper.Nested;

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
	public SalesLine(int id, @Nested("sales_header") @NonNull SalesHeader salesHeader, @Nested("item_variant") @NonNull ItemVariant itemVariant, float itemPrice, int quantity, float lineAmount)
	{
		this.id = id;
		this.salesHeader = salesHeader;
		this.itemVariant = itemVariant;
		this.itemPrice = itemPrice;
		this.quantity = quantity;
		this.lineAmount = lineAmount;
	}
	
	public int getSalesHeaderId()
	{
		return salesHeader.getId();
	}
	
	public int getItemVariantId()
	{
		return itemVariant.getId();
	}
}
