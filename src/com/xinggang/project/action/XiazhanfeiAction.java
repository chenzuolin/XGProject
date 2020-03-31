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

import com.xinggang.project.entity.InteriorUser;
import com.xinggang.project.entity.Xiazhanfei;
import com.xinggang.project.form.XiazhanfeiForm;
import com.xinggang.project.service.ClientService;
import com.xinggang.project.service.XiazhanfeiService;
import com.xinggang.project.tools.PresentTime;

public class XiazhanfeiAction extends DispatchAction {

	// 下站费service
	private XiazhanfeiService xiazhanfeiService;
	// 时间工具类
	private PresentTime pt = new PresentTime();
	// 客户service
	private ClientService clientService;

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public void setXiazhanfeiService(XiazhanfeiService xiazhanfeiService) {
		this.xiazhanfeiService = xiazhanfeiService;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return super.execute(mapping, form, request, response);
	}

	// 添加下站费
	public ActionForward SaveXiaZhan(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XiazhanfeiForm xf = (XiazhanfeiForm) form;
		String message = "";
		if (xf != null) {
			Xiazhanfei xz = new Xiazhanfei();
			xz.setClientByXzzhuanchuclient(this.clientService.getClientId(xf
					.getClientByXzzhuanchuclient()));// 保存出库的客户
			if (xf.getClientByXzzhuanruclient() != null
					&& xf.getClientByXzzhuanruclient().equals("") == false) {
				xz.setClientByXzzhuanruclient(this.clientService.getClientId(xf
						.getClientByXzzhuanruclient()));// 保存转入的客户
			}
			xz.setXzweight(xf.getXzweight());// 保存出库的重量
			xz.setXzdanjia(xf.getXzdanjia());// 保存下站费的单价
			xz.setXzcount(xf.getXzcount());// 保存下站费的合计
			xz.setXzzhifu(xf.getXzzhifu());// 保存下站费的支付方式
			xz.setXzpiaoju(xf.getXzpiaoju());// 保存下站费的票据类型
			InteriorUser iu = (InteriorUser) request.getSession().getAttribute(
					"iulist");// 获得登录人的类
			xz.setXzshoufeiren(iu.getIuserName());// 保存收费人
			xz.setXzshoufeitime(pt.getTimes());// 保存收费的时间
			xz.setXzdefinedone(xf.getXzdefinedone());// 保存结算方式

			if (xf.getXzdefinedone().equals("月结")) {
				xz.setXzzhuangtai("未收费");
			} else {
				xz.setXzzhuangtai("已收费");
			}
			xz.setXzremark(xf.getXzremark());// 保存下站费备注
			xz.setXzdefinedthree(xf.getXzdefinedthree());// 保存业务类型
			xz.setXadefinedtwo(xf.getXadefinedtwo());// 保存订单号
			xz.setXadefinedfour(xf.getXadefinedfour());// 设置实收费用
			xz.setXadefinedfive(xf.getXadefinedfive());// 设置操作订单编号
			boolean ok = this.xiazhanfeiService.save(xz);
			if (ok) {
				message = "提交成功！";
			} else {
				message = "提交失败！";
			}
		} else {
			message = "提交失败！";
		}
		PrintWriter out = response.getWriter();
		out.print("<script>alert('" + message
				+ "');window.history.go(-1);</script>");
		return null;
	}

	// 查询下站费，通过时间，和客户的简称进行模糊的查询并且还有结算方式
	public ActionForward getXiaZhanAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XiazhanfeiForm xzf = (XiazhanfeiForm) form;

		// 返回json格式
		JSONArray array = new JSONArray();
		// 输出
		PrintWriter out = response.getWriter();
		// 如果当开始时间为null的时候，
		if (xzf.getBegin() == null || xzf.getBegin().equals("")) {
			xzf.setBegin(pt.getNowJianYi());
		} else {
			xzf.setBegin(xzf.getBegin());
		}
		if (xzf.getFinish() == null || xzf.getFinish().equals("")) {
			xzf.setFinish(pt.getTimes());
		} else {
			xzf.setFinish(xzf.getFinish());
		}
		if (xzf.getClientName() == null) {
			xzf.setClientName("");
		}
		if (xzf.getJiesuan() == null) {
			xzf.setJiesuan("");
		}
		//收费人
		String shoufeiren = "";
		if(request.getParameter("shoufeiren")!=null){
			shoufeiren = request.getParameter("shoufeiren").toString();
		}
		int pageNow = 1;
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}

		int pageCount = this.xiazhanfeiService.getAllByPageCount(
				xzf.getClientName(), xzf.getBegin(), xzf.getFinish(),
				xzf.getJiesuan(), 15,shoufeiren);
		if (pageNow >= pageCount) {
			pageNow = pageCount;
		}
		if (pageNow <= 1) {
			pageNow = 1;
		}

		List<Xiazhanfei> xzlist = this.xiazhanfeiService.getAllByPage(
				xzf.getClientName(), xzf.getBegin(), xzf.getFinish(),
				xzf.getJiesuan(), pageNow, 15,shoufeiren);

		if (xzlist.size() <= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}

		for (int i = 0; i < xzlist.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("xzbianhao", xzlist.get(i).getXzid());// 下站费编号
			obj.put("zongbianhao", xzlist.get(i).getXadefinedtwo());// 保存订单的总编号
			obj.put("caozuobianhao", xzlist.get(i).getXadefinedfive());// 保存操作订单的编号
			obj.put("zhuanchu",
					(xzlist.get(i).getClientByXzzhuanchuclient() == null ? "无"
							: xzlist.get(i).getClientByXzzhuanchuclient()
									.getClientAbbreviation()));// 保存转出客户
			obj.put("zhuanru", xzlist.get(i).getClientByXzzhuanruclient()
					.getClientAbbreviation());// 保存转入客户
			obj.put("type", xzlist.get(i).getXzdefinedthree());// 保存下站类型
			obj.put("weight", xzlist.get(i).getXzweight());// 保存下站重量
			obj.put("danjia", xzlist.get(i).getXzdanjia());// 下站费单价
			obj.put("yingshou", xzlist.get(i).getXzcount());// 下站费应收费用
			obj.put("shoufeiren", xzlist.get(i).getXzshoufeiren());// 收费人
			obj.put("shoufeitime", xzlist.get(i).getXzshoufeitime());// 收费时间
			obj.put("shishou", xzlist.get(i).getXadefinedfour());// 实收费用
			obj.put("jiesuan", xzlist.get(i).getXzdefinedone());// 结算范式
			obj.put("zhifu", xzlist.get(i).getXzzhifu());// 支付方式
			obj.put("piaoju", xzlist.get(i).getXzpiaoju());// 票据类型
			obj.put("zhuangtai", xzlist.get(i).getXzzhuangtai());// 状态，收费或者是未收费
			obj.put("remark", xzlist.get(i).getXzremark());// 备注
			obj.put("result", "notnull");
			obj.put("pageNow", pageNow);
			obj.put("pageCount", pageCount);
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();

		return null;
	}

	// 进行修改下站费
	public ActionForward UpdateXiaZhan(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XiazhanfeiForm xf = (XiazhanfeiForm) form;
		String message = "";
		if (xf != null) {
			Xiazhanfei xz = this.xiazhanfeiService.getXiazhangfei(xf.getXzid());// 通过编号查询

			xz.setXzdanjia(xf.getXzdanjia());// 保存下站费的单价
			xz.setXzcount(xf.getXzcount());// 保存下站费的合计
			xz.setXzzhifu(xf.getXzzhifu());// 保存下站费的支付方式
			xz.setXzpiaoju(xf.getXzpiaoju());// 保存下站费的票据类型
			xz.setXzdefinedone(xf.getXzdefinedone());// 保存结算方式

			if (xf.getXzdefinedone().equals("月结")) {
				xz.setXzzhuangtai("未收费");
			} else {
				xz.setXzzhuangtai("已收费");
			}
			String remark = "";
			if (xz.getXzremark() != null) {
				remark += xz.getXzremark();
			}
			remark += "," + xf.getXzremark();
			xz.setXzremark(remark);// 保存下站费备注
			xz.setXadefinedfour(xf.getXadefinedfour());// 设置实收费用
			boolean ok = this.xiazhanfeiService.update(xz);
			if (ok) {
				message = "修改成功！";
			} else {
				message = "修改失败！";
			}
		} else {
			message = "修改失败！";
		}
		PrintWriter out = response.getWriter();
		out.print("<script>alert('"
				+ message
				+ "');document.location.href='/XGProject/caiwu-page/xiazhanfeijilu.jsp';</script>");
		return null;
	}
}
