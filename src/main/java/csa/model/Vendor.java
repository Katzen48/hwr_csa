package csa.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Vendor 
{
	private int id;
	
	@NonNull
	private String Name;
	
	@NonNull
	private String Address;
	
	@NonNull
	private int postCode;
	
	@NonNull
	private String city;
	
	@NonNull
	private String country;
}
