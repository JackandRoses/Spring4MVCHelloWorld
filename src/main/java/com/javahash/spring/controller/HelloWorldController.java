package com.javahash.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javahash.spring.Dao.OpUserDAO;

@Controller
public class HelloWorldController {

	@Autowired
	private OpUserDAO opUserDAO;

	// @Autowired
	// private AlertService AlertService;

	// @Autowired
	// private JmsTemplate jmsTemplate;

	@RequestMapping("/hello")
	public String hello(
			@ModelAttribute @RequestParam(value = "id", required = false, defaultValue = "100000") Integer id,
			Model model) throws InterruptedException {

		// new Thread(new Runnable() {
		// @Override
		// public void run() {
		// try {
		// System.out.println("start receive");
		// // receive()方法一直阻塞,没有价值,用MDP 消息驱动POJO
		// ObjectMessage receivedMessage = (ObjectMessage) jmsTemplate
		// .receive();
		// System.out.println(((Spittle) receivedMessage.getObject())
		// .getVolume());
		// System.out.println("receive success");
		// } catch (JMSException e) {
		// throw JmsUtils.convertJmsAccessException(e);
		// }
		// }
		// }).start();

		return "helloworld";
	}

	/**
	 * 加了@modelAttribute的方法在一个Controller内会先于加了@requestMapping的方法执行
	 * 
	 * @param name
	 * @param model
	 */
	// @ModelAttribute
	// public void populateModel(@RequestParam(value = "name", required = true)
	// String name, Model model) {
	// model.addAttribute("name", name);
	// }
	@ModelAttribute
	public void setNameByUserId(
			@ModelAttribute @RequestParam(value = "id", required = false, defaultValue = "100000") Integer id,
			Model model) {
		String userName = opUserDAO.selectUserNameById(id);
		model.addAttribute("name", userName);
		// Spittle spittle = new Spittle();
		// spittle.setVolume("10");
		// AlertService.sendSpittleAlert(spittle);
	}
}
