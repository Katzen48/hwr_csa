package csa.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ItemVariant {
	
	private int id;
	@NonNull
	private Item item;
	@NonNull
	private String name;
	@NonNull
	private float price;
	@NonNull
	private String size;

}
