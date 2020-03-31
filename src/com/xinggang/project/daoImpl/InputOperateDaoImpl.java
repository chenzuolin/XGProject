package com.xinggang.project.daoImpl;

import java.util.List;
import com.xinggang.project.base.BaseDaoImpl;
import com.xinggang.project.dao.InputOperateDao;
import com.xinggang.project.entity.InputOperate;

/**
 * 入库订单操作Dao实现
 * 
 * @author Administrator
 * 
 */
public class InputOperateDaoImpl extends BaseDaoImpl implements InputOperateDao {
	// 增
	public boolean save(InputOperate inputOperate) {
		return super.BaseSave(inputOperate);
	}

	// 删除
	public boolean delete(InputOperate inputOperate) {
		return super.BaseDelete(inputOperate);
	}

	// 修改
	public boolean update(InputOperate inputOperate) {
		return super.BaseUpdate(inputOperate);
	}

	// 通过id查询
	public InputOperate getInputOperateId(String id) {
		return (InputOperate) super.findById(InputOperate.class, id);
	}

	// 通过子订单id查询
	@SuppressWarnings("unchecked")
	public List<InputOperate> getInputSeedId(String id) {
		String hql = "from InputOperate where inputSeed.iseedId='" + id
				+ "' order by ioperateCollectTime";
		return (List<InputOperate>) super.executeQuery(hql, null);
	}

	// 查询全部
	@SuppressWarnings("unchecked")
	public List<InputOperate> getAll() {
		String hql = "from InputOperate";
		return (List<InputOperate>) super.executeQuery(hql, null);
	}

	// 查询全部完成的操作订单,改变子订单的状态
	@SuppressWarnings("unchecked")
	public List<InputOperate> getAllFinallyDingdan(String zongId) {
		String hql = "from InputOperate where inputSeed.iseedId='" + zongId
				+ "' and ioperateDefinedTwo!='订单作废'";
		return (List<InputOperate>) super.executeQuery(hql, null);
	};

	// 通过全部分页查询
	@SuppressWarnings("unchecked")
	public List<InputOperate> getAllByPage(int pageNow, int rowSize) {
		String hql = "from InputOperate order by ioperateDispatcherTime desc";
		return (List<InputOperate>) super.executeQueryByPage(hql, null,
				pageNow, rowSize);
	}

	// 查询全部数据的总行数
	public int getAllByCount() {
		String hql = "select count(*) from InputOperate order by ioperateDispatcherTime desc";
		return super.executeQueryRowCount(hql, null);
	}

	// 通过库位查询
	@SuppressWarnings("unchecked")
	public List<InputOperate> getKuwei(Integer kuweiId) {
		String hql = "from InputOperate where tarehouse=" + kuweiId
				+ " order by ioperateDispatcherTime desc";
		return (List<InputOperate>) super.executeQuery(hql, null);
	}

	// 通过使用系统的内部人员查询，查询审核未通过的订单
	@SuppressWarnings("unchecked")
	// 保管员查询准备入库的订单
	public List<InputOperate> getNeibu(Integer neibuId) {
		String hql = "from InputOperate where interiorUserByIoperateDispatcherId="
				+ neibuId
				+ " or interiorUserByIoperateStoremanId = "
				+ neibuId
				+ "or interiorUserByIoperatePonderationManId="
				+ neibuId
				+ " or interiorUserByIoperateAuditingId="
				+ neibuId
				+ " or interiorUserByIoperateCollectManId="
				+ neibuId
				+ "or ioperateAuditingTrue='未通过'  order by ioperateDispatcherTime desc";
		return (List<InputOperate>) super.executeQuery(hql, null);
	}

	// 通过保管查询
	@SuppressWarnings("unchecked")
	public List<InputOperate> getBaoguan(Integer baoguanId) {
		String hql = "from InputOperate where interiorUserByIoperateStoremanId="
				+ baoguanId
				+ "  and ioperateDefinedTwo='准备入库' order by ioperateDispatcherTime desc";
		return (List<InputOperate>) super.executeQuery(hql, null);
	};

	// 通过工作人员查询，天车工，装卸工
	@SuppressWarnings("unchecked")
	public List<InputOperate> getWorkingTC(String working) {
		String hql = "from InputOperate where ioperateCraneman like ('%"
				+ working + "%') or ioperateStevedore like ('%" + working
				+ "%') order by ioperateDispatcherTime desc";
		return (List<InputOperate>) super.executeQuery(hql, null);
	}

	// 通过审核状态查询
	@SuppressWarnings("unchecked")
	public List<InputOperate> getZhuangtai(String zhuangtai) {
		String hql = "from InputOperate where ioperateAuditingTrue='"
				+ zhuangtai + "'";
		return (List<InputOperate>) super.executeQuery(hql, null);
	}

	// 通过天车工查询
	@SuppressWarnings("unchecked")
	public List<InputOperate> getWorkingTC(String begin, String finish,
			String working) {
		String hql = "from InputOperate where ioperateCraneman like ('%"
				+ working + "%') and ioperateScreateTime > '" + begin
				+ "' and ioperateScreateTime <= '" + finish + "'";
		return (List<InputOperate>) super.executeQuery(hql, null);
	}

	// 通过装卸工查询
	@SuppressWarnings("unchecked")
	public List<InputOperate> getWorkingZX(String begin, String finish,
			String working) {
		String hql = "from InputOperate where ioperateStevedore like ('%"
				+ working + "%') and ioperateScreateTime >= '" + begin
				+ "' and ioperateScreateTime <= '" + finish + "'";
		return (List<InputOperate>) super.executeQuery(hql, null);
	}

	// 通过保管员查询
	@SuppressWarnings("unchecked")
	public List<InputOperate> getWorkingBG(String begin, String finish,
			String working) {
		String hql = "from InputOperate where interiorUserByIoperateStoremanId.iuserLoginName like "
				+ "('%"
				+ working
				+ "%') and ioperateScreateTime >= '"
				+ begin
				+ "' and ioperateScreateTime <= '" + finish + "'";
		return (List<InputOperate>) super.executeQuery(hql, null);
	}

	// 通过调度员查询
	@SuppressWarnings("unchecked")
	public List<InputOperate> getWorkingDU(String begin, String finish,
			String working) {
		String hql = "from InputOperate where interiorUserByIoperateDispatcherId.iuserLoginName like "
				+ "('%"
				+ working
				+ "%') and ioperateScreateTime >= '"
				+ begin
				+ "' and ioperateScreateTime <= '" + finish + "'";
		return (List<InputOperate>) super.executeQuery(hql, null);
	}

	// 通过审核人员查询
	@SuppressWarnings("unchecked")
	public List<InputOperate> getWorkingSH(String begin, String finish,
			String working) {
		String hql = "from InputOperate where interiorUserByIoperateAuditingId.iuserLoginName like "
				+ "('%"
				+ working
				+ "%') and ioperateScreateTime >= '"
				+ begin
				+ "' and ioperateScreateTime <= '" + finish + "'";
		return (List<InputOperate>) super.executeQuery(hql, null);
	}

	// 通过收费人员查询
	@SuppressWarnings("unchecked")
	public List<InputOperate> getWorkingSF(String begin, String finish,
			String working) {
		String hql = "from InputOperate where interiorUserByIoperateCollectManId.iuserLoginName like "
				+ "('%"
				+ working
				+ "%') and ioperateScreateTime >= '"
				+ begin
				+ "' and ioperateScreateTime <= '" + finish + "'";
		return (List<InputOperate>) super.executeQuery(hql, null);
	}

	// 通过司磅人员查询
	@SuppressWarnings("unchecked")
	public List<InputOperate> getWorkingSB(String begin, String finish,
			String working) {
		String hql = "from InputOperate where interiorUserByIoperatePonderationManId.iuserLoginName like "
				+ "('%"
				+ working
				+ "%') and ioperateScreateTime >= '"
				+ begin
				+ "' and ioperateScreateTime <= '" + finish + "'";
		return (List<InputOperate>) super.executeQuery(hql, null);
	}

	// 根据客户姓名、订单生产时间、货主、车号、司机姓名、运输方式、订单是否作废、货物名称、订单状态
	// 库位、调度员、保管员、天车工、装卸工、司磅员、审核人员、审核时间、收费员、收费时间得到所有,根据收费时间排序
	@SuppressWarnings("unchecked")
	public List<InputOperate> getInputOperateByPage2(String dingDanHao,
			String clientName, String inputTime, String chehao,
			String sijiName, String yunshuStyle, String dingdanYesNo,
			String huowuName, String dingdanStatus, String kuwei,
			String diaodu, String baoguan, String tianche, String zhuangxie,
			String sibang, String shenhe, String shenheTime, String shoufei,
			String shoufeiTime, String chandi, String beginTime,
			String finishTime, int pageNow, int rowSize) {
		String hql = "from InputOperate "
				+ "where inputSeed.input.inputClientNumber like ('%"
				+ dingDanHao
				+ "%') "
				+ " and inputSeed.input.client.clientLoginName like ('%"
				+ clientName
				+ "%') "
				+ " and inputSeed.input.inputCreateTime like ('%"
				+ inputTime
				+ "%')"
				+ " and inputSeed.input.inputPlateNumber like ('%"
				+ chehao
				+ "%') "
				+ " and inputSeed.input.inputDriverName like ('%"
				+ sijiName
				+ "%')"
				+ " and inputSeed.input.inputCarryType like ('%"
				+ yunshuStyle
				+ "%') "
				+ " and inputSeed.input.inputCancel like ('%"
				+ dingdanYesNo
				+ "%') "
				+ " and inputSeed.goods.goodsName like ('%"
				+ huowuName
				+ "%') "
				+ " and inputSeed.goods.goodsYieldly.goodsYieldlyName like ('%"
				+ chandi
				+ "%')"
				+ " and inputSeed.iseedOrderStatus like ('%"
				+ dingdanStatus
				+ "%')"
				+ " and tarehouse.tarehouseName like ('%"
				+ kuwei
				+ "%') "
				+ " and interiorUserByIoperateDispatcherId.iuserLoginName like ('%"
				+ diaodu
				+ "%')"
				+ " and interiorUserByIoperateStoremanId.iuserLoginName like ('%"
				+ baoguan
				+ "%') "
				+ " and interiorUserByIoperatePonderationManId.iuserLoginName like ('%"
				+ tianche
				+ "%')"
				+ " and ioperateStevedore like ('%"
				+ zhuangxie
				+ "%') "
				+ " and interiorUserByIoperatePonderationManId.iuserLoginName like ('%"
				+ sibang
				+ "%')"
				+ " and interiorUserByIoperateAuditingId.iuserLoginName like ('%"
				+ shenhe
				+ "%')"
				+ " and ioperateAuditingTime like ('%"
				+ shenheTime
				+ "%')	"
				+ " and interiorUserByIoperateCollectManId.iuserLoginName like ('%"
				+ shoufei
				+ "%')"
				+ " and ioperateCollectTime like ('%"
				+ shoufeiTime
				+ "%') "
				+ " and ioperateDispatcherTime>='"
				+ beginTime
				+ "' "
				+ " and ioperateDispatcherTime<='"
				+ finishTime + "' order by ioperateCollectTime desc";

		return (List<InputOperate>) super.executeQueryByPage(hql, null,
				pageNow, rowSize);
	}

	// 分页查询所有,根据收费时间排序
	@SuppressWarnings("unchecked")
	public List<InputOperate> getInputOperateByPage(int pageNow, int rowSize) {
		String hql = "from InputOperate order by ioperateCollectTime desc";

		return (List<InputOperate>) super.executeQueryByPage(hql, null,
				pageNow, rowSize);

	}

	// 分页查询分页页数
	public int getPageCount(int pageSize) {
		String hql = "select count(*) from InputOperate";
		return super.queryPageCount(hql, null, pageSize);
	}

	// 根据客户姓名、订单生产时间、车号、司机姓名、运输方式、订单是否作废、货物名称、订单状态
	// 库位、调度员、保管员、天车工、装卸工、司磅员、审核人员、审核时间、收费员、收费时间得分页页数
	public int getPageCountTwo(String dingDanHao, String clientName,
			String inputTime, String chehao, String sijiName,
			String yunshuStyle, String dingdanYesNo, String huowuName,
			String dingdanStatus, String kuwei, String diaodu, String baoguan,
			String tianche, String zhuangxie, String sibang, String shenhe,
			String shenheTime, String shoufei, String shoufeiTime,
			String chandi, String beginTime, String finishTime, int pageSize) {
		String hql = "select count(*) from InputOperate "
				+ " where inputSeed.input.inputClientNumber like ('%"
				+ dingDanHao
				+ "%') "
				+ " and inputSeed.input.client.clientLoginName like ('%"
				+ clientName
				+ "%') "
				+ " and inputSeed.input.inputCreateTime like ('%"
				+ inputTime
				+ "%')"
				+ " and inputSeed.input.inputPlateNumber like ('%"
				+ chehao
				+ "%') "
				+ " and inputSeed.input.inputDriverName like ('%"
				+ sijiName
				+ "%')"
				+ " and inputSeed.input.inputCarryType like ('%"
				+ yunshuStyle
				+ "%') "
				+ " and inputSeed.input.inputCancel like ('%"
				+ dingdanYesNo
				+ "%') "
				+ " and inputSeed.goods.goodsName like ('%"
				+ huowuName
				+ "%') "
				+ " and inputSeed.goods.goodsYieldly.goodsYieldlyName like ('%"
				+ chandi
				+ "%')"
				+ " and inputSeed.iseedOrderStatus like ('%"
				+ dingdanStatus
				+ "%')"
				+ " and tarehouse.tarehouseName like ('%"
				+ kuwei
				+ "%') "
				+ " and interiorUserByIoperateDispatcherId.iuserLoginName like ('%"
				+ diaodu
				+ "%')"
				+ " and interiorUserByIoperateStoremanId.iuserLoginName like ('%"
				+ baoguan
				+ "%') "
				+ " and interiorUserByIoperatePonderationManId.iuserLoginName like ('%"
				+ tianche
				+ "%')"
				+ " and ioperateStevedore like ('%"
				+ zhuangxie
				+ "%') "
				+ " and interiorUserByIoperatePonderationManId.iuserLoginName like ('%"
				+ sibang
				+ "%')"
				+ " and interiorUserByIoperateAuditingId.iuserLoginName like ('%"
				+ shenhe
				+ "%')"
				+ " and ioperateAuditingTime like ('%"
				+ shenheTime
				+ "%')	"
				+ " and interiorUserByIoperateCollectManId.iuserLoginName like ('%"
				+ shoufei
				+ "%')"
				+ " and ioperateCollectTime like ('%"
				+ shoufeiTime
				+ "%') "
				+ " and ioperateDispatcherTime>='"
				+ beginTime
				+ "' and ioperateDispatcherTime<='"
				+ finishTime
				+ "'";
		return super.queryPageCount(hql, null, pageSize);
	}

	// 通过司磅人员查询
	@SuppressWarnings("unchecked")
	public List<InputOperate> getSBInfo(String status, String danhao,
			String huozhu, int pageNow, int rowSize) {
		String hql = "from InputOperate where ioperateDefinedTwo='" + status
				+ "'" + " and ioperatePonderationTrue='过磅' "
				+ " and inputSeed.input.inputPlateNumber like '%" + danhao
				+ "%' " + " and (inputSeed.input.client.clientFirmName like '%"
				+ huozhu + "%' "
				+ " or inputSeed.input.client.clientAbbreviation like '%"
				+ huozhu + "%' "
				+ " or inputSeed.input.client.clientSign like '%" + huozhu
				+ "%') order by ioperateDispatcherTime desc";

		return (List<InputOperate>) super.executeQueryByPage(hql, null,
				pageNow, rowSize);
	}

	// 通过司磅人员查询的行数
	public int getSBCount(String status, String danhao, String huozhu,
			int pageSize) {

		String hql = "select count(*) from InputOperate where ioperateDefinedTwo='"
				+ status
				+ "'"
				+ " and ioperatePonderationTrue='过磅' "
				+ " and inputSeed.input.inputPlateNumber like '%"
				+ danhao
				+ "%' "
				+ " and (inputSeed.input.client.clientFirmName like '%"
				+ huozhu
				+ "%' "
				+ " or inputSeed.input.client.clientAbbreviation like '%"
				+ huozhu
				+ "%' "
				+ " or inputSeed.input.client.clientSign like '%"
				+ huozhu
				+ "%')";
		return super.queryPageCount(hql, null, pageSize);
	};

	// 返回所有内部人员查询操作表分页页数
	public int count(String status, String danhao, String huozhu, int pageSize) {
		String hql = "select count(*) from InputOperate where ioperateDefinedTwo='"
				+ status
				+ "'"
				+ " and inputSeed.input.inputPlateNumber like '%"
				+ danhao
				+ "%' "
				+ " and (inputSeed.input.client.clientFirmName like '%"
				+ huozhu
				+ "%' "
				+ " or inputSeed.input.client.clientAbbreviation like '%"
				+ huozhu
				+ "%' "
				+ " or inputSeed.input.client.clientSign like '%"
				+ huozhu
				+ "%')";
		return super.queryPageCount(hql, null, pageSize);
	}

	// 返回所有内部人员查询操作表分页的信息
	@SuppressWarnings("unchecked")
	public List<InputOperate> getInfo(String status, String danhao,
			String huozhu, int pageNow, int rowSize) {
		String hql = "from InputOperate where ioperateDefinedTwo='" + status
				+ "'" + " and inputSeed.input.inputPlateNumber like '%"
				+ danhao + "%' "
				+ " and (inputSeed.input.client.clientFirmName like '%"
				+ huozhu + "%' "
				+ " or inputSeed.input.client.clientAbbreviation like '%"
				+ huozhu + "%' "
				+ " or inputSeed.input.client.clientSign like '%" + huozhu
				+ "%') order by ioperateDispatcherTime desc";
		return (List<InputOperate>) super.executeQueryByPage(hql, null,
				pageNow, rowSize);
	}

	// 入库工作量统计查询
	@SuppressWarnings("unchecked")
	public List<InputOperate> QueryInputWorkByPage(String begin, String finish,
			String zhiwu, String name, int pageNow, int rowSize) {

		String hql = "from InputOperate where ioperateDispatcherTime>='"
				+ begin + "' and ioperateDispatcherTime<='" + finish + "'";
		if (zhiwu != null) {
			if (zhiwu.equals("1")) {
				hql += " and interiorUserByIoperateDispatcherId.iuserName like ('%"
						+ name + "%')";
			}
			if (zhiwu.equals("2")) {
				hql += " and interiorUserByIoperateStoremanId.iuserName like ('%"
						+ name + "%')";
			}
			if (zhiwu.equals("3")) {
				hql += " and interiorUserByIoperatePonderationManId.iuserName like ('%"
						+ name + "%')";
			}
			if (zhiwu.equals("4")) {
				hql += " and interiorUserByIoperateAuditingId.iuserName like ('%"
						+ name + "%')";
			}
			if (zhiwu.equals("5")) {
				hql += " and interiorUserByIoperateCollectManId.iuserName like ('%"
						+ name + "%')";
			}
			if (zhiwu.equals("6")) {
				hql += " and ioperateCraneman like ('%" + name + "%')";
			}
			if (zhiwu.equals("7")) {
				hql += " and ioperateStevedore like ('%" + name + "%')";
			}
		}

		hql += "order by ioperateDispatcherTime desc";
		return (List<InputOperate>) super.executeQueryByPage(hql, null,
				pageNow, rowSize);
	}

	// 入库工作量统计页数
	public int QueryInputWorkByPageCount(String begin, String finish,
			String zhiwu, String name, int rowSize) {
		String hql = "select count(*) from InputOperate where ioperateDispatcherTime>='"
				+ begin + "' and ioperateDispatcherTime<='" + finish + "'";
		if (zhiwu != null) {
			if (zhiwu.equals("1")) {
				hql += " and interiorUserByIoperateDispatcherId.iuserName like ('%"
						+ name + "%')";
			}
			if (zhiwu.equals("2")) {
				hql += " and interiorUserByIoperateStoremanId.iuserName like ('%"
						+ name + "%')";
			}
			if (zhiwu.equals("3")) {
				hql += " and interiorUserByIoperatePonderationManId.iuserName like ('%"
						+ name + "%')";
			}
			if (zhiwu.equals("4")) {
				hql += " and interiorUserByIoperateAuditingId.iuserName like ('%"
						+ name + "%')";
			}
			if (zhiwu.equals("5")) {
				hql += " and interiorUserByIoperateCollectManId.iuserName like ('%"
						+ name + "%')";
			}
			if (zhiwu.equals("6")) {
				hql += " and ioperateCraneman like ('%" + name + "%')";
			}
			if (zhiwu.equals("7")) {
				hql += " and ioperateStevedore like ('%" + name + "%')";
			}
		}

		return super.queryPageCount(hql, null, rowSize);
	}

	// 统计输入的该人员的入库合计
	@SuppressWarnings("unchecked")
	public Double QueryInputHeJi(String begin, String finish, String zhiwu,
			String name) {
		String hql = "select sum(ioperateRealityWeight) from InputOperate where ioperateDispatcherTime>='"
				+ begin + "' and ioperateDispatcherTime <= '" + finish + "'";
		if (zhiwu != null) {
			if (zhiwu.equals("1")) {
				hql += " and interiorUserByIoperateDispatcherId.iuserName like ('%"
						+ name + "%')";
			} else if (zhiwu.equals("2")) {
				hql += " and interiorUserByIoperateStoremanId.iuserName like ('%"
						+ name + "%')";
			} else if (zhiwu.equals("3")) {
				hql += " and interiorUserByIoperatePonderationManId.iuserName like ('%"
						+ name + "%')";
			} else if (zhiwu.equals("4")) {
				hql += " and interiorUserByIoperateAuditingId.iuserName like ('%"
						+ name + "%')";
			} else if (zhiwu.equals("5")) {
				hql += " and interiorUserByIoperateCollectManId.iuserName like ('%"
						+ name + "%')";
			} else if (zhiwu.equals("6")) {
				hql += " and ioperateCraneman like ('%" + name + "%')";
			} else if (zhiwu.equals("7")) {
				hql += " and ioperateStevedore like ('%" + name + "%')";
			} else {
				return 0.0;
			}
		}

		List<Double> iolist = (List<Double>) super.executeQuery(hql, null);
		return iolist.get(0);
	}

	// 统计输入的该人员的出库合计
	@SuppressWarnings("unchecked")
	public Double QueryExportHeJi(String begin, String finish, String zhiwu,
			String name) {
		String hql = "select sum(eoperateRealityWeight) from ExportOperate where eoperateDispatcherTime>='"
				+ begin + "' and eoperateDispatcherTime<='" + finish + "'";
		if (zhiwu != null) {
			if (zhiwu.equals("1")) {
				hql += " and interiorUserByEoperateDispatcher.iuserName like ('%"
						+ name + "%')";
			} else if (zhiwu.equals("2")) {
				hql += " and interiorUserByEoperateStoreman.iuserName like ('%"
						+ name + "%')";
			} else if (zhiwu.equals("3")) {
				hql += " and interiorUserByEoperatePonderationMan.iuserName like ('%"
						+ name + "%')";
			} else if (zhiwu.equals("4")) {
				hql += " and interiorUserByEoperateAuditing.iuserName like ('%"
						+ name + "%')";
			} else if (zhiwu.equals("5")) {
				hql += " and interiorUserByEoperateCollectMan.iuserName like ('%"
						+ name + "%')";
			} else if (zhiwu.equals("6")) {
				hql += " and eoperateCraneman like ('%" + name + "%')";
			} else if (zhiwu.equals("7")) {
				hql += " and eoperateStevedore like ('%" + name + "%')";
			} else {
				return 0.0;
			}
		}

		List<Double> iolist = (List<Double>) super.executeQuery(hql, null);
		return iolist.get(0);
	}

	// 统计输入的该人员的过户合计
	@SuppressWarnings("unchecked")
	public Double QueryShiftStockHeJi(String begin, String finish,
			String zhiwu, String name) {
		String hql = "select sum(ssseedWeight) from ShiftStockSeed where ssseedAuditingTime>='"
				+ begin + "' and ssseedAuditingTime<='" + finish + "'";
		if (zhiwu != null) {
			if (zhiwu.equals("4")) {
				hql += " and interiorUserBySsseedAuditing.iuserName like ('%"
						+ name + "%')";
			} else if (zhiwu.equals("5")) {
				hql += " and interiorUserBySsseedCollectMan.iuserName like ('%"
						+ name + "%')";
			} else {
				return 0.0000;
			}

			List<Double> iolist = (List<Double>) super.executeQuery(hql, null);
			if (iolist == null) {
				return 0.0;
			} else {
				return iolist.get(0) == null ? 0.000 : iolist.get(0);
			}

		} else {
			return 0.0000;
		}
	}

	// 统计输入的该人员的挪库合计
	@SuppressWarnings("unchecked")
	public Double QueryShiftHeJi(String begin, String finish, String zhiwu,
			String name) {
		String hql = "select sum(shiftWeight) from Shift where shiftTime>='"
				+ begin + "' and shiftTime<='" + finish + "'";
		if (zhiwu != null) {
			if (zhiwu.equals("1")) {
				hql += " and interiorUserByShiftFounderMember.iuserName like ('%"
						+ name + "%')";
			} else if (zhiwu.equals("2")) {
				hql += " and interiorUserByShiftExecutor.iuserName like ('%"
						+ name + "%')";
			} else if (zhiwu.equals("6")) {
				hql += " and shiftCraneman like ('%" + name + "%')";
			} else if (zhiwu.equals("7")) {
				hql += " and shiftStevedore like ('%" + name + "%')";
			} else {
				return 0.0;
			}
		}

		List<Double> iolist = (List<Double>) super.executeQuery(hql, null);
		return iolist.get(0);
	}

	// 统计输入的该人员的短倒合计
	@SuppressWarnings("unchecked")
	public Double QueryDuanDaoHeJi(String begin, String finish, String zhiwu,
			String name) {
		String hql = "select sum(shiftWeight) from DuanDao where shiftTime>='"
				+ begin + "' and shiftTime<='" + finish + "'";
		if (zhiwu != null) {
			if (zhiwu.equals("1")) {
				hql += " and interiorUserByShiftFounderMember.iuserName like ('%"
						+ name + "%')";
			} else if (zhiwu.equals("2")) {
				hql += " and interiorUserByShiftExecutor.iuserName like ('%"
						+ name + "%')";
			} else if (zhiwu.equals("6")) {
				hql += " and shiftCraneman like ('%" + name + "%')";
			} else if (zhiwu.equals("7")) {
				hql += " and shiftStevedore like ('%" + name + "%')";
			} else if (zhiwu.equals("8")) {
				hql += " and driverName like ('%" + name + "%')";
			} else {
				return 0.0;
			}
		}

		List<Double> iolist = (List<Double>) super.executeQuery(hql, null);
		return iolist.get(0);
	}

}
