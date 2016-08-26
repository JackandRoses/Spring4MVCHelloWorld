package com.javahash.spring.service.buildupgradepkg;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface IBuildUpgradePackageService {
	void buildPackage(HttpServletRequest request, List<String> fileList);
}
