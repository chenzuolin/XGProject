package com.xinggang.project.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Functions entity. @author MyEclipse Persistence Tools 功能表类
 */

public class Functions implements java.io.Serializable {
	// Fields
	private static final long serialVersionUID = -7143501289197248065L;
	private Integer functionId;// 功能编号
	private Classify classify;// 功能类别(调用分类表)
	private String functionName;// 功能名称
	private String functionDefinedOne;// 自定义字段1
	private String functionDefinedTwo;// 自定义字段2
	private String functionRemark;// 备注
	private Set<Object> powerses = new HashSet<Object>(0);

	// Constructors

	/** default constructor */
	public Functions() {
	}

	/** full constructor */
	public Functions(Classify classify, String functionName,
			String functionDefinedOne, String functionDefinedTwo,
			String functionRemark, Set<Object> powerses) {
		this.classify = classify;
		this.functionName = functionName;
		this.functionDefinedOne = functionDefinedOne;
		this.functionDefinedTwo = functionDefinedTwo;
		this.functionRemark = functionRemark;
		this.powerses = powerses;
	}

	// Property accessors

	public Integer getFunctionId() {
		return this.functionId;
	}

	public void setFunctionId(Integer functionId) {
		this.functionId = functionId;
	}

	public Classify getClassify() {
		return this.classify;
	}

	public void setClassify(Classify classify) {
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

	public Set<Object> getPowerses() {
		return this.powerses;
	}

	public void setPowerses(Set<Object> powerses) {
		this.powerses = powerses;
	}

}