package com.okayjava.html.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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


import com.okayjava.html.model.insert;

@Controller
public class connectingbackendmain {

	 @Autowired  
	 DataSource ds;  
	 ResultSet tempRes;
	 String[] arrOfStr;
	 String service_name;
	 int sno;
	 String returndet="";
	 String quesmark;
	 int rowvalue,count=1;
		@GetMapping("/insert")
		public String welcome() {
			return "insert";
		}
		@GetMapping("/create")
		public String create() {
			return "create";
		}
		@PostMapping("/registerinput")
		public String userRegistration(@ModelAttribute insert Insert, Model model) {
			System.out.println(Insert.toString());
			model.addAttribute("insert_name", Insert.getInsert_name());
			model.addAttribute("noofrows", Insert.getNoofrows());
			model.addAttribute("insert_rows", Insert.getInsert_rows());
			arrOfStr = Insert.getInsert_rows().split(":");
			sno= Insert.getNoofrows();
			//System.out.println(Insert.getInsert_name()+"   "+Insert.getInsert_rows());
			service_name=Insert.getInsert_name();
			rowvalue=arrOfStr.length/sno;
			System.out.println("rowvalue="+rowvalue);
			insertingdb();
			getdetmicroservice();

			
			if(Insert.yesno.equals("yes"))
			 return "insert";
			else 
			{
			Insert.yesno=returndet;
		
			return "index";
			}
		}
		
		public  ResponseEntity<String> insertingdb () 
		   {
			int nosqlaffected=0;
			   try(Connection cn=ds.getConnection())
			     {
				   quesmark="?";
				   for(int k=0;k<rowvalue-1;k++)
				   {quesmark+= ",?";
				   }
				   
				   System.out.println("quesmark="+quesmark);
			       for(int i=0;i<sno;i++)
			       {	
			       String type;
			       int x=0;
			       count=1;
				   String set="INSERT INTO "+service_name+" VALUES"+"(" +quesmark+ ")";
				   System.out.println(set);
				   PreparedStatement ps=cn.prepareStatement(set);
				   
				   for(int j=i*rowvalue;j<rowvalue*(i+1);j++)
					 { 
					   try {
						   x=Integer.parseInt(arrOfStr[j]); type="Integer"; 
					   }
					   catch(Exception e) {
						   type="String";
					   }
	
					 if(type.equals("Integer"))
					 {
						 ps.setInt(count, Integer.parseInt(arrOfStr[j]));
						 System.out.println(count+type+ " "+Integer.parseInt(arrOfStr[j]));
						 count++;
					 }
					 else if(type.equals("String"))
					 {
						 System.out.println(count+type+" "+arrOfStr[j]);
						 ps.setString(count, arrOfStr[j]);count++;
					 }
					     }
				   nosqlaffected = ps.executeUpdate();
				   System.out.println(x);
			     }
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
		
		@PostMapping("/getdetails")
		public String getdetmicroservice() {
			System.out.println("result set:") ; 
			try(Connection cn=ds.getConnection()) {
				String set="SELECT * FROM "+"phone_no";
				PreparedStatement ps=cn.prepareStatement(set);
				ResultSet rs=ps.executeQuery();
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnsNumber = rsmd.getColumnCount();
				returndet="";
				while (rs.next()) {
				    for (int i = 1; i <= columnsNumber; i++) {
				        String columnValue = rs.getString(i);
				        if(columnsNumber==i & columnsNumber!=1)
				        	returndet+=rsmd.getColumnName(i)+ " " + columnValue+".";
				        else
				        returndet+=rsmd.getColumnName(i)+ " " + columnValue +",  ";
				    }
				    System.out.println("");
				    returndet+= "\n";
				}
				System.out.println(returndet);
				
				}
				 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			return "returndetails";
		
		}
		



}