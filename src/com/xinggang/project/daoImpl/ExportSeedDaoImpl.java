package com.xinggang.project.daoImpl;

import java.util.List;

import com.xinggang.project.base.BaseDaoImpl;
import com.xinggang.project.dao.ExportSeedDao;
import com.xinggang.project.entity.ExportSeed;

/**
 * 出库子订单Dao实现类
 * 
 * @author Administrator
 * 
 */
public class ExportSeedDaoImpl extends BaseDaoImpl implements ExportSeedDao {
	// 增
	public boolean save(ExportSeed exportSeed) {
		return super.BaseSave(exportSeed);
	}

	// 删,此项目中不能删除
	public boolean delete(ExportSeed exportSeed) {
		ExportSeed e = (ExportSeed) super.findById(ExportSeed.class,
				exportSeed.getEseedId());
		e.setEseedOrderStatus("订单作废");
		return super.BaseUpdate(e);
	}

	// 改
	public boolean update(ExportSeed exportSeed) {
		return super.BaseUpdate(exportSeed);
	}

	// 通过id查询
	public ExportSeed getExportSeedId(String id) {
		return (ExportSeed) super.findById(ExportSeed.class, id);
	}

	// 查询全部
	@SuppressWarnings("unchecked")
	public List<ExportSeed> getAll() {
		String hql = "from ExportSeed where eseedOrderStatus!='订单作废'";
		return (List<ExportSeed>) super.executeQuery(hql, null);
	}

	// 通过总订单编号查询
	@SuppressWarnings("unchecked")
	public List<ExportSeed> getExportId(String exportId) {
		String hql = "from ExportSeed where eseedOrderStatus!='订单作废' and export= '"
				+ exportId + "'";
		return (List<ExportSeed>) super.executeQuery(hql, null);
	}

	// 通过货物编号查询
	@SuppressWarnings("unchecked")
	public List<ExportSeed> getGoodsId(Integer goodsId) {
		String hql = "from ExportSeed where eseedOrderStatus!='订单作废' and goods="
				+ goodsId;
		return (List<ExportSeed>) super.executeQuery(hql, null);
	}

	// 通过状态查询
	@SuppressWarnings("unchecked")
	public List<ExportSeed> getZhuangtai(String zhuang) {
		String hql = "from ExportSeed where eseedOrderStatus= '" + zhuang + "'";
		return (List<ExportSeed>) super.executeQuery(hql, null);
	}

	// 通过结算方式查询
	@SuppressWarnings("unchecked")
	public List<ExportSeed> getJiesuan(String jiesuan) {
		String hql = "from ExportSeed where eseedClientAccounts= '" + jiesuan
				+ "' and eseedOrderStatus!='订单作废'";
		return (List<ExportSeed>) super.executeQuery(hql, null);
	}

	// 查询待处理的订单，也就是计划出库的订单
	@SuppressWarnings("unchecked")
	public List<ExportSeed> getDaiChuLiByPage(String begin, String finish,
			String zongbianhao, String kehubianhao, String danwei,
			String danweizhujifu, String jiancheng, String goodsName,
			String guige, String caizhi, String shuxing, String zhujifu,
			int pageNow, int rowSize) {
		// 编号，支付方式，出库总订单，货物，应出重量，实出重量,应出件数，实出件数，应收费用，实收费用，二次作业重量，二次作业应收费用，二次作业实收费用，结算方式，订单状态，预留字段一，预留字段二，备注
		String hql = "from ExportSeed where export.exportWagonNumber like ('%"
				+ kehubianhao
				+ "%') and (export.client.clientFirmName like ('%"
				+ jiancheng
				+ "%') or export.client.clientSign like('%"
				+ jiancheng
				+ "%') or export.client.clientAbbreviation like ('%"
				+ jiancheng
				+ "%')) and (eseedOrderStatus='计划出库' or eseedOrderStatus='准备出库') order by export.exportReateTime desc";
		return (List<ExportSeed>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 查询待处理订单的全部页数
	public int getDaiChuLiByPageCount(String begin, String finish,
			String zongbianhao, String kehubianhao, String danwei,
			String danweizhujifu, String jiancheng, String goodsName,
			String guige, String caizhi, String shuxing, String zhujifu,
			int rowSize) {
		String hql = "select count(*) from ExportSeed where export.exportWagonNumber like ('%"
				+ kehubianhao
				+ "%') and (export.client.clientFirmName like ('%"
				+ jiancheng
				+ "%') or export.client.clientSign like('%"
				+ jiancheng
				+ "%') or export.client.clientAbbreviation like ('%"
				+ jiancheng
				+ "%')) and (eseedOrderStatus='计划出库' or eseedOrderStatus='准备出库')";
		return super.queryPageCount(hql, null, rowSize);
	}

	// 查询配送的订单
	@SuppressWarnings("unchecked")
	public List<ExportSeed> getPeiSongByPage(String begin, String finish,
			String zongbianhao, String kehubianhao, String danwei,
			String danweizhujifu, String jiancheng, String goodsName,
			String guige, String caizhi, String shuxing, String zhujifu,
			int pageNow, int rowSize) {
		String hql = "from ExportSeed where export.exportWagonNumber like ('%"
				+ kehubianhao
				+ "%') and (export.client.clientFirmName like ('%"
				+ jiancheng
				+ "%') or export.client.clientSign like('%"
				+ jiancheng
				+ "%') or export.client.clientAbbreviation like ('%"
				+ jiancheng
				+ "%')) and export.exportRemark = '配送' and (eseedOrderStatus='计划出库' or eseedOrderStatus='准备出库') order by export.exportReateTime asc";
		return (List<ExportSeed>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 查询配送订单的页数
	public int getPeiSongByPageCount(String begin, String finish,
			String zongbianhao, String kehubianhao, String danwei,
			String danweizhujifu, String jiancheng, String goodsName,
			String guige, String caizhi, String shuxing, String zhujifu,
			int rowSize) {
		String hql = "select count(*) from ExportSeed where export.exportWagonNumber like ('%"
				+ kehubianhao
				+ "%') and (export.client.clientFirmName like ('%"
				+ jiancheng
				+ "%') or export.client.clientSign like('%"
				+ jiancheng
				+ "%') or export.client.clientAbbreviation like ('%"
				+ jiancheng
				+ "%')) and export.exportRemark = '配送' and (eseedOrderStatus='计划出库' or eseedOrderStatus='准备出库')";
		return super.queryPageCount(hql, null, rowSize);
	}

	// 查询全部的订单，去除订单作废的订单，
	// 通过起始日期，结束日期，订单编号，客户订单编号，货物名称，规格，产地，品类，客户简称，助记符，全拼，
	@SuppressWarnings("unchecked")
	public List<ExportSeed> getChuKuLiShiByPage(String begin, String finish,
			String zongbianhao, String kehubianhao, String goodsName,
			String huozhu, int pageNow, int rowSize) {
		String hql = "from ExportSeed where export.exportClientNumber like ('%"
				+ kehubianhao
				+ "%') and (export.client.clientFirmName like ('%"
				+ huozhu
				+ "%') or export.client.clientSign like('%"
				+ huozhu
				+ "%') or export.client.clientAbbreviation like ('%"
				+ huozhu
				+ "%')) and export.exportId like ('%"
				+ zongbianhao
				+ "%') and export.exportReateTime >= '"
				+ begin
				+ "' and export.exportReateTime<='"
				+ finish
				+ "' and (goods.goodsCategory.goodsCategoryName like ('%"
				+ goodsName
				+ "%') or goods.goodsName like ('%"
				+ goodsName
				+ "%') or goods.goodsSign like ('%"
				+ goodsName
				+ "%') or goods.goodsStandard.goodsStandardName like ('%"
				+ goodsName
				+ "%') or goods.goodsYieldly.goodsYieldlyName like ('%"
				+ goodsName
				+ "%')) and eseedOrderStatus!='订单作废'  order by export.exportReateTime desc";

		return (List<ExportSeed>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 通过起始日期，结束日期，订单编号，客户订单编号，货物名称，规格，产地，品类，客户简称，助记符，全拼，查询总页数
	public int getChuKuLiShiByPageCount(String begin, String finish,
			String zongbianhao, String kehubianhao, String goodsName,
			String huozhu, int rowSize) {
		String hql = "select count(*) from ExportSeed where export.exportClientNumber like ('%"
				+ kehubianhao
				+ "%') and (export.client.clientFirmName like ('%"
				+ huozhu
				+ "%') or export.client.clientSign like('%"
				+ huozhu
				+ "%') or export.client.clientAbbreviation like ('%"
				+ huozhu
				+ "%')) and export.exportId like ('%"
				+ zongbianhao
				+ "%') and export.exportReateTime >= '"
				+ begin
				+ "' and export.exportReateTime<='"
				+ finish
				+ "' and (goods.goodsCategory.goodsCategoryName like ('%"
				+ goodsName
				+ "%') or goods.goodsName like ('%"
				+ goodsName
				+ "%') or goods.goodsSign like ('%"
				+ goodsName
				+ "%') or goods.goodsStandard.goodsStandardName like ('%"
				+ goodsName
				+ "%') or goods.goodsYieldly.goodsYieldlyName like ('%"
				+ goodsName + "%')) and eseedOrderStatus!='订单作废'";
		return super.queryPageCount(hql, null, rowSize);
	}

	// 查询作废全部的订单，去除订单作废的订单，
	// 通过起始日期，结束日期，订单编号，客户订单编号，货物名称，规格，产地，品类，客户简称，助记符，全拼，
	@SuppressWarnings("unchecked")
	public List<ExportSeed> getChuKuLiShiZuoFeiByPage(String begin,
			String finish, String zongbianhao, String kehubianhao,
			String goodsName, String huozhu, int pageNow, int rowSize) {
		String hql = "from ExportSeed where export.exportClientNumber like ('%"
				+ kehubianhao
				+ "%') and (export.client.clientFirmName like ('%"
				+ huozhu
				+ "%') or export.client.clientSign like('%"
				+ huozhu
				+ "%') or export.client.clientAbbreviation like ('%"
				+ huozhu
				+ "%')) and export.exportId like ('%"
				+ zongbianhao
				+ "%') and export.exportReateTime >= '"
				+ begin
				+ "' and export.exportReateTime<='"
				+ finish
				+ "' and (goods.goodsCategory.goodsCategoryName like ('%"
				+ goodsName
				+ "%') or goods.goodsName like ('%"
				+ goodsName
				+ "%') or goods.goodsSign like ('%"
				+ goodsName
				+ "%') or goods.goodsStandard.goodsStandardName like ('%"
				+ goodsName
				+ "%') or goods.goodsYieldly.goodsYieldlyName like ('%"
				+ goodsName
				+ "%')) and eseedOrderStatus='订单作废'  order by export.exportReateTime desc";

		return (List<ExportSeed>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 通过起始日期，结束日期，订单编号，客户订单编号，货物名称，规格，产地，品类，客户简称，助记符，全拼，查询总页数
	public int getChuKuLiShiZuoFeiByPageCount(String begin, String finish,
			String zongbianhao, String kehubianhao, String goodsName,
			String huozhu, int rowSize) {
		String hql = "select count(*) from ExportSeed where export.exportClientNumber like ('%"
				+ kehubianhao
				+ "%') and (export.client.clientFirmName like ('%"
				+ huozhu
				+ "%') or export.client.clientSign like('%"
				+ huozhu
				+ "%') or export.client.clientAbbreviation like ('%"
				+ huozhu
				+ "%')) and export.exportId like ('%"
				+ zongbianhao
				+ "%') and export.exportReateTime >= '"
				+ begin
				+ "' and export.exportReateTime<='"
				+ finish
				+ "' and (goods.goodsCategory.goodsCategoryName like ('%"
				+ goodsName
				+ "%') or goods.goodsName like ('%"
				+ goodsName
				+ "%') or goods.goodsSign like ('%"
				+ goodsName
				+ "%') or goods.goodsStandard.goodsStandardName like ('%"
				+ goodsName
				+ "%') or goods.goodsYieldly.goodsYieldlyName like ('%"
				+ goodsName + "%')) and eseedOrderStatus='订单作废'";
		return super.queryPageCount(hql, null, rowSize);
	}

	// -----------------------------------app客户查询出所有未作废的订单

	// 客户app客户根据条件查询入库订单
	@SuppressWarnings("unchecked")
	public List<ExportSeed> getChukuInfo(Integer kehuId, String jiesuanType,
			String startTime, String endTime, String kehuDanhao, String huowu,
			int pageNow, int rowSize) {

		String hql = "from ExportSeed where export.client.clientId=" + kehuId
				+ " and (eseedClientAccounts like '%" + jiesuanType + "%')"
				+ " and export.exportClientNumber like '%" + kehuDanhao + "%' "
				+ " and (goods.goodsName like '%" + huowu + "%' "
				+ " or goods.goodsSign like '%" + huowu
				+ "%') and eseedOrderStatus!='订单作废'";

		if (startTime != "" || endTime != "") {
			hql += " and (export.exportReateTime between '" + startTime + "' "
					+ " and '" + endTime
					+ "')  order by export.exportReateTime desc";
		} else {
			hql += " order by export.exportReateTime desc";
		}

		return (List<ExportSeed>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 客户app查询入库订单,返回行数
	public int getChukuCount(Integer kehuId, String jiesuanType,
			String startTime, String endTime, String kehuDanhao, String huowu,
			int pageSize) {
		String hql = "select count(*) from ExportSeed where export.client.clientId="
				+ kehuId
				+ " and (eseedClientAccounts like '%" + jiesuanType + "%')"
				+ " and export.exportClientNumber like '%"
				+ kehuDanhao
				+ "%' "
				+ " and (goods.goodsName like '%"
				+ huowu
				+ "%' "
				+ " or goods.goodsSign like '%"
				+ huowu
				+ "%') and eseedOrderStatus!='订单作废' ";
		// 如果时间不为空，加入时间范围
		if (startTime != "" || endTime != "") {
			hql += " and (export.exportReateTime between '" + startTime + "' "
					+ " and '" + endTime + "')";
		}
		return super.queryPageCount(hql, null, pageSize);
	}

	// 客户app查询入库订单
	@SuppressWarnings("unchecked")
	public List<ExportSeed> getChukuInfoAll(Integer kehuId,String begin,String finish, int pageNow,
			int rowSize) {

		String hql = "from ExportSeed where export.client.clientId="
				+ kehuId
				+ " and eseedOrderStatus!='订单作废' and (export.exportReateTime between '" + begin + "' "
					+ " and '" + finish
					+ "') order by export.exportReateTime desc";
		return (List<ExportSeed>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 客户app查询入库订单,返回行数
	public int getChukuCountAll(Integer kehuId,String begin,String finish, int pageSize) {
		String hql = "select count(*) from ExportSeed where export.client.clientId="
				+ kehuId + " and eseedOrderStatus!='订单作废' and (export.exportReateTime between '" + begin + "' "
					+ " and '" + finish
					+ "')";

		return super.queryPageCount(hql, null, pageSize);
	}

	// -----------------------------------app客户查询出所有作废的订单

	// 客户app客户根据条件查询入库订单
	@SuppressWarnings("unchecked")
	public List<ExportSeed> getChukuInfoPash(Integer kehuId, String startTime,
			String endTime, String kehuDanhao, String huowu, int pageNow,
			int rowSize) {

		String hql = "from ExportSeed where export.client.clientId=" + kehuId
				+ " " + " and export.exportClientNumber like '%" + kehuDanhao
				+ "%' " + " and (goods.goodsName like '%" + huowu + "%' "
				+ " or goods.goodsSign like '%" + huowu
				+ "%') and eseedOrderStatus='订单作废'";

		if (startTime != "" && endTime != "") {
			hql += " and (export.exportReateTime between '" + startTime + "' "
					+ " and '" + endTime
					+ "')  order by export.exportReateTime desc";
		} else {
			hql += " order by export.exportReateTime desc";
		}

		return (List<ExportSeed>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 客户app查询入库订单,返回行数
	public int getChukuCountPash(Integer kehuId, String startTime,
			String endTime, String kehuDanhao, String huowu, int pageSize) {
		String hql = "select count(*) from ExportSeed where export.client.clientId="
				+ kehuId
				+ " "
				+ " and export.exportClientNumber like '%"
				+ kehuDanhao
				+ "%' "
				+ " and (goods.goodsName like '%"
				+ huowu
				+ "%' "
				+ " or goods.goodsSign like '%"
				+ huowu
				+ "%') and eseedOrderStatus='订单作废' ";
		// 如果时间不为空，加入时间范围
		if (startTime != "" && endTime != "") {
			hql += " and (export.exportReateTime between '" + startTime + "' "
					+ " and '" + endTime + "')";
		}
		return super.queryPageCount(hql, null, pageSize);
	}

	// 客户app查询入库订单
	@SuppressWarnings("unchecked")
	public List<ExportSeed> getChukuInfoAllPash(Integer kehuId, int pageNow,
			int rowSize) {

		String hql = "from ExportSeed where export.client.clientId="
				+ kehuId
				+ " and eseedOrderStatus='订单作废' order by export.exportReateTime desc";
		return (List<ExportSeed>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 客户app查询入库订单,返回行数
	public int getChukuCountAllPash(Integer kehuId, int pageSize) {
		String hql = "select count(*) from ExportSeed where export.client.clientId="
				+ kehuId + " and eseedOrderStatus='订单作废' ";

		return super.queryPageCount(hql, null, pageSize);
	}

	// 通过货物进行统计转发存的数值,在这一段时间范围内的出库重量的合计
	@SuppressWarnings("unchecked")
	public List<ExportSeed> getZhuanFaCunChuKuGeJi(String begin, String finish,
			Integer goodsId) {
		String hql = "select new ExportSeed(g,SUM(es.eseedRealityWeight)) from ExportSeed es,Goods g where es.export.exportReateTime >= '"
				+ begin
				+ "' and es.export.exportReateTime<='"
				+ finish
				+ "' and es.eseedOrderStatus!='订单作废' and es.goods.goodsId=g.goodsId and g.goodsId="
				+ goodsId + " group by g";
		return (List<ExportSeed>) super.executeQuery(hql, null);
	}

	// 通过货物进行统计期初库存
	@SuppressWarnings("unchecked")
	public List<ExportSeed> getZhuanFaCunChuKuQiChu(String begin,
			Integer goodsId) {
		String hql = "select new ExportSeed(g,SUM(es.eseedRealityWeight)) from ExportSeed es,Goods g where es.export.exportReateTime < '"
				+ begin
				+ "' and es.eseedOrderStatus!='订单作废' and es.goods.goodsId=g.goodsId and g.goodsId = "
				+ goodsId + " group by g";
		return (List<ExportSeed>) super.executeQuery(hql, null);
	}

	// 统计客户的库存，统计的计算方法是：期初库存+（入库实入重量+转入重量）-（出库实出重量+转出重量）=期末库存 **指的是在某个时间段内的重量**
	// 客户的期初库存指的是在开始时间之前的库存：（入库实入重量+转入重量）-（出库实出重量+转出重量）=期初库存**指的是在某个时间段开始时间之前的重量***
	// 先将该时间段内的客户的库存相加，也就是出库的合计
	@SuppressWarnings("unchecked")
	public List<ExportSeed> getKeHuKunCunCKHJ(String begin, String finish,
			Integer clientId, Integer goodsId) {
		String hql = "select new ExportSeed(SUM(es.eseedRealityWeight)) from ExportSeed es where es.export.exportReateTime >= '"
				+ begin
				+ "' and es.export.exportReateTime<='"
				+ finish
				+ "' and es.eseedOrderStatus!='订单作废' and es.goods.goodsId="
				+ goodsId
				+ " and es.export.client.clientId="
				+ clientId
				+ " group by es.goods.goodsId,es.export.client.clientId";
		return (List<ExportSeed>) super.executeQuery(hql, null);
	}

	// 统计客户库存中期初的出库合计
	@SuppressWarnings("unchecked")
	public List<ExportSeed> getKeHuKunCunQCHJ(String begin, Integer clientId,
			Integer goodsId) {
		String hql = "select new ExportSeed(SUM(es.eseedRealityWeight)) from ExportSeed es where es.export.exportReateTime < '"
				+ begin
				+ "' and es.eseedOrderStatus!='订单作废' and es.goods.goodsId = "
				+ goodsId
				+ " and es.export.client.clientId="
				+ clientId
				+ " group by es.goods.goodsId,es.export.client.clientId";
		return (List<ExportSeed>) super.executeQuery(hql, null);
	}

	@SuppressWarnings("unchecked")
	public List<ExportSeed> getSFCYunShu(String begin, String finish,
			Integer clientId, String goodsName, String yunshu) {
		String hql = "select new ExportSeed(SUM(es.eseedRealityWeight)) from ExportSeed es where es.export.exportReateTime >= '"
				+ begin
				+ "' and es.export.exportReateTime<='"
				+ finish
				+ "' and es.eseedOrderStatus!='订单作废' and es.goods.goodsName='"
				+ goodsName
				+ "' and es.export.client.clientId="
				+ clientId
				+ " and es.export.exportCarryType ='"
				+ yunshu
				+ "' group by es.export.client.clientId";
		return (List<ExportSeed>) super.executeQuery(hql, null);
	}

	// 统计汽运和火运的出库费用
	@SuppressWarnings("unchecked")
	public List<ExportSeed> getYunShuJieSuan(String begin, String finish,
			Integer clientId, String yunshu) {
		String hql = "select new ExportSeed(SUM(es.eseedShouldCost)) from ExportSeed es where es.export.exportReateTime >= '"
				+ begin
				+ "' and es.export.exportReateTime<='"
				+ finish
				+ "' and es.eseedOrderStatus!='订单作废' and es.eseedOrderStatus='未收费' and es.export.client.clientId="
				+ clientId
				+ " and es.export.exportCarryType ='"
				+ yunshu
				+ "' and eseedClientAccounts='月结' group by es.export.client.clientId";
		return (List<ExportSeed>) super.executeQuery(hql, null);
	}

	// 统计二次作业的费用
	@SuppressWarnings("unchecked")
	public List<ExportSeed> getErCiZuoYeCost(String begin, String finish,
			Integer clientId) {
		String hql = "select new ExportSeed(SUM(es.eseedSwshouldCost)) from ExportSeed es where es.export.exportReateTime >= '"
				+ begin
				+ "' and es.export.exportReateTime<='"
				+ finish
				+ "' and es.eseedOrderStatus!='订单作废' and es.eseedOrderStatus!='未收费' and es.export.client.clientId="
				+ clientId
				+ " and eseedClientAccounts='月结' group by es.export.client.clientId";
		return (List<ExportSeed>) super.executeQuery(hql, null);
	}

	// 收发存的统计
	@SuppressWarnings("unchecked")
	public List<ExportSeed> getKeHuKunCunCKHJSFC(String begin, String finish,
			Integer clientId, String goodsName) {
		String hql = "select new ExportSeed(SUM(es.eseedRealityWeight)) from ExportSeed es where es.export.exportReateTime >= '"
				+ begin
				+ "' and es.export.exportReateTime<='"
				+ finish
				+ "' and es.eseedOrderStatus!='订单作废' and es.goods.goodsName='"
				+ goodsName
				+ "' and es.export.client.clientId="
				+ clientId
				+ " group by es.export.client.clientId";
		return (List<ExportSeed>) super.executeQuery(hql, null);
	}

	// 统计客户库存中期初的出库合计
	@SuppressWarnings("unchecked")
	public List<ExportSeed> getKeHuKunCunQCHJSFC(String begin,
			Integer clientId, String goodsName) {
		String hql = "select new ExportSeed(SUM(es.eseedRealityWeight)) from ExportSeed es where es.export.exportReateTime < '"
				+ begin
				+ "' and es.eseedOrderStatus!='订单作废' and es.goods.goodsName = '"
				+ goodsName
				+ "' and es.export.client.clientId="
				+ clientId
				+ " group by es.export.client.clientId";
		return (List<ExportSeed>) super.executeQuery(hql, null);
	}

	// 查询月结和日结的费用，通过时间，客户进行查询
	@SuppressWarnings("unchecked")
	public List<ExportSeed> getJieSuanQueryByPage(String clientName,
			String begin, String finish, String jiesuan, int pageNow,
			int rowSize, String shoufeiren) {
		if (jiesuan.equals("日结")) {
			jiesuan = "日结";
		}
		String hql = "from ExportSeed where (export.client.clientSign like ('%"
				+ clientName
				+ "%') or export.client.clientAbbreviation like ('%"
				+ clientName
				+ "%') or export.client.clientFirmName like ('%"
				+ clientName
				+ "%')) and eseedClientAccounts like ('%"
				+ jiesuan
				+ "%')"
				+ " and eseedOrderStatus!='订单作废' and (export.exportReateTime between '"
				+ begin
				+ "' "
				+ " and '"
				+ finish
				+ "') and eseedId in (select exportSeed from ExportOperate where interiorUserByEoperateCollectMan.iuserName like '%"
				+ shoufeiren + "%')  order by export.exportReateTime desc";
		return (List<ExportSeed>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 查询月结和日结的费用，通过时间，客户进行查询总页数
	public int getJieSuanQueryByCount(String clientName, String begin,
			String finish, String jiesuan, int rowSize, String shoufeiren) {
		if (jiesuan.equals("日结")) {
			jiesuan = "日结";
		}
		String hql = "select count(*) from ExportSeed where (export.client.clientSign like ('%"
				+ clientName
				+ "%') or export.client.clientAbbreviation like ('%"
				+ clientName
				+ "%') or export.client.clientFirmName like ('%"
				+ clientName
				+ "%')) and eseedClientAccounts like ('%"
				+ jiesuan
				+ "%')"
				+ " and eseedOrderStatus!='订单作废' and (export.exportReateTime between '"
				+ begin
				+ "' "
				+ " and '"
				+ finish
				+ "') and eseedId in (select exportSeed from ExportOperate where interiorUserByEoperateCollectMan.iuserName like '%"
				+ shoufeiren + "%') ";
		return super.queryPageCount(hql, null, rowSize);
	}

	// app中客户查询订单中日结的订单的费用
	@SuppressWarnings("unchecked")
	public List<ExportSeed> getAppQueryDayCostByPage(Integer clientId,
			String search, int pageNow, int rowSize) {
		String hql = "from ExportSeed where export.client.clientId="
				+ clientId
				+ " and (goods.goodsName like ('%"
				+ search
				+ "%')"
				+ " or goods.goodsSign like ('%"
				+ search
				+ "%') or export.exportClientNumber like ('%"
				+ search
				+ "%') or export.exportWagonNumber like ('%"
				+ search
				+ "%') or goods.goodsYieldly.goodsYieldlyName like ('%"
				+ search
				+ "%')) and eseedClientAccounts = '日结' and eseedOrderStatus!='订单作废' order by eseedId desc";
		return (List<ExportSeed>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// app中客户查询订单中日结的订单的费用的中也属
	public int getAppQueryDayCostByCount(Integer clientId, String search,
			int pageNow, int rowSize) {
		String hql = "select count(*) from ExportSeed where export.client.clientId="
				+ clientId
				+ " and (goods.goodsName like ('%"
				+ search
				+ "%')"
				+ " or goods.goodsSign like ('%"
				+ search
				+ "%') or export.exportClientNumber like ('%"
				+ search
				+ "%') or export.exportWagonNumber like ('%"
				+ search
				+ "%') or goods.goodsYieldly.goodsYieldlyName like ('%"
				+ search
				+ "%')) and eseedClientAccounts = '日结' and eseedOrderStatus!='订单作废'";
		return super.queryPageCount(hql, null, rowSize);
	}

	// 分组出库订单中的年
	@SuppressWarnings("unchecked")
	public List<String> getGroupByYear() {
		String hql = "select convert(varchar(4),exportReateTime,0) from Export group by convert(varchar(4),exportReateTime,0)";
		return ((List<String>) super.QuerySql(hql));
	}
	
	//通过时间查询对应的入、出、过量
	public Double getLikeDate(String date,String type){
		String hql = "";
		if(type.equals("ruku")){
			hql+="select SUM(inputSeed.iseedRealityWeight) from InputOperate where ioperateAuditingTime like ('%"+date+"%')";
		}else if(type.equals("chuku")){
			hql+="select SUM(exportSeed.eseedRealityWeight) from ExportOperate where eoperateAuditingTime like ('%"+date+"%')";
		}else if(type.equals("guohu")){
			hql+="select SUM(ssseedWeight) from ShiftStockSeed where ssseedAuditingTime like ('%"+date+"%')";
		}
		return super.executeQuery(hql,null)==null?0.0:super.executeQuery(hql,null).get(0)==null?0.0:(Double)super.executeQuery(hql,null).get(0);
	}
}
