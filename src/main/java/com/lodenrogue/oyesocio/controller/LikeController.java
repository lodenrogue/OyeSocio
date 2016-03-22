package com.lodenrogue.oyesocio.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lodenrogue.oyesocio.model.Like;
import com.lodenrogue.oyesocio.service.LikeFacade;

@RestController
public class LikeController {

	@RequestMapping(path = "/api/likes/{id}", method = RequestMethod.GET)
	public Like getLike(@PathVariable long id) {
		return new LikeFacade().find(id);
	}

	@RequestMapping(path = "/api/likes/comment/{commentId}", method = RequestMethod.GET)
	public List<Like> getCommentLikes(@PathVariable long commentId) {
		return new LikeFacade().findAllByComment(commentId);
	}

	@RequestMapping(path = "/api/likes/post/{postId}", method = RequestMethod.GET)
	public List<Like> getPostLikes(@PathVariable long postId) {
		return new LikeFacade().findAllByPost(postId);
	}

	@RequestMapping(path = "api/likes/comment/{commentId}", method = RequestMethod.POST)
	public Like createCommentLike(@PathVariable long commentId, @RequestParam long userId) {
		Like like = new Like();
		like.setCommentId(commentId);
		like.setUserId(userId);
		return new LikeFacade().create(like);
	}

	@RequestMapping(path = "api/likes/post/{postId}", method = RequestMethod.POST)
	public Like createPostLike(@PathVariable long postId, @RequestParam long userId) {
		Like like = new Like();
		like.setPostId(postId);
		like.setUserId(userId);
		return new LikeFacade().create(like);
	}

	@RequestMapping(path = "api/likes/{id}", method = RequestMethod.DELETE)
	public void deleteLike(@PathVariable long id) {
		new LikeFacade().delete(id);
	}

}
