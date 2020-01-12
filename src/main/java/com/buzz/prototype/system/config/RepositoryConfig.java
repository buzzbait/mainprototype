package com.buzz.prototype.system.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
/***********************************************************************************************************************
 * 데이터 저장소에 대한 설정
 * 1. Database(DBCP) 연결설정
 * 2. Transaction 설정
 * 3. Mybatis 설정
 **********************************************************************************************************************/
@Configuration
@EnableTransactionManagement
public class RepositoryConfig {
	@Autowired
	private ApplicationContext applicationContext;
	
	@Bean
	@ConfigurationProperties(prefix="spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}
	
	@Bean(destroyMethod="close")
	public DataSource dataSource(){
		DataSource dataSource = new HikariDataSource(hikariConfig());
		return dataSource;
	}
	
	@Bean
	public PlatformTransactionManager  transactionManager(){
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource());
		transactionManager.setGlobalRollbackOnParticipationFailure(false);
		return transactionManager;
	}
	
	/******************************************************************************************************************
	 * 	MyBatis 설정파일을 바탕으로 SqlSessionFactory를 생성
	 ******************************************************************************************************************/
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		//1. 매퍼 설정
		//sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mapper/**/*.xml"));
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath*:/mapper/**/*.xml"));
		
		//2. Config 설정
		sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:/mybatis-config.xml"));
		
		//3. alias 설정
		sqlSessionFactoryBean.setTypeAliasesPackage("com.buzz.prototype.business");
		return sqlSessionFactoryBean.getObject();
	}
	
	/******************************************************************************************************************
	 * 	Mybatis 설정
	 * 핵심적인 역할을 하는 클래스로서 SQL 실행이나 트랜잭션 관리를 한다.
	 * SqlSession 인터페이스를 구현해야 하며, Thread-safe 하다.
	 ******************************************************************************************************************/	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
}

