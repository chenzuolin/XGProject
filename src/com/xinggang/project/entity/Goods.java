package com.xinggang.project.entity;

/**
 * Goods entity. @author MyEclipse Persistence Tools
 */

public class Goods implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2953364253957670466L;
	private Integer goodsId;// 货物名称编号
	private GoodsProperty goodsProperty;// 货物属性(调用属性ID)
	private GoodsCategory goodsCategory;// 货物品类(调用品类ID)
	private GoodsStandard goodsStandard;// 规格
	private GoodsQuality goodsQuality;// 材质
	private GoodsYieldly goodsYieldly;// 货物产地(调用产地ID)
	private String goodsName;// 货物名称
	private String goodsSign;// 货物名称助记符
	private GoodsUnit goodsUnit;// 计量单位
	private Double goodsAdjustment;// 理算重量(数值，保留三位)
	private String goodsDefinedOne;// 是否停用
	private String goodsDefinedTwo;// 自定义字段2
	private String goodsRemark;// 备注

	private Double QiChu;// 转发村时统计的期初的库存入库的相加减去出库的相加
	private Double ExportHeJi;// 转发存时统计的某个时间段的出库合计
	private Double InputHeJi;// 转发存时统计的某个时间段的入库合计
	private Double QiMo;// 转发存时统计某个时间段的期末库存，期初的库存加入库的 重量减去出库的重量

	public Goods() {
	}

	public Goods(Integer goodsId, GoodsProperty goodsProperty,
			GoodsCategory goodsCategory, GoodsStandard goodsStandard,
			GoodsQuality goodsQuality, GoodsYieldly goodsYieldly,
			String goodsName) {
		this.goodsId = goodsId;
		this.goodsProperty = goodsProperty;
		this.goodsCategory = goodsCategory;
		this.goodsStandard = goodsStandard;
		this.goodsQuality = goodsQuality;
		this.goodsYieldly = goodsYieldly;
		this.goodsName = goodsName;
	}

	public Goods(GoodsProperty goodsProperty, GoodsCategory goodsCategory,
			GoodsStandard goodsStandard, GoodsQuality goodsQuality,
			GoodsYieldly goodsYieldly, String goodsName, String goodsSign,
			GoodsUnit goodsUnit, Double goodsAdjustment,
			String goodsDefinedOne, String goodsDefinedTwo, String goodsRemark) {
		this.goodsProperty = goodsProperty;
		this.goodsCategory = goodsCategory;
		this.goodsStandard = goodsStandard;
		this.goodsQuality = goodsQuality;
		this.goodsYieldly = goodsYieldly;
		this.goodsName = goodsName;
		this.goodsSign = goodsSign;
		this.goodsUnit = goodsUnit;
		this.goodsAdjustment = goodsAdjustment;
		this.goodsDefinedOne = goodsDefinedOne;
		this.goodsDefinedTwo = goodsDefinedTwo;
		this.goodsRemark = goodsRemark;
	}

	public Double getExportHeJi() {
		return ExportHeJi;
	}

	public void setExportHeJi(Double exportHeJi) {
		ExportHeJi = exportHeJi;
	}

	public Double getQiChu() {
		return QiChu;
	}

	public void setQiChu(Double qiChu) {
		QiChu = qiChu;
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

	public Integer getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public GoodsProperty getGoodsProperty() {
		return this.goodsProperty;
	}

	public void setGoodsProperty(GoodsProperty goodsProperty) {
		this.goodsProperty = goodsProperty;
	}

	public GoodsCategory getGoodsCategory() {
		return this.goodsCategory;
	}

	public void setGoodsCategory(GoodsCategory goodsCategory) {
		this.goodsCategory = goodsCategory;
	}

	public GoodsStandard getGoodsStandard() {
		return this.goodsStandard;
	}

	public void setGoodsStandard(GoodsStandard goodsStandard) {
		this.goodsStandard = goodsStandard;
	}

	public GoodsQuality getGoodsQuality() {
		return this.goodsQuality;
	}

	public void setGoodsQuality(GoodsQuality goodsQuality) {
		this.goodsQuality = goodsQuality;
	}

	public GoodsYieldly getGoodsYieldly() {
		return this.goodsYieldly;
	}

	public void setGoodsYieldly(GoodsYieldly goodsYieldly) {
		this.goodsYieldly = goodsYieldly;
	}

	public String getGoodsName() {
		return this.goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsSign() {
		return this.goodsSign;
	}

	public void setGoodsSign(String goodsSign) {
		this.goodsSign = goodsSign;
	}

	public GoodsUnit getGoodsUnit() {
		return this.goodsUnit;
	}

	public void setGoodsUnit(GoodsUnit goodsUnit) {
		this.goodsUnit = goodsUnit;
	}

	public Double getGoodsAdjustment() {
		return this.goodsAdjustment;
	}

	public void setGoodsAdjustment(Double goodsAdjustment) {
		this.goodsAdjustment = goodsAdjustment;
	}

	public String getGoodsDefinedOne() {
		return this.goodsDefinedOne;
	}

	public void setGoodsDefinedOne(String goodsDefinedOne) {
		this.goodsDefinedOne = goodsDefinedOne;
	}

	public String getGoodsDefinedTwo() {
		return this.goodsDefinedTwo;
	}

	public void setGoodsDefinedTwo(String goodsDefinedTwo) {
		this.goodsDefinedTwo = goodsDefinedTwo;
	}

	public String getGoodsRemark() {
		return this.goodsRemark;
	}

	public void setGoodsRemark(String goodsRemark) {
		this.goodsRemark = goodsRemark;
	}

}