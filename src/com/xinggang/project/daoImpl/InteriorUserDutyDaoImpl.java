package com.xinggang.project.daoImpl;

import java.util.List;

import com.xinggang.project.base.BaseDaoImpl;
import com.xinggang.project.dao.InteriorUserDutyDao;
import com.xinggang.project.entity.InteriorUserDuty;

/**
 * 内部人员职责接口实现
 * 
 * @author Administrator
 * 
 */
public class InteriorUserDutyDaoImpl extends BaseDaoImpl implements
		InteriorUserDutyDao {
	// 增
	public boolean save(InteriorUserDuty interiorUserDuty) {
		return super.BaseSave(interiorUserDuty);
	}

	// 修改
	public boolean update(InteriorUserDuty interiorUserDuty) {
		return super.BaseUpdate(interiorUserDuty);
	}

	// 删除
	public boolean delete(InteriorUserDuty interiorUserDuty) {
		return super.BaseDelete(interiorUserDuty);
	}

	// 通过id查询
	public InteriorUserDuty getInteriorUserDutyId(Integer id) {
		String hql="from InteriorUserDuty where interiorUserDutyId="+id;
		return (InteriorUserDuty) super.findById(hql);
	}

	// 查询全部
	@SuppressWarnings("unchecked")
	public List<InteriorUserDuty> getAll() {
		String hql = "from InteriorUserDuty where interiorUserDutyDefinedOne!='"+0+"'";
		return (List<InteriorUserDuty>) super.executeQuery(hql, null);
	}

	// 通过职责名称查询
	@SuppressWarnings("unchecked")
	public List<InteriorUserDuty> getZhizeName(String zhizeName) {
		String hql = "from InteriorUserDuty where interiorUserDutyName='"
				+ zhizeName+"'";
		return (List<InteriorUserDuty>) super.executeQuery(hql, null);
	}

	// 通过部门查询
	@SuppressWarnings("unchecked")
	public List<InteriorUserDuty> getBumen(Integer bumenId) {
		String hql = "from InteriorUserDuty where section=" + bumenId;
		return (List<InteriorUserDuty>) super.executeQuery(hql, null);
	}

}
