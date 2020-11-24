package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.repository.RepoImpl;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }
    
    protected boolean validSession = false;
    public boolean sessionIsValid() {
    	return validSession;
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("./index.html");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//confirm credentials
		RepoImpl dao = new RepoImpl();
		boolean credentialsValid = dao.validateCredentials(username, password);
		
		if(credentialsValid) {
			validSession = true;
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			session.setAttribute("isManager", dao.isManager(username));
			AjaxEndpoint.setLoginFailedFlag(false);
			response.sendRedirect("./views/home.html");
		} else {
			validSession = false;
			AjaxEndpoint.setLoginFailedFlag(true);
			response.sendRedirect("./index.html");
		}

	}

}
