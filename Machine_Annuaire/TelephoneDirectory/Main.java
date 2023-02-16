package TelephoneDirectory;

import java.net.ServerSocket;
import java.net.SocketException;

public class Main {
	
	public static void main(String[] args) 
	{
		try 
		{
			ServerSocket s = new ServerSocket(1717);
			while(true) 
			{
				ThreadedSocket th = new ThreadedSocket(s.accept());
				th.start();
			}
		} catch (SocketException se) {se.printStackTrace();}		  
		catch (Exception e) {System.out.println(e);}		 
	}			
}