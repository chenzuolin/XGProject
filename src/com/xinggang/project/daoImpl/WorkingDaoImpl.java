package com.xinggang.project.daoImpl;

import java.util.List;

import com.xinggang.project.base.BaseDaoImpl;
import com.xinggang.project.dao.WorkingDao;
import com.xinggang.project.entity.Working;

/**
 * 作业人员Dao实现类
 */
public class WorkingDaoImpl extends BaseDaoImpl implements WorkingDao {
	// 增加
	public boolean save(Working working) {
		return super.BaseSave(working);
	}

	// 删除
	public boolean delete(Working working) {
		return super.BaseDelete(working);
	}

	// 修改
	public boolean update(Working working) {
		return super.BaseUpdate(working);
	}

	// 通过id查询
	public Working getWorkingId(Integer id) {
		String hql = "from Working where workingId=" + id;
		return (Working) super.findById(hql);
	}

	// 查询全部
	@SuppressWarnings("unchecked")
	public List<Working> getAll() {
		String hql = "from Working";
		return (List<Working>) super.executeQuery(hql, null);
	}

	// 分页查询
	@SuppressWarnings("unchecked")
	public List<Working> getWorkingByPage(String name, int pageNow, int rowSize) {
		String hql = "from Working where workingName='" + name
				+ "' order by workingId desc";
		return (List<Working>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 查询数据总行数
	public int getWorkingByCount(String name) {
		String hql = "select count(*) from Working where workingName='" + name
				+ "'";
		return super.executeQueryRowCount(hql, null);
	}

	// 通过工种查询
	@SuppressWarnings("unchecked")
	public List<Working> getWorkingKind(String kind) {
		String hql = "from Working where workingKindOfWork= '" + kind + "'";
		return (List<Working>) super.executeQuery(hql, null);
	}

	// 通过班组查询
	@SuppressWarnings("unchecked")
	public List<Working> getWorkingClass(Integer classId) {
		String hql = "from Working where workingClassId=" + classId;
		return (List<Working>) super.executeQuery(hql, null);
	}

	// 以分页的方式查询作业人员
	@SuppressWarnings("unchecked")
	public List<Working> getWorkingAllByPage(String name, String className,
			String gongzhong, int pageNow, int rowSize) {

		// 编号，姓名，班组，工种，预留字段一，预留字段二，备注
		String hql = "from Working w where w.workingName like ('%" + name
				+ "%') and w.workingKindOfWork like ('%" + gongzhong
				+ "%') order by w.workingId desc";
		return (List<Working>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 查询作业的总页数
	public int getWorkingAllByPageCount(String name, String className,
			String gongzhong, int rowSize) {
		String hql = "select count(*) from Working w where w.workingName like ('%"
				+ name
				+ "%') and w.workingKindOfWork like ('%"
				+ gongzhong
				+ "%')";
		return super.queryPageCount(hql, null, rowSize);
	}

	// 通过名字查询
	@SuppressWarnings("unchecked")
	public List<Working> getWorkingName(String name) {
		String hql = "from Working where workingName like '%" + name + "%'";
		return (List<Working>) super.executeQuery(hql, null);
	}

	// 通过职务、起始日期、结束日期统计入库的工作量
	@SuppressWarnings("unchecked")
	public Double RuKuWorkWeight(String begin, String finish, String zhiwu) {
		String hql = "select sum(ioperateRealityWeight) from InputOperate where ioperateDispatcherTime >='"
				+ begin + "' and ioperateDispatcherTime <= '" + finish + "'";
		if (zhiwu != null) {
			if (zhiwu.equals("1")) {// 调度
				hql += " and interiorUserByIoperateDispatcherId.iuserName like ('%%')";
			} else if (zhiwu.equals("2")) {// 保管
				hql += " and interiorUserByIoperateStoremanId.iuserName like ('%%')";
			} else if (zhiwu.equals("3")) {// 司磅
				hql += " and interiorUserByIoperatePonderationManId.iuserName like ('%%')";
			} else if (zhiwu.equals("4")) {// 审核
				hql += " and interiorUserByIoperateAuditingId.iuserName like ('%%')";
			} else if (zhiwu.equals("5")) {// 收费
				hql += " and interiorUserByIoperateCollectManId.iuserName like ('%%')";
			} else if (zhiwu.equals("6")) {// 天车
				hql += " and ioperateCraneman like ('%%')";
			} else if (zhiwu.equals("7")) {// 装卸
				hql += " and ioperateStevedore like ('%%')";
			} else {
				return 0.0;
			}
		}

		List<Double> iolist = (List<Double>) super.executeQuery(hql, null);
		return iolist == null ? 0.0 : iolist.get(0) == null ? 0.0 : iolist
				.get(0);
	}

	// 通过职务、起始日期、结束日期统计出库的工作量
	@SuppressWarnings("unchecked")
	public Double ChuKuWorkWeight(String begin, String finish, String zhiwu) {
		String hql = "select sum(eoperateRealityWeight) from ExportOperate where eoperateDispatcherTime>='"
				+ begin + "' and eoperateDispatcherTime<='" + finish + "'";
		if (zhiwu != null) {
			if (zhiwu.equals("1")) {// 调度
				hql += " and interiorUserByEoperateDispatcher.iuserName like ('%%')";
			} else if (zhiwu.equals("2")) {// 保管
				hql += " and interiorUserByEoperateStoreman.iuserName like ('%%')";
			} else if (zhiwu.equals("3")) {// 司磅
				hql += " and interiorUserByEoperatePonderationMan.iuserName like ('%%')";
			} else if (zhiwu.equals("4")) {// 审核
				hql += " and interiorUserByEoperateAuditing.iuserName like ('%%')";
			} else if (zhiwu.equals("5")) {// 收费
				hql += " and interiorUserByEoperateCollectMan.iuserName like ('%%')";
			} else if (zhiwu.equals("6")) {// 天车
				hql += " and eoperateCraneman like ('%%')";
			} else if (zhiwu.equals("7")) {// 装卸
				hql += " and eoperateStevedore like ('%%')";
			} else {
				return 0.0;
			}
		}

		List<Double> iolist = (List<Double>) super.executeQuery(hql, null);
		return iolist == null ? 0.0 : iolist.get(0) == null ? 0.0 : iolist
				.get(0);
	}

	// 通过职务、起始日期、结束日期统计过户的工作量
	@SuppressWarnings("unchecked")
	public Double GuoHuWorkWeight(String begin, String finish, String zhiwu) {
		String hql = "select sum(ssseedWeight) from ShiftStockSeed where ssseedAuditingTime>='"
				+ begin + "' and ssseedAuditingTime<='" + finish + "'";
		if (zhiwu != null) {
			if (zhiwu.equals("4")) {// 审核
				hql += " and interiorUserBySsseedAuditing.iuserName like ('%%')";
			} else if (zhiwu.equals("5")) {// 收费
				hql += " and interiorUserBySsseedCollectMan.iuserName like ('%%')";
			} else {
				return 0.00;
			}

			List<Double> iolist = (List<Double>) super.executeQuery(hql, null);
			return iolist == null ? 0.0 : iolist.get(0) == null ? 0.0 : iolist
					.get(0);

		} else {
			return 0.0000;
		}
	}

	// 通过职务、起始日期、结束日期统计挪库的工作量
	@SuppressWarnings("unchecked")
	public Double NuoKuWorkWeight(String begin, String finish, String zhiwu) {
		String hql = "select sum(shiftWeight) from Shift where shiftTime>='"
				+ begin + "' and shiftTime<='" + finish + "'";
		if (zhiwu != null) {
			if (zhiwu.equals("1")) {// 发起人
				hql += " and interiorUserByShiftFounderMember.iuserName like ('%%')";
			} else if (zhiwu.equals("2")) {// 操作员
				hql += " and interiorUserByShiftExecutor.iuserName like ('%%')";
			} else if (zhiwu.equals("6")) {// 天车工
				hql += " and shiftCraneman like ('%%')";
			} else if (zhiwu.equals("7")) {// 装卸工
				hql += " and shiftStevedore like ('%%')";
			} else {
				return 0.0;
			}
		}

		List<Double> iolist = (List<Double>) super.executeQuery(hql, null);
		return iolist == null ? 0.0 : iolist.get(0) == null ? 0.0 : iolist
				.get(0);
	}

	// 通过职务、起始日期、结束日期统计短倒的工作量
	@SuppressWarnings("unchecked")
	public Double DuanDaoWorkWeight(String begin, String finish, String zhiwu) {
		String hql = "select sum(shiftWeight) from DuanDao where shiftTime>='"
				+ begin + "' and shiftTime<='" + finish + "'";
		if (zhiwu != null) {
			if (zhiwu.equals("1")) {// 发起人
				hql += " and interiorUserByShiftFounderMember.iuserName like ('%%')";
			} else if (zhiwu.equals("2")) {// 保管员
				hql += " and interiorUserByShiftExecutor.iuserName like ('%%')";
			} else if (zhiwu.equals("6")) {// 天车工
				hql += " and shiftCraneman like ('%%')";
			} else if (zhiwu.equals("7")) {// 装卸工
				hql += " and shiftStevedore like ('%%')";
			} else if (zhiwu.equals("8")) {// 短倒司机
				hql += " and driverName like ('%%')";
			} else {
				return 0.0;
			}
		}

		List<Double> iolist = (List<Double>) super.executeQuery(hql, null);
		return iolist == null ? 0.0 : iolist.get(0) == null ? 0.0 : iolist
				.get(0);
	}

}
