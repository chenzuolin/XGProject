package com.xinggang.project.form;

import org.apache.struts.action.ActionForm;

/**
 * 过户子订单类
 * 
 * @author Administrator
 * 
 */
public class ShiftStockSeedForm extends ActionForm {

	private static final long serialVersionUID = 120814507514822607L;
	private String ssseedId;// 子订单编号
	private Integer interiorUserBySsseedAuditing;// 对应的审核人员
	private Integer interiorUserBySsseedCollectMan;// 对应的收费人员
	private String shiftStock;// 对应的总订单
	private Integer paymentFashion;// 对应的收费方式类
	private Integer goods;// 对应的货物
	private String ssseedAuditingTime;// 审核时间
	private String ssseedAuditingTrue;// 审核是否通过
	private Double ssseedShouldCost;// 应收费用
	private Double ssseedRealityCost;// 实收费用
	private String ssseedCollectTime;// 收费时间
	private Double ssseedWeight;// 过户重量
	private Double ssseedNumber;// 过户件数
	private String ssseedClientAccounts;// 结算方式
	private String ssseedOrderStatus;// 订单状态
	private String ssseedDefinedOne;// 预留字段一
	private String ssseedDefinedTwo;// 预留字段二
	private String ssseedRemark;// 备注

	private Integer clientBySstockClientId;// 对应的货主，当审核通过的时候，给相应的转入单位增加库存，当审核未通过的时候，给相应的转出单位增加相应的库存
	private Integer clientBySstockShiftToFirm;// 对应的转入单位

	private Integer clientId;// 当查询某个客户的过户的订单的时候用到

	// 模糊查询时调用的属性

	private String begin;// 时间的起始日期
	private String finish;// 时间的结束日期
	private String danwei;// 客户单位全拼
	private String jiancheng;// 客户单位简称
	private String danweizhujifu;// 单位助记符
	private String goodsname;// 货物名称
	private String goodszhujifu;// 货物助记符
	private Integer pinlei;// 当发起过户的时候选择对应的品类之后，通过品类查询后面的名称
	private Integer guige;
	private Integer caizhi;
	private Integer shuxing;
	private Integer chandi;

	private String kehudanhao;// 查询全部时通过客户单号进行模糊查询

	private String huozhu;// 当审核的时候模糊查询的货主
	private String tihuohao;// 当审核的时候模糊查询的客户订单号

	public String getKehudanhao() {
		return kehudanhao;
	}

	public void setKehudanhao(String kehudanhao) {
		this.kehudanhao = kehudanhao;
	}

	public String getHuozhu() {
		return huozhu;
	}

	public void setHuozhu(String huozhu) {
		this.huozhu = huozhu;
	}

	public String getTihuohao() {
		return tihuohao;
	}

	public void setTihuohao(String tihuohao) {
		this.tihuohao = tihuohao;
	}

	public Integer getPinlei() {
		return pinlei;
	}

	public void setPinlei(Integer pinlei) {
		this.pinlei = pinlei;
	}

	public Integer getGuige() {
		return guige;
	}

	public void setGuige(Integer guige) {
		this.guige = guige;
	}

	public Integer getCaizhi() {
		return caizhi;
	}

	public void setCaizhi(Integer caizhi) {
		this.caizhi = caizhi;
	}

	public Integer getShuxing() {
		return shuxing;
	}

	public void setShuxing(Integer shuxing) {
		this.shuxing = shuxing;
	}

	public Integer getChandi() {
		return chandi;
	}

	public void setChandi(Integer chandi) {
		this.chandi = chandi;
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

	public String getDanwei() {
		return danwei;
	}

	public void setDanwei(String danwei) {
		this.danwei = danwei;
	}

	public String getJiancheng() {
		return jiancheng;
	}

	public void setJiancheng(String jiancheng) {
		this.jiancheng = jiancheng;
	}

	public String getDanweizhujifu() {
		return danweizhujifu;
	}

	public void setDanweizhujifu(String danweizhujifu) {
		this.danweizhujifu = danweizhujifu;
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public String getGoodszhujifu() {
		return goodszhujifu;
	}

	public void setGoodszhujifu(String goodszhujifu) {
		this.goodszhujifu = goodszhujifu;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Integer getClientBySstockClientId() {
		return clientBySstockClientId;
	}

	public void setClientBySstockClientId(Integer clientBySstockClientId) {
		this.clientBySstockClientId = clientBySstockClientId;
	}

	public Integer getClientBySstockShiftToFirm() {
		return clientBySstockShiftToFirm;
	}

	public void setClientBySstockShiftToFirm(Integer clientBySstockShiftToFirm) {
		this.clientBySstockShiftToFirm = clientBySstockShiftToFirm;
	}

	public String getSsseedId() {
		return this.ssseedId;
	}

	public void setSsseedId(String ssseedId) {
		this.ssseedId = ssseedId;
	}

	public Integer getInteriorUserBySsseedAuditing() {
		return this.interiorUserBySsseedAuditing;
	}

	public void setInteriorUserBySsseedAuditing(
			Integer interiorUserBySsseedAuditing) {
		this.interiorUserBySsseedAuditing = interiorUserBySsseedAuditing;
	}

	public Integer getInteriorUserBySsseedCollectMan() {
		return this.interiorUserBySsseedCollectMan;
	}

	public void setInteriorUserBySsseedCollectMan(
			Integer interiorUserBySsseedCollectMan) {
		this.interiorUserBySsseedCollectMan = interiorUserBySsseedCollectMan;
	}

	public String getShiftStock() {
		return this.shiftStock;
	}

	public void setShiftStock(String shiftStock) {
		this.shiftStock = shiftStock;
	}

	public Integer getPaymentFashion() {
		return this.paymentFashion;
	}

	public void setPaymentFashion(Integer paymentFashion) {
		this.paymentFashion = paymentFashion;
	}

	public Integer getGoods() {
		return this.goods;
	}

	public void setGoods(Integer goods) {
		this.goods = goods;
	}

	public String getSsseedAuditingTime() {
		return this.ssseedAuditingTime;
	}

	public void setSsseedAuditingTime(String ssseedAuditingTime) {
		this.ssseedAuditingTime = ssseedAuditingTime;
	}

	public String getSsseedAuditingTrue() {
		return this.ssseedAuditingTrue;
	}

	public void setSsseedAuditingTrue(String ssseedAuditingTrue) {
		this.ssseedAuditingTrue = ssseedAuditingTrue;
	}

	public Double getSsseedShouldCost() {
		return this.ssseedShouldCost;
	}

	public void setSsseedShouldCost(Double ssseedShouldCost) {
		this.ssseedShouldCost = ssseedShouldCost;
	}

	public Double getSsseedRealityCost() {
		return this.ssseedRealityCost;
	}

	public void setSsseedRealityCost(Double ssseedRealityCost) {
		this.ssseedRealityCost = ssseedRealityCost;
	}

	public String getSsseedCollectTime() {
		return this.ssseedCollectTime;
	}

	public void setSsseedCollectTime(String ssseedCollectTime) {
		this.ssseedCollectTime = ssseedCollectTime;
	}

	public Double getSsseedWeight() {
		return this.ssseedWeight;
	}

	public void setSsseedWeight(Double ssseedWeight) {
		this.ssseedWeight = ssseedWeight;
	}

	public Double getSsseedNumber() {
		return this.ssseedNumber;
	}

	public void setSsseedNumber(Double ssseedNumber) {
		this.ssseedNumber = ssseedNumber;
	}

	public String getSsseedClientAccounts() {
		return this.ssseedClientAccounts;
	}

	public void setSsseedClientAccounts(String ssseedClientAccounts) {
		this.ssseedClientAccounts = ssseedClientAccounts;
	}

	public String getSsseedOrderStatus() {
		return this.ssseedOrderStatus;
	}

	public void setSsseedOrderStatus(String ssseedOrderStatus) {
		this.ssseedOrderStatus = ssseedOrderStatus;
	}

	public String getSsseedDefinedOne() {
		return this.ssseedDefinedOne;
	}

	public void setSsseedDefinedOne(String ssseedDefinedOne) {
		this.ssseedDefinedOne = ssseedDefinedOne;
	}

	public String getSsseedDefinedTwo() {
		return this.ssseedDefinedTwo;
	}

	public void setSsseedDefinedTwo(String ssseedDefinedTwo) {
		this.ssseedDefinedTwo = ssseedDefinedTwo;
	}

	public String getSsseedRemark() {
		return this.ssseedRemark;
	}

	public void setSsseedRemark(String ssseedRemark) {
		this.ssseedRemark = ssseedRemark;
	}

}