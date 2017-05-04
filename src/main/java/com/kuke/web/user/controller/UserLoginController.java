package com.kuke.web.user.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kuke.web.core.entity.Result;
import com.kuke.web.core.export.ExcelFile;
import com.kuke.web.core.featrue.orm.page.DataTablePage;
import com.kuke.web.core.utils.JSONUtils;
import com.kuke.web.core.utils.PropertieReader;
import com.kuke.web.core.utils.Utils;
import com.kuke.web.upload.service.UploadService;
import com.kuke.web.user.model.User;
import com.kuke.web.user.service.UserService;

@Controller
@RequestMapping(value = "userLogin")
public class UserLoginController {
	@Autowired
	private UserService userService;
	private String errorMessage;
	@Autowired
	private UploadService uploadService;
	@RequestMapping(value = "userLoginInit")
	public String userLoginInit(HttpServletRequest request, Model model) {
		try {
			Properties p = getProperties();
			System.out.println(p.getProperty("userName"));
			List<User> list = userService.findUser();
			model.addAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "user/login";
	}
	
	public DataTablePage<User> dataTablePageUser(Model model, HttpServletRequest request, @RequestParam("user") User user){
		DataTablePage<User> pageList = new DataTablePage<User>(request);
		return pageList;
	}
	//调用properties
	private static Properties getProperties() {
		Properties mailProperties = new Properties();
		try {
			mailProperties = PropertieReader.getProperties("messageResources_zh_CN.properties");
		} catch (Exception e) {
		}
		return mailProperties;
	}

	/**
	 * 
	 * @Title: importDealtracker
	 * @Description: 导出
	 * @param request
	 * @param response
	 * @param file
	 *            文件名全称
	 * @param title
	 *            标题
	 * @param dealtrackerDto
	 *            条件
	 * @return void 返回类型
	 */
	@RequestMapping(value = "importUser.do", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void importUser(HttpServletRequest request,
			HttpServletResponse response, String files, String title, User user) {
		try {
			List<User> datas = userService.findUser();
			Object[] objs = new Object[1];
			objs[0] = datas;
			ExcelFile ef = new ExcelFile(objs, files, title, null, 1);
			Utils.export(ef, request, response);// 导出
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	@RequestMapping(value = "saveLogin.do", method = {RequestMethod.POST })
	public void saveLogin(User user){
		List<Map<String, Object>> l = JSONUtils.toList(user);
		System.out.println(l);
	}

	/**
	 * 
	 * @author xiedengfeng
	 * @ClassName downUser
	 * @Description: 导入
	 * @Date 2016-11-30
	 * @return Map<String,String>
	 */
	@RequestMapping(value = "downUser", method = RequestMethod.POST)
	@ResponseBody
	public Result downUser(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("filename") MultipartFile file) {
		Result result = new Result();
		if (Utils.isEmpty(file)) {
			result.setMessage("失败");
			return result;
		}
		// 获取文件名字
		String fileName = file.getOriginalFilename();
		// 获取文件大小
		long fileSize = file.getSize();
		if (Utils.isEmpty(fileName) && fileSize == 0) {
			result.setMessage("失败");
			return result;
		}
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = format.format(date);
		try {
			String filePath = request.getSession().getServletContext()
					.getRealPath("/")
					+ "upload/" + time + ".xls";
			// 归档，存到Tomcat
			file.transferTo(new File(filePath));
			errorMessage = userService.saveUsers(filePath);
			result.setMessage("成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
