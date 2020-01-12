package com.buzz.prototype.system.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class LoginFailureHandler implements AuthenticationFailureHandler {
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException, ServletException {
				
		String redirectURL = "/login?error=1";
		
		if(authenticationException instanceof AuthenticationServiceException){
			//AuthenticationServiceException 처리
			response.sendRedirect(request.getContextPath() + "/noAuthor");
		} else if(authenticationException instanceof Exception){
			//일반적인 Exception
			
			response.sendRedirect(request.getContextPath() + redirectURL);
		} else if(authenticationException instanceof UsernameNotFoundException) {
			//사용자 정보 없음
			response.sendRedirect(request.getContextPath() + "/login?error=1");
		}else {
			//검증하지 못한 Exception
			response.sendRedirect(request.getContextPath() + "/noAuthor");
		}
		//to-do  로그인실패로그 추가
	}
}
