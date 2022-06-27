package facebook;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class FBLoginPage {
  
	static int userId=1;
         	
	
	public void loginPage() throws SQLException 
	{    Connection con=FBHomePage.con;
		Scanner scanner=new Scanner(System.in);
        FBLoginPage fbLoginPage=new FBLoginPage();		
	     boolean flag=true;
		while(flag) 
		{  System.out.println("----------------------------------------------------------");
		   System.out.println(" 1. login Account \n 2. create Account  \n 3. Exit ");
		   System.out.println("----------------------------------------------------------");
		   char choice=scanner.next().charAt(0);
		   if(Character.getNumericValue(choice)<=3 && Character.getNumericValue(choice)>=1);
		   else
		   {
			   flag=true;
			   System.out.println("                 *Enter Correct Input               ");
			   System.out.println();
			   continue;
		   }
		   
		   switch (choice) {
		   case '1': {
                fbLoginPage.sub_loginpage();                    			
			   break;
		     }
		   case '2':{
			   fbLoginPage.accountCreationpage(con); 
			   break;
		   }
		   case '3':{
	           System.out.println("                           [Exit Page]  ");   
			   System.exit(0);
			   break;
		   }
		  }
		   
		   
		}
	}
	
	
	private void sub_loginpage() {
        boolean flag=true;
        int leaveSubLoginPage=3;
		while(flag) 
		{      Statement st;
			   ResultSet rs;
               leaveSubLoginPage--;
			   try {
				System.out.println( "                           [Login_page]                         ");
				System.out.println("|----------------------------------------------------------|");
				Scanner scanner=new Scanner(System.in);
				System.out.println("Enter Name : ");
				String name=scanner.next();
//		        System.out.println("Enter password : ");
//				String pasword=scanner.next();
				FBHomePage ob=new FBHomePage();
			    st=ob.con.createStatement();
			    rs=st.executeQuery("select count(*) from logindetails where name=\""+name+"\";");
			    rs.next();
			    int userNameFound=rs.getInt(1);
			    if(userNameFound!=1)
			    {      System.out.println("                                     [ Cannot found User ]  \n");
			           new FBLoginPage().loginPage();
			    }
			    else if(userNameFound==1) 
			    {
			      rs=st.executeQuery("select password from logindetails where name=\""+name+"\";");	
			      rs.next();
			      String pass=rs.getString(1);
			      System.out.println("Enter password : ");
				  String pasword=scanner.next();
			      if(pass.equals(pasword)) 
			      { //pass user name
                    // System.out.println("Login....");
			    	//FBOPTION(); 
			         new FBUserInteractivePage().fbUserHomePage(name);
			        break;
			      }
			      else 
			      {   
			    	     if(leaveSubLoginPage==0) 
			    	     {
			    	         System.out.println("                                 [Many more Attempt]         ");
			    	    	 break;
			    	     }
			    	System.out.println(" check User name And Password ! try again "+leaveSubLoginPage);
			    	continue;
			      }
			    }
			} catch (Exception e) {
			    System.out.println(e);	
			}
			
		}
		
		
		
        
		
	}


	public void accountCreationpage(Connection con) throws SQLException {

		Statement st=con.createStatement();
		ResultSet rs;
	    rs=st.executeQuery("select count(distinct id) from logindetails");
		rs.next();
	    userId=rs.getInt(1);
	    System.out.println(userId);
		userId++;
		System.out.println();
	    Scanner scanner=new Scanner(System.in);
        boolean flag=true;
        int leaveAccountCreationpage=0;
        
        while(flag) 
        {
        	System.out.println("");
        	System.out.println("<----------------[Create_Account_Page]------------------->");
    		System.out.println("");
    	    System.out.println("Enter user name : ");
    	    String name =scanner.next();
    	 
            	    
//    	    rs=st.executeQuery("select count(distinct name) from logindetails;");
//    	    if(rs.getShort(1)==0)
            
    	    rs=st.executeQuery("select count(distinct name) from logindetails where name=\""+name+"\";"); //name is unique
    	    rs.next();
    	    System.out.println();
    	   
    	     if(leaveAccountCreationpage==2)
    	    {   System.out.println("                             Many  more Attempt try again !!");	
    	        System.out.println();
    	        break;
    	    }
    	    
    	    if(rs.getInt(1)==1)
            {   System.out.println("             username already exist !!          ");
                System.out.println();
                flag=true;
                leaveAccountCreationpage++;
                continue;
            }
    	    else 
    	    {       System.out.println(" Enter password : ");
    	            String password=scanner.next();    
       	         st.executeUpdate("insert into logindetails (id,name,password) values ("+userId+",\""+name+"\",\""+password+"\");");	
    	         System.out.println("           Successfully user Account Created ....         ");
                    
    	         FBLoginPage fbLoginPage=new FBLoginPage();
    	         fbLoginPage.addUserDetails(name);
    	         
    	         //write your code  :)  
    	         flag=false;    
    	    }
	
    	     System.out.println();
        	
        }
	}


	private void addUserDetails(String username){
		Scanner scanner=new Scanner(System.in);
		//must be handle Exception
		FBHomePage fbHomePage=new FBHomePage();
		Connection con=fbHomePage.con;
		Statement st=fbHomePage.st;
		
		    System.out.println("|----------------------------------------------------------|");
			System.out.println("                       Add Pesonal Details                 ");
			System.out.println();
		   
		    System.out.println("Enter Name         : ");
		    String name=scanner.nextLine();
		    scanner.nextLine();
		    String age="";
		    while(true) {
		    System.out.println("Enter Age          : ");
		    age=scanner.next();
		      if(Integer.parseInt(age)>0) 
		      {
		    	  break;
		      }
		      else 
		      {
		    	  System.out.println("  *Enter Correct input like Number  ");
		    	  System.out.println();
		      }
		    }
		    System.out.println("Enter MobileNumber : ");
		    String mobileno=scanner.next();
		    String gender="";
		    while(true) {
		    System.out.println("Enter Gender       : ");
		    gender=scanner.next().toLowerCase();
		      if(gender.equals("male")) {gender="M"; break;}
		      else if(gender.equals("female")) { gender="F";break;}
		      else System.out.println(" *Enter Correctly in Gender flied  ");
		     }         
		    
		    System.out.println(" Enter Quotes      : ");
		    String quotes=scanner.nextLine();
	        
	        try {
	        	st.executeUpdate(" insert into personaldetails (UniqueName,username,Age,Gender,MobileNO,Quotes) values\r\n"
		        		+ " (\""+username+"\",\""+name+"\","+age+",\""+gender+"\","+mobileno+",\""+quotes+"\");");    	
	        	System.out.print(  "                  Pesronal details added successfully...    ");
	        	System.out.println("|----------------------------------------------------------|");
	        } catch (Exception e) {
		         e.printStackTrace();
			}
		    
		
		
		
		
	    
	    
	
	}

}
