package com.xinggang.project.dao;

import java.util.List;
import com.xinggang.project.entity.GoodsUnit;

public interface GoodsUnitDao {
	// 增
	public boolean save(GoodsUnit goodsUnit);

	// 删除
	public boolean delete(GoodsUnit goodsUnit);

	// 修改
	public boolean update(GoodsUnit goodsUnit);

	// 通过id查询
	public GoodsUnit getGoodsUnitId(Integer id);

	// 查询全部
	public List<GoodsUnit> getAll();

	// 通过产地名称查询
	public List<GoodsUnit> getJiLiangName(String JiLiangName);
}
