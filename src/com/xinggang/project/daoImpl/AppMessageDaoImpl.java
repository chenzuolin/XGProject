package com.xinggang.project.daoImpl;

import java.util.List;

import com.xinggang.project.base.BaseDaoImpl;
import com.xinggang.project.dao.AppMessageDao;
import com.xinggang.project.entity.AppMessage;

public class AppMessageDaoImpl extends BaseDaoImpl implements AppMessageDao {
	// 添加消息
	public boolean save(AppMessage message) {
		return super.BaseSave(message);
	}

	// 修改消息
	public boolean update(AppMessage message) {
		return super.BaseUpdate(message);
	}

	// 统计未读消息的数据也就是状态为1的
	public int getWeiCount(Integer clientId, String status) {
		String hql = "select count(*) from AppMessage where (appClientOne="
				+ clientId + " or appClientTwo = " + clientId
				+ ") and appStatus='" + status + "'";
		int count =  super.executeQueryRowCount(hql, null);
		return count;
	}

	// 查询某个客户的全部的消息
	@SuppressWarnings("unchecked")
	public List<AppMessage> getClientMessageByPage(Integer clientId,
			int pageNow, int rowSize) {
		String hql = "from AppMessage where (appClientOne=" + clientId
				+ " or appClientTwo = " + clientId + ") order by appTime desc";
		return (List<AppMessage>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 通过某个客户进行查询消息的行数
	public int getClientMessageByPageCount(Integer clientId, int rowSize) {
		String hql = "select count(*) from AppMessage where (appClientOne="
				+ clientId + " or appClientTwo = " + clientId + ")";
		return super.queryPageCount(hql, null, rowSize);
	}

	// 通过和状态进行查询
	@SuppressWarnings("unchecked")
	public List<AppMessage> getUpdateMessage(Integer clientId, String status) {
		String hql = "from AppMessage where (appClientOne=" + clientId
				+ " or appClientTwo = " + clientId + ") order by appTime desc";
		return (List<AppMessage>) super.executeQuery(hql, null);
	}

}
