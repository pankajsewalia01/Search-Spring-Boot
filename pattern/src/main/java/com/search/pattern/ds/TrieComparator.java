package com.search.pattern.ds;

import java.util.Comparator;

import com.search.core.entity.UserSearchRecords;

public class TrieComparator<T> implements Comparator<T> {

	@Override
	public int compare(T o1, T o2) {
		
		if(o1 instanceof UserSearchRecords) {
			
			return ((UserSearchRecords) o1).getToken()
					.compareToIgnoreCase(((UserSearchRecords) o2).getToken());
		}

		return o1.toString().compareToIgnoreCase(o2.toString());
	}

}
