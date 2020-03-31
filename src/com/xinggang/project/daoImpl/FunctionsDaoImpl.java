package com.xinggang.project.daoImpl;

import java.util.List;

import com.xinggang.project.base.BaseDaoImpl;
import com.xinggang.project.dao.FunctionsDao;
import com.xinggang.project.entity.Functions;

/**
 * 功能接口实现类
 * 
 * @author Administrator
 * 
 */
public class FunctionsDaoImpl extends BaseDaoImpl implements FunctionsDao {
	// 添加功能
	public boolean save(Functions functions) {
		return super.BaseSave(functions);
	}

	// 修改功能
	public boolean update(Functions functions) {
		return super.BaseUpdate(functions);
	}

	// 删除功能
	public boolean delete(Functions functions) {
		return super.BaseDelete(functions);
	}

	// 查询全部功能
	@SuppressWarnings("unchecked")
	public List<Functions> getAll() {
		String hql = "from Functions order by classify desc";
		return (List<Functions>) super.executeQuery(hql, null);
	}

	// 通过id查询功能
	public Functions getFunctionsId(Integer id) {
		String hql = "from Functions where functionId=" + id;
		return (Functions) super.findById(hql);
	}

	// 通过功能名称查询
	@SuppressWarnings("unchecked")
	public List<Functions> getFunctionsName(String functionsName) {
		String hql = "from Functions where functionName='" + functionsName
				+ "'";
		return (List<Functions>) super.executeQuery(hql, null);
	}

	// 通过功能类别查询
	@SuppressWarnings("unchecked")
	public List<Functions> getFunctionsClassify(Integer classifyId) {
		String hql = "from Functions where classify=" + classifyId;
		return (List<Functions>) super.executeQuery(hql, null);
	}

}
