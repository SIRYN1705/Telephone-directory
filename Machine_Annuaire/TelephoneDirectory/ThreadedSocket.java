package TelephoneDirectory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ThreadedSocket extends Thread {
	private Socket user; 
	public ThreadedSocket(Socket user)  
	{
		this.user = user; 
	}
	public void run()  
	{
		try 
		{
			BufferedReader buffer = new BufferedReader( new InputStreamReader(user.getInputStream()));
			PrintWriter writer = new PrintWriter( user.getOutputStream(),true);
			String name = buffer.readLine();
			writer.println(recherche(name));
			user.close(); 
		} 
		catch (Exception e) {System.out.println(e);}
	}
	public String recherche(String name) { 
		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/TelephoneDirectory", "root", "");
			java.sql.Statement statement = conn.createStatement();
			ResultSet res = statement.executeQuery("SELECT number From User where name='"+name+"'");
			while( res.next()) {
				return res.getString("number");
			}
		}catch(SQLException e){        
			System.out.println(e.getMessage());
		}
		return "";
	}     
}