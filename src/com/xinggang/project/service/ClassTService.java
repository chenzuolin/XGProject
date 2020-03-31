package com.xinggang.project.service;

import java.util.List;

import com.xinggang.project.entity.ClassT;
import com.xinggang.project.form.ClassTForm;

/**
 * 班组service
 * 
 * @author Administrator
 * 
 */
public interface ClassTService {
	// 增
	public boolean save(ClassT classT);

	// 删
	public boolean delete(ClassT classT);

	// 改
	public boolean update(ClassT classT);

	// 通过id查询
	public ClassT getClassTId(Integer id);

	// 查询全部
	public List<ClassT> getAll();

	// 通过班组名称查询
	public List<ClassT> getBanzuName(String banzuName);

	// 通过部门查询
	public List<ClassT> getBumen(Integer bumenId);
	
	//保存班组
	public boolean saveClassT(ClassTForm classTForm);
	
	//修改班组
	public boolean updateClassT(ClassTForm classTForm);
}
