package com.javahash.spring.service.buildupgradepkg;

import java.io.File;

public class GenerateDir {
	public static void main(String[] args) {
		String dirname = "\\Fablesoft\\InsightView\\third\\apache-tomcat-insightview\\webapps\\insightview";
		String parentDir = "C:\\Users\\Administrator\\Desktop\\";
		File f = new File(parentDir+dirname);
		f.mkdirs();
	}
}
