package com.okayjava.html.model;

public class update {
	
	public String microservice_name;
	public String microservice_column;
	public String microservice_pkname;
	public String microservice_pk;
	public String microservice_update;
	public String getMicroservice_name() {
		return microservice_name;
	}
	public void setMicroservice_name(String microservice_name) {
		this.microservice_name = microservice_name;
	}
	public String getMicroservice_column() {
		return microservice_column;
	}
	public void setMicroservice_column(String microservice_column) {
		this.microservice_column = microservice_column;
	}
	public String getMicroservice_pkname() {
		return microservice_pkname;
	}
	public void setMicroservice_pkname(String microservice_pkname) {
		this.microservice_pkname = microservice_pkname;
	}
	public String getMicroservice_pk() {
		return microservice_pk;
	}
	public void setMicroservice_pk(String microservice_pk) {
		this.microservice_pk = microservice_pk;
	}
	public String getMicroservice_update() {
		return microservice_update;
	}
	public void setMicroservice_update(String microservice_update) {
		this.microservice_update = microservice_update;
	}
	public update(String microservice_name, String microservice_column, String microservice_pkname,
			String microservice_pk, String microservice_update) {
		super();
		this.microservice_name = microservice_name;
		this.microservice_column = microservice_column;
		this.microservice_pkname = microservice_pkname;
		this.microservice_pk = microservice_pk;
		this.microservice_update = microservice_update;
	}
	@Override
	public String toString() {
		return "update [microservice_name=" + microservice_name + ", microservice_column=" + microservice_column
				+ ", microservice_pkname=" + microservice_pkname + ", microservice_pk=" + microservice_pk
				+ ", microservice_update=" + microservice_update + "]";
	}
	
	
}
	
	