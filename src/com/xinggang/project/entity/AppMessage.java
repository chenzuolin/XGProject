package com.xinggang.project.entity;

/**
 * AppMessage entity. @author MyEclipse Persistence Tools 客户app消息记录类
 */

public class AppMessage implements java.io.Serializable {

	private static final long serialVersionUID = -685324133759217690L;
	private String appId;// 编号
	private Integer appClientOne;// 客户一
	private Integer appClientTwo;// 客户二
	private String appIndentId;// 订单总编号
	private String appIndentZiId;// 订单子订单编号
	private String appIndentGoodsName;// 货物的名称
	private String appTime;// 消息的时间
	private String appContent;// 消息的内容
	private String appStatus;// 消息的状态，是否是未读//1否0是
	private String appType;// 类型
	private String appDefinedOne;// 预留一
	private String appDefinedTwo;// 预留二
	private String appDefinedThree;// 预留三
	private String appDefinedFour;// 预留四
	private String appDefinedFive;// 预留五

	public AppMessage() {
	}

	public AppMessage(String appId) {
		this.appId = appId;
	}

	public AppMessage(String appId, Integer appClientOne, Integer appClientTwo,
			String appIndentId, String appIndentZiId,
			String appIndentGoodsName, String appTime, String appContent,
			String appStatus, String appType, String appDefinedOne,
			String appDefinedTwo, String appDefinedThree,
			String appDefinedFour, String appDefinedFive) {
		this.appId = appId;
		this.appClientOne = appClientOne;
		this.appClientTwo = appClientTwo;
		this.appIndentId = appIndentId;
		this.appIndentZiId = appIndentZiId;
		this.appIndentGoodsName = appIndentGoodsName;
		this.appTime = appTime;
		this.appContent = appContent;
		this.appStatus = appStatus;
		this.appType = appType;
		this.appDefinedOne = appDefinedOne;
		this.appDefinedTwo = appDefinedTwo;
		this.appDefinedThree = appDefinedThree;
		this.appDefinedFour = appDefinedFour;
		this.appDefinedFive = appDefinedFive;
	}

	public String getAppId() {
		return this.appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public Integer getAppClientOne() {
		return this.appClientOne;
	}

	public void setAppClientOne(Integer appClientOne) {
		this.appClientOne = appClientOne;
	}

	public Integer getAppClientTwo() {
		return this.appClientTwo;
	}

	public void setAppClientTwo(Integer appClientTwo) {
		this.appClientTwo = appClientTwo;
	}

	public String getAppIndentId() {
		return this.appIndentId;
	}

	public void setAppIndentId(String appIndentId) {
		this.appIndentId = appIndentId;
	}

	public String getAppIndentZiId() {
		return this.appIndentZiId;
	}

	public void setAppIndentZiId(String appIndentZiId) {
		this.appIndentZiId = appIndentZiId;
	}

	public String getAppIndentGoodsName() {
		return this.appIndentGoodsName;
	}

	public void setAppIndentGoodsName(String appIndentGoodsName) {
		this.appIndentGoodsName = appIndentGoodsName;
	}

	public String getAppTime() {
		return this.appTime;
	}

	public void setAppTime(String appTime) {
		this.appTime = appTime;
	}

	public String getAppContent() {
		return this.appContent;
	}

	public void setAppContent(String appContent) {
		this.appContent = appContent;
	}

	public String getAppStatus() {
		return this.appStatus;
	}

	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}

	public String getAppType() {
		return this.appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getAppDefinedOne() {
		return this.appDefinedOne;
	}

	public void setAppDefinedOne(String appDefinedOne) {
		this.appDefinedOne = appDefinedOne;
	}

	public String getAppDefinedTwo() {
		return this.appDefinedTwo;
	}

	public void setAppDefinedTwo(String appDefinedTwo) {
		this.appDefinedTwo = appDefinedTwo;
	}

	public String getAppDefinedThree() {
		return this.appDefinedThree;
	}

	public void setAppDefinedThree(String appDefinedThree) {
		this.appDefinedThree = appDefinedThree;
	}

	public String getAppDefinedFour() {
		return this.appDefinedFour;
	}

	public void setAppDefinedFour(String appDefinedFour) {
		this.appDefinedFour = appDefinedFour;
	}

	public String getAppDefinedFive() {
		return this.appDefinedFive;
	}

	public void setAppDefinedFive(String appDefinedFive) {
		this.appDefinedFive = appDefinedFive;
	}

}