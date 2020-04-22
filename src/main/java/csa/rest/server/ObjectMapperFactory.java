package csa.rest.server;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class ObjectMapperFactory
{
	public ObjectMapper buildObjectMapper()
	{
		ObjectMapper mapper = new ObjectMapper();
		
		return mapper;
	}
}
