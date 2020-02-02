package com.search.pattern.validator;

import org.springframework.stereotype.Service;

import com.search.core.entity.Search;
import com.search.core.enums.ErrorCodes;
import com.search.core.exceptions.SearchException;

@Service
public class Validator {

		public boolean validateSearch(Search search) {
			
			if(search==null || search.getUserId()==null || search.getSearchToken()==null) {
				throw new SearchException(ErrorCodes.INVALID_USER_SEARCH);
			}
			
			return true;
		}
}
