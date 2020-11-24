package com.revature.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.revature.business.RequestListGetter;
import com.revature.model.Request;

public class GetRequests extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetRequests() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().write("");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = null;
		String level = request.getParameter("level");
		String status = request.getParameter("status");
		
		if(status.equals("all")) {
			user = request.getParameter("employee");
		} else {
			HttpSession session = request.getSession();
			user = (String) session.getAttribute("username");
		}
		
		List<Request> reqs = RequestListGetter.getReqs(user, level, status);
		
		String json = new Gson().toJson(reqs);
		response.setContentType("application/json");
		response.getWriter().write(json);
	}

}
