package com.xinggang.project.dao;

import java.util.List;

import com.xinggang.project.entity.GoodsStandard;

public interface GoodsStandardDao {
	// 增
	public boolean save(GoodsStandard goodsStandard);

	// 删除
	public boolean delete(GoodsStandard goodsStandard);

	// 修改
	public boolean update(GoodsStandard goodsStandard);

	// 通过id查询
	public GoodsStandard getGoodsStandardId(Integer id);

	// 查询全部
	public List<GoodsStandard> getAll();

	// 通过产地名称查询
	public List<GoodsStandard> getGuigeName(String guigeName);
}
