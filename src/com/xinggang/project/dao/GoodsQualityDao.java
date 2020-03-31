package com.xinggang.project.dao;

import java.util.List;

import com.xinggang.project.entity.GoodsQuality;

public interface GoodsQualityDao {
	// 增
	public boolean save(GoodsQuality goodsQuality);

	// 删除
	public boolean delete(GoodsQuality goodsQuality);

	// 修改
	public boolean update(GoodsQuality goodsQuality);

	// 通过id查询
	public GoodsQuality getGoodsQualityId(Integer id);

	// 查询全部
	public List<GoodsQuality> getAll();

	// 通过产地名称查询
	public List<GoodsQuality> getCaizhiName(String caizhiName);
}
