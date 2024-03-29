package csa.model;

import java.beans.ConstructorProperties;
import java.time.LocalDate;

import org.jvnet.hk2.annotations.Contract;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(value={"id", "employee"}, allowGetters=true)
@Contract
public class SalesHeader 
{
	private int id;
	@NonNull
	private Employee employee;
	@NonNull
	@JsonProperty("posting_date")
	private LocalDate postingDate;
	
	// Only for JSON deserialization
	@JsonIgnoreProperties(allowSetters=true)
	private int employee_id;
	
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
