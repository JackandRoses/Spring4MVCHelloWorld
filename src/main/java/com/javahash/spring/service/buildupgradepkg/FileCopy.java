package com.javahash.spring.service.buildupgradepkg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;


public class FileCopy {
	static Object[] distinctFilePathPart1;
	static Object[] distinctFilePathPart2;

	public static void main(String[] args) {
		List<String> filePathList = new ArrayList<String>();
		File file = new File("C:\\test.txt");
		try {
			FileInputStream fins = new FileInputStream(file);
			InputStreamReader insReader = new InputStreamReader(fins);
			BufferedReader bufferedReader = new BufferedReader(insReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				if (!line.startsWith("//")) {
					filePathList.add(line);
				}
			}
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("文件不存在！");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (filePathList.size() >= 1) {
			System.out.println("读取完毕！");
			Map<String, List<String>> classifiedFileTypeMap = classifyFileByType(filePathList);
			long totalCount = filePathList.stream().distinct().count();
			distinctFilePathPart1 = (Object[]) filePathList.stream().distinct().limit(totalCount / 2).toArray();
			distinctFilePathPart2 = (Object[]) filePathList.stream().distinct().skip(distinctFilePathPart1.length).toArray();
		}

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				copyFile(distinctFilePathPart1);
			}
		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				copyFile(distinctFilePathPart2);
			}
		});
		t1.start();
		t2.start();
	}

	public static void copyFile(Object[] sourceFilePaths) {
		File sourceFile = null;
		File targetDir = new File("E://test");
		if (!targetDir.exists()) {
			targetDir.mkdir();
		}
		for (Object object : sourceFilePaths) {
			System.out.println(Thread.currentThread().getName()+ "复制： " + object.toString());
			sourceFile = new File(object.toString());
			Path sourceFilePath = Paths.get(object.toString(), "");
			int beginIndex;
			if ((beginIndex = object.toString().lastIndexOf("\\resources\\")) > 0) {
			} else if ((beginIndex = object.toString().lastIndexOf("\\webapp\\")) > 0) {
			} else if ((beginIndex = object.toString().lastIndexOf("\\java\\")) > 0) {
			} else if((beginIndex = object.toString().lastIndexOf(".sql")) > 0){
			} else if((beginIndex = object.toString().lastIndexOf(".txt")) > 0){}
			Path targetFilePath = Paths.get(targetDir.getAbsolutePath(), object.toString().substring(beginIndex, object.toString().length()));
			if (!targetFilePath.getParent().toFile().exists()) {
				targetFilePath.getParent().toFile().mkdirs();
			}
			if (sourceFile.exists()) {
				try {
					Files.copy(sourceFilePath, targetFilePath, StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					System.out.println("文件复制失败");
					e.printStackTrace();
				}
			} else {
				System.out.println("文件不存在：" + object.toString());
			}
		}
	}
	
	/**
	 * 按文件类型分类统计
	 * @param filePathList
	 */
	private static Map<String, List<String>> classifyFileByType(List<String> filePathList) {
		Function<String, String> key = s -> getFileType(s).getValue();
		Function<String, List<String>> value = s -> {List<String> list = new ArrayList<String>();list.add(s);return list;};
		BinaryOperator<List<String>> mergeFunction = (s, a) -> {s.addAll(a);return s;};
		System.out.println("=============SVN文件记录总数: " + filePathList.stream().collect(Collectors.counting()));
		System.out.println("进行分类！");
		Map<String, List<String>> map = filePathList.stream().collect(Collectors.toMap(key, value, mergeFunction));
		BiConsumer<String,List<String>> action = (k,v) -> {
			List<String> distinctEle = v.stream().distinct().collect(Collectors.toList());
			System.out.println("============="+k+": 去重前: "+v.size()+" | 去重后: "+ distinctEle.stream().count());
			
			//http://stackoverflow.com/questions/27677256/java-8-streams-to-find-the-duplicate-elements
			 Set<Entry<String, Long>> repeatedEleSet = v.stream()
			.collect(Collectors.groupingBy( c -> c , Collectors.counting()))
			.entrySet()
			.stream()
			.filter(p -> p.getValue()>1)
			.collect(Collectors.toSet());
			 
			 List<String> repeatedEleList =  repeatedEleSet.stream().map(e -> e.getKey()+":"+e.getValue()).collect( Collectors.toList());
			System.out.println("============="+k+": 重复项: "+ repeatedEleList);
			
			File outPutFile = new File("E://test//" + k+".txt");
			String newline = System.getProperty("line.separator");
			try{
				FileOutputStream outPuts= new FileOutputStream(outPutFile);
				v.stream().forEach(s -> {
					try{
						byte[] data = s.getBytes();
						outPuts.write(data);
						outPuts.write(newline.getBytes());
						outPuts.flush();
					}catch(Exception e){
						
					}
				});
				outPuts.close();
			}catch (Exception e) {
			}
		};
		map.forEach(action);
		return map;
	}
	
	/**
	 * 获得文件类型
	 * @param filePath
	 * @return 
	 */
	private static FileType getFileType(String filePath){
		if(filePath != null){
			String fileExtName = endWith(filePath).toUpperCase();
			return FileType.valueOf(fileExtName);
		}
		System.out.println("获取文件类型出错！");
		return null;
	}
	
	/**
	 * 获得文件扩展名
	 * @param filePath
	 * @return
	 */
	private static String endWith(String filePath) {
		int beginIndex = 0;
		int endIndex = 0;
		if (filePath != null) {
			File file = new File(filePath);
			if (file.isFile()) {
				beginIndex = filePath.lastIndexOf(".")+1;
				endIndex = filePath.length();
			}
			return filePath.substring(beginIndex, endIndex);
		} else {
			System.out.println("没有得到文件扩展名！");
			return null;
		}
	}
}
