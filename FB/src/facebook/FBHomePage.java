package facebook;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class FBHomePage {

	static Connection con=null; 
    static Statement st=null;
	public static void main(String[] args) {
		
          FBHomePage fbHomePage=new FBHomePage();
          fbHomePage.connectedDatabase();
        
	}
	
	void connectedDatabase()
	{
	    DbConnection databaseConnection=new DbConnection();
		
		con=databaseConnection.dbConnection();
		System.out.println();
		if(con==null)
			System.out.println("                  Cannot be connected to server check again !                  ");
		else
			System.out.println("              @MySQL Connection Established  \n\t\t Connected to MySQLDatabase .../                                       ");
        System.out.println() ;
	
        
          FBLoginPage fbLoginPage=new FBLoginPage();
          try {
			  st=con.createStatement(); 
        	  fbLoginPage.loginPage();
		      } 
          catch (SQLException e) {
        	  System.out.println("Login page 404 error !!! ");
			  e.printStackTrace();
		    }
    	
	}
	
}
