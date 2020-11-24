package com.revature.repository;

import java.io.InputStream;
import java.util.List;

import com.revature.business.Employee;
import com.revature.model.Request;

public interface Repository {
	public boolean validateCredentials(String username, String password);
	public String getUserInfo(String username);
	public boolean updateUserInfo(String username, String info);
	public boolean isManager(String username);
	public boolean newRequest(String currentUser, float amount, String summary, InputStream fileContent);
	public List<Request> getPendingRequests(String user);
	public List<Request> getPendingRequestsAsManager(String manager);
	public List<Request> getResolvedRequests(String user);
	public List<Request> getResolvedRequestsAsManager(String manager);
	public boolean approveRequest(int id);
	public boolean denyRequest(int id);
	public List<Employee> getEmployees();
	public List<Employee> getEmployees(String manager);
}
