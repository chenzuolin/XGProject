package com.xinggang.project.entity;

/**
 * 下站收费记录
 */

public class Xiazhanfei implements java.io.Serializable {

	private static final long serialVersionUID = 8202706316881484561L;
	private Integer xzid;// 编号
	private Client clientByXzzhuanchuclient;// 转出客户
	private Client clientByXzzhuanruclient;// 转入客户
	private Double xzweight;// 相应重量
	private Double xzdanjia;// 下站单价
	private Double xzcount;// 下站费合计
	private String xzzhifu;// 支付方式
	private String xzpiaoju;// 票据类型
	private String xzshoufeiren;// 收费人
	private String xzshoufeitime;// 收费时间
	private String xzzhuangtai;// 状态，判断月结还是现结，
	private String xzremark;// 备注
	private String xzdefinedone;// 结算方式
	private String xadefinedtwo;// 保存订单编号
	private String xzdefinedthree;// 保存业务类型
	private String xadefinedfour;// 实收费用
	private String xadefinedfive;// 操作订单编号

	public Xiazhanfei() {
	}

	public Xiazhanfei(Client clientByXzzhuanchuclient,
			Client clientByXzzhuanruclient, Double xzweight, Double xzdanjia,
			Double xzcount, String xzzhifu, String xzpiaoju,
			String xzshoufeiren, String xzshoufeitime, String xzzhuangtai,
			String xzremark, String xzdefinedone, String xadefinedtwo,
			String xzdefinedthree, String xadefinedfour, String xadefinedfive) {
		this.clientByXzzhuanchuclient = clientByXzzhuanchuclient;
		this.clientByXzzhuanruclient = clientByXzzhuanruclient;
		this.xzweight = xzweight;
		this.xzdanjia = xzdanjia;
		this.xzcount = xzcount;
		this.xzzhifu = xzzhifu;
		this.xzpiaoju = xzpiaoju;
		this.xzshoufeiren = xzshoufeiren;
		this.xzshoufeitime = xzshoufeitime;
		this.xzzhuangtai = xzzhuangtai;
		this.xzremark = xzremark;
		this.xzdefinedone = xzdefinedone;
		this.xadefinedtwo = xadefinedtwo;
		this.xzdefinedthree = xzdefinedthree;
		this.xadefinedfour = xadefinedfour;
		this.xadefinedfive = xadefinedfive;
	}

	public Integer getXzid() {
		return this.xzid;
	}

	public void setXzid(Integer xzid) {
		this.xzid = xzid;
	}

	public Client getClientByXzzhuanchuclient() {
		return this.clientByXzzhuanchuclient;
	}

	public void setClientByXzzhuanchuclient(Client clientByXzzhuanchuclient) {
		this.clientByXzzhuanchuclient = clientByXzzhuanchuclient;
	}

	public Client getClientByXzzhuanruclient() {
		return this.clientByXzzhuanruclient;
	}

	public void setClientByXzzhuanruclient(Client clientByXzzhuanruclient) {
		this.clientByXzzhuanruclient = clientByXzzhuanruclient;
	}

	public Double getXzweight() {
		return this.xzweight;
	}

	public void setXzweight(Double xzweight) {
		this.xzweight = xzweight;
	}

	public Double getXzdanjia() {
		return this.xzdanjia;
	}

	public void setXzdanjia(Double xzdanjia) {
		this.xzdanjia = xzdanjia;
	}

	public Double getXzcount() {
		return this.xzcount;
	}

	public void setXzcount(Double xzcount) {
		this.xzcount = xzcount;
	}

	public String getXzzhifu() {
		return this.xzzhifu;
	}

	public void setXzzhifu(String xzzhifu) {
		this.xzzhifu = xzzhifu;
	}

	public String getXzpiaoju() {
		return this.xzpiaoju;
	}

	public void setXzpiaoju(String xzpiaoju) {
		this.xzpiaoju = xzpiaoju;
	}

	public String getXzshoufeiren() {
		return this.xzshoufeiren;
	}

	public void setXzshoufeiren(String xzshoufeiren) {
		this.xzshoufeiren = xzshoufeiren;
	}

	public String getXzshoufeitime() {
		return this.xzshoufeitime;
	}

	public void setXzshoufeitime(String xzshoufeitime) {
		this.xzshoufeitime = xzshoufeitime;
	}

	public String getXzzhuangtai() {
		return this.xzzhuangtai;
	}

	public void setXzzhuangtai(String xzzhuangtai) {
		this.xzzhuangtai = xzzhuangtai;
	}

	public String getXzremark() {
		return this.xzremark;
	}

	public void setXzremark(String xzremark) {
		this.xzremark = xzremark;
	}

	public String getXzdefinedone() {
		return this.xzdefinedone;
	}

	public void setXzdefinedone(String xzdefinedone) {
		this.xzdefinedone = xzdefinedone;
	}

	public String getXadefinedtwo() {
		return this.xadefinedtwo;
	}

	public void setXadefinedtwo(String xadefinedtwo) {
		this.xadefinedtwo = xadefinedtwo;
	}

	public String getXzdefinedthree() {
		return this.xzdefinedthree;
	}

	public void setXzdefinedthree(String xzdefinedthree) {
		this.xzdefinedthree = xzdefinedthree;
	}

	public String getXadefinedfour() {
		return this.xadefinedfour;
	}

	public void setXadefinedfour(String xadefinedfour) {
		this.xadefinedfour = xadefinedfour;
	}

	public String getXadefinedfive() {
		return this.xadefinedfive;
	}

	public void setXadefinedfive(String xadefinedfive) {
		this.xadefinedfive = xadefinedfive;
	}

}