package com.xinggang.project.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.xinggang.project.entity.AppMessage;
import com.xinggang.project.form.AppMessageForm;
import com.xinggang.project.service.AppMessageService;

/*
 * app中消息的action
 * 查询对应的入库订单，出库订单，过户订单，内容人员进行结算了没有交费的推送
 * 订单中分别的状态是，准备入库、入库完成、准备出库、出库完成、正在收费、
 * 
 * 查询的时候通过对应的客户的id进行查询
 * 
 * */

public class AppMessageAction extends DispatchAction {

	// app消息dao
	private AppMessageService appMessageService;

	public void setAppMessageService(AppMessageService appMessageService) {
		this.appMessageService = appMessageService;
	}

	// 消息的显示
	public ActionForward QueryMessage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AppMessageForm amf = (AppMessageForm) form;// 得到form
		JSONArray array = new JSONArray();// json
		PrintWriter out = response.getWriter();// 输出流
		if (amf.getClientId() == null) {// 如果客户为空则直接返回
			JSONObject obj = new JSONObject();
			obj.put("result", "clientnull");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}

		// 进行查询消息
		int pageNow = 1;
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}
		int pageCount = this.appMessageService.getClientMessageByPageCount(
				amf.getClientId(), 15);
		if (pageNow >= pageCount) {
			pageNow = pageCount;
		} else if (pageNow <= 1) {
			pageNow = 1;
		}
		// 进行查询
		List<AppMessage> amlist = this.appMessageService
				.getClientMessageByPage(amf.getClientId(), pageNow, 15);
		if (amlist.size() <= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "yesnull");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		for (int i = 0; i < amlist.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("zong", amlist.get(i).getAppIndentId());
			obj.put("ziid", amlist.get(i).getAppIndentZiId());
			obj.put("goodsName", amlist.get(i).getAppIndentGoodsName());
			obj.put("time", amlist.get(i).getAppTime());
			obj.put("content", amlist.get(i).getAppContent());
			obj.put("zhuangtai", amlist.get(i).getAppStatus());
			obj.put("type", amlist.get(i).getAppType());
			obj.put("result", "notnull");
			obj.put("pageNow", pageNow);
			obj.put("pageCount", pageCount);
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
		return null;
	}

	// 写一个当点击一个消息的时候进行改变
	public ActionForward updateMessage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AppMessageForm amf = (AppMessageForm) form;// 得到form
		List<AppMessage> aplist = this.appMessageService.getUpdateMessage(
				amf.getClientId(), "1");
		for (int i = 0; i < aplist.size(); i++) {
			AppMessage ap = aplist.get(i);
			ap.setAppStatus("0");
			this.appMessageService.update(ap);
		}
		return null;
	}

	// 获得消息的个数
	public ActionForward messageCount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AppMessageForm amf = (AppMessageForm) form;// 得到form
		PrintWriter out = response.getWriter();
		int messagecount = 0;
		messagecount = this.appMessageService.getWeiCount(amf.getClientId(), "1");
		out.print(messagecount);
		out.flush();
		out.close();
		return null;
	}
}
