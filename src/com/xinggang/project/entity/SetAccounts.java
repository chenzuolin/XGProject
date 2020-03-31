package com.xinggang.project.entity;

/**
 * 滞纳金设置类
 * 
 * @author Administrator
 * 
 */

public class SetAccounts implements java.io.Serializable {

	private static final long serialVersionUID = -7819737998558943000L;
	private Integer said;// 滞纳金设置编号
	private String saday;// 轧账号数
	private String satime;// 设置时间，
	private Double sarate;// 费率
	private String sadefinedOne;// 预留字段一
	private String sadefinedTwo;// 预留字段二
	private String saremark;// 备注

	public SetAccounts() {
	}

	public SetAccounts(String saday, String satime, Double sarate,
			String sadefinedOne, String sadefinedTwo, String saremark) {
		this.saday = saday;
		this.satime = satime;
		this.sarate = sarate;
		this.sadefinedOne = sadefinedOne;
		this.sadefinedTwo = sadefinedTwo;
		this.saremark = saremark;
	}

	public Integer getSaid() {
		return this.said;
	}

	public void setSaid(Integer said) {
		this.said = said;
	}

	public String getSaday() {
		return this.saday;
	}

	public void setSaday(String saday) {
		this.saday = saday;
	}

	public String getSatime() {
		return this.satime;
	}

	public void setSatime(String satime) {
		this.satime = satime;
	}

	public Double getSarate() {
		return this.sarate;
	}

	public void setSarate(Double sarate) {
		this.sarate = sarate;
	}

	public String getSadefinedOne() {
		return this.sadefinedOne;
	}

	public void setSadefinedOne(String sadefinedOne) {
		this.sadefinedOne = sadefinedOne;
	}

	public String getSadefinedTwo() {
		return this.sadefinedTwo;
	}

	public void setSadefinedTwo(String sadefinedTwo) {
		this.sadefinedTwo = sadefinedTwo;
	}

	public String getSaremark() {
		return this.saremark;
	}

	public void setSaremark(String saremark) {
		this.saremark = saremark;
	}

}