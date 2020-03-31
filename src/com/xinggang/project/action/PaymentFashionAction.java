package com.xinggang.project.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.xinggang.project.entity.PaymentFashion;
import com.xinggang.project.form.PaymentFashionForm;
import com.xinggang.project.service.LoginLogService;
import com.xinggang.project.service.PaymentFashionService;

/**
 * 支付方式类action
 * 
 * @author Administrator
 * 
 */
public class PaymentFashionAction extends DispatchAction {

	// 支付方式service
	private PaymentFashionService paymentFashionService;
	// 登录日志表service
	private LoginLogService loginLogService;

	public void setLoginLogService(LoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}

	public void setPaymentFashionService(
			PaymentFashionService paymentFashionService) {
		this.paymentFashionService = paymentFashionService;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return super.execute(mapping, form, request, response);
	}

	// 添加支付方式
	public void savePaymentFashion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		boolean ok = false;
		PaymentFashionForm pff = (PaymentFashionForm) form;
		List<PaymentFashion> pflist = this.paymentFashionService.getAll();
		List<PaymentFashion> fapiao = pflist;

		for (int i = 0; i < fapiao.size(); i++) {
			for (int j = 0; j < pflist.size(); j++) {
				if (fapiao.get(i).toString()
						.equals(pflist.get(j).getPfashionReceipt())
						&& i != j) {
					fapiao.remove(i);
					continue;
				}
			}
		}
		if (fapiao.size() <= 0) {// 如果没有数据那么就进行增加
			PaymentFashion paymentFashion = new PaymentFashion();
			paymentFashion.setPfashionName(pff.getPfashionName());// 添加支付方式
			paymentFashion.setPfashionDefinedOne("1");// 是否停用，默认不停用为1
			paymentFashion.setPfashionRemark(pff.getPfashionRemark());// 添加备注
			ok = this.paymentFashionService.save(paymentFashion);
		} else {
			for (int i = 0; i < fapiao.size(); i++) {
				PaymentFashion paymentFashion = new PaymentFashion();
				paymentFashion.setPfashionName(pff.getPfashionName());// 添加支付方式
				paymentFashion.setPfashionDefinedOne("1");// 是否停用，默认不停用为1
				paymentFashion.setPfashionReceipt(fapiao.get(i)
						.getPfashionReceipt());// 添加发票类型
				paymentFashion.setPfashionRemark(pff.getPfashionRemark());// 添加备注
				ok = this.paymentFashionService.save(paymentFashion);
			}
		}

		if (ok) {
			this.loginLogService.updateLogin(
					request.getSession().getAttribute("loginlogId").toString(),
					"添加支付方式");// 将这一操作记录在日志表中
			out.print("<script>alert('添加成功！');window.location.href='paymentFashion.do?flag=getAll';</script>");
		} else {
			out.print("<script>alert('添加失败！');window.location.href='paymentFashion.do?flag=getAll';</script>");
		}

	}

	// 添加发票类型
	public void TianJiaFaPiao(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		PaymentFashionForm pff = (PaymentFashionForm) form;
		if (pff != null) {
			List<PaymentFashion> pflist = this.paymentFashionService.getAll();
			List<PaymentFashion> zhifu = pflist;
			boolean ok = false;
			System.out.println(zhifu.size()
					+ "      6666666666666666666666666666666666");
			for (int i = 0; i < zhifu.size(); i++) {
				for (int j = 0; j < pflist.size(); j++) {
					System.out.println(zhifu.get(i).toString());
					if (pflist.get(j).getPfashionName() == null) {
						zhifu.remove(i);
						continue;
					}
					if (zhifu.get(i).getPfashionName()
							.equals(pflist.get(j).getPfashionName())
							&& i != j) {
						System.out.println("i:" + i + ",j:" + j);
						zhifu.remove(i);
						break;
					}
				}
			}

			System.out.println(zhifu.size()
					+ "      77777777777777777777777777777777");
			if (zhifu.size() <= 0) {// 如果没有数据那么就进行增加
				PaymentFashion paymentFashion = new PaymentFashion();
				paymentFashion.setPfashionReceipt(pff.getPfashionReceipt());// 添加发票类型
				paymentFashion.setPfashionDefinedOne("1");// 是否停用，默认不停用为1
				paymentFashion.setPfashionRemark(pff.getPfashionRemark());// 添加备注
				ok = this.paymentFashionService.save(paymentFashion);
			} else {
				for (int i = 0; i < zhifu.size(); i++) {
					PaymentFashion paymentFashion = new PaymentFashion();
					paymentFashion.setPfashionName(zhifu.get(i)
							.getPfashionName());// 添加支付方式
					paymentFashion.setPfashionReceipt(pff.getPfashionReceipt());// 添加发票类型
					paymentFashion.setPfashionDefinedOne("1");// 是否停用，默认不停用为1
					paymentFashion.setPfashionRemark(pff.getPfashionRemark());// 添加备注
					ok = this.paymentFashionService.save(paymentFashion);
				}
			}
			if (ok) {
				this.loginLogService.updateLogin(request.getSession()
						.getAttribute("loginlogId").toString(), "添加发票类型");// 将这一操作记录在日志表中
				out.print("<script>alert('添加成功！');window.location.href='paymentFashion.do?flag=getAllTwo';</script>");
			} else {
				out.print("<script>alert('添加失败！');window.location.href='paymentFashion.do?flag=getAllTwo';</script>");
			}
		} else {
			out.print("<script>alert('添加失败！');window.location.href='paymentFashion.do?flag=getAllTwo';</script>");
		}

	}

	// 修改支付方式
	public void updatePaymentFashion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		PaymentFashionForm pff = (PaymentFashionForm) form;
		if (pff != null) {

			boolean ok = false;
			List<PaymentFashion> pflist = this.paymentFashionService.getAll();// 查询所有

			PaymentFashion paymentFashion = this.paymentFashionService
					.getPaymentFashionId(pff.getPfashionId());// 通过id查询

			for (int i = 0; i < pflist.size(); i++) {
				if (pflist.get(i).getPfashionName()
						.equals(pff.getPfashionName())) {

					pflist.get(i).setPfashionName(pff.getPfashionName());// 修改支付方式
					pflist.get(i).setPfashionRemark(
							paymentFashion.getPfashionRemark()
									+ pff.getPfashionRemark());// 添加备注

					ok = this.paymentFashionService.update(pflist.get(i));
				}
			}

			if (ok) {
				this.loginLogService.updateLogin(request.getSession()
						.getAttribute("loginlogId").toString(), "修改支付方式");// 将这一操作记录在日志表中
				out.print("<script>alert('修改成功！');window.location.href='paymentFashion.do?flag=getAll';</script>");
			} else {
				out.print("<script>alert('修改失败！');window.location.href='paymentFashion.do?flag=getAll';</script>");
			}
		} else {
			out.print("<script>alert('修改失败！');window.location.href='paymentFashion.do?flag=getAll';</script>");
		}

	}

	// 修改票据类型
	public void updateFaPiao(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		PaymentFashionForm pff = (PaymentFashionForm) form;
		if (pff != null) {

			boolean ok = false;
			List<PaymentFashion> pflist = this.paymentFashionService.getAll();// 查询所有

			PaymentFashion paymentFashion = this.paymentFashionService
					.getPaymentFashionId(pff.getPfashionId());// 通过id查询

			for (int i = 0; i < pflist.size(); i++) {
				if (pflist.get(i).getPfashionReceipt()
						.equals(pff.getPfashionReceipt())) {

					pflist.get(i).setPfashionReceipt(pff.getPfashionReceipt());// 修改支付方式
					pflist.get(i).setPfashionRemark(
							paymentFashion.getPfashionRemark()
									+ pff.getPfashionRemark());// 添加备注

					ok = this.paymentFashionService.update(pflist.get(i));
				}
			}

			if (ok) {
				this.loginLogService.updateLogin(request.getSession()
						.getAttribute("loginlogId").toString(), "修改发票类型");// 将这一操作记录在日志表中
				out.print("<script>alert('修改成功！');window.location.href='paymentFashion.do?flag=getAllTwo';</script>");
			} else {
				out.print("<script>alert('修改失败！');window.location.href='paymentFashion.do?flag=getAllTwo';</script>");
			}
		} else {
			out.print("<script>alert('修改失败！');window.location.href='paymentFashion.do?flag=getAllTwo';</script>");
		}

	}

	// 查看支付方式和发票类型
	public ActionForward getAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		List<PaymentFashion> pflist = this.paymentFashionService.getAll();

		// 筛选重复的支付方式名称
		List<PaymentFashion> pflists = pflist;
		for (int i = 0; i < pflist.size(); i++) {
			for (int j = 0; j < pflists.size(); j++) {
				if (pflist.get(i).getPfashionName() == null) {
					pflist.remove(i);
					i--;
					break;
				}
				if (pflist.get(i).getPfashionName()
						.equals(pflists.get(j).getPfashionName())
						&& i != j) {
					pflist.remove(i);
					i--;
					break;
				}
			}
		}
		request.setAttribute("pflist", pflist);// 保存到request中

		PrintWriter out=response.getWriter();
		JSONArray array=new JSONArray();
		if(pflist.size()<=0){
			JSONObject obj=new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		for(int i=0; i<pflist.size();i++){
			JSONObject obj=new JSONObject();
			obj.put("result", "notnull");
			obj.put("id", pflist.get(i).getPfashionId());
			obj.put("payment", pflist.get(i).getPfashionName());
			obj.put("remark", pflist.get(i).getPfashionRemark());
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
		return null;
	}

	// 查看支付方式和发票类型
	public ActionForward getAllpiaoju(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		List<PaymentFashion> pflist = this.paymentFashionService.getAll();

		// 筛选重复的票据类型名称
		List<PaymentFashion> pflists = pflist;
		for (int i = 0; i < pflist.size(); i++) {
			for (int j = 0; j < pflists.size(); j++) {
				if (pflist.get(i).getPfashionReceipt() == null) {
					pflist.remove(i);
					i--;
					break;
				}
				if (pflist.get(i).getPfashionReceipt()
						.equals(pflists.get(j).getPfashionReceipt())
						&& i != j) {
					pflist.remove(i);
					i--;
					break;
				}
			}
		}
		
		System.out.println("票据类型");
		request.setAttribute("pflist", pflist);// 保存到request中
		PrintWriter out=response.getWriter();
		JSONArray array=new JSONArray();
		if(pflist.size()<=0){
			JSONObject obj=new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		for(int i=0; i<pflist.size();i++){
			JSONObject obj=new JSONObject();
			obj.put("result", "notnull");
			obj.put("id", pflist.get(i).getPfashionId());
			obj.put("bill", pflist.get(i).getPfashionReceipt());
			obj.put("remark", pflist.get(i).getPfashionRemark());
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
		return null;

		
	}

	// 查看支付方式和发票类型
	public ActionForward getAllTwo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		List<PaymentFashion> pflist = this.paymentFashionService.getAll();

		// 筛选重复的支付方式名称

		List<PaymentFashion> zhifu = pflist;
		for (int i = 0; i < zhifu.size(); i++) {
			for (int j = 0; j < pflist.size(); j++) {
				System.out.println("a:" + zhifu.get(i).getPfashionReceipt()
						+ ",b:" + pflist.get(j).getPfashionReceipt());
				if (zhifu.get(i).getPfashionReceipt() == null) {
					zhifu.remove(i);
					i--;
					break;
				}

				if (zhifu.get(i).getPfashionReceipt()
						.equals(pflist.get(j).getPfashionReceipt())
						&& i != j) {
					System.out.println("i:" + i + ",j:" + j);
					zhifu.remove(i);
					i--;
					break;
				}
			}
		}
		request.setAttribute("pflist", zhifu);// 保存到request中

		// 返回到对应的页面
		return mapping.findForward("getAllTwo");
	}

	// 当过户收费的时候查询的对应的的支付方式和票据类型
	public ActionForward getGuoHuQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PaymentFashionForm pff = (PaymentFashionForm) form;
		String pay = request.getParameter("ff") == null ? "" : request
				.getParameter("ff");
		JSONArray array = new JSONArray();
		PrintWriter out = response.getWriter();
		// 查询支付方式
		if (pay.equals("zhifu")) {
			List<PaymentFashion> pflist = this.paymentFashionService.getAll();
			List<PaymentFashion> peilist = new ArrayList<PaymentFashion>();
			peilist = pflist;
			for (int i = 0; i < pflist.size(); i++) {
				for (int j = 0; j < peilist.size(); j++) {
					if (pflist.get(i).getPfashionName() == null) {
						pflist.remove(i);
						i--;
						break;
					}
					if (pflist.get(i).getPfashionName()
							.equals(peilist.get(j).getPfashionName())
							&& i != j) {
						pflist.remove(i);
						i--;
						break;
					}
				}
			}
			if (pflist.size() <= 0) {
				JSONObject obj = new JSONObject();
				obj.put("result", "null");// 保存支付方式
				array.add(obj);
				out.print(array.toString());
				out.flush();
				out.close();
				return null;
			}
			for (int i = 0; i < pflist.size(); i++) {
				JSONObject obj = new JSONObject();
				obj.put("zhifu", pflist.get(i).getPfashionName());// 保存支付方式
				obj.put("result", "notnull");
				array.add(obj);
			}
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}

		// 查询票据类型，通过支付方式的名称查询，并且不是为空的票据类型，
		if (pay.equals("piaoju")) {
			List<PaymentFashion> pflist = this.paymentFashionService
					.getMingcheng(pff.getPfashionName());// 通过支付方式的名称查询

			List<PaymentFashion> peilist = new ArrayList<PaymentFashion>();

			peilist = pflist;
			for (int i = 0; i < pflist.size(); i++) {
				for (int j = 0; j < peilist.size(); j++) {
					if (pflist.get(i).getPfashionReceipt() == null) {
						pflist.remove(i);
						i--;
						break;
					}
					if (pflist.get(i).getPfashionReceipt()
							.equals(peilist.get(j).getPfashionReceipt())
							&& i != j) {
						pflist.remove(i);
						i--;
						break;
					}
				}
			}
			if (pflist.size() <= 0) {
				JSONObject obj = new JSONObject();
				obj.put("result", "null");// 保存支付方式
				array.add(obj);
				out.print(array.toString());
				out.flush();
				out.close();
				return null;
			}
			for (int i = 0; i < pflist.size(); i++) {
				JSONObject obj = new JSONObject();
				obj.put("zhifuid", pflist.get(i).getPfashionId());// 保存支付方式id
				obj.put("piaoju", pflist.get(i).getPfashionReceipt());// 保存票据类型名称
				obj.put("result", "notnull");
				array.add(obj);
			}
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		return null;
	}
}
