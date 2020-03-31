package com.xinggang.project.form;

import org.apache.struts.action.ActionForm;

/**
 * 滞纳金表单
 * 
 * @author Administrator
 * 
 */
public class SetAccountsForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1111274804111081863L;
	private Integer said;// 滞纳金设置编号
	private String saday;// 轧账号数
	private String satime;// 设置时间，
	private Double sarate;// 费率
	private String sadefinedOne;// 预留字段一
	private String sadefinedTwo;// 预留字段二
	private String saremark;// 备注

	public Integer getSaid() {
		return said;
	}

	public void setSaid(Integer said) {
		this.said = said;
	}

	public String getSaday() {
		return saday;
	}

	public void setSaday(String saday) {
		this.saday = saday;
	}

	public String getSatime() {
		return satime;
	}

	public void setSatime(String satime) {
		this.satime = satime;
	}

	public Double getSarate() {
		return sarate;
	}

	public void setSarate(Double sarate) {
		this.sarate = sarate;
	}

	public String getSadefinedOne() {
		return sadefinedOne;
	}

	public void setSadefinedOne(String sadefinedOne) {
		this.sadefinedOne = sadefinedOne;
	}

	public String getSadefinedTwo() {
		return sadefinedTwo;
	}

	public void setSadefinedTwo(String sadefinedTwo) {
		this.sadefinedTwo = sadefinedTwo;
	}

	public String getSaremark() {
		return saremark;
	}

	public void setSaremark(String saremark) {
		this.saremark = saremark;
	}

}