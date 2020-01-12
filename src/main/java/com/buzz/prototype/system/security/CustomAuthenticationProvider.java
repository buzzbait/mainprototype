package com.buzz.prototype.system.security;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);
	
	@Autowired
	private CustomUserDetailService userDetailService;
	
	/******************************************************************************************************
	 * 인증성공시 Authentication(인증객체) 를 리턴함
	 * Spring Security 는 리턴된 Authentication 객체를 SecurityContextHolder 를 통해 SecurityContext 에저장
	 * SecurityContext 는 상태유지를 위해 Session 에 저장 된다.
	 * SecurityContextHolder 는 세션보관소가 tomcat,jdbc,redis 에 상관 없이 일관된 인터페이스를 제공한다
	 ******************************************************************************************************/
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
	
		//로그인 인증 진행		
		Collection<? extends GrantedAuthority> authorities = null;
				
		String userName = authentication.getName();
		String password = (String) authentication.getCredentials();
		
		logger.debug("{} : 사용자 인증 시작...........",userName);
		
		CustomUserDetailDto userDetailDto = (CustomUserDetailDto)userDetailService.loadUserByUsername(userName);
		
		//저장소에서 가져온 유저정보의 검증 체크
		//패스워드 검증,유효기간 검증,활성화 검증
		
		
		//정상적으로 return 하는 경우 successHandler 가 호출 된다.
				
		return new UsernamePasswordAuthenticationToken(userName, password, authorities);
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);		
	}
}
