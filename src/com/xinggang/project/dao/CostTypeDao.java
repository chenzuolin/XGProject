package com.xinggang.project.dao;

import java.util.List;

import com.xinggang.project.entity.CostType;

public interface CostTypeDao {
	// 增加
	public boolean save(CostType costType);

	// 删除
	public boolean delete(CostType costType);

	// 修改
	public boolean update(CostType costType);

	// 通过编号查询
	public CostType getCostTypeId(Integer id);

	// 查询全部
	public List<CostType> getAll();

	// 通过费用类型的名称查询
	public List<CostType> getCostTypeName(String ctName);
}
