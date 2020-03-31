package com.xinggang.project.form;

import org.apache.struts.action.ActionForm;

/**
 * 滞纳金表单
 * 
 * @author Administrator
 * 
 */
public class AccountsForm extends ActionForm {

	private static final long serialVersionUID = 3457110464858588870L;
	private String accountsId;// 编号
	private Integer[] client;// 对应的客户
	private String accountsCreateTime;// 滞纳金起始日期
	private String accountsFinishTime;// 滞纳金结束日期
	private Double[] accountsExpensesSeed;// 费用合计
	private Double accountsRate;// 滞纳金费率
	private Double accountsSeed;// 滞纳金合计
	private String accountsCollectTime;// 收费时间
	private String accountsDefinedOne;//
	private Integer accountsDefinedTwo;// 支付方式
	private String accountsRemark;// 备注

	private String danwei;// 通过单位名称全拼模糊查询
	private String jiancheng;// 通过简称进行模糊查询
	private String zhujifu;// 通过助记符模糊查询

	private String begin;// 起始日期
	private String finish;// 结束日期

	// 后期增加
	private Double[] rukuCost;// 汽车入库
	private Double chukuCost;// 出库费用
	private Double[] guohuCost;// 过户费用
	private Double ercizuoyeCost;// 二次作业费用
	private Double[] cangchuCost;// 仓储费用
	private Double[] qimoWeight;// 期末库存重量
	private String shoufeiqixian;// 收费期限
	private Double zhinaFeilv;// 滞纳金费率
	private Double shishouCost;// 实收费用
	private Integer shoufeiren;// 收费人
	private String zhuangtai;// 状态（收费或者未收费）
	private String[] zidingyiFour;// 火车入库费
	private String[] zidingyiFive;// 汽车出库费
	private String[] zidingyiSix;// 火车出库

	// 后期增加结束

	private Integer jiesuanren;// 结算人
	private String jiesuantime;// 结算时间

	private String[] chukumaxtime;// 出库下站费
	private String[] zhuanchukumaxtime;// 过户下站费
	private String jiaofeiren;// 缴费人

	private String clientName;// 对应的客户名称
	private Double xishu;// 对应的货物价值的系数

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public Double getXishu() {
		return xishu;
	}

	public void setXishu(Double xishu) {
		this.xishu = xishu;
	}

	public String[] getChukumaxtime() {
		return chukumaxtime;
	}

	public void setChukumaxtime(String[] chukumaxtime) {
		this.chukumaxtime = chukumaxtime;
	}

	public String[] getZhuanchukumaxtime() {
		return zhuanchukumaxtime;
	}

	public void setZhuanchukumaxtime(String[] zhuanchukumaxtime) {
		this.zhuanchukumaxtime = zhuanchukumaxtime;
	}

	public String getJiaofeiren() {
		return jiaofeiren;
	}

	public void setJiaofeiren(String jiaofeiren) {
		this.jiaofeiren = jiaofeiren;
	}

	public Integer getJiesuanren() {
		return jiesuanren;
	}

	public void setJiesuanren(Integer jiesuanren) {
		this.jiesuanren = jiesuanren;
	}

	public String getJiesuantime() {
		return jiesuantime;
	}

	public void setJiesuantime(String jiesuantime) {
		this.jiesuantime = jiesuantime;
	}

	public String getBegin() {
		return begin;
	}

	public Double[] getRukuCost() {
		return rukuCost;
	}

	public void setRukuCost(Double[] rukuCost) {
		this.rukuCost = rukuCost;
	}

	public Double getChukuCost() {
		return chukuCost;
	}

	public void setChukuCost(Double chukuCost) {
		this.chukuCost = chukuCost;
	}

	public Double[] getGuohuCost() {
		return guohuCost;
	}

	public void setGuohuCost(Double[] guohuCost) {
		this.guohuCost = guohuCost;
	}

	public Double getErcizuoyeCost() {
		return ercizuoyeCost;
	}

	public void setErcizuoyeCost(Double ercizuoyeCost) {
		this.ercizuoyeCost = ercizuoyeCost;
	}

	public Double[] getCangchuCost() {
		return cangchuCost;
	}

	public void setCangchuCost(Double[] cangchuCost) {
		this.cangchuCost = cangchuCost;
	}

	public Double[] getQimoWeight() {
		return qimoWeight;
	}

	public void setQimoWeight(Double[] qimoWeight) {
		this.qimoWeight = qimoWeight;
	}

	public String getShoufeiqixian() {
		return shoufeiqixian;
	}

	public void setShoufeiqixian(String shoufeiqixian) {
		this.shoufeiqixian = shoufeiqixian;
	}

	public Double getZhinaFeilv() {
		return zhinaFeilv;
	}

	public void setZhinaFeilv(Double zhinaFeilv) {
		this.zhinaFeilv = zhinaFeilv;
	}

	public Double getShishouCost() {
		return shishouCost;
	}

	public void setShishouCost(Double shishouCost) {
		this.shishouCost = shishouCost;
	}

	public Integer getShoufeiren() {
		return shoufeiren;
	}

	public void setShoufeiren(Integer shoufeiren) {
		this.shoufeiren = shoufeiren;
	}

	public String getZhuangtai() {
		return zhuangtai;
	}

	public void setZhuangtai(String zhuangtai) {
		this.zhuangtai = zhuangtai;
	}

	public String[] getZidingyiFour() {
		return zidingyiFour;
	}

	public void setZidingyiFour(String[] zidingyiFour) {
		this.zidingyiFour = zidingyiFour;
	}

	public String[] getZidingyiFive() {
		return zidingyiFive;
	}

	public void setZidingyiFive(String[] zidingyiFive) {
		this.zidingyiFive = zidingyiFive;
	}

	public String[] getZidingyiSix() {
		return zidingyiSix;
	}

	public void setZidingyiSix(String[] zidingyiSix) {
		this.zidingyiSix = zidingyiSix;
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

	public String getZhujifu() {
		return zhujifu;
	}

	public void setZhujifu(String zhujifu) {
		this.zhujifu = zhujifu;
	}

	public String getAccountsId() {
		return this.accountsId;
	}

	public void setAccountsId(String accountsId) {
		this.accountsId = accountsId;
	}

	public Integer[] getClient() {
		return this.client;
	}

	public void setClient(Integer[] client) {
		this.client = client;
	}

	public String getAccountsCreateTime() {
		return this.accountsCreateTime;
	}

	public void setAccountsCreateTime(String accountsCreateTime) {
		this.accountsCreateTime = accountsCreateTime;
	}

	public String getAccountsFinishTime() {
		return this.accountsFinishTime;
	}

	public void setAccountsFinishTime(String accountsFinishTime) {
		this.accountsFinishTime = accountsFinishTime;
	}

	public Double[] getAccountsExpensesSeed() {
		return this.accountsExpensesSeed;
	}

	public void setAccountsExpensesSeed(Double[] accountsExpensesSeed) {
		this.accountsExpensesSeed = accountsExpensesSeed;
	}

	public Double getAccountsRate() {
		return this.accountsRate;
	}

	public void setAccountsRate(Double accountsRate) {
		this.accountsRate = accountsRate;
	}

	public Double getAccountsSeed() {
		return this.accountsSeed;
	}

	public void setAccountsSeed(Double accountsSeed) {
		this.accountsSeed = accountsSeed;
	}

	public String getAccountsCollectTime() {
		return this.accountsCollectTime;
	}

	public void setAccountsCollectTime(String accountsCollectTime) {
		this.accountsCollectTime = accountsCollectTime;
	}

	public String getAccountsDefinedOne() {
		return this.accountsDefinedOne;
	}

	public void setAccountsDefinedOne(String accountsDefinedOne) {
		this.accountsDefinedOne = accountsDefinedOne;
	}

	public Integer getAccountsDefinedTwo() {
		return this.accountsDefinedTwo;
	}

	public void setAccountsDefinedTwo(Integer accountsDefinedTwo) {
		this.accountsDefinedTwo = accountsDefinedTwo;
	}

	public String getAccountsRemark() {
		return this.accountsRemark;
	}

	public void setAccountsRemark(String accountsRemark) {
		this.accountsRemark = accountsRemark;
	}

}