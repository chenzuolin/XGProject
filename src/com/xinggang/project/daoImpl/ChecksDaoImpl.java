package com.xinggang.project.daoImpl;

import java.util.List;

import com.xinggang.project.base.BaseDaoImpl;
import com.xinggang.project.dao.ChecksDao;
import com.xinggang.project.entity.Checks;

/**
 * 盘点Dao实现类
 * 
 * @author Administrator
 * 
 */
public class ChecksDaoImpl extends BaseDaoImpl implements ChecksDao {
	// 增加
	public boolean save(Checks checks) {
		return super.BaseSave(checks);
	}

	// 删除
	public boolean delete(Checks checks) {
		return super.BaseDelete(checks);
	}

	// 修改
	public boolean update(Checks checks) {
		return super.BaseUpdate(checks);
	}

	// 通过id查询
	public Checks getChecksId(String id) {
		String hql = "from Checks where checkId='" + id + "'";
		return (Checks) super.findById(hql);
	}

	// 查询全部
	@SuppressWarnings("unchecked")
	public List<Checks> getAll() {
		String hql = "from Checks order by CheckId desc";
		return (List<Checks>) super.executeQuery(hql, null);
	}

	// 分页查询
	@SuppressWarnings("unchecked")
	public List<Checks> getChecksByPage(int pageNow, int rowSize) {
		String hql = "from Checks order by checkTime desc";
		return (List<Checks>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 查询数据总行数
	public int getChecksByCount() {
		String hql = "select count(*) from Checks";
		return super.executeQueryRowCount(hql, null);
	}

	// 通过编号模糊查询
	@SuppressWarnings("unchecked")
	public List<Checks> getChecksLike(String ChecksId) {
		String hql = "from Checks where checkId like '%" + ChecksId + "%'";
		return (List<Checks>) super.executeQuery(hql, null);
	}

	// 通过多种条件分页查询
	@SuppressWarnings("unchecked")
	public List<Checks> getDuozhongByPage(String begin, String finish,
			Integer kucun) {// 盘点时间范围，通过货物查询
		String hql = "from Checks where checkTime>= '" + begin
				+ "' and checkTime<= '" + finish + "' and tarehouseGoods="
				+ kucun + " order by checkTime desc";
		return (List<Checks>) super.executeQuery(hql, null);
	}

	// 通过多种条件查询总行数
	public int getDuozhongByCount(String begin, String finish, Integer kucun) {// 盘点时间范围，通过货物查询
		String hql = "select count(*) from Checks where checkTime>= '" + begin
				+ "' and checkTime<= '" + finish + "' and tarehouseGoods="
				+ kucun + " order by checkTime desc";
		return super.executeQueryRowCount(hql, null);
	}

	// 通过盘点状态查询，字段审核是否通过
	@SuppressWarnings("unchecked")
	public List<Checks> getChecksZhuangtai(String zhuang) {
		String hql = "from Checks where checkAuditingTrue= '" + zhuang + "'";
		return (List<Checks>) super.executeQuery(hql, null);
	}

	@SuppressWarnings("unchecked")
	public List<Checks> getGoods(Integer goods) {
		String hql = "from Checks where tarehouseGoods= '" + goods + "'";
		return (List<Checks>) super.executeQuery(hql, null);
	}

	// 查询所有的盘点订单，以分页的方式显示
	@SuppressWarnings("unchecked")
	public List<Checks> getChecksAllByPage(String begin, String finish,
			String goodsName, String zhujifu, String guige, String caizhi,
			String shuxing, String kuname, int pageNow, int rowSize) {

		// 编号，盘点人，对应的库位，审核人，发起人，库存，盘点日期，现有件数，现有重量，盘点结果重量，盘点结果件数，审核时间，审核是否通过，预留字段一，预留字段二，备注
		String hql = "from Checks c " + "where c.checkTime >= '" + begin
				+ "' and c.checkTime <= '" + finish
				+ "' and c.tarehouse.tarehouseName like ('%" + kuname + "%')"
				+ " order by c.checkTime desc ";

		return (List<Checks>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 查询所有盘点订单的总页数
	public int getChecksAllByPageCount(String begin, String finish,
			String goodsName, String zhujifu, String guige, String caizhi,
			String shuxing, String kuname, int rowSize) {
		String hql = "select count(*) "
				+ "from Checks c,Goods g,Tarehouse t,TarehouseGoods tg,GoodsStandard gs,GoodsQuality gq,GoodsProperty gp "
				+ "where c.tarehouse=t.tarehouseId and c.tarehouseGoods=tg.tgoodsId and g.goodsId=tg.goods "
				+ "and tg.tarehouse=t.tarehouseId and c.checkTime > '"
				+ begin
				+ "' and c.checkTime <= '"
				+ finish
				+ "' and g.goodsName like ('%"
				+ goodsName
				+ "%') and g.goodsSign like ('%"
				+ zhujifu
				+ "%') and gs.goodsStandardName like ('%"
				+ guige
				+ "%') and gq.goodsQualityName like ('%"
				+ caizhi
				+ "%') and gp.goodsPropertyName like ('%"
				+ shuxing
				+ "%') and t.tarehouseName like ('%"
				+ kuname
				+ "%')"
				+ " and g.goodsProperty=gp.goodsPropertyId and g.goodsStandard=gs.goodsStandardId "
				+ " and g.goodsQuality=gq.goodsQualityId";
		return super.queryPageCount(hql, null, rowSize);
	}

	// 通过库位进行模糊的查询，并且以分页的方式显示
	@SuppressWarnings("unchecked")
	public List<Checks> getTarehouseByPage(String kuname, int pageNow,
			int rowSize) {
		String hql = "from Checks where checkAuditingTrue='正在审核' and tarehouse.tarehouseName like ('%"
				+ kuname + "%') order by checkTime asc ";
		return (List<Checks>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 通过库位的模糊查询进行查询数据的页数
	public int getTarehouseByPageCount(String kuname, int rowSize) {
		String hql = "select count(*) from Checks where checkAuditingTrue='正在审核' and tarehouse.tarehouseName like ('%"
				+ kuname + "%')";
		return super.queryPageCount(hql, null, rowSize);
	}

}
