package com.lodenrogue.oyesocio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "friend_requests")
public class FriendRequest {
	@Id
	@GeneratedValue
	private long id;

	@Column(name = "user_target_id")
	private long userTargetId;

	@Column(name = "user_requesting_id")
	private long userRequestingId;

	public FriendRequest() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserTargetId() {
		return userTargetId;
	}

	public void setUserTargetId(long userTargetId) {
		this.userTargetId = userTargetId;
	}

	public long getUserRequestingId() {
		return userRequestingId;
	}

	public void setUserRequestingId(long userRequestingId) {
		this.userRequestingId = userRequestingId;
	}
}