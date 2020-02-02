package com.search.pattern.builder.builder;

import com.search.core.entity.Search;
import com.search.core.entity.UserSearchRecords;

public interface UserSearchBuilder {

	public UserSearchRecords by(Long id, String token);
	

	public void by(Search search);
}
