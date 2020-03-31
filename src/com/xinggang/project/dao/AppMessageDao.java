package com.xinggang.project.dao;

import java.util.List;

import com.xinggang.project.entity.AppMessage;

public interface AppMessageDao {
	// 添加消息
	public boolean save(AppMessage message);

	// 修改消息
	public boolean update(AppMessage message);

	// 统计未读消息的数据也就是状态为1的
	public int getWeiCount(Integer clientId, String status);

	// 查询某个客户的全部的消息
	public List<AppMessage> getClientMessageByPage(Integer clientId,
			int pageNow, int rowSize);

	// 通过某个客户进行查询消息的行数
	public int getClientMessageByPageCount(Integer clientId, int rowSize);

	// 通过和状态进行查询
	public List<AppMessage> getUpdateMessage(Integer clientId, String status);

}
