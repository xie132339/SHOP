package com.kuke.web.upload.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.kuke.web.core.entity.Result;


public interface UploadService {
	/**
	 * 流的方式上传文档
	 * @Title: upload
	 * @param file
	 * @return 
	 * @return String 返回类型
	 */
	public String upload(MultipartFile file);
	/**
	 * 通过解析器上传文档 
	 * @Title: uploadFile
	 * @param file
	 * @return 
	 * @return String 返回类型
	 */
	public String uploadFile(HttpServletRequest request);
	
	/**
	 * 删除文档
	 * @Title: deleteFile
	 * @param downloadPath
	 * @param downloadId
	 * @return 
	 * @return Result 返回类型
	 */
	public Result deleteFile(String downloadPath, String downloadId);
	
	/**
	 * 删除多个文档
	 * @Title: deleteFiles
	 * @param downloadPath
	 * @param downloadId
	 * @return 
	 * @return Result 返回类型
	 */
	public Result deleteFiles(String downloadPath, String downloadId);
	
	/**
	 * 下载文档
	 * @Title: download
	 * @param downloadPath
	 * @param fileName
	 * @return 
	 * @return String 返回类型
	 */
	public String download(HttpServletResponse response, 
			String downloadPath, 
			String fileName);
	
	/**
	 * 下载模板
	 * @Title: downloadTemplates
	 * @param downloadPath
	 * @param fileName
	 * @return 
	 * @return String 返回类型
	 */
	public String downloadTemplates(HttpServletResponse response, 
			HttpServletRequest request, 
			String downloadPath, 
			String fileName);
}
