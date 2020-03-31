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

import com.xinggang.project.entity.CostType;
import com.xinggang.project.form.CostTypeForm;
import com.xinggang.project.service.CostTypeService;
import com.xinggang.project.service.LoginLogService;

/**
 * 费用类型action
 * 
 * @author Administrator
 * 
 */
public class CostTypeAction extends DispatchAction {

	// 费用类型service
	private CostTypeService costTypeService;
	// 日志service
	private LoginLogService loginLogService;

	public void setLoginLogService(LoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}

	public void setCostTypeService(CostTypeService costTypeService) {
		this.costTypeService = costTypeService;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return super.execute(mapping, form, request, response);
	}

	// 增加
	public void saveCostType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out=response.getWriter();
		CostTypeForm ctf = (CostTypeForm) form;
		if (ctf != null) {
			CostType costType = new CostType();
			costType.setCtypeName(ctf.getCtypeName());// 费用类型名称
			costType.setCtypeCost(ctf.getCtypeCost());// 费用
			costType.setCtypeDefinedOne("1");// 是否停用，默认不停用
			costType.setCtypeRemark(ctf.getCtypeRemark());// 备注
			boolean ok = this.costTypeService.save(costType);
			if (ok) {
				this.loginLogService.updateLogin(request.getSession()
						.getAttribute("loginlogId").toString(), "添加费用类型");// 记录到登录日志中
				out.print("<script>alert('添加成功！');window.location.href='costType.do?flag=getCostTypeAll';</script>");
			} else {
				out.print("<script>alert('添加失败！');window.location.href='costType.do?flag=getCostTypeAll';</script>");
			}
		} else {
			out.print("<script>alert('添加失败！');window.location.href='costType.do?flag=getCostTypeAll';</script>");
		}

	}

	// 修改
	public void updateCostType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out=response.getWriter();
		CostTypeForm ctf = (CostTypeForm) form;
		System.out.println(ctf.getCtypeId());
		CostType ct = this.costTypeService.getCostTypeId(ctf.getCtypeId());// 通过编号查询
		ct.setCtypeName(ctf.getCtypeName());// 修改的费用类型
		ct.setCtypeCost(ctf.getCtypeCost());// 修改的费用
		ct.setCtypeRemark(ctf.getCtypeRemark());// 备注
		boolean ok = this.costTypeService.update(ct);
		if (ok) {
			this.loginLogService.updateLogin(request.getSession()
					.getAttribute("loginlogId").toString(),
					"修改费用类型" + ctf.getCtypeId());// 记录到登录日志中
			out.print("<script>alert('修改成功！');window.location.href='costType.do?flag=getCostTypeAll';</script>");
		} else {
			out.print("<script>alert('修改失败！');window.location.href='costType.do?flag=getCostTypeAll';</script>");
		}
	}

	// 查询全部
	public ActionForward getCostTypeAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		List<CostType> ctlist = this.costTypeService.getAll();// 查询全部
		request.setAttribute("ctlist", ctlist);// 保存到request中
		PrintWriter out = response.getWriter();
		JSONArray array = new JSONArray();
		if (ctlist.size() <= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		for (int i = 0; i < ctlist.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("result", "notnull");
			obj.put("id", ctlist.get(i).getCtypeId());
			obj.put("cost", ctlist.get(i).getCtypeName());
			obj.put("money", ctlist.get(i).getCtypeCost());
			obj.put("remark", ctlist.get(i).getCtypeRemark());
			array.add(obj);

		}
		out.print(array.toString());
		out.flush();
		out.close();
		return null;

		
	}
	
	// 停用
	public void stopCostTypeAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out=response.getWriter();
		CostTypeForm costTypeForm=(CostTypeForm) form;
		CostType ct= costTypeService.getCostTypeId(costTypeForm.getCtypeId());
		ct.setCtypeDefinedOne("0");
		boolean ok= costTypeService.update(ct);
		if(ok){
			this.loginLogService.updateLogin(request.getSession()
					.getAttribute("loginlogId").toString(),
					"修改费用类型" + ct.getCtypeId());// 记录到登录日志中
			out.print("<script>alert('停用成功！');window.location.href='costType.do?flag=getCostTypeAll';</script>");
		}else{
			out.print("<script>alert('停用失败！');window.location.href='costType.do?flag=getCostTypeAll';</script>");
		}
		
	}
}
