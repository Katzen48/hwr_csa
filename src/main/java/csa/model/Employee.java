package csa.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Employee
{
	private int id;
	@NonNull
	private String givenName;
	@NonNull
	private String surname;
}
