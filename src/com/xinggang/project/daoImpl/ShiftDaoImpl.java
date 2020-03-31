package com.xinggang.project.daoImpl;

import java.util.List;

import com.xinggang.project.base.BaseDaoImpl;
import com.xinggang.project.dao.ShiftDao;
import com.xinggang.project.entity.Shift;

/**
 * 挪库Dao实现类
 * 
 * @author Administrator
 * 
 */
public class ShiftDaoImpl extends BaseDaoImpl implements ShiftDao {
	// 增加
	public boolean save(Shift shift) {
		return super.BaseSave(shift);
	}

	// 删除
	public boolean delete(Shift shift) {
		return super.BaseDelete(shift);
	}

	// 修改
	public boolean update(Shift shift) {
		return super.BaseUpdate(shift);
	}

	// 通过id查询
	public Shift getShiftId(String id) {
		return (Shift) super.findById(Shift.class, id);
	}

	// 查询全部
	@SuppressWarnings("unchecked")
	public List<Shift> getAll() {
		String hql = "from Shift order by shiftId desc";
		return (List<Shift>) super.executeQuery(hql, null);
	}

	// 分页查询
	@SuppressWarnings("unchecked")
	public List<Shift> getShiftByPage(int pageNow, int rowSize) {
		String hql = "from Shift order by shiftTime desc";
		return (List<Shift>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 返回数据的总行数
	public int getShiftByCount() {
		String hql = "select count(*) from Shift";
		return super.executeQueryRowCount(hql, null);
	}

	// 通过挪库编号模糊查询
	@SuppressWarnings("unchecked")
	public List<Shift> getShiftLike(String shiftId) {
		String hql = "from Shift where shiftId like ('%" + shiftId + "%')";
		return (List<Shift>) super.executeQuery(hql, null);
	}

	// 通过挪库发起人查询
	@SuppressWarnings("unchecked")
	public List<Shift> getShiftMember(Integer MemberId) {
		String hql = "from Shift where interiorUserByShiftExecutor=" + MemberId
				+ "or interiorUserByShiftFounderMember=" + MemberId;
		return (List<Shift>) super.executeQuery(hql, null);
	}

	// 通过多种条件模糊查询
	@SuppressWarnings("unchecked")
	public List<Shift> getShiftDuozhongByPage(String begin, String finish,
			Integer goodsId, int pageNow, int rowSize) {
		String hql = "from Shift where shiftTime>= '" + begin
				+ "' and shiftTime<= '" + finish + "' and goods=" + goodsId
				+ " order by shiftTime desc";
		return (List<Shift>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 通过多种条件查询出数据的总行数
	public int getShiftDuozhongByCount(String begin, String finish,
			Integer goodsId, int rowSize) {
		String hql = "from Shift where shiftTime>= '" + begin
				+ "' and shiftTime<='" + finish + "' and goods=" + goodsId
				+ " order by shiftTime desc";
		return super.queryPageCount(hql, null, rowSize);
	}

	// 通过挪库状态查询
	@SuppressWarnings("unchecked")
	public List<Shift> getShiftZhuangtai(String zhuang) {
		String hql = "from Shift where shiftDefinedOne='" + zhuang + "'";
		return (List<Shift>) super.executeQuery(hql, null);
	}

	// 通过库位查询
	@SuppressWarnings("unchecked")
	public List<Shift> getKuwei(Integer kuweiId) {
		String hql = "from Shift where tarehouseByShiftPast like ('%" + kuweiId
				+ "%')" + " or tarehouseByShiftNew like ('%" + kuweiId + "%')";
		return (List<Shift>) super.executeQuery(hql, null);
	}

	// 通过工作人员模糊查询，天车工
	@SuppressWarnings("unchecked")
	public List<Shift> getWorkingTC(String working, String begin, String finish) {
		String hql = "from Shift where shiftCraneman like ('%" + working
				+ "%') and shiftTime > '" + begin + "' and shiftTime <= '"
				+ finish + "'";
		return (List<Shift>) super.executeQuery(hql, null);
	}

	// 通过货物查询j
	@SuppressWarnings("unchecked")
	public List<Shift> getGoodsAll(Integer goodsId) {
		String hql = "from Shift where goods=" + goodsId;
		return (List<Shift>) super.executeQuery(hql, null);
	}

	// 统计装卸工的工作量
	@SuppressWarnings("unchecked")
	public List<Shift> getWorkingZX(String working, String begin, String finish) {
		String hql = "from Shift where shiftStevedore like ('%" + working
				+ "%') and shiftTime >= '" + begin + "' and shiftTime <= '"
				+ finish + "'";
		return (List<Shift>) super.executeQuery(hql, null);
	}

	// 查询所有的挪库订单，以分页的方式显示
	@SuppressWarnings("unchecked")
	public List<Shift> getShiftAllByPage(String begin, String finish,
			String goodsName, String guige, String caizhi, String shuxing,
			String zhujifu, String kuweiname, int pageNow, int rowSize) {

		// 编号，原库位，挪库执行人，新库位，发起人,货物，发起日期，完成日期，件数,重量，天车工，装卸工，挪库状态，预留字段二，备注
		String hql = "from Shift where shiftTime >= '" + begin
				+ "' and shiftTime <= '" + finish
				+ "' and goods.goodsName like ('%" + goodsName
				+ "%') and goods.goodsSign like ('%" + zhujifu
				+ "%') and goods.goodsStandard.goodsStandardName like ('%"
				+ guige
				+ "%') and goods.goodsQuality.goodsQualityName like ('%"
				+ caizhi
				+ "%') and goods.goodsProperty.goodsPropertyName like ('%"
				+ shuxing
				+ "%') and tarehouseByShiftPast.tarehouseName like ('%"
				+ kuweiname + "%') order by shiftTime desc";
		return (List<Shift>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 查询所有挪库订单的总页数
	public int getShiftAllByPageCount(String begin, String finish,
			String goodsName, String guige, String caizhi, String shuxing,
			String zhujifu, String kuweiname, int rowSize) {
		String hql = "select count(*) from Shift where shiftTime >= '" + begin
				+ "' and shiftTime <= '" + finish
				+ "' and goods.goodsName like ('%" + goodsName
				+ "%') and goods.goodsSign like ('%" + zhujifu
				+ "%') and goods.goodsStandard.goodsStandardName like ('%"
				+ guige
				+ "%') and goods.goodsQuality.goodsQualityName like ('%"
				+ caizhi
				+ "%') and goods.goodsProperty.goodsPropertyName like ('%"
				+ shuxing
				+ "%') and tarehouseByShiftPast.tarehouseName like ('%"
				+ kuweiname + "%')";
		return super.queryPageCount(hql, null, rowSize);
	}

	// 挪库工作量统计查询
	@SuppressWarnings("unchecked")
	public List<Shift> QueryShiftWorkByPage(String begin, String finish,
			String zhiwu, String name, int pageNow, int rowSize) {
		String hql = "from Shift where shiftTime>='" + begin
				+ "' and shiftTime<='" + finish + "'";
		if (zhiwu != null) {
			if (zhiwu.equals("1")) {
				hql += " and interiorUserByShiftFounderMember.iuserName like ('%"
						+ name + "%')";
			}
			if (zhiwu.equals("2")) {
				hql += " and interiorUserByShiftExecutor.iuserName like ('%"
						+ name + "%')";
			}
			if (zhiwu.equals("6")) {
				hql += " and shiftCraneman like ('%" + name + "%')";
			}
			if (zhiwu.equals("7")) {
				hql += " and shiftStevedore like ('%" + name + "%')";
			}
		}

		hql += "order by shiftTime desc";
		return (List<Shift>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 挪库工作量统计页数
	public int QueryShiftWorkByPageCount(String begin, String finish,
			String zhiwu, String name, int rowSize) {
		String hql = "select count(*) from Shift where shiftTime>='" + begin
				+ "' and shiftTime<='" + finish + "'";
		if (zhiwu != null) {
			if (zhiwu.equals("1")) {
				hql += " and interiorUserByShiftFounderMember.iuserName like ('%"
						+ name + "%')";
			}
			if (zhiwu.equals("2")) {
				hql += " and interiorUserByShiftExecutor.iuserName like ('%"
						+ name + "%')";
			}
			if (zhiwu.equals("6")) {
				hql += " and shiftCraneman like ('%" + name + "%')";
			}
			if (zhiwu.equals("7")) {
				hql += " and shiftStevedore like ('%" + name + "%')";
			}
		}
		return super.queryPageCount(hql, null, rowSize);
	}

}
