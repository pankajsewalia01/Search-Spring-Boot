package com.search.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.search.core.entity.UserSearchRecords;
import com.search.pattern.ds.Trie;
import com.search.service.service.TrieService;

@Configuration
public class SearchConfig {
	
	@Autowired
	private TrieService trieService;
	
	@Bean
	public Trie<UserSearchRecords> userSearch() {
		return trieService.userSearch();
	}

}
