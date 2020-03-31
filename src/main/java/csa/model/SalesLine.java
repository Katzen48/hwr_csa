package csa.model;

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
}
