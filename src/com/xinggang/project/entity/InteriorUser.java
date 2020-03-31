package com.xinggang.project.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 使用系统公司内部人员类
 */

public class InteriorUser implements java.io.Serializable {

	private static final long serialVersionUID = -4477304512297105927L;
	private Integer iuserId;// 内部人员编号
	private Section section;// 对应类部门类
	private InteriorUserDuty interiorUserDuty;// 对应类使用系统的公司内部人员职责类
	private ClassT classT;// 对应类班组类
	private String iuserLoginName;// 用户登录名
	private String iuserPassword;// 用户登录名密码
	private String iuserCreateTime;// 注册时间
	private String iuserTel;// 联系电话
	private String iuserName;// 用户姓名 
	private String iuserSex;// 用户性别
	private Integer iuserCease;// 用户是否停用(1否0是)
	private Integer iuserOnline;// 是否在线(1否0是)
	private Integer iuserWork;// 是否作业(1否0是)
	private String iuserDefinedOne;// 自定义字段1
	private String iuserDefinedTwo;// 自定义字段2
	private String iuserRemark;// 备注
	private Set<Object> exportOperatesForEoperateDispatcher = new HashSet<Object>(
			0);// 调度员，对应类出库订单操作类
	private Set<Object> checksesForCheckAuditing = new HashSet<Object>(0);// 盘点审核人对应盘点类
	private Set<Object> shiftStockSeedsForSsseedCollectMan = new HashSet<Object>(
			0);// 对应类过户子订单表
	private Set<Object> checksesForCheckFounderMember = new HashSet<Object>(0);// 盘点发起人，对应盘点类
	private Set<Object> shiftsForShiftFounderMember = new HashSet<Object>(0);// 挪库发起人，对应挪库类
	private Set<Object> checksesForCheckHuman = new HashSet<Object>(0);// 盘点人，对应盘点类
	private Set<Object> exportOperatesForEoperateAuditing = new HashSet<Object>(
			0);// 审核人，对应出库订单操作类
	private Set<Object> exportOperatesForEoperateCollectMan = new HashSet<Object>(
			0);// 收费人，对应出库订单操作类
	private Set<Object> exportOperatesForEoperateStoreman = new HashSet<Object>(
			0);// 现场保管员对应出库订单操作类
	private Set<Object> shiftsForShiftExecutor = new HashSet<Object>(0);// 挪库执行人，对应挪库类
	private Set<Object> exportOperatesForEoperatePonderationMan = new HashSet<Object>(
			0);// 司磅员对应出库操作类
	private Set<Object> inputOperatesForIoperateStoremanId = new HashSet<Object>(
			0);// 入库现场保管员对应入库订单操作类
	private Set<Object> shiftStockSeedsForSsseedAuditing = new HashSet<Object>(
			0);// 子订单审核人对应过户子订单类
	private Set<Object> inputOperatesForIoperateDispatcherId = new HashSet<Object>(
			0);// 入库调度员对应入库操作类
	private Set<Object> inputOperatesForIoperatePonderationManId = new HashSet<Object>(
			0);// 司磅员对应入库订单操作类
	private Set<Object> inputOperatesForIoperateCollectManId = new HashSet<Object>(
			0);// 收费人对应入库订单操作类
	private Set<Object> inputOperatesForIoperateAuditingId = new HashSet<Object>(
			0);// 审核人对应入库订单操作类
	private Set<Object> interiorUser = new HashSet<Object>(0);

	// 滞纳金中的收费人
	private Set<Object> shoufeiren = new HashSet<Object>(0);

	public InteriorUser() {
	}

	public InteriorUser(String iuserLoginName, String iuserPassword) {
		this.iuserLoginName = iuserLoginName;
		this.iuserPassword = iuserPassword;
	}

	public InteriorUser(Section section, InteriorUserDuty interiorUserDuty,
			ClassT classT, String iuserLoginName, String iuserPassword,
			String iuserCreateTime, String iuserTel, String iuserName,
			String iuserSex, Integer iuserCease, Integer iuserOnline,
			Integer iuserWork, String iuserDefinedOne, String iuserDefinedTwo,
			String iuserRemark,
			Set<Object> exportOperatesForEoperateDispatcher,
			Set<Object> checksesForCheckAuditing,
			Set<Object> shiftStockSeedsForSsseedCollectMan,
			Set<Object> checksesForCheckFounderMember,
			Set<Object> shiftsForShiftFounderMember,
			Set<Object> checksesForCheckHuman,
			Set<Object> exportOperatesForEoperateAuditing,
			Set<Object> exportOperatesForEoperateCollectMan,
			Set<Object> exportOperatesForEoperateStoreman,
			Set<Object> shiftsForShiftExecutor,
			Set<Object> exportOperatesForEoperatePonderationMan,
			Set<Object> inputOperatesForIoperateStoremanId,
			Set<Object> shiftStockSeedsForSsseedAuditing,
			Set<Object> inputOperatesForIoperateDispatcherId,
			Set<Object> inputOperatesForIoperatePonderationManId,
			Set<Object> inputOperatesForIoperateCollectManId,
			Set<Object> inputOperatesForIoperateAuditingId,
			Set<Object> interiorUser, Set<Object> shoufeiren) {
		this.shoufeiren = shoufeiren;
		this.interiorUser = interiorUser;
		this.section = section;
		this.interiorUserDuty = interiorUserDuty;
		this.classT = classT;
		this.iuserLoginName = iuserLoginName;
		this.iuserPassword = iuserPassword;
		this.iuserCreateTime = iuserCreateTime;
		this.iuserTel = iuserTel;
		this.iuserName = iuserName;
		this.iuserSex = iuserSex;
		this.iuserCease = iuserCease;
		this.iuserOnline = iuserOnline;
		this.iuserWork = iuserWork;
		this.iuserDefinedOne = iuserDefinedOne;
		this.iuserDefinedTwo = iuserDefinedTwo;
		this.iuserRemark = iuserRemark;
		this.exportOperatesForEoperateDispatcher = exportOperatesForEoperateDispatcher;
		this.checksesForCheckAuditing = checksesForCheckAuditing;
		this.shiftStockSeedsForSsseedCollectMan = shiftStockSeedsForSsseedCollectMan;
		this.checksesForCheckFounderMember = checksesForCheckFounderMember;
		this.shiftsForShiftFounderMember = shiftsForShiftFounderMember;
		this.checksesForCheckHuman = checksesForCheckHuman;
		this.exportOperatesForEoperateAuditing = exportOperatesForEoperateAuditing;
		this.exportOperatesForEoperateCollectMan = exportOperatesForEoperateCollectMan;
		this.exportOperatesForEoperateStoreman = exportOperatesForEoperateStoreman;
		this.shiftsForShiftExecutor = shiftsForShiftExecutor;
		this.exportOperatesForEoperatePonderationMan = exportOperatesForEoperatePonderationMan;
		this.inputOperatesForIoperateStoremanId = inputOperatesForIoperateStoremanId;
		this.shiftStockSeedsForSsseedAuditing = shiftStockSeedsForSsseedAuditing;
		this.inputOperatesForIoperateDispatcherId = inputOperatesForIoperateDispatcherId;
		this.inputOperatesForIoperatePonderationManId = inputOperatesForIoperatePonderationManId;
		this.inputOperatesForIoperateCollectManId = inputOperatesForIoperateCollectManId;
		this.inputOperatesForIoperateAuditingId = inputOperatesForIoperateAuditingId;
	}

	public Set<Object> getShoufeiren() {
		return shoufeiren;
	}

	public void setShoufeiren(Set<Object> shoufeiren) {
		this.shoufeiren = shoufeiren;
	}

	public Integer getIuserId() {
		return this.iuserId;
	}

	public Set<Object> getInteriorUser() {
		return interiorUser;
	}

	public void setInteriorUser(Set<Object> interiorUser) {
		this.interiorUser = interiorUser;
	}

	public void setIuserId(Integer iuserId) {
		this.iuserId = iuserId;
	}

	public Section getSection() {
		return this.section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public InteriorUserDuty getInteriorUserDuty() {
		return this.interiorUserDuty;
	}

	public void setInteriorUserDuty(InteriorUserDuty interiorUserDuty) {
		this.interiorUserDuty = interiorUserDuty;
	}

	public ClassT getClassT() {
		return this.classT;
	}

	public void setClassT(ClassT classT) {
		this.classT = classT;
	}

	public String getIuserLoginName() {
		return this.iuserLoginName;
	}

	public void setIuserLoginName(String iuserLoginName) {
		this.iuserLoginName = iuserLoginName;
	}

	public String getIuserPassword() {
		return this.iuserPassword;
	}

	public void setIuserPassword(String iuserPassword) {
		this.iuserPassword = iuserPassword;
	}

	public String getIuserCreateTime() {
		return this.iuserCreateTime;
	}

	public void setIuserCreateTime(String iuserCreateTime) {
		this.iuserCreateTime = iuserCreateTime;
	}

	public String getIuserTel() {
		return this.iuserTel;
	}

	public void setIuserTel(String iuserTel) {
		this.iuserTel = iuserTel;
	}

	public String getIuserName() {
		return this.iuserName;
	}

	public void setIuserName(String iuserName) {
		this.iuserName = iuserName;
	}

	public String getIuserSex() {
		return this.iuserSex;
	}

	public void setIuserSex(String iuserSex) {
		this.iuserSex = iuserSex;
	}

	public Integer getIuserCease() {
		return this.iuserCease;
	}

	public void setIuserCease(Integer iuserCease) {
		this.iuserCease = iuserCease;
	}

	public Integer getIuserOnline() {
		return this.iuserOnline;
	}

	public void setIuserOnline(Integer iuserOnline) {
		this.iuserOnline = iuserOnline;
	}

	public Integer getIuserWork() {
		return this.iuserWork;
	}

	public void setIuserWork(Integer iuserWork) {
		this.iuserWork = iuserWork;
	}

	public String getIuserDefinedOne() {
		return this.iuserDefinedOne;
	}

	public void setIuserDefinedOne(String iuserDefinedOne) {
		this.iuserDefinedOne = iuserDefinedOne;
	}

	public String getIuserDefinedTwo() {
		return this.iuserDefinedTwo;
	}

	public void setIuserDefinedTwo(String iuserDefinedTwo) {
		this.iuserDefinedTwo = iuserDefinedTwo;
	}

	public String getIuserRemark() {
		return this.iuserRemark;
	}

	public void setIuserRemark(String iuserRemark) {
		this.iuserRemark = iuserRemark;
	}

	public Set<Object> getExportOperatesForEoperateDispatcher() {
		return this.exportOperatesForEoperateDispatcher;
	}

	public void setExportOperatesForEoperateDispatcher(
			Set<Object> exportOperatesForEoperateDispatcher) {
		this.exportOperatesForEoperateDispatcher = exportOperatesForEoperateDispatcher;
	}

	public Set<Object> getChecksesForCheckAuditing() {
		return this.checksesForCheckAuditing;
	}

	public void setChecksesForCheckAuditing(Set<Object> checksesForCheckAuditing) {
		this.checksesForCheckAuditing = checksesForCheckAuditing;
	}

	public Set<Object> getShiftStockSeedsForSsseedCollectMan() {
		return this.shiftStockSeedsForSsseedCollectMan;
	}

	public void setShiftStockSeedsForSsseedCollectMan(
			Set<Object> shiftStockSeedsForSsseedCollectMan) {
		this.shiftStockSeedsForSsseedCollectMan = shiftStockSeedsForSsseedCollectMan;
	}

	public Set<Object> getChecksesForCheckFounderMember() {
		return this.checksesForCheckFounderMember;
	}

	public void setChecksesForCheckFounderMember(
			Set<Object> checksesForCheckFounderMember) {
		this.checksesForCheckFounderMember = checksesForCheckFounderMember;
	}

	public Set<Object> getShiftsForShiftFounderMember() {
		return this.shiftsForShiftFounderMember;
	}

	public void setShiftsForShiftFounderMember(
			Set<Object> shiftsForShiftFounderMember) {
		this.shiftsForShiftFounderMember = shiftsForShiftFounderMember;
	}

	public Set<Object> getChecksesForCheckHuman() {
		return this.checksesForCheckHuman;
	}

	public void setChecksesForCheckHuman(Set<Object> checksesForCheckHuman) {
		this.checksesForCheckHuman = checksesForCheckHuman;
	}

	public Set<Object> getExportOperatesForEoperateAuditing() {
		return this.exportOperatesForEoperateAuditing;
	}

	public void setExportOperatesForEoperateAuditing(
			Set<Object> exportOperatesForEoperateAuditing) {
		this.exportOperatesForEoperateAuditing = exportOperatesForEoperateAuditing;
	}

	public Set<Object> getExportOperatesForEoperateCollectMan() {
		return this.exportOperatesForEoperateCollectMan;
	}

	public void setExportOperatesForEoperateCollectMan(
			Set<Object> exportOperatesForEoperateCollectMan) {
		this.exportOperatesForEoperateCollectMan = exportOperatesForEoperateCollectMan;
	}

	public Set<Object> getExportOperatesForEoperateStoreman() {
		return this.exportOperatesForEoperateStoreman;
	}

	public void setExportOperatesForEoperateStoreman(
			Set<Object> exportOperatesForEoperateStoreman) {
		this.exportOperatesForEoperateStoreman = exportOperatesForEoperateStoreman;
	}

	public Set<Object> getShiftsForShiftExecutor() {
		return this.shiftsForShiftExecutor;
	}

	public void setShiftsForShiftExecutor(Set<Object> shiftsForShiftExecutor) {
		this.shiftsForShiftExecutor = shiftsForShiftExecutor;
	}

	public Set<Object> getExportOperatesForEoperatePonderationMan() {
		return this.exportOperatesForEoperatePonderationMan;
	}

	public void setExportOperatesForEoperatePonderationMan(
			Set<Object> exportOperatesForEoperatePonderationMan) {
		this.exportOperatesForEoperatePonderationMan = exportOperatesForEoperatePonderationMan;
	}

	public Set<Object> getInputOperatesForIoperateStoremanId() {
		return this.inputOperatesForIoperateStoremanId;
	}

	public void setInputOperatesForIoperateStoremanId(
			Set<Object> inputOperatesForIoperateStoremanId) {
		this.inputOperatesForIoperateStoremanId = inputOperatesForIoperateStoremanId;
	}

	public Set<Object> getShiftStockSeedsForSsseedAuditing() {
		return this.shiftStockSeedsForSsseedAuditing;
	}

	public void setShiftStockSeedsForSsseedAuditing(
			Set<Object> shiftStockSeedsForSsseedAuditing) {
		this.shiftStockSeedsForSsseedAuditing = shiftStockSeedsForSsseedAuditing;
	}

	public Set<Object> getInputOperatesForIoperateDispatcherId() {
		return this.inputOperatesForIoperateDispatcherId;
	}

	public void setInputOperatesForIoperateDispatcherId(
			Set<Object> inputOperatesForIoperateDispatcherId) {
		this.inputOperatesForIoperateDispatcherId = inputOperatesForIoperateDispatcherId;
	}

	public Set<Object> getInputOperatesForIoperatePonderationManId() {
		return this.inputOperatesForIoperatePonderationManId;
	}

	public void setInputOperatesForIoperatePonderationManId(
			Set<Object> inputOperatesForIoperatePonderationManId) {
		this.inputOperatesForIoperatePonderationManId = inputOperatesForIoperatePonderationManId;
	}

	public Set<Object> getInputOperatesForIoperateCollectManId() {
		return this.inputOperatesForIoperateCollectManId;
	}

	public void setInputOperatesForIoperateCollectManId(
			Set<Object> inputOperatesForIoperateCollectManId) {
		this.inputOperatesForIoperateCollectManId = inputOperatesForIoperateCollectManId;
	}

	public Set<Object> getInputOperatesForIoperateAuditingId() {
		return this.inputOperatesForIoperateAuditingId;
	}

	public void setInputOperatesForIoperateAuditingId(
			Set<Object> inputOperatesForIoperateAuditingId) {
		this.inputOperatesForIoperateAuditingId = inputOperatesForIoperateAuditingId;
	}

}