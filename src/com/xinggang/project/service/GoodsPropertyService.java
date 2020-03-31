package com.xinggang.project.service;

import java.util.List;

import com.xinggang.project.entity.GoodsProperty;
import com.xinggang.project.form.GoodsPropertyForm;

public interface GoodsPropertyService {
	// 增
	public boolean save(GoodsProperty goodsProperty);

	// 删除
	public boolean delete(GoodsProperty goodsProperty);

	// 修改
	public boolean update(GoodsProperty goodsProperty);

	// 通过id查询
	public GoodsProperty getGoodsPropertyId(Integer id);

	// 查询全部
	public List<GoodsProperty> getAll();

	// 通过产地名称查询
	public List<GoodsProperty> getShuxinName(String shuxinName);
	
	// 添加属性
	public boolean addGoodsProperty(GoodsPropertyForm goodsPropertyForm);
	// 修改属性
	public boolean updateGoodsProperty(GoodsPropertyForm goodsPropertyForm);
}
