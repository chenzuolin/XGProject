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

import com.xinggang.project.entity.Working;
import com.xinggang.project.form.WorkingForm;
import com.xinggang.project.service.ClassTService;
import com.xinggang.project.service.LoginLogService;
import com.xinggang.project.service.WorkingService;

/**
 * 作业人员类，例天车工，装卸工action
 * 
 * @author Administrator
 * 
 */
public class WorkingAction extends DispatchAction {
	// 作业人员service
	private WorkingService workingService;
	// 班组service
	@SuppressWarnings("unused")
	private ClassTService classTService;
	// 登录日志service
	private LoginLogService loginLogService;


	public void setLoginLogService(LoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}

	public void setClassTService(ClassTService classTService) {
		this.classTService = classTService;
	}

	public void setWorkingService(WorkingService workingService) {
		this.workingService = workingService;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return super.execute(mapping, form, request, response);
	}

	// 通过不同的工种进行查询
	public ActionForward getZuoYeRenYuan(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String gongzhong = request.getParameter("ff");
		PrintWriter out = response.getWriter();
		JSONArray array = new JSONArray();
		if (gongzhong.equals("tianche")) {
			List<Working> tlist = this.workingService.getWorkingKind("天车工");

			if (tlist.size() <= 0) {
				JSONObject obj = new JSONObject();
				obj.put("result", "null");
				array.add(obj);
				out.print(array.toString());
				out.flush();
				out.close();
			}
			for (int i = 0; i < tlist.size(); i++) {
				JSONObject obj = new JSONObject();
				obj.put("name", tlist.get(i).getWorkingName());// 保存作业人员姓名
				obj.put("result", "notnull");
				array.add(obj);
			}
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		} else if (gongzhong.equals("zhuangxie")) {
			List<Working> tlist = this.workingService.getWorkingKind("装卸工");

			if (tlist.size() <= 0) {
				JSONObject obj = new JSONObject();
				obj.put("result", "null");
				array.add(obj);
				out.print(array.toString());
				out.flush();
				out.close();
			}
			for (int i = 0; i < tlist.size(); i++) {
				JSONObject obj = new JSONObject();
				obj.put("zname", tlist.get(i).getWorkingName());// 保存作业人员姓名
				obj.put("result", "notnull");
				array.add(obj);
			}
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		} else {
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}

	}

	// 添加作业人员
	public void saveWorking(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WorkingForm wf = (WorkingForm) form;
		System.out.println("添加的工作人员的姓名是：" + wf.getWorkingName());
		boolean ok = this.workingService.saveWorking(wf);
		if (ok) {
			this.loginLogService.updateLogin(
					request.getSession().getAttribute("loginlogId").toString(),
					"添加作业人员");
			request.getSession().setAttribute("saveWorking", "添加成功！");
		} else {
			request.getSession().setAttribute("saveWorking", "添加失败！");
		}
		wf.setWorkingName("");
		wf.setWorkingKindOfWork("");
		PrintWriter out = response.getWriter();
		out.print("<script>alert('"
				+ request.getSession().getAttribute("saveWorking")
				+ "');window.location.href='/XGProject/sys-page/zuoye-man.jsp';</script>");
		// 返回到对应的页面
	}

	// 删除工作人员
	public void deleteWorking(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WorkingForm wf = (WorkingForm) form;
		Working working = this.workingService.getWorkingId(wf.getWorkingId());// 通过编号查询
		boolean ok = this.workingService.delete(working);
		if (ok) {
			this.loginLogService.updateLogin(
					request.getSession().getAttribute("loginlogId").toString(),
					"删除作业人员，编号：" + wf.getWorkingId());
			request.getSession().setAttribute("delWorking", "停用成功！");
		} else {
			request.getSession().setAttribute("delWorking", "停用失败！");
		}
		// 返回到对应的页面
		PrintWriter out = response.getWriter();
		out.print("<script>alert('"
				+ request.getSession().getAttribute("delWorking")
				+ "');window.location.href='/XGProject/sys-page/zuoye-man.jsp';</script>");
	}

	// 修改作业人员
	public void updateWorking(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WorkingForm wf = (WorkingForm) form;
		if (wf != null) {
			Working working = this.workingService.getWorkingId(wf
					.getWorkingId());// 通过编号查询
			if (working != null) {
				/*
				 * working.setWorkingClassId(this.classTService.getClassTId(wf
				 * .getWorkingClassId()));// 修改班组
				 */
				// 通过作业人员的姓名查询，如果有重复则修改失败
				if (this.workingService.getWorkingName(wf.getWorkingName())
						.size() <= 0
						|| working.getWorkingName().equals(wf.getWorkingName())) {
					working.setWorkingName(wf.getWorkingName());// 修改作业人员名成
					working.setWorkingKindOfWork(wf.getWorkingKindOfWork());// 修改工种
					working.setWorkingDefinedOne(wf.getWorkingDefinedOne());// 修改作业人员电话
					String remark = working.getWorkingRemark();// 取出备注
					working.setWorkingRemark(remark + ","
							+ wf.getWorkingRemark());// 设置备注
					boolean ok = this.workingService.update(working);
					if (ok) {
						this.loginLogService.updateLogin(request.getSession()
								.getAttribute("loginlogId").toString(),
								"修改作业人员，编号：" + wf.getWorkingId());
						request.getSession().setAttribute("updateWorking",
								"修改成功！");
					} else {
						request.getSession().setAttribute("updateWorking",
								"修改失败！");
					}
				} else {
					request.getSession().setAttribute("updateWorking", "修改失败！");
				}
			} else {
				request.getSession().setAttribute("updateWorking", "修改失败！");
			}
		} else {
			request.getSession().setAttribute("updateWorking", "修改失败！");
		}
		// 返回到对应的页面
		wf.setWorkingName("");
		wf.setWorkingKindOfWork("");
		PrintWriter out = response.getWriter();
		out.print("<script>alert('"
				+ request.getSession().getAttribute("updateWorking")
				+ "');window.location.href='/XGProject/sys-page/zuoye-man.jsp';</script>");
	}

	// 查看所有的作业人员,可通过姓名，班组，工种，进行查询
	public ActionForward getWorkingAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WorkingForm wf = (WorkingForm) form;
		if (wf.getWorkingName() == null
				|| wf.getWorkingName().toString().equals("")) {
			wf.setWorkingName("");
		}
		if (wf.getClassName() == null
				|| wf.getClassName().toString().equals("")) {
			wf.setClassName("");
		}
		if (wf.getWorkingKindOfWork() == null
				|| wf.getWorkingKindOfWork().toString().equals("")) {
			wf.setWorkingKindOfWork("");
		}

		int pageNow = 1;
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}
		int pageCount = this.workingService.getWorkingAllByPageCount(
				wf.getWorkingName(), wf.getClassName(),
				wf.getWorkingKindOfWork(), 20);

		if (pageNow >= pageCount) {
			pageNow = pageCount;
		}
		if (pageNow <= 1) {
			pageNow = 1;
		}
		List<Working> workingList = this.workingService.getWorkingAllByPage(
				wf.getWorkingName(), wf.getClassName(),
				wf.getWorkingKindOfWork(), pageNow, 20);
		request.setAttribute("workingList", workingList);// 保存到request中

		// 将当前页保存到request中
		request.getSession().setAttribute("pageNow", pageNow);

		if (request.getParameter("ff") != null
				&& request.getParameter("ff").toString().equals("ajax")) {
			PrintWriter out = response.getWriter();
			JSONArray array = new JSONArray();
			for (int i = 0; i < workingList.size(); i++) {
				JSONObject obj = new JSONObject();
				obj.put("id", workingList.get(i).getWorkingId());// 编号
				obj.put("name", workingList.get(i).getWorkingName());// 作业人员姓名
				obj.put("tel", workingList.get(i).getWorkingDefinedOne());// 作业人员联系电话
				obj.put("gongzhong", workingList.get(i).getWorkingKindOfWork());// 作业人员工种
				obj.put("remark", workingList.get(i).getWorkingRemark());// 备注
				obj.put("pageNow", pageNow);
				array.add(obj);
			}
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		// 返回到对应的页面
		return mapping.findForward("addSuccess");
	}

}
