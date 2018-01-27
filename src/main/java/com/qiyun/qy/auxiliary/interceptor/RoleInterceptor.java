package com.qiyun.qy.auxiliary.interceptor;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.alibaba.fastjson.JSONObject;
import com.microsoft.sqlserver.jdbc.StringUtils;
import com.qiyun.qy.auxiliary.annotation.RoleAnnotation;
import com.qiyun.qy.auxiliary.annotation.RoleAnnotation.TYPE;

@Service("qiYunRoleInterceptor")
public class RoleInterceptor implements HandlerInterceptor {
	
	@Autowired
	private TokenStore tokenStore;

	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler)
			throws Exception {
		
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		RoleAnnotation roleAnnotation = 
				handlerMethod.getMethodAnnotation(RoleAnnotation.class);
		if (roleAnnotation == null) {
			return true;
	    }
		
		String token = request.getParameter("access_token");
		if (StringUtils.isEmpty(token)) {
			return responseError(response, "400", "reuired access_token");
		}
		OAuth2Authentication auth = tokenStore.readAuthentication(token);
		Collection<GrantedAuthority> authorities = auth.getAuthorities();
		if (authorities.isEmpty()) {
			return responseError(response, "400", "reuired Authority");
		}
		
		Set<String> permissons = new HashSet<String>();
		for (GrantedAuthority authority : authorities) {
			permissons.add(authority.getAuthority());
		}
		
		if (!hasRole(permissons, roleAnnotation)) {
			return responseError(response, "400", "reuired Role");
		}
		return true;
	}
	
	private boolean hasRole(Set<String> permissons, RoleAnnotation roleAnnotation) {
		boolean hasRole = false;
		
		TYPE type = roleAnnotation.type();
		switch (type) {
			case OR :
				String[] orRoles = roleAnnotation.or();
				for (String orRole : orRoles) {
					if (permissons.contains(orRole)) {
						hasRole = true;
						break;
					}
				};
				break;
			case AND :
				String[] andRoles = roleAnnotation.and();
				Set<String> requireRoles = new HashSet<String>();
				for (String role : andRoles) {
					requireRoles.add(role);
				}
				if (permissons.containsAll(requireRoles)) {
					hasRole = true;
				}
				break;
			case MIXED :
				break;
		}
		
		return hasRole;
	}
	
	
	private boolean responseError(HttpServletResponse response, String errorCode, String errorMsg) throws IOException {
		response.setContentType("application/json; charset=utf-8");  
        response.setCharacterEncoding("UTF-8");  
        
        JSONObject json = new JSONObject();
        json.put("error code",  errorCode);
        json.put("description",  errorMsg);
  
        String error = json.toString();
        OutputStream out = response.getOutputStream();  
        out.write(error.getBytes("UTF-8"));  
        out.flush();  
        out.close();
        
        return false;
	}

}
