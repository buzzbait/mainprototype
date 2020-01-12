package com.buzz.prototype.business.common.menu;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buzz.prototype.business.base.BaseService;
import com.buzz.prototype.business.base.ResultHashMap;
import com.buzz.prototype.business.tables.co.TBCO1001Mapper;
import com.buzz.prototype.business.tables.co.TBCO1001Vo;
import com.buzz.prototype.constants.RestResponseConstants;

@Service
public class MenuService extends BaseService {
	
	@Autowired
	private TBCO1001Mapper _tbco1001Mappper;
	
	@Autowired
	private MenuMapper _menuMapper;
	
	public HashMap<String,Object> getUserMenu() {
		HashMap<String,Object> reponseData = new HashMap<String,Object>();
		TBCO1001Vo paramVo = TBCO1001Vo.builder()							
							.mnuId(10)
							.build();
		
		TBCO1001Vo tableVo  = _tbco1001Mappper.selectOne(paramVo);
		reponseData.put(RestResponseConstants.REST_DATA, tableVo);
		reponseData.put(RestResponseConstants.RESULT_CODE,0);
		return reponseData;
	}
	
	public HashMap<String,Object> getUserMenuById() {
		HashMap<String,Object> reponseData = new HashMap<String,Object>();
		
		ResultHashMap hashMap = _menuMapper.selectMenuById(10);
		reponseData.put(RestResponseConstants.REST_DATA, hashMap);
		reponseData.put(RestResponseConstants.RESULT_CODE,0);
		return reponseData;
	}
}
