package csa.model;

import java.beans.ConstructorProperties;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NonNull;

@Data
@JsonIgnoreProperties(value={"id", "purchase_header", "item_variant", "line_amount"}, allowGetters=true)
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
	
	@JsonIgnoreProperties(allowSetters=true)
	private int item_variant_id;
	
	@JsonCreator
	public PurchaseLine(@JsonProperty("price") float price, @JsonProperty("quantity") int quantity, @JsonProperty("delivered") boolean delivered) 
	{
		this.price = price;
		this.quantity = quantity;
		this.delivered = delivered;
		this.lineAmount = price * quantity;
	}
	
	@JsonCreator(mode=Mode.DISABLED)
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
