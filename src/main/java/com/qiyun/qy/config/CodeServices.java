package com.qiyun.qy.config;

import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;

public class CodeServices implements AuthorizationCodeServices{

	@Override
	public String createAuthorizationCode(OAuth2Authentication authentication) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OAuth2Authentication consumeAuthorizationCode(String code) throws InvalidGrantException {
		// TODO Auto-generated method stub
		return null;
	}

}
