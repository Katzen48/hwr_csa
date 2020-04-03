package csa.model;

import java.beans.ConstructorProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor(onConstructor_={@ConstructorProperties({"id", "name"})})
public class Item
{
	private int id;
	@NonNull
	private String name;
}
