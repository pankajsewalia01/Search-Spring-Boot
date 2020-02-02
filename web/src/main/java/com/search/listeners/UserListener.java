package com.search.listeners;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.search.core.enums.ErrorLogs;
import com.search.pattern.ds.Trie;
import com.search.pattern.validator.Validator;
import com.search.core.entity.Search;
import com.search.core.entity.UserSearchRecords;
import com.search.service.service.TrieService;
import com.search.service.service.UserService;

public class UserListener {

	private Logger logger=Logger.getLogger(UserListener.class);

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private Validator validator;

	@Autowired
	private TrieService trieService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private Trie<UserSearchRecords> trie;
	
	@KafkaListener(topics = "search", groupId = "user")
    public void consume(String message){
    	logger.info(String.format("$$ -> Consumed Message -> %s",message));

		Search search=null;
    	if(StringUtils.isNotBlank(message)) {
    		try {
    			search=objectMapper.readValue(message, Search.class);
			} catch (IOException e) {
				logger.error(ErrorLogs.DESERIALIZATION_ERROR, e);
			}    		
    	}
    	
    	if(validator.validateSearch(search)) {
    		
    		if(trie==null) {
    			trie=trieService.userSearch();
    		}
    		
    		if(!trie.contain(search.getSearchToken())) {
        		userService.buildMeta(search);
    		}
    		
    		trie.update(
    			search.getSearchToken(),
    			userService.getUser(search.getUserId(), search.getSearchToken())
    		);
    		
    	}
    }
}
