package com.buzz.prototype;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/*========================================================================================================
 * 	Spring Boot 는 프로젝트 구성이 DB사용을 체크 하면 기본설정으로 DB연결을 시도 하기 때문에 연결설정 없이
 *  프로젝트를 구동하기 위해서는 @EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
 *  를 사용 한다
 *  [주의]Controller,Service 등 ScanComponent 대상은 root 패키지인 com.buzz.prototype 에 위치 해야 한다 
 ======================================================================================================== */
@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class}) 
public class Prototype01Application {

	public static void main(String[] args) {
		SpringApplication.run(Prototype01Application.class, args);
	}

}
