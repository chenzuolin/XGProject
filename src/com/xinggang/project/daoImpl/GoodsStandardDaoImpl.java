package com.xinggang.project.daoImpl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xinggang.project.base.BaseDaoImpl;
import com.xinggang.project.dao.GoodsStandardDao;
import com.xinggang.project.entity.GoodsStandard;

@Transactional
public class GoodsStandardDaoImpl extends BaseDaoImpl implements
		GoodsStandardDao {

	public boolean save(GoodsStandard goodsStandard) {
		return super.BaseSave(goodsStandard);
	}

	public boolean delete(GoodsStandard goodsStandard) {
		return super.BaseDelete(goodsStandard);
	}

	public boolean update(GoodsStandard goodsStandard) {
		return super.BaseUpdate(goodsStandard);
	}

	public GoodsStandard getGoodsStandardId(Integer id) {
		String hql = "from GoodsStandard where goodsStandardId=" + id;
		return (GoodsStandard) super.findById(hql);
	}

	@SuppressWarnings("unchecked")
	public List<GoodsStandard> getAll() {
		String hql = "from GoodsStandard where goodsStandardDefinedOne!='"+0+"'";
		return (List<GoodsStandard>) super.executeQuery(hql, null);
	}

	@SuppressWarnings("unchecked")
	public List<GoodsStandard> getGuigeName(String guigeName) {
		String hql = "from GoodsStandard where goodsStandardDefinedOne!='"+0+"' and goodsStandardName='"+guigeName+ "'";
		return (List<GoodsStandard>) super.executeQuery(hql, null);
	}

}
