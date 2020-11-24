package com.revature.business;

import com.revature.repository.RepoImpl;

public class Credentials {

	public static boolean areValid(String username, String password) {
		RepoImpl dao = new RepoImpl();
		return dao.validateCredentials(username, password);
	}
}
