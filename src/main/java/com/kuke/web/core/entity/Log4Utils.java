package com.kuke.web.core.entity;


public class Log4Utils {
	public static void debug(Object s) {
		System.err.println(s);
		org.apache.log4j.Logger.getLogger(Log4Utils.class).debug(s);
	}
}
