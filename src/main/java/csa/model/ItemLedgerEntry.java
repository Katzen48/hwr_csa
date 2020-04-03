package csa.model;

import java.beans.ConstructorProperties;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor(onConstructor_={@ConstructorProperties({"entry_no", "item_id", "item_variant_id", "item_name", "item_variant_name", "item_price", "quantity", "posting_date", "source_doc_type", "source_doc_no"})})
public class ItemLedgerEntry 
{
	private int entryNo;
	
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
	private LocalDate postingDate;
	
	@NonNull
	private SourceDocType sourceDocType;
	
	@NonNull
	private int sourceDocNo;
}
