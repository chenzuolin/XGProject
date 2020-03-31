package com.xinggang.project.entity;

/**
 * DuanDao entity. @author MyEclipse Persistence Tools 对应的短倒实体类
 */

public class DuanDao implements java.io.Serializable {

	private static final long serialVersionUID = -1704475323989611998L;
	private String shiftId; // 对应的短倒中的编号
	private Tarehouse tarehouseByShiftPast;// 短倒中的原库位
	private InteriorUser interiorUserByShiftExecutor;// 短倒中的执行人
	private Tarehouse tarehouseByShiftNew;// 短倒中的新库位
	private InteriorUser interiorUserByShiftFounderMember;// 对应发起人
	private Goods goods;// 对应的货物类
	private String shiftTime;// 对应的短倒的发起日期
	private String shiftFinishTime;// 对应短倒完成时间
	private Double numbers;// 对应的短倒之前原库位的现有件数
	private Double weights;// 对应的短倒之前的原库位的现有重量
	private Double shiftNumber;// 对应的额短倒的件数
	private Double shiftWeight;// 对应的短倒的重量
	private String shiftCraneman;// 天车工
	private String shiftStevedore;// 装卸工
	private String driverName;// 短倒的司机姓名
	private String shiftDefinedOne;// 短倒的状态
	private String shiftDefinedTwo;// 预留字段二
	private String shiftRemark;// 备注

	public DuanDao() {
	}

	public DuanDao(String driverName) {
		this.driverName = driverName;
	}

	public DuanDao(Tarehouse tarehouseByShiftPast,
			InteriorUser interiorUserByShiftExecutor,
			Tarehouse tarehouseByShiftNew,
			InteriorUser interiorUserByShiftFounderMember, Goods goods,
			String shiftTime, String shiftFinishTime, Double numbers,
			Double weights, Double shiftNumber, Double shiftWeight,
			String shiftCraneman, String shiftStevedore, String driverName,
			String shiftDefinedOne, String shiftDefinedTwo, String shiftRemark) {
		this.tarehouseByShiftPast = tarehouseByShiftPast;
		this.interiorUserByShiftExecutor = interiorUserByShiftExecutor;
		this.tarehouseByShiftNew = tarehouseByShiftNew;
		this.interiorUserByShiftFounderMember = interiorUserByShiftFounderMember;
		this.goods = goods;
		this.shiftTime = shiftTime;
		this.shiftFinishTime = shiftFinishTime;
		this.numbers = numbers;
		this.weights = weights;
		this.shiftNumber = shiftNumber;
		this.shiftWeight = shiftWeight;
		this.shiftCraneman = shiftCraneman;
		this.shiftStevedore = shiftStevedore;
		this.driverName = driverName;
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

	public Double getNumbers() {
		return this.numbers;
	}

	public void setNumbers(Double numbers) {
		this.numbers = numbers;
	}

	public Double getWeights() {
		return this.weights;
	}

	public void setWeights(Double weights) {
		this.weights = weights;
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

	public String getDriverName() {
		return this.driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
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