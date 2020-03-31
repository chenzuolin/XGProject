package com.xinggang.project.entity;

/**
 * 盘点类
 * 
 * @author Administrator
 * 
 */
public class Checks implements java.io.Serializable {

	private static final long serialVersionUID = -2172288739007231707L;
	private String checkId;// 盘点编号
	private InteriorUser interiorUserByCheckHuman;// 盘点人，对应的内部人员类
	private Tarehouse tarehouse;// 对应的库位
	private InteriorUser interiorUserByCheckAuditing;// 审核人，对应的内部人员类
	private InteriorUser interiorUserByCheckFounderMember;// 发起人，对应的内部人员类
	private TarehouseGoods tarehouseGoods;// 对应的库存类
	private String checkTime;// 盘点日期
	private Double checkTarehouseNumber;// 库位现有件数
	private Double checkTarehouseWeight;// 库位现有重量
	private Double checkResultWeight;// 盘点结果，重量
	private Double checkResultNumber;// 盘点结果，件数
	private String checkAuditingTime;// 审核时间
	private String checkAuditingTrue;// 盘点状态
	private String checkDefinedTwo;// 预留字段一
	private String checkDefinedOne;// 预留字段二
	private String checkRemark;// 备注

	/**
	 * 
	 */
	public Checks() {
	}

	public Checks(InteriorUser interiorUserByCheckHuman, Tarehouse tarehouse,
			InteriorUser interiorUserByCheckAuditing,
			InteriorUser interiorUserByCheckFounderMember,
			TarehouseGoods tarehouseGoods, String checkTime,
			Double checkTarehouseNumber, Double checkTarehouseWeight,
			Double checkResultWeight, Double checkResultNumber,
			String checkAuditingTime, String checkAuditingTrue,
			String checkDefinedTwo, String checkDefinedOne, String checkRemark) {
		this.interiorUserByCheckHuman = interiorUserByCheckHuman;
		this.tarehouse = tarehouse;
		this.interiorUserByCheckAuditing = interiorUserByCheckAuditing;
		this.interiorUserByCheckFounderMember = interiorUserByCheckFounderMember;
		this.tarehouseGoods = tarehouseGoods;
		this.checkTime = checkTime;
		this.checkTarehouseNumber = checkTarehouseNumber;
		this.checkTarehouseWeight = checkTarehouseWeight;
		this.checkResultWeight = checkResultWeight;
		this.checkResultNumber = checkResultNumber;
		this.checkAuditingTime = checkAuditingTime;
		this.checkAuditingTrue = checkAuditingTrue;
		this.checkDefinedTwo = checkDefinedTwo;
		this.checkDefinedOne = checkDefinedOne;
		this.checkRemark = checkRemark;
	}

	// 编号，盘点人，对应的库位，审核人，发起人，库存，盘点日期，现有件数，现有重量，盘点结果重量，盘点结果件数，审核时间，审核是否通过，预留字段一，预留字段二，备注
	public Checks(String checkId, InteriorUser interiorUserByCheckHuman,
			Tarehouse tarehouse, InteriorUser interiorUserByCheckAuditing,
			InteriorUser interiorUserByCheckFounderMember,
			TarehouseGoods tarehouseGoods, String checkTime,
			Double checkTarehouseNumber, Double checkTarehouseWeight,
			Double checkResultWeight, Double checkResultNumber,
			String checkAuditingTime, String checkAuditingTrue,
			String checkDefinedTwo, String checkDefinedOne, String checkRemark) {
		this.checkId = checkId;
		this.interiorUserByCheckHuman = interiorUserByCheckHuman;
		this.tarehouse = tarehouse;
		this.interiorUserByCheckAuditing = interiorUserByCheckAuditing;
		this.interiorUserByCheckFounderMember = interiorUserByCheckFounderMember;
		this.tarehouseGoods = tarehouseGoods;
		this.checkTime = checkTime;
		this.checkTarehouseNumber = checkTarehouseNumber;
		this.checkTarehouseWeight = checkTarehouseWeight;
		this.checkResultWeight = checkResultWeight;
		this.checkResultNumber = checkResultNumber;
		this.checkAuditingTime = checkAuditingTime;
		this.checkAuditingTrue = checkAuditingTrue;
		this.checkDefinedTwo = checkDefinedTwo;
		this.checkDefinedOne = checkDefinedOne;
		this.checkRemark = checkRemark;
	}

	public String getCheckId() {
		return this.checkId;
	}

	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}

	public InteriorUser getInteriorUserByCheckHuman() {
		return this.interiorUserByCheckHuman;
	}

	public void setInteriorUserByCheckHuman(
			InteriorUser interiorUserByCheckHuman) {
		this.interiorUserByCheckHuman = interiorUserByCheckHuman;
	}

	public Tarehouse getTarehouse() {
		return this.tarehouse;
	}

	public void setTarehouse(Tarehouse tarehouse) {
		this.tarehouse = tarehouse;
	}

	public InteriorUser getInteriorUserByCheckAuditing() {
		return this.interiorUserByCheckAuditing;
	}

	public void setInteriorUserByCheckAuditing(
			InteriorUser interiorUserByCheckAuditing) {
		this.interiorUserByCheckAuditing = interiorUserByCheckAuditing;
	}

	public InteriorUser getInteriorUserByCheckFounderMember() {
		return this.interiorUserByCheckFounderMember;
	}

	public void setInteriorUserByCheckFounderMember(
			InteriorUser interiorUserByCheckFounderMember) {
		this.interiorUserByCheckFounderMember = interiorUserByCheckFounderMember;
	}

	public TarehouseGoods getTarehouseGoods() {
		return this.tarehouseGoods;
	}

	public void setTarehouseGoods(TarehouseGoods tarehouseGoods) {
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