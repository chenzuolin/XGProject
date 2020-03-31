package com.xinggang.project.service;

import java.util.List;

import com.xinggang.project.entity.InteriorUserDuty;
import com.xinggang.project.form.InteriorUserDutyForm;

/**
 * 使用系统的公司内部人员职责service
 * 
 * @author Administrator
 * 
 */
public interface InteriorUserDutyService {
	// 增
	public boolean save(InteriorUserDuty interiorUserDuty);

	// 修改
	public boolean update(InteriorUserDuty interiorUserDuty);

	// 删除
	public boolean delete(InteriorUserDuty interiorUserDuty);

	// 通过id查询
	public InteriorUserDuty getInteriorUserDutyId(Integer id);

	// 查询全部
	public List<InteriorUserDuty> getAll();

	// 通过职责名称查询
	public List<InteriorUserDuty> getZhizeName(String zhizeName);

	// 通过部门查询
	public List<InteriorUserDuty> getBumen(Integer bumenId);
	
	public boolean saveInteriorUserDuty(InteriorUserDutyForm interiorUserDutyForm);
}
