package com.lodenrogue.oyesocio.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lodenrogue.oyesocio.model.Comment;
import com.lodenrogue.oyesocio.model.Post;
import com.lodenrogue.oyesocio.service.CommentFacade;
import com.lodenrogue.oyesocio.service.PostFacade;

@RestController
public class PostController {

	@RequestMapping(path = "api/posts/{id}", method = RequestMethod.GET)
	public Post getPost(@PathVariable long id) {
		Post post = new PostFacade().find(id);
		if (post != null) {
			post.setComments(getComments(post.getId()));
			return post;
		}
		else {
			return new Post();
		}
	}

	@RequestMapping(path = "/api/posts/user/{userId}", method = RequestMethod.GET)
	public List<Post> getPosts(@PathVariable long userId) {
		List<Post> posts = new PostFacade().findAllByUser(userId);
		if (posts != null) {
			for (Post p : posts) {
				p.setComments(getComments(p.getId()));
			}
			return posts;
		}
		else {
			return new ArrayList<Post>();
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

	private List<Long> getComments(long postId) {
		List<Comment> comments = new CommentFacade().findAllByPost(postId);
		List<Long> commentIds = new ArrayList<Long>();

		for (Comment c : comments) {
			commentIds.add(c.getId());
		}
		return commentIds;
	}

}
