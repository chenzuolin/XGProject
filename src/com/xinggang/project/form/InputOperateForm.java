package com.xinggang.project.form;

import org.apache.struts.action.ActionForm;

/**
 * 入库订单操作表单
 * 
 * @author Administrator
 * 
 */
public class InputOperateForm extends ActionForm {

	private static final long serialVersionUID = 7931874897191868103L;
	private String ioperateId;// 操作编号
	private Integer interiorUserByIoperateDispatcherId;// 调度员,对应的内部人员类
	private Integer tarehouse;// 对应的库位
	private String inputSeed;// 对应的入库子订单编号，入库子订单类
	private Integer interiorUserByIoperateStoremanId;// 现场保管员，对应的内部人员类
	private Integer interiorUserByIoperatePonderationManId;// 司磅员,对应的内部人员类
	private Integer interiorUserByIoperateAuditingId;// 审核人员,对应的内部人员类
	private Integer interiorUserByIoperateCollectManId;// 收费人员，对应的内部人员类
	private String ioperateDispatcherTime;// 调度员操作时间
	private String ioperatePonderationTime;// 过磅时间
	private String ioperatePonderationTrue;// 过磅还是理算货物(过磅/理算)
	private String ioperateScreateTime;// 现场保管员开始操作时间
	private String ioperateSfinishTime;// 现场保管员结束操作时间
	private String ioperateCraneman;// 天车工
	private String ioperateStevedore;// 装卸工
	private String ioperateAuditingTrue;// 审核是否通过（通过/未通过）
	private String ioperateAuditingTime;// 审核时间
	private String ioperateCollectTime;// 收费时间
	private Double ioperateRealityCost;// 实收费用
	private Double ioperateRealityWeight;// 对应人员的入库重量
	private Double ioperateRealityNumber;// 对应人员的入库件数
	private String ioperateDefinedOne;// 预留字段一
	private String ioperateDefinedTwo;// 入库的操作状态
	private String ioperateRemark;// 备注
	
	private String ioperatepici;//入库批次
	
	private String[] zhuangxieGong;
		
	private String ioperateClientAccounts;//结算方式，用于获取前端的值，判断是否需要改变订单状态	
	private String ioperateTruckNum;// 保管填入的车号
	private Double ioperateShouldCost;// 应收费用
	private int ioperatePaymentFashion;// 支付方式，调用支付方式类
	
	
	private Integer[] kuwei;	
	//加入子订单中需要的字段
	private Double iSeedRealityWeight;//实收重量
	private Double iSeedRealityNumber;//实收件数	
		
	public String getIoperatepici() {
		return ioperatepici;
	}

	public void setIoperatepici(String ioperatepici) {
		this.ioperatepici = ioperatepici;
	}

	public String[] getZhuangxieGong() {
		return zhuangxieGong;
	}

	public void setZhuangxieGong(String[] zhuangxieGong) {
		this.zhuangxieGong = zhuangxieGong;
	}

	public String getIoperateClientAccounts() {
		return ioperateClientAccounts;
	}

	public void setIoperateClientAccounts(String ioperateClientAccounts) {
		this.ioperateClientAccounts = ioperateClientAccounts;
	}
	
	

	public String getIoperateTruckNum() {
		return ioperateTruckNum;
	}

	public void setIoperateTruckNum(String ioperateTruckNum) {
		this.ioperateTruckNum = ioperateTruckNum;
	}

	public Double getIoperateShouldCost() {
		return ioperateShouldCost;
	}

	public void setIoperateShouldCost(Double ioperateShouldCost) {
		this.ioperateShouldCost = ioperateShouldCost;
	}

	public int getIoperatePaymentFashion() {
		return ioperatePaymentFashion;
	}

	public void setIoperatePaymentFashion(int ioperatePaymentFashion) {
		this.ioperatePaymentFashion = ioperatePaymentFashion;
	}

	public Integer[] getKuwei() {
		return kuwei;
	}

	public void setKuwei(Integer[] kuwei) {
		this.kuwei = kuwei;
	}

	public Double getiSeedRealityWeight() {
		return iSeedRealityWeight;
	}

	public void setiSeedRealityWeight(Double iSeedRealityWeight) {
		this.iSeedRealityWeight = iSeedRealityWeight;
	}

	public Double getiSeedRealityNumber() {
		return iSeedRealityNumber;
	}

	public void setiSeedRealityNumber(Double iSeedRealityNumber) {
		this.iSeedRealityNumber = iSeedRealityNumber;
	}

	public String getIoperateId() {
		return this.ioperateId;
	}

	public void setIoperateId(String ioperateId) {
		this.ioperateId = ioperateId;
	}

	public Integer getInteriorUserByIoperateDispatcherId() {
		return this.interiorUserByIoperateDispatcherId;
	}

	public void setInteriorUserByIoperateDispatcherId(
			Integer interiorUserByIoperateDispatcherId) {
		this.interiorUserByIoperateDispatcherId = interiorUserByIoperateDispatcherId;
	}

	public Integer getTarehouse() {
		return this.tarehouse;
	}

	public void setTarehouse(Integer tarehouse) {
		this.tarehouse = tarehouse;
	}

	public String getInputSeed() {
		return this.inputSeed;
	}

	public void setInputSeed(String inputSeed) {
		this.inputSeed = inputSeed;
	}

	public Integer getInteriorUserByIoperateStoremanId() {
		return this.interiorUserByIoperateStoremanId;
	}

	public void setInteriorUserByIoperateStoremanId(
			Integer interiorUserByIoperateStoremanId) {
		this.interiorUserByIoperateStoremanId = interiorUserByIoperateStoremanId;
	}

	public Integer getInteriorUserByIoperatePonderationManId() {
		return this.interiorUserByIoperatePonderationManId;
	}

	public void setInteriorUserByIoperatePonderationManId(
			Integer interiorUserByIoperatePonderationManId) {
		this.interiorUserByIoperatePonderationManId = interiorUserByIoperatePonderationManId;
	}

	public Integer getInteriorUserByIoperateAuditingId() {
		return this.interiorUserByIoperateAuditingId;
	}

	public void setInteriorUserByIoperateAuditingId(
			Integer interiorUserByIoperateAuditingId) {
		this.interiorUserByIoperateAuditingId = interiorUserByIoperateAuditingId;
	}

	public Integer getInteriorUserByIoperateCollectManId() {
		return this.interiorUserByIoperateCollectManId;
	}

	public void setInteriorUserByIoperateCollectManId(
			Integer interiorUserByIoperateCollectManId) {
		this.interiorUserByIoperateCollectManId = interiorUserByIoperateCollectManId;
	}

	public String getIoperateDispatcherTime() {
		return this.ioperateDispatcherTime;
	}

	public void setIoperateDispatcherTime(String ioperateDispatcherTime) {
		this.ioperateDispatcherTime = ioperateDispatcherTime;
	}

	public String getIoperatePonderationTime() {
		return this.ioperatePonderationTime;
	}

	public void setIoperatePonderationTime(String ioperatePonderationTime) {
		this.ioperatePonderationTime = ioperatePonderationTime;
	}

	public String getIoperatePonderationTrue() {
		return this.ioperatePonderationTrue;
	}

	public void setIoperatePonderationTrue(String ioperatePonderationTrue) {
		this.ioperatePonderationTrue = ioperatePonderationTrue;
	}

	public String getIoperateScreateTime() {
		return this.ioperateScreateTime;
	}

	public void setIoperateScreateTime(String ioperateScreateTime) {
		this.ioperateScreateTime = ioperateScreateTime;
	}

	public String getIoperateSfinishTime() {
		return this.ioperateSfinishTime;
	}

	public void setIoperateSfinishTime(String ioperateSfinishTime) {
		this.ioperateSfinishTime = ioperateSfinishTime;
	}

	public String getIoperateCraneman() {
		return this.ioperateCraneman;
	}

	public void setIoperateCraneman(String ioperateCraneman) {
		this.ioperateCraneman = ioperateCraneman;
	}

	public String getIoperateStevedore() {
		return this.ioperateStevedore;
	}

	public void setIoperateStevedore(String ioperateStevedore) {
		this.ioperateStevedore = ioperateStevedore;
	}

	public String getIoperateAuditingTrue() {
		return this.ioperateAuditingTrue;
	}

	public void setIoperateAuditingTrue(String ioperateAuditingTrue) {
		this.ioperateAuditingTrue = ioperateAuditingTrue;
	}

	public String getIoperateAuditingTime() {
		return this.ioperateAuditingTime;
	}

	public void setIoperateAuditingTime(String ioperateAuditingTime) {
		this.ioperateAuditingTime = ioperateAuditingTime;
	}

	public String getIoperateCollectTime() {
		return this.ioperateCollectTime;
	}

	public void setIoperateCollectTime(String ioperateCollectTime) {
		this.ioperateCollectTime = ioperateCollectTime;
	}

	public Double getIoperateRealityCost() {
		return this.ioperateRealityCost;
	}

	public void setIoperateRealityCost(Double ioperateRealityCost) {
		this.ioperateRealityCost = ioperateRealityCost;
	}

	public Double getIoperateRealityWeight() {
		return this.ioperateRealityWeight;
	}

	public void setIoperateRealityWeight(Double ioperateRealityWeight) {
		this.ioperateRealityWeight = ioperateRealityWeight;
	}

	public Double getIoperateRealityNumber() {
		return this.ioperateRealityNumber;
	}

	public void setIoperateRealityNumber(Double ioperateRealityNumber) {
		this.ioperateRealityNumber = ioperateRealityNumber;
	}

	public String getIoperateDefinedOne() {
		return this.ioperateDefinedOne;
	}

	public void setIoperateDefinedOne(String ioperateDefinedOne) {
		this.ioperateDefinedOne = ioperateDefinedOne;
	}

	public String getIoperateDefinedTwo() {
		return this.ioperateDefinedTwo;
	}

	public void setIoperateDefinedTwo(String ioperateDefinedTwo) {
		this.ioperateDefinedTwo = ioperateDefinedTwo;
	}

	public String getIoperateRemark() {
		return this.ioperateRemark;
	}

	public void setIoperateRemark(String ioperateRemark) {
		this.ioperateRemark = ioperateRemark;
	}

}