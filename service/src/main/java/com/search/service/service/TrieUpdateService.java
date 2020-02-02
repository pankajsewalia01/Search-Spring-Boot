package com.search.service.service;

import com.search.core.entity.Search;
import com.search.core.entity.UserSearchRecords;

public interface TrieUpdateService {

	void update(String key, UserSearchRecords value);

	void update(Search search);
	
}
