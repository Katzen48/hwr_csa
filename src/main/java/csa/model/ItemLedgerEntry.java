package csa.model;

import java.util.Date;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ItemLedgerEntry 
{
	private int id;
	
	@NonNull
	private int itemId;
	
	@NonNull
	private int itemVariantId;
	
	@NonNull
	private String itemName;
	
	@NonNull
	private String itemVariantName;
	
	@NonNull
	private float itemPrice;
	
	@NonNull
	private int quantity;
	
	@NonNull
	private Date postingDate;
	
	@NonNull
	private String sourceDocType;
	
	@NonNull
	private int sourceDocNo;
}
