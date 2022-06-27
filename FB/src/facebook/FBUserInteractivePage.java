package facebook;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class FBUserInteractivePage {

	public void fbUserHomePage(String username) {

		Scanner scanner=new Scanner(System.in);
        int logout=0;

        while(true) 
		{   System.out.println("   Fb user Home page ");
			System.out.println();
            System.out.println(" 1. view your Profile \n 2. post \n 3.view own post's  \n 4. Message \n 5.Logout");
            char choice=scanner.next().charAt(0);
            switch (choice) {
			   case '1': {
                new FBUserInteractivePage().viewProfile(username);				
			   break;	
			   }
			   case '2': {
				   new FBUserInteractivePage().post(username);	
				   break;	
				}
			   case '3': {
				   new FBUserInteractivePage().viewOwnPost(username);	
				   break;	
			   }
			   case '4':{
				   break;
			   }
			   case '5': {
					System.out.println();
				    System.out.println("                          LogOut                      ");
			        logout=1;
				    break;		
			   }
			
			}
            System.out.println();
            if(logout==1)
            	break;
		}
		
		
		
		
		
		
		
	}

	private void viewOwnPost(String username) {
		Connection connection=FBHomePage.con;
	    Statement statement=FBHomePage.st;
	    ResultSet resultSet;
		Scanner scanner=new Scanner(System.in);
		ArrayList<Integer> tempIdstore=new ArrayList<Integer>();
        while(true) 
        {
        	try {
    			System.out.println("Hi "+username);
    	        System.out.println("-------------------------------your post in below--------------------------------");		
    	        resultSet =statement.executeQuery(" select * from userposttable where username=\""+username+"\" ;");	    
    	        System.out.println("ID              |               UserName             |          POST      ");
    	        while(resultSet.next()) 
    	        {
    	          System.out.println(resultSet.getString(1)+"\t\t| "+resultSet.getString(2)+"\t\t | "+resultSet.getString(3));	
    	          tempIdstore.add(resultSet.getInt(1));
    	        }
    	        
    	        while(true) 
    	        {
    	        	System.out.println(" 1. view Comment 2.Back");
              	     char choice=scanner.next().charAt(0);    
          	        
              	   if(choice=='1') {
                	    
              		       System.out.println("View Comment Enter ID : ");
            	           int Id=scanner.nextInt();
            	         if(tempIdstore.contains(Id)) 
            	         {
            	        	   resultSet =statement.executeQuery(" select * from userposttable where id="+Id+";");
                	           resultSet.next();
                	           String tempname=resultSet.getString(2);
                	           String temppost=resultSet.getString(3);
                	           resultSet =statement.executeQuery("select* from commentstable where username=\""+tempname+"\" AND post=\""+temppost+"\";");
                    	       System.out.println(" ---------------------Comments--------------------");
              	              while(resultSet.next()) 
              	              {      System.out.println();
              	        	         System.out.println(resultSet.getString(2)+" Comment by "+resultSet.getString(4));    
              	              }	 
            	         }
            	         else
            	        	 System.out.println(" *Enter Correct ID ");
            	     }
              	     else if(choice=='2') 
              	     {
              	       break;    	 
              	     }
              	     else
              	    	 System.out.println(" *Enter correct option !!");
       	         }
        	   break;    
        	} catch (Exception e) {
    		     e.printStackTrace();
    		}
	
        }
			    	
	
	}

	private void post(String username) {
         Scanner scanner=new Scanner(System.in);
		int backToFBUserHomePage=0;
         while(true) 
		{
			  System.out.println(" 1.create Post \n 2.View Post \n  3.Back ");
	            char choice=scanner.next().charAt(0);
	            switch (choice) {
				   case '1': {
		                  new FbUserPost().createPost(username);
					   break;	
				   }
				   case '2': {
					   new FbUserPost().viewPost(username);
					   break;	
					}
				   case '3': {
					   backToFBUserHomePage=1;			   	
					   break;	
				   }
	            }
	            
	            System.out.println();
	            if(backToFBUserHomePage==1)
	            	break;

		}
		 
	}

	private void viewProfile(String username) {

		Connection con=FBHomePage.con;
	    Statement st=FBHomePage.st;
	    ResultSet rset;
		Scanner scanner=new Scanner(System.in);

	   while(true) 
       {
		
		  try {
			rset=st.executeQuery(" select * from personaldetails where UniqueName=\""+username+"\";");
			//rset.next();
			while(rset.next()) 
		    {
		         System.out.println("\t\t\t\t\t\t\t"+"\n Name : "+rset.getString(2)+"\t\t\t\t\t\t\t"+"\n Age : "+rset.getString(3)+"\t\t\t\t\t\t\t"+"\n Gender :"
		         +rset.getString(4)+"\n MobileNumber :"+rset.getString(5)+"\t\t\t\t\t\t\t"+"\n Quotes :"+rset.getString(6)+"\n");
                 
		    }	
			break;
	     } 
		 catch (Exception e) {
		           e.printStackTrace();
	      }
	    
	   }
		
		
		
	}

	
}
