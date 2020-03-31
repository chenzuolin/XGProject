package com.xinggang.project.daoImpl;

/**
 * 今日钢价dao实现
 * */
import java.util.List;

import com.xinggang.project.base.BaseDaoImpl;
import com.xinggang.project.dao.SteelPriceDao;
import com.xinggang.project.entity.SteelPrice;

public class SteelPriceDaoImpl extends BaseDaoImpl implements SteelPriceDao {

	public boolean save(SteelPrice steelPrice) {
		return super.BaseSave(steelPrice);
	}

	public boolean update(SteelPrice steelPrice) {
		return super.BaseUpdate(steelPrice);
	}

	public boolean delete(SteelPrice steelPrice) {
		return super.BaseDelete(steelPrice);
	}

	public SteelPrice getSteelPrice(String id) {
		return (SteelPrice) super.findById(SteelPrice.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<SteelPrice> getAll() {
		String hql = "from SteelPrice order by spid desc";
		return (List<SteelPrice>) super.executeQuery(hql, null);
	}

	@SuppressWarnings("unchecked")
	public List<SteelPrice> getConditionQueryByPage(String goodsName,
			String guige, String caizhi, String chandi, String caozuoren,
			String begin, String finish, int pageNow, int rowSize) {
		String hql = "from SteelPrice where (spgoodsName like ('%" + goodsName
				+ "%') or spgoodsStandard like ('%" + goodsName
				+ "%') or spgoodsQuality like ('%" + goodsName
				+ "%') or spgoodsYieldly like ('%" + goodsName
				+ "%')) and spoperatorTime >= '" + begin
				+ "' and spoperatorTime <='" + finish + "' order by spid desc";
		return (List<SteelPrice>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	public int getConditionQueryByPageCount(String goodsName, String guige,
			String caizhi, String chandi, String caozuoren, String begin,
			String finish, int rowSize) {
		String hql = "select count(*) from SteelPrice where (spgoodsName like ('%"
				+ goodsName
				+ "%') or spgoodsStandard like ('%"
				+ goodsName
				+ "%') or spgoodsQuality like ('%"
				+ goodsName
				+ "%') or spgoodsYieldly like ('%"
				+ goodsName
				+ "%')) and spoperatorTime >= '"
				+ begin
				+ "' and spoperatorTime <='" + finish + "'";
		return super.queryPageCount(hql, null, rowSize);
	}

	// 取出上一天的数据
	@SuppressWarnings("unchecked")
	public List<SteelPrice> getLastTimeData(String goodsName, String guige,
			String caizhi, String shuxing, String chandi, String begin,
			String finish) {
		String hql = "from SteelPrice where spgoodsName like ('%" + goodsName
				+ "%') and spgoodsStandard like ('%" + guige
				+ "%') and spgoodsQuality like ('%" + caizhi
				+ "%') and spgoodsProperty like ('%" + shuxing
				+ "%') and spgoodsYieldly like ('%" + chandi
				+ "%') and spoperatorTime >= '" + begin
				+ "' and spoperatorTime <='" + finish + "' order by spid desc";
		return (List<SteelPrice>) super.executeQuery(hql, null);
	}

	// 取出最后一天的最大的时间
	public String getMaxTime() {
		String hql = "select MAX(spoperatorTime) from SteelPrice";
		return (String) super.executeQuery(hql, null).get(0);
	}

	// 得出最后一天货物的平均值
	public Double getMaxAVG(String time) {
		String hql = "select avg(spgoodsPrice) from SteelPrice where spoperatorTime like ('%"
				+ time + "%')";
		return (Double) super.executeQuery(hql, null).get(0);
	}

}
