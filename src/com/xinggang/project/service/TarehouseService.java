package com.xinggang.project.service;

import java.util.List;

import com.xinggang.project.entity.Tarehouse;

/**
 * 库位Service
 * 
 * @author Administrator
 * 
 */
public interface TarehouseService {
	// 增
	public boolean save(Tarehouse tarehouse);

	// 删,此项目中不能删除
	public boolean delete(Tarehouse tarehouse);

	// 改
	public boolean update(Tarehouse tarehouse);

	// 通过id查询
	public Tarehouse getTarehouseId(Integer id);

	// 查询全部
	public List<Tarehouse> getAll();

	// 通过库位名称查询
	public List<Tarehouse> getKuName(String kuname);

	// 通过库房查询
	public List<Tarehouse> getKufang(Integer kufang);
	
	public List<Tarehouse> getKuNameMoHu(String kuname);
}
