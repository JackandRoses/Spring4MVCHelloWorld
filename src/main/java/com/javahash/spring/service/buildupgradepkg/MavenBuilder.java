package com.javahash.spring.service.buildupgradepkg;

import java.io.IOException;
import java.util.List;

public class MavenBuilder {
	public final static String CD_COMMOND = "cmd /c start cmd.exe /K \"cd /d ";
	public final static String MVN_INSTALL = " && mvn clean install\"";

	public static void callBuild(List<String> dirList) {
		String dir = "D:/dev_env/insightview/扬州/dev/01 trunk/02-Source/insightview-webapps/insightview/platform/insightview-platform-security";
		String command = CD_COMMOND + dir + MVN_INSTALL;
		try {
			Process process = Runtime.getRuntime().exec(command);
			process.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
