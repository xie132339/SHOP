package com.kuke.web.user.service.impl;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuke.web.core.utils.Utils;
import com.kuke.web.user.dao.UserDao;
import com.kuke.web.user.model.User;
import com.kuke.web.user.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	private static int TEMPLATE_COLUMN_NUMBER = 2;
	@Override
	public List<User> findUser() {
		return userDao.findUser();
	}

	@Override
	public String saveUsers(String fileName) {
		String errorMessage = "";
		try {
			HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(fileName));
			HSSFSheet sheet = wb.getSheetAt(0);
			int rowNum = sheet.getLastRowNum();//获得总行数
			/* 根据指标标题获得指标标识并存入map */
			HSSFRow titleCell = sheet.getRow(1);
			/* 如果模板小于11列说明模板被改动了 */
			if (titleCell.getLastCellNum() < TEMPLATE_COLUMN_NUMBER) {
				errorMessage = "模板已被改动，请重新下载模板！";
				return errorMessage;
			}
			List<User> listUser = new ArrayList<User>();
			for(int i=1;i<rowNum;i++){
				User user = new User();
				HSSFRow dataCellArray = sheet.getRow(i);
				user.setName(getDataNumberHSSF(dataCellArray.getCell(0)));
				user.setPassword(getDataNumberHSSF(dataCellArray.getCell(1)));
				listUser.add(user);
			}
			if(!Utils.isEmpty(listUser)){
				try {
					for(int i=0; i<listUser.size(); i++){
						User us = listUser.get(i);
						userDao.saveUser(us);
					}
					errorMessage = "成功";
				} catch (Exception e) {
					e.printStackTrace();
					errorMessage = "失败";
				}
			}
		} catch (Exception e) {
		}
		return errorMessage;
	}
	
	 public String getDataNumberHSSF(HSSFCell cell) {
		 String data = "";
		 if (!Utils.isEmpty(cell)) {  
             switch (cell.getCellType()) {   
             case HSSFCell.CELL_TYPE_NUMERIC: // 数字   
            	 if(HSSFDateUtil.isCellDateFormatted(cell)) {
            		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
     				 data = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
            	 } else {
                	 BigDecimal bd = new BigDecimal(cell.getNumericCellValue()); 
        	    	 data = String.valueOf(bd);     
            	 }
                 break;   
             case HSSFCell.CELL_TYPE_STRING: // 字符串   
            	 data = cell.getStringCellValue();   
                 break;   
             case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean   
            	 data = String.valueOf(cell.getBooleanCellValue());   
                 break;   
             case HSSFCell.CELL_TYPE_FORMULA: // 公式   
            	 if(HSSFDateUtil.isCellDateFormatted(cell)) {
            		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
     				 data = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
            	 } else {
                	 data = String.valueOf(cell.getCellFormula());   
            	 }
                 break;   
             case HSSFCell.CELL_TYPE_BLANK: // 空值   
                 break;   
             case HSSFCell.CELL_TYPE_ERROR: // 故障   
                 break;   
             default:   
            	 if(HSSFDateUtil.isCellDateFormatted(cell)) {
            		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
     				 data = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
            	 }
                 break;   
             }   
         }
		 return data;
	 }
	 
	 public String getDataNumber(HSSFCell cell){
			String data = "";
			if (!Utils.isEmpty(cell)) { 
				switch (cell.getCellType()) {   
		             case HSSFCell.CELL_TYPE_NUMERIC: // 数字   
		            	 if(HSSFDateUtil.isCellDateFormatted(cell)) {
		            		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		     				 data = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
		            	 } else {
		                	 BigDecimal bd = new BigDecimal(cell.getNumericCellValue()); 
		        	    	 data = String.valueOf(bd);     
		            	 }
		                 break; 
		             case HSSFCell.CELL_TYPE_FORMULA: // 公式   
		            	 if(HSSFDateUtil.isCellDateFormatted(cell)) {
		            		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		     				 data = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
		            	 } else {
		                	 data = String.valueOf(cell.getCellFormula());   
		            	 }
		             default:   
		            	 if(HSSFDateUtil.isCellDateFormatted(cell)) {
		            		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		     				 data = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
		            	 }
		                 break;  
				}
			}
			 return data;
		}
}
