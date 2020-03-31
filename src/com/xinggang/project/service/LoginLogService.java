package com.xinggang.project.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.xinggang.project.entity.LoginLog;

/**
 * 登录日志Service
 * 
 * @author Administrator
 * 
 */
public interface LoginLogService {
	// 增加
	public boolean save(LoginLog loginLog);

	// 删除
	public boolean delete(LoginLog loginLog);

	// 修改
	public boolean update(LoginLog loginLog);

	// 通过id查询
	public LoginLog getLoginLogId(String id);

	// 查询全部，通过登录时间的范围显示，也可以通过登录人，进行模糊查询
	public List<LoginLog> getAll(String begin, String finish, String loginname);

	// 分页查询
	public List<LoginLog> getLoginLogByPage(String begin, String finish,
			String loginname, String remark, int pageNow, int rowSize);

	// 获得查询数据的总行数
	public int getLoginLogByCount(String begin, String finish,
			String loginname, String remark, int rowSize);

	// 向登录日志表中增加固定的操作，每一个用户登录时的添加 ,传入的用户名
	public String saveLogin(String loginName, HttpServletRequest request,
			String logType);

	// 操作记录中增加内容,传入登录日志的编号和要记录的内容
	public void updateLogin(String loginId, String context);
}
