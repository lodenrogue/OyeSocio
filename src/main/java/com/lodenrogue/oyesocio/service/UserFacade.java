package com.lodenrogue.oyesocio.service;

import com.lodenrogue.oyesocio.model.User;

public class UserFacade extends AbstractFacade<User> {

	public UserFacade() {
		super(User.class);
	}

	public User findByEmail(String email) {
		return findUnique("FROM User where email='" + email + "'");
	}

}
