package com.buzz.prototype.system.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


/**************************************************************************************************************************
 * UserDetails 는 Spring Security 에서 제공하는 사용자정보 객체이다 
 * 프로젝트에서 필요한 부가 인증정보가 필요한 경우 UserDetails 를 상속받아서 구현 한다
 * ************************************************************************************************************************/
@SuppressWarnings("serial")
@Getter
@Setter
public class CustomUserDetailDto implements UserDetails {

	private String AUTHORITY;
	private String userId;
	private String userPass;
	private boolean isAccountExpired = false;
	private boolean isAccountLocked = false;
	private boolean isCredentialsExpired  = false;
	private boolean isEnable = true;
		
	@Override
    public String getPassword() {
        return userPass;
    }
 
    @Override
    public String getUsername() {
        return userId;
    }

	
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
        auth.add(new SimpleGrantedAuthority(AUTHORITY));
        return auth;
    }
	
	@Override
    public boolean isAccountNonExpired() {
        return isAccountExpired;
    }
 
    @Override
    public boolean isAccountNonLocked() {
        return isAccountLocked;
    }
 
    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsExpired;
    }
 
    @Override
    public boolean isEnabled() {
        return isEnable;
    }

    @Builder
    public CustomUserDetailDto(String userId,String userPass) {
    	this.userId = userId;
    	this.userPass = userPass;
    	
    	this.isAccountExpired = false;
    	this.isAccountLocked = false;
    	this.isCredentialsExpired  = false;
    	this.isEnable = true;
    }
	
}
