package com.xinggang.project.serviceImpl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xinggang.project.dao.InteriorUserDao;
import com.xinggang.project.entity.ClassT;
import com.xinggang.project.entity.InteriorUser;
import com.xinggang.project.entity.InteriorUserDuty;
import com.xinggang.project.entity.Section;
import com.xinggang.project.form.InteriorUserForm;
import com.xinggang.project.service.InteriorUserService;
import com.xinggang.project.tools.PresentTime;

/**
 * 使用系统的公司内部人员Service实现类
 * 
 * @author Administrator
 * 
 */
@Transactional
public class InteriorUserServiceImpl implements InteriorUserService {
	private InteriorUserDao interiorUserDao;

	public void setInteriorUserDao(InteriorUserDao interiorUserDao) {
		this.interiorUserDao = interiorUserDao;
	}

	// 添加内部人员
	public boolean save(InteriorUser interiorUser) {
		// 首先判断该用户名是否存在
		List<InteriorUser> list = interiorUserDao.getLoginName(interiorUser
				.getIuserLoginName());
		if (list.size() <= 0) {
			return interiorUserDao.save(interiorUser);
		} else {
			return false;
		}
	}

	// 删除内部人员
	public boolean delete(InteriorUser interiorUser) {
		InteriorUser i = interiorUserDao.getInteriorUserId(interiorUser
				.getIuserId());
		if (i == null) {
			return false;
		} else {
			return interiorUserDao.delete(interiorUser);
		}
	}

	// 修改
	public boolean update(InteriorUser interiorUser) {
		return this.interiorUserDao.update(interiorUser);
	}

	// 通过id查询
	public InteriorUser getInteriorUserId(Integer id) {

		return interiorUserDao.getInteriorUserId(id);
	}

	// 查询全部
	public List<InteriorUser> getAll() {

		return interiorUserDao.getAll();
	}

	// 分页查询
	public List<InteriorUser> getInteriorUserByPage(int pageNow, int rowSize) {

		return interiorUserDao.getInteriorUserByPage(pageNow, rowSize);
	}

	// 查询出数据的总行数
	public int getInteriorUserByCount() {

		return interiorUserDao.getInteriorUserByCount();
	}

	// -------------------------------修改后
	// 通过用户名和密码查询，登录时进行判断
	public List<InteriorUser> getLogin(String loginName, String loginPwd) {
		List<InteriorUser> iulist = interiorUserDao.getLogin(loginName,
				loginPwd);
		return iulist;// 否则返回集合
	}

	// 通过部门查询
	public List<InteriorUser> getBumen(Integer bumenId) {

		return interiorUserDao.getBumen(bumenId);
	}

	// 通过班组查询
	public List<InteriorUser> getBanzu(Integer banzuId) {

		return interiorUserDao.getBanzu(banzuId);
	}

	// 通过职责查询
	public List<InteriorUser> getZhize(Integer zhizeId) {

		return interiorUserDao.getZhize(zhizeId);
	}

	// 查询在线人员
	public List<InteriorUser> getZaixian() {

		return interiorUserDao.getZaixian();
	}

	// 根据真实姓名查询
	public List<InteriorUser> getName(String iuserName) {
		return interiorUserDao.getName(iuserName);
	};

	// 查询作业人员
	public List<InteriorUser> getZuoye() {

		return interiorUserDao.getZuoye();
	}

	// 查询没有作业的人员
	public List<InteriorUser> getNotZuoye() {

		return interiorUserDao.getNotZuoye();
	}

	// 查询停用的人员
	public List<InteriorUser> getTingyong() {

		return interiorUserDao.getTingyong();
	}

	// 通过用户名查询，判断用户名是否存在
	public List<InteriorUser> getLoginName(String loginName) {
		return interiorUserDao.getLoginName(loginName);
	}

	// 保存数据
	public boolean saveInteriorUser(InteriorUserForm interiorUserForm,
			ClassT classT, InteriorUserDuty interiorUserDuty, Section section2) {
		// 根据登录名查询，如果有就让注册
		List<InteriorUser> list = interiorUserDao.getLoginName(interiorUserForm
				.getIuserLoginName());
		// 根据姓名查询，如果有就让注册
		List<InteriorUser> listName = interiorUserDao.getName(interiorUserForm
				.getIuserName());
		boolean ok = false;
		PresentTime presentTime = new PresentTime();
		// 如果登录名和真实姓名有一个有，就返回false,不让注册
		if (list.size() > 0 || listName.size() > 0) {
			ok = false;
		} else {
			InteriorUser interiorUser = new InteriorUser();
			interiorUser.setIuserId(interiorUserForm.getIuserId());
			interiorUser.setSection(section2);
			interiorUser.setInteriorUserDuty(interiorUserDuty);
			interiorUser.setClassT(classT);
			interiorUser
					.setIuserLoginName(interiorUserForm.getIuserLoginName());
			interiorUser.setIuserPassword(interiorUserForm.getIuserPassword());
			interiorUser.setIuserCreateTime(presentTime.getTimes());
			interiorUser.setIuserTel(interiorUserForm.getIuserTel());
			interiorUser.setIuserName(interiorUserForm.getIuserName());
			interiorUser.setIuserSex(interiorUserForm.getIuserSex());
			interiorUser.setIuserCease(1);
			interiorUser.setIuserWork(1);
			interiorUser.setIuserOnline(1);
			interiorUser.setIuserRemark(interiorUserForm.getIuserRemark());
			ok = this.save(interiorUser);
		}
		return ok;
	}

	public boolean updateInteriorUser(InteriorUserForm interiorUserForm,
			ClassT classT, InteriorUserDuty interiorUserDuty, Section section2) {

		InteriorUser interiorUser = this.getInteriorUserId(interiorUserForm
				.getIuserId());
		interiorUser.setSection(section2);
		interiorUser.setInteriorUserDuty(interiorUserDuty);
		interiorUser.setClassT(classT);
		//interiorUser.setIuserLoginName(interiorUserForm.getIuserLoginName());
		interiorUser.setIuserPassword(interiorUserForm.getIuserPassword());
		interiorUser.setIuserTel(interiorUserForm.getIuserTel());
		interiorUser.setIuserName(interiorUserForm.getIuserName());
		//interiorUser.setIuserSex(interiorUserForm.getIuserSex());
		interiorUser.setIuserRemark(interiorUserForm.getIuserRemark());
		boolean ok = this.update(interiorUser);
		return ok;
	}

	// 修改内部人员是否作业状态
	public boolean updateIuserWork(Integer id, Integer iuserWork) {
		// 根据id查询出改人员的信息
		InteriorUser interiorUser = this.getInteriorUserId(id);
		// 修改状态
		interiorUser.setIuserWork(iuserWork);
		// 得到并返回结果
		boolean ok = this.update(interiorUser);
		return ok;
	}

	// 修改在线状态
	public boolean updateIuserOnline(Integer id, Integer iuserOnline) {
		// 根据id查询出改人员的信息
		InteriorUser interiorUser = this.getInteriorUserId(id);
		interiorUser.setIuserOnline(iuserOnline);
		boolean ok = this.update(interiorUser);
		return ok;
	}

	// 通过部门和班组查询
	public List<InteriorUser> getBuAndBan(String bumen, String banzu) {

		return interiorUserDao.getBuAndBan(bumen, banzu);
	}

	//通过权限名称进行查询人员
	public List<InteriorUser> getPowerMan(String Power){
		return this.interiorUserDao.getPowerMan(Power);
	}

}
