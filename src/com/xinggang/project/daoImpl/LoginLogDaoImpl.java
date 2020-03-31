package com.xinggang.project.daoImpl;

import java.util.List;

import com.xinggang.project.base.BaseDaoImpl;
import com.xinggang.project.dao.LoginLogDao;
import com.xinggang.project.entity.LoginLog;

/**
 * 登录日志Dao实现类
 * 
 * @author Administrator
 * 
 */
public class LoginLogDaoImpl extends BaseDaoImpl implements LoginLogDao {
	// 增加
	public boolean save(LoginLog loginLog) {
		return super.BaseSave(loginLog);
	}

	// 删除
	public boolean delete(LoginLog loginLog) {
		return super.BaseDelete(loginLog);
	}

	// 修改
	public boolean update(LoginLog loginLog) {
		return super.BaseUpdate(loginLog);
	}

	// 通过id查询
	public LoginLog getLoginLogId(String id) {
		return (LoginLog) super.findById(LoginLog.class, id);
	}

	// 查询全部
	@SuppressWarnings("unchecked")
	public List<LoginLog> getAll(String begin, String finish, String loginname) {
		String hql = "from LoginLog where llogTime>='" + begin
				+ "' and llogTime<= '" + finish + "' and llogName like ('%"
				+ loginname + "%') order by llogTime desc";
		return (List<LoginLog>) super.executeQuery(hql, null);
	}

	// 分页查询
	@SuppressWarnings("unchecked")
	public List<LoginLog> getLoginLogByPage(String begin, String finish,
			String loginname, String remark, int pageNow, int rowSize) {
		String hql = "from LoginLog where llogTime>='" + begin
				+ "' and llogTime<='" + finish + "' and llogName like ('%"
				+ loginname + "%') and llogRemark like ('%" + remark
				+ "%') order by llogTime desc";
		return (List<LoginLog>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 获得总数据行数
	public int getLoginLogByCount(String begin, String finish,
			String loginname, String remark, int rowSize) {
		String hql = "select count(*) from LoginLog where llogTime>='" + begin
				+ "' and llogTime<='" + finish + "' and llogName like ('%"
				+ loginname + "%') and llogRemark like ('%" + remark + "%')";
		return super.queryPageCount(hql, null, rowSize);
	}

}
