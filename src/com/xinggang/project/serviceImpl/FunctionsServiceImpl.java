package com.xinggang.project.serviceImpl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xinggang.project.dao.FunctionsDao;
import com.xinggang.project.entity.Functions;
import com.xinggang.project.entity.InteriorUserDuty;
import com.xinggang.project.service.FunctionsService;

/**
 * 功能service实现类
 * 
 * @author Administrator
 * 
 */
@Transactional
public class FunctionsServiceImpl implements FunctionsService {

	private FunctionsDao functionsDao;

	public void setFunctionsDao(FunctionsDao functionsDao) {
		this.functionsDao = functionsDao;
	}

	// 添加
	public boolean save(Functions functions) {
		List<Functions> list = functionsDao.getFunctionsName(functions
				.getFunctionName());
		if (list.size() <= 0) {// 判断功能名称是否存在，如果存在，请重新输入功能名称
			return functionsDao.save(functions);
		} else {
			return false;
		}
	}

	// 修改
	public boolean update(Functions functions) {
		// 判断要修改的对象是否存在
		Functions fun = functionsDao.getFunctionsId(functions.getFunctionId());
		if (fun == null) {
			return false;
		} else {// 如果存在则去修改，否则直接返回false；
			return functionsDao.update(functions);
		}
	}

	// 删除
	public boolean delete(Functions functions) {
		Functions fun = functionsDao.getFunctionsId(functions.getFunctionId());
		if (fun == null) {// 判断该对象是否存在，如果存在则去删除，如果不存在，不用删除
			return true;
		} else {
			return functionsDao.delete(functions);
		}
	}

	// 查询全部
	public List<Functions> getAll() {

		return functionsDao.getAll();
	}

	// 通过id查询
	public Functions getFunctionsId(Integer id) {

		return functionsDao.getFunctionsId(id);
	}

	// 通过功能名称查询
	public List<Functions> getFunctionsName(String functionsName) {

		return functionsDao.getFunctionsName(functionsName);
	}

	// 通过类别编号查询
	public List<Functions> getFunctionsClassify(Integer classifyId) {
		return functionsDao.getFunctionsClassify(classifyId);
	}

	public boolean save(InteriorUserDuty interiorUserDuty) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean update(InteriorUserDuty interiorUserDuty) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean delete(InteriorUserDuty interiorUserDuty) {
		// TODO Auto-generated method stub
		return false;
	}

	public InteriorUserDuty getInteriorUserDutyId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
