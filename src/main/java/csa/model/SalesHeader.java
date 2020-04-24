package csa.model;

import java.beans.ConstructorProperties;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(value={"id", "employee"}, allowGetters=true)
public class SalesHeader {

	private int id;
	@NonNull
	private Employee employee;
	@NonNull
	private LocalDate postingDate;
	
	@JsonCreator(mode=Mode.DISABLED)
	@ConstructorProperties({"id", "employee_id", "posting_date"})
	public SalesHeader(int id, @NonNull Employee employee, @NonNull LocalDate postingDate)
	{
		this.id = id;
		this.employee = employee;
		this.postingDate = postingDate;
	}
	
	@JsonIgnore
	public int getEmployeeId()
	{
		return employee.getId();
	}
}
