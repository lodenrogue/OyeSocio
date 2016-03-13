package com.lodenrogue.oyesocio.service;

import java.util.List;

import com.lodenrogue.oyesocio.model.FriendRequest;

public class FriendRequestFacade extends AbstractFacade<FriendRequest> {

	public FriendRequestFacade() {
		super(FriendRequest.class);
	}
	
	public List<FriendRequest> findAllByTarget(long targetId) {
		return findAllFromQuery("FROM FriendRequest WHERE userTargetId=" + targetId);
	}

}
