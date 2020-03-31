package com.xinggang.project.daoImpl;

import java.util.List;
import com.xinggang.project.base.BaseDaoImpl;
import com.xinggang.project.dao.GoodsPropertyDao;
import com.xinggang.project.entity.GoodsProperty;

public class GoodsPropertyDaoImpl extends BaseDaoImpl implements
		GoodsPropertyDao {

	public boolean save(GoodsProperty goodsProperty) {
		return super.BaseSave(goodsProperty);
	}
	
	public boolean delete(GoodsProperty goodsProperty) {
		return super.BaseDelete(goodsProperty);
	}

	public boolean update(GoodsProperty goodsProperty) {
		return super.BaseUpdate(goodsProperty);
	}

	public GoodsProperty getGoodsPropertyId(Integer id) {
		String hql="from GoodsProperty where goodsPropertyId="+id;
		return (GoodsProperty) super.findById(hql);
	}

	@SuppressWarnings("unchecked")
	public List<GoodsProperty> getAll() {
		String hql = "from GoodsProperty where goodsPropertyDefinedOne!='"+0+"'";
		return (List<GoodsProperty>) super.executeQuery(hql, null);
	}

	@SuppressWarnings("unchecked")
	public List<GoodsProperty> getShuxinName(String shuxinName) {
		String hql = "from GoodsProperty where goodsPropertyDefinedOne!='"+0+"' and goodsPropertyName='"+shuxinName+"'";
		return (List<GoodsProperty>) super.executeQuery(hql, null);
	}

}
