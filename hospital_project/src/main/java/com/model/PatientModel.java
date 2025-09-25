package com.model;

import java.sql.Date;

public class PatientModel {
	private int patient_id;
	private String fullname;
	private String email;
	private String phone;
	private Date preferred_date;
	private String reason;
	private String doctor_name;
	private int age;
	private String address;
	
	public PatientModel(int patient_id, String fullname, String email, String phone, Date preferred_date, String reason,
			String doctor_name, int age, String address) {
		super();
		this.patient_id = patient_id;
		this.fullname = fullname;
		this.email = email;
		this.phone = phone;
		this.preferred_date = preferred_date;
		this.reason = reason;
		this.doctor_name = doctor_name;
		this.age = age;
		this.address = address;
	}

	public int getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getPreferred_date() {
		return preferred_date;
	}

	public void setPreferred_date(Date preferred_date) {
		this.preferred_date = preferred_date;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getDoctor_name() {
		return doctor_name;
	}

	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
