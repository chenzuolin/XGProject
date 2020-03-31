package com.xinggang.project.form;

import org.apache.struts.action.ActionForm;

/**
 * 作业人员表单，例天车工，装卸工
 * 
 * @author Administrator
 * 
 */
public class WorkingForm extends ActionForm {

	private static final long serialVersionUID = 6195206363501993438L;
	private Integer workingId;// 作业人员编号
	private String workingName;// 作业人员名字
	private Integer workingClassId;// 对应的班组
	private String workingKindOfWork;// 工种（天车工，装卸工）
	private String workingDefinedOne;// 预留字段一
	private String workingDefinedTwo;// 预留字段二
	private String workingRemark;// 备注

	private String className;// 班组名称，查询时

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Integer getWorkingId() {
		return this.workingId;
	}

	public void setWorkingId(Integer workingId) {
		this.workingId = workingId;
	}

	public String getWorkingName() {
		return this.workingName;
	}

	public void setWorkingName(String workingName) {
		this.workingName = workingName;
	}

	public Integer getWorkingClassId() {
		return this.workingClassId;
	}

	public void setWorkingClassId(Integer workingClassId) {
		this.workingClassId = workingClassId;
	}

	public String getWorkingKindOfWork() {
		return this.workingKindOfWork;
	}

	public void setWorkingKindOfWork(String workingKindOfWork) {
		this.workingKindOfWork = workingKindOfWork;
	}

	public String getWorkingDefinedOne() {
		return this.workingDefinedOne;
	}

	public void setWorkingDefinedOne(String workingDefinedOne) {
		this.workingDefinedOne = workingDefinedOne;
	}

	public String getWorkingDefinedTwo() {
		return this.workingDefinedTwo;
	}

	public void setWorkingDefinedTwo(String workingDefinedTwo) {
		this.workingDefinedTwo = workingDefinedTwo;
	}

	public String getWorkingRemark() {
		return this.workingRemark;
	}

	public void setWorkingRemark(String workingRemark) {
		this.workingRemark = workingRemark;
	}

}