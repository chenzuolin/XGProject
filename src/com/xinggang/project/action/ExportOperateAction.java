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

import com.xinggang.project.entity.Export;
import com.xinggang.project.entity.ExportOperate;
import com.xinggang.project.entity.ExportSeed;
import com.xinggang.project.entity.InteriorUser;
import com.xinggang.project.entity.PaymentFashion;
import com.xinggang.project.entity.TarehouseDetail;
import com.xinggang.project.form.ExportOperateForm;
import com.xinggang.project.service.ClientGoodsService;
import com.xinggang.project.service.CostTypeService;
import com.xinggang.project.service.ExportOperateService;
import com.xinggang.project.service.ExportSeedService;
import com.xinggang.project.service.ExportService;
import com.xinggang.project.service.InteriorUserService;
import com.xinggang.project.service.LoginLogService;
import com.xinggang.project.service.PaymentFashionService;
import com.xinggang.project.service.TarehouseDetailService;
import com.xinggang.project.service.TarehouseGoodsService;
import com.xinggang.project.service.TarehouseService;
import com.xinggang.project.service.UpdateRecordService;
import com.xinggang.project.tools.PageRow;
import com.xinggang.project.tools.PresentTime;
import com.xinggang.project.tools.StatisticsWorking;

/**
 * 出库子订单操作类action
 * 
 * @author Administrator
 * 
 */
public class ExportOperateAction extends DispatchAction {

	// 出库订单操作表service
	private ExportOperateService exportOperateService;
	// 出库子订单service
	private ExportSeedService exportSeedService;
	// 库位service
	private TarehouseService TarehouseService;
	// 登录日志表service
	private LoginLogService loginLogService;
	// 内部人员service
	private InteriorUserService interiorUserService;
	// 货物库存service
	private TarehouseGoodsService tarehouseGoodsService;
	// 费用类型service
	private CostTypeService costTypeService;
	// 支付方式service
	private PaymentFashionService paymentFashionService;
	// 货物批次service
	private TarehouseDetailService tarehouseDetailService;
	// 出库总订单service
	private ExportService exportService;
	// 订单修改记录service
	private UpdateRecordService updateRecordService;
	// 客户货物service
	private ClientGoodsService clientGoodsService;

	// 时间工具类
	private PresentTime pt = new PresentTime();

	private PageRow pr = new PageRow();

	public void setClientGoodsService(ClientGoodsService clientGoodsService) {
		this.clientGoodsService = clientGoodsService;
	}

	public void setUpdateRecordService(UpdateRecordService updateRecordService) {
		this.updateRecordService = updateRecordService;
	}

	public void setExportService(ExportService exportService) {
		this.exportService = exportService;
	}

	public void setTarehouseDetailService(
			TarehouseDetailService tarehouseDetailService) {
		this.tarehouseDetailService = tarehouseDetailService;
	}

	public void setPaymentFashionService(
			PaymentFashionService paymentFashionService) {
		this.paymentFashionService = paymentFashionService;
	}

	public void setCostTypeService(CostTypeService costTypeService) {
		this.costTypeService = costTypeService;
	}

	public void setTarehouseGoodsService(
			TarehouseGoodsService tarehouseGoodsService) {
		this.tarehouseGoodsService = tarehouseGoodsService;
	}

	public void setInteriorUserService(InteriorUserService interiorUserService) {
		this.interiorUserService = interiorUserService;
	}

	public void setLoginLogService(LoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}

	public void setExportSeedService(ExportSeedService exportSeedService) {
		this.exportSeedService = exportSeedService;
	}

	public void setExportOperateService(
			ExportOperateService exportOperateService) {
		this.exportOperateService = exportOperateService;
	}

	public void setTarehouseService(TarehouseService tarehouseService) {
		TarehouseService = tarehouseService;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return super.execute(mapping, form, request, response);
	}

	// 查询准备出库的操作订单，审核未通过
	public ActionForward ZhunBiChuKu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ExportOperateForm eof = (ExportOperateForm) form;
		if (eof != null) {

			// 查询操作订单为准备出库的订单，并且通过指定的保管员查询
			List<ExportOperate> eolist = this.exportOperateService
					.getZhuangTaiBaoGuan("准备出库",
							eof.getInteriorUserByEoperateStoreman());// 通过保管和状态进行查询

			request.getSession().setAttribute("CZChuKu", eolist.size());

			JSONArray array = new JSONArray();
			PrintWriter out = response.getWriter();
			if (eolist.size() > 0) {
				for (ExportOperate eo : eolist) {
					JSONObject obj = new JSONObject();
					obj.put("id", eo.getExportSeed().getExport().getExportId());// 保存总订单的编号
					obj.put("zid", eo.getExportSeed().getEseedId());// 保存子订单的编号
					obj.put("cid", eo.getEoperateId());// 保存操作编号
					obj.put("huozhu", eo.getExportSeed().getExport()
							.getClient().getClientAbbreviation());// 保存客户的简称
					obj.put("kehuhao", eo.getExportSeed().getExport()
							.getExportClientNumber());// 保存客户单号
					obj.put("youxiao", eo.getExportSeed().getExport()
							.getExportDefinedTwo());// 保存订单的有效期
					obj.put("chaofa", eo.getExportSeed().getExport()
							.getExportDefinedOne());// 保存是否接受超发
					obj.put("yunshu", eo.getExportSeed().getExport()
							.getExportCarryType());// 保存运输方式
					obj.put("chehao", eo.getExportSeed().getExport()
							.getExportWagonNumber());// 保存车号
					obj.put("siji", eo.getExportSeed().getExport()
							.getExportDriverName());// 保存司机姓名
					obj.put("sijitel", eo.getExportSeed().getExport()
							.getExportDriverTel());// 保存司机电话
					obj.put("pinlei", eo.getExportSeed().getGoods()
							.getGoodsCategory().getGoodsCategoryName());// 保存货物品类
					obj.put("mingcheng", eo.getExportSeed().getGoods()
							.getGoodsName());// 保存货物名称
					obj.put("guige", eo.getExportSeed().getGoods()
							.getGoodsStandard().getGoodsStandardName());// 保存货物规格
					obj.put("caizhi", eo.getExportSeed().getGoods()
							.getGoodsQuality().getGoodsQualityName());// 保存货物材质
					obj.put("shuxing", eo.getExportSeed().getGoods()
							.getGoodsProperty().getGoodsPropertyName());// 保存货物属性
					obj.put("chandi", eo.getExportSeed().getGoods()
							.getGoodsYieldly().getGoodsYieldlyName());// 保存货物品类
					obj.put("yingchuweight", eo.getExportSeed()
							.getEseedShouldWeight());// 保存应出重量
					obj.put("faqitime", eo.getExportSeed().getExport()
							.getExportReateTime());// 保存发起的时间
					obj.put("diaoduyuan",
							eo.getInteriorUserByEoperateDispatcher() == null ? "无"
									: eo.getInteriorUserByEoperateDispatcher()
											.getIuserName());// 保存调度员
					obj.put("fenpeitime", eo.getEoperateDispatcherTime());// 保存调度员的分配时间
					obj.put("lisuan", eo.getEoperatePonderationTrue());// 保存是否过磅
					obj.put("kuwei", eo.getTarehouse().getTarehouseName());// 保存库位名称
					obj.put("remark", eo.getEoperateRemark());// 保存备注
					obj.put("fenpeiweight",
							eo.getEoperateDefinedTwo() == null ? 0.0 : eo
									.getEoperateDefinedTwo());
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

		// 返回到对应的页面
		return null;
	}

	// 保管点击开始作业，订单状态改变为正在出库
	public ActionForward ZhengZaiChuKu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ExportOperateForm eof = (ExportOperateForm) form;
		System.out.println(eof.getEoperateId());
		if (eof != null) {
			ExportOperate eo = this.exportOperateService
					.getExportOperateId(URLDecoder.decode(eof.getEoperateId(),
							"UTF-8"));// 通过编号查询
			if (eo != null) {
				try {
					eo.setEoperateDefinedOne("正在出库");// 订单状态改变
					eo.setEoperateScreateTime(pt.getTimes());// 设置保管员开始操作的时间
					this.exportOperateService.update(eo);
					this.loginLogService.updateLogin(request.getSession()
							.getAttribute("loginlogId").toString(),
							"接受出库订单，开始作业"
									+ eo.getExportSeed().getExport()
											.getExportId());// 记录到日志表中
					this.interiorUserService.updateIuserWork(eo
							.getInteriorUserByEoperateStoreman().getIuserId(),
							0);// 改变该保管的作业状态为作业1.否0.是
					request.setAttribute("eo", eo);// 将查询到的内容保存到request中
				} catch (Exception e) {

				}
			}
		}

		// 返回到对应的页面
		return mapping.findForward("goCaozuo");
	}

	public ActionForward updateCaozuo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ExportOperateForm eof = (ExportOperateForm) form;
		if (eof != null) {
			ExportOperate eo = this.exportOperateService
					.getExportOperateId(URLDecoder.decode(eof.getEoperateId(),
							"UTF-8"));// 通过编号查询
			if (eo != null) {
				request.setAttribute("eo", eo);// 将查询到的内容保存到request中
			}
		}
		return mapping.findForward("goupdate");
	}

	// 保管作业完成，点击提交
	public ActionForward ChuKuWanCheng(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ExportOperateForm eof = (ExportOperateForm) form;
		if (eof != null) {
			ExportOperate eo = this.exportOperateService.getExportOperateId(eof
					.getEoperateId());// 通过编号查询

			System.out.println("上传的操作订单的编号是：" + eof.getEoperateId());
			if (eo != null) {
				/*
				 * if (eo.getEoperatePonderationTrue().equals("过磅") &&
				 * eo.getEoperateRealityWeight() == null) {
				 * request.getSession().setAttribute("chukuwanchengMessage",
				 * "请联系司磅员，填写对应重量!"); } else {
				 */try {
					if (eo.getEoperatePonderationTrue().equals("理算")) {
						eo.setEoperateRealityWeight(eof
								.getEoperateRealityWeight());// 该保管员操作的重量
					}
					eo.setEoperateRealityNumber(eof.getEoperateRealityNumber());// 该保管员操作的件数
					eo.setEoperateSfinishTime(pt.getTimes());// 该高官员完成操作的时间
					eo.setEoperateCraneman(eof.getEoperateCraneman());// 天车工，保管填入的
					eo.setEoperateStevedore(eof.getEoperateStevedore());// 装卸工，多个，保管填入
					eo.setEoperateDefinedOne("正在审核");// 改变订单状态为正在审核
					eo.setEoperateTruckNum(eof.getEoperateTruckNum());// 设置保管员填入的车号
					eo.setEoperateRemark(eo.getEoperateRemark() + ","
							+ eof.getEoperateRemark());// 设置备注

					this.interiorUserService.updateIuserWork(eo
							.getInteriorUserByEoperateStoreman().getIuserId(),
							1);// 修改该保管员的作业状态为1，未作业

					boolean ok = this.exportOperateService.update(eo);
					if (ok) {
						this.loginLogService.updateLogin(request.getSession()
								.getAttribute("loginlogId").toString(),
								"出库操作完成，提交审核"
										+ eo.getExportSeed().getExport()
												.getExportId());// 记录到日志表中
						request.getSession().setAttribute(
								"chukuwanchengMessage", "提交成功！");
					} else {
						request.getSession().setAttribute(
								"chukuwanchengMessage", "提交失败！");
					}
				} catch (Exception e) {
				}
			} else {
				request.getSession().setAttribute("chukuwanchengMessage",
						"提交失败！");
			}
		} else {
			request.getSession().setAttribute("chukuwanchengMessage", "提交失败！");
		}

		// 返回到对应的页面
		PrintWriter out = response.getWriter();
		out.print("<script>alert('"
				+ request.getSession().getAttribute("chukuwanchengMessage")
				+ "');window.history.go(-2);</script>");
		return null;
	}

	// 查询订单中是过磅的订单,并且出库重量是为空的
	public ActionForward getGuoBang(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		/* 设置响应头允许ajax跨域访问 */
		response.setHeader("Access-Control-Allow-Origin", "*");
		/* 星号表示所有的异域请求都可以接受， */
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");

		ExportOperateForm eof = (ExportOperateForm) form;
		if (eof.getKehudaohao() == null) {
			eof.setKehudaohao("");
		}
		if (eof.getJiacheng() == null) {
			eof.setJiacheng("");
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
		int pageCount = this.exportOperateService.getGuoBangAllByCount("过磅",
				eof.getKehudaohao(), eof.getJiacheng(), pageRow);
		request.getSession()
				.setAttribute(
						"GBChuKu",
						this.exportOperateService.getGuoBangAllByCount("过磅",
								"", "", 1));
		JSONArray array = new JSONArray();
		PrintWriter out = response.getWriter();
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
		List<ExportOperate> daichuliList = this.exportOperateService
				.getGuoBangAll("过磅", eof.getKehudaohao(), eof.getJiacheng(),
						pageNow, pageRow);

		if (daichuliList.size() > 0) {
			for (ExportOperate es : daichuliList) {
				JSONObject obj = new JSONObject();
				obj.put("id", es.getExportSeed().getExport().getExportId());// 保存出库总订单的id
				obj.put("zid", es.getExportSeed().getEseedId());// 保存出库子订单的id
				obj.put("cid", es.getEoperateId());
				obj.put("huozhu", es.getExportSeed().getExport().getClient()
						.getClientAbbreviation());// 保存发起的客户的简称
				obj.put("kehudanhao", es.getExportSeed().getExport()
						.getExportClientNumber());// 保存客户的订单编号
				obj.put("youxiao", es.getExportSeed().getExport()
						.getExportDefinedTwo());// 保存订单的有效期
				obj.put("zhuangtai", es.getEoperateDefinedOne());// 保存订单的状态
				obj.put("yunshu", es.getExportSeed().getExport()
						.getExportCarryType());	//运输方式	
				obj.put("chehao", es.getExportSeed().getExport()
						.getExportWagonNumber());// 保存车号
				obj.put("siji", es.getExportSeed().getExport()
						.getExportDriverName());// 保存司机的姓名
				obj.put("sijitel", es.getExportSeed().getExport()
						.getExportDriverTel());// 保存司机的电话
				obj.put("pinlei", es.getExportSeed().getGoods()
						.getGoodsCategory().getGoodsCategoryName());// 保存货物的品类
				obj.put("mingcheng", es.getExportSeed().getGoods()
						.getGoodsName());// 保存货物名称
				obj.put("guige", es.getExportSeed().getGoods()
						.getGoodsStandard().getGoodsStandardName());// 保存货物规格
				obj.put("caizhi", es.getExportSeed().getGoods()
						.getGoodsQuality().getGoodsQualityName());// 保存货物材质
				obj.put("shuxing", es.getExportSeed().getGoods()
						.getGoodsProperty().getGoodsPropertyName());// 保存货物属性
				obj.put("chandi", es.getExportSeed().getGoods()
						.getGoodsYieldly().getGoodsYieldlyName());// 保存货物产地
				obj.put("goodsId", es.getExportSeed().getGoods().getGoodsId());// 保存货物的id
				obj.put("zhongliang", es.getExportSeed().getEseedShouldWeight());// 保存应出的重量
				obj.put("faqitime", es.getExportSeed().getExport()
						.getExportReateTime());// 保存订单的发起时间
				obj.put("diaodu",
						es.getInteriorUserByEoperateDispatcher() == null ? "无"
								: es.getInteriorUserByEoperateDispatcher()
										.getIuserName());// 保存调度
				obj.put("fenpeitime", es.getEoperateDispatcherTime());// 保存调度的分配时间
				obj.put("lisuan", es.getEoperatePonderationTrue());// 判断是否过磅
				obj.put("fenpeiweight", es.getEoperateDefinedTwo());// 分配的重量
				obj.put("baoguan", es.getInteriorUserByEoperateStoreman().getIuserName());// 保管员
				obj.put("caozuoshijian", es.getEoperateScreateTime());// 保管操作时间
				obj.put("jianshu", es.getEoperateRealityNumber());// 保管操作件数
				obj.put("kuwei", es.getTarehouse().getTarehouseName());// 保存库位的名称
				obj.put("beizhu", es.getEoperateRemark());// 保存备注
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

	// 过磅人员填写中，过磅提交
	public ActionForward getGuoBangCaoZuo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ExportOperateForm eof = (ExportOperateForm) form;
		ExportOperate eo = this.exportOperateService
				.getExportOperateId(URLDecoder.decode(eof.getEoperateId(),
						"UTF-8"));// 通过编号查询
		request.setAttribute("eo", eo);
		// 返回到对应的界面
		return mapping.findForward("goGuoBang");
	}

	// 过磅人员填写中，过磅提交
	public ActionForward GuoBangTiJiao(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ExportOperateForm eof = (ExportOperateForm) form;
		if (eof != null) {
			ExportOperate eo = this.exportOperateService.getExportOperateId(eof
					.getEoperateId());// 通过编号查询
			if (eo != null) {
				eo.setInteriorUserByEoperatePonderationMan(this.interiorUserService
						.getInteriorUserId(eof
								.getInteriorUserByEoperatePonderationMan()));// 设置过磅的人员
				eo.setEoperatePonderationTime(pt.getTimes());// 设置过磅的时间
				eo.setEoperateRealityWeight(eof.getEoperateRealityWeight());// 设置过磅人员过磅的重量
				eo.setEoperateRemark(eo.getEoperateRemark() + ","
						+ eof.getEoperateRemark());// 设置备注
				boolean ok = this.exportOperateService.update(eo);
				if (ok) {
					this.loginLogService.updateLogin(request.getSession()
							.getAttribute("loginlogId").toString(),
							"过磅货物进行过磅，并填写重量"
									+ eo.getExportSeed().getExport()
											.getExportId());// 记录到日志表中
					request.getSession().setAttribute("guobangtijiaoMessage",
							"提交成功！");
				} else {
					request.getSession().setAttribute("guobangtijiaoMessage",
							"提交失败！");
				}
			} else {
				request.getSession().setAttribute("guobangtijiaoMessage",
						"提交失败！");
			}
		} else {
			request.getSession().setAttribute("guobangtijiaoMessage", "提交失败！");
		}
		// 返回到对应的界面
		PrintWriter out = response.getWriter();
		out.print("<script>alert('"
				+ request.getSession().getAttribute("guobangtijiaoMessage")
				+ "');window.history.go(-2);</script>");
		return null;
	}

	// 查询订单状态为正在审核的，给审核人员看
	public ActionForward ZhengZaiShengHe(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ExportOperateForm eof = (ExportOperateForm) form;
		int pageNow = 1;

		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}

		if (eof.getBegin() != null) {
			eof.setBegin("2015-6-6 12:12:12");
		}
		if (eof.getFinish() != null) {
			eof.setFinish(pt.getTimes());
		}

		if (eof.getKehudaohao() == null) {
			eof.setKehudaohao("");
		}
		if (eof.getDanwei() == null) {
			eof.setDanwei("");
		}

		int pageCount = this.exportOperateService.getZhuangTaiByPageCount(
				eof.getBegin(), eof.getFinish(), eof.getDanwei(),
				eof.getKehudaohao(), "正在审核", pr.getRow());

		request.getSession().setAttribute(
				"SHChuKu",
				this.exportOperateService.getZhuangTaiByPageCount("",
						pt.getTimes(), "", "", "正在审核", 1));
		if (pageNow >= pageCount) {
			pageNow = pageCount;
		}
		if (pageNow <= 1) {
			pageNow = 1;
		}
		List<ExportOperate> eolist = this.exportOperateService
				.getZhuangTaiByPage(eof.getBegin(), eof.getFinish(),
						eof.getDanwei(), eof.getKehudaohao(), "正在审核", pageNow,
						pr.getRow());
		// 保存到request中
		request.setAttribute("zhengzaishenhelist", eolist);

		// 返回到对应的界面
		return super.execute(mapping, form, request, response);
	}

	// ------------------------------------------------后来加入开始

	// 审核人员查询正在审核的订单
	public void getShenheChaxun(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PrintWriter out = response.getWriter();
		String danhao = request.getParameter("danhao");
		String huozhu = request.getParameter("huozhu");

		/*
		 * int pageCount = this.exportOperateService.getZhuangTaiByPageCount(
		 * "", "", huozhu, danhao, "审核通过", pr.getRow());
		 */

		int pageRow = 20;
		if (request.getParameter("pageRow") != null) {
			pageRow = Integer.parseInt(request.getParameter("pageRow")
					.toString());
		}

		int pageCount = this.exportOperateService.pageCount(huozhu, danhao,
				"正在审核", pageRow);

		// 判断，如果总页数为0，即：没有数据，不让访问数据库，直接return
		if (pageCount == 0) {
			JSONObject obj = new JSONObject();
			obj.put("qingkong", "clean");
			out.print(obj.toString());
			out.flush();
			out.close();
			return;
		}

		int pageNow = 1;
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
			// 如果输入页数大于总页数，return
			if (pageNow > pageCount) {
				JSONObject obj = new JSONObject();
				obj.put("qingkong", "clean");
				out.print(obj.toString());
				out.flush();
				out.close();
				return;
			}
		}

		if (pageNow >= pageCount) {
			pageNow = pageCount;
		}
		if (pageNow <= 1) {
			pageNow = 1;
		}
		/*
		 * List<ExportOperate> tongguoList = this.exportOperateService
		 * .getZhuangTaiByPage("", "", huozhu, danhao, "审核通过", pageNow,
		 * pr.getRow());
		 */

		List<ExportOperate> tongguoList = this.exportOperateService
				.pageAllInfo(huozhu, danhao, "正在审核", pageNow, pageRow);

		JSONArray array = new JSONArray();
		for (ExportOperate c : tongguoList) {
			JSONObject obj = new JSONObject();
			obj.put("zongId", c.getExportSeed().getExport().getExportId());// 出库总订单号
			obj.put("huozhu", c.getExportSeed().getExport().getClient()
					.getClientAbbreviation());// 货主单位简称
			obj.put("exportClientNumber", c.getExportSeed().getExport()
					.getExportClientNumber());// 客户订单号
			obj.put("exportDefinedOne", c.getExportSeed().getExport()
					.getExportDefinedOne());// 是否接受超发
			obj.put("exportCarryType", c.getExportSeed().getExport()
					.getExportCarryType());// 运输方式
			obj.put("exportPlateNumber", c.getExportSeed().getExport()
					.getExportWagonNumber());// 车牌号
			obj.put("exportDriverName", c.getExportSeed().getExport()
					.getExportDriverName());// 司机姓名
			obj.put("exportDriverTel", c.getExportSeed().getExport()
					.getExportDriverTel());// 司机电话
			obj.put("goodsCategoryName", c.getExportSeed().getGoods()
					.getGoodsCategory().getGoodsCategoryName());// 货物品类名字
			obj.put("goodsName", c.getExportSeed().getGoods().getGoodsName());// 货物名称
			obj.put("goodsStandardName", c.getExportSeed().getGoods()
					.getGoodsStandard().getGoodsStandardName());// 货物规格名称
			obj.put("goodsQualityName", c.getExportSeed().getGoods()
					.getGoodsQuality().getGoodsQualityName());// 货物材质名称
			obj.put("goodsPropertyName", c.getExportSeed().getGoods()
					.getGoodsProperty().getGoodsPropertyName());// 货物属性名称
			obj.put("goodsYieldlyName", c.getExportSeed().getGoods()
					.getGoodsYieldly().getGoodsYieldlyName());// 货物产地名称
			obj.put("iseedShouldWeight", c.getExportSeed()
					.getEseedShouldWeight());// 应出重量
			obj.put("exportCreateTime", c.getExportSeed().getExport()
					.getExportReateTime());// 发起出库时间
			obj.put("diaodu",
					c.getInteriorUserByEoperateDispatcher() == null ? "无" : c
							.getInteriorUserByEoperateDispatcher()
							.getIuserName());// 调度员
			obj.put("fenpeiTime", c.getEoperateDispatcherTime());// 调度分配时间
			obj.put("guoLi", c.getEoperatePonderationTrue());// 过磅或者理算
			obj.put("fenpeiWeight", c.getEoperateDefinedTwo());// 给保管分配重量
			obj.put("kuwei", c.getTarehouse().getTarehouseName());// 库位
			obj.put("baoguan", c.getInteriorUserByEoperateStoreman()
					.getIuserName());// 保管员
			obj.put("beizhu", c.getEoperateRemark());// 备注
			// 查询出实际件数让司磅员看
			obj.put("shijiJianshu", c.getEoperateRealityNumber());// 实际件数

			obj.put("shijiWeight", c.getEoperateRealityWeight());// 实际重量
			obj.put("qianche", c.getEoperateCraneman());// 牵车工
			obj.put("chuangxie", c.getEoperateStevedore());// 装卸工
			obj.put("baoguanFinishTime", c.getEoperateSfinishTime());// 保管完成时间
			obj.put("youxiao", c.getExportSeed().getExport()
					.getExportDefinedTwo());

			// 放入隐藏域中的值
			obj.put("exportSeedId", c.getExportSeed().getEseedId());// 出库子订单id
			obj.put("exportOperateId", c.getEoperateId());// 出库操作订单id
			obj.put("pageNow", pageNow);
			obj.put("pageCount", pageCount);

			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();

	}

	// ------------------------------------------------后来加入结束

	// 查询单个审核通过的订单
	public ActionForward getShenheChakai(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 得到操作表id,并且转为utf-8码
		String caozuoid = new String(request.getParameter("caozuoid").getBytes(
				"ISO-8859-1"), "utf-8");
		// 得到子订单id,并且转为utf-8码
		String ziId = new String(request.getParameter("ziId").getBytes(
				"ISO-8859-1"), "utf-8");

		System.out.println("caozuoid:" + caozuoid);

		// 保存
		request.setAttribute("caozuoid", caozuoid);
		request.setAttribute("ziId", ziId);
		ExportOperate exportOperate = this.exportOperateService
				.getExportOperateId(caozuoid);

		// 获得登录日志编号
		String loginId = (String) request.getSession().getAttribute("loginId");
		System.out.println("loginId:" + loginId);
		System.out.println("登录人:"
				+ request.getSession().getAttribute("loginlogId").toString());

		loginLogService.updateLogin(loginId,
				request.getSession().getAttribute("loginName").toString()
						+ "正在审核出库订单");
		// 保存request中
		request.setAttribute("exportOperate", exportOperate);
		// 返回到对应的页面
		return mapping.findForward("getShenheChakai");
	}

	// 审核通过++++
	@SuppressWarnings("unused")
	public void ShengHeTongGuo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();

		String mess = "";

		DecimalFormat df = new DecimalFormat("###########0.00");
		;
		// 得到操作表id,并且转为utf-8码
		String caozuoid = new String(request.getParameter("czId"));
		// 得到操作表id,并且转为utf-8码
		String ziId = new String(request.getParameter("ziId"));
		// 得到备注
		String beizhu = new String(request.getParameter("beizhu"));

		System.out.println("caozuoid:" + caozuoid);
		if (caozuoid != null) {
			ExportOperate eo = this.exportOperateService
					.getExportOperateId(caozuoid);// 通过编号查询

			if (eo != null) {

				try {
					// 审核通过，相应的库存减少，对应的总子订单的实出重量相加
					// 得到内部人员id
					int iulistId = Integer.parseInt(request.getSession()
							.getAttribute("iulistId").toString());

					eo.setInteriorUserByEoperateAuditing(interiorUserService
							.getInteriorUserId(iulistId));// 设置审核人员
					eo.setEoperateAuditingTime(pt.getTimes());// 设置审核的时间
					if (eo.getEoperateAuditingTrue() == null) {
						eo.setEoperateAuditingTrue("1");// 设置审核的次数
					} else {
						Integer ci = Integer.parseInt(eo
								.getEoperateAuditingTrue()) + 1;// 取出审核次数
						eo.setEoperateAuditingTrue(String.valueOf(ci));
					}
					eo.setEoperateDefinedOne("审核通过");// 设置订单状态为审核通过
					eo.setEoperateRemark(eo.getEoperateRemark() + "," + beizhu);// 设置备注

					// --------------------------------------二次作业开始

					// 如果已经审核次数为1,二次重量不为空，说明二次作业,再设置二次作业

					if (eo.getEoperateDefinedThree() != null) {

						System.out
								.println("--------------------------------二次作业中进来来没");

						// 设置应收费用
						// 取出费用类型单价
						double danjia = this.costTypeService.getErCi();
						// 取出二次作业的费用类型
						double zhong = 0.0;
						if (eo.getEoperateDefinedThree() != null) {
							zhong = Double.parseDouble(eo
									.getEoperateDefinedThree());// 取出二次作业重量 }
						}

						double sum = danjia * zhong;

						long sumint = (long) (sum * 1000);
						if (sumint % 10 != 0 && sumint % 10 < 5) {
							sumint = sumint + 10;

						}

						sum = (double) sumint / 1000;
						eo.setEoperateDefinedFour(df.format(sum));// 设置二次作业应收费用

						try {
							// 增加出库子订单中的重量，件数，费用
							ExportSeed es = this.exportSeedService
									.getExportSeedId(eo.getExportSeed()
											.getEseedId());// 通过子订单编号查询
							double real = 0.0;
							if (es.getEseedSecondWork() != null) {
								real += es.getEseedSecondWork();
							}

							// -------------------取出二次作业的重量

							es.setEseedSecondWork(real
									+ Double.parseDouble(eo
											.getEoperateDefinedThree()));// 设置二次作业重量

							double feiyong = 0.0;
							if (es.getEseedSwshouldCost() != null) {
								feiyong = es.getEseedSwshouldCost();// 取出应收费用
							}
							es.setEseedSwshouldCost(Double.parseDouble(eo
									.getEoperateDefinedFour()) + feiyong);// 应收费用相加
							this.exportSeedService.update(es);

						} catch (Exception e) {
							System.out
									.println("---------------------------二次作业异常了");
						}

					} else {// 设置第一次作业费用

						// 设置应收费用
						// 取出费用类型单价++++++++++++++++++++++++++++++++++++++++++++++++++++++++++（包括了火运和汽运的费用类型）
						double chukudanjia = 1;
						try {
							String yunshu = eo.getExportSeed().getExport()
									.getExportCarryType();// 取得运输方式
							if (yunshu.equals("火运")) {
								chukudanjia = this.costTypeService
										.getHuoYunChuKu();// 取出火运的费用类型
							} else {
								chukudanjia = this.costTypeService
										.getQiYunChuKu();// 取出汽运的费用类型
							}
						} catch (Exception e) {

						}

						double zhekou = eo.getExportSeed().getExport()
								.getClient().getClientAgio();// 取出该客户的折扣
						double rzhong = eo.getEoperateRealityWeight();

						double rsum = chukudanjia * zhekou * rzhong;
						long rsumint = (long) (rsum * 1000);
						if (rsumint % 10 != 0 && rsumint % 10 < 5) {
							rsumint = rsumint + 10;
						}
						rsum = (double) rsumint / 1000;

						eo.setEoperateShouldCost(Double.parseDouble(df
								.format(rsum)));// 设置应收费用
					}

					boolean ok = this.exportOperateService.update(eo);

					if (ok) {

						this.loginLogService.updateLogin(request.getSession()
								.getAttribute("loginlogId").toString(),
								"审核出库操作订单，并通过"
										+ eo.getExportSeed().getExport()
												.getExportId());// 记录到日志表中

						// 如果二次作业重量为空，到子订单中设置重量件数

						if (eo.getEoperateDefinedThree() == null) {
							// 相应的库存减少
							// try {
							this.tarehouseGoodsService.jianTGoods(eo
									.getTarehouse().getTarehouseId(), eo
									.getExportSeed().getGoods().getGoodsId(),
									eo.getEoperateRealityWeight(), eo
											.getEoperateRealityNumber());

							// 增加出库子订单中的重量，件数，费用
							ExportSeed es = this.exportSeedService
									.getExportSeedId(eo.getExportSeed()
											.getEseedId());// 通过子订单编号查询
							double real = 0.0;
							if (es.getEseedRealityWeight() != null) {
								real = es.getEseedRealityWeight();
							}// 取出已出的重量
							es.setEseedRealityWeight(real
									+ eo.getEoperateRealityWeight());
							double jian = 0.0;
							if (es.getEseedRealityNumber() != null) {
								jian = es.getEseedRealityNumber();
							}// 取出件数
							es.setEseedRealityNumber(jian
									+ eo.getEoperateRealityNumber());// 实出件数相加
							es.setEseedShouldNumber(jian
									+ eo.getEoperateRealityNumber());// 应出件数相加
							double feiyong = 0.0;
							if (es.getEseedShouldCost() != null) {
								feiyong = es.getEseedShouldCost();// 取出应收费用
							}
							es.setEseedShouldCost(eo.getEoperateShouldCost()
									+ feiyong);// 应收费用相加
							this.exportSeedService.update(es);

							// 查出对应的批次，进行减少

							double Weight = eo.getEoperateRealityWeight();// 取出保管填写的重量
							double Number = eo.getEoperateRealityNumber();// 取出保管填写的件数
							// 进行对批次分解，查找对应的批次
							StatisticsWorking s = new StatisticsWorking();
							String str = eo.getEOperatepici();
							String strs = str.replaceAll(",", "");
							int x = s.countInnerStr(str, ",");
							for (int i = 0; i < x; i++) {
								String id = strs
										.substring(i * 12, (i + 1) * 12);
								TarehouseDetail td = this.tarehouseDetailService
										.getTarehouseDetailId(id);
								double rukuWeight = 0.0;
								double rukuNumber = td.getTdetailNumber();// 取出入库的件数
								double chukuWeight = td.getTdetailEweight();// 取出该批次已出库的重量
								double chukuNumber = td.getTdetailEnumber();// 取出该批次已出库的件数

								if (td.getTdetailWeight() != null) {
									rukuWeight = td.getTdetailWeight();
								}// 取出入库的重量

								if (Weight + chukuWeight > rukuWeight) {
									td.setTdetailEweight(rukuWeight);// 如果大于该批次的入库重量，那么，第一个批次货物已出完，出库重量就等于入库时的重量
									td.setTdetailEnumber(rukuNumber);
									Weight = Weight
											- (rukuWeight - chukuWeight);// 得出剩余的重量
									Number = Number
											- (rukuNumber - chukuNumber);
									td.setTdetailDefinedTwo("0");
									boolean oks = this.tarehouseDetailService
											.update(td);

								} else if (Weight + chukuWeight <= rukuWeight) {// 如果分配的重量加已经出库的重量小于入库的重量时，分配重量加出库的重量
									td.setTdetailEweight(Weight + chukuWeight);// 设置已出库的重量
									td.setTdetailEnumber(Number + chukuNumber);
									Weight = 0;
									Number = 0;
									td.setTdetailDefinedTwo("0");// 改变调度分配的标记
									boolean oks = this.tarehouseDetailService
											.update(td);
									break;
								}
							}

						}

						mess = "审核成功！";

					} else {
						mess = "审核失败！";
					}
				} catch (Exception e) {
					mess = "审核失败！";
				}

			} else {
				mess = "审核失败！";
			}
		} else {
			mess = "审核失败！";
		}

		out.print("<script>alert('"
				+ mess
				+ "');window.location.href='inputOperate.do?flag=selectShenHeInputOperat';</script>");

	}

	// 审核未通过
	@SuppressWarnings("unused")
	public void ShengHeWeiTongGuo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String mess = "";

		PrintWriter out = response.getWriter();

		// 得到操作表id,并且转为utf-8码
		String caozuoid = new String(request.getParameter("czId"));
		// 得到操作表id,并且转为utf-8码
		String ziId = new String(request.getParameter("ziId"));
		// 得到备注
		String beizhu = new String(request.getParameter("beizhu"));

		// 获取内如人员登录id
		int iulistId = Integer.parseInt(request.getSession()
				.getAttribute("iulistId").toString());
		System.out.println(caozuoid + "," + ziId + "," + beizhu);

		if (caozuoid != null) {
			ExportOperate eo = this.exportOperateService
					.getExportOperateId(caozuoid);// 通过编号查询
			if (eo != null) {
				eo.setInteriorUserByEoperateAuditing(this.interiorUserService
						.getInteriorUserId(iulistId));// 设置审核人员
				eo.setEoperateAuditingTime(pt.getTimes());// 设置审核的时间
				eo.setEoperateDefinedOne("审核未通过");// 修改订单状态为审核未通过
				eo.setEoperateRemark(eo.getEoperateRemark() + "," + beizhu);// 设置备注
				int ci = 1;
				if (eo.getEoperateAuditingTrue() != null) {
					ci = Integer.parseInt(eo.getEoperateAuditingTrue()) + 1;
				}
				eo.setEoperateAuditingTrue(String.valueOf(ci));// 设置审核的次数
				boolean ok = this.exportOperateService.update(eo);
				if (ok) {

					this.loginLogService.updateLogin(request.getSession()
							.getAttribute("loginlogId").toString(),
							"审核出库操作订单，审核未通过"
									+ eo.getExportSeed().getExport()
											.getExportId());// 记录到日志表中
					mess = "审核成功！";
				} else {
					mess = "审核失败！";
				}
			} else {
				mess = "审核失败！";
			}
		} else {
			mess = "审核失败！";
		}
		out.print("<script>alert('"
				+ mess
				+ "');window.location.href='inputOperate.do?flag=selectShenHeInputOperat';</script>");

	}

	// 查询审核通过的订单
	public ActionForward getShenHeTongGuo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ExportOperateForm eof = (ExportOperateForm) form;
		if (eof != null) {
			if (eof.getBegin() == null) {
				eof.setBegin("");
			}
			if (eof.getFinish() == null) {
				eof.setFinish("");
			}
			if (eof.getDanwei() == null) {
				eof.setDanwei("");
			}
			if (eof.getKehudaohao() == null) {
				eof.setKehudaohao("");
			}
			int pageNow = 1;
			if (request.getParameter("pageNow") != null) {
				pageNow = Integer.parseInt(request.getParameter("pageNow"));
			}
			int pageCount = this.exportOperateService.getZhuangTaiByPageCount(
					eof.getBegin(), eof.getFinish(), eof.getDanwei(),
					eof.getKehudaohao(), "审核通过", pr.getRow());
			if (pageNow >= pageCount) {
				pageNow = pageCount;
			}
			if (pageNow <= 1) {
				pageNow = 1;
			}
			List<ExportOperate> tongguoList = this.exportOperateService
					.getZhuangTaiByPage(eof.getBegin(), eof.getFinish(),
							eof.getDanwei(), eof.getKehudaohao(), "审核通过",
							pageNow, pr.getRow());

			// 保存request中
			request.setAttribute("tongguoList", tongguoList);

		}
		// 返回到对应的页面
		return super.execute(mapping, form, request, response);
	}

	// ------------------------------------------------后来加入开始

	// 收费人员查询审核通过的订单
	public void getShoufeiChaxun(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PrintWriter out = response.getWriter();
		String danhao = request.getParameter("danhao");
		String huozhu = request.getParameter("huozhu");
		/*
		 * int pageCount = this.exportOperateService.getZhuangTaiByPageCount(
		 * "", "", huozhu, danhao, "审核通过", pr.getRow());
		 */

		int pageRow = 20;
		if (request.getParameter("pageRow") != null) {
			pageRow = Integer.parseInt(request.getParameter("pageRow")
					.toString());
		}

		int pageCount = this.exportOperateService.pageCount(huozhu, danhao,
				"审核通过", pageRow);

		request.getSession().setAttribute("SFChuKu",
				this.exportOperateService.pageCount("", "", "审核通过", 1));

		// 判断，如果总页数为0，即：没有数据，不让访问数据库，直接return
		if (pageCount == 0) {
			JSONObject obj = new JSONObject();
			obj.put("qingkong", "clean");
			out.print(obj.toString());
			out.flush();
			out.close();
			return;
		}

		int pageNow = 1;
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
			// 如果输入页数大于总页数，return
			if (pageNow > pageCount) {
				JSONObject obj = new JSONObject();
				obj.put("qingkong", "clean");
				out.print(obj.toString());
				out.flush();
				out.close();
				return;
			}
		}

		if (pageNow >= pageCount) {
			pageNow = pageCount;
		}
		if (pageNow <= 1) {
			pageNow = 1;
		}

		List<ExportOperate> tongguoList = this.exportOperateService
				.pageAllInfo(huozhu, danhao, "审核通过", pageNow, pageRow);

		JSONArray array = new JSONArray();
		for (ExportOperate c : tongguoList) {
			JSONObject obj = new JSONObject();
			obj.put("zongId", c.getExportSeed().getExport().getExportId());// 出库总订单号
			obj.put("clientFirmName", c.getExportSeed().getExport().getClient()
					.getClientFirmName());// 货主单位名称
			obj.put("exportClientNumber", c.getExportSeed().getExport()
					.getExportClientNumber());// 客户订单号
			obj.put("exportDefinedOne", c.getExportSeed().getExport()
					.getExportDefinedOne());// 是否接受超发
			obj.put("youxiao", c.getExportSeed().getExport().getExportDefinedTwo());//订单有效期
			obj.put("exportCarryType", c.getExportSeed().getExport()
					.getExportCarryType());// 运输方式
			obj.put("exportPlateNumber", c.getExportSeed().getExport()
					.getExportWagonNumber());// 车牌号
			obj.put("exportDriverName", c.getExportSeed().getExport()
					.getExportDriverName());// 司机姓名
			obj.put("exportDriverTel", c.getExportSeed().getExport()
					.getExportDriverTel());// 司机电话
			obj.put("goodsCategoryName", c.getExportSeed().getGoods()
					.getGoodsCategory().getGoodsCategoryName());// 货物品类名字
			obj.put("goodsName", c.getExportSeed().getGoods().getGoodsName());// 货物名称
			obj.put("goodsStandardName", c.getExportSeed().getGoods()
					.getGoodsStandard().getGoodsStandardName());// 货物规格名称
			obj.put("goodsQualityName", c.getExportSeed().getGoods()
					.getGoodsQuality().getGoodsQualityName());// 货物材质名称
			obj.put("goodsPropertyName", c.getExportSeed().getGoods()
					.getGoodsProperty().getGoodsPropertyName());// 货物属性名称
			obj.put("goodsYieldlyName", c.getExportSeed().getGoods()
					.getGoodsYieldly().getGoodsYieldlyName());// 货物产地名称
			obj.put("iseedShouldWeight", c.getExportSeed()
					.getEseedShouldWeight());// 应出重量
			obj.put("exportCreateTime", c.getExportSeed().getExport()
					.getExportReateTime());// 发起出库时间
			obj.put("diaodu",
					c.getInteriorUserByEoperateDispatcher() == null ? "无" : c
							.getInteriorUserByEoperateDispatcher()
							.getIuserName());// 调度员
			obj.put("fenpeiTime", c.getEoperateDispatcherTime());// 调度分配时间
			obj.put("guoLi", c.getEoperatePonderationTrue());// 过磅或者理算
			obj.put("fenpeiWeight", c.getEoperateDefinedTwo());// 给保管分配重量
			obj.put("kuwei", c.getTarehouse().getTarehouseName());// 库位
			obj.put("baoguan", c.getInteriorUserByEoperateStoreman()
					.getIuserName());// 保管员
			obj.put("beizhu", c.getEoperateRemark());// 备注
			// 查询出实际件数让司磅员看
			obj.put("shijiJianshu", c.getEoperateRealityNumber());// 实际件数
			obj.put("shijiWeight", c.getEoperateRealityWeight());// 实际重量
			obj.put("qianche", c.getEoperateCraneman());// 牵车工
			obj.put("chuangxie", c.getEoperateStevedore());// 装卸工
			obj.put("baoguanFinishTime", c.getEoperateSfinishTime());// 保管完成时间

			// 放入隐藏域中的值
			obj.put("exportSeedId", c.getExportSeed().getEseedId());// 出库子订单id
			obj.put("exportOperateId", c.getEoperateId());// 出库操作订单id
			obj.put("pageNow", pageNow);
			obj.put("pageCount", pageCount);

			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();

	}

	// ------------------------------------------------后来加入结束

	// 查询单个审核通过的订单
	public ActionForward getShoufeiChakai(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 得到操作表id,并且转为utf-8码
		String caozuoid = new String(request.getParameter("caozuoid").getBytes(
				"ISO-8859-1"), "utf-8");
		// 得到子订单id,并且转为utf-8码
		String ziId = new String(request.getParameter("ziId").getBytes(
				"ISO-8859-1"), "utf-8");
		// 保存
		request.setAttribute("caozuoid", caozuoid);
		request.setAttribute("ziId", ziId);
		ExportOperate exportOperate = this.exportOperateService
				.getExportOperateId(caozuoid);
		// 查询支付方式
		List<PaymentFashion> payment = paymentFashionService.getAll();
		request.setAttribute("payment", payment);
		// 获得登录日志编号
		String loginId = (String) request.getSession().getAttribute("loginId");
		loginLogService.updateLogin(loginId,
				request.getSession().getAttribute("loginName").toString()
						+ "正在收取出库收费");

		// 保存request中
		request.setAttribute("exportOperate", exportOperate);
		// 返回到对应的页面
		return mapping.findForward("getShoufeiChakai");
	}

	// 收费人员点击收费开始收费，判断有月结和日结
	public ActionForward ShouFei(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ExportOperateForm eof = (ExportOperateForm) form;
		// 得到内部人员id(收费人员)
		int id = Integer.parseInt(request.getSession().getAttribute("iulistId")
				.toString());
		InteriorUser Iuser = interiorUserService.getInteriorUserId(id);

		PrintWriter out = response.getWriter();
		if (eof != null) {

			ExportOperate eo = this.exportOperateService.getExportOperateId(eof
					.getEoperateId());// 通过编号查询
			if (eo != null) {

				try {
					eo.setEoperateCollectTime(pt.getTimes());// 设置收费的时间
					eo.setEoperateRemark(eo.getEoperateRemark());// 设置备注
					eo.setEoperateClientAccounts(eof
							.getEoperateClientAccounts());// 设置结算的方式
					eo.setInteriorUserByEoperateCollectMan(Iuser);// 收费人

					ExportSeed es = this.exportSeedService.getExportSeedId(eo
							.getExportSeed().getEseedId());// 通过编号查询子订单 // 月结/现结

					if (eof.getEoperateClientAccounts().equals("月结")
							&& (eof.getEoperateRealityCost() == null || eof
									.getEoperateRealityCost() == 0)) {
						eo.setEoperateClientAccounts("月结");// 设置操作订单中的结算方式
						eo.setEoperateDefinedOne("未收费");// 设置操作订单中的订单状态为未收费
						es.setEseedClientAccounts("月结");// 设置子订单中的结算方式为月结
						this.loginLogService.updateLogin(request.getSession()
								.getAttribute("loginlogId").toString(),
								"出库订单收费，未收费"
										+ eo.getExportSeed().getExport()
												.getExportId());

					} else if (eof.getEoperateClientAccounts().equals("日结")
							&& eof.getEoperateRealityCost() != null) {

						eo.setEoperateRealityCost(eof.getEoperateRealityCost());// 设置操作订单中的实收费用

						double feiyong = 0.0;
						if (es.getEseedRealityCost() != null) {
							feiyong += es.getEseedRealityCost();// 子订单中的实收费用相加
						}
						double feiyongs = 0.0;
						if (es.getEseedSwrealityCost() != null) {
							feiyongs += es.getEseedSwrealityCost();// 子订单中的实收二次作业费用相加
						}

						// 设置子订单中二次作业的实收费用
						if (eof.getErcishishou() != null) {
							es.setEseedSwrealityCost(feiyongs
									+ eof.getErcishishou());
						}

						// 设置子订单中实收费用
						es.setEseedRealityCost(feiyong
								+ eof.getEoperateRealityCost());

						es.setPaymentFashion(paymentFashionService
								.getPaymentFashionId(eof
										.getEoperatePaymentFashion()));// 设置子订单中的支付方式
						es.setEseedClientAccounts("日结");// 设置子订单中的结算方式为日结
						eo.setEoperatePaymentFashion(paymentFashionService
								.getPaymentFashionId(eof
										.getEoperatePaymentFashion()));// 设置操作订单中的支付方式
						eo.setEoperateDefinedOne("已收费");// 设置操作订单中的订单状态为已收费
						eo.setEoperateClientAccounts("日结");// 设置操作订单中的结算方式为日结

						this.loginLogService.updateLogin(request.getSession()
								.getAttribute("loginlogId").toString(),
								"出库订单收费，已收费"
										+ eo.getExportSeed().getExport()
												.getExportId());

					}

					this.exportSeedService.update(es);
					boolean ok = exportOperateService.update(eo);
					if (ok) {

						request.setAttribute("es", es);
						request.setAttribute("eo", eo);
						out.print("<script>alert('提交成功！');</script>");

						return null; // mapping.findForward("goDaYin");

					} else {
						out.print("<script>alert('提交失败！');window.location.href='inputOperate.do?flag=selectMoneyInputOperat';</script>");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				out.print("<script>alert('提交失败！');window.location.href='inputOperate.do?flag=selectMoneyInputOperat';</script>");
			}
		} else {
			out.print("<script>alert('提交失败！');window.location.href='inputOperate.do?flag=selectMoneyInputOperat';</script>");
		}

		// 返回到对应的界面
		return null;
	}

	// 保管提交二次作业重量
	public ActionForward ErCiZuoYe(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ExportOperateForm eof = (ExportOperateForm) form;
		if (eof != null) {
			ExportOperate eo = this.exportOperateService.getExportOperateId(eof
					.getEoperateId());// 通过编号查询
			if (eo != null) {
				eo.setEoperateDefinedThree(eof.getEoperateDefinedThree());// 设置二次作业的重量
				eo.setEoperateSfinishTime(pt.getTimes());// 设置现场保管结束操作的时间
				eo.setEoperateCraneman(eof.getEoperateCraneman());// 设置天车工
				eo.setEoperateStevedore(eof.getEoperateStevedore());// 设置装卸工
				eo.setEoperateDefinedOne("正在审核");// 设置订单状态
				eo.setEoperateRemark(eof.getEoperateRemark());// 设置备注
				// eo.setEoperateTruckNum(eof.getEoperateTruckNum());// 设置车号
				boolean ok = this.exportOperateService.update(eo);
				if (ok) {
					this.loginLogService.updateLogin(request.getSession()
							.getAttribute("loginlogId").toString(),
							"二次作业完成并提交审核，订单编号："
									+ eo.getExportSeed().getExport()
											.getExportId());
					request.getSession().setAttribute("ercizuoyeMessage",
							"提交成功！");
				} else {
					request.getSession().setAttribute("ercizuoyeMessage",
							"提交失败！");
				}
			} else {
				request.getSession().setAttribute("ercizuoyeMessage", "提交失败！");
			}
		} else {
			request.getSession().setAttribute("ercizuoyeMessage", "提交失败！");
		}
		// 返回到对应的页面
		PrintWriter out = response.getWriter();
		out.print("<script>alert('"
				+ request.getSession().getAttribute("ercizuoyeMessage")
				+ "');window.history.go(-2);</script>");
		return null;
	}

	// 查询全部，可以通过出库总订单编号，客户订单号，订单生成的时间范围，起始日期，结束日期，客户单位名称全拼，简称，单位助记符，通过货物名称，货物助记符，规格，材质，属性，库位名称，调度员，司磅员，保管员，审核，
	public ActionForward getExportOperateAll(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ExportOperateForm eof = (ExportOperateForm) form;
		if (eof.getBegin() == null) {
			eof.setBegin("2015-6-6 12:12:12");
		}
		if (eof.getFinish() == null) {
			eof.setFinish(pt.getTimes());
		}
		if (eof.getZongdingdanhao() == null) {
			eof.setZongdingdanhao("");
		}
		if (eof.getKehudaohao() == null) {
			eof.setKehudaohao("");
		}
		if (eof.getDanwei() == null) {
			eof.setDanwei("");
		}
		if (eof.getJiacheng() == null) {
			eof.setJiacheng("");
		}
		if (eof.getDanweiSign() == null) {
			eof.setDanweiSign("");
		}
		if (eof.getGoodsName() == null) {
			eof.setGoodsName("");
		}
		if (eof.getGoodsSign() == null) {
			eof.setGoodsSign("");
		}
		if (eof.getGuige() == null) {
			eof.setGuige("");
		}
		if (eof.getCaizhi() == null) {
			eof.setCaizhi("");
		}
		if (eof.getShuxing() == null) {
			eof.setShuxing("");
		}
		if (eof.getDiaodu() == null) {
			eof.setDiaodu("");
		}
		if (eof.getSibang() == null) {
			eof.setSibang("");
		}
		if (eof.getBaoguan() == null) {
			eof.setBaoguan("");
		}
		if (eof.getShenhe() == null) {
			eof.setShenhe("");
		}
		if (eof.getShoufei() == null) {
			eof.setShoufei("");
		}
		if (eof.getKuweiName() == null) {
			eof.setKuweiName("");
		}

		int pageNow = 1;
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}

		int pageCount = this.exportOperateService.getExportOperateBYPageCount(
				eof.getZongdingdanhao(), eof.getKehudaohao(), eof.getBegin(),
				eof.getFinish(), eof.getDanwei(), eof.getJiacheng(),
				eof.getDanweiSign(), eof.getGoodsName(), eof.getGuige(),
				eof.getCaizhi(), eof.getShuxing(), eof.getGoodsSign(),
				eof.getKuweiName(), eof.getDiaodu(), eof.getSibang(),
				eof.getBaoguan(), eof.getShenhe(), eof.getShoufei(),
				pr.getRow());
		if (pageNow >= pageCount) {
			pageNow = pageCount;
		}
		if (pageNow <= 1) {
			pageNow = 1;
		}
		List<ExportOperate> eoAllList = this.exportOperateService
				.getExportOperateByPage(eof.getZongdingdanhao(),
						eof.getKehudaohao(), eof.getBegin(), eof.getFinish(),
						eof.getDanwei(), eof.getJiacheng(),
						eof.getDanweiSign(), eof.getGoodsName(),
						eof.getGuige(), eof.getCaizhi(), eof.getShuxing(),
						eof.getGoodsSign(), eof.getKuweiName(),
						eof.getDiaodu(), eof.getSibang(), eof.getBaoguan(),
						eof.getShenhe(), eof.getShoufei(), pageNow, pr.getRow());

		// 保存到request中
		request.setAttribute("eoAllList", eoAllList);

		// 保存当前的页
		request.getSession().setAttribute("pageNow", pageNow);
		// 返回到对应的页面
		return super.execute(mapping, form, request, response);
	}

	// 当操作有误的时候进行操作的订单进行作废，重新开始作业
	public ActionForward DanGeZuoFei(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ExportOperateForm eof = (ExportOperateForm) form;
		DecimalFormat df = new DecimalFormat("########0.000");
		DecimalFormat cdf = new DecimalFormat("########0.00");
		if (eof != null) {
			ExportOperate eo = this.exportOperateService
					.getExportOperateId(URLDecoder.decode(eof.getEoperateId(),
							"UTF-8"));// 通过编号进行查询
			if (eo != null) {
				// 重新设置子订单的状态为准备出库
				// 通过操作订单中保存的子订单的id进行查询子订单
				ExportSeed es = this.exportSeedService.getExportSeedId(eo
						.getExportSeed().getEseedId());
				es.setEseedOrderStatus("准备出库");
				// 将子订单中增加的货物的重量和件数进行减少，指的是实出的重量和实出的件数
				Double weight = (eo.getEoperateRealityWeight() == null ? 0.0
						: eo.getEoperateRealityWeight());// 取出操作订单中的重量
				Double number = (eo.getEoperateRealityNumber() == null ? 0.0
						: eo.getEoperateRealityNumber());// 取出操作订单中的件数
				Double cost = (eo.getEoperateRealityCost() == null ? 0.0 : eo
						.getEoperateRealityCost());// 取出操作订单中的实收的费用
				Double shouldCost = (eo.getEoperateShouldCost() == null ? 0.0
						: eo.getEoperateShouldCost());// 取出操作订单中的应收费用
				Double Zweight = (es.getEseedRealityWeight() == null ? 0.0 : es
						.getEseedRealityWeight());// 取出子订单中的重量
				Double Znumber = (es.getEseedRealityNumber() == null ? 0.0 : es
						.getEseedRealityNumber());// 取出子订单中得到件数
				Double Zcost = (es.getEseedRealityCost() == null ? 0.0 : es
						.getEseedRealityCost());// 取出自订单中的实收费用
				Double ZshouldCost = (es.getEseedShouldCost() == null ? 0.0
						: es.getEseedShouldCost());// 取出子订单中的应收的费用

				es.setEseedRealityCost(Double.parseDouble(cdf.format(Zcost
						- cost)));// 子订单中的实收的费用减少对应的操作订单中的费用
				es.setEseedShouldCost(Double.parseDouble(cdf.format(shouldCost
						- ZshouldCost)));

				// 对应的库位的库存进行减少，对应的批次也进行变化
				if (eo.getEoperateDefinedOne().equals("审核通过")
						|| eo.getEoperateDefinedOne().equals("正在收费")
						|| eo.getEoperateDefinedOne().equals("已收费")
						|| eo.getEoperateDefinedOne().equals("未收费")
						|| eo.getEoperateDefinedOne().equals("出库完成")) {
					es.setEseedRealityWeight(Double.parseDouble(df
							.format(Zweight - weight)));// 子订单中的重量减少对应的操作订单中的重量
					es.setEseedRealityNumber(Double.parseDouble(df
							.format(Znumber - number)));// 子订单中的件数减少对应的操作订单中的件数
					this.tarehouseGoodsService.zengGoods(eo.getTarehouse()
							.getTarehouseId(), es.getGoods().getGoodsId(),
							weight, number);
					// 进行对批次分解，查找对应的批次
					StatisticsWorking s = new StatisticsWorking();
					String str = eo.getEOperatepici();
					String strs = str.replaceAll(",", "");
					int x = s.countInnerStr(str, ",");
					for (int i = 0; i < x; i++) {
						String id = strs.substring(i * 12, (i + 1) * 12);
						TarehouseDetail td = this.tarehouseDetailService
								.getTarehouseDetailId(id);
						td.setTdetailDefinedTwo("0");// 改变批次中的标记
						td.setTdetailEweight(td.getTdetailEweight() - weight);// 对应的批次进行减少
						td.setTdetailEnumber(td.getTdetailEnumber() - number);// 对应的件数减少
						this.tarehouseDetailService.update(td);
					}
				}

				eo.setEoperateDefinedOne("订单作废");
				// 进行修改
				boolean ok = this.exportOperateService.update(eo);
				boolean oks = this.exportSeedService.update(es);
				if (ok && oks) {
					this.loginLogService.updateLogin(request.getSession()
							.getAttribute("loginlogId").toString(),
							"作废出库操作订单,订单编号："
									+ eo.getExportSeed().getExport()
											.getExportId());
					request.getSession()
							.setAttribute("shanchuMessage", "作废成功！");
				} else {
					request.getSession()
							.setAttribute("shanchuMessage", "作废失败！");
				}
			} else {
				request.getSession().setAttribute("shanchuMessage", "作废失败！");
			}
		} else {
			request.getSession().setAttribute("shanchuMessage", "作废失败！");
		}
		PrintWriter out = response.getWriter();
		out.print("<script>alert('"
				+ request.getSession().getAttribute("shanchuMessage")
				+ "');window.history.go(-1);</script>");
		// 返回到对应的面
		return null;
	}

	// 内部人员进行删除订单，如果一切关联的订单全部为订单作废的时候将上一级进行设置为订单作废
	public ActionForward ShanChuDingDan(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ExportOperateForm eof = (ExportOperateForm) form;
		try {
			if (eof != null) {
				ExportOperate eo = this.exportOperateService
						.getExportOperateId(eof.getEoperateId());// 通过编号进行查询
				if (eo != null) {
					// 查询出库总订单下的子订单是否全部删除
					if (this.exportSeedService.getExportId(
							eo.getExportSeed().getExport().getExportId())
							.size() == 1) {
						Export export = this.exportService.getExportId(eo
								.getExportSeed().getExport().getExportId());// 通过总订单编号查询
						if (export != null) {
							this.exportService.delete(export);
						}
					}
					if (this.exportOperateService.getZidingdan(
							eo.getExportSeed().getEseedId()).size() == 1) {
						ExportSeed es = this.exportSeedService
								.getExportSeedId(eo.getExportSeed()
										.getEseedId());// 通过编号查询子订单
						if (es != null) {
							es.setEseedOrderStatus("订单作废");
							this.exportSeedService.update(es);
						}
					}

					eo.setEoperateDefinedOne("订单作废");
					boolean ok = this.exportOperateService.update(eo);
					if (ok) {
						request.getSession().setAttribute("shanchuMessage",
								"删除成功！");
						// 记录到日志表中
						this.loginLogService.updateLogin(request.getSession()
								.getAttribute("loginlogId").toString(),
								"删除出库订单"
										+ eo.getExportSeed().getExport()
												.getExportId());
					} else {
						request.getSession().setAttribute("shanchuMessage",
								"删除失败！");
					}
				} else {
					request.getSession()
							.setAttribute("shanchuMessage", "删除失败！");
				}
			} else {
				request.getSession().setAttribute("shanchuMessage", "删除失败！");
			}
		} catch (Exception e) {

		}

		// 返回到对应的面
		return super.execute(mapping, form, request, response);
	}

	// 查询作废的订单,以分页的方式展现
	public ActionForward getZuoFeiDingDan(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int pageNow = 1;
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}
		int pageCount = this.exportOperateService
				.getZhuangTaiByPageCount("2000-1-1 12:12:12", pt.getTimes(),
						"", "", "订单作废", pr.getRow());
		if (pageNow >= pageCount) {
			pageNow = pageCount;
		}
		if (pageNow <= 1) {
			pageNow = 1;
		}

		List<ExportOperate> zuofeiList = this.exportOperateService
				.getZhuangTaiByPage("2000-1-1 12:12:12", pt.getTimes(), "", "",
						"订单作废", pageNow, pr.getRow());
		// 保存到request中
		request.setAttribute("zuofeiList", zuofeiList);
		// 将但前页保存到session中
		request.getSession().setAttribute("pageNow", pageNow);
		// 返回到对应的页面
		return super.execute(mapping, form, request, response);
	}

	// 待处理中的查看详情
	public ActionForward getDaiChuLiXQ(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ExportOperateForm eof = (ExportOperateForm) form;

		List<ExportOperate> list = this.exportOperateService
				.getZidingdan(URLDecoder.decode(eof.getExportSeed(), "UTF-8"));
		ExportSeed eslist = this.exportSeedService.getExportSeedId(URLDecoder
				.decode(eof.getExportSeed(), "UTF-8"));
		// 保存到request中
		request.setAttribute("zlist", list);
		request.setAttribute("es", eslist);

		// 返回到对应的页面
		return mapping.findForward("foDaiChuLiXQ");
	}

	// 当修改时进行修改批次
	public void jianxiugaipici(ExportOperate eo, Double Weight, Double Number) {
		StatisticsWorking s = new StatisticsWorking();
		String str = eo.getEOperatepici();
		String strs = str.replaceAll(",", "");
		int x = s.countInnerStr(str, ",");
		Double weight = Weight;
		Double number = Number;
		for (int i = 0; i < x; i++) {
			String id = strs.substring(i * 12, (i + 1) * 12);
			TarehouseDetail td = this.tarehouseDetailService
					.getTarehouseDetailId(id);
			double chukuWeight = td.getTdetailEweight();// 取出该批次已出库的重量
			double chukuNumber = td.getTdetailEnumber();// 取出该批次已出库的件数

			if (chukuWeight - weight > 0) {
				td.setTdetailEweight(chukuWeight - weight);// 如果大于该批次的入库重量，那么，第一个批次货物已出完，出库重量就等于入库时的重量
				td.setTdetailEnumber(chukuNumber - number);
				weight = 0.0;// 得出剩余的重量
				Number = 0.0;
				this.tarehouseDetailService.update(td);
				break;
			} else if (chukuWeight - weight <= 0) {// 如果分配的重量加已经出库的重量小于入库的重量时，分配重量加出库的重量
				td.setTdetailEweight(0.0);// 设置已出库的重量
				td.setTdetailEnumber(0.0);
				weight = weight - chukuWeight;
				number = number - chukuNumber;
				td.setTdetailDefinedTwo("0");// 改变调度分配的标记
				this.tarehouseDetailService.update(td);
			}
		}
	}

	// 当修改时进行修改批次
	public void jiaxiugaipici(String pici, Double Weight, Double Number) {
		Double weight = Weight;
		Double number = Number;
		TarehouseDetail td = this.tarehouseDetailService
				.getTarehouseDetailId(pici);
		double rukuWeight = td.getTdetailWeight();// 取出入库重量
		double rukuNumber = td.getTdetailNumber();// 取出入库件数
		double chukuWeight = td.getTdetailEweight();// 取出该批次已出库的重量
		double chukuNumber = td.getTdetailEnumber();// 取出该批次已出库的件数

		if (chukuWeight + weight > rukuWeight) {
			td.setTdetailEweight(rukuWeight);// 如果大于该批次的入库重量，那么，第一个批次货物已出完，出库重量就等于入库时的重量
			td.setTdetailEnumber(rukuNumber);
			weight = rukuWeight - chukuWeight;// 得出剩余的重量
			number = rukuNumber - chukuNumber;
			this.tarehouseDetailService.update(td);
		} else if (chukuWeight + weight <= rukuWeight) {// 如果分配的重量加已经出库的重量小于入库的重量时，分配重量加出库的重量
			td.setTdetailEweight(chukuWeight + weight);// 设置已出库的重量
			td.setTdetailEnumber(chukuNumber + number);
			weight = 0.0;
			number = 0.0;
			td.setTdetailDefinedTwo("0");// 改变调度分配的标记
			this.tarehouseDetailService.update(td);
		}
	}

	// 进行修改操作订单，在不同的状态下，相应的库存发生相应的变化
	public ActionForward updateExportOperate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ExportOperateForm eof = (ExportOperateForm) form;
		DecimalFormat df = new DecimalFormat("##########0.000");
		DecimalFormat cdf = new DecimalFormat("###########0.00");
		InteriorUser iu = (InteriorUser) request.getSession().getAttribute(
				"iulist");// 对应的登录的发起修改的人
		String message = "";
		if (eof == null) {
			message = "修改失败！";
		} else {
			ExportOperate eo = this.exportOperateService.getExportOperateId(eof
					.getEoperateId());// 通过编号查询
			if (eo == null) {
				message = "修改失败！";
			} else {
				// 判断是否牵连到其他的表
				if (eo.getEoperateDefinedOne().equals("审核通过")
						|| eo.getEoperateDefinedOne().equals("正在收费")
						|| eo.getEoperateDefinedOne().equals("未收费")
						|| eo.getEoperateDefinedOne().equals("已收费")
						|| eo.getEoperateDefinedOne().equals("出库完成")) {
					// 首先判断重量是否发生了改变，并且判断件数是否发生了改变，如果发生了改变，相应的设置子订单的重量
					// 如果对库位进行了修改
					if (eof.getTarehouse() != null) {
						if (eof.getTarehouse().equals(
								(eo.getTarehouse() == null ? 0 : eo
										.getTarehouse().getTarehouseId())) == false) {
							// 如果对库位进行了修改，那么以前的库位加上以前的重量，而现在的库位减去现在的重量
							// 给之前的库位增加相应的之前的重量
							this.tarehouseGoodsService.zengGoods(eo
									.getTarehouse().getTarehouseId(), eo
									.getExportSeed().getGoods().getGoodsId(),
									eo.getEoperateRealityWeight() == null ? 0.0
											: eo.getEoperateRealityWeight(),
									eo.getEoperateRealityNumber() == null ? 0.0
											: eo.getEoperateRealityNumber());
							// 给对应的批次减去已出重量
							this.jianxiugaipici(eo, eo
									.getEoperateRealityWeight() == null ? 0.0
									: eo.getEoperateRealityWeight(), eo
									.getEoperateRealityNumber() == null ? 0.0
									: eo.getEoperateRealityNumber());
							// 现在的库位发生了变化，减去相应的重量和件数
							// 判断 现在的重量和之前的重量是否发生了变化
							if (eof.getEoperateRealityWeight() != null) {
								// 如果重量发生变化，那么现在的库位减去现在的重量
								this.tarehouseGoodsService.jianTGoods(
										eof.getTarehouse(), eo.getExportSeed()
												.getGoods().getGoodsId(),
										eof.getEoperateRealityWeight(),
										eof.getEoperateRealityNumber());
								for (int i = 0; i < eof.getPici().length; i++) {
									this.jiaxiugaipici(eof.getPici()[i],
											eof.getEoperateRealityWeight(),
											eof.getEoperateRealityNumber());
								}
							} else {
								// 如果重量没有发生变化那么就给库位减去以前的重量
								this.tarehouseGoodsService.jianTGoods(
										eof.getTarehouse(), eo.getExportSeed()
												.getGoods().getGoodsId(),
										eo.getEoperateRealityWeight(),
										eo.getEoperateRealityNumber());
								for (int i = 0; i < eof.getPici().length; i++) {
									this.jiaxiugaipici(eof.getPici()[i],
											eo.getEoperateRealityWeight(),
											eo.getEoperateRealityNumber());
								}
							}
						} else {
							if (eof.getEoperateRealityWeight() != null) {
								// 如果库位没有发生变化的时候
								// 只是去加减相应的库存，批次，
								Double weight = eo.getEoperateRealityWeight() == null ? 0.0
										: eo.getEoperateRealityWeight();
								Double number = eo.getEoperateRealityNumber() == null ? 0.0
										: eo.getEoperateRealityNumber();
								if (eof.getEoperateRealityWeight() > (weight)) {
									this.tarehouseGoodsService
											.jianTGoods(
													eo.getTarehouse()
															.getTarehouseId(),
													eo.getExportSeed()
															.getGoods()
															.getGoodsId(),
													Double.parseDouble(df.format((eof
															.getEoperateRealityWeight() - weight))),
													Double.parseDouble(df.format((eof
															.getEoperateRealityNumber() - number))));
									// 进行操作批次
									StatisticsWorking s = new StatisticsWorking();
									String str = eo.getEOperatepici();
									String strs = str.replaceAll(",", "");
									int x = s.countInnerStr(str, ",");
									for (int i = 0; i < x; i++) {
										String id = strs.substring(i * 12,
												(i + 1) * 12);
										this.jiaxiugaipici(
												id,
												Double.parseDouble(df.format((eof
														.getEoperateRealityWeight() - weight))),
												Double.parseDouble(df.format((eof
														.getEoperateRealityNumber() - number))));
									}
								} else if (eof.getEoperateRealityWeight() < (eo
										.getEoperateRealityWeight() == null ? 0.0
										: eo.getEoperateRealityWeight())) {
									this.tarehouseGoodsService
											.zengGoods(
													eo.getTarehouse()
															.getTarehouseId(),
													eo.getExportSeed()
															.getGoods()
															.getGoodsId(),
													Double.parseDouble(df.format((eo
															.getEoperateRealityWeight() - eof
															.getEoperateRealityWeight()))),
													Double.parseDouble(df.format(eo
															.getEoperateRealityNumber()
															- eof.getEoperateRealityNumber())));
									this.jianxiugaipici(
											eo,
											Double.parseDouble(df.format((eo
													.getEoperateRealityWeight() - eof
													.getEoperateRealityWeight()))),
											Double.parseDouble(df.format(eo
													.getEoperateRealityNumber()
													- eof.getEoperateRealityNumber())));
								}
							}
						}
					} else {
						if (eof.getEoperateRealityWeight() != null) {
							// 如果库位没有发生变化的时候
							// 只是去加减相应的库存，批次，
							Double weight = eo.getEoperateRealityWeight() == null ? 0.0
									: eo.getEoperateRealityWeight();
							Double number = eo.getEoperateRealityNumber() == null ? 0.0
									: eo.getEoperateRealityNumber();
							if (eof.getEoperateRealityWeight() > weight) {
								this.tarehouseGoodsService
										.jianTGoods(
												eo.getTarehouse()
														.getTarehouseId(),
												eo.getExportSeed().getGoods()
														.getGoodsId(),
												Double.parseDouble(df.format((eof
														.getEoperateRealityWeight() - weight))),
												Double.parseDouble(df.format((eof
														.getEoperateRealityNumber() - number))));
								// 进行操作批次
								StatisticsWorking s = new StatisticsWorking();
								String str = eo.getEOperatepici();
								String strs = str.replaceAll(",", "");
								int x = s.countInnerStr(str, ",");
								for (int i = 0; i < x; i++) {
									String id = strs.substring(i * 12,
											(i + 1) * 12);
									this.jiaxiugaipici(
											id,
											Double.parseDouble(df.format((eof
													.getEoperateRealityWeight() - weight))),
											Double.parseDouble(df.format((eof
													.getEoperateRealityNumber() - number))));
								}
							} else if (eof.getEoperateRealityWeight() < eo
									.getEoperateRealityWeight()) {
								this.tarehouseGoodsService
										.zengGoods(
												eo.getTarehouse()
														.getTarehouseId(),
												eo.getExportSeed().getGoods()
														.getGoodsId(),
												Double.parseDouble(df.format((eo
														.getEoperateRealityWeight() - eof
														.getEoperateRealityWeight()))),
												Double.parseDouble(df.format(eo
														.getEoperateRealityNumber()
														- eof.getEoperateRealityNumber())));
								this.jianxiugaipici(
										eo,
										Double.parseDouble(df.format((eo
												.getEoperateRealityWeight() - eof
												.getEoperateRealityWeight()))),
										Double.parseDouble(df.format(eo
												.getEoperateRealityNumber()
												- eof.getEoperateRealityNumber())));
							}
						}
					}
					// 当重量和件数发生变化的时候
					if (eof.getEoperateRealityWeight() != null) {
						ExportSeed es = eo.getExportSeed();
						Double weight = eo.getEoperateRealityWeight() == null ? 0.0
								: eo.getEoperateRealityWeight();
						if (eof.getEoperateRealityWeight() > weight) {
							es.setEseedRealityWeight((es
									.getEseedRealityWeight() == null ? 0.0 : es
									.getEseedRealityWeight())
									+ Double.parseDouble(df.format(eof
											.getEoperateRealityWeight()
											- weight)));
							if (es.getExport().getExportCarryType()
									.equals("火运")) {
								// 关联修改子订单中的应收费用
								es.setEseedShouldCost(Double.parseDouble(cdf.format(es
										.getEseedShouldCost()
										+ (eof.getEoperateRealityWeight() - eo
												.getEoperateRealityWeight())
										* this.costTypeService.getHuoYunChuKu())));
								// 关联修改操作订单中的应收费用
								eo.setEoperateShouldCost(Double.parseDouble(cdf
										.format(eof.getEoperateRealityWeight()
												* this.costTypeService
														.getHuoYunChuKu())));
							} else if (es.getExport().getExportCarryType()
									.equals("汽运")) {
								es.setEseedShouldCost(Double.parseDouble(cdf.format(es
										.getEseedShouldCost()
										+ (eof.getEoperateRealityWeight() - (eo
												.getEoperateRealityWeight() == null ? 0
												: eo.getEoperateRealityWeight()))
										* this.costTypeService.getQiYunChuKu())));
								// 关联修改操作订单中的应收费用
								eo.setEoperateShouldCost(Double.parseDouble(cdf
										.format(eof.getEoperateRealityWeight()
												* this.costTypeService
														.getQiYunChuKu())));
							}
							// 当子订单的状态是出库完成的时候，将对应客户的库存产生影响
							if (es.getEseedOrderStatus().equals("出库完成")
									|| es.getEseedOrderStatus().equals("未收费")
									|| es.getEseedOrderStatus().equals("已收费")) {
								this.clientGoodsService
										.JianshaoKucun(
												es.getExport().getClient()
														.getClientId(),
												es.getGoods().getGoodsId(),
												Double.parseDouble(df.format(eof
														.getEoperateRealityWeight()
														- eo.getEoperateRealityWeight())));
							}
						} else if (eof.getEoperateRealityWeight() < (eo
								.getEoperateRealityWeight() == null ? 0.0 : eo
								.getEoperateRealityWeight())) {
							es.setEseedRealityWeight(es.getEseedRealityWeight()
									- Double.parseDouble(df.format(eo
											.getEoperateRealityWeight()
											- eof.getEoperateRealityWeight())));
							if (es.getExport().getExportCarryType()
									.equals("火运")) {
								es.setEseedShouldCost(Double.parseDouble(cdf.format(es
										.getEseedShouldCost()
										- (eo.getEoperateRealityWeight() - eof
												.getEoperateRealityWeight())
										* this.costTypeService.getHuoYunChuKu())));
								eo.setEoperateShouldCost(Double.parseDouble(cdf
										.format(eof.getEoperateRealityWeight()
												* this.costTypeService
														.getHuoYunChuKu())));
							} else if (es.getExport().getExportCarryType()
									.equals("汽运")) {
								es.setEseedShouldCost(Double.parseDouble(cdf.format(es
										.getEseedShouldCost()
										- (eo.getEoperateRealityWeight() - eof
												.getEoperateRealityWeight())
										* this.costTypeService.getQiYunChuKu())));
								eo.setEoperateShouldCost(Double.parseDouble(cdf
										.format(eof.getEoperateRealityWeight()
												* this.costTypeService
														.getQiYunChuKu())));
							}
							// 当子订单的状态是出库完成的时候，将对应客户的库存产生影响
							if (es.getEseedOrderStatus().equals("出库完成")
									|| es.getEseedOrderStatus().equals("未收费")
									|| es.getEseedOrderStatus().equals("已收费")) {
								this.clientGoodsService
										.ZengjiaKucun(
												es.getExport().getClient()
														.getClientId(),
												es.getGoods().getGoodsId(),
												Double.parseDouble(df.format(eo
														.getEoperateRealityWeight()
														- eof.getEoperateRealityWeight())),
												0.0);
							}
						}

						this.exportSeedService.update(es);
					}
					// 当件数不为空的时候
					if (eof.getEoperateRealityNumber() != null) {
						Double number = eo.getEoperateRealityNumber() == null ? 0.0
								: eo.getEoperateRealityNumber();
						ExportSeed es = eo.getExportSeed();
						if (eof.getEoperateRealityNumber() > (number)) {
							es.setEseedRealityNumber(Double.parseDouble(df
									.format(es.getEseedRealityNumber()
											+ (eof.getEoperateRealityNumber() - (number)))));
						} else if (eof.getEoperateRealityNumber() < number) {
							es.setEseedRealityNumber(Double.parseDouble(df.format(es
									.getEseedRealityNumber()
									- (number - (eof.getEoperateRealityNumber())))));
						}
						this.exportSeedService.update(es);
					}
					// 当修改实收费用的时候，子订单发生相应的变化
					if (eof.getEoperateRealityCost() != null) {
						ExportSeed es = eo.getExportSeed();
						if (eof.getEoperateRealityCost() > (eo
								.getEoperateRealityCost() == null ? 0.0 : eo
								.getEoperateRealityCost())) {
							es.setEseedRealityCost(Double.parseDouble(cdf.format((es
									.getEseedRealityCost() == null ? 0.0 : es
									.getEseedRealityCost())
									+ (eof.getEoperateRealityCost() - (eo
											.getEoperateRealityCost() == null ? 0.0
											: eo.getEoperateRealityCost())))));
						} else if (eof.getEoperateRealityCost() < (eo
								.getEoperateRealityCost() == null ? 0.0 : eo
								.getEoperateRealityCost())) {
							es.setEseedRealityCost(Double.parseDouble(cdf.format(es
									.getEseedRealityCost()
									- ((eo.getEoperateRealityCost() == null ? 0.0
											: eo.getEoperateRealityCost()) - eof
											.getEoperateRealityCost()))));
						}
						this.exportSeedService.update(es);
					}
					// 当修改结算方式的时候，子订单发生相应的变化
					if (eof.getEoperateClientAccounts() != null) {
						ExportSeed es = eo.getExportSeed();
						es.setEseedClientAccounts(eof
								.getEoperateClientAccounts());// 修改结算范式
						this.exportSeedService.update(es);
					}
					// 当修改支付方式的时候，子订单发生相应的变化
					Integer pay = eof.getEoperatePaymentFashion();
					if (pay != 0 && pay != null) {
						ExportSeed es = eo.getExportSeed();
						es.setPaymentFashion(this.paymentFashionService
								.getPaymentFashionId(pay));
						this.exportSeedService.update(es);
					}
				}// 如果是审核之后的判断结束

				// 进行修改对应的操作订单就可以了，并且记录到修改记录表中
				// 修改是否过磅，理算
				if (eof.getEoperatePonderationTrue() != null) {
					// 判断原值和传入的值是否发生了变化
					if (eof.getEoperatePonderationTrue().equals(
							eo.getEoperatePonderationTrue()) == false) {
						this.updateRecordService.RecordUpdate(
								eo.getEoperateId(),
								iu.getIuserName(),
								"修改是否过磅，原值：" + eo.getEoperatePonderationTrue()
										+ ",更改为："
										+ eof.getEoperatePonderationTrue());
						eo.setEoperatePonderationTrue(eof
								.getEoperatePonderationTrue());// 设置是否过磅
					}
				}
				// 修改库位
				if (eof.getTarehouse() != null) {
					if (eof.getTarehouse().equals(
							eo.getTarehouse().getTarehouseId()) == false) {
						this.updateRecordService.RecordUpdate(
								eo.getEoperateId(),
								iu.getIuserName(),
								"修改库位，原值："
										+ eo.getTarehouse().getTarehouseName()
										+ ",更改为："
										+ this.TarehouseService.getTarehouseId(
												eof.getTarehouse())
												.getTarehouseName());
						eo.setTarehouse(this.TarehouseService
								.getTarehouseId(eof.getTarehouse()));// 修改库位

						String pici = "";
						for (int i = 0; i < eof.getPici().length; i++) {
							pici += eof.getPici()[i] + ",";
						}

						// 修改了库位相当于修改了批次
						this.updateRecordService.RecordUpdate(
								eo.getEoperateId(), iu.getIuserName(),
								"修改批次，原值：" + eo.getEOperatepici() + ",更改为："
										+ pici);
						eo.setEOperatepici(pici);
					}
				}

				// 修改执行人
				if (eof.getInteriorUserByEoperateStoreman() != null) {
					if (eof.getInteriorUserByEoperateStoreman()
							.equals(eo.getInteriorUserByEoperateStoreman()
									.getIuserId()) == false) {
						// 判断修改了执行人
						this.updateRecordService
								.RecordUpdate(
										eo.getEoperateId(),
										iu.getIuserName(),
										"修改执行人，原值："
												+ eo.getInteriorUserByEoperateStoreman()
														.getIuserName()
												+ ",更改为："
												+ this.interiorUserService
														.getInteriorUserId(
																eof.getInteriorUserByEoperateStoreman())
														.getIuserName());
						eo.setInteriorUserByEoperateStoreman(this.interiorUserService
								.getInteriorUserId(eof
										.getInteriorUserByEoperateStoreman()));
					}
				}
				// 修改重量
				if (eof.getEoperateRealityWeight() != null) {
					if (eof.getEoperateRealityWeight().equals(
							eo.getEoperateRealityWeight()) == false) {
						this.updateRecordService
								.RecordUpdate(
										eo.getEoperateId(),
										iu.getIuserName(),
										"修改重量，原值："
												+ (eo.getEoperateRealityWeight() == null ? 0.0
														: eo.getEoperateRealityWeight())
												+ ",更改为："
												+ eof.getEoperateRealityWeight());
						eo.setEoperateRealityWeight(eof
								.getEoperateRealityWeight());
					}
				}
				// 修改件数
				if (eof.getEoperateRealityNumber() != null) {
					if (eof.getEoperateRealityNumber().equals(
							eo.getEoperateRealityNumber()) == false) {
						this.updateRecordService
								.RecordUpdate(
										eo.getEoperateId(),
										iu.getIuserName(),
										"修改件数，原值："
												+ (eo.getEoperateRealityNumber() == null ? 0.0
														: eo.getEoperateRealityNumber())
												+ ",更改为："
												+ eof.getEoperateRealityNumber());
						eo.setEoperateRealityNumber(eof
								.getEoperateRealityNumber());
					}
				}
				// 修改天车工
				if (eof.getEoperateCraneman() != null) {
					if (eof.getEoperateCraneman().equals(
							eo.getEoperateCraneman()) == false) {
						this.updateRecordService
								.RecordUpdate(
										eo.getEoperateId(),
										iu.getIuserName(),
										"修改天车工，原值："
												+ (eo.getEoperateCraneman() == null ? "无"
														: eo.getEoperateCraneman())
												+ ",更改为："
												+ eof.getEoperateCraneman());
						eo.setEoperateCraneman(eof.getEoperateCraneman());
					}
				}
				// 修改装卸工
				if (eof.getEoperateStevedore() != null) {
					if (eof.getEoperateStevedore().equals(
							eo.getEoperateStevedore()) == false) {
						this.updateRecordService
								.RecordUpdate(
										eo.getEoperateId(),
										iu.getIuserName(),
										"修改装卸工，原值："
												+ (eo.getEoperateStevedore() == null ? "无"
														: eo.getEoperateStevedore())
												+ ",更改为："
												+ eof.getEoperateStevedore());
						eo.setEoperateStevedore(eof.getEoperateStevedore());
					}
				}
				// 修改实收费用
				if (eof.getEoperateRealityCost() != null) {
					if (eof.getEoperateRealityCost().equals(
							eo.getEoperateRealityCost()) == false) {
						this.updateRecordService
								.RecordUpdate(
										eo.getEoperateId(),
										iu.getIuserName(),
										"修改实收费用，原值："
												+ eo.getEoperateRealityCost()
												+ "更改为："
												+ eof.getEoperateRealityCost());
						eo.setEoperateRealityCost(eof.getEoperateRealityCost());// 设置实收费用
					}
				}
				// 修改支付方式
				Integer pay = eof.getEoperatePaymentFashion();
				if (pay != 0 && pay != null) {
					if (pay.equals((eo.getEoperatePaymentFashion() == null ? 0
							: eo.getEoperatePaymentFashion().getPfashionId())) == false) {
						if (this.paymentFashionService
								.getPaymentFashionId(pay)
								.getPfashionName()
								.equals(eo.getEoperatePaymentFashion() == null ? 0
										: eo.getEoperatePaymentFashion()
												.getPfashionName()) == false) {
							this.updateRecordService
									.RecordUpdate(
											eo.getEoperateId(),
											iu.getIuserName(),
											"修改支付方式，原值："
													+ (eo.getEoperatePaymentFashion() == null ? "无"
															: eo.getEoperatePaymentFashion()
																	.getPfashionName())
													+ "更改为："
													+ this.paymentFashionService
															.getPaymentFashionId(
																	pay)
															.getPfashionName());
						}
						if (this.paymentFashionService
								.getPaymentFashionId(pay)
								.getPfashionReceipt()
								.equals(eo.getEoperatePaymentFashion() == null ? "无"
										: eo.getEoperatePaymentFashion()
												.getPfashionReceipt()) == false) {
							this.updateRecordService
									.RecordUpdate(
											eo.getEoperateId(),
											iu.getIuserName(),
											"修改票据类型，原值："
													+ (eo.getEoperatePaymentFashion() == null ? "无"
															: eo.getEoperatePaymentFashion()
																	.getPfashionReceipt())
													+ "更改为："
													+ this.paymentFashionService
															.getPaymentFashionId(
																	pay)
															.getPfashionReceipt());
						}
						eo.setEoperatePaymentFashion(this.paymentFashionService
								.getPaymentFashionId(pay));
					}
				}
				// 修改结算范式
				if (eof.getEoperateClientAccounts() != null) {
					if (eof.getEoperateClientAccounts().equals(
							eo.getEoperateClientAccounts()) == false) {
						this.updateRecordService.RecordUpdate(
								eo.getEoperateId(),
								iu.getIuserName(),
								"修改结算方式，原值：" + eo.getEoperateClientAccounts()
										+ "更改为："
										+ eof.getEoperateClientAccounts());
						eo.setEoperateClientAccounts(eof
								.getEoperateClientAccounts());
					}
				}
				// 设置备注
				eo.setEoperateRemark(eo.getEoperateRemark() + ","
						+ eof.getEoperateRemark());
				boolean ok = this.exportOperateService.update(eo);
				if (ok) {
					message = "修改成功！";
				} else {
					message = "修改失败！";
				}
			}

		}
		eof.setTarehouse(null);
		eof.setInteriorUserByEoperateStoreman(null);
		eof.setEoperateRealityWeight(null);
		eof.setEoperateRealityCost(null);
		eof.setEoperateRealityNumber(null);
		eof.setEoperatePonderationTrue(null);
		eof.setEoperateCraneman(null);
		eof.setEoperateStevedore(null);
		eof.setEoperateClientAccounts(null);
		eof.setEoperatePaymentFashion(0);
		eof.setPici(null);

		PrintWriter out = response.getWriter();
		out.print("<script>alert('"
				+ message
				+ "');window.history.go(-1);document.location.reload();</script>");
		// 返回到对应的页面
		return null;
	}

	// 保管在操作的时候修改对应的库位和批次
	public ActionForward caozuo_update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ExportOperateForm eof = (ExportOperateForm) form;
		String message = "";
		PrintWriter out = response.getWriter();
		if (eof != null) {
			// 通过编号进行查询
			ExportOperate eo = this.exportOperateService.getExportOperateId(eof
					.getEoperateId());
			if (eo != null) {
				eo.setTarehouse(this.TarehouseService.getTarehouseId(eof
						.getTarehouse()));// 修改库位
				String pici = "";
				for (int i = 0; i < eof.getPici().length; i++) {
					pici += eof.getPici()[i] + ",";
				}
				eo.setEOperatepici(pici);// 修改批次
				eo.setEoperateRemark(eo.getEoperateRemark() + ","
						+ eof.getEoperateRemark());// 备注
				boolean ok = this.exportOperateService.update(eo);
				if (ok) {
					this.loginLogService.updateLogin(request.getSession()
							.getAttribute("loginlogId").toString(),
							"修改出库操作订单中的库位和批次,订单编号："
									+ eo.getExportSeed().getExport()
											.getExportId());
					message = "修改成功";
				} else {
					message = "修改失败！";
				}
			} else {
				message = "修改失败！";
			}
		} else {
			message = "修改失败！";
		}
		out.print("<script>alert('"
				+ message
				+ "');window.history.go(-1);document.location.reload();</script>");
		out.flush();
		out.close();
		return null;
	}

}
