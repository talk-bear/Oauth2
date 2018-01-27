package com.qiyun.qy;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String rawPassword = "secret";
		String encodePass = encoder.encode(rawPassword);
		System.out.println(encodePass);
	}

}
