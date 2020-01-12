package com.buzz.prototype.business.tables.co;


import org.apache.ibatis.annotations.Mapper;

import com.buzz.prototype.business.base.BaseMapper;
/***********************************************************************************************************************
 * Mapper interface 선언시 Mybaits 가 빈생성을 할 수 있게 @Mapper 를 사용한다.
 * @Mapper 어노테이션이 붙어있으면 Mybatis 가 알아서 빈으로 등록 해준다.
 * Config 에서 @Mapperscan 으로 특정 패키지 를 매퍼로 등록 시켜 줄수도 있으나 PBF 구조에서는 매퍼가 업무별로 분리되어 
 * 있어 사용하기에 부적합 하다.
 * @Mapper 어노테이션을 사용 할수 없는 특수 한 경우에만 @Mapperscan 으로 매퍼가 있는 위치를 지정해 준다
 ***********************************************************************************************************************/
@Mapper
public interface TBCO1001Mapper extends BaseMapper<TBCO1001Vo> {
	//TO-DO BaseMapper 에 정의된 메소드 외에 별도 작성 해야 하는경우 아래에 기술 할 것
}
