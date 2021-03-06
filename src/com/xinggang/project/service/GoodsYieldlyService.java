package com.xinggang.project.service;

import java.util.List;

import com.xinggang.project.entity.GoodsYieldly;
import com.xinggang.project.form.GoodsYieldlyForm;

/**
 * 货物产地Service
 * 
 * @author Administrator
 * 
 */
public interface GoodsYieldlyService {
	// 增
	public boolean save(GoodsYieldly goodsYieldly);

	// 删除
	public boolean delete(GoodsYieldly goodsYieldly);

	// 修改
	public boolean update(GoodsYieldly goodsYieldly);

	// 通过id查询
	public GoodsYieldly getGoodsYieldlyId(Integer id);

	// 查询全部
	public List<GoodsYieldly> getAll();

	// 通过产地名称查询
	public List<GoodsYieldly> getChandiName(String chandiName);
	
	//修改货物产地
	public boolean updateGoodsYieldly(GoodsYieldlyForm goodsYieldlyForm);
}
