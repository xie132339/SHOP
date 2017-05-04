package com.kuke.web.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.excelutils.ExcelUtils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.alibaba.fastjson.JSONObject;
import com.kuke.web.core.export.ExcelFile;
import com.kuke.web.core.export.ExportExcelDealTitle;
import com.kuke.web.core.utils.JSONUtils;
/**
* @ClassName: WebServiceUtils
* @Description: WebService帮助类
* @date 2016-4-5 下午1:27:14
 */
public class Utils {
	public static StringBuffer	filePackage;
	public static StringBuffer	filename;
	public static String config; 
    private static Utils instance = null; // 单例对象
	private static File file; // 操作文件
    /**
     * 私有化构造方法
     * 
     * @param file
     *            文件对象
     */
    private Utils(File file) {
        super();
        this.file = file;
    }
 
    public File getFile() {
        return file;
    }
 
    public void setFile(File file) {
        this.file = file;
    }
 
    /**
     * 获取单例对象并进行初始化
     * 
     * @param file
     *            文件对象
     * @return 返回初始化后的单例对象
     */
    public static Utils getInstance(File file) {
        if (instance == null) {
            // 当单例对象为null时进入同步代码块
            synchronized (Utils.class) {
                // 再次判断单例对象是否为null，防止多线程访问时多次生成对象
                if (instance == null) {
                    instance = new Utils(file);
                }
            }
        } else {
            // 如果操作的文件对象不同，则重置文件对象
            if (!file.equals(instance.getFile())) {
                instance.setFile(file);
            }
        }
        return instance;
    }
 
    /**
     * 获取单例对象并进行初始化
     * 
     * @param filePath
     *            文件路径
     * @return 返回初始化后的单例对象
     */
    public static Utils getInstance(String filePath) {
        return getInstance(new File(filePath));
    }
	
	/**
	 * 
	 * @Title: isObj
	 * @Description: 根据请求值执行相应操作
	 * @param is
	 * @param objects void 返回类型
	 */
	@SuppressWarnings("all")
	public static void isObj(Integer is, Object[] objects) {
		switch (is) {
			case 1:
				ExcelUtils.addValue("list", objects[0]);
				break;
			case 2:
				ExcelUtils.addValue("bean", objects[0]);
				break;
			case 3://多集合
				for (int i = 0; i < objects.length; i++) {
					ExcelUtils.addValue("list" + (i+1), objects[i]);
				}
				break;
			case 4://集合与对象，参数位置顺序要对
				ExcelUtils.addValue("bean", objects[0]);
				ExcelUtils.addValue("list", objects[1]);
				break;
			case 5://多集合与对象，参数位置顺序要对
				ExcelUtils.addValue("bean", objects[0]);
				for (int i = 1; i < objects.length; i++) {
					ExcelUtils.addValue("list" + (i+1), objects[i]);
				}
				break;
			default:
				ExcelUtils.addValue("list", objects[0]);
				break;
		}
	}
	
	/**
	 * 通用-通过模板导出方法
	 * 
	 * @return
	 */
	public static void export(ExcelFile excelFile
			, HttpServletRequest request
			, HttpServletResponse response) {
		try {
			if(!Utils.isEmpty(excelFile.getTitle()))
				ExcelUtils.addValue("title", excelFile.getTitle()); 
			if(!Utils.isEmpty(excelFile.getHeadTitle()))
				ExcelUtils.addValue("headTitle", excelFile.getHeadTitle());
			//区分是集合或是对象,亦或者是多集合或集合与对象等
			isObj(excelFile.getIsObj(), excelFile.getObjs());
			if(!Utils.isEmpty(excelFile.getFile())) {
				Utils.config = request.getSession().getServletContext().getRealPath("/WEB-INF/xls/" + excelFile.getFile());
			} else {
				
			}
			// 为每个用户创建一个导出文件夹
			excelFile.setFilePackage(Constant.EXCLEPATH 
					+ "/" + DateUtil.parseFormatYYYYMM(new Date()) + "/excel");
			excelFile.setFilename(excelFile.getTitle() + "-(" + DateUtil.getYMDTO(new Date()) + ")" + ".xls");
			excelFile.setFilename(new String(excelFile.getFilename().getBytes("gb2312"), "iso8859-1"));
			Utils.filePackage = new StringBuffer(excelFile.getFilePackage());
			Utils.filename = new StringBuffer(excelFile.getFilename());
			File filex = new File(String.valueOf(filePackage));
			if(!filex.exists()) {
				filex.mkdirs();
			}
			System.out.println("----------------------------");
			System.out.println("Start export excel.....");
			
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ new String(excelFile.getFile().getBytes(), "iso8859-1"));
			FileOutputStream out = new FileOutputStream(filePackage + "/" + filename);
			ExcelUtils.export(config, out);
			if(excelFile.isFlag()) {
	            InputStream inputStream = new FileInputStream(filePackage + "/" + filename);  
				OutputStream ouputStream = response.getOutputStream();
				byte[] b = new byte[1024];
				int length;  
	            while((length = inputStream.read(b)) > 0){  
	            	ouputStream.write(b, 0, length);  
	            }  
	            inputStream.close();  
				ouputStream.flush();     
		        ouputStream.close();
			}
			System.out.println("End export excel!");
			System.out.println("----------------------------");
		} catch (Exception ex) {
			System.out.println("Excel error");
			System.out.println("----------------------------");
		}
	}
	
	/**
	 * 
	 * @Title: readExcel
	 * @Description: 解析excel
	 * @param file
	 * @return 
	 * @return Map<String,Object> 返回类型
	 */
	public static List<Map<String,Object>> readExcel() throws Exception {
		//根据其完整路径获得该workbook
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file));
		//读取第一个sheet
		Sheet sheet = workbook.getSheetAt(0);
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>(); 
		//逐行处理excel数据 
		for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			Row row = sheet.getRow(i);//获得行
			int cellCount = row.getPhysicalNumberOfCells();//获得改行总列数
			for (int j = 0; j < cellCount; j++) {
				Cell cell = row.getCell(j);//获得一列               
				map.put("cell_key_" + j, cell.getStringCellValue());
			}
			resultList.add(map);
		}
		return resultList;
	}
	
	/**
	 * 
	 * @Title: exportExcelTeam
	 * @Description: 此Method中ComPliance - Confirmation Team 共用
	 * @param obj
	 * @param title
	 * @param file
	 * @param request
	 * @param response void 返回类型
	 */
	public static void exportExcelTeam(Object object,String file, String title, HttpServletRequest request
			, HttpServletResponse response) {
		Map<String, Object> maps = new HashMap<String, Object>();
        JSONObject jsonObject = JSONObject.parseObject(JSONUtils.toJson(object));//转换成JSONObject对象
        Iterator<Entry<String, Object>> it = jsonObject.entrySet().iterator();  
		while (it.hasNext()) {
			Map.Entry<String, Object> param = (Map.Entry<String, Object>) it.next();
			if(param.getValue() instanceof String) {//转换数据
				if("0".equals(param.getValue())){
					maps.put(param.getKey(), "Y");
				} else if ("1".equals(param.getValue())){
					maps.put(param.getKey(), "N");
				} else if ("2".equals(param.getValue())){
					maps.put(param.getKey(), "N/A");
				}
			}
		}
		Object objs[] = new Object[1];
		objs[0] = JSONUtils.toJSONObject(JSONUtils.toJson(maps));//数据集
		Object obj = JSONUtils.toJSONObject(JSONUtils.toJson(new ExportExcelDealTitle()));//头部标题
		ExcelFile ef = new ExcelFile();
		ef.setIsObj(2);//单个对象
		ef.setFile(file);//文件全称
		ef.setTitle(title);//标题
		ef.setObjs(objs);
		ef.setHeadTitle(obj);
		Utils.export(ef, request, response);//导出
	}
	
	/**
	 * 是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(Object obj) {
		if (obj == null || "".equals(obj)) {
			return true;
		}
		if (obj instanceof Collection) {
			@SuppressWarnings("rawtypes")
			Collection coll = (Collection) obj;
			if (coll.size() <= 0) {
				return true;
			}
		}
		if (obj instanceof Map) {
			@SuppressWarnings("rawtypes")
			Map map = (Map) obj;
			return map.isEmpty();
		}
		if(obj instanceof Object []) {
			Object[] objs = (Object [])obj;
			if(objs.length > 0) {
				boolean flag = false;
				for (Object objl : objs) {
					if(objl instanceof String) {
						String str = (String)objl;
						if (str == null || str == "null" || "".equals(str) || str.length() <= 0) {
							flag = true;
							break;
						}
					}
				}
				return flag;
			}
		}
		return false;
	}
	
	/**
	 * 是否是整数
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String... strs) {
		if (isEmpty(strs))
			return false;
		Pattern pattern = Pattern.compile("\\d*");
		boolean isNumber = true;
		for (String str : strs) {
			isNumber = pattern.matcher(str).matches();
		}
		return isNumber;
	}
	
	/**
	 * 
	 * @Title: replaceSymbol
	 * @Description: 替换符号
	 * @param str
	 * @param oldSymbol
	 * @param newSymbol
	 * @return String 返回类型
	 */
	public static String replaceSymbol(String str,String oldSymbol,String newSymbol) {
		if(!Utils.isEmpty(str)) {
			str = str.replace(oldSymbol, newSymbol);
		}
		return str;
	}
	
	/**
	 * 
	 * @Title: splitString
	 * @Description: 分割字符串
	 * @param str 字符串
	 * @param splitStr 分割符号
	 * @return 
	 * @return String[] 返回类型
	 */
	public static String[] splitString(String str, String splitStr) {
		String[] strs = null;
		if(!Utils.isEmpty(str) && !Utils.isEmpty(splitStr)) {
			strs = str.split(splitStr);
		}
		return strs;
	}
	
	/**
	 * 
	 * @Title: splitDocument
	 * @Description: 分割文档
	 * @param doc 文档
	 * @param reg 分割符号
	 * @return 
	 * @return List<DocumentEntity> 返回类型
	 */
	public static List<DocumentEntity> splitDocument(DocumentEntity doc, String reg) {
		String[] filenames = doc.getFileName().split(reg);
		String[] filepaths = doc.getFilePath().split(reg);
		String[] filesizes = doc.getFileSize().split(reg);
		List<DocumentEntity> docs = new ArrayList<DocumentEntity>();
		if(filenames.length == filepaths.length && 
				filenames.length == filesizes.length) {
			for (int i = 0; i < filenames.length; i++) {
				DocumentEntity tranDoc = new DocumentEntity();
				tranDoc.setFileName(filenames[i]);
				tranDoc.setFilePath(filepaths[i]);
				tranDoc.setFileSize(filesizes[i]);
				docs.add(tranDoc);
			}
		}
		return docs;
	}
  
	/**
	 * 
	 * @Title: isExcel2003
	 * @Description: 是否是2003的excel，返回true是2003 
	 * @param fileName 文件的名称
	 * @return 
	 * @return boolean 返回类型
	 */
    public static boolean isExcel2003(String fileName) {  
        return fileName.matches("^.+\\.(?i)(xls)$");  
    }  
  
    /**
     * 
     * @Title: isExcel2007
     * @Description: 是否是2007的excel，返回true是2007
     * @param fileName 文件的名称
     * @return 
     * @return boolean 返回类型
     */
    public static boolean isExcel2007(String fileName) {  
        return fileName.matches("^.+\\.(?i)(xlsx)$");  
    } 
}
