package com.xinggang.project.entity;

/**
 * 作业人员类，例天车工，装卸工
 * 
 * @author Administrator
 * 
 */
public class Working implements java.io.Serializable {

	private static final long serialVersionUID = 6195206363501993438L;
	private Integer workingId;// 作业人员编号
	private String workingName;// 作业人员名字
	private ClassT workingClassId;// 对应的班组
	private String workingKindOfWork;// 工种（天车工，装卸工）
	private String workingDefinedOne;// 联系电话
	private String workingDefinedTwo;// 预留字段二
	private String workingRemark;// 备注

	public Working() {
	}

	public Working(String workingName, ClassT workingClassId,
			String workingKindOfWork, String workingDefinedOne,
			String workingDefinedTwo, String workingRemark) {
		this.workingName = workingName;
		this.workingClassId = workingClassId;
		this.workingKindOfWork = workingKindOfWork;
		this.workingDefinedOne = workingDefinedOne;
		this.workingDefinedTwo = workingDefinedTwo;
		this.workingRemark = workingRemark;
	}

	// 编号，姓名，班组，工种，预留字段一，预留字段二，备注
	public Working(Integer workingId, String workingName,
			ClassT workingClassId, String workingKindOfWork,
			String workingDefinedOne, String workingDefinedTwo,
			String workingRemark) {
		this.workingId = workingId;
		this.workingName = workingName;
		this.workingClassId = workingClassId;
		this.workingKindOfWork = workingKindOfWork;
		this.workingDefinedOne = workingDefinedOne;
		this.workingDefinedTwo = workingDefinedTwo;
		this.workingRemark = workingRemark;
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

	public ClassT getWorkingClassId() {
		return this.workingClassId;
	}

	public void setWorkingClassId(ClassT workingClassId) {
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