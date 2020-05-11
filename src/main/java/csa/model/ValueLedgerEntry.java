package csa.model;

import java.beans.ConstructorProperties;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor(onConstructor_={@ConstructorProperties({"entry_no", "amount", "posting_date", "source_doc_type", "source_doc_no"})})
public class ValueLedgerEntry 
{
	private int entryNo;
	
	@NonNull
	private float amount;
	
	@NonNull
	private LocalDate postingDate;
	
	@NonNull
	private SourceDocType sourceDocType;
	
	@NonNull
	private int sourceDocNo;
}
