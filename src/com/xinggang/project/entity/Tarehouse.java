package com.xinggang.project.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 库位类
 */
public class Tarehouse implements java.io.Serializable {

	private static final long serialVersionUID = -4808830432996378728L;
	private Integer tarehouseId;// 库位编号
	private Bursary bursary;// 对应的库房类
	private String tarehouseName;// 库位名称
	private Double tarehouseMaxWeight;// 库位最大重量
	private Double tarehouseMaxNumber;// 库位最大件数
	private String tarehouseDefinedOne;// 预留字段一
	private String tarehouseDefinedTwo;// 预留字段二
	private String tarehouseRemark;// 备注
	private Set<Object> shiftsForShiftPast = new HashSet<Object>(0);// 对应的挪库类，旧库位
	private Set<Object> inputOperates = new HashSet<Object>(0);// 对应的入库操作类
	private Set<Object> exportOperates = new HashSet<Object>(0);// 对应的出库操作类
	private Set<Object> checkses = new HashSet<Object>(0);// 对应的盘点类
	private Set<Object> tarehouseGoodses = new HashSet<Object>(0);// 对应的货物库存类
	private Set<Object> shiftsForShiftNew = new HashSet<Object>(0);// 对应的挪库类，新库位
	private Set<Object> tdetailTarehouse = new HashSet<Object>();// 对应的批次类

	public Tarehouse() {
	}

	/** full constructor */
	public Tarehouse(Bursary bursary, String tarehouseName,
			Double tarehouseMaxWeight, Double tarehouseMaxNumber,
			String tarehouseDefinedOne, String tarehouseDefinedTwo,
			String tarehouseRemark, Set<Object> shiftsForShiftPast,
			Set<Object> inputOperates, Set<Object> exportOperates,
			Set<Object> checkses, Set<Object> tarehouseGoodses,
			Set<Object> shiftsForShiftNew) {
		this.bursary = bursary;
		this.tarehouseName = tarehouseName;
		this.tarehouseMaxWeight = tarehouseMaxWeight;
		this.tarehouseMaxNumber = tarehouseMaxNumber;
		this.tarehouseDefinedOne = tarehouseDefinedOne;
		this.tarehouseDefinedTwo = tarehouseDefinedTwo;
		this.tarehouseRemark = tarehouseRemark;
		this.shiftsForShiftPast = shiftsForShiftPast;
		this.inputOperates = inputOperates;
		this.exportOperates = exportOperates;
		this.checkses = checkses;
		this.tarehouseGoodses = tarehouseGoodses;
		this.shiftsForShiftNew = shiftsForShiftNew;
	}

	public Set<Object> getTdetailTarehouse() {
		return tdetailTarehouse;
	}

	public void setTdetailTarehouse(Set<Object> tdetailTarehouse) {
		this.tdetailTarehouse = tdetailTarehouse;
	}

	public Integer getTarehouseId() {
		return this.tarehouseId;
	}

	public void setTarehouseId(Integer tarehouseId) {
		this.tarehouseId = tarehouseId;
	}

	public Bursary getBursary() {
		return this.bursary;
	}

	public void setBursary(Bursary bursary) {
		this.bursary = bursary;
	}

	public String getTarehouseName() {
		return this.tarehouseName;
	}

	public void setTarehouseName(String tarehouseName) {
		this.tarehouseName = tarehouseName;
	}

	public Double getTarehouseMaxWeight() {
		return this.tarehouseMaxWeight;
	}

	public void setTarehouseMaxWeight(Double tarehouseMaxWeight) {
		this.tarehouseMaxWeight = tarehouseMaxWeight;
	}

	public Double getTarehouseMaxNumber() {
		return this.tarehouseMaxNumber;
	}

	public void setTarehouseMaxNumber(Double tarehouseMaxNumber) {
		this.tarehouseMaxNumber = tarehouseMaxNumber;
	}

	public String getTarehouseDefinedOne() {
		return this.tarehouseDefinedOne;
	}

	public void setTarehouseDefinedOne(String tarehouseDefinedOne) {
		this.tarehouseDefinedOne = tarehouseDefinedOne;
	}

	public String getTarehouseDefinedTwo() {
		return this.tarehouseDefinedTwo;
	}

	public void setTarehouseDefinedTwo(String tarehouseDefinedTwo) {
		this.tarehouseDefinedTwo = tarehouseDefinedTwo;
	}

	public String getTarehouseRemark() {
		return this.tarehouseRemark;
	}

	public void setTarehouseRemark(String tarehouseRemark) {
		this.tarehouseRemark = tarehouseRemark;
	}

	public Set<Object> getShiftsForShiftPast() {
		return this.shiftsForShiftPast;
	}

	public void setShiftsForShiftPast(Set<Object> shiftsForShiftPast) {
		this.shiftsForShiftPast = shiftsForShiftPast;
	}

	public Set<Object> getInputOperates() {
		return this.inputOperates;
	}

	public void setInputOperates(Set<Object> inputOperates) {
		this.inputOperates = inputOperates;
	}

	public Set<Object> getExportOperates() {
		return this.exportOperates;
	}

	public void setExportOperates(Set<Object> exportOperates) {
		this.exportOperates = exportOperates;
	}

	public Set<Object> getCheckses() {
		return this.checkses;
	}

	public void setCheckses(Set<Object> checkses) {
		this.checkses = checkses;
	}

	public Set<Object> getTarehouseGoodses() {
		return this.tarehouseGoodses;
	}

	public void setTarehouseGoodses(Set<Object> tarehouseGoodses) {
		this.tarehouseGoodses = tarehouseGoodses;
	}

	public Set<Object> getShiftsForShiftNew() {
		return this.shiftsForShiftNew;
	}

	public void setShiftsForShiftNew(Set<Object> shiftsForShiftNew) {
		this.shiftsForShiftNew = shiftsForShiftNew;
	}

}