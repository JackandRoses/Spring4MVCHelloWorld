package com.javahash.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javahash.spring.mapper.SelectUserMapper;
import com.javahash.spring.po.User;

@Controller
public class TestController {
	@Autowired
	private SelectUserMapper selectUserMapper;

	@RequestMapping("/test")
	public String search(@ModelAttribute @RequestParam(value = "id", required = false, defaultValue = "100000") Integer id, Model model) {
		return "test";
	}

	@ModelAttribute
	public void setNameByUserId(@ModelAttribute @RequestParam(value = "id", required = false, defaultValue = "100000") Integer id, Model model) {
		User user = selectUserMapper.selectUserById(id);
		model.addAttribute("name", user.getUserName());
	}
}
