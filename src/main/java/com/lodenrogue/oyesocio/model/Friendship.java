package com.lodenrogue.oyesocio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "friendships")
public class Friendship {
	@Id
	@GeneratedValue
	private long id;

	@Column(name = "user_id")
	private long userId;

	@Column(name = "friend_id")
	private long friendId;

	public Friendship() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getFriendId() {
		return friendId;
	}

	public void setFriendId(long friendId) {
		this.friendId = friendId;
	}

}