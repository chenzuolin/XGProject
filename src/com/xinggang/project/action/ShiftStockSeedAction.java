package com.xinggang.project.action;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.xinggang.project.entity.ClientGoods;
import com.xinggang.project.entity.ShiftStock;
import com.xinggang.project.entity.ShiftStockSeed;
import com.xinggang.project.entity.Xiazhanfei;
import com.xinggang.project.form.ShiftStockSeedForm;
import com.xinggang.project.service.AppMessageService;
import com.xinggang.project.service.ClientGoodsService;
import com.xinggang.project.service.ClientService;
import com.xinggang.project.service.CostTypeService;
import com.xinggang.project.service.GoodsService;
import com.xinggang.project.service.InteriorUserService;
import com.xinggang.project.service.LoginLogService;
import com.xinggang.project.service.PaymentFashionService;
import com.xinggang.project.service.ShiftStockSeedService;
import com.xinggang.project.service.ShiftStockService;
import com.xinggang.project.service.XiazhanfeiService;
import com.xinggang.project.tools.PageRow;
import com.xinggang.project.tools.PresentTime;

/**
 * 挪库类action
 * 
 * @author Administrator
 * 
 */
public class ShiftStockSeedAction extends DispatchAction {
	// 过户子订单service
	private ShiftStockSeedService shiftStockSeedService;
	// 货物service
	@SuppressWarnings("unused")
	private GoodsService goodsService;
	// 登录日志表service
	private LoginLogService loginLogService;
	// 内部人员service
	private InteriorUserService interiorUserService;
	// 客户service
	@SuppressWarnings("unused")
	private ClientService clientService;
	// 客户库存service
	private ClientGoodsService clientGoodsService;
	// 支付方式service
	private PaymentFashionService paymentFashionService;
	// 过户总订单service
	private ShiftStockService shiftStockService;
	// 费用类型service
	private CostTypeService costTypeService;
	// 下站service
	private XiazhanfeiService xiazhanfeiService;
	// app消息service
	private AppMessageService appMessageService;

	public void setXiazhanfeiService(XiazhanfeiService xiazhanfeiService) {
		this.xiazhanfeiService = xiazhanfeiService;
	}

	private PageRow pr = new PageRow();

	// 当前时间工具类
	private PresentTime pt = new PresentTime();

	public void setCostTypeService(CostTypeService costTypeService) {
		this.costTypeService = costTypeService;
	}

	public void setShiftStockService(ShiftStockService shiftStockService) {
		this.shiftStockService = shiftStockService;
	}

	public void setClientGoodsService(ClientGoodsService clientGoodsService) {
		this.clientGoodsService = clientGoodsService;
	}

	public void setAppMessageService(AppMessageService appMessageService) {
		this.appMessageService = appMessageService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public void setInteriorUserService(InteriorUserService interiorUserService) {
		this.interiorUserService = interiorUserService;
	}

	public void setLoginLogService(LoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}

	public void setPaymentFashionService(
			PaymentFashionService paymentFashionService) {
		this.paymentFashionService = paymentFashionService;
	}

	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}

	public void setShiftStockSeedService(
			ShiftStockSeedService shiftStockSeedService) {
		this.shiftStockSeedService = shiftStockSeedService;
	}

	// 查询正在审核的订单
	public ActionForward getJiHuaShiftStock(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ShiftStockSeedForm sssf = (ShiftStockSeedForm) form;
		JSONArray array = new JSONArray();
		PrintWriter out = response.getWriter();

		if (sssf.getTihuohao() == null) {
			sssf.setTihuohao("");
		}
		if (sssf.getHuozhu() == null) {
			sssf.setHuozhu("");
		}
		int pageNow = 1;
		int pageRow = 20;
		if (request.getParameter("pageRow") != null) {
			pageRow = Integer.parseInt(request.getParameter("pageRow")
					.toString());
		}
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}
		int pageCount = this.shiftStockSeedService.getTiHuoDataByPageCount(
				sssf.getTihuohao(), sssf.getHuozhu(), "正在审核", pageRow);
		if (pageNow > pageCount) {
			JSONObject obj = new JSONObject();
			obj.put("max", "maxs");
			out.print(obj.toString());
			out.flush();
			out.close();

			return null;
		}
		if (pageNow >= pageCount) {
			pageNow = pageCount;
		}
		if (pageNow <= 1) {
			pageNow = 1;
		}

		List<ShiftStockSeed> list = this.shiftStockSeedService
				.getTiHuoDataByPage(sssf.getTihuohao(), sssf.getHuozhu(),
						"正在审核", pageNow, pageRow);
		if (list.size() > 0) {
			for (ShiftStockSeed s : list) {
				JSONObject obj = new JSONObject();
				obj.put("id", s.getShiftStock().getSstockId());// 保存过户总订单的id
				obj.put("zid", s.getSsseedId());// 保存过户子订单的id
				obj.put("zhuanchu", s.getShiftStock()
						.getClientBySstockClientId().getClientAbbreviation());// 保存对应的转出单位
				obj.put("zhuanru", s.getShiftStock()
						.getClientBySstockShiftToFirm().getClientAbbreviation());// 保存对应的转入单位
				obj.put("time", s.getShiftStock().getSstockReateTime());// 保存对应的过户发起日期
				obj.put("kehudanhao", s.getShiftStock().getSstockClientNumber());// 保存对应的客户单号
				obj.put("pinlei", s.getGoods().getGoodsCategory()
						.getGoodsCategoryName());// 保存货物品类
				obj.put("mingcheng", s.getGoods().getGoodsName());// 保存货物名称
				obj.put("guige", s.getGoods().getGoodsStandard()
						.getGoodsStandardName());// 保存规格名称
				obj.put("caizhi", s.getGoods().getGoodsQuality()
						.getGoodsQualityName());// 保存对应的 材质名称
				obj.put("shuxing", s.getGoods().getGoodsProperty()
						.getGoodsPropertyName());// 保存对应的属性名称
				obj.put("chandi", s.getGoods().getGoodsYieldly()
						.getGoodsYieldlyName());// 保存对应的产地名称
				obj.put("zhongliang", s.getSsseedWeight());// 保存对应的过户重量
				obj.put("zhuangtai", s.getSsseedOrderStatus());// 保存对应的订单状态
				obj.put("remark", s.getSsseedRemark());// 保存对应的备注
				obj.put("pageNow", pageNow);// 保存当前页
				obj.put("pageCount", pageCount);
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
		}

		// 返回到对应的页面
		return null;
	}

	// 点击要审核的订单，查看详细
	public ActionForward getShenHeXiangXi(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ShiftStockSeedForm sssf = (ShiftStockSeedForm) form;
		if (sssf != null) {
			// 通过订单号进行查询
			ShiftStockSeed sss = this.shiftStockSeedService
					.getShiftStockSeedId(new String(sssf.getSsseedId()
							.getBytes("ISO-8859-1"), "utf-8"));
			// 保存到request中
			request.setAttribute("sss", sss);
		}
		if (request.getParameter("ff") != null
				&& request.getParameter("ff").toString().equals("shoufei")) {
			return mapping.findForward("goShouFeiXiangXi");
		}
		return mapping.findForward("goShenHeXiangXi");
	}

	// 审核通过，
	public ActionForward TongGuoShiftStock(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ShiftStockSeedForm sssf = (ShiftStockSeedForm) form;
		if (sssf != null) {
			ShiftStockSeed sss = this.shiftStockSeedService
					.getShiftStockSeedId(sssf.getSsseedId());// 通过编号查询
			sss.setInteriorUserBySsseedAuditing(this.interiorUserService
					.getInteriorUserId(sssf.getInteriorUserBySsseedAuditing()));// 添加审核人员
			sss.setSsseedAuditingTrue("通过");// 设置为审核通过
			sss.setSsseedOrderStatus("正在收费");// 修改订单状态
			sss.setSsseedAuditingTime(pt.getTimes());// 审核时间
			// ----------------------------------------------------------------计算应收的费用,有问题
			DecimalFormat df = new DecimalFormat("###########0.00");
			double should = sss.getSsseedWeight()
					* this.costTypeService.getGuoHu();
			long shouldint = (long) (should * 1000);
			if (shouldint % 10 != 0 && shouldint % 10 < 5) {
				shouldint = shouldint + 10;
			}

			should = (double) shouldint / 1000;

			sss.setSsseedShouldCost(Double.parseDouble(df.format(should)));// 设置应收费用
			if (sssf.getSsseedRemark() != null
					&& !sssf.getSsseedRemark().equals("")) {
				sss.setSsseedRemark(sss.getSsseedRemark() + ","
						+ sssf.getSsseedRemark());// 添加备注
			}

			boolean ok = this.shiftStockSeedService.update(sss);
			if (ok) {
				this.appMessageService.saveMessage(sss.getShiftStock()
						.getClientBySstockClientId().getClientId(), sss
						.getShiftStock().getClientBySstockShiftToFirm()
						.getClientId(), sss.getShiftStock().getSstockId(),
						sss.getSsseedId(), sss.getGoods().getGoodsName(),
						"您得过户订单已处理完成，转出单位："
								+ sss.getShiftStock()
										.getClientBySstockClientId()
										.getClientAbbreviation()
								+ ",转入单位："
								+ sss.getShiftStock()
										.getClientBySstockShiftToFirm()
										.getClientAbbreviation() + ",客户单号："
								+ sss.getShiftStock().getSstockClientNumber(),
						"过户订单");
				this.loginLogService.updateLogin(request.getSession()
						.getAttribute("loginlogId").toString(), "审核过户订单，通过"
						+ sss.getSsseedId());// 将这一操作记录在日志表中
				// 当审核通过的时候，给相应的转入单位增加相应的库存
				this.clientGoodsService.ZengjiaKucun(sssf
						.getClientBySstockShiftToFirm(), sss.getGoods()
						.getGoodsId(), sss.getSsseedWeight(), 0.0);
				request.getSession().setAttribute("TongGuoShiftStock",
						"审核提交成功！");
			} else {
				request.getSession()
						.setAttribute("TongGuoShiftStock", "审核提交失败");
			}
		} else {
			request.getSession().setAttribute("TongGuoShiftStock", "审核提交失败！");
		}
		PrintWriter out = response.getWriter();
		out.print("<script>alert('"
				+ request.getSession().getAttribute("TongGuoShiftStock")
				+ "');window.history.go(-2);</script>");
		// 返回到对应的页面
		return null;
	}

	// 审核未通过，
	public ActionForward NotTongGuoShiftStock(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ShiftStockSeedForm sssf = (ShiftStockSeedForm) form;
		if (sssf != null) {
			ShiftStockSeed sss = this.shiftStockSeedService
					.getShiftStockSeedId(sssf.getSsseedId());// 通过编号查询
			sss.setInteriorUserBySsseedAuditing(this.interiorUserService
					.getInteriorUserId(sssf.getInteriorUserBySsseedAuditing()));// 添加审核人员
			sss.setSsseedAuditingTrue("未通过");// 设置为审核通过
			sss.setSsseedOrderStatus("审核未通过");// 修改订单状态
			sss.setSsseedAuditingTime(pt.getTimes());// 审核时间
			if (sssf.getSsseedRemark() != null
					&& !sssf.getSsseedRemark().equals("")) {
				sss.setSsseedRemark(sss.getSsseedRemark() + ","
						+ sssf.getSsseedRemark());// 添加备注
			}
			boolean ok = this.shiftStockSeedService.update(sss);
			if (ok) {
				this.loginLogService.updateLogin(request.getSession()
						.getAttribute("loginlogId").toString(), "审核过户订单，未通过"
						+ sss.getSsseedId());// 将这一操作记录在日志表中
				// 当审核未通过的时候，给相应的转出单位增加相应的库存
				this.clientGoodsService.ZengjiaKucun(sssf
						.getClientBySstockClientId(), sss.getGoods()
						.getGoodsId(), sss.getSsseedWeight(), 0.0);// 给相应的转出单位增加相应的重量
				request.getSession().setAttribute("NotTongGuoShiftStock",
						"审核提交成功！");
			} else {
				request.getSession().setAttribute("NotTongGuoShiftStock",
						"审核提交失败");
			}
		} else {
			request.getSession()
					.setAttribute("NotTongGuoShiftStock", "审核提交失败！");
		}
		PrintWriter out = response.getWriter();
		out.print("<script>alert('"
				+ request.getSession().getAttribute("NotTongGuoShiftStock")
				+ "');window.history.go(-2);</script>");
		// 返回到对应的页面
		return null;
	}

	// 查询正在收费的订单
	public ActionForward getTongGuoShiftStock(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ShiftStockSeedForm sssf = (ShiftStockSeedForm) form;
		JSONArray array = new JSONArray();
		PrintWriter out = response.getWriter();

		if (sssf.getTihuohao() == null) {
			sssf.setTihuohao("");
		}
		if (sssf.getHuozhu() == null) {
			sssf.setHuozhu("");
		}
		int pageNow = 1;
		int pageRow = 20;
		if (request.getParameter("pageRow") != null) {
			pageRow = Integer.parseInt(request.getParameter("pageRow")
					.toString());
		}
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}
		int pageCount = this.shiftStockSeedService.getTiHuoDataByPageCount(
				sssf.getTihuohao(), sssf.getHuozhu(), "正在收费", pageRow);
		if (pageNow > pageCount) {
			JSONObject obj = new JSONObject();
			obj.put("max", "maxs");
			out.print(obj.toString());
			out.flush();
			out.close();

			return null;
		}
		if (pageNow >= pageCount) {
			pageNow = pageCount;
		}
		if (pageNow <= 1) {
			pageNow = 1;
		}

		List<ShiftStockSeed> list = this.shiftStockSeedService
				.getTiHuoDataByPage(sssf.getTihuohao(), sssf.getHuozhu(),
						"正在收费", pageNow, pageRow);
		if (list.size() > 0) {
			for (ShiftStockSeed s : list) {
				JSONObject obj = new JSONObject();
				obj.put("id", s.getShiftStock().getSstockId());// 保存过户总订单的id
				obj.put("zid", s.getSsseedId());// 保存过户子订单的id
				obj.put("zhuanchu", s.getShiftStock()
						.getClientBySstockClientId().getClientAbbreviation());// 保存对应的转出单位
				obj.put("zhuanru", s.getShiftStock()
						.getClientBySstockShiftToFirm().getClientAbbreviation());// 保存对应的转入单位
				obj.put("time", s.getShiftStock().getSstockReateTime());// 保存对应的过户发起日期
				obj.put("kehudanhao", s.getShiftStock().getSstockClientNumber());// 保存对应的客户单号
				obj.put("pinlei", s.getGoods().getGoodsCategory()
						.getGoodsCategoryName());// 保存货物品类
				obj.put("mingcheng", s.getGoods().getGoodsName());// 保存货物名称
				obj.put("guige", s.getGoods().getGoodsStandard()
						.getGoodsStandardName());// 保存规格名称
				obj.put("caizhi", s.getGoods().getGoodsQuality()
						.getGoodsQualityName());// 保存对应的 材质名称
				obj.put("shuxing", s.getGoods().getGoodsProperty()
						.getGoodsPropertyName());// 保存对应的属性名称
				obj.put("chandi", s.getGoods().getGoodsYieldly()
						.getGoodsYieldlyName());// 保存对应的产地名称
				obj.put("yingshou", s.getSsseedShouldCost());// 保存应收费费用
				obj.put("zhongliang", s.getSsseedWeight());// 保存对应的过户重量
				obj.put("zhuangtai", s.getSsseedOrderStatus());// 保存对应的订单状态
				obj.put("remark", s.getSsseedRemark());// 保存对应的备注
				obj.put("pageNow", pageNow);// 保存当前页
				obj.put("pageCount", pageCount);
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
		}

		// 返回到对应的页面
		return null;
	}

	// 收费
	public ActionForward ShouFeiShiftStock(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ShiftStockSeedForm sssf = (ShiftStockSeedForm) form;
		PrintWriter out = response.getWriter();
		if (sssf != null) {
			ShiftStockSeed sss = this.shiftStockSeedService
					.getShiftStockSeedId(sssf.getSsseedId());// 通过编号查询
			if (sss != null) {
				boolean shou = false;
				if (sssf.getSsseedRealityCost() != null
						&& sssf.getSsseedClientAccounts().equals("日结")) {
					sss.setSsseedOrderStatus("已收费");
					sss.setSsseedRealityCost(sssf.getSsseedRealityCost());// 设置实收费用
					sss.setInteriorUserBySsseedCollectMan(this.interiorUserService
							.getInteriorUserId(sssf
									.getInteriorUserBySsseedCollectMan()));// 设置收费员
					sss.setSsseedCollectTime(pt.getTimes());// 收费时间
					sss.setSsseedClientAccounts(sssf.getSsseedClientAccounts());// 设置结算的方式
					sss.setPaymentFashion(this.paymentFashionService
							.getPaymentFashionId(sssf.getPaymentFashion()));// 设置支付方式
					sss.setSsseedRemark(sssf.getSsseedRemark());// 设置备注
					shou = true;
				} else {
					sss.setSsseedOrderStatus("未收费");
					sss.setInteriorUserBySsseedCollectMan(this.interiorUserService
							.getInteriorUserId(sssf
									.getInteriorUserBySsseedCollectMan()));// 设置收费员
					sss.setSsseedCollectTime(pt.getTimes());// 收费时间
					sss.setSsseedClientAccounts(sssf.getSsseedClientAccounts());// 设置结算的方式
					sss.setSsseedRemark(sssf.getSsseedRemark());// 设置备注
					shou = false;
				}
				boolean ok = shiftStockSeedService.update(sss);
				if (ok) {
					// 记录在日志表中
					if (shou) {
						this.loginLogService.updateLogin(request.getSession()
								.getAttribute("loginlogId").toString(),
								"收取过户费用，已收取" + sss.getSsseedId());
					} else {
						this.loginLogService.updateLogin(request.getSession()
								.getAttribute("loginlogId").toString(),
								"收取过户费用，未收取" + sss.getSsseedId());
					}
					request.getSession().setAttribute("ShouFeiShiftStock",
							"提交成功！");
					request.setAttribute("ss", sss);
					out.print("<script>alert('"
							+ request.getSession().getAttribute(
									"ShouFeiShiftStock") + "');</script>");
					// return mapping.findForward("goDaYin");
					request.getSession().setAttribute("ShouFeiShiftStock",
							"提交成功！");
				} else {
					request.getSession().setAttribute("ShouFeiShiftStock",
							"提交失败！");
				}
			} else {
				request.getSession().setAttribute("ShouFeiShiftStock", "提交失败！");
			}
		} else {
			request.getSession().setAttribute("ShouFeiShiftStock", "提交失败！");
		}

		out.print("<script>alert('"
				+ request.getSession().getAttribute("ShouFeiShiftStock")
				+ "');window.history.go(-2);</script>");
		return null;
	}

	// 通过客户查询过户的订单
	public ActionForward getClientShiftStock(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ShiftStockSeedForm sssf = (ShiftStockSeedForm) form;

		int pageNow = 1;
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}

		int pageCount = this.shiftStockSeedService.getClientByPageCount(
				sssf.getClientId(), pr.getRow());
		if (pageNow >= pageCount) {
			pageNow = pageCount;
		}
		if (pageNow <= 1) {
			pageNow = 1;
		}

		List<ShiftStockSeed> clientsssList = this.shiftStockSeedService
				.getClientByPageAll(sssf.getClientId(), pageNow, pr.getRow());
		request.setAttribute("clientsssList", clientsssList);// 保存到request中

		// 返回到对应的页面
		return super.execute(mapping, form, request, response);
	}

	// 查询全部过户订单，可以通过，客户，货物，
	public ActionForward getShiftStockAll(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ShiftStockSeedForm sssf = (ShiftStockSeedForm) form;
		if (sssf.getBegin() == null) {
			sssf.setBegin("");
		}
		if (sssf.getFinish() == null || sssf.getFinish().equals("")) {
			sssf.setFinish(pt.getTimes());
		}
		if (sssf.getDanwei() == null) {
			sssf.setDanwei("");
		}
		if (sssf.getDanweizhujifu() == null) {
			sssf.setDanweizhujifu("");
		}
		if (sssf.getJiancheng() == null) {
			sssf.setJiancheng("");
		}
		if (sssf.getGoodsname() == null) {
			sssf.setGoodsname("");
		}
		if (sssf.getGoodszhujifu() == null) {
			sssf.setGoodszhujifu("");
		}
		if (sssf.getKehudanhao() == null) {
			sssf.setKehudanhao("");
		}
		if (sssf.getShiftStock() == null) {
			sssf.setShiftStock("");
		}

		int pageNow = 1;
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}

		int pageCount = this.shiftStockSeedService.getMoHuAllByCount(
				sssf.getBegin(), sssf.getFinish(), sssf.getDanwei(),
				sssf.getDanweizhujifu(), sssf.getJiancheng(),
				sssf.getGoodsname(), sssf.getGoodszhujifu(),
				sssf.getShiftStock(), sssf.getKehudanhao(), 30);

		JSONArray array = new JSONArray();
		PrintWriter out = response.getWriter();
		if (pageNow >= pageCount) {
			pageNow = pageCount;
		}
		if (pageNow <= 1) {
			pageNow = 1;
		}

		List<ShiftStockSeed> ssslist = this.shiftStockSeedService.getMoHuAll(
				sssf.getBegin(), sssf.getFinish(), sssf.getDanwei(),
				sssf.getDanweizhujifu(), sssf.getJiancheng(),
				sssf.getGoodsname(), sssf.getGoodszhujifu(),
				sssf.getShiftStock(), sssf.getKehudanhao(), pageNow, 30);

		if (ssslist.size() <= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		DecimalFormat df = new DecimalFormat("##########0.000");
		if (ssslist.size() > 0) {
			double sum = 0.0;
			for (ShiftStockSeed s : ssslist) {
				JSONObject obj = new JSONObject();
				obj.put("id", s.getShiftStock().getSstockId());// 保存过户总订单编号
				obj.put("zid", s.getSsseedId());// 保存过户子订单的编号
				obj.put("zhuanchu", s.getShiftStock()
						.getClientBySstockClientId().getClientAbbreviation());// 保存转出单位的简称
				obj.put("zhuanru", s.getShiftStock()
						.getClientBySstockShiftToFirm().getClientAbbreviation());// 保存转入单位的简称
				obj.put("huowu", s.getGoods().getGoodsName());// 保存转出的货物的名称
				obj.put("kehudanhao", s.getShiftStock().getSstockClientNumber());// 保存客户单号
				obj.put("zhuangtai", s.getSsseedOrderStatus());// 保存订单状态
				obj.put("faqishijian", s.getShiftStock().getSstockReateTime());// 保存过户的发起时间
				obj.put("zhongliang", s.getSsseedWeight());// 保存过户的重量
				obj.put("jieguo",
						s.getSsseedAuditingTrue() == null ? "无" : s
								.getSsseedAuditingTrue());// 审核结果
				obj.put("pageNow", pageNow);// 保存当前页
				obj.put("pageCount", pageCount);
				obj.put("result", "notnull");
				obj.put("faqiren", s.getShiftStock().getSstockFaQiRen());//订单发起人
				sum += s.getSsseedWeight();// 过户重量
				obj.put("sum", df.format(sum));
				array.add(obj);
			}
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		// 返回到对应的页面
		return null;
	}

	// 当点击订单查询中过户订单查看性情的时候通过过户子订单的id进行查询
	public ActionForward getXiangQing(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ShiftStockSeedForm sssf = (ShiftStockSeedForm) form;
		if (sssf != null) {
			ShiftStockSeed ss = this.shiftStockSeedService
					.getShiftStockSeedId(URLDecoder.decode(sssf.getSsseedId(),
							"UTF-8"));// 通过id进行查询
			// 将查询到的类保存到request中
			request.setAttribute("ss", ss);
		}

		if (request.getParameter("ff") != null) {
			if (request.getParameter("ff").equals("dayin")) {
				return mapping.findForward("goDaYin");
			}
			if (request.getParameter("ff").equals("client")) {
				return mapping.findForward("goClient");
			}
			if (request.getParameter("ff").equals("shoufei")) {
				// 查询对应的下站费
				List<Xiazhanfei> xzlist = this.xiazhanfeiService.getAll();
				request.setAttribute("xzlist", xzlist);
				return mapping.findForward("goShoufei");
			}
		}
		// 返回到对应的页面
		return mapping.findForward("goDDXiangXi");
	}

	// 修改子订单，修改的时候有两种，一种是总订单，一种是子订单
	public ActionForward updateShiftStockSeed(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ShiftStockSeedForm sssf = (ShiftStockSeedForm) form;
		if (sssf != null) {
			ShiftStockSeed sss = this.shiftStockSeedService
					.getShiftStockSeedId(sssf.getSsseedId());// 通过编号查询
			if (sss != null) {
				sss.setSsseedAuditingTrue(sssf.getSsseedAuditingTrue());// 修改审核是否通过
				sss.setSsseedAuditingTime(sssf.getSsseedAuditingTime());// 修改审核的时间
				sss.setSsseedShouldCost(sssf.getSsseedShouldCost());// 修改应收费用
				sss.setSsseedRealityCost(sssf.getSsseedRealityCost());// 修改实收费用
				sss.setSsseedClientAccounts(sssf.getSsseedClientAccounts());// 修改结算方式
				sss.setPaymentFashion(this.paymentFashionService
						.getPaymentFashionId(sssf.getPaymentFashion()));// 修改支付方式
				sss.setSsseedRemark(sss.getSsseedRemark() + ","
						+ sssf.getSsseedRemark());// 修改备注
				boolean ok = this.shiftStockSeedService.update(sss);
				if (ok) {
					this.loginLogService
							.updateLogin(
									request.getSession()
											.getAttribute("loginlogId")
											.toString(), "修改过户子订单信息");// 记录到日志表中
					request.getSession().setAttribute("updateSSSeed", "修改成功！");
				} else {
					request.getSession().setAttribute("updateSSSeed", "修改失败！");
				}
			} else {
				request.getSession().setAttribute("updateSSSeed", "修改失败！");
			}
		} else {
			request.getSession().setAttribute("updateSSSeed", "修改失败！");
		}

		// 返回到对应的页面
		return super.execute(mapping, form, request, response);
	}

	// 删除过户订单
	public ActionForward deleteShiftStockSeed(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ShiftStockSeedForm sssf = (ShiftStockSeedForm) form;
		if (sssf != null) {
			ShiftStockSeed sss = this.shiftStockSeedService
					.getShiftStockSeedId(URLDecoder.decode(sssf.getSsseedId(),
							"UTF-8"));// 通过编号查询
			if (sss != null) {
				boolean ok = this.shiftStockSeedService.delete(sss);
				if (ok) {
					this.loginLogService.updateLogin(request.getSession()
							.getAttribute("loginlogId").toString(), "删除过户订单");// 记录到日志表中
					request.getSession().setAttribute("deleteSSSeed", "删除成功！");
				} else {
					request.getSession().setAttribute("deleteSSSeed", "删除失败！");
				}
			} else {
				request.getSession().setAttribute("deleteSSSeed", "删除失败！");
			}
		} else {
			request.getSession().setAttribute("deleteSSSeed", "删除失败！");
		}

		// 返回到对应的页面
		PrintWriter out = response.getWriter();
		out.print("<script>alert('"
				+ request.getSession().getAttribute("deleteSSSeed")
				+ "');window.history.go(-2);</script>");
		return null;
	}

	// 查询某个客户的货物库存，显示在发起过户的界面
	public ActionForward getKeHuKuCun(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ShiftStockSeedForm sssf = (ShiftStockSeedForm) form;
		String ff = request.getParameter("ff");
		JSONArray array = new JSONArray();
		PrintWriter out = response.getWriter();
		try {
			if (sssf != null) {
				List<ClientGoods> cglist = this.clientGoodsService
						.getClient(sssf.getClientBySstockClientId());
				if (ff != null) {
					// 查询品类的时候调用
					if (ff.equals("pinlei")) {
						if (cglist.size() > 0) {
							for (ClientGoods cg : cglist) {
								JSONObject obj = new JSONObject();
								obj.put("pinleiId", cg.getGoods()
										.getGoodsCategory()
										.getGoodsCategoryId());
								obj.put("pinlei", cg.getGoods()
										.getGoodsCategory()
										.getGoodsCategoryName());// 保存货物的品类
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
					}// 判断为品类的结束

					// 查询货物名称的时候调用
					if (ff.equals("mingcheng")) {
						if (cglist.size() > 0) {
							int num = 0;
							for (ClientGoods cg : cglist) {
								if (cg.getGoods().getGoodsCategory()
										.getGoodsCategoryId()
										.equals(sssf.getPinlei())) {
									JSONObject obj = new JSONObject();
									obj.put("mingcheng", cg.getGoods()
											.getGoodsName());// 保存货物名称
									obj.put("result", "notnull");
									array.add(obj);
									num++;
								}
							}
							if (num == 0) {
								JSONObject obj = new JSONObject();
								obj.put("result", "null");
								array.add(obj);
								out.print(array.toString());
								out.flush();
								out.close();
								return null;
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

					// 查询货物规格的时候调用
					if (ff.equals("guige")) {
						if (cglist.size() > 0) {
							int num = 0;
							for (ClientGoods cg : cglist) {
								if (cg.getGoods().getGoodsName()
										.equals(sssf.getGoodsname())
										&& cg.getGoods().getGoodsCategory()
												.getGoodsCategoryId()
												.equals(sssf.getPinlei())) {

									JSONObject obj = new JSONObject();
									obj.put("guigeId", cg.getGoods()
											.getGoodsStandard()
											.getGoodsStandardId());// 保存货物规格的id
									obj.put("gui", cg.getGoods()
											.getGoodsStandard()
											.getGoodsStandardName());// 保存货物的规格名称
									obj.put("result", "notnull");
									array.add(obj);
									num++;
								}
							}
							if (num == 0) {
								JSONObject obj = new JSONObject();
								obj.put("result", "null");
								array.add(obj);
								out.print(array.toString());
								out.flush();
								out.close();
								return null;
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

					// 查询货物材质的时候调用
					if (ff.equals("caizhi")) {
						if (cglist.size() > 0) {
							int num = 0;
							for (ClientGoods cg : cglist) {
								if (cg.getGoods().getGoodsStandard()
										.getGoodsStandardId()
										.equals(sssf.getGuige())
										&& cg.getGoods().getGoodsName()
												.equals(sssf.getGoodsname())
										&& cg.getGoods().getGoodsCategory()
												.getGoodsCategoryId()
												.equals(sssf.getPinlei())) {
									System.out.println("规格的编号是："
											+ sssf.getGuige()
											+ ",对应的材质的编号是："
											+ cg.getGoods().getGoodsQuality()
													.getGoodsQualityId());
									JSONObject obj = new JSONObject();
									obj.put("caizhiId", cg.getGoods()
											.getGoodsQuality()
											.getGoodsQualityId());// 保存货物材质的id
									obj.put("caizhi", cg.getGoods()
											.getGoodsQuality()
											.getGoodsQualityName());// 保存货物的材质名称
									obj.put("result", "notnull");
									array.add(obj);
									num++;
								}
							}
							if (num == 0) {
								JSONObject obj = new JSONObject();
								obj.put("result", "null");
								array.add(obj);
								out.print(array.toString());
								out.flush();
								out.close();
								return null;
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

					// 查询货物属性的时候调用
					if (ff.equals("shuxing")) {
						if (cglist.size() > 0) {
							int num = 0;
							for (ClientGoods cg : cglist) {
								if (cg.getGoods().getGoodsQuality()
										.getGoodsQualityId()
										.equals(sssf.getCaizhi())
										&& cg.getGoods().getGoodsStandard()
												.getGoodsStandardId()
												.equals(sssf.getGuige())
										&& cg.getGoods().getGoodsName()
												.equals(sssf.getGoodsname())
										&& cg.getGoods().getGoodsCategory()
												.getGoodsCategoryId()
												.equals(sssf.getPinlei())) {

									JSONObject obj = new JSONObject();
									obj.put("shuxingId", cg.getGoods()
											.getGoodsProperty()
											.getGoodsPropertyId());// 保存货物属性的id
									obj.put("shuxing", cg.getGoods()
											.getGoodsProperty()
											.getGoodsPropertyName());// 保存货物的属性名称
									obj.put("result", "notnull");
									array.add(obj);
									num++;
								}
							}
							if (num == 0) {
								JSONObject obj = new JSONObject();
								obj.put("result", "null");
								array.add(obj);
								out.print(array.toString());
								out.flush();
								out.close();
								return null;
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

					// 查询货物产地的时候调用
					if (ff.equals("chandi")) {
						if (cglist.size() > 0) {
							int num = 0;
							for (ClientGoods cg : cglist) {
								if (cg.getGoods().getGoodsProperty()
										.getGoodsPropertyId()
										.equals(sssf.getShuxing())
										&& cg.getGoods().getGoodsQuality()
												.getGoodsQualityId()
												.equals(sssf.getCaizhi())
										&& cg.getGoods().getGoodsStandard()
												.getGoodsStandardId()
												.equals(sssf.getGuige())
										&& cg.getGoods().getGoodsName()
												.equals(sssf.getGoodsname())
										&& cg.getGoods().getGoodsCategory()
												.getGoodsCategoryId()
												.equals(sssf.getPinlei())) {

									JSONObject obj = new JSONObject();
									obj.put("goodsId", cg.getGoods()
											.getGoodsId());// 保存货物的id
									obj.put("chandi", cg.getGoods()
											.getGoodsYieldly()
											.getGoodsYieldlyName());// 保存货物的产地名称
									obj.put("zhongliang", cg.getCgoodsWeight());// 保存重量，输入的重量不能大于此重量
									obj.put("result", "notnull");
									array.add(obj);
									num++;
								}
							}
							if (num == 0) {
								JSONObject obj = new JSONObject();
								obj.put("result", "null");
								array.add(obj);
								out.print(array.toString());
								out.flush();
								out.close();
								return null;
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
				}// ff的非空判断结束
			}// sssf的判断结束
		} catch (Exception e) {
		}
		return null;
	}

	// -------------------app客户查询出库订单

	// 传入当前页数和总页数进行判断
	public int fenye(String pageNows, int pageCount) {
		int pageNow = 1;
		// 如果超过总页数
		if (Integer.parseInt(pageNows) > pageCount) {
			pageNow = pageCount;
		}
		// 点击下一页，则判断是否超过总页数,没有超过总页数
		if (Integer.parseInt(pageNows) > 0
				&& Integer.parseInt(pageNows) <= pageCount) {
			pageNow = Integer.parseInt(pageNows);
		}
		// 点击上一页，如果低于1页，则设为第1页
		if (Integer.parseInt(pageNows) <= 0) {
			pageNow = 1;
		}
		// 返回新的当前页
		return pageNow;
	}

	// app客户查询所有订单
	public void getDingdanApp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		// 返回行数
		PageRow pageRow = new PageRow();

		// 获取页面传入的值
		String s_pageNows = request.getParameter("pageNow");

		int clientId = Integer.parseInt(request.getSession()
				.getAttribute("clientId").toString());
		ShiftStockSeedForm sssf = (ShiftStockSeedForm) form;
		if (sssf.getBegin() == null) {
			sssf.setBegin("");
		} 

		if (sssf.getFinish() == null || sssf.getFinish().equals("")) {
			sssf.setFinish(pt.getTimes());
		} 
		// 获得总页数
		int pageCount = shiftStockSeedService.getNuokuCountAll(clientId,sssf.getBegin(),sssf.getFinish(),
				pageRow.getRow());
		request.setAttribute("pageCount", pageCount);

		// 判断，如果总页数为0，即：没有数据，不让访问数据库，直接return
		if (pageCount == 0) {
			JSONObject obj = new JSONObject();
			obj.put("qingkong", "clean");
			out.print(obj.toString());
			out.flush();
			out.close();
			return;
		}

		int pageNow2 = 1;
		// 获取用户输入页数
		// 当前页
		if (s_pageNows != null) {
			pageNow2 = fenye(s_pageNows, pageCount);
		}

		System.out.println("pageNow2:" + pageNow2);

		List<ShiftStockSeed> shiftList = shiftStockSeedService.getNuokuInfoAll(
				clientId,sssf.getBegin(),sssf.getFinish(), pageNow2, pageRow.getRow());

		JSONArray array = new JSONArray();
		for (ShiftStockSeed c : shiftList) {
			JSONObject obj = new JSONObject();

			obj.put("shiftClientNumber", c.getShiftStock()
					.getSstockClientNumber());// 客户单号
			obj.put("goodsName", c.getGoods().getGoodsName());// 货物名称
			obj.put("goodsStandardName", c.getGoods()// 货物规格
					.getGoodsStandard().getGoodsStandardName());
			obj.put("goodsYieldlyName", c.getGoods()// 货物产地
					.getGoodsYieldly().getGoodsYieldlyName());
			obj.put("goodsCategoryName", c.getGoods().getGoodsCategory()
					.getGoodsCategoryName());// 货物品类
			obj.put("goodsQualityName", c.getGoods().getGoodsQuality()
					.getGoodsQualityName());// 货物材质
			obj.put("shiftDriverTel", c.getShiftStock().getSstockTel());// 联系方式
			// 将子订单id放入隐藏域中的值
			obj.put("seedId", c.getSsseedId());

			obj.put("zongId", c.getShiftStock().getSstockId());

			// 保存页数
			obj.put("pageCount", pageCount);
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();

	}

	// app客户有条件查询订单
	public ActionForward getDingdanAppTj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		// 返回行数
		int pageRow = 10;
		if(request.getParameter("pageRow")!=null){
			pageRow = Integer.parseInt(request.getParameter("pageRow").toString());
		}
		// 获取页面传入的值
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String kehuDanhao = request.getParameter("kehuDanhao");
		String goodsName = request.getParameter("goodsName");
		String s_pageNows = request.getParameter("pageNow");

		int clientId = Integer.parseInt(request.getSession().getAttribute("clientId").toString());

		// 获得总页数
		int pageCount = shiftStockSeedService.getNuokuCount(clientId, "", startTime, endTime, kehuDanhao, goodsName, pageRow);

		request.setAttribute("pageCount", pageCount);

		int pageNow2 = 1;
		// 获取用户输入页数
		// 当前页
		if (s_pageNows != null) {
			pageNow2 = fenye(s_pageNows, pageCount);
		}

		List<ShiftStockSeed> shiftList = shiftStockSeedService.getNuokuInfo(clientId, "", startTime, endTime, kehuDanhao, goodsName, pageNow2, pageRow);

		JSONArray array = new JSONArray();
		// 判断，如果总页数为0，即：没有数据，不让访问数据库，直接return
		if (shiftList.size() <= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		for (ShiftStockSeed c : shiftList) {
			JSONObject obj = new JSONObject();
			
			obj.put("result","notnull");
			obj.put("id", c.getShiftStock().getSstockId());//保存总订单编号
			obj.put("zid", c.getSsseedId());//保存子订单编号
			obj.put("zhuanchu", c.getShiftStock().getClientBySstockClientId().getClientAbbreviation());//保存转出单位
			obj.put("zhuanru", c.getShiftStock().getClientBySstockShiftToFirm().getClientAbbreviation());//保存转入单位
			obj.put("huowu", c.getGoods().getGoodsName());//保存货物名称
			obj.put("kehudanhao", c.getShiftStock().getSstockClientNumber());//保存客户单号
			obj.put("faqishijian", c.getShiftStock().getSstockReateTime());//保存发起时间
			obj.put("zhongliang", c.getSsseedWeight());//保存重量
			obj.put("jieguo", c.getSsseedAuditingTrue() == null ? "无" : c.getSsseedAuditingTrue());//保存审核结果
			obj.put("zhuangtai", c.getSsseedOrderStatus());//保存订单状态
			
			obj.put("shiftClientNumber", c.getShiftStock().getSstockClientNumber());// 客户单号
			obj.put("goodsName", c.getGoods().getGoodsName());// 货物名称
			obj.put("goodsStandardName", c.getGoods().getGoodsStandard().getGoodsStandardName());// 货物规格
			obj.put("goodsYieldlyName", c.getGoods().getGoodsYieldly().getGoodsYieldlyName());// 货物产地
			obj.put("goodsCategoryName", c.getGoods().getGoodsCategory().getGoodsCategoryName());// 货物品类
			obj.put("goodsQualityName", c.getGoods().getGoodsQuality().getGoodsQualityName());// 货物材质
			obj.put("shiftDriverTel", c.getShiftStock().getSstockTel());// 联系方式
			// 将子订单id放入隐藏域中的值
			obj.put("seedId", c.getSsseedId());
			obj.put("zongId", c.getShiftStock().getSstockId());
			// 保存页数
			obj.put("pageCount", pageCount);
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
		return null;
	}

	// app客户查询所有订单
	public ActionForward getAllShiftApp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String id = new String(request.getParameter("ziId").getBytes(
				"ISO-8859-1"), "utf-8");

		ShiftStockSeed shiftStockSeed = shiftStockSeedService
				.getShiftStockSeedId(id);
		request.setAttribute("shiftStockSeed", shiftStockSeed);
		return mapping.findForward("getAllShiftApp");
	}

	// app客户查询所有订单
	public void getDingdanAppPash(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		// 返回行数
		PageRow pageRow = new PageRow();

		// 获取页面传入的值
		String s_pageNows = request.getParameter("pageNow");

		int clientId = Integer.parseInt(request.getSession()
				.getAttribute("clientId").toString());

		// 获得总页数
		int pageCount = shiftStockSeedService.getNuokuCountAllPash(clientId,
				pageRow.getRow());
		request.setAttribute("pageCount", pageCount);

		// 判断，如果总页数为0，即：没有数据，不让访问数据库，直接return
		if (pageCount == 0) {
			JSONObject obj = new JSONObject();
			obj.put("qingkong", "clean");
			out.print(obj.toString());
			out.flush();
			out.close();
			return;
		}

		int pageNow2 = 1;
		// 获取用户输入页数
		// 当前页
		if (s_pageNows != null) {
			pageNow2 = fenye(s_pageNows, pageCount);
		}

		System.out.println("pageNow2:" + pageNow2);

		List<ShiftStockSeed> shiftList = shiftStockSeedService
				.getNuokuInfoAllPash(clientId, pageNow2, pageRow.getRow());

		JSONArray array = new JSONArray();
		for (ShiftStockSeed c : shiftList) {
			JSONObject obj = new JSONObject();

			obj.put("shiftClientNumber", c.getShiftStock()
					.getSstockClientNumber());// 客户单号
			obj.put("goodsName", c.getGoods().getGoodsName());// 货物名称
			obj.put("goodsStandardName", c.getGoods()// 货物规格
					.getGoodsStandard().getGoodsStandardName());
			obj.put("goodsYieldlyName", c.getGoods()// 货物产地
					.getGoodsYieldly().getGoodsYieldlyName());
			obj.put("goodsCategoryName", c.getGoods().getGoodsCategory()
					.getGoodsCategoryName());// 货物品类
			obj.put("goodsQualityName", c.getGoods().getGoodsQuality()
					.getGoodsQualityName());// 货物材质
			obj.put("shiftDriverTel", c.getShiftStock().getSstockTel());// 联系方式
			// 将子订单id放入隐藏域中的值
			obj.put("seedId", c.getSsseedId());

			// 保存页数
			obj.put("pageCount", pageCount);
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();

	}

	// app客户有条件查询订单
	public ActionForward getDingdanAppTjPash(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		// 返回行数
		int pageRow = 10;
		if(request.getParameter("pageRow")!=null){
			pageRow = Integer.parseInt(request.getParameter("pageRow").toString());
		}
		// 获取页面传入的值

		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String kehuDanhao = request.getParameter("kehuDanhao");
		String goodsName = request.getParameter("goodsName");
		String s_pageNows = request.getParameter("pageNow");

		int clientId = Integer.parseInt(request.getSession().getAttribute("clientId").toString());

		// 获得总页数
		int pageCount = shiftStockSeedService.getNuokuCountPash(clientId, startTime, endTime, kehuDanhao, goodsName, pageRow);

		request.setAttribute("pageCount", pageCount);

		int pageNow2 = 1;
		// 获取用户输入页数
		// 当前页
		if (s_pageNows != null) {
			pageNow2 = fenye(s_pageNows, pageCount);
		}

		List<ShiftStockSeed> shiftList = shiftStockSeedService.getNuokuInfoPash(clientId, startTime, endTime, kehuDanhao, goodsName, pageNow2, pageRow);

		JSONArray array = new JSONArray();
		// 判断，如果总页数为0，即：没有数据，不让访问数据库，直接return
		if (shiftList.size()<= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		for (ShiftStockSeed c : shiftList) {
			JSONObject obj = new JSONObject();
			obj.put("result","notnull");
			obj.put("id", c.getShiftStock().getSstockId());//保存总订单编号
			obj.put("zid", c.getSsseedId());//保存子订单编号
			obj.put("zhuanchu", c.getShiftStock().getClientBySstockClientId().getClientAbbreviation());//保存转出单位
			obj.put("zhuanru", c.getShiftStock().getClientBySstockShiftToFirm().getClientAbbreviation());//保存转入单位
			obj.put("huowu", c.getGoods().getGoodsName());//保存货物名称
			obj.put("kehudanhao", c.getShiftStock().getSstockClientNumber());//保存客户单号
			obj.put("faqishijian", c.getShiftStock().getSstockReateTime());//保存发起时间
			obj.put("zhongliang", c.getSsseedWeight());//保存重量
			obj.put("jieguo", c.getSsseedAuditingTrue() == null ? "无" : c.getSsseedAuditingTrue());//保存审核结果
			obj.put("zhuangtai", c.getSsseedOrderStatus());//保存订单状态

			obj.put("shiftClientNumber", c.getShiftStock().getSstockClientNumber());// 客户单号
			obj.put("goodsName", c.getGoods().getGoodsName());// 货物名称
			obj.put("goodsStandardName", c.getGoods().getGoodsStandard().getGoodsStandardName());// 货物规格
			obj.put("goodsYieldlyName", c.getGoods().getGoodsYieldly().getGoodsYieldlyName());// 货物产地
			obj.put("goodsCategoryName", c.getGoods().getGoodsCategory().getGoodsCategoryName());// 货物品类
			obj.put("goodsQualityName", c.getGoods().getGoodsQuality().getGoodsQualityName());// 货物材质
			obj.put("shiftDriverTel", c.getShiftStock().getSstockTel());// 联系方式
			// 将子订单id放入隐藏域中的值
			obj.put("seedId", c.getSsseedId());
			// 保存页数
			obj.put("pageCount", pageCount);
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
		return null;
	}

	// 过户订单作废
	public void goDingdanZuofei(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		boolean ok = false;
		boolean seedOk = false;
		String id = new String(request.getParameter("id")
				.getBytes("ISO-8859-1"), "utf-8");
		ShiftStockSeed shiftStockSeed = shiftStockSeedService
				.getShiftStockSeedId(id);
		// 当子订单状态为计划入库时,客户可以修改为订单作废
		if (shiftStockSeed.getSsseedOrderStatus().equals("正在审核")) {
			// 修改子订单状态为订单作废
			shiftStockSeed.setSsseedOrderStatus("订单作废");
			ok = shiftStockSeedService.update(shiftStockSeed);
			// 修改总订单状态为订单作废
			if (ok) {
				ShiftStock shiftStock = shiftStockService
						.getShiftStockId(shiftStockSeed.getShiftStock()
								.getSstockId());
				shiftStock.setSstockOrderStatus("订单作废");
				seedOk = shiftStockService.update(shiftStock);
			}
			if (seedOk) {
				out.print("<script>alert('操作成功！');window.location.href='/XGProject/app-web/indent-search.jsp'</script>");
			} else {
				out.print("<script>alert('操作失败！');window.location.href='/XGProject/app-web/indent-search.jsp'</script>");
			}
		} else {
			out.print("<script>alert('操作失败！订单可能正在操作中...');window.location.href='/XGProject/app-web/indent-search.jsp'</script>");
		}

	}

	// app客户查询所有订单
	public void getGuojiesuanApp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		// 返回行数
		PageRow pageRow = new PageRow();

		// 获取页面传入的值
		String s_pageNows = request.getParameter("pageNow");

		int clientId = Integer.parseInt(request.getSession()
				.getAttribute("clientId").toString());
		ShiftStockSeedForm sssf = (ShiftStockSeedForm) form;
		if (sssf.getBegin() == null) {
			sssf.setBegin("");
		} 

		if (sssf.getFinish() == null || sssf.getFinish().equals("")) {
			sssf.setFinish(pt.getTimes());
		} 
		// 获得总页数
		int pageCount = shiftStockSeedService.getNuokuCountAll(clientId,sssf.getBegin(),sssf.getFinish(),
				pageRow.getRow());
		request.setAttribute("pageCount", pageCount);

		// 判断，如果总页数为0，即：没有数据，不让访问数据库，直接return
		if (pageCount == 0) {
			JSONObject obj = new JSONObject();
			obj.put("qingkong", "clean");
			out.print(obj.toString());
			out.flush();
			out.close();
			return;
		}

		int pageNow2 = 1;
		// 获取用户输入页数
		// 当前页
		if (s_pageNows != null) {
			pageNow2 = fenye(s_pageNows, pageCount);
		}

		System.out.println("pageNow2:" + pageNow2);

		List<ShiftStockSeed> shiftList = shiftStockSeedService.getNuokuInfoAll(
				clientId, sssf.getBegin(),sssf.getFinish(),pageNow2, pageRow.getRow());

		JSONArray array = new JSONArray();
		for (ShiftStockSeed c : shiftList) {
			JSONObject obj = new JSONObject();

			obj.put("zongId", c.getShiftStock().getSstockId());// 总订单编号
			obj.put("clientNumber", c.getShiftStock().getSstockClientNumber());// 客户单号
			obj.put("jiesuanType", c.getSsseedClientAccounts());// 结算方式
			obj.put("goodsName", c.getGoods().getGoodsName());// 货物名称
			obj.put("realityWeight", c.getSsseedWeight());// 货物实际重量
			obj.put("shouldCost", c.getSsseedShouldCost());// 应收钱数
			obj.put("realityCost", c.getSsseedRealityCost());// 实收钱数

			// 将子订单id放入隐藏域中的值
			obj.put("seedId", c.getSsseedId());
			// 保存页数
			obj.put("pageCount", pageCount);
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();

	}

	// app客户有条件查询订单
	public void getGuoJiesuanTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		// 返回行数
		int pageRow = 10;
		if(request.getParameter("pageRow")!=null){
			pageRow = Integer.parseInt(request.getParameter("pageRow").toString());
		}

		// 获取页面传入的值

		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String kehuDanhao = request.getParameter("kehuDanhao");
		String goodsName = request.getParameter("goodsName");
		String jiesuanType = request.getParameter("jiesuanType");
		String s_pageNows = request.getParameter("pageNow");

		int clientId = Integer.parseInt(request.getSession()
				.getAttribute("clientId").toString());

		// 获得总页数
		int pageCount = shiftStockSeedService.getNuokuCount(clientId,
				jiesuanType, startTime, endTime, kehuDanhao, goodsName,
				pageRow);

		request.setAttribute("pageCount", pageCount);

		// 判断，如果总页数为0，即：没有数据，不让访问数据库，直接return
		if (pageCount == 0) {
			JSONObject obj = new JSONObject();
			obj.put("qingkong", "clean");
			out.print(obj.toString());
			out.flush();
			out.close();
			return;
		}

		int pageNow2 = 1;
		// 获取用户输入页数
		// 当前页
		if (s_pageNows != null) {
			pageNow2 = fenye(s_pageNows, pageCount);
		}

		List<ShiftStockSeed> shiftList = shiftStockSeedService.getNuokuInfo(
				clientId, jiesuanType, startTime, endTime, kehuDanhao,
				goodsName, pageNow2, pageRow);

		JSONArray array = new JSONArray();
		for (ShiftStockSeed c : shiftList) {
			JSONObject obj = new JSONObject();

			obj.put("zongId", c.getShiftStock().getSstockId());// 总订单编号
			obj.put("clientNumber", c.getShiftStock().getSstockClientNumber());// 客户单号
			obj.put("jiesuanType", c.getSsseedClientAccounts());// 结算方式
			obj.put("goodsName", c.getGoods().getGoodsName());// 货物名称
			obj.put("realityWeight", c.getSsseedWeight());// 货物实际重量
			obj.put("shouldCost", c.getSsseedShouldCost());// 应收钱数
			obj.put("realityCost", c.getSsseedRealityCost());// 实收钱数
			// 将子订单id放入隐藏域中的值
			obj.put("seedId", c.getSsseedId());
			obj.put("yewu", "过户业务");

			// 保存页数
			obj.put("pageCount", pageCount);
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();

	}

	// app客户查询结算详情订单
	public ActionForward getGuojiesuanXiangqing(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String id = new String(request.getParameter("ziId").getBytes(
				"ISO-8859-1"), "utf-8");

		ShiftStockSeed shiftStockSeed = shiftStockSeedService
				.getShiftStockSeedId(id);

		// 下站应收费用
		Double XZshouldCost = 0.0;
		// 下站实收费用
		Double XZshijieCost = 0.0;

		Xiazhanfei xiazhanfei = new Xiazhanfei();

		System.out.println("总订单："
				+ shiftStockSeed.getShiftStock().getSstockId());

		// 根据总订单id查询下站费的信息
		List<Xiazhanfei> listXiazhan = xiazhanfeiService
				.getAllzongId(shiftStockSeed.getShiftStock().getSstockId());

		if (listXiazhan.size() > 0) {
			// 得到类
			xiazhanfei = listXiazhan.get(0);

			for (int i = 0; i < listXiazhan.size(); i++) {
				if (listXiazhan.get(i).getXzcount() != null) {
					XZshouldCost += listXiazhan.get(i).getXzcount();
				}
				if (listXiazhan.get(i).getXadefinedfour() != null) {
					XZshijieCost += Double.valueOf(listXiazhan.get(i)
							.getXadefinedfour());
				}
			}
		}

		// 保存类listXiazhan
		request.setAttribute("xiazhanfei", xiazhanfei);
		// 保存下站应收费用
		request.setAttribute("XZshouldCost", XZshouldCost);
		// 保存下站实收费用
		request.setAttribute("XZshijieCost", XZshijieCost);

		request.setAttribute("shiftStockSeed", shiftStockSeed);
		return mapping.findForward("getGuojiesuanXiangqing");
	}

	// 通过结算方式查询过户订单
	public ActionForward QueryJieSuan(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ShiftStockSeedForm sssf = (ShiftStockSeedForm) form;
		if (sssf.getBegin() == null || sssf.getBegin().equals("")) {
			sssf.setBegin(pt.getNowJianYi());
		} else {
			sssf.setBegin(sssf.getBegin());
		}

		if (sssf.getFinish() == null || sssf.getFinish().equals("")) {
			sssf.setFinish(pt.getTimes());
		} else {
			sssf.setFinish(sssf.getFinish());
		}
		if (sssf.getSsseedClientAccounts() == null) {
			sssf.setSsseedClientAccounts("");
		}
		if (sssf.getJiancheng() == null) {
			sssf.setJiancheng("");
		}
		String shoufeiren = "";
		if (request.getParameter("shoufeiren") != null) {
			shoufeiren = request.getParameter("shoufeiren").toString();
		}
		int pageNow = 1;
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}
		int pageCount = this.shiftStockSeedService.getJieSuanQueryByPageCount(
				sssf.getJiancheng(), sssf.getBegin(), sssf.getFinish(),
				sssf.getSsseedClientAccounts(), 30, shoufeiren);
		if (pageNow >= pageCount) {
			pageNow = pageCount;
		} else if (pageNow <= 1) {
			pageNow = 1;
		}
		List<ShiftStockSeed> ssslist = this.shiftStockSeedService
				.getJieSuanQueryByPage(sssf.getJiancheng(), sssf.getBegin(),
						sssf.getFinish(), sssf.getSsseedClientAccounts(),
						pageNow, 30, shoufeiren);

		JSONArray array = new JSONArray();
		PrintWriter out = response.getWriter();
		if (ssslist.size() <= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		for (int i = 0; i < ssslist.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("zongid", ssslist.get(i).getShiftStock().getSstockId());// 保存总编号
			obj.put("zid", ssslist.get(i).getSsseedId());// b保存子订单编号
			obj.put("kehudanhao", ssslist.get(i).getShiftStock()
					.getSstockClientNumber());// 保存客户好
			obj.put("zhuanchu", ssslist.get(i).getShiftStock()
					.getClientBySstockClientId().getClientAbbreviation());// 简称
			obj.put("zhuanru", ssslist.get(i).getShiftStock()
					.getClientBySstockShiftToFirm().getClientAbbreviation());// 简称
			obj.put("time", ssslist.get(i).getShiftStock().getSstockReateTime());// 订单生成日期
			obj.put("goods", ssslist.get(i).getGoods().getGoodsName());// 货物名称
			obj.put("weight", ssslist.get(i).getSsseedWeight());// 过户重量
			obj.put("cost", (ssslist.get(i).getSsseedShouldCost() == null ? 0.0
					: ssslist.get(i).getSsseedShouldCost()));// 应收出库费
			obj.put("sscost",
					(ssslist.get(i).getSsseedRealityCost() == null ? 0.0
							: ssslist.get(i).getSsseedRealityCost()));// 实收出库费
			List<Xiazhanfei> xzlist = this.xiazhanfeiService
					.getAllzongId(ssslist.get(i).getShiftStock().getSstockId());
			Double xzcost = 0.0;
			Double ssxzcost = 0.0;
			for (int j = 0; j < xzlist.size(); j++) {
				if (xzlist.get(j).getXzcount() != null
						&& !xzlist.get(j).getXzcount().equals("")) {
					xzcost += xzlist.get(j).getXzcount();
				}
				if (xzlist.get(j).getXadefinedfour() != null
						&& !xzlist.get(j).getXadefinedfour().equals("")) {
					ssxzcost += Double.parseDouble(xzlist.get(j)
							.getXadefinedfour());
				}
			}
			obj.put("jiesuan", ssslist.get(i).getSsseedClientAccounts());// 结算方式
			obj.put("xzcost", xzcost);
			obj.put("ssxzcost", ssxzcost);
			obj.put("result", "notnull");
			obj.put("pageNow", pageNow);
			obj.put("pageCount", pageCount);
			array.add(obj);
			xzcost = 0.0;
			ssxzcost = 0.0;
		}
		out.print(array.toString());
		out.flush();
		out.close();
		return null;
	}

	// 手机客户查询返回json格式

	public void getAllShoujiApp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 获取前台传入子订单id

		System.out.println("过户中----------------------------");

		String id = request.getParameter("ziId");

		ShiftStockSeed shiftStockSeed = shiftStockSeedService
				.getShiftStockSeedId(id);
		request.setAttribute("shiftStockSeed", shiftStockSeed);

		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		if (shiftStockSeed != null) {

			obj.put("ziDingdanId", shiftStockSeed.getSsseedId());
			obj.put("changjianTime", shiftStockSeed.getShiftStock()
					.getSstockReateTime());
			obj.put("kehuName", shiftStockSeed.getShiftStock()
					.getClientBySstockClientId().getClientFirmName());
			obj.put("kehuDanhao", shiftStockSeed.getShiftStock()
					.getSstockClientNumber());

			obj.put("zhuanruDanwei", shiftStockSeed.getShiftStock()
					.getClientBySstockShiftToFirm().getClientAbbreviation());

			obj.put("pinlei", shiftStockSeed.getGoods().getGoodsCategory()
					.getGoodsCategoryName());
			obj.put("goodsName", shiftStockSeed.getGoods().getGoodsName());
			obj.put("guige", shiftStockSeed.getGoods().getGoodsStandard()
					.getGoodsStandardName());

			obj.put("caizhi", shiftStockSeed.getGoods().getGoodsQuality()
					.getGoodsQualityName());
			obj.put("shuxin", shiftStockSeed.getGoods().getGoodsProperty()
					.getGoodsPropertyName());
			obj.put("chandi", shiftStockSeed.getGoods().getGoodsYieldly()
					.getGoodsYieldlyName());
			obj.put("shouldWeight", shiftStockSeed.getSsseedWeight());

			obj.put("yinShoufeiYong", shiftStockSeed.getSsseedShouldCost());
			obj.put("shiShoufeiYong", shiftStockSeed.getSsseedRealityCost());

			obj.put("dingdanStatus", shiftStockSeed.getSsseedOrderStatus());
			obj.put("finishTime", shiftStockSeed.getSsseedCollectTime());
			obj.put("beizhu", shiftStockSeed.getShiftStock().getSstockRemark());

		}

		out.print(obj.toString());
		out.flush();
		out.close();

	}

	// app中进行查询某个客户的日结费用
	public ActionForward getAppQueryDayCost(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ShiftStockSeedForm ssf = (ShiftStockSeedForm) form;
		JSONArray array = new JSONArray();
		PrintWriter out = response.getWriter();
		if (ssf.getGoodsname() == null) {
			ssf.setGoodsname("");
		}
		if (ssf.getClientId() == null) {
			JSONObject obj = new JSONObject();
			obj.put("result", "nofive");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}

		int pageNow = 1;
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}
		int pageCount = this.shiftStockSeedService.getAppQueryDayCostByCount(
				ssf.getClientId(), ssf.getGoodsname(), pageNow, 15);
		if (pageNow >= pageCount) {
			pageNow = pageCount;
		} else if (pageNow <= 1) {
			pageNow = 1;
		}

		List<ShiftStockSeed> sslist = this.shiftStockSeedService
				.getAppQueryDayCostByPage(ssf.getClientId(),
						ssf.getGoodsname(), pageNow, 15);
		if (sslist.size() <= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "no");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}

		for (int i = 0; i < sslist.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("zongid", sslist.get(i).getShiftStock().getSstockId());// 保存中订单的编号
			obj.put("kehuid", sslist.get(i).getShiftStock()
					.getSstockClientNumber());// 保存客户的单号
			obj.put("shouldcost", sslist.get(i).getSsseedShouldCost());// 保存应收的费用
			obj.put("reladycost", sslist.get(i).getSsseedRealityCost());// 保存实收的费用
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

}
