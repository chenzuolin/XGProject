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

import com.xinggang.project.entity.Classify;
import com.xinggang.project.entity.Functions;
import com.xinggang.project.form.FunctionsForm;
import com.xinggang.project.service.ClassifyService;
import com.xinggang.project.service.FunctionsService;
import com.xinggang.project.service.LoginLogService;

/**
 * Functions entity. @author MyEclipse Persistence Tools 功能表类action
 */
public class FunctionsAction extends DispatchAction {
	// 定义功能functionsService
	private FunctionsService functionsService;
	// 定义功能分类classifyService
	private ClassifyService classifyService;
	// 登录日志表service
	private LoginLogService loginLogService;

	public void setLoginLogService(LoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}

	public void setFunctionsService(FunctionsService functionsService) {
		this.functionsService = functionsService;
	}

	public void setClassifyService(ClassifyService classifyService) {
		this.classifyService = classifyService;
	}

	// 取得所有的功能类别
	public ActionForward goAddFunctions(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 查询出功能分类表中的分类名称，然后保存
		List<Classify> listClassify = classifyService.getAll();
		// 保存
		request.setAttribute("listClassify", listClassify);
		return mapping.findForward("goAddFunctions");
	}

	// 添加功能
	public ActionForward addFunctions(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 获取表单中提交的数据
		FunctionsForm functionForm = (FunctionsForm) form;

		// new实体类保存数据
		Functions functions = new Functions();
		functions.setClassify(classifyService.getClassifyId(functionForm
				.getClassify()));// 添加类别
		functions.setFunctionName(functionForm.getFunctionName());// 添加功能名称
		functions.setFunctionRemark(functionForm.getFunctionRemark());// 添加功能备注
		boolean ok = functionsService.save(functions);// 保存功能
		if (ok) {
			// 获得登录日志编号
			String loginId = (String) request.getSession().getAttribute(
					"loginId");
			String loginName = (String) request.getSession().getAttribute(
					"loginName");
			loginLogService.updateLogin(loginId, loginName + "添加功能信息");
			request.getSession().setAttribute("ok", "true");
		} else {
			request.getSession().setAttribute("ok", "false");
			// 查询出功能分类表中的分类名称，然后保存
			List<Classify> listClassify = classifyService.getAll();
			// 保存
			request.setAttribute("listClassify", listClassify);
			// 获取表单中提交的数据
		}
		return mapping.findForward("goAddFunctions");

	}

	// 查询功能
	public void goSelectFunctions(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/* 设置响应头允许ajax跨域访问 */
		response.setHeader("Access-Control-Allow-Origin", "*");
		/* 星号表示所有的异域请求都可以接受， */
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");

		PrintWriter out = response.getWriter();
		// 保存数据
		List<Functions> listFunctions = functionsService.getAll();

		request.setAttribute("listFunctions", listFunctions);
		JSONArray array = new JSONArray();
		for (Functions c : listFunctions) {
			JSONObject obj = new JSONObject();
			obj.put("id", c.getFunctionId());
			obj.put("name", c.getFunctionName());
			obj.put("leibei", c.getClassify().getClassifyName());
			obj.put("beizhu", c.getFunctionRemark());
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
	}

}
