package com.xinggang.project.action;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.transaction.annotation.Transactional;

import com.xinggang.project.entity.SetAccounts;
import com.xinggang.project.form.SetAccountsForm;
import com.xinggang.project.service.LoginLogService;
import com.xinggang.project.service.SetAccountsService;
import com.xinggang.project.tools.PresentTime;

/**
 * 滞纳金设置类action
 * 
 * @author Administrator
 * 
 */
@Transactional
public class SetAccountsAction extends DispatchAction {

	// 设置滞纳金service
	private SetAccountsService setAccountsService;
	// 登录日志service
	private LoginLogService loginLogService;
	// 获取当前时间工具类
	PresentTime pt = new PresentTime();

	public void setSetAccountsService(SetAccountsService setAccountsService) {
		this.setAccountsService = setAccountsService;
	}

	public void setLoginLogService(LoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return super.execute(mapping, form, request, response);
	}

	// 添加滞纳金设置
	public ActionForward saveSAccounts(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 取出滞纳金设置中form中的值
		SetAccountsForm sAccountsForm = (SetAccountsForm) form;
		boolean ok = this.setAccountsService.save(sAccountsForm);
		if (ok) {// 添加成功
			request.getSession().setAttribute("savemessage", "添加成功！");
			this.loginLogService.updateLogin(
					request.getSession().getAttribute("loginlogId").toString(),
					"设置滞纳金");// 记录到日志表中
		} else {// 添加失败
			request.getSession().setAttribute("savemessage", "添加失败！");
		}
		// 在登录日志中记录这一操作
		// 取出该用户登录时向日志表中增加的数据编号
		/*
		 * String loginlogId = request.getSession().getAttribute("loginlogId")
		 * .toString(); // 修改数据表中该编号的操作记录
		 * this.loginLogService.updateLogin(loginlogId, "添加滞纳金");
		 */

		// 返回到相应的页面
		return mapping.findForward("success");
	}

	// 修改滞纳金设置表
	public ActionForward updateSAccounts(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 取出滞纳金设置中form中的值
		SetAccountsForm sAccountsForm = (SetAccountsForm) form;
		boolean ok = this.setAccountsService.update(sAccountsForm);
		if (ok) {// 修改成功
			request.getSession().setAttribute("updatemessage", "修改成功！");
		} else {// 修改失败
			request.getSession().setAttribute("updatemessage", "修改失败！");
		}
		// 在登录日志中记录这一操作
		// 取出该用户登录时向日志表中增加的数据编号
		String loginlogId = request.getSession().getAttribute("loginlogId")
				.toString();
		// 修改数据表中该编号的操作记录
		this.loginLogService.updateLogin(loginlogId,
				"修改滞纳金" + sAccountsForm.getSaid());

		getAllsAccounts(mapping, sAccountsForm, request, response);// 调用到全部查询的页面
		// 返回到相应的页面
		return super.execute(mapping, form, request, response);
	}

	// 查询滞纳金设置表
	public ActionForward getAllsAccounts(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 查询出全部滞纳金
		List<SetAccounts> sAccountsList = this.setAccountsService.getAll();
		// 保存在request中
		request.getSession().setAttribute("sAccountsList", sAccountsList);
		// 返回到相应的界面
		return super.execute(mapping, form, request, response);
	}

	// 文件的下载
	public ActionForward down(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String fileName = "androidApp.apk";// 下载文件的名称加扩展名
		// String filePath =
		// "E:\\StockProject\\LogisticsProject\\XGProject\\WebRoot\\img\\";//文件下载的相对的路径
		String filePath = request.getContextPath() + ("/App/" + fileName);// //获取目标文件的绝对路径
		// File file = new File(filePath,fileName);//创建文件
		// Thread.sleep(1000 * 5); //延迟加载
		// 设置文件MIME类型
		response.setContentType("Content-Disposition");
		// response.addHeader("Content-Disposition", "attachment;filename=" +
		// fileName);
		// 设置Content-Disposition
		response.setHeader("Content-Disposition", "attachment;filename="
				+ fileName);
		// BufferedOutputStream bos = new BufferedOutputStream(os);
		// long len = file.length();//长度
		try {
			// FileInputStream fis = new FileInputStream(file);
			InputStream in = new FileInputStream(filePath);// z读取文件
			OutputStream os = response.getOutputStream();
			try {
				// byte[] buffer = new byte[1024 * 10];
				/*
				 * for (int read; (read = in.read(buffer)) != -1;) {
				 * os.write(buffer, 0, read); }
				 */
				int b;
				while ((b = in.read()) != -1) {
					os.write(b);
				}
			} finally {
				in.close();
				os.close();
			}
		} finally {
		}
		// 返回到相应的界面
		return null;
	}
}
