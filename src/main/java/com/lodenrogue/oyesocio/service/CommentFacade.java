package com.lodenrogue.oyesocio.service;

import java.util.List;

import com.lodenrogue.oyesocio.model.Comment;

public class CommentFacade extends AbstractFacade<Comment> {

	public CommentFacade() {
		super(Comment.class);
	}

	public List<Comment> findAllByPost(long postId) {
		return findAllFromQuery("FROM Comment WHERE postId=" + postId);
	}

}
