package com.xinggang.project.entity;

/**
 * 出库子订单操作类
 * 
 * @author Administrator
 * 
 */
public class ExportOperate implements java.io.Serializable {

	private static final long serialVersionUID = -7464426285140432961L;
	private String eoperateId;// 出库订单操作编号
	private InteriorUser interiorUserByEoperateCollectMan; // 对应的收费员
	private Tarehouse tarehouse;// 对应的库位
	private InteriorUser interiorUserByEoperatePonderationMan;// 对应的司磅员
	private InteriorUser interiorUserByEoperateStoreman;// 对应的现场保管员
	private ExportSeed exportSeed;// 对应的子订单类
	private InteriorUser interiorUserByEoperateAuditing;// 对应的审核人员
	private InteriorUser interiorUserByEoperateDispatcher;// 对应的调度员
	private Double eoperateRealityWeight;// 对应的操作重量
	private Double eoperateRealityNumber;// 对应的操作件数
	private String eoperateDispatcherTime;// 调度员操作时间
	private String eoperatePonderationTime;// 过磅时间
	private String eoperatePonderationTrue;// 过磅还是理算货物(过磅/理算)
	private String eoperateScreateTime;// 现场保管员开始操作时间
	private String eoperateSfinishTime;// 现场保管员结束操作时间
	private String eoperateCraneman;// 天车工
	private String eoperateStevedore;// 装卸工
	private String eoperateAuditingTrue;// 审核是否通过（通过/未通过）
	private String eoperateAuditingTime;// 审核时间
	private String eoperateCollectTime;// 收费时间
	private Double eoperateRealityCost;// 实收费用
	private String eoperateDefinedOne;// 操作订单状态
	private String eoperateDefinedTwo;// 调度向保管员分配的重量
	private String eoperateRemark;// 备注

	private String eoperateTruckNum;// 对应的车号，由保管员进行填入
	private String eoperateDefinedThree;// 二次作业重量
	private String eoperateDefinedFour;// 二次作业应收费用

	private String eoperateClientAccounts;// 结算方式
	private Double eoperateShouldCost;// 应收费用
	private PaymentFashion eoperatePaymentFashion;// 支付方式，调用支付方式类

	// 增加批次记录
	private String EOperatepici;// 批次记录

	public ExportOperate() {
	}

	// 编号，收费员，库位，司磅,保管，子订单，，审核，调度，操作重量，操作件数，调度操作时间，过磅时间，是否理算，保管开始操作时间，保管结束操作时间，天车工，装卸工，
	// 审核次数，审核时间,收费时间，实收费用，订单状态，分配重量,备注，对应车号，二次作业重量，二次作业应收费用，结算方式，应收费用，支付方式
	public ExportOperate(String eoperateId,
			InteriorUser interiorUserByEoperateCollectMan, Tarehouse tarehouse,
			InteriorUser interiorUserByEoperatePonderationMan,
			InteriorUser interiorUserByEoperateStoreman, ExportSeed exportSeed,
			InteriorUser interiorUserByEoperateAuditing,
			InteriorUser interiorUserByEoperateDispatcher,
			Double eoperateRealityWeight, Double eoperateRealityNumber,
			String eoperateDispatcherTime, String eoperatePonderationTime,
			String eoperatePonderationTrue, String eoperateScreateTime,
			String eoperateSfinishTime, String eoperateCraneman,
			String eoperateStevedore, String eoperateAuditingTrue,
			String eoperateAuditingTime, String eoperateCollectTime,
			Double eoperateRealityCost, String eoperateDefinedOne,
			String eoperateDefinedTwo, String eoperateRemark,
			String eoperateTruckNum, String eoperateDefinedThree,
			String eoperateDefinedFour, String eoperateClientAccounts,
			Double eoperateShouldCost, PaymentFashion eoperatePaymentFashion) {
		this.eoperateId = eoperateId;
		this.eoperateClientAccounts = eoperateClientAccounts;
		this.eoperateShouldCost = eoperateShouldCost;
		this.eoperatePaymentFashion = eoperatePaymentFashion;
		this.eoperateTruckNum = eoperateTruckNum;
		this.eoperateDefinedThree = eoperateDefinedThree;
		this.eoperateDefinedFour = eoperateDefinedFour;
		this.interiorUserByEoperateCollectMan = interiorUserByEoperateCollectMan;
		this.tarehouse = tarehouse;
		this.interiorUserByEoperatePonderationMan = interiorUserByEoperatePonderationMan;
		this.interiorUserByEoperateStoreman = interiorUserByEoperateStoreman;
		this.exportSeed = exportSeed;
		this.interiorUserByEoperateAuditing = interiorUserByEoperateAuditing;
		this.interiorUserByEoperateDispatcher = interiorUserByEoperateDispatcher;
		this.eoperateRealityWeight = eoperateRealityWeight;
		this.eoperateRealityNumber = eoperateRealityNumber;
		this.eoperateDispatcherTime = eoperateDispatcherTime;
		this.eoperatePonderationTime = eoperatePonderationTime;
		this.eoperatePonderationTrue = eoperatePonderationTrue;
		this.eoperateScreateTime = eoperateScreateTime;
		this.eoperateSfinishTime = eoperateSfinishTime;
		this.eoperateCraneman = eoperateCraneman;
		this.eoperateStevedore = eoperateStevedore;
		this.eoperateAuditingTrue = eoperateAuditingTrue;
		this.eoperateAuditingTime = eoperateAuditingTime;
		this.eoperateCollectTime = eoperateCollectTime;
		this.eoperateRealityCost = eoperateRealityCost;
		this.eoperateDefinedOne = eoperateDefinedOne;
		this.eoperateDefinedTwo = eoperateDefinedTwo;
		this.eoperateRemark = eoperateRemark;
	}

	public ExportOperate(InteriorUser interiorUserByEoperateCollectMan,
			Tarehouse tarehouse,
			InteriorUser interiorUserByEoperatePonderationMan,
			InteriorUser interiorUserByEoperateStoreman, ExportSeed exportSeed,
			InteriorUser interiorUserByEoperateAuditing,
			InteriorUser interiorUserByEoperateDispatcher,
			Double eoperateRealityWeight, Double eoperateRealityNumber,
			String eoperateDispatcherTime, String eoperatePonderationTime,
			String eoperatePonderationTrue, String eoperateScreateTime,
			String eoperateSfinishTime, String eoperateCraneman,
			String eoperateStevedore, String eoperateAuditingTrue,
			String eoperateAuditingTime, String eoperateCollectTime,
			Double eoperateRealityCost, String eoperateDefinedOne,
			String eoperateDefinedTwo, String eoperateRemark,
			String eoperateTruckNum, String eoperateDefinedThree,
			String eoperateDefinedFour, String eoperateClientAccounts,
			Double eoperateShouldCost, PaymentFashion eoperatePaymentFashion) {
		this.eoperateClientAccounts = eoperateClientAccounts;
		this.eoperateShouldCost = eoperateShouldCost;
		this.eoperatePaymentFashion = eoperatePaymentFashion;
		this.eoperateTruckNum = eoperateTruckNum;
		this.eoperateDefinedThree = eoperateDefinedThree;
		this.eoperateDefinedFour = eoperateDefinedFour;
		this.interiorUserByEoperateCollectMan = interiorUserByEoperateCollectMan;
		this.tarehouse = tarehouse;
		this.interiorUserByEoperatePonderationMan = interiorUserByEoperatePonderationMan;
		this.interiorUserByEoperateStoreman = interiorUserByEoperateStoreman;
		this.exportSeed = exportSeed;
		this.interiorUserByEoperateAuditing = interiorUserByEoperateAuditing;
		this.interiorUserByEoperateDispatcher = interiorUserByEoperateDispatcher;
		this.eoperateRealityWeight = eoperateRealityWeight;
		this.eoperateRealityNumber = eoperateRealityNumber;
		this.eoperateDispatcherTime = eoperateDispatcherTime;
		this.eoperatePonderationTime = eoperatePonderationTime;
		this.eoperatePonderationTrue = eoperatePonderationTrue;
		this.eoperateScreateTime = eoperateScreateTime;
		this.eoperateSfinishTime = eoperateSfinishTime;
		this.eoperateCraneman = eoperateCraneman;
		this.eoperateStevedore = eoperateStevedore;
		this.eoperateAuditingTrue = eoperateAuditingTrue;
		this.eoperateAuditingTime = eoperateAuditingTime;
		this.eoperateCollectTime = eoperateCollectTime;
		this.eoperateRealityCost = eoperateRealityCost;
		this.eoperateDefinedOne = eoperateDefinedOne;
		this.eoperateDefinedTwo = eoperateDefinedTwo;
		this.eoperateRemark = eoperateRemark;
	}

	public String getEOperatepici() {
		return EOperatepici;
	}

	public void setEOperatepici(String eOperatepici) {
		EOperatepici = eOperatepici;
	}

	public String getEoperateTruckNum() {
		return eoperateTruckNum;
	}

	public void setEoperateTruckNum(String eoperateTruckNum) {
		this.eoperateTruckNum = eoperateTruckNum;
	}

	public String getEoperateDefinedThree() {
		return eoperateDefinedThree;
	}

	public void setEoperateDefinedThree(String eoperateDefinedThree) {
		this.eoperateDefinedThree = eoperateDefinedThree;
	}

	public String getEoperateDefinedFour() {
		return eoperateDefinedFour;
	}

	public void setEoperateDefinedFour(String eoperateDefinedFour) {
		this.eoperateDefinedFour = eoperateDefinedFour;
	}

	public String getEoperateClientAccounts() {
		return eoperateClientAccounts;
	}

	public void setEoperateClientAccounts(String eoperateClientAccounts) {
		this.eoperateClientAccounts = eoperateClientAccounts;
	}

	public Double getEoperateShouldCost() {
		return eoperateShouldCost;
	}

	public void setEoperateShouldCost(Double eoperateShouldCost) {
		this.eoperateShouldCost = eoperateShouldCost;
	}

	public PaymentFashion getEoperatePaymentFashion() {
		return eoperatePaymentFashion;
	}

	public void setEoperatePaymentFashion(PaymentFashion eoperatePaymentFashion) {
		this.eoperatePaymentFashion = eoperatePaymentFashion;
	}

	public String getEOperateDefinedThree() {
		return eoperateDefinedThree;
	}

	public void setEOperateDefinedThree(String eOperateDefinedThree) {
		eoperateDefinedThree = eOperateDefinedThree;
	}

	public String getEOperateDefinedFour() {
		return eoperateDefinedFour;
	}

	public void setEOperateDefinedFour(String eOperateDefinedFour) {
		eoperateDefinedFour = eOperateDefinedFour;
	}

	public String getEoperateId() {
		return this.eoperateId;
	}

	public void setEoperateId(String eoperateId) {
		this.eoperateId = eoperateId;
	}

	public InteriorUser getInteriorUserByEoperateCollectMan() {
		return this.interiorUserByEoperateCollectMan;
	}

	public void setInteriorUserByEoperateCollectMan(
			InteriorUser interiorUserByEoperateCollectMan) {
		this.interiorUserByEoperateCollectMan = interiorUserByEoperateCollectMan;
	}

	public Tarehouse getTarehouse() {
		return this.tarehouse;
	}

	public void setTarehouse(Tarehouse tarehouse) {
		this.tarehouse = tarehouse;
	}

	public InteriorUser getInteriorUserByEoperatePonderationMan() {
		return this.interiorUserByEoperatePonderationMan;
	}

	public void setInteriorUserByEoperatePonderationMan(
			InteriorUser interiorUserByEoperatePonderationMan) {
		this.interiorUserByEoperatePonderationMan = interiorUserByEoperatePonderationMan;
	}

	public InteriorUser getInteriorUserByEoperateStoreman() {
		return this.interiorUserByEoperateStoreman;
	}

	public void setInteriorUserByEoperateStoreman(
			InteriorUser interiorUserByEoperateStoreman) {
		this.interiorUserByEoperateStoreman = interiorUserByEoperateStoreman;
	}

	public ExportSeed getExportSeed() {
		return this.exportSeed;
	}

	public void setExportSeed(ExportSeed exportSeed) {
		this.exportSeed = exportSeed;
	}

	public InteriorUser getInteriorUserByEoperateAuditing() {
		return this.interiorUserByEoperateAuditing;
	}

	public void setInteriorUserByEoperateAuditing(
			InteriorUser interiorUserByEoperateAuditing) {
		this.interiorUserByEoperateAuditing = interiorUserByEoperateAuditing;
	}

	public InteriorUser getInteriorUserByEoperateDispatcher() {
		return this.interiorUserByEoperateDispatcher;
	}

	public void setInteriorUserByEoperateDispatcher(
			InteriorUser interiorUserByEoperateDispatcher) {
		this.interiorUserByEoperateDispatcher = interiorUserByEoperateDispatcher;
	}

	public Double getEoperateRealityWeight() {
		return this.eoperateRealityWeight;
	}

	public void setEoperateRealityWeight(Double eoperateRealityWeight) {
		this.eoperateRealityWeight = eoperateRealityWeight;
	}

	public Double getEoperateRealityNumber() {
		return this.eoperateRealityNumber;
	}

	public void setEoperateRealityNumber(Double eoperateRealityNumber) {
		this.eoperateRealityNumber = eoperateRealityNumber;
	}

	public String getEoperateDispatcherTime() {
		return this.eoperateDispatcherTime;
	}

	public void setEoperateDispatcherTime(String eoperateDispatcherTime) {
		this.eoperateDispatcherTime = eoperateDispatcherTime;
	}

	public String getEoperatePonderationTime() {
		return this.eoperatePonderationTime;
	}

	public void setEoperatePonderationTime(String eoperatePonderationTime) {
		this.eoperatePonderationTime = eoperatePonderationTime;
	}

	public String getEoperatePonderationTrue() {
		return this.eoperatePonderationTrue;
	}

	public void setEoperatePonderationTrue(String eoperatePonderationTrue) {
		this.eoperatePonderationTrue = eoperatePonderationTrue;
	}

	public String getEoperateScreateTime() {
		return this.eoperateScreateTime;
	}

	public void setEoperateScreateTime(String eoperateScreateTime) {
		this.eoperateScreateTime = eoperateScreateTime;
	}

	public String getEoperateSfinishTime() {
		return this.eoperateSfinishTime;
	}

	public void setEoperateSfinishTime(String eoperateSfinishTime) {
		this.eoperateSfinishTime = eoperateSfinishTime;
	}

	public String getEoperateCraneman() {
		return this.eoperateCraneman;
	}

	public void setEoperateCraneman(String eoperateCraneman) {
		this.eoperateCraneman = eoperateCraneman;
	}

	public String getEoperateStevedore() {
		return this.eoperateStevedore;
	}

	public void setEoperateStevedore(String eoperateStevedore) {
		this.eoperateStevedore = eoperateStevedore;
	}

	public String getEoperateAuditingTrue() {
		return this.eoperateAuditingTrue;
	}

	public void setEoperateAuditingTrue(String eoperateAuditingTrue) {
		this.eoperateAuditingTrue = eoperateAuditingTrue;
	}

	public String getEoperateAuditingTime() {
		return this.eoperateAuditingTime;
	}

	public void setEoperateAuditingTime(String eoperateAuditingTime) {
		this.eoperateAuditingTime = eoperateAuditingTime;
	}

	public String getEoperateCollectTime() {
		return this.eoperateCollectTime;
	}

	public void setEoperateCollectTime(String eoperateCollectTime) {
		this.eoperateCollectTime = eoperateCollectTime;
	}

	public Double getEoperateRealityCost() {
		return this.eoperateRealityCost;
	}

	public void setEoperateRealityCost(Double eoperateRealityCost) {
		this.eoperateRealityCost = eoperateRealityCost;
	}

	public String getEoperateDefinedOne() {
		return this.eoperateDefinedOne;
	}

	public void setEoperateDefinedOne(String eoperateDefinedOne) {
		this.eoperateDefinedOne = eoperateDefinedOne;
	}

	public String getEoperateDefinedTwo() {
		return this.eoperateDefinedTwo;
	}

	public void setEoperateDefinedTwo(String eoperateDefinedTwo) {
		this.eoperateDefinedTwo = eoperateDefinedTwo;
	}

	public String getEoperateRemark() {
		return this.eoperateRemark;
	}

	public void setEoperateRemark(String eoperateRemark) {
		this.eoperateRemark = eoperateRemark;
	}

}