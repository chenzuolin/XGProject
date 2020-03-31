package com.xinggang.project.serviceImpl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xinggang.project.dao.ClassTDao;
import com.xinggang.project.dao.SectionDao;
import com.xinggang.project.entity.ClassT;
import com.xinggang.project.form.ClassTForm;
import com.xinggang.project.service.ClassTService;

/**
 * 班组service实现类
 * 
 * @author Administrator
 * 
 */
@Transactional
public class ClassTServiceImpl implements ClassTService {
	private ClassTDao classTDao;
	
	private SectionDao sectionDao;
	
	
	public void setSectionDao(SectionDao sectionDao) {
		this.sectionDao = sectionDao;
	}

	public void setClassTDao(ClassTDao classTDao) {
		this.classTDao = classTDao;
	}
	

	// 添加班组
	public boolean save(ClassT classT) {
		List<ClassT> list = classTDao.getBanzuName(classT.getClassName());
		if (list.size() <= 0) {
			return classTDao.save(classT);
		} else {
			return false;
		}
	}

	// 删除班组，此项目中不能删除
	public boolean delete(ClassT classT) {

		return false;
	}

	// 修改部门
	public boolean update(ClassT classT) {
		ClassT c = classTDao.getClassTId(classT.getClassId());
		if (c == null) {
			return false;
		} else {
			return classTDao.update(classT);
		}
	}

	// 通过id查询
	public ClassT getClassTId(Integer id) {
		return classTDao.getClassTId(id);
	}

	// 查询全部
	public List<ClassT> getAll() {
		return classTDao.getAll();
	}

	// 通过班组名称查询
	public List<ClassT> getBanzuName(String banzuName) {
		return classTDao.getBanzuName(banzuName);
	}

	// 通过部门查询
	public List<ClassT> getBumen(Integer bumenId) {
		return classTDao.getBumen(bumenId);
	}

	//保存班组
	public boolean saveClassT(ClassTForm classTForm) {
		ClassT classT=new ClassT();
		classT.setClassName(classTForm.getClassName());
		classT.setClassHuman(classTForm.getClassHuman());
		classT.setClassRemark(classTForm.getClassRemark());
		classT.setClassDefinedOne("1");
		classT.setSection(sectionDao.getSectionId(classTForm.getSection()));
		boolean ok=this.save(classT);
		return ok;
	}
	//修改班组
	public boolean updateClassT(ClassTForm classTForm) {
		ClassT classT=this.getClassTId(classTForm.getClassId());
		classT.setClassName(classTForm.getClassName());
		classT.setClassRemark(classTForm.getClassRemark());
		classT.setSection(sectionDao.getSectionId(classTForm.getSection()));
		classT.setClassHuman(classTForm.getClassHuman());
		boolean ok=this.update(classT);
		return ok;
	}

}
