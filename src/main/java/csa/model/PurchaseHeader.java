package csa.model;

import java.beans.ConstructorProperties;
import java.time.LocalDate;

import org.jdbi.v3.core.mapper.Nested;

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
	private LocalDate postingDate;
	@NonNull
	private LocalDate deliveryDate;
	
	@ConstructorProperties({"id", "vendor_id", "posting_date", "delivery_date"})
	public PurchaseHeader(int id, @Nested("vendor") @NonNull Vendor vendor, @NonNull LocalDate postingDate, @NonNull LocalDate deliveryDate)
	{
		this.id = id;
		this.vendor = vendor;
		this.postingDate = postingDate;
		this.deliveryDate = deliveryDate;
	}
	
	public int getVendorId()
	{
		return vendor.getId();
	}
}	
