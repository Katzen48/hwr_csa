package csa.model;

import java.beans.ConstructorProperties;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(value={"id"}, allowGetters=true)
public class Item
{
	private int id;
	@NonNull
	private String name;
	
	@JsonCreator(mode=Mode.DISABLED)
	@ConstructorProperties({"id", "name"})
	public Item(int id, String name)
	{
		this.id = id;
		this.name = name;
	}
}
