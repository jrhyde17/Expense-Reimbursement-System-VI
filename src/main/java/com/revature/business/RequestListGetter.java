package com.revature.business;

import java.util.List;

import com.revature.model.Request;
import com.revature.repository.RepoImpl;

public class RequestListGetter {

	public static List<Request> getReqs(String user, String level, String status) {

		RepoImpl dao = new RepoImpl();
		
		if(status.equals("all")) {
			List<Request> reqs = dao.getPendingRequests(user);
			reqs.addAll(dao.getResolvedRequests(user));
			return reqs;
		}
		
		if(level.equals("employee") && status.equals("pending")) {
			return dao.getPendingRequests(user);
		}
		if(level.equals("employee") && status.equals("resolved")) {
			return dao.getResolvedRequests(user);
		}
		if(level.equals("manager") && status.equals("pending")) {
			return dao.getPendingRequestsAsManager(user);
		}
		if(level.equals("manager") && status.equals("resolved")) {
			return dao.getResolvedRequestsAsManager(user);
		}
		
		return null;
	}
	
}
