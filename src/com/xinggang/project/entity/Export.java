package com.xinggang.project.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 出库总订单类
 * 
 * @author Administrator
 * 
 */
public class Export implements java.io.Serializable {

	private static final long serialVersionUID = 2803626509009886905L;
	private String exportId;// 出库总订单编号
	private Client client;// 对应的客户，客户类
	private String exportClientNumber;// 客户订单号，由客户填入
	private String exportReateTime;// 订单生成日期
	private String exportCarryType;// 运输方式（火车/汽车）
	private String exportWagonNumber;// 车牌号
	private String exportDriverName;// 司机姓名
	private String exportDriverTel;// 司机电话
	private String exportOrderStatus;// 订单状态
	private String exportDefinedOne;// 是否接受超发
	private String exportDefinedTwo;// 有效期
	private String exportRemark;// 备注
	private String exportFaQiRen;//出库发起人
	private Set<Object> exportSeeds = new HashSet<Object>(0);// 对应的出库子订单类

	public Export() {
	}

	public Export(Client client, String exportClientNumber,
			String exportReateTime, String exportCarryType,
			String exportWagonNumber, String exportDriverName,
			String exportDriverTel, String exportOrderStatus,
			String exportDefinedOne, String exportDefinedTwo,
			String exportRemark, Set<Object> exportSeeds) {
		this.client = client;
		this.exportClientNumber = exportClientNumber;
		this.exportReateTime = exportReateTime;
		this.exportCarryType = exportCarryType;
		this.exportWagonNumber = exportWagonNumber;
		this.exportDriverName = exportDriverName;
		this.exportDriverTel = exportDriverTel;
		this.exportOrderStatus = exportOrderStatus;
		this.exportDefinedOne = exportDefinedOne;
		this.exportDefinedTwo = exportDefinedTwo;
		this.exportRemark = exportRemark;
		this.exportSeeds = exportSeeds;
	}

	// Property accessors

	public String getExportId() {
		return this.exportId;
	}


	public String getExportFaQiRen() {
		return exportFaQiRen;
	}

	public void setExportFaQiRen(String exportFaQiRen) {
		this.exportFaQiRen = exportFaQiRen;
	}

	public void setExportId(String exportId) {
		this.exportId = exportId;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getExportClientNumber() {
		return this.exportClientNumber;
	}

	public void setExportClientNumber(String exportClientNumber) {
		this.exportClientNumber = exportClientNumber;
	}

	public String getExportReateTime() {
		return this.exportReateTime;
	}

	public void setExportReateTime(String exportReateTime) {
		this.exportReateTime = exportReateTime;
	}

	public String getExportCarryType() {
		return this.exportCarryType;
	}

	public void setExportCarryType(String exportCarryType) {
		this.exportCarryType = exportCarryType;
	}

	public String getExportWagonNumber() {
		return this.exportWagonNumber;
	}

	public void setExportWagonNumber(String exportWagonNumber) {
		this.exportWagonNumber = exportWagonNumber;
	}

	public String getExportDriverName() {
		return this.exportDriverName;
	}

	public void setExportDriverName(String exportDriverName) {
		this.exportDriverName = exportDriverName;
	}

	public String getExportDriverTel() {
		return this.exportDriverTel;
	}

	public void setExportDriverTel(String exportDriverTel) {
		this.exportDriverTel = exportDriverTel;
	}

	public String getExportOrderStatus() {
		return this.exportOrderStatus;
	}

	public void setExportOrderStatus(String exportOrderStatus) {
		this.exportOrderStatus = exportOrderStatus;
	}

	public String getExportDefinedOne() {
		return this.exportDefinedOne;
	}

	public void setExportDefinedOne(String exportDefinedOne) {
		this.exportDefinedOne = exportDefinedOne;
	}

	public String getExportDefinedTwo() {
		return this.exportDefinedTwo;
	}

	public void setExportDefinedTwo(String exportDefinedTwo) {
		this.exportDefinedTwo = exportDefinedTwo;
	}

	public String getExportRemark() {
		return this.exportRemark;
	}

	public void setExportRemark(String exportRemark) {
		this.exportRemark = exportRemark;
	}

	public Set<Object> getExportSeeds() {
		return this.exportSeeds;
	}

	public void setExportSeeds(Set<Object> exportSeeds) {
		this.exportSeeds = exportSeeds;
	}

}