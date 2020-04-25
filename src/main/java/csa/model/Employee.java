package csa.model;

import java.beans.ConstructorProperties;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor(onConstructor_={@ConstructorProperties({"id", "given_name", "surname"})})
@JsonIgnoreProperties(value={"id"}, allowGetters=true)
public class Employee
{
	@Setter(value=AccessLevel.NONE)
	private int id;
	@NonNull
	@JsonProperty("given_name")
	private String givenName;
	@NonNull
	private String surname;
}
