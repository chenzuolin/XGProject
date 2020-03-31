package com.xinggang.project.daoImpl;

import java.util.List;

import com.xinggang.project.base.BaseDaoImpl;
import com.xinggang.project.dao.TarehouseGoodsDao;
import com.xinggang.project.entity.TarehouseGoods;

/**
 * 货物库存Dao实现类
 * 
 * @author Administrator
 * 
 */
public class TarehouseGoodsDaoImpl extends BaseDaoImpl implements
		TarehouseGoodsDao {
	// 增加
	public boolean save(TarehouseGoods tarehouseGoods) {
		return super.BaseSave(tarehouseGoods);
	}

	// 删除
	public boolean delete(TarehouseGoods tarehouseGoods) {
		return super.BaseDelete(tarehouseGoods);
	}

	// 修改
	public boolean update(TarehouseGoods tarehouseGoods) {
		return super.BaseUpdate(tarehouseGoods);
	}

	// 通过id查询
	public TarehouseGoods getTarehouseGoodsId(Integer id) {
		String hql = "from TarehouseGoods where tgoodsId=" + id;
		return (TarehouseGoods) super.findById(hql);
	}

	// 查询全部
	@SuppressWarnings("unchecked")
	public List<TarehouseGoods> getAll() {
		String hql = "from TarehouseGoods";
		return (List<TarehouseGoods>) super.executeQuery(hql, null);
	}

	// 通过库位和货物模糊分页查询
	@SuppressWarnings("unchecked")
	public List<TarehouseGoods> getTarehouseGoodsByPage(Integer kuwei,
			Integer goodsId, int pageNow, int rowSize) {
		String hql = "from TarehouseGoods where goods=" + goodsId
				+ " and tarehouse=" + kuwei + " order by tgoodsId desc";
		return (List<TarehouseGoods>) super.executeQueryByPage(hql, null,
				pageNow, rowSize);
	}

	// 通过库位和货物模糊分页查询
	public int getTarehouseGoodsByCount(Integer kuwei, Integer goodsId) {
		String hql = "select count(*) from TarehouseGoods where goods="
				+ goodsId + " and tarehouse=" + kuwei
				+ " order by tgoodsId desc";
		return super.executeQueryRowCount(hql, null);
	}

	// 通过货物查询
	@SuppressWarnings("unchecked")
	public List<TarehouseGoods> getGoods(Integer goodsId) {
		String hql = "from TarehouseGoods where goods=" + goodsId
				+ " and tgoodsWeight > 0";
		return (List<TarehouseGoods>) super.executeQuery(hql, null);
	}

	// 通过库位查询
	@SuppressWarnings("unchecked")
	public List<TarehouseGoods> getKuwei(Integer kuwei) {
		String hql = "from TarehouseGoods where tarehouse=" + kuwei;
		return (List<TarehouseGoods>) super.executeQuery(hql, null);
	}

	// 通过库位和货物同时查询
	@SuppressWarnings("unchecked")
	public List<TarehouseGoods> tggetAllList(Integer kuwei, Integer goodsId) {
		String hql = "from TarehouseGoods where tarehouse=" + kuwei
				+ "and goods=" + goodsId;
		return (List<TarehouseGoods>) super.executeQuery(hql, null);
	}

	// 查询货物的全部，以模糊查询的方式
	@SuppressWarnings("unchecked")
	public List<TarehouseGoods> getTarehouseGoodsAllByPage(String goodsName,
			String guige, String caizhi, String shuxing, String zhujifu,
			String kuName, String chandi, String pinlei, int pageNow,
			int rowSize) {

		// 编号，货物，库位，重量，件数，预留字段一，预留字段二，备注
		String hql = "select new TarehouseGoods(tg.tgoodsId,tg.goods,tg.tarehouse,tg.tgoodsWeight,tg.tgoodsNumber,"
				+ "tg.tgoodsDefinedTwo,tg.tgoodsDefinedOne,tg.tgoodsRemark) "
				+ "from TarehouseGoods tg,Goods g,Tarehouse t,GoodsCategory gc,GoodsYieldly gy,GoodsProperty gp,GoodsQuality gq,GoodsStandard gs "
				+ "where tg.goods = g.goodsId and tg.tarehouse = t.tarehouseId and gc.goodsCategoryId = g.goodsCategory and "
				+ "g.goodsYieldly = gy.goodsYieldlyId and g.goodsName like ('%"
				+ goodsName
				+ "%') and g.goodsSign like ('%"
				+ zhujifu
				+ "%') and gs.goodsStandardName like ('%"
				+ guige
				+ "%') and gq.goodsQualityName like ('%"
				+ caizhi
				+ "%') and gp.goodsPropertyName like ('%"
				+ shuxing
				+ "%') and gc.goodsCategoryName like ('%"
				+ pinlei
				+ "%') and gy.goodsYieldlyName like ('%"
				+ chandi
				+ "%') and t.tarehouseName like ('%"
				+ kuName
				+ "%') and g.goodsStandard=gs.goodsStandardId and g.goodsQuality=gq.goodsQualityId "
				+ " and g.goodsProperty=gp.goodsPropertyId  order by tgoodsId desc";
		return (List<TarehouseGoods>) super.executeQueryByPage(hql, null,
				pageNow, rowSize);
	}

	// 查询全部数据的总页数
	public int getTarehouseGoodsAllByPageCount(String goodsName, String guige,
			String caizhi, String shuxing, String zhujifu, String kuName,
			String chandi, String pinlei, int rowSize) {
		String hql = "select count(*) "
				+ "from TarehouseGoods tg,Goods g,Tarehouse t,GoodsCategory gc,GoodsYieldly gy,GoodsProperty gp,GoodsQuality gq,GoodsStandard gs "
				+ "where tg.goods = g.goodsId and tg.tarehouse = t.tarehouseId and gc.goodsCategoryId = g.goodsCategory and "
				+ "g.goodsYieldly = gy.goodsYieldlyId and g.goodsName like ('%"
				+ goodsName
				+ "%') and g.goodsSign like ('%"
				+ zhujifu
				+ "%') and gs.goodsStandardName like ('%"
				+ guige
				+ "%') and gq.goodsQualityName like ('%"
				+ caizhi
				+ "%') and gp.goodsPropertyName like ('%"
				+ shuxing
				+ "%') and gc.goodsCategoryName like ('%"
				+ pinlei
				+ "%') and gy.goodsYieldlyName like ('%"
				+ chandi
				+ "%') and t.tarehouseName like ('%"
				+ kuName
				+ "%') and g.goodsStandard=gs.goodsStandardId and g.goodsQuality=gq.goodsQualityId "
				+ " and g.goodsProperty=gp.goodsPropertyId";
		return super.queryPageCount(hql, null, rowSize);
	}

	// 当盘库的时候查询库存在盘库表里没有发起的，并且是当日的时间，并且以分页的方式显示
	@SuppressWarnings("unchecked")
	public List<TarehouseGoods> getChecksDataByPage(String search,
			String begin, String finish, int pageNow, int rowSize) {
		String hql = "from TarehouseGoods tg where tg.tgoodsId not in(select cs.tarehouseGoods.tgoodsId from"
				+ " Checks cs,TarehouseGoods tgs where cs.tarehouseGoods.tgoodsId=tgs.tgoodsId and cs.checkTime>='"
				+ begin
				+ " 00:00:00'"
				+ " and cs.checkTime<='"
				+ finish
				+ " 23:59:59' and cs.checkAuditingTrue='计划盘库') "
				+ " and (tg.tarehouse.tarehouseName like ('%"
				+ search
				+ "%') or tg.goods.goodsCategory.goodsCategoryName like ('%"
				+ search
				+ "%') or tg.goods.goodsName like ('%"
				+ search
				+ "%') or tg.goods.goodsSign like ('%"
				+ search
				+ "%')) order by tg.tarehouse.tarehouseId desc";
		return (List<TarehouseGoods>) super.executeQueryByPage(hql, null,
				pageNow, rowSize);
	}

	// 当盘库的时候查询库存在盘库表里没有发起的，并且是当日的时间，并且以分页的方式显示,查询数据的总页数
	public int getChecksDataByPageCount(String search, String begin,
			String finish, int rowSize) {
		String hql = "select count(*) from TarehouseGoods tg where tg.tgoodsId not in(select cs.tarehouseGoods.tgoodsId from"
				+ " Checks cs,TarehouseGoods tgs where cs.tarehouseGoods.tgoodsId=tgs.tgoodsId and cs.checkTime>='"
				+ begin
				+ " 00:00:00'"
				+ " and cs.checkTime<='"
				+ finish
				+ " 23:59:59' and cs.checkAuditingTrue='计划盘库') "
				+ " and (tg.tarehouse.tarehouseName like ('%"
				+ search
				+ "%') or tg.goods.goodsCategory.goodsCategoryName like ('%"
				+ search
				+ "%') or tg.goods.goodsName like ('%"
				+ search
				+ "%') or tg.goods.goodsSign like ('%" + search + "%'))";
		return super.queryPageCount(hql, null, rowSize);
	}

	// --------------------------后来加入

	// 库位库存查询
	@SuppressWarnings("unchecked")
	public List<TarehouseGoods> getKeweiPage(String kuwei, String huowu,
			int pageNow, int rowSize) {
		String hql = "";

		hql = "from TarehouseGoods where tarehouse.tarehouseName like '%"
				+ kuwei + "%' and (goods.goodsSign like '%" + huowu + "%'"
				+ " or goods.goodsName like '%" + huowu + "%'"
				+ " or goods.goodsCategory.goodsCategoryName like '%" + huowu
				+ "%'" + " or goods.goodsStandard.goodsStandardName like '%"
				+ huowu + "%'"
				+ " or goods.goodsQuality.goodsQualityName like '%" + huowu
				+ "%'" + " or goods.goodsYieldly.goodsYieldlyName like '%"
				+ huowu + "%') order by tgoodsId desc";

		return (List<TarehouseGoods>) super.executeQueryByPage(hql, null,
				pageNow, rowSize);
	}

	// 库位库存查询页数
	public int getKeweiPageCount(String kuwei, String huowu, int pageSize) {

		String hql = "";

		hql = "select count(*) from TarehouseGoods where tarehouse.tarehouseName like '%"
				+ kuwei
				+ "%' and (goods.goodsSign like '%"
				+ huowu
				+ "%'"
				+ " or goods.goodsName like '%"
				+ huowu
				+ "%'"
				+ " or goods.goodsCategory.goodsCategoryName like '%"
				+ huowu
				+ "%'"
				+ " or goods.goodsStandard.goodsStandardName like '%"
				+ huowu
				+ "%'"
				+ " or goods.goodsQuality.goodsQualityName like '%"
				+ huowu
				+ "%'"
				+ " or goods.goodsYieldly.goodsYieldlyName like '%"
				+ huowu + "%')";

		return super.queryPageCount(hql, null, pageSize);
	}

}
