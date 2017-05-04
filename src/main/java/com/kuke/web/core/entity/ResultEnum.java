package com.kuke.web.core.entity;

public enum ResultEnum {
	SUCCESS("1", "成功"), FAIL("0", "失败"), ERROR("2", "错误");
	
	private String	value;
	private String	text;
	
	private ResultEnum(String value, String text) {
		this.value = value;
		this.text = text;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
}