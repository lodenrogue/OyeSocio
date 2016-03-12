package com.lodenrogue.oyesocio.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lodenrogue.oyesocio.model.Friendship;
import com.lodenrogue.oyesocio.service.FriendshipFacade;

@RestController
public class FriendshipController {

	@RequestMapping(path = "/api/friends", method = RequestMethod.POST)
	public Friendship addFriend(@RequestParam long userId, @RequestParam long friendId) {

		// Check to see if friendship already exists
		Friendship friendship = new FriendshipFacade().find(userId, friendId);
		if (friendship == null) {
			// Add friendship for first user
			friendship = new Friendship();
			friendship.setUserId(userId);
			friendship.setFriendId(friendId);
			new FriendshipFacade().create(friendship);

			// Add friendship for second user
			friendship = new Friendship();
			friendship.setUserId(friendId);
			friendship.setFriendId(userId);
			new FriendshipFacade().create(friendship);
			return friendship;
		}
		else {
			return new Friendship();
		}
	}

	@RequestMapping(path = "/api/friends/user/{userId}", method = RequestMethod.GET)
	public List<Friendship> getFriends(@PathVariable long userId) {
		return new FriendshipFacade().findAllByUser(userId);
	}

	@RequestMapping(path = "api/friends/{id}", method = RequestMethod.GET)
	public Friendship getFriendship(@PathVariable long id) {
		return new FriendshipFacade().find(id);
	}

}
