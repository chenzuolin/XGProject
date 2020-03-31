package com.xinggang.project.entity;

/**
 * Powers entity. @author MyEclipse Persistence Tools
 * 权限类
 */

public class Powers implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2812108276170816672L;
	/**
	 * 
	 */
	private Integer powerId;//权限编号
	private InteriorUserDuty interiorUserDuty;//对应的职责类
	private Functions functions;//对应的功能类
	private String powerDefinedOne;//自定义字段1
	private String powerDefinedTwo;//自定义字段2
	private String powerRemark;//备注

	// Constructors

	/** default constructor */
	public Powers() {
	}

	/** full constructor */
	public Powers(InteriorUserDuty interiorUserDuty, Functions functions,
			String powerDefinedOne, String powerDefinedTwo, String powerRemark) {
		this.interiorUserDuty = interiorUserDuty;
		this.functions = functions;
		this.powerDefinedOne = powerDefinedOne;
		this.powerDefinedTwo = powerDefinedTwo;
		this.powerRemark = powerRemark;
	}

	// Property accessors

	public Integer getPowerId() {
		return this.powerId;
	}

	public void setPowerId(Integer powerId) {
		this.powerId = powerId;
	}

	public InteriorUserDuty getInteriorUserDuty() {
		return this.interiorUserDuty;
	}

	public void setInteriorUserDuty(InteriorUserDuty interiorUserDuty) {
		this.interiorUserDuty = interiorUserDuty;
	}

	public Functions getFunctions() {
		return this.functions;
	}

	public void setFunctions(Functions functions) {
		this.functions = functions;
	}

	public String getPowerDefinedOne() {
		return this.powerDefinedOne;
	}

	public void setPowerDefinedOne(String powerDefinedOne) {
		this.powerDefinedOne = powerDefinedOne;
	}

	public String getPowerDefinedTwo() {
		return this.powerDefinedTwo;
	}

	public void setPowerDefinedTwo(String powerDefinedTwo) {
		this.powerDefinedTwo = powerDefinedTwo;
	}

	public String getPowerRemark() {
		return this.powerRemark;
	}

	public void setPowerRemark(String powerRemark) {
		this.powerRemark = powerRemark;
	}

}