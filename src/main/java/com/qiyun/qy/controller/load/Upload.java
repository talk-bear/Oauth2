package com.qiyun.qy.controller.load;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class Upload {
	
	Logger log = LoggerFactory.getLogger(Upload.class);
	
	@GetMapping("/upload")
	public String upload() {
		return "home";
	}

	@PostMapping("/upload")
	public void upload(@RequestParam("file") MultipartFile file, HttpServletRequest req) throws IllegalStateException, IOException {
		
		String origName = file.getOriginalFilename();
		
		log.info("origName：" + origName);
		
		String pathStr = req.getSession().getServletContext().getRealPath("upload/");
		log.info("pathStr：" + pathStr);
		
		File uploadPath = new File(pathStr);
		if (!uploadPath.exists()) {
			log.info("文件路径错误");
			uploadPath.mkdirs();
		}
		
		//File dest = new File(uploadPath, origName);
		FileOutputStream out = new FileOutputStream(pathStr + origName);
        out.write(file.getBytes());
        out.flush();
        out.close();
		//file.transferTo(dest);
		
	}
	
}
