package com.xinggang.project.form;

import org.apache.struts.action.ActionForm;

public class TidingsForm extends ActionForm {

	// 使用于TidingsAction，用来统计鑫港历史的库存
	/**
	 * 
	 */
	private static final long serialVersionUID = 8288471969205774813L;
	private String begin;// 起始日期
	private String finish;// 结束日期
	private String goodsName;// 货物名称
	private String pinlei;// 货物品类
	private String jiancheng;// 客户简称
	private String guige;// 货物规格
	private String caizhi;// 货物材质
	private String shuxing;// 货物属性
	private String chandi;// 货物产地

	public String getGuige() {
		return guige;
	}

	public void setGuige(String guige) {
		this.guige = guige;
	}

	public String getCaizhi() {
		return caizhi;
	}

	public void setCaizhi(String caizhi) {
		this.caizhi = caizhi;
	}

	public String getShuxing() {
		return shuxing;
	}

	public void setShuxing(String shuxing) {
		this.shuxing = shuxing;
	}

	public String getChandi() {
		return chandi;
	}

	public void setChandi(String chandi) {
		this.chandi = chandi;
	}

	public String getJiancheng() {
		return jiancheng;
	}

	public void setJiancheng(String jiancheng) {
		this.jiancheng = jiancheng;
	}

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

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getPinlei() {
		return pinlei;
	}

	public void setPinlei(String pinlei) {
		this.pinlei = pinlei;
	}

}
