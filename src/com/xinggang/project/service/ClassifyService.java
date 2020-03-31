package com.xinggang.project.service;

import java.util.List;

import com.xinggang.project.entity.Classify;

/**
 * 功能类别service
 * 
 * @author Administrator
 * 
 */
public interface ClassifyService {
	// 添加功能类别
	public boolean save(Classify classify);

	// 修改功能类别
	public boolean update(Classify classify);

	// 删除功能类别
	public boolean delete(Classify classify);

	// 查询功能类别
	public List<Classify> getAll();

	// 通过id查询
	public Classify getClassifyId(Integer id);
}
