package com.revature.repository;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.business.Employee;
import com.revature.model.Request;
import com.revature.utility.ConnectionFactory;

public class RepoImpl implements Repository {

	@Override
	public boolean validateCredentials(String username, String password) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		final String SQL = "select * from employees where username = ? and password = ?";
		
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, username);
			stmt.setString(2, password);
			rset = stmt.executeQuery();
			if(rset.next()) {
				return true;
			}			
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	@Override
	public String getUserInfo(String username) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		final String SQL = "select info from employees where username = ?";

		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, username);
			rset = stmt.executeQuery();
			if(rset.next()) {
				String info = rset.getString(1);
				if(info == null) {
					return "[information not set]";
				}
				else {
					return rset.getString(1);					
				}
			}			
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return "";
	}

	@Override
	public boolean updateUserInfo(String username, String info) {
		Connection conn = null;
		PreparedStatement stmt = null;
		final String SQL = "update employees set info = ? where username = ?";
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(2, username);
			stmt.setString(1, info);
			if(stmt.executeUpdate() > 0) {
				return true;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	@Override
	public boolean isManager(String username) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		final String SQL = "select * from managers where manager = ?";
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, username);
			rset = stmt.executeQuery();
			if(rset.next()) {
				return true;
			}			
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean newRequest(String currentUser, float amount, String summary, InputStream fileContent) {
		Connection conn = null;
		PreparedStatement stmt = null;
		final String SQL = "insert into requests values(default,?,?,?,?,'pending')";
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, currentUser);
			stmt.setFloat(2, amount);
			stmt.setString(3, summary);
			stmt.setBinaryStream(4,fileContent);
			if(stmt.executeUpdate() > 0) {
				return true;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public List<Request> getPendingRequests(String user) {
		List<Request> reqs = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		final String SQL = "select id, amount, description, imagedata, status from requests where employee = ? and status = 'pending' order by id";
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, user);
			rset = stmt.executeQuery();
			while(rset.next()) {
				Request req = new Request();
				req.setId(rset.getInt(1));
				req.setEmployee(user);
				req.setAmount(rset.getFloat(2));
				req.setSummary(rset.getString(3));
				req.setFileContent(rset.getBinaryStream(4));
				req.setStatus(rset.getString(5));
				reqs.add(req);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return reqs;
	}
	
	@Override
	public List<Request> getPendingRequestsAsManager(String manager) {
		List<Request> reqs = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		final String SQL = "select id, requests.employee, amount, description, imagedata, status from requests " +
						   "inner join managers on requests.employee = managers.employee " +
						   "where manager = ? and status = 'pending' order by id";
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, manager);
			rset = stmt.executeQuery();
			while(rset.next()) {
				Request req = new Request();
				req.setId(rset.getInt(1));
				req.setEmployee(rset.getString(2));
				req.setAmount(rset.getFloat(3));
				req.setSummary(rset.getString(4));
				req.setFileContent(rset.getBinaryStream(5));
				req.setStatus(rset.getString(6));
				reqs.add(req);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return reqs;
	}

	@Override
	public List<Request> getResolvedRequests(String user) {
		List<Request> reqs = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		final String SQL = "select id, amount, description, imagedata, status from requests where employee = ? and status != 'pending' order by id";
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, user);
			rset = stmt.executeQuery();
			while(rset.next()) {
				Request req = new Request();
				req.setId(rset.getInt(1));
				req.setEmployee(user);
				req.setAmount(rset.getFloat(2));
				req.setSummary(rset.getString(3));
				req.setFileContent(rset.getBinaryStream(4));
				req.setStatus(rset.getString(5));
				reqs.add(req);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return reqs;
	}
	
	@Override
	public List<Request> getResolvedRequestsAsManager(String manager) {
		List<Request> reqs = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		final String SQL = "select id, requests.employee, amount, description, imagedata, status, managers.manager from requests " +
						   "inner join managers on requests.employee = managers.employee " +
						   "where status != 'pending' order by id";
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			rset = stmt.executeQuery();
			while(rset.next()) {
				Request req = new Request();
				req.setId(rset.getInt(1));
				req.setEmployee(rset.getString(2));
				req.setAmount(rset.getFloat(3));
				req.setSummary(rset.getString(4));
				req.setFileContent(rset.getBinaryStream(5));
				req.setStatus(rset.getString(6));
				req.setManager(rset.getString(7));
				reqs.add(req);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return reqs;
	}
	
	@Override
	public boolean approveRequest(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		final String SQL = "update requests set status = 'approved' where id = ?";
		
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, id);
			if(stmt.executeUpdate() > 0) {
				return true;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	@Override
	public boolean denyRequest(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		final String SQL = "update requests set status = 'denied' where id = ?";
		
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, id);
			if(stmt.executeUpdate() > 0) {
				return true;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public List<Employee> getEmployees() {
		List<Employee> employees = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		final String SQL = "select * from managers order by manager, employee";
		
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			rset = stmt.executeQuery();
			while(rset.next()) {
				Employee emp = new Employee();
				emp.setUsername(rset.getString(1));
				emp.setManager(rset.getString(2));
				employees.add(emp);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return employees;
	}

	@Override
	public List<Employee> getEmployees(String manager) {
		List<Employee> employees = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		final String SQL = "select * from managers where manager = ? order by manager, employee";
		
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1,manager);
			rset = stmt.executeQuery();
			while(rset.next()) {
				Employee emp = new Employee();
				emp.setUsername(rset.getString(1));
				emp.setManager(rset.getString(2));
				employees.add(emp);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return employees;
	}


}
