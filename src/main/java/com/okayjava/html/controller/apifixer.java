package com.okayjava.html.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import com.okayjava.html.model.Serviceslist;

@Controller
public class apifixer {
	static String finalstring;
	@Autowired  
	 DataSource ds;  
	 ResultSet tempRes;
		String service_head = "";
		String returndet;
		String definedapi="localhost:8080/api:";
		String terminalservicedet;
	String[] arrayofservicename;
	String[] arrservicename;

	@PostMapping("/listservicereq")
	public String userRegistration(@ModelAttribute Serviceslist serviceslist, Model model) {
		model.addAttribute("service_head", serviceslist.getService_head());
arrayofservicename=serviceslist.getService_head().split(",");
terminalservicedet="";
for(int i=0;i<arrayofservicename.length;i++)
{
	System.out.println(arrayofservicename[i]);
	definedapi+=arrayofservicename[i];
	readmicroservice(arrayofservicename[i]);
}
System.out.println(terminalservicedet);
finalstring=terminalservicedet;
		 return "index";
	}
	
	
	public void readmicroservice(String servicename) {
		try(Connection cn=ds.getConnection()) {
			String set="SELECT * FROM "+servicename;
			PreparedStatement ps=cn.prepareStatement(set);
			ResultSet rs=ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			 returndet = "";
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
			terminalservicedet+="\n"+servicename+":"+"\n\n";
			terminalservicedet+=returndet;
			
			}
			 catch (SQLException e) {
			e.printStackTrace();
			
		}
	
	}
	



	

}