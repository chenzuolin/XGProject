package com.xinggang.project.dao;

import java.util.List;

import com.xinggang.project.entity.InteriorUserDuty;

/**
 * 使用系统的公司内部人员职责Dao
 * 
 * @author Administrator
 * 
 */
public interface InteriorUserDutyDao {
	// 增
	public boolean save(InteriorUserDuty interiorUserDuty);

	// 修改
	public boolean update(InteriorUserDuty interiorUserDuty);

	// 删除
	public boolean delete(InteriorUserDuty interiorUserDuty);

	// 通过id查询
	public InteriorUserDuty getInteriorUserDutyId(Integer id);

	// 查询全部
	public List<InteriorUserDuty> getAll();

	// 通过职责名称查询
	public List<InteriorUserDuty> getZhizeName(String zhizeName);

	// 通过部门查询
	public List<InteriorUserDuty> getBumen(Integer bumenId);
}
