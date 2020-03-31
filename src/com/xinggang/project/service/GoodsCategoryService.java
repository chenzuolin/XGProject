package com.xinggang.project.service;

import java.util.List;

import com.xinggang.project.entity.GoodsCategory;

/**
 * 使用系统的公司内部人员职责Service
 * 
 * @author Administrator
 * 
 */
public interface GoodsCategoryService {
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
	//根据货物品类查询
	public List<GoodsCategory> getName(String name);
}
