package csa.rest.server;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

public class RestServer
{
	public static final String BASE_URI = "http://0.0.0.0:8080/";
	private final ServiceLocator serviceLocator;
	private final HttpServer httpServer;
	
	public RestServer()
	{
		httpServer = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), new ServerConfig());
		serviceLocator = ServiceLocatorUtilities.createAndPopulateServiceLocator();
	}
	
	public void start()
	{
		try
		{
			httpServer.start();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
}
