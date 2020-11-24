package com.revature.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.revature.repository.RepoImpl;

/**
 * Servlet implementation class AjaxEndpoint
 */
public class AjaxEndpoint extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static boolean loginFailed;
	
	public static boolean getLoginFailedFlag() {
		return loginFailed;
	}
	public static boolean setLoginFailedFlag(boolean loginFailed) {
		AjaxEndpoint.loginFailed = loginFailed;
		return AjaxEndpoint.loginFailed;
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxEndpoint() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,Object> flags = new HashMap<>();
		flags.put("loginFailed", loginFailed);

		HttpSession session = request.getSession(false);
		if(session == null) {
			flags.put("managerTools", false);
			flags.put("info","");
		} else {
			flags.put("managerTools",(boolean)session.getAttribute("isManager"));
			
			RepoImpl dao = new RepoImpl();
			String info = dao.getUserInfo((String)session.getAttribute("username"));
			flags.put("info", info);
		}
		
		
		
		
		String json = new Gson().toJson(flags);
		response.setContentType("application/json");
		response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		switch(action) {
		case "flushLogin":
			setLoginFailedFlag(false);
			break;
		default:
		}
	}

}
