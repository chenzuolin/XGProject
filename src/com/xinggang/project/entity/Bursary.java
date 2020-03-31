package com.xinggang.project.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 库房Bursary
 * 
 * @author Administrator
 * 
 */
public class Bursary implements java.io.Serializable {

	private static final long serialVersionUID = -3570027573397315902L;
	private Integer bursaryId;// 库房编号
	private String bursaryName;// 库房名称
	private Double bursaryMaxWeight;// 库房最大重量
	private String bursaryDefinedOne;//停用
	private String bursaryDefinedTwo;//库房的位置
	private String bursaryRemark;// 备注
	private Set<Object> tarehouses = new HashSet<Object>(0);// 对应的库位类
	private Set<Object> bursary = new HashSet<Object>(0);// 对应的盘点客户货物库存类

	public Bursary() {
	}

	public Bursary(String bursaryName, Double bursaryMaxWeight,
			String bursaryDefinedOne, String bursaryDefinedTwo,
			String bursaryRemark, Set<Object> tarehouses, Set<Object> bursary) {
		this.bursary = bursary;
		this.bursaryName = bursaryName;
		this.bursaryMaxWeight = bursaryMaxWeight;
		this.bursaryDefinedOne = bursaryDefinedOne;
		this.bursaryDefinedTwo = bursaryDefinedTwo;
		this.bursaryRemark = bursaryRemark;
		this.tarehouses = tarehouses;
	}

	public Set<Object> getBursary() {
		return bursary;
	}

	public void setBursary(Set<Object> bursary) {
		this.bursary = bursary;
	}

	public Integer getBursaryId() {
		return this.bursaryId;
	}

	public void setBursaryId(Integer bursaryId) {
		this.bursaryId = bursaryId;
	}

	public String getBursaryName() {
		return this.bursaryName;
	}

	public void setBursaryName(String bursaryName) {
		this.bursaryName = bursaryName;
	}

	public Double getBursaryMaxWeight() {
		return this.bursaryMaxWeight;
	}

	public void setBursaryMaxWeight(Double bursaryMaxWeight) {
		this.bursaryMaxWeight = bursaryMaxWeight;
	}

	public String getBursaryDefinedOne() {
		return this.bursaryDefinedOne;
	}

	public void setBursaryDefinedOne(String bursaryDefinedOne) {
		this.bursaryDefinedOne = bursaryDefinedOne;
	}

	public String getBursaryDefinedTwo() {
		return this.bursaryDefinedTwo;
	}

	public void setBursaryDefinedTwo(String bursaryDefinedTwo) {
		this.bursaryDefinedTwo = bursaryDefinedTwo;
	}

	public String getBursaryRemark() {
		return this.bursaryRemark;
	}

	public void setBursaryRemark(String bursaryRemark) {
		this.bursaryRemark = bursaryRemark;
	}

	public Set<Object> getTarehouses() {
		return this.tarehouses;
	}

	public void setTarehouses(Set<Object> tarehouses) {
		this.tarehouses = tarehouses;
	}

}