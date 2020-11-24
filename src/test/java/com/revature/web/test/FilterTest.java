package com.revature.web.test;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.revature.web.AuthenticationFilter;

public class FilterTest {
	
	@Mock
	private HttpServletRequest request;
	
	@Mock
	private HttpServletResponse response;
	
	@InjectMocks
	private AuthenticationFilter filter;

	@Test
	public void testAuthenticationFilter() {
		FilterChain chain = null;
		try {
			filter.doFilter(request, response, chain);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {}
		
		
		fail("Not yet implemented");
	}

}
