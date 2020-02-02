package com.search.core.service;


public interface CacheService {

	/**
	 * 
	 * @param key
	 * @param value
	 * @param ttl
	 */
	public void set(String key, String value, int ttl);
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public Object get(String key) ;

	/**
	 * 
	 * @param key
	 */
	void del(String key);
	
	/**
	 * 
	 * @param key
	 * @param value
	 */
	void set(String key, String value);
}