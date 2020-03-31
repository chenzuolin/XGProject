package com.xinggang.project.dao;

import java.util.List;

import com.xinggang.project.entity.GoodsCategory;

/**
 * 货物品类Dao
 * 
 * @author Administrator
 * 
 */
public interface GoodsCategoryDao {
	// 添加货物品类
	public boolean save(GoodsCategory goodsCategory);

	// 修改货物品类
	public boolean update(GoodsCategory goodsCategory);

	// 删除货物品类
	public boolean delete(GoodsCategory goodsCategory);

	// 通过ID查询货物品类
	public GoodsCategory getGoodsCategoryId(Integer id);

	// 查询全部
	public List<GoodsCategory> getAll();
	
	public List<GoodsCategory> getName(String name);
}
