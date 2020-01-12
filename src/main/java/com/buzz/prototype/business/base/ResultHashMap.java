package com.buzz.prototype.business.base;

import java.util.HashMap;

import org.springframework.jdbc.support.JdbcUtils;

/***********************************************************************************************************************
 * Mybatis 에서 DB결과값을 담을 HashMap
 * 일반적으로 DB 필드가 대문자로 되어 있어 HashMap 에 담길때 필드명이 대문자로 들어가기 camelCase 처리를 위함 
 ***********************************************************************************************************************/
public class ResultHashMap extends HashMap {	
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
    @Override
    public Object put(Object key,Object value){
        return super.put(JdbcUtils.convertUnderscoreNameToPropertyName((String)key),value);
    }
}
