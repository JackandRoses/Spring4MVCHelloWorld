/*
 * Full @Configuration vs 'lite' @Beans mode? When @Bean methods are
 * declared within classes that are not annotated with @Configuration they
 * are referred to as being processed in a 'lite' mode. For example, bean
 * methods declared in a @Component or even in a plain old class will be
 * considered 'lite'. Unlike full @Configuration, lite @Bean methods cannot
 * easily declare inter-bean dependencies. Usually one @Bean method should
 * not invoke another @Bean method when operating in 'lite' mode. Only using
 * 
 * @Bean methods within @Configuration classes is a recommended approach of
 * ensuring that 'full' mode is always used. This will prevent the same
 * 
 * @Bean method from accidentally being invoked multiple times and helps to
 * reduce subtle bugs that can be hard to track down when operating in
 * 'lite' mode.
 * 
 * Annotating a class with @Configuration indicates that its primary purpose
 * is as a source of bean definitions. Furthermore, @Configuration classes
 * allow inter-bean dependencies to be defined by simply calling other @Bean
 * methods in the same class. The simplest possible @Configuration class
 * would read as follows
 * 
 * 总之是在@configuration注解的class里，@bean才是"full"mode,要引用其他bean，直接call
 * 定义其他bean的method
 */
package com.javahash.spring.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

//@Configuration
public class MQConfig {
	/**
	 * 配置连接工厂
	 * 
	 * @return 连接工厂
	 */
	// @Bean(name = "connectionFactory")
	public ActiveMQConnectionFactory setActiveMQConnectionFactory() {
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
		activeMQConnectionFactory.setBrokerURL("tcp://localhost:61616");
		return activeMQConnectionFactory;
	}

	/**
	 * 设置消息目的地 (queue)
	 * 
	 * @return 消息目的地
	 */
	// @Bean(name = "queue")
	public ActiveMQQueue setActiveMQQueue() {
		return new ActiveMQQueue("spitter.alert.queue");
	}

	/**
	 * 设置消息目的地 (topic)
	 * 
	 * @return 消息目的地
	 */
	// @Bean(name = "topic")
	public ActiveMQTopic setActiveMQTopic() {
		return new ActiveMQTopic("spitter.alert.topic");
	}

	/**
	 * 设置 JMS模板
	 * 
	 * @return JMS模板
	 */
	// @Bean(name = "jmsTemplate")
	public JmsTemplate setJmsTemplate() {
		JmsTemplate jmsTemplate = new JmsTemplate();
		// 设置连接工厂
		jmsTemplate.setConnectionFactory(setActiveMQConnectionFactory());
		// 设置默认目的地
		jmsTemplate.setDefaultDestinationName("spitter.alert.queue");
		return jmsTemplate;
	}

}
