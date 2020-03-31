package com.xinggang.project.dao;

import java.util.List;

import com.xinggang.project.entity.TarehouseDetailOperate;

/**
 * 货物批次操作Dao
 * 
 * @author Administrator
 * 
 */
public interface TarehouseDetailOperateDao {
	// 增
	public boolean save(TarehouseDetailOperate tarehouseDetailOperate);

	// 删
	public boolean delete(TarehouseDetailOperate tarehouseDetailOperate);

	// 改
	public boolean update(TarehouseDetailOperate tarehouseDetailOperate);

	// 通过id查询
	public TarehouseDetailOperate getTarehouseDetailOperateId(String id);

	// 查询全部
	public List<TarehouseDetailOperate> getAll();
}
