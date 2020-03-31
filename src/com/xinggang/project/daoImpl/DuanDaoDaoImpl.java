package com.xinggang.project.daoImpl;

import java.util.List;

import com.xinggang.project.base.BaseDaoImpl;
import com.xinggang.project.dao.DuanDaoDao;
import com.xinggang.project.entity.DuanDao;

/*
 * 短倒dao接口的实现类
 * */
public class DuanDaoDaoImpl extends BaseDaoImpl implements DuanDaoDao {

	// 增加
	public boolean save(DuanDao duandao) {
		return super.BaseSave(duandao);
	}

	// 删除
	public boolean delete(DuanDao duandao) {
		return super.BaseDelete(duandao);
	}

	// 修改
	public boolean update(DuanDao duandao) {
		return super.BaseUpdate(duandao);
	}

	// 通过id查询
	public DuanDao getShiftId(String id) {
		return (DuanDao) super.findById(DuanDao.class, id);
	}

	// 查询全部
	@SuppressWarnings("unchecked")
	public List<DuanDao> getAll() {
		String hql = "from DuanDao order by shiftId desc";
		return (List<DuanDao>) super.executeQuery(hql, null);
	}

	// 分页查询
	@SuppressWarnings("unchecked")
	public List<DuanDao> getShiftByPage(int pageNow, int rowSize) {
		String hql = "from DuanDao order by shiftTime desc";
		return (List<DuanDao>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 查询数据的总行数
	public int getShiftByCount() {
		String hql = "select count(*) from DuanDao";
		return super.executeQueryRowCount(hql, null);
	}

	// 通过短倒的编号模糊查询
	@SuppressWarnings("unchecked")
	public List<DuanDao> getShiftLike(String shiftId) {
		String hql = "from DuanDao where shiftId like ('%" + shiftId + "%')";
		return (List<DuanDao>) super.executeQuery(hql, null);
	}

	// 通过短倒的发起人查询
	@SuppressWarnings("unchecked")
	public List<DuanDao> getShiftMember(Integer MemberId) {
		String hql = "from DuanDao where interiorUserByShiftExecutor="
				+ MemberId + "or interiorUserByShiftFounderMember=" + MemberId;
		return (List<DuanDao>) super.executeQuery(hql, null);
	}

	// 通过多种条件模糊查询
	@SuppressWarnings("unchecked")
	public List<DuanDao> getShiftDuozhongByPage(String begin, String finish,
			Integer goodsId, int pageNow, int rowSize) {
		String hql = "from DuanDao where shiftTime>= '" + begin
				+ "' and shiftTime<= '" + finish + "' and goods=" + goodsId
				+ " order by shiftTime desc";
		return (List<DuanDao>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 通过多种条件查询数据的总行数
	public int getShiftDuozhongByCount(String begin, String finish,
			Integer goodsId, int rowSize) {
		String hql = "select count(*) from DuanDao where shiftTime>= '" + begin
				+ "' and shiftTime<= '" + finish + "' and goods=" + goodsId
				+ "";
		return super.queryPageCount(hql, null, rowSize);
	}

	// 通过短倒的状态查询
	@SuppressWarnings("unchecked")
	public List<DuanDao> getShiftZhuangtai(String zhuang) {
		String hql = "from DuanDao where shiftDefinedOne='" + zhuang + "'";
		return (List<DuanDao>) super.executeQuery(hql, null);
	}

	// 通过库位查询
	@SuppressWarnings("unchecked")
	public List<DuanDao> getKuwei(Integer kuweiId) {
		String hql = "from DuanDao where tarehouseByShiftPast like ('%"
				+ kuweiId + "%')" + " or tarehouseByShiftNew like ('%"
				+ kuweiId + "%')";
		return (List<DuanDao>) super.executeQuery(hql, null);
	}

	// 通过工作人员模糊查询，天车工
	@SuppressWarnings("unchecked")
	public List<DuanDao> getWorkingTC(String working, String begin,
			String finish) {
		String hql = "from DuanDao where shiftCraneman like ('%" + working
				+ "%') and shiftTime > '" + begin + "' and shiftTime <= '"
				+ finish + "'";
		return (List<DuanDao>) super.executeQuery(hql, null);
	}

	// 统计装卸工的工作量
	@SuppressWarnings("unchecked")
	public List<DuanDao> getWorkingZX(String working, String begin,
			String finish) {
		String hql = "from DuanDao where shiftStevedore like ('%" + working
				+ "%') and shiftTime >= '" + begin + "' and shiftTime <= '"
				+ finish + "'";
		return (List<DuanDao>) super.executeQuery(hql, null);
	}

	// 通过货物进行查询
	@SuppressWarnings("unchecked")
	public List<DuanDao> getGoodsAll(Integer goodsId) {
		String hql = "from DuanDao where goods=" + goodsId;
		return (List<DuanDao>) super.executeQuery(hql, null);
	}

	// 查询所有的短倒订单，以分页的方式显示
	@SuppressWarnings("unchecked")
	public List<DuanDao> getShiftAllByPage(String begin, String finish,
			String goodsName, String guige, String caizhi, String shuxing,
			String zhujifu, String kuweiname, int pageNow, int rowSize) {
		// 编号，原库位，挪库执行人，新库位，发起人,货物，发起日期，完成日期，件数,重量，天车工，装卸工，挪库状态，预留字段二，备注
		String hql = "from DuanDao where shiftTime >= '" + begin
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
		return (List<DuanDao>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 查询所有短倒订单的页数
	public int getShiftAllByPageCount(String begin, String finish,
			String goodsName, String guige, String caizhi, String shuxing,
			String zhujifu, String kuweiname, int rowSize) {
		String hql = "select count(*) from DuanDao where shiftTime >= '"
				+ begin + "' and shiftTime <= '" + finish
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

	// 短倒工作量统计查询
	@SuppressWarnings("unchecked")
	public List<DuanDao> QueryShiftWorkByPage(String begin, String finish,
			String zhiwu, String name, int pageNow, int rowSize) {
		String hql = "from DuanDao where shiftTime>='" + begin
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
			if (zhiwu.equals("8")) {
				hql += " and driverName like ('%" + name + "%')";// 统计短倒司机
			}
		}

		hql += "order by shiftTime desc";
		return (List<DuanDao>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 查询短倒工作量统计的总页数
	public int QueryShiftWorkByPageCount(String begin, String finish,
			String zhiwu, String name, int rowSize) {
		String hql = "select count(*) from DuanDao where shiftTime>='" + begin
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
			if (zhiwu.equals("8")) {
				hql += " and driverName like ('%" + name + "%')";// 统计短倒司机
			}
		}

		return super.queryPageCount(hql, null, rowSize);
	}

	// 通过短倒司机进行分类，用来统计短倒司机的工作量
	@SuppressWarnings("unchecked")
	public List<DuanDao> ClassDriverName(String name) {
		String hql = "select new DuanDao(driverName) from DuanDao where driverName like ('%"
				+ name + "%') group by driverName";
		return (List<DuanDao>) super.executeQuery(hql, null);
	}
}
