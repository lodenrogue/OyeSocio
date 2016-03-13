package com.lodenrogue.oyesocio.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lodenrogue.oyesocio.model.FriendRequest;
import com.lodenrogue.oyesocio.service.FriendRequestFacade;

@RestController
public class FriendRequestController {

	@RequestMapping(path = "/api/requests/{id}", method = RequestMethod.GET)
	public FriendRequest getFriendRequest(@PathVariable long id) {
		FriendRequest request = new FriendRequestFacade().find(id);
		if (request != null) {
			return request;
		}
		else {
			return new FriendRequest();
		}
	}

	@RequestMapping(path = "/api/requests/user/{userId}", method = RequestMethod.GET)
	public List<FriendRequest> getFriendRequests(@PathVariable long userId) {
		List<FriendRequest> requests = new FriendRequestFacade().findAllByTarget(userId);
		if (requests != null) {
			return requests;
		}
		else {
			return new ArrayList<FriendRequest>();
		}
	}

	@RequestMapping(path = "/api/requests", method = RequestMethod.POST)
	public FriendRequest createFriendRequest(@RequestParam long userTargetId, @RequestParam long userRequestingId) {
		FriendRequest request = new FriendRequest();
		request.setUserRequestingId(userRequestingId);
		request.setUserTargetId(userTargetId);
		return new FriendRequestFacade().create(request);
	}
}
