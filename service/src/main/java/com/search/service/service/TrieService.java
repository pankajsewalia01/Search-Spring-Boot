package com.search.service.service;

import com.search.core.entity.UserSearchRecords;
import com.search.pattern.ds.Trie;

public interface TrieService {
	
	Trie<UserSearchRecords> userSearch();

}
