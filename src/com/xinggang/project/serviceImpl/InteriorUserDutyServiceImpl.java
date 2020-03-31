package com.xinggang.project.serviceImpl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xinggang.project.dao.InteriorUserDutyDao;
import com.xinggang.project.entity.InteriorUserDuty;
import com.xinggang.project.form.InteriorUserDutyForm;
import com.xinggang.project.service.InteriorUserDutyService;

/**
 * 使用系统的公司内部人员职责service类
 * 
 * @author Administrator
 * 
 */
@Transactional
public class InteriorUserDutyServiceImpl implements InteriorUserDutyService {
	private InteriorUserDutyDao interiorUserDutyDao;

	public void setInteriorUserDutyDao(InteriorUserDutyDao interiorUserDutyDao) {
		this.interiorUserDutyDao = interiorUserDutyDao;
	}

	// 添加
	public boolean save(InteriorUserDuty interiorUserDuty) {
		List<InteriorUserDuty> list = interiorUserDutyDao
				.getZhizeName(interiorUserDuty.getInteriorUserDutyName());
		if (list.size() <= 0) {
			return interiorUserDutyDao.save(interiorUserDuty);
		} else {
			return false;
		}
	}

	// 修改
	public boolean update(InteriorUserDuty interiorUserDuty) {
		InteriorUserDuty i = interiorUserDutyDao
				.getInteriorUserDutyId(interiorUserDuty.getInteriorUserDutyId());
		if (i == null) {
			return false;
		} else {
			return interiorUserDutyDao.update(interiorUserDuty);
		}
	}

	// 删除,此项目中不可以删除
	public boolean delete(InteriorUserDuty interiorUserDuty) {
		return false;
	}

	// 通过id查询
	public InteriorUserDuty getInteriorUserDutyId(Integer id) {
		return interiorUserDutyDao.getInteriorUserDutyId(id);
	}

	// 查询全部
	public List<InteriorUserDuty> getAll() {
		return interiorUserDutyDao.getAll();
	}

	// 通过职责名称查询
	public List<InteriorUserDuty> getZhizeName(String zhizeName) {
		return interiorUserDutyDao.getZhizeName(zhizeName);
	}

	// 通过部门查询
	public List<InteriorUserDuty> getBumen(Integer bumenId) {
		return interiorUserDutyDao.getBumen(bumenId);
	}
	
	//保存内部人员职责表
	public boolean saveInteriorUserDuty(InteriorUserDutyForm interiorUserDutyForm) {
		//内部人员职责表
		InteriorUserDuty interiorUserDuty=new InteriorUserDuty();
		interiorUserDuty.setInteriorUserDutyName(interiorUserDutyForm.getInteriorUserDutyName());
		interiorUserDuty.setInteriorUserDutyDefinedOne("1");
		interiorUserDuty.setInteriorUserDutyRemark(interiorUserDutyForm.getInteriorUserDutyRemark());
		
		//interiorUserDuty.setSection(section);
		boolean ok=this.save(interiorUserDuty);
		return ok;
	}

}
