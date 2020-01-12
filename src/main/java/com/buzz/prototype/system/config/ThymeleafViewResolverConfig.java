package com.buzz.prototype.system.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import nz.net.ultraq.thymeleaf.LayoutDialect;

/*=============================================================================
 * Spring5 에 맞춘 ThymeleafViewResolverConfig 설정 클래스
 * Spring Thymeleaf 설정은 applcation.properties 에서 정의 하거나 Config 에서 한다
 * (STS 프로젝트 설정시 Thymeleaf 를 포함시켜버리면 별다른 설정 없이도 작동은 한다.)
 * 환경파일과 Config 파일에 모두 환경설정이 된 경우 환경설정 옵션은 무시되고
 * Config 파일에 설정된 옵션이 적용된다 ( 환경파일 로딩 후 Cofing 파일 로딩)
=============================================================================*/

@Configuration
public class ThymeleafViewResolverConfig {
	@Autowired
	private ApplicationContext applicationContext;
	
	@Value("${thymeleaf.cache}") 
    private boolean isCache;
	
	/*=============================================================================
	 *  SpringResourceTemplateResolver 의 역활
	 *  캐쉬설정 - 개발모드:false , 운영모드 : true 
 	=============================================================================*/
	@Bean
	public SpringResourceTemplateResolver templateResolver(){
	    // SpringResourceTemplateResolver automatically integrates with Spring's own
	    // resource resolution infrastructure, which is highly recommended.
	    SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
	    templateResolver.setApplicationContext(this.applicationContext);	    
	    
	    //템플릿파일 기본위치
	    templateResolver.setPrefix("classpath:templates/");
	    //템플릿파일 확장자 지정
	    templateResolver.setSuffix(".html");
	    // HTML is the default value, added here for the sake of clarity.
	    templateResolver.setTemplateMode(TemplateMode.HTML);
	    // Template cache is true by default. Set to false if you want
	    // templates to be automatically updated when modified.
	    templateResolver.setCacheable(isCache);
	    return templateResolver;
	}
	
	/*=============================================================================
	 *  layout:decorator 를 통한 소스 재사용성을 높이기 위해 layout Dialect 지정
	 *  spring-boot-starter-thymeleaf 1.X 버전에서는 nz.net.ultraq.thymeleaf.LayoutDialect
	 *  가 종속성으로 포함되어 있지만 Spring 5,spring-boot-starter-thymeleaf 2.X 
	 *  버전에서는 별도로 종속성을 추가해서 사용해야 한다. 
 	=============================================================================*/
	@Bean
	public SpringTemplateEngine templateEngine(){
	    // SpringTemplateEngine automatically applies SpringStandardDialect and
	    // enables Spring's own MessageSource message resolution mechanisms.
	    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	    templateEngine.setTemplateResolver(templateResolver());
	    // Enabling the SpringEL compiler with Spring 4.2.4 or newer can
	    // speed up execution in most scenarios, but might be incompatible
	    // with specific cases when expressions in one template are reused
	    // across different data types, so this flag is "false" by default
	    // for safer backwards compatibility.
	    templateEngine.setEnableSpringELCompiler(true);
	    //layout Dialect 지정	    
	    templateEngine.addDialect(layoutDialect() );	    
	    return templateEngine;
	}
	
	@Bean
	public LayoutDialect layoutDialect() {
	    return new LayoutDialect();
	}
	
	/*=============================================================================
	 *  ViewResolver 의 역활
	 *  Controller(RestController 아님)에서 문자열을 리턴할때 해당 html 페이지를
	 *  매핑하여 반환
	 *  뷰오브젝트를 넘겨주는것 보다 뷰이름을 Controller 에서 넘겨주고 viewResolver
	 *  에서 처리 하는것이 성능면에서 유리
 	=============================================================================*/
	@Bean
	public ThymeleafViewResolver viewResolver(){
	    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
	    viewResolver.setTemplateEngine(templateEngine());
	    // NOTE 'order' and 'viewNames' are optional
	    viewResolver.setOrder(1);
	    viewResolver.setViewNames(new String[] {".html", ".xhtml"});
	    return viewResolver;
	}
}
