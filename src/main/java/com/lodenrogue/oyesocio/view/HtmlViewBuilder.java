package com.lodenrogue.oyesocio.view;

import java.io.IOException;
import java.util.Collections;

import com.lodenrogue.oyesocio.Schema;
import com.lodenrogue.oyesocio.controller.UserController;
import com.lodenrogue.oyesocio.model.Comment;
import com.lodenrogue.oyesocio.model.Post;
import com.lodenrogue.oyesocio.model.User;

public class HtmlViewBuilder implements ViewBuilder {
	private String schemaDirectory;

	public HtmlViewBuilder(String schemaDirectory) {
		this.schemaDirectory = schemaDirectory;
	}

	@Override
	public String buildProfile(User user) {
		String output = "";
		try {
			Schema profileSchema = new Schema(schemaDirectory + "/profile.schema");
			profileSchema.setAttribute("[FIRST_NAME]", user.getFirstName());
			profileSchema.setAttribute("[LAST_NAME]", user.getLastName());

			String posts = "";
			Collections.reverse(user.getPosts());
			for (Post p : user.getPosts()) {
				Schema postSchema = new Schema(schemaDirectory + "/post.schema");
				postSchema.setAttribute("[FIRST_NAME]", user.getFirstName());
				postSchema.setAttribute("[LAST_NAME]", user.getLastName());
				postSchema.setAttribute("[POST_ID]", String.valueOf(p.getId()));
				postSchema.setAttribute("[TEXT]", p.getContent());

				String comments = "";
				for (Comment c : p.getComments()) {
					Schema commentSchema = new Schema(schemaDirectory + "/comment.schema");
					User commentUser = new UserController().getUser(String.valueOf(c.getUserId()));
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
