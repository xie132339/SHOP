package com.kuke.web.upload.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kuke.web.core.entity.Result;
import com.kuke.web.core.utils.DocumentEntity;
import com.kuke.web.upload.service.UploadService;

/**
 * @ClassName: OnePaperController
 * @Description: One paper控制类
 * @date 2016-3-22 上午10:48:57
 */
@Controller
@RequestMapping(value = "/commonUpload")
public class UploadController {
	
	//private DocumentService documentService;

	@Resource
	private UploadService uploadService;

	/**
	 * 
	 * @Title: upload
	 * @Description: 通过流的方式上传文档（单个）
	 * @param model
	 * @param request
	 * @param file
	 * @return 
	 * @return String 返回类型
	 */
	@RequestMapping(value = "/upload.do", method = RequestMethod.POST)
	@ResponseBody
	public String upload(Model model, HttpServletRequest request,
			@RequestParam(value = "file") MultipartFile file) {
		return uploadService.upload(file);
	}
	
	/**
	 * 
	 * @Title: uploadFile
	 * @Description: 通过解析器解析文件（文档上传）
	 * @param request
	 * @param response
	 * @return 
	 * @return String 返回类型
	 */
	@RequestMapping(value = "/uploadFile.do", method = RequestMethod.POST)
	@ResponseBody
	public String uploadFile(HttpServletRequest request,HttpServletResponse response) {
		String path = "";
		try {
			path = uploadService.uploadFile(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return path;
	}
	
	
	/**
	 * 
	 * @Title: deleteFile
	 * @Description: 取消文件（删除）
	 * @param request
	 * @param downloadPath
	 * @return 
	 * @return Result 返回类型
	 */
	@RequestMapping(value = "/deleteFile.do", method = RequestMethod.POST)
	public @ResponseBody Result deleteFile(HttpServletRequest request,
			@RequestParam(value = "downloadPath") String downloadPath
			//,@RequestParam(value = "downloadId") String downloadId
			) {
		return uploadService.deleteFile(downloadPath, null);
	}
	
	/**
	 * 
	 * @Title: deleteFiles
	 * @Description: 取消所有文件（删除）
	 * @param request
	 * @param downloadPath
	 * @return 
	 * @return Result 返回类型
	 */
	@RequestMapping(value = "/deleteFiles.do", method = RequestMethod.POST)
	public @ResponseBody Result deleteFiles(HttpServletRequest request,
			@RequestParam(value = "downloadPath") String downloadPath
			//,@RequestParam(value = "downloadId") String downloadId
			) {
		return uploadService.deleteFile(downloadPath, null);
	}
	
	/**
	 * 
	 * @Title: download
	 * @Description: 下载
	 * @param response
	 * @param downloadPath 文件路径
	 * @param fileName 文件名称
	 * @return 
	 * @return String 返回类型
	 */
	@RequestMapping(value = "/download.do", method = RequestMethod.GET)
	public String download(HttpServletResponse response,
			@RequestParam(value = "downloadPath") String downloadPath,
			@RequestParam(value = "fileName") String fileName) {
		return uploadService.download(response, downloadPath, fileName);
	}
	
	/**
	 * 
	 * @Title: downloadTemplates
	 * @Description: 下载 模板
	 * @param response
	 * @param downloadPath 文件路径
	 * @param fileName 文件名称
	 * @return 
	 * @return String 返回类型
	 */
	@RequestMapping(value = "/downloadTemplates.do", method = RequestMethod.GET)
	public String downloadTemplates(HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam(value = "downloadPath") String downloadPath,
			@RequestParam(value = "fileName") String fileName) {
		return uploadService.downloadTemplates(response, request, downloadPath, fileName);
	}

	@RequestMapping(value = "/openUploadWindow.do", method = RequestMethod.POST)
	public String openUploadWindow(Model model, HttpServletRequest request,
			@ModelAttribute("basicExit") DocumentEntity doc) {
		model.addAttribute("doc", doc);
		return "uploadify/multi_upload_file";
	}
}
