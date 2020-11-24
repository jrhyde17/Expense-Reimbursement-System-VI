package com.revature.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.revature.business.Employee;
import com.revature.repository.RepoImpl;

/**
 * Servlet implementation class ViewEmployees
 */
public class ViewEmployees extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ViewEmployees() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RepoImpl dao = new RepoImpl();
		List<Employee> employees = dao.getEmployees();

		String json = new Gson().toJson(employees);
		response.setContentType("application/json");
		response.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String single = request.getParameter("single");

		RepoImpl dao = new RepoImpl();
		List<Employee> employees = new ArrayList<>();
		if(single.equals("true")) {
			employees = dao.getEmployees((String)request.getSession().getAttribute("username"));
		} else {
			employees = dao.getEmployees();
		}

		String json = new Gson().toJson(employees);
		response.setContentType("application/json");
		response.getWriter().write(json);
	}

}
