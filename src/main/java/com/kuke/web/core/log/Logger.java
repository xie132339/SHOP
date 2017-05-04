package com.kuke.web.core.log;

/**
 * 日志的类型（logger4j）
 * 
 * @Action: 替换在DataDriverLog中调用的Logger
 * @DATE: 2010-4-18-下午08:16:51
 */
public class Logger {
	public static void debug(Object s) {
		org.apache.log4j.Logger.getLogger(Logger.class).debug(s);
	}

	public static void error(Object s) {
		org.apache.log4j.Logger.getLogger(Logger.class).error(s);
	}

	public static void info(Object s) {
		System.out.println(s);

	}

	public static void debug(Object s, Object invoder) {
		System.out.println(s + " [" + invoder.getClass().getName() + "]");
	}

	public static void error(Object message, Throwable t) {
		org.apache.log4j.Logger.getLogger(Logger.class).error(message, t);
	}

	public static void error(Throwable t) {
		org.apache.log4j.Logger.getLogger(Logger.class)
				.error(t.getMessage(), t);
	}
}
