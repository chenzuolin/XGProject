package com.xinggang.project.daoImpl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xinggang.project.base.BaseDaoImpl;
import com.xinggang.project.entity.Bursary;

/**
 * 库房Dao实现类
 * 
 * @author Administrator
 * 
 */
@Transactional
public class BursaryDaoImpl extends BaseDaoImpl implements
		com.xinggang.project.dao.BursaryDao {

	// 增加
	public boolean save(Bursary bursary) {
		return super.BaseSave(bursary);
	}

	// 删除
	public boolean delete(Bursary bursary) {
		return super.BaseDelete(bursary);
	}

	// 修改
	public boolean update(Bursary bursary) {
		return super.BaseUpdate(bursary);
	}

	// 通过id查询
	public Bursary getBursaryId(Integer id) {
		String hql = "from Bursary where bursaryId=" + id;
		return (Bursary) super.findById(hql);
	}

	// 查询全部
	@SuppressWarnings("unchecked")
	public List<Bursary> getAll() {
		String hql = "from Bursary where bursaryDefinedOne!='" + 0 + "'";
		return (List<Bursary>) super.executeQuery(hql, null);
	}

	@SuppressWarnings("unchecked")
	public List<Bursary> getKufangName(String kufangName) {
		String hql = "from Bursary where bursaryName='" + kufangName + "'";
		return (List<Bursary>) super.executeQuery(hql, null);
	}

}
