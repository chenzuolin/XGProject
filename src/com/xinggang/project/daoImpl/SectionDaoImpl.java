package com.xinggang.project.daoImpl;

import java.util.List;

import com.xinggang.project.base.BaseDaoImpl;
import com.xinggang.project.dao.SectionDao;
import com.xinggang.project.entity.Section;

/**
 * 部门接口实现类
 * 
 * @author Administrator
 * 
 */
public class SectionDaoImpl extends BaseDaoImpl implements SectionDao {
	// 增
	public boolean save(Section section) {
		return super.BaseSave(section);
	}

	// 删
	public boolean delete(Section section) {
		return super.BaseDelete(section);
	}

	// 改
	public boolean update(Section section) {
		return super.BaseUpdate(section);
	}

	// 通过id查询
	public Section getSectionId(Integer id) {
		String hql="from Section where sectionId="+id;
		return (Section) super.findById(hql);
	}

	// 查询全部
	@SuppressWarnings("unchecked")
	public List<Section> getAll() {
		String hql = "from Section where sectionDefinedOne!='"+0+"'";
		return (List<Section>) super.executeQuery(hql, null);
	}

	@SuppressWarnings("unchecked")
	public List<Section> getBumenName(String bumenName) {
		String hql = "from Section where sectionName='" + bumenName+"'";
		return (List<Section>) super.executeQuery(hql, null);
	}

}
