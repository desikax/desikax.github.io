package com.okayjava.html.model;

public class User {
	
	public String microservice_head;
	public String microservice_name;
	public String microservice_columns;
	public String microservice_multitable;
	public String microservice_multicolumns;
	public String getMicroservice_multitable() {
		return microservice_multitable;
	}
	public void setMicroservice_multitable(String microservice_multitable) {
		this.microservice_multitable = microservice_multitable;
	}
	public String getMicroservice_multicolumns() {
		return microservice_multicolumns;
	}
	public void setMicroservice_multicolumns(String microservice_multicolumns) {
		this.microservice_multicolumns = microservice_multicolumns;
	}
	public String getMicroservice_head() {
		return microservice_head;
	}
	public void setMicroservice_head(String microservice_head) {
		this.microservice_head = microservice_head;
	}
	public String getMicroservice_name() {
		return microservice_name;
	}
	public void setMicroservice_name(String microservice_name) {
		this.microservice_name = microservice_name;
	}
	public String getMicroservice_columns() {
		return microservice_columns;
	}
	public void setMicroservice_columns(String microservice_columns) {
		this.microservice_columns = microservice_columns;
	}
	public User(String microservice_head, String microservice_name, String microservice_columns,
			String microservice_multitable, String microservice_multicolumns) {
		super();
		this.microservice_head = microservice_head;
		this.microservice_name = microservice_name;
		this.microservice_columns = microservice_columns;
		this.microservice_multitable = microservice_multitable;
		this.microservice_multicolumns = microservice_multicolumns;
	}
	@Override
	public String toString() {
		return "User [microservice_head=" + microservice_head + ", microservice_name=" + microservice_name
				+ ", microservice_columns=" + microservice_columns + ", microservice_multitable="
				+ microservice_multitable + ", microservice_multicolumns=" + microservice_multicolumns + "]";
	}
	
	}

	
	
