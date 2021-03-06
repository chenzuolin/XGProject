package com.xinggang.project.dao;

import java.util.List;

import com.xinggang.project.entity.Section;

/**
 * 部门Dao
 * 
 * @author Administrator
 * 
 */
public interface SectionDao {
	// 增
	public boolean save(Section section);

	// 删
	public boolean delete(Section section);

	// 改
	public boolean update(Section section);

	// 通过id查询
	public Section getSectionId(Integer id);

	// 查询全部
	public List<Section> getAll();

	// 通过部门名称进行查询
	public List<Section> getBumenName(String bumenName);
}
