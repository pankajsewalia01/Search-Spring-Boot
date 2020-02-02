package com.search.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="user_meta")
@EntityListeners(AuditingEntityListener.class)
public class UserMeta extends Audit implements Serializable{	
	
	private static final long serialVersionUID = 8168010898779043694L;

	@GeneratedValue(strategy = javax.persistence.GenerationType.AUTO )
	@Column(name="user_meta_id", nullable=false)
	@Id
	private Long id;
	
	@Column(name="user_id", nullable = false, unique = true)
	private Long userId;
	
	@Column(nullable = false, unique = true )
	private String token;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserMeta [id=");
		builder.append(id);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", token=");
		builder.append(token);
		builder.append("]");
		return builder.toString();
	}
	
}
