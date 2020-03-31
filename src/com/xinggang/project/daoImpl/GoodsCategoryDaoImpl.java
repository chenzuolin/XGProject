package com.xinggang.project.daoImpl;

import java.util.List;

import com.xinggang.project.base.BaseDaoImpl;
import com.xinggang.project.dao.GoodsCategoryDao;
import com.xinggang.project.entity.GoodsCategory;

/**
 * 货物品类接口实现类
 * 
 * @author Administrator
 * 
 */
class GoodsCategoryDaoImpl extends BaseDaoImpl implements GoodsCategoryDao {
	// 添加货物品类
	public boolean save(GoodsCategory goodsCategory) {
		return super.BaseSave(goodsCategory);
	}

	// 修改货物品类
	public boolean update(GoodsCategory goodsCategory) {
		return super.BaseUpdate(goodsCategory);
	}

	// 删除货物品类
	public boolean delete(GoodsCategory goodsCategory) {
		goodsCategory.setGoodsCategoryDefinedOne("0");
		return super.BaseUpdate(goodsCategory);
	}
	//-----------------------------------------------改
	// 通过ID查询货物品类
	public GoodsCategory getGoodsCategoryId(Integer id) {
		String hql = "from GoodsCategory where goodsCategoryId="+id;
		return (GoodsCategory) super.findById(hql);
	}

	// 查询全部
	@SuppressWarnings("unchecked")
	public List<GoodsCategory> getAll() {
		String hql = "from GoodsCategory where goodsCategoryDefinedOne!='0'";
		return (List<GoodsCategory>) super.executeQuery(hql, null);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<GoodsCategory> getName(String name) {
		String hql = "from GoodsCategory where goodsCategoryDefinedOne!='0' and goodsCategoryName='"+name+"'";
		return (List<GoodsCategory>) super.executeQuery(hql, null);
	}

}
