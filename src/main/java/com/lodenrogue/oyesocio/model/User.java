package com.lodenrogue.oyesocio.model;

import java.util.List;

/**
 * 
 */
public class User {
	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String accessToken;
	private List<Long> friends;
	private List<Long> posts;

	/**
	 * Default constructor
	 */
	public User() {
	}

}