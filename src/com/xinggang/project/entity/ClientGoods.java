package com.xinggang.project.entity;

/**
 * 客户货物库存类
 * 
 * @author Administrator
 * 
 */
public class ClientGoods implements java.io.Serializable {

	private static final long serialVersionUID = -7761903875629130856L;
	private String cgoodsId;// 客户货物库存编号
	private Client client;// 对应的客户
	private Goods  goods;// 对应的货物
	private Double cgoodsWeight;// 货物重量
	private Double cgoodsNumber;// 货物件数
	private String cgoodsFreeze;// 是否冻结
	private Double cgoodsFreezeWeight;// 冻结重量
	private Double cgoodsFreezeNumber;// 冻结件数
	private String cgoodsImpawn;// 是否质押
	private Double cgoodsImpawnWeight;// 质押重量
	private Double cgoodsImpawnNumber;// 质押件数
	private String cgoodsDefinedOne;// 预留字段一
	private String cgoodsDefinedTwo;// 预留字段二
	private String cgoodsRemark;// 备注

	private Double QiChu;// 转发村时统计的期初的库存入库的相加减去出库的相加
	private Double ExportHeJi;// 转发存时统计的某个时间段的出库合计
	private Double InputHeJi;// 转发存时统计的某个时间段的入库合计
	private Double QiMo;// 转发存时统计某个时间段的期末库存，期初的库存加入库的 重量减去出库的重量

	private Double huoru;// 收发存中的火车入库
	private Double qiru;// 收发存中的汽车入库
	private Double huochu;// 收发存中的火车出库
	private Double qichechu;// 收发存中的汽车出库
	private Double guohuru;// 收发存中的过户入库
	private Double guohuchu;// 收发存中的过户出库

	private String goodsName;// 收发存中显示的货物的名称
	private String pinlei;// 收发存中显示的货物的品类

	public ClientGoods() {
	}

	public ClientGoods(Client client, String goodsName, String pinlei) {
		this.client = client;
		this.goodsName = goodsName;
		this.pinlei = pinlei;
	}

	public ClientGoods(Client client, Goods goods, Double cgoodsWeight,
			Double cgoodsNumber, String cgoodsFreeze,
			Double cgoodsFreezeWeight, Double cgoodsFreezeNumber,
			String cgoodsImpawn, Double cgoodsImpawnWeight,
			Double cgoodsImpawnNumber, String cgoodsDefinedOne,
			String cgoodsDefinedTwo, String cgoodsRemark) {
		this.client = client;
		this.goods = goods;
		this.cgoodsWeight = cgoodsWeight;
		this.cgoodsNumber = cgoodsNumber;
		this.cgoodsFreeze = cgoodsFreeze;
		this.cgoodsFreezeWeight = cgoodsFreezeWeight;
		this.cgoodsFreezeNumber = cgoodsFreezeNumber;
		this.cgoodsImpawn = cgoodsImpawn;
		this.cgoodsImpawnWeight = cgoodsImpawnWeight;
		this.cgoodsImpawnNumber = cgoodsImpawnNumber;
		this.cgoodsDefinedOne = cgoodsDefinedOne;
		this.cgoodsDefinedTwo = cgoodsDefinedTwo;
		this.cgoodsRemark = cgoodsRemark;
	}

	// 编号，客户，货物，重量，件数，是否冻结，冻结重量，冻结件数，是否质押,质押重量,质押件数，预留字段一，预留字段二，备注
	public ClientGoods(String cgoodsId, Client client, Goods goods,
			Double cgoodsWeight, Double cgoodsNumber, String cgoodsFreeze,
			Double cgoodsFreezeWeight, Double cgoodsFreezeNumber,
			String cgoodsImpawn, Double cgoodsImpawnWeight,
			Double cgoodsImpawnNumber, String cgoodsDefinedOne,
			String cgoodsDefinedTwo, String cgoodsRemark) {
		this.cgoodsId = cgoodsId;
		this.client = client;
		this.goods = goods;
		this.cgoodsWeight = cgoodsWeight;
		this.cgoodsNumber = cgoodsNumber;
		this.cgoodsFreeze = cgoodsFreeze;
		this.cgoodsFreezeWeight = cgoodsFreezeWeight;
		this.cgoodsFreezeNumber = cgoodsFreezeNumber;
		this.cgoodsImpawn = cgoodsImpawn;
		this.cgoodsImpawnWeight = cgoodsImpawnWeight;
		this.cgoodsImpawnNumber = cgoodsImpawnNumber;
		this.cgoodsDefinedOne = cgoodsDefinedOne;
		this.cgoodsDefinedTwo = cgoodsDefinedTwo;
		this.cgoodsRemark = cgoodsRemark;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getPinlei() {
		return pinlei;
	}

	public void setPinlei(String pinlei) {
		this.pinlei = pinlei;
	}

	public Double getHuoru() {
		return huoru;
	}

	public void setHuoru(Double huoru) {
		this.huoru = huoru;
	}

	public Double getQiru() {
		return qiru;
	}

	public void setQiru(Double qiru) {
		this.qiru = qiru;
	}

	public Double getHuochu() {
		return huochu;
	}

	public void setHuochu(Double huochu) {
		this.huochu = huochu;
	}

	public Double getQichechu() {
		return qichechu;
	}

	public void setQichechu(Double qichechu) {
		this.qichechu = qichechu;
	}

	public Double getGuohuru() {
		return guohuru;
	}

	public void setGuohuru(Double guohuru) {
		this.guohuru = guohuru;
	}

	public Double getGuohuchu() {
		return guohuchu;
	}

	public void setGuohuchu(Double guohuchu) {
		this.guohuchu = guohuchu;
	}

	public Double getQiChu() {
		return QiChu;
	}

	public void setQiChu(Double qiChu) {
		QiChu = qiChu;
	}

	public Double getExportHeJi() {
		return ExportHeJi;
	}

	public void setExportHeJi(Double exportHeJi) {
		ExportHeJi = exportHeJi;
	}

	public Double getInputHeJi() {
		return InputHeJi;
	}

	public void setInputHeJi(Double inputHeJi) {
		InputHeJi = inputHeJi;
	}

	public Double getQiMo() {
		return QiMo;
	}

	public void setQiMo(Double qiMo) {
		QiMo = qiMo;
	}

	public String getCgoodsId() {
		return this.cgoodsId;
	}

	public void setCgoodsId(String cgoodsId) {
		this.cgoodsId = cgoodsId;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Goods getGoods() {
		return this.goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Double getCgoodsWeight() {
		return this.cgoodsWeight;
	}

	public void setCgoodsWeight(Double cgoodsWeight) {
		this.cgoodsWeight = cgoodsWeight;
	}

	public Double getCgoodsNumber() {
		return this.cgoodsNumber;
	}

	public void setCgoodsNumber(Double cgoodsNumber) {
		this.cgoodsNumber = cgoodsNumber;
	}

	public String getCgoodsFreeze() {
		return this.cgoodsFreeze;
	}

	public void setCgoodsFreeze(String cgoodsFreeze) {
		this.cgoodsFreeze = cgoodsFreeze;
	}

	public Double getCgoodsFreezeWeight() {
		return this.cgoodsFreezeWeight;
	}

	public void setCgoodsFreezeWeight(Double cgoodsFreezeWeight) {
		this.cgoodsFreezeWeight = cgoodsFreezeWeight;
	}

	public Double getCgoodsFreezeNumber() {
		return this.cgoodsFreezeNumber;
	}

	public void setCgoodsFreezeNumber(Double cgoodsFreezeNumber) {
		this.cgoodsFreezeNumber = cgoodsFreezeNumber;
	}

	public String getCgoodsImpawn() {
		return this.cgoodsImpawn;
	}

	public void setCgoodsImpawn(String cgoodsImpawn) {
		this.cgoodsImpawn = cgoodsImpawn;
	}

	public Double getCgoodsImpawnWeight() {
		return this.cgoodsImpawnWeight;
	}

	public void setCgoodsImpawnWeight(Double cgoodsImpawnWeight) {
		this.cgoodsImpawnWeight = cgoodsImpawnWeight;
	}

	public Double getCgoodsImpawnNumber() {
		return this.cgoodsImpawnNumber;
	}

	public void setCgoodsImpawnNumber(Double cgoodsImpawnNumber) {
		this.cgoodsImpawnNumber = cgoodsImpawnNumber;
	}

	public String getCgoodsDefinedOne() {
		return this.cgoodsDefinedOne;
	}

	public void setCgoodsDefinedOne(String cgoodsDefinedOne) {
		this.cgoodsDefinedOne = cgoodsDefinedOne;
	}

	public String getCgoodsDefinedTwo() {
		return this.cgoodsDefinedTwo;
	}

	public void setCgoodsDefinedTwo(String cgoodsDefinedTwo) {
		this.cgoodsDefinedTwo = cgoodsDefinedTwo;
	}

	public String getCgoodsRemark() {
		return this.cgoodsRemark;
	}

	public void setCgoodsRemark(String cgoodsRemark) {
		this.cgoodsRemark = cgoodsRemark;
	}

}