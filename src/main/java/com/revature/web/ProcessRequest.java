package com.revature.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.repository.RepoImpl;

/**
 * Servlet implementation class ProcessRequest
 */
public class ProcessRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProcessRequest() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		int id = Integer.parseInt(request.getParameter("id"));
		RepoImpl dao = new RepoImpl();
		
		switch(action) {
		case "approve":
			dao.approveRequest(id);
			break;
		case "deny":
			dao.denyRequest(id);
			break;
		default:
		}
		
		response.sendRedirect("./views/manager/manager-tools.html");
	}

}
