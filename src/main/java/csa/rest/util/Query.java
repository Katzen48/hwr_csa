package csa.rest.util;

import java.beans.ConstructorProperties;

import lombok.Data;
import lombok.NonNull;

@Data
public class Query
{
	@NonNull
	private String query;
	
	@ConstructorProperties({"query"})
	public Query(String query)
	{
		this.query = query;
	}
}
