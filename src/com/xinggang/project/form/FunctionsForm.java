package com.xinggang.project.form;


import org.apache.struts.action.ActionForm;

/**
 * 功能表表单
 */

public class FunctionsForm extends ActionForm {

	private static final long serialVersionUID = -7143501289197248065L;
	private Integer functionId;// 功能编号
	private Integer classify;// 功能类别，对应的功能类别类
	private String functionName;// 功能名称
	private String functionDefinedOne;// 自定义字段1
	private String functionDefinedTwo;// 自定义字段2
	private String functionRemark;// 备注	

	public Integer getFunctionId() {
		return this.functionId;
	}

	public void setFunctionId(Integer functionId) {
		this.functionId = functionId;
	}

	public Integer getClassify() {
		return this.classify;
	}

	public void setClassify(Integer classify) {
		this.classify = classify;
	}

	public String getFunctionName() {
		return this.functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getFunctionDefinedOne() {
		return this.functionDefinedOne;
	}

	public void setFunctionDefinedOne(String functionDefinedOne) {
		this.functionDefinedOne = functionDefinedOne;
	}

	public String getFunctionDefinedTwo() {
		return this.functionDefinedTwo;
	}

	public void setFunctionDefinedTwo(String functionDefinedTwo) {
		this.functionDefinedTwo = functionDefinedTwo;
	}

	public String getFunctionRemark() {
		return this.functionRemark;
	}

	public void setFunctionRemark(String functionRemark) {
		this.functionRemark = functionRemark;
	}

}