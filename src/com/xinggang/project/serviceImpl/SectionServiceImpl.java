package com.xinggang.project.serviceImpl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xinggang.project.dao.SectionDao;
import com.xinggang.project.entity.Section;
import com.xinggang.project.form.SectionForm;
import com.xinggang.project.service.SectionService;

/**
 * 部门Service实现类
 * 
 * @author Administrator
 * 
 */
@Transactional
public class SectionServiceImpl implements SectionService {
	private SectionDao sectionDao;

	public void setSectionDao(SectionDao sectionDao) {
		this.sectionDao = sectionDao;
	}

	// 添加部门
	public boolean save(Section section) {
		List<Section> list = sectionDao.getBumenName(section.getSectionName());
		if (list.size() <= 0) {
			return sectionDao.save(section);
		} else {
			return false;
		}
	}

	// 部门不能删除,
	public boolean delete(Section section) {
		return false;
	}

	// 修改部门
	public boolean update(Section section) {
		Section s = sectionDao.getSectionId(section.getSectionId());
		if (s == null) {
			return false;
		} else {
			return sectionDao.update(section);
		}
	}

	// 通过id查询
	public Section getSectionId(Integer id) {

		return sectionDao.getSectionId(id);
	}

	// 查询全部
	public List<Section> getAll() {
		return sectionDao.getAll();
	}

	// 通过部门名称查询
	public List<Section> getBumenName(String bumenName) {
		return sectionDao.getBumenName(bumenName);
	}

	//添加部门信息
	public boolean saveSection(SectionForm sectionForm) {
		Section section=new Section();
		section.setSectionName(sectionForm.getSectionName());
		section.setSectionHuman(sectionForm.getSectionHuman());
		section.setSectionRemark(sectionForm.getSectionRemark());
		section.setSectionDefinedOne("1");
		boolean ok=this.save(section);
		return ok;
	}

	//修改部门信息
	public boolean updateSection(SectionForm sectionForm){
		Section section=this.getSectionId(sectionForm.getSectionId());
		section.setSectionName(sectionForm.getSectionName());
		section.setSectionHuman(sectionForm.getSectionHuman());
		section.setSectionRemark(sectionForm.getSectionRemark());
		boolean ok=this.update(section);		
		return ok;
	}
	
	//保存部门信息
	public boolean deleteSection(SectionForm sectionForm){
		Section section=this.getSectionId(sectionForm.getSectionId());
		boolean ok= this.delete(section);
		return ok;
	}
}
