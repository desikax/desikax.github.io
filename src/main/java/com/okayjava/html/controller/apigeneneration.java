package com.okayjava.html.controller;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;


import com.okayjava.html.model.Serviceslist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class apigeneneration {
	 @Autowired  
	 DataSource ds;  
	 ResultSet tempRes;
		String service_head = "";
		String returndet;
		public String terminalservicedet;
		String returndetonid;
		public String terminalservicedetonid;
		static String finalstringid;
		List<String> arrayofservicename=new ArrayList<String>();  
		@GetMapping("/read")
		public String read()
		{return "read";}
		
		@PostMapping("/requiredservice")
		public String userRegistration(@ModelAttribute Serviceslist serviceslist, Model model) {
			arrayofservicename.clear();
			service_head=serviceslist.getService_head();
			model.addAttribute("service_head", serviceslist.getService_head());
			terminalservicedet="";
			readservicehead();
			model.addAttribute("service_name", "");
			model.addAttribute("service_name", arrayofservicename);
			//subarrOfStr = servicelist.getMicroservice_multicolumns().split(":");
System.out.println(service_head);
System.out.println(terminalservicedet);
			 return "listofservice";
		}

		public String readservicehead()
		{
			terminalservicedet="\n";
			 try(Connection cn=ds.getConnection()) 
			 {
					String set="SELECT * FROM list_of_microservices ";
					java.sql.Statement stmt = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,       ResultSet.CONCUR_UPDATABLE);
					ResultSet rs=stmt.executeQuery(set);



					int i=1;
					while(rs.next())
					{
						rs.absolute(i);
						if((rs.getString(1).equals(service_head)))
						{
						arrayofservicename.add(rs.getString(2)) ;
						}
						i++;
						
					}
					} 
			 
			 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			 for(String k:arrayofservicename)
			 {
				 readmicroservice(k);
			 }
			 
			 
			 return "/requiredservice";
		}
		
		@GetMapping("/getserviceonid")
		public String apiextractonid( @RequestParam String name) {
			terminalservicedet="";
			finalstringid="";
			readmicroservice(name);
			System.out.println(terminalservicedet);
			finalstringid=terminalservicedet;
			return "welcome";
		}
		
		
		
		public void readmicroservice(String servicename) {
			try(Connection cn=ds.getConnection()) {
				terminalservicedet+="\n";
				String set="SELECT * FROM "+servicename;
				PreparedStatement ps=cn.prepareStatement(set);
				ResultSet rs=ps.executeQuery();
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnsNumber = rsmd.getColumnCount();
				returndet="";
				while (rs.next()) {
				    for (int i = 1; i <= columnsNumber; i++) {
				        String columnValue = rs.getString(i);
				        if(columnsNumber==i & columnsNumber!=1)
				        	returndet+=rsmd.getColumnName(i)+": "+ columnValue;
				        else
				        returndet+=rsmd.getColumnName(i)+": "+ columnValue +"| ";
				    }
				    returndet+= "\n";
				}
				terminalservicedet+=servicename+":"+"\n";
				terminalservicedet+=returndet;
				
				}
				 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		
		}
		
		public void readmicroserviceonkeyvalue(String serviceheaddet, String serviceidname) {
			try(Connection cn=ds.getConnection()) {
				terminalservicedetonid+="\n";
				String set="SELECT * FROM "+serviceheaddet;
				PreparedStatement ps=cn.prepareStatement(set);
				ResultSet rs=ps.executeQuery();
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnsNumber = rsmd.getColumnCount();
				returndetonid="";
				while (rs.next()) {
				    for (int i = 1; i <= columnsNumber; i++) {
				        String columnValue = rs.getString(i);
				        if(columnValue.equals(serviceidname)) 
				        {
				        if(columnsNumber==i & columnsNumber!=1)
				        	returndetonid+=rsmd.getColumnName(i)+": "+ columnValue;
				        else
				        returndetonid+=rsmd.getColumnName(i)+": "+ columnValue +"| ";
				        }
				    returndetonid+= "\n";
					}
				}
				terminalservicedetonid+=serviceheaddet+":"+"\n";
				terminalservicedetonid+=returndetonid;
				
				}
				 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		
		}

}
