package csa.model;

import java.util.Date;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ValueLedgerEntry 
{
	private int entry_no;
	
	@NonNull
	private Employee employee;
	@NonNull
	private float amount;
	@NonNull
	private Date postingDate;
	@NonNull
	private String sourceDocType;
	@NonNull
	private int sourceDocNo;
}
