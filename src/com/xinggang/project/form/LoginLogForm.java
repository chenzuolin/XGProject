package com.xinggang.project.form;

import org.apache.struts.action.ActionForm;

/**
 * 登录日志表单
 * 
 * @author Administrator
 * 
 */
public class LoginLogForm extends ActionForm {

	private static final long serialVersionUID = -7046502522117086686L;
	private String llogId;// 登录日志编号
	private String llogName;// 登录人用户名
	private String llogTime;// 登录时间
	private String llogIp;// 登录的IP地址
	private String llogDefinedOne;// 预留字段一
	private String llogDefinedTwo;// 预留字段二
	private String llogRemark;// 备注
	private String begin;// 要模糊查询的开始时间
	private String finish;// 要模糊查询的结束时间

	public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public String getFinish() {
		return finish;
	}

	public void setFinish(String finish) {
		this.finish = finish;
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