package il.cshaifasweng.OCSFMediatorExample.server;

import java.io.IOException;


public class SimpleChatServer
{
	
	private static SimpleServer server;
    public static void main( String[] args ) throws IOException
    {
        SimpleServer.getSessionFactory("213461692");
        server = new SimpleServer(3000);
        System.out.println("server is listening");
        server.listen();
    }
}
