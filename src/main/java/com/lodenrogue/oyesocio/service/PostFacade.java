package com.lodenrogue.oyesocio.service;

import java.util.List;

import com.lodenrogue.oyesocio.model.Post;

public class PostFacade extends AbstractFacade<Post> {

	public PostFacade() {
		super(Post.class);
	}

	public List<Post> findAllByUser(long userId) {
		return findAllFromQuery("FROM Post WHERE userId=" + userId);
	}

}
