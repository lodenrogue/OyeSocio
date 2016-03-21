package com.lodenrogue.oyesocio.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lodenrogue.oyesocio.TypeResponse;
import com.lodenrogue.oyesocio.model.Post;
import com.lodenrogue.oyesocio.model.User;
import com.lodenrogue.oyesocio.view.HtmlViewBuilder;

@RestController
public class ServiceController {

	@RequestMapping(path = "/api/signin", method = RequestMethod.GET)
	public TypeResponse signin(@RequestParam String email) throws IOException {
		String type = "";
		String data = "";
		User user = new UserController().getUser(email);
		if (user == null) {
			type = "SIGNUP";
		}
		else {
			type = "PROFILE";
		}
		TypeResponse response = new TypeResponse();
		response.setType(type);
		response.setData(data);
		return response;
	}

	@RequestMapping(path = "/api/register", method = RequestMethod.POST)
	public User register(@RequestParam String email, @RequestParam String firstName, @RequestParam String lastName) {
		User user = new UserController().getUser(email);
		if (user == null) {
			user = new UserController().createUser(firstName, lastName, email);
		}
		return user;
	}

	/**
	 * Get profile by user email
	 * 
	 * @param email
	 * @return
	 */
	@RequestMapping(path = "api/profile", method = RequestMethod.GET)
	public String getProfile(@RequestParam String email) {
		User user = new UserController().getUser(email);
		if (user != null) {
			return new HtmlViewBuilder("schemas").buildProfile(user);
		}
		else {
			return "ERROR: NO USER FOUND";
		}
	}

	/**
	 * Get profile by user id
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(path = "api/profile/{id}", method = RequestMethod.GET)
	public String getProfile(@PathVariable long id) {
		User user = new UserController().getUser(String.valueOf(id));
		if (user != null) {
			return new HtmlViewBuilder("schemas").buildProfile(user);
		}
		else {
			return "ERROR: NO USER FOUND";
		}
	}

	@RequestMapping(path = "api/publish", method = RequestMethod.POST)
	public String publishPost(@RequestParam String userEmail, @RequestParam String content) {
		User user = new UserController().getUser(userEmail);
		if (user == null) {
			return "ERROR: NO USER FOUND";
		}
		else {
			return new PostController().createPost(user.getId(), content).getContent();
		}
	}

	@RequestMapping(path = "api/reply", method = RequestMethod.POST)
	public Object reply(@RequestParam long postId, @RequestParam String userEmail, @RequestParam String content) {
		User user = new UserController().getUser(userEmail);
		if (user == null) {
			return "ERROR: NO USER FOUND";
		}
		else {
			Post post = new PostController().getPost(postId);
			if (post == null) {
				return "ERROR: NO POST FOUND";
			}
			else {
				new CommentController().createComment(user.getId(), postId, content).getContent();
				return post;
			}
		}
	}

}
