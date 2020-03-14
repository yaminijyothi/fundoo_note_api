package com.bridgelabz.fundoonotes.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
@Configuration
public class ReddisConfig {
	
	
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		return new JedisConnectionFactory();

	}

	@Bean
	RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
	template.setConnectionFactory(jedisConnectionFactory());
//	template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
		
		return template;
}

}
