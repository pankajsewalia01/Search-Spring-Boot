package com.search.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.search.core.entity.Search;
import com.search.core.entity.UserSearchRecords;
import com.search.pattern.builder.builder.UserSearchBuilder;
import com.search.pattern.ds.Trie;
import com.search.pattern.validator.Validator;
import com.search.service.service.TrieUpdateService;

@Service
public class TrieUpdateServiceImpl implements TrieUpdateService {

	@Autowired
	private Trie<UserSearchRecords> trie;	

	@Autowired
	private Validator validator;
	
	@Autowired
	private UserSearchBuilder builder;

	@Override
	public void update(String key, UserSearchRecords value) {

		trie.update(key, value);
	}

	@Override
	public void update(Search search) {

    	if(validator.validateSearch(search)) {
    		
    		this.update(
    				search.getSearchToken(),
    				builder.by(search.getUserId(), search.getSearchToken())
    			);
    	}

		
	}

}
