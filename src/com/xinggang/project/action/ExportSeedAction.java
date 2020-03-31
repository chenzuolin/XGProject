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

import com.xinggang.project.entity.Client;
import com.xinggang.project.entity.Export;
import com.xinggang.project.entity.ExportOperate;
import com.xinggang.project.entity.ExportSeed;
import com.xinggang.project.entity.InteriorUser;
import com.xinggang.project.entity.TarehouseDetail;
import com.xinggang.project.entity.Xiazhanfei;
import com.xinggang.project.form.ExportForm;
import com.xinggang.project.form.ExportSeedForm;
import com.xinggang.project.service.AppMessageService;
import com.xinggang.project.service.ClientGoodsService;
import com.xinggang.project.service.ClientService;
import com.xinggang.project.service.CostTypeService;
import com.xinggang.project.service.ExportOperateService;
import com.xinggang.project.service.ExportSeedService;
import com.xinggang.project.service.ExportService;
import com.xinggang.project.service.GoodsService;
import com.xinggang.project.service.InteriorUserService;
import com.xinggang.project.service.LoginLogService;
import com.xinggang.project.service.PaymentFashionService;
import com.xinggang.project.service.TarehouseDetailService;
import com.xinggang.project.service.TarehouseService;
import com.xinggang.project.service.XiazhanfeiService;
import com.xinggang.project.tools.PageRow;
import com.xinggang.project.tools.PresentTime;

/**
 * 出库子订单类action
 * 
 * @author Administrator
 * 
 */
public class ExportSeedAction extends DispatchAction {
	// 出库子订单service
	private ExportSeedService exportSeedService;
	// 出库总订单service
	private ExportService exportService;
	// 货物service
	@SuppressWarnings("unused")
	private GoodsService goodsService;
	// 支付方式service
	@SuppressWarnings("unused")
	private PaymentFashionService paymentFashionService;
	// 登录日志表service
	private LoginLogService loginLogService;
	// 库位service
	private TarehouseService tarehouseService;
	// 内部人员service
	private InteriorUserService interiorUserService;
	// 出库订单操作service
	private ExportOperateService exportOperateService;
	// 批次service
	private TarehouseDetailService tarehouseDetailService;
	// 客户库存service
	private ClientGoodsService clientGoodsService;
	// 费用类型
	private CostTypeService costTypeService;
	// 客户
	private ClientService clientService;
	// 下站service
	private XiazhanfeiService xiazhanfeiService;
	// app消息
	private AppMessageService appMessageService;

	public void setAppMessageService(AppMessageService appMessageService) {
		this.appMessageService = appMessageService;
	}

	public void setXiazhanfeiService(XiazhanfeiService xiazhanfeiService) {
		this.xiazhanfeiService = xiazhanfeiService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public void setCostTypeService(CostTypeService costTypeService) {
		this.costTypeService = costTypeService;
	}

	// 分页
	private PageRow pr = new PageRow();

	// 时间工具类
	private PresentTime pt = new PresentTime();

	public void setClientGoodsService(ClientGoodsService clientGoodsService) {
		this.clientGoodsService = clientGoodsService;
	}

	public void setTarehouseDetailService(
			TarehouseDetailService tarehouseDetailService) {
		this.tarehouseDetailService = tarehouseDetailService;
	}

	public void setExportOperateService(
			ExportOperateService exportOperateService) {
		this.exportOperateService = exportOperateService;
	}

	public void setInteriorUserService(InteriorUserService interiorUserService) {
		this.interiorUserService = interiorUserService;
	}

	public void setTarehouseService(TarehouseService tarehouseService) {
		this.tarehouseService = tarehouseService;
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

	public void setExportService(ExportService exportService) {
		this.exportService = exportService;
	}

	public void setExportSeedService(ExportSeedService exportSeedService) {
		this.exportSeedService = exportSeedService;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return super.execute(mapping, form, request, response);
	}

	// 查询子订单状态为计划出库，待处理的订单
	public ActionForward getDaiChuLi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ExportSeedForm esf = (ExportSeedForm) form;
		if (esf.getZongbianhao() == null) {
			esf.setZongbianhao("");
		}
		if (esf.getKehubianhao() == null) {
			esf.setKehubianhao("");
		}
		if (esf.getDanwei() == null) {
			esf.setDanwei("");
		}
		if (esf.getJiancheng() == null) {
			esf.setJiancheng("");
		}
		if (esf.getDanweizhujifu() == null) {
			esf.setDanweizhujifu("");
		}
		if (esf.getGoodsName() == null) {
			esf.setGoodsName("");
		}
		if (esf.getGuige() == null) {
			esf.setGuige("");
		}
		if (esf.getCaizhi() == null) {
			esf.setCaizhi("");
		}
		if (esf.getShuxing() == null) {
			esf.setShuxing("");
		}
		if (esf.getZhujifu() == null) {
			esf.setZhujifu("");
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
		int pageCount = this.exportSeedService.getDaiChuLiByPageCount(
				"2015-6-6 12:12:12", pt.getTimes() + " 23:59:59",
				esf.getZongbianhao(), esf.getKehubianhao(), esf.getDanwei(),
				esf.getDanweizhujifu(), esf.getJiancheng(), esf.getGoodsName(),
				esf.getGuige(), esf.getCaizhi(), esf.getShuxing(),
				esf.getShuxing(), pageRow);
		request.getSession().setAttribute(
				"DCLchuku",
				this.exportSeedService.getDaiChuLiByPageCount(
						"2015-6-6 12:12:12", pt.getTimes() + " 23:59:59", "",
						"", "", "", "", "", "", "", "", "", 1));
		JSONArray array = new JSONArray();
		PrintWriter out = response.getWriter();
		System.out.println("pageNow是多少呢？输出看看：" + pageNow);
		if (pageNow > pageCount) {
			JSONObject obj = new JSONObject();
			obj.put("max", "maxs");
			array.add(obj);
			out.print(array.toString());
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
		List<ExportSeed> daichuliList = this.exportSeedService
				.getDaiChuLiByPage("2015-6-6 12:12:12", pt.getTimes(),
						esf.getZongbianhao(), esf.getKehubianhao(),
						esf.getDanwei(), esf.getDanweizhujifu(),
						esf.getJiancheng(), esf.getGoodsName(), esf.getGuige(),
						esf.getCaizhi(), esf.getShuxing(), esf.getShuxing(),
						pageNow, pageRow);

		if (daichuliList.size() > 0) {
			for (ExportSeed es : daichuliList) {
				JSONObject obj = new JSONObject();
				obj.put("id", es.getExport().getExportId());// 保存出库总订单的id
				obj.put("zid", es.getEseedId());// 保存出库子订单的id
				obj.put("huozhu", es.getExport().getClient()
						.getClientAbbreviation());// 保存发起的客户的简称
				obj.put("kehudanhao", es.getExport().getExportClientNumber());// 保存客户的订单编号
				obj.put("youxiao", es.getExport().getExportDefinedTwo());// 保存订单的有效期
				obj.put("zhuangtai", es.getEseedOrderStatus());// 保存订单的状态
				obj.put("yunshu", es.getExport().getExportCarryType());
				obj.put("chehao",
						es.getExport().getExportWagonNumber() == null ? "无"
								: es.getExport().getExportWagonNumber());// 保存车号
				obj.put("siji",
						es.getExport().getExportDriverName() == null ? "无" : es
								.getExport().getExportDriverName());// 保存司机的姓名
				obj.put("sijitel",
						es.getExport().getExportDriverTel() == null ? "无" : es
								.getExport().getExportDriverTel());// 保存司机的电话
				obj.put("pinlei", es.getGoods().getGoodsCategory()
						.getGoodsCategoryName());// 保存货物的品类
				obj.put("mingcheng", es.getGoods().getGoodsName());// 保存货物名称
				obj.put("guige", es.getGoods().getGoodsStandard()
						.getGoodsStandardName());// 保存货物规格
				obj.put("caizhi", es.getGoods().getGoodsQuality()
						.getGoodsQualityName());// 保存货物材质
				obj.put("shuxing", es.getGoods().getGoodsProperty()
						.getGoodsPropertyName());// 保存货物属性
				obj.put("chandi", es.getGoods().getGoodsYieldly()
						.getGoodsYieldlyName());// 保存货物产地
				obj.put("goodsId", es.getGoods().getGoodsId());// 保存货物的id
				obj.put("zhongliang", es.getEseedShouldWeight());// 保存应出的重量
				obj.put("shichuZL", es.getEseedRealityWeight());// 保存实出重量
				obj.put("faqitime", es.getExport().getExportReateTime());// 保存订单的发起时间
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
			// 返回到对应的页面
			return null;
		}

	}

	// 当调度点击处理按钮的时候,将单个查询出来
	public ActionForward DiaoDuChuLi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ExportSeedForm esf = (ExportSeedForm) form;
		if (esf != null) {
			ExportSeed es = this.exportSeedService.getExportSeedId(esf
					.getEseedId());// 通过编号查询
			if (es != null) {
				request.setAttribute("exportSeed", es);// 查询相应的库位和批次*--------------------------------------------------------
			}
		}
		// 返回到对应的页面
		return super.execute(mapping, form, request, response);
	}

	// 调度进行分配，选择对应的库位，批次，库位和批次可以多选,
	public ActionForward DiaoDuFenPei(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ExportSeedForm esf = (ExportSeedForm) form;
		if (esf != null) {
			int num = 0;
			ExportSeed es = this.exportSeedService.getExportSeedId(esf
					.getEseedId());// 通过编号查询
			es.setEseedOrderStatus("准备出库");
			boolean oks = this.exportSeedService.update(es);
			if (oks == false) {
				request.getSession()
						.setAttribute("diaodufenpeiMessage", "提交失败");
			} else {
				if (es != null) {
					String id = es.getEseedId();
					List<ExportOperate> eolist = this.exportOperateService
							.getZidingdan(id);
					String ids = "";
					String is = "";
					int y = 0;
					this.appMessageService.saveMessage(es.getExport()
							.getClient().getClientId(), null, es.getExport()
							.getExportId(), es.getEseedId(), es.getGoods()
							.getGoodsName(),
							"您的司机已与我们取得联系，并且您的订单已经开始受理，我们会尽快为您服务！客户单号："
									+ es.getExport().getExportClientNumber(),
							"出库订单");
					if (eolist.size() > 0 && eolist != null) {
						ids = id.substring(2, id.length());
						ids = id.substring(2, id.length());
						String eoid = "";

						for (int d = 0; d < eolist.size(); d++) {
							eoid = eolist.get(d).getEoperateId();
							int x = Integer.parseInt(eoid.substring(
									eoid.length() - 2, eoid.length()));
							if (x >= y) {
								y = x;
							}
						}

					}

					for (int i = 0; i < esf.getKuwei().length; i++) {

						ExportOperate eo = new ExportOperate();
						if (eolist.size() <= 0 || eolist == null) {
							ids = id.substring(2, id.length());
							if (i + 1 < 10) {
								is = "0" + (i + 1);
							} else {
								is = String.valueOf(i + 1);
							}
						} else {
							y++;
							if (y + 1 < 10) {
								is = "0" + (y + 1);
							} else {
								is = String.valueOf(y + 1);
							}
						}

						eo.setEoperateId("出C" + ids + is);// 设置操作订单编号
						eo.setExportSeed(es);// 设置子订单编号，外键
						eo.setTarehouse(this.tarehouseService
								.getTarehouseId(esf.getKuwei()[i]));// 设置库位
						eo.setInteriorUserByEoperateDispatcher(this.interiorUserService
								.getInteriorUserId(esf.getDiaodu()));// 设置调度员
						eo.setEoperateDispatcherTime(pt.getTimes());// 设置调度员操作时间
						eo.setEoperatePonderationTrue(esf.getLisuan());// 设置是否过磅，页面前端显示两个单选按钮，理算和过磅
						eo.setInteriorUserByEoperateStoreman(this.interiorUserService
								.getInteriorUserId(esf.getBaoguan()));// 设置保管员
						eo.setEoperateDefinedOne("准备出库");// 设置订单状态为准备出库
						eo.setEoperateDefinedTwo(String.valueOf(esf
								.getFenpeizhongliang()));// 设置分配的重量
						eo.setEoperateRemark(esf.getBeizhu()[0]);// 设置备注
						if (esf.getPici().length > 0) {
							for (int j = 0; j < esf.getPici().length; j++) {
								System.out.println(esf.getPici()[j]);
								TarehouseDetail td = this.tarehouseDetailService
										.getTarehouseDetailId(esf.getPici()[j]);// 通过编号查询批次
								if (td != null) {
									eo.setEOperatepici((eo.getEOperatepici() == null ? ""
											: eo.getEOperatepici())
											+ esf.getPici()[j] + ",");
									// 修改批次中的预留字段二为1,当保管员进行提价时，进行修改已出的重量
									td.setTdetailDefinedTwo("1");

									this.tarehouseDetailService.update(td);
								}
							}
						}
						boolean ok = this.exportOperateService.save(eo);
						// 设置该操作员为作业状态
						InteriorUser iu = this.interiorUserService
								.getInteriorUserId(esf.getBaoguan());
						iu.setIuserWork(0);
						this.interiorUserService.update(iu);
						if (ok) {
							num++;
						}
					}
					if (num == esf.getKuwei().length) {
						request.getSession().setAttribute(
								"diaodufenpeiMessage", "提交成功！");
						this.loginLogService.updateLogin(request.getSession()
								.getAttribute("loginlogId").toString(),
								"分配出库订单" + es.getExport().getExportId());// 记录在日志表中
					} else {
						request.getSession().setAttribute(
								"diaodufenpeiMessage", "提交失败！");
					}

				}
			}
		} else {
			request.getSession().setAttribute("diaodufenpeiMessage", "提交失败");
		}
		// 返回到对应的页面
		PrintWriter out = response.getWriter();
		out.print("<script>alert('"
				+ request.getSession().getAttribute("diaodufenpeiMessage")
				+ "');window.history.go(-1);</script>");
		return null;
	}

	// 当调度或者审核点击出库完成的时候一ajax的方式验证出库操作订单的状态是否完成
	public ActionForward ChuKuWanChengAjax(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ExportSeedForm esf = (ExportSeedForm) form;

		ExportSeed es = this.exportSeedService
				.getExportSeedId(esf.getEseedId());// 通过编号查询
		// 通过子订单的编号查询操作订单，如果该子订单的下面有操作订单的状态不是未收费或者不是已收费的时候，弹出提醒，是否要出库完成
		List<ExportOperate> eolist = this.exportOperateService.getZidingdan(es
				.getEseedId());// 通过子订单的编号查询出库的操作订单
		// 用遍历的方式查询是否有未完成的操作订单
		JSONArray array = new JSONArray();
		PrintWriter out = response.getWriter();
		int x = 0;
		for (ExportOperate eo : eolist) {
			if (eo.getEoperateDefinedOne().equals("未收费") == false) {
				if (eo.getEoperateDefinedOne().equals("已收费") == false) {
					if (eo.getEoperateDefinedOne().equals("订单作废") == false) {
						x++;
					}
				}
			}
		}
		if (x > 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "no");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		} else {
			JSONObject obj = new JSONObject();
			obj.put("result", "yes");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
	}

	// 订单处理完成，当操作的订单，所有的都完成的时候，调度点击出库完成，说明该订单操作完成，订单状态改变，如果子订单中的结算方式为月结，
	// 并且实收的费用为空的时候，那么该客户就月结客户，如果实收费用不为空，结算方式为日结，那么就是日结客户，将订单状态改变为出库完成，月结的客户改变为未收费
	// 判断如果应收重量和实收重量相等的时候，客户的库存不改变，如果当应出的重量大于实出的重量的时候，客户的库存加应出减实出，如果当实出大于应出的时候，
	// 客户库存等于库存减（实出减应出）
	public ActionForward ChuKuWanCheng(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ExportSeedForm esf = (ExportSeedForm) form;
		if (esf != null) {

			ExportSeed es = this.exportSeedService.getExportSeedId(esf
					.getEseedId());// 通过编号查询

			if (es != null) {
				double reality = es.getEseedRealityWeight() == null ? 0.0 : es
						.getEseedRealityWeight();
				if (es.getEseedShouldWeight() > reality) {
					try {
						double result = es.getEseedShouldWeight() - reality;// 应出重量大于实出的时候，应出减去实出
						this.clientGoodsService.ZengjiaKucun(es.getExport()
								.getClient().getClientId(), es.getGoods()
								.getGoodsId(), result, 0.0);
					} catch (Exception e) {

					}
				} else if (es.getEseedShouldWeight() < reality) {
					try {
						double result = reality - es.getEseedShouldWeight();// 应出重量大于实出的时候，应出减去实出
						this.clientGoodsService.JianshaoKucun(es.getExport()
								.getClient().getClientId(), es.getGoods()
								.getGoodsId(), result);
					} catch (Exception e) {
					}
				}

				if (es.getEseedClientAccounts() != null) {
					// 判断是月结客户
					if (es.getEseedClientAccounts().equals("月结")
							&& es.getEseedRealityCost() == null
							&& es.getEseedSwrealityCost() == null) {
						es.setEseedOrderStatus("未收费");// 订单状态改变为未收费

					} else {
						// 为日结客户
						es.setEseedOrderStatus("出库完成");// 设置订单状态为出库完成
					}
				} else {
					// 为日结客户
					es.setEseedOrderStatus("出库完成");// 设置订单状态为出库完成
				}
				boolean ok = this.exportSeedService.update(es);
				if (ok) {
					this.appMessageService.saveMessage(es.getExport()
							.getClient().getClientId(), null, es.getExport()
							.getExportId(), es.getEseedId(), es.getGoods()
							.getGoodsName(), "您的订单已经处理完成，货物已出库！客户单号："
							+ es.getExport().getExportClientNumber(), "出库订单");
					request.getSession().setAttribute("chukuwanchengMessage",
							"提交成功！");
					this.loginLogService.updateLogin(request.getSession()
							.getAttribute("loginlogId").toString(), "出库完成"
							+ es.getExport().getExportId());// 将该操作记录到日志表中
				} else {
					request.getSession().setAttribute("chukuwanchengMessage",
							"提交失败！");
				}
			} else {
				request.getSession().setAttribute("chukuwanchengMessage",
						"提交失败！");
			}
		} else {
			request.getSession().setAttribute("chukuwanchengMessage", "提交失败！");
		}

		if (request.getParameter("ff").toString().equals("daichuli")) {
			// 返回到对应的界面
			PrintWriter out = response.getWriter();
			out.print("<script>alert('"
					+ request.getSession().getAttribute("chukuwanchengMessage")
					+ "');window.history.go(-2);</script>");
			return null;
		} else {
			// 返回到对应的界面
			PrintWriter out = response.getWriter();
			out.print("<script>alert('"
					+ request.getSession().getAttribute("chukuwanchengMessage")
					+ "');window.history.go(-2);</script>");
			return null;
		}

	}

	// 当客户发起的订单还没有处理的时候，客户点击修改，进行修改货物的重量
	public ActionForward ClientXiuGaiWeight(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ExportSeedForm esf = (ExportSeedForm) form;
		if (esf != null) {
			ExportSeed es = this.exportSeedService.getExportSeedId(esf
					.getEseedId());// 通过编号查询
			if (es != null) {
				try {
					if (this.exportOperateService.getQuXiaoDingDan(
							es.getExport().getExportId()).size() > 0) {
						request.getSession().setAttribute(
								"xiugaiExportMessage", "订单已开始操作，无法修改！");
					} else {
						es.setEseedShouldWeight(esf.getEseedShouldWeight());// 修改重量
						boolean ok = this.exportSeedService.update(es);
						if (ok) {
							request.getSession().setAttribute(
									"xiugaiExportMessage", "修改成功！");
							this.loginLogService.updateLogin(request
									.getSession().getAttribute("loginlogId")
									.toString(), "修改出库订单中货物重量"
									+ es.getExport().getExportId());// 记录到日志表中
						} else {
							request.getSession().setAttribute(
									"xiugaiExportMessage", "修改失败！");
						}
					}
				} catch (Exception e) {
				}
			} else {
				request.getSession().setAttribute("xiugaiExportMessage",
						"修改失败！");
			}
		} else {
			request.getSession().setAttribute("xiugaiExportMessage", "修改失败！");
		}

		// 返回到对应的界面
		return super.execute(mapping, form, request, response);
	}

	// 查询今日订单
	public ActionForward getJinRiDingDan(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ExportSeedForm esf = (ExportSeedForm) form;
		if (esf.getZongbianhao() == null) {
			esf.setZongbianhao("");
		}
		if (esf.getKehubianhao() == null) {
			esf.setKehubianhao("");
		}
		if (esf.getDanwei() == null) {
			esf.setDanwei("");
		}
		if (esf.getJiancheng() == null) {
			esf.setJiancheng("");
		}
		if (esf.getDanweizhujifu() == null) {
			esf.setDanweizhujifu("");
		}
		if (esf.getGoodsName() == null) {
			esf.setGoodsName("");
		}
		if (esf.getGuige() == null) {
			esf.setGuige("");
		}
		if (esf.getCaizhi() == null) {
			esf.setCaizhi("");
		}
		if (esf.getShuxing() == null) {
			esf.setShuxing("");
		}
		if (esf.getZhujifu() == null) {
			esf.setZhujifu("");
		}

		int pageNow = 1;
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}
		int pageCount = this.exportSeedService.getDaiChuLiByPageCount(
				pt.getDatesNianYR() + "00:00:00", pt.getDatesNianYR()
						+ "23:59:59", esf.getZongbianhao(),
				esf.getKehubianhao(), esf.getDanwei(), esf.getDanweizhujifu(),
				esf.getJiancheng(), esf.getGoodsName(), esf.getGuige(),
				esf.getCaizhi(), esf.getShuxing(), esf.getZhujifu(),
				pr.getRow());
		if (pageNow >= pageCount) {
			pageNow = pageCount;
		}
		if (pageNow <= 1) {
			pageNow = 1;
		}
		List<ExportSeed> daichuliList = this.exportSeedService
				.getDaiChuLiByPage(pt.getDatesNianYR() + "00:00:00",
						pt.getDatesNianYR() + "23:59:59", esf.getZongbianhao(),
						esf.getKehubianhao(), esf.getDanwei(),
						esf.getDanweizhujifu(), esf.getJiancheng(),
						esf.getGoodsName(), esf.getGuige(), esf.getCaizhi(),
						esf.getShuxing(), esf.getZhujifu(), pageNow,
						pr.getRow());
		// 保存到request中
		request.setAttribute("jinriList", daichuliList);
		// 将当前页保存
		request.getSession().setAttribute("pageNow", pageNow);
		// 返回到对应的页面
		return super.execute(mapping, form, request, response);
	}

	// 内部人员进行修改出库订单
	public ActionForward NeiBuXiuGai(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ExportSeedForm esf = (ExportSeedForm) form;
		if (esf != null) {
			ExportOperate eo = this.exportOperateService.getExportOperateId(esf
					.getChucaobianhao());// 通过编号查询出库操作订单
			if (eo != null) {
				eo.setEoperateRealityWeight(esf.getChucaoWeight());// 修改出库操作订单中重量
				eo.setEoperateRealityNumber(esf.getChucaoNumber());// 修改出库操作订单中的件数
				eo.setEoperateRealityCost(esf.getChucaoRealityCost());// 修改出库操作订单中的实收费用
				eo.setEoperateShouldCost(esf.getChucaoSholdCost());// 修改出库操作订单中的应收费用

				// 修改出库自订单中的内容
				ExportSeed es = this.exportSeedService.getExportSeedId(eo
						.getExportSeed().getEseedId());// 通过id查询子订单
				if (es != null) {
					es.setEseedShouldNumber(esf.getEseedRealityNumber());// 修改子订单中的应出件数
					es.setEseedRealityNumber(esf.getEseedRealityNumber());// 修改子订单中的实出件数
					es.setEseedRealityWeight(esf.getEseedRealityWeight());// 修改子订单中的实出重量
					es.setEseedShouldCost(esf.getEseedShouldCost());// 修改子订单中的应收费用
					es.setEseedRealityCost(esf.getEseedRealityCost());// 修改子订单中的实收费用
					es.setEseedClientAccounts(esf.getEseedClientAccounts());// 修改子订单中的结算方式
					es.setEseedSecondWork(esf.getEseedSecondWork());// 修改子订单中的二次作业重量
					es.setEseedSwshouldCost(esf.getEseedSwshouldCost());// 修改子订单中的二次作业应收费用
					es.setEseedSwrealityCost(esf.getEseedSwrealityCost());// 修改子订单中的二次作业的实收费用
					es.setEseedRemark(es.getEseedRemark() + ","
							+ esf.getEseedRemark());// 修改备注
					boolean ok = this.exportOperateService.update(eo);
					boolean oks = this.exportSeedService.update(es);
					if (ok && oks) {
						request.getSession().setAttribute("neibuxiugaiMessage",
								"修改成功！");
					} else {
						request.getSession().setAttribute("neibuxiugaiMessage",
								"修改失败！");
					}
				} else {
					request.getSession().setAttribute("neibuxiugaiMessage",
							"修改失败！");
				}
			} else {
				request.getSession()
						.setAttribute("neibuxiugaiMessage", "修改失败！");
			}
		} else {
			request.getSession().setAttribute("neibuxiugaiMessage", "修改失败！");
		}

		// 返回到对应的页面
		return super.execute(mapping, form, request, response);
	}

	// 通过客户进行查询
	public ActionForward getClientChaXun(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ExportSeedForm esf = (ExportSeedForm) form;
		if (esf != null) {
			if (esf.getBegin() == null) {
				esf.setBegin("2015-6-6 12:12:12");
			}
			if (esf.getFinish() == null) {
				esf.setFinish(pt.getTimes());
			}
			if (esf.getGoodsName() == null) {
				esf.setGoodsName("");
			}
			if (esf.getZongbianhao() == null) {
				esf.setZongbianhao("");
			}
			if (esf.getKehubianhao() == null) {
				esf.setKehubianhao("");
			}

			int pageNow = 1;
			if (request.getParameter("pageNow") != null) {
				pageNow = Integer.parseInt(request.getParameter("pageNow"));
			}
			int pageCount = this.exportService.getClientAllByCount(
					esf.getBegin(), esf.getFinish(), esf.getGoodsName(),
					esf.getZongbianhao(), esf.getKehubianhao(),
					esf.getClientId(), pr.getRow());
			if (pageNow >= pageCount) {
				pageNow = pageCount;
			}
			if (pageNow <= 1) {
				pageNow = 1;
			}
			List<ExportSeed> elist = this.exportService.getClientAllByPage(
					esf.getBegin(), esf.getFinish(), esf.getGoodsName(),
					esf.getZongbianhao(), esf.getKehubianhao(),
					esf.getClientId(), pageNow, pr.getRow());
			// 将查询内容保存到request中
			request.setAttribute("clientexportList", elist);
			// 将当前页保存到session中
			request.getSession().setAttribute("pageNow", pageNow);
		}
		// 返回到对应的页面
		return super.execute(mapping, form, request, response);
	}

	// 当客户点击详情进入到详情页面的时候，通过总订单编号查询，总订单下面的子订单，显示出对应的货物信息
	public ActionForward getClientXiangQing(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ExportForm ef = (ExportForm) form;
		if (ef != null) {
			List<ExportSeed> eslist = this.exportSeedService.getExportId(ef
					.getExportId());// 通过总订单编号查询对应的子订单
			// 保存到request中
			request.setAttribute("clientEseedList", eslist);
		}

		// 返回到对应的页面
		return super.execute(mapping, form, request, response);
	}

	// 查询是非作废的订单，可以通过起始日期，结束日期，客户的名称或者全拼或者助记符，通过订单编号，客户订单编号，货物的名称，规格，产地，品类进行模糊的查询
	public ActionForward getChuKuLiShi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/* 设置响应头允许ajax跨域访问 */
		response.setHeader("Access-Control-Allow-Origin", "*");
		/* 星号表示所有的异域请求都可以接受， */
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");

		ExportSeedForm esf = (ExportSeedForm) form;
		if (esf.getBegin() == null) {
			esf.setBegin("");// 起始的日期
		}
		if (esf.getFinish() == null || esf.getFinish().equals("")) {
			esf.setFinish(pt.getTimes());// 结束的日期
		}

		if (esf.getJiancheng() == null) {
			esf.setJiancheng("");// 客户的名称，助记符，全拼等
		}
		if (esf.getExport() == null) {
			esf.setExport("");
		}
		if (esf.getKehubianhao() == null) {
			esf.setKehubianhao("");
		}
		if (esf.getGoodsName() == null) {
			esf.setGoodsName("");
		}
		int pageNow = 1;
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}

		int pageCount = this.exportSeedService.getChuKuLiShiByPageCount(
				esf.getBegin(), esf.getFinish(), esf.getExport(),
				esf.getKehubianhao(), esf.getGoodsName(), esf.getJiancheng(),
				30);
		JSONArray array = new JSONArray();
		PrintWriter out = response.getWriter();
		if (pageNow >= pageCount) {
			pageNow = pageCount;
		}
		if (pageNow <= 1) {
			pageNow = 1;
		}

		List<ExportSeed> eslist = this.exportSeedService.getChuKuLiShiByPage(
				esf.getBegin(), esf.getFinish(), esf.getExport(),
				esf.getKehubianhao(), esf.getGoodsName(), esf.getJiancheng(),
				pageNow, 30);

		if (eslist.size() <= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		DecimalFormat df = new DecimalFormat("###########0.000");
		if (eslist.size() > 0) {
			double ycsum = 0.0;
			double scsum = 0.0;
			for (ExportSeed es : eslist) {
				JSONObject obj = new JSONObject();
				obj.put("id", es.getExport().getExportId());// 保存出库的总订单的编号
				obj.put("zid", es.getEseedId());// 保存出库子订单的编号
				obj.put("huozhu", es.getExport().getClient()
						.getClientAbbreviation());// 保存出库货主的简称

				obj.put("yunshu", es.getExport().getExportCarryType());// 保存出库的运输的方式
				obj.put("huowu", es.getGoods().getGoodsName());// 保存出库货物的名称
				obj.put("kehudanhao", es.getExport().getExportClientNumber());// 保存客户单号
				obj.put("zhuangtai", es.getEseedOrderStatus());// 保存订单状态
				obj.put("faqishijian", es.getExport().getExportReateTime());// 保存出库的发起时间
				obj.put("zhongliang", es.getEseedShouldWeight());// 保存出库的应出重量
				obj.put("shichuweight", es.getEseedRealityWeight() == null ? 0.0 : es.getEseedRealityWeight());// 保存出库的实出的重量
				obj.put("pageNow", pageNow);// 保存当前页
				obj.put("faqiren", es.getExport().getExportFaQiRen());
				obj.put("result", "notnull");
				ycsum += (es.getEseedShouldWeight() == null ? 0.0 : es
						.getEseedShouldWeight());// 应出重量相加
				scsum += (es.getEseedRealityWeight() == null ? 0.0 : es
						.getEseedRealityWeight());// 实出重量相加
				obj.put("ycsum", df.format(ycsum));// 保存应出
				obj.put("scsum", df.format(scsum));// 保存实出
				obj.put("pageCount", pageCount);
				array.add(obj);
			}
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		return null;
	}

	// 当点击订单查询中过户订单查看性情的时候通过过户子订单的id进行查询
	public ActionForward getXiangQing(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ExportSeedForm esf = (ExportSeedForm) form;
		if (esf != null) {
			ExportSeed es = this.exportSeedService.getExportSeedId(URLDecoder
					.decode(esf.getEseedId(), "UTF-8"));
			// 将查询到的类保存到request中
			request.setAttribute("es", es);
			List<ExportOperate> eolist = this.exportOperateService
					.getZidingdan(URLDecoder.decode(esf.getEseedId(), "UTF-8"));
			request.setAttribute("eolist", eolist);
			//通过操作订单编号查询
			if(request.getParameter("caozuoId")!=null){
				String caozuoId = request.getParameter("caozuoId").toString();
				ExportOperate eo = this.exportOperateService.getExportOperateId(URLDecoder.decode(caozuoId, "UTF-8"));
				request.setAttribute("eo", eo);
			}
		}

		if (request.getParameter("ff") != null) {
			if (request.getParameter("ff").equals("dayin")) {
				if(request.getParameter("p")!=null){
					List<ExportOperate> eolist = this.exportOperateService
							.getZidingdan(URLDecoder.decode(esf.getEseedId(), "UTF-8"));
					if(eolist!=null){
						if(eolist.size()>0){
							request.setAttribute("list", "list");
							return mapping.findForward("goDaYin");
						}
					}
					//通过操作订单编号查询
					if(request.getParameter("caozuoId")!=null){
						String caozuoId = request.getParameter("caozuoId").toString();
						ExportOperate eo = this.exportOperateService.getExportOperateId(URLDecoder.decode(caozuoId, "UTF-8"));
						request.setAttribute("eo", eo);
					}
				}
				return mapping.findForward("goDaYin");
			}
		}
		if (request.getParameter("ff") != null) {
			if (request.getParameter("ff").equals("update")) {
				return mapping.findForward("goUpdate");
			}
			if (request.getParameter("ff").equals("client")) {
				return mapping.findForward("goClient");
			}
		}
		if (request.getParameter("ff") != null) {
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

	// 查询作废的订单，可以通过起始日期，结束日期，客户的名称或者全拼或者助记符，通过订单编号，客户订单编号，货物的名称，规格，产地，品类进行模糊的查询
	public ActionForward getChuKuLiShiZuoFei(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ExportSeedForm esf = (ExportSeedForm) form;
		if (esf.getBegin() == null) {
			esf.setBegin("");// 起始的日期
		}
		if (esf.getFinish() == null || esf.getFinish().equals("")) {
			esf.setFinish(pt.getTimes());// 结束的日期
		}

		if (esf.getJiancheng() == null) {
			esf.setJiancheng("");// 客户的名称，助记符，全拼等
		}
		if (esf.getExport() == null) {
			esf.setExport("");
		}
		if (esf.getKehubianhao() == null) {
			esf.setKehubianhao("");
		}
		if (esf.getGoodsName() == null) {
			esf.setGoodsName("");
		}
		int pageNow = 1;
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}

		int pageCount = this.exportSeedService.getChuKuLiShiZuoFeiByPageCount(
				esf.getBegin(), esf.getFinish(), esf.getExport(),
				esf.getKehubianhao(), esf.getGoodsName(), esf.getJiancheng(),
				30);
		JSONArray array = new JSONArray();
		PrintWriter out = response.getWriter();
		if (pageNow >= pageCount) {
			pageNow = pageCount;
		}
		if (pageNow <= 1) {
			pageNow = 1;
		}

		List<ExportSeed> eslist = this.exportSeedService
				.getChuKuLiShiZuoFeiByPage(esf.getBegin(), esf.getFinish(),
						esf.getExport(), esf.getKehubianhao(),
						esf.getGoodsName(), esf.getJiancheng(), pageNow, 30);

		if (eslist.size() <= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}

		if (eslist.size() > 0) {
			for (ExportSeed es : eslist) {
				JSONObject obj = new JSONObject();
				obj.put("id", es.getExport().getExportId());// 保存出库的总订单的编号
				obj.put("zid", es.getEseedId());// 保存出库子订单的编号
				obj.put("huozhu", es.getExport().getClient()
						.getClientAbbreviation());// 保存出库货主的简称

				obj.put("yunshu", es.getExport().getExportCarryType());// 保存出库的运输的方式
				obj.put("huowu", es.getGoods().getGoodsName());// 保存出库货物的名称
				obj.put("kehudanhao", es.getExport().getExportClientNumber());// 保存客户单号
				obj.put("zhuangtai", es.getEseedOrderStatus());// 保存订单状态
				obj.put("faqishijian", es.getExport().getExportReateTime());// 保存出库的发起时间
				obj.put("zhongliang", es.getEseedShouldWeight());// 保存出库的应出重量
				obj.put("shichuweight", es.getEseedRealityWeight() == null ? 0
						: es.getEseedRealityWeight());// 保存出库的实出的重量
				obj.put("pageNow", pageNow);// 保存当前页
				obj.put("pageCount", pageCount);
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
		ExportSeedForm esf = (ExportSeedForm) form;
		if (esf.getBegin() == null) {
			esf.setBegin("");
		}

		if (esf.getFinish() == null || esf.getFinish().equals("")) {
			esf.setFinish(pt.getTimes());
		} 
		// 获得总页数
		int pageCount = exportSeedService.getChukuCountAll(clientId,esf.getBegin(),esf.getFinish(),
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

		List<ExportSeed> exportSeedList = exportSeedService.getChukuInfoAll(
				clientId,esf.getBegin(),esf.getFinish(), pageNow2, pageRow.getRow());

		System.out.println("size:------" + exportSeedList.size());

		JSONArray array = new JSONArray();
		for (ExportSeed c : exportSeedList) {
			JSONObject obj = new JSONObject();

			obj.put("exportClientNumber", c.getExport().getExportClientNumber());// 客户单号
			obj.put("goodsName", c.getGoods().getGoodsName());// 货物名称
			obj.put("goodsStandardName", c.getGoods()// 货物规格
					.getGoodsStandard().getGoodsStandardName());
			obj.put("goodsYieldlyName", c.getGoods()// 货物产地
					.getGoodsYieldly().getGoodsYieldlyName());
			obj.put("exportCarryType", c.getExport().getExportCarryType());// 运输方式
			obj.put("goodsCategoryName", c.getGoods().getGoodsCategory()
					.getGoodsCategoryName());// 货物品类
			obj.put("goodsQualityName", c.getGoods().getGoodsQuality()
					.getGoodsQualityName());// 货物材质
			obj.put("exportDriverTel", c.getExport().getExportDriverTel());// 联系方式
			// 将子订单id放入隐藏域中的值
			obj.put("seedId", c.getEseedId());
			obj.put("zongId", c.getExport().getExportId());

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
		int pageCount = exportSeedService.getChukuCount(clientId, "", startTime, endTime, kehuDanhao, goodsName,pageRow);

		request.setAttribute("pageCount", pageCount);

		int pageNow2 = 1;
		// 获取用户输入页数
		// 当前页
		if (s_pageNows != null) {
			pageNow2 = fenye(s_pageNows, pageCount);
		}

		List<ExportSeed> exportSeedList = exportSeedService.getChukuInfo(clientId, "", startTime, endTime, kehuDanhao, goodsName, pageNow2, pageRow);

		JSONArray array = new JSONArray();
		// 判断，如果总页数为0，即：没有数据，不让访问数据库，直接return
		if (exportSeedList.size() <= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		System.out.println(exportSeedList.size());
		for (ExportSeed c : exportSeedList) {
			JSONObject obj = new JSONObject();
			obj.put("result","notnull");
			obj.put("id", c.getExport().getExportId());//保存总订单编号
			obj.put("zid", c.getEseedId());//保存子订单编号
			obj.put("huozhu", c.getExport().getClient().getClientAbbreviation());//保存货主
			obj.put("yunshu", c.getExport().getExportCarryType());//保存运输方式
			obj.put("huowu", c.getGoods().getGoodsName());//保存货物名称
			obj.put("kehudanhao", c.getExport().getExportClientNumber());//保存客户单号
			obj.put("faqishijian", c.getExport().getExportReateTime());//保存发起时间
			obj.put("zhongliang", c.getEseedShouldWeight());//保存应出重量
			obj.put("shichuweight", c.getEseedRealityWeight());//保存实出重量
			obj.put("zhuangtai", c.getEseedOrderStatus());//保存订单状态
			
			obj.put("exportClientNumber", c.getExport().getExportClientNumber());// 客户单号
			obj.put("goodsName", c.getGoods().getGoodsName());// 货物名称
			obj.put("goodsStandardName", c.getGoods().getGoodsStandard().getGoodsStandardName());// 货物规格
			obj.put("goodsYieldlyName", c.getGoods().getGoodsYieldly().getGoodsYieldlyName());// 货物产地
			obj.put("exportCarryType", c.getExport().getExportCarryType());// 运输方式
			obj.put("goodsCategoryName", c.getGoods().getGoodsCategory().getGoodsCategoryName());// 货物品类
			obj.put("goodsQualityName", c.getGoods().getGoodsQuality().getGoodsQualityName());// 货物材质
			obj.put("exportDriverTel", c.getExport().getExportDriverTel());// 联系方式
			// 将子订单id放入隐藏域中的值
			obj.put("seedId", c.getEseedId());
			obj.put("zongId", c.getExport().getExportId());
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
	public ActionForward getAllExportApp(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String id = new String(request.getParameter("ziId").getBytes(
				"ISO-8859-1"), "utf-8");

		ExportSeed exportSeed = exportSeedService.getExportSeedId(id);

		// 查询出最后操作时间
		List<ExportOperate> listExportOperate = exportOperateService
				.getExportSeedId(exportSeed.getEseedId());
		String finishTime = null;
		if (listExportOperate.size() > 0) {
			finishTime = listExportOperate.get(0).getEoperateCollectTime();
		}
		request.setAttribute("finishTime", finishTime);

		request.setAttribute("exportSeed", exportSeed);
		return mapping.findForward("getAllExportApp");
	}

	// app客户查询所有作废订单
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
		int pageCount = exportSeedService.getChukuCountAllPash(clientId,
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

		List<ExportSeed> exportSeedList = exportSeedService
				.getChukuInfoAllPash(clientId, pageNow2, pageRow.getRow());

		JSONArray array = new JSONArray();
		for (ExportSeed c : exportSeedList) {
			JSONObject obj = new JSONObject();

			obj.put("exportClientNumber", c.getExport().getExportClientNumber());// 客户单号
			obj.put("goodsName", c.getGoods().getGoodsName());// 货物名称
			obj.put("goodsStandardName", c.getGoods()// 货物规格
					.getGoodsStandard().getGoodsStandardName());
			obj.put("goodsYieldlyName", c.getGoods()// 货物产地
					.getGoodsYieldly().getGoodsYieldlyName());
			obj.put("exportCarryType", c.getExport().getExportCarryType());// 运输方式
			obj.put("goodsCategoryName", c.getGoods().getGoodsCategory()
					.getGoodsCategoryName());// 货物品类
			obj.put("goodsQualityName", c.getGoods().getGoodsQuality()
					.getGoodsQualityName());// 货物材质
			obj.put("exportDriverTel", c.getExport().getExportDriverTel());// 联系方式
			// 将子订单id放入隐藏域中的值
			obj.put("seedId", c.getEseedId());

			// 保存页数
			obj.put("pageCount", pageCount);
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();

	}

	// app客户有条件查询作废订单
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
		int pageCount = exportSeedService.getChukuCountPash(clientId, startTime, endTime, kehuDanhao, goodsName, pageRow);

		request.setAttribute("pageCount", pageCount);

		int pageNow2 = 1;
		// 获取用户输入页数
		// 当前页
		if (s_pageNows != null) {
			pageNow2 = fenye(s_pageNows, pageCount);
		}

		List<ExportSeed> exportSeedList = exportSeedService.getChukuInfoPash(clientId, startTime, endTime, kehuDanhao, goodsName, pageNow2, pageRow);

		JSONArray array = new JSONArray();
		// 判断，如果总页数为0，即：没有数据，不让访问数据库，直接return
		if (exportSeedList.size()<= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		for (ExportSeed c : exportSeedList) {
			JSONObject obj = new JSONObject();
			obj.put("result","notnull");
			obj.put("id", c.getExport().getExportId());//保存总订单编号
			obj.put("zid", c.getEseedId());//保存子订单编号
			obj.put("huozhu", c.getExport().getClient().getClientAbbreviation());//保存货主
			obj.put("yunshu", c.getExport().getExportCarryType());//保存运输方式
			obj.put("huowu", c.getGoods().getGoodsName());//保存货物名称
			obj.put("kehudanhao", c.getExport().getExportClientNumber());//保存客户单号
			obj.put("faqishijian", c.getExport().getExportReateTime());//保存发起时间
			obj.put("zhongliang", c.getEseedShouldWeight());//保存应出重量
			obj.put("shichuweight", c.getEseedRealityWeight());//保存实出重量
			obj.put("zhuangtai", c.getEseedOrderStatus());//保存订单状态

			obj.put("exportClientNumber", c.getExport().getExportClientNumber());// 客户单号
			obj.put("goodsName", c.getGoods().getGoodsName());// 货物名称
			obj.put("goodsStandardName", c.getGoods().getGoodsStandard().getGoodsStandardName());// 货物规格
			obj.put("goodsYieldlyName", c.getGoods().getGoodsYieldly().getGoodsYieldlyName());// 货物产地
			obj.put("exportCarryType", c.getExport().getExportCarryType());// 运输方式
			obj.put("goodsCategoryName", c.getGoods().getGoodsCategory().getGoodsCategoryName());// 货物品类
			obj.put("goodsQualityName", c.getGoods().getGoodsQuality().getGoodsQualityName());// 货物材质
			obj.put("exportDriverTel", c.getExport().getExportDriverTel());// 联系方式
			// 将子订单id放入隐藏域中的值
			obj.put("seedId", c.getEseedId());
			// 保存页数
			obj.put("pageCount", pageCount);
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
		return null;
	}

	// 将订单改为作废
	public void goDingdanZuofei(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		boolean ok = false;
		boolean seedOk = false;
		String id = new String(request.getParameter("id")
				.getBytes("ISO-8859-1"), "utf-8");
		ExportSeed exportSeed = exportSeedService.getExportSeedId(id);
		// 当子订单状态为计划入库时,客户可以修改为订单作废
		if (exportSeed.getEseedOrderStatus().equals("计划出库")) {
			// 修改子订单状态为订单作废
			exportSeed.setEseedOrderStatus("订单作废");
			ok = exportSeedService.update(exportSeed);
			// 修改总订单状态为订单作废
			if (ok) {
				Export export = exportService.getExportId(exportSeed
						.getExport().getExportId());
				export.setExportOrderStatus("订单作废");
				seedOk = exportService.update(export);
			}
			if (seedOk) {
				this.clientGoodsService.ZengjiaKucun(exportSeed.getExport()
						.getClient().getClientId(), exportSeed.getGoods()
						.getGoodsId(), exportSeed.getEseedShouldWeight(), 0.0);
				out.print("<script>alert('操作成功！');window.location.href='/XGProject/app-web/indent-search.jsp'</script>");
			} else {
				out.print("<script>alert('操作失败！');window.location.href='/XGProject/app-web/indent-search.jsp'</script>");
			}
		} else {
			out.print("<script>alert('操作失败！订单可能正在操作中...');window.location.href='/XGProject/app-web/indent-search.jsp'</script>");
		}

	}

	// -------------结算查询

	// app客户查询所有结算订单
	public void getChuJiesuanApp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		// 返回行数
		PageRow pageRow = new PageRow();

		// 获取页面传入的值
		String s_pageNows = request.getParameter("pageNow");

		int clientId = Integer.parseInt(request.getSession()
				.getAttribute("clientId").toString());
		ExportSeedForm esf = (ExportSeedForm) form;
		if (esf.getBegin() == null) {
			esf.setBegin("");
		}

		if (esf.getFinish() == null || esf.getFinish().equals("")) {
			esf.setFinish(pt.getTimes());
		} 
		// 获得总页数
		int pageCount = exportSeedService.getChukuCountAll(clientId,esf.getBegin(),esf.getFinish(),
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

		List<ExportSeed> exportSeedList = exportSeedService.getChukuInfoAll(
				clientId,esf.getBegin(),esf.getFinish(), pageNow2, pageRow.getRow());

		// 根据客户的id查询客户的折扣
		Client client = clientService.getClientId(clientId);
		// 得到折扣
		Double zhekou = client.getClientAgio();

		// 得到汽运的单价
		Double QYDanjia = costTypeService.getQiYunRuKu() * zhekou;
		// 得到火运的单价
		Double HYDanjia = costTypeService.getHuoYunRuKu() * zhekou;

		JSONArray array = new JSONArray();
		for (ExportSeed c : exportSeedList) {
			JSONObject obj = new JSONObject();

			obj.put("zongId", c.getExport().getExportId());// 总订单编号
			obj.put("clientNumber", c.getExport().getExportClientNumber());// 客户单号
			obj.put("jiesuanType", c.getEseedClientAccounts());// 结算方式
			obj.put("carryType", c.getExport().getExportCarryType());// 运输方式
			obj.put("sijiName", c.getExport().getExportDriverName());// 司机姓名
			obj.put("goodsName", c.getGoods().getGoodsName());// 货物名称
			obj.put("realityWeight", c.getEseedRealityWeight());// 货物实际重量
			obj.put("shouldCost", c.getEseedShouldCost());// 应收钱数
			obj.put("realityCost", c.getEseedRealityCost());// 实收钱数
			obj.put("QYDanjia", QYDanjia);// 汽运费用单价
			obj.put("HYDanjia", HYDanjia);// 火运费用单价

			// 放入隐藏域中的值
			obj.put("seedId", c.getEseedId());// 子订单id
			// 保存页数
			obj.put("pageCount", pageCount);

			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();

	}

	// app客户有条件查询结算订单
	public void getChuJiesuanAppTj(ActionMapping mapping, ActionForm form,
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
		int pageCount = exportSeedService.getChukuCount(clientId, jiesuanType,
				startTime, endTime, kehuDanhao, goodsName, pageRow);

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

		List<ExportSeed> exportSeedList = exportSeedService.getChukuInfo(
				clientId, jiesuanType, startTime, endTime, kehuDanhao,
				goodsName, pageNow2, pageRow);

		// 根据客户的id查询客户的折扣
		Client client = clientService.getClientId(clientId);
		// 得到折扣
		Double zhekou = client.getClientAgio();

		// 得到汽运的单价
		Double QYDanjia = costTypeService.getQiYunRuKu() * zhekou;
		// 得到火运的单价
		Double HYDanjia = costTypeService.getHuoYunRuKu() * zhekou;

		JSONArray array = new JSONArray();
		for (ExportSeed c : exportSeedList) {
			JSONObject obj = new JSONObject();

			obj.put("zongId", c.getExport().getExportId());// 总订单编号
			obj.put("clientNumber", c.getExport().getExportClientNumber());// 客户单号
			obj.put("jiesuanType", c.getEseedClientAccounts());// 结算方式
			obj.put("carryType", c.getExport().getExportCarryType());// 运输方式
			obj.put("sijiName", c.getExport().getExportDriverName());// 司机姓名
			obj.put("goodsName", c.getGoods().getGoodsName());// 货物名称
			obj.put("realityWeight", c.getEseedRealityWeight());// 货物实际重量
			obj.put("shouldCost", c.getEseedShouldCost());// 应收钱数
			obj.put("realityCost", c.getEseedRealityCost());// 实收钱数
			obj.put("QYDanjia", QYDanjia);// 汽运费用单价
			obj.put("HYDanjia", HYDanjia);// 火运费用单价
			obj.put("yewu", "出库业务");

			// 放入隐藏域中的值
			obj.put("seedId", c.getEseedId());// 子订单id
			// 保存页数
			obj.put("pageCount", pageCount);

			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();

	}

	// app客户查询所有订单
	public ActionForward getChujiesuanApp(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String finishTime = "";
		String shoufeiRen = "";
		// 下站应收费用
		Double XZshouldCost = 0.0;
		// 下站实收费用
		Double XZshijieCost = 0.0;

		Xiazhanfei xiazhanfei = new Xiazhanfei();

		String id = new String(request.getParameter("ziId").getBytes(
				"ISO-8859-1"), "utf-8");

		ExportSeed exportSeed = exportSeedService.getExportSeedId(id);

		// 根据总订单id查询下站费的信息
		List<Xiazhanfei> listXiazhan = xiazhanfeiService
				.getAllzongId(exportSeed.getExport().getExportId());

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

		// 查询出最后操作时间
		List<ExportOperate> listExportOperate = exportOperateService
				.getExportSeedId(exportSeed.getEseedId());

		if (listExportOperate.size() > 0) {
			// 结束时间
			finishTime = listExportOperate.get(0).getEoperateCollectTime();
			// 收费人
			shoufeiRen = listExportOperate.get(0)
					.getInteriorUserByEoperateCollectMan().getIuserName();
		}

		// 保存类listXiazhan
		request.setAttribute("xiazhanfei", xiazhanfei);
		// 保存下站应收费用
		request.setAttribute("XZshouldCost", XZshouldCost);
		// 保存下站实收费用
		request.setAttribute("XZshijieCost", XZshijieCost);

		// 保存操作完成时间
		request.setAttribute("finishTime", finishTime);
		// 保存收费人
		request.setAttribute("shoufeiRen", shoufeiRen);

		request.setAttribute("exportSeed", exportSeed);
		return mapping.findForward("getChujiesuanApp");

	}

	// 通过结算方式查询出库订单
	public ActionForward QueryJieSuan(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ExportSeedForm esf = (ExportSeedForm) form;
		if (esf.getBegin() == null || esf.getBegin().equals("")) {
			esf.setBegin(pt.getNowJianYi());
		} else {
			esf.setBegin(esf.getBegin());
		}

		if (esf.getFinish() == null || esf.getFinish().equals("")) {
			esf.setFinish(pt.getTimes());
		} else {
			esf.setFinish(esf.getFinish());
		}
		if (esf.getEseedClientAccounts() == null) {
			esf.setEseedClientAccounts("");
		}
		if (esf.getJiancheng() == null) {
			esf.setJiancheng("");
		}
		String shoufeiren = "";
		if (request.getParameter("shoufeiren") != null) {
			shoufeiren = request.getParameter("shoufeiren").toString();
		}
		int pageNow = 1;
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}
		int pageCount = this.exportSeedService.getJieSuanQueryByCount(
				esf.getJiancheng(), esf.getBegin(), esf.getFinish(),
				esf.getEseedClientAccounts(), 30, shoufeiren);
		if (pageNow >= pageCount) {
			pageNow = pageCount;
		} else if (pageNow <= 1) {
			pageNow = 1;
		}
		List<ExportSeed> eslist = this.exportSeedService.getJieSuanQueryByPage(
				esf.getJiancheng(), esf.getBegin(), esf.getFinish(),
				esf.getEseedClientAccounts(), pageNow, 30, shoufeiren);

		JSONArray array = new JSONArray();
		PrintWriter out = response.getWriter();
		if (eslist.size() <= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		for (int i = 0; i < eslist.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("zongid", eslist.get(i).getExport().getExportId());// 保存总编号
			obj.put("zid", eslist.get(i).getEseedId());// 保存子订单编号
			obj.put("kehudanhao", eslist.get(i).getExport()
					.getExportClientNumber());// 保存客户好
			obj.put("kehu", eslist.get(i).getExport().getClient()
					.getClientAbbreviation());// 简称
			obj.put("time", eslist.get(i).getExport().getExportReateTime());// 订单生成日期
			obj.put("goods", eslist.get(i).getGoods().getGoodsName());// 货物名称
			obj.put("ycweight", eslist.get(i).getEseedShouldWeight());// 应出重量
			obj.put("scweight",
					(eslist.get(i).getEseedRealityWeight() == null ? 0.0
							: eslist.get(i).getEseedRealityWeight()));// 实出重量
			obj.put("yunshu", eslist.get(i).getExport().getExportCarryType());// 运输方式
			obj.put("chukucost",
					(eslist.get(i).getEseedShouldCost() == null ? 0.0 : eslist
							.get(i).getEseedShouldCost()));// 应收出库费
			obj.put("sscost",
					(eslist.get(i).getEseedRealityCost() == null ? 0.0 : eslist
							.get(i).getEseedRealityCost()));// 实收出库费
			obj.put("ercizuoye",
					(eslist.get(i).getEseedSwshouldCost() == null ? 0.0
							: eslist.get(i).getEseedSwshouldCost()));// 二次作业应收费用
			obj.put("ercishishou",
					(eslist.get(i).getEseedSwrealityCost() == null ? 0.0
							: eslist.get(i).getEseedSwrealityCost()));// 二次作业实收
			List<Xiazhanfei> xzlist = this.xiazhanfeiService
					.getAllzongId(eslist.get(i).getExport().getExportId());
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
			obj.put("jiesuan", eslist.get(i).getEseedClientAccounts());// 结算方式
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

	// app中进行查询某个客户的日结费用
	public ActionForward getAppQueryDayCost(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ExportSeedForm esf = (ExportSeedForm) form;
		JSONArray array = new JSONArray();
		PrintWriter out = response.getWriter();
		if (esf.getGoodsName() == null) {
			esf.setGoodsName("");
		}
		if (esf.getClientId() == null) {
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
		int pageCount = this.exportSeedService.getAppQueryDayCostByCount(
				esf.getClientId(), esf.getGoodsName(), pageNow, 3);
		if (pageNow >= pageCount) {
			pageNow = pageCount;
		} else if (pageNow <= 1) {
			pageNow = 1;
		}

		List<ExportSeed> eslist = this.exportSeedService
				.getAppQueryDayCostByPage(esf.getClientId(),
						esf.getGoodsName(), pageNow, 3);
		if (eslist.size() <= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "no");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}

		for (int i = 0; i < eslist.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("zongid", eslist.get(i).getExport().getExportId());// 保存中订单的编号
			obj.put("kehuid", eslist.get(i).getExport().getExportClientNumber());// 保存客户的单号
			obj.put("shouldcost", eslist.get(i).getEseedShouldCost());// 保存应收的费用
			obj.put("reladycost", eslist.get(i).getEseedRealityCost());// 保存实收的费用
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

	// 手机客户查询返回json格式

	public void getAllShoujiApp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 获取前台传入子订单id

		String id = request.getParameter("ziId");

		ExportSeed exportSeed = exportSeedService.getExportSeedId(id);

		// 查询出最后操作时间
		List<ExportOperate> listExportOperate = exportOperateService
				.getExportSeedId(exportSeed.getEseedId());
		String finishTime = null;
		if (listExportOperate.size() > 0) {
			finishTime = listExportOperate.get(0).getEoperateCollectTime();
		}

		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		if (exportSeed != null) {

			obj.put("ziDingdanId", exportSeed.getEseedId());
			obj.put("changjianTime", exportSeed.getExport()
					.getExportReateTime());
			obj.put("kehuName", exportSeed.getExport().getClient()
					.getClientFirmName());
			obj.put("kehuDanhao", exportSeed.getExport()
					.getExportClientNumber());
			obj.put("yunshuType", exportSeed.getExport().getExportCarryType());
			obj.put("cheHao", exportSeed.getExport().getExportWagonNumber());
			obj.put("sijiName", exportSeed.getExport().getExportDriverName());

			obj.put("sijiTel", exportSeed.getExport().getExportDriverTel());
			obj.put("pinlei", exportSeed.getGoods().getGoodsCategory()
					.getGoodsCategoryName());
			obj.put("goodsName", exportSeed.getGoods().getGoodsName());
			obj.put("guige", exportSeed.getGoods().getGoodsStandard()
					.getGoodsStandardName());

			obj.put("caizhi", exportSeed.getGoods().getGoodsQuality()
					.getGoodsQualityName());
			obj.put("shuxin", exportSeed.getGoods().getGoodsProperty()
					.getGoodsPropertyName());
			obj.put("chandi", exportSeed.getGoods().getGoodsYieldly()
					.getGoodsYieldlyName());
			obj.put("shouldWeight", exportSeed.getEseedShouldWeight());

			obj.put("yinShoufeiYong", exportSeed.getEseedShouldCost());
			obj.put("shiShoufeiYong", exportSeed.getEseedRealityCost());

			obj.put("dingdanStatus", exportSeed.getEseedOrderStatus());
			obj.put("finishTime", finishTime);
			obj.put("beizhu", exportSeed.getEseedRemark());

		}

		out.print(obj.toString());
		out.flush();
		out.close();

	}

	// 查询配送的订单，
	public ActionForward getPeiSong(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ExportSeedForm esf = (ExportSeedForm) form;
		if (esf.getZongbianhao() == null) {
			esf.setZongbianhao("");
		}
		if (esf.getKehubianhao() == null) {
			esf.setKehubianhao("");
		}
		if (esf.getDanwei() == null) {
			esf.setDanwei("");
		}
		if (esf.getJiancheng() == null) {
			esf.setJiancheng("");
		}
		if (esf.getDanweizhujifu() == null) {
			esf.setDanweizhujifu("");
		}
		if (esf.getGoodsName() == null) {
			esf.setGoodsName("");
		}
		if (esf.getGuige() == null) {
			esf.setGuige("");
		}
		if (esf.getCaizhi() == null) {
			esf.setCaizhi("");
		}
		if (esf.getShuxing() == null) {
			esf.setShuxing("");
		}
		if (esf.getZhujifu() == null) {
			esf.setZhujifu("");
		}

		int pageNow = 1;
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}
		int pageCount = this.exportSeedService.getPeiSongByPageCount(
				"2015-6-6 12:12:12", pt.getTimes() + " 23:59:59",
				esf.getZongbianhao(), esf.getKehubianhao(), esf.getDanwei(),
				esf.getDanweizhujifu(), esf.getJiancheng(), esf.getGoodsName(),
				esf.getGuige(), esf.getCaizhi(), esf.getShuxing(),
				esf.getShuxing(), pr.getRow());
		request.getSession().setAttribute(
				"DCLchuku",
				this.exportSeedService.getPeiSongByPageCount(
						"2015-6-6 12:12:12", pt.getTimes() + " 23:59:59", "",
						"", "", "", "", "", "", "", "", "", 1));
		JSONArray array = new JSONArray();
		PrintWriter out = response.getWriter();
		System.out.println("pageNow是多少呢？输出看看：" + pageNow);
		if (pageNow > pageCount) {
			JSONObject obj = new JSONObject();
			obj.put("max", "maxs");
			array.add(obj);
			out.print(array.toString());
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
		List<ExportSeed> daichuliList = this.exportSeedService
				.getPeiSongByPage("2015-6-6 12:12:12", pt.getTimes(),
						esf.getZongbianhao(), esf.getKehubianhao(),
						esf.getDanwei(), esf.getDanweizhujifu(),
						esf.getJiancheng(), esf.getGoodsName(), esf.getGuige(),
						esf.getCaizhi(), esf.getShuxing(), esf.getShuxing(),
						pageNow, pr.getRow());

		if (daichuliList.size() > 0) {
			for (ExportSeed es : daichuliList) {
				JSONObject obj = new JSONObject();
				obj.put("id", es.getExport().getExportId());// 保存出库总订单的id
				obj.put("zid", es.getEseedId());// 保存出库子订单的id
				obj.put("huozhu", es.getExport().getClient()
						.getClientAbbreviation());// 保存发起的客户的简称
				obj.put("kehudanhao", es.getExport().getExportClientNumber());// 保存客户的订单编号
				obj.put("youxiao", es.getExport().getExportDefinedTwo());// 保存订单的有效期
				obj.put("zhuangtai", es.getEseedOrderStatus());// 保存订单的状态
				obj.put("yunshu", es.getExport().getExportCarryType());
				obj.put("chehao", es.getExport().getExportWagonNumber());// 保存车号
				obj.put("siji", es.getExport().getExportDriverName());// 保存司机的姓名
				obj.put("tel", es.getEseedDefinedOne());// 保存联系电话
				obj.put("pinlei", es.getGoods().getGoodsCategory()
						.getGoodsCategoryName());// 保存货物的品类
				obj.put("mingcheng", es.getGoods().getGoodsName());// 保存货物名称
				obj.put("guige", es.getGoods().getGoodsStandard()
						.getGoodsStandardName());// 保存货物规格
				obj.put("caizhi", es.getGoods().getGoodsQuality()
						.getGoodsQualityName());// 保存货物材质
				obj.put("shuxing", es.getGoods().getGoodsProperty()
						.getGoodsPropertyName());// 保存货物属性
				obj.put("chandi", es.getGoods().getGoodsYieldly()
						.getGoodsYieldlyName());// 保存货物产地
				obj.put("goodsId", es.getGoods().getGoodsId());// 保存货物的id
				obj.put("zhongliang", es.getEseedShouldWeight());// 保存应出的重量
				obj.put("shichuZL", es.getEseedRealityWeight());// 保存实出重量
				obj.put("faqitime", es.getExport().getExportReateTime());// 保存订单的发起时间
				obj.put("pageNow", pageNow);// 保存当前页
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
			// 返回到对应的页面
			return null;
		}

	}
}
