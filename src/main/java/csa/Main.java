package csa;

import csa.rest.server.RestServer;

public class Main
{
	private RestServer server;
	
	public Main()
	{
		server = new RestServer();
		
		server.start();
	}
	
	public static void main(String[] args)
	{
		new Main();
	}
}
