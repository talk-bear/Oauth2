package com.qiyun.qy.utils.load;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

public class DownloadUtil {
	
	/**
	 * <b>"upload"目录，下载</b>
	 * <p><p>
	 * 
	 * @param response 响应
	 * @param name 文件名 <b>[必须包含后缀名]</b>
	 * 
	 * @return 是否下载成功
	 * 
	 * @throws IOException
	 */
	public static boolean download(
			HttpServletResponse response,
			String name) throws IOException {
		return download(response, name, "upload", false);
	}
	
	/**
	 * 
	 * <b>下载</b>
	 * <p><p>
	 * 
	 * @param response 响应
	 * @param name 文件名 <b>[必须包含后缀名]</b>
	 * @param path 文件路径 <b>[结尾不能含有"/"]</b>
	 * 
	 * @return 是否下载成功
	 * 
	 * @throws IOException
	 */
	public static boolean download(
			HttpServletResponse response,
			String name, String path) throws IOException {
		return download(response, name, path, false);
	}
	
	/**
	 * <b>"upload"目录，下载或浏览</b>
	 * <p><p>
	 * 
	 * @param response 响应
	 * @param name 文件名 <b>[必须包含后缀名]</b>
	 * @param isInline 是否在线阅读 <b>[true：在线阅读，false：下载。默认false。]</b>
	 * 
	 * @return
	 * 
	 * @throws IOException
	 */
	public static boolean download(
			HttpServletResponse response,
			String name, boolean isInline) throws IOException {
		return download(response, name, "upload", isInline);
	}
	
	/**
	 * <b>下载或浏览</b>
	 * <p><p>
	 * 
	 * @param response 响应
	 * @param name 文件名 <b>[必须包含后缀名]</b>
	 * @param path 文件路径 <b>[结尾不能含有"/"]</b>
	 * @param isInline 是否在线阅读 <b>[true：在线阅读，false：下载。默认false。]</b>
	 * 
	 * @return 是否下载成功
	 * 
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public static boolean download(
			HttpServletResponse response,
			String name, String path,  
			boolean isInline) 
					throws FileNotFoundException, 
						UnsupportedEncodingException, IOException 
					 {
		String file = path + "/" + name;
		File dest = new File(file);
	    FileInputStream fis = new FileInputStream(dest);
	    
		name = URLEncoder.encode(name, "UTF-8");
		String disposition = "attachment;";
		if (isInline) {
			disposition = "inline;";
		} else {
			response.setContentType("application/octet-stream");
		}
		disposition += "filename=" + name;
		response.setHeader("content-disposition", disposition);
		
	    BufferedInputStream bis = new BufferedInputStream(fis);
	    ServletOutputStream out = response.getOutputStream();
	    byte[] buff = new byte[1024];
	    while (bis.read(buff) > -1) {
	    	out.write(buff, 0, buff.length);
	    	out.flush();
	    }
	    fis.close();
	    bis.close();
	    out.close();
		
		return true;
	}

}
