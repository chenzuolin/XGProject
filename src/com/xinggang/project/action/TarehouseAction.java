package com.xinggang.project.action;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.xinggang.project.entity.Bursary;
import com.xinggang.project.entity.Tarehouse;
import com.xinggang.project.form.TarehouseForm;
import com.xinggang.project.service.BursaryService;
import com.xinggang.project.service.LoginLogService;
import com.xinggang.project.service.TarehouseService;

/**
 * 库位类action
 */
public class TarehouseAction extends DispatchAction {

	// 库位service
	private TarehouseService tarehouseService;
	// 库房service
	private BursaryService bursaryService;

	public void setBursaryService(BursaryService bursaryService) {
		this.bursaryService = bursaryService;
	}

	// 登录日志表service
	private LoginLogService loginLogService;

	public void setLoginLogService(LoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}

	public void setTarehouseService(TarehouseService tarehouseService) {
		this.tarehouseService = tarehouseService;
	}

	// 转到库位页面
	public void goSaveTarehouse(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		List<Bursary> listBursary = bursaryService.getAll();
		request.setAttribute("listBursary", listBursary);
		// 用JSONObject保存班组
		JSONArray array = new JSONArray();
		for (Bursary b : listBursary) {
			JSONObject obj = new JSONObject();
			obj.put("id", b.getBursaryId());
			obj.put("bursaryName", b.getBursaryName());
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
	}

	// 查询库位
	public void selectAjaxKuwei(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/* 设置响应头允许ajax跨域访问 */
		response.setHeader("Access-Control-Allow-Origin", "*");
		/* 星号表示所有的异域请求都可以接受， */
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");

		PrintWriter out = response.getWriter();
		List<Tarehouse> listTarehouse = tarehouseService.getAll();
		// 用JSONObject保存班组
		JSONArray array = new JSONArray();
		for (Tarehouse t : listTarehouse) {
			JSONObject obj = new JSONObject();
			obj.put("id", t.getTarehouseId());
			obj.put("name", t.getTarehouseName());
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
	}

	// 添加库位
	public void saveTarehouse(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		TarehouseForm tf = (TarehouseForm) form;
		Tarehouse tarehouse = new Tarehouse();

		tarehouse.setTarehouseName(tf.getTarehouseName());// 设置库位名称
		tarehouse.setTarehouseMaxWeight(tf.getTarehouseMaxWeight());// 设置库位的最大容量，吨
		tarehouse.setTarehouseMaxNumber(tf.getTarehouseMaxNumber());// 设置库位的最大容量，件
		tarehouse.setBursary(this.bursaryService.getBursaryId(tf.getBursary()));// 设置库房
		tarehouse.setTarehouseRemark(tf.getTarehouseRemark());// 设置备注
		boolean ok = this.tarehouseService.save(tarehouse);
		if (ok) {
			this.loginLogService.updateLogin(
					request.getSession().getAttribute("loginlogId").toString(),
					"添加库房");// 记录到日志表中
			out.print("<script>alert('添加成功！');window.location.href='tarehouse.do?flag=getTarehouseAll';</script>");
		} else {
			out.print("<script>alert('添加失败！');window.location.href='tarehouse.do?flag=getTarehouseAll';</script>");
		}

	}

	// 修改库位
	public void updateTarehouse(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		TarehouseForm tf = (TarehouseForm) form;
		Tarehouse tarehouse = this.tarehouseService.getTarehouseId(tf
				.getTarehouseId());// 通过ID查询
		tarehouse.setTarehouseName(tf.getTarehouseName());// 修改库位名称
		tarehouse.setTarehouseMaxNumber(tf.getTarehouseMaxNumber());// 修改库位的最大容量，件
		tarehouse.setTarehouseMaxWeight(tf.getTarehouseMaxWeight());// 修改库位的最大容量，吨
		tarehouse.setBursary(this.bursaryService.getBursaryId(tf.getBursary()));// 修改库房
		tarehouse.setTarehouseRemark(tarehouse.getTarehouseRemark()
				+ tf.getTarehouseRemark());// 添加备注
		boolean ok = this.tarehouseService.update(tarehouse);
		if (ok) {
			this.loginLogService.updateLogin(
					request.getSession().getAttribute("loginlogId").toString(),
					"修改库位");// 记录在日志表中
			out.print("<script>alert('修改成功！');window.location.href='tarehouse.do?flag=getTarehouseAll';</script>");
		} else {
			out.print("<script>alert('修改失败！');window.location.href='tarehouse.do?flag=getTarehouseAll';</script>");
		}

	}

	// 查看库位
	public ActionForward getTarehouseAll(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String kuwei = "";
		if (request.getParameter("kuwei") != null) {
			kuwei = URLDecoder.decode(request.getParameter("kuwei"), "UTF-8");
		}
		List<Tarehouse> tlist = this.tarehouseService.getKuNameMoHu(kuwei);// 获得全部
		request.setAttribute("tlist", tlist);// 保存到request中
		request.setAttribute("kuwei", kuwei);

		if (request.getParameter("ff") != null) {
			if (request.getParameter("ff").equals("checks")) {
				return mapping.findForward("goChecks");
			} else if (request.getParameter("ff") != null
					&& request.getParameter("ff").equals("shift")) {
				return mapping.findForward("goShift");
			} else if (request.getParameter("ff") != null
					&& request.getParameter("ff").equals("pici")) {
				return mapping.findForward("goPiCi");
			} else if (request.getParameter("ff") != null
					&& request.getParameter("ff").equals("duandao")) {
				return mapping.findForward("goDuandao");
			}
		}
		PrintWriter out = response.getWriter();
		JSONArray array = new JSONArray();
		if (tlist.size() <= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		for (int i = 0; i < tlist.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("result", "notnull");
			obj.put("id", tlist.get(i).getTarehouseId());
			obj.put("kuwei", tlist.get(i).getTarehouseName());
			obj.put("miaoshu", tlist.get(i).getBursary() == null ? "无" : tlist
					.get(i).getBursary().getBursaryName());
			obj.put("remark", tlist.get(i).getTarehouseRemark());
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
		return null;
	}

}
