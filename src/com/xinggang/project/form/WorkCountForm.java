package com.xinggang.project.form;

import org.apache.struts.action.ActionForm;

//工作累统计form
public class WorkCountForm extends ActionForm {

	private static final long serialVersionUID = -8576640274873018862L;
	private String begin;// 统计的起始日期
	private String finish;// 统计的结束日期
	private String name;// 通过的操作员的名字
	private String zhiwu;// 统计工作量的时候选择的职务

	public String getZhiwu() {
		return zhiwu;
	}

	public void setZhiwu(String zhiwu) {
		this.zhiwu = zhiwu;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
