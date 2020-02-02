package com.search.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.search.core.entity.UserSearchRecords;
import com.search.pattern.ds.Trie;
import com.search.service.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private Trie<UserSearchRecords> trie;
	
	@Override
	public ResponseEntity<?> search(String lnSessionId, String text) {

		return ResponseEntity.ok(trie.searchAll(text));
	}
}
