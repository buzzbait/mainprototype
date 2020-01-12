package com.buzz.prototype.system.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/*=============================================================================
 *  Spring Security 역활과 사용목적  
=============================================================================*/

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity -> Service layer 에서의 보완 활성화
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) 
public class SecurityConfig extends WebSecurityConfigurerAdapter  {

	@Autowired
	private CustomAuthenticationProvider  authProvider;
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
    	//TO-DO : AuthenticationProvider 지정
        auth.authenticationProvider(authProvider);
    }
	
	/******************************************************************************************************************
	 *  리소스 또는 요청에 대한 접근 권한 설정 
	 *  [리소스 접근 설정]
	 *  anyRequest().authenticated() -> 모든요청은 로그인 상태에서만 접근이 가능 하다(로그인 판단여부는 스프링이 처리)
	 *  
	 *  [폼기반 로그인 설정]
	 *  formLogin() -> Form기반의 로그인 처리
	 *  loginPage("/login") -> 로그인 페이지 URL 지정 
	 *  loginProcessingUrl("/userauthcheck") -> 로그인폼에서 /userauthcheck POST 요청이 오는경우 Security Filter 에서 해당 요청을 가로채서 인증을 진행
	 *  successHandler 인증성공후 수행되는 핸들러 지정
 	******************************************************************************************************************/
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http.exceptionHandling();
			//.authenticationEntryPoint();
		
		//리소스 또는 요청에 대한 접근권한 설정
		http.authorizeRequests()
    		.antMatchers("/login", "/login?**", "/images/**", "/bower_components/**", "/dist/**", "/plugins/**").permitAll()
    		.antMatchers("/logout").permitAll()
    		.antMatchers("/accessDenied").permitAll();    	
    	//	.anyRequest().authenticated();
		
		//폼기반 로그인 설정    	
		http.formLogin()
        	.loginPage("/login")           
        	.loginProcessingUrl("/userauthcheck") 
        	.successHandler(loginSuccessHandler())            
        	.failureUrl("/login?error5")        
        	.failureHandler(loginFailureHandler())
        	.permitAll();
	
		http.logout()
        	.logoutSuccessUrl("/login");		
        
		http.headers()
        	.frameOptions()
        	.disable();
	
		http.csrf()
			.disable();
	}
	/*******************************************************************************************************
	 * 로그인이 성공하는 경우 실행되는 핸들러빈 생성
	 *******************************************************************************************************/
	@Bean
    public AuthenticationSuccessHandler loginSuccessHandler() throws Exception {
        return new LoginSuccessHandler();
    }
	
	/*******************************************************************************************************
	 * 로그인이 실패하는 경우 실행되는 핸들러빈 생성
	 *******************************************************************************************************/
	@Bean
	public LoginFailureHandler loginFailureHandler() throws Exception {
		return new LoginFailureHandler();
	}
}
