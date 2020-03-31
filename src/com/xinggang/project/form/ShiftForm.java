package com.xinggang.project.form;

import org.apache.struts.action.ActionForm;

/**
 * 挪库表单
 * 
 * @author Administrator
 * 
 */
public class ShiftForm extends ActionForm {

	private static final long serialVersionUID = 9138255037727480266L;
	private String shiftId;// 挪库编号
	private Integer tarehouseByShiftPast;// 原库位，调用库位类
	private Integer interiorUserByShiftExecutor;// 挪库执行人
	private Integer tarehouseByShiftNew;// 新库位，调用库位类
	private Integer interiorUserByShiftFounderMember;// 挪库发起人，调用内部人员类
	private Integer goods;// 对应的货物类
	private String shiftTime;// 挪库发起日期
	private String shiftFinishTime;// 挪库完成时间
	private Double shiftNumber;// 挪库件数
	private Double shiftWeight;// 挪库重量
	private String shiftCraneman;// 天车工
	private String shiftStevedore;// 装卸工
	private String shiftDefinedOne;// 预留字段一
	private String shiftDefinedTwo;// 预留字段二
	private String shiftRemark;// 备注
	private String begin;// 挪库中选择的起始日期
	private String finish;// 挪库中选择的结束日期
	private String goodsName;// 货物的名称
	private String goodsSign;// 货物的助记符
	private String goodsStandard;// 货物的规格
	private String goodsQuality;// 货物的材质
	private String goodsProperty;// 货物的属性
	private Integer[] goodsId;// 当发起挪库的时候的货物的id定义为数组
	private String[] remark;// 发起挪库时的备注数组
	private Double[] shiftNumbers;// 挪库时发起的件数，以数组 的形式上传
	private Double[] shiftWeights;// 挪库时发起的重量，以数组的形式上传

	private String shenhe;// 审核人员
	private String shoufei;// 收费人员
	private String diaodu;// 调度人员
	private String sibang;// 司磅人员
	private String[] pici;// 当挪库的时候选择的批次，多选的方式

	private String kuName;// 当模糊查询库位的时候调用

	public String getKuName() {
		return kuName;
	}

	public void setKuName(String kuName) {
		this.kuName = kuName;
	}

	public Double[] getShiftNumbers() {
		return shiftNumbers;
	}

	public void setShiftNumbers(Double[] shiftNumbers) {
		this.shiftNumbers = shiftNumbers;
	}

	public Double[] getShiftWeights() {
		return shiftWeights;
	}

	public void setShiftWeights(Double[] shiftWeights) {
		this.shiftWeights = shiftWeights;
	}

	public String[] getRemark() {
		return remark;
	}

	public void setRemark(String[] remark) {
		this.remark = remark;
	}

	public Integer[] getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer[] goodsId) {
		this.goodsId = goodsId;
	}

	public String getShenhe() {
		return shenhe;
	}

	public void setShenhe(String shenhe) {
		this.shenhe = shenhe;
	}

	public String getShoufei() {
		return shoufei;
	}

	public void setShoufei(String shoufei) {
		this.shoufei = shoufei;
	}

	public String getDiaodu() {
		return diaodu;
	}

	public void setDiaodu(String diaodu) {
		this.diaodu = diaodu;
	}

	public String getSibang() {
		return sibang;
	}

	public void setSibang(String sibang) {
		this.sibang = sibang;
	}

	public String[] getPici() {
		return pici;
	}

	public void setPici(String[] pici) {
		this.pici = pici;
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

	public String getShiftId() {
		return this.shiftId;
	}

	public void setShiftId(String shiftId) {
		this.shiftId = shiftId;
	}

	public Integer getTarehouseByShiftPast() {
		return this.tarehouseByShiftPast;
	}

	public void setTarehouseByShiftPast(Integer tarehouseByShiftPast) {
		this.tarehouseByShiftPast = tarehouseByShiftPast;
	}

	public Integer getInteriorUserByShiftExecutor() {
		return this.interiorUserByShiftExecutor;
	}

	public void setInteriorUserByShiftExecutor(
			Integer interiorUserByShiftExecutor) {
		this.interiorUserByShiftExecutor = interiorUserByShiftExecutor;
	}

	public Integer getTarehouseByShiftNew() {
		return this.tarehouseByShiftNew;
	}

	public void setTarehouseByShiftNew(Integer tarehouseByShiftNew) {
		this.tarehouseByShiftNew = tarehouseByShiftNew;
	}

	public Integer getInteriorUserByShiftFounderMember() {
		return this.interiorUserByShiftFounderMember;
	}

	public void setInteriorUserByShiftFounderMember(
			Integer interiorUserByShiftFounderMember) {
		this.interiorUserByShiftFounderMember = interiorUserByShiftFounderMember;
	}

	public Integer getGoods() {
		return this.goods;
	}

	public void setGoods(Integer goods) {
		this.goods = goods;
	}

	public String getShiftTime() {
		return this.shiftTime;
	}

	public void setShiftTime(String shiftTime) {
		this.shiftTime = shiftTime;
	}

	public String getShiftFinishTime() {
		return this.shiftFinishTime;
	}

	public void setShiftFinishTime(String shiftFinishTime) {
		this.shiftFinishTime = shiftFinishTime;
	}

	public Double getShiftNumber() {
		return this.shiftNumber;
	}

	public void setShiftNumber(Double shiftNumber) {
		this.shiftNumber = shiftNumber;
	}

	public Double getShiftWeight() {
		return this.shiftWeight;
	}

	public void setShiftWeight(Double shiftWeight) {
		this.shiftWeight = shiftWeight;
	}

	public String getShiftCraneman() {
		return this.shiftCraneman;
	}

	public void setShiftCraneman(String shiftCraneman) {
		this.shiftCraneman = shiftCraneman;
	}

	public String getShiftStevedore() {
		return this.shiftStevedore;
	}

	public void setShiftStevedore(String shiftStevedore) {
		this.shiftStevedore = shiftStevedore;
	}

	public String getShiftDefinedOne() {
		return this.shiftDefinedOne;
	}

	public void setShiftDefinedOne(String shiftDefinedOne) {
		this.shiftDefinedOne = shiftDefinedOne;
	}

	public String getShiftDefinedTwo() {
		return this.shiftDefinedTwo;
	}

	public void setShiftDefinedTwo(String shiftDefinedTwo) {
		this.shiftDefinedTwo = shiftDefinedTwo;
	}

	public String getShiftRemark() {
		return this.shiftRemark;
	}

	public void setShiftRemark(String shiftRemark) {
		this.shiftRemark = shiftRemark;
	}

}