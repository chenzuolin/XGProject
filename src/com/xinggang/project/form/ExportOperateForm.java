package com.xinggang.project.form;

import org.apache.struts.action.ActionForm;

/**
 * 出库子订单操作表单
 * 
 * @author Administrator
 * 
 */
public class ExportOperateForm extends ActionForm {

	private static final long serialVersionUID = -7464426285140432961L;
	private String eoperateId;// 出库订单操作编号
	private Integer interiorUserByEoperateCollectMan; // 对应的收费员
	private Integer tarehouse;// 对应的库位
	private Integer interiorUserByEoperatePonderationMan;// 对应的司磅员
	private Integer interiorUserByEoperateStoreman;// 对应的现场保管员
	private String exportSeed;// 对应的子订单类
	private Integer interiorUserByEoperateAuditing;// 对应的审核人员
	private Integer interiorUserByEoperateDispatcher;// 对应的调度员
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
	private String eoperateDefinedOne;// 操作表的订单状态
	private String eoperateDefinedTwo;// 调度给保管分配的重量
	private String eoperateRemark;// 备注

	private String eoperateTruckNum;// 对应的车号，由保管员进行填入
	private String eoperateDefinedThree;// 预留字段三
	private String eoperateDefinedFour;// 预留字段四

	private String eoperateClientAccounts;// 结算方式
	private Double eoperateShouldCost;// 应收费用
	private int eoperatePaymentFashion;// 支付方式，调用支付方式类

	private Double ercishishou;// 二次作业实收费用

	// 查询全部，可以通过出库总订单编号，客户订单号，订单生成的时间范围，起始日期，结束日期，客户单位名称全拼，简称，单位助记符，通过货物名称，货物助记符，规格，材质，属性，库位名称，调度员，司磅员，保管员，审核，收费
	private String begin;// 日式日期
	private String finish;// 结束日期
	private String zongdingdanhao;// 总订单编号，用到查询全部，模糊查询
	private String kehudaohao;// 客户单号
	private String danwei;// 客户单位全拼
	private String jiacheng;// 单位简称
	private String danweiSign;// 单位助记符
	private String goodsName;// 货物名称
	private String goodsSign;// 货物助记符
	private String guige;// 规格
	private String caizhi;// 材质
	private String shuxing;// 属性
	private String kuweiName;// 库位
	private String diaodu;// 调度员
	private String sibang;// 司磅员
	private String baoguan;// 保管员
	private String shenhe;// 审核员
	private String shoufei;// 收费员

	private String EOperatepici;// 批次
	private String[] pici;// 对应修改时的批次数组

	public String[] getPici() {
		return pici;
	}

	public void setPici(String[] pici) {
		this.pici = pici;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getEOperatepici() {
		return EOperatepici;
	}

	public void setEOperatepici(String eOperatepici) {
		EOperatepici = eOperatepici;
	}

	public String getZongdingdanhao() {
		return zongdingdanhao;
	}

	public void setZongdingdanhao(String zongdingdanhao) {
		this.zongdingdanhao = zongdingdanhao;
	}

	public String getKehudaohao() {
		return kehudaohao;
	}

	public void setKehudaohao(String kehudaohao) {
		this.kehudaohao = kehudaohao;
	}

	public String getDanwei() {
		return danwei;
	}

	public void setDanwei(String danwei) {
		this.danwei = danwei;
	}

	public String getJiacheng() {
		return jiacheng;
	}

	public void setJiacheng(String jiacheng) {
		this.jiacheng = jiacheng;
	}

	public String getDanweiSign() {
		return danweiSign;
	}

	public void setDanweiSign(String danweiSign) {
		this.danweiSign = danweiSign;
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

	public String getGuige() {
		return guige;
	}

	public void setGuige(String guige) {
		this.guige = guige;
	}

	public String getCaizhi() {
		return caizhi;
	}

	public void setCaizhi(String caizhi) {
		this.caizhi = caizhi;
	}

	public String getShuxing() {
		return shuxing;
	}

	public void setShuxing(String shuxing) {
		this.shuxing = shuxing;
	}

	public String getKuweiName() {
		return kuweiName;
	}

	public void setKuweiName(String kuweiName) {
		this.kuweiName = kuweiName;
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

	public String getBaoguan() {
		return baoguan;
	}

	public void setBaoguan(String baoguan) {
		this.baoguan = baoguan;
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

	public Double getErcishishou() {
		return ercishishou;
	}

	public void setErcishishou(Double ercishishou) {
		this.ercishishou = ercishishou;
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

	public int getEoperatePaymentFashion() {
		return eoperatePaymentFashion;
	}

	public void setEoperatePaymentFashion(int eoperatePaymentFashion) {
		this.eoperatePaymentFashion = eoperatePaymentFashion;
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

	public String getEoperateId() {
		return this.eoperateId;
	}

	public void setEoperateId(String eoperateId) {
		this.eoperateId = eoperateId;
	}

	public Integer getInteriorUserByEoperateCollectMan() {
		return this.interiorUserByEoperateCollectMan;
	}

	public void setInteriorUserByEoperateCollectMan(
			Integer interiorUserByEoperateCollectMan) {
		this.interiorUserByEoperateCollectMan = interiorUserByEoperateCollectMan;
	}

	public Integer getTarehouse() {
		return this.tarehouse;
	}

	public void setTarehouse(Integer tarehouse) {
		this.tarehouse = tarehouse;
	}

	public Integer getInteriorUserByEoperatePonderationMan() {
		return this.interiorUserByEoperatePonderationMan;
	}

	public void setInteriorUserByEoperatePonderationMan(
			Integer interiorUserByEoperatePonderationMan) {
		this.interiorUserByEoperatePonderationMan = interiorUserByEoperatePonderationMan;
	}

	public Integer getInteriorUserByEoperateStoreman() {
		return this.interiorUserByEoperateStoreman;
	}

	public void setInteriorUserByEoperateStoreman(
			Integer interiorUserByEoperateStoreman) {
		this.interiorUserByEoperateStoreman = interiorUserByEoperateStoreman;
	}

	public String getExportSeed() {
		return this.exportSeed;
	}

	public void setExportSeed(String exportSeed) {
		this.exportSeed = exportSeed;
	}

	public Integer getInteriorUserByEoperateAuditing() {
		return this.interiorUserByEoperateAuditing;
	}

	public void setInteriorUserByEoperateAuditing(
			Integer interiorUserByEoperateAuditing) {
		this.interiorUserByEoperateAuditing = interiorUserByEoperateAuditing;
	}

	public Integer getInteriorUserByEoperateDispatcher() {
		return this.interiorUserByEoperateDispatcher;
	}

	public void setInteriorUserByEoperateDispatcher(
			Integer interiorUserByEoperateDispatcher) {
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