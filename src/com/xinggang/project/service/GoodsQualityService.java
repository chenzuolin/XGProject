package com.xinggang.project.service;

import java.util.List;

import com.xinggang.project.entity.GoodsQuality;
import com.xinggang.project.form.GoodsQualityForm;

public interface GoodsQualityService {
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
	
	// 增
	public boolean addGoodsQuality(GoodsQualityForm goodsQualityForm);
	
	// 修改
	public boolean updateGoodsQuality(GoodsQualityForm goodsQualityForm);
}
