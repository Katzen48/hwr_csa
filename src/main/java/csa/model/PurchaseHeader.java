package csa.model;

import java.beans.ConstructorProperties;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(value={"id", "vendor"}, allowGetters=true)
public class PurchaseHeader 
{
	private int id;
	
	@NonNull
	private Vendor vendor;
	@NonNull
	private LocalDate postingDate;
	@NonNull
	private LocalDate deliveryDate;
	
	@JsonCreator(mode=Mode.DISABLED)
	@ConstructorProperties({"id", "vendor_id", "posting_date", "delivery_date"})
	public PurchaseHeader(int id, @NonNull Vendor vendor, @NonNull LocalDate postingDate, @NonNull LocalDate deliveryDate)
	{
		this.id = id;
		this.vendor = vendor;
		this.postingDate = postingDate;
		this.deliveryDate = deliveryDate;
	}
	
	@JsonIgnore
	public int getVendorId()
	{
		return vendor.getId();
	}
}	
