package com.kuke.web.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;


public class PropertiesUtil {
	private static InputStream			inputStream;
	private static InputStreamReader	read;
	
	/**
	 * @Title: loadValue
	 * @Description: 获取键值
	 * @param @param fileName
	 * @param @param key
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String loadValue(String fileName, String key) {
		String result = null;
		// 获取配置文件
		Properties properties = loadProperties(fileName);
		if (properties != null) {
			// 获取键值
			result = properties.get(key).toString();
		}
		// 读取完成后关闭流的操作
		closeRead();
		return result;
	}
	
	/**
	 * @Title: loadProperties
	 * @Description: 获取配置文件
	 * @param 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public static Properties loadProperties(String fileName) {
		Properties properties = new Properties();
		inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);
		try {
			read = new InputStreamReader(inputStream, "UTF8");
			properties.load(read);
		} catch (Exception e) {
			properties = null;
			read = null;
			inputStream = null;
		}
		return properties;
	}
	
	/**
	 * @Title: closeRead
	 * @Description: 关闭流
	 * @param 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	private static void closeRead() {
		if (read != null && inputStream != null) {
			try {
				read.close();
				inputStream.close();
			} catch (Exception e) {
			} finally {
				try {
					inputStream.close();
				} catch (IOException e) {
				}
			}
		}
	}
}
