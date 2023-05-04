package com.okayjava.html.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class apiextractor {
	 @Autowired  
	 DataSource ds;  
	 ResultSet tempRes;
	String terminalservicedetonid;
	String terminal;
	String[] arrOfStr;
	int val;
	String returndet;
	String returndetonid;
	String finalstringonid=null;
	@GetMapping("/definedapi")
	public String apiextract()
	{
		return apifixer.finalstring;
	}
	@GetMapping("/getserviceonidapi")
	public String apiextractid()
	{
		return apigeneneration.finalstringid;
	}

	
	
	public String listofservicedecider(String head)
	{
		try(Connection cn=ds.getConnection()) {
			terminal=null;
			returndet=null;
			String set="SELECT * FROM list_of_microservices";
			PreparedStatement ps=cn.prepareStatement(set);
			ResultSet rs=ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			while (rs.next()) {
			    for (int i = 1; i <= columnsNumber; i++) {
			        String columnValue = rs.getString(i);
			        if(columnValue.equals(head)) {
			        	if(returndet==null)
			        		returndet=rs.getString(i+1)+" ";
			        	else
			        	returndet+=rs.getString(i+1)+" ";
		
			    }}
			}
			if(terminal==null)
				terminal=returndet;
			else
			terminal+=returndet;
			
			}
			 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	return terminal;
	}
	
	@GetMapping("/getserviceonpkey")
	public String apixtractoronkey(@RequestParam String head,@RequestParam String idval)
	{
	String tempo=listofservicedecider(head);
	System.out.println("**"+tempo+"**");
	arrOfStr=null;
	finalstringonid=null;
	arrOfStr = tempo.split(" ");
	for(int h=0;h<arrOfStr.length;h++)
	{
		System.out.println(arrOfStr[h]);
		String temp=detextractor(arrOfStr[h], idval);
		if(finalstringonid!=null)
		finalstringonid+=temp;
		else
			finalstringonid=temp;
	}
	return finalstringonid;
	}
	
	public String detextractor(String head, String idval)
	{
		try(Connection cn=ds.getConnection()) {
			terminalservicedetonid = null;
			String set="SELECT * FROM "+head;
			PreparedStatement ps=cn.prepareStatement(set);
			ResultSet rs=ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			val=0;
			returndetonid="";
			while (rs.next()) {
			    for (int i = 1; i <= columnsNumber; i++) {
			    	 String columnValue = rs.getString(i);
			        if(columnValue.equals(idval)) 
			        {
			        	for (int j = 1; j <= columnsNumber; j++) {
			        if(columnsNumber==j & columnsNumber!=1)
			        	returndetonid+=rsmd.getColumnName(j)+": "+ rs.getString(j)+"\n";
			        else
			        returndetonid+=rsmd.getColumnName(j)+": "+rs.getString(j)+"| ";}
			        	val++;
			        }
				}
			}
			if(val!=0) {
			if(terminalservicedetonid!=null) {
			terminalservicedetonid+=head+":";
			terminalservicedetonid+=returndetonid+"\n";}
			else
			{
				terminalservicedetonid=head+":";
				terminalservicedetonid+=returndetonid+"\n";	
			}
			
			}
		}
			 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return terminalservicedetonid;
	}
}
