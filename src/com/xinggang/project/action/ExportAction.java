package com.xinggang.project.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.xinggang.project.entity.ClientGoods;
import com.xinggang.project.entity.Export;
import com.xinggang.project.entity.ExportSeed;
import com.xinggang.project.entity.Goods;
import com.xinggang.project.entity.InteriorUser;
import com.xinggang.project.form.ExportForm;
import com.xinggang.project.service.ClientGoodsService;
import com.xinggang.project.service.ClientService;
import com.xinggang.project.service.ExportOperateService;
import com.xinggang.project.service.ExportSeedService;
import com.xinggang.project.service.ExportService;
import com.xinggang.project.service.GoodsService;
import com.xinggang.project.service.LoginLogService;
import com.xinggang.project.tools.PageRow;
import com.xinggang.project.tools.PresentTime;

/**
 * 出库总订单类action
 * 
 * @author Administrator
 * 
 */
public class ExportAction extends DispatchAction {

	// 出库总订单service
	private ExportService exportService;
	// 客户service
	private ClientService clientService;
	// 登录日志表service
	private LoginLogService loginLogService;
	// 客户货物库存service
	private ClientGoodsService clientGoodsService;
	// 货物service
	private GoodsService goodsService;
	// 时间工具类
	private PresentTime pt = new PresentTime();
	// 编号
	private PageRow pr = new PageRow();
	// 出库子订单service
	private ExportSeedService exportSeedService;
	// 出库操作订单service
	private ExportOperateService exportOperateService;

	public void setExportOperateService(
			ExportOperateService exportOperateService) {
		this.exportOperateService = exportOperateService;
	}

	public void setExportSeedService(ExportSeedService exportSeedService) {
		this.exportSeedService = exportSeedService;
	}

	public void setClientGoodsService(ClientGoodsService clientGoodsService) {
		this.clientGoodsService = clientGoodsService;
	}

	public void setLoginLogService(LoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public void setExportService(ExportService exportService) {
		this.exportService = exportService;
	}

	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return super.execute(mapping, form, request, response);
	}

	// 查询登录客户的货物库存，显示在对应的页面，对应的货物在客户的库存中具有的货物
	public ActionForward getClientGoodsFirst(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ExportForm ef = (ExportForm) form;
		// 首先取出登录客户
		Integer clientId = ef.getClient();
		List<Goods> cglist = this.goodsService.getClientGoods(clientId);// 通过客户查询

		// 保存到request中
		request.setAttribute("cglist", cglist);
		// 返回到对应的页面
		return super.execute(mapping, form, request, response);
	}

	// 发起出库，填写相应的信息，选择对应的货物，货物多选
	public ActionForward FaQiChuKu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ExportForm ef = (ExportForm) form;
		if (ef != null) {
			Export export = new Export();// new一个实例化对象
			try {
				String numbers = pr.getChuKuNumber();
				// 总订单内容
				String faqiren = "";
				if(request.getSession().getAttribute("iulist")!=null){
					InteriorUser iu = (InteriorUser) request.getSession().getAttribute("iulist");// 对应的内部登录人
					faqiren = iu.getIuserName();
				}else{
					faqiren = this.clientService.getClientId(ef.getClient()).getClientAbbreviation();
				}
				export.setExportFaQiRen(faqiren);//保存对应的订单发起人
				export.setExportId("出" + pt.getDatesNotTime() + numbers);// 设置出库的总订单编号
				export.setExportClientNumber(ef.getExportClientNumber());// 设置客户填写的订单号，相当于页面的提货单号
				export.setExportReateTime(pt.getTimes());// 设置订单的生成时间
				export.setClient(this.clientService.getClientId(ef.getClient()));// 设置客户，在页面中隐藏的文本框中，将保存的客户的登录id保存在里面
				export.setExportCarryType(ef.getExportCarryType());// 设置运输方式
				export.setExportWagonNumber(ef.getExportWagonNumber());// 设置车号
				export.setExportDriverName(ef.getExportDriverName());// 设置司机姓名
				export.setExportDriverTel(ef.getExportDriverTel());// 设置司机的电话
				export.setExportOrderStatus("计划出库");// 设置总订单的状态为计划出库
				export.setExportDefinedOne(ef.getExportDefinedOne());// 设置是否接受超发
				export.setExportDefinedTwo(ef.getExportDefinedTwo());// 设置订单的有效期
				export.setExportRemark(ef.getExportRemark());// 设置备注

				boolean ok = this.exportService.save(export);// 添加到出库总订单表中
				System.out.println("传入的货物的长度是：" + ef.getGoodsId().length
						+ "总订单的返回值是：" + ok);
				if (ok) {
					// 出库子订单内容
					int num = 0;
					for (int i = 0; i < ef.getGoodsId().length; i++) {
						ExportSeed es = new ExportSeed();// new一个出库子订单实例化对象
						String is = "";
						if ((i + 1) < 10) {
							is = "0" + (i + 1);
						} else {
							is = String.valueOf(i + 1);
						}
						String ids = export.getExportId().substring(2,
								export.getExportId().length());
						es.setEseedId("出Z" + ids + is);// 设置子订单编号
						es.setExport(export);// 设置总订单编号，外键
						es.setGoods(this.goodsService.getGoodsId(ef
								.getGoodsId()[i]));// 设置货物，多个
						es.setEseedShouldWeight(ef.getGoodsWeight()[i]);// 设置应出重量，多个
						es.setEseedOrderStatus("计划出库");// 设置子订单的订单状态
						es.setEseedRemark(ef.getRemark()[i]);// 添加到备注
						es.setEseedDefinedOne(ef.getEseedDefinedOne());//设置联系电话

						boolean oks = this.exportSeedService.save(es);// 添加到出库子订单表中
						System.out.println("传入的货物的长度是："
								+ ef.getGoodsId().length + "z订单的返回值是：" + oks);
						if (oks) {
							// 该客户相应的货物库存减少
							this.clientGoodsService.JianshaoKucun(
									ef.getClient(), ef.getGoodsId()[i],
									ef.getGoodsWeight()[i]);
							num++;
						}
					}
					this.loginLogService.updateLogin(request.getSession()
							.getAttribute("loginlogId").toString(), "发起出库:"
							+ "出" + pt.getDatesNotTime() + numbers);// 记录在日志表中

					if (num == ef.getGoodsId().length) {
						request.getSession().setAttribute("faqichukuMessage",
								"出库发起成功！");
					} else {
						request.getSession().setAttribute("faqichukuMessage",
								"出库发起失败！");
					}

				} else {
					request.getSession().setAttribute("faqichukuMessage",
							"出库发起失败！");
				}
			} catch (Exception e) {

			}
		} else {
			request.getSession().setAttribute("faqichukuMessage", "出库发起失败！");
		}

		PrintWriter out = response.getWriter();
		out.print("<script>alert('"
				+ request.getSession().getAttribute("faqichukuMessage")
				+ "');document.location.href='/XGProject/cangchu/page/faqichuku.jsp';</script>");
		// 返回到对应的页面
		return null;
	}

	int i = 0;

	// 手机app客户发起出库
	public ActionForward FaQiChuKuApp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String mess = "";

		PrintWriter out = response.getWriter();
		ExportForm ef = (ExportForm) form;

		int kehuId = Integer.parseInt(request.getSession()
				.getAttribute("clientId").toString());

		// 根据品类，规格，产地，属性，材质等到客户货物中查询是否有该出库货物

		Goods good = goodsService.getOneGoods(ef.getGoodsCategoryId(),
				ef.getGoodsStandardId(), ef.getGoodsName(),
				ef.getGoodsQualityId(), ef.getGoodsPropertyId(),
				ef.getGoodsYieldlyId());

		// 根据客户和货物查询是否有该货物
		if (good == null) {
			mess = "没有此货物，请重新选择！";
			if (request.getParameter("ff") != null) {
				out.print(mess);// 如果是手机app的访问怎直接返回内容
				out.flush();
				out.close();
				return null;
			}
			out.print("<script>alert('" + mess
					+ "');window.history.go(-1);</script>");
			out.flush();
			out.close();
			return null;
		} else {
			List<ClientGoods> clientGoods = clientGoodsService
					.getClientGoodsByCG(kehuId, good.getGoodsId());

			if (clientGoods.size() <= 0) {
				mess = "发起出库失败！你没有该出库货物！";
				if (request.getParameter("ff") != null) {
					out.print(mess);// 如果是手机app的访问怎直接返回内容
					out.flush();
					out.close();
					return null;
				}
				out.print("<script>alert('" + mess
						+ "');window.history.go(-1);</script>");
				out.flush();
				out.close();
				return null;
			} else {
				// 如果出库重量大于客户货物库存重量就返回
				if (clientGoods.get(0).getCgoodsWeight() < ef
						.getExportShouldWeights()) {
					mess = "出库货物重量不能大于库存货物重量！";
					if (request.getParameter("ff") != null) {
						out.print(mess);// 如果是手机app的访问怎直接返回内容
						out.flush();
						out.close();
						return null;
					}
					out.print("<script>alert('" + mess
							+ "');window.history.go(-1);</script>");
					out.flush();
					out.close();
					return null;
				} else {
					// 根据货物id查询该货物
					try {

						Export export = new Export();// new一个实例化对象
						String numbers = pr.getChuKuNumber();
						// 总订单内容
						export.setExportId("出" + pt.getDatesNotTime() + numbers);// 设置出库的总订单编号
						export.setExportClientNumber(ef.getExportClientNumber());// 设置客户填写的订单号，相当于页面的提货单号
						export.setExportReateTime(pt.getTimes());// 设置订单的生成时间
						export.setClient(this.clientService.getClientId(kehuId));// 设置客户，在页面中隐藏的文本框中，将保存的客户的登录id保存在里面
						export.setExportCarryType(ef.getExportCarryType());// 设置运输方式
						export.setExportWagonNumber(ef.getExportWagonNumber());// 设置车号
						export.setExportDriverName(ef.getExportDriverName());// 设置司机姓名
						export.setExportDriverTel(ef.getExportDriverTel());// 设置司机的电话
						export.setExportOrderStatus("计划出库");// 设置总订单的状态为计划出库
						if (ef.getExportDefinedOne() != null
								&& ef.getExportDefinedOne().equals("接受超发")) {
							export.setExportDefinedOne("接受超发");// 设置是否接受超发
						} else {
							export.setExportDefinedOne("不接受超发");// 设置是否接受超发
						}
						export.setExportDefinedTwo(ef.getExportDefinedTwo());// 设置订单的有效期

						if (ef.getExportRemark() != null
								&& ef.getExportRemark().equals("配送")) {
							export.setExportRemark("配送");// 设置配送
						} else {
							export.setExportRemark("不配送");// 设置配送
						}

						boolean ok = this.exportService.save(export);// 添加到出库总订单表中

						if (ok) {

							// 出库子订单内容
							int num = 0;

							ExportSeed es = new ExportSeed();// new一个出库子订单实例化对象
							String is = "";
							if (i + 1 < 10) {
								is = "0" + i + 1;
							} else {
								is = String.valueOf(i + 1);
							}
							i++;
							es.setEseedId("出Z" + pt.getDatesNotTime() + numbers
									+ is);// 设置子订单编号
							es.setExport(export);// 设置总订单编号，外键
							es.setGoods(good);// 设置货物
							es.setEseedShouldWeight(ef.getExportShouldWeights());// 设置应出重量
							es.setEseedOrderStatus("计划出库");// 设置子订单的订单状态
							es.setEseedDefinedOne(ef.getEseedDefinedOne()
									.toString());// 单位电话
							es.setEseedRemark(ef.getEseedRemark());// 添加到备注

							boolean oks = exportSeedService.save(es);// 添加到出库子订单表中
							if (oks) {
								// 该客户相应的货物库存减少
								this.clientGoodsService.JianshaoKucun(kehuId,
										good.getGoodsId(),
										ef.getExportShouldWeights());
								num++;

							}
							this.loginLogService.updateLogin(request
									.getSession().getAttribute("loginlogId")
									.toString(),
									"发起出库:" + "出" + pt.getDatesNotTime()
											+ numbers);// 记录在日志表中

							if (num > 0) {
								mess = "发起出库成功！";
							} else {
								mess = "发起出库失败！";
							}

						} else {
							mess = "发起出库失败！";
							// out.print("<script>alert('发起出库失败！');document.location.href='/XGProject/app-web/indent-out.jsp';</script>");
						}
					} catch (Exception e) {
					}
				}

			}
		}

		if (request.getParameter("ff") != null) {
			out.print(mess);
			out.flush();
			out.close();
			return null;
		}
		out.print("<script>alert('"
				+ mess
				+ "');document.location.href='/XGProject/app-web/indent-out.jsp';</script>");
		// 返回到对应的页面
		return null;
	}

	// 取消订单，判断内部人员是否开始操作，如果开始操作，则订单取消不了
	public ActionForward QuXiaoDingDan(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ExportForm ef = (ExportForm) form;
		if (ef != null) {
			Export export = this.exportService.getExportId(ef.getExportId());// 通过编号进行查询
			if (export != null) {
				if (this.exportOperateService.getQuXiaoDingDan(
						export.getExportId()).size() > 0) {
					request.getSession().setAttribute("QuXiaoMessage",
							"订单已经开始操作，无法取消，取消请联系管理员！");
				} else {
					List<ExportSeed> eslist = this.exportSeedService
							.getExportId(export.getExportId());// 通过总订单编号查询
					if (eslist.size() > 0) {
						for (int i = 0; i < eslist.size(); i++) {
							ExportSeed es = eslist.get(i);
							es.setEseedOrderStatus("订单取消");
							boolean ok = this.exportSeedService.update(es);
							if (ok) {
								this.clientGoodsService.ZengjiaKucun(export
										.getClient().getClientId(), es
										.getGoods().getGoodsId(), es
										.getEseedShouldWeight(), 0.0);// 增加该客户的库存
								request.getSession().setAttribute(
										"QuXiaoMessage", "取消成功！");
								this.loginLogService.updateLogin(request
										.getSession()
										.getAttribute("loginlogId").toString(),
										"出库订单取消" + export.getExportId());// 记录到日志表当中
							} else {
								request.getSession().setAttribute(
										"QuXiaoMessage", "取消失败！");
							}
						}
					} else {
						request.getSession().setAttribute("QuXiaoMessage",
								"取消失败！");
					}
				}
			} else {
				request.getSession().setAttribute("QuXiaoMessage", "取消失败！");
			}
		} else {
			request.getSession().setAttribute("QuXiaoMessage", "取消失败！");
		}

		// 返回到对应的页面
		return super.execute(mapping, form, request, response);
	}

	// 当客户点击修改的时候调用的方法
	public ActionForward getClientXiuGai(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ExportForm ef = (ExportForm) form;
		if (ef != null) {
			Export export = this.exportService.getExportId(ef.getExportId());// 通过编号查询
			if (export != null) {
				List<ExportSeed> eslist = this.exportSeedService.getExportId(ef
						.getExportId());// 通过总订单编号查询
				// 将两个值保存到request中
				request.setAttribute("getxiugaiList", eslist);
				request.setAttribute("export", export);
			}
		}
		// 返回到对应的界面
		return super.execute(mapping, form, request, response);
	}

	// 当客户点击修改的时候调用的方法
	public ActionForward ClientXiuGai(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ExportForm ef = (ExportForm) form;
		if (ef != null) {

			Export export = this.exportService.getExportId(ef.getExportId());// 通过编号查询
			if (this.exportOperateService.getQuXiaoDingDan(ef.getExportId())
					.size() > 0) {
				request.getSession().setAttribute("xiugaiExportMessage",
						"订单已开始操作，无法修改！");
			} else {
				if (export != null) {
					export.setExportClientNumber(ef.getExportClientNumber());// 修改客户的订单号
					export.setExportWagonNumber(ef.getExportWagonNumber());// 修改车号
					export.setExportDriverName(ef.getExportDriverName());// 修改司机姓名
					export.setExportDriverTel(ef.getExportDriverTel());// 修改司机的电话
					boolean ok = this.exportService.update(export);
					if (ok) {
						request.getSession().setAttribute(
								"xiugaiExportMessage", "修改成功！");
						this.loginLogService.updateLogin(request.getSession()
								.getAttribute("loginlogId").toString(),
								"修改出库订单" + export.getExportId());// 记录到日志表中
					} else {
						request.getSession().setAttribute(
								"xiugaiExportMessage", "修改失败！");
					}
				} else {
					request.getSession().setAttribute("xiugaiExportMessage",
							"修改失败！");
				}
			}
		} else {
			request.getSession().setAttribute("xiugaiExportMessage", "修改失败！");
		}
		// 返回到对应的界面
		return super.execute(mapping, form, request, response);
	}

	// 进行修改订单的发起时间
	public ActionForward UpdateTime(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ExportForm ef = (ExportForm) form;
		PrintWriter out = response.getWriter();
		String mess = "";
		if (ef != null) {
			// 通过编号进行查询
			Export e = this.exportService.getExportId(ef.getExportId());
			e.setExportReateTime(ef.getExportReateTime());// 进行设置时间
			boolean ok = this.exportService.update(e);
			if (ok) {
				mess = "修改成功！";
			} else {
				mess = "修改失败！";
			}
		} else {
			mess = "修改失败！";
		}
		out.print("<script>alert('" + mess
				+ "');window.history.go(-1);</script>");
		out.flush();
		out.close();
		return null;
	}
}
