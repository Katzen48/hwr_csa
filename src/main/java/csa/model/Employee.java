package csa.model;

import java.beans.ConstructorProperties;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@RequiredArgsConstructor
@AllArgsConstructor(onConstructor_={@ConstructorProperties({"id", "given_name", "surname"})})
public class Employee
{
	@Setter(value=AccessLevel.NONE)
	private int id;
	@NonNull
	private String givenName;
	@NonNull
	private String surname;
}
