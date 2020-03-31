package com.xinggang.project.daoImpl;

import java.util.List;

import com.xinggang.project.base.BaseDaoImpl;
import com.xinggang.project.dao.ExportDao;
import com.xinggang.project.entity.Export;
import com.xinggang.project.entity.ExportSeed;

/**
 * 出库总订单Dao实现类
 * 
 * @author Administrator
 * 
 */
public class ExportDaoImpl extends BaseDaoImpl implements ExportDao {
	// 增
	public boolean save(Export export) {
		return super.BaseSave(export);
	}

	// 删，此项目中不能删除
	public boolean delete(Export export) {
		Export e = (Export) super.findById(Export.class, export.getExportId());
		e.setExportOrderStatus("订单作废");
		return super.BaseUpdate(e);
	}

	// 改
	public boolean update(Export export) {
		return super.BaseUpdate(export);
	}

	// 通过id查询
	public Export getExportId(String id) {
		return (Export) super.findById(Export.class, id);
	}

	// 查询全部
	@SuppressWarnings("unchecked")
	public List<Export> getAll() {
		String hql = "from Export order by exportReateTime desc";
		return (List<Export>) super.executeQuery(hql, null);
	}

	// 分页查询全部
	@SuppressWarnings("unchecked")
	public List<Export> getExportByPage(int pageNow, int rowSize) {
		String hql = "from Export where exportOrderStatus!='订单作废' order by exportReateTime desc";
		return (List<Export>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 查询全部数据的总行数
	public int getExportByCount() {
		String hql = "select count(*) from Export where exportOrderStatus!='订单作废'";
		return super.executeQueryRowCount(hql, null);
	}

	// 通过客户查询
	@SuppressWarnings("unchecked")
	public List<Export> getClientAll(Integer ClientId) {
		String hql = "from Export  where exportOrderStatus!='订单作废' and client="
				+ ClientId + " order by exportReateTime desc";
		;
		return (List<Export>) super.executeQuery(hql, null);
	}

	// 通过客户分页查询
	@SuppressWarnings("unchecked")
	public List<ExportSeed> getClientAllByPage(String begin, String finish,
			String goodsName, String zongbianhao, String kehubianhao,
			Integer ClientId, int pageNow, int rowSize) {
		String hql = "select new ExportSeed(es.eseedId,es.paymentFashion,es.export,es.goods,es.eseedShouldWeight,"
				+ "es.eseedRealityWeight,es.eseedShouldNumber,es.eseedRealityNumber,es.eseedShouldCost,es.eseedRealityCost,es.eseedSecondWork,"
				+ "es.eseedSwshouldCost,es.eseedSwrealityCost,es.eseedClientAccounts,es.eseedOrderStatus,es.eseedDefinedOne,"
				+ "es.eseedDefinedTwo,es.eseedRemark) from Export e,ExportSeed es,Goods g where e.exportOrderStatus!='订单作废' and e.client="
				+ ClientId
				+ " and e.exportReateTime >= '"
				+ begin
				+ "' and e.exportReateTime <='"
				+ finish
				+ "' and g.goodsName like ('%"
				+ goodsName
				+ "%') and e.exportId like ('%"
				+ zongbianhao
				+ "%') and e.exportClientNumber like('%"
				+ kehubianhao
				+ "%') and e.exportId = es.export and g.goodsId =es.goods order by e.exportReateTime desc";
		return (List<ExportSeed>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 通过客户查询数据的总行数
	public int getClientAllByCount(String begin, String finish,
			String goodsName, String zongbianhao, String kehubianhao,
			Integer ClientId, int rowSize) {
		String hql = "select count(*) from Export e,ExportSeed es,Goods g where e.exportOrderStatus!='订单作废' and e.client="
				+ ClientId
				+ " and e.exportReateTime >= '"
				+ begin
				+ "' and e.exportReateTime <='"
				+ finish
				+ "' and g.goodsName like ('%"
				+ goodsName
				+ "%') and e.exportId like ('%"
				+ zongbianhao
				+ "%') and e.exportClientNumber like('%"
				+ kehubianhao
				+ "%') and e.exportId = es.export and g.goodsId =es.goods";
		return super.queryPageCount(hql, null, rowSize);
	}

	// 通过时间范围查询
	@SuppressWarnings("unchecked")
	public List<Export> getShijian(String begin, String finish, String dingdan) {
		String hql = "from Export where exportOrderStatus!='订单作废' and exportReateTime>= '"
				+ begin
				+ "' and exportReateTime<= '"
				+ finish
				+ "' and exportClientNumber like ('%"
				+ dingdan
				+ "%') order by exportReateTime desc";
		return (List<Export>) super.executeQuery(hql, null);
	}

	// 通过时间范围分页查询
	@SuppressWarnings("unchecked")
	public List<Export> getShijianByPage(String begin, String finish,
			String dingdan, int pageNow, int rowSize) {
		String hql = "from Export where exportOrderStatus!='订单作废' and exportReateTime>= '"
				+ begin
				+ "' and exportReateTime<= '"
				+ finish
				+ "' and exportClientNumber like ('%"
				+ dingdan
				+ "%') order by exportReateTime desc";
		return (List<Export>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 通过时间范围查询数据的总行数
	public int getShijianByCount(String begin, String finish, String dingdan) {
		String hql = "select count(*) from Export where exportOrderStatus!='订单作废' and exportReateTime>= '"
				+ begin
				+ "' and exportReateTime<= '"
				+ finish
				+ "' and exportClientNumber like ('%"
				+ dingdan
				+ "%') order by exportReateTime desc";
		return super.executeQueryRowCount(hql, null);
	}

	// 通过状态查询
	@SuppressWarnings("unchecked")
	public List<Export> getZhuangtai(String zhuang) {
		String hql = "from Export where exportOrderStatus= '" + zhuang + "'";
		return (List<Export>) super.executeQuery(hql, null);
	}

	// 通过状态分页查询
	@SuppressWarnings("unchecked")
	public List<Export> getZhuangtaiByPage(String zhuang, int pageNow,
			int rowSize) {
		String hql = "from Export where exportOrderStatus= '" + zhuang + "'"
				+ " order by exportReateTime desc";
		return (List<Export>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 通过状态查询数据的总行数
	public int getZhuangtaiByCount(String zhuang) {
		String hql = "select count(*) from Export where exportOrderStatus="
				+ zhuang;
		return super.executeQueryRowCount(hql, null);
	}
}
