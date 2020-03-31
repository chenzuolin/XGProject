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

import com.xinggang.project.entity.InteriorUserDuty;
import com.xinggang.project.entity.Powers;
import com.xinggang.project.entity.Section;
import com.xinggang.project.form.InteriorUserDutyForm;
import com.xinggang.project.service.FunctionsService;
import com.xinggang.project.service.InteriorUserDutyService;
import com.xinggang.project.service.LoginLogService;
import com.xinggang.project.service.PowersService;
import com.xinggang.project.service.SectionService;

/**
 * InteriorUserDuty entity. @author MyEclipse Persistence Tools
 * 使用系统的公司内部人员职责类action
 */
public class InteriorUserDutyAction extends DispatchAction {
	// 职责表service
	private InteriorUserDutyService interiorUserDutyService;
	// 部门表service
	private SectionService sectionService;
	// 权限
	private PowersService powersService;

	// 权限
	private FunctionsService functionsService;
	// 登录日志表service
	private LoginLogService loginLogService;

	public void setFunctionsService(FunctionsService functionsService) {
		this.functionsService = functionsService;
	}

	public void setPowersService(PowersService powersService) {
		this.powersService = powersService;
	}

	public void setLoginLogService(LoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}

	public void setSectionService(SectionService sectionService) {
		this.sectionService = sectionService;
	}

	public void setInteriorUserDutyService(
			InteriorUserDutyService interiorUserDutyService) {
		this.interiorUserDutyService = interiorUserDutyService;
	}

	public ActionForward goAddinteriorUserDuty(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<Section> listSection = sectionService.getAll();
		request.setAttribute("listSection", listSection);
		return mapping.findForward("goAddinteriorUserDuty");
	}

	// 添加内部人员职责
	public void addinteriorUserDuty(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int id = 0;
		PrintWriter out = response.getWriter();
		InteriorUserDutyForm interiorUserDutyForm = (InteriorUserDutyForm) form;
		boolean ok = interiorUserDutyService
				.saveInteriorUserDuty(interiorUserDutyForm);
		List<InteriorUserDuty> listDuty = interiorUserDutyService
				.getZhizeName(interiorUserDutyForm.getInteriorUserDutyName());
		for (InteriorUserDuty i : listDuty) {
			id = i.getInteriorUserDutyId();
		}
		if (ok) {
			boolean ok2 = false;
			Integer[] functionId = interiorUserDutyForm.getFunctionId();
			if (functionId.length > 0) {
				for (int i = 0; i < functionId.length; i++) {
					Powers p = new Powers();
					p.setFunctions(functionsService
							.getFunctionsId(functionId[i]));
					p.setInteriorUserDuty(interiorUserDutyService
							.getInteriorUserDutyId(id));
					ok2 = powersService.save(p);
				}
				if (ok2) {
					// 获得登录日志编号
					String loginId = (String) request.getSession()
							.getAttribute("loginId");
					String loginName = (String) request.getSession()
							.getAttribute("loginName");
					// 到登录日志中修改信息
					loginLogService.updateLogin(loginId, loginName
							+ "添加内部人员职责和权限");
					out.print("<script>alert('添加成功！');window.location.href='interiorUserDuty.do?flag=selectInteriorUserDuty';</script>");
				} else {
					out.print("<script>alert('添加失败！');window.location.href='interiorUserDuty.do?flag=selectInteriorUserDuty';</script>");
				}
			} else {
				out.print("<script>alert('添加成功！');window.location.href='interiorUserDuty.do?flag=selectInteriorUserDuty';</script>");
			}

		} else {
			out.print("<script>alert('添加失败！');window.location.href='interiorUserDuty.do?flag=selectInteriorUserDuty';</script>");
		}
	}

	// 修改内部人员职责
	public void updateInteriorUserDuty(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		InteriorUserDutyForm interiorUserDutyForm = (InteriorUserDutyForm) form;
		InteriorUserDuty interiorUserDuty = interiorUserDutyService
				.getInteriorUserDutyId(interiorUserDutyForm
						.getInteriorUserDutyId());
		System.out.println("职务id："
				+ interiorUserDutyForm.getInteriorUserDutyId() + "职务名称："
				+ interiorUserDutyForm.getInteriorUserDutyName() + "职务描述："
				+ interiorUserDutyForm.getInteriorUserDutyRemark());
		interiorUserDuty.setInteriorUserDutyName(interiorUserDutyForm
				.getInteriorUserDutyName());// 修改职务名称
		interiorUserDuty.setInteriorUserDutyRemark(interiorUserDutyForm
				.getInteriorUserDutyRemark());// 修改职务描述
		int dutyId = interiorUserDutyForm.getInteriorUserDutyId();
		boolean ok = interiorUserDutyService.update(interiorUserDuty);
		List<Powers> listPowers = powersService.getZhize(interiorUserDutyForm
				.getInteriorUserDutyId());
		if (ok) {
			boolean ok2 = false;
			// 先清空，后编辑
			for (Powers p : listPowers) {
				powersService.delete(p);
			}
			Integer[] functionId = interiorUserDutyForm.getFunctionId();
			if (functionId != null) {
				System.out.println("length:" + functionId.length);
				System.out.println("-------------进来啊------------");
				for (int i = 0; i < functionId.length; i++) {
					Powers powers = new Powers();
					powers.setFunctions(functionsService
							.getFunctionsId(functionId[i]));
					powers.setInteriorUserDuty(interiorUserDutyService
							.getInteriorUserDutyId(dutyId));
					ok2 = powersService.save(powers);
				}
				if (ok2) {
					// 获得登录日志编号
					String loginId = (String) request.getSession()
							.getAttribute("loginId");
					String loginName = (String) request.getSession()
							.getAttribute("loginName");
					// 到登录日志中修改信息
					loginLogService
							.updateLogin(loginId, loginName + "修改内部人员职责");
					out.print("<script>alert('修改成功！');window.location.href='interiorUserDuty.do?flag=selectInteriorUserDuty';</script>");
				} else {
					out.print("<script>alert('修改失败！');window.location.href='interiorUserDuty.do?flag=selectInteriorUserDuty';</script>");
				}
			} else {
				out.print("<script>alert('修改失败！不能为空！');window.location.href='interiorUserDuty.do?flag=selectInteriorUserDuty';</script>");
			}

		} else {
			out.print("<script>alert('修改失败！');window.location.href='interiorUserDuty.do?flag=selectInteriorUserDuty';</script>");
		}
	}

	public ActionForward selectInteriorUserDuty(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		/* 设置响应头允许ajax跨域访问 */
		response.setHeader("Access-Control-Allow-Origin", "*");
		/* 星号表示所有的异域请求都可以接受， */
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");

		List<InteriorUserDuty> listInteriorUserDuty = interiorUserDutyService
				.getAll();
		request.setAttribute("listInteriorUserDuty", listInteriorUserDuty);
		if (request.getParameter("ff") != null) {
			if (request.getParameter("ff").toString().equals("ajax")) {
				JSONArray array = new JSONArray();
				PrintWriter out = response.getWriter();
				if (listInteriorUserDuty != null
						&& listInteriorUserDuty.size() > 0) {
					for (int i = 0; i < listInteriorUserDuty.size(); i++) {
						JSONObject obj = new JSONObject();
						obj.put("id", listInteriorUserDuty.get(i)
								.getInteriorUserDutyId());// 职务编号
						obj.put("mingcheng", listInteriorUserDuty.get(i)
								.getInteriorUserDutyName());// 职务名称
						obj.put("miaoshu", listInteriorUserDuty.get(i)
								.getInteriorUserDutyRemark());// 职务描述
						array.add(obj);
					}
				}
				out.print(array.toString());
				out.flush();
				out.close();
				return null;
			}
		}
		return mapping.findForward("selectInteriorUserDuty");
	}

	public void stopInteriorUserDuty(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));
		InteriorUserDuty interiorUserDuty = interiorUserDutyService
				.getInteriorUserDutyId(id);
		interiorUserDuty.setInteriorUserDutyDefinedOne("0");

		boolean ok = interiorUserDutyService.update(interiorUserDuty);
		if (ok) {
			// 获得登录日志编号
			String loginId = (String) request.getSession().getAttribute(
					"loginId");
			String loginName = (String) request.getSession().getAttribute(
					"loginName");
			// 到登录日志中修改信息
			loginLogService.updateLogin(loginId, loginName + "停用职责");
			out.print("<script>alert('停用成功！');window.location.href='interiorUserDuty.do?flag=selectInteriorUserDuty';</script>");
		} else {
			out.print("<script>alert('停用失败！');window.location.href='interiorUserDuty.do?flag=selectInteriorUserDuty';</script>");
		}
	}

}
