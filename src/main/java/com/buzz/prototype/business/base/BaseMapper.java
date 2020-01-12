package com.buzz.prototype.business.base;

/***********************************************************************************************************************
 * 필수적으로 구현해야 하는 메소드
 * Mapper 는 BaseMapper 를 상속 받아야 한다.
 ***********************************************************************************************************************/
public interface BaseMapper<T> {
	int insert(T t);
	int update(T t);
	int delete(T t);
	T selectOne(T t);	
	Iterable<T> selectMulti(T t);	
}
