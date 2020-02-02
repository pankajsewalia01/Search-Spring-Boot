package com.search.configs;


import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheManager.RedisCacheManagerBuilder;
//import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration.JedisClientConfigurationBuilder;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.search.core.handlers.CustomKeyGenerator;



@Configuration
@EnableCaching
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class RedisConfig extends CachingConfigurerSupport implements CachingConfigurer {
	
	private static final Logger logger = LoggerFactory.getLogger(RedisConfig.class);
	
	@Autowired
	private Environment env;
	
	@Bean
	public RedisStandaloneConfiguration redisStandaloneConfiguration() {
		RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
		redisConfig.setDatabase(Integer.parseInt(env.getProperty("spring.redis.jedis.index")));
		redisConfig.setHostName(env.getProperty("spring.redis.host"));
		redisConfig.setPort(Integer.parseInt(env.getProperty("spring.redis.port")));
		//redisConfig.setPassword(RedisPassword.of(env.getProperty("spring.redis.password")));
		logger.info("[Redis Database Information] Connection Properties ::" + redisConfig.getDatabase() + " "
				+ redisConfig.getHostName() + " " + redisConfig.getPort());
		return redisConfig;
	}

	@Bean
	@SuppressWarnings("rawtypes")
	public GenericObjectPoolConfig getPoolConfig() {
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		poolConfig.setMaxTotal(Integer.parseInt(env.getProperty("spring.redis.jedis.pool.max-active")));
		poolConfig.setMaxIdle(Integer.parseInt(env.getProperty("spring.redis.jedis.pool.max-idle")));
		poolConfig.setMinIdle(Integer.parseInt(env.getProperty("spring.redis.jedis.pool.min-idle")));
		poolConfig.setTestOnBorrow(true);
		poolConfig.setTestOnReturn(true);
		poolConfig.setTestWhileIdle(true);
		poolConfig.setNumTestsPerEvictionRun(3);
		poolConfig.setBlockWhenExhausted(true);
		logger.info("[Redis Pool Config] Pool configuration :::" + poolConfig);
		return poolConfig;
	}

	@Bean
	public JedisClientConfiguration poolConfig() {
		JedisClientConfigurationBuilder jedisClientConfigrationBuilder = JedisClientConfiguration.builder();
		jedisClientConfigrationBuilder.readTimeout(Duration.ofSeconds(env.getProperty("spring.redis.jedis.pool.timeout",Long.class)));
		jedisClientConfigrationBuilder.usePooling().poolConfig(getPoolConfig());
		return jedisClientConfigrationBuilder.build();
	}

	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		logger.info("[Redis Pool Config] JedisConnectionFactory :::");
		JedisConnectionFactory factory = new JedisConnectionFactory(redisStandaloneConfiguration(), poolConfig());
		return factory;
	}

	@Bean
	@Autowired
	public RedisTemplate<String, String> redisTemplate() {
		final RedisTemplate<String, String> template = new RedisTemplate<String, String>();
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new StringRedisSerializer());
		template.setConnectionFactory(jedisConnectionFactory());
		return template;
	}
	
	@Bean
	public CacheManager cacheManager() {
		logger.info("[Redis Pool Config] CacheManager :::");
		RedisCacheManagerBuilder redisBuilder = RedisCacheManager.builder(jedisConnectionFactory());
		Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<String, RedisCacheConfiguration>();
		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl(Duration.ofMinutes(env.getProperty("spring.redis.key.ttl", Long.class)));
		cacheConfigurations.put("backtonext", config);
		logger.info("[Redis Pool Config] CacheManager :::");
		return redisBuilder.withInitialCacheConfigurations(cacheConfigurations).build();
	}
	
	@Bean("keyGenerator")
    public KeyGenerator keyGenerator() {
		logger.info("[Redis Pool Config] KeyGenerator :::");
        return new CustomKeyGenerator();
    }
	
	@Bean 
	@Override
	public CacheErrorHandler errorHandler() {
	 return new RedisCacheErrorHandler();
	}
	
	 public static class RedisCacheErrorHandler implements CacheErrorHandler {

		@Override
		public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
			logger.error("Unable to get from cache " + cache.getName() + " : " + exception.getMessage());
		}
		@Override
		public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
			logger.error("Unable to put into cache " + cache.getName() + " : " + exception.getMessage());
		}
		@Override
		public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
			logger.error("Unable to evict from cache " + cache.getName() + " : " + exception.getMessage());
		}
		@Override
		public void handleCacheClearError(RuntimeException exception, Cache cache) {
			logger.error("Unable to clear cache " + cache.getName() + " : " + exception.getMessage());
		}
	}

	@Override
	public CacheResolver cacheResolver() {
		return  null;
	}

}