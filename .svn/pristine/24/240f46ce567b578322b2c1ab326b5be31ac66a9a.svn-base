package com.qiyun.qy.utils.file;

import java.io.File;

import org.json.JSONException;
import org.json.JSONObject;

public class FileAssistent {
	
	public static JSONObject loopFile(
			String filePath, JSONObject json, String rootPath) 
					throws JSONException {

		File file = new File(filePath);

		if (file.exists()) {
			File[] subfiles = file.listFiles();
			for (File subfile : subfiles) {
				String subPath = subfile.getAbsolutePath();
				boolean isDirectory = subfile.isDirectory();

				String subFileName = subfile.getName();
				json.put(subFileName, subPath.replace(rootPath, ""));
				if (isDirectory) {
					JSONObject subJson = new JSONObject();
					subJson.put("isDirectory", isDirectory);
					subJson = loopFile(subPath, subJson, rootPath);
					json.put(subFileName, subJson);
				}
			}
		}

		return json;
	}
	
	public static void main(String[] args) 
			throws InstantiationException, IllegalAccessException {

		String filePath = "D:\\apache-maven-3.5.2";
		JSONObject json = new JSONObject();
		try {
			json = loopFile(filePath, json, filePath);
			System.out.println(json);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}
	
}
