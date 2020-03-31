package com.xinggang.project.daoImpl;

import java.util.List;

import com.xinggang.project.base.BaseDaoImpl;
import com.xinggang.project.dao.AccountsDao;
import com.xinggang.project.entity.Accounts;

/**
 * 滞纳金Dao实现类
 * 
 * @author Administrator
 * 
 */
public class AccountsDaoImpl extends BaseDaoImpl implements AccountsDao {
	// 增加
	public boolean save(Accounts accounts) {
		return super.BaseSave(accounts);
	}

	// 删除
	public boolean delete(Accounts accounts) {
		return super.BaseDelete(accounts);
	}

	// 修改
	public boolean update(Accounts accounts) {
		return super.BaseUpdate(accounts);
	}

	// 通过id查询
	public Accounts getAccountsId(String id) {
		return (Accounts) super.findById(Accounts.class, id);
	}

	// 查询全部
	@SuppressWarnings("unchecked")
	public List<Accounts> getAll() {
		String hql = "from Accounts order by accountsId desc";
		return (List<Accounts>) super.executeQuery(hql, null);
	}

	// 分页查询-------------------------------------------------------------------------未实现的
	@SuppressWarnings("unchecked")
	public List<Accounts> getAccountsByPage(Integer clientId, int pageNow,
			int rowSize) {
		String hql = "from Accounts where client=" + clientId
				+ " order by accountsId desc";
		return (List<Accounts>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 查询数据的总行数
	public int getAccountsByCount(Integer clientId) {
		String hql = "select count(*) from Accounts where client=" + clientId
				+ " order by accountsId desc";
		return super.executeQueryRowCount(hql, null);
	}

	// 通过客户查询
	@SuppressWarnings("unchecked")
	public List<Accounts> getClient(Integer clientId) {
		String hql = "from Accounts where client=" + clientId
				+ " order by accountsId desc";
		return (List<Accounts>) super.executeQuery(hql, null);
	}

	// 查询未收费的订单
	@SuppressWarnings("unchecked")
	public List<Accounts> getWeishou() {
		String hql = "from Accounts where zhuangtai='未收费' order by accountsId desc";
		return (List<Accounts>) super.executeQuery(hql, null);
	}

	// 通过客户查询是否有滞纳金产生
	@SuppressWarnings("unchecked")
	public List<Accounts> getChansheng(Integer clientId) {
		String hql = "from Accounts where client="
				+ clientId
				+ " and accountsFinishTime in ('') and accountsCollectTime in ('')"
				+ " order by accountsId desc";
		return (List<Accounts>) super.executeQuery(hql, null);
	}

	// 查询全部，以分页，通过单位全拼，简称，助记符，进行模糊的查询
	@SuppressWarnings("unchecked")
	public List<Accounts> getMoHuAll(String danwei, String jiancheng,
			String zhujifu, int pageNow, int rowSize) {

		// 滞纳金编号，滞纳金费率（滞纳金设置类），客户，起始日期，结束日期,费用合计，滞纳金合计，收费时间,滞纳金的实收费用，支付方式，备注
		String hql = "from Accounts a where a.client.clientFirmName like ('%"
				+ danwei + "%') and a.client.clientAbbreviation like ('%"
				+ jiancheng + "%') and a.client.clientSign like ('%" + zhujifu
				+ "%') order by a.accountsCreateTime desc";
		return (List<Accounts>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 查询全部，模糊查询数据的总页数
	public int getMoHuInt(String danwei, String jiancheng, String zhujifu,
			int rowSize) {
		String hql = "select count(*) "
				+ "from Accounts a,Client c where a.client = c.clientId and c.clientFirmName like ('%"
				+ danwei + "%') and c.clientAbbreviation like ('%" + jiancheng
				+ "%') and c.clientSign like ('%" + zhujifu
				+ "%') order by a.accountsCreateTime desc";
		return super.queryPageCount(hql, null, rowSize);
	}

	// 通过客户查询
	@SuppressWarnings("unchecked")
	public List<Accounts> getClientByPage(Integer clientId, int pageNow,
			int rowSize) {
		// 滞纳金编号，滞纳金费率（滞纳金设置类），客户，起始日期，结束日期,费用合计，滞纳金合计，收费时间,滞纳金的实收费用，支付方式，备注
		String hql = "select new Accounts(a.accountsId, a.setAccounts,a.client,a.accountsCreateTime,"
				+ "a.accountsFinishTime,a.accountsExpensesSeed,a.accountsSeed,a.accountsCollectTime,"
				+ "a.accountsDefinedOne,a.accountsDefinedTwo,a.accountsRemark) "
				+ "from Accounts a where a.client = "
				+ clientId
				+ " order by a.accountsCreateTime desc";
		return (List<Accounts>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 通过客户查询数据的总页数
	public int getClientByPageCount(Integer clientId, int rowSize) {
		String hql = "select count(*) " + "from Accounts a where a.client = "
				+ clientId + " order by a.accountsCreateTime desc";
		return super.queryPageCount(hql, null, rowSize);
	}

	// 查询未收费的滞纳金订单，以分页的方式显示
	@SuppressWarnings("unchecked")
	public List<Accounts> getWeiShouByPage(String danwei, String jiancheng,
			String zhujifu, int pageNow, int rowSize) {
		// 滞纳金编号，滞纳金费率（），客户，起始日期，结束日期,费用合计，滞纳金合计，收费时间,滞纳金的实收费用，支付方式，备注,入库费，出库费，过户费，二次作业费,仓储费，期末库存重量
		// 收费期限，收费人，状态，自定义4，自定义5，自定义6
		String hql = "select new Accounts(a.accountsId, a.zhinaFeilv,a.client,a.accountsCreateTime,"
				+ "a.accountsFinishTime,a.accountsExpensesSeed,a.accountsSeed,a.accountsCollectTime,"
				+ "a.shishouCost,a.accountsDefinedTwo,a.accountsRemark,a.rukuCost,a.chukuCost,a.guohuCost,"
				+ "a.ercizuoyeCost,a.cangchuCost,a.qimoWeight,a.shoufeiqixian,a.shoufeiren,a.zhuangtai,a.zidingyiFour,a.zidingyiFive,a.zidingyiSix) "
				+ "from Accounts a,Client c where a.client=c.clientId "
				+ " and c.clientFirmName like ('%"
				+ danwei
				+ "%') and c.clientAbbreviation like ('%"
				+ jiancheng
				+ "%')  and c.clientSign like ('%"
				+ zhujifu
				+ "%') and a.zhuangtai='未收费' order by a.accountsCreateTime desc";
		return (List<Accounts>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 查询未收费的总页数
	public int getWeiShouByPageCount(String danwei, String jiancheng,
			String zhujifu, int rowSize) {
		String hql = "select count(*) "
				+ "from Accounts a,Client c where a.client=c.clientId "
				+ " and c.clientFirmName like ('%"
				+ danwei
				+ "%') and c.clientAbbreviation like ('%"
				+ jiancheng
				+ "%')  and c.clientSign like ('%"
				+ zhujifu
				+ "%') and a.zhuangtai='未收费' order by a.accountsCreateTime desc";
		return super.queryPageCount(hql, null, rowSize);
	}

	// 通过开始日期，结束日期，客户，统计应收费用的总和,并且订单不是作废的，
	@SuppressWarnings("unchecked")
	public List<Accounts> TJRuKuShouldCost(String begin, String finish,
			String clientName, int pageNow, int rowSize) {
		// 入库费用总和，入库费用总和，二次作业费用总和，过户应收费用总和，客户
		String hql = "select new Accounts(SUM(is.iseedShouldCost),SUM(es.eseedShouldCost),SUM(es.eseedSwshouldCost),SUM(ss.ssseedShouldCost),c) "
				+ "from InputSeed is,ExportSeed es, ShiftStockSeed ss,Client c where "
				+ " is.input.inputCreateTime > '"
				+ begin
				+ "' and is.input.inputCreateTime <= '"
				+ finish
				+ "' and c.clientAbbreviation like ('%"
				+ clientName
				+ "%') and c.clientId=is.input.client.clientId"
				+ " and is.iseedOrderStatus='未收费' and is.iseedClientAccounts = '月结'"
				+ " and es.export.exportReateTime > '"
				+ begin
				+ "' and es.export.exportReateTime <= '"
				+ finish
				+ "' and es.eseedOrderStatus = '未收费' and es.eseedClientAccounts = '月结' "
				+ " and es.export.client.clientId=c.clientId and ss.shiftStock.sstockReateTime > '"
				+ begin
				+ "' and ss.shiftStock.sstockReateTime <= '"
				+ finish
				+ "' and ss.ssseedClientAccounts='月结' and iseedOrderStatus='未收费' "
				+ " and c.clientId=ss.shiftStock.clientBySstockShiftToFirm.clientId group by c order by c.clientId desc ";
		return (List<Accounts>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 通过开始日期，结束日期，客户，统计入库重量的总和，计算相应的期末库存
	@SuppressWarnings("unchecked")
	public List<Accounts> TJRuKuWeigthSum(String begin, String finish,
			String clientName, int pageNow, int rowSize) {
		String hql = "select new Accounts((SUM(is.iseedRealityWeight)+SUM(ss.ssseedWeight))-(SUM(es.eseedRealityWeight)+SUM(shifts.ssseedWeight)),c) "
				+ "from InputSeed is,ExportSeed es, ShiftStockSeed ss,Client c,ShiftStockSeed shifts where "
				+ " is.input.inputCreateTime > '"
				+ begin
				+ "' and is.input.inputCreateTime <= '"
				+ finish
				+ "'and is.iseedOrderStatus!='订单作废' and c.clientAbbreviation like ('%"
				+ clientName
				+ "%') and c.clientId=is.input.client.clientId"
				+ ""
				+ " and es.export.exportReateTime > '"
				+ begin
				+ "' and es.export.exportReateTime <= '"
				+ finish
				+ "' "
				+ " and es.export.client.clientId=c.clientId and ss.shiftStock.sstockReateTime > '"
				+ begin
				+ "' and ss.shiftStock.sstockReateTime <= '"
				+ finish
				+ "' "
				+ " and c.clientId=ss.shiftStock.clientBySstockShiftToFirm.clientId "
				+ " and shifts.shiftStock.sstockReateTime > '"
				+ begin
				+ "' and shifts.shiftStock.sstockReateTime <= '"
				+ finish
				+ "' and shifts.shiftStock.clientBySstockClientId.clientId=c.clientId group by c order by c.clientId desc";

		return (List<Accounts>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 查询大于上传入日期的期末库存
	@SuppressWarnings("unchecked")
	public List<Accounts> TJZuiDaKuCun(String finish, String clientName,
			int pageNow, int rowSize) {

		String hql = "select new Accounts(SUM(cg.cgoodsWeight)-((SUM(is.iseedRealityWeight)+SUM(ss.ssseedWeight))-(SUM(es.eseedRealityWeight)+SUM(shifts.ssseedWeight))),c) "
				+ "from InputSeed is,ExportSeed es, ShiftStockSeed ss,Client c,ShiftStockSeed shifts,ClientGoods cg where "
				+ "' and is.input.inputCreateTime > '"
				+ finish
				+ "'and is.iseedOrderStatus!='订单作废' and c.clientAbbreviation like ('%"
				+ clientName
				+ "%') and c.clientId=is.input.client.clientId"
				+ ""
				+ "' and es.export.exportReateTime > '"
				+ finish
				+ "' "
				+ " and es.export.client.clientId=c.clientId "
				+ "' and ss.shiftStock.sstockReateTime > '"
				+ finish
				+ "' "
				+ " and c.clientId=ss.shiftStock.clientBySstockShiftToFirm.clientId "
				+ "' and shifts.shiftStock.sstockReateTime > '"
				+ finish
				+ "' and shifts.shiftStock.clientBySstockClientId.clientId=c.clientId "
				+ "and c.clientId=cg.client.clientId group by c order by c.clientId desc";
		return (List<Accounts>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 查询客户出库业务和转出库业务的最大时间
	@SuppressWarnings("unchecked")
	public List<Accounts> TJChuKuMaxTime(String clientName, int pageNow,
			int rowSize) {
		String hql = "select new Accounts(max(es.export.exportReateTime),max(shifts.shiftStock.sstockReateTime),c) "
				+ "from ExportSeed es,Client c,ShiftStockSeed shifts where "
				+ " c.clientAbbreviation like ('%"
				+ clientName
				+ " and es.export.client.clientId=c.clientId and "
				+ "' shifts.shiftStock.clientBySstockClientId.clientId=c.clientId group by c order by c.clientId desc";
		return (List<Accounts>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 查询没有收费的记录
	@SuppressWarnings("unchecked")
	public List<Accounts> getCostRecordByPage(String begin, String finish,
			String jiancheng, String type, int pageNow, int rowSize,
			String shoufeiren) {
		String hql = "from Accounts where client.clientAbbreviation like ('%"
				+ jiancheng + "%')";
		if (type.equals("未收费")) {
			hql += " and zhuangtai='未收费'";
		} else {
			hql += " and accountsCollectTime >= '" + begin
					+ "' and accountsCollectTime <= '" + finish
					+ "'  and shoufeiren.iuserName like ('%" + shoufeiren
					+ "%') ";
		}
		hql += " order by accountsCreateTime desc";
		return (List<Accounts>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 查询没有收费记录的总页数
	public int getCostRecordByPageCount(String begin, String finish,
			String jiancheng, String type, int rowSize, String shoufeiren) {
		String hql = "select count(*) from Accounts where client.clientAbbreviation like ('%"
				+ jiancheng + "%')";
		if (type.equals("未收费")) {
			hql += " and zhuangtai='未收费'";
		} else {
			hql += " and accountsCollectTime>='" + begin
					+ "' and accountsCollectTime<='" + finish
					+ "' and shoufeiren.iuserName like ('%" + shoufeiren
					+ "%')";
		}
		return super.queryPageCount(hql, null, rowSize);
	}

	// ------------app客户查询收费记录

	// 查询没有收费的记录
	@SuppressWarnings("unchecked")
	public List<Accounts> getCostRecordByPageKH(Integer clientId, String begin,
			String finish, String type, int pageNow, int rowSize) {
		String hql = "from Accounts where client.clientId =" + clientId + "";
		if (type.equals("未收费")) {
			hql += " and zhuangtai='未收费'";
		} else {
			hql += " and accountsCollectTime >= '" + begin
					+ "' and accountsCollectTime <= '" + finish + "'";
		}
		hql += " order by accountsCreateTime desc";
		return (List<Accounts>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 查询没有收费记录的总页数
	public int getCostRecordByPageCountKH(Integer clientId, String begin,
			String finish, String type, int rowSize) {
		String hql = "select count(*) from Accounts where client.clientId ="
				+ clientId + "";
		if (type.equals("未收费")) {
			hql += " and zhuangtai='未收费'";
		} else {
			hql += " and accountsCollectTime>='" + begin
					+ "' and accountsCollectTime<='" + finish + "'";
		}
		return super.queryPageCount(hql, null, rowSize);
	}

	// 通过客户进行查询结算的信息和收费的信息
	@SuppressWarnings("unchecked")
	public List<Accounts> getAppQuery(Integer ClientId, String type) {
		String hql = "from Accounts where client.clientId =" + ClientId;
		if (type.equals("未收费")) {
			hql += " and zhuangtai = '未收费'";
		} else {
			hql += " and zhuangtai != '未收费'";
		}
		hql += " order by accountsCreateTime desc";
		return (List<Accounts>) super.executeQuery(hql, null);
	}

	// 查询对应客户的费用总和，并且是未收费的
	public Double getMoneySum(Integer clientId) {
		String hql = "select sum(accountsExpensesSeed) from Accounts where client.clientId ="
				+ clientId + " and zhuangtai='未收费'";
		return (Double) super.executeQuery(hql, null).get(0);
	}

	// 通过客户和起始时间、结束时间查询对应的结算费用
	@SuppressWarnings("unchecked")
	public List<Accounts> getClientAccount(Integer clientId, String begin,
			String finish, String zhuangtai) {
		String hql = "from Accounts where client.clientId =" + clientId
				+ " and zhuangtai='" + zhuangtai
				+ "' and accountsCreateTime>='" + begin
				+ "' and accountsCreateTime <= '" + finish
				+ "' order by accountsCreateTime desc";
		return (List<Accounts>) super.executeQuery(hql, null);
	}

}
