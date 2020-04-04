package csa.model;

import java.beans.ConstructorProperties;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PurchaseLine 
{
	private int id;
	
	@NonNull
	private PurchaseHeader purchaseHeader;
	
	@NonNull
	private ItemVariant itemVariant;
	
	@NonNull
	private float price;
	
	@NonNull
	private int quantity;
	
	@NonNull
	private float lineAmount;
	
	@NonNull
	private boolean delivered;
	
	@ConstructorProperties({"id", "purchase_header_id", "item_variant_id", "price", "quantity", "line_amount", "delivered"})
	public PurchaseLine(int id, @NonNull PurchaseHeader purchaseHeader, @NonNull ItemVariant itemVariant, float price, int quantity, float lineAmount, boolean delivered)
	{
		this.id = id;
		this.purchaseHeader = purchaseHeader;
		this.itemVariant = itemVariant;
		this.price = price;
		this.quantity = quantity;
		this.lineAmount = lineAmount;
		this.delivered = delivered;
	}
	
	public int getPurchaseHeaderId()
	{
		return purchaseHeader.getId();
	}
	
	public int getItemVariantId()
	{
		return itemVariant.getId();
	}
}
