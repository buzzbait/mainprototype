package com.buzz.prototype.system.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.buzz.prototype.constants.SecurityConstants;

/**************************************************************************************************************************
 * SavedRequestAwareAuthenticationSuccessHandler makes use of the saved request stored in the session. 
 * After a successful login, users will be redirected to the URL saved in the original request.
 * For form login, SavedRequestAwareAuthenticationSuccessHandler is used as the default AuthenticationSuccessHandler.
 * ************************************************************************************************************************/
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	public LoginSuccessHandler() {
		//세션이 종료후 재접속후에 이전페이지로 가지 않고 항상 DefaultTargetUrl 로 이동한다.
		//별다른 설정이 없으면 DefaultTargetUrl 은 / 이다
		setAlwaysUseDefaultTargetUrl(true);	
    }
	
	/**********************************************************************************************************************
	 * 유저정보를 세션에 저장 한다
	 **********************************************************************************************************************/
	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException,ServletException{
    	
		HttpSession session = request.getSession();
		
		//session.setAttribute(SecurityConstants.USER_SESSION_ATTR, userInfoVo); // Session에 사용자 객체 등록
		
		super.onAuthenticationSuccess(request, response, authentication);
        
    }
}
