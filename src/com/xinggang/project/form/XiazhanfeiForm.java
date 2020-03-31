package com.xinggang.project.form;

import org.apache.struts.action.ActionForm;

public class XiazhanfeiForm extends ActionForm {

	private static final long serialVersionUID = 275752331340175518L;
	private Integer xzid;// 编号
	private Integer clientByXzzhuanchuclient;// 转出客户
	private Integer clientByXzzhuanruclient;// 转入客户
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
	private String xadefinedtwo;// 订单号
	private String xzdefinedthree;// 业务类型
	private String xadefinedfour;// 实收费用
	private String xadefinedfive;// 操作订单编号

	// 查询的条件
	private String begin; // 起始日期
	private String finish; // 结束日期
	private String clientName;// 客户单位名称
	private String jiesuan;// 结算方式

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

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getJiesuan() {
		return jiesuan;
	}

	public void setJiesuan(String jiesuan) {
		this.jiesuan = jiesuan;
	}

	public Integer getXzid() {
		return xzid;
	}

	public void setXzid(Integer xzid) {
		this.xzid = xzid;
	}

	public Integer getClientByXzzhuanchuclient() {
		return clientByXzzhuanchuclient;
	}

	public void setClientByXzzhuanchuclient(Integer clientByXzzhuanchuclient) {
		this.clientByXzzhuanchuclient = clientByXzzhuanchuclient;
	}

	public Integer getClientByXzzhuanruclient() {
		return clientByXzzhuanruclient;
	}

	public void setClientByXzzhuanruclient(Integer clientByXzzhuanruclient) {
		this.clientByXzzhuanruclient = clientByXzzhuanruclient;
	}

	public Double getXzweight() {
		return xzweight;
	}

	public void setXzweight(Double xzweight) {
		this.xzweight = xzweight;
	}

	public Double getXzdanjia() {
		return xzdanjia;
	}

	public void setXzdanjia(Double xzdanjia) {
		this.xzdanjia = xzdanjia;
	}

	public Double getXzcount() {
		return xzcount;
	}

	public void setXzcount(Double xzcount) {
		this.xzcount = xzcount;
	}

	public String getXzzhifu() {
		return xzzhifu;
	}

	public void setXzzhifu(String xzzhifu) {
		this.xzzhifu = xzzhifu;
	}

	public String getXzpiaoju() {
		return xzpiaoju;
	}

	public void setXzpiaoju(String xzpiaoju) {
		this.xzpiaoju = xzpiaoju;
	}

	public String getXzshoufeiren() {
		return xzshoufeiren;
	}

	public void setXzshoufeiren(String xzshoufeiren) {
		this.xzshoufeiren = xzshoufeiren;
	}

	public String getXzshoufeitime() {
		return xzshoufeitime;
	}

	public void setXzshoufeitime(String xzshoufeitime) {
		this.xzshoufeitime = xzshoufeitime;
	}

	public String getXzzhuangtai() {
		return xzzhuangtai;
	}

	public void setXzzhuangtai(String xzzhuangtai) {
		this.xzzhuangtai = xzzhuangtai;
	}

	public String getXzremark() {
		return xzremark;
	}

	public void setXzremark(String xzremark) {
		this.xzremark = xzremark;
	}

	public String getXzdefinedone() {
		return xzdefinedone;
	}

	public void setXzdefinedone(String xzdefinedone) {
		this.xzdefinedone = xzdefinedone;
	}

	public String getXadefinedtwo() {
		return xadefinedtwo;
	}

	public void setXadefinedtwo(String xadefinedtwo) {
		this.xadefinedtwo = xadefinedtwo;
	}

	public String getXzdefinedthree() {
		return xzdefinedthree;
	}

	public void setXzdefinedthree(String xzdefinedthree) {
		this.xzdefinedthree = xzdefinedthree;
	}

	public String getXadefinedfour() {
		return xadefinedfour;
	}

	public void setXadefinedfour(String xadefinedfour) {
		this.xadefinedfour = xadefinedfour;
	}

	public String getXadefinedfive() {
		return xadefinedfive;
	}

	public void setXadefinedfive(String xadefinedfive) {
		this.xadefinedfive = xadefinedfive;
	}

}
