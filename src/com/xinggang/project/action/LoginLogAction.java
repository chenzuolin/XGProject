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

import com.xinggang.project.entity.LoginLog;
import com.xinggang.project.form.LoginLogForm;
import com.xinggang.project.service.LoginLogService;
import com.xinggang.project.tools.PageRow;
import com.xinggang.project.tools.PresentTime;

/**
 * 登录日志action
 * 
 * @author Administrator
 * 
 */
public class LoginLogAction extends DispatchAction {
	// 登录日志service
	private LoginLogService loginLogService;
	// 获取当前日期
	private PresentTime pt = new PresentTime();

	@SuppressWarnings("unused")
	private PageRow pr = new PageRow();

	public void setLoginLogService(LoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return super.execute(mapping, form, request, response);
	}

	// 删除登录日志
	public ActionForward deleteLog(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LoginLogForm llf = (LoginLogForm) form;
		LoginLog loginLog = this.loginLogService.getLoginLogId(llf.getLlogId());
		boolean ok = this.loginLogService.delete(loginLog);
		if (ok) {
			request.getSession().setAttribute("deleteLogMessage", "删除成功！");
		} else {
			request.getSession().setAttribute("deleteLogMessage", "删除失败！");
		}
		// 将这一操作记录在日志表中
		this.loginLogService.updateLogin(request.getAttribute("loginlogId")
				.toString(), "删除登录日志");

		// 返回到对应的界面
		return super.execute(mapping, form, request, response);
	}

	// 查看全部的登录日志
	public ActionForward getAllLLog(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		/* 设置响应头允许ajax跨域访问 */
		response.setHeader("Access-Control-Allow-Origin", "*");
		/* 星号表示所有的异域请求都可以接受， */
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");

		LoginLogForm llForm = (LoginLogForm) form;
		// 如果查询的条件为空的时候
		if (llForm.getBegin() == null
				|| llForm.getBegin().toString().equals("")) {
			llForm.setBegin("2015-01-01 12:12:12");
		}
		if (llForm.getFinish() == null
				|| llForm.getFinish().toString().equals("")) {
			llForm.setFinish(pt.getTimes());
		}
		if (llForm.getLlogName() == null
				|| llForm.getLlogName().toString().equals("")) {
			llForm.setLlogName("");
		}
		if (llForm.getLlogRemark() == null
				|| llForm.getLlogRemark().toString().equals("")) {
			llForm.setLlogRemark("");
		}
		// 获得当前页
		int pageNow = 1;
		int pageRow = 30;// 显示的行数
		if (request.getParameter("pageRow") != null) {
			pageRow = Integer.parseInt(request.getParameter("pageRow")
					.toString());
		}
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}
		int pageCount = this.loginLogService.getLoginLogByCount(
				llForm.getBegin(), llForm.getFinish() + " 23:59:59",
				llForm.getLlogName(), llForm.getLlogRemark(), pageRow);// 获得总页数
		// 如果当前页大于总页数的时候，当前页就等于总页数
		if (pageNow >= pageCount) {
			pageNow = pageCount;
		}
		// 如果当前页小于1的时候，当前页就等于1
		if (pageNow <= 1) {
			pageNow = 1;
		}
		// 通过条件去查询
		List<LoginLog> listLLog = this.loginLogService.getLoginLogByPage(
				llForm.getBegin(), llForm.getFinish() + " 23:59:59",
				llForm.getLlogName(), llForm.getLlogRemark(), pageNow, pageRow);
		// 将查询到的值保存在request中
		request.setAttribute("listLLog", listLLog);
		request.setAttribute("pageNow", pageNow);
		request.setAttribute("pageCount", pageCount);

		if (request.getParameter("ff") != null
				&& request.getParameter("ff").toString().equals("ajax")) {

			PrintWriter out = response.getWriter();
			JSONArray array = new JSONArray();
			for (int i = 0; i < listLLog.size(); i++) {
				JSONObject obj = new JSONObject();
				obj.put("id", listLLog.get(i).getLlogId());
				obj.put("time", listLLog.get(i).getLlogTime());
				obj.put("loginName", listLLog.get(i).getLlogName());
				obj.put("ip", listLLog.get(i).getLlogIp());
				obj.put("type", listLLog.get(i).getLlogDefinedOne());
				obj.put("remark", listLLog.get(i).getLlogRemark());
				obj.put("pageNow", pageNow);
				obj.put("pageCount", pageCount);
				array.add(obj);
			}
			out.print(array.toString());

			out.flush();
			out.close();
			return null;
		}
		// 返回到相应的界面
		return mapping.findForward("QueryLog");
	}
}
