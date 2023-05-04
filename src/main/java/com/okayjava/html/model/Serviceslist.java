package com.okayjava.html.model;

public class Serviceslist {

	public String service_head;
	
	public String getService_head() {
		return service_head;
	}
	public void setService_head(String service_head) {
		this.service_head = service_head;
	}
	public Serviceslist(String service_head) {
	super();
		this.service_head = service_head;
	}
	
	@Override
	public String toString() {
		return "Serviceslist [service_head=" + service_head+"]";
	}
}
