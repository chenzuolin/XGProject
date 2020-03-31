package com.xinggang.project.daoImpl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xinggang.project.base.BaseDaoImpl;
import com.xinggang.project.dao.InteriorUserDao;
import com.xinggang.project.entity.InteriorUser;

/**
 * 使用系统的公司内部人员实现类
 * 
 * @author Administrator
 * 
 */

@Transactional
public class InteriorUserDaoImpl extends BaseDaoImpl implements InteriorUserDao {
	// 增
	public boolean save(InteriorUser interiorUser) {
		return super.BaseSave(interiorUser);
	}

	// 删,此项目中不可删除，直接停用
	public boolean delete(InteriorUser interiorUser) {
		interiorUser.setIuserCease(0);
		return super.BaseUpdate(interiorUser);
	}

	// 改
	public boolean update(InteriorUser interiorUser) {
		return super.BaseUpdate(interiorUser);
	}

	// 通过id查询
	public InteriorUser getInteriorUserId(Integer id) {
		String hql = "from InteriorUser where iuserId=" + id;
		return (InteriorUser) super.findById(hql);
	}

	// 查询全部
	@SuppressWarnings("unchecked")
	public List<InteriorUser> getAll() {
		String hql = "from InteriorUser where iuserCease!=0";
		return (List<InteriorUser>) super.executeQuery(hql, null);
	}

	// 分页查询
	@SuppressWarnings("unchecked")
	public List<InteriorUser> getInteriorUserByPage(int pageNow, int rowSize) {
		String hql = "from InteriorUser where iuserCease!=0 order by iuserId desc";
		return (List<InteriorUser>) super.executeQueryByPage(hql, null,
				pageNow, rowSize);
	}

	// 查询数据总行数
	public int getInteriorUserByCount() {
		String hql = "select count(*) from InteriorUser where iuserCease!=0";
		return super.executeQueryRowCount(hql, null);
	}

	// 登录验证，通过用户名和密码查询
	@SuppressWarnings("unchecked")
	public List<InteriorUser> getLogin(String loginName, String loginPwd) {
		String hql = "from InteriorUser where iuserCease!=0 and iuserLoginName = '"
				+ loginName + "' and iuserPassword = '" + loginPwd + "'";
		return (List<InteriorUser>) super.executeQuery(hql, null);
	}

	// 通过部门查询
	@SuppressWarnings("unchecked")
	public List<InteriorUser> getBumen(Integer bumenId) {
		String hql = "from InteriorUser where iuserCease!=0 and section="
				+ bumenId;
		return (List<InteriorUser>) super.executeQuery(hql, null);
	}

	// 通过班组查询
	@SuppressWarnings("unchecked")
	public List<InteriorUser> getBanzu(Integer banzuId) {
		String hql = "from InteriorUser where iuserCease!=0 and classT="
				+ banzuId;
		return (List<InteriorUser>) super.executeQuery(hql, null);
	}

	// 通过职责查询
	@SuppressWarnings("unchecked")
	public List<InteriorUser> getZhize(Integer zhizeId) {
		String hql = "from InteriorUser where iuserCease!=0 and interiorUserDuty="
				+ zhizeId;
		return (List<InteriorUser>) super.executeQuery(hql, null);
	}

	// 查询出在线的内部人员
	@SuppressWarnings("unchecked")
	public List<InteriorUser> getZaixian() {
		String hql = "from InteriorUser where iuserCease!=0 and iuserOnline=0";// 1否：不在线，0是：在线，
		return (List<InteriorUser>) super.executeQuery(hql, null);
	}

	// 查询出正在作业的人员
	@SuppressWarnings("unchecked")
	public List<InteriorUser> getZuoye() {
		String hql = "from InteriorUser where iuserCease!=0 and iuserWork=0";// 1否：没有作业，0是：正在作业
		return (List<InteriorUser>) super.executeQuery(hql, null);
	}

	// 查询出停用的人员
	@SuppressWarnings("unchecked")
	public List<InteriorUser> getTingyong() {
		String hql = "from InteriorUser where iuserCease=0";
		return (List<InteriorUser>) super.executeQuery(hql, null);
	}

	// (保管员)查询出来没有作业并且在线的人员
	@SuppressWarnings("unchecked")
	public List<InteriorUser> getNotZuoye() {
		String hql = "from InteriorUser where iuserCease!=0 and iuserOnline=0";// 1否，0是
		return (List<InteriorUser>) super.executeQuery(hql, null);
	}

	// 通过用户名查询
	@SuppressWarnings("unchecked")
	public List<InteriorUser> getLoginName(String loginName) {
		String hql = "from InteriorUser where iuserLoginName='" + loginName
				+ "' and iuserCease != 0";
		return (List<InteriorUser>) super.executeQuery(hql, null);
	}

	// 根据真实姓名查询
	@SuppressWarnings("unchecked")
	public List<InteriorUser> getName(String iuserName) {
		String hql = "from InteriorUser where iuserName like '%" + iuserName + "%'";
		return (List<InteriorUser>) super.executeQuery(hql, null);
	}

	// 通过部门和班组查询
	@SuppressWarnings("unchecked")
	public List<InteriorUser> getBuAndBan(String bumen, String banzu) {
		String hql = "from InteriorUser where iuserCease!=0 and iuserOnline=0 ";
		if (bumen != null && bumen.equals("") == false) {
			hql += "and  (section.sectionName like ('%" + bumen + "%'))";
		}
		if (banzu != null && banzu.equals("") == false) {
			hql += "and (classT.className like ('%" + banzu + "%'))";
		}
		return (List<InteriorUser>) super.executeQuery(hql, null);
	}
	

	//通过权限名称进行查询人员
	@SuppressWarnings("unchecked")
	public List<InteriorUser> getPowerMan(String Power){
		String hql = "from InteriorUser where iuserCease!=0 and interiorUserDuty.interiorUserDutyId in (" +
				"select interiorUserDuty.interiorUserDutyId from Powers where functions.functionName like ('%"+Power+"%'))";
		return (List<InteriorUser>) super.executeQuery(hql, null);
	}

}
