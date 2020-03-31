package com.xinggang.project.dao;

import java.util.List;

import com.xinggang.project.entity.ClassT;

/**
 * 班组Dao
 * 
 * @author Administrator
 * 
 */
public interface ClassTDao {
	// 增
	public boolean save(ClassT classT);

	// 删
	public boolean delete(ClassT classT);

	// 改
	public boolean update(ClassT classT);

	// 通过id查询
	public ClassT getClassTId(Integer id);

	// 查询全部
	public List<ClassT> getAll();

	// 通过班组名称查询
	public List<ClassT> getBanzuName(String banzuName);

	// 通过部门查询
	public List<ClassT> getBumen(Integer bumenId);
}
