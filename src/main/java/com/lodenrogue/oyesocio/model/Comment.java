package com.lodenrogue.oyesocio.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment {
	@Id
	@GeneratedValue
	private long id;

	@Column(name = "post_id")
	private long postId;

	@Column(name = "user_id")
	private long userId;
	private String content;

	@Column(name = "time_created")
	private Calendar timeCreated;

	public Comment() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Calendar getTimeCreated() {
		return timeCreated;
	}

	public void setTimeCreated(Calendar timeCreated) {
		this.timeCreated = timeCreated;
	}

}