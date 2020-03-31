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
import com.xinggang.project.entity.InteriorUser;
import com.xinggang.project.entity.InteriorUserDuty;
import com.xinggang.project.entity.Section;
import com.xinggang.project.form.InteriorUserForm;
import com.xinggang.project.service.ClassTService;
import com.xinggang.project.service.InteriorUserDutyService;
import com.xinggang.project.service.InteriorUserService;
import com.xinggang.project.service.LoginLogService;
import com.xinggang.project.service.SectionService;

/**
 * InteriorUser entity. @author MyEclipse Persistence Tools 使用系统公司内部人员类action
 */
public class InteriorUserAction extends DispatchAction {
	// 内部人员service
	private InteriorUserService interiorUserService;
	// 部门service
	private SectionService sectionService;
	// 班组service
	private ClassTService classTService;
	// 职责service
	private InteriorUserDutyService interiorUserDutyService;
	// 登录日志sevice
	private LoginLogService loginLogService;

	public void setLoginLogService(LoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}

	public void setInteriorUserDutyService(
			InteriorUserDutyService interiorUserDutyService) {
		this.interiorUserDutyService = interiorUserDutyService;
	}

	public void setClassTService(ClassTService classTService) {
		this.classTService = classTService;
	}

	public void setSectionService(SectionService sectionService) {
		this.sectionService = sectionService;
	}

	public void setInteriorUserService(InteriorUserService interiorUserService) {
		this.interiorUserService = interiorUserService;
	}

	// 到添加内部人员表
	public ActionForward goAddinteriorUser(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 查询部门
		List<Section> section = sectionService.getAll();
		// 查询班组
		List<ClassT> listClassT = classTService.getAll();
		// 查询职责
		List<InteriorUserDuty> listinteriorUserDuty = interiorUserDutyService
				.getAll();
		// 保存部门
		request.setAttribute("section", section);
		// 保存班组
		request.setAttribute("listClassT", listClassT);
		// 保存内部人员
		request.setAttribute("listinteriorUserDuty", listinteriorUserDuty);
		return mapping.findForward("goAddinteriorUser");
	}

	// 根据部门和班组查询
	public ActionForward selectBuMenAndBanzu(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 查询部门
		List<Section> section = sectionService.getAll();
		// 查询班组
		List<ClassT> listClassT = classTService.getAll();
		// 保存部门
		request.setAttribute("section", section);
		// 保存班组
		request.setAttribute("listClassT", listClassT);
		// 根据部门查询
		List<InteriorUser> listInteriorUser = interiorUserService.getBuAndBan(
				"", "");
		request.setAttribute("listInteriorUser", listInteriorUser);
		return null;
	}

	public void getBumen(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//
		// int id=Integer.parseInt(request.getParameter("id"));
		PrintWriter out = response.getWriter();
		// 查询部门
		// List<Section> section=sectionService.getAll();
		// 查询班组
		List<Section> listSection = sectionService.getAll();
		// 保存部门
		// request.setAttribute("section",section);
		// 用JSONObject保存班组
		JSONArray array = new JSONArray();
		for (Section c : listSection) {
			JSONObject obj = new JSONObject();
			obj.put("id", c.getSectionId());
			obj.put("name", c.getSectionName());
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
	}

	public void getClassT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//
		// int id=Integer.parseInt(request.getParameter("id"));
		PrintWriter out = response.getWriter();
		// 查询部门
		// List<Section> section=sectionService.getAll();
		// 查询班组
		List<ClassT> listClassT = classTService.getAll();
		// 保存部门
		// request.setAttribute("section",section);
		// 用JSONObject保存班组
		JSONArray array = new JSONArray();
		for (ClassT c : listClassT) {
			JSONObject obj = new JSONObject();
			obj.put("id", c.getClassId());
			obj.put("classTName", c.getClassName());
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
	}

	public void getDuty(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PrintWriter out = response.getWriter();
		List<InteriorUserDuty> listinteriorUserDuty = interiorUserDutyService
				.getAll();
		JSONArray array = new JSONArray();
		for (InteriorUserDuty I : listinteriorUserDuty) {
			JSONObject obj = new JSONObject();
			obj.put("id", I.getInteriorUserDutyId());
			obj.put("name", I.getInteriorUserDutyName());
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();

	}

	// 添加内部人员
	public void addInteriorUserOk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		InteriorUserForm interiorUserForm = (InteriorUserForm) form;
		List<Section> section = sectionService.getAll();
		// 保存部门
		request.setAttribute("section", section);
		ClassT classT = classTService.getClassTId(interiorUserForm.getClassT());
		InteriorUserDuty interiorUserDuty = interiorUserDutyService
				.getInteriorUserDutyId(interiorUserForm.getInteriorUserDuty());
		Section section2 = sectionService.getSectionId(interiorUserForm
				.getSection());

		boolean ok = interiorUserService.saveInteriorUser(interiorUserForm,
				classT, interiorUserDuty, section2);
		if (ok) {
			// 获得登录日志编号
			String loginId = (String) request.getSession().getAttribute(
					"loginId");
			String loginName = (String) request.getSession().getAttribute(
					"loginName");
			// 到登录日志中修改信息
			loginLogService.updateLogin(loginId, loginName + "添加内部人员");
			out.print("<script>alert('添加成功！');window.location.href='interiorUser.do?flag=selectInteriorUser';</script>");
		} else {
			out.print("<script>alert('添加失败！');window.location.href='interiorUser.do?flag=selectInteriorUser';</script>");
		}

	}

	public ActionForward selectInteriorUser(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<InteriorUser> listInteriorUser = interiorUserService.getAll();
		request.setAttribute("listInteriorUser", listInteriorUser);
		PrintWriter out=response.getWriter();
		JSONArray array=new JSONArray();
		if(listInteriorUser.size()<=0){
			JSONObject obj=new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		for(int i=0; i<listInteriorUser.size();i++){
			JSONObject obj=new JSONObject();
			obj.put("result", "notnull");
			obj.put("id", listInteriorUser.get(i).getIuserId());
			obj.put("name", listInteriorUser.get(i).getIuserName());
			obj.put("sex", listInteriorUser.get(i).getIuserSex());
			obj.put("department", listInteriorUser.get(i).getSection()==null?"无":listInteriorUser.get(i).getSection().getSectionName());
			obj.put("team", listInteriorUser.get(i).getClassT()==null?"无":listInteriorUser.get(i).getClassT().getClassName());
			obj.put("duty", listInteriorUser.get(i).getInteriorUserDuty()==null?"无":listInteriorUser.get(i).getInteriorUserDuty().getInteriorUserDutyName());
			obj.put("username", listInteriorUser.get(i).getIuserLoginName());
			obj.put("password", listInteriorUser.get(i).getIuserPassword());
			obj.put("tel", listInteriorUser.get(i).getIuserTel());
			obj.put("date", listInteriorUser.get(i).getIuserCreateTime());
			obj.put("remark", listInteriorUser.get(i).getIuserRemark());
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
		return null;
	}

	// 转到修改页面
	public ActionForward goUpdateinteriorUser(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		int iuserId = Integer.parseInt(request.getParameter("iuserId"));
		// 根据id查询相应的信息
		InteriorUser interiorUser = interiorUserService
				.getInteriorUserId(iuserId);
		request.setAttribute("interiorUser", interiorUser);
		// 查询部门表，并保存
		List<Section> section = sectionService.getAll();
		request.setAttribute("section", section);
		// 查询班组表，并保存
		List<ClassT> listClassT = classTService.getAll();
		request.setAttribute("listClassT", listClassT);
		// 查询职责，并保存
		List<InteriorUserDuty> listinteriorUserDuty = interiorUserDutyService
				.getAll();
		request.setAttribute("listinteriorUserDuty", listinteriorUserDuty);

		return mapping.findForward("goUpdateinteriorUser");
	}

	// 修改内部人员
	public void updateinteriorUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		InteriorUserForm interiorUserForm = (InteriorUserForm) form;
		ClassT classT = classTService.getClassTId(interiorUserForm.getClassT());
		InteriorUserDuty interiorUserDuty = interiorUserDutyService
				.getInteriorUserDutyId(interiorUserForm.getInteriorUserDuty());
		Section section2 = sectionService.getSectionId(interiorUserForm
				.getSection());

		boolean ok = interiorUserService.updateInteriorUser(interiorUserForm,
				classT, interiorUserDuty, section2);

		if (ok) {
			// 获得登录日志编号
			String loginId = (String) request.getSession().getAttribute(
					"loginId");
			String loginName = (String) request.getSession().getAttribute(
					"loginName");
			// 到登录日志中修改信息
			loginLogService.updateLogin(loginId, loginName + "修改内部人员");
			out.print("<script>alert('修改成功！');window.location.href='interiorUser.do?flag=selectInteriorUser';</script>");
		} else {
			out.print("<script>alert('修改失败！');window.location.href='interiorUser.do?flag=selectInteriorUser';</script>");
		}
	}

	// 停用内部人员
	public void stopInteriorUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));
		InteriorUser interiorUser = interiorUserService.getInteriorUserId(id);
		interiorUser.setIuserCease(0);
		boolean ok = interiorUserService.update(interiorUser);
		if (ok) {
			// 获得登录日志编号
			String loginId = (String) request.getSession().getAttribute(
					"loginId");
			String loginName = (String) request.getSession().getAttribute(
					"loginName");
			// 到登录日志中修改信息
			loginLogService.updateLogin(loginId, loginName + "停用内部人员");
			out.print("<script>alert('停用成功！');window.location.href='interiorUser.do?flag=selectInteriorUser';</script>");
		} else {
			out.print("<script>alert('停用失败！');window.location.href='interiorUser.do?flag=selectInteriorUser';</script>");
		}
	}

	public void ShiFouZuoYe(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));
		InteriorUser interiorUser = interiorUserService.getInteriorUserId(id);
		Integer work = interiorUser.getIuserWork();
		JSONArray array = new JSONArray();
		if (work == 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "zaixian");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
		} else {
			JSONObject obj = new JSONObject();
			obj.put("result", "no");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
		}
	}

	// 通过登录的人的id进行查询
	public ActionForward getGeRen(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (request.getSession().getAttribute("iulistId") != null) {
			InteriorUser iu = this.interiorUserService
					.getInteriorUserId(Integer.parseInt(request.getSession()
							.getAttribute("iulistId").toString()));
			// 将查询结果保存到request中
			request.setAttribute("iu", iu);
		}
		// 返回到对应的页面
		return mapping.findForward("goGeRen");
	}

	// 个人进行修改密码
	public ActionForward updatePassword(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		InteriorUserForm iuf = (InteriorUserForm) form;
		if (request.getSession().getAttribute("iulistId") != null) {
			InteriorUser iu = this.interiorUserService
					.getInteriorUserId(Integer.parseInt(request.getSession()
							.getAttribute("iulistId").toString()));
			if (iu != null) {
				iu.setIuserPassword(iuf.getIuserPassword());// 修改密码
				iu.setIuserTel(iuf.getIuserTel());// 修改联系电话
				boolean ok = this.interiorUserService.update(iu);
				if (ok) {
					request.getSession().setAttribute("updateP", "修改成功！");
				} else {
					request.getSession().setAttribute("updateP", "修改失败！");
				}
			} else {
				request.getSession().setAttribute("updateP", "修改失败！");
			}
		} else {
			request.getSession().setAttribute("updateP", "修改失败！");
		}
		// 返回到对应的页面
		PrintWriter out = response.getWriter();
		out.print("<script>alert('"
				+ request.getSession().getAttribute("updateP")
				+ "');document.location.href='/XGProject/home-page/user-info.jsp';</script>");
		return null;
	}

	// 通过部门，班组进行查询，并在线状态是在线的
	public ActionForward getOnLine(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		InteriorUserForm iuf = (InteriorUserForm) form;
		if (iuf.getBumen() == null) {
			iuf.setBumen("");
		}
		if (iuf.getBanzu() == null) {
			iuf.setBanzu("");
		}
		List<InteriorUser> iulist = this.interiorUserService.getBuAndBan(
				iuf.getBumen(), iuf.getBanzu());
		PrintWriter out = response.getWriter();
		JSONArray array = new JSONArray();
		if (iulist.size() <= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		try {
			for (int i = 0; i < iulist.size(); i++) {
				JSONObject obj = new JSONObject();
				obj.put("id", i + 1);// 保存序号
				obj.put("bumen", iulist.get(i).getSection() == null ? "无"
						: iulist.get(i).getSection().getSectionName());// 保存部门名称
				obj.put("banzu", iulist.get(i).getClassT() == null ? "无"
						: iulist.get(i).getClassT().getClassName());// 保存班组名称
				obj.put("name", iulist.get(i).getIuserName());// 保存用户姓名
				obj.put("juese", iulist.get(i).getInteriorUserDuty()
						.getInteriorUserDutyName() == null ? "无" : iulist
						.get(i).getInteriorUserDuty().getInteriorUserDutyName());// 保存角色名称
				obj.put("zaixian", "在线");
				obj.put("zuoye", iulist.get(i).getIuserWork() == 1 ? "未作业"
						: "作业中");// 保存是否作业
				obj.put("tel", iulist.get(i).getIuserTel());// 保存联系电话
				obj.put("remark", iulist.get(i).getIuserRemark() == null ? "无"
						: iulist.get(i).getIuserRemark());// 保存备注
				obj.put("result", "notnull");
				array.add(obj);
			}
		} catch (Exception e) {
			System.out.println("在线用户异常");
		}
		out.print(array.toString());
		out.flush();
		out.close();
		// 返回到对应的页面
		return null;
	}
	
	//通过权限查询相应的人员
	public ActionForward getPowerMan(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获得内部人员form
		InteriorUserForm iuf = (InteriorUserForm) form;
		//得到查询的权限名称
		String power = new String(iuf.getIuserDefinedOne().getBytes("ISO-8859-1"), "utf-8");
		//通过权限名称查询相应的内部人员
		List<InteriorUser> iuList = this.interiorUserService.getPowerMan(power);
		PrintWriter out =response.getWriter();
		JSONArray array = new JSONArray();
		if(iuList!=null && iuList.size()>0){
			for(int i=0; i<iuList.size(); i++){
				JSONObject obj = new JSONObject();
				obj.put("result", "notnull");
				obj.put("id", iuList.get(i).getIuserId());//人员id
				obj.put("name", iuList.get(i).getIuserName());//人员姓名
				array.add(obj);
			}
			out.print(array.toString());
			out.flush();
			out.close();
		}else{
			//当查询结果为空的时候返回结果为null
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
		}
		return null;
	}
}
