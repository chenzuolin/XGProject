package com.xinggang.project.entity;

import java.util.HashSet;
import java.util.Set;
/**
 * 
 * @author Administrator
 *功能分类表类
 */
public class Classify implements java.io.Serializable {

	private static final long serialVersionUID = 8368850472166119739L;
	private Integer classifyId;//功能分类编号
	private String classifyName;//功能分类名称
	private Set<Object> functionses = new HashSet<Object>(0);//对应功能类

	public Classify() {
	}

	public Classify(String classifyName, Set<Object> functionses) {
		this.classifyName = classifyName;
		this.functionses = functionses;
	}

	public Integer getClassifyId() {
		return this.classifyId;
	}

	public void setClassifyId(Integer classifyId) {
		this.classifyId = classifyId;
	}

	public String getClassifyName() {
		return this.classifyName;
	}

	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}

	public Set<Object> getFunctionses() {
		return this.functionses;
	}

	public void setFunctionses(Set<Object> functionses) {
		this.functionses = functionses;
	}

}