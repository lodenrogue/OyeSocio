package com.lodenrogue.oyesocio.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lodenrogue.oyesocio.model.FriendRequest;

public class FriendRequestFacade extends AbstractFacade<FriendRequest> {

	public FriendRequestFacade() {
		super(FriendRequest.class);
	}

	public List<FriendRequest> findAllByTarget(long targetId) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("targetId", targetId);
		return findAllFromQuery("FROM FriendRequest WHERE userTargetId = :targetId", parameters);
	}

}
