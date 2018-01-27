package com.qiyun.qy.controller.UE;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ueditor/")
public class UEController {

	@ResponseBody
	@RequestMapping(path = "controller")
	public String factory(HttpServletRequest req,
			HttpServletResponse resp) {
		String action = req.getParameter("action");
		System.out.println("/ueditor/controller?action="+action);
		if ("config".equals(action)) {
			return "";
		} else if ("uploadimage".equals(action)) {
			return uploadImage(req, resp);
		} else if("uploadscrawl".equals(action)){
			return uploadScrawl(req, resp);
		} else if("uploadvideo".equals(action)){
			return uploadVideo(req, resp);
		} else if("uploadfile".equals(action)){
			return uploadFile(req, resp);
		}
		return "";
	}
	
	@ResponseBody
	@RequestMapping(path = "config")
	public String config(HttpServletRequest req,
			HttpServletResponse resp) {
		String jsonPath = "src/main/resources/config/UE/config.json";
		BufferedReader reader = null;
		String laststr = "";
		try{
			FileInputStream fileInputStream = new FileInputStream(jsonPath);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
			reader = new BufferedReader(inputStreamReader);
			String tempString = null;
			while((tempString = reader.readLine()) != null){
				laststr += tempString;
			}
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//JSONObject json = (JSONObject) JSONObject.wrap(laststr);
		return laststr;
	}
	
	@ResponseBody
	@RequestMapping(path = "uploadimage")
	public String uploadImage(HttpServletRequest req,
			HttpServletResponse resp) {
		return "";
	}
	
	@ResponseBody
	@RequestMapping(path = "uploadscrawl")
	public String uploadScrawl(HttpServletRequest req,
			HttpServletResponse resp) {
		return "";
	}
	
	@ResponseBody
	@RequestMapping(path = "uploadvideo")
	public String uploadVideo(HttpServletRequest req,
			HttpServletResponse resp) {
		return "";
	}
	
	@ResponseBody
	@RequestMapping(path = "uploadfile")
	public String uploadFile(HttpServletRequest req,
			HttpServletResponse resp) {
		return "";
	}
	
}
