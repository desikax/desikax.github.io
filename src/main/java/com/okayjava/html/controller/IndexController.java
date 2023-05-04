package com.okayjava.html.controller;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.okayjava.html.model.*;


@Controller
public class IndexController {
	 @Autowired  
	DataSource ds;  
	ResultSet tempRes;
	String service_name;
	String service_nameupdate;
	String service_pknameupdate;
	String service_columnupdate;
	String service_head;
	String quesmark;
	String service_namedel;
	String service_columnupdatenew;
	String service_pkupdate;
	String subservice_name;
	String col_name[];
	String col_type[];
	int error=1;
	public static String[] arrOfStr,subarrOfStr ;
	
	
	@GetMapping("/")
	public String index() {
		return "index";
	}


@GetMapping("/delete")
public String delete()
{return "delete";}

@GetMapping("/update")
public String update()
{return "update";}
	
	@PostMapping("/register")
	public String userRegistration(@ModelAttribute User user, Model model) {
		System.out.println(user.toString());
		service_name= user.getMicroservice_name();
		service_head=user.getMicroservice_head();
		subservice_name=user.getMicroservice_multitable();
		model.addAttribute("microservice_head", user.getMicroservice_head());
		model.addAttribute("microservice_name", user.getMicroservice_name());
		model.addAttribute("microservice_columns", user.getMicroservice_columns());
		model.addAttribute("microservice_multitable", user.getMicroservice_multitable());
		model.addAttribute("microservice_multicolumns", user.getMicroservice_multicolumns());
		arrOfStr = user.getMicroservice_columns().split(":");
		subarrOfStr = user.getMicroservice_multicolumns().split(":");
		 createInfo(); 
		 postInfo();
		 creatingdb();
		 delgenericwebservice();
		 postInfosub();
		 creatingdbsub();
		 delgenericwebservice();
		 list_of_microservices();
		 return "index";
	}
	
	@PostMapping("/update")
	public String userupdation(@ModelAttribute update Update, Model model) {
		System.out.println(Update.toString());
		service_nameupdate= Update.getMicroservice_name();
		service_columnupdate=Update.getMicroservice_column();
		service_pkupdate=Update.getMicroservice_pk();
		service_pknameupdate=Update.getMicroservice_pkname();
		service_columnupdatenew=Update.getMicroservice_update();
		model.addAttribute("microservice_name", Update.getMicroservice_name());
		model.addAttribute("microservice_column", Update.getMicroservice_name());
		model.addAttribute("microservice_pkname", Update.getMicroservice_pkname());
		model.addAttribute("microservice_pk", Update.getMicroservice_name());
		model.addAttribute("microservice_update", Update.getMicroservice_name());
		updateInfo();
		return "read";
	}
	
	@PostMapping("/delete")
	public String deletion(@ModelAttribute delete Delete, Model model)
	{
		System.out.println(Delete.toString());
		service_namedel=Delete.getMicroservice_namedel();
		model.addAttribute("microservice_namedel",Delete.getMicroservice_namedel());
		deleteInfo();
		deleteInfolistofmicroservices();
		return "index";
	}
	public void list_of_microservices() {
		   int nosqlaffected=0;
	       try(Connection cn=ds.getConnection())
			{if(error==1)
			{
				PreparedStatement ps=cn.prepareStatement("INSERT INTO List_of_microservices VALUES(?,?)");

				 for (int j=0;j<1;j++)
				 {
					 ps.setString(j+1, service_head);
					 ps.setString(j+2, service_name);
				 }
					nosqlaffected = ps.executeUpdate();	
					if(subservice_name.equals("null"))
						;
					else {
						PreparedStatement ps1=cn.prepareStatement("INSERT INTO List_of_microservices VALUES(?,?)");

						 for (int j=0;j<1;j++)
						 {
							 ps1.setString(j+1, service_head);
							 ps1.setString(j+2, subservice_name);
						 }
							nosqlaffected = ps1.executeUpdate();	
					}
					
	    	    System.out.println("setted in db");

			}
			}
	      	catch( SQLException e) 
	   		{
	   			// TODO Auto-generated catch block
	   			e.printStackTrace();
	   			System.out.println(nosqlaffected);
	   		}
	}
	
	   public void createInfo() 
	   {
	       
	       int nosqlaffected=0;
					     try(Connection cn=ds.getConnection())
					     {PreparedStatement ps=cn.prepareStatement("CREATE TABLE IF NOT EXISTS  genericwebservices(COLUMN_NAME CHAR(50), COLUMN_TYPE CHAR(50))");
					     nosqlaffected = ps.executeUpdate();	}
					     catch(SQLException e)
					     {
					    	 e.printStackTrace();
					    	 System.out.println(nosqlaffected);
					     }
	   }
	   public ResponseEntity<String> postInfo(){
		   int nosqlaffected=0;
					       try(Connection cn=ds.getConnection())
							{
					    	   for(int i=0;i<arrOfStr.length;i+=2) {
								PreparedStatement ps=cn.prepareStatement("INSERT INTO genericwebservices VALUES(?,?)");
				
								 for (int j=0;j<1;j++)
								 {
									 ps.setString(j+1, arrOfStr[i]);
									 ps.setString(j+2, arrOfStr[i+1]);
								 }
									nosqlaffected = ps.executeUpdate();	
					    	   }
									
					    	    System.out.println("setted in db");

							}
					      	catch( SQLException e) 
					   		{
					   			// TODO Auto-generated catch block
					   			e.printStackTrace();
					   			System.out.println(nosqlaffected);
					   		}
	       
	      
	          String response = new String();
	   		  response = "request receivved";
	          return new ResponseEntity<String>(response, HttpStatus.OK);
	      }
	   
	   public ResponseEntity<String> updateInfo(){
			int nosqlaffected=0;
			   try(Connection cn=ds.getConnection())
			     {
				   
				   String set="UPDATE "+service_nameupdate+" SET "+service_columnupdate+ " = '"+ service_columnupdatenew+"' WHERE "+service_pknameupdate+" = '"+service_pkupdate+"'";
				   System.out.println(set);
				   PreparedStatement ps=cn.prepareStatement(set);
				   nosqlaffected = ps.executeUpdate();
			     }
			     
			     catch(SQLException e)
			     {
			    	 e.printStackTrace();
			    	 System.out.println(nosqlaffected);
			     }

			
			  String response = new String();
	 		  response = "Inserted in database";
	          return new ResponseEntity<String>(response, HttpStatus.OK);
		   }
	   
	   public ResponseEntity<String> deleteInfo(){
		   int nosqlaffected=0;
		   try(Connection cn=ds.getConnection())
		   {
			   String set="DROP TABLE "+service_namedel;
			   try {
				PreparedStatement ps=cn.prepareStatement(set);
				nosqlaffected=ps.executeUpdate();
			   	   } 
			   catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(nosqlaffected);
			}
		   } catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		   String response = new String();
	 		  response = "Inserted in database";
	          return new ResponseEntity<String>(response, HttpStatus.OK);
	   }
	   public ResponseEntity<String> deleteInfolistofmicroservices(){
		   int nosqlaffected=0;
		   try(Connection cn=ds.getConnection())
		   {
			   String set="DELETE FROM list_of_microservices WHERE servicename = '"+service_namedel+"'";
			   try {
				PreparedStatement ps=cn.prepareStatement(set);
				nosqlaffected=ps.executeUpdate();
			   	   } 
			   catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(nosqlaffected);
			}
		   } catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		   String response = new String();
	 		  response = "Inserted in database";
	          return new ResponseEntity<String>(response, HttpStatus.OK);
	   }
	   public void delgenericwebservice()
	   {
		   int nosqlaffected=0;
		     try(Connection cn=ds.getConnection())
		     {PreparedStatement ps=cn.prepareStatement("DROP TABLE genericwebservices");
		     nosqlaffected = ps.executeUpdate();
		     }
		     catch(SQLException e)
		     {
		    	 e.printStackTrace();
		    	 System.out.println(nosqlaffected);
		     }
	   }
	   
	   public ResponseEntity<String> postInfosub() 
	   {
	       
	       int nosqlaffected=0;
					     try(Connection cn=ds.getConnection())
					     {PreparedStatement ps=cn.prepareStatement("CREATE TABLE IF NOT EXISTS  genericwebservices(COLUMN_NAME CHAR(50), COLUMN_TYPE CHAR(50))");
					     nosqlaffected = ps.executeUpdate();	}
					     catch(SQLException e)
					     {
					    	 e.printStackTrace();
					     }
	     
	     
					       try(Connection cn=ds.getConnection())
							{
					    	   for(int i=0;i<subarrOfStr.length;i+=2) {
								PreparedStatement ps=cn.prepareStatement("INSERT INTO genericwebservices VALUES(?,?)");
				
								 for (int j=0;j<1;j++)
								 {
									 ps.setString(j+1, subarrOfStr[i]);
									 ps.setString(j+2, subarrOfStr[i+1]);
								 }
									nosqlaffected = ps.executeUpdate();	
					    	   }
									
					    	    System.out.println("setted in db");

							}
					      	catch( SQLException e) 
					   		{
					   			// TODO Auto-generated catch block
					   			e.printStackTrace();
					   		   System.out.println(nosqlaffected);
					   		}
	       
	       
	          String response = new String();
	   		  response = "request receivved";
	          return new ResponseEntity<String>(response, HttpStatus.OK);
	      }
	
		public void recevie_det() {
			String set;
			try(Connection cn=ds.getConnection()) 
			{
				 set="SELECT * FROM "+service_name;
			
			PreparedStatement ps=cn.prepareStatement(set);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) 
			{     int i=0;
			  System.out.println("Executed");
			  col_name[i]=rs.getString("column_name");
			  col_type[i]=rs.getString("column_type"); 
			  System.out.println("blue "+col_name[i]+"bird "+col_type[i]);
			}
			}
		
			 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 }
		}
			
			
		public  String creatingdb () 
		   {
			if(service_name.equals("null"))
				return "cant create";
			int nosqlaffected=0;
			   try(Connection cn=ds.getConnection())
			   
			     {String  subset = "(";
						 for (int i=0;i<arrOfStr.length;i+=2)
						 {
							 if(i!=0)
								 subset+=",";
							 subset+=arrOfStr[i+1]+" "+arrOfStr[i];
							 if(i==0)
								 subset+=" "+"PRIMARY KEY";
							 
						 }
						 
				   String set="CREATE TABLE IF NOT EXISTS " +service_name +subset+")";
				   PreparedStatement ps=cn.prepareStatement(set);
			  
			     nosqlaffected = ps.executeUpdate();	}
			     catch(SQLException e)
			     {
			    	 e.printStackTrace();
			    	 System.out.println(nosqlaffected);
			     }

			

			  String response = new String();
	 		  response = "created database";
	          return (response);
		   }
	   
		
		
		public  ResponseEntity<String> creatingdbsub () 
		   {
			int nosqlaffected=0;
			if(subservice_name.equals("null"))
				{return new ResponseEntity<String>("failed", HttpStatus.OK);}
			else {
			   try(Connection cn=ds.getConnection())
			   
			     {
				  
					   String  subset = "(";
						 for (int i=0;i<subarrOfStr.length;i+=2)
						 {
							 if(i!=0)
								 subset+=",";
							 subset+=subarrOfStr[i+1]+" "+subarrOfStr[i];
							 
							 
						 }
				System.out.println(subset);		 
				 String primaryset="PRIMARY KEY("+subarrOfStr[1]+")";
				 String foreignset="CONSTRAINT "+"CONS_fk  "+"FOREIGN KEY(" +subarrOfStr[3]+ ")"+" REFERENCES "+service_name+"("+arrOfStr[1]+")";
				 String set="CREATE TABLE IF NOT EXISTS " +subservice_name+subset+","+primaryset+","+ foreignset  +")";
				 System.out.println(set);
				   PreparedStatement ps=cn.prepareStatement(set);
			  
			     nosqlaffected = ps.executeUpdate();
			     error=1;
			     }	
			     catch(SQLException e)
			     {
			    	 e.printStackTrace();
			    	 System.out.println(nosqlaffected);
			    	 error=0;
			     }

			

			  String response = new String();
	 		  response = "created database";
	          return new ResponseEntity<String>(response, HttpStatus.OK);}
		   }
	   
		
		
}
