package com.search.core.serviceImpl;


import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.search.core.service.CacheService;

@Service("cacheService")
public class CacheServiceImpl implements CacheService {
	
	private static final Logger logger = LoggerFactory.getLogger(CacheServiceImpl.class);

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Value("${redis.key.prefix.name}")
	private String keyPrefix;

	@Override
	public void del(String key) {
		redisTemplate.delete(this.getkey(key));
	}

	@Override
	public void set(String key, String value) {
		try{
			redisTemplate.opsForValue().set(this.getkey(key), value);			
		}catch(final Throwable t) {
            logger.warn("Error executing cache operation: {}", t.getMessage());
        }
	}

	@Override
	public void set(String key, String value, int ttl) {
		logger.info("[Redis Template] Redis Key ::" + key);
		redisTemplate.opsForValue().set(this.getkey(key), value, ttl, TimeUnit.SECONDS);
		logger.info("[Redis Template] Key successfully saved in redis ::" + key);
	}

	@Override
	public Object get(String key) {
		try {
			return redisTemplate.opsForValue().get(this.getkey(key));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	public boolean exists(String key) {
		boolean isExits = redisTemplate.hasKey(keyPrefix + key);
		logger.info("[Redis Template] key " + key + " exits in redis :: " + isExits);
		return isExits;
	}
	
	public String getkey(String key){
		return new StringBuilder().append(keyPrefix)
				.append(key).toString();
	}

}
