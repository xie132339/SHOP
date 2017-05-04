package com.kuke.web.core.entity;

import java.util.Date;

/**
 * @author: 
 * @email:  
 * @company:  
 * @Action:文档以及文档类型同排排列需要的POJO,主要展示目录目标企业资料清单 方便显示
 * @DATE: 2010-10-20-下午02:52:37
 */
public class DocAndType {
	private long doctypeid; // 文档类型id

	private String posid;// 位置ID
	/**
	 * 文档的stage，投资实施5
	 */
	private String stagetypeid;// 文档阶段id
	private Dim stagetypedim;// 阶段维度名称//要么写死，要么去维度表查//2、3、4、5、6//
	private String stageName;// 阶段名称

	private String stagetypeidin;// 用于文档阶段权限过滤

	private String firsttypeid;// 文档大类型id
	private String firsttypename;// 文档大类型名称
	private String firstshorttypename;// 大类简称

	private String sectypeid;// 文档小类型id
	private String sectypename;// 文档小类型名称

	private String ismust;// 是否是必须文档
	private Dim mustdim;// 显示ismust的名称

	private String rolelist;// 文档小类型角色

	private String docid;// 文档ID

	private String projectid;// 项目ID
	private String projectName;
	/**
	 * 也是条款名称
	 */
	private String vcdocname;// 文档名称
	private String lastvcdocname;// 修改前的名称，主要用于保存历史文件使用
	private String year;

	private String docname;// 实际上传的文件名称//////////////////+++相关文档名称
	private String lastdocname;// 上一个文件实际名称

	private String havesupplyed;// 是否已提供
	private Dim havesupplyeddim;// 是否已提供维度

	private String supplytype;// 提供方式
	private Dim supplytypedim;// 提供方式维度

	private String sourcetype;// 来源方式
	private Dim sourcetypedim;// 来源方式维度

	private Date docupdatetime;// 更新时间
	private String docstatus;// 文档状态

	private Dim docstatusdim;// 显示状态名称
	/**
	 * 此属性也用于投资实施的跟踪进度
	 */
	private String docsize;// 文档大小//也就是完成度
	private String lastdocsize;// 未修改前文档的大小

	private String userid;// 用户ID
	private Dim userdim;// 用户维度

	private String updateuserid;// 更新用户
	private Dim updateuserdim;// 更新用户维度

	private String canmodift;// 是否可修改//
	private Dim canmodiftdim;// 显示是否可修改名称
	private String remark;// 备注信息
	private String orderstr;// 用于排序

	private String vcdocpath;
	private String lastvcdocpath;// 未修改前的文件路径
	private boolean modify;// 是否修改过上传文档
	/**
	 * add by Fred 是否已完成，用户投资实施的提交条款
	 */
	private String havedoneId;// 是否完成0没有完成，1完成
	private Dim havedonedim;

	private String resource;// 跟踪过程
	
	private String requestId;

	/**
	 * 尽调方式的维度
	 */
	private String duetypeId;// 尽调方式Id
	private Dim duetypedim;// 尽调方式的维度

	public String getDuetypeId() {
		return duetypeId;
	}

	public void setDuetypeId(String duetypeId) {
		this.duetypeId = duetypeId;
	}

	public Dim getDuetypedim() {
		return duetypedim;
	}

	public void setDuetypedim(Dim duetypedim) {
		this.duetypedim = duetypedim;
	}

	public String getHavedoneId() {
		return havedoneId;
	}

	public void setHavedoneId(String havedoneId) {
		this.havedoneId = havedoneId;
	}

	public Dim getHavedonedim() {
		return havedonedim;
	}

	public void setHavedonedim(Dim havedonedim) {
		this.havedonedim = havedonedim;
	}

	public boolean isModify() {
		if (vcdocpath == null) {
			vcdocpath = "";
		}
		if (lastvcdocpath == null) {
			lastvcdocpath = "";
		}
		modify = lastvcdocpath.equals(vcdocpath);
		return modify;
	}

	public void setModify(boolean modify) {
		this.modify = modify;
	}

	public long getDoctypeid() {
		return doctypeid;
	}

	public void setDoctypeid(long doctypeid) {
		this.doctypeid = doctypeid;
	}

	public String getStagetypeid() {
		return stagetypeid;
	}

	public void setStagetypeid(String stagetypeid) {
		this.stagetypeid = stagetypeid;
	}

	public String getFirsttypeid() {
		return firsttypeid;
	}

	public void setFirsttypeid(String firsttypeid) {
		this.firsttypeid = firsttypeid;
	}

	public String getFirsttypename() {
		return firsttypename;
	}

	public void setFirsttypename(String firsttypename) {
		this.firsttypename = firsttypename;
	}

	public String getSectypeid() {
		return sectypeid;
	}

	public void setSectypeid(String sectypeid) {
		this.sectypeid = sectypeid;
	}

	public String getSectypename() {
		return sectypename;
	}

	public void setSectypename(String sectypename) {
		this.sectypename = sectypename;
	}

	public String getIsmust() {
		return ismust;
	}

	public void setIsmust(String ismust) {
		this.ismust = ismust;
	}

	public Dim getMustdim() {
		if (mustdim == null) {
			return new Dim();
		}
		return mustdim;
	}

	public void setMustdim(Dim mustdim) {
		this.mustdim = mustdim;
	}

	public String getRolelist() {
		return rolelist;
	}

	public void setRolelist(String rolelist) {
		this.rolelist = rolelist;
	}

	public String getDocid() {
		return docid;
	}

	public void setDocid(String docid) {
		this.docid = docid;
	}

	public String getProjectid() {
		return projectid;
	}

	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}

	public String getVcdocname() {
		return vcdocname;
	}

	public void setVcdocname(String vcdocname) {
		this.vcdocname = vcdocname;
	}

	public String getHavesupplyed() {
		return havesupplyed;
	}

	public void setHavesupplyed(String havesupplyed) {
		this.havesupplyed = havesupplyed;
	}

	public String getSupplytype() {
		return supplytype;
	}

	public void setSupplytype(String supplytype) {
		this.supplytype = supplytype;
	}

	public String getSourcetype() {
		return sourcetype;
	}

	public void setSourcetype(String sourcetype) {
		this.sourcetype = sourcetype;
	}

	public Date getDocupdatetime() {
		return docupdatetime;
	}

	public void setDocupdatetime(Date docupdatetime) {
		this.docupdatetime = docupdatetime;
	}

	public String getDocstatus() {
		return docstatus;
	}

	public void setDocstatus(String docstatus) {
		this.docstatus = docstatus;
	}

	public Dim getDocstatusdim() {
		if (docstatusdim == null) {
			return new Dim();
		}
		return docstatusdim;
	}

	public void setDocstatusdim(Dim docstatusdim) {
		this.docstatusdim = docstatusdim;
	}

	public String getDocsize() {
		return docsize;
	}

	public void setDocsize(String docsize) {
		this.docsize = docsize;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getCanmodift() {
		return canmodift;
	}

	public void setCanmodift(String canmodift) {
		this.canmodift = canmodift;
	}

	public Dim getCanmodiftdim() {
		return canmodiftdim;
	}

	public void setCanmodiftdim(Dim canmodiftdim) {
		this.canmodiftdim = canmodiftdim;
	}

	public Dim getUserdim() {
		if (userdim == null) {
			return new Dim();
		}
		return userdim;
	}

	public void setUserdim(Dim userdim) {
		this.userdim = userdim;
	}

	public String getOrderstr() {
		return orderstr;
	}

	public void setOrderstr(String orderstr) {
		this.orderstr = orderstr;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getVcdocpath() {
		return vcdocpath;
	}

	public void setVcdocpath(String vcdocpath) {
		this.vcdocpath = vcdocpath;
	}

	public String getLastvcdocpath() {
		return lastvcdocpath;
	}

	public void setLastvcdocpath(String lastvcdocpath) {
		this.lastvcdocpath = lastvcdocpath;
	}

	public String getLastvcdocname() {
		return lastvcdocname;
	}

	public void setLastvcdocname(String lastvcdocname) {
		this.lastvcdocname = lastvcdocname;
	}

	public String getLastdocsize() {
		return lastdocsize;
	}

	public void setLastdocsize(String lastdocsize) {
		this.lastdocsize = lastdocsize;
	}

	public String getDocname() {
		return docname;
	}

	public void setDocname(String docname) {
		this.docname = docname;
	}

	public String getLastdocname() {
		return lastdocname;
	}

	public void setLastdocname(String lastdocname) {
		this.lastdocname = lastdocname;
	}

	public Dim getHavesupplyeddim() {
		if (havesupplyeddim == null) {
			havesupplyeddim = new Dim();
		}
		return havesupplyeddim;
	}

	public void setHavesupplyeddim(Dim havesupplyeddim) {
		this.havesupplyeddim = havesupplyeddim;
	}

	public Dim getSupplytypedim() {
		if (supplytypedim == null) {
			supplytypedim = new Dim();
		}
		return supplytypedim;
	}

	public void setSupplytypedim(Dim supplytypedim) {
		this.supplytypedim = supplytypedim;
	}

	public Dim getSourcetypedim() {
		if (sourcetypedim == null) {
			sourcetypedim = new Dim();
		}
		return sourcetypedim;
	}

	public void setSourcetypedim(Dim sourcetypedim) {
		this.sourcetypedim = sourcetypedim;
	}

	public String getPosid() {
		return posid;
	}

	public void setPosid(String posid) {
		this.posid = posid;
	}

	public Dim getStagetypedim() {
		if (stagetypedim == null) {
			stagetypedim = new Dim();
		}
		return stagetypedim;
	}

	public void setStagetypedim(Dim stagetypedim) {
		this.stagetypedim = stagetypedim;
	}

	public String getUpdateuserid() {
		return updateuserid;
	}

	public void setUpdateuserid(String updateuserid) {
		this.updateuserid = updateuserid;
	}

	public Dim getUpdateuserdim() {
		if (updateuserdim == null) {
			updateuserdim = new Dim();
		}
		return updateuserdim;
	}

	public void setUpdateuserdim(Dim updateuserdim) {
		this.updateuserdim = updateuserdim;
	}

	public String getFirstshorttypename() {
		return firstshorttypename;
	}

	public void setFirstshorttypename(String firstshorttypename) {
		this.firstshorttypename = firstshorttypename;
	}

	public String getStagetypeidin() {
		return stagetypeidin;
	}

	public void setStagetypeidin(String stagetypeidin) {
		this.stagetypeidin = stagetypeidin;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}


}
