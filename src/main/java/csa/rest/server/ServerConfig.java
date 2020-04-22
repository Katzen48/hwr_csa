package csa.rest.server;

import org.glassfish.jersey.server.ResourceConfig;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

public class ServerConfig extends ResourceConfig
{
	public ServerConfig()
	{
		packages("csa.rest.resources");
		
		register(new ApplicationBinder());
		register(new JacksonJaxbJsonProvider(new ObjectMapperFactory().buildObjectMapper(), JacksonJaxbJsonProvider.DEFAULT_ANNOTATIONS));
	}
}
