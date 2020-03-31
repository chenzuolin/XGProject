package com.xinggang.project.service;

import java.util.List;

import com.xinggang.project.entity.Powers;
import com.xinggang.project.form.PowersForm;

/**
 * 
 * @author Administrator 权限接口，定义权限所需要的方法
 * @author Administrator
 *
 */
public interface PowersService {
	// 保存权限
	public boolean save(Powers powers);

	// 修改权限表
	public boolean update(Powers powers);

	// 删除权限
	public boolean delete(Powers powers);

	// 通过id查询权限
	public Powers getPowersId(Integer id);

	// 查询全部权限
	public List<Powers> getAll();

	// 通过职责查询权限
	public List<Powers> getZhize(Integer zhizeId);

	// 通过功能查询全向
	public List<Powers> getGongneng(Integer gongnengId);

	// 通过功能和职责并且查询
	public List<Powers> getZG(Integer zhizeId, Integer gongnengId);
	//增加权限
	public boolean addPowers(PowersForm powersForm);
	//修改权限
	public boolean updatePowers(Integer id);
	

}
