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

import com.xinggang.project.entity.ClassT;
import com.xinggang.project.form.ClassTForm;
import com.xinggang.project.service.ClassTService;
import com.xinggang.project.service.LoginLogService;
import com.xinggang.project.service.SectionService;

/**
 * 班组类action
 * 
 * @author Administrator
 * 
 */

public class ClassTAction extends DispatchAction {
	// 班组service
	private ClassTService classTService;
	// 部门service
	@SuppressWarnings("unused")
	private SectionService sectionService;
	// 登录日志表service
	private LoginLogService loginLogService;

	public void setLoginLogService(LoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}

	public void setSectionService(SectionService sectionService) {
		this.sectionService = sectionService;
	}

	public void setClassTService(ClassTService classTService) {
		this.classTService = classTService;
	}

	public ActionForward goAddclassT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<ClassT> listClassT = classTService.getAll();
		request.setAttribute("listClassT", listClassT);
		return mapping.findForward("goAddclassT");
	}

	// 添加班组
	public void addClassT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		ClassTForm classTForm = (ClassTForm) form;
		// Section section=
		// sectionService.getSectionId(classTForm.getSection());

		// List<Section> listSection=sectionService.getAll();
		// request.setAttribute("listSection", listSection);
		boolean ok = classTService.saveClassT(classTForm);
		if (ok) {
			// 获得登录日志编号
			String loginId = (String) request.getSession().getAttribute(
					"loginId");
			String loginName = (String) request.getSession().getAttribute(
					"loginName");
			// 到登录日志中修改信息
			loginLogService.updateLogin(loginId, loginName + "添加班组");
			out.print("<script>alert('添加成功！');window.location.href='classT.do?flag=selectclassT';</script>");
		} else {
			out.print("<script>alert('添加失败！');window.location.href='classT.do?flag=selectclassT';</script>");
		}

	}

	// 转到修改班组页面
	public void stopClassT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));
		ClassT classT = classTService.getClassTId(id);
		classT.setClassDefinedOne("0");
		boolean ok = classTService.update(classT);
		if (ok) {
			// 获得登录日志编号
			String loginId = (String) request.getSession().getAttribute(
					"loginId");
			String loginName = (String) request.getSession().getAttribute(
					"loginName");
			// 到登录日志中修改信息
			loginLogService.updateLogin(loginId, loginName + "停用班组");
			out.print("<script>alert('停用成功！');window.location.href='classT.do?flag=selectclassT';</script>");
		} else {
			out.print("<script>alert('停用失败！');window.location.href='classT.do?flag=selectclassT';</script>");
		}
	}

	// 修改班组
	public void updateClassT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		ClassTForm classTForm = (ClassTForm) form;
		// 根据id获得section类
		// Section section=
		// sectionService.getSectionId(classTForm.getSection());
		boolean ok = classTService.updateClassT(classTForm);
		if (ok) {
			// 获得登录日志编号
			String loginId = (String) request.getSession().getAttribute(
					"loginId");
			String loginName = (String) request.getSession().getAttribute(
					"loginName");
			// 到登录日志中修改信息
			loginLogService.updateLogin(loginId, loginName + "修改班组");
			out.print("<script>alert('修改成功！');window.location.href='classT.do?flag=selectclassT';</script>");
		} else {
			out.print("<script>alert('修改失败！');window.location.href='classT.do?flag=selectclassT';</script>");
		}

	}

	// 查询班组
	public ActionForward selectclassT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<ClassT> listClassT = classTService.getAll();
		request.setAttribute("listClassT", listClassT);
		PrintWriter out=response.getWriter();
		JSONArray array=new JSONArray();
		if(listClassT.size()<=0){
			JSONObject obj=new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		for(int i=0; i<listClassT.size();i++){
			JSONObject obj=new JSONObject();
			obj.put("result", "notnull");
			obj.put("id", listClassT.get(i).getClassId());
			obj.put("team", listClassT.get(i).getClassName());
			obj.put("department", listClassT.get(i).getSection().getSectionName());
			obj.put("incharge", listClassT.get(i).getClassHuman());
			obj.put("remark", listClassT.get(i).getClassRemark());
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
		return null;
	}

	// 用ajax查询班组
	public ActionForward selectclassTAjax(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ClassTForm cf = (ClassTForm) form;
		
		List<ClassT> listClassT = classTService.getBumen(cf.getSection());

		PrintWriter out = response.getWriter();
		JSONArray array = new JSONArray();
		for (ClassT ct : listClassT) {
			JSONObject obj = new JSONObject();
			obj.put("id", ct.getClassId());// 保存班组id
			obj.put("name", ct.getClassName());// 保存班组名称
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
		return null;
	}

}
