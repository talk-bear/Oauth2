package com.qiyun.qy.utils.load;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

/**
 * 上传工具类
 * @author Administrator
 *
 */
public class UploadUtil {
	
	/**
	 * <b>原文件名称、不覆盖上传</b>
	 * <p><p>
	 * 
	 * @param file 上传文件
	 * @param path 上传路径
	 * 
	 * @return 上传是否成功
	 * @throws IOException 
	 */
	public static boolean upload(MultipartFile file,
			String path) throws IOException {
		return upload(file, path, file.getOriginalFilename(), false);
	}
	
	/**
	 * <b>原文件名称、选择是否覆盖上传</b>
	 * <p><p>
	 * 
	 * @param file 上传文件
	 * @param path 上传路径
	 * @param rewrite 当目标文件存在，是否覆盖
	 * 
	 * @return 上传是否成功
	 * @throws IOException 
	 */
	public static boolean upload(MultipartFile file,
			String path, Boolean rewrite) throws IOException {
		return upload(file, path, file.getOriginalFilename(), rewrite);
	}
	
	/**
	 * <b>重命名文件、不覆盖上传</b>
	 * <p><p>
	 * 
	 * @param file 上传文件
	 * @param path 上传路径
	 * @param name 上传文件名
	 * 
	 * @return 上传是否成功
	 * @throws IOException 
	 */
	public static boolean upload(MultipartFile file,
			String path, String name) throws IOException {
		return upload(file, path, name, false);
	}
	
	/**
	 * <b>上传</b>
	 * <p><p>
	 * 
	 * @param file 上传文件
	 * @param path 上传路径
	 * @param name 上传文件名
	 * @param rewrite 当目标文件存在，是否覆盖
	 * 
	 * @return 上传是否成功
	 * @throws IOException 
	 */
	public static boolean upload(MultipartFile file,
			String path, String name, Boolean rewrite) throws IOException {
		
		File targetPath = new File(path);  
        if(!targetPath.exists()){    
        	targetPath.mkdirs();    
        }
        
        File targetFile = new File(targetPath, name);
    	if (targetFile.exists()) {
    		if (rewrite) {
    			targetFile.delete();
            } else {
            	name = name + UUID.randomUUID().toString().substring(0, 6);
            }
    	}
        
        FileOutputStream out = new FileOutputStream(path + name);
        byte[] b = file.getBytes();
        out.write(b);
        out.flush();
        out.close();
		
		return true;
	}

}
