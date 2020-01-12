package com.buzz.prototype.system.config;


import org.springframework.context.annotation.Configuration;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*===========================================================================================================
 * 이전버전의 extends WebMvcConfigurerAdapter 대체(Spring 5.0 에서 WebMvcConfigurerAdapter Deprecated 됨)
 * 자바 8이 기본 버전(baseline)이 되면서 Java 8의 Interface의 기능 중 하나인 default method 기능을 이용하면 
 * 구지 추상 클래스를 쓰지 않아도 되기 때문이다.

 =========================================================================================================== */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{
		
    private static final int STATIC_CACHAE_PERIOD = 31556926;
	
	//================================== viewController 설정 ===================================================
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
		//loign 컨트롤러에 대한 View 지정
		registry.addViewController("/login").setViewName("/user/login");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);

    }
	
	//================================== resourceHandler 설정 ===================================================
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	    	    	
        registry.addResourceHandler("/bower_components/**").addResourceLocations("classpath:/static/bower_components/").setCachePeriod(STATIC_CACHAE_PERIOD);
        registry.addResourceHandler("/dist/**").addResourceLocations("classpath:/static/dist/").setCachePeriod(STATIC_CACHAE_PERIOD);
        registry.addResourceHandler("/plugins/**").addResourceLocations("classpath:/static/plugins/").setCachePeriod(STATIC_CACHAE_PERIOD);
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/").setCachePeriod(STATIC_CACHAE_PERIOD);
        //WebJars 설정
        registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/").setCachePeriod(STATIC_CACHAE_PERIOD);        
            	
    }	
	
}
