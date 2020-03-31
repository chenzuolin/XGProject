package com.xinggang.project.daoImpl;

import java.util.List;

import com.xinggang.project.base.BaseDaoImpl;
import com.xinggang.project.dao.ShiftStockSeedDao;
import com.xinggang.project.entity.ShiftStockSeed;

/**
 * 过户子订单Dao实现类
 * 
 * @author Administrator
 * 
 */
public class ShiftStockSeedDaoImpl extends BaseDaoImpl implements
		ShiftStockSeedDao {
	// 增
	public boolean save(ShiftStockSeed shiftStockSeed) {
		return super.BaseSave(shiftStockSeed);
	}

	// 删
	public boolean delete(ShiftStockSeed shiftStockSeed) {
		return super.BaseDelete(shiftStockSeed);
	}

	// 改
	public boolean update(ShiftStockSeed shiftStockSeed) {
		return super.BaseUpdate(shiftStockSeed);
	}

	// 通过id查询
	public ShiftStockSeed getShiftStockSeedId(String id) {
		return (ShiftStockSeed) super.findById(ShiftStockSeed.class, id);
	}

	// 查询全部
	@SuppressWarnings("unchecked")
	public List<ShiftStockSeed> getAll() {
		String hql = "from ShiftStockSeed";
		return (List<ShiftStockSeed>) super.executeQuery(hql, null);
	}

	// 通过过户总订单编号查询
	@SuppressWarnings("unchecked")
	public List<ShiftStockSeed> getShiftStockId(String shiftStockId) {
		String hql = "from ShiftStockSeed where shiftStock= '" + shiftStockId
				+ "' ";
		return (List<ShiftStockSeed>) super.executeQuery(hql, null);
	}

	// 通过货物编号查询
	@SuppressWarnings("unchecked")
	public List<ShiftStockSeed> getGoodsId(Integer goodsId) {
		String hql = "from ShiftStockSeed where goods=" + goodsId
				+ " and ssseedOrderStatus!='订单作废'";
		return (List<ShiftStockSeed>) super.executeQuery(hql, null);
	}

	// 通过订单状态查询
	@SuppressWarnings("unchecked")
	public List<ShiftStockSeed> getZhuangtai(String zhuang) {
		String hql = "from ShiftStockSeed where ssseedOrderStatus= '" + zhuang
				+ "'";
		return (List<ShiftStockSeed>) super.executeQuery(hql, null);
	}

	// 通过内部人员模糊查询
	@SuppressWarnings("unchecked")
	public List<ShiftStockSeed> getNeibu(Integer neibu) {
		String hql = "from ShiftStockSeed where interiorUserBySsseedAuditing= "
				+ neibu + " or interiorUserBySsseedCollectMan=" + neibu
				+ "  and ssseedOrderStatus!='订单作废'";
		return (List<ShiftStockSeed>) super.executeQuery(hql, null);
	}

	// 通过结算方式查询
	@SuppressWarnings("unchecked")
	public List<ShiftStockSeed> getJiesuan(String jiesuan) {
		String hql = "from ShiftStockSeed where ssseedClientAccounts= '"
				+ jiesuan + "'  and ssseedOrderStatus!='订单作废'";
		return (List<ShiftStockSeed>) super.executeQuery(hql, null);
	}

	// 以分页的形式模糊查询，传入起始日期，结束日期，单位全拼，单位助记符，单位简称，货物名称，货物助记符
	@SuppressWarnings("unchecked")
	public List<ShiftStockSeed> getMoHuAll(String begin, String finish,
			String danwei, String danweizhujifu, String jiancheng,
			String goodsname, String goodszhujifu, String zonghao,
			String kehuhao, int pageNow, int rowSize) {

		// 过户子订单编号，审核人员，收费人员，过户总订单编号,支付方式,货物,审核时间，审核是否通过，应收费用，实收费用，收费时间,重量，件数，结算方式，订单状态，预留字段一，预留字段二，备注
		String hql = "from ShiftStockSeed where shiftStock.sstockReateTime >= '"
				+ begin
				+ "' and shiftStock.sstockReateTime <= '"
				+ finish
				+ "' and (shiftStock.clientBySstockClientId.clientFirmName like ('%"
				+ jiancheng
				+ "%') or shiftStock.clientBySstockClientId.clientAbbreviation like ('%"
				+ jiancheng
				+ "%') or shiftStock.clientBySstockClientId.clientSign like ('%"
				+ jiancheng
				+ "%')) and (goods.goodsName like ('%"
				+ goodsname
				+ "%') or goods.goodsSign like ('%"
				+ goodsname
				+ "%') or goods.goodsYieldly.goodsYieldlyName like ('%"
				+ goodsname
				+ "%') or goods.goodsStandard.goodsStandardName like ('%"
				+ goodsname
				+ "%')) and shiftStock.sstockId like ('%"
				+ zonghao
				+ "%') and shiftStock.sstockClientNumber like ('%"
				+ kehuhao
				+ "%')  and ssseedOrderStatus!='订单作废' order by shiftStock.sstockReateTime desc";
		return (List<ShiftStockSeed>) super.executeQueryByPage(hql, null,
				pageNow, rowSize);
	}

	// 查询总页数
	public int getMoHuAllByCount(String begin, String finish, String danwei,
			String danweizhujifu, String jiancheng, String goodsname,
			String goodszhujifu, String zonghao, String kehuhao, int rowSize) {
		String hql = "select count(*) from ShiftStockSeed where shiftStock.sstockReateTime >= '"
				+ begin
				+ "' and shiftStock.sstockReateTime <= '"
				+ finish
				+ "' and (shiftStock.clientBySstockClientId.clientFirmName like ('%"
				+ jiancheng
				+ "%') or shiftStock.clientBySstockClientId.clientAbbreviation like ('%"
				+ jiancheng
				+ "%') or shiftStock.clientBySstockClientId.clientSign like ('%"
				+ jiancheng
				+ "%')) and (goods.goodsName like ('%"
				+ goodsname
				+ "%') or goods.goodsSign like ('%"
				+ goodsname
				+ "%') or goods.goodsYieldly.goodsYieldlyName like ('%"
				+ goodsname
				+ "%') or goods.goodsStandard.goodsStandardName like ('%"
				+ goodsname
				+ "%')) and shiftStock.sstockId like ('%"
				+ zonghao
				+ "%') and shiftStock.sstockClientNumber like ('%"
				+ kehuhao
				+ "%')  and ssseedOrderStatus!='订单作废'";
		return super.queryPageCount(hql, null, rowSize);
	}

	// 通过客户分页显示
	@SuppressWarnings("unchecked")
	public List<ShiftStockSeed> getClientByPageAll(Integer clientId,
			int pageNow, int rowSize) {
		// 过户子订单编号，审核人员，收费人员，过户总订单编号,支付方式,货物,审核时间，审核是否通过，应收费用，实收费用，收费时间,重量，件数，结算方式，订单状态，预留字段一，预留字段二，备注
		String hql = "select new ShiftStockSeed(sss.ssseedId,sss.interiorUserBySsseedAuditing,sss.interiorUserBySsseedCollectMan,"
				+ "sss.shiftStock,sss.paymentFashion,sss.goods,sss.ssseedAuditingTime,sss.ssseedAuditingTrue,sss.ssseedShouldCost,sss.ssseedRealityCost,sss.ssseedCollectTime"
				+ " sss.ssseedWeight,sss.ssseedNumber,sss.ssseedClientAccounts,sss.ssseedOrderStatus,sss.ssseedDefinedOne,sss.ssseedDefinedTwo,sss.ssseedRemark) "
				+ "from ShiftStockSeed sss,ShiftStock ss where sss.shiftStock=ss.sstockId ss.clientBySstockClientId = "
				+ clientId
				+ " or "
				+ "ss.clientBySstockShiftToFirm = "
				+ clientId
				+ "  and ssseedOrderStatus!='订单作废' order by ss.sstockReateTime desc";
		return (List<ShiftStockSeed>) super.executeQueryByPage(hql, null,
				pageNow, rowSize);
	}

	// 通过客户查询数据的总页数
	public int getClientByPageCount(Integer clientId, int rowSize) {
		String hql = "select count(*) from ShiftStockSeed sss,ShiftStock ss where sss.shiftStock=ss.sstockId and ss.clientBySstockClientId= "
				+ clientId
				+ " or ss.clientBySstockShiftToFirm= "
				+ clientId
				+ "  and ssseedOrderStatus!='订单作废'";
		return super.queryPageCount(hql, null, rowSize);
	}

	// 通过提货单号和货主进行模糊分页查询
	@SuppressWarnings("unchecked")
	public List<ShiftStockSeed> getTiHuoDataByPage(String tihuohao,
			String huozhu, String zhuangtai, int pageNow, int rowSize) {
		String hql = "from ShiftStockSeed where ssseedOrderStatus = '"
				+ zhuangtai
				+ "'and shiftStock.sstockClientNumber like ('%"
				+ tihuohao
				+ "%') and (shiftStock.clientBySstockClientId.clientAbbreviation like ('%"
				+ huozhu
				+ "%')  or shiftStock.clientBySstockClientId.clientFirmName like ('%"
				+ huozhu
				+ "%') or shiftStock.clientBySstockClientId.clientSign like ('%"
				+ huozhu
				+ "%'))  and ssseedOrderStatus!='订单作废' order by shiftStock.sstockReateTime asc";
		return (List<ShiftStockSeed>) super.executeQueryByPage(hql, null,
				pageNow, rowSize);
	}

	// 通过提货单号和货主进行模糊查询页数
	public int getTiHuoDataByPageCount(String tihuohao, String huozhu,
			String zhuangtai, int rowSize) {
		String hql = "select count(*) from ShiftStockSeed where ssseedOrderStatus = '"
				+ zhuangtai
				+ "' and shiftStock.sstockClientNumber like ('%"
				+ tihuohao
				+ "%') and (shiftStock.clientBySstockClientId.clientAbbreviation like ('%"
				+ huozhu
				+ "%')  or shiftStock.clientBySstockClientId.clientFirmName like ('%"
				+ huozhu
				+ "%') or shiftStock.clientBySstockClientId.clientSign like ('%"
				+ huozhu + "%'))  and ssseedOrderStatus!='订单作废'";
		return super.queryPageCount(hql, null, rowSize);
	}

	// -----------------------------------app客户查询未作废的所有订单

	// -----------------------------------app客户

	// 客户app客户根据条件查询入库订单
	@SuppressWarnings("unchecked")
	public List<ShiftStockSeed> getNuokuInfo(Integer kehuId,
			String jiesuanType, String startTime, String endTime,
			String kehuDanhao, String huowu, int pageNow, int rowSize) {

		String hql = "from ShiftStockSeed where (shiftStock.clientBySstockClientId.clientId="
				+ kehuId
				+ " or shiftStock.clientBySstockShiftToFirm.clientId="+kehuId+") and (ssseedClientAccounts like '%"
				+ jiesuanType
				+ "%')"
				+ " and shiftStock.sstockClientNumber like '%"
				+ kehuDanhao
				+ "%' "
				+ " and (goods.goodsName like '%"
				+ huowu
				+ "%' "
				+ " or goods.goodsSign like '%"
				+ huowu
				+ "%') and ssseedOrderStatus!='订单作废' ";

		if (startTime != "" || endTime != "") {
			hql += " and (shiftStock.sstockReateTime between '" + startTime
					+ "' " + " and '" + endTime
					+ "')  order by shiftStock.sstockReateTime desc";
		} else {
			hql += " order by shiftStock.sstockReateTime desc";

		}

		return (List<ShiftStockSeed>) super.executeQueryByPage(hql, null,
				pageNow, rowSize);
	}

	// 客户app查询入库订单,返回行数

	public int getNuokuCount(Integer kehuId, String jiesuanType,
			String startTime, String endTime, String kehuDanhao, String huowu,
			int pageSize) {
		String hql = "select count(*) from ShiftStockSeed where (shiftStock.clientBySstockClientId.clientId="
				+ kehuId
				+ " or shiftStock.clientBySstockShiftToFirm.clientId="+kehuId+") and (ssseedClientAccounts like '%"
				+ jiesuanType
				+ "%')"
				+ " and shiftStock.sstockId like '%"
				+ kehuDanhao
				+ "%' "
				+ " and (goods.goodsName like '%"
				+ huowu
				+ "%' "
				+ " or goods.goodsSign like '%" + huowu + "%')";
		// 如果时间不为空，加入时间范围
		if (startTime != "" && endTime != "") {
			hql += " and (shiftStock.sstockReateTime between '" + startTime
					+ "' " + " and '" + endTime + "')";
		}
		return super.queryPageCount(hql, null, pageSize);
	}

	// 客户app查询入库订单
	@SuppressWarnings("unchecked")
	public List<ShiftStockSeed> getNuokuInfoAll(Integer kehuId,String begin,String finish, int pageNow,
			int rowSize) {

		String hql = "from ShiftStockSeed where shiftStock.clientBySstockClientId.clientId="
				+ kehuId
				+ " and ssseedOrderStatus!='订单作废' and (shiftStock.sstockReateTime between '" + begin
					+ "' " + " and '" + finish
					+ "') order by shiftStock.sstockReateTime desc";

		return (List<ShiftStockSeed>) super.executeQueryByPage(hql, null,
				pageNow, rowSize);
	}

	// 客户app查询入库订单,返回行数
	public int getNuokuCountAll(Integer kehuId,String begin,String finish, int pageSize) {
		String hql = "select count(*) from ShiftStockSeed where shiftStock.clientBySstockClientId.clientId="
				+ kehuId + " and ssseedOrderStatus!='订单作废' and (shiftStock.sstockReateTime between '" + begin
					+ "' " + " and '" + finish
					+ "')";

		return super.queryPageCount(hql, null, pageSize);
	}

	// -----------------------------------app客户查询所有作废的所有订单

	// 客户app客户根据条件查询入库订单
	@SuppressWarnings("unchecked")
	public List<ShiftStockSeed> getNuokuInfoPash(Integer kehuId,
			String startTime, String endTime, String kehuDanhao, String huowu,
			int pageNow, int rowSize) {

		String hql = "from ShiftStockSeed where shiftStock.clientBySstockClientId.clientId="
				+ kehuId
				+ " "
				+ " and shiftStock.sstockClientNumber like '%"
				+ kehuDanhao
				+ "%' "
				+ " and (goods.goodsName like '%"
				+ huowu
				+ "%' "
				+ " or goods.goodsSign like '%"
				+ huowu
				+ "%') and ssseedOrderStatus='订单作废' ";

		if (startTime != "" && endTime != "") {
			hql += " and (shiftStock.sstockReateTime between '" + startTime
					+ "' " + " and '" + endTime
					+ "')  order by shiftStock.sstockReateTime desc";
		} else {
			hql += " order by shiftStock.sstockReateTime desc";
		}

		return (List<ShiftStockSeed>) super.executeQueryByPage(hql, null,
				pageNow, rowSize);
	}

	// 客户app查询入库订单,返回行数
	public int getNuokuCountPash(Integer kehuId, String startTime,
			String endTime, String kehuDanhao, String huowu, int pageSize) {
		String hql = "select count(*) from ShiftStockSeed where shiftStock.clientBySstockClientId.clientId="
				+ kehuId
				+ " "
				+ " and shiftStock.sstockClientNumber like '%"
				+ kehuDanhao
				+ "%' "
				+ " and (goods.goodsName like '%"
				+ huowu
				+ "%' "
				+ " or goods.goodsSign like '%"
				+ huowu
				+ "%') and ssseedOrderStatus='订单作废'";
		// 如果时间不为空，加入时间范围
		if (startTime != "" && endTime != "") {
			hql += " and (shiftStock.sstockReateTime between '" + startTime
					+ "' " + " and '" + endTime + "')";
		}
		return super.queryPageCount(hql, null, pageSize);
	}

	// 客户app查询入库订单
	@SuppressWarnings("unchecked")
	public List<ShiftStockSeed> getNuokuInfoAllPash(Integer kehuId,
			int pageNow, int rowSize) {

		String hql = "from ShiftStockSeed where shiftStock.clientBySstockClientId.clientId="
				+ kehuId
				+ " and ssseedOrderStatus='订单作废' order by shiftStock.sstockReateTime desc";
		return (List<ShiftStockSeed>) super.executeQueryByPage(hql, null,
				pageNow, rowSize);
	}

	// 客户app查询入库订单,返回行数
	public int getNuokuCountAllPash(Integer kehuId, int pageSize) {
		String hql = "select count(*) from ShiftStockSeed where shiftStock.clientBySstockClientId.clientId="
				+ kehuId + " and ssseedOrderStatus='订单作废'";

		return super.queryPageCount(hql, null, pageSize);
	}

	// 统计客户的库存，统计的计算方法是：期初库存+（入库实入重量+转入重量）-（出库实出重量+转出重量）=期末库存 **指的是在某个时间段内的重量**
	// 客户的期初库存指的是在开始时间之前的库存：（入库实入重量+转入重量）-（出库实出重量+转出重量）=期初库存**指的是在某个时间段开始时间之前的重量***
	// 先将该时间段内的客户的库存相加，也就是出库的合计,转出的合计
	@SuppressWarnings("unchecked")
	public List<ShiftStockSeed> getKeHuKuCunGHZCHJ(String begin, String finish,
			Integer clientId, Integer goodsId) {
		String hql = "select new ShiftStockSeed(SUM(ssseedWeight)) from ShiftStockSeed where ssseedOrderStatus!='订单作废' and shiftStock.sstockReateTime >= '"
				+ begin
				+ "' and shiftStock.sstockReateTime <='"
				+ finish
				+ "' and shiftStock.clientBySstockClientId.clientId="
				+ clientId
				+ " and goods.goodsId ="
				+ goodsId
				+ " and ssseedOrderStatus!='订单作废' and ssseedOrderStatus!='审核未通过' group by goods.goodsId,shiftStock.clientBySstockClientId.clientId";
		return (List<ShiftStockSeed>) super.executeQuery(hql, null);
	}

	// 转入的合计
	@SuppressWarnings("unchecked")
	public List<ShiftStockSeed> getKeHuKuCunGHZRHJ(String begin, String finish,
			Integer clientId, Integer goodsId) {
		String hql = "select new ShiftStockSeed(SUM(ssseedWeight)) from ShiftStockSeed where ssseedOrderStatus!='订单作废' and shiftStock.sstockReateTime >= '"
				+ begin
				+ "' and shiftStock.sstockReateTime <='"
				+ finish
				+ "' and shiftStock.clientBySstockShiftToFirm.clientId="
				+ clientId
				+ " and goods.goodsId ="
				+ goodsId
				+ " and ssseedOrderStatus!='订单作废' and ssseedOrderStatus!='审核未通过' group by goods.goodsId,shiftStock.clientBySstockShiftToFirm.clientId";
		return (List<ShiftStockSeed>) super.executeQuery(hql, null);
	}

	// 统计客户库存中期初的出库合计,转出的期初
	@SuppressWarnings("unchecked")
	public List<ShiftStockSeed> getKeHuKuCunZCQCHJ(String begin,
			Integer clientId, Integer goodsId) {
		String hql = "select new ShiftStockSeed(SUM(ssseedWeight)) from ShiftStockSeed where ssseedOrderStatus!='订单作废' and shiftStock.sstockReateTime < '"
				+ begin
				+ "' and shiftStock.clientBySstockClientId.clientId="
				+ clientId
				+ " and goods.goodsId ="
				+ goodsId
				+ " and ssseedOrderStatus!='订单作废' and ssseedOrderStatus!='审核未通过' group by goods.goodsId,shiftStock.clientBySstockClientId.clientId";
		return (List<ShiftStockSeed>) super.executeQuery(hql, null);
	}

	// 统计客户库存中期初的出库合计,转人的期初
	@SuppressWarnings("unchecked")
	public List<ShiftStockSeed> getKeHuKuCunZRQCHJ(String begin,
			Integer clientId, Integer goodsId) {
		String hql = "select new ShiftStockSeed(SUM(ssseedWeight)) from ShiftStockSeed where ssseedOrderStatus!='订单作废' and shiftStock.sstockReateTime < '"
				+ begin
				+ "' and shiftStock.clientBySstockShiftToFirm.clientId="
				+ clientId
				+ " and goods.goodsId ="
				+ goodsId
				+ " and ssseedOrderStatus!='订单作废' and ssseedOrderStatus!='审核未通过' group by goods.goodsId,shiftStock.clientBySstockShiftToFirm.clientId";
		return (List<ShiftStockSeed>) super.executeQuery(hql, null);
	}

	// 收发存统计查询
	@SuppressWarnings("unchecked")
	public List<ShiftStockSeed> getKeHuKuCunGHZCHJSFC(String begin,
			String finish, Integer clientId, String goodsName) {
		String hql = "select new ShiftStockSeed(SUM(ssseedWeight)) from ShiftStockSeed where ssseedOrderStatus!='订单作废' and shiftStock.sstockReateTime >= '"
				+ begin
				+ "' and shiftStock.sstockReateTime <='"
				+ finish
				+ "' and shiftStock.clientBySstockClientId.clientId="
				+ clientId
				+ " and goods.goodsName ='"
				+ goodsName
				+ "' and ssseedOrderStatus!='订单作废' and ssseedOrderStatus!='审核未通过' group by shiftStock.clientBySstockClientId.clientId";
		return (List<ShiftStockSeed>) super.executeQuery(hql, null);
	}

	// 转入的合计
	@SuppressWarnings("unchecked")
	public List<ShiftStockSeed> getKeHuKuCunGHZRHJSFC(String begin,
			String finish, Integer clientId, String goodsName) {
		String hql = "select new ShiftStockSeed(SUM(ssseedWeight)) from ShiftStockSeed where ssseedOrderStatus!='订单作废' and shiftStock.sstockReateTime >= '"
				+ begin
				+ "' and shiftStock.sstockReateTime <='"
				+ finish
				+ "' and shiftStock.clientBySstockShiftToFirm.clientId="
				+ clientId
				+ " and goods.goodsName ='"
				+ goodsName
				+ "' and ssseedOrderStatus!='订单作废' and ssseedOrderStatus!='审核未通过' group by shiftStock.clientBySstockShiftToFirm.clientId";
		return (List<ShiftStockSeed>) super.executeQuery(hql, null);
	}

	// 统计客户库存中期初的出库合计,转出的期初
	@SuppressWarnings("unchecked")
	public List<ShiftStockSeed> getKeHuKuCunZCQCHJSFC(String begin,
			Integer clientId, String goodsName) {
		String hql = "select new ShiftStockSeed(SUM(ssseedWeight)) from ShiftStockSeed where ssseedOrderStatus!='订单作废' and shiftStock.sstockReateTime < '"
				+ begin
				+ "' and shiftStock.clientBySstockClientId.clientId="
				+ clientId
				+ " and goods.goodsName ='"
				+ goodsName
				+ "' and ssseedOrderStatus!='订单作废' and ssseedOrderStatus!='审核未通过' group by shiftStock.clientBySstockClientId.clientId";
		return (List<ShiftStockSeed>) super.executeQuery(hql, null);
	}

	// 统计客户库存中期初的出库合计,转人的期初
	@SuppressWarnings("unchecked")
	public List<ShiftStockSeed> getKeHuKuCunZRQCHJSFC(String begin,
			Integer clientId, String goodsName) {
		String hql = "select new ShiftStockSeed(SUM(ssseedWeight)) from ShiftStockSeed where ssseedOrderStatus!='订单作废' and shiftStock.sstockReateTime < '"
				+ begin
				+ "' and shiftStock.clientBySstockShiftToFirm.clientId="
				+ clientId
				+ " and goods.goodsName ='"
				+ goodsName
				+ "' and ssseedOrderStatus!='订单作废' and ssseedOrderStatus!='审核未通过' group by shiftStock.clientBySstockShiftToFirm.clientId";
		return (List<ShiftStockSeed>) super.executeQuery(hql, null);
	}

	// 过户工作量统计查询
	@SuppressWarnings("unchecked")
	public List<ShiftStockSeed> QueryShiftStockWorkByPage(String begin,
			String finish, String zhiwu, String name, int pageNow, int rowSize) {
		String hql = "from ShiftStockSeed where ssseedAuditingTime>='" + begin
				+ "' and ssseedAuditingTime<='" + finish + "'";
		if (zhiwu != null) {
			if (zhiwu.equals("4")) {
				hql += " and interiorUserBySsseedAuditing.iuserName like ('%"
						+ name + "%')";
			}
			if (zhiwu.equals("5")) {
				hql += " and interiorUserBySsseedCollectMan.iuserName like ('%"
						+ name + "%')";
			}
		}

		hql += "order by ssseedAuditingTime desc";
		return (List<ShiftStockSeed>) super.executeQueryByPage(hql, null,
				pageNow, rowSize);
	}

	// 过户工作量统计页数
	public int QueryShiftStockWorkByPageCount(String begin, String finish,
			String zhiwu, String name, int rowSize) {
		String hql = "select count(*) from ShiftStockSeed where ssseedAuditingTime>='"
				+ begin + "' and ssseedAuditingTime<='" + finish + "'";
		if (zhiwu != null) {
			if (zhiwu.equals("4")) {
				hql += " and interiorUserBySsseedAuditing.iuserName like ('%"
						+ name + "%')";
			}
			if (zhiwu.equals("5")) {
				hql += " and interiorUserBySsseedCollectMan.iuserName like ('%"
						+ name + "%')";
			}
		}
		return super.queryPageCount(hql, null, rowSize);
	}

	// 通过客户和开始和结束日期计算过户的费用
	public Double getGuoHuCost(String begin, String finish, Integer clientId) {
		String hql = "select sum(ssseedShouldCost) from ShiftStockSeed where ssseedAuditingTime>='"
				+ begin
				+ "' and ssseedAuditingTime<='"
				+ finish
				+ "' and ssseedClientAccounts ='月结' and ssseedOrderStatus = '未收费' and shiftStock.clientBySstockShiftToFirm = "
				+ clientId;
		Double cost = (Double) super.executeQuery(hql, null).get(0);
		return cost;
	}

	// 查询过户中的订单的相应费用，通过时间和结算方式进行查询
	@SuppressWarnings("unchecked")
	public List<ShiftStockSeed> getJieSuanQueryByPage(String clientName,
			String begin, String finish, String jiesuan, int pageNow,
			int rowSize, String shoufeiren) {
		String hql = "from ShiftStockSeed where ssseedOrderStatus!='订单作废' and shiftStock.sstockReateTime >= '"
				+ begin
				+ "' and shiftStock.sstockReateTime <='"
				+ finish
				+ "' and (shiftStock.clientBySstockClientId.clientSign like ('%"
				+ clientName
				+ "%') or shiftStock.clientBySstockClientId.clientAbbreviation like ('%"
				+ clientName
				+ "%') or shiftStock.clientBySstockClientId.clientFirmName like ('%"
				+ clientName
				+ "%') or shiftStock.clientBySstockShiftToFirm.clientSign like ('%"
				+ clientName
				+ "%') or shiftStock.clientBySstockShiftToFirm.clientAbbreviation like ('%"
				+ clientName
				+ "%') or shiftStock.clientBySstockShiftToFirm.clientFirmName like ('%"
				+ clientName
				+ "%')) and ssseedClientAccounts like ('%"
				+ jiesuan
				+ "%') and interiorUserBySsseedCollectMan.iuserName like ('%"
				+ shoufeiren + "%') order by shiftStock.sstockReateTime desc";
		return (List<ShiftStockSeed>) super.executeQueryByPage(hql, null,
				pageNow, rowSize);
	}

	// 查询过户中的订单的相应费用，通过时间和结算方式进行查询总页数
	public int getJieSuanQueryByPageCount(String clientName, String begin,
			String finish, String jiesuan, int rowSize, String shoufeiren) {
		String hql = "select count(*) from ShiftStockSeed where ssseedOrderStatus!='订单作废' and shiftStock.sstockReateTime >= '"
				+ begin
				+ "' and shiftStock.sstockReateTime <='"
				+ finish
				+ "' and (shiftStock.clientBySstockClientId.clientSign like ('%"
				+ clientName
				+ "%') or shiftStock.clientBySstockClientId.clientAbbreviation like ('%"
				+ clientName
				+ "%') or shiftStock.clientBySstockClientId.clientFirmName like ('%"
				+ clientName
				+ "%') or shiftStock.clientBySstockShiftToFirm.clientSign like ('%"
				+ clientName
				+ "%') or shiftStock.clientBySstockShiftToFirm.clientAbbreviation like ('%"
				+ clientName
				+ "%') or shiftStock.clientBySstockShiftToFirm.clientFirmName like ('%"
				+ clientName
				+ "%')) and ssseedClientAccounts like ('%"
				+ jiesuan
				+ "%') and interiorUserBySsseedCollectMan.iuserName like ('%"
				+ shoufeiren + "%')";
		return super.queryPageCount(hql, null, rowSize);
	}

	// app中客户查询订单中日结的订单的费用
	@SuppressWarnings("unchecked")
	public List<ShiftStockSeed> getAppQueryDayCostByPage(Integer clientId,
			String search, int pageNow, int rowSize) {
		String hql = "from ShiftStockSeed where ssseedOrderStatus!='订单作废'  and shiftStock.clientBySstockShiftToFirm.clientId = "
				+ clientId
				+ " and (goods.goodsName like ('%"
				+ search
				+ "%') or goods.goodsSign like ('%"
				+ search
				+ "%') or goods.goodsYieldly.goodsYieldlyName like ('%"
				+ search
				+ "%') or shiftStock.sstockClientNumber like ('%"
				+ search
				+ "%')) and ssseedClientAccounts = '日结'  order by shiftStock.sstockReateTime desc";
		return (List<ShiftStockSeed>) super.executeQueryByPage(hql, null,
				pageNow, rowSize);
	}

	// app中客户查询订单中日结的订单的费用的中也属
	public int getAppQueryDayCostByCount(Integer clientId, String search,
			int pageNow, int rowSize) {
		String hql = "select count(*) from ShiftStockSeed where ssseedOrderStatus!='订单作废'  and shiftStock.clientBySstockShiftToFirm.clientId = "
				+ clientId
				+ " and (goods.goodsName like ('%"
				+ search
				+ "%') or goods.goodsSign like ('%"
				+ search
				+ "%') or goods.goodsYieldly.goodsYieldlyName like ('%"
				+ search
				+ "%') or shiftStock.sstockClientNumber like ('%"
				+ search + "%')) and ssseedClientAccounts = '日结'";
		return super.queryPageCount(hql, null, rowSize);
	}
}
