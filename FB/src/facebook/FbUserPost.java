package facebook;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class FbUserPost {

	
	public void createPost(String username) {
    		Connection con=FBHomePage.con;
	        Statement st=FBHomePage.st;

		    System.out.println("                   create post            ");
		    Scanner scanner=new Scanner(System.in);
		    System.out.println();
		    
		    System.out.println("hi "+username);
		    System.out.println(" write Post below to share social media  : ");
		    String post=scanner.nextLine();
            try {
            	   st.executeUpdate(" insert into userposttable (username,post) values(\""+username+"\",\""+post+"\");");
//                   st.executeUpdate(" insert into commentstable (username,viewcomment,post,viewname) values\r\n"
//                   		+ " (\""+username+"\",\""+null+"\",\""+post+"\",\""+null+"\");");       		       
            	   System.out.println("                             post Uploaded                  ");	
                   
            } catch (Exception e) {
                     e.printStackTrace();
			}              
	}

	public void viewPost(String username) {
		Connection con=FBHomePage.con;
        Statement st=FBHomePage.st;
        ResultSet resultSet;	   
		System.out.println("--------------------------------------------------");
		Scanner scanner=new Scanner(System.in);
        try {
			resultSet=st.executeQuery("Select * from userposttable");
            System.out.println("PostID   \t\t       |      UserName   \t\t   |   Post ");
			while(resultSet.next()) 
		    {
		    	System.out.println();
		    	System.out.println(resultSet.getString(1)+"   \t\t   |   "+resultSet.getString(2)+"   \t\t   |   "+resultSet.getString(3));
                
		    }
			System.out.println("post options :> ");
			new FbUserPost().postOptions(username);
			
         } 
        catch (SQLException e) {
			
			e.printStackTrace();
		}                     		
		
		
	}

	private void postOptions(String username) {
		Connection con=FBHomePage.con;
        Statement st=FBHomePage.st;
        ResultSet resultSet;
		Scanner scanner=new Scanner(System.in);
		
		System.out.println();          
		System.out.println("slect post(Id) to view it ");
        
		int id=scanner.nextInt();
		String tempusername="",temppost="";
		try {
			     resultSet=st.executeQuery(" select * from userposttable where id="+id+";");
		         resultSet.next();
		         tempusername=resultSet.getString(2);
		         temppost=resultSet.getString(3);
                 System.out.println("username => "+tempusername);
                 System.out.println("--------------------------");
                 System.out.println("           "+temppost);
                 System.out.println("--------------------------");
		         resultSet=st.executeQuery(" select count(*) from commentstable where viewcomment is not null AND username=\""+tempusername+"\" AND post=\""+temppost+"\";");
		         resultSet.next();
		         int checkemptyornot=resultSet.getInt(1);
		        
		         while(true) 
		         {
		        	 if(checkemptyornot==0) 
			         {
			        	 System.out.println("   No Comments !!! ");
			        	 System.out.println();
			             break;
			         }
		        	 resultSet=st.executeQuery(" select* from commentstable where username=\""+tempusername+"\" AND post=\""+temppost+"\";");
		        	 while(resultSet.next()) 
		        	 {
		        	    System.out.println(resultSet.getString(4)+" Comment as this post => "+resultSet.getString(2));	 
		        	 }
		        	 
		        	 break;
		         }
		            
		         System.out.println(" If Add your comments press 1 or 0 to leave it  => ");
		         char choice=scanner.next().charAt(0);
		         if(choice=='1')
		         {     String comment=scanner.next();
		        	   if(username.equals(tempusername)) {
		        		   String tempname="*Owner"+username;
		        		   st.executeUpdate(" insert into commentstable (username,viewcomment,post,viewname) values(\""+tempusername+"\",\""+comment+"\",\""+temppost+"\",\""+tempname+"\");");
		        	       }
		        	   else
		            	   st.executeUpdate(" insert into commentstable (username,viewcomment,post,viewname) values(\""+tempusername+"\",\""+comment+"\",\""+temppost+"\",\""+username+"\");");
		        	  
		        	   System.out.println("                 Comment added  !!");
		         }
		            
	    	} 
		catch (Exception e) {
		      e.printStackTrace();
		}
		
		
	}

}
