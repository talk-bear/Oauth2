package com.qiyun.qy.auxiliary.advice;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alibaba.fastjson.JSONObject;

@RestControllerAdvice
public class ExceptionHandle {
	
	@ExceptionHandler(value = UsernameNotFoundException.class)
	public JSONObject  handle(UsernameNotFoundException exception) {
		return errorJSON(exception,  "400");
	}
	
	public JSONObject errorJSON(Exception exception, String errorCode) {
		JSONObject json = new JSONObject();
		json.put("error code",  errorCode);
		json.put("description",  exception.getMessage());
		return json;
	}

}
