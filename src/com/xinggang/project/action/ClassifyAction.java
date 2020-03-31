package com.xinggang.project.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.xinggang.project.entity.Classify;
import com.xinggang.project.form.ClassifyForm;
import com.xinggang.project.service.ClassifyService;
import com.xinggang.project.service.LoginLogService;

/**
 * 功能类别Action
 * @author Administrator 功能分类action
 */

public class ClassifyAction extends DispatchAction {
	//功能分类service
	private ClassifyService classifyService;
	//登录日志表service
	private LoginLogService loginLogService;
	public void setLoginLogService(LoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}

	public void setClassifyService(ClassifyService classifyService) {
		this.classifyService = classifyService;
	}

	public ActionForward goAddClassify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("goAddClassify");
	}

	public ActionForward addClassify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 得到form表单中的值
		ClassifyForm classifyForm = (ClassifyForm) form;

		Classify classify = new Classify();
		classify.setClassifyName(classifyForm.getClassifyName());
		boolean ok = classifyService.save(classify);
		if (ok) {
			//获得登录日志编号
			String loginId=(String) request.getSession().getAttribute("loginId");
			String loginName=(String) request.getSession().getAttribute("loginName");
			loginLogService.updateLogin(loginId, loginName+"添加功能分类信息");
			System.out.println("成功！");
		} else {
			System.out.println("失败！");
		}
		return mapping.findForward("addClassify");
	}
}
