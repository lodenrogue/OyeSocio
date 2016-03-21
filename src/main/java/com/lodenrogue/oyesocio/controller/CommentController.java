package com.lodenrogue.oyesocio.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lodenrogue.oyesocio.model.Comment;
import com.lodenrogue.oyesocio.service.CommentFacade;

@RestController
public class CommentController {

	@RequestMapping(path = "/api/comments/{id}", method = RequestMethod.GET)
	public Comment getComment(@PathVariable long id) {
		Comment comment = new CommentFacade().find(id);
		if (comment != null) {
			return comment;
		}
		else {
			return null;
		}
	}

	@RequestMapping(path = "/api/comments/post/{postId}", method = RequestMethod.GET)
	public List<Comment> getComments(@PathVariable long postId) {
		List<Comment> comments = new CommentFacade().findAllByPost(postId);
		if (comments != null) {
			return comments;
		}
		else {
			return null;
		}
	}

	@RequestMapping(path = "/api/comments", method = RequestMethod.POST)
	public Comment createComment(@RequestParam long userId, @RequestParam long postId, @RequestParam String content) {
		Comment comment = new Comment();
		comment.setUserId(userId);
		comment.setPostId(postId);
		comment.setTimeCreated(Calendar.getInstance());
		comment.setContent(content);
		return new CommentFacade().create(comment);
	}

	@RequestMapping(path = "api/comments/{id}", method = RequestMethod.DELETE)
	public void deleteComment(@PathVariable long id) {
		new CommentFacade().delete(id);
	}

}
