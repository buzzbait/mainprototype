package com.buzz.prototype.business.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*==============================================================================================================
 * Controller 명명규칙(로직담당)
 * 모두 소문자로 구성 한다
 * annotation 은 RestController 이다
 * Api업무영Controller(Api 로 시작하는 Controller 는 Json Object 를 리턴 하는 컨트롤러이다)
 * root uri 는 /api/업무명 로 시작 한다. 
==============================================================================================================*/
@RestController
@RequestMapping("/api/user")
public class ApiUserController {

	
}
