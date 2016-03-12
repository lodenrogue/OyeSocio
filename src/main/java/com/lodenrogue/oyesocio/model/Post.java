package com.lodenrogue.oyesocio.model;

import java.util.List;

/**
 * 
 */
public class Post {
	private long id;
	private long userId;
	private String content;
	private List<Long> comments;

	/**
	 * Default constructor
	 */
	public Post() {
	}

}