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

import com.xinggang.project.entity.Client;
import com.xinggang.project.entity.ClientGoods;
import com.xinggang.project.entity.Goods;
import com.xinggang.project.entity.InteriorUser;
import com.xinggang.project.entity.ShiftStock;
import com.xinggang.project.entity.ShiftStockSeed;
import com.xinggang.project.form.ShiftStockForm;
import com.xinggang.project.service.ClientGoodsService;
import com.xinggang.project.service.ClientService;
import com.xinggang.project.service.GoodsService;
import com.xinggang.project.service.LoginLogService;
import com.xinggang.project.service.ShiftStockSeedService;
import com.xinggang.project.service.ShiftStockService;

/**
 * 过户总订单类action
 * 
 * @author Administrator
 * 
 */
public class ShiftStockAction extends DispatchAction {
	// 过户总订单service
	private ShiftStockService shiftStockService;
	// 登录日志表service
	private LoginLogService loginLogService;
	// 子订单service
	private ShiftStockSeedService shiftStockSeedService;
	// 客户货物库存
	private ClientGoodsService clientGoodsService;
	// 客户service
	private ClientService clientService;
	// 货物service
	private GoodsService goodsService;

	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public void setClientGoodsService(ClientGoodsService clientGoodsService) {
		this.clientGoodsService = clientGoodsService;
	}

	public void setShiftStockSeedService(
			ShiftStockSeedService shiftStockSeedService) {
		this.shiftStockSeedService = shiftStockSeedService;
	}

	public void setLoginLogService(LoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}

	public void setShiftStockService(ShiftStockService shiftStockService) {
		this.shiftStockService = shiftStockService;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return super.execute(mapping, form, request, response);
	}

	// 发起过户之前的查询
	// 查询对应的转出客户
	public ActionForward QueryZhuanChu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		/* 设置响应头允许ajax跨域访问 */
		response.setHeader("Access-Control-Allow-Origin", "*");
		/* 星号表示所有的异域请求都可以接受， */
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");

		ShiftStockForm ssf = (ShiftStockForm) form;
		List<Client> clist = this.clientService.getAll();
		String ff = request.getParameter("ff");
		JSONArray array = new JSONArray();
		PrintWriter out = response.getWriter();
		if (ff != null) {
			if (ff.equals("zhuanchu")) {
				if (clist.size() <= 0) {
					return null;
				}
				for (Client c : clist) {
					JSONObject obj = new JSONObject();
					obj.put("id", c.getClientId());// 保存客户的编号
					obj.put("jiancheng", c.getClientAbbreviation());// 保存客户的简称
					obj.put("sign", c.getClientSign());// 保存客户的助记符
					array.add(obj);
				}
				out.print(array.toString());
				out.flush();
				out.close();
				return null;
			}

			if (ff.equals("zhuanru")) {
				if (clist.size() <= 1) {
					return null;
				}
				for (Client c : clist) {
					if (!c.getClientId().equals(
							ssf.getClientBySstockShiftToFirm())) {
						JSONObject obj = new JSONObject();
						obj.put("id", c.getClientId());// 保存客户的编号
						obj.put("jiancheng", c.getClientAbbreviation());// 保存客户的简称
						array.add(obj);
					}
				}
				out.print(array.toString());
				out.flush();
				out.close();
				return null;
			}
		}

		return null;
	}

	// 发起过户
	public ActionForward saveShiftStock(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ShiftStockForm ssf = (ShiftStockForm) form;
		String faqiren = "";
		if(request.getSession().getAttribute("iulist")!=null){
			InteriorUser iu = (InteriorUser) request.getSession().getAttribute("iulist");// 对应的内部登录人
			faqiren = iu.getIuserName();
		}else{
			faqiren = this.clientService.getClientId(ssf.getClientBySstockClientId()).getClientAbbreviation();
		}
		if (ssf != null) {
			boolean ok = this.shiftStockService.saveShiftStock(ssf,faqiren);
			if (ok) {
				this.loginLogService.updateLogin(request.getSession()
						.getAttribute("loginlogId").toString(), "发起过户");// 将这一操作记录在对应的日志表中
				request.getSession().setAttribute("saveShiftStock", "提交成功！");
			} else {
				request.getSession().setAttribute("saveShiftStock", "提交失败！");
			}
		} else {
			request.getSession().setAttribute("saveShiftStock", "提交失败！");
		}
		PrintWriter out = response.getWriter();
		out.print("<script>alert('"
				+ request.getSession().getAttribute("saveShiftStock")
				+ "');document.location.href='/XGProject/cangchu/page/faqiguohu.jsp'</script>");
		out.flush();
		out.close();
		// 返回到对应的页面
		return null;
	}

	// App发起过户
	public ActionForward saveAppShiftStock(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ShiftStockForm ssf = (ShiftStockForm) form;
		PrintWriter out = response.getWriter();
		if (ssf != null) {
			Goods good = this.goodsService.getOneGoods(
					ssf.getGoodsCategoryId(), ssf.getGoodsStandardId(),
					ssf.getGoodsName(), ssf.getGoodsQualityId(),
					ssf.getGoodsPropertyId(), ssf.getGoodsYieldlyId());
			if (good == null) {
				out.print("notno");
				out.flush();
				out.close();
				return null;
			}
			List<ClientGoods> cglist = this.clientGoodsService
					.getClientGoodsByCG(ssf.getClientBySstockClientId(),
							good.getGoodsId());
			if (ssf.getWeight()[0] > cglist.get(0).getCgoodsWeight()) {
				out.print("转出重量不能大于现有重量！");
				out.flush();
				out.close();
				return null;
			}
			if (this.clientGoodsService.getClientGoodsByCG(
					ssf.getClientBySstockClientId(), good.getGoodsId()) == null
					|| this.clientGoodsService.getClientGoodsByCG(
							ssf.getClientBySstockClientId(), good.getGoodsId())
							.size() <= 0) {
				out.print("notno");
				out.flush();
				out.close();
				return null;
			}

			Integer goods[] = { good.getGoodsId() };
			ssf.setGoodss(goods);
			String faqiren = "";
			if(request.getSession().getAttribute("iulist")!=null){
				InteriorUser iu = (InteriorUser) request.getSession().getAttribute("iulist");// 对应的内部登录人
				faqiren = iu.getIuserName();
			}else{
				faqiren = this.clientService.getClientId(ssf.getClientBySstockClientId()).getClientAbbreviation();
			}
			boolean ok = this.shiftStockService.saveShiftStock(ssf,faqiren);
			if (ok) {
				this.loginLogService.updateLogin(request.getSession()
						.getAttribute("loginlogId").toString(), "发起过户");// 将这一操作记录在对应的日志表中
				request.getSession().setAttribute("saveShiftStock", "提交成功！");
			} else {
				request.getSession().setAttribute("saveShiftStock", "提交失败！");
			}
		} else {
			request.getSession().setAttribute("saveShiftStock", "提交失败！");
		}
		out.print(request.getSession().getAttribute("saveShiftStock"));
		out.flush();
		out.close();
		// 返回到对应的页面
		return null;
	}

	// app手机客户发起过户
	public ActionForward saveShiftStockApp(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ShiftStockForm ssf = (ShiftStockForm) form;
		if (ssf != null) {
			boolean ok = this.shiftStockService.saveShiftStockApp(ssf);
			if (ok) {
				this.loginLogService.updateLogin(request.getSession()
						.getAttribute("loginlogId").toString(), "发起过户");// 将这一操作记录在对应的日志表中
				request.getSession().setAttribute("saveShiftStock", "提交成功！");
			} else {
				request.getSession().setAttribute("saveShiftStock", "提交失败！");
			}
		} else {
			request.getSession().setAttribute("saveShiftStock", "提交失败！");
		}
		PrintWriter out = response.getWriter();
		out.print("<script>alert('"
				+ request.getSession().getAttribute("saveShiftStock")
				+ "');document.location.href='/XGProject/app-web/indent-guo.jsp'</script>");
		out.flush();
		out.close();
		// 返回到对应的页面
		return null;
	}

	// 查询正在审核的订单，在过户子订单中实现
	// 审核通过，子订单中实现
	// 审核未通过，子订单中实现
	// 查询正在收费的订单，子订单中实现
	// 正在收费，子订单中实现
	// 通过客户查询所有的订单，并且以分页的形式显示，通过过户日期降序，子订单中实现
	// 查询所有的订单，可通过客户，货物，订单编号，进行模糊的查询，子订单中实现

	// 客户取消订单

	public ActionForward QuXiaoShiftStock(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ShiftStockForm ssf = (ShiftStockForm) form;
		try {
			if (ssf != null) {
				ShiftStock ss = this.shiftStockService.getShiftStockId(ssf
						.getSstockId());// 通过编号查询
				if (ss != null) {
					List<ShiftStockSeed> sssList = this.shiftStockSeedService
							.getShiftStockId(ss.getSstockId());
					boolean ok = false;
					for (ShiftStockSeed sss : sssList) {
						if (!sss.getSsseedOrderStatus().equals("正在审核")) {
							ok = true;
						}
					}
					if (ok) {
						request.getSession().setAttribute("QuXiaoShiftStock",
								"取消失败！");
					} else {
						for (ShiftStockSeed sss : sssList) {
							ShiftStockSeed ssseed = this.shiftStockSeedService
									.getShiftStockSeedId(sss.getSsseedId());// 通过编号查询
							ssseed.setSsseedOrderStatus("订单取消");
							this.shiftStockSeedService.update(ssseed);
							this.clientGoodsService.ZengjiaKucun(ss
									.getClientBySstockClientId().getClientId(),
									sss.getGoods().getGoodsId(), sss
											.getSsseedWeight(), 0.0);
						}
						this.loginLogService.updateLogin(request.getSession()
								.getAttribute("loginlogId").toString(),
								"取消过户订单" + ssf.getSstockId());// 记录到日志表中
						request.getSession().setAttribute("QuXiaoShiftStock",
								"取消成功！");
					}
				} else {
					request.getSession().setAttribute("QuXiaoShiftStock",
							"取消失败！");
				}
			} else {
				request.getSession().setAttribute("QuXiaoShiftStock", "取消失败！");
			}
		} catch (Exception e) {

		}

		// 返回到对应的页面
		return super.execute(mapping, form, request, response);
	}
}
