package com.xinggang.project.form;

import org.apache.struts.action.ActionForm;

/**
 * 内部人员和客户登录公用form
 * 
 * @author Administrator
 * 
 */
public class LoginAllForm extends ActionForm {

	private static final long serialVersionUID = 4062901574909469163L;
	private String loginName = null;// 登录名
	private String loginPwd = null;// 密码
	private String code = null;// 验证码
	private Integer clientId;// 客户编号

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}