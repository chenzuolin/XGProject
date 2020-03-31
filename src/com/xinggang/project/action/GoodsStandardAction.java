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
import com.xinggang.project.entity.GoodsStandard;
import com.xinggang.project.form.GoodsStandardForm;
import com.xinggang.project.service.GoodsStandardService;
import com.xinggang.project.service.LoginLogService;

public class GoodsStandardAction extends DispatchAction {
	
	private GoodsStandardService goodsStandardService;
	//登录日志表service
	private LoginLogService loginLogService;
	public void setLoginLogService(LoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}
	public void setGoodsStandardService(
			GoodsStandardService goodsStandardService) {
		this.goodsStandardService = goodsStandardService;
	}
	
	//转到添加货物规格页面
	public ActionForward goAddGoodsStandard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return null;
	}
	
	//添加规格
	public void addGoodsStandard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		boolean ok=false;
		
		PrintWriter out=response.getWriter();
		GoodsStandardForm goodsStandardForm=(GoodsStandardForm) form;
		List<GoodsStandard> list= goodsStandardService.getGuigeName(goodsStandardForm.getGoodsStandardName());
		
		if(list.size()>0){
			ok=false;
		}else{
			ok= goodsStandardService.addGoodsStandard(goodsStandardForm);
		}
				
		if(ok){
			//获得登录日志编号
			String loginId=(String) request.getSession().getAttribute("loginId");
			String loginName=(String) request.getSession().getAttribute("loginName");
			//到登录日志中修改信息
			loginLogService.updateLogin(loginId, loginName+"添加货物规格");
			out.print("<script>alert('添加成功！');window.location.href='goodsStandard.do?flag=selectGoodsStandard';</script>");
		}else{
			out.print("<script>alert('添加失败！');window.location.href='goodsStandard.do?flag=selectGoodsStandard';</script>");
		}
	}
	
	//转到修改货物规格页面
	public ActionForward goUpdatGoodsStandard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
	    int id=Integer.parseInt(request.getParameter("id"));
	    GoodsStandard goodsStandard=goodsStandardService.getGoodsStandardId(id);
	    request.setAttribute("goodsStandard", goodsStandard);
		return null;
	}
	
	//修改规格
	public void updateGoodsStandard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out=response.getWriter();
		GoodsStandardForm goodsStandardForm=(GoodsStandardForm) form;
		boolean ok= goodsStandardService.updateGoodsStandard(goodsStandardForm);
		if(ok){
			//获得登录日志编号
			String loginId=(String) request.getSession().getAttribute("loginId");
			String loginName=(String) request.getSession().getAttribute("loginName");
			//到登录日志中修改信息
			loginLogService.updateLogin(loginId, loginName+"修改货物规格");
			out.print("<script>alert('修改成功！');window.location.href='goodsStandard.do?flag=selectGoodsStandard';</script>");
		}else{
			out.print("<script>alert('修改失败！');window.location.href='goodsStandard.do?flag=selectGoodsStandard';</script>");
		}
	
	}
	
	//查询货物规格
	public ActionForward selectGoodsStandard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
	    List<GoodsStandard> listGoodsStandard=goodsStandardService.getAll();
	    request.setAttribute("listGoodsStandard", listGoodsStandard);
	    PrintWriter out=response.getWriter();
		JSONArray array=new JSONArray();
		if(listGoodsStandard.size()<=0){
			JSONObject obj=new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		for(int i=0; i<listGoodsStandard.size();i++){
			JSONObject obj=new JSONObject();
			obj.put("result", "notnull");
			obj.put("id", listGoodsStandard.get(i).getGoodsStandardId());
			obj.put("guige", listGoodsStandard.get(i).getGoodsStandardName());
			obj.put("miaoshu", listGoodsStandard.get(i).getGoodsStandardDefinedTwo());
			obj.put("remark", listGoodsStandard.get(i).getGoodsStandardRemark());
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
		return null;
	}
	
	//停用货物规格
	public void stopGoodsStandard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PrintWriter out=response.getWriter();
		int id=Integer.parseInt(request.getParameter("id"));
		GoodsStandard g= goodsStandardService.getGoodsStandardId(id);
		g.setGoodsStandardDefinedOne("0");
		boolean ok=goodsStandardService.update(g);
		if(ok){
			List<GoodsStandard> list=goodsStandardService.getAll();
			request.setAttribute("listGoodsStandard", list);
			//获得登录日志编号
			String loginId=(String) request.getSession().getAttribute("loginId");
			String loginName=(String) request.getSession().getAttribute("loginName");
			loginLogService.updateLogin(loginId, loginName+"停用货物规格");
			out.print("<script>alert('停用成功！');window.location.href='goodsStandard.do?flag=selectGoodsStandard';</script>");
		}else{
			out.print("<script>alert('停用失败！');window.location.href='goodsStandard.do?flag=selectGoodsStandard';</script>");
		}
		 
	}
	
	//ajax返回json规格
	public void getGoodsStandardApp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out=response.getWriter();
		//货物规格
		List<GoodsStandard> goodsStandard=goodsStandardService.getAll();
		JSONArray array=new JSONArray();
	    for(GoodsStandard c:goodsStandard){
	    	JSONObject obj = new JSONObject();
	    	obj.put("id", c.getGoodsStandardId());
	    	obj.put("name", c.getGoodsStandardName());
	    	array.add(obj);
	    }
	    out.print(array.toString());
	    out.flush();
	    out.close();	
	}
	
}
