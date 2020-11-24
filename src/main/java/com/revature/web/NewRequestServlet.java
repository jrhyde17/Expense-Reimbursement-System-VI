package com.revature.web;

import java.io.IOException;
import java.io.InputStream;
//import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.revature.repository.RepoImpl;

@MultipartConfig
public class NewRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewRequestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("./views/new-request.html");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		float amount = Float.parseFloat(request.getParameter("amount"));
		String summary = request.getParameter("summary");
		Part filePart = request.getPart("image");
		//String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
		InputStream fileContent = filePart.getInputStream();
		
		HttpSession session = request.getSession(false);
		String currentUser = (String)session.getAttribute("username");
		
		RepoImpl dao = new RepoImpl();
		boolean success = dao.newRequest(currentUser,amount,summary,fileContent);
		if(success) {
			response.sendRedirect("./views/home.html");
		} else {
			response.sendRedirect("./views/new-request.html");
		}
	}

}
