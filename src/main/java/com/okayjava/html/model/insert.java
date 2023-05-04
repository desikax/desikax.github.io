package com.okayjava.html.model;

public class insert {
	public int noofrows;
	public String insert_name;
	public String insert_rows;
	public String yesno;
	public String getYesno() {
		return yesno;
	}
	public void setYesno(String yesno) {
		this.yesno = yesno;
	}
	public String getInsert_rows() {
		return insert_rows;
	}
	public void setInsert_rows(String insert_rows) {
		this.insert_rows = insert_rows;
	}
	public String getInsert_name() {
		return insert_name;
	}
	
	public int getNoofrows() {
		return noofrows;
	}
	public void setNoofrows(int noofrows) {
		this.noofrows = noofrows;
	}
	public void setInsert_name(String insert_name) {
		this.insert_name = insert_name;
	}
	public insert(int noofrows, String insert_name, String insert_rows, String yesno) {
		super();
		this.noofrows = noofrows;
		this.insert_name = insert_name;
		this.insert_rows = insert_rows;
		this.yesno = yesno;
	}
	@Override
	public String toString() {
		return "insert [noofrows=" + noofrows + ", insert_name=" + insert_name + ", insert_rows=" + insert_rows
				+ ", yesno=" + yesno + "]";
	}

}


