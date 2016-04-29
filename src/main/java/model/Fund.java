package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Fund {
	private int id;
	private Date date;
	private double value;
	
	public Fund(int id, Date date, double value) {
		super();
		this.id = id;
		this.date = date;
		this.value = value;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	
	public String getParsedDate() {
		String dateString = new SimpleDateFormat("dd-MM-yyyy").format(date);
		return dateString;
		
	}
}
