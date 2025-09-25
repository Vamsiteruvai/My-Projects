package com.model;

import java.sql.Date;
import java.sql.Timestamp;

public class ReportModel {
	private int report_id;
	private int patient_id;
	private String name;
	private String phone;
	private String report;
	private Timestamp date_time;
	public ReportModel(int report_id, int patient_id, String name, String phone, String report, Timestamp date_time) {
		super();
		this.report_id = report_id;
		this.patient_id = patient_id;
		this.name = name;
		this.phone = phone;
		this.report = report;
		this.date_time = date_time;
	}
	public int getReport_id() {
		return report_id;
	}
	public void setReport_id(int report_id) {
		this.report_id = report_id;
	}
	public int getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
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
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	public Timestamp getDate_time() {
		return date_time;
	}
	public void setDate_time(Timestamp date_time) {
		this.date_time = date_time;
	}
	
}
