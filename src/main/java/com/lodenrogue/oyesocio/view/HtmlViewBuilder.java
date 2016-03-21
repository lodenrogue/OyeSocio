package com.lodenrogue.oyesocio.view;

import java.io.IOException;
import java.util.Collections;

import com.lodenrogue.oyesocio.Schema;
import com.lodenrogue.oyesocio.controller.UserController;
import com.lodenrogue.oyesocio.model.Comment;
import com.lodenrogue.oyesocio.model.Post;
import com.lodenrogue.oyesocio.model.User;

public class HtmlViewBuilder implements ViewBuilder {
	//@formatter:off
	private static String DELETE_POST_HTML = 
			"<a href=\"mailto:apretaste@gmail.com?Subject=oyesocio%20eliminar_publicacion%20[DELETE_POST_ID]&Body=Mande%20este%20mensaje%20para%20borrar%20la%20publicacion.\" style=\"text-decoration: none; color: black;\">"
			+ "<p style=\"text-align: right;\">&#10005;</p>"
			+ "</a>";
	private static String DELETE_COMMENT_HTML = ""
			+ "<a href=\"mailto:apretaste@gmail.com?Subject=oyesocio%20eliminar_repuesta%20[DELETE_COMMENT_ID]&Body=Mande%20este%20mensaje%20para%20borrar%20la%20repuesta.\" style=\"text-decoration: none; color: black;\">"
			+ "<p style=\"text-align: right;\">&#10005;</p>"
			+ "</a>";
	//@formatter:on
	private String schemaDirectory;

	public HtmlViewBuilder(String schemaDirectory) {
		this.schemaDirectory = schemaDirectory;
	}

	@Override
	public String buildProfile(User viewer, User target) {
		String output = "";
		try {
			Schema profileSchema = new Schema(schemaDirectory + "/profile.schema");
			profileSchema.setAttribute("[FIRST_NAME]", target.getFirstName());
			profileSchema.setAttribute("[LAST_NAME]", target.getLastName());

			String posts = "";
			Collections.reverse(target.getPosts());
			for (Post p : target.getPosts()) {
				Schema postSchema = new Schema(schemaDirectory + "/post.schema");
				if (viewer.getId() == p.getUserId()) {
					String deleteButton = DELETE_POST_HTML.replace("[DELETE_POST_ID]", String.valueOf(p.getId()));
					postSchema.setAttribute("[DELETE_BUTTON]", deleteButton);
				}
				else {
					postSchema.setAttribute("[DELETE_BUTTON]", "");
				}
				postSchema.setAttribute("[USER_ID]", String.valueOf(target.getId()));
				postSchema.setAttribute("[FIRST_NAME]", target.getFirstName());
				postSchema.setAttribute("[LAST_NAME]", target.getLastName());
				postSchema.setAttribute("[POST_ID]", String.valueOf(p.getId()));
				postSchema.setAttribute("[TEXT]", p.getContent());

				String comments = "";
				for (Comment c : p.getComments()) {
					Schema commentSchema = new Schema(schemaDirectory + "/comment.schema");
					if (viewer.getId() == c.getUserId()) {
						String deleteButton = DELETE_COMMENT_HTML.replace("[DELETE_COMMENT_ID]", String.valueOf(c.getId()));
						commentSchema.setAttribute("[DELETE_BUTTON]", deleteButton);
					}
					else {
						commentSchema.setAttribute("[DELETE_BUTTON]", "");
					}
					User commentUser = new UserController().getUser(String.valueOf(c.getUserId()));
					commentSchema.setAttribute("[USER_ID]", String.valueOf(commentUser.getId()));
					commentSchema.setAttribute("[FIRST_NAME]", commentUser.getFirstName());
					commentSchema.setAttribute("[LAST_NAME]", commentUser.getLastName());
					commentSchema.setAttribute("[TEXT]", c.getContent());
					comments += "\n" + commentSchema.build();
				}

				postSchema.setAttribute("[COMMENTS]", comments);
				posts += "\n" + postSchema.build();
			}

			profileSchema.setAttribute("[POSTS]", posts);
			output = profileSchema.build();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return output;
	}

}
