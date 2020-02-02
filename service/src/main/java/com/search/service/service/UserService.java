package com.search.service.service;

import com.search.core.entity.Search;
import com.search.core.entity.UserSearchRecords;

public interface UserService {
	
	public void buildMeta(Search search);

	public UserSearchRecords getUser(Long id, String token);
	

}
