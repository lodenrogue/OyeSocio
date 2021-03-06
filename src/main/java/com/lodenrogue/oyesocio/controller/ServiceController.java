package com.lodenrogue.oyesocio.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lodenrogue.oyesocio.model.Comment;
import com.lodenrogue.oyesocio.model.Like;
import com.lodenrogue.oyesocio.model.Post;
import com.lodenrogue.oyesocio.model.User;
import com.lodenrogue.oyesocio.view.HtmlViewBuilder;

@RestController
public class ServiceController {

	@RequestMapping(path = "/service/signin", method = RequestMethod.GET)
	public String signin(@RequestParam String email) throws IOException {
		User user = new UserController().getUser(email);
		if (user == null) {
			return "SIGNUP";
		}
		else {
			return "PROFILE";
		}
	}

	@RequestMapping(path = "/service/register", method = RequestMethod.POST)
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
	@RequestMapping(path = "/service/profile", method = RequestMethod.GET)
	public String getProfile(@RequestParam String viewerEmail, @RequestParam String targetEmail) {
		User viewer = new UserController().getUser(viewerEmail);
		User target = new UserController().getUser(targetEmail);

		if (viewer != null && target != null) {
			return new HtmlViewBuilder("schemas").buildProfile(viewer, target);
		}
		else {
			if (viewer == null) {
				return "ERROR: NO USER FOUND WITH EMAIL " + viewerEmail;
			}
			else {
				return "ERROR: NO USER FOUND WITH EMAIL " + targetEmail;
			}
		}
	}

	/**
	 * Get profile by user id
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(path = "/service/profile/{id}", method = RequestMethod.GET)
	public String getProfile(@RequestParam String viewerEmail, @PathVariable long id) {
		User viewer = new UserController().getUser(viewerEmail);
		User target = new UserController().getUser(String.valueOf(id));

		if (viewer != null && target != null) {
			return new HtmlViewBuilder("schemas").buildProfile(viewer, target);
		}
		else {
			if (viewer == null) {
				return "ERROR: NO USER FOUND WITH EMAIL " + viewerEmail;
			}
			else {
				return "ERROR: NO USER FOUND WITH ID " + id;
			}
		}
	}

	@RequestMapping(path = "/service/publish", method = RequestMethod.POST)
	public Object publishPost(@RequestParam String userEmail, @RequestParam String content) {
		User user = new UserController().getUser(userEmail);
		if (user == null) {
			return "ERROR: NO USER FOUND";
		}
		else {
			return new PostController().createPost(user.getId(), content);
		}
	}

	@RequestMapping(path = "/service/like/post/{postId}", method = RequestMethod.POST)
	public Object likePost(@PathVariable long postId, @RequestParam String userEmail) {
		User user = new UserController().getUser(userEmail);
		if (user != null) {
			Post post = new PostController().getPost(postId);
			if (post != null) {
				for (Like l : post.getLikes()) {
					if (l.getUserId() == user.getId()) {
						return "ERROR: USER ALREADY LIKED THIS";
					}
				}
				return new LikeController().createPostLike(postId, user.getId());
			}
			else {
				return "ERROR: POST NOT FOUND";
			}
		}
		else {
			return "ERROR: NO USER FOUND";
		}
	}

	@RequestMapping(path = "/service/delete-post/{id}", method = RequestMethod.DELETE)
	public String deletePost(@RequestParam String userEmail, @PathVariable long id) {
		User user = new UserController().getUser(userEmail);
		if (user != null) {
			Post post = new PostController().getPost(id);
			if (post != null) {
				if (post.getUserId() == user.getId()) {

					// Delete comments
					for (Comment c : post.getComments()) {

						// Delete comment likes
						for (Like l : c.getLikes()) {
							new LikeController().deleteLike(l.getId());
						}
						new CommentController().deleteComment(c.getId());
					}

					// Delete post likes
					for (Like l : post.getLikes()) {
						new LikeController().deleteLike(l.getId());
					}

					// Delete post
					new PostController().deletePost(post.getId());
					return "DONE";
				}
				else {
					return "ERROR: USER IS NOT OWNER";
				}
			}
			else {
				return "ERROR: POST NOT FOUND";
			}
		}
		else {
			return "ERROR: NO USER FOUND";
		}
	}

	@RequestMapping(path = "/service/reply", method = RequestMethod.POST)
	public Object publishComment(@RequestParam long postId, @RequestParam String userEmail, @RequestParam String content) {
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

	@RequestMapping(path = "/service/like/comment/{commentId}", method = RequestMethod.POST)
	public Object likeComment(@PathVariable long commentId, @RequestParam String userEmail) {
		User user = new UserController().getUser(userEmail);
		if (user != null) {
			Comment comment = new CommentController().getComment(commentId);
			if (comment != null) {
				for (Like l : comment.getLikes()) {
					if (l.getUserId() == user.getId()) {
						return "ERROR: USER ALREADY LIKED THIS";
					}
				}
				return new LikeController().createCommentLike(commentId, user.getId());
			}
			else {
				return "ERROR: COMMENT NOT FOUND";
			}
		}
		else {
			return "ERROR: NO USER FOUND";
		}
	}

	@RequestMapping(path = "/service/delete-comment/{id}", method = RequestMethod.DELETE)
	public String deleteComment(@RequestParam String userEmail, @PathVariable long id) {
		User user = new UserController().getUser(userEmail);
		if (user != null) {
			Comment comment = new CommentController().getComment(id);
			if (comment != null) {
				if (comment.getUserId() == user.getId()) {

					// Delete comment likes
					for (Like l : comment.getLikes()) {
						new LikeController().deleteLike(l.getId());
					}

					// Delete comment
					new CommentController().deleteComment(comment.getId());
					return "DONE";
				}
				else {
					return "ERROR: USER IS NOT OWNER";
				}
			}
			else {
				return "ERROR: COMMENT NOT FOUND";
			}
		}
		else {
			return "ERROR: NO USER FOUND";
		}
	}
}
