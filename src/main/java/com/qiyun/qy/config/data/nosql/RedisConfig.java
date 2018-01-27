package com.qiyun.qy.config.data.nosql;

import java.lang.reflect.Method;
import java.time.Duration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableCaching
@Configuration
public class RedisConfig extends CachingConfigurerSupport{

	@Bean("qiYunkeyGenerator")
    public KeyGenerator keyGenerator(){
        return new KeyGenerator() {
			@Override
			public Object generate(Object target, Method method, Object... params) {
				StringBuilder sb = new StringBuilder();
                //sb.append(target.getClass().getName());
                //sb.append(method.getName());
				
	            sb.append(getKeyPrifix(method));
	            
                for (Object obj : params) {
                	sb.append("_");
                    sb.append(obj.toString());
                }
                return sb.toString();
			}
        };
    }
	
	private String getKeyPrifix(Method method) {
		String[] value = new String[1];
		Cacheable cacheable = method.getAnnotation(Cacheable.class);
        if (cacheable != null) {
            value = cacheable.value();
        } else {
        	CachePut cachePut = method.getAnnotation(CachePut.class);
            if (cachePut != null) {
                value = cachePut.value();
            } else {
            	CacheEvict cacheEvict = method.getAnnotation(CacheEvict.class);
	            if (cacheEvict != null) {
	                value = cacheEvict.value();
	            }
            }
        }
        return value[0];
	}
	
    @Bean
    public CacheManager cacheManager(RedisTemplate<String, Object> redisTemplate) {
    	RedisCacheWriter cacheWriter = null;
    	RedisCacheConfiguration config= null;
    	CacheManager cacheManager = new RedisCacheManager(cacheWriter, config);
        return cacheManager;
    }
    
    @Bean
    public RedisTemplate<String, Object> objectRedisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        serializer.setObjectMapper(objectMapper);
        template.setValueSerializer(serializer);
        return template;
    }
    
}