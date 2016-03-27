package com.lodenrogue.oyesocio.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lodenrogue.oyesocio.model.Like;

public class LikeFacade extends AbstractFacade<Like> {

	public LikeFacade() {
		super(Like.class);
	}

	public List<Like> findAllByComment(long commentId) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("commentId", commentId);
		return findAllFromQuery("FROM Like WHERE commentId = :commentId", parameters);
	}

	public List<Like> findAllByPost(long postId) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("postId", postId);
		return findAllFromQuery("FROM Like WHERE postId = :postId", parameters);
	}
}
