package com.search.controller;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.search.core.entity.Search;
import com.search.service.service.TrieUpdateService;
import com.search.service.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {
	
	@SuppressWarnings("unused")
	private Logger logger=Logger.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;

	@Autowired
	private TrieUpdateService trieUpdateService;
	
	
	@RequestMapping(value = "/meta", method = RequestMethod.POST)
	public void userMeta(@RequestBody Search request){
		userService.buildMeta(request);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void update(@RequestBody Search request){
		trieUpdateService.update(request);
	}
	
	@RequestMapping(value = "/{id}/{token}", method = RequestMethod.GET)
	public ResponseEntity<?> userSearch(
			@PathVariable("id") Long id,
			@PathVariable("token") String token){
		return ResponseEntity.ok(userService.getUser(id,token));
	}
	

}
