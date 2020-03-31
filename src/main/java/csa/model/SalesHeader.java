package csa.model;

import java.util.Date;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SalesHeader {

	private int id;
	@NonNull
	private String employee;
	@NonNull
	private Date postingDate;
}
