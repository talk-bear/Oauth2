package com.qiyun.qy.service.impl.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.qiyun.qy.entity.User;

@Service("qiYunSecurityProvider")
public class SecurityProvider implements AuthenticationProvider {
	
	@Autowired
	@Qualifier("qiYunUserService")
    private UserDetailsService userService;
	
	/**
	 * 拓展验证
	 * @return
	 */
	public void expendAuth() throws AuthenticationException {
		
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		//authentication.getPrincipal();
		
		String name = authentication.getName();
        String rawPassword = authentication.getCredentials().toString();
        
        User user = (User) userService.loadUserByUsername(name);
        String sqlPassword = user.getPassword();
        
        //加密
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(rawPassword, sqlPassword)) {
            throw new BadCredentialsException("用户名或密码错误");
        }
   
        expendAuth();
        	
        List<GrantedAuthority> grantedAuths = user.getAuthorities();
        return new UsernamePasswordAuthenticationToken(name, sqlPassword, grantedAuths);
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	

}
