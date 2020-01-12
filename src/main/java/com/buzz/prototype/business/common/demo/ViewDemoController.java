package com.buzz.prototype.business.common.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/***********************************************************************************************************************
 * 	테스트를 위한 패키지로서 배포시에는 제외한다.
 *  com.buzz.prototype.business.common.demo 패키지는 maven,gadle 배포시 exclude 한다.
 ***********************************************************************************************************************/
@Controller
@RequestMapping("/view/demo")
public class ViewDemoController {

	//ex)http://localhost:8080/view/demo/content
	@RequestMapping("/content")
    public String demo( Model model) {
		
        return "user/demo_content"; //view
    }
}
