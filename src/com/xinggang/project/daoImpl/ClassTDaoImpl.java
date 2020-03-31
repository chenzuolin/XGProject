package com.xinggang.project.daoImpl;

import java.util.List;

import com.xinggang.project.base.BaseDaoImpl;
import com.xinggang.project.dao.ClassTDao;
import com.xinggang.project.entity.ClassT;

/**
 * 班组接口实现类
 */
public class ClassTDaoImpl extends BaseDaoImpl implements ClassTDao {
	// 增
	public boolean save(ClassT classT) {
		return super.BaseSave(classT);
	}

	// 删
	public boolean delete(ClassT classT) {
		return super.BaseDelete(classT);
	}

	// 改
	public boolean update(ClassT classT) {
		return super.BaseUpdate(classT);
	}

	// 通过id查询
	public ClassT getClassTId(Integer id) {
		String hql="from ClassT where classId="+id;
		return (ClassT) super.findById(hql);
	}

	// 查询全部
	@SuppressWarnings("unchecked")
	public List<ClassT> getAll() {
		String hql = "from ClassT where classDefinedOne!='"+0+"'";
		return (List<ClassT>) super.executeQuery(hql, null);
	}

	// 通过班组名称查询
	@SuppressWarnings("unchecked")
	public List<ClassT> getBanzuName(String banzuName) {
		String hql = "from ClassT where className='" + banzuName+"'";
		return (List<ClassT>) super.executeQuery(hql, null);
	}

	// 通过部门查询
	@SuppressWarnings("unchecked")
	public List<ClassT> getBumen(Integer bumenId) {
		String hql = "from ClassT where section=" + bumenId;
		return (List<ClassT>) super.executeQuery(hql, null);
	}

}
