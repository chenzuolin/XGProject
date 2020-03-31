package com.xinggang.project.service;

import java.util.List;

import com.xinggang.project.entity.Bursary;

/**
 * 库房service
 * 
 * @author Administrator
 * 
 */
public interface BursaryService {
	// 增加
	public boolean save(Bursary bursary);

	// 删除
	public boolean delete(Bursary bursary);

	// 修改
	public boolean update(Bursary bursary);

	// 通过id查询
	public Bursary getBursaryId(Integer id);

	// 查询全部
	public List<Bursary> getAll();

	// 通过库房名称查询
	public List<Bursary> getKufangName(String kufangName);
}
