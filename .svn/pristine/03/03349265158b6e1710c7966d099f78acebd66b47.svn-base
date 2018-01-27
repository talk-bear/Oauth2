package com.qiyun.qy.entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.thymeleaf.util.StringUtils;

@SuppressWarnings("serial")
public class Client implements ClientDetails{
	
	private static String spliter =  ",";
	
	private String clientId;
	private String resourceIds;
	private Integer secretRequired = 1;
	private String clientSecret;
	private String scope;
	private String grantTypes;
	/*	authorization_code：授权码类型。
		implicit：隐式授权类型。
		password：资源所有者（即用户）密码类型。
		client_credentials：客户端凭据（客户端ID以及Key）类型。
		refresh_token：通过以上授权获得的刷新令牌来获取新的令牌。*/
	private String redirectUri;
	private String authorities;
	private Integer tokenValidity;
	private Integer refreshValidity;
	@SuppressWarnings("unused")
	private String autoApprove;
	private String additionalInfo;
	
	public Set<String> parseStrToSet(String source, String spliter) {
		
		Set<String> hashSet = new HashSet<String>();
		if (!StringUtils.isEmptyOrWhitespace(source)) {
			String[] strArray = source.split(spliter);
			for (int i = 0; i < strArray.length; i++) {
				hashSet.add(strArray[i]);
			}
		}
		
		return hashSet;
	}

	@Override
	public String getClientId() {
		return this.clientId;
	}
	
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	@Override
	public Set<String> getResourceIds() {
		return parseStrToSet(this.resourceIds, spliter);
	}
	
	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}

	@Override
	public boolean isSecretRequired() {
		return (!this.secretRequired.equals(0));
	}
	
	public void setSecretRequired(Integer secretRequired) {
		this.secretRequired = secretRequired;
	}

	@Override
	public String getClientSecret() {
		return this.clientSecret;
	}
	
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	@Override
	public boolean isScoped() {
		return (getScope().size() > 0);
	}
	
	@Override
	public Set<String> getScope() {
		return parseStrToSet(this.scope, spliter);
	}
	
	public void setScope(String scope) {
		this.scope = scope;
	}

	@Override
	public Set<String> getAuthorizedGrantTypes() {
		return parseStrToSet(this.grantTypes, spliter);
	}
	
	public void setGrantTypes(String grantTypes) {
		this.grantTypes = grantTypes;
	}

	@Override
	public Set<String> getRegisteredRedirectUri() {
		return parseStrToSet(this.redirectUri, spliter);
	}
	
	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		Set<String> authStrSet = parseStrToSet(this.authorities, spliter);
		Set<GrantedAuthority> authoritySet = new HashSet<GrantedAuthority>();
		for (String authStr : authStrSet) {
			authoritySet.add(new GrantedAuth(authStr));
		}
		return authoritySet;
	}
	
	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}
	
	public void setAuthorities(List<GrantedAuthority> authorities) {
		String authStr = "";
		for (GrantedAuthority authority : authorities) {
			authStr += authority.getAuthority();
		}
		this.authorities = authStr;
	}

	@Override
	public Integer getAccessTokenValiditySeconds() {
		return this.tokenValidity;
	}
	
	public void setTokenValidity(Integer tokenValidity) {
		this.tokenValidity = tokenValidity;
	}

	@Override
	public Integer getRefreshTokenValiditySeconds() {
		return this.refreshValidity;
	}
	
	public void setRefreshValidity(Integer refreshValidity) {
		this.refreshValidity = refreshValidity;
	}

	@Override
	public boolean isAutoApprove(String scope) {
		//getScope().contains(scope);
		return false;
	}
	
	public void setAutoApprove(String autoApprove) {
		this.autoApprove = autoApprove;
	}

	@Override
	public Map<String, Object> getAdditionalInformation() {
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("info", this.additionalInfo);
		return info;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
	
}
