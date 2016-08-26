package com.javahash.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javahash.spring.service.buildupgradepkg.IBuildUpgradePackageService;

@Controller
@RequestMapping("/buildUpgradePackage")
public class BuildUpgradePackageController {
	@Autowired
	public IBuildUpgradePackageService buildUpgradePackageService;

	@RequestMapping(value = "/toBuild")
	public String toBuild(HttpServletRequest request) {
		return "toBuild";
	}
	
	@RequestMapping(value = "/toXXNet")
	public String toXXNet(HttpServletRequest request) {
		return "xxnet/xxnet";
	}
}

