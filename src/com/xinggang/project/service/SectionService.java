package com.xinggang.project.service;

import java.util.List;

import com.xinggang.project.entity.Section;
import com.xinggang.project.form.SectionForm;

/**
 * 部门Service
 * 
 * @author Administrator
 * 
 */
public interface SectionService {
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
	
	//保存部门信息
	public boolean saveSection(SectionForm sectionForm);
	
	//保存部门信息
	public boolean updateSection(SectionForm sectionForm);
	
	//保存部门信息
	public boolean deleteSection(SectionForm sectionForm);
}
