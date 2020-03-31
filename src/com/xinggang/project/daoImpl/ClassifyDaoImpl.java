package com.xinggang.project.daoImpl;

import java.util.List;

import com.xinggang.project.base.BaseDaoImpl;
import com.xinggang.project.dao.ClassifyDao;
import com.xinggang.project.entity.Classify;

/**
 * 功能类别接口实现类
 * 
 * @author Administrator
 * 
 */
public class ClassifyDaoImpl extends BaseDaoImpl implements ClassifyDao {

	// 添加
	public boolean save(Classify classify) {
	
		return super.BaseSave(classify);
	}

	// 修改
	public boolean update(Classify classify) {
		return super.BaseUpdate(classify);
	}

	// 删除
	public boolean delete(Classify classify) {
		return super.BaseDelete(classify);
	}

	// 查询全部
	@SuppressWarnings("unchecked")
	public List<Classify> getAll() {
		String hql = "from Classify";
		return (List<Classify>) this.executeQuery(hql, null);
	}

	// 通过id查询
	public Classify getClassifyId(Integer id) {
		String hql="from Classify where classifyId="+id;
		return (Classify) super.findById(hql);
	}

}
