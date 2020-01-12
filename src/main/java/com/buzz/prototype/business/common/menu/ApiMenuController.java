package com.buzz.prototype.business.common.menu;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/*******************************************************************************************************************
 * RestController 작성 규칙
 * 1. Return Type는 ResponseEntity<HashMap<String, Object>> 를 원칙으로 한다
 * 2. throws Exception 사용금지
 * 3. Try~catch 사용금지
 * 4. ResponseEntity 에 저장할 HashMap 객체는 Service Layer 에서 새성한다
 ******************************************************************************************************************/
@RestController
@RequestMapping("/api/menu")
public class ApiMenuController {
	
	@Autowired
	private MenuService  _menuService;
	
	@RequestMapping(value ="/usermenu" , method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getUserMenuList( ) {
		ResponseEntity<HashMap<String, Object>> entity = null;
		//서비스 호출
		//HashMap<String, Object> resultMap = _menuService.getUserMenu(); 
		HashMap<String, Object> resultMap = _menuService.getUserMenuById();
		//ResponseEntity 에 결과데이터 적재
		entity = new ResponseEntity<HashMap<String, Object>>(resultMap,HttpStatus.OK);
        return entity;
    }
}
