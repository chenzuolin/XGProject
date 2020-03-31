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

import com.xinggang.project.entity.Section;
import com.xinggang.project.form.SectionForm;
import com.xinggang.project.service.LoginLogService;
import com.xinggang.project.service.SectionService;
/**
 * 部门类action
 * 
 * @author Administrator
 * 
 */
public class SectionAction extends DispatchAction {
	private SectionService sectionService;
	//登录日志表service
		private LoginLogService loginLogService;
		public void setLoginLogService(LoginLogService loginLogService) {
			this.loginLogService = loginLogService;
		}
	public void setSectionService(SectionService sectionService) {
		this.sectionService = sectionService;
	}
	public ActionForward goAddSection(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		return mapping.findForward("goAddSection");
	}
	
	//添加部门
	public void addSection(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		PrintWriter out=response.getWriter();
		SectionForm sectionForm=(SectionForm) form;
		boolean ok=sectionService.saveSection(sectionForm);
		if(ok){
			//获得登录日志编号
			String loginId=(String) request.getSession().getAttribute("loginId");
			String loginName=(String) request.getSession().getAttribute("loginName");
			//到登录日志中修改信息
			loginLogService.updateLogin(loginId, loginName+"添加部门");
			out.print("<script>alert('添加成功！');window.location.href='section.do?flag=selectSection';</script>");
		}else{
			out.print("<script>alert('添加失败！');window.location.href='section.do?flag=selectSection';</script>");
		}
		
	}
	
	//编辑部门
	public void updateSection(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		PrintWriter out=response.getWriter();
		SectionForm sectionForm=(SectionForm) form;
		boolean ok=sectionService.updateSection(sectionForm);
		if(ok){
			//获得登录日志编号
			String loginId=(String) request.getSession().getAttribute("loginId");
			String loginName=(String) request.getSession().getAttribute("loginName");
			//到登录日志中修改信息
			loginLogService.updateLogin(loginId, loginName+"编辑部门");
			out.print("<script>alert('修改成功！');window.location.href='section.do?flag=selectSection';</script>");
		}else{
			out.print("<script>alert('修改失败！');window.location.href='section.do?flag=selectSection';</script>");
		}
	
	}
	
	//停用部门
	public void stopSection(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		PrintWriter out=response.getWriter();
		int id=Integer.parseInt(request.getParameter("id"));
		Section s=sectionService.getSectionId(id);
		s.setSectionDefinedOne("0");
		boolean ok=sectionService.update(s);
		if(ok){
			//获得登录日志编号
			String loginId=(String) request.getSession().getAttribute("loginId");
			String loginName=(String) request.getSession().getAttribute("loginName");
			//到登录日志中修改信息
			loginLogService.updateLogin(loginId, loginName+"停用部门");
			out.print("<script>alert('停用成功！');window.location.href='section.do?flag=selectSection';</script>");
		}else{
			out.print("<script>alert('停用失败！');window.location.href='section.do?flag=selectSection';</script>");
		}
		
	}
	
	//查询部门
	public ActionForward selectSection(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {			

		List<Section> listSection=sectionService.getAll();
		request.setAttribute("listSection", listSection);
		PrintWriter out=response.getWriter();
		JSONArray array=new JSONArray();
		if(listSection.size()<=0){
			JSONObject obj=new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		for(int i=0; i<listSection.size();i++){
			JSONObject obj=new JSONObject();
			obj.put("result", "notnull");
			obj.put("id", listSection.get(i).getSectionId());
			obj.put("department", listSection.get(i).getSectionName());
			obj.put("incharge", listSection.get(i).getSectionHuman());
			obj.put("remark", listSection.get(i).getSectionRemark());
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
		return null;
	}
	
	
	//ajax查询部门
	public void selectSectionAjax(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {			
		PrintWriter out=response.getWriter();
		List<Section> listSection=sectionService.getAll();
				
		JSONArray array=new JSONArray();
		//遍历集合中元素，然后转换为JSON格式
	    for(Section c:listSection){
	    	JSONObject obj = new JSONObject();
	    	obj.put("id", c.getSectionId());
	    	obj.put("name", c.getSectionName());
	    	array.add(obj);
	    }
	    //将数据写入到前台
	    out.print(array.toString());
	    out.flush();
	    out.close();
		
	}

}
