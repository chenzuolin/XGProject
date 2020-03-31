package com.xinggang.project.form;

import org.apache.struts.action.ActionForm;

/**
 * 出库子订单表单
 * 
 * @author Administrator
 * 
 */
public class ExportSeedForm extends ActionForm {

	private static final long serialVersionUID = -608563319160455036L;
	private String eseedId; // 出库子订单编号
	private Integer paymentFashion;// 支付方式，对应的支付方式类
	private String export;// 出库总订单编号，对应的出库总订单类
	private Integer goods;// 出库的货物，对应的货物类
	private Double eseedShouldWeight;// 应出重量
	private Double eseedRealityWeight;// 实出重量
	private Double eseedShouldNumber;// 应出件数
	private Double eseedRealityNumber;// 实出件数
	private Double eseedShouldCost;// 应收费用
	private Double eseedRealityCost;// 实收费用
	private Double eseedSecondWork;// 二次作业重量
	private Double eseedSwshouldCost;// 二次作业重量应收费用
	private Double eseedSwrealityCost;// 二次作业重量实收费用
	private String eseedClientAccounts;// 结算方式(现/月)
	private String eseedOrderStatus;// 订单状态（计划出库，准备出库，出库完成，正在审核，审核通过，审核未通过，正在收费，收费完成）
	private String eseedDefinedOne;// 预留字段一
	private String eseedDefinedTwo;// 预留字段二
	private String eseedRemark;// 备注

	private String zongbianhao;// 总出库订单的编号
	private String kehubianhao;// 总订单中客户的编号
	private String danwei;// 客户中的单位全拼
	private String jiancheng;// 客户中单位的简称
	private String danweizhujifu;// 客户类中单位的助记符
	private String goodsName;// 货物的名称
	private String guige;// 货物的规格
	private String caizhi;// 货物的材质
	private String shuxing;// 货物属性
	private String zhujifu;// 货物的助记符

	private Integer[] kuwei;// 对应的货物
	private String[] pici;// 对应的批次
	private String[] beizhu;// 备注多个
	private Integer diaodu;// 调度员j
	private Integer baoguan;// 保管员
	private Double fenpeizhongliang;// 分配重量
	private String lisuan;// 过磅货物还是理算货物

	private String chucaobianhao;// 出库操作订单编号
	private Double chucaoWeight;// 出库操作订单中的重量
	private Double chucaoNumber;// 出库操作订单中的件数
	private Double chucaoSholdCost;// 出库操作订单中的应收费用
	private Double chucaoRealityCost;// 出库操作订单中的实收费用

	private String begin;// 起始日期
	private String finish;// 结束日期

	private Integer clientId;// 客户编号

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
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

	public Double getChucaoWeight() {
		return chucaoWeight;
	}

	public void setChucaoWeight(Double chucaoWeight) {
		this.chucaoWeight = chucaoWeight;
	}

	public Double getChucaoNumber() {
		return chucaoNumber;
	}

	public void setChucaoNumber(Double chucaoNumber) {
		this.chucaoNumber = chucaoNumber;
	}

	public Double getChucaoSholdCost() {
		return chucaoSholdCost;
	}

	public void setChucaoSholdCost(Double chucaoSholdCost) {
		this.chucaoSholdCost = chucaoSholdCost;
	}

	public Double getChucaoRealityCost() {
		return chucaoRealityCost;
	}

	public void setChucaoRealityCost(Double chucaoRealityCost) {
		this.chucaoRealityCost = chucaoRealityCost;
	}

	public String getChucaobianhao() {
		return chucaobianhao;
	}

	public void setChucaobianhao(String chucaobianhao) {
		this.chucaobianhao = chucaobianhao;
	}

	public String[] getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String[] beizhu) {
		this.beizhu = beizhu;
	}

	public String getLisuan() {
		return lisuan;
	}

	public void setLisuan(String lisuan) {
		this.lisuan = lisuan;
	}

	public Integer getDiaodu() {
		return diaodu;
	}

	public void setDiaodu(Integer diaodu) {
		this.diaodu = diaodu;
	}

	public Integer getBaoguan() {
		return baoguan;
	}

	public void setBaoguan(Integer baoguan) {
		this.baoguan = baoguan;
	}

	public Double getFenpeizhongliang() {
		return fenpeizhongliang;
	}

	public void setFenpeizhongliang(Double fenpeizhongliang) {
		this.fenpeizhongliang = fenpeizhongliang;
	}

	public Integer[] getKuwei() {
		return kuwei;
	}

	public void setKuwei(Integer[] kuwei) {
		this.kuwei = kuwei;
	}

	public String[] getPici() {
		return pici;
	}

	public void setPici(String[] pici) {
		this.pici = pici;
	}

	public String getZongbianhao() {
		return zongbianhao;
	}

	public void setZongbianhao(String zongbianhao) {
		this.zongbianhao = zongbianhao;
	}

	public String getKehubianhao() {
		return kehubianhao;
	}

	public void setKehubianhao(String kehubianhao) {
		this.kehubianhao = kehubianhao;
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

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

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

	public String getZhujifu() {
		return zhujifu;
	}

	public void setZhujifu(String zhujifu) {
		this.zhujifu = zhujifu;
	}

	public String getEseedId() {
		return this.eseedId;
	}

	public void setEseedId(String eseedId) {
		this.eseedId = eseedId;
	}

	public Integer getPaymentFashion() {
		return this.paymentFashion;
	}

	public void setPaymentFashion(Integer paymentFashion) {
		this.paymentFashion = paymentFashion;
	}

	public String getExport() {
		return this.export;
	}

	public void setExport(String export) {
		this.export = export;
	}

	public Integer getGoods() {
		return this.goods;
	}

	public void setGoods(Integer goods) {
		this.goods = goods;
	}

	public Double getEseedShouldWeight() {
		return this.eseedShouldWeight;
	}

	public void setEseedShouldWeight(Double eseedShouldWeight) {
		this.eseedShouldWeight = eseedShouldWeight;
	}

	public Double getEseedRealityWeight() {
		return this.eseedRealityWeight;
	}

	public void setEseedRealityWeight(Double eseedRealityWeight) {
		this.eseedRealityWeight = eseedRealityWeight;
	}

	public Double getEseedShouldNumber() {
		return this.eseedShouldNumber;
	}

	public void setEseedShouldNumber(Double eseedShouldNumber) {
		this.eseedShouldNumber = eseedShouldNumber;
	}

	public Double getEseedRealityNumber() {
		return this.eseedRealityNumber;
	}

	public void setEseedRealityNumber(Double eseedRealityNumber) {
		this.eseedRealityNumber = eseedRealityNumber;
	}

	public Double getEseedShouldCost() {
		return this.eseedShouldCost;
	}

	public void setEseedShouldCost(Double eseedShouldCost) {
		this.eseedShouldCost = eseedShouldCost;
	}

	public Double getEseedRealityCost() {
		return this.eseedRealityCost;
	}

	public void setEseedRealityCost(Double eseedRealityCost) {
		this.eseedRealityCost = eseedRealityCost;
	}

	public Double getEseedSecondWork() {
		return this.eseedSecondWork;
	}

	public void setEseedSecondWork(Double eseedSecondWork) {
		this.eseedSecondWork = eseedSecondWork;
	}

	public Double getEseedSwshouldCost() {
		return this.eseedSwshouldCost;
	}

	public void setEseedSwshouldCost(Double eseedSwshouldCost) {
		this.eseedSwshouldCost = eseedSwshouldCost;
	}

	public Double getEseedSwrealityCost() {
		return this.eseedSwrealityCost;
	}

	public void setEseedSwrealityCost(Double eseedSwrealityCost) {
		this.eseedSwrealityCost = eseedSwrealityCost;
	}

	public String getEseedClientAccounts() {
		return this.eseedClientAccounts;
	}

	public void setEseedClientAccounts(String eseedClientAccounts) {
		this.eseedClientAccounts = eseedClientAccounts;
	}

	public String getEseedOrderStatus() {
		return this.eseedOrderStatus;
	}

	public void setEseedOrderStatus(String eseedOrderStatus) {
		this.eseedOrderStatus = eseedOrderStatus;
	}

	public String getEseedDefinedOne() {
		return this.eseedDefinedOne;
	}

	public void setEseedDefinedOne(String eseedDefinedOne) {
		this.eseedDefinedOne = eseedDefinedOne;
	}

	public String getEseedDefinedTwo() {
		return this.eseedDefinedTwo;
	}

	public void setEseedDefinedTwo(String eseedDefinedTwo) {
		this.eseedDefinedTwo = eseedDefinedTwo;
	}

	public String getEseedRemark() {
		return this.eseedRemark;
	}

	public void setEseedRemark(String eseedRemark) {
		this.eseedRemark = eseedRemark;
	}

}