package facebook;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection  {

   	
	Connection dbConnection() 
	{    
		Connection con=null;
		
		try {
		
			String url="jdbc:mysql://localhost:3306/fbdatabase";
			String username="root";
			String pin="root";
			Class.forName("com.mysql.cj.jdbc.Driver");
		    con=DriverManager.getConnection(url, username, pin);
			return con;
		    	
		} catch (Exception e) {
		
		}
		return con;
	}
	
}
