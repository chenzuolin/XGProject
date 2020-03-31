package com.xinggang.project.entity;

/**
 * 登录日志类
 * 
 * @author Administrator
 * 
 */
public class LoginLog implements java.io.Serializable {

	private static final long serialVersionUID = -7046502522117086686L;
	private String llogId;// 登录日志编号
	private String llogName;// 登录人用户名
	private String llogTime;// 登录时间
	private String llogIp;// 登录的IP地址
	private String llogDefinedOne;// 操作类型，（指客户，或者是内部用户）
	private String llogDefinedTwo;// 预留字段二
	private String llogRemark;// 备注

	public LoginLog() {
	}

	public LoginLog(String llogName, String llogTime, String llogIp,
			String llogDefinedOne, String llogDefinedTwo, String llogRemark) {
		this.llogName = llogName;
		this.llogTime = llogTime;
		this.llogIp = llogIp;
		this.llogDefinedOne = llogDefinedOne;
		this.llogDefinedTwo = llogDefinedTwo;
		this.llogRemark = llogRemark;
	}
	
	
	public LoginLog(String llogId, String llogName, String llogTime,
			String llogIp, String llogDefinedOne, String llogDefinedTwo,
			String llogRemark) {
		super();
		this.llogId = llogId;
		this.llogName = llogName;
		this.llogTime = llogTime;
		this.llogIp = llogIp;
		this.llogDefinedOne = llogDefinedOne;
		this.llogDefinedTwo = llogDefinedTwo;
		this.llogRemark = llogRemark;
	}

	public String getLlogId() {
		return this.llogId;
	}

	public void setLlogId(String llogId) {
		this.llogId = llogId;
	}

	public String getLlogName() {
		return this.llogName;
	}

	public void setLlogName(String llogName) {
		this.llogName = llogName;
	}

	public String getLlogTime() {
		return this.llogTime;
	}

	public void setLlogTime(String llogTime) {
		this.llogTime = llogTime;
	}

	public String getLlogIp() {
		return this.llogIp;
	}

	public void setLlogIp(String llogIp) {
		this.llogIp = llogIp;
	}

	public String getLlogDefinedOne() {
		return this.llogDefinedOne;
	}

	public void setLlogDefinedOne(String llogDefinedOne) {
		this.llogDefinedOne = llogDefinedOne;
	}

	public String getLlogDefinedTwo() {
		return this.llogDefinedTwo;
	}

	public void setLlogDefinedTwo(String llogDefinedTwo) {
		this.llogDefinedTwo = llogDefinedTwo;
	}

	public String getLlogRemark() {
		return this.llogRemark;
	}

	public void setLlogRemark(String llogRemark) {
		this.llogRemark = llogRemark;
	}

}