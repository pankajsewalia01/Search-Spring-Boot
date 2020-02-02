package com.search.pattern.builder.impl;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Service;

import com.search.core.entity.Search;
import com.search.core.entity.UserSearchRecords;
import com.search.core.utility.Constants;
import com.search.pattern.builder.builder.UserSearchBuilder;

@Service
public class UserSearchBuilderImpl implements UserSearchBuilder {

    @PersistenceContext
    private EntityManager entityManager;
    
	@Override
	public UserSearchRecords by(Long id, String token) {
		//"login" this is the name of your procedure
        StoredProcedureQuery query = 
        		entityManager.createStoredProcedureQuery(Constants.PROC_GET_USER_SEARCH); 

        //Declare the parameters in the same order
        query.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, String.class, ParameterMode.INOUT);
        query.registerStoredProcedureParameter(4, String.class, ParameterMode.INOUT);

        //Pass the parameter values
        query.setParameter(1, id);
        query.setParameter(2, token);

        //Execute query
        query.execute();

        //Get output parameters
        String name = (String) query.getOutputParameterValue(3),
        	profile = (String) query.getOutputParameterValue(4);
        

		if(profile==null || 
				profile.equalsIgnoreCase(Constants.EMPTY_STRING)) {
	        StoredProcedureQuery profileQuery = 
	        		entityManager.createStoredProcedureQuery(Constants.PROC_DEFAULT_PROFILE_URL); 
	        profileQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.INOUT);
	        profileQuery.execute();
	        profile = (String) profileQuery.getOutputParameterValue(1);
		}
		
		return new UserSearchRecords(id, token, name, profile);
	}

	@Override
	public void by(Search search) {
		
        StoredProcedureQuery query = 
        		entityManager.createStoredProcedureQuery(Constants.PROC_BUILD_USER_META); 

        query.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);

        query.setParameter(1, search.getUserId());
        query.setParameter(2, search.getSearchToken());

        query.execute();
		
	}

}
