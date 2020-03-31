package com.xinggang.project.entity;

/**
 * 滞纳金类
 */

public class Accounts implements java.io.Serializable {

	private static final long serialVersionUID = -1676859818883700406L;

	private String accountsId;// 滞纳金编号
	private Client client;// 对应的客户，调用客户类
	private String accountsCreateTime;// 起始日期
	private String accountsFinishTime;// 结束日期
	private Double accountsExpensesSeed;// 费用合计
	private Double accountsSeed;// 滞纳金合计
	private String accountsCollectTime;// 收费时间
	private PaymentFashion accountsDefinedTwo;// 支付方式

	private Double rukuCost;// 汽车入库费
	private Double guohuCost;// 过户费用
	private Double cangchuCost;// 仓储费用
	private Double qimoWeight;// 期末库存重量
	private String shoufeiqixian;// 收费期限
	private Double zhinaFeilv;// 滞纳金费率

	private InteriorUser jiesuanren;// 结算人
	private String jiesuantime;// 结算时间
	private Double shishouCost;// 实收费用
	private InteriorUser shoufeiren;// 收费人
	private String zhuangtai;// 状态（收费或者未收费）
	private String zidingyiFour;// 火车入库费
	private String zidingyiFive;// 汽车出库费
	private String zidingyiSix;// 火车出库费

	private String accountsRemark;// 备注
	// 后期增加
	private String jiaofeiren;// 缴费人

	private Double ercizuoyeCost;// 二次作业费用

	private String accountsDefinedOne;//
	// 后期增加结束
	private Double rukuheji;// 入库费用的合计
	private Double chukuheji;// 出库费用的合计
	private Double guohuheji;// 过户费用的合计
	private Double erciheji;// 二次作业费的合计

	private Double qimokucun;// 统计期末库存

	private String chukumaxtime;// 出库下站费
	private String zhuanchukumaxtime;// 过户下站费

	private Double cangchufei;// 定义仓储费

	private Double setAccounts;// 无用字段

	private Double chukuCost;// 出库费用

	public Accounts() {
	}

	// 入库费用总和，出库费用总和，二次作业费用总和，过户应收费用总和，客户
	public Accounts(Double rukuheji, Double chukuheji, Double erciheji,
			Double guohuheji, Client client) {
		this.rukuheji = rukuheji;
		this.chukuheji = chukuheji;
		this.erciheji = erciheji;
		this.guohuheji = guohuheji;
		this.client = client;
	}

	// 统计最大的出库业务的时间
	public Accounts(String chukumaxtime, String zhuanchukumaxtime, Client client) {
		this.chukumaxtime = chukumaxtime;
		this.zhuanchukumaxtime = zhuanchukumaxtime;
		this.client = client;
	}

	// 统计期末库存
	public Accounts(Double qimokucun, Client client) {
		this.qimokucun = qimokucun;
		this.client = client;
	}

	public Accounts(Double setAccounts, Client client,
			String accountsCreateTime, String accountsFinishTime,
			Double accountsExpensesSeed, Double accountsSeed,
			String accountsCollectTime, String accountsDefinedOne,
			PaymentFashion accountsDefinedTwo, String accountsRemark,
			Double rukuCost, Double chukuCost, Double guohuCost,
			Double ercizuoyeCost, Double cangchuCost, Double qimoWeight,
			String shoufeiqixian, Double zhinaFeilv, Double shishouCost,
			InteriorUser shoufeiren, String zhuangtai, String zidingyiFour,
			String zidingyiFive, String zidingyiSix) {
		this.rukuCost = rukuCost;
		this.chukuCost = chukuCost;
		this.guohuCost = guohuCost;
		this.ercizuoyeCost = ercizuoyeCost;
		this.cangchuCost = cangchuCost;
		this.qimoWeight = qimoWeight;
		this.shoufeiqixian = shoufeiqixian;
		this.zhinaFeilv = zhinaFeilv;
		this.shishouCost = shishouCost;
		this.shoufeiren = shoufeiren;
		this.zhuangtai = zhuangtai;
		this.zidingyiFour = zidingyiFour;
		this.zidingyiFive = zidingyiFive;
		this.zidingyiSix = zidingyiSix;

		this.setAccounts = setAccounts;
		this.client = client;
		this.accountsCreateTime = accountsCreateTime;
		this.accountsFinishTime = accountsFinishTime;
		this.accountsExpensesSeed = accountsExpensesSeed;
		this.accountsSeed = accountsSeed;
		this.accountsCollectTime = accountsCollectTime;
		this.accountsDefinedOne = accountsDefinedOne;
		this.accountsDefinedTwo = accountsDefinedTwo;
		this.accountsRemark = accountsRemark;
	}

	// 滞纳金编号，滞纳金费率（滞纳金设置类），客户，起始日期，结束日期,费用合计，滞纳金合计，收费时间,滞纳金的实收费用，支付方式，备注入库费，出库费，过户费，二次作业费,仓储费，期末库存重量
	// 收费期限，收费人，状态，自定义4，自定义5，自定义6
	public Accounts(String accountsId, Double zhinaFeilv, Client client,
			String accountsCreateTime, String accountsFinishTime,
			Double accountsExpensesSeed, Double accountsSeed,
			String accountsCollectTime, Double shishouCost,
			PaymentFashion accountsDefinedTwo, String accountsRemark,
			Double rukuCost, Double chukuCost, Double guohuCost,
			Double ercizuoyeCost, Double cangchuCost, Double qimoWeight,
			String shoufeiqixian, InteriorUser shoufeiren, String zhuangtai,
			String zidingyiFour, String zidingyiFive, String zidingyiSix) {
		this.rukuCost = rukuCost;
		this.chukuCost = chukuCost;
		this.guohuCost = guohuCost;
		this.ercizuoyeCost = ercizuoyeCost;
		this.cangchuCost = cangchuCost;
		this.qimoWeight = qimoWeight;
		this.shoufeiqixian = shoufeiqixian;
		this.shoufeiren = shoufeiren;
		this.zhuangtai = zhuangtai;
		this.zidingyiFour = zidingyiFour;
		this.zidingyiFive = zidingyiFive;
		this.zidingyiSix = zidingyiSix;

		this.zhinaFeilv = zhinaFeilv;
		this.accountsId = accountsId;
		this.client = client;
		this.accountsCreateTime = accountsCreateTime;
		this.accountsFinishTime = accountsFinishTime;
		this.accountsExpensesSeed = accountsExpensesSeed;
		this.accountsSeed = accountsSeed;
		this.accountsCollectTime = accountsCollectTime;
		this.accountsDefinedTwo = accountsDefinedTwo;
		this.accountsRemark = accountsRemark;
		this.shishouCost = shishouCost;
	}

	public String getJiesuantime() {
		return jiesuantime;
	}

	public String getJiaofeiren() {
		return jiaofeiren;
	}

	public void setJiaofeiren(String jiaofeiren) {
		this.jiaofeiren = jiaofeiren;
	}

	public void setJiesuantime(String jiesuantime) {
		this.jiesuantime = jiesuantime;
	}

	public InteriorUser getJiesuanren() {
		return jiesuanren;
	}

	public void setJiesuanren(InteriorUser jiesuanren) {
		this.jiesuanren = jiesuanren;
	}

	public Double getRukuCost() {
		return rukuCost;
	}

	public void setRukuCost(Double rukuCost) {
		this.rukuCost = rukuCost;
	}

	public Double getChukuCost() {
		return chukuCost;
	}

	public void setChukuCost(Double chukuCost) {
		this.chukuCost = chukuCost;
	}

	public Double getGuohuCost() {
		return guohuCost;
	}

	public void setGuohuCost(Double guohuCost) {
		this.guohuCost = guohuCost;
	}

	public Double getErcizuoyeCost() {
		return ercizuoyeCost;
	}

	public void setErcizuoyeCost(Double ercizuoyeCost) {
		this.ercizuoyeCost = ercizuoyeCost;
	}

	public Double getCangchuCost() {
		return cangchuCost;
	}

	public void setCangchuCost(Double cangchuCost) {
		this.cangchuCost = cangchuCost;
	}

	public Double getQimoWeight() {
		return qimoWeight;
	}

	public void setQimoWeight(Double qimoWeight) {
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

	public InteriorUser getShoufeiren() {
		return shoufeiren;
	}

	public void setShoufeiren(InteriorUser shoufeiren) {
		this.shoufeiren = shoufeiren;
	}

	public String getZhuangtai() {
		return zhuangtai;
	}

	public void setZhuangtai(String zhuangtai) {
		this.zhuangtai = zhuangtai;
	}

	public String getZidingyiFour() {
		return zidingyiFour;
	}

	public void setZidingyiFour(String zidingyiFour) {
		this.zidingyiFour = zidingyiFour;
	}

	public String getZidingyiFive() {
		return zidingyiFive;
	}

	public void setZidingyiFive(String zidingyiFive) {
		this.zidingyiFive = zidingyiFive;
	}

	public String getZidingyiSix() {
		return zidingyiSix;
	}

	public void setZidingyiSix(String zidingyiSix) {
		this.zidingyiSix = zidingyiSix;
	}

	public Double getCangchufei() {
		return cangchufei;
	}

	public void setCangchufei(Double cangchufei) {
		this.cangchufei = cangchufei;
	}

	public String getChukumaxtime() {
		return chukumaxtime;
	}

	public void setChukumaxtime(String chukumaxtime) {
		this.chukumaxtime = chukumaxtime;
	}

	public String getZhuanchukumaxtime() {
		return zhuanchukumaxtime;
	}

	public void setZhuanchukumaxtime(String zhuanchukumaxtime) {
		this.zhuanchukumaxtime = zhuanchukumaxtime;
	}

	public Double getQimokucun() {
		return qimokucun;
	}

	public void setQimokucun(Double qimokucun) {
		this.qimokucun = qimokucun;
	}

	public Double getRukuheji() {
		return rukuheji;
	}

	public void setRukuheji(Double rukuheji) {
		this.rukuheji = rukuheji;
	}

	public Double getChukuheji() {
		return chukuheji;
	}

	public void setChukuheji(Double chukuheji) {
		this.chukuheji = chukuheji;
	}

	public Double getGuohuheji() {
		return guohuheji;
	}

	public void setGuohuheji(Double guohuheji) {
		this.guohuheji = guohuheji;
	}

	public Double getErciheji() {
		return erciheji;
	}

	public void setErciheji(Double erciheji) {
		this.erciheji = erciheji;
	}

	public String getAccountsId() {
		return this.accountsId;
	}

	public void setAccountsId(String accountsId) {
		this.accountsId = accountsId;
	}

	public Double getSetAccounts() {
		return this.setAccounts;
	}

	public void setSetAccounts(Double setAccounts) {
		this.setAccounts = setAccounts;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
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

	public Double getAccountsExpensesSeed() {
		return this.accountsExpensesSeed;
	}

	public void setAccountsExpensesSeed(Double accountsExpensesSeed) {
		this.accountsExpensesSeed = accountsExpensesSeed;
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

	public PaymentFashion getAccountsDefinedTwo() {
		return this.accountsDefinedTwo;
	}

	public void setAccountsDefinedTwo(PaymentFashion accountsDefinedTwo) {
		this.accountsDefinedTwo = accountsDefinedTwo;
	}

	public String getAccountsRemark() {
		return this.accountsRemark;
	}

	public void setAccountsRemark(String accountsRemark) {
		this.accountsRemark = accountsRemark;
	}

}