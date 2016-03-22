package com.lodenrogue.oyesocio.service;

import java.util.List;

import com.lodenrogue.oyesocio.model.Like;

public class LikeFacade extends AbstractFacade<Like> {

	public LikeFacade() {
		super(Like.class);
	}

	public List<Like> findAllByComment(long commentId) {
		return findAllFromQuery("FROM Like WHERE commentId=" + commentId);
	}

	public List<Like> findAllByPost(long postId) {
		return findAllFromQuery("FROM Like WHERE postId=" + postId);
	}
}
