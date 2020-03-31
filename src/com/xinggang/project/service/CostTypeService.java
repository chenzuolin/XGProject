package com.xinggang.project.service;

import java.util.List;

import com.xinggang.project.entity.CostType;

public interface CostTypeService {
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

	// 查询火运入库费
	public double getHuoYunRuKu();

	// 查询入库的费用
	public double getQiYunRuKu();

	// 查询火运出库费
	public double getHuoYunChuKu();

	// 查询出库的费用
	public double getQiYunChuKu();

	// 查询过户的费用
	public double getGuoHu();

	// 查询二次作业的费用
	public double getErCi();

	// 查询仓储的费用
	public double getCangChu();
}
