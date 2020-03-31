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

import com.xinggang.project.entity.GoodsUnit;
import com.xinggang.project.form.GoodsUnitForm;
import com.xinggang.project.service.GoodsUnitService;
import com.xinggang.project.service.LoginLogService;

public class GoodsUnitAction extends DispatchAction {
	private GoodsUnitService goodsUnitService;
	// 登录日志表service
	private LoginLogService loginLogService;

	public void setLoginLogService(LoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}

	public void setGoodsUnitService(GoodsUnitService goodsUnitService) {
		this.goodsUnitService = goodsUnitService;
	}

	// 转到添加计量单位页面
	public ActionForward goAddGoodsUnit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return null;
	}

	// 转到添加计量单位页面
	public void addGoodsUnit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		boolean ok = false;
		PrintWriter out = response.getWriter();
		GoodsUnitForm goodsUnitForm = (GoodsUnitForm) form;

		List<GoodsUnit> list = goodsUnitService.getJiLiangName(goodsUnitForm
				.getGoodsUnitName());
		if (list.size() > 0) {
			ok = false;
		} else {
			ok = goodsUnitService.addGoodsUnit(goodsUnitForm);
		}
		if (ok) {
			// 获得登录日志编号
			String loginId = (String) request.getSession().getAttribute(
					"loginId");
			String loginName = (String) request.getSession().getAttribute(
					"loginName");
			// 到登录日志中修改信息
			loginLogService.updateLogin(loginId, loginName + "添加计量单位");
			out.print("<script>alert('添加成功！');window.location.href='goodsUnit.do?flag=selectGoodsUnit';</script>");
		} else {
			out.print("<script>alert('添加失败！');window.location.href='goodsUnit.do?flag=selectGoodsUnit';</script>");
		}

	}

	// 转到修改计量单位页面
	public ActionForward goUpdateGoodsUnit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));
		GoodsUnit goodsUnit = goodsUnitService.getGoodsUnitId(id);
		request.setAttribute("goodsUnit", goodsUnit);
		return null;
	}

	// 修改计量单位页面
	public void updateGoodsUnit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		GoodsUnitForm goodsUnitForm = (GoodsUnitForm) form;
		boolean ok = goodsUnitService.updateGoodsUnit(goodsUnitForm);
		if (ok) {
			// 获得登录日志编号
			String loginId = (String) request.getSession().getAttribute(
					"loginId");
			String loginName = (String) request.getSession().getAttribute(
					"loginName");
			// 到登录日志中修改信息
			loginLogService.updateLogin(loginId, loginName + "修改计量单位");
			out.print("<script>alert('修改成功！');window.location.href='goodsUnit.do?flag=selectGoodsUnit';</script>");
		} else {
			out.print("<script>alert('修改失败！');window.location.href='goodsUnit.do?flag=selectGoodsUnit';</script>");
		}

	}

	// 查询单位页面
	public ActionForward selectGoodsUnit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<GoodsUnit> listGoodsUnit = goodsUnitService.getAll();
		request.setAttribute("listGoodsUnit", listGoodsUnit);

		PrintWriter out = response.getWriter();
		JSONArray array = new JSONArray();
		if (listGoodsUnit.size() <= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		for (int i = 0; i < listGoodsUnit.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("result", "notnull");
			obj.put("id", listGoodsUnit.get(i).getGoodsUnitId());
			obj.put("danwei", listGoodsUnit.get(i).getGoodsUnitName());
			obj.put("miaoshu", listGoodsUnit.get(i).getGoodsUnitDefinedTwo());
			obj.put("remark", listGoodsUnit.get(i).getGoodsUnitRemark());
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
		return null;
	}

	// 停用计量单位
	public void stopGoodsUnit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));
		GoodsUnit g = goodsUnitService.getGoodsUnitId(id);
		g.setGoodsUnitDefinedOne("0");
		boolean ok = goodsUnitService.delete(g);
		if (ok) {
			// 获得登录日志编号
			String loginId = (String) request.getSession().getAttribute(
					"loginId");
			String loginName = (String) request.getSession().getAttribute(
					"loginName");
			// 到登录日志中修改信息
			loginLogService.updateLogin(loginId, loginName + "停用货物计量单位");
			out.print("<script>alert('停用成功！');window.location.href='goodsUnit.do?flag=selectGoodsUnit';</script>");
		} else {
			out.print("<script>alert('停用失败！');window.location.href='goodsUnit.do?flag=selectGoodsUnit';</script>");
		}

	}

}
