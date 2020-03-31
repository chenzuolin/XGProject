package com.xinggang.project.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.xinggang.project.service.ClientService;
import com.xinggang.project.service.LoginLogService;
import com.xinggang.project.service.TarehouseDetailOperateService;
import com.xinggang.project.service.TarehouseService;

/**
 * 货物批次操作类action
 * 
 * @author Administrator
 * 
 */
public class TarehouseDetailOperateAction extends DispatchAction {
	// 货物批次操作service
	@SuppressWarnings("unused")
	private TarehouseDetailOperateService tarehouseDetailOperateService;
	// 货物批次service
	@SuppressWarnings("unused")
	private TarehouseService tarehouseService;
	// 客户service
	@SuppressWarnings("unused")
	private ClientService clientService;
	// 登录日志表service
	@SuppressWarnings("unused")
	private LoginLogService loginLogService;

	public void setLoginLogService(LoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public void setTarehouseService(TarehouseService tarehouseService) {
		this.tarehouseService = tarehouseService;
	}

	public void setTarehouseDetailOperateService(
			TarehouseDetailOperateService tarehouseDetailOperateService) {
		this.tarehouseDetailOperateService = tarehouseDetailOperateService;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return super.execute(mapping, form, request, response);
	}

}
