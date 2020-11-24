package com.revature.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.repository.RepoImpl;

/**
 * Servlet implementation class EditInfoServlet
 */
public class EditInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("./views/edit-info.html");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("username");
		String newInfo = request.getParameter("newInfo");
		RepoImpl dao = new RepoImpl();
		
		dao.updateUserInfo(username, newInfo);
		// TODO let user know if update failed
		
		response.sendRedirect("./views/home.html");
	}

}
