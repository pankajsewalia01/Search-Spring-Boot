package com.search.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.search.core.entity.Search;
import com.search.core.entity.UserSearchRecords;
import com.search.core.enums.ErrorCodes;
import com.search.core.exceptions.SearchException;
import com.search.core.utility.Constants;
import com.search.dao.repository.UserMetaRepository;
import com.search.pattern.builder.builder.UserSearchBuilder;
import com.search.pattern.validator.Validator;
import com.search.service.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @PersistenceContext
    private EntityManager entityManager;    

	@Autowired
	private Validator validator;
    	
	@Autowired
	private UserSearchBuilder builder;

	@Autowired
	private UserMetaRepository userMetaRepository;
	
	
	
	@Override
	public void buildMeta(Search search) {
		

    	if(validator.validateSearch(search)) {
    		
    		if(userMetaRepository.findByToken(search.getSearchToken())==null) {
    			builder.by(search);
    		}

    	}
	}

	@Override
	public UserSearchRecords getUser(Long id, String token) {

		if(id==null || StringUtils.isBlank(token) || token.equalsIgnoreCase(Constants.NULL))
			throw new SearchException(ErrorCodes.INVALID_USER_SEARCH_PARAM);
		
		return builder.by(id, token);
	}




}
