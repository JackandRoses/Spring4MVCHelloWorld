package com.javahash.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javahash.spring.Dao.OpUserDAO;

@Controller
public class TestController {
	@Autowired
	private OpUserDAO opUserDAO;

	@RequestMapping("/test")
	public String search(@ModelAttribute @RequestParam(value = "id", required = false, defaultValue = "100000") Integer id, Model model) {
		return "test";
	}

	@ModelAttribute
	public void setNameByUserId(@ModelAttribute @RequestParam(value = "id", required = false, defaultValue = "100000") Integer id, Model model) {
		String userName = opUserDAO.selectUserNameById(id);
		model.addAttribute("name", userName);
	}
}
