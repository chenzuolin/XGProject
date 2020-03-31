package com.xinggang.project.form;

import org.apache.struts.action.ActionForm;

/**
 * 盘点表单
 * 
 * @author Administrator
 * 
 */
public class ChecksForm extends ActionForm {

	private static final long serialVersionUID = -2172288739007231707L;
	private String checkId;// 盘点编号
	private Integer interiorUserByCheckHuman;// 盘点人，对应的内部人员类
	private Integer tarehouse;// 对应的库位
	private Integer interiorUserByCheckAuditing;// 审核人，对应的内部人员类
	private Integer interiorUserByCheckFounderMember;// 发起人，对应的内部人员类
	private Integer tarehouseGoods;// 对应的库存类
	private String checkTime;// 盘点日期
	private Double checkTarehouseNumber;// 库位现有件数
	private Double checkTarehouseWeight;// 库位现有重量
	private Double checkResultWeight;// 盘点结果，重量
	private Double checkResultNumber;// 盘点结果，件数
	private String checkAuditingTime;// 审核时间
	private String checkAuditingTrue;// 审核是否通过
	private String checkDefinedTwo;// 预留字段一
	private String checkDefinedOne;// 预留字段二
	private String checkRemark;// 备注

	private String begin; // 模糊查询的起始日期
	private String finish;// 模糊查询的结束日期
	private String goodsName;// 货物名称
	private String goodsSign;// 货物助记符
	private String goodsStandard;// 货物规格
	private String goodsQuality;// 货物材质
	private String goodsProperty;// 货物属性
	private String kuName;// 库位名称

	private Integer[] tarehouses;// 对应的库位，数组形式添加
	private Integer[] tarehouseGoodss;// 对应的库存，数组形式添加
	private String[] checkRemarks;// 备注，数组的形式添加
	private Double[] checkWeight;// 重量，以数组的形式添加
	private Double[] checkNumber;// 件数，以数组的形式添加

	public Double[] getCheckWeight() {
		return checkWeight;
	}

	public void setCheckWeight(Double[] checkWeight) {
		this.checkWeight = checkWeight;
	}

	public Double[] getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(Double[] checkNumber) {
		this.checkNumber = checkNumber;
	}

	public String[] getCheckRemarks() {
		return checkRemarks;
	}

	public void setCheckRemarks(String[] checkRemarks) {
		this.checkRemarks = checkRemarks;
	}

	public Integer[] getTarehouses() {
		return tarehouses;
	}

	public void setTarehouses(Integer[] tarehouses) {
		this.tarehouses = tarehouses;
	}

	public Integer[] getTarehouseGoodss() {
		return tarehouseGoodss;
	}

	public void setTarehouseGoodss(Integer[] tarehouseGoodss) {
		this.tarehouseGoodss = tarehouseGoodss;
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

	public String getGoodsSign() {
		return goodsSign;
	}

	public void setGoodsSign(String goodsSign) {
		this.goodsSign = goodsSign;
	}

	public String getGoodsStandard() {
		return goodsStandard;
	}

	public void setGoodsStandard(String goodsStandard) {
		this.goodsStandard = goodsStandard;
	}

	public String getGoodsQuality() {
		return goodsQuality;
	}

	public void setGoodsQuality(String goodsQuality) {
		this.goodsQuality = goodsQuality;
	}

	public String getGoodsProperty() {
		return goodsProperty;
	}

	public void setGoodsProperty(String goodsProperty) {
		this.goodsProperty = goodsProperty;
	}

	public String getKuName() {
		return kuName;
	}

	public void setKuName(String kuName) {
		this.kuName = kuName;
	}

	public String getCheckId() {
		return this.checkId;
	}

	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}

	public Integer getInteriorUserByCheckHuman() {
		return this.interiorUserByCheckHuman;
	}

	public void setInteriorUserByCheckHuman(Integer interiorUserByCheckHuman) {
		this.interiorUserByCheckHuman = interiorUserByCheckHuman;
	}

	public Integer getTarehouse() {
		return this.tarehouse;
	}

	public void setTarehouse(Integer tarehouse) {
		this.tarehouse = tarehouse;
	}

	public Integer getInteriorUserByCheckAuditing() {
		return this.interiorUserByCheckAuditing;
	}

	public void setInteriorUserByCheckAuditing(
			Integer interiorUserByCheckAuditing) {
		this.interiorUserByCheckAuditing = interiorUserByCheckAuditing;
	}

	public Integer getInteriorUserByCheckFounderMember() {
		return this.interiorUserByCheckFounderMember;
	}

	public void setInteriorUserByCheckFounderMember(
			Integer interiorUserByCheckFounderMember) {
		this.interiorUserByCheckFounderMember = interiorUserByCheckFounderMember;
	}

	public Integer getTarehouseGoods() {
		return this.tarehouseGoods;
	}

	public void setTarehouseGoods(Integer tarehouseGoods) {
		this.tarehouseGoods = tarehouseGoods;
	}

	public String getCheckTime() {
		return this.checkTime;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

	public Double getCheckTarehouseNumber() {
		return this.checkTarehouseNumber;
	}

	public void setCheckTarehouseNumber(Double checkTarehouseNumber) {
		this.checkTarehouseNumber = checkTarehouseNumber;
	}

	public Double getCheckTarehouseWeight() {
		return this.checkTarehouseWeight;
	}

	public void setCheckTarehouseWeight(Double checkTarehouseWeight) {
		this.checkTarehouseWeight = checkTarehouseWeight;
	}

	public Double getCheckResultWeight() {
		return this.checkResultWeight;
	}

	public void setCheckResultWeight(Double checkResultWeight) {
		this.checkResultWeight = checkResultWeight;
	}

	public Double getCheckResultNumber() {
		return this.checkResultNumber;
	}

	public void setCheckResultNumber(Double checkResultNumber) {
		this.checkResultNumber = checkResultNumber;
	}

	public String getCheckAuditingTime() {
		return this.checkAuditingTime;
	}

	public void setCheckAuditingTime(String checkAuditingTime) {
		this.checkAuditingTime = checkAuditingTime;
	}

	public String getCheckAuditingTrue() {
		return this.checkAuditingTrue;
	}

	public void setCheckAuditingTrue(String checkAuditingTrue) {
		this.checkAuditingTrue = checkAuditingTrue;
	}

	public String getCheckDefinedTwo() {
		return this.checkDefinedTwo;
	}

	public void setCheckDefinedTwo(String checkDefinedTwo) {
		this.checkDefinedTwo = checkDefinedTwo;
	}

	public String getCheckDefinedOne() {
		return this.checkDefinedOne;
	}

	public void setCheckDefinedOne(String checkDefinedOne) {
		this.checkDefinedOne = checkDefinedOne;
	}

	public String getCheckRemark() {
		return this.checkRemark;
	}

	public void setCheckRemark(String checkRemark) {
		this.checkRemark = checkRemark;
	}

}