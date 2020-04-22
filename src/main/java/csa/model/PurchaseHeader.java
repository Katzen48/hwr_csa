package csa.model;

import java.beans.ConstructorProperties;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
	public PurchaseHeader(int id, @NonNull Vendor vendor, @NonNull LocalDate postingDate, @NonNull LocalDate deliveryDate)
	{
		this.id = id;
		this.vendor = vendor;
		this.postingDate = postingDate;
		this.deliveryDate = deliveryDate;
	}
	
	@JsonCreator
	public PurchaseHeader(LocalDate postingDate, LocalDate deliveryDate)
	{
		this.postingDate = postingDate;
		this.deliveryDate = deliveryDate;
	}
	
	@JsonIgnore
	public int getVendorId()
	{
		return vendor.getId();
	}
}	
