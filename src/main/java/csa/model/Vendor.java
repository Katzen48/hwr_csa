package csa.model;

import java.beans.ConstructorProperties;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@JsonIgnoreProperties(value={"id"}, allowGetters=true)
@AllArgsConstructor(onConstructor_={@ConstructorProperties({"id", "name", "address", "post_code", "city", "country"})})
public class Vendor 
{
	private int id;
	
	@NonNull
	private String name;
	
	@NonNull
	private String address;
	
	@NonNull
	private int postCode;
	
	@NonNull
	private String city;
	
	@NonNull
	private String country;
}
