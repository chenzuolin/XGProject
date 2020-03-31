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

import com.xinggang.project.entity.GoodsYieldly;
import com.xinggang.project.form.GoodsYieldlyForm;
import com.xinggang.project.service.GoodsYieldlyService;
import com.xinggang.project.service.LoginLogService;

/**
 * 入库总订单类action
 * 
 * @author Administrator
 * 
 */
public class GoodsYieldlyAction extends DispatchAction {

	private GoodsYieldlyService goodsYieldlyService;
	// 登录日志表service
	private LoginLogService loginLogService;

	public void setLoginLogService(LoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}

	public void setGoodsYieldlyService(GoodsYieldlyService goodsYieldlyService) {
		this.goodsYieldlyService = goodsYieldlyService;
	}

	// 转到添加货物产地页面
	public ActionForward goAddgoodsYieldly(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		List<GoodsYieldly> goodsYieldly = goodsYieldlyService.getAll();
		request.setAttribute("goodsYieldly", goodsYieldly);
		PrintWriter out = response.getWriter();
		JSONArray array = new JSONArray();
		if (goodsYieldly.size() <= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		for (int i = 0; i < goodsYieldly.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("result", "notnull");
			obj.put("id", goodsYieldly.get(i).getGoodsYieldlyId());
			obj.put("chandi", goodsYieldly.get(i).getGoodsYieldlyName());
			obj.put("miaoshu", goodsYieldly.get(i).getGoodsYieldlyDefinedTwo());
			obj.put("remark", goodsYieldly.get(i).getGoodsYieldlyRemark());
			array.add(obj);

		}
		out.print(array.toString());
		out.flush();
		out.close();
		return null;
	}

	// 添加货物产地
	public void addgoodsYieldly(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		boolean ok = false;
		PrintWriter out = response.getWriter();
		GoodsYieldlyForm goodsYieldlyForm = (GoodsYieldlyForm) form;

		List<GoodsYieldly> list = goodsYieldlyService
				.getChandiName(goodsYieldlyForm.getGoodsYieldlyName());

		if (list.size() > 0) {
			ok = false;
		} else {
			GoodsYieldly goodsYieldly = new GoodsYieldly();
			goodsYieldly.setGoodsYieldlyName(goodsYieldlyForm
					.getGoodsYieldlyName());
			goodsYieldly.setGoodsYieldlyDefinedOne("1");// 是否停用，默认1
			goodsYieldly.setGoodsYieldlyDefinedTwo(goodsYieldlyForm
					.getGoodsYieldlyDefinedTwo());// 描述
			goodsYieldly.setGoodsYieldlyRemark(goodsYieldlyForm
					.getGoodsYieldlyRemark());

			ok = goodsYieldlyService.save(goodsYieldly);
		}
		if (ok) {
			// 获得登录日志编号
			String loginId = (String) request.getSession().getAttribute(
					"loginId");
			String loginName = (String) request.getSession().getAttribute(
					"loginName");
			// 到登录日志中修改信息
			loginLogService.updateLogin(loginId, loginName + "添加货物产地");
			out.print("<script>alert('添加成功！');window.location.href='goodsYieldly.do?flag=goSelectgoodsYieldly';</script>");
		} else {
			out.print("<script>alert('添加失败！');window.location.href='goodsYieldly.do?flag=goSelectgoodsYieldly';</script>");
		}

	}

	// 停用货物产地
	public void stopgoodsYieldly(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));

		GoodsYieldly g = goodsYieldlyService.getGoodsYieldlyId(id);
		g.setGoodsYieldlyDefinedOne("0");
		boolean ok = goodsYieldlyService.update(g);
		if (ok) {
			// 获得登录日志编号
			String loginId = (String) request.getSession().getAttribute(
					"loginId");
			String loginName = (String) request.getSession().getAttribute(
					"loginName");
			// 到登录日志中修改信息
			loginLogService.updateLogin(loginId, loginName + "停用货物产地");
			out.print("<script>alert('停用成功！');window.location.href='goodsYieldly.do?flag=goSelectgoodsYieldly';</script>");
		} else {
			out.print("<script>alert('停用失败！');window.location.href='goodsYieldly.do?flag=goSelectgoodsYieldly';</script>");
		}

	}

	// 查询货物产地
	public ActionForward goSelectgoodsYieldly(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		List<GoodsYieldly> goodsYieldly = goodsYieldlyService.getAll();
		request.setAttribute("goodsYieldly", goodsYieldly);
		PrintWriter out=response.getWriter();
		JSONArray array=new JSONArray();
		if(goodsYieldly.size()<=0){
			JSONObject obj=new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		for(int i=0; i<goodsYieldly.size();i++){
			JSONObject obj=new JSONObject();
			obj.put("result", "notnull");
			obj.put("id", goodsYieldly.get(i).getGoodsYieldlyId());
			obj.put("chandi", goodsYieldly.get(i).getGoodsYieldlyName());
			obj.put("miaoshu", goodsYieldly.get(i).getGoodsYieldlyDefinedTwo());
			obj.put("beizhu", goodsYieldly.get(i).getGoodsYieldlyRemark());
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
		return null;
	}

	// 转到修改货物产地
	public ActionForward goupdategoodsYieldly(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		List<GoodsYieldly> goodsYieldly = goodsYieldlyService.getAll();
		request.setAttribute("goodsYieldly", goodsYieldly);
		return mapping.findForward("goupdategoodsYieldly");
	}

	// 修改货物产地
	public void updategoodsYieldly(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PrintWriter out = response.getWriter();
		GoodsYieldlyForm goodsYieldlyForm = (GoodsYieldlyForm) form;
		boolean ok = goodsYieldlyService.updateGoodsYieldly(goodsYieldlyForm);
		if (ok) {
			// 获得登录日志编号
			String loginId = (String) request.getSession().getAttribute(
					"loginId");
			String loginName = (String) request.getSession().getAttribute(
					"loginName");
			// 到登录日志中修改信息
			loginLogService.updateLogin(loginId, loginName + "修改货物产地");
			out.print("<script>alert('修改成功！');window.location.href='goodsYieldly.do?flag=goSelectgoodsYieldly';</script>");
		} else {
			out.print("<script>alert('修改失败！');window.location.href='goodsYieldly.do?flag=goSelectgoodsYieldly';</script>");
		}

	}

	// ajax返回json产地
	public void getGoodsYieldlyApp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		// 查询出货物品类
		List<GoodsYieldly> goodsYieldly = goodsYieldlyService.getAll();
		JSONArray array = new JSONArray();
		for (GoodsYieldly c : goodsYieldly) {
			JSONObject obj = new JSONObject();
			obj.put("id", c.getGoodsYieldlyId());
			obj.put("name", c.getGoodsYieldlyName());
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
	}

}
