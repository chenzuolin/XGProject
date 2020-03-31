package com.xinggang.project.dao;

import java.util.List;

import com.xinggang.project.entity.InteriorUser;

/**
 * 使用系统的公司内部人员Dao
 * 
 * @author Administrator
 * 
 */
public interface InteriorUserDao {
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
	
	public List<InteriorUser> getZhize(Integer zhizeId);

	// 通过部门和班组查询
	public List<InteriorUser> getBuAndBan(String bumen,String banzu);

	// 查询在线内部人员
	public List<InteriorUser> getZaixian();

	// 查询是否作业
	public List<InteriorUser> getZuoye();

	// 查询出没有作业的人员
	public List<InteriorUser> getNotZuoye();

	// 查询停用人员
	public List<InteriorUser> getTingyong();
	
	//通过权限名称进行查询人员
	public List<InteriorUser> getPowerMan(String Power);
		
	
}
