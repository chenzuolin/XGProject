package com.xinggang.project.entity;

/**
 * 挪库类
 * 
 * @author Administrator
 * 
 */
public class Shift implements java.io.Serializable {

	private static final long serialVersionUID = 9138255037727480266L;
	private String shiftId;// 挪库编号
	private Tarehouse tarehouseByShiftPast;// 原库位，调用库位类
	private InteriorUser interiorUserByShiftExecutor;// 挪库执行人
	private Tarehouse tarehouseByShiftNew;// 新库位，调用库位类
	private InteriorUser interiorUserByShiftFounderMember;// 挪库发起人，调用内部人员类
	private Goods goods;// 对应的货物类
	private String shiftTime;// 挪库发起日期
	private String shiftFinishTime;// 挪库完成时间
	private Double shiftNumber;// 挪库件数
	private Double shiftWeight;// 挪库重量
	private String shiftCraneman;// 天车工
	private String shiftStevedore;// 装卸工
	private String shiftDefinedOne;// 挪库状态（计划挪库，正在挪库，挪库完成）
	private String shiftDefinedTwo;// 预留字段二
	private String shiftRemark;// 备注

	public Shift() {
		
	}

	public Shift(Tarehouse tarehouseByShiftPast,
			InteriorUser interiorUserByShiftExecutor,
			Tarehouse tarehouseByShiftNew,
			InteriorUser interiorUserByShiftFounderMember, Goods goods,
			String shiftTime, String shiftFinishTime, Double shiftNumber,
			Double shiftWeight, String shiftCraneman, String shiftStevedore,
			String shiftDefinedOne, String shiftDefinedTwo, String shiftRemark) {
		this.tarehouseByShiftPast = tarehouseByShiftPast;
		this.interiorUserByShiftExecutor = interiorUserByShiftExecutor;
		this.tarehouseByShiftNew = tarehouseByShiftNew;
		this.interiorUserByShiftFounderMember = interiorUserByShiftFounderMember;
		this.goods = goods;
		this.shiftTime = shiftTime;
		this.shiftFinishTime = shiftFinishTime;
		this.shiftNumber = shiftNumber;
		this.shiftWeight = shiftWeight;
		this.shiftCraneman = shiftCraneman;
		this.shiftStevedore = shiftStevedore;
		this.shiftDefinedOne = shiftDefinedOne;
		this.shiftDefinedTwo = shiftDefinedTwo;
		this.shiftRemark = shiftRemark;
	}

	// 编号，原库位，挪库执行人，新库位，发起人,货物，发起日期，完成日期，件数,重量，天车工，装卸工，挪库状态，预留字段二，备注
	public Shift(String shiftId, Tarehouse tarehouseByShiftPast,
			InteriorUser interiorUserByShiftExecutor,
			Tarehouse tarehouseByShiftNew,
			InteriorUser interiorUserByShiftFounderMember, Goods goods,
			String shiftTime, String shiftFinishTime, Double shiftNumber,
			Double shiftWeight, String shiftCraneman, String shiftStevedore,
			String shiftDefinedOne, String shiftDefinedTwo, String shiftRemark) {
		this.shiftId = shiftId;
		this.tarehouseByShiftPast = tarehouseByShiftPast;
		this.interiorUserByShiftExecutor = interiorUserByShiftExecutor;
		this.tarehouseByShiftNew = tarehouseByShiftNew;
		this.interiorUserByShiftFounderMember = interiorUserByShiftFounderMember;
		this.goods = goods;
		this.shiftTime = shiftTime;
		this.shiftFinishTime = shiftFinishTime;
		this.shiftNumber = shiftNumber;
		this.shiftWeight = shiftWeight;
		this.shiftCraneman = shiftCraneman;
		this.shiftStevedore = shiftStevedore;
		this.shiftDefinedOne = shiftDefinedOne;
		this.shiftDefinedTwo = shiftDefinedTwo;
		this.shiftRemark = shiftRemark;
	}

	public String getShiftId() {
		return this.shiftId;
	}

	public void setShiftId(String shiftId) {
		this.shiftId = shiftId;
	}

	public Tarehouse getTarehouseByShiftPast() {
		return this.tarehouseByShiftPast;
	}

	public void setTarehouseByShiftPast(Tarehouse tarehouseByShiftPast) {
		this.tarehouseByShiftPast = tarehouseByShiftPast;
	}

	public InteriorUser getInteriorUserByShiftExecutor() {
		return this.interiorUserByShiftExecutor;
	}

	public void setInteriorUserByShiftExecutor(
			InteriorUser interiorUserByShiftExecutor) {
		this.interiorUserByShiftExecutor = interiorUserByShiftExecutor;
	}

	public Tarehouse getTarehouseByShiftNew() {
		return this.tarehouseByShiftNew;
	}

	public void setTarehouseByShiftNew(Tarehouse tarehouseByShiftNew) {
		this.tarehouseByShiftNew = tarehouseByShiftNew;
	}

	public InteriorUser getInteriorUserByShiftFounderMember() {
		return this.interiorUserByShiftFounderMember;
	}

	public void setInteriorUserByShiftFounderMember(
			InteriorUser interiorUserByShiftFounderMember) {
		this.interiorUserByShiftFounderMember = interiorUserByShiftFounderMember;
	}

	public Goods getGoods() {
		return this.goods;
	}

	public void setGoods(Goods goods) {
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