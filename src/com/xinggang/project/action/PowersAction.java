package com.xinggang.project.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
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

import com.xinggang.project.entity.Functions;
import com.xinggang.project.entity.InteriorUserDuty;
import com.xinggang.project.entity.Powers;
import com.xinggang.project.form.PowersForm;
import com.xinggang.project.service.FunctionsService;
import com.xinggang.project.service.InteriorUserDutyService;
import com.xinggang.project.service.LoginLogService;
import com.xinggang.project.service.PowersService;

/**
 * Powers entity. @author MyEclipse Persistence Tools 权限类action
 */
public class PowersAction extends DispatchAction {
	private FunctionsService functionsService;// 功能service
	private PowersService powersService;// 权限service
	private InteriorUserDutyService interiorUserDutyService;// 内部人员职责/角色service
	// 登录日志表service
	@SuppressWarnings("unused")
	private LoginLogService loginLogService;

	public void setLoginLogService(LoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}

	public void setFunctionsService(FunctionsService functionsService) {
		this.functionsService = functionsService;
	}

	public void setPowersService(PowersService powersService) {
		this.powersService = powersService;
	}

	public void setInteriorUserDutyService(
			InteriorUserDutyService interiorUserDutyService) {
		this.interiorUserDutyService = interiorUserDutyService;
	}

	// 取得所有的权限
	public ActionForward getPowersAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<Powers> powersList = powersService.getAll();
		request.getSession().setAttribute("powersList", powersList);

		return null;
	}

	// 取得所有的权限
	public void selectPowersAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		/* 设置响应头允许ajax跨域访问 */
		response.setHeader("Access-Control-Allow-Origin", "*");
		/* 星号表示所有的异域请求都可以接受， */
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");

		PrintWriter out = response.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));
		List<Powers> powersList = powersService.getZhize(id);
		JSONArray array = new JSONArray();
		for (Powers p : powersList) {
			JSONObject obj = new JSONObject();
			obj.put("id", p.getFunctions().getFunctionId());
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();

	}

	// 分配权限
	public ActionForward goPowers(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 查询所有的功能
		List<Functions> functionsList = functionsService.getAll();
		List<InteriorUserDuty> interiorUserDutylist = interiorUserDutyService
				.getAll();

		request.setAttribute("functionsList", functionsList);
		request.setAttribute("interiorUserDutylist", interiorUserDutylist);

		return null;

	}

	// 点击分配权限
	public ActionForward fenpeiPowers(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PowersForm powersForm = (PowersForm) form;
		boolean ok = powersService.addPowers(powersForm);
		if (ok) {
			System.out.println("分配权限成功！");
		} else {
			System.out.println("分配权限失败！");
		}
		return null;

	}

	// 查询该角色的功能
	public ActionForward selectDutyPowers(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 到的角色的id
		Integer zhizeId = Integer.parseInt(request.getParameter("zhizeId"));
		List<Powers> listZheZe = powersService.getZhize(zhizeId);
		if (listZheZe != null) {
			System.out.println("成功！");
		} else {
			System.out.println("失败！");
		}
		return null;

	}

	// 取消分配权限
	public ActionForward updatePowers(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Integer id = Integer.parseInt(request.getParameter("id"));

		boolean ok = powersService.updatePowers(id);
		if (ok) {
			System.out.println("分配权限成功！");
		} else {
			System.out.println("分配权限失败！");
		}
		return null;

	}

	// 文件的下载
	public ActionForward downApp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 获得上传的要下载文件名
		String str = "wuliu.jpg";
		// 下载文件的位置
		File fileLoad = new File(
				"E:\\StockProject\\LogisticsProject\\XGProject\\WebRoot\\img\\",
				str);
		response.setHeader("Content-disposition", "attachment;filename="
				+ new String(str.getBytes("gb2312"), "ISO-8859-1"));
		// 客户使用保存文件的对话框
		response.setContentType("application/x-msdownload");// 通知客户文件的mime类型
		OutputStream o = response.getOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(o);
		byte b[] = new byte[500];// 传输文件用的字节数组，每次发送500个字节到输出流
		long fileLength = fileLoad.length();// 通知客户的文件长度
		String length = String.valueOf(fileLength);
		response.setHeader("Content_Length", length);
		FileInputStream in = new FileInputStream(fileLoad);
		// 读取文件，并发送给客户进行下载
		int n = 0;
		while ((n = in.read(b)) != -1) {
			bos.write(b, 0, n);
		}
		bos.flush();
		bos.close();
		return null;
	}

}
