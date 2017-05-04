package com.kuke.web.core.entity;

/**
 * @author:  
 * @Email:  
 * @Company: 
 * @Action:  
 * @DATE:  
 */
public class Dim {
	private String code;// 代码
	private String codename;// 名称
	private String codenamelike;// 用于模糊查询使用
	private String dimtype;// 代码类型
	private String dimtable;// 所用表
	private String dimcolumn;// 所用列
	private String dimtypeshortname;
	private String orderstr;// 用于排序

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodename() {
		return codename;
	}

	public void setCodename(String codename) {
		this.codename = codename;
	}

	public String getDimtype() {
		return dimtype;
	}

	public void setDimtype(String dimtype) {
		this.dimtype = dimtype;
	}

	public String getDimtable() {
		return dimtable;
	}

	public void setDimtable(String dimtable) {
		this.dimtable = dimtable;
	}

	public String getDimcolumn() {
		return dimcolumn;
	}

	public void setDimcolumn(String dimcolumn) {
		this.dimcolumn = dimcolumn;
	}

	public String getCodenamelike() {
		return codenamelike;
	}

	public void setCodenamelike(String codenamelike) {
		this.codenamelike = codenamelike;
	}

	public String getOrderstr() {
		return orderstr;
	}

	public void setOrderstr(String orderstr) {
		this.orderstr = orderstr;
	}

	public String getDimtypeshortname() {
		return dimtypeshortname;
	}

	public void setDimtypeshortname(String dimtypeshortname) {
		this.dimtypeshortname = dimtypeshortname;
	}

}
