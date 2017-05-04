package com.kuke.web.upload.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.kuke.web.core.entity.Result;
import com.kuke.web.core.entity.ResultEnum;
import com.kuke.web.core.utils.Constant;
import com.kuke.web.core.utils.DateUtil;
import com.kuke.web.core.utils.Utils;
import com.kuke.web.upload.service.UploadService;

@Service
public class UploadServiceImpl implements UploadService{
	
	@Override
	public String upload(MultipartFile file) {
		String fileName = null;
		String path = null;
		try {
			//path = request.getSession().getServletContext().getRealPath("/doc/");

			SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
			String str = format.format(new Date());
			path = Constant.PATH;

			String orgName = file.getOriginalFilename();
			if(Utils.isEmpty(orgName)) 
				return "";
			String buff = orgName.substring(orgName.lastIndexOf("."));
			fileName = new Date().getTime() + buff;

			path = path + (path.endsWith("/") ? "" : "/") + str
					+ (path.endsWith("/") ? "" : "/") + fileName;

			InputStream in = file.getInputStream();
			File to = new File(path);
			FileUtils.copyInputStreamToFile(in, to);

		} catch (IOException e) {
			fileName = null;
			e.printStackTrace();
		}
		return path;
	}

	@Override
	public String uploadFile(HttpServletRequest request) {
		String path = "";
		try {
			//创建一个通用的多部分解析器  
	        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
	        //判断 request 是否有文件上传,即多部分请求  
	        if(multipartResolver.isMultipart(request)){  
	            //转换成多部分request    
	            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
	            //取得request中的所有文件名  
	            Iterator<String> iter = multiRequest.getFileNames();  
	            if(!Utils.isEmpty(iter)) {
					path = Constant.PATH;
		        	path += "/" + DateUtil.parseFormatYYYYMM(new Date()) + "/";
		        	File initFile = new File(path); 
		            if(!initFile.exists()) 
		            	initFile.mkdirs();
	            }
	            while(iter.hasNext()){  
	                //记录上传过程起始时的时间，用来计算上传时间  
	                int pre = (int) System.currentTimeMillis();  
	                //取得上传文件  
	                MultipartFile file = multiRequest.getFile(iter.next());  
	                if(file != null){  
	                    //取得当前上传文件的文件名称  
	                    String fileName = file.getOriginalFilename();  
	                    if(!Utils.isEmpty(fileName)){  
	                        System.out.println(fileName);  
	                        //重命名上传后的文件名  
	                        String buff = fileName.substring(fileName.lastIndexOf("."));
	            			fileName = new Date().getTime() + buff;
	                        //定义上传路径  
	                        path += fileName;  
	                        File localFile = new File(path);  
	                        //存放文件
	                        file.transferTo(localFile);  
	                    }  
	                }  
	                //记录上传该文件后的时间  
	                int finaltime = (int) System.currentTimeMillis();  
	                System.out.println(finaltime - pre);  
	            }  
	        }
		} catch (Exception e) {
			path = "";
			e.printStackTrace();
		}
        return path;
	}

	@Override
	public Result deleteFile(String downloadPath, String downloadId) {
		Result result = new Result();
		try {
			File file = new File(downloadPath);
			if(file.exists()) {
				/*if(!Utils.isEmpty(downloadId))
					documentMapper.deleteByPrimaryKey(Long.valueOf(downloadId));*/
				file.delete();
				result.setMessage("Cancel the file successfully");
				result.setResultCode(ResultEnum.SUCCESS.getValue());
			} else {
				result.setMessage("Can not find the file");
				result.setResultCode(ResultEnum.FAIL.getValue());
			}
		} catch (Exception e) {
			result.setMessage("Cancel file failed");
			result.setResultCode(ResultEnum.ERROR.getValue());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result deleteFiles(String downloadPath, String downloadId) {
		Result result = new Result();
		try {
			boolean flag = true;
			String[] files = Utils.splitString(downloadPath, ",");
			if(!Utils.isEmpty(files)) {
				/*if(!Utils.isEmpty(downloadId)) {
					String[] ids = Utils.splitString(downloadId, ",");
					Map<String, Object> map = new HashMap<String, Object>();
					Map<String, Object> maps = new HashMap<String, Object>();
					for (int j = 0; j < ids.length; j++) {
						maps.put("key_" + j, ids[j]);
					}
					map.put("maps", maps);
					if(!Utils.isEmpty(map)) 
						documentMapper.deleteByPrimaryKeyAll(map);
				}*/
				for (int i = 0; i < files.length; i++) {
					File file = new File(files[i]);
					if(file.exists()) {
						flag = true;
						file.delete();
					} else {
						flag = false;
						break;
					}
				}
			}
			if(!flag) {
				result.setMessage("Can not find the file");
				result.setResultCode(ResultEnum.FAIL.getValue());
			} else {
				result.setMessage("Cancel the files successfully");
				result.setResultCode(ResultEnum.SUCCESS.getValue());
			}
		} catch (Exception e) {
			result.setMessage("Cancel files failed");
			result.setResultCode(ResultEnum.ERROR.getValue());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String download(HttpServletResponse response, 
			String downloadPath, 
			String fileName) {
		InputStream inputStream = null;
		OutputStream os = null;
        try {
        	if(Utils.isEmpty(downloadPath) || Utils.isEmpty(fileName))
        		return null;
        	/*int index = downloadPath.lastIndexOf("/");
        	downloadPath = downloadPath.substring(index);
        	String path = PropertiesUtil.loadValue("/path.properties", "path");
        	path += "/" + DateUtil.parseFormatYYYYMM(new Date()) + downloadPath;*/
        	
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename="
					+ new String(fileName.getBytes(), "iso8859-1"));
            inputStream = new FileInputStream(new File(downloadPath));
            os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        } finally {
        	try {
	        	if(!Utils.isEmpty(inputStream)) {
	        		inputStream.close();
	        	}
	        	if(!Utils.isEmpty(os)) {
	                os.close();
	        	}
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
		return null;
	}

	@Override
	public String downloadTemplates(HttpServletResponse response, 
			HttpServletRequest request,
			String downloadPath, 
			String fileName) {
		InputStream inputStream = null;
		OutputStream os = null;
        try {
        	if(Utils.isEmpty(fileName))
        		return null;

        	if(Utils.isEmpty(downloadPath)) {
        		downloadPath = request.getSession().getServletContext().getRealPath("/WEB-INF/xls");
        		downloadPath += "\\"+fileName;
        	}
        	
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename="
					+ new String(fileName.getBytes(), "iso8859-1"));
            inputStream = new FileInputStream(new File(downloadPath));
            os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        } finally {
        	try {
	        	if(!Utils.isEmpty(inputStream)) {
	        		inputStream.close();
	        	}
	        	if(!Utils.isEmpty(os)) {
	                os.close();
	        	}
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
		return null;
	}

}
