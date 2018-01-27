package com.qiyun.qy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@EnableAuthorizationServer
@SpringBootApplication
public class QyApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(QyApplication.class, args);
		
	}
	
}
