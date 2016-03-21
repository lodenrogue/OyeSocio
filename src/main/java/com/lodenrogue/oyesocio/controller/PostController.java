package com.lodenrogue.oyesocio.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lodenrogue.oyesocio.model.Post;
import com.lodenrogue.oyesocio.service.PostFacade;

@RestController
public class PostController {

	@RequestMapping(path = "api/posts/{id}", method = RequestMethod.GET)
	public Post getPost(@PathVariable long id) {
		Post post = new PostFacade().find(id);
		if (post != null) {
			post.setComments(new CommentController().getComments(id));
			return post;
		}
		else {
			return null;
		}
	}

	@RequestMapping(path = "/api/posts/user/{userId}", method = RequestMethod.GET)
	public List<Post> getPosts(@PathVariable long userId) {
		List<Post> posts = new PostFacade().findAllByUser(userId);
		if (posts != null) {
			for (Post p : posts) {
				p.setComments(new CommentController().getComments(p.getId()));
			}
			return posts;
		}
		else {
			return null;
		}
	}

	@RequestMapping(path = "/api/posts", method = RequestMethod.POST)
	public Post createPost(@RequestParam long userId, @RequestParam String content) {
		Post post = new Post();
		post.setUserId(userId);
		post.setContent(content);
		post.setTimeCreated(Calendar.getInstance());
		return new PostFacade().create(post);
	}

}
