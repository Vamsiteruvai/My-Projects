package com.model;

import java.sql.Date;

public class StaffModel {
	private int staff_id;
	private String name;
	private int age;
	private String address;
	private String phone;
	private String email;
	private String department;
	private String education;
	private Date joined_date;
	private String username;
	private String password;
	private String start_time;
	private String end_time;
	public StaffModel(int staff_id, String name, int age, String address, String phone, String email, String department,
			String education, Date joined_date, String username, String password,String start_time,String end_time) {
		super();
		this.staff_id = staff_id;
		this.name = name;
		this.age = age;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.department = department;
		this.education = education;
		this.joined_date = joined_date;
		this.username = username;
		this.password = password;
		this.start_time = start_time;
		this.end_time = end_time;
	}
	public int getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(int staff_id) {
		this.staff_id = staff_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getDesignation() {
		return department;
	}
	public void setDesignation(String department) {
		this.department = department;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public Date getJoined_date() {
		return joined_date;
	}
	public void setJoined_date(Date joined_date) {
		this.joined_date = joined_date;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
}
