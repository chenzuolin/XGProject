package com.xinggang.project.daoImpl;

import java.util.List;

import com.xinggang.project.base.BaseDaoImpl;
import com.xinggang.project.dao.GoodsQualityDao;
import com.xinggang.project.entity.GoodsQuality;

public class GoodsQualityDaoImpl extends BaseDaoImpl implements GoodsQualityDao {

	public boolean save(GoodsQuality goodsQuality) {
		return super.BaseSave(goodsQuality);
	}

	public boolean delete(GoodsQuality goodsQuality) {
		return super.BaseDelete(goodsQuality);
	}

	public boolean update(GoodsQuality goodsQuality) {
		return super.BaseUpdate(goodsQuality);
	}

	public GoodsQuality getGoodsQualityId(Integer id) {
		String hql="from GoodsQuality where goodsQualityId="+id;
		return (GoodsQuality) super.findById(hql);
	}

	@SuppressWarnings("unchecked")
	public List<GoodsQuality> getAll() {
		String hql = "from GoodsQuality where goodsQualityDefinedOne!='"+0+"'";
		return (List<GoodsQuality>) super.executeQuery(hql, null);
	}

	@SuppressWarnings("unchecked")
	public List<GoodsQuality> getCaizhiName(String caizhiName) {
		String hql = "from GoodsQuality where goodsQualityDefinedOne!='"+0+"' and goodsQualityName='"+ caizhiName+"'";
		return (List<GoodsQuality>) super.executeQuery(hql, null);
	}

}
