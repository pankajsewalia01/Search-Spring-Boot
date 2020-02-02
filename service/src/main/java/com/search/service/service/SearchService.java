package com.search.service.service;

import org.springframework.http.ResponseEntity;

public interface SearchService {

	public ResponseEntity<?> search(String lnSessionId, String text);
}
