package csa.model;

import java.beans.ConstructorProperties;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@JsonCreator
	public PurchaseLine(float price, int quantity, boolean delivered)
	{
		this.price = price;
		this.quantity = quantity;
		this.delivered = delivered;
		this.lineAmount = price * quantity;
	}
	
	@JsonIgnore
	public int getPurchaseHeaderId()
	{
		return purchaseHeader.getId();
	}
	
	@JsonIgnore
	public int getItemVariantId()
	{
		return itemVariant.getId();
	}
}
