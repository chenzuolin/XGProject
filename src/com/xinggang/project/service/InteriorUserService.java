package com.xinggang.project.service;

import java.util.List;

import com.xinggang.project.entity.ClassT;
import com.xinggang.project.entity.InteriorUser;
import com.xinggang.project.entity.InteriorUserDuty;
import com.xinggang.project.entity.Section;
import com.xinggang.project.form.InteriorUserForm;

/**
 * 使用系统的公司内部人员Service
 * 
 * @author Administrator
 * 
 */
public interface InteriorUserService {
	// 增
	public boolean save(InteriorUser interiorUser);

	// 删除
	public boolean delete(InteriorUser interiorUser);

	// 修改
	public boolean update(InteriorUser interiorUser);

	// 通过id查询
	public InteriorUser getInteriorUserId(Integer id);

	// 查询全部
	public List<InteriorUser> getAll();

	// 分页查询
	public List<InteriorUser> getInteriorUserByPage(int pageNow, int rowSize);

	// 查询数据总行数
	public int getInteriorUserByCount();

	// 通过用户名查询j
	public List<InteriorUser> getLoginName(String loginName);

	// 通过用户名和密码查询，在登录时用到。
	public List<InteriorUser> getLogin(String loginName, String loginPwd);

	// 根据真实姓名查询
	public List<InteriorUser> getName(String iuserName);

	// 通过部门查询
	public List<InteriorUser> getBumen(Integer bumenId);

	// 通过班组查询
	public List<InteriorUser> getBanzu(Integer banzuId);

	// 通过职责查询
	public List<InteriorUser> getZhize(Integer zhizeId);

	// 查询在线内部人员
	public List<InteriorUser> getZaixian();

	// 查询是否作业
	public List<InteriorUser> getZuoye();

	// 查询出没有作业的人员
	public List<InteriorUser> getNotZuoye();

	// 查询停用人员
	public List<InteriorUser> getTingyong();

	// 添加内部人员表
	public boolean saveInteriorUser(InteriorUserForm interiorUserForm,
			ClassT classT, InteriorUserDuty interiorUserDuty, Section section2);

	// 修改内部人员
	public boolean updateInteriorUser(InteriorUserForm interiorUserForm,
			ClassT classT, InteriorUserDuty interiorUserDuty, Section section2);

	// 修改作业状态
	public boolean updateIuserWork(Integer id, Integer IuserWork);

	// 修改在线状态
	public boolean updateIuserOnline(Integer id, Integer iuserOnline);

	// 通过部门和班组查询
	public List<InteriorUser> getBuAndBan(String bumen, String banzu);
	
	//通过权限名称进行查询人员
	public List<InteriorUser> getPowerMan(String Power);
}
