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

import com.xinggang.project.entity.Bursary;
import com.xinggang.project.form.BursaryForm;
import com.xinggang.project.service.BursaryService;
import com.xinggang.project.service.LoginLogService;

/**
 * 库房action
 * 
 * @author Administrator
 * 
 */

public class BursaryAction extends DispatchAction {
	// 库房service
	private BursaryService bursaryService;
	// 日志service
	private LoginLogService loginLogService;

	public void setBursaryService(BursaryService bursaryService) {
		this.bursaryService = bursaryService;
	}

	public void setLoginLogService(LoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return super.execute(mapping, form, request, response);
	}

	// 添加库房
	public void saveBursary(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		BursaryForm bf = (BursaryForm) form;
		Bursary bursary = new Bursary();
		bursary.setBursaryName(bf.getBursaryName());// 添加库房的名称
		bursary.setBursaryDefinedTwo(bf.getBursaryDefinedTwo());// 添加库房的位置
		bursary.setBursaryMaxWeight(bf.getBursaryMaxWeight());// 添加库房的最大容量（吨）
		bursary.setBursaryDefinedOne("1");// 是否停用，默认1不停用，0为停用
		bursary.setBursaryRemark(bf.getBursaryRemark());// 添加备注
		boolean ok = this.bursaryService.save(bursary);
		if (ok) {
			this.loginLogService.updateLogin(
					request.getSession().getAttribute("loginlogId").toString(),
					"添加库房" + bursary.getBursaryId());// 将这一操作保存在日志表中
			out.print("<script>alert('添加成功！');window.location.href='bursary.do?flag=getBursaryAll';</script>");
		} else {
			out.print("<script>alert('添加失败！');window.location.href='bursary.do?flag=getBursaryAll';</script>");
		}

	}

	// 修改库房
	public void updateBursary(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		BursaryForm bf = (BursaryForm) form;
		Bursary bursary = this.bursaryService.getBursaryId(bf.getBursaryId());// 通过id查询出来
		bursary.setBursaryName(bf.getBursaryName());// 修改库房的名称
		bursary.setBursaryDefinedTwo(bf.getBursaryDefinedTwo());// 修改库房的位置
		bursary.setBursaryMaxWeight(bf.getBursaryMaxWeight());// 修改库房的最大容量（吨）
		bursary.setBursaryRemark(bursary.getBursaryRemark() + ","
				+ bf.getBursaryRemark());// 修改备注
		boolean ok = this.bursaryService.update(bursary);
		if (ok) {
			this.loginLogService.updateLogin(
					request.getSession().getAttribute("loginlogId").toString(),
					"修改库房" + bursary.getBursaryId());// 将这一操作保存在日志表中
			out.print("<script>alert('修改成功！');window.location.href='bursary.do?flag=getBursaryAll';</script>");
		} else {
			out.print("<script>alert('修改成功！');window.location.href='bursary.do?flag=getBursaryAll';</script>");
		}

	}

	// 查看库房
	public ActionForward getBursaryAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<Bursary> blist = this.bursaryService.getAll();// 查询全部的库房
		request.getSession().setAttribute("bursaryList", blist);// 保存到request中
		PrintWriter out=response.getWriter();
		JSONArray array=new JSONArray();
		if(blist.size()<=0){
			JSONObject obj=new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		for(int i=0; i<blist.size();i++){
			JSONObject obj=new JSONObject();
			obj.put("result", "notnull");
			obj.put("id", blist.get(i).getBursaryId());
			obj.put("kuqu", blist.get(i).getBursaryName());
			obj.put("miaoshu", blist.get(i).getBursaryDefinedTwo());
			obj.put("beizhu", blist.get(i).getBursaryRemark());
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
		return null;
	}

	// 停用库房
	public void stopBursary(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		Integer id = Integer.parseInt(request.getParameter("id"));
		Bursary b = bursaryService.getBursaryId(id);
		b.setBursaryDefinedOne("0");
		boolean ok = bursaryService.update(b);
		if (ok) {
			this.loginLogService.updateLogin(
					request.getSession().getAttribute("loginlogId").toString(),
					"修改库房" + b.getBursaryId());// 将这一操作保存在日志表中
			out.print("<script>alert('停用成功！');window.location.href='bursary.do?flag=getBursaryAll';</script>");
		} else {
			out.print("<script>alert('停用成功！');window.location.href='bursary.do?flag=getBursaryAll';</script>");
		}

	}

	// 查看库房
	public ActionForward getKuQuDaiChuLi(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<Bursary> blist = this.bursaryService.getAll();// 查询全部的库房
		PrintWriter out = response.getWriter();
		JSONArray array = new JSONArray();
		for (int i = 0; i < blist.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("id", blist.get(i).getBursaryId());// 保存库区id
			obj.put("name", blist.get(i).getBursaryName());// 保存库区名称
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
		// 返回到对应的页面
		return null;
	}

}
