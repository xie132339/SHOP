package com.kuke.web.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;


public class PropertieReader {
	public static Properties getProperties(String str) {

		Properties properties = new Properties();
		try {
			InputStream is = PropertieReader.class.getClassLoader()
					.getResourceAsStream(str);
			properties.load(is);
			if (is != null) {
				is.close();
			}
		} catch (IOException ioexception) {
			System.out.println("Open config file failure");
		} catch (NullPointerException e) {
			System.out.println(" is null");
		}
		return properties;
	}

	public static String getBasePath() {
		HttpServletRequest request = null;
		String path = request.getSession().getServletContext()
				.getRealPath("/");
		return path.substring(0, path.length() - 1);
	}

	/*public static String setParam(String src, FinanceExcel financeExcel) {
		src = src.replace("{$param}", financeExcel.getProjectId());
		src = src
				.replace("{$vc_company_scope}", financeExcel.getCompanyScope());
		src = src.replace("{$vc_audit_status}", financeExcel.getAuditStatus());
		src = src
				.replace("{$vc_currency_type}", financeExcel.getCurrencyType());
		src = src.replace("{$excel_templete}", financeExcel.getExcelType());
		src = src.replace("{$vc_check}", financeExcel.getIsChecked());
		src = src.replace("{$finance_status}", financeExcel.getFinanceStatus());
		src = src.replace("{$date}", financeExcel.getFdate());
		src = src.replace("{$userid}", financeExcel.getUserId());
		return src;
	}*/
}
