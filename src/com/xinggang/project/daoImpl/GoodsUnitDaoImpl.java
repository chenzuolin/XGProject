package com.xinggang.project.daoImpl;

import java.util.List;

import com.xinggang.project.base.BaseDaoImpl;
import com.xinggang.project.dao.GoodsUnitDao;
import com.xinggang.project.entity.GoodsUnit;

public class GoodsUnitDaoImpl extends BaseDaoImpl implements GoodsUnitDao {

	public boolean save(GoodsUnit goodsUnit) {
		return super.BaseSave(goodsUnit);
	}

	public boolean delete(GoodsUnit goodsUnit) {
		return super.BaseDelete(goodsUnit);
	}

	public boolean update(GoodsUnit goodsUnit) {
		return super.BaseUpdate(goodsUnit);
	}

	public GoodsUnit getGoodsUnitId(Integer id) {
		String hql="from GoodsUnit where goodsUnitId="+id;
		return (GoodsUnit) super.findById(hql);
	}

	@SuppressWarnings("unchecked")
	public List<GoodsUnit> getAll() {
		String hql = "from GoodsUnit where goodsUnitDefinedOne!='"+0+"'";
		return (List<GoodsUnit>) super.executeQuery(hql, null);
	}

	@SuppressWarnings("unchecked")
	public List<GoodsUnit> getJiLiangName(String JiLiangName) {
		String hql = "from GoodsUnit where goodsUnitName='"+ JiLiangName+"'";
		return (List<GoodsUnit>) super.executeQuery(hql, null);
	}

}
