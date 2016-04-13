package com.javahash.spring.serviceImpl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Repository;

import com.javahash.spring.po.Spittle;
import com.javahash.spring.service.AlertService;

@Repository
@ComponentScan({ "com.javahash.spring" })
public class AlertServiceImpl implements AlertService {

	@Autowired
	JmsTemplate jmsTemplate;

	@Override
	public void sendSpittleAlert(final Spittle spittle) {
		// 指定消息目的地 默认目的地已经设定
		jmsTemplate.send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				// 创建消息
				return session.createObjectMessage(spittle);
			}
		});
	}

}
