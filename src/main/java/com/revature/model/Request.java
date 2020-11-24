package com.revature.model;

import java.io.File;
import java.io.InputStream;

public class Request {
	private int id;
	private String employee;
	private float amount;
	private String summary;
	private File file;
	private InputStream fileContent;
	private String status;
	private String manager;
	
	public Request() {}
	
	//old: inputstream
	public Request(int id, String employee, float amount, String summary, InputStream fileContent, String status, String manager) {
		setId(id);
		setEmployee(employee);
		setAmount(amount);
		setSummary(summary);
		setFileContent(fileContent);
		setStatus(status);
		setManager(manager);
	}
	
	//new: file
	public Request(int id, String employee, float amount, String summary, File file, String status, String manager) {
		setId(id);
		setEmployee(employee);
		setAmount(amount);
		setSummary(summary);
		setFile(file);
		setStatus(status);
		setManager(manager);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmployee() {
		return employee;
	}
	public void setEmployee(String employee) {
		this.employee = employee;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public InputStream getFileContent() {
		return fileContent;
	}
	public void setFileContent(InputStream fileContent) {
		this.fileContent = fileContent;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
}
