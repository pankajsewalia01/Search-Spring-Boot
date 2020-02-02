package com.search.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.search.service.service.SearchService;


@CrossOrigin(origins = "*")
@RestController
public class SearchController {

	@Autowired
	private SearchService searchService;
	
	@RequestMapping(value = "/change", method = RequestMethod.GET)
	public ResponseEntity<?> search(
			@RequestHeader("nsSessionId") String nsSessionId,
			@RequestHeader("text") String text
			){
		return searchService.search(nsSessionId,text);
	}
}
