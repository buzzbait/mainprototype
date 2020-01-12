package com.buzz.prototype.business.user;

import org.springframework.stereotype.Service;

/**************************************************************************************************************************
 * Service Layer 정의
 * 네이밍 규칙 : 업무명Service.java ( ex : user 관련 서비스 -> UserService.java)
 * 모든 비지니스 로직은 Service Layer 에서 구현함을 원칙으로 한다
 * ************************************************************************************************************************/
@Service
public class UserService {

	public MasterUserVo getMatserUserInfo(String userName) {
		MasterUserVo userVo = new MasterUserVo();
		//저장소를 통해서 데이터 조회
		
		userVo.setUserId("demo");
		userVo.setUserPass("demo");
		
		return userVo;
	}
	
}
