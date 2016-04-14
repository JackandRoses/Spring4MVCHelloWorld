package org.activiti.explorer.conf;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.AbstractFormType;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.explorer.form.MonthFormType;
import org.activiti.explorer.form.ProcessDefinitionFormType;
import org.activiti.explorer.form.UserFormType;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource({ "classpath:/db.properties", "classpath:/host.properties" })
public class ActivitiEngineConfiguration {
	private final Logger log;

	@Autowired
	protected Environment environment;

	public ActivitiEngineConfiguration() {
		this.log = LoggerFactory.getLogger(ActivitiEngineConfiguration.class);
	}

	@SuppressWarnings("unchecked")
	@Bean
	public DataSource dataSource() {
		SimpleDriverDataSource ds = new SimpleDriverDataSource();
		try {
			@SuppressWarnings("rawtypes")
			Class driverClass = Class.forName(this.environment.getProperty("jdbc.driver", "org.h2.Driver"));
			ds.setDriverClass(driverClass);
		} catch (Exception e) {
			this.log.error("Error loading driver class", e);
		}

		ds.setUrl(this.environment.getProperty("jdbc.url", "jdbc:h2:mem:activiti;DB_CLOSE_DELAY=1000"));
		ds.setUsername(this.environment.getProperty("jdbc.username", "sa"));
		ds.setPassword(this.environment.getProperty("jdbc.password", ""));

		return ds;
	}

	@Bean(name = { "transactionManager" })
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(dataSource());
		return transactionManager;
	}

	@Bean(name = { "processEngineFactoryBean" })
	public ProcessEngineFactoryBean processEngineFactoryBean() {
		ProcessEngineFactoryBean factoryBean = new ProcessEngineFactoryBean();
		factoryBean.setProcessEngineConfiguration(processEngineConfiguration());
		return factoryBean;
	}

	@Bean(name = { "processEngine" })
	public ProcessEngine processEngine() {
		try {
			return processEngineFactoryBean().getObject();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Bean(name = { "processEngineConfiguration" })
	public ProcessEngineConfigurationImpl processEngineConfiguration() {
		SpringProcessEngineConfiguration processEngineConfiguration = new SpringProcessEngineConfiguration();
		processEngineConfiguration.setDataSource(dataSource());
		processEngineConfiguration.setDatabaseSchemaUpdate(this.environment.getProperty("engine.schema.update", "true"));
		processEngineConfiguration.setTransactionManager(annotationDrivenTransactionManager());
		processEngineConfiguration.setJobExecutorActivate(Boolean.valueOf(this.environment.getProperty("engine.activate.jobexecutor", "false")).booleanValue());

		processEngineConfiguration.setAsyncExecutorEnabled(Boolean.valueOf(this.environment.getProperty("engine.asyncexecutor.enabled", "true")).booleanValue());

		processEngineConfiguration.setAsyncExecutorActivate(Boolean.valueOf(this.environment.getProperty("engine.asyncexecutor.activate", "true")).booleanValue());

		processEngineConfiguration.setHistory(this.environment.getProperty("engine.history.level", "full"));

		String mailEnabled = this.environment.getProperty("engine.email.enabled");
		if ("true".equals(mailEnabled)) {
			processEngineConfiguration.setMailServerHost(this.environment.getProperty("engine.email.host"));
			int emailPort = 1025;
			String emailPortProperty = this.environment.getProperty("engine.email.port");
			if (StringUtils.isNotEmpty(emailPortProperty))
				emailPort = Integer.valueOf(emailPortProperty).intValue();

			processEngineConfiguration.setMailServerPort(emailPort);
			String emailUsernameProperty = this.environment.getProperty("engine.email.username");
			if (StringUtils.isNotEmpty(emailUsernameProperty)) {
				processEngineConfiguration.setMailServerUsername(emailUsernameProperty);
			}

			String emailPasswordProperty = this.environment.getProperty("engine.email.password");
			if (StringUtils.isNotEmpty(emailPasswordProperty))
				processEngineConfiguration.setMailServerPassword(emailPasswordProperty);

		}

		List<AbstractFormType> formTypes = new ArrayList<AbstractFormType>();
		formTypes.add(new UserFormType());
		formTypes.add(new ProcessDefinitionFormType());
		formTypes.add(new MonthFormType());
		processEngineConfiguration.setCustomFormTypes(formTypes);

		return processEngineConfiguration;
	}

	@Bean
	public RepositoryService repositoryService() {
		return processEngine().getRepositoryService();
	}

	@Bean
	public RuntimeService runtimeService() {
		return processEngine().getRuntimeService();
	}

	@Bean
	public TaskService taskService() {
		return processEngine().getTaskService();
	}

	@Bean
	public HistoryService historyService() {
		return processEngine().getHistoryService();
	}

	@Bean
	public FormService formService() {
		return processEngine().getFormService();
	}

	@Bean
	public IdentityService identityService() {
		return processEngine().getIdentityService();
	}

	@Bean
	public ManagementService managementService() {
		return processEngine().getManagementService();
	}
}