package com.model;

import java.sql.Date;

public class BillModel {
	private int bill_id;
	private String name;
	private String phone;
	private String email;
	private String address;
	private String disease;
	private double total_amount;
	private Date bill_date;
	private int patient_id;
	public BillModel(int bill_id, String name, String phone, String email, String address, String disease,
			double total_amount, Date bill_date,int patient_id) {
		super();
		this.bill_id = bill_id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.disease = disease;
		this.total_amount = total_amount;
		this.bill_date = bill_date;
		this.patient_id=patient_id;
	}
	public int getBill_id() {
		return bill_id;
	}
	public void setBill_id(int bill_id) {
		this.bill_id = bill_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDisease() {
		return disease;
	}
	public void setDisease(String disease) {
		this.disease = disease;
	}
	public double getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}
	public Date getBill_date() {
		return bill_date;
	}
	public void setBill_date(Date bill_date) {
		this.bill_date = bill_date;
	}
	public int getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}
	
	
	
}
