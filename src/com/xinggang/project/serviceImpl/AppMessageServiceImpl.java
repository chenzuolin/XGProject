package com.xinggang.project.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.xinggang.project.dao.AppMessageDao;
import com.xinggang.project.entity.AppMessage;
import com.xinggang.project.service.AppMessageService;
import com.xinggang.project.tools.PresentTime;

public class AppMessageServiceImpl implements AppMessageService {

	// app消息dao
	private AppMessageDao appMessageDao;

	public void setAppMessageDao(AppMessageDao appMessageDao) {
		this.appMessageDao = appMessageDao;
	}

	public boolean save(AppMessage message) {

		return this.appMessageDao.save(message);
	}

	public boolean update(AppMessage message) {

		return this.appMessageDao.update(message);
	}

	public int getWeiCount(Integer clientId, String status) {

		return this.appMessageDao.getWeiCount(clientId, status);
	}

	public List<AppMessage> getClientMessageByPage(Integer clientId,
			int pageNow, int rowSize) {

		return this.appMessageDao.getClientMessageByPage(clientId, pageNow,
				rowSize);
	}

	public int getClientMessageByPageCount(Integer clientId, int rowSize) {

		return this.appMessageDao
				.getClientMessageByPageCount(clientId, rowSize);
	}

	// 通过和状态进行查询
	public List<AppMessage> getUpdateMessage(Integer clientId, String status) {
		return this.appMessageDao.getUpdateMessage(clientId, status);
	}

	// 当准备入库、入库完成、准备出库、出库完成、过户时正在收费、结算的时候向表中增加数据
	public void saveMessage(Integer clientOne, Integer clientTwo,
			String zongdanhao, String zidanhao, String goodsName,
			String content, String type) {
		AppMessage am = new AppMessage();
		PresentTime pt = new PresentTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSS");
		am.setAppId(df.format(new Date()));// 编号
		am.setAppClientOne(clientOne);// 客户一
		am.setAppClientTwo(clientTwo);// 客户二
		am.setAppIndentId(zongdanhao);// 总单号
		am.setAppIndentZiId(zidanhao);// 子单号
		am.setAppIndentGoodsName(goodsName);// 货物名称
		am.setAppTime(pt.getDatesNianYR());// 时间
		am.setAppContent(content);// 内容
		am.setAppStatus("1");// 状态为未读
		am.setAppType(type);// 类型
		this.appMessageDao.save(am);
	}

}
