package com.kuke.web.core.utils;

public class Constant {

	public static final String password = "111111";

	public static final String PATH = PropertiesUtil.loadValue("/path.properties", "path");
	
	public static final String EXCLEPATH = PropertiesUtil.loadValue("/path.properties", "excelFilePath");

	public final static String	MESSAGE_EN_PROPERTIES	= "messageEN.properties";

	public final static String	MESSAGE_CH_PROPERTIES	= "messageCH.properties";
	
	public static final String USERNAME = "登录名称已经存在";

	public static final String RoleNAME = "该角色已经存在";
}
