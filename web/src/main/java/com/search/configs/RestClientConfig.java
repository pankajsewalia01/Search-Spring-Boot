package com.search.configs;


import java.util.concurrent.TimeUnit;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.search.core.handlers.MyResponseErrorHandler;

@Configuration
public class RestClientConfig {

	private static final int CONNECT_TIMEOUT = 30000;

	private static final int REQUEST_TIMEOUT = 30000;

	private static final int SOCKET_TIMEOUT = 30000;

	private static final int MAX_TOTAL_CONNECTIONS = 100;

	private static final int MAX_PER_ROUTE = 100;

	private static final int IDLE_CONNECTION_WAIT_TIME = 60;
		
	@Bean
	public RestTemplate restTemplate(MyResponseErrorHandler myResponseErrorHandler){
		RestTemplate template = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient()));
		
		for(HttpMessageConverter<?> converter : template.getMessageConverters()){
			if(converter instanceof MappingJackson2HttpMessageConverter){
				MappingJackson2HttpMessageConverter jsonConverter=
						(MappingJackson2HttpMessageConverter)converter;
				jsonConverter.setObjectMapper(objectMapper());
			}
		}
		template.setErrorHandler(myResponseErrorHandler);
		return template;
	}

	private ObjectMapper objectMapper() {
		// customize later
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, false);
		// for serializing lazy entities
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		mapper.setSerializationInclusion(Include.NON_NULL);
		return mapper;
	}

	private CloseableHttpClient httpClient() {
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectionRequestTimeout(REQUEST_TIMEOUT)
				.setConnectTimeout(CONNECT_TIMEOUT)
				.setSocketTimeout(SOCKET_TIMEOUT).build();
		PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
		connManager.setMaxTotal(MAX_TOTAL_CONNECTIONS);
		connManager.setDefaultMaxPerRoute(MAX_PER_ROUTE);
		
		return HttpClients.custom()
				.setConnectionManager(connManager)
				.setDefaultRequestConfig(requestConfig)
				.evictIdleConnections(IDLE_CONNECTION_WAIT_TIME, TimeUnit.SECONDS)
				.build();
	}
}
