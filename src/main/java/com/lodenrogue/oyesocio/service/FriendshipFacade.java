package com.lodenrogue.oyesocio.service;

import java.util.List;

import com.lodenrogue.oyesocio.model.Friendship;

public class FriendshipFacade extends AbstractFacade<Friendship> {

	public FriendshipFacade() {
		super(Friendship.class);
	}

	public Friendship find(long userId, long friendId) {
		return findUnique("FROM Friendship WHERE userId=" + userId + " AND friendId=" + friendId);
	}

	public List<Friendship> findAllByUser(long userId) {
		return findAllFromQuery("FROM Friendship WHERE userId=" + userId);
	}

}
