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
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

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

	@Component("DataSrc")
	static class CustomerRoutingDataSource extends AbstractRoutingDataSource {

		@Override
		protected Object determineCurrentLookupKey() {
			return CustomerContextHolder.getType();
		}

		@Autowired
		public void setDefaultTargetDataSource(@Qualifier("myDataSource1") DataSource defaultTargetDataSource) {
			super.setDefaultTargetDataSource(defaultTargetDataSource);
		}
	}

	static class CustomerContextHolder {
		private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

		public static void setType(String type) {
			Assert.notNull(type, "不能为空");
			contextHolder.set(type);
		}

		public static String getType() {
			return contextHolder.get();
		}

	}

	// DB 配置
	private static final String DB_DRIVER = "jdbc.driver";
	private static final String DB_URL = "jdbc.url";
	private static final String DB_USERNAME = "jdbc.username";
	private static final String DB_PASSWORD = "jdbc.password";
	private static final String DB_URL2 = "jdbc.url2";

	@Bean(name = "myDataSource1")
	public DriverManagerDataSource myDataSource1() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty(DB_DRIVER));
		dataSource.setUrl(env.getProperty(DB_URL));
		dataSource.setUsername(env.getProperty(DB_USERNAME));
		dataSource.setPassword(env.getProperty(DB_PASSWORD));
		return dataSource;
	}

	@Bean(name = "myDataSource2")
	public DriverManagerDataSource myDataSource2() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty(DB_DRIVER));
		dataSource.setUrl(env.getProperty(DB_URL2));
		dataSource.setUsername(env.getProperty(DB_USERNAME));
		dataSource.setPassword(env.getProperty(DB_PASSWORD));
		return dataSource;
	}


	@Bean(name = "myJdbcTemplate")
	@Qualifier("myJdbcTemplate")
	public JdbcTemplate JdbcTemplate(@Qualifier("DataSrc") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
}
