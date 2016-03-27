package com.lodenrogue.oyesocio.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lodenrogue.oyesocio.model.Comment;

public class CommentFacade extends AbstractFacade<Comment> {

	public CommentFacade() {
		super(Comment.class);
	}

	public List<Comment> findAllByPost(long postId) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("postId", postId);
		return findAllFromQuery("FROM Comment WHERE postId = :postId", parameters);
	}

}
