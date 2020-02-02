package com.search.service.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.search.core.entity.UserSearchRecords;
import com.search.core.enums.ErrorLogs;
import com.search.dao.entity.UserMeta;
import com.search.dao.repository.UserMetaRepository;
import com.search.pattern.ds.Trie;
import com.search.service.service.TrieService;
import com.search.service.service.UserService;

@Service
public class TrieServiceImpl implements TrieService {

	
	private Logger logger=Logger.getLogger(TrieServiceImpl.class);

	@Autowired
	private UserMetaRepository userMetaRepository;
	
	@Autowired
	private UserService userService;
	
	@Override
	public Trie<UserSearchRecords> userSearch() {

		
		List<UserMeta> metas=userMetaRepository.findAll();
		
		Trie<UserSearchRecords> trie= new Trie<UserSearchRecords>();
				
		for(UserMeta meta:metas) {
			UserSearchRecords value;
			try {
				value=userService.getUser(meta.getUserId(),meta.getToken());
				trie.insert(meta.getToken(), value);
			}catch(Exception e) {
				logger.error(ErrorLogs.TRIE_INSERTION_ERROR, e);
			}
		}
		return trie;
	
	}


}
