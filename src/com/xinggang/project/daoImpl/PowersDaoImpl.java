package com.xinggang.project.daoImpl;

import java.util.List;

import com.xinggang.project.base.BaseDaoImpl;
import com.xinggang.project.dao.PowersDao;
import com.xinggang.project.entity.Powers;

/**
 * 权限接口实现类
 * 
 * @author Administrator
 * 
 */
public class PowersDaoImpl extends BaseDaoImpl implements PowersDao {
	// 保存权限
	public boolean save(Powers powers) {

		return super.BaseSave(powers);
	}

	// 修改权限表
	public boolean update(Powers powers) {

		return super.BaseUpdate(powers);
	}

	// 删除权限
	public boolean delete(Powers powers) {

		return super.BaseDelete(powers);
	}

	// 通过id查询权限
	public Powers getPowersId(Integer id) {
		String hql="from Powers where powerId="+id;
		return (Powers) super.findById(hql);
	}

	// 查询全部权限
	@SuppressWarnings("unchecked")
	public List<Powers> getAll() {
		String hql = "from Powers";
		return (List<Powers>) super.executeQuery(hql, null);
	}

	// 通过职责查询
	@SuppressWarnings("unchecked")
	public List<Powers> getZhize(Integer zhizeId) {
		String hql = "from Powers where interiorUserDuty=" + zhizeId;
		return (List<Powers>) super.executeQuery(hql, null);
	}

	// 通过功能查询
	@SuppressWarnings("unchecked")
	public List<Powers> getGongneng(Integer gongnengId) {
		String hql = "from Powers where functions=" + gongnengId;
		return (List<Powers>) super.executeQuery(hql, null);
	}

	// 通过功能和职责并且查询
	@SuppressWarnings("unchecked")
	public List<Powers> getZG(Integer zhizeId, Integer gongnengId) {
		String hql = "from Powers where functions=" + gongnengId
				+ "and interiorUserDuty=" + zhizeId;
		return (List<Powers>) super.executeQuery(hql, null);
	}

}
