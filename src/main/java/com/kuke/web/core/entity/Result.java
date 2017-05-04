package com.kuke.web.core.entity;

import java.io.Serializable;


/**
 * Result : 响应的结果对象
 *
 * @author StarZou
 * @since 2014-09-27 16:28
 */
public class Result implements Serializable {
	private static final long	serialVersionUID	= 6288374846131788743L;
	
	/**
	 * 实体类信息
	 */
	private Object				objectInfo; 
	/**
	 * 信息
	 */
	private String				message;
	
	/**
	 * 信息状态码
	 */
	private String				messageCode;
	
	/**
	 * 结果状态
	 */
	private String				resultCode			= ResultEnum.FAIL.getValue();
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMessageCode() {
		return messageCode;
	}
	
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}
	
	public String getResultCode() {
		return resultCode;
	}
	
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Result() {
	}

	public void setObjectInfo(Object objectInfo) {
		this.objectInfo = objectInfo;
	}

	public Object getObjectInfo() {
		return objectInfo;
	}

}
