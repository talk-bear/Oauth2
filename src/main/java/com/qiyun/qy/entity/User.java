package com.qiyun.qy.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
public class User implements UserDetails {
	
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phone;
    
    private Integer locked;
    private Integer accountExpired;
    private Integer credentialsExpired;
    private List<Role> roles;
    
    public User() {
    	this.locked = 0;
    	this.accountExpired = 0;
    	this.credentialsExpired = 0;
    	Role role = new Role("user");
    	List<Role> roles = new ArrayList<Role>();
    	roles.add(role);
    	this.roles = roles;
    }
    
	@Override
	public int hashCode() {
		return username.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof User) || (obj == null)) {
			return false;
		}

		User user = (User) obj;
		
		boolean nameEqual = username.equals(user.getUsername());
		boolean emailEqual = email.equals(user.getEmail());
		boolean phoneEqual = phone.equals(user.getPhone());
		
		return (nameEqual || emailEqual || phoneEqual);
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("账户：");
		sb.append(username);
		if (!isAccountNonExpired()) {
			sb.append("，登录名失效。");
		}
		if (!isAccountNonLocked()) {
			sb.append("，被锁定。");
		}
		
		if (!isCredentialsNonExpired()) {
			sb.append("，认证过期。");
		}
		
		return sb.toString();
	}

	@Override
	public List<GrantedAuthority> getAuthorities() {
		
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		for (Role role : roles) {
			list.add(new GrantedAuth(role.getEname()));
		}
		
		return list;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		if (this.accountExpired == 1) {
			return false;
		}
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		if (this.locked == 1) {
			return false;
		}
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		if (this.credentialsExpired == 1) {
			return false;
		}
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getLocked() {
		return locked;
	}

	public void setLocked(Integer locked) {
		this.locked = locked;
	}

	public Integer getAccountExpired() {
		return accountExpired;
	}

	public void setAccountExpired(Integer accountExpired) {
		this.accountExpired = accountExpired;
	}

	public Integer getCredentialsExpired() {
		return credentialsExpired;
	}

	public void setCredentialsExpired(Integer credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
