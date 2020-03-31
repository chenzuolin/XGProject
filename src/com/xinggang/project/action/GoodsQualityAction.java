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

import com.xinggang.project.entity.GoodsQuality;
import com.xinggang.project.form.GoodsQualityForm;
import com.xinggang.project.service.GoodsQualityService;
import com.xinggang.project.service.LoginLogService;

public class GoodsQualityAction extends DispatchAction {
	// 将货物service注入到货物Action中
	private GoodsQualityService goodsQualityService;
	// 登录日志表service
	private LoginLogService loginLogService;

	public void setLoginLogService(LoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}

	public void setGoodsQualityService(GoodsQualityService goodsQualityService) {
		this.goodsQualityService = goodsQualityService;
	}

	// 转到添加货物材质页面
	public ActionForward goAddGoodsQuality(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return null;
	}

	// 添加货物材质
	public void addGoodsQuality(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		boolean ok = false;
		PrintWriter out = response.getWriter();
		GoodsQualityForm goodsQualityForm = (GoodsQualityForm) form;

		List<GoodsQuality> list = goodsQualityService
				.getCaizhiName(goodsQualityForm.getGoodsQualityName());

		if (list.size() > 0) {
			ok = false;
		} else {
			ok = goodsQualityService.addGoodsQuality(goodsQualityForm);
		}
		if (ok) {
			// 获得登录日志编号
			String loginId = (String) request.getSession().getAttribute(
					"loginId");
			String loginName = (String) request.getSession().getAttribute(
					"loginName");
			// 到登录日志中修改信息
			loginLogService.updateLogin(loginId, loginName + "添加货物材质");
			out.print("<script>alert('添加成功！');window.location.href='goodsQuality.do?flag=selectGoodsQuality';</script>");
		} else {
			out.print("<script>alert('添加失败！');window.location.href='goodsQuality.do?flag=selectGoodsQuality';</script>");
		}

	}

	// 转到修改货物材质页面
	public ActionForward goUpdateGoodsQuality(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));
		GoodsQuality goodsQuality = goodsQualityService.getGoodsQualityId(id);
		request.setAttribute("goodsQuality", goodsQuality);
		return null;
	}

	// 修改货物材质
	public void updateGoodsQuality(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		GoodsQualityForm goodsQualityForm = (GoodsQualityForm) form;
		boolean ok = goodsQualityService.updateGoodsQuality(goodsQualityForm);
		if (ok) {
			// 获得登录日志编号
			String loginId = (String) request.getSession().getAttribute(
					"loginId");
			String loginName = (String) request.getSession().getAttribute(
					"loginName");
			// 到登录日志中修改信息
			loginLogService.updateLogin(loginId, loginName + "修改货物材质");
			out.print("<script>alert('修改成功！');window.location.href='goodsQuality.do?flag=selectGoodsQuality';</script>");
		} else {
			out.print("<script>alert('修改失败！');window.location.href='goodsQuality.do?flag=selectGoodsQuality';</script>");
		}

	}

	// 转到查询
	public ActionForward selectGoodsQuality(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<GoodsQuality> listGoodsQuality = goodsQualityService.getAll();
		request.setAttribute("listGoodsQuality", listGoodsQuality);
		PrintWriter out=response.getWriter();
		JSONArray array=new JSONArray();
		if(listGoodsQuality.size()<=0){
			JSONObject obj=new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		for(int i=0; i<listGoodsQuality.size();i++){
			JSONObject obj=new JSONObject();
			obj.put("result", "notnull");
			obj.put("id", listGoodsQuality.get(i).getGoodsQualityId());
			obj.put("caizhi", listGoodsQuality.get(i).getGoodsQualityName());
			obj.put("miaoshu", listGoodsQuality.get(i).getGoodsQualityDefinedTwo());
			obj.put("remark", listGoodsQuality.get(i).getGoodsQualityRemark());
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
		return null;
	}

	// 停用货物材质
	public void stopGoodsQuality(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));
		GoodsQuality g = goodsQualityService.getGoodsQualityId(id);
		g.setGoodsQualityDefinedOne("0");
		boolean ok = goodsQualityService.update(g);
		if (ok) {
			List<GoodsQuality> list = goodsQualityService.getAll();
			request.setAttribute("listGoodsQuality", list);
			// 获得登录日志编号
			String loginId = (String) request.getSession().getAttribute(
					"loginId");
			String loginName = (String) request.getSession().getAttribute(
					"loginName");
			loginLogService.updateLogin(loginId, loginName + "停用货物材质");
			out.print("<script>alert('停用成功！');window.location.href='goodsQuality.do?flag=selectGoodsQuality';</script>");
		} else {
			out.print("<script>alert('停用失败！');window.location.href='goodsQuality.do?flag=selectGoodsQuality';</script>");
		}

	}

	// ajax返回json材质
	public void getGoodsQualityApp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		// 货物材质
		List<GoodsQuality> goodsQuality = goodsQualityService.getAll();
		JSONArray array = new JSONArray();
		for (GoodsQuality c : goodsQuality) {
			JSONObject obj = new JSONObject();
			obj.put("id", c.getGoodsQualityId());
			obj.put("name", c.getGoodsQualityName());
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
	}

}
