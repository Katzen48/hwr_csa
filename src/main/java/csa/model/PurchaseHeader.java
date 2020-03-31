package csa.model;

import java.util.Date;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PurchaseHeader 
{
	private int id;
	
	@NonNull
	private Vendor vendor;
	@NonNull
	private Date postingDate;
	@NonNull
	private Date deliveryDate;
	
}	
