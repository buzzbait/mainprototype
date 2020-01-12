package com.buzz.prototype.business.common.menu;

import org.apache.ibatis.annotations.Mapper;

import com.buzz.prototype.business.base.ResultHashMap;

@Mapper
public interface MenuMapper {
	ResultHashMap selectMenuById(int menuId);
}
