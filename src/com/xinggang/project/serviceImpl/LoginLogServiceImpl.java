package com.xinggang.project.serviceImpl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;

import com.xinggang.project.dao.LoginLogDao;
import com.xinggang.project.entity.LoginLog;
import com.xinggang.project.service.LoginLogService;
import com.xinggang.project.tools.AchieveIp;
import com.xinggang.project.tools.PageRow;
import com.xinggang.project.tools.PresentTime;

/**
 * 登录日志Service
 * 
 * @author Administrator
 * 
 */
@Transactional
public class LoginLogServiceImpl implements LoginLogService {
	private LoginLogDao loginLogDao;

	// 编号类
	private PageRow pr = new PageRow();

	public void setLoginLogDao(LoginLogDao loginLogDao) {
		this.loginLogDao = loginLogDao;
	}

	// 添加
	public boolean save(LoginLog loginLog) {

		return loginLogDao.save(loginLog);
	}

	// 删除
	public boolean delete(LoginLog loginLog) {
		LoginLog l = loginLogDao.getLoginLogId(loginLog.getLlogId());
		if (l == null) {
			return false;
		}
		return loginLogDao.delete(loginLog);
	}

	// 修改
	public boolean update(LoginLog loginLog) {
		LoginLog l = loginLogDao.getLoginLogId(loginLog.getLlogId());
		if (l == null) {
			return false;
		}
		return loginLogDao.update(loginLog);
	}

	// 通过id查询
	public LoginLog getLoginLogId(String id) {

		return loginLogDao.getLoginLogId(id);
	}

	// 查询全部
	public List<LoginLog> getAll(String begin, String finish, String loginname) {

		return loginLogDao.getAll(begin, finish, loginname);
	}

	// 通过多种条件分页查询
	public List<LoginLog> getLoginLogByPage(String begin, String finish,
			String loginname, String remark, int pageNow, int rowSize) {

		return loginLogDao.getLoginLogByPage(begin, finish, loginname, remark,
				pageNow, rowSize);
	}

	// 通过多种条件查询出数据的总页数
	public int getLoginLogByCount(String begin, String finish,
			String loginname, String remark, int rowSize) {

		return loginLogDao.getLoginLogByCount(begin, finish, loginname, remark,
				rowSize);
	}

	// 每一个用户登录的时候向登录日志表中增加数据,传入登录名和request
	public String saveLogin(String loginName, HttpServletRequest request,
			String logType) {
		/*
		 * Random rand=new Random(); int n=rand.nextInt(100);
		 */
		PresentTime pt = new PresentTime();// 获得当前时间
		AchieveIp AIp = new AchieveIp();// 获得登录人的ip地址

		String ip = AIp.getIpAddress(request);

		String Times = pt.getTimes();// 当前的系统时间
		String Dates = pt.getDatesNotTime();// 获得当前时间，但是没有时分秒

		LoginLog ll = new LoginLog();// 实例化登录日志类
		String logId = Dates + pr.getRiZhiNumber();
		request.getSession().setAttribute("loginlogId", logId);// 将登录日志的编号保存在request中
		ll.setLlogId(logId);// 设置登录日志的编号，当前时间加上用户名
		ll.setLlogName(loginName);// 设置登录人
		ll.setLlogTime(Times);// 设置登录的时间
		ll.setLlogIp(ip);// 设置登录的ip地址
		ll.setLlogDefinedOne(logType);// 设置登录的类型
		ll.setLlogRemark(loginName + "登录，");// 操作记录
		this.loginLogDao.save(ll);// 向登录日志表中增加记录
		// 返回登录日志的编号，根据登录日志的编号，后期进行增加操作记录
		return logId;

	}

	// 向操作记录中增加操作的内容，传入登录日志编号，内容
	public void updateLogin(String loginlogId, String context) {
		LoginLog ll = this.loginLogDao.getLoginLogId(loginlogId);// 通过编号查询出登录日志中的数据，
		String jilu = ll.getLlogRemark();
		ll.setLlogRemark(jilu + context + "；");// 增加登录日志的操作内容
		this.loginLogDao.update(ll);// 进行修改
	}

}
