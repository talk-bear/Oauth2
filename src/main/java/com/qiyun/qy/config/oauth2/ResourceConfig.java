package com.qiyun.qy.config.oauth2;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration  
@EnableResourceServer 
public class ResourceConfig extends ResourceServerConfigurerAdapter {
	
	/*private String RESOURCE_ID = "RESOURCE_ID";

	@Override  
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception { 
        resources.resourceId(RESOURCE_ID);
    } */
	
    @Override  
    public void configure(HttpSecurity http) throws Exception {  
        http
        //.anonymous().disable()//不允许匿名访问api
        .requestMatchers().antMatchers("/api/**").and()
        .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/api/**").access("#oauth2.hasScope('read')")
            .antMatchers(HttpMethod.POST, "/api/**").access("#oauth2.hasScope('write')");
        //.and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
        //.and().httpBasic().disable();
    }  
	
}
