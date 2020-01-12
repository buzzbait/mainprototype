package com.buzz.prototype.system.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.buzz.prototype.business.user.MasterUserVo;
import com.buzz.prototype.business.user.UserService;

/**************************************************************************************************************************
 * UserDetailsService 는 Spring Security 에서 저장소에서 유저의 정보를 조회 하는 역활을 하는 서비스 이다 
 * loadUserByUsername 메소드를 오버라이드 하여 저장소에서 유정정보를 조회 한다
 * 유저 검증은 하지 않고 단순하게 유저정보만을 리턴하며 조회가 안되는 경우 UsernameNotFoundException 을 발생시킨다
 * ************************************************************************************************************************/
@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
    	
		//사용자정보 조회
		MasterUserVo userVo = userService.getMatserUserInfo(userName);
		CustomUserDetailDto userDetailDto = CustomUserDetailDto.builder()
																 .userId(userVo.getUserId())
																 .userPass(userVo.getUserPass())
																 .build();
		
    	return userDetailDto;
    }
	    
	
	public Collection<GrantedAuthority> getAuthorities(String userName) {
		/*List<String> string_authorities = userMapper.readAuthority(username);
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (String authority : string_authorities) {
              authorities.add(new SimpleGrantedAuthority(authority));
        }
        return authorities;*/
		
		return null;
    }
 
    
}
