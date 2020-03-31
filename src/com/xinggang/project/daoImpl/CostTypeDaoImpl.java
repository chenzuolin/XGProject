package com.xinggang.project.daoImpl;

import java.util.List;

import com.xinggang.project.base.BaseDaoImpl;
import com.xinggang.project.dao.CostTypeDao;
import com.xinggang.project.entity.CostType;

public class CostTypeDaoImpl extends BaseDaoImpl implements CostTypeDao {
	// 增加
	public boolean save(CostType costType) {
		return super.BaseSave(costType);
	}

	// 删除
	public boolean delete(CostType costType) {

		return super.BaseDelete(costType);
	}

	// 修改
	public boolean update(CostType costType) {

		return super.BaseUpdate(costType);
	}

	// 通过编号查询
	public CostType getCostTypeId(Integer id) {
		String hql = "from CostType where ctypeId=" + id;
		return (CostType) super.findById(hql);
	}

	// 查询全部
	@SuppressWarnings("unchecked")
	public List<CostType> getAll() {
		String hql = "from CostType where ctypeDefinedOne!='"+0+"'";
		return (List<CostType>) super.executeQuery(hql, null);
	}

	// 通过费用类型的名称查询
	@SuppressWarnings("unchecked")
	public List<CostType> getCostTypeName(String ctName) {
		String hql = "from CostType where ctypeName= '" + ctName + "'";
		return (List<CostType>) super.executeQuery(hql, null);
	}

}
