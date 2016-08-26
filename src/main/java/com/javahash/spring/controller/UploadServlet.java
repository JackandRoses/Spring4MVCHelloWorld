package com.javahash.spring.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/buildUpgradePackage/receiveFile")
@MultipartConfig
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)  {
		// Retrieves <input type="file" name="file">
		Part filePart;
		try {
			filePart = request.getPart("inputFile[]");
		// String fileName =
		// Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
		// // MSIE fix.
	    InputStream fileContentIS = filePart.getInputStream();
	    Set<String> dispositions = (HashSet<String>) filePart.getHeaders("content-disposition");
	    String fullFileName = "";
	    Iterator<String> it = dispositions.iterator();
	    while(it.hasNext()){
	    	String element =it.next();
	    	fullFileName = element.substring(element.lastIndexOf("=")+1);
	    };
		String fileNameForSaving = generateFileNameForSaving(fullFileName);
		String projectRealPath = this.getServletConfig().getServletContext().getRealPath("/");
		File dir2save = new File(projectRealPath+"//uploadFiles//");
		if(!dir2save.isDirectory()){dir2save.mkdir();}
		File file2save = new File(projectRealPath+"//uploadFiles//"+fileNameForSaving);
		FileOutputStream fos = new FileOutputStream(file2save);
		byte[] b = new byte[1024];
		int len = 0;
		while((len = fileContentIS.read(b))!=-1){
			fos.write(b,0,len);//len 是实际读取的字节数http://zhidao.baidu.com/question/447251921.html
		}
		fos.flush();
		fos.close();
		fileContentIS.close();
		response.getWriter().print("{}");
		} catch (IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static String generateFileNameForSaving(String fullFileName) {
		Calendar cld = Calendar.getInstance();
		String suffix = String.valueOf(cld.get(Calendar.YEAR)) + "-"+
				String.valueOf(cld.get(Calendar.MONTH) + 1) + "-"+
				String.valueOf(cld.get(Calendar.DAY_OF_MONTH)) + " "+
				String.valueOf(cld.get(Calendar.HOUR_OF_DAY)) + "-" +
				String.valueOf(cld.get(Calendar.MINUTE)) + "-" +
				String.valueOf(cld.get(Calendar.SECOND));
		String fileName = fullFileName.substring(0, fullFileName.lastIndexOf(".")) + "-"+suffix + fullFileName.substring(fullFileName.lastIndexOf("."), fullFileName.length());
		return fileName.replace("\"", "");
	}

}
