package csa.model;

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
	private float price;
	
	@NonNull
	private int quantity;
	
	@NonNull
	private float lineAmount;
	
	@NonNull
	private boolean delivered;
}
