package com.xinggang.project.serviceImpl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xinggang.project.dao.ClassifyDao;
import com.xinggang.project.entity.Classify;
import com.xinggang.project.service.ClassifyService;

/**
 * 功能类别service实现类
 * 
 * @author Administrator
 * 
 */
@Transactional
public class ClassifyServiceImpl implements ClassifyService {
	// 调用功能类别注入Dao
	private ClassifyDao classifyDao;

	public void setClassifyDao(ClassifyDao classifyDao) {
		this.classifyDao = classifyDao;
	}

	// 添加
	public boolean save(Classify classify) {
		boolean ok = true;
		List<Classify> list = classifyDao.getAll();
		for (Classify c : list) {
			if (c.getClassifyName().equals(classify.getClassifyName())) { // 判断类别名称是否存在
				ok = false;
			}
		}
		if (ok) {
			return classifyDao.save(classify);
		} else {
			return false;
		}
	}

	// 修改
	@SuppressWarnings("unchecked")
	public boolean update(Classify classify) {
		boolean ok = true;
		List<Classify> list = (List<Classify>) classifyDao
				.getClassifyId(classify.getClassifyId());
		if (list.size() <= 0) {// 判断修改的对象是否存在
			ok = false;
		}
		if (ok) {
			return classifyDao.update(classify);
		} else {
			return false;
		}
	}

	// 删除
	public boolean delete(Classify classify) {

		boolean ok = true;
		Classify c = classifyDao.getClassifyId(classify.getClassifyId());
		if (c == null) {// 判断修改的对象是否存在,如果存在，则不需要删除
			ok = false;
		}
		if (ok == false) {
			return true;
		} else {
			return classifyDao.delete(classify);
		}
	}

	// 查询全部
	public List<Classify> getAll() {

		return classifyDao.getAll();
	}

	// 通过id查询
	public Classify getClassifyId(Integer id) {

		return classifyDao.getClassifyId(id);
	}

}
