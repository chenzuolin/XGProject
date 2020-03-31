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

import com.xinggang.project.entity.GoodsProperty;
import com.xinggang.project.form.GoodsPropertyForm;
import com.xinggang.project.service.GoodsPropertyService;
import com.xinggang.project.service.LoginLogService;

public class GoodsPropertyAction extends DispatchAction {
	//货物属性service注入到货物action中
	private GoodsPropertyService goodsPropertyService;
	//登录日志表service
	private LoginLogService loginLogService;
	public void setLoginLogService(LoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}
	
	public void setGoodsPropertyService(
			GoodsPropertyService goodsPropertyService) {
		this.goodsPropertyService = goodsPropertyService;
	}
	
	//转到添加货物产地页面
	public ActionForward goAddGoodsProperty(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return null;
	}
	
	//添加货物属性
	public void addGoodsProperty(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		boolean ok=false;
		PrintWriter out=response.getWriter();
		GoodsPropertyForm goodsPropertyForm=(GoodsPropertyForm) form;
		
	    List<GoodsProperty> list=goodsPropertyService.getShuxinName(goodsPropertyForm.getGoodsPropertyName());
		//如果集合大于0这说明有该属性，不能添加
		if(list.size()>0){
			ok=false;
		}else{
			ok= goodsPropertyService.addGoodsProperty(goodsPropertyForm);
		}				
		if(ok){
			List<GoodsProperty> listGoodsProperty= goodsPropertyService.getAll();
			request.setAttribute("listGoodsProperty", listGoodsProperty);
			//获得登录日志编号
			String loginId=(String) request.getSession().getAttribute("loginId");
			String loginName=(String) request.getSession().getAttribute("loginName");
			//到登录日志中修改信息
			loginLogService.updateLogin(loginId, loginName+"添加货物属性");
			out.print("<script>alert('添加成功！');window.location.href='goodsProperty.do?flag=selectGoodsProperty';</script>");
		}else{		
			out.print("<script>alert('添加失败！');window.location.href='goodsProperty.do?flag=selectGoodsProperty';</script>");
		}

	}
	
	public ActionForward goUpdateGoodsProperty(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int id=Integer.parseInt(request.getParameter("id"));
		GoodsProperty goodsProperty= goodsPropertyService.getGoodsPropertyId(id);
		request.setAttribute("goodsProperty", goodsProperty);
		return null;
	}
	
	//修改货物属性
	public void updateGoodsProperty(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out=response.getWriter();
		GoodsPropertyForm goodsPropertyForm=(GoodsPropertyForm) form;
		boolean ok= goodsPropertyService.updateGoodsProperty(goodsPropertyForm);
		if(ok){
			List<GoodsProperty> listGoodsProperty= goodsPropertyService.getAll();
			request.setAttribute("listGoodsProperty", listGoodsProperty);
			//获得登录日志编号
			String loginId=(String) request.getSession().getAttribute("loginId");
			String loginName=(String) request.getSession().getAttribute("loginName");
			//到登录日志中修改信息
			loginLogService.updateLogin(loginId, loginName+"修改货物属性");
			out.print("<script>alert('修改成功！');window.location.href='goodsProperty.do?flag=selectGoodsProperty';</script>");
		}else{		
			out.print("<script>alert('修改失败！');window.location.href='goodsProperty.do?flag=selectGoodsProperty';</script>");
		}
	}
	
	public ActionForward selectGoodsProperty(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		List<GoodsProperty> listGoodsProperty= goodsPropertyService.getAll();
		request.setAttribute("listGoodsProperty", listGoodsProperty);
		PrintWriter out=response.getWriter();
		JSONArray array=new JSONArray();
		if(listGoodsProperty.size()<=0){
			JSONObject obj=new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		for(int i=0; i<listGoodsProperty.size();i++){
			JSONObject obj=new JSONObject();
			obj.put("result", "notnull");
			obj.put("id", listGoodsProperty.get(i).getGoodsPropertyId());
			obj.put("shuxing", listGoodsProperty.get(i).getGoodsPropertyName());
			obj.put("miaoshu", listGoodsProperty.get(i).getGoodsPropertyDefinedTwo());
			obj.put("remark", listGoodsProperty.get(i).getGoodsPropertyRemark());
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
		return null;
	}
	
	//停用货物属性
	public void stopGoodsProperty(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		int id=Integer.parseInt(request.getParameter("id"));
		GoodsProperty g= goodsPropertyService.getGoodsPropertyId(id);
		g.setGoodsPropertyDefinedOne("0");
		boolean ok=goodsPropertyService.update(g);
		if(ok){
			List<GoodsProperty> list=goodsPropertyService.getAll();
			request.setAttribute("listGoodsProperty", list);
			//获得登录日志编号
			String loginId=(String) request.getSession().getAttribute("loginId");
			String loginName=(String) request.getSession().getAttribute("loginName");
			loginLogService.updateLogin(loginId, loginName+"停用货物属性");
			out.print("<script>alert('停用成功！');window.location.href='goodsProperty.do?flag=selectGoodsProperty';</script>");
		}else{
			out.print("<script>alert('停用失败！');window.location.href='goodsProperty.do?flag=selectGoodsProperty';</script>");
		}
		
	}
	
	//ajax返回json属性
	public void getGoodsPropertyApp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out=response.getWriter();
		//查询出货物品类
		List<GoodsProperty> goodsProperty=goodsPropertyService.getAll();
		//查询出货物产地
		
		JSONArray array=new JSONArray();
		//遍历集合中元素，然后转换为JSON格式
	    for(GoodsProperty c:goodsProperty){
	    	JSONObject obj = new JSONObject();
	    	obj.put("id", c.getGoodsPropertyId());
	    	obj.put("shuxing", c.getGoodsPropertyName());
	    	array.add(obj);
	    }
	    //将数据写入到前台
	    out.print(array.toString());
	    out.flush();
	    out.close();	
	}
	
	
}
