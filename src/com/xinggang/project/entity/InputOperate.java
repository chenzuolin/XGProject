package com.xinggang.project.entity;

/**
 * 入库订单操作类
 * 
 * @author Administrator
 * 
 */
public class InputOperate implements java.io.Serializable {

	private static final long serialVersionUID = 7931874897191868103L;
	private String ioperateId;// 操作编号
	private InteriorUser interiorUserByIoperateDispatcherId;// 调度员,对应的内部人员类
	private Tarehouse tarehouse;// 对应的库位
	private InputSeed inputSeed;// 对应的入库子订单编号，入库子订单类
	private InteriorUser interiorUserByIoperateStoremanId;// 现场保管员，对应的内部人员类
	private InteriorUser interiorUserByIoperatePonderationManId;// 司磅员,对应的内部人员类
	private InteriorUser interiorUserByIoperateAuditingId;// 审核人员,对应的内部人员类
	private InteriorUser interiorUserByIoperateCollectManId;// 收费人员，对应的内部人员类
	private String ioperateDispatcherTime;// 调度员操作时间
	private String ioperatePonderationTime;// 过磅时间
	private String ioperatePonderationTrue;// 过磅还是理算货物(过磅/理算)
	private String ioperateScreateTime;// 现场保管员开始操作时间
	private String ioperateSfinishTime;// 现场保管员结束操作时间
	private String ioperateCraneman;// 天车工
	private String ioperateStevedore;// 装卸工
	private String ioperateAuditingTrue;// 审核次数
	private String ioperateAuditingTime;// 审核时间
	private String ioperateCollectTime;// 收费时间
	private Double ioperateRealityCost;// 实收费用
	private Double ioperateRealityWeight;// 对应人员的入库重量
	private Double ioperateRealityNumber;// 对应人员的入库件数
	private String ioperateDefinedOne;// 入库次数
	private String ioperateDefinedTwo;// 入库的操作状态
	private String ioperateRemark;// 备注
	
	private String ioperatepici;//入库批次
	
	private Input input;// 总订单
	private String ioperateTruckNum;// 保管填入的车号
	private String ioperateClientAccounts;// 结算方式
	private Double ioperateShouldCost;// 应收费用
	private PaymentFashion ioperatePaymentFashion;// 支付方式，调用支付方式类
	
	
		
	//收费人员查询审核完成的订单
		public InputOperate(String ioperateId,
				InteriorUser interiorUserByIoperateDispatcherId,
				Tarehouse tarehouse, InputSeed inputSeed,
				InteriorUser interiorUserByIoperateStoremanId,
				InteriorUser interiorUserByIoperatePonderationManId,
				InteriorUser interiorUserByIoperateAuditingId,
				InteriorUser interiorUserByIoperateCollectManId,
				String ioperateDispatcherTime, String ioperatePonderationTime,
				String ioperatePonderationTrue, String ioperateScreateTime,
				String ioperateSfinishTime, String ioperateCraneman,
				String ioperateStevedore, String ioperateAuditingTrue,
				String ioperateAuditingTime, String ioperateCollectTime,
				Double ioperateRealityCost, Double ioperateRealityWeight,
				Double ioperateRealityNumber, String ioperateDefinedOne,
				String ioperateDefinedTwo,String ioperateRemark) {
			this.ioperateId = ioperateId;
			this.interiorUserByIoperateDispatcherId = interiorUserByIoperateDispatcherId;
			this.tarehouse = tarehouse;
			this.inputSeed = inputSeed;
			this.interiorUserByIoperateStoremanId = interiorUserByIoperateStoremanId;
			this.interiorUserByIoperatePonderationManId = interiorUserByIoperatePonderationManId;
			this.interiorUserByIoperateAuditingId = interiorUserByIoperateAuditingId;
			this.interiorUserByIoperateCollectManId = interiorUserByIoperateCollectManId;
			this.ioperateDispatcherTime = ioperateDispatcherTime;
			this.ioperatePonderationTime = ioperatePonderationTime;
			this.ioperatePonderationTrue = ioperatePonderationTrue;
			this.ioperateScreateTime = ioperateScreateTime;
			this.ioperateSfinishTime = ioperateSfinishTime;
			this.ioperateCraneman = ioperateCraneman;
			this.ioperateStevedore = ioperateStevedore;
			this.ioperateAuditingTrue = ioperateAuditingTrue;
			this.ioperateAuditingTime = ioperateAuditingTime;
			this.ioperateCollectTime = ioperateCollectTime;
			this.ioperateRealityCost = ioperateRealityCost;
			this.ioperateRealityWeight = ioperateRealityWeight;
			this.ioperateRealityNumber = ioperateRealityNumber;
			this.ioperateDefinedOne = ioperateDefinedOne;
			this.ioperateDefinedTwo=ioperateDefinedTwo;
			this.ioperateRemark = ioperateRemark;
		}

	// 用于查询没有审核，而且订单状态为已经入库的信息
	public InputOperate(String ioperateId,
			InteriorUser interiorUserByIoperateDispatcherId,
			Tarehouse tarehouse, InputSeed inputSeed,
			InteriorUser interiorUserByIoperateStoremanId,
			InteriorUser interiorUserByIoperatePonderationManId,
			InteriorUser interiorUserByIoperateAuditingId,
			InteriorUser interiorUserByIoperateCollectManId,
			String ioperateDispatcherTime, String ioperatePonderationTime,
			String ioperatePonderationTrue, String ioperateScreateTime,
			String ioperateSfinishTime, String ioperateCraneman,
			String ioperateStevedore, String ioperateAuditingTrue,
			String ioperateAuditingTime, Double ioperateRealityWeight,
			Double ioperateRealityNumber, String ioperateRemark,
			String ioperateDefinedOne) {
		this.ioperateId = ioperateId;
		this.interiorUserByIoperateDispatcherId = interiorUserByIoperateDispatcherId;
		this.tarehouse = tarehouse;
		this.inputSeed = inputSeed;
		this.interiorUserByIoperateStoremanId = interiorUserByIoperateStoremanId;
		this.interiorUserByIoperatePonderationManId = interiorUserByIoperatePonderationManId;
		this.interiorUserByIoperateAuditingId = interiorUserByIoperateAuditingId;
		this.interiorUserByIoperateCollectManId = interiorUserByIoperateCollectManId;
		this.ioperateDispatcherTime = ioperateDispatcherTime;
		this.ioperatePonderationTime = ioperatePonderationTime;
		this.ioperatePonderationTrue = ioperatePonderationTrue;
		this.ioperateScreateTime = ioperateScreateTime;
		this.ioperateSfinishTime = ioperateSfinishTime;
		this.ioperateCraneman = ioperateCraneman;
		this.ioperateStevedore = ioperateStevedore;
		this.ioperateAuditingTrue = ioperateAuditingTrue;
		this.ioperateAuditingTime = ioperateAuditingTime;
		this.ioperateRealityWeight = ioperateRealityWeight;
		this.ioperateRealityNumber = ioperateRealityNumber;
		this.ioperateRemark = ioperateRemark;
		this.ioperateDefinedOne = ioperateDefinedOne;
	}
	
	//收费人员查询审核完成的订单
	public InputOperate(String ioperateId,
			InteriorUser interiorUserByIoperateDispatcherId,
			Tarehouse tarehouse, InputSeed inputSeed,
			InteriorUser interiorUserByIoperateStoremanId,
			InteriorUser interiorUserByIoperatePonderationManId,
			InteriorUser interiorUserByIoperateAuditingId,
			InteriorUser interiorUserByIoperateCollectManId,
			String ioperateDispatcherTime, String ioperatePonderationTime,
			String ioperatePonderationTrue, String ioperateScreateTime,
			String ioperateSfinishTime, String ioperateCraneman,
			String ioperateStevedore, String ioperateAuditingTrue,
			String ioperateAuditingTime, String ioperateCollectTime,
			Double ioperateRealityCost, Double ioperateRealityWeight,
			Double ioperateRealityNumber, String ioperateDefinedOne,
			String ioperateRemark) {
		this.ioperateId = ioperateId;
		this.interiorUserByIoperateDispatcherId = interiorUserByIoperateDispatcherId;
		this.tarehouse = tarehouse;
		this.inputSeed = inputSeed;
		this.interiorUserByIoperateStoremanId = interiorUserByIoperateStoremanId;
		this.interiorUserByIoperatePonderationManId = interiorUserByIoperatePonderationManId;
		this.interiorUserByIoperateAuditingId = interiorUserByIoperateAuditingId;
		this.interiorUserByIoperateCollectManId = interiorUserByIoperateCollectManId;
		this.ioperateDispatcherTime = ioperateDispatcherTime;
		this.ioperatePonderationTime = ioperatePonderationTime;
		this.ioperatePonderationTrue = ioperatePonderationTrue;
		this.ioperateScreateTime = ioperateScreateTime;
		this.ioperateSfinishTime = ioperateSfinishTime;
		this.ioperateCraneman = ioperateCraneman;
		this.ioperateStevedore = ioperateStevedore;
		this.ioperateAuditingTrue = ioperateAuditingTrue;
		this.ioperateAuditingTime = ioperateAuditingTime;
		this.ioperateCollectTime = ioperateCollectTime;
		this.ioperateRealityCost = ioperateRealityCost;
		this.ioperateRealityWeight = ioperateRealityWeight;
		this.ioperateRealityNumber = ioperateRealityNumber;
		this.ioperateDefinedOne = ioperateDefinedOne;
		this.ioperateRemark = ioperateRemark;
	}


	//用于查询非理算货物
	public InputOperate(String ioperateId,
			InteriorUser interiorUserByIoperateDispatcherId,
			InputSeed inputSeed, InteriorUser interiorUserByIoperateStoremanId,
			InteriorUser interiorUserByIoperatePonderationManId,
			String ioperateDispatcherTime, String ioperatePonderationTrue) {
		this.ioperateId = ioperateId;
		this.interiorUserByIoperateDispatcherId = interiorUserByIoperateDispatcherId;
		this.inputSeed = inputSeed;
		this.interiorUserByIoperateStoremanId = interiorUserByIoperateStoremanId;
		this.interiorUserByIoperatePonderationManId = interiorUserByIoperatePonderationManId;
		this.ioperateDispatcherTime = ioperateDispatcherTime;
		this.ioperatePonderationTrue = ioperatePonderationTrue;
	}

	// Constructors
	
	
	

	/** default constructor */
	public InputOperate() {
	}

	public String getIoperatepici() {
		return ioperatepici;
	}

	public void setIoperatepici(String ioperatepici) {
		this.ioperatepici = ioperatepici;
	}

	public String getIoperateClientAccounts() {
		return ioperateClientAccounts;
	}

	public void setIoperateClientAccounts(String ioperateClientAccounts) {
		this.ioperateClientAccounts = ioperateClientAccounts;
	}

	public Double getIoperateShouldCost() {
		return ioperateShouldCost;
	}

	public void setIoperateShouldCost(Double ioperateShouldCost) {
		this.ioperateShouldCost = ioperateShouldCost;
	}

	public PaymentFashion getIoperatePaymentFashion() {
		return ioperatePaymentFashion;
	}

	public void setIoperatePaymentFashion(PaymentFashion ioperatePaymentFashion) {
		this.ioperatePaymentFashion = ioperatePaymentFashion;
	}

	/** full constructor */
	public InputOperate(InteriorUser interiorUserByIoperateDispatcherId,
			Tarehouse tarehouse, InputSeed inputSeed,
			InteriorUser interiorUserByIoperateStoremanId,
			InteriorUser interiorUserByIoperatePonderationManId,
			InteriorUser interiorUserByIoperateAuditingId,
			InteriorUser interiorUserByIoperateCollectManId,
			String ioperateDispatcherTime, String ioperatePonderationTime,
			String ioperatePonderationTrue, String ioperateScreateTime,
			String ioperateSfinishTime, String ioperateCraneman,
			String ioperateStevedore, String ioperateAuditingTrue,
			String ioperateAuditingTime, String ioperateCollectTime,
			Double ioperateRealityCost, Double ioperateRealityWeight,
			Double ioperateRealityNumber, String ioperateDefinedOne,
			String ioperateDefinedTwo, String ioperateRemark,
			String ioperateTruckNum, String ioperateClientAccounts,
			Double ioperateShouldCost, PaymentFashion ioperatePaymentFashion) {
		this.ioperateClientAccounts = ioperateClientAccounts;
		this.ioperateShouldCost = ioperateShouldCost;
		this.ioperatePaymentFashion = ioperatePaymentFashion;
		this.ioperateTruckNum = ioperateTruckNum;
		this.interiorUserByIoperateDispatcherId = interiorUserByIoperateDispatcherId;
		this.tarehouse = tarehouse;
		this.inputSeed = inputSeed;
		this.interiorUserByIoperateStoremanId = interiorUserByIoperateStoremanId;
		this.interiorUserByIoperatePonderationManId = interiorUserByIoperatePonderationManId;
		this.interiorUserByIoperateAuditingId = interiorUserByIoperateAuditingId;
		this.interiorUserByIoperateCollectManId = interiorUserByIoperateCollectManId;
		this.ioperateDispatcherTime = ioperateDispatcherTime;
		this.ioperatePonderationTime = ioperatePonderationTime;
		this.ioperatePonderationTrue = ioperatePonderationTrue;
		this.ioperateScreateTime = ioperateScreateTime;
		this.ioperateSfinishTime = ioperateSfinishTime;
		this.ioperateCraneman = ioperateCraneman;
		this.ioperateStevedore = ioperateStevedore;
		this.ioperateAuditingTrue = ioperateAuditingTrue;
		this.ioperateAuditingTime = ioperateAuditingTime;
		this.ioperateCollectTime = ioperateCollectTime;
		this.ioperateRealityCost = ioperateRealityCost;
		this.ioperateRealityWeight = ioperateRealityWeight;
		this.ioperateRealityNumber = ioperateRealityNumber;
		this.ioperateDefinedOne = ioperateDefinedOne;
		this.ioperateDefinedTwo = ioperateDefinedTwo;
		this.ioperateRemark = ioperateRemark;
	}


	public String getIoperateTruckNum() {
		return ioperateTruckNum;
	}

	public void setIoperateTruckNum(String ioperateTruckNum) {
		this.ioperateTruckNum = ioperateTruckNum;
	}

	public Input getInput() {
		return input;
	}

	public void setInput(Input input) {
		this.input = input;
	}

	public String getIoperateId() {
		return this.ioperateId;
	}

	public void setIoperateId(String ioperateId) {
		this.ioperateId = ioperateId;
	}

	public InteriorUser getInteriorUserByIoperateDispatcherId() {
		return this.interiorUserByIoperateDispatcherId;
	}

	public void setInteriorUserByIoperateDispatcherId(
			InteriorUser interiorUserByIoperateDispatcherId) {
		this.interiorUserByIoperateDispatcherId = interiorUserByIoperateDispatcherId;
	}

	public Tarehouse getTarehouse() {
		return this.tarehouse;
	}

	public void setTarehouse(Tarehouse tarehouse) {
		this.tarehouse = tarehouse;
	}

	public InputSeed getInputSeed() {
		return this.inputSeed;
	}

	public void setInputSeed(InputSeed inputSeed) {
		this.inputSeed = inputSeed;
	}

	public InteriorUser getInteriorUserByIoperateStoremanId() {
		return this.interiorUserByIoperateStoremanId;
	}

	public void setInteriorUserByIoperateStoremanId(
			InteriorUser interiorUserByIoperateStoremanId) {
		this.interiorUserByIoperateStoremanId = interiorUserByIoperateStoremanId;
	}

	public InteriorUser getInteriorUserByIoperatePonderationManId() {
		return this.interiorUserByIoperatePonderationManId;
	}

	public void setInteriorUserByIoperatePonderationManId(
			InteriorUser interiorUserByIoperatePonderationManId) {
		this.interiorUserByIoperatePonderationManId = interiorUserByIoperatePonderationManId;
	}

	public InteriorUser getInteriorUserByIoperateAuditingId() {
		return this.interiorUserByIoperateAuditingId;
	}

	public void setInteriorUserByIoperateAuditingId(
			InteriorUser interiorUserByIoperateAuditingId) {
		this.interiorUserByIoperateAuditingId = interiorUserByIoperateAuditingId;
	}

	public InteriorUser getInteriorUserByIoperateCollectManId() {
		return this.interiorUserByIoperateCollectManId;
	}

	public void setInteriorUserByIoperateCollectManId(
			InteriorUser interiorUserByIoperateCollectManId) {
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