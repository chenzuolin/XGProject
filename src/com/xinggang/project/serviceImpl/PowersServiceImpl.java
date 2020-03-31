package com.xinggang.project.serviceImpl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xinggang.project.dao.FunctionsDao;
import com.xinggang.project.dao.InteriorUserDutyDao;
import com.xinggang.project.dao.PowersDao;
import com.xinggang.project.entity.Powers;
import com.xinggang.project.form.PowersForm;
import com.xinggang.project.service.PowersService;

/**
 * 权限service实现类
 * 
 * @author Administrator
 * 
 */
@Transactional
public class PowersServiceImpl implements PowersService {
	private PowersDao powersDao;
	
	private InteriorUserDutyDao interiorUserDutyDao;
	
	private FunctionsDao functionsDao;
	
	

	public InteriorUserDutyDao getInteriorUserDutyDao() {
		return interiorUserDutyDao;
	}


	public void setInteriorUserDutyDao(InteriorUserDutyDao interiorUserDutyDao) {
		this.interiorUserDutyDao = interiorUserDutyDao;
	}


	public FunctionsDao getFunctionsDao() {
		return functionsDao;
	}


	public void setFunctionsDao(FunctionsDao functionsDao) {
		this.functionsDao = functionsDao;
	}


	public PowersDao getPowersDao() {
		return powersDao;
	}


	public void setPowersDao(PowersDao powersDao) {
		this.powersDao = powersDao;
	}
	

	// 增加
	public boolean save(Powers powers) {
		List<Powers> list = powersDao
				.getZG(powers.getInteriorUserDuty().getInteriorUserDutyId(),
						powers.getFunctions().getFunctionId());
		if (list.size() <= 0) {// 查询对应职责是否具有该功能，如果具有该功能，则不去添加
			return powersDao.save(powers);
		} else {
			return false;
		}
	}

	// 修改
	public boolean update(Powers powers) {
		Powers p = powersDao.getPowersId(powers.getPowerId());// 判断该对象是否存在，如果不存在则修改失败
		if (p == null) {
			return false;
		} else {
			return powersDao.update(powers);
		}
	}

	// 删除
	public boolean delete(Powers powers) {
		Powers p = powersDao.getPowersId(powers.getPowerId());// 判断该对象是否存在，如果不存在，则删除失败
		if (p == null) {
			return false;
		} else {
			return powersDao.delete(powers);
		}
	}

	// 通过id查询
	public Powers getPowersId(Integer id) {

		return powersDao.getPowersId(id);
	}

	// 查询全部
	public List<Powers> getAll() {

		return powersDao.getAll();
	}

	// 通过职责查询
	public List<Powers> getZhize(Integer zhizeId) {

		return powersDao.getZhize(zhizeId);
	}

	// 通过功能查询
	public List<Powers> getGongneng(Integer gongnengId) {

		return powersDao.getGongneng(gongnengId);
	}

	// 通过功能和职责并且查询
	public List<Powers> getZG(Integer zhizeId, Integer gongnengId) {
		return powersDao.getZG(zhizeId, gongnengId);
	}

	public boolean addPowers(PowersForm powersForm) {		
		Powers powers=new Powers();		
		powers.setInteriorUserDuty(interiorUserDutyDao.getInteriorUserDutyId(powersForm.getInteriorUserDuty()));
		Integer[] functionId= powersForm.getFunctionId();
		for(int i=0;i<functionId.length;i++){
			powers.setFunctions(functionsDao.getFunctionsId(functionId[i]));
		}		
		powers.setPowerRemark(powersForm.getPowerRemark());
		boolean ok=powersDao.save(powers);
		return ok;
	}

	//修改权限
	public boolean updatePowers(Integer id) {
		Powers powers=powersDao.getPowersId(id);
		boolean ok=powersDao.delete(powers);
		return ok;
	}

}
