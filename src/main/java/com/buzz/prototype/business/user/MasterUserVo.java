package com.buzz.prototype.business.user;

import lombok.Getter;
import lombok.Setter;

/**************************************************************************************************************************
 * Table 에 1:1 매핑되는 Vo 정의
 * 네이밍 규칙 : Master테이블명Vo.java ( ex : user 테이블의 Vo -> MasterUserVo.java)
 * Read Only 특성
 * Write 속성은 DTO 만 가진다(별도의 로직을 통해서 원본 데이터를 훼손하지 않는다)
 * Lombck을 사용 한다
 * @Builder 는 Class 레벨이 아닌 생성자 에 선언 해준다(클래스 선언시 모든 멤버 접근 가능)
 * ************************************************************************************************************************/
@Setter
@Getter
public class MasterUserVo {

	private String userId;
	private String userPass;
}
