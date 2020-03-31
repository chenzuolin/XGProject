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

import com.xinggang.project.entity.GoodsCategory;
import com.xinggang.project.form.GoodsCategoryForm;
import com.xinggang.project.service.GoodsCategoryService;
import com.xinggang.project.service.LoginLogService;
/**
 * GoodsCategory entity. @author MyEclipse Persistence Tools
 * 货物品类action
 */
public class GoodsCategoryAction extends DispatchAction {
	
	//定义货物品类GoodsCategoryService
	private GoodsCategoryService goodsCategoryService;
	//登录日志表service
	private LoginLogService loginLogService;
	
	public void setLoginLogService(LoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}
	public void setGoodsCategoryService(
			GoodsCategoryService goodsCategoryService) {
		this.goodsCategoryService = goodsCategoryService;
	}
	//添加货物品类
	public void addGoodsCategory(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		boolean ok=false;
		PrintWriter out=response.getWriter();
		GoodsCategoryForm goodsCategoryForm=(GoodsCategoryForm) form;
		
		//查询是否有相同的品类
		List<GoodsCategory> list= goodsCategoryService.getName(goodsCategoryForm.getGoodsCategoryName());
		System.out.println(goodsCategoryForm.getGoodsCategoryName());
		if(list.size()>0){
			ok=false;
		}else{
			GoodsCategory goodsCategory=new GoodsCategory();
			goodsCategory.setGoodsCategoryName(goodsCategoryForm.getGoodsCategoryName());
			goodsCategory.setGoodsCategoryRemark(goodsCategoryForm.getGoodsCategoryRemark());
			goodsCategory.setGoodsCategoryPovalidity(goodsCategoryForm.getGoodsCategoryPovalidity());
			goodsCategory.setGoodsCategoryDefinedOne("1");//是否停用，默认为1不停用
			goodsCategory.setGoodsCategoryDefinedTwo(goodsCategoryForm.getGoodsCategoryDefinedTwo());//描述
			
			ok=goodsCategoryService.save(goodsCategory);//保存货物规格，返回boolean值
		}		
		if(ok){
			//获得登录日志编号
			String loginId=(String) request.getSession().getAttribute("loginId");
			String loginName=(String) request.getSession().getAttribute("loginName");
			loginLogService.updateLogin(loginId, loginName+"添加货物品类信息");
			
			out.print("<script>alert('添加成功！');window.location.href='goodsCategory.do?flag=selectGoodsCategory';</script>");
		}else{
			out.print("<script>alert('添加失败！');window.location.href='goodsCategory.do?flag=selectGoodsCategory';</script>");
		}

	}
	
	//修改品类
	public void updateGoodsCategory(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out=response.getWriter();
		GoodsCategoryForm goodsCategoryForm=(GoodsCategoryForm) form;
		GoodsCategory gc=goodsCategoryService.getGoodsCategoryId(goodsCategoryForm.getGoodsCategoryId());
		gc.setGoodsCategoryName(goodsCategoryForm.getGoodsCategoryName());
		gc.setGoodsCategoryDefinedTwo(goodsCategoryForm.getGoodsCategoryDefinedTwo());
		gc.setGoodsCategoryPovalidity(goodsCategoryForm.getGoodsCategoryPovalidity());
		gc.setGoodsCategoryRemark(goodsCategoryForm.getGoodsCategoryRemark());
		boolean ok= goodsCategoryService.update(gc);
		if(ok){
			List<GoodsCategory> list=goodsCategoryService.getAll();
			request.setAttribute("listGoodsCategory", list);
			//获得登录日志编号
			String loginId=(String) request.getSession().getAttribute("loginId");
			String loginName=(String) request.getSession().getAttribute("loginName");
			loginLogService.updateLogin(loginId, loginName+"修改货物品类信息");
			out.print("<script>alert('修改成功！');window.location.href='goodsCategory.do?flag=selectGoodsCategory';</script>");
		}else{
			out.print("<script>alert('修改失败！');window.location.href='goodsCategory.do?flag=selectGoodsCategory';</script>");
		}

	}
	
	//查询货物品类
	public ActionForward selectGoodsCategory(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		List<GoodsCategory> list = goodsCategoryService.getAll();
		request.setAttribute("listGoodsCategory", list);
		PrintWriter out=response.getWriter();
		JSONArray array=new JSONArray();
		if(list.size()<=0){
			JSONObject obj=new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		for(int i=0; i<list.size();i++){
			JSONObject obj=new JSONObject();
			obj.put("result", "notnull");
			obj.put("id", list.get(i).getGoodsCategoryId());
			obj.put("pinlei", list.get(i).getGoodsCategoryName());
			obj.put("miaoshu", list.get(i).getGoodsCategoryDefinedTwo());
			obj.put("remark", list.get(i).getGoodsCategoryRemark());
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
		return null;
	}
	
	//停用货物品类
	public void stopGoodsCategory(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out=response.getWriter();
		int id=Integer.parseInt(request.getParameter("id"));
		GoodsCategory g= goodsCategoryService.getGoodsCategoryId(id);
		g.setGoodsCategoryDefinedOne("0");
		boolean ok=goodsCategoryService.update(g);
		if(ok){
			List<GoodsCategory> list=goodsCategoryService.getAll();
			request.setAttribute("listGoodsCategory", list);
			//获得登录日志编号
			String loginId=(String) request.getSession().getAttribute("loginId");
			String loginName=(String) request.getSession().getAttribute("loginName");
			loginLogService.updateLogin(loginId, loginName+"停用货物品类信息");
			out.print("<script>alert('停用成功！');window.location.href='goodsCategory.do?flag=selectGoodsCategory';</script>");
		}else{
			out.print("<script>alert('停用失败！');window.location.href='goodsCategory.do?flag=selectGoodsCategory';</script>");
		}
		
	}
	
	//ajax返回json品类
	public void getGoodsCategoryApp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out=response.getWriter();
		//查询出货物品类
		List<GoodsCategory> goodsCategory=goodsCategoryService.getAll();
		//查询出货物产地
		
		JSONArray array=new JSONArray();
		//遍历集合中元素，然后转换为JSON格式
	    for(GoodsCategory c:goodsCategory){
	    	JSONObject obj = new JSONObject();
	    	obj.put("id", c.getGoodsCategoryId());
	    	obj.put("pinlei", c.getGoodsCategoryName());
	    	array.add(obj);
	    }
	    //将数据写入到前台
	    out.print(array.toString());
	    out.flush();
	    out.close();
	}
	
	
	

}
