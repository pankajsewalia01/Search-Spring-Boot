package com.search.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.search.listeners.UserListener;


@Configuration
public class KafkaConfigs {
	
	  @Bean
	  public UserListener receiver() {
	    return new UserListener();
	  }
}
