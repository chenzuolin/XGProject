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
import com.xinggang.project.entity.Input;
import com.xinggang.project.entity.InputOperate;
import com.xinggang.project.entity.InputSeed;
import com.xinggang.project.entity.InteriorUser;
import com.xinggang.project.entity.InteriorUserDuty;
import com.xinggang.project.entity.PaymentFashion;
import com.xinggang.project.entity.TarehouseDetail;
import com.xinggang.project.entity.Working;
import com.xinggang.project.form.InputOperateForm;
import com.xinggang.project.service.AppMessageService;
import com.xinggang.project.service.ClientGoodsService;
import com.xinggang.project.service.CostTypeService;
import com.xinggang.project.service.InputOperateService;
import com.xinggang.project.service.InputSeedService;
import com.xinggang.project.service.InputService;
import com.xinggang.project.service.InteriorUserDutyService;
import com.xinggang.project.service.InteriorUserService;
import com.xinggang.project.service.LoginLogService;
import com.xinggang.project.service.PaymentFashionService;
import com.xinggang.project.service.TarehouseDetailOperateService;
import com.xinggang.project.service.TarehouseDetailService;
import com.xinggang.project.service.TarehouseGoodsService;
import com.xinggang.project.service.TarehouseService;
import com.xinggang.project.service.UpdateRecordService;
import com.xinggang.project.service.WorkingService;
import com.xinggang.project.tools.PageRow;
import com.xinggang.project.tools.PresentTime;
import com.xinggang.project.tools.StatisticsWorking;

/**
 * 入库订单操作类action
 * 
 * @author Administrator
 * 
 */
public class InputOperateAction extends DispatchAction {

	// 入库总订单service
	private InputService inputService;
	// 入库订单操作表service
	private InputOperateService inputOperateService;
	// 入库子订单service
	private InputSeedService inputSeedService;
	// 库位service
	private TarehouseService tarehouseService;
	// 登录日志表service
	private LoginLogService loginLogService;
	// 内部人员service
	private InteriorUserService interiorUserService;
	// 职责service
	private InteriorUserDutyService interiorUserDutyService;
	// 客户货物库存表
	private ClientGoodsService clientGoodsService;
	// 货物库存表
	private TarehouseGoodsService tarehouseGoodsService;
	// 货物批次表
	private TarehouseDetailService tarehouseDetailService;
	// 货物批次操作表
	@SuppressWarnings("unused")
	private TarehouseDetailOperateService tarehouseDetailOperateService;
	// 费用类型
	private CostTypeService costTypeService;
	// 装卸工，牵车工
	private WorkingService workingService;
	// 支付方式service
	private PaymentFashionService paymentFashionService;
	// 客户app消息添加service
	private AppMessageService appMessageService;

	private UpdateRecordService updateRecordService;

	public void setUpdateRecordService(UpdateRecordService updateRecordService) {
		this.updateRecordService = updateRecordService;
	}

	public void setPaymentFashionService(
			PaymentFashionService paymentFashionService) {
		this.paymentFashionService = paymentFashionService;
	}

	public void setWorkingService(WorkingService workingService) {
		this.workingService = workingService;
	}

	public void setCostTypeService(CostTypeService costTypeService) {
		this.costTypeService = costTypeService;
	}

	public void setClientGoodsService(ClientGoodsService clientGoodsService) {
		this.clientGoodsService = clientGoodsService;
	}

	public void setTarehouseGoodsService(
			TarehouseGoodsService tarehouseGoodsService) {
		this.tarehouseGoodsService = tarehouseGoodsService;
	}

	public void setTarehouseDetailService(
			TarehouseDetailService tarehouseDetailService) {
		this.tarehouseDetailService = tarehouseDetailService;
	}

	public void setTarehouseDetailOperateService(
			TarehouseDetailOperateService tarehouseDetailOperateService) {
		this.tarehouseDetailOperateService = tarehouseDetailOperateService;
	}

	public void setAppMessageService(AppMessageService appMessageService) {
		this.appMessageService = appMessageService;
	}

	public void setInteriorUserDutyService(
			InteriorUserDutyService interiorUserDutyService) {
		this.interiorUserDutyService = interiorUserDutyService;
	}

	public void setInteriorUserService(InteriorUserService interiorUserService) {
		this.interiorUserService = interiorUserService;
	}

	public void setInputService(InputService inputService) {
		this.inputService = inputService;
	}

	public void setLoginLogService(LoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}

	public void setTarehouseService(TarehouseService tarehouseService) {
		this.tarehouseService = tarehouseService;
	}

	public void setInputSeedService(InputSeedService inputSeedService) {
		this.inputSeedService = inputSeedService;
	}

	public void setInputOperateService(InputOperateService inputOperateService) {
		this.inputOperateService = inputOperateService;
	}

	DecimalFormat ndf = new DecimalFormat();// 进行格式化数字的计算

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

	// 查询所有的订单
	public ActionForward selectAllInputOperat(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 返回行数
		PageRow pageRow = new PageRow();
		// 获得总页数
		int pageCount = inputOperateService.getPageCount(pageRow.getRow());
		request.setAttribute("pageCount", pageCount);
		// 获得所有客户
		String s_pageNows = request.getParameter("pageNow");
		// 获取从文本框中输入的页数
		String yeshu = request.getParameter("yeshu");
		int pageNow2 = 1;
		// 获取用户输入页数
		if (s_pageNows != null) {
			pageNow2 = fenye(s_pageNows, pageCount);
		}
		if (yeshu != null) {
			pageNow2 = fenye(yeshu, pageCount);
		}
		request.setAttribute("pageNow2", pageNow2);
		// 获得数据
		List<InputOperate> InputOperatelist = inputOperateService
				.getInputOperateByPage(pageNow2, pageRow.getRow());
		request.setAttribute("InputOperatelist", InputOperatelist);

		// 转到查询页面
		return null;
	}

	// 根据条件查询所有订单
	public ActionForward selectIfInputOperat(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 返回行数
		PageRow pageRow = new PageRow();
		InputOperateForm inputOperateForm = (InputOperateForm) form;
		// 根据id查询操作表
		InputOperate inputOperate = inputOperateService
				.getInputOperateId(inputOperateForm.getIoperateId());
		// 根据子订单id查询子订单
		InputSeed inputSeed = inputSeedService.getInputSeedId(inputOperateForm
				.getInputSeed());
		// 根据总订单id,查询总订单
		Input input = inputService
				.getInputId(inputSeed.getInput().getInputId());

		// 根据条件获得总页数
		int pageCount = inputOperateService.getPageCountTwo(input.getInputId(),
				input.getClient().getClientLoginName(), input
						.getInputCreateTime(), input.getInputPlateNumber(),
				input.getInputDriverName(), input.getInputCarryType(), input
						.getInputCancel(), inputSeed.getGoods().getGoodsName(),
				inputSeed.getIseedOrderStatus(), inputOperate.getTarehouse()
						.getTarehouseName(),
				inputOperate.getInteriorUserByIoperateDispatcherId()
						.getIuserName(), inputOperate
						.getInteriorUserByIoperateStoremanId().getIuserName(),
				inputOperate.getIoperateCraneman(), inputOperate
						.getIoperateStevedore(), inputOperate
						.getInteriorUserByIoperatePonderationManId()
						.getIuserName(), inputOperate
						.getInteriorUserByIoperateAuditingId().getIuserName(),
				inputOperate.getIoperateAuditingTime(),
				inputOperate.getInteriorUserByIoperateCollectManId()
						.getIuserName(), inputOperate.getIoperateCollectTime(),
				null, null, null, pageRow.getRow());
		request.setAttribute("pageCount", pageCount);
		// 获得当前页数
		String s_pageNows = request.getParameter("pageNow");
		String yeshu = request.getParameter("yeshu");
		int pageNow2 = 1;
		// 获取用户输入页数
		if (s_pageNows != null) {
			pageNow2 = fenye(s_pageNows, pageCount);
		}
		if (yeshu != null) {
			pageNow2 = fenye(yeshu, pageCount);
		}
		request.setAttribute("pageNow2", pageNow2);
		// 模糊查询根据客户姓名、订单生成时间、车号、司机姓名、运输方式、订单是否作废、货物名称、订单状态
		// 库位、调度员、保管员、天车工、装卸工、司磅员、审核人员、审核时间、收费员、收费时间得到分页页数
		// 获得数据
		List<InputOperate> InputOperatelist = inputOperateService
				.getInputOperateByPage2(input.getInputId(), input.getClient()
						.getClientLoginName(), input.getInputCreateTime(),
						input.getInputPlateNumber(),
						input.getInputDriverName(), input.getInputCarryType(),
						input.getInputCancel(), inputSeed.getGoods()
								.getGoodsName(), inputSeed
								.getIseedOrderStatus(), inputOperate
								.getTarehouse().getTarehouseName(),
						inputOperate.getInteriorUserByIoperateDispatcherId()
								.getIuserName(), inputOperate
								.getInteriorUserByIoperateStoremanId()
								.getIuserName(), inputOperate
								.getIoperateCraneman(), inputOperate
								.getIoperateStevedore(), inputOperate
								.getInteriorUserByIoperatePonderationManId()
								.getIuserName(), inputOperate
								.getInteriorUserByIoperateAuditingId()
								.getIuserName(), inputOperate
								.getIoperateAuditingTime(), inputOperate
								.getInteriorUserByIoperateCollectManId()
								.getIuserName(), inputOperate
								.getIoperateCollectTime(), null, null, null,
						pageNow2, pageRow.getRow());
		request.setAttribute("InputOperatelist", InputOperatelist);

		// 转到查询页面
		return null;
	}

	// 查询内部人员查询所有今日订单
	public ActionForward selectOperatAllToday(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 返回行数
		PageRow pageRow = new PageRow();
		PresentTime time = new PresentTime();
		// 模糊查询根据客户姓名、货物名称，货物产地，订单号查询
		int pageCount = inputOperateService.getPageCountTwo(null, null, null,
				null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, time.getDatesNianYR()
						+ "00:00:00", time.getTimes(), pageRow.getRow());
		request.setAttribute("pageCount", pageCount);
		// 获得所有客户
		String s_pageNows = request.getParameter("pageNow");
		String yeshu = request.getParameter("yeshu");
		int pageNow2 = 1;
		// 获取用户输入页数
		if (s_pageNows != null) {
			pageNow2 = fenye(s_pageNows, pageCount);
		}
		if (yeshu != null) {
			pageNow2 = fenye(yeshu, pageCount);
		}
		request.setAttribute("pageNow2", pageNow2);
		// 模糊查询根据客户姓名、货物名称，货物产地，订单号查询
		// 获得数据
		List<InputOperate> InputOperatelist = inputOperateService
				.getInputOperateByPage2(null, null, null, null, null, null,
						null, null, null, null, null, null, null, null, null,
						null, null, null, null, null, time.getDatesNianYR()
								+ "00:00:00", time.getTimes(), pageNow2,
						pageRow.getRow());
		request.setAttribute("InputOperatelist", InputOperatelist);

		// 转到查询页面
		return null;
	}

	// 查询内部人员根据条件查询今日订单
	public ActionForward selectInputOperatToday(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 返回行数
		PageRow pageRow = new PageRow();
		InputOperateForm inputOperateForm = (InputOperateForm) form;
		// 根据id查询操作表
		/*
		 * InputOperate inputOperate = inputOperateService
		 * .getInputOperateId(inputOperateForm.getIoperateId());
		 */// 根据子订单id查询子订单
		InputSeed inputSeed = inputSeedService.getInputSeedId(inputOperateForm
				.getInputSeed());
		// 根据总订单id,查询总订单
		Input input = inputService
				.getInputId(inputSeed.getInput().getInputId());
		PresentTime time = new PresentTime();
		// 模糊查询根据客户姓名、货物名称，货物产地，订单号查询
		int pageCount = inputOperateService.getPageCountTwo(input.getInputId(),
				input.getClient().getClientLoginName(), null, null, null, null,
				null, inputSeed.getGoods().getGoodsName(), null, null, null,
				null, null, null, null, null, null, null, null, inputSeed
						.getGoods().getGoodsYieldly().getGoodsYieldlyName(),
				time.getDatesNianYR() + "00:00:00", time.getTimes(),
				pageRow.getRow());
		request.setAttribute("pageCount", pageCount);
		// 获得所有客户
		String s_pageNows = request.getParameter("pageNow");
		String yeshu = request.getParameter("yeshu");
		int pageNow2 = 1;
		// 获取用户输入页数
		if (s_pageNows != null) {
			pageNow2 = fenye(s_pageNows, pageCount);
		}
		if (yeshu != null) {
			pageNow2 = fenye(yeshu, pageCount);
		}
		request.setAttribute("pageNow2", pageNow2);
		// 模糊查询根据客户姓名、货物名称，货物产地，订单号查询
		// 获得数据
		List<InputOperate> InputOperatelist = inputOperateService
				.getInputOperateByPage2(input.getInputId(), input.getClient()
						.getClientLoginName(), null, null, null, null, null,
						inputSeed.getGoods().getGoodsName(), null, null, null,
						null, null, null, null, null, null, null, null,
						inputSeed.getGoods().getGoodsYieldly()
								.getGoodsYieldlyName(), time.getDatesNianYR()
								+ "00:00:00", time.getTimes(), pageNow2,
						pageRow.getRow());
		request.setAttribute("InputOperatelist", InputOperatelist);

		// 转到查询页面
		return null;
	}

	// 查询内部人员点击查询今日订单详情
	public ActionForward selectXiangqingToday(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		InputOperate inputOperate = inputOperateService.getInputOperateId(id);
		request.setAttribute("inputOperate", inputOperate);
		return null;
	}

	// 客户根据条件查询所有今日订单
	public ActionForward selectClientAllToday(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 返回行数
		PageRow pageRow = new PageRow();
		PresentTime time = new PresentTime();
		// 获取客户登录名
		String loginName = request.getSession().getAttribute("loginName")
				.toString();

		// 模糊查询根据客户姓名、货物名称，货物产地，订单号查询
		int pageCount = inputOperateService.getPageCountTwo(null, loginName,
				null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, null,
				time.getDatesNianYR() + "00:00:00", time.getTimes(),
				pageRow.getRow());
		request.setAttribute("pageCount", pageCount);
		// 获得所有客户
		String s_pageNows = request.getParameter("pageNow");
		String yeshu = request.getParameter("yeshu");
		int pageNow2 = 1;
		// 获取用户输入页数
		if (s_pageNows != null) {
			pageNow2 = fenye(s_pageNows, pageCount);
		}
		if (yeshu != null) {
			pageNow2 = fenye(yeshu, pageCount);
		}
		request.setAttribute("pageNow2", pageNow2);

		// 获得数据
		List<InputOperate> InputOperatelist = inputOperateService
				.getInputOperateByPage2(null, loginName, null, null, null,
						null, null, null, null, null, null, null, null, null,
						null, null, null, null, null, null,
						time.getDatesNianYR() + "00:00:00", time.getTimes(),
						pageNow2, pageRow.getRow());
		request.setAttribute("InputOperatelist", InputOperatelist);

		// 转到查询页面
		return null;
	}

	// 客户根据条件查询今日订单
	public ActionForward selectClientToday(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 返回行数
		PageRow pageRow = new PageRow();
		InputOperateForm inputOperateForm = (InputOperateForm) form;
		// 根据id查询操作表
		/*
		 * InputOperate inputOperate = inputOperateService
		 * .getInputOperateId(inputOperateForm.getIoperateId());
		 */
		// 根据子订单id查询子订单
		InputSeed inputSeed = inputSeedService.getInputSeedId(inputOperateForm
				.getInputSeed());
		// 根据总订单id,查询总订单
		Input input = inputService
				.getInputId(inputSeed.getInput().getInputId());
		PresentTime time = new PresentTime();

		// 获取客户登录名
		String loginName = request.getSession().getAttribute("loginName")
				.toString();

		// 模糊查询根据客户姓名、货物名称，货物产地，订单号查询
		int pageCount = inputOperateService.getPageCountTwo(input.getInputId(),
				loginName, null, null, null, null, null, inputSeed.getGoods()
						.getGoodsName(), null, null, null, null, null, null,
				null, null, null, null, null, inputSeed.getGoods()
						.getGoodsYieldly().getGoodsYieldlyName(),
				time.getDatesNianYR() + "00:00:00", time.getTimes(), pageRow
						.getRow());
		request.setAttribute("pageCount", pageCount);
		// 获得所有客户
		String s_pageNows = request.getParameter("pageNow");
		// 获取用户输入的页数
		String yeshu = request.getParameter("yeshu");
		int pageNow2 = 1;
		// 获取用户输入页数
		if (s_pageNows != null) {
			pageNow2 = fenye(s_pageNows, pageCount);
		}
		if (yeshu != null) {
			pageNow2 = fenye(yeshu, pageCount);
		}
		request.setAttribute("pageNow2", pageNow2);
		// 模糊查询根据客户姓名、货物名称，货物产地，订单号查询
		// 获得数据
		List<InputOperate> InputOperatelist = inputOperateService
				.getInputOperateByPage2(input.getInputId(), input.getClient()
						.getClientLoginName(), null, null, null, null, null,
						inputSeed.getGoods().getGoodsName(), null, null, null,
						null, null, null, null, null, null, null, null,
						inputSeed.getGoods().getGoodsYieldly()
								.getGoodsYieldlyName(), time.getDatesNianYR()
								+ "00:00:00", time.getTimes(), pageNow2,
						pageRow.getRow());
		request.setAttribute("InputOperatelist", InputOperatelist);

		// 转到查询页面
		return null;
	}

	// 客户查询今日订单详情
	public ActionForward selectClientXingqingToday(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		InputOperate inputOperate = inputOperateService.getInputOperateId(id);
		request.setAttribute("inputOperate", inputOperate);
		return null;
	}

	// 当调度员确定保管员提交后添加操作信息，此时司磅员也可以看到非理算货物
	public void addInputOperat(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		// 定义一个内部人员类，传入类保存到实体类中
		InteriorUser user = null;

		// 得到该保管员的登录名，然后查询该保管员需要操作的订单信息
		String loginName = (String) request.getSession().getAttribute(
				"loginName");
		// 根据登录名名查询内部人员类，传过去
		List<InteriorUser> listUser = interiorUserService
				.getLoginName(loginName);
		for (int i = 0; i < listUser.size(); i++) {
			user = listUser.get(i);
		}

		InputOperateForm inputOperateForm = (InputOperateForm) form;
		// 保存操作
		boolean okInputOperate = inputOperateService.saveInputOperat(
				inputOperateForm, user);
		if (okInputOperate) {
			// 向消息中添加内容
			InputSeed inputSeed = this.inputSeedService
					.getInputSeedId(inputOperateForm.getInputSeed());
			this.appMessageService.saveMessage(inputSeed.getInput().getClient()
					.getClientId(), null, inputSeed.getInput().getInputId(),
					inputOperateForm.getInputSeed(), inputSeed.getGoods()
							.getGoodsName(),
					"您的司机已与我们取得联系，并且已经受理您的订单，我们会尽快为您服务！客户单号:"
							+ inputSeed.getInput().getInputClientNumber(),
					"入库订单");

			// 根据名字查询该作业人员id
			int baoguanid = inputOperateForm
					.getInteriorUserByIoperateStoremanId();
			// 修改该保管员的作业状态为正在作业
			interiorUserService.updateIuserWork(baoguanid, 0);
			String loginId = (String) request.getSession().getAttribute(
					"loginId");
			loginLogService.updateLogin(loginId, loginName + "分配人员入库中");
			// 修改入库子订单状态为准备入库
			inputSeedService.updateInputSeed(inputOperateForm.getInputSeed(),
					"准备入库");

			out.print("<script>alert('分配成功！');window.location.href='input.do?flag=selectPlanInput';</script>");

		} else {
			out.print("<script>alert('分配失败！');window.location.href='input.do?flag=selectPlanInput';</script>");
		}

	}

	// 公共方法用于返回职责名称
	public String chaXunUserDuty(HttpServletRequest request) {
		// 用职责表的id查询职责,登录时保存的id
		int zhizeId = Integer.parseInt(request.getSession()
				.getAttribute("zhizeId").toString());
		// 查询职责
		InteriorUserDuty interiorUserDuty = interiorUserDutyService
				.getInteriorUserDutyId(zhizeId);
		// 返回职责名称
		return interiorUserDuty.getInteriorUserDutyName();
	}

	// 查询该保管员要操作的信息
	public ActionForward selectBaoGuanInputOperat(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 调用公共方法返回职责名称
		// String interiorUserDutyName = chaXunUserDuty(request);
		// 如果是保管员进行如下操作

		// 得到该保管员的登录名，然后查询该保管员需要操作的订单信息
		/*
		 * String loginName = (String) request.getSession().getAttribute(
		 * "loginName");
		 */
		// 得到内部人员id
		int iulistId = Integer.parseInt(request.getSession()
				.getAttribute("iulistId").toString());

		// 根据操作员id确定要操作多少个订单
		List<InputOperate> listInputOperate = inputOperateService
				.getBaoguan(iulistId);
		// 保存需要操作的订单
		request.setAttribute("listInputOperate", listInputOperate);

		// 返回到操作界面
		return null;
	}

	// ajax查询保管员操作信息
	public void selectBaoGuanInputOperatAjax(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PrintWriter out = response.getWriter();
		// 调用公共方法返回职责名称
		// String interiorUserDutyName = chaXunUserDuty(request);
		// 如果是保管员进行如下操作

		// 获取保管员的id
		int loginNameId = Integer.parseInt(request.getParameter("dengluName"));

		// 根据操作员id确定要操作多少个订单
		List<InputOperate> listInputOperate = inputOperateService
				.getBaoguan(loginNameId);
		request.getSession().setAttribute("CZRuKu", listInputOperate.size());
		JSONArray array = new JSONArray();
		for (InputOperate c : listInputOperate) {
			JSONObject obj = new JSONObject();
			obj.put("zongId", c.getInputSeed().getInput().getInputId());
			obj.put("clientLoginName", c.getInputSeed().getInput().getClient()
					.getClientAbbreviation());
			obj.put("inputClientNumber", c.getInputSeed().getInput()
					.getInputClientNumber());
			obj.put("inputDefinedOne", c.getInputSeed().getInput()
					.getInputDefinedOne());
			obj.put("inputCarryType", c.getInputSeed().getInput()
					.getInputCarryType());
			obj.put("inputPlateNumber", c.getInputSeed().getInput()
					.getInputPlateNumber());
			obj.put("inputDriverName", c.getInputSeed().getInput()
					.getInputDriverName());
			obj.put("inputDriverTel", c.getInputSeed().getInput()
					.getInputDriverTel());
			obj.put("goodsCategoryName", c.getInputSeed().getGoods()
					.getGoodsCategory().getGoodsCategoryName());
			obj.put("goodsName", c.getInputSeed().getGoods().getGoodsName());
			obj.put("goodsStandardName", c.getInputSeed().getGoods()
					.getGoodsStandard().getGoodsStandardName());
			obj.put("goodsQualityName", c.getInputSeed().getGoods()
					.getGoodsQuality().getGoodsQualityName());
			obj.put("goodsPropertyName", c.getInputSeed().getGoods()
					.getGoodsProperty().getGoodsPropertyName());
			obj.put("goodsYieldlyName", c.getInputSeed().getGoods()
					.getGoodsYieldly().getGoodsYieldlyName());
			obj.put("iseedShouldWeight", c.getInputSeed()
					.getIseedShouldWeight());
			obj.put("inputCreateTime", c.getInputSeed().getInput()
					.getInputCreateTime());
			try {
				obj.put("diaodu", c.getInteriorUserByIoperateDispatcherId()
						.getIuserName());
			} catch (Exception e) {
			}
			obj.put("fenpeiTime", c.getIoperateDispatcherTime());
			obj.put("guoLi", c.getIoperatePonderationTrue());
			obj.put("feipeiWeight", c.getIoperateRealityWeight());
			obj.put("kuwei", c.getTarehouse().getTarehouseName());
			try {
				obj.put("baoguan", c.getInteriorUserByIoperateStoremanId()
						.getIuserName());
			} catch (Exception e) {
			}
			obj.put("dingdanStatus", c.getIoperateDefinedTwo());
			obj.put("beizhu", c.getIoperateRemark());
			// 放入隐藏域中的值
			obj.put("inputSeedId", c.getInputSeed().getIseedId());
			obj.put("inputOperateId", c.getIoperateId());

			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();

	}

	// 保管员点击处理后显示信息
	public ActionForward selectBaoGuanChuli(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PresentTime presentTime = new PresentTime();
		// 得到该保管员的登录名，然后查询该保管员需要操作的订单信息
		String loginName = (String) request.getSession().getAttribute(
				"loginName");
		// 得到操作表id,并且转为utf-8码
		String caozuoid = new String(request.getParameter("caozuoid").getBytes(
				"ISO-8859-1"), "utf-8");
		// 保存
		request.setAttribute("caozuoid", caozuoid);
		List<Working> workinglist = workingService.getAll();
		request.setAttribute("workinglist", workinglist);

		InputOperate inputOperate = inputOperateService
				.getInputOperateId(caozuoid);
		// 保存入库操作表对象
		request.setAttribute("inputOperate", inputOperate);
		inputOperate.setIoperateScreateTime(presentTime.getTimes());
		inputOperateService.update(inputOperate);// 保存保管员的操作时间
		// 获得登录日志编号
		String loginId = (String) request.getSession().getAttribute("loginId");
		loginLogService.updateLogin(loginId, loginName + "正在入库");

		return mapping.findForward("selectBaoGuanChuli");
	}

	// 保管员提交未完成货物操作，修改操作信息
	public void updateInputOperat(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PrintWriter out = response.getWriter();
		// 得到该保管员的登录名，然后查询该保管员需要操作的订单信息
		String loginName = (String) request.getSession().getAttribute(
				"loginName");
		// 得到内部人员id
		int iulistId = Integer.parseInt(request.getSession()
				.getAttribute("iulistId").toString());

		InputOperateForm inputOperateForm = (InputOperateForm) form;

		// 修改操作信息
		boolean ok = inputOperateService.updateInputOperat(inputOperateForm);
		if (ok) {
			System.out.println("修改操作订单成功!");
			// 获得登录日志编号
			String loginId = (String) request.getSession().getAttribute(
					"loginId");
			loginLogService.updateLogin(loginId, loginName + "正在审核");
			// 修改保管员的作业状态
			interiorUserService.updateIuserWork(iulistId, 1);
			out.print("<script>alert('提交成功！');window.location.href='inputOperate.do?flag=baoguanOkpage';</script>");
		} else {
			out.print("<script>alert('提交失败！');window.location.href='inputOperate.do?flag=baoguanOkpage';</script>");
		}

	}

	// 跳转页面
	public ActionForward baoguanOkpage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("baoguanOkpage");
	}

	public ActionForward sibangOkpage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("sibangOkpage");
	}

	// 司磅人员查询出非理算货物
	public ActionForward noLiSuanOperate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 返回行数
		PageRow pageRow = new PageRow();
		// 获得总页数
		int pageCount = inputOperateService.getSBCount("准备入库", "", "",
				pageRow.getRow());

		request.setAttribute("pageCount", pageCount);
		// 获得页面输入当前页
		String s_pageNows = request.getParameter("pageNow");

		int pageNow2 = 1;
		// 获取用户输入页数
		// 当前页
		if (s_pageNows != null) {
			pageNow2 = fenye(s_pageNows, pageCount);
		}

		request.setAttribute("pageNow2", pageNow2);

		List<InputOperate> listInputOperate = inputOperateService.getSBInfo(
				"准备入库", "", "", pageNow2, pageRow.getRow());
		// 保存该操作信息
		request.setAttribute("listInputOperate", listInputOperate);
		// 返回给司磅员看，当司磅人员填入数据后提交给管理员
		return mapping.findForward("noLiSuanOperate");
	}

	// 司磅通过ajax的方式查询所有未处理的订单
	public void selectSibangInputAjax(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();

		// 返回行数
		PageRow pageRow = new PageRow();
		// 获得总页数
		int pageCount = inputOperateService.getSBCount("准备入库", "", "",
				pageRow.getRow());
		// 判断，如果总页数为0，即：没有数据，不让访问数据库，直接return
		if (pageCount == 0) {
			JSONObject obj = new JSONObject();
			obj.put("qingkong", "clean");
			out.print(obj.toString());
			out.flush();
			out.close();
			return;
		}

		// 获得所有客户
		String s_pageNows = request.getParameter("pageNow");

		int pageNow2 = 1;
		// 获取用户输入页数
		// 当前页
		if (s_pageNows != null) {
			pageNow2 = fenye(s_pageNows, pageCount);
			if (Integer.parseInt(s_pageNows) > pageCount) {
				JSONObject obj = new JSONObject();
				obj.put("qingkong", "clean");
				out.print(obj.toString());
				out.flush();
				out.close();
				return;
			}
		}

		// 获得数据，保存子订单的信息
		List<InputOperate> listInputOperate = inputOperateService.getSBInfo(
				"准备入库", "", "", pageNow2, pageRow.getRow());
		// 用JSONObject保存班组
		JSONArray array = new JSONArray();
		for (InputOperate c : listInputOperate) {
			JSONObject obj = new JSONObject();
			obj.put("zongId", c.getInputSeed().getInput().getInputId());
			obj.put("clientLoginName", c.getInputSeed().getInput().getClient()
					.getClientAbbreviation());
			obj.put("inputClientNumber", c.getInputSeed().getInput()
					.getInputClientNumber());
			obj.put("inputDefinedOne", c.getInputSeed().getInput()
					.getInputDefinedOne());
			obj.put("inputCarryType", c.getInputSeed().getInput()
					.getInputCarryType());
			obj.put("inputPlateNumber", c.getInputSeed().getInput()
					.getInputPlateNumber());
			obj.put("inputDriverName", c.getInputSeed().getInput()
					.getInputDriverName());
			obj.put("inputDriverTel", c.getInputSeed().getInput()
					.getInputDriverTel());
			obj.put("goodsCategoryName", c.getInputSeed().getGoods()
					.getGoodsCategory().getGoodsCategoryName());
			obj.put("goodsName", c.getInputSeed().getGoods().getGoodsName());
			obj.put("goodsStandardName", c.getInputSeed().getGoods()
					.getGoodsStandard().getGoodsStandardName());
			obj.put("goodsQualityName", c.getInputSeed().getGoods()
					.getGoodsQuality().getGoodsQualityName());
			obj.put("goodsPropertyName", c.getInputSeed().getGoods()
					.getGoodsProperty().getGoodsPropertyName());
			obj.put("goodsYieldlyName", c.getInputSeed().getGoods()
					.getGoodsYieldly().getGoodsYieldlyName());
			obj.put("iseedShouldWeight", c.getInputSeed()
					.getIseedShouldWeight());
			obj.put("inputCreateTime", c.getInputSeed().getInput()
					.getInputCreateTime());
			obj.put("diaodu", c.getInteriorUserByIoperateDispatcherId()
					.getIuserName());
			obj.put("fenpeiTime", c.getIoperateDispatcherTime());
			obj.put("guoLi", c.getIoperatePonderationTrue());
			obj.put("feipeiWeight", c.getIoperateRealityWeight());
			obj.put("kuwei", c.getTarehouse().getTarehouseName());
			obj.put("baoguan", c.getInteriorUserByIoperateStoremanId()
					.getIuserName());
			obj.put("beizhu", c.getIoperateRemark());
			// 查询出实际件数让司磅员看
			obj.put("shijiJianshu", c.getIoperateRealityNumber());
			// 放入隐藏域中的值
			obj.put("inputSeedId", c.getInputSeed().getIseedId());
			obj.put("inputOperateId", c.getIoperateId());

			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
	}

	// 司磅点击处理后显示信息
	public ActionForward selectSiBangChuli(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// PresentTime presentTime = new PresentTime();
		// 得到该保管员的登录名，然后查询该保管员需要操作的订单信息
		String loginName = (String) request.getSession().getAttribute(
				"loginName");
		// 得到操作表id,并且转为utf-8码
		String caozuoid = new String(request.getParameter("caozuoid").getBytes(
				"ISO-8859-1"), "utf-8");
		// 保存
		request.setAttribute("caozuoid", caozuoid);
		InputOperate inputOperate = inputOperateService
				.getInputOperateId(caozuoid);
		// 保存入库操作表对象
		request.setAttribute("inputOperate", inputOperate);
		// 获得登录日志编号
		String loginId = (String) request.getSession().getAttribute("loginId");
		loginLogService.updateLogin(loginId, loginName + "正在过磅");
		return mapping.findForward("selectSiBangChuli");
	}

	// 司磅根据条件ajax的方式查询所有未处理的订单
	public void selectSibangInputAjaxTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();

		String danhao = request.getParameter("danhao");
		String huozhu = request.getParameter("huozhu");

		int pageRow = 20;
		if (request.getParameter("pageRow") != null) {
			pageRow = Integer.parseInt(request.getParameter("pageRow")
					.toString());
		}

		// 获得总页数
		int pageCount = inputOperateService.getSBCount("准备入库", danhao, huozhu,
				pageRow);
		// 判断，如果总页数为0，即：没有数据，不让访问数据库，直接return
		if (pageCount == 0) {
			JSONObject obj = new JSONObject();
			obj.put("qingkong", "clean");
			out.print(obj.toString());
			out.flush();
			out.close();
			return;
		}
		request.setAttribute("pageCount", pageCount);
		// 获得所有客户
		String s_pageNows = request.getParameter("pageNow");

		int pageNow2 = 1;
		// 获取用户输入页数
		// 当前页
		if (s_pageNows != null) {
			pageNow2 = fenye(s_pageNows, pageCount);
			if (Integer.parseInt(s_pageNows) > pageCount) {
				JSONObject obj = new JSONObject();
				obj.put("qingkong", "clean");
				out.print(obj.toString());
				out.flush();
				out.close();
				return;
			}
		}

		request.setAttribute("pageNow2", pageNow2);

		// 获得数据，保存子订单的信息
		List<InputOperate> listInputOperate = inputOperateService.getSBInfo(
				"准备入库", danhao, huozhu, pageNow2, pageRow);
		// 用JSONObject保存班组
		JSONArray array = new JSONArray();
		for (InputOperate c : listInputOperate) {
			JSONObject obj = new JSONObject();
			obj.put("zongId", c.getInputSeed().getInput().getInputId());// 总订单id
			obj.put("clientLoginName", c.getInputSeed().getInput().getClient()
					.getClientAbbreviation());// 客户简称
			obj.put("inputClientNumber", c.getInputSeed().getInput()
					.getInputClientNumber());// 客户单号
			obj.put("inputDefinedOne", c.getInputSeed().getInput()
					.getInputDefinedOne());// 订单有效期
			obj.put("inputCarryType", c.getInputSeed().getInput()
					.getInputCarryType());// 运输方式
			obj.put("inputPlateNumber", c.getInputSeed().getInput()
					.getInputPlateNumber());// 车号
			obj.put("inputDriverName", c.getInputSeed().getInput()
					.getInputDriverName());// 司机姓名
			obj.put("inputDriverTel", c.getInputSeed().getInput()
					.getInputDriverTel());// 司机电话
			obj.put("goodsCategoryName", c.getInputSeed().getGoods()
					.getGoodsCategory().getGoodsCategoryName());// 货物品类
			obj.put("goodsName", c.getInputSeed().getGoods().getGoodsName());// 货物名称
			obj.put("goodsStandardName", c.getInputSeed().getGoods()
					.getGoodsStandard().getGoodsStandardName());// 货物规格
			obj.put("goodsQualityName", c.getInputSeed().getGoods()
					.getGoodsQuality().getGoodsQualityName());// 货物材质
			obj.put("goodsPropertyName", c.getInputSeed().getGoods()
					.getGoodsProperty().getGoodsPropertyName());// 货物属性
			obj.put("goodsYieldlyName", c.getInputSeed().getGoods()
					.getGoodsYieldly().getGoodsYieldlyName());// 货物产地
			obj.put("iseedShouldWeight", c.getInputSeed()
					.getIseedShouldWeight());// 应收重量
			obj.put("inputCreateTime", c.getInputSeed().getInput()
					.getInputCreateTime());// 订单发起时间
			obj.put("diaodu", c.getInteriorUserByIoperateDispatcherId()
					.getIuserName());// 调度员
			obj.put("fenpeiTime", c.getIoperateDispatcherTime());// 分配时间
			obj.put("guoLi", c.getIoperatePonderationTrue());// 过磅理算
			obj.put("feipeiWeight", c.getIoperateRealityWeight());// 操作重量
			obj.put("kuwei", c.getTarehouse().getTarehouseName());// 库位
			obj.put("baoguan", c.getInteriorUserByIoperateStoremanId()
					.getIuserName());// 保管
			obj.put("jianshu", c.getIoperateRealityNumber());// 查询出实际件数让司磅员看
			obj.put("caozuoshijian", c.getIoperateScreateTime());// 保管操作时间
			obj.put("beizhu", c.getIoperateRemark());// 备注
			obj.put("inputSeedId", c.getInputSeed().getIseedId());// 子订单编号
			obj.put("inputOperateId", c.getIoperateId());// 操作订单编号
			obj.put("pageCount", pageCount); // 分页总页数
			obj.put("pageNow", pageNow2);// 当前页

			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
	}

	// 当司磅员点击确定后，修改操作表中的信息
	public void updateLiSuanOperat(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PrintWriter out = response.getWriter();
		InputOperateForm inputOperateForm = (InputOperateForm) form;
		// 获取入库操作表id,保存费理算货物重量
		// String interiorUserDutyName = chaXunUserDuty(request);
		// 得到登录名，然后查询该保管员需要操作的订单信息
		String loginName = (String) request.getSession().getAttribute(
				"loginName");

		// 得到内部人员id
		int iulistId = Integer.parseInt(request.getSession()
				.getAttribute("iulistId").toString());
		// 保存该类
		InteriorUser user = interiorUserService.getInteriorUserId(iulistId);

		// 修改货物信息（需要过磅的重量）
		boolean ok = inputOperateService.updateWeight(inputOperateForm, user);
		if (ok) {
			// 获得登录日志编号
			String loginId = (String) request.getSession().getAttribute(
					"loginId");
			loginLogService.updateLogin(loginId, loginName + "司磅人员完成称重");
			out.print("<script>alert('提交成功！');window.location.href='inputOperate.do?flag=sibangOkpage';</script>");
		} else {
			out.print("<script>alert('提交失败！');window.location.href='inputOperate.do?flag=sibangOkpage';</script>");

		}

	}

	// 审核人员查看需要审核的订单
	public ActionForward selectShenHeInputOperat(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 返回行数
		PageRow pageRow = new PageRow();
		// 获得总页数
		int pageCount = inputOperateService.count("正在审核", "", "",
				pageRow.getRow());
		request.setAttribute("pageCount", pageCount);
		// 获得所有客户
		String s_pageNows = request.getParameter("pageNow");

		int pageNow2 = 1;
		// 获取用户输入页数
		// 当前页
		if (s_pageNows != null) {
			pageNow2 = fenye(s_pageNows, pageCount);
		}

		// 已经入库，而且未审核的货物
		List<InputOperate> listInputOperate = inputOperateService.getInfo(
				"正在审核", "", "", pageNow2, pageRow.getRow());
		;
		// 保存该操作信息
		request.setAttribute("listInputOperate", listInputOperate);
		// 返回到页面查看
		return mapping.findForward("selectShenHeInputOperat");
	}

	// ----------------------------------------------------审核

	// 审核通过ajax的方式查询所有待处理的订单
	public void selectShenheInputAjax(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PrintWriter out = response.getWriter();
		// 返回行数
		PageRow pageRow = new PageRow();
		// 获得总页数
		int pageCount = inputOperateService.count("正在审核", "", "",
				pageRow.getRow());

		// 判断，如果总页数为0，即：没有数据，不让访问数据库，直接return
		if (pageCount == 0) {
			JSONObject obj = new JSONObject();
			obj.put("qingkong", "clean");
			out.print(obj.toString());
			out.flush();
			out.close();
			return;
		}

		// 获得所有客户
		String s_pageNows = request.getParameter("pageNow");

		int pageNow2 = 1;
		// 获取用户输入页数
		// 当前页
		if (s_pageNows != null) {
			pageNow2 = fenye(s_pageNows, pageCount);
			if (Integer.parseInt(s_pageNows) > pageCount) {
				JSONObject obj = new JSONObject();
				obj.put("qingkong", "clean");
				out.print(obj.toString());
				out.flush();
				out.close();
				return;
			}
		}

		// 获得数据，保存子订单的信息
		List<InputOperate> listInputOperate = inputOperateService.getInfo(
				"正在审核", "", "", pageNow2, pageRow.getRow());
		// 用JSONObject保存班组
		JSONArray array = new JSONArray();
		for (InputOperate c : listInputOperate) {
			JSONObject obj = new JSONObject();

			try {

				obj.put("zongId", c.getInputSeed().getInput().getInputId());
				obj.put("clientLoginName", c.getInputSeed().getInput()
						.getClient().getClientAbbreviation());
				obj.put("inputClientNumber", c.getInputSeed().getInput()
						.getInputClientNumber());
				obj.put("inputDefinedOne", c.getInputSeed().getInput()
						.getInputDefinedOne());
				obj.put("inputCarryType", c.getInputSeed().getInput()
						.getInputCarryType());
				obj.put("inputPlateNumber", c.getInputSeed().getInput()
						.getInputPlateNumber());
				obj.put("inputDriverName", c.getInputSeed().getInput()
						.getInputDriverName());
				obj.put("inputDriverTel", c.getInputSeed().getInput()
						.getInputDriverTel());
				obj.put("goodsCategoryName", c.getInputSeed().getGoods()
						.getGoodsCategory().getGoodsCategoryName());
				obj.put("goodsName", c.getInputSeed().getGoods().getGoodsName());
				obj.put("goodsStandardName", c.getInputSeed().getGoods()
						.getGoodsStandard().getGoodsStandardName());
				obj.put("goodsQualityName", c.getInputSeed().getGoods()
						.getGoodsQuality().getGoodsQualityName());
				obj.put("goodsPropertyName", c.getInputSeed().getGoods()
						.getGoodsProperty().getGoodsPropertyName());
				obj.put("goodsYieldlyName", c.getInputSeed().getGoods()
						.getGoodsYieldly().getGoodsYieldlyName());
				obj.put("iseedShouldWeight", c.getInputSeed()
						.getIseedShouldWeight());
				obj.put("inputCreateTime", c.getInputSeed().getInput()
						.getInputCreateTime());
				obj.put("diaodu", c.getInteriorUserByIoperateDispatcherId()
						.getIuserName());

				obj.put("fenpeiTime", c.getIoperateDispatcherTime());
				obj.put("guoLi", c.getIoperatePonderationTrue());
				obj.put("feipeiWeight", c.getIoperateRealityWeight());
				obj.put("kuwei", c.getTarehouse().getTarehouseName());
				obj.put("baoguan", c.getInteriorUserByIoperateStoremanId()
						.getIuserName());
				obj.put("beizhu", c.getIoperateRemark());
				// 查询出实际件数让司磅员看
				obj.put("shijiJianshu", c.getIoperateRealityNumber());

				obj.put("shijiWeight", c.getIoperateRealityWeight());
				obj.put("qianche", c.getIoperateCraneman());
				obj.put("chuangxie", c.getIoperateStevedore());
				obj.put("baoguanFinishTime", c.getIoperateSfinishTime());

				// 放入隐藏域中的值
				obj.put("inputSeedId", c.getInputSeed().getIseedId());
				obj.put("inputOperateId", c.getIoperateId());

				array.add(obj);
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
		out.print(array.toString());
		out.flush();
		out.close();
	}

	// 审核人员通过条件查询
	public void selectShenheInputAjaxTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();

		String danhao = request.getParameter("danhao");
		String huozhu = request.getParameter("huozhu");

		// 返回行数
		int pageRow = 20;
		if (request.getParameter("pageRow") != null) {
			pageRow = Integer.parseInt(request.getParameter("pageRow")
					.toString());
		}
		// 获得总页数
		int pageCount = inputOperateService.count("正在审核", danhao, huozhu,
				pageRow);
		if (pageCount == 0) {
			JSONObject obj = new JSONObject();
			obj.put("qingkong", "clean");
			out.print(obj.toString());
			out.flush();
			out.close();
			return;
		}

		// 获得所有客户
		String s_pageNows = request.getParameter("pageNow");

		int pageNow2 = 1;
		// 获取用户输入页数
		// 当前页
		if (s_pageNows != null) {
			pageNow2 = fenye(s_pageNows, pageCount);
			if (Integer.parseInt(s_pageNows) > pageCount) {
				JSONObject obj = new JSONObject();
				obj.put("qingkong", "clean");
				out.print(obj.toString());
				out.flush();
				out.close();
				return;
			}
		}

		request.setAttribute("pageNow2", pageNow2);

		// 获得数据，保存子订单的信息
		List<InputOperate> listInputOperate = inputOperateService.getInfo(
				"正在审核", danhao, huozhu, pageNow2, pageRow);
		// 用JSONObject保存班组
		JSONArray array = new JSONArray();
		for (InputOperate c : listInputOperate) {
			JSONObject obj = new JSONObject();
			obj.put("zongId", c.getInputSeed().getInput().getInputId());
			obj.put("clientLoginName", c.getInputSeed().getInput().getClient()
					.getClientAbbreviation());
			obj.put("inputClientNumber", c.getInputSeed().getInput()
					.getInputClientNumber());
			obj.put("inputDefinedOne", c.getInputSeed().getInput()
					.getInputDefinedOne());// 订单有效期
			obj.put("inputCarryType", c.getInputSeed().getInput()
					.getInputCarryType());// 运输方式
			obj.put("inputPlateNumber", c.getInputSeed().getInput()
					.getInputPlateNumber());// 车号
			obj.put("inputDriverName", c.getInputSeed().getInput()
					.getInputDriverName());
			obj.put("inputDriverTel", c.getInputSeed().getInput()
					.getInputDriverTel());
			obj.put("goodsCategoryName", c.getInputSeed().getGoods()
					.getGoodsCategory().getGoodsCategoryName());
			obj.put("goodsName", c.getInputSeed().getGoods().getGoodsName());
			obj.put("goodsStandardName", c.getInputSeed().getGoods()
					.getGoodsStandard().getGoodsStandardName());
			obj.put("goodsQualityName", c.getInputSeed().getGoods()
					.getGoodsQuality().getGoodsQualityName());
			obj.put("goodsPropertyName", c.getInputSeed().getGoods()
					.getGoodsProperty().getGoodsPropertyName());
			obj.put("goodsYieldlyName", c.getInputSeed().getGoods()
					.getGoodsYieldly().getGoodsYieldlyName());
			obj.put("iseedShouldWeight", c.getInputSeed()
					.getIseedShouldWeight());// 应收入库货物重量
			obj.put("inputCreateTime", c.getInputSeed().getInput()
					.getInputCreateTime());
			obj.put("diaodu", c.getInteriorUserByIoperateDispatcherId()
					.getIuserName());
			obj.put("fenpeiTime", c.getIoperateDispatcherTime());
			obj.put("guoLi", c.getIoperatePonderationTrue());
			obj.put("feipeiWeight", c.getInputSeed().getIseedShouldWeight());
			obj.put("kuwei", c.getTarehouse().getTarehouseName());
			obj.put("baoguan", c.getInteriorUserByIoperateStoremanId()
					.getIuserName());
			obj.put("beizhu", c.getIoperateRemark());
			// 查询出实际件数让司磅员看
			obj.put("shijiJianshu", c.getIoperateRealityNumber());
			obj.put("shijiWeight", c.getIoperateRealityWeight());
			obj.put("qianche", c.getIoperateCraneman());
			obj.put("chuangxie", c.getIoperateStevedore());
			obj.put("baoguanFinishTime", c.getIoperateSfinishTime());

			// 放入隐藏域中的值
			obj.put("inputSeedId", c.getInputSeed().getIseedId());
			obj.put("inputOperateId", c.getIoperateId());
			obj.put("pageNow", pageNow2);
			obj.put("pageCount", pageCount);

			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
	}

	// 审核人员处理订单
	public ActionForward selectShenHeOperat(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String loginName = (String) request.getSession().getAttribute(
				"loginName");

		// 得到操作表id,并且转为utf-8码
		String caozuoid = new String(request.getParameter("caozuoid").getBytes(
				"ISO-8859-1"), "utf-8");
		// 得到子订单id,并且转为utf-8码
		String ziId = new String(request.getParameter("ziId").getBytes(
				"ISO-8859-1"), "utf-8");
		// 保存
		request.setAttribute("caozuoid", caozuoid);
		request.setAttribute("ziId", ziId);
		InputOperate inputOperate = inputOperateService
				.getInputOperateId(caozuoid);
		// 保存入库操作表对象
		request.setAttribute("inputOperate", inputOperate);
		// 获得登录日志编号
		String loginId = (String) request.getSession().getAttribute("loginId");
		loginLogService.updateLogin(loginId, loginName + "正在审核");

		return mapping.findForward("selectShenHeOperat");

	}

	// 审核人员审核通过
	public void updateShenHeStatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PrintWriter out = response.getWriter();
		// 得到操作表id,并且转为utf-8码
		String caozuoid = new String(request.getParameter("czId"));
		// 得到操作表id,并且转为utf-8码
		String ziId = new String(request.getParameter("ziId"));
		// 得到备注
		String beizhu = new String(request.getParameter("beizhu"));

		InputOperate inputOperate = inputOperateService
				.getInputOperateId(caozuoid);

		// 通过子订单查询子订单
		InputSeed inputSeed = inputSeedService.getInputSeedId(ziId);
		// 得到总订单id,修改订单状态
		String zongId = inputSeed.getInput().getInputId();
		// 查询总订单信息，得到客户的id
		Input input = inputService.getInputId(zongId);
		// 得到该审核人员的登录名，然后查询该保管员需要操作的订单信息
		String loginName = (String) request.getSession().getAttribute(
				"loginName");
		// 得到折扣
		Double zhekou = input.getClient().getClientAgio();

		DecimalFormat df = new DecimalFormat("###########0.00");

		// 审核后算出费用
		Double shouMoney = 0.0;
		// 查询运输方式，汽运乘以汽运的费用，火车运输乘以火车运输的费用
		if (input.getInputCarryType().equals("汽运")) {
			// 审核后算出费用
			shouMoney = costTypeService.getQiYunRuKu()
					* inputOperate.getIoperateRealityWeight() * zhekou;
		}
		if (input.getInputCarryType().equals("火运")) {
			// 审核后算出费用
			shouMoney = costTypeService.getHuoYunRuKu()
					* inputOperate.getIoperateRealityWeight() * zhekou;
		}

		// 得到内部人员id
		int iulistId = Integer.parseInt(request.getSession()
				.getAttribute("iulistId").toString());
		// 得到该类然后将其传过去
		InteriorUser user = interiorUserService.getInteriorUserId(iulistId);
		boolean ok = inputOperateService.SHupdateInputOperat(user,
				Double.parseDouble(df.format(shouMoney)), caozuoid, beizhu);
		if (ok) {
			System.out.println("修改状态成功！");

			String loginId = (String) request.getSession().getAttribute(
					"loginId");
			loginLogService.updateLogin(loginId, loginName + "审核完成");
			// 将数据保存到子订单中
			boolean inputOk = inputService.updateWeight(inputOperate, ziId);
			// 如果添加成功
			if (inputOk) {
				// 在子订单中此时已经有数据，查询出来
				InputSeed inputseed2 = inputSeedService.getInputSeedId(ziId);
				// 在客户货物表中添加数据，传入子订单中的重量，和件数
				clientGoodsService.ZengjiaKucun(inputseed2.getInput()
						.getClient().getClientId(), inputseed2.getGoods()
						.getGoodsId(), inputOperate.getIoperateRealityWeight(),
						inputOperate.getIoperateRealityNumber());

				// 在库房中添加数据
				tarehouseGoodsService.zengGoods(inputOperate.getTarehouse()
						.getTarehouseId(), inputseed2.getGoods().getGoodsId(),
						inputOperate.getIoperateRealityWeight(), inputOperate
								.getIoperateRealityNumber());
				// 货物批次表表中添加数据
				tarehouseDetailService.saveGoodsDetail(request, inputseed2
						.getGoods().getGoodsId(), inputOperate.getTarehouse()
						.getTarehouseId(), inputOperate
						.getIoperateRealityWeight(), inputOperate
						.getIoperateRealityNumber());

				// 得到批次
				String pici = request.getSession().getAttribute("pici")
						.toString();
				InputOperate io = inputOperateService
						.getInputOperateId(caozuoid);
				System.out.println("批次是" + pici);

				io.setIoperatepici(pici + ",");
				// 在操作订单中保存批次
				inputOperateService.update(io);

			}

			out.print("<script>alert('审核成功！');window.location.href='inputOperate.do?flag=selectShenHeInputOperat';</script>");

		} else {

			out.print("<script>alert('审核失败！');window.location.href='inputOperate.do?flag=selectShenHeInputOperat';</script>");
		}
		//

	}

	// 如果审核不通过
	public void updateShenHeStatusTwo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PrintWriter out = response.getWriter();
		// 得到操作表id,并且转为utf-8码
		String caozuoid = new String(request.getParameter("czId"));
		// 得到子订单id,并且转为utf-8码
		String ziId = new String(request.getParameter("ziId"));
		String beizhu = new String(request.getParameter("beizhu"));

		/*
		 * InputOperate inputOperate = inputOperateService
		 * .getInputOperateId(caozuoid);
		 */
		// 通过子订单查询子订单
		InputSeed inputSeed = inputSeedService.getInputSeedId(ziId);
		// 得到该审核人员的登录名，然后查询该保管员需要操作的订单信息
		String loginName = (String) request.getSession().getAttribute(
				"loginName");

		// 得到内部人员id
		int iulistId = Integer.parseInt(request.getSession()
				.getAttribute("iulistId").toString());
		// 得到该类然后将其传过去
		InteriorUser user = interiorUserService.getInteriorUserId(iulistId);
		// 调用审核未通过的方法
		boolean ok = inputOperateService.SHupdateInputOperatTwo(user, caozuoid,
				beizhu);
		if (ok) {

			// 修改子订单表中的订单状态
			inputSeedService.updateInputSeed(inputSeed.getIseedId(), "准备入库");
			// 修改操作表中订单状态
			boolean bool = false;
			if (bool) {
				// 获得登录日志编号
				String loginId = (String) request.getSession().getAttribute(
						"loginId");
				loginLogService.updateLogin(loginId, loginName + "审核未通过");
			}
			out.print("<script>alert('提交成功！');window.location.href='inputOperate.do?flag=selectShenHeInputOperat';</script>");
		} else {
			out.print("<script>alert('提交失败！');window.location.href='inputOperate.do?flag=selectShenHeInputOperat';</script>");
		}

	}

	// 收费人员查看需要收费的订单
	public ActionForward selectMoneyInputOperat(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 返回行数
		PageRow pageRow = new PageRow();
		// 获得总页数
		int pageCount = inputOperateService.count("审核通过", "", "",
				pageRow.getRow());
		request.setAttribute("pageCount", pageCount);
		// 获得所有客户
		String s_pageNows = request.getParameter("pageNow");

		int pageNow2 = 1;

		// 判断用户输入的当前页是否为空
		if (s_pageNows != null) {
			pageNow2 = fenye(s_pageNows, pageCount);
		}

		// 已经入库，而且未审核的货物
		List<InputOperate> listInputOperate = inputOperateService.getInfo(
				"审核通过", "", "", pageNow2, pageRow.getRow());
		// 保存该操作信息
		request.setAttribute("listInputOperate", listInputOperate);
		// 返回到页面查看
		return mapping.findForward("selectMoneyInputOperat");
	}

	// 收费通过ajax的方式查询所有未处理的订单
	public void selectShoufeiInputAjax(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();

		// 返回行数
		PageRow pageRow = new PageRow();
		// 获得总页数
		int pageCount = inputOperateService.count("审核通过", "", "",
				pageRow.getRow());
		// 判断，如果总页数为0，即：没有数据，不让访问数据库，直接return
		if (pageCount == 0) {
			JSONObject obj = new JSONObject();
			obj.put("qingkong", "clean");
			out.print(obj.toString());
			out.flush();
			out.close();
			return;
		}
		// 获得所有客户
		String s_pageNows = request.getParameter("pageNow");

		int pageNow2 = 1;
		// 判断用户输入的当前页是否为空
		if (s_pageNows != null) {
			pageNow2 = fenye(s_pageNows, pageCount);
			if (Integer.parseInt(s_pageNows) > pageCount) {
				JSONObject obj = new JSONObject();
				obj.put("qingkong", "clean");
				out.print(obj.toString());
				out.flush();
				out.close();
				return;
			}
		}

		request.setAttribute("pageNow2", pageNow2);

		// 获得数据，保存子订单的信息
		List<InputOperate> listInputOperate = inputOperateService.getInfo(
				"审核通过", "", "", pageNow2, pageRow.getRow());
		// 用JSONObject保存班组
		JSONArray array = new JSONArray();
		for (InputOperate c : listInputOperate) {
			JSONObject obj = new JSONObject();
			obj.put("zongId", c.getInputSeed().getInput().getInputId());
			obj.put("clientLoginName", c.getInputSeed().getInput().getClient()
					.getClientAbbreviation());
			obj.put("inputClientNumber", c.getInputSeed().getInput()
					.getInputClientNumber());
			obj.put("inputDefinedOne", c.getInputSeed().getInput()
					.getInputDefinedOne());
			obj.put("inputCarryType", c.getInputSeed().getInput()
					.getInputCarryType());
			obj.put("inputPlateNumber", c.getInputSeed().getInput()
					.getInputPlateNumber());
			obj.put("inputDriverName", c.getInputSeed().getInput()
					.getInputDriverName());
			obj.put("inputDriverTel", c.getInputSeed().getInput()
					.getInputDriverTel());
			obj.put("goodsCategoryName", c.getInputSeed().getGoods()
					.getGoodsCategory().getGoodsCategoryName());
			obj.put("goodsName", c.getInputSeed().getGoods().getGoodsName());
			obj.put("goodsStandardName", c.getInputSeed().getGoods()
					.getGoodsStandard().getGoodsStandardName());
			obj.put("goodsQualityName", c.getInputSeed().getGoods()
					.getGoodsQuality().getGoodsQualityName());
			obj.put("goodsPropertyName", c.getInputSeed().getGoods()
					.getGoodsProperty().getGoodsPropertyName());
			obj.put("goodsYieldlyName", c.getInputSeed().getGoods()
					.getGoodsYieldly().getGoodsYieldlyName());
			obj.put("iseedShouldWeight", c.getInputSeed()
					.getIseedShouldWeight());
			obj.put("inputCreateTime", c.getInputSeed().getInput()
					.getInputCreateTime());
			obj.put("diaodu", c.getInteriorUserByIoperateDispatcherId()
					.getIuserName());
			obj.put("fenpeiTime", c.getIoperateDispatcherTime());
			obj.put("guoLi", c.getIoperatePonderationTrue());
			obj.put("feipeiWeight", c.getIoperateRealityWeight());
			obj.put("kuwei", c.getTarehouse().getTarehouseName());
			obj.put("baoguan", c.getInteriorUserByIoperateStoremanId()
					.getIuserName());
			obj.put("beizhu", c.getIoperateRemark());
			// 查询出实际件数让司磅员看
			obj.put("shijiJianshu", c.getIoperateRealityNumber());

			obj.put("shijiWeight", c.getIoperateRealityWeight());
			obj.put("qianche", c.getIoperateCraneman());
			obj.put("chuangxie", c.getIoperateStevedore());
			obj.put("baoguanFinishTime", c.getIoperateSfinishTime());

			// 放入隐藏域中的值
			obj.put("inputSeedId", c.getInputSeed().getIseedId());
			obj.put("inputOperateId", c.getIoperateId());

			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
	}

	// 收费人员通过条件查询
	public void selectShoufeiInputAjaxTj(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();

		String danhao = request.getParameter("danhao");
		String huozhu = request.getParameter("huozhu");

		// 返回行数
		int pageRow = 20;
		if (request.getParameter("pageRow") != null) {
			pageRow = Integer.parseInt(request.getParameter("pageRow")
					.toString());
		}
		// 获得总页数
		int pageCount = inputOperateService.count("审核通过", danhao, huozhu,
				pageRow);
		// 判断，如果总页数为0，即：没有数据，不让访问数据库，直接return
		if (pageCount == 0) {
			JSONObject obj = new JSONObject();
			obj.put("qingkong", "clean");
			out.print(obj.toString());
			out.flush();
			out.close();
			return;
		}
		// 获得所有客户
		String s_pageNows = request.getParameter("pageNow");
		int pageNow2 = 1;
		// 获取用户输入页数
		// 判断用户输入的当前页是否为空
		if (s_pageNows != null) {
			pageNow2 = fenye(s_pageNows, pageCount);
			if (Integer.parseInt(s_pageNows) > pageCount) {
				JSONObject obj = new JSONObject();
				obj.put("qingkong", "clean");
				out.print(obj.toString());
				out.flush();
				out.close();
				return;
			}
		}

		request.setAttribute("pageNow2", pageNow2);

		// 获得数据，保存子订单的信息
		List<InputOperate> listInputOperate = inputOperateService.getInfo(
				"审核通过", danhao, huozhu, pageNow2, pageRow);

		// 用JSONObject保存班组
		JSONArray array = new JSONArray();
		for (InputOperate c : listInputOperate) {
			JSONObject obj = new JSONObject();
			obj.put("zongId", c.getInputSeed().getInput().getInputId());
			obj.put("huozhu", c.getInputSeed().getInput().getClient()
					.getClientAbbreviation());
			obj.put("inputClientNumber", c.getInputSeed().getInput()
					.getInputClientNumber());
			obj.put("inputDefinedOne", c.getInputSeed().getInput()
					.getInputDefinedOne());// 订单有效期
			obj.put("inputCarryType", c.getInputSeed().getInput()
					.getInputCarryType());
			obj.put("inputPlateNumber", c.getInputSeed().getInput()
					.getInputPlateNumber());// 车号
			obj.put("inputDriverName", c.getInputSeed().getInput()
					.getInputDriverName());
			obj.put("inputDriverTel", c.getInputSeed().getInput()
					.getInputDriverTel());
			obj.put("goodsCategoryName", c.getInputSeed().getGoods()
					.getGoodsCategory().getGoodsCategoryName());
			obj.put("goodsName", c.getInputSeed().getGoods().getGoodsName());
			obj.put("goodsStandardName", c.getInputSeed().getGoods()
					.getGoodsStandard().getGoodsStandardName());
			obj.put("goodsQualityName", c.getInputSeed().getGoods()
					.getGoodsQuality().getGoodsQualityName());
			obj.put("goodsPropertyName", c.getInputSeed().getGoods()
					.getGoodsProperty().getGoodsPropertyName());
			obj.put("goodsYieldlyName", c.getInputSeed().getGoods()
					.getGoodsYieldly().getGoodsYieldlyName());
			obj.put("iseedShouldWeight", c.getInputSeed()
					.getIseedShouldWeight());// 应收重量
			obj.put("inputCreateTime", c.getInputSeed().getInput()
					.getInputCreateTime());// 订单发起时间
			obj.put("diaodu", c.getInteriorUserByIoperateDispatcherId()
					.getIuserName());
			obj.put("fenpeiTime", c.getIoperateDispatcherTime());
			obj.put("guoLi", c.getIoperatePonderationTrue());
			obj.put("feipeiWeight", c.getInputSeed().getIseedShouldWeight());
			obj.put("kuwei", c.getTarehouse().getTarehouseName());
			obj.put("baoguan", c.getInteriorUserByIoperateStoremanId()
					.getIuserName());
			obj.put("beizhu", c.getIoperateRemark());
			// 查询出实际件数让司磅员看
			obj.put("shijiJianshu", c.getIoperateRealityNumber());
			obj.put("shijiWeight", c.getIoperateRealityWeight());
			obj.put("qianche", c.getIoperateCraneman());
			obj.put("chuangxie", c.getIoperateStevedore());
			obj.put("baoguanFinishTime", c.getIoperateSfinishTime());

			// 放入隐藏域中的值
			obj.put("inputSeedId", c.getInputSeed().getIseedId());
			obj.put("inputOperateId", c.getIoperateId());
			obj.put("pageNow", pageNow2);
			obj.put("pageCount", pageCount);

			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
	}

	// 收费人员处理订单
	public ActionForward selectShoufeiOperat(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String loginName = (String) request.getSession().getAttribute(
				"loginName");

		// 得到操作表id,并且转为utf-8码
		String caozuoid = new String(request.getParameter("caozuoid").getBytes(
				"ISO-8859-1"), "utf-8");
		// 得到子订单id,并且转为utf-8码
		String ziId = new String(request.getParameter("ziId").getBytes(
				"ISO-8859-1"), "utf-8");
		// 保存
		request.setAttribute("caozuoid", caozuoid);
		request.setAttribute("ziId", ziId);
		InputOperate inputOperate = inputOperateService
				.getInputOperateId(caozuoid);
		List<PaymentFashion> payment = paymentFashionService.getAll();
		request.setAttribute("payment", payment);
		// 保存入库操作表对象
		request.setAttribute("inputOperate", inputOperate);
		// 获得登录日志编号
		String loginId = (String) request.getSession().getAttribute("loginId");
		loginLogService.updateLogin(loginId, loginName + "正在收费");

		return mapping.findForward("selectShoufeiOperat");

	}

	// 点击订单打印的时候查看详情，进行打印
	public ActionForward getDaYin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		InputOperateForm iof = (InputOperateForm) form;
		if (iof != null) {
			if (request.getParameter("zid") != null) {
				InputSeed is = this.inputSeedService.getInputSeedId(URLDecoder.decode(request.getParameter("zid").toString(), "UTF-8"));
				InputOperate inputOperate = this.inputOperateService
						.getInputOperateId(new String(iof.getIoperateId()
								.getBytes("ISO-8859-1"), "utf-8"));
				System.out.println(request.getParameter("zid"));
				request.setAttribute("is", is);// 将对应的子订单的值保存起来
				request.setAttribute("io", inputOperate);// 操作订单
				List<InputOperate> iolist = this.inputOperateService
						.getInputSeedId(is.getIseedId());
				request.setAttribute("iolist", iolist);
				if (request.getParameter("p") != null) {
					if (iolist != null) {
						if (iolist.size() > 0) {
							request.setAttribute("list", "list");
							return mapping.findForward("rukudayin");
						}
					}
				}
			}

		}
		return mapping.findForward("rukudayin");
	}

	// 收费人员收费
	public ActionForward getMoneyInputOperat(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		boolean ok = false;
		String loginName = (String) request.getSession().getAttribute(
				"loginName");
		PrintWriter out = response.getWriter();
		InputOperateForm inputOperateForm = (InputOperateForm) form;

		// 得到内部人员id
		int iulistId = Integer.parseInt(request.getSession()
				.getAttribute("iulistId").toString());
		// 得到该类然后将其传过去
		InteriorUser user = interiorUserService.getInteriorUserId(iulistId);
		// 获得登录日志编号
		String loginId = (String) request.getSession().getAttribute("loginId");
		loginLogService.updateLogin(loginId, loginName + "收费人员收费完成");

		// 子订单中的状态为入库完成
		if (inputOperateForm != null) {
			ok = inputOperateService.updateMoney(inputOperateForm, user);
		}

		if (ok) {
			InputOperate inputOperate = this.inputOperateService
					.getInputOperateId(inputOperateForm.getIoperateId());

			InputSeed inputSeed = this.inputSeedService
					.getInputSeedId(inputOperate.getInputSeed().getIseedId());// 通过编号查询子订单
																				// //
																				// 月结/现结
			request.setAttribute("is", inputSeed);// 将对应的子订单的值保存起来
			request.setAttribute("io", inputOperate);// 操作订单
			out.print("<script>alert('提交成功！');window.location.href='inputOperate.do?flag=selectMoneyInputOperat';</script>");
			// return mapping.findForward("rukudayin");// 返回到打印的界面
		} else {
			out.print("<script>alert('提交失败！');window.location.href='inputOperate.do?flag=selectMoneyInputOperat';</script>");
		}
		return null;
	}

	// 调度查询所用完成的订单
	public ActionForward getAllFinally(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// PrintWriter out = response.getWriter();
		String ziId = new String(request.getParameter("ziId").getBytes(
				"ISO-8859-1"), "utf-8");
		List<InputOperate> listInputOperate = inputOperateService
				.getAllFinallyDingdan(ziId);

		System.out.println("size:" + listInputOperate.size());

		if (listInputOperate.size() > 0) {
			request.setAttribute("inputOperate", listInputOperate.get(0));
		}
		request.setAttribute("listInputOperate", listInputOperate);
		// 通过子订单的编号进行查询子订单
		InputSeed inputSeed = this.inputSeedService.getInputSeedId(ziId);
		request.setAttribute("inputSeed", inputSeed);// 保存子订单类
		return mapping.findForward("getAllFinally");

	}

	// 调度查修改子订单状态
	public void updataSeedStatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		boolean ok = false;
		PrintWriter out = response.getWriter();
		InputOperateForm inputOperateForm = (InputOperateForm) form;
		String ziid = inputOperateForm.getInputSeed();
		// 通过子订单的编号进行查询
		InputSeed is = this.inputSeedService.getInputSeedId(ziid);
		this.appMessageService
				.saveMessage(is.getInput().getClient().getClientId(), null, is
						.getInput().getInputId(), is.getIseedId(), is
						.getGoods().getGoodsName(), "您的订单已经处理完成，货物已入库！客户单号："
						+ is.getInput().getInputClientNumber(), "入库订单");
		// 如果是月结客户则修改子订单状态为“未收费”
		if (is.getIseedClientAccounts() != null
				&& is.getIseedClientAccounts().equals("月结")) {
			ok = inputSeedService.updateInputSeed(
					inputOperateForm.getInputSeed(), "未收费");
		} else {// 否则子订单状态为“入库完成”
			ok = inputSeedService.updateInputSeed(
					inputOperateForm.getInputSeed(), "入库完成");
		}
		if (ok) {
			out.print("<script>alert('提交完成！');window.location.href='input.do?flag=selectPlanInput';</script>");
		} else {
			out.print("<script>alert('提交失败！');window.location.href='input.do?flag=selectPlanInput';</script>");
		}

	}

	// 入库订单作废

	// 当操作有误的时候进行操作的订单进行作废，重新开始作业
	public ActionForward DanGeZuoFei(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 保存弹出消息
		String mess = "";

		InputOperateForm eof = (InputOperateForm) form;
		DecimalFormat df = new DecimalFormat("########0.000");
		DecimalFormat cdf = new DecimalFormat("########0.00");

		if (eof != null) {

			String caozuoId = URLDecoder.decode(eof.getIoperateId(), "UTF-8");

			InputOperate eo = this.inputOperateService
					.getInputOperateId(caozuoId);// 通过编号进行查询
			if (eo != null) {

				// 重新设置子订单的状态为准备出库
				// 通过操作订单中保存的子订单的id进行查询子订单
				InputSeed es = this.inputSeedService.getInputSeedId(eo
						.getInputSeed().getIseedId());
				es.setIseedOrderStatus("准备入库");
				// 将子订单中增加的货物的重量和件数进行减少，指的是实出的重量和实出的件数
				Double weight = eo.getIoperateRealityWeight() == null ? 0.0
						: eo.getIoperateRealityWeight();// 取出操作订单中的重量
				Double number = eo.getIoperateRealityNumber() == null ? 0.0
						: eo.getIoperateRealityNumber();// 取出操作订单中的件数
				Double cost = eo.getIoperateRealityCost() == null ? 0.0 : eo
						.getIoperateRealityCost();// 取出操作订单中的实收的费用
				Double shouldCost = eo.getIoperateShouldCost() == null ? 0.0
						: eo.getIoperateShouldCost();// 取出操作订单中的应收费用

				// -----------------------------子订单

				Double Zweight = es.getIseedRealityWeight() == null ? 0.0 : es
						.getIseedRealityWeight();// 取出子订单中的重量
				Double Znumber = es.getIseedRealityNumber() == null ? 0.0 : es
						.getIseedRealityNumber();// 取出子订单中得到件数
				Double Zcost = es.getIseedRealityCost() == null ? 0.0 : es
						.getIseedRealityCost();// 取出自订单中的实收费用
				Double ZshouldCost = es.getIseedShouldCost() == null ? 0.0 : es
						.getIseedShouldCost();// 取出子订单中的应收的费用

				es.setIseedRealityCost(Double.parseDouble(cdf.format(Zcost
						- cost)));// 子订单中的实收的费用减少对应的操作订单中的费用
				es.setIseedShouldCost(Double.parseDouble(cdf.format(shouldCost
						- ZshouldCost)));

				// 对应的库位的库存进行减少，对应的批次也进行变化
				if (eo.getIoperateDefinedTwo().equals("审核通过")
						|| eo.getIoperateDefinedTwo().equals("正在收费")
						|| eo.getIoperateDefinedTwo().equals("已收费")
						|| eo.getIoperateDefinedTwo().equals("未收费")
						|| eo.getIoperateDefinedTwo().equals("入库完成")) {
					es.setIseedRealityWeight(Double.parseDouble(df
							.format(Zweight - weight)));// 子订单中的重量减少对应的操作订单中的重量
					es.setIseedRealityNumber(Double.parseDouble(df
							.format(Znumber - number)));// 子订单中的件数减少对应的操作订单中的件数
					this.tarehouseGoodsService.jianTGoods(eo.getTarehouse()
							.getTarehouseId(), es.getGoods().getGoodsId(),
							weight, number);

					System.out.println("操作批次：" + eo.getIoperatepici());

					// 进行对批次分解，查找对应的批次
					StatisticsWorking s = new StatisticsWorking();
					String str = eo.getIoperatepici();
					String strs = str.replaceAll(",", "");
					int x = s.countInnerStr(str, ",");

					for (int i = 0; i < x; i++) {
						String id = strs.substring(i * 12, (i + 1) * 12);
						TarehouseDetail td = this.tarehouseDetailService
								.getTarehouseDetailId(id);
						td.setTdetailDefinedTwo("0");// 改变批次中的标记
						td.setTdetailWeight(td.getTdetailWeight() - weight);// 对应的批次进行减少
						td.setTdetailNumber(td.getTdetailNumber() - number);// 对应的件数减少
						this.tarehouseDetailService.update(td);
					}

					// 客户货物库存减少
					List<ClientGoods> clientG = clientGoodsService
							.getClientGoodsByCG(es.getInput().getClient()
									.getClientId(), es.getGoods().getGoodsId());
					if (clientG.size() > 0) {
						for (ClientGoods c : clientG) {
							// 客户货物库存表中的实际重量减去操作订单中的重量
							c.setCgoodsWeight(c.getCgoodsWeight() - weight);
							// 修改作废后的重量
							clientGoodsService.update(c);
						}

					}

				}

				eo.setIoperateDefinedTwo("订单作废");
				// 进行修改
				boolean ok = this.inputOperateService.update(eo);
				boolean oks = this.inputSeedService.update(es);

				System.out.println(ok + "," + oks);

				if (ok && oks) {
					this.loginLogService
							.updateLogin(
									request.getSession()
											.getAttribute("loginlogId")
											.toString(), "作废入库订单，订单编号："
											+ eo.getInputSeed().getInput()
													.getInputId());
					mess = "作废成功！";
				} else {
					mess = "作废失败！";
				}
			} else {
				mess = "作废失败！";
			}
		} else {
			mess = "作废失败！";
		}

		PrintWriter out = response.getWriter();
		out.print("<script>alert('" + mess
				+ "');window.history.go(-1);</script>");
		// 返回到对应的面
		return null;
	}

	// 修改操作订单

	public ActionForward updateInputOperate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String message = "";

		InputOperateForm eof = (InputOperateForm) form;

		InteriorUser iu = (InteriorUser) request.getSession().getAttribute(
				"iulist");// 对应的登录的发起修改的人
		InputOperate eo = null;

		if (eof != null) {

			eo = inputOperateService.getInputOperateId(eof.getIoperateId());
			// 修改执行人
			if (eof.getInteriorUserByIoperateStoremanId() != null) {

				if (eof.getInteriorUserByIoperateStoremanId().equals(
						eo.getInteriorUserByIoperateStoremanId().getIuserId()) == false) {

					// 判断修改了执行人
					this.updateRecordService
							.RecordUpdate(
									eo.getIoperateId(),
									iu.getIuserName(),
									"修改执行人，原值："
											+ eo.getInteriorUserByIoperateStoremanId()
													.getIuserName()
											+ ",更改为："
											+ this.interiorUserService
													.getInteriorUserId(
															eof.getInteriorUserByIoperateStoremanId())
													.getIuserName());
					eo.setInteriorUserByIoperateStoremanId(this.interiorUserService
							.getInteriorUserId(eof
									.getInteriorUserByIoperateStoremanId()));
				}
			}

			// 修改库位
			if (eof.getTarehouse() != null) {
				if (eof.getTarehouse().equals(
						eo.getTarehouse().getTarehouseId()) == false) {
					this.updateRecordService.RecordUpdate(
							eo.getIoperateId(),
							iu.getIuserName(),
							"修改库位，原值："
									+ eo.getTarehouse().getTarehouseName()
									+ ",更改为："
									+ this.tarehouseService.getTarehouseId(
											eof.getTarehouse())
											.getTarehouseName());
					eo.setTarehouse(this.tarehouseService.getTarehouseId(eof
							.getTarehouse()));// 修改库位

				}
			}

			// 修改是否过磅，理算
			if (eof.getIoperatePonderationTrue() != null) {
				// 判断原值和传入的值是否发生了变化
				if (eof.getIoperatePonderationTrue().equals(
						eo.getIoperatePonderationTrue()) == false) {
					this.updateRecordService.RecordUpdate(
							eo.getIoperateId(),
							iu.getIuserName(),
							"修改是否过磅，原值：" + eo.getIoperatePonderationTrue()
									+ ",更改为："
									+ eof.getIoperatePonderationTrue());
					eo.setIoperatePonderationTrue(eof
							.getIoperatePonderationTrue());// 设置是否过磅
				}
			}

			// 修改重量
			if (eof.getIoperateRealityWeight() != null) {
				if (eof.getIoperateRealityWeight().equals(
						eo.getIoperateRealityWeight()) == false) {
					this.updateRecordService.RecordUpdate(eo.getIoperateId(),
							iu.getIuserName(),
							"修改重量，原值：" + eo.getIoperateRealityWeight()
									+ ",更改为：" + eof.getIoperateRealityWeight());
					eo.setIoperateRealityWeight(eof.getIoperateRealityWeight());
				}
			}
			// 修改件数
			if (eof.getIoperateRealityNumber() != null) {
				if (eof.getIoperateRealityNumber().equals(
						eo.getIoperateRealityNumber()) == false) {
					this.updateRecordService.RecordUpdate(eo.getIoperateId(),
							iu.getIuserName(),
							"修改件数，原值：" + eo.getIoperateRealityNumber()
									+ ",更改为：" + eof.getIoperateRealityNumber());
					eo.setIoperateRealityNumber(eof.getIoperateRealityNumber());
				}
			}
			// 修改天车工
			if (eof.getIoperateCraneman() != null) {
				if (eof.getIoperateCraneman().equals(eo.getIoperateCraneman()) == false) {
					this.updateRecordService.RecordUpdate(eo.getIoperateId(),
							iu.getIuserName(),
							"修改天车工，原值：" + eo.getIoperateCraneman() + ",更改为："
									+ eof.getIoperateCraneman());
					eo.setIoperateCraneman(eof.getIoperateCraneman());
				}
			}
			// 修改装卸工

			String strZX = "";

			// 获取多个装卸工，然后遍历后保存在字符串中
			String[] zhuangStr = eof.getZhuangxieGong();

			if (zhuangStr != null) {
				for (int i = 0; i < zhuangStr.length; i++) {
					strZX = strZX + zhuangStr[i] + ",";
				}

				if (strZX.equals(eo.getIoperateStevedore()) == false) {
					this.updateRecordService.RecordUpdate(eo.getIoperateId(),
							iu.getIuserName(),
							"修改装卸工，原值：" + eo.getIoperateStevedore() + ",更改为："
									+ strZX);
					eo.setIoperateStevedore(strZX);
				}
			}

			// 设置备注
			eo.setIoperateRemark(eo.getIoperateRemark() + ","
					+ eof.getIoperateRemark());

		}

		boolean ok = inputOperateService.update(eo);
		if (ok) {
			this.loginLogService.updateLogin(
					request.getSession().getAttribute("loginlogId").toString(),
					"修改出库操作订单,订单编号："
							+ eo.getInputSeed().getInput().getInputId());
			message = "修改成功！";
		} else {
			message = "修改失败！";
		}
		PrintWriter out = response.getWriter();
		out.print("<script>alert('" + message
				+ "');window.history.go(-3);</script>");
		// 返回到对应的页面
		return null;
	}

	// 保管在操作的时候修改对应的库位
	public ActionForward updateTarehouse(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		InputOperateForm iof = (InputOperateForm) form;
		String message = "";
		PrintWriter out = response.getWriter();
		if (iof != null) {
			// 通过编号进行查询
			InputOperate io = this.inputOperateService.getInputOperateId(iof
					.getIoperateId());
			if (io != null) {
				// 修改对应的库位
				try {
					io.setTarehouse(this.tarehouseService.getTarehouseId(iof
							.getTarehouse()));
					io.setIoperateRemark(io.getIoperateRemark() + ","
							+ iof.getIoperateRemark());
					boolean ok = this.inputOperateService.update(io);
					if (ok) {
						this.loginLogService.updateLogin(request.getSession()
								.getAttribute("loginlogId").toString(),
								"修改入库库位，订单编号："
										+ io.getInputSeed().getInput()
												.getInputId());
						message = "修改成功！";
					} else {
						message = "修改失败！";
					}
				} catch (Exception e) {
					System.out.println("保管在修改的时候出错了");
					message = "修改失败！";
				}

			} else {
				message = "修改失败！";
			}
		} else {
			message = "修改失败！";
		}
		out.print("<script>alert('" + message
				+ "');window.history.go(-1);</script>");
		out.flush();
		out.close();
		return null;
	}

}
