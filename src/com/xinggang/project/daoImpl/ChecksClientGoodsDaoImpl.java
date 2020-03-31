package com.xinggang.project.daoImpl;

import java.util.List;

import com.xinggang.project.base.BaseDaoImpl;
import com.xinggang.project.dao.ChecksClientGoodsDao;
import com.xinggang.project.entity.ChecksClientGoods;

public class ChecksClientGoodsDaoImpl extends BaseDaoImpl implements
		ChecksClientGoodsDao {

	// 增加
	public boolean save(ChecksClientGoods ccg) {
		return super.BaseSave(ccg);
	}

	// 删除
	public boolean delete(ChecksClientGoods ccg) {
		return super.BaseDelete(ccg);
	}

	// 修改
	public boolean update(ChecksClientGoods ccg) {
		return super.BaseUpdate(ccg);
	}

	// 通过编号查询
	public ChecksClientGoods getChecksClientGoodsId(String id) {
		return (ChecksClientGoods) super.findById(ChecksClientGoods.class, id);
	}

	// 查询全部
	@SuppressWarnings("unchecked")
	public List<ChecksClientGoods> getAll() {
		String hql = "from ChecksClientGoods order by ccgoodsChecksTime desc";
		return (List<ChecksClientGoods>) super.executeQuery(hql, null);
	}

	// 统计盈库的重量
	public double getTongJiYingKu(String danwei, String jiancheng,
			String danweizhujifu, String ccgId, String begin, String finish,
			String goodsName, String goodsSign, String kufangname) {
		String hql = "select sum(ccgoodsSurplus) from ChecksClientGoods "
				+ "where (client.clientFirmName like ('%" + jiancheng
				+ "%') or client.clientAbbreviation like ('%" + jiancheng
				+ "%') or client.clientSign like ('%" + jiancheng
				+ "%')) and ccgoodsChecksTime >= '" + begin
				+ "' and ccgoodsChecksTime <= '" + finish
				+ "' and (goods.goodsName like ('%" + goodsName
				+ "%') or goods.goodsSign like ('%" + goodsName
				+ "%') or goods.goodsCategory.goodsCategoryName like ('%"
				+ goodsName
				+ "%') or goods.goodsStandard.goodsStandardName like ('%"
				+ goodsName + "%'))";
		double result = (Double) (super.executeQuery(hql, null).get(0) == null ? 0.0
				: super.executeQuery(hql, null).get(0));
		return result;
	}

	// 统计亏库的重量
	public double getTongJiKuiKu(String danwei, String jiancheng,
			String danweizhujifu, String ccgId, String begin, String finish,
			String goodsName, String goodsSign, String kufangname) {
		String hql = "select sum(ccgoodsLoss) from ChecksClientGoods "
				+ "where (client.clientFirmName like ('%" + jiancheng
				+ "%') or client.clientAbbreviation like ('%" + jiancheng
				+ "%') or client.clientSign like ('%" + jiancheng
				+ "%')) and ccgoodsChecksTime >= '" + begin
				+ "' and ccgoodsChecksTime <= '" + finish
				+ "' and (goods.goodsName like ('%" + goodsName
				+ "%') or goods.goodsSign like ('%" + goodsName
				+ "%') or goods.goodsCategory.goodsCategoryName like ('%"
				+ goodsName
				+ "%') or goods.goodsStandard.goodsStandardName like ('%"
				+ goodsName + "%'))";
		double result = (Double) (super.executeQuery(hql, null).get(0) == null ? 0.0
				: super.executeQuery(hql, null).get(0));
		return result;
	}

	// 通过模糊查询，以分页的方式显示
	@SuppressWarnings("unchecked")
	public List<ChecksClientGoods> getMoHuByPage(String danwei,
			String jiancheng, String danweizhujifu, String ccgId, String begin,
			String finish, String goodsName, String goodsSign,
			String kufangname, int pageNow, int rowSize) {

		// 编号，客户，库房，内部人员，货物，原有重量，盈库重量，亏库重量，盘点日期，预留字段一，预留字段二，备注
		String hql = "from ChecksClientGoods "
				+ "where (client.clientFirmName like ('%" + jiancheng
				+ "%') or client.clientAbbreviation like ('%" + jiancheng
				+ "%') or client.clientSign like ('%" + jiancheng
				+ "%')) and ccgoodsChecksTime >= '" + begin
				+ "' and ccgoodsChecksTime <= '" + finish
				+ "' and (goods.goodsName like ('%" + goodsName
				+ "%') or goods.goodsSign like ('%" + goodsName
				+ "%') or goods.goodsCategory.goodsCategoryName like ('%"
				+ goodsName
				+ "%') or goods.goodsStandard.goodsStandardName like ('%"
				+ goodsName + "%')) order by ccgoodsChecksTime desc";
		return (List<ChecksClientGoods>) super.executeQueryByPage(hql, null,
				pageNow, rowSize);
	}

	// 通过模糊查询数据的总页数
	public int getMoHuByPageCount(String danwei, String jiancheng,
			String danweizhujifu, String ccgId, String begin, String finish,
			String goodsName, String goodsSign, String kufangname, int rowSize) {
		String hql = "select count(*) from ChecksClientGoods "
				+ "where (client.clientFirmName like ('%" + jiancheng
				+ "%') or client.clientAbbreviation like ('%" + jiancheng
				+ "%') or client.clientSign like ('%" + jiancheng
				+ "%')) and ccgoodsChecksTime >= '" + begin
				+ "' and ccgoodsChecksTime <= '" + finish
				+ "' and (goods.goodsName like ('%" + goodsName
				+ "%') or goods.goodsSign like ('%" + goodsName
				+ "%') or goods.goodsCategory.goodsCategoryName like ('%"
				+ goodsName
				+ "%') or goods.goodsStandard.goodsStandardName like ('%"
				+ goodsName + "%'))";
		return super.queryPageCount(hql, null, rowSize);
	}

	// 统计客户的期初盈库或者亏库，统计到相应的客户库存报表中
	public double getChecksQiChu(Integer clientId, Integer GoodsId, String begin) {
		String hql = "select sum(ccgoodsLoss)-sum(ccgoodsSurplus) from ChecksClientGoods where client="
				+ clientId
				+ " and goods="
				+ GoodsId
				+ " and ccgoodsChecksTime<'" + begin + "'";
		double result = (Double) (super.executeQuery(hql, null).get(0) == null ? 0.0
				: super.executeQuery(hql, null).get(0));
		return result;
	}

	// 统计客户的期末盈库或者亏库，统计到相应的客户库存报表中
	public double getChecksQiMo(Integer clientId, Integer GodosId,
			String begin, String finish) {
		String hql = "select sum(ccgoodsLoss)-sum(ccgoodsSurplus) from ChecksClientGoods where client="
				+ clientId
				+ " and goods="
				+ GodosId
				+ " and ccgoodsChecksTime>='"
				+ begin
				+ "' and ccgoodsChecksTime<='" + finish + "'";
		double result = (Double) (super.executeQuery(hql, null).get(0) == null ? 0.0
				: super.executeQuery(hql, null).get(0));
		return result;
	}

}
