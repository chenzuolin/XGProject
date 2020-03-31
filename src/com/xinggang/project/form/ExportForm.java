package com.xinggang.project.form;

import org.apache.struts.action.ActionForm;

/**
 * 出库总订单表单
 * 
 * @author Administrator
 * 
 */
public class ExportForm extends ActionForm {

	private static final long serialVersionUID = 2803626509009886905L;
	private String exportId;// 出库总订单编号
	private Integer client;// 对应的客户，客户类
	private String exportClientNumber;// 客户订单号，由客户填入
	private String exportReateTime;// 订单生成日期
	private String exportCarryType;// 运输方式（火车/汽车）
	private String exportWagonNumber;// 车牌号
	private String exportDriverName;// 司机姓名
	private String exportDriverTel;// 司机电话
	private String exportOrderStatus;// 订单状态
	private String exportDefinedOne;// 是否接受超发
	private String exportDefinedTwo;// 有效期
	private String exportRemark;// 是否配送

	private Integer[] goodsId;// 货物，在此处是多选
	private Double[] goodsWeight;// 重量，多个
	private String[] remark;// 备注，多个
	
	private Double xiugaiWeight;// 修改时的重量，当用户点击修改的时候
	
	
	//app手机版所需字段
	private Integer goodsPropertyId;// 货物属性(调用属性ID)
	private Integer goodsCategoryId;// 货物品类(调用品类ID)
	private Integer goodsStandardId;//规格
	private Integer goodsQualityId;// 材质
	private Integer goodsYieldlyId;// 货物产地(调用产地ID)
	private String goodsName;// 货物名称
	private String eseedRemark;// 货物名称
	private Double exportShouldWeights;//应出重量
	
	private String eseedDefinedOne;//单位电话
	
	
	public String getEseedDefinedOne() {
		return eseedDefinedOne;
	}

	public void setEseedDefinedOne(String eseedDefinedOne) {
		this.eseedDefinedOne = eseedDefinedOne;
	}

	public String getEseedRemark() {
		return eseedRemark;
	}

	public void setEseedRemark(String eseedRemark) {
		this.eseedRemark = eseedRemark;
	}

	public Double getExportShouldWeights() {
		return exportShouldWeights;
	}

	public void setExportShouldWeights(Double exportShouldWeights) {
		this.exportShouldWeights = exportShouldWeights;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Integer getGoodsPropertyId() {
		return goodsPropertyId;
	}

	public void setGoodsPropertyId(Integer goodsPropertyId) {
		this.goodsPropertyId = goodsPropertyId;
	}

	public Integer getGoodsCategoryId() {
		return goodsCategoryId;
	}

	public void setGoodsCategoryId(Integer goodsCategoryId) {
		this.goodsCategoryId = goodsCategoryId;
	}

	public Integer getGoodsStandardId() {
		return goodsStandardId;
	}

	public void setGoodsStandardId(Integer goodsStandardId) {
		this.goodsStandardId = goodsStandardId;
	}

	public Integer getGoodsQualityId() {
		return goodsQualityId;
	}

	public void setGoodsQualityId(Integer goodsQualityId) {
		this.goodsQualityId = goodsQualityId;
	}

	public Integer getGoodsYieldlyId() {
		return goodsYieldlyId;
	}

	public void setGoodsYieldlyId(Integer goodsYieldlyId) {
		this.goodsYieldlyId = goodsYieldlyId;
	}


	public Double getXiugaiWeight() {
		return xiugaiWeight;
	}

	public void setXiugaiWeight(Double xiugaiWeight) {
		this.xiugaiWeight = xiugaiWeight;
	}

	public String[] getRemark() {
		return remark;
	}

	public void setRemark(String[] remark) {
		this.remark = remark;
	}

	public Integer[] getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer[] goodsId) {
		this.goodsId = goodsId;
	}

	public Double[] getGoodsWeight() {
		return goodsWeight;
	}

	public void setGoodsWeight(Double[] goodsWeight) {
		this.goodsWeight = goodsWeight;
	}

	public String getExportId() {
		return this.exportId;
	}

	public void setExportId(String exportId) {
		this.exportId = exportId;
	}

	public Integer getClient() {
		return this.client;
	}

	public void setClient(Integer client) {
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

}