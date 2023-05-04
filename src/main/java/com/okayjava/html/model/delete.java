package com.okayjava.html.model;

public class delete {
	public String microservice_namedel;

	public String getMicroservice_namedel() {
		return microservice_namedel;
	}

	public void setMicroservice_namedel(String microservice_namedel) {
		this.microservice_namedel = microservice_namedel;
	}

	public delete(String microservice_namedel) {
		super();
		this.microservice_namedel = microservice_namedel;
	}

	@Override
	public String toString() {
		return "delete [microservice_namedel=" + microservice_namedel + "]";
	}
	
}