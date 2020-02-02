package com.search.core.entity;

import java.util.Objects;

public class UserSearchRecords {

	private Long userId;
	
	private String token;
	
	private String name;
	
	private String profile_url;
	
	public UserSearchRecords() {}
	
	public UserSearchRecords(Long userId, String token, String name, String profile_url) {
		this.userId=userId;
		this.token=token;
		this.name=name;
		this.profile_url=profile_url;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfile_url() {
		return profile_url;
	}

	public void setProfile_url(String profile_url) {
		this.profile_url = profile_url;
	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(name, profile_url, token, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof UserSearchRecords)) {
			return false;
		}
		UserSearchRecords other = (UserSearchRecords) obj;
		return Objects.equals(name, other.name) && Objects.equals(profile_url, other.profile_url)
				&& Objects.equals(token, other.token) && Objects.equals(userId, other.userId);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserSearchRecords [userId=");
		builder.append(userId);
		builder.append(", token=");
		builder.append(token);
		builder.append(", name=");
		builder.append(name);
		builder.append(", profile_url=");
		builder.append(profile_url);
		builder.append("]");
		return builder.toString();
	}
	
}
