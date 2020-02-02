package com.search.core.entity;

import java.io.Serializable;

public class Search implements Serializable{

	private static final long serialVersionUID = -8718335664561658788L;
	
	public Search(Long id, String token) {
		this.userId=id;
		this.searchToken=token;
	}

	private Long userId;
	
	private String searchToken;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getSearchToken() {
		return searchToken;
	}

	public void setSearchToken(String searchToken) {
		this.searchToken = searchToken;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Search [userId=");
		builder.append(userId);
		builder.append(", searchToken=");
		builder.append(searchToken);
		builder.append("]");
		return builder.toString();
	}

}
