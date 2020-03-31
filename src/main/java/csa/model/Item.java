package csa.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Item
{
	private int id;
	@NonNull
	private String name;
}
