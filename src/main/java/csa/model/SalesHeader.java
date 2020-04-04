package csa.model;

import java.beans.ConstructorProperties;
import java.time.LocalDate;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SalesHeader {

	private int id;
	@NonNull
	private Employee employee;
	@NonNull
	private LocalDate postingDate;
	
	@ConstructorProperties({"id", "employee_id", "posting_date"})
	public SalesHeader(int id, @NonNull Employee employee, @NonNull LocalDate postingDate)
	{
		this.id = id;
		this.employee = employee;
		this.postingDate = postingDate;
	}
	
	public int getEmployeeId()
	{
		return employee.getId();
	}
}
