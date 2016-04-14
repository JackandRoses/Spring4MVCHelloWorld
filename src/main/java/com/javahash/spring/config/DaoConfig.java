package com.javahash.spring.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.javahash.spring.service.Ifuck;

@Configuration
@ComponentScan({ "com.javahash.spring" })
@PropertySource({ "classpath:/db.properties", "classpath:/host.properties" })
public class DaoConfig {

	@Autowired
	private Environment env;

	private Ifuck f;

	@Autowired
	public DaoConfig(Ifuck f) {
		this.f = f;
		System.out.println(f.a);
	}

	/**
	 * 有了自定义的构造函数后，需要明确写出默认构造函数，不然spring会报错
	 */
	public DaoConfig() {
	}

	// DB 配置
	private static final String DB_DRIVER = "jdbc.driver";
	private static final String DB_URL = "jdbc.url";
	private static final String DB_USERNAME = "jdbc.username";
	private static final String DB_PASSWORD = "jdbc.password";

	@Bean(name = "myDataSource")
	public DriverManagerDataSource DataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty(DB_DRIVER));
		dataSource.setUrl(env.getProperty(DB_URL));
		dataSource.setUsername(env.getProperty(DB_USERNAME));
		dataSource.setPassword(env.getProperty(DB_PASSWORD));
		return dataSource;
	}

	@Bean(name = "myJdbcTemplate")
	@Qualifier("myJdbcTemplate")
	public JdbcTemplate JdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
}
