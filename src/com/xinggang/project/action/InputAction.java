package com.xinggang.project.action;

import java.io.PrintWriter;
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
import com.xinggang.project.entity.Functions;
import com.xinggang.project.entity.Goods;
import com.xinggang.project.entity.GoodsCategory;
import com.xinggang.project.entity.Input;
import com.xinggang.project.entity.InputSeed;
import com.xinggang.project.entity.InteriorUser;
import com.xinggang.project.entity.Powers;
import com.xinggang.project.entity.Tarehouse;
import com.xinggang.project.form.InputForm;
import com.xinggang.project.service.BursaryService;
import com.xinggang.project.service.ClientService;
import com.xinggang.project.service.CostTypeService;
import com.xinggang.project.service.FunctionsService;
import com.xinggang.project.service.GoodsCategoryService;
import com.xinggang.project.service.GoodsPropertyService;
import com.xinggang.project.service.GoodsQualityService;
import com.xinggang.project.service.GoodsService;
import com.xinggang.project.service.GoodsStandardService;
import com.xinggang.project.service.GoodsYieldlyService;
import com.xinggang.project.service.InputSeedService;
import com.xinggang.project.service.InputService;
import com.xinggang.project.service.InteriorUserService;
import com.xinggang.project.service.LoginLogService;
import com.xinggang.project.service.PaymentFashionService;
import com.xinggang.project.service.PowersService;
import com.xinggang.project.service.TarehouseService;
import com.xinggang.project.tools.PageRow;

/**
 * GoodsYieldly entity. @author MyEclipse Persistence Tools 货物产地类action
 */
public class InputAction extends DispatchAction {
	// 入库总订单service
	private InputService inputService;
	// 客户service
	private ClientService clientService;
	// 入库子订单service
	private InputSeedService inputSeedService;
	// 货物service
	private GoodsService goodsService;
	// 功能service
	private FunctionsService functionsService;
	// 支付方式Service
	@SuppressWarnings("unused")
	private PaymentFashionService paymentFashionService;
	// 内部人员
	private InteriorUserService interiorUserService;
	// 库位
	private TarehouseService tarehouseService;
	// 库房
	@SuppressWarnings("unused")
	private BursaryService bursaryService;

	// 权限service
	private PowersService powersService;
	// 登录日志表service
	private LoginLogService loginLogService;
	// 货物的品类
	private GoodsCategoryService goodsCategoryService;
	// 货物属性
	@SuppressWarnings("unused")
	private GoodsPropertyService goodsPropertyService;
	// 货物材质
	@SuppressWarnings("unused")
	private GoodsQualityService goodsQualityService;
	// 货物规格
	@SuppressWarnings("unused")
	private GoodsStandardService goodsStandardService;
	// 货物产地
	@SuppressWarnings("unused")
	private GoodsYieldlyService goodsYieldlyService;
	// 费用类型
	private CostTypeService costTypeService;

	public void setPowersService(PowersService powersService) {
		this.powersService = powersService;
	}

	public void setFunctionsService(FunctionsService functionsService) {
		this.functionsService = functionsService;
	}

	public void setCostTypeService(CostTypeService costTypeService) {
		this.costTypeService = costTypeService;
	}

	public void setGoodsCategoryService(
			GoodsCategoryService goodsCategoryService) {
		this.goodsCategoryService = goodsCategoryService;
	}

	public void setGoodsPropertyService(
			GoodsPropertyService goodsPropertyService) {
		this.goodsPropertyService = goodsPropertyService;
	}

	public void setGoodsQualityService(GoodsQualityService goodsQualityService) {
		this.goodsQualityService = goodsQualityService;
	}

	public void setGoodsStandardService(
			GoodsStandardService goodsStandardService) {
		this.goodsStandardService = goodsStandardService;
	}

	public void setGoodsYieldlyService(GoodsYieldlyService goodsYieldlyService) {
		this.goodsYieldlyService = goodsYieldlyService;
	}

	public void setBursaryService(BursaryService bursaryService) {
		this.bursaryService = bursaryService;
	}

	public void setTarehouseService(TarehouseService tarehouseService) {
		this.tarehouseService = tarehouseService;
	}

	public void setInteriorUserService(InteriorUserService interiorUserService) {
		this.interiorUserService = interiorUserService;
	}

	// 客户service
	public void setPaymentFashionService(
			PaymentFashionService paymentFashionService) {
		this.paymentFashionService = paymentFashionService;
	}

	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}

	public void setInputSeedService(InputSeedService inputSeedService) {
		this.inputSeedService = inputSeedService;
	}

	public void setLoginLogService(LoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public void setInputService(InputService inputService) {
		this.inputService = inputService;
	}

	DecimalFormat ndf = new DecimalFormat("############0.00");// 进行转换相应的数字计算

	// 查询客户
	public void goSelectClient(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PrintWriter out = response.getWriter();
		// 查询客户信息
		List<Client> listClient = clientService.getAll();

		JSONArray array = new JSONArray();
		if (listClient.size() > 0) {
			for (Client c : listClient) {
				JSONObject obj = new JSONObject();
				obj.put("id", c.getClientId());
				obj.put("danweiName", c.getClientAbbreviation());
				array.add(obj);
			}
		}

		out.print(array.toString());
		out.flush();
		out.close();

	}

	public ActionForward goInputPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("goInputPage");
	}

	// 转到发起入库
	public ActionForward goInputPageApp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("goInputPageApp");
	}

	public void selectGoodsPinlei(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();

		// 查询客户信息
		List<Client> listClient = clientService.getAll();
		request.setAttribute("listClient", listClient);
		// 查询货物品类
		List<Goods> goodsPinlei = goodsService.getAllGoods();
		JSONArray array = new JSONArray();
		if (goodsPinlei != null) {
			for (Goods c : goodsPinlei) {
				JSONObject obj = new JSONObject();
				obj.put("pinleiId", c.getGoodsCategory().getGoodsCategoryId());
				obj.put("pinlei", c.getGoodsCategory().getGoodsCategoryName());
				array.add(obj);
			}
		}

		out.print(array.toString());
		out.flush();
		out.close();

	}

	// ajax当货物品类选定后改变货物的名称等
	public void xuanGoodsPinlei(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 得到货物名称品类
		int pinleiid = Integer.parseInt(request.getParameter("pinlei"));
		PrintWriter out = response.getWriter();
		// 根据货物品类id查询货物，进行过滤
		List<Goods> listGoodsName = goodsService.getPinlei(pinleiid);
		JSONArray array = new JSONArray();
		if (listGoodsName != null) {
			for (Goods c : listGoodsName) {
				JSONObject obj = new JSONObject();
				obj.put("mingcheng", c.getGoodsName());
				array.add(obj);
			}
		}

		out.print(array.toString());
		out.flush();
		out.close();

	}

	// ajax当货物名称选定后改变货物的名称等
	public void xuanGoodsName(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 得到品类id
		int pinleiid = Integer.parseInt(request.getParameter("pinlei"));
		// 得到货物名称
		String name = request.getParameter("goodsname");

		PrintWriter out = response.getWriter();

		// 根据货物名称查询货物，进行过滤
		List<Goods> listGuiGe = goodsService.getGoodSName(pinleiid, name);

		// 用JSONObject保存班组
		JSONArray array = new JSONArray();
		for (Goods c : listGuiGe) {
			JSONObject obj = new JSONObject();
			obj.put("guigeId", c.getGoodsStandard().getGoodsStandardId());
			obj.put("gui", c.getGoodsStandard().getGoodsStandardName());
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();

	}

	// ajax当货物规格选定后改变货物的信息等
	public void xuanGuige(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int peileiid = Integer.parseInt(request.getParameter("pinlei"));
		String name = request.getParameter("goodsname");
		int guigeid = Integer.parseInt(request.getParameter("guige"));

		PrintWriter out = response.getWriter();
		// 根据货物规格id查询货物，进行过滤
		List<Goods> listCaiZhi = goodsService.getGuiGeId(peileiid, name,
				guigeid);
		// 用JSONObject保存班组
		JSONArray array = new JSONArray();
		for (Goods c : listCaiZhi) {
			JSONObject obj = new JSONObject();
			obj.put("caizhiId", c.getGoodsQuality().getGoodsQualityId());
			obj.put("caizhi", c.getGoodsQuality().getGoodsQualityName());
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();

	}

	// ajax当货物材质选定后改变货物的信息等
	public void xuanCaizhi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		int peileiid = Integer.parseInt(request.getParameter("pinlei"));
		String name = request.getParameter("goodsname");
		int guigeid = Integer.parseInt(request.getParameter("guige"));
		// 得到材质id
		int caizhiid = Integer.parseInt(request.getParameter("caizhi"));
		PrintWriter out = response.getWriter();
		// 根据货物规格id查询货物，进行过滤
		List<Goods> listShuxin = goodsService.getCaiZhiId(peileiid, name,
				guigeid, caizhiid);
		// 用JSONObject保存班组
		JSONArray array = new JSONArray();
		for (Goods c : listShuxin) {
			JSONObject obj = new JSONObject();
			obj.put("shuxingId", c.getGoodsProperty().getGoodsPropertyId());
			obj.put("shuxing", c.getGoodsProperty().getGoodsPropertyName());
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();

	}

	// ajax当货物材质选定后改变货物的信息等
	public void xuanShuxin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		int peileiid = Integer.parseInt(request.getParameter("pinlei"));
		String name = request.getParameter("goodsname");
		int guigeid = Integer.parseInt(request.getParameter("guige"));
		// 得到材质id
		int caizhiid = Integer.parseInt(request.getParameter("caizhi"));
		// 得到属性id
		int shuxingid = Integer.parseInt(request.getParameter("shuxing"));
		PrintWriter out = response.getWriter();

		// 查询货物品类
		List<GoodsCategory> goodsPinlei = goodsCategoryService.getAll();
		// 保存货物品类
		request.setAttribute("goodsPinlei", goodsPinlei);
		// 根据货物规格id查询货物，进行过滤
		List<Goods> listChandi = goodsService.getShuXinId(peileiid, name,
				guigeid, caizhiid, shuxingid);
		// 用JSONObject保存班组
		JSONArray array = new JSONArray();
		for (Goods c : listChandi) {
			JSONObject obj = new JSONObject();
			obj.put("goodsId", c.getGoodsId());
			obj.put("chandi", c.getGoodsYieldly().getGoodsYieldlyName());
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
	}

	// 计划入库
	public void planInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		InputForm inputForm = (InputForm) form;

		// 根据客户的id查询客户的折扣
		Client client = clientService.getClientId(inputForm.getClient());
		// 得到折扣
		Double zhekou = client.getClientAgio();
		// 审核后算出费用
		Double shouMoney = 0.0;
		//
		Double[] weights = inputForm.getIseedShouldWeights();
		// 查询运输方式，汽运乘以汽运的费用，火车运输乘以火车运输的费用
		if (inputForm.getInputCarryType().equals("汽运")) {
			// 审核后算出费用
			for (int i = 0; i < weights.length; i++) {
				shouMoney = costTypeService.getQiYunRuKu() * weights[i]
						* zhekou;
			}

		}
		if (inputForm.getInputCarryType().equals("火运")) {
			// 审核后算出费用
			for (int i = 0; i < weights.length; i++) {
				shouMoney = costTypeService.getHuoYunRuKu() * weights[i]
						* zhekou;
			}
		}
		String faqiren = "";
		if(request.getSession().getAttribute("iulist")!=null){
			InteriorUser iu = (InteriorUser) request.getSession().getAttribute("iulist");// 对应的内部登录人
			faqiren = iu.getIuserName();
		}else{
			faqiren = this.clientService.getClientId(inputForm.getClient()).getClientAbbreviation();
		}
		boolean ok = inputService.saveInput(inputForm,
				Double.parseDouble(ndf.format(shouMoney)),faqiren);
		if (ok) {
			
			// 得到该客户的登录名
			String loginName = (String) request.getSession().getAttribute(
					"loginName");
			String loginId = (String) request.getSession().getAttribute(
					"loginId");
			loginLogService.updateLogin(loginId, loginName + "发起计划入库");

			out.print("<script>alert('计划入库成功！');window.location.href='input.do?flag=goInputPage';</script>");
		} else {
			out.print("<script>alert('计划入库失败！');window.location.href='input.do?flag=goInputPage';</script>");
		}
	}

	// 计划入库app
	public ActionForward planInputApp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		boolean ok = false;
		String mess = "";
		PrintWriter out = response.getWriter();
		InputForm inputForm = (InputForm) form;

		// 根据客户的id查询客户的折扣
		Client client = clientService.getClientId(inputForm.getClient());
		// 得到折扣
		Double zhekou = client.getClientAgio();
		// 审核后算出费用
		Double shouMoney = 0.0;
		//
		Double[] weights = inputForm.getIseedShouldWeights();
		// 查询运输方式，汽运乘以汽运的费用，火车运输乘以火车运输的费用
		if (inputForm.getInputCarryType().equals("汽运")) {
			// 审核后算出费用
			for (int i = 0; i < weights.length; i++) {
				shouMoney = costTypeService.getQiYunRuKu() * weights[i]
						* zhekou;
			}

		}
		if (inputForm.getInputCarryType().equals("火运")) {
			// 审核后算出费用
			for (int i = 0; i < weights.length; i++) {
				shouMoney = costTypeService.getQiYunRuKu() * weights[i]
						* zhekou;
			}
		}

		// 查询是否有该货物
		Goods good = goodsService.getOneGoods(inputForm.getGoodsCategoryId(),
				inputForm.getGoodsStandardId(), inputForm.getGoodsName(),
				inputForm.getGoodsQualityId(), inputForm.getGoodsPropertyId(),
				inputForm.getGoodsYieldlyId());

		if (good == null) {
			mess = "没有该货物！";
		} else {
			ok = inputService.saveInputApp(inputForm,
					Double.parseDouble(ndf.format(shouMoney)), good);
			if (ok) {
				// 得到该客户的登录名
				String loginName = (String) request.getSession().getAttribute(
						"loginName");
				String loginId = (String) request.getSession().getAttribute(
						"loginId");
				loginLogService.updateLogin(loginId, loginName + "发起计划入库");
				mess = "计划入库成功！";

			} else {
				mess = "计划入库失败！";
			}
		}

		if (request.getParameter("ff") != null) {
			if (request.getParameter("ff").equals("shouji")) {
				out.print(mess);
				out.flush();
				out.close();
				return null;
			}
		}
		out.print("<script>alert('"
				+ mess
				+ "');window.location.href='input.do?flag=goInputPageApp';</script>");
		return null;
	}

	// 调度模糊查询未处理订单
	public void selectPlanInputDZAjax(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		// 车号
		String chehao = request.getParameter("danhao");
		// 货主
		String clienthuozhu = request.getParameter("huozhu");

		// 返回行数
		int pageRow = 20;
		if (request.getParameter("pageRow") != null) {
			pageRow = Integer.parseInt(request.getParameter("pageRow")
					.toString());
		}
		// 获得总页数
		int pageCount = inputSeedService.count("计划入库", "准备入库", chehao,
				clienthuozhu, pageRow);

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
		List<InputSeed> inputSeedlist = inputSeedService.getInfo("计划入库",
				"准备入库", chehao, clienthuozhu, pageNow2, pageRow);

		JSONArray array = new JSONArray();
		if (inputSeedlist.size() <= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
		} else {
			for (InputSeed c : inputSeedlist) {
				JSONObject obj = new JSONObject();
				obj.put("zongId", c.getInput().getInputId());
				obj.put("clientLoginName", c.getInput().getClient()
						.getClientAbbreviation());
				obj.put("inputClientNumber", c.getInput()
						.getInputClientNumber());
				obj.put("inputDefinedOne", c.getInput().getInputDefinedOne());
				obj.put("inputCarryType", c.getInput().getInputCarryType());
				obj.put("inputPlateNumber", c.getInput().getInputPlateNumber());
				obj.put("inputDriverName", c.getInput().getInputDriverName());
				obj.put("inputDriverTel", c.getInput().getInputDriverTel());
				obj.put("goodsCategoryName", c.getGoods().getGoodsCategory()
						.getGoodsCategoryName());
				obj.put("goodsName", c.getGoods().getGoodsName());
				obj.put("goodsStandardName", c.getGoods().getGoodsStandard()
						.getGoodsStandardName());
				obj.put("goodsQualityName", c.getGoods().getGoodsQuality()
						.getGoodsQualityName());
				obj.put("goodsPropertyName", c.getGoods().getGoodsProperty()
						.getGoodsPropertyName());
				obj.put("goodsYieldlyName", c.getGoods().getGoodsYieldly()
						.getGoodsYieldlyName());
				obj.put("iseedShouldWeight", c.getIseedShouldWeight());
				obj.put("inputCreateTime", c.getInput().getInputCreateTime());
				obj.put("status", c.getIseedOrderStatus());
				obj.put("result", "notnull");
				obj.put("pageNow", pageNow2);
				obj.put("pageCount", pageCount);
				// 放入隐藏域中的对象
				obj.put("iseedId", c.getIseedId());

				array.add(obj);
			}
		}

		out.print(array.toString());
		out.flush();
		out.close();

	}

	// 调度员通过ajax的方式查询所有未处理的订单
	public void selectPlanInputAjax(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();

		// 返回行数
		PageRow pageRow = new PageRow();
		// 获得总页数
		int pageCount = inputSeedService.count("计划入库", "准备入库", "", "",
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
			// 如果当前页数大于总页数直接返回
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
		System.out.println(pageNow2);
		// 获得数据，保存子订单的信息
		List<InputSeed> inputSeedlist = inputSeedService.getInfo("计划入库",
				"准备入库", "", "", pageNow2, pageRow.getRow());
		// 用JSONObject保存班组
		JSONArray array = new JSONArray();
		for (InputSeed c : inputSeedlist) {
			JSONObject obj = new JSONObject();
			obj.put("zongId", c.getInput().getInputId());
			obj.put("clientLoginName", c.getInput().getClient()
					.getClientAbbreviation());
			obj.put("inputClientNumber", c.getInput().getInputClientNumber());
			obj.put("inputDefinedOne", c.getInput().getInputDefinedOne());
			obj.put("inputCarryType", c.getInput().getInputCarryType());
			obj.put("inputPlateNumber", c.getInput().getInputPlateNumber());
			obj.put("inputDriverName", c.getInput().getInputDriverName());
			obj.put("inputDriverTel", c.getInput().getInputDriverTel());
			obj.put("goodsCategoryName", c.getGoods().getGoodsCategory()
					.getGoodsCategoryName());
			obj.put("goodsName", c.getGoods().getGoodsName());
			obj.put("goodsStandardName", c.getGoods().getGoodsStandard()
					.getGoodsStandardName());
			obj.put("goodsQualityName", c.getGoods().getGoodsQuality()
					.getGoodsQualityName());
			obj.put("goodsPropertyName", c.getGoods().getGoodsProperty()
					.getGoodsPropertyName());
			obj.put("goodsYieldlyName", c.getGoods().getGoodsYieldly()
					.getGoodsYieldlyName());
			obj.put("iseedShouldWeight", c.getIseedShouldWeight());
			obj.put("inputCreateTime", c.getInput().getInputCreateTime());
			obj.put("status", c.getIseedOrderStatus());
			// 放入隐藏域中的对象
			obj.put("iseedId", c.getIseedId());

			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
	}

	// 调度员查询所有未处理的订单
	public ActionForward selectPlanInput(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 返回行数
		PageRow pageRow = new PageRow();
		// 获得总页数
		int pageCount = inputSeedService.count("计划入库", "准备入库", "", "",
				pageRow.getRow());
		request.setAttribute("pageCount", pageCount);
		// 获得所有客户
		String s_pageNows = request.getParameter("pageNow");

		int pageNow2 = 1;
		// 判断用户输入的当前页是否为空
		if (s_pageNows != null) {
			pageNow2 = fenye(s_pageNows, pageCount);
		}

		request.setAttribute("pageNow2", pageNow2);

		// 获得数据，保存子订单的信息
		List<InputSeed> inputSeedlist = inputSeedService.getInfo("计划入库",
				"准备入库", "", "", pageNow2, pageRow.getRow());
		request.setAttribute("inputSeedlist", inputSeedlist);
		// 点击处理转到调度员处理页面
		return mapping.findForward("selectPlanInput");
	}

	// 查询出库时的执行入库的操作员
	public void selectBaoguanAjax(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();

		// 发起挪库之前查询挪库的执行人，通过分配的权限进行查询
		try {
			JSONArray array = new JSONArray();
			// 首先通过功能名称查询到功能的id
			List<Functions> flist = this.functionsService
					.getFunctionsName("执行入库");
			if (flist.size() > 0) {
				// 再通过功能的编号查询到权限
				List<Powers> plist = this.powersService.getGongneng(flist
						.get(0).getFunctionId());
				for (int i = 0; i < plist.size(); i++) {
					// 通过职责进行查询内部的人员
					List<InteriorUser> iuList = this.interiorUserService
							.getZhize(plist.get(i).getInteriorUserDuty()
									.getInteriorUserDutyId());
					for (int j = 0; j < iuList.size(); j++) {
						if (iuList.get(j).getIuserOnline() == 0) {
							JSONObject obj = new JSONObject();
							obj.put("id", iuList.get(j).getIuserId());// 内部人员的编号
							obj.put("name", iuList.get(j).getIuserName());// 内部人员的名字
							obj.put("result", "notnull");
							array.add(obj);
						}
					}
				}
				out.print(array.toString());
				out.flush();
				out.close();
			} else {
				JSONObject obj = new JSONObject();
				obj.put("result", "null");
				array.add(obj);
				out.print(array.toString());
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	// ajax查询库位
	public void selectKuweiAjax(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		Integer kuqu = Integer
				.parseInt(request.getParameter("kuqu").toString());
		// 得到库位
		List<Tarehouse> listTarehouse = tarehouseService.getKufang(kuqu);
		JSONArray array = new JSONArray();
		if (listTarehouse.size() <= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
		} else {
			for (Tarehouse c : listTarehouse) {
				JSONObject obj = new JSONObject();
				obj.put("id", c.getTarehouseId());
				obj.put("name", c.getTarehouseName());
				obj.put("result", "notnull");
				array.add(obj);
			}
		}
		out.print(array.toString());
		out.flush();
		out.close();

	}

	// 调度员处理计划入库的订单
	public ActionForward chuLiPlanInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 得到计划入库的总订单id
		String id = request.getParameter("id");
		// 得到计划入库的子订单id
		String seedid = request.getParameter("seeDid");
		// 保存总订单id
		request.getSession().setAttribute("id", id);
		// 保存子订单id
		request.getSession().setAttribute("seedid", seedid);
		// 根据id查询该订单信息
		Input input = inputService.getInputId(id);
		// 根据id查询该订单子订单信息
		InputSeed inputseed = inputSeedService.getInputSeedId(seedid);
		// 保存总订单
		request.setAttribute("input", input);
		// 保存子订单
		request.setAttribute("inputseed", inputseed);

		// 得到在线并且不再作业中的人员，不全是保管员，可以在前台用if判断,内部人员调用职责表外键打点调用职责表中名称是否==“保管员”
		// 如果是则循环输出
		List<InteriorUser> listInteriorUser = interiorUserService.getNotZuoye();
		request.setAttribute("listInteriorUser", listInteriorUser);
		// 得到库位
		List<Tarehouse> listTarehouse = tarehouseService.getAll();
		request.setAttribute("listTarehouse", listTarehouse);
		// 转到分配保管员界面
		return null;
	}

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

	// 客户查询自己的订单
	public ActionForward selectClientInput(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 返回行数
		PageRow pageRow = new PageRow();
		// 获得总页数
		int pageCount = inputService.getPageCount(pageRow.getRow());
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
		List<InputSeed> inputSeedList = inputService.getInputByPage2(pageNow2,
				pageRow.getRow());
		request.setAttribute("inputSeedList", inputSeedList);
		return null;
	}

	// 点击查看详细订单
	public ActionForward selectClientInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String inputSeedId = request.getParameter("seedId");
		InputSeed inputSeed = inputSeedService.getInputSeedId(inputSeedId);
		request.setAttribute("inputSeed", inputSeed);
		// 转到查看详情页面
		return null;
	}

	// 进行修改订单的发起时间
	public ActionForward UpdateTime(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 获得相应的入库form
		InputForm inf = (InputForm) form;
		String mess = "";
		PrintWriter out = response.getWriter();
		if (inf != null) {
			// 通过编号进行查询
			Input input = this.inputService.getInputId(inf.getInputId());// 通过编号进行查询
			if (input != null) {
				// 进行修改订单的发起时间
				input.setInputCreateTime(inf.getInputCreateTime());
				boolean ok = this.inputService.update(input);// 进行修改
				if (ok) {
					// 记录到日志中
					this.loginLogService.updateLogin(request.getSession()
							.getAttribute("loginlogId").toString(),
							"修改订单发起时间,订单编号：" + input.getInputId());
					mess = "修改成功！";
				} else {
					mess = "修改失败！";
				}
			} else {
				mess = "修改失败！";
			}
		} else {
			mess = "修改失败！";
		}
		// 返回到对应的界面
		out.print("<script>alert('" + mess
				+ "');window.history.go(-1);</script>");
		out.flush();
		out.close();
		return null;
	}

}
