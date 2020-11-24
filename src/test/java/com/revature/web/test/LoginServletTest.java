package com.revature.web.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.revature.repository.RepoImpl;
import com.revature.web.LoginServlet;

public class LoginServletTest {
	
	@Mock
	private HttpServletRequest request;
	
	@Mock
	private HttpServletResponse response;
	
	@Mock
	private RepoImpl dao;
	
	@InjectMocks
	private LoginServlet login;
	
	@Before
	public void before() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testValidLogin() {
		Mockito.when(request.getParameter("username")).thenReturn("name");
		Mockito.when(request.getParameter("password")).thenReturn("pass");
		Mockito.when(dao.validateCredentials("name", "pass")).thenReturn(true);
		
		try {
			login.doPost(request, response);
			
			//TODO update assertion
			Assert.assertEquals(login.sessionIsValid(),true);
		} catch(IOException e) {
			e.printStackTrace();
			Assert.fail("IO Exception");
		} catch(ServletException e) {
			e.printStackTrace();
			Assert.fail("Servlet Exception");
		} catch(NullPointerException e) {}
		
	}

	@Test
	public void testInvalidUsername() {
		Mockito.when(request.getParameter("username")).thenReturn("name");
		Mockito.when(request.getParameter("password")).thenReturn("pass");
		Mockito.when(dao.validateCredentials("name", "pass")).thenReturn(false);
		
		try {
			login.doPost(request, response);

			//TODO update assertion
			Assert.assertEquals(login.sessionIsValid(),false);
		} catch(IOException e) {
			e.printStackTrace();
			Assert.fail("IO Exception");
		} catch(ServletException e) {
			e.printStackTrace();
			Assert.fail("Servlet Exception");
		} catch(NullPointerException e) {}
		
	}

	@Test
	public void testInvalidPassword() {
		Mockito.when(request.getParameter("username")).thenReturn("name");
		Mockito.when(request.getParameter("password")).thenReturn("pass");
		Mockito.when(dao.validateCredentials("name", "pass")).thenReturn(false);
		
		try {
			
			login.doPost(request, response);

			//TODO update assertion
			Assert.assertEquals(login.sessionIsValid(),false);
		} catch(IOException e) {
			e.printStackTrace();
			Assert.fail("IO Exception");
		} catch(ServletException e) {
			e.printStackTrace();
			Assert.fail("Servlet Exception");
		} catch(NullPointerException e) {}
		
	}

}
