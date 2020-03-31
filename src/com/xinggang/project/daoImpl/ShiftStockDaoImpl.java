package com.xinggang.project.daoImpl;

import java.util.List;

import com.xinggang.project.base.BaseDaoImpl;
import com.xinggang.project.dao.ShiftStockDao;
import com.xinggang.project.entity.ShiftStock;

/**
 * 过户总订单Dao实现类
 * 
 * @author Administrator
 * 
 */
public class ShiftStockDaoImpl extends BaseDaoImpl implements ShiftStockDao {
	// 增加
	public boolean save(ShiftStock shiftStock) {
		return super.BaseSave(shiftStock);
	}

	// 删除
	public boolean delete(ShiftStock shiftStock) {
		ShiftStock s = (ShiftStock) super.findById(ShiftStock.class,
				shiftStock.getSstockId());
		s.setSstockOrderStatus("订单作废");
		return super.BaseUpdate(s);
	}

	// 修改
	public boolean update(ShiftStock shiftStock) {
		return super.BaseUpdate(shiftStock);
	}

	// 通过id查询
	public ShiftStock getShiftStockId(String id) {
		return (ShiftStock) super.findById(ShiftStock.class, id);
	}

	// 查询全部
	@SuppressWarnings("unchecked")
	public List<ShiftStock> getAll() {
		String hql = "from ShiftStock order by sstockReateTime desc";
		return (List<ShiftStock>) super.executeQuery(hql, null);
	}

	// 分页查询全部
	@SuppressWarnings("unchecked")
	public List<ShiftStock> getShiftStockByPage(int pageNow, int rowSize) {
		String hql = "from ShiftStock where sstockOrderStatus!='订单作废' order by sstockReateTime desc";
		return (List<ShiftStock>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 查询全部数据的总行数
	public int getShiftStockByCount() {
		String hql = "select count(*) from ShiftStock where sstockOrderStatus!='订单作废'";
		return super.executeQueryRowCount(hql, null);
	}

	// 通过时间范围查询
	@SuppressWarnings("unchecked")
	public List<ShiftStock> getShijian(String begin, String finish,
			String dingdan, Integer kehu) {
		String hql = "from ShiftStock where sstockReateTime>= '"
				+ begin
				+ "' and sstockReateTime<= '"
				+ finish
				+ "' and sstockClientNumber like ('%"
				+ dingdan
				+ "%') and sstockOrderStatus!='订单作废' and clientBySstockClientId="
				+ kehu + " or clientBySstockShiftToFirm=" + kehu
				+ " order by sstockReateTime desc";
		return (List<ShiftStock>) super.executeQuery(hql, null);
	}

	// 通过时间范围分页查询
	@SuppressWarnings("unchecked")
	public List<ShiftStock> getShijianByPage(String begin, Integer kehu,
			String finish, String dingdan, int pageNow, int rowSize) {
		String hql = "from ShiftStock where sstockReateTime>= '"
				+ begin
				+ "' and sstockReateTime<= '"
				+ finish
				+ "' and sstockClientNumber like ('%"
				+ dingdan
				+ "%') and sstockOrderStatus!='订单作废' and clientBySstockClientId="
				+ kehu + " or clientBySstockShiftToFirm=" + kehu
				+ " order by sstockReateTime desc";
		return (List<ShiftStock>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 通过时间范围查询数据的总行数
	public int getShijianByCount(String begin, Integer kehu, String finish,
			String dingdan) {
		String hql = "select count(*) from ShiftStock where sstockReateTime>= '"
				+ begin
				+ "' and sstockReateTime<= '"
				+ finish
				+ "' and sstockClientNumber like ('%"
				+ dingdan
				+ "%') and sstockOrderStatus!='订单作废' and clientBySstockClientId="
				+ kehu
				+ " or clientBySstockShiftToFirm="
				+ kehu
				+ " order by sstockReateTime desc";
		return super.executeQueryRowCount(hql, null);
	}

	// 通过客户查询
	@SuppressWarnings("unchecked")
	public List<ShiftStock> getKehu(Integer kehu, Integer zhuanru) {
		String hql = "from ShiftStock clientBySstockClientId="
				+ kehu
				+ " or clientBySstockShiftToFirm="
				+ zhuanru
				+ " and sstockOrderStatus!='订单作废' order by sstockReateTime desc";
		return (List<ShiftStock>) super.executeQuery(hql, null);
	}

	// 通过客户分页查询
	@SuppressWarnings("unchecked")
	public List<ShiftStock> getKehuByPage(Integer kehu, Integer zhuanru,
			int pageNow, int rowSize) {
		String hql = "from ShiftStock clientBySstockClientId="
				+ kehu
				+ " or clientBySstockShiftToFirm="
				+ zhuanru
				+ " and sstockOrderStatus!='订单作废' order by sstockReateTime desc";
		return (List<ShiftStock>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 通过客户查询数据的总行数
	public int getKehuByCount(Integer kehu, Integer zhuanru) {
		String hql = "select count(*) from ShiftStock clientBySstockClientId="
				+ kehu
				+ " or clientBySstockShiftToFirm="
				+ zhuanru
				+ " and sstockOrderStatus!='订单作废' order by sstockReateTime desc";
		return super.executeQueryRowCount(hql, null);
	}

	// 通过订单状态查询
	@SuppressWarnings("unchecked")
	public List<ShiftStock> getZhuangtai(String zhuang) {
		String hql = "from ShiftStock sstockOrderStatus= '" + zhuang
				+ "' order by sstockReateTime desc";
		return (List<ShiftStock>) super.executeQuery(hql, null);
	}

	// 通过订单状态分页查询
	@SuppressWarnings("unchecked")
	public List<ShiftStock> getZhuangtaiByPage(String zhuang, int pageNow,
			int rowSize) {
		String hql = "from ShiftStock sstockOrderStatus= '" + zhuang
				+ "' order by sstockReateTime desc";
		return (List<ShiftStock>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 通过订单状态查询数据的总行数
	public int getZhuangtaiByCount(String zhuang) {
		String hql = "select count(*) from ShiftStock sstockOrderStatus= '"
				+ zhuang + "' order by sstockReateTime desc";
		return super.executeQueryRowCount(hql, null);
	}

}
