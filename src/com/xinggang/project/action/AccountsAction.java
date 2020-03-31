package com.xinggang.project.action;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.xinggang.project.entity.Accounts;
import com.xinggang.project.entity.Client;
import com.xinggang.project.entity.ClientGoods;
import com.xinggang.project.entity.Export;
import com.xinggang.project.entity.ExportSeed;
import com.xinggang.project.entity.Input;
import com.xinggang.project.entity.InputSeed;
import com.xinggang.project.entity.InteriorUser;
import com.xinggang.project.entity.ShiftStock;
import com.xinggang.project.entity.ShiftStockSeed;
import com.xinggang.project.entity.Xiazhanfei;
import com.xinggang.project.form.AccountsForm;
import com.xinggang.project.service.AccountsService;
import com.xinggang.project.service.AppMessageService;
import com.xinggang.project.service.ClientGoodsService;
import com.xinggang.project.service.ClientService;
import com.xinggang.project.service.CostTypeService;
import com.xinggang.project.service.ExportSeedService;
import com.xinggang.project.service.ExportService;
import com.xinggang.project.service.InputSeedService;
import com.xinggang.project.service.InputService;
import com.xinggang.project.service.InteriorUserService;
import com.xinggang.project.service.LoginLogService;
import com.xinggang.project.service.PaymentFashionService;
import com.xinggang.project.service.SetAccountsService;
import com.xinggang.project.service.ShiftStockSeedService;
import com.xinggang.project.service.ShiftStockService;
import com.xinggang.project.service.SteelPriceService;
import com.xinggang.project.service.XiazhanfeiService;
import com.xinggang.project.tools.PageRow;
import com.xinggang.project.tools.PresentTime;

/**
 * 滞纳金Action
 */
public class AccountsAction extends DispatchAction {
	// 滞纳金service
	private AccountsService accountsService;
	// 客户service
	private ClientService clientService;
	// 日志service
	private LoginLogService loginLogService;
	// 过户子订单service
	private ShiftStockSeedService shiftStockSeedService;
	// 出库子订单service
	private ExportSeedService exportSeedService;
	// 入库子订单service
	private InputSeedService inputSeedService;
	// 过户总订单service
	private ShiftStockService shiftStockService;
	// 出库总订单service
	private ExportService exportService;
	// 入库总订单service
	private InputService inputService;
	// 客户货物库存service
	private ClientGoodsService clientGoodsService;
	// 支付方式service
	private PaymentFashionService paymentFashionService;
	// 滞纳金设置service
	@SuppressWarnings("unused")
	private SetAccountsService setAccountsService;
	// 内部人员service
	@SuppressWarnings("unused")
	private InteriorUserService interiorUserService;
	// 实例化取得当前时间工具类
	PresentTime pt = new PresentTime();
	// 下站费service
	private XiazhanfeiService xiazhanfeiService;

	// 费用类型service
	private CostTypeService costTypeService;
	// app消息service
	private AppMessageService appMessageService;
	// 今日钢价service
	private SteelPriceService steelPriceService;

	private PageRow pr = new PageRow();

	public void setSteelPriceService(SteelPriceService steelPriceService) {
		this.steelPriceService = steelPriceService;
	}

	public void setAccountsService(AccountsService accountsService) {
		this.accountsService = accountsService;
	}

	public void setXiazhanfeiService(XiazhanfeiService xiazhanfeiService) {
		this.xiazhanfeiService = xiazhanfeiService;
	}

	public void setInteriorUserService(InteriorUserService interiorUserService) {
		this.interiorUserService = interiorUserService;
	}

	public void setAppMessageService(AppMessageService appMessageService) {
		this.appMessageService = appMessageService;
	}

	public void setSetAccountsService(SetAccountsService setAccountsService) {
		this.setAccountsService = setAccountsService;
	}

	public void setPaymentFashionService(
			PaymentFashionService paymentFashionService) {
		this.paymentFashionService = paymentFashionService;
	}

	public void setClientGoodsService(ClientGoodsService clientGoodsService) {
		this.clientGoodsService = clientGoodsService;
	}

	public void setInputService(InputService inputService) {
		this.inputService = inputService;
	}

	public void setExportService(ExportService exportService) {
		this.exportService = exportService;
	}

	public void setShiftStockService(ShiftStockService shiftStockService) {
		this.shiftStockService = shiftStockService;
	}

	public void setExportSeedService(ExportSeedService exportSeedService) {
		this.exportSeedService = exportSeedService;
	}

	public void setInputSeedService(InputSeedService inputSeedService) {
		this.inputSeedService = inputSeedService;
	}

	public void setShiftStockSeedService(
			ShiftStockSeedService shiftStockSeedService) {
		this.shiftStockSeedService = shiftStockSeedService;
	}

	public void setLoginLogService(LoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public void setCostTypeService(CostTypeService costTypeService) {
		this.costTypeService = costTypeService;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return super.execute(mapping, form, request, response);
	}

	// 结算之前的查询
	public ActionForward QueryJieSuanFirst(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 获得上传的form
		AccountsForm af = (AccountsForm) form;
		// 判断起始的时间是否为空，如果为空则设置起始日期为当前时间减去一个月，如果不为空则在开始时间的末尾加00:00:00
		if (af.getBegin() == null || af.getBegin().equals("")) {
			af.setBegin(pt.getNowJianYi());
		} else {
			af.setBegin(af.getBegin());
		}
		// 判断结束日期，如果结束日期为空则设置结束日期为当前的日期，如果不为空则在上传的结束时间的末尾加23:59:59
		if (af.getFinish() == null || af.getFinish().equals("")) {
			af.setFinish(pt.getTimes());
		} else {
			af.setFinish(af.getFinish());
		}
		// 判断输入的客户是否为空，如果为空则查询所有的
		if (af.getJiancheng() == null) {
			af.setJiancheng("");
		}
		int pageNow = 1;// 当前页
		int rowSize = 5;// 每页显示的行数，可以在界面上设置显示的行数
		// 如果上传的当前页不为空的时候，当前页等于上传的当前页
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}
		// 如果页面上传的显示的行数不为空的时候，设置每页显示的行数为上传的页数
		if (request.getParameter("rowSize") != null) {
			rowSize = Integer.parseInt(request.getParameter("rowSize"));
		}
		// 通过条件查询总的行数
		int pageCount = this.clientService.getWeiJieSuanCount(af.getBegin(),
				af.getFinish(), af.getJiancheng(), pageNow, rowSize);
		// 判断当前页是否大于显示的页数
		if (pageNow >= pageCount) {
			pageNow = pageCount;
		}
		if (pageNow <= 1) {
			pageNow = 1;
		}
		// 通过客户进行分页查询，而通过时间进行查询对应的费用订单
		List<Client> clist = this.clientService.getWeiJieSuan(af.getBegin(),
				af.getFinish(), af.getJiancheng(), pageNow, rowSize);

		DecimalFormat df = new DecimalFormat("###################0.00");
		DecimalFormat ddf = new DecimalFormat("###############0.000");
		JSONArray array = new JSONArray();
		PrintWriter out = response.getWriter();

		Double huochu = 0.0;// 火运出库费
		Double qichu = 0.0;// 汽运出库费
		Double huoru = 0.0;// 火运入库费
		Double qiru = 0.0;// 汽运入库费
		Double erci = 0.0;// 二次作业费
		Double guohu = 0.0;// 过户费
		Double cangchu = 0.0;// 仓储费
		Double qimoweight = 0.0;// 期末的库存重量
		Double chuxz = 0.0;// 统计出库下站的费用
		Double guoxz = 0.0; // 统计过户的下站费用
		Double heji = 0.0;// 统计出的费用合计

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// 用客户和起始时间去遍历每一个入库子订单、出库子订单、过户子订单
		for (int i = 0; i < clist.size(); i++) {
			Client c = clist.get(i);
			JSONObject obj = new JSONObject();
			// 统计火车出库费
			List<ExportSeed> eslisth = this.exportSeedService.getYunShuJieSuan(
					af.getBegin(), af.getFinish(), c.getClientId(), "火运");
			if (eslisth.size() > 0) {
				huochu = eslisth.get(0).getEseedRealityWeight() == null ? 0.0
						: eslisth.get(0).getEseedRealityWeight();
			}
			// 统计汽车出库的费用
			List<ExportSeed> eslistq = this.exportSeedService.getYunShuJieSuan(
					af.getBegin(), af.getFinish(), c.getClientId(), "汽运");
			if (eslistq.size() > 0) {
				qichu = eslistq.get(0).getEseedRealityWeight() == null ? 0.0
						: eslistq.get(0).getEseedRealityWeight();
			}
			/*
			 * // 统计出库的二次作业的费用 List<ExportSeed> ercilist =
			 * this.exportSeedService .getErCiZuoYeCost(af.getBegin(),
			 * af.getFinish(), c.getClientId()); if (ercilist.size() > 0) { erci
			 * = ercilist.get(0).getEseedRealityWeight() == null ? 0.0 :
			 * ercilist.get(0).getEseedRealityWeight(); }
			 */

			// 统计该时间段的期末重量，统计出仓储费用
			// 进行遍历客户的货物库存
			List<ClientGoods> cglistall = this.clientGoodsService.getAll();
			List<ClientGoods> cglist = this.clientGoodsService
					.getKeHuSFCByPage(c.getClientAbbreviation(), "", 1,
							cglistall.size());
			for (int j = 0; j < cglist.size(); j++) {
				// 统计出库合计
				List<ExportSeed> eslist = this.exportSeedService
						.getKeHuKunCunCKHJSFC(af.getBegin(), af.getFinish(),
								cglist.get(j).getClient().getClientId(), cglist
										.get(j).getGoodsName());
				if (eslist.size() > 0
						&& eslist.get(0).getEseedRealityWeight() != null) {
					cglist.get(j)
							.setExportHeJi(
									Double.parseDouble(ddf
											.format((cglist.get(j)
													.getExportHeJi() == null ? 0.0
													: cglist.get(j)
															.getExportHeJi())
													+ (eslist
															.get(0)
															.getEseedRealityWeight() == null ? 0.0
															: eslist.get(0)
																	.getEseedRealityWeight()))));
				} else {
					cglist.get(j).setExportHeJi(
							(cglist.get(j).getExportHeJi() == null ? 0.0
									: cglist.get(j).getExportHeJi()));
				}
				// 统计出库合计结束------
				// 统计入库的期初
				List<InputSeed> islist = this.inputSeedService
						.getKeHuKuCunQCHJSFC(af.getBegin(), cglist.get(j)
								.getClient().getClientId(), cglist.get(j)
								.getGoodsName());
				if (islist.size() > 0
						&& islist.get(0).getIseedRealityWeight() != null) {
					cglist.get(j).setQiChu(
							Double.parseDouble(ddf.format(islist.get(0)
									.getIseedRealityWeight()
									+ (cglist.get(j).getQiChu() == null ? 0.0
											: cglist.get(j).getQiChu()))));
				} else {
					cglist.get(j).setQiChu(
							(cglist.get(j).getQiChu() == null ? 0.0 : cglist
									.get(j).getQiChu()));
				}
				// 统计入库的期初结束
				// 统计出库期初
				List<ExportSeed> esqclist = this.exportSeedService
						.getKeHuKunCunQCHJSFC(af.getBegin(), cglist.get(j)
								.getClient().getClientId(), cglist.get(j)
								.getGoodsName());
				if (esqclist.size() > 0
						&& esqclist.get(0).getEseedRealityWeight() != null) {
					cglist.get(j)
							.setQiChu(
									Double.parseDouble(ddf.format(cglist.get(j)
											.getQiChu()
											- esqclist.get(0)
													.getEseedRealityWeight())));
				}
				// 统计出库期初结束
				// 统计入库合计---------------------------------------------------------------------------------------------+++++
				List<InputSeed> isrklist = this.inputSeedService
						.getKeHuKuCunRKHJSFC(af.getBegin(), af.getFinish(),
								cglist.get(j).getClient().getClientId(), cglist
										.get(j).getGoodsName());
				if (isrklist.size() > 0
						&& isrklist.get(0).getIseedRealityWeight() != null) {
					cglist.get(j)
							.setInputHeJi(
									(isrklist.get(0).getIseedRealityWeight() == null ? 0.0
											: isrklist.get(0)
													.getIseedRealityWeight())
											+ (cglist.get(j).getInputHeJi() == null ? 0.0
													: cglist.get(j)
															.getInputHeJi()));
				} else {
					cglist.get(j).setInputHeJi(
							cglist.get(j).getInputHeJi() == null ? 0.0 : cglist
									.get(j).getInputHeJi());
				}
				// 统计入库合计结束
				// 过户转出合计，统计到出库合计中
				List<ShiftStockSeed> ssslist = this.shiftStockSeedService
						.getKeHuKuCunGHZCHJSFC(af.getBegin(), af.getFinish(),
								cglist.get(j).getClient().getClientId(), cglist
										.get(j).getGoodsName());
				if (ssslist.size() > 0
						&& ssslist.get(0).getSsseedWeight() != null) {
					cglist.get(j).setExportHeJi(
							Double.parseDouble(ddf.format(cglist.get(j)
									.getExportHeJi()
									+ ssslist.get(0).getSsseedWeight())));
				}

				// 过户转出统计结束
				// 统计过户转入开始
				List<ShiftStockSeed> ssszulist = this.shiftStockSeedService
						.getKeHuKuCunGHZRHJSFC(af.getBegin(), af.getFinish(),
								cglist.get(j).getClient().getClientId(), cglist
										.get(j).getGoodsName());
				if (ssszulist.size() > 0
						&& ssszulist.get(0).getSsseedWeight() != null) {
					cglist.get(j).setInputHeJi(
							Double.parseDouble(ddf.format(cglist.get(j)
									.getInputHeJi()
									+ ssszulist.get(0).getSsseedWeight())));
				}
				// 统计过户转入结束
				// 统计过户转出期初统计
				List<ShiftStockSeed> ssszclist = this.shiftStockSeedService
						.getKeHuKuCunZCQCHJSFC(af.getBegin(), cglist.get(j)
								.getClient().getClientId(), cglist.get(j)
								.getGoodsName());
				if (ssszclist.size() > 0 && ssszclist.get(0) != null) {
					cglist.get(j).setQiChu(
							Double.parseDouble(ddf.format(cglist.get(j)
									.getQiChu()
									- (ssszclist.get(0).getSsseedWeight()))));
				}
				// 统计过户转出期初统计结束
				// 统计过户转入期初的统计
				List<ShiftStockSeed> sssqczr = this.shiftStockSeedService
						.getKeHuKuCunZRQCHJSFC(af.getBegin(), cglist.get(j)
								.getClient().getClientId(), cglist.get(j)
								.getGoodsName());
				if (sssqczr.size() > 0 && sssqczr.get(0) != null) {
					cglist.get(j).setQiChu(
							Double.parseDouble(ddf.format(cglist.get(j)
									.getQiChu()
									+ sssqczr.get(0).getSsseedWeight())));
				}
				// 统计过户的转入期初结束

				cglist.get(j).setQiMo(
						Double.parseDouble(ddf
								.format((cglist.get(j).getQiChu() + cglist.get(
										j).getInputHeJi())
										- cglist.get(j).getExportHeJi())));

				qimoweight += cglist.get(j).getQiMo();
			}

			// 计算过户的费用
			guohu = this.shiftStockSeedService.getGuoHuCost(af.getBegin(),
					af.getFinish(), c.getClientId()) == null ? 0.0
					: this.shiftStockSeedService.getGuoHuCost(af.getBegin(),
							af.getFinish(), c.getClientId());

			// 统计出库下站的费用
			chuxz = this.xiazhanfeiService.getXiaZhanCost(af.getBegin(),
					af.getFinish(), c.getClientId(), "出库") == null ? 0.0
					: this.xiazhanfeiService.getXiaZhanCost(af.getBegin(),
							af.getFinish(), c.getClientId(), "出库");
			// 统计过户下站的费用
			guoxz = this.xiazhanfeiService.getXiaZhanCost(af.getBegin(),
					af.getFinish(), c.getClientId(), "过户") == null ? 0.0
					: this.xiazhanfeiService.getXiaZhanCost(af.getBegin(),
							af.getFinish(), c.getClientId(), "过户");

			// 统计火车入库的费用
			List<InputSeed> islisth = this.inputSeedService.getYunShuCost(
					af.getBegin(), af.getFinish(), c.getClientId(), "火运");
			if (islisth.size() > 0) {
				huoru = islisth.get(0).getIseedRealityWeight();
			}
			// 统计汽车入库的费用
			List<InputSeed> islistq = this.inputSeedService.getYunShuCost(
					af.getBegin(), af.getFinish(), c.getClientId(), "汽运");
			if (islistq.size() > 0) {
				qiru = islistq.get(0).getIseedRealityWeight();
			}

			obj.put("clientId", c.getClientId());// 保存客户的编号
			obj.put("clientName", c.getClientAbbreviation());// 保存客户简称
			obj.put("begin", af.getBegin());// 保存起始日期
			obj.put("finish", af.getFinish());// 保存结束日期
			obj.put("huochu", huochu == null ? 0.0 : df.format(huochu));// 保存火运出库的费用
			obj.put("qichu", qichu == null ? 0.0 : df.format(qichu));// 保存汽车出库的费用
			obj.put("erci", erci == null ? 0.0 : df.format(erci));// 保存二次作业的费用
			obj.put("guohu", guohu == null ? 0.0 : df.format(guohu));// 保存过户的费用
			obj.put("chuxz", chuxz == null ? 0.0 : df.format(chuxz));// 保存出库下站的费用
			obj.put("guoxz", guoxz == null ? 0.0 : df.format(guoxz));// 保存过户的下站的费用
			obj.put("huoru", huoru == null ? 0.0 : df.format(huoru));// 保存火车入库的费用
			obj.put("qiru", qiru == null ? 0.0 : df.format(qiru));// 保存汽车入库的费用

			cangchu = this.costTypeService.getCangChu() * qimoweight;

			System.out.println("期末的重量是：" + qimoweight);

			String str[] = this.inputService.getIESMaxTime(c.getClientId());
			long time = (long) 3 * 30 * 24 * 60 * 60 * 1000;
			if (((sdf.parse(pt.getTimes()).getTime() - sdf.parse(str[0])
					.getTime()) >= time)
					&& ((sdf.parse(pt.getTimes()).getTime() - sdf.parse(str[1])
							.getTime()) >= time)
					&& ((sdf.parse(pt.getTimes()).getTime() - sdf.parse(str[2])
							.getTime()) >= time)) {
				System.out.println("进来了"
						+ time
						+ ","
						+ sdf.parse(str[1]).getTime()
						+ ","
						+ sdf.parse(str[2]).getTime()
						+ "jieguo"
						+ (sdf.parse(pt.getTimes()).getTime() - sdf.parse(
								str[0]).getTime()));
				cangchu = cangchu * 2;
			}

			obj.put("cangchu", df.format(cangchu));

			heji = huoru + qiru + huochu + qichu + guohu + cangchu + chuxz
					+ guoxz;
			obj.put("heji", df.format(heji));// 保存所有的费用合计
			obj.put("pageNow", pageNow);// 保存当前页
			obj.put("pageCount", pageCount);// 保存当前页
			obj.put("qimoweight", qimoweight);// 保存期末重量
			obj.put("rowSize", rowSize);// 保存每页显示的行数

			array.add(obj);
			huochu = 0.0;// 火运出库费
			qichu = 0.0;// 汽运出库费
			huoru = 0.0;// 火运入库费
			qiru = 0.0;// 汽运入库费
			erci = 0.0;// 二次作业费
			guohu = 0.0;// 过户费
			cangchu = 0.0;// 仓储费
			qimoweight = 0.0;// 期末的库存重量
			chuxz = 0.0;// 统计出库下站的费用
			guoxz = 0.0; // 统计过户的下站费用
			heji = 0.0;// 统计出的费用合计
		}
		out.print(array.toString());
		out.flush();
		out.close();
		return null;
	}
	//通过客户查询结算的订单
	public ActionForward getClientJieSuan(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 获取客户id
		int clientId = Integer.parseInt(request.getSession().getAttribute("clientId").toString());
		PrintWriter out = response.getWriter();
		JSONArray array = new JSONArray();
		// 获得上传的form
		AccountsForm af = (AccountsForm) form;
		if (af.getBegin() == null || af.getBegin().equals("")) {
			af.setBegin("");
		} else {
			af.setBegin(af.getBegin() + " 00:00:00");
		}
		// 判断结束日期，如果结束日期为空则设置结束日期为当前的日期，如果不为空则在上传的结束时间的末尾加23:59:59
		if (af.getFinish() == null || af.getFinish().equals("")) {
			af.setFinish(pt.getTimes());
		} else {
			af.setFinish(af.getFinish() + " 23:59:59");
		}
		List<Accounts> alist = this.accountsService.getClientAccount(clientId, af.getBegin(), af.getFinish(), "未收费");
		if(alist.size()<=0){
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		for(Accounts a:alist){
			JSONObject obj = new JSONObject();
			obj.put("result", "notnull");
			obj.put("kehu", a.getClient().getClientAbbreviation());//保存客户名称
			obj.put("begin", a.getAccountsCreateTime());//周期开始时间
			obj.put("finish", a.getAccountsFinishTime());//周期结束时间
			obj.put("heji", a.getAccountsExpensesSeed());//费用合计
			obj.put("zhinajin", a.getAccountsSeed());//滞纳金合计
			obj.put("feilv", a.getZhinaFeilv());//滞纳金费率
			obj.put("qixian", a.getShoufeiqixian());//缴费期限
			obj.put("qiyunfei", a.getRukuCost());//汽运入库field
			obj.put("huoyunfei", a.getZidingyiFour());//火运入库费
			obj.put("qiyunchu", a.getZidingyiFive());//汽运出库费
			obj.put("huoyunchu", a.getZidingyiSix());//火运出库费
			obj.put("xiachu", a.getChukumaxtime());//下站费出库
			obj.put("xiaguo", a.getZhuanchukumaxtime());//下站费过户
			obj.put("guohu", a.getGuohuCost());//过户费
			obj.put("cangchu", a.getCangchuCost());//仓储费
			obj.put("jiesuanren", a.getJiesuanren().getIuserName());//结算人
			obj.put("jiesuantime", a.getJiesuantime());//结算时间
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
		return null;
	}
	
	//通过客户查询结算的订单
	public ActionForward getClientJieSuanJiLu(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 获取客户id
		int clientId = Integer.parseInt(request.getSession().getAttribute("clientId").toString());
		PrintWriter out = response.getWriter();
		JSONArray array = new JSONArray();
		// 获得上传的form
		AccountsForm af = (AccountsForm) form;
		if (af.getBegin() == null || af.getBegin().equals("")) {
			af.setBegin("");
		} else {
			af.setBegin(af.getBegin() + " 00:00:00");
		}
		// 判断结束日期，如果结束日期为空则设置结束日期为当前的日期，如果不为空则在上传的结束时间的末尾加23:59:59
		if (af.getFinish() == null || af.getFinish().equals("")) {
			af.setFinish(pt.getTimes());
		} else {
			af.setFinish(af.getFinish() + " 23:59:59");
		}
		List<Accounts> alist = this.accountsService.getClientAccount(clientId, af.getBegin(), af.getFinish(), "已收费");
		if(alist.size()<=0){
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		for(Accounts a:alist){
			JSONObject obj = new JSONObject();
			obj.put("result", "notnull");
			obj.put("kehu", a.getClient().getClientAbbreviation());//保存客户名称
			obj.put("begin", a.getAccountsCreateTime());//周期开始时间
			obj.put("finish", a.getAccountsFinishTime());//周期结束时间
			obj.put("heji", a.getAccountsExpensesSeed());//费用合计
			obj.put("zhinajin", a.getAccountsSeed());//滞纳金合计
			obj.put("feilv", a.getZhinaFeilv());//滞纳金费率
			obj.put("qixian", a.getShoufeiqixian());//缴费期限
			obj.put("qiyunfei", a.getRukuCost());//汽运入库field
			obj.put("huoyunfei", a.getZidingyiFour());//火运入库费
			obj.put("qiyunchu", a.getZidingyiFive());//汽运出库费
			obj.put("huoyunchu", a.getZidingyiSix());//火运出库费
			obj.put("xiachu", a.getChukumaxtime());//下站费出库
			obj.put("xiaguo", a.getZhuanchukumaxtime());//下站费过户
			obj.put("guohu", a.getGuohuCost());//过户费
			obj.put("cangchu", a.getCangchuCost());//仓储费
			obj.put("jiesuanren", a.getJiesuanren().getIuserName());//结算人
			obj.put("jiesuantime", a.getJiesuantime());//结算时间
			obj.put("shoufeitime", a.getAccountsCollectTime());//滞纳金结束日期
			obj.put("jiaofeiren", a.getJiaofeiren());//缴费人
			obj.put("zhifu", a.getAccountsDefinedTwo()==null?"无":a.getAccountsDefinedTwo().getPfashionName());//支付方式
			obj.put("piaoju", a.getAccountsDefinedTwo()==null?"无":a.getAccountsDefinedTwo().getPfashionReceipt());//票据类型
			obj.put("shishou", a.getShishouCost());//实收费用
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
		return null;
	}

	// ---------------------------------------------------------------------------------------------

	// 查询该客户的费用信息
	public ActionForward QueryJieSuanKehu(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 获取客户id
		int clientId = Integer.parseInt(request.getSession()
				.getAttribute("clientId").toString());

		// 获得上传的form
		AccountsForm af = (AccountsForm) form;
		// 判断起始的时间是否为空，如果为空则设置起始日期为当前时间减去一个月，如果不为空则在开始时间的末尾加00:00:00
		if (af.getBegin() == null || af.getBegin().equals("")) {
			af.setBegin(pt.getNowJianYi());
		} else {
			af.setBegin(af.getBegin() + " 00:00:00");
		}
		// 判断结束日期，如果结束日期为空则设置结束日期为当前的日期，如果不为空则在上传的结束时间的末尾加23:59:59
		if (af.getFinish() == null || af.getFinish().equals("")) {
			af.setFinish(pt.getTimes());
		} else {
			af.setFinish(af.getFinish() + " 23:59:59");
		}
		// 判断输入的客户是否为空，如果为空则查询所有的
		// if (af.getJiancheng() == null) {
		// af.setJiancheng("");
		// }
		int pageNow = 1;// 当前页
		int rowSize = 5;// 每页显示的行数，可以在界面上设置显示的行数
		// 如果上传的当前页不为空的时候，当前页等于上传的当前页
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}
		// 如果页面上传的显示的行数不为空的时候，设置每页显示的行数为上传的页数
		if (request.getParameter("rowSize") != null) {
			rowSize = Integer.parseInt(request.getParameter("rowSize"));
		}
		// 通过条件查询总的行数
		int pageCount = this.clientService.getWeiJieSuanCountKH(clientId,
				af.getBegin(), af.getFinish(), pageNow, rowSize);
		// 判断当前页是否大于显示的页数
		if (pageNow >= pageCount) {
			pageNow = pageCount;
		}
		if (pageNow <= 1) {
			pageNow = 1;
		}
		// 通过客户进行分页查询，而通过时间进行查询对应的费用订单
		List<Client> clist = this.clientService.getWeiJieSuanKH(clientId,
				af.getBegin(), af.getFinish(), pageNow, rowSize);

		DecimalFormat df = new DecimalFormat("###################0.000");
		JSONArray array = new JSONArray();
		PrintWriter out = response.getWriter();

		Double huochu = 0.0;// 火运出库费
		Double qichu = 0.0;// 汽运出库费
		Double huoru = 0.0;// 火运入库费
		Double qiru = 0.0;// 汽运入库费
		Double erci = 0.0;// 二次作业费
		Double guohu = 0.0;// 过户费
		Double cangchu = 0.0;// 仓储费
		Double qimoweight = 0.0;// 期末的库存重量
		Double chuxz = 0.0;// 统计出库下站的费用
		Double guoxz = 0.0; // 统计过户的下站费用
		Double heji = 0.0;// 统计出的费用合计

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// 用客户和起始时间去遍历每一个入库子订单、出库子订单、过户子订单
		for (int i = 0; i < clist.size(); i++) {
			Client c = clist.get(i);
			JSONObject obj = new JSONObject();
			// 统计火车出库费
			List<ExportSeed> eslisth = this.exportSeedService.getYunShuJieSuan(
					af.getBegin(), af.getFinish(), c.getClientId(), "火运");
			if (eslisth.size() > 0) {
				huochu = eslisth.get(0).getEseedRealityWeight() == null ? 0.0
						: eslisth.get(0).getEseedRealityWeight();
			}
			// 统计汽车出库的费用
			List<ExportSeed> eslistq = this.exportSeedService.getYunShuJieSuan(
					af.getBegin(), af.getFinish(), c.getClientId(), "汽运");
			if (eslistq.size() > 0) {
				qichu = eslistq.get(0).getEseedRealityWeight() == null ? 0.0
						: eslistq.get(0).getEseedRealityWeight();
			}
			/*
			 * // 统计出库的二次作业的费用 List<ExportSeed> ercilist =
			 * this.exportSeedService .getErCiZuoYeCost(af.getBegin(),
			 * af.getFinish(), c.getClientId()); if (ercilist.size() > 0) { erci
			 * = ercilist.get(0).getEseedRealityWeight() == null ? 0.0 :
			 * ercilist.get(0).getEseedRealityWeight(); }
			 */

			// 统计该时间段的期末重量，统计出仓储费用
			// 进行遍历客户的货物库存
			List<ClientGoods> cglistall = this.clientGoodsService.getAll();
			List<ClientGoods> cglist = this.clientGoodsService
					.getKeHuSFCByPage(c.getClientAbbreviation(), "", 1,
							cglistall.size());
			for (int j = 0; j < cglist.size(); j++) {
				// 统计出库合计
				List<ExportSeed> eslist = this.exportSeedService
						.getKeHuKunCunCKHJSFC(af.getBegin(), af.getFinish(),
								cglist.get(j).getClient().getClientId(), cglist
										.get(j).getGoodsName());
				if (eslist.size() > 0
						&& eslist.get(0).getEseedRealityWeight() != null) {
					cglist.get(j)
							.setExportHeJi(
									Double.parseDouble(df
											.format((cglist.get(j)
													.getExportHeJi() == null ? 0.0
													: cglist.get(j)
															.getExportHeJi())
													+ (eslist
															.get(0)
															.getEseedRealityWeight() == null ? 0.0
															: eslist.get(0)
																	.getEseedRealityWeight()))));
				} else {
					cglist.get(j).setExportHeJi(
							(cglist.get(j).getExportHeJi() == null ? 0.0
									: cglist.get(j).getExportHeJi()));
				}
				// 统计出库合计结束------
				// 统计入库的期初
				List<InputSeed> islist = this.inputSeedService
						.getKeHuKuCunQCHJSFC(af.getBegin(), cglist.get(j)
								.getClient().getClientId(), cglist.get(j)
								.getGoodsName());
				if (islist.size() > 0
						&& islist.get(0).getIseedRealityWeight() != null) {
					cglist.get(j).setQiChu(
							Double.parseDouble(df.format(islist.get(0)
									.getIseedRealityWeight()
									+ (cglist.get(j).getQiChu() == null ? 0.0
											: cglist.get(j).getQiChu()))));
				} else {
					cglist.get(j).setQiChu(
							(cglist.get(j).getQiChu() == null ? 0.0 : cglist
									.get(j).getQiChu()));
				}
				// 统计入库的期初结束
				// 统计出库期初
				List<ExportSeed> esqclist = this.exportSeedService
						.getKeHuKunCunQCHJSFC(af.getBegin(), cglist.get(j)
								.getClient().getClientId(), cglist.get(j)
								.getGoodsName());
				if (esqclist.size() > 0
						&& esqclist.get(0).getEseedRealityWeight() != null) {
					cglist.get(j)
							.setQiChu(
									Double.parseDouble(df.format(cglist.get(j)
											.getQiChu()
											- esqclist.get(0)
													.getEseedRealityWeight())));
				}
				// 统计出库期初结束
				// 统计入库合计---------------------------------------------------------------------------------------------+++++
				List<InputSeed> isrklist = this.inputSeedService
						.getKeHuKuCunRKHJSFC(af.getBegin(), af.getFinish(),
								cglist.get(j).getClient().getClientId(), cglist
										.get(j).getGoodsName());
				if (isrklist.size() > 0
						&& isrklist.get(0).getIseedRealityWeight() != null) {
					cglist.get(j)
							.setInputHeJi(
									(isrklist.get(0).getIseedRealityWeight() == null ? 0.0
											: isrklist.get(0)
													.getIseedRealityWeight())
											+ (cglist.get(j).getInputHeJi() == null ? 0.0
													: cglist.get(j)
															.getInputHeJi()));
				} else {
					cglist.get(j).setInputHeJi(
							cglist.get(j).getInputHeJi() == null ? 0.0 : cglist
									.get(j).getInputHeJi());
				}
				// 统计入库合计结束
				// 过户转出合计，统计到出库合计中
				List<ShiftStockSeed> ssslist = this.shiftStockSeedService
						.getKeHuKuCunGHZCHJSFC(af.getBegin(), af.getFinish(),
								cglist.get(j).getClient().getClientId(), cglist
										.get(j).getGoodsName());
				if (ssslist.size() > 0
						&& ssslist.get(0).getSsseedWeight() != null) {
					cglist.get(j).setExportHeJi(
							Double.parseDouble(df.format(cglist.get(j)
									.getExportHeJi()
									+ ssslist.get(0).getSsseedWeight())));
				}

				// 过户转出统计结束
				// 统计过户转入开始
				List<ShiftStockSeed> ssszulist = this.shiftStockSeedService
						.getKeHuKuCunGHZRHJSFC(af.getBegin(), af.getFinish(),
								cglist.get(j).getClient().getClientId(), cglist
										.get(j).getGoodsName());
				if (ssszulist.size() > 0
						&& ssszulist.get(0).getSsseedWeight() != null) {
					cglist.get(j).setInputHeJi(
							Double.parseDouble(df.format(cglist.get(j)
									.getInputHeJi()
									+ ssszulist.get(0).getSsseedWeight())));
				}
				// 统计过户转入结束
				// 统计过户转出期初统计
				List<ShiftStockSeed> ssszclist = this.shiftStockSeedService
						.getKeHuKuCunZCQCHJSFC(af.getBegin(), cglist.get(j)
								.getClient().getClientId(), cglist.get(j)
								.getGoodsName());
				if (ssszclist.size() > 0 && ssszclist.get(0) != null) {
					cglist.get(j).setQiChu(
							Double.parseDouble(df.format(cglist.get(j)
									.getQiChu()
									- (ssszclist.get(0).getSsseedWeight()))));
				}
				// 统计过户转出期初统计结束
				// 统计过户转入期初的统计
				List<ShiftStockSeed> sssqczr = this.shiftStockSeedService
						.getKeHuKuCunZRQCHJSFC(af.getBegin(), cglist.get(j)
								.getClient().getClientId(), cglist.get(j)
								.getGoodsName());
				if (sssqczr.size() > 0 && sssqczr.get(0) != null) {
					cglist.get(j).setQiChu(
							Double.parseDouble(df.format(cglist.get(j)
									.getQiChu()
									+ sssqczr.get(0).getSsseedWeight())));
				}
				// 统计过户的转入期初结束

				cglist.get(j).setQiMo(
						Double.parseDouble(df
								.format((cglist.get(j).getQiChu() + cglist.get(
										j).getInputHeJi())
										- cglist.get(j).getExportHeJi())));

				qimoweight += cglist.get(j).getQiMo();
			}

			// 计算过户的费用
			guohu = this.shiftStockSeedService.getGuoHuCost(af.getBegin(),
					af.getFinish(), c.getClientId()) == null ? 0.0
					: this.shiftStockSeedService.getGuoHuCost(af.getBegin(),
							af.getFinish(), c.getClientId());

			// 统计出库下站的费用
			chuxz = this.xiazhanfeiService.getXiaZhanCost(af.getBegin(),
					af.getFinish(), c.getClientId(), "出库") == null ? 0.0
					: this.xiazhanfeiService.getXiaZhanCost(af.getBegin(),
							af.getFinish(), c.getClientId(), "出库");
			// 统计过户下站的费用
			guoxz = this.xiazhanfeiService.getXiaZhanCost(af.getBegin(),
					af.getFinish(), c.getClientId(), "过户") == null ? 0.0
					: this.xiazhanfeiService.getXiaZhanCost(af.getBegin(),
							af.getFinish(), c.getClientId(), "过户");

			// 统计火车入库的费用
			List<InputSeed> islisth = this.inputSeedService.getYunShuCost(
					af.getBegin(), af.getFinish(), c.getClientId(), "火运");
			if (islisth.size() > 0) {
				huoru = islisth.get(0).getIseedRealityWeight();
			} else {
				huoru = 0.0;
			}
			// 统计汽车入库的费用
			List<InputSeed> islistq = this.inputSeedService.getYunShuCost(
					af.getBegin(), af.getFinish(), c.getClientId(), "汽运");
			if (islistq.size() > 0) {
				qiru = islistq.get(0).getIseedRealityWeight();
			} else {
				qiru = 0.0;
			}

			obj.put("begin", af.getBegin());// 保存起始日期
			obj.put("finish", af.getFinish());// 保存结束日期
			obj.put("huoQichu",
					huochu == null ? 0.0 : df.format(huochu) + df.format(qichu));// 保存火运出库的费用
			// obj.put("qichu", qichu == null ? 0.0 : df.format(qichu));//
			// 保存汽车出库的费用
			obj.put("erci", erci == null ? 0.0 : df.format(erci));// 保存二次作业的费用
			obj.put("guohu", guohu == null ? 0.0 : df.format(guohu));// 保存过户的费用
			obj.put("chuGuoxz",
					chuxz == null ? 0.0 : df.format(chuxz) + df.format(guoxz));// 保存出库下站的费用
			// obj.put("guoxz", guoxz == null ? 0.0 : df.format(guoxz));//
			// 保存过户的下站的费用
			obj.put("huoQiru",
					huoru == null ? 0.0 : df.format(huoru) + df.format(qiru));// 保存火车入库的费用
			// obj.put("qiru", qiru == null ? 0.0 : df.format(qiru));//
			// 保存汽车入库的费用

			cangchu = this.costTypeService.getCangChu() * qimoweight;
			String str[] = this.inputService.getIESMaxTime(c.getClientId());
			if ((sdf.parse(pt.getTimes()).getTime()
					- sdf.parse(str[0]).getTime() >= 3 * 30 * 24 * 60 * 60
					* 1000)
					&& (sdf.parse(pt.getTimes()).getTime()
							- sdf.parse(str[1]).getTime() >= 3 * 30 * 24 * 60
							* 60 * 1000)
					&& (sdf.parse(pt.getTimes()).getTime()
							- sdf.parse(str[2]).getTime() >= 3 * 30 * 24 * 60
							* 60 * 1000)) {
				cangchu = cangchu * 2;
			}
			obj.put("cangchu", df.format(cangchu));

			// 合计相加
			heji = huoru + huochu + guohu + cangchu + chuxz;

			obj.put("heji", df.format(heji));// 保存所有的费用合计
			obj.put("pageNow", pageNow);// 保存当前页
			obj.put("qimoweight", qimoweight);// 保存期末重量
			obj.put("rowSize", rowSize);// 保存每页显示的行数

			qimoweight = 0.0;
			huochu = 0.0;// 火运出库费
			qichu = 0.0;// 汽运出库费
			huoru = 0.0;// 火运入库费
			qiru = 0.0;// 汽运入库费
			erci = 0.0;// 二次作业费
			guohu = 0.0;// 过户费
			cangchu = 0.0;// 仓储费
			qimoweight = 0.0;// 期末的库存重量
			chuxz = 0.0;// 统计出库下站的费用
			guoxz = 0.0; // 统计过户的下站费用
			heji = 0.0;// 统计出的费用合计

			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
		return null;
	}

	// 在收取滞纳金之前的查询
	public ActionForward getShouQuQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		AccountsForm af = (AccountsForm) form;
		if (af.getJiancheng() == null) {
			af.setJiancheng("");
		}
		int pageNow = 1;
		int rowSize = 20;
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}
		if (request.getParameter("rowSize") != null) {
			rowSize = Integer.parseInt(request.getParameter("rowSize"));
		}

		int pageCount = this.accountsService.getCostRecordByPageCount("", "",
				af.getJiancheng(), "未收费", rowSize, "");
		if (pageNow >= pageCount) {
			pageNow = pageCount;
		}
		if (pageNow <= 1) {
			pageNow = 1;
		}
		DecimalFormat df = new DecimalFormat("###########0.00");
		// 查询整个应收费用
		List<Accounts> alist = this.accountsService.getCostRecordByPage("", "",
				af.getJiancheng(), "未收费", pageNow, rowSize, "");
		PrintWriter out = response.getWriter();
		JSONArray array = new JSONArray();
		for (int i = 0; i < alist.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("id", alist.get(i).getAccountsId());// 保存收费记录的编号
			obj.put("clientName", alist.get(i).getClient()
					.getClientAbbreviation());// 保存对应的客户的简称
			obj.put("begin", alist.get(i).getAccountsCreateTime());// 保存起始日期
			obj.put("finish", alist.get(i).getAccountsFinishTime());// 保存结束日期
			obj.put("count", alist.get(i).getAccountsExpensesSeed());// 保存费用合计
			obj.put("feilv", alist.get(i).getZhinaFeilv());// 保存滞纳金费率
			obj.put("zhinajin", alist.get(i).getAccountsSeed() == null ? 0.0
					: alist.get(i).getAccountsSeed());// 保存应收的滞纳金
			obj.put("zong",
					df.format(alist.get(i).getAccountsExpensesSeed()
							+ (alist.get(i).getAccountsSeed() == null ? 0.0
									: alist.get(i).getAccountsSeed())));
			obj.put("jiesuanren", alist.get(i).getJiesuanren().getIuserName());// 保存结算人
			obj.put("jiesuantime", alist.get(i).getJiesuantime());// 保存结算时间
			obj.put("pageNow", pageNow);// 保存当前页
			obj.put("pageCount", pageCount);
			obj.put("rowSize", rowSize);// 保存显示的行数
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();

		// 返回到对应的页面
		return null;
	}

	// 通过滞纳金的编号进行查询
	public ActionForward getAccountsId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AccountsForm af = (AccountsForm) form;
		Accounts accounts = this.accountsService.getAccountsId(af
				.getAccountsId());
		request.setAttribute("ac", accounts);
		// 返回到对应的界面
		return mapping.findForward("goXiangQing");
	}

	// 通过滞纳金的编号进行查询
	public ActionForward getAccountsKHId(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		AccountsForm af = (AccountsForm) form;
		Accounts accounts = this.accountsService.getAccountsId(af
				.getAccountsId());
		request.setAttribute("ac", accounts);
		// 返回到对应的界面
		return mapping.findForward("getAccountsKHId");
	}

	// 删除滞纳金
	public ActionForward deleteAccounts(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AccountsForm af = (AccountsForm) form;
		Accounts accounts = this.accountsService.getAccountsId(af
				.getAccountsId());
		boolean ok = this.accountsService.delete(accounts);
		if (ok) {
			request.getSession().setAttribute("deleteAccountsMessage", "删除成功！");
		} else {
			request.getSession().setAttribute("deleteAccountsMessage", "删除失败！");
		}
		// 将这一操作记录在日志中
		this.loginLogService.updateLogin(
				request.getSession().getAttribute("loginlogId").toString(),
				"删除滞纳金" + af.getAccountsId());
		// 返回到对应的界面
		return super.execute(mapping, form, request, response);
	}

	// 修改
	public ActionForward updateAccounts(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AccountsForm af = (AccountsForm) form;
		Accounts accounts = this.accountsService.getAccountsId(af
				.getAccountsId());
		accounts.setAccountsFinishTime(af.getAccountsFinishTime());// 修改结束日期
		accounts.setAccountsCollectTime(af.getAccountsCollectTime());// 修改收费时间
		accounts.setAccountsExpensesSeed(af.getAccountsExpensesSeed()[0]);// 修改费用合计
		accounts.setAccountsSeed(af.getAccountsSeed());// 修改滞纳金合计
		accounts.setAccountsDefinedOne(af.getAccountsDefinedOne());// 修改实收的滞纳金费用
		boolean ok = this.accountsService.update(accounts);
		if (ok) {
			request.getSession().setAttribute("updateAccountsMessage", "修改成功！");
		} else {
			request.getSession().setAttribute("updateAccountsMessage", "修改失败！");
		}
		// 将这一操作记录在日志中
		this.loginLogService.updateLogin(request.getAttribute("loginlogId")
				.toString(),
				"修改滞纳金" + af.getAccountsId() + accounts.getAccountsId());
		// 返回到对应的界面
		return super.execute(mapping, form, request, response);
	}

	// 进行收费
	public ActionForward ShouQuCost(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AccountsForm af = (AccountsForm) form;

		if (af != null) {
			Accounts accounts = this.accountsService.getAccountsId(af
					.getAccountsId());

			if (accounts != null) {
				accounts.setShishouCost(af.getShishouCost());// 设置实收费用
				accounts.setJiaofeiren(af.getJiaofeiren());// 设置缴费人
				accounts.setAccountsDefinedTwo(this.paymentFashionService
						.getPaymentFashionId(af.getAccountsDefinedTwo()));// 设置支付方式
				accounts.setAccountsCollectTime(pt.getTimes());// 设置收费时间
				accounts.setShoufeiren((InteriorUser) request.getSession()
						.getAttribute("iulist"));// 设置收费人

				accounts.setZhuangtai("已收费");// 设置状态
				accounts.setAccountsRemark(accounts.getAccountsRemark() + ","
						+ af.getAccountsRemark());// 设置备注

				boolean ok = this.accountsService.update(accounts);
				if (ok) {
					request.getSession().setAttribute("updateAccountsMessage",
							"收费成功！");
					// 将这已操作记录在日志中
					this.loginLogService.updateLogin(request.getSession()
							.getAttribute("loginlogId").toString(),
							"收取月结客户费用，已收费" + accounts.getAccountsId());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					if (sdf.parse(pt.getTimes()).getTime()
							- sdf.parse(accounts.getShoufeiqixian()).getTime() >= 15
							* 24 * 60 * 60 * 1000) {
						this.clientGoodsService.JieDong(accounts.getClient()
								.getClientId());// 解冻库存
					}
				} else {
					request.getSession().setAttribute("updateAccountsMessage",
							"收费失败！");
				}
			} else {
				request.getSession().setAttribute("updateAccountsMessage",
						"收费失败！");
			}
		} else {
			request.getSession().setAttribute("updateAccountsMessage", "收费失败！");
		}

		// 返回到对应的界面
		PrintWriter out = response.getWriter();
		out.print("<script>alert('"
				+ request.getSession().getAttribute("updateAccountsMessage")
				+ "');document.location.href='/XGProject/caiwu-page/yuejieshoufei.jsp'</script>");
		return null;
	}

	// 对月结的客户的费用进行结算
	public ActionForward shouquAccounts(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AccountsForm af = (AccountsForm) form;
		int num = 0;
		for (int i = 0; i < af.getClient().length; i++) {
			Accounts accounts = new Accounts();

			String accountsid = "Z" + pt.getDatesNotTime()
					+ pr.getZhiNaJinNumber();
			accounts.setAccountsId(accountsid);// 设置滞纳金编号
			accounts.setAccountsCreateTime(af.getAccountsCreateTime());// 设置开始日期
			accounts.setAccountsFinishTime(af.getAccountsFinishTime());// 设置结束日期
			accounts.setRukuCost(af.getRukuCost()[i]);// 设置汽车入库费用
			accounts.setZidingyiFour(af.getZidingyiFour()[i]);// 设置火车入库费用
			accounts.setZidingyiFive(af.getZidingyiFive()[i]);// 设置汽车出库费用
			accounts.setZidingyiSix(af.getZidingyiSix()[i]);// 设置火车出库费用

			accounts.setChukumaxtime(af.getChukumaxtime()[i]);// 设置出库下站费
			accounts.setZhuanchukumaxtime(af.getZhuanchukumaxtime()[i]);// 设置过户下站费
			accounts.setGuohuCost(af.getGuohuCost()[i]);// 设置过户费用
			accounts.setCangchuCost(af.getCangchuCost()[i]);// 设置仓储费用
			accounts.setAccountsExpensesSeed(af.getAccountsExpensesSeed()[i]);// 设置费用合计
			accounts.setShoufeiqixian(af.getShoufeiqixian());// 设置收费期限
			accounts.setZhinaFeilv(af.getZhinaFeilv());// 设置滞纳金费率
			accounts.setAccountsRemark(af.getAccountsRemark());// 设置备注
			accounts.setClient(this.clientService.getClientId(af.getClient()[i]));// 设置客户
			this.appMessageService.saveMessage(af.getClient()[i], null, "", "",
					"", "", "结算");// ---------------------------------------------------------------
			accounts.setChukuCost(af.getChukuCost());// 设置出库费用
			accounts.setErcizuoyeCost(af.getErcizuoyeCost());// 设置二次作业费
			accounts.setQimoWeight(af.getQimoWeight()[i]);// 设置期末库存的重量

			accounts.setJiesuanren((InteriorUser) request.getSession()
					.getAttribute("iulist"));// 设置结算人
			accounts.setJiesuantime(pt.getTimes());// 设置结算的时间
			accounts.setZhuangtai("未收费");// 设置状态
			this.loginLogService.updateLogin(
					request.getSession().getAttribute("loginlogId").toString(),
					"结算月结客户费用，客户："
							+ accounts.getClient().getClientAbbreviation());

			boolean ok = this.accountsService.save(accounts);
			if (ok) {
				num++;
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				Date begin = df.parse(af.getAccountsCreateTime() + " 00:00:00");// 取出滞纳金的起始日期

				Date shangge = df.parse(af.getAccountsFinishTime()
						+ " 23:59:59");

				List<InputSeed> islist = this.inputSeedService
						.getZhuangtai("未收费");// 取出未收费的入库子订单
				for (InputSeed is : islist) {
					Date dingdan = df.parse(is.getInput().getInputCreateTime());
					if (is.getInput().getClient().getClientId() == accounts
							.getClient().getClientId()
							&& dingdan.getTime() >= begin.getTime()
							&& dingdan.getTime() <= shangge.getTime()) {
						InputSeed iseed = this.inputSeedService
								.getInputSeedId(is.getIseedId());
						double should = iseed.getIseedShouldCost() == null ? 0.0
								: iseed.getIseedShouldCost();// 取出应收费用
						iseed.setIseedRealityCost(should);// 设置实收费用为应收费用
						iseed.setIseedOrderStatus("已收费");// 子订单状态改变为已收费
						Input input = this.inputService.getInputId(iseed
								.getInput().getInputId());// 取出入库总订单;
						input.setInputOrderStatus("已收费");// 将总订单的订单状态改变为已收费
						this.inputSeedService.update(iseed);
						this.inputService.update(input);
					}
				}
				List<ExportSeed> eslist = this.exportSeedService
						.getZhuangtai("未收费");// 取出未收费的出库子订单
				for (ExportSeed es : eslist) {
					Date dingdan = df
							.parse(es.getExport().getExportReateTime());// 取出订单的生成时间

					if (es.getExport().getClient().getClientId() == accounts
							.getClient().getClientId()
							&& dingdan.getTime() >= begin.getTime()
							&& dingdan.getTime() <= shangge.getTime()) {
						ExportSeed eseed = this.exportSeedService
								.getExportSeedId(es.getEseedId());// 通过ID查询

						double should = eseed.getEseedShouldCost() == null ? 0.0
								: eseed.getEseedShouldCost();// 取出应收费用
						eseed.setEseedRealityCost(should);// 设置实收费用为应收费用
						eseed.setEseedOrderStatus("已收费");// 设置订单状态为已收费

						Export export = this.exportService.getExportId(eseed
								.getExport().getExportId());// 通过外键查询主键
						export.setExportOrderStatus("已收费");
						this.exportSeedService.update(eseed);
						this.exportService.update(export);
					}
				}

				// 取出未收费的过户子订单
				List<ShiftStockSeed> sslist = this.shiftStockSeedService
						.getZhuangtai("未收费");
				for (ShiftStockSeed ss : sslist) {
					Date dingdan = df.parse(ss.getShiftStock()
							.getSstockReateTime());// 取出未收费的订单时间
					if (ss.getShiftStock().getClientBySstockShiftToFirm()
							.getClientId() == accounts.getClient()
							.getClientId()
							&& dingdan.getTime() > begin.getTime()
							&& dingdan.getTime() <= shangge.getTime()) {
						ShiftStockSeed sss = this.shiftStockSeedService
								.getShiftStockSeedId(ss.getSsseedId());// 通过ID查询
						double should = sss.getSsseedShouldCost() == null ? 0.0
								: sss.getSsseedShouldCost();// 取出应收费用
						sss.setSsseedRealityCost(should);// 设置实收费用为应收费用
						sss.setSsseedOrderStatus("已收费");// 设置订单状态为已收费
						ShiftStock shiftStock = this.shiftStockService
								.getShiftStockId(sss.getShiftStock()
										.getSstockId());// 通过过户总订单编号查询
						shiftStock.setSstockOrderStatus("已收费");// 改变订单状态
						this.shiftStockSeedService.update(sss);
						this.shiftStockService.update(shiftStock);
					}
				}

				// 将下站费进行改变
				List<Xiazhanfei> xzlist = this.xiazhanfeiService
						.getWeiShouCost(af.getAccountsCreateTime(),
								af.getAccountsFinishTime(), af.getClient()[i]);
				for (Xiazhanfei xz : xzlist) {
					Xiazhanfei xzf = this.xiazhanfeiService.getXiazhangfei(xz
							.getXzid());//
					Double should = xzf.getXzcount() == null ? 0.0 : xzf
							.getXzcount();
					xzf.setXadefinedfour(should.toString());
					xzf.setXzzhuangtai("已收费");
					this.xiazhanfeiService.update(xzf);
				}
				/*
				 * // 如果该客户的货物冻结那么滞纳金收取完就可以解冻
				 * this.clientGoodsService.JieDong(af.getClient());
				 */
				// 将返回的结果保存在request中
				request.getSession().setAttribute("shouquAccountsMessage",
						"结算成功！");
			} else {
				request.getSession().setAttribute("shouquAccountsMessage",
						"结算失败！");
			}
		}
		if (num == af.getClient().length) {
			request.getSession().setAttribute("shouquAccountsMessage", "结算成功！");
		} else {
			request.getSession().setAttribute("shouquAccountsMessage", "结算失败！");
		}

		// 返回到对应的页面
		PrintWriter out = response.getWriter();
		out.print("<script>alert('"
				+ request.getSession().getAttribute("shouquAccountsMessage")
				+ "');" + "window.history.go(-1);</script>");
		return null;
	}

	// 对应客户查询自己结算的信息，
	public ActionForward getAppJieSuan(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AccountsForm af = (AccountsForm) form;
		PrintWriter out = response.getWriter();
		JSONArray array = new JSONArray();
		if (af == null) {
			JSONObject obj = new JSONObject();
			obj.put("result", "no");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		if (af.getClient() == null) {
			JSONObject obj = new JSONObject();
			obj.put("result", "no");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		List<Accounts> alist = this.accountsService.getAppQuery(
				af.getClient()[0], "未收费");
		if (alist.size() <= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "nofive");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		DecimalFormat df = new DecimalFormat("########0.00");
		for (int i = 0; i < alist.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("qixian", alist.get(i).getShoufeiqixian());// 保存收费期限
			obj.put("jiesuantime",
					alist.get(i).getJiesuantime().substring(0, 10));// 保存结算时间
			obj.put("cost",
					df.format(alist.get(i).getAccountsExpensesSeed()
							+ (alist.get(i).getAccountsSeed() == null ? 0.0
									: alist.get(i).getAccountsSeed())));// 保存费用合计
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();

		// 返回到对应的页面
		return null;
	}

	// 手机app中对应的客户查询对应的月结的缴费记录的信息
	public ActionForward getAppCostRecords(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		AccountsForm af = (AccountsForm) form;
		PrintWriter out = response.getWriter();
		JSONArray array = new JSONArray();
		if (af == null) {
			JSONObject obj = new JSONObject();
			obj.put("result", "no");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		if (af.getClient() == null) {
			JSONObject obj = new JSONObject();
			obj.put("result", "no");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		List<Accounts> alist = this.accountsService.getAppQuery(
				af.getClient()[0], "已收费");
		if (alist.size() <= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "nofive");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		DecimalFormat df = new DecimalFormat("########0.00");
		for (int i = 0; i < alist.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("jiaofeiren", alist.get(i).getJiaofeiren() == null ? "无"
					: alist.get(i).getJiaofeiren());// 保存收费期限
			obj.put("time",
					alist.get(i).getAccountsCollectTime().substring(0, 10));// 保存结算时间
			obj.put("cost", df.format(alist.get(i).getShishouCost()));// 保存费用合计
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();

		// 返回到对应的页面
		return null;
	}

	// 查看所有的滞纳金
	public ActionForward getAccountsAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AccountsForm af = (AccountsForm) form;
		if (af.getBegin() == null || af.getBegin().equals("")) {
			af.setBegin("");
		}
		if (af.getFinish() == null || af.getFinish().equals("")) {
			af.setFinish(pt.getTimes());
		}
		if (af.getJiancheng() == null) {
			af.setJiancheng("");
		}
		String shoufeiren = "";
		if (request.getParameter("shoufeiren") != null) {
			shoufeiren = request.getParameter("shoufeiren").toString();
		}
		int pageNow = 1;
		int rowSize = 20;
		if (request.getParameter("pageRow") != null) {
			rowSize = Integer.parseInt(request.getParameter("pageRow")
					.toString());
		}
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}

		int pageCount = this.accountsService.getCostRecordByPageCount(
				af.getBegin(), af.getFinish(), af.getJiancheng(), "随便",
				rowSize, shoufeiren);
		if (pageNow >= pageCount) {
			pageNow = pageCount;
		}
		if (pageNow <= 1) {
			pageNow = 1;
		}
		DecimalFormat df = new DecimalFormat("###########0.00");
		// 查询整个
		List<Accounts> alist = this.accountsService.getCostRecordByPage(
				af.getBegin(), af.getFinish(), af.getJiancheng(), "随便",
				pageNow, rowSize, shoufeiren);
		PrintWriter out = response.getWriter();
		JSONArray array = new JSONArray();
		for (int i = 0; i < alist.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("id", alist.get(i).getAccountsId());// 保存收费记录的编号
			obj.put("clientName", alist.get(i).getClient()
					.getClientAbbreviation());// 保存对应的客户的简称
			obj.put("begin", alist.get(i).getAccountsCreateTime());// 保存起始日期
			obj.put("finish", alist.get(i).getAccountsFinishTime());// 保存结束日期
			obj.put("count", alist.get(i).getAccountsExpensesSeed());// 保存费用合计
			obj.put("feilv", alist.get(i).getZhinaFeilv());// 保存滞纳金费率
			obj.put("zhinajin", alist.get(i).getAccountsSeed() == null ? 0.0
					: alist.get(i).getAccountsSeed());// 保存应收的滞纳金
			obj.put("zong",
					df.format(alist.get(i).getAccountsExpensesSeed()
							+ (alist.get(i).getAccountsSeed() == null ? 0.0
									: alist.get(i).getAccountsSeed())));
			obj.put("shoufeiren", alist.get(i).getShoufeiren().getIuserName());// 保存结算人
			obj.put("shoufeitime", alist.get(i).getAccountsCollectTime());// 保存结算时间
			obj.put("pageNow", pageNow);// 保存当前页
			obj.put("pageCount", pageCount);
			obj.put("rowSize", rowSize);// 保存显示的行数
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
		return null;
	}

	// app客户查询结算汇总

	// 查看所有的滞纳金
	public ActionForward getAccountsAllKH(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		int clientId = Integer.parseInt(request.getSession()
				.getAttribute("clientId").toString());

		AccountsForm af = (AccountsForm) form;
		if (af.getBegin() == null || af.getBegin().equals("")) {
			af.setBegin("");
		}
		if (af.getFinish() == null || af.getFinish().equals("")) {
			af.setFinish(pt.getTimes());
		}

		int pageNow = 1;
		int rowSize = 20;
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}

		System.out.println("结束时间：" + af.getFinish());

		int pageCount = this.accountsService.getCostRecordByPageCountKH(
				clientId, af.getBegin() + " 00:00:00", af.getFinish()
						+ " 23:59:59", "", rowSize);
		if (pageNow >= pageCount) {
			pageNow = pageCount;
		}
		if (pageNow <= 1) {
			pageNow = 1;
		}
		DecimalFormat df = new DecimalFormat("###########0.00");
		// 查询整个
		List<Accounts> alist = this.accountsService.getCostRecordByPageKH(
				clientId, af.getBegin() + " 00:00:00", af.getFinish()
						+ " 23:59:59", "", pageNow, rowSize);

		System.out.println("size:" + alist.size());

		PrintWriter out = response.getWriter();
		JSONArray array = new JSONArray();
		for (int i = 0; i < alist.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("id", alist.get(i).getAccountsId());// 保存收费记录的编号
			obj.put("clientName", alist.get(i).getClient()
					.getClientAbbreviation());// 保存对应的客户的简称
			obj.put("begin", alist.get(i).getAccountsCreateTime());// 保存起始日期
			obj.put("finish", alist.get(i).getAccountsFinishTime());// 保存结束日期
			obj.put("count", alist.get(i).getAccountsExpensesSeed());// 保存费用合计
			obj.put("feilv", alist.get(i).getZhinaFeilv());// 保存滞纳金费率
			obj.put("zhinajin", alist.get(i).getAccountsSeed() == null ? 0.0
					: alist.get(i).getAccountsSeed());// 保存应收的滞纳金
			obj.put("zong",
					df.format(alist.get(i).getAccountsExpensesSeed()
							+ (alist.get(i).getAccountsSeed() == null ? 0.0
									: alist.get(i).getAccountsSeed())));
			obj.put("shoufeiren", alist.get(i).getShoufeiren().getIuserName());// 保存结算人
			obj.put("shoufeitime", alist.get(i).getAccountsCollectTime());// 保存结算时间
			obj.put("pageNow", pageNow);// 保存当前页
			obj.put("rowSize", rowSize);// 保存显示的行数
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
		return null;
	}

	// 查询所有的
	public ActionForward getAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<Accounts> list = this.accountsService.getAll();
		request.setAttribute("alist", list);

		// 返回到对应的界面
		// PrintWriter out = response.getWriter();
		// out.print("<script>document.location.href='/XGProject/cangchu/page/shoufeijiludaochu.jsp';</script>");
		return mapping.findForward("goAll");
	}

	// 通过客户查询滞纳金，显示给对应的客户
	public ActionForward getClientAccounts(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String clientId = request.getParameter("clientId");

		int pageNow = 1;
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}
		int pageCount = this.accountsService.getClientByPageCount(
				Integer.parseInt(clientId), pr.getRow());
		if (pageNow >= pageCount) {
			pageNow = pageCount;
		}
		if (pageNow <= 1) {
			pageNow = 1;
		}
		List<Accounts> aclientList = this.accountsService.getClientByPage(
				Integer.parseInt(clientId), pageNow, pr.getRow());
		// 保存到request中
		request.setAttribute("aclientList", aclientList);

		// 将当前页保存到request中
		request.getSession().setAttribute("pageNow", pageNow);

		// 返回到对应的界面
		return super.execute(mapping, form, request, response);
	}

	// 计算相应客户的货物价值是否小于欠费金额
	public ActionForward ClientMoney(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		AccountsForm af = (AccountsForm) form;
		if (af.getClientName() == null) {
			af.setClientName("");
		}
		// 查询对应的客户
		List<Client> clist = this.clientService.getMohuClient(af.getClientName(), af.getClientName(), af.getClientName());
		JSONArray array = new JSONArray();
		PrintWriter out = response.getWriter();
		if (clist != null && clist.size() > 0) {
			// 得到今日钢价中最后一次录入的最大时间
			String maxTime = this.steelPriceService.getMaxTime();
			// 查询对应的时间的平均值
			Double avg = this.steelPriceService.getMaxAVG(maxTime.substring(0,11));
			DecimalFormat mdf = new DecimalFormat("##########0.00");
			DecimalFormat wdf = new DecimalFormat("##########0.000");
			
			// 通过客户查询相应的货物的价值
			for (int i = 0; i < clist.size(); i++) {
				Double xishu = 1.0;//对应客户的系数
				if(clist.get(i).getClientCredit()!=null && clist.get(i).getClientCredit()>0.0){
					xishu=clist.get(i).getClientCredit();
				}
				Double clientWeight = this.clientGoodsService.clientGoodsSum(clist.get(i).getClientId());// 通过客户查询相应的货物重量的总和
				// 得出当前客户的货物的价值
				Double jiazhi = (clientWeight == null ? 0.0 : clientWeight) * (avg == null ? 1 : avg) * xishu;
				// 查询对应客户的欠费金额
				Double qianFei = this.accountsService.getMoneySum(clist.get(i).getClientId());

				JSONObject obj = new JSONObject();
				obj.put("client", clist.get(i).getClientAbbreviation());// 保存客户名称
				obj.put("clientId", clist.get(i).getClientId());// 保存客户id
				obj.put("weight", clientWeight == null ? 0.0 : wdf.format(clientWeight));// 保存客户的全部货物的重量
				obj.put("danjia", avg == null ? 1.0 : mdf.format(avg));// 保存当前的单价
				obj.put("jiazhi", mdf.format(jiazhi));// 保存当前客户的货物的价值
				obj.put("qianfei", qianFei);// 保存对应客户的欠费金额
				obj.put("xishu", xishu);//保存对应的系数
				obj.put("result", "notnull");
				array.add(obj);
			}
			out.print(array);
			out.flush();
			out.close();
		} else {
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array);
			out.flush();
			out.close();
		}
		return null;
	}
}
