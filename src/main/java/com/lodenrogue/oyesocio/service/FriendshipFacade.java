package com.lodenrogue.oyesocio.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lodenrogue.oyesocio.model.Friendship;

public class FriendshipFacade extends AbstractFacade<Friendship> {

	public FriendshipFacade() {
		super(Friendship.class);
	}

	public Friendship find(long userId, long friendId) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("userId", userId);
		parameters.put("friendId", friendId);
		return findUnique("FROM Friendship WHERE userId = :userId AND friendId = :friendId", parameters);
	}

	public List<Friendship> findAllByUser(long userId) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("userId", userId);
		return findAllFromQuery("FROM Friendship WHERE userId = :userId", parameters);
	}

}
