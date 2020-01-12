package com.buzz.prototype.business.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/*==============================================================================================================
 * Controller 명명규칙(뷰담당)
 * 모두 소문자로 구성한다
 * annotation 은 Controller 이다
 * View업무영Controller(View 로 시작하는 Controller 는 ModelView 를 리턴 하는 컨트롤러이다)
 * root uri 는 /view/업무명 로 시작 한다.
 * 팝업화면은 sub uri 에 popup 을 추가 한다 ex)http://localhost:8080/view/user/popup/newuser
==============================================================================================================*/
@Controller
@RequestMapping("/view/user")
public class ViewUserController {

	//ex)http://localhost:8080/view/user/login
	@RequestMapping("/login")
    public String hello( Model model) {
		
        return "user/login"; //view
    }
	
	//ex)http://localhost:8080/view/user/login
	@RequestMapping("/demo")
    public String demo( Model model) {
		
        return "user/demo_content"; //view
    }
}
