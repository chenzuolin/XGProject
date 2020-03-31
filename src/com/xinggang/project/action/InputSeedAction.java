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
import com.xinggang.project.entity.InputOperate;
import com.xinggang.project.entity.InputSeed;
import com.xinggang.project.form.InputSeedForm;
import com.xinggang.project.service.ClientService;
import com.xinggang.project.service.CostTypeService;
import com.xinggang.project.service.GoodsService;
import com.xinggang.project.service.InputOperateService;
import com.xinggang.project.service.InputSeedService;
import com.xinggang.project.service.InputService;
import com.xinggang.project.service.LoginLogService;
import com.xinggang.project.service.PaymentFashionService;
import com.xinggang.project.tools.PageRow;
import com.xinggang.project.tools.PresentTime;

/**
 * 入库子订单类action
 * 
 * @author Administrator
 * 
 */
public class InputSeedAction extends DispatchAction {
	// 入库子订单service
	private InputSeedService inputSeedService;
	// 入库总订单service
	private InputService inputService;
	// 货物service
	@SuppressWarnings("unused")
	private GoodsService goodsService;
	// 支付方式Service
	@SuppressWarnings("unused")
	private PaymentFashionService paymentFashionService;
	// 客户service
	private ClientService clientService;
	// 登录日志表service
	private LoginLogService loginLogService;
	// 入库操作订单表
	private InputOperateService inputOperateService;
	// 费用类型
	private CostTypeService costTypeService;
	// 时间工具类
	private PresentTime pt = new PresentTime();

	public void setCostTypeService(CostTypeService costTypeService) {
		this.costTypeService = costTypeService;
	}

	public void setInputOperateService(InputOperateService inputOperateService) {
		this.inputOperateService = inputOperateService;
	}

	public void setLoginLogService(LoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public void setPaymentFashionService(
			PaymentFashionService paymentFashionService) {
		this.paymentFashionService = paymentFashionService;
	}

	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}

	public void setInputService(InputService inputService) {
		this.inputService = inputService;
	}

	public void setInputSeedService(InputSeedService inputSeedService) {
		this.inputSeedService = inputSeedService;
	}

	DecimalFormat ndf = new DecimalFormat("###############0.00");

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

	// app客户查询所有未作废订单并进行分页
	public void getDingdanApp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		InputSeedForm isf = (InputSeedForm) form;
		if (isf.getBegin() == null) {
			isf.setBegin("");// 起始的日期
		}
		if (isf.getFinish() == null || isf.getFinish().equals("")) {
			isf.setFinish(pt.getTimes());// 结束的日期
		}

		// 定义输出流对象
		PrintWriter out = response.getWriter();
		// 返回行数
		PageRow pageRow = new PageRow();

		// 获取页面传入的值
		String s_pageNows = request.getParameter("pageNow");
		// 获取客户id
		int clientId = Integer.parseInt(request.getSession().getAttribute("clientId").toString());
		// 获得总页数
		int pageCount = inputSeedService.getRukuCountAll(clientId,isf.getBegin(),isf.getFinish(), pageRow.getRow());
		// 保存总页数
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

		List<InputSeed> inputSeedList = inputSeedService.getRukuInfoAll(clientId,isf.getBegin(),isf.getFinish(), pageNow2, pageRow.getRow());

		JSONArray array = new JSONArray();
		for (InputSeed c : inputSeedList) {
			JSONObject obj = new JSONObject();

			obj.put("inputClientNumber", c.getInput().getInputClientNumber());// 客户单号
			obj.put("goodsName", c.getGoods().getGoodsName());// 货物名称
			obj.put("goodsStandardName", c.getGoods()// 货物规格
					.getGoodsStandard().getGoodsStandardName());
			obj.put("goodsYieldlyName", c.getGoods()// 货物产地
					.getGoodsYieldly().getGoodsYieldlyName());
			obj.put("inputCarryType", c.getInput().getInputCarryType());// 运输方式
			obj.put("goodsCategoryName", c.getGoods().getGoodsCategory()
					.getGoodsCategoryName());// 货物品类
			obj.put("goodsQualityName", c.getGoods().getGoodsQuality()
					.getGoodsQualityName());// 货物材质
			obj.put("inputDriverTel", c.getInput().getInputDriverTel());// 联系方式
			// 将子订单id放入隐藏域中的值
			obj.put("inputSeedId", c.getIseedId());
			obj.put("zongId", c.getInput().getInputId());

			// 保存页数
			obj.put("pageCount", pageCount);
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();

	}

	// app客户有条件查询所有未作废订单
	public ActionForward getDingdanAppTj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//输出
		PrintWriter out = response.getWriter();
		// 返回行数
		int pageRow = 10;
		if(request.getParameter("pageRow")!=null){
			pageRow = Integer.parseInt(request.getParameter("pageRow").toString());
		}
		
		// 获取页面传入的值
		String startTime = request.getParameter("startTime");//起始时间
		String endTime = request.getParameter("endTime");//结束时间
		String kehuDanhao = request.getParameter("kehuDanhao");//客户单号
		String goodsName = request.getParameter("goodsName");//货物名称
		String s_pageNows = request.getParameter("pageNow");//显示行数

		int clientId = Integer.parseInt(request.getSession().getAttribute("clientId").toString());//获得对应的登录的客户的编号
		
		// 获得总页数
		int pageCount = inputSeedService.getRukuCount(clientId, "", startTime, endTime, kehuDanhao, goodsName, pageRow);
		// 保存总页数
		request.setAttribute("pageCount", pageCount);

		int pageNow2 = 1;
		// 获取用户输入页数
		// 当前页
		if (s_pageNows != null) {
			pageNow2 = fenye(s_pageNows, pageCount);
		}

		List<InputSeed> inputSeedList = inputSeedService.getRukuInfo(clientId, "", startTime, endTime, kehuDanhao, goodsName, pageNow2, pageRow);
		JSONArray array = new JSONArray();
		// 判断，如果总页数为0，即：没有数据，不让访问数据库，直接return
		if (inputSeedList.size() <= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		for (InputSeed c : inputSeedList) {
			JSONObject obj = new JSONObject();
			obj.put("id", c.getInput().getInputId());//保存总订单编号
			obj.put("zid", c.getIseedId());//保存子订单编号
			obj.put("huozhu", c.getInput().getClient().getClientAbbreviation());//保存货主
			obj.put("yunshu", c.getInput().getInputCarryType());// 运输方式
			obj.put("huowu", c.getGoods().getGoodsName());//货物名称
			obj.put("kehudanhao", c.getInput().getInputClientNumber());//客户单号
			obj.put("faqishijian", c.getInput().getInputCreateTime());//发起时间
			obj.put("zhongliang", c.getIseedShouldWeight());//应收重量
			obj.put("shichuweight", c.getIseedRealityWeight());//实收重量
			obj.put("zhuangtai", c.getIseedOrderStatus());//订单状态
			obj.put("result", "notnull");
			
			obj.put("inputClientNumber", c.getInput().getInputClientNumber());// 客户单号
			obj.put("goodsName", c.getGoods().getGoodsName());// 货物名称
			obj.put("goodsStandardName", c.getGoods().getGoodsStandard().getGoodsStandardName());
			obj.put("goodsYieldlyName", c.getGoods().getGoodsYieldly().getGoodsYieldlyName());
			obj.put("inputCarryType", c.getInput().getInputCarryType());// 运输方式
			obj.put("goodsCategoryName", c.getGoods().getGoodsCategory().getGoodsCategoryName());// 货物品类
			obj.put("goodsQualityName", c.getGoods().getGoodsQuality().getGoodsQualityName());// 货物材质
			obj.put("inputDriverTel", c.getInput().getInputDriverTel());// 联系方式
			// 将子订单id放入隐藏域中的值
			obj.put("inputSeedId", c.getIseedId());
			obj.put("zongId", c.getInput().getInputId());
			// 保存页数
			obj.put("pageCount", pageCount);
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
		return null;
	}

	// app客户查询获取该订单id的订单
	public ActionForward getAllInputApp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 获取前台传入子订单id
		String inputSeedId = new String(request.getParameter("ziId").getBytes(
				"ISO-8859-1"), "utf-8");
		// 根据子订单得到类
		InputSeed inputSeed = inputSeedService.getInputSeedId(inputSeedId);
		// 根据子订单id查询出入库操作订单并得到最后操作时间
		List<InputOperate> listInputOperate = inputOperateService
				.getInputSeedId(inputSeed.getIseedId());
		String finishTime = null;
		if (listInputOperate.size() > 0) {
			finishTime = listInputOperate.get(0).getIoperateCollectTime();
		}
		// 保存最后操作时间
		request.setAttribute("finishTime", finishTime);
		// 保存子订单类
		request.setAttribute("inputSeed", inputSeed);
		// 控制权交给struts-config.xml,然后转移到相应页面
		return mapping.findForward("getAllInputApp");
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
		int pageCount = inputSeedService.getRukuCountAllPash(clientId,
				pageRow.getRow());
		request.setAttribute("pageCount", pageCount);
		System.out.println("pageCount:" + pageCount);

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

		List<InputSeed> inputSeedList = inputSeedService.getRukuInfoAllPash(
				clientId, pageNow2, pageRow.getRow());

		JSONArray array = new JSONArray();
		for (InputSeed c : inputSeedList) {
			JSONObject obj = new JSONObject();

			obj.put("inputClientNumber", c.getInput().getInputClientNumber());// 客户单号
			obj.put("goodsName", c.getGoods().getGoodsName());// 货物名称
			obj.put("goodsStandardName", c.getGoods()// 货物规格
					.getGoodsStandard().getGoodsStandardName());
			obj.put("goodsYieldlyName", c.getGoods()// 货物产地
					.getGoodsYieldly().getGoodsYieldlyName());
			obj.put("inputCarryType", c.getInput().getInputCarryType());// 运输方式
			obj.put("goodsCategoryName", c.getGoods().getGoodsCategory()
					.getGoodsCategoryName());// 货物品类
			obj.put("goodsQualityName", c.getGoods().getGoodsQuality()
					.getGoodsQualityName());// 货物材质
			obj.put("inputDriverTel", c.getInput().getInputDriverTel());// 联系方式
			// 将子订单id放入隐藏域中的值
			obj.put("inputSeedId", c.getIseedId());

			// 保存页数
			obj.put("pageCount", pageCount);
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();

	}

	// app客户有条件查询所有作废订单
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
		int pageCount = inputSeedService.getRukuCountPash(clientId, startTime, endTime, kehuDanhao, goodsName, pageRow);

		request.setAttribute("pageCount", pageCount);

		// 判断，如果总页数为0，即：没有数据，不让访问数据库，直接return

		int pageNow2 = 1;
		// 获取用户输入页数
		// 当前页
		if (s_pageNows != null) {
			pageNow2 = fenye(s_pageNows, pageCount);
		}


		List<InputSeed> inputSeedList = inputSeedService.getRukuInfoPash(clientId, startTime, endTime, kehuDanhao, goodsName, pageNow2, pageRow);

		JSONArray array = new JSONArray();
		if (inputSeedList.size() <= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		for (InputSeed c : inputSeedList) {
			JSONObject obj = new JSONObject();
			obj.put("id", c.getInput().getInputId());//保存总订单编号
			obj.put("zid", c.getIseedId());//保存子订单编号
			obj.put("huozhu", c.getInput().getClient().getClientAbbreviation());//保存货主
			obj.put("yunshu", c.getInput().getInputCarryType());// 运输方式
			obj.put("huowu", c.getGoods().getGoodsName());//货物名称
			obj.put("kehudanhao", c.getInput().getInputClientNumber());//客户单号
			obj.put("faqishijian", c.getInput().getInputCreateTime());//发起时间
			obj.put("zhongliang", c.getIseedShouldWeight());//应收重量
			obj.put("shichuweight", c.getIseedRealityWeight());//实收重量
			obj.put("zhuangtai", c.getIseedOrderStatus());//订单状态
			obj.put("result", "notnull");
			
			obj.put("inputClientNumber", c.getInput().getInputClientNumber());// 客户单号
			obj.put("goodsName", c.getGoods().getGoodsName());// 货物名称
			obj.put("goodsStandardName", c.getGoods().getGoodsStandard().getGoodsStandardName());// 货物规格
			obj.put("goodsYieldlyName", c.getGoods().getGoodsYieldly().getGoodsYieldlyName());// 货物产地
			obj.put("inputCarryType", c.getInput().getInputCarryType());// 运输方式
			obj.put("goodsCategoryName", c.getGoods().getGoodsCategory().getGoodsCategoryName());// 货物品类
			obj.put("goodsQualityName", c.getGoods().getGoodsQuality().getGoodsQualityName());// 货物材质
			obj.put("inputDriverTel", c.getInput().getInputDriverTel());// 联系方式
			// 将子订单id放入隐藏域中的值
			obj.put("inputSeedId", c.getIseedId());
			// 保存页数
			obj.put("pageCount", pageCount);
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
		return null;
	}

	// 客户将订单改为作废订单
	public void goDingdanZuofei(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		boolean ok = false;
		boolean seedOk = false;
		String id = new String(request.getParameter("id").getBytes("ISO-8859-1"), "utf-8");
		InputSeed inputSeed = inputSeedService.getInputSeedId(id);
		System.out.println("dingdan:" + inputSeed.getIseedOrderStatus());
		// 当子订单状态为计划入库时,客户可以修改为订单作废
		if (inputSeed.getIseedOrderStatus().equals("计划入库")) {
			// 修改子订单状态为订单作废
			ok = inputSeedService.updateInputSeed(id, "订单作废");
			// 修改总订单状态为订单作废
			if (ok) {
				seedOk = inputService.updateInputStatus(inputSeed.getInput()
						.getInputId(), "订单作废");
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

	// --------------结算查询

	// app客户查询所有结算订单
	public void getJieSuanAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		// 返回行数
		PageRow pageRow = new PageRow();

		// 获取页面传入的值
		String s_pageNows = request.getParameter("pageNow");

		int clientId = Integer.parseInt(request.getSession()
				.getAttribute("clientId").toString());

		InputSeedForm isf = (InputSeedForm) form;
		if (isf.getBegin() == null) {
			isf.setBegin("");// 起始的日期
		}
		if (isf.getFinish() == null || isf.getFinish().equals("")) {
			isf.setFinish(pt.getTimes());// 结束的日期
		}
		// 获得总页数
		int pageCount = inputSeedService.getRukuCountAll(clientId, isf.getBegin(),isf.getFinish(),pageRow.getRow());
		request.setAttribute("pageCount", pageCount);

		// 判断，如果总页数为0，即：没有数据，不让访问数据库，直接return
		int pageNow2 = 1;
		// 获取用户输入页数
		// 当前页
		if (s_pageNows != null) {
			pageNow2 = fenye(s_pageNows, pageCount);
		}
		request.setAttribute("pageNow2", pageNow2);

		List<InputSeed> inputSeedList = inputSeedService.getRukuInfoAll(
				clientId,isf.getBegin(),isf.getFinish(), pageNow2, pageRow.getRow());

		request.setAttribute("inputSeedList", inputSeedList);

		// 根据客户的id查询客户的折扣
		Client client = clientService.getClientId(clientId);
		// 得到折扣
		Double zhekou = client.getClientAgio();

		// 得到汽运的单价
		Double QYDanjia = costTypeService.getQiYunRuKu() * zhekou;
		// 得到火运的单价
		Double HYDanjia = costTypeService.getHuoYunRuKu() * zhekou;

		JSONArray array = new JSONArray();
		for (InputSeed c : inputSeedList) {
			JSONObject obj = new JSONObject();

			obj.put("zongId", c.getInput().getInputId());// 总订单编号
			obj.put("clientNumber", c.getInput().getInputClientNumber());// 客户单号
			obj.put("carryType", c.getInput().getInputCarryType());// 运输方式
			obj.put("sijiName", c.getInput().getInputDriverName());// 司机姓名
			obj.put("goodsName", c.getGoods().getGoodsName());// 货物名称
			obj.put("jiesuanType", c.getIseedClientAccounts());// 结算方式
			obj.put("realityWeight", c.getIseedRealityWeight());// 货物实际重量
			obj.put("shouldCost", c.getIseedShouldCost());// 应收钱数
			obj.put("realityCost", c.getIseedRealityCost());// 实收钱数
			obj.put("QYDanjia", QYDanjia);// 汽运费用单价
			obj.put("HYDanjia", HYDanjia);// 火运费用单价

			// 放入隐藏域中的值
			obj.put("seedId", c.getIseedId());// 子订单id

			// 保存页数
			obj.put("pageCount", pageCount);

			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();

	}

	// app客户有条件查询所有结算订单
	public void getJieSuanAllTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PrintWriter out = response.getWriter();

		// 返回行数
		int pageRow = 10;
		if (request.getParameter("pageRow") != null) {
			pageRow = Integer.parseInt(request.getParameter("pageRow")
					.toString());
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
		int pageCount = inputSeedService.getRukuCount(clientId, jiesuanType,
				startTime, endTime, kehuDanhao, goodsName, pageRow);

		request.setAttribute("pageCount", pageCount);

		// 判断，如果总页数为0，即：没有数据，不让访问数据库，直接return

		int pageNow2 = 1;
		// 获取用户输入页数
		// 当前页
		if (s_pageNows != null) {
			pageNow2 = fenye(s_pageNows, pageCount);
		}

		request.setAttribute("pageNow2", pageNow2);

		List<InputSeed> inputSeedList = inputSeedService.getRukuInfo(clientId,
				jiesuanType, startTime, endTime, kehuDanhao, goodsName,
				pageNow2, pageRow);

		// 根据客户的id查询客户的折扣
		Client client = clientService.getClientId(clientId);
		// 得到折扣
		Double zhekou = client.getClientAgio();

		// 得到汽运的单价
		Double QYDanjia = costTypeService.getQiYunRuKu() * zhekou;
		// 得到火运的单价
		Double HYDanjia = costTypeService.getHuoYunRuKu() * zhekou;

		JSONArray array = new JSONArray();
		for (InputSeed c : inputSeedList) {
			JSONObject obj = new JSONObject();

			obj.put("zongId", c.getInput().getInputId());// 总订单编号
			obj.put("clientNumber", c.getInput().getInputClientNumber());// 客户单号
			obj.put("carryType", c.getInput().getInputCarryType());// 运输方式
			obj.put("sijiName", c.getInput().getInputDriverName());// 司机姓名
			obj.put("goodsName", c.getGoods().getGoodsName());// 货物名称
			obj.put("jiesuanType", c.getIseedClientAccounts());// 结算方式
			obj.put("realityWeight", c.getIseedRealityWeight());// 货物实际重量
			obj.put("shouldCost", c.getIseedShouldCost());// 应收钱数
			obj.put("realityCost", c.getIseedRealityCost());// 实收钱数
			obj.put("QYDanjia", ndf.format(QYDanjia));// 汽运费用单价
			obj.put("HYDanjia", ndf.format(HYDanjia));// 火运费用单价
			obj.put("yewu", "入库业务");

			// 放入隐藏域中的值
			obj.put("seedId", c.getIseedId());// 子订单id

			// 保存页数
			obj.put("pageCount", pageCount);

			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();

	}

	// app客户查询结算的详情订单
	public ActionForward getAlljiesuanApp(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Double yunshu = 0.0;
		// 得到客户id
		int clientId = Integer.parseInt(request.getSession()
				.getAttribute("clientId").toString());

		// 获取前台传入子订单id
		String inputSeedId = new String(request.getParameter("ziId").getBytes(
				"ISO-8859-1"), "utf-8");
		// 根据子订单得到类
		InputSeed inputSeed = inputSeedService.getInputSeedId(inputSeedId);
		// 根据子订单id查询出入库操作订单并得到最后操作时间
		List<InputOperate> listInputOperate = inputOperateService
				.getInputSeedId(inputSeed.getIseedId());
		String finishTime = null;
		if (listInputOperate.size() > 0) {
			finishTime = listInputOperate.get(0).getIoperateCollectTime();
		}

		// 根据客户的id查询客户的折扣
		Client client = clientService.getClientId(clientId);
		// 得到折扣
		Double zhekou = client.getClientAgio();

		// 得到汽运的单价
		Double QYDanjia = costTypeService.getQiYunRuKu() * zhekou;
		// 得到火运的单价
		Double HYDanjia = costTypeService.getHuoYunRuKu() * zhekou;

		if (inputSeed.getInput().getInputCarryType().equals("汽运")) {
			yunshu = QYDanjia;
		}

		if (inputSeed.getInput().getInputCarryType().equals("火运")) {
			yunshu = HYDanjia;
		}

		// 保存费用单价
		request.setAttribute("yunshu", yunshu);

		// 保存最后操作时间
		request.setAttribute("finishTime", finishTime);
		// 保存子订单类
		request.setAttribute("inputSeed", inputSeed);
		// 控制权交给struts-config.xml,然后转移到相应页面
		return mapping.findForward("getAlljiesuanApp");

	}

	// 查询所有的入库订单
	public ActionForward getRuKuLiShi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/* 设置响应头允许ajax跨域访问 */
		response.setHeader("Access-Control-Allow-Origin", "*");
		/* 星号表示所有的异域请求都可以接受， */
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");
		InputSeedForm isf = (InputSeedForm) form;
		if (isf.getBegin() == null) {
			isf.setBegin("");// 起始的日期
		}
		if (isf.getFinish() == null || isf.getFinish().equals("")) {
			isf.setFinish(pt.getTimes());// 结束的日期
		}

		if (isf.getClientName() == null) {
			isf.setClientName("");// 客户的名称，助记符，全拼等
		}
		if (isf.getInput() == null) {
			isf.setInput("");
		}
		if (isf.getClientNumber() == null) {
			isf.setClientNumber("");
		}
		if (isf.getGoodsName() == null) {
			isf.setGoodsName("");
		}
		int pageNow = 1;
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}

		int pageCount = this.inputSeedService.getRuKuLiShiByPageCount(
				isf.getBegin(), isf.getFinish(), isf.getClientName(),
				isf.getInput(), isf.getClientNumber(), isf.getGoodsName(), 30);
		JSONArray array = new JSONArray();
		PrintWriter out = response.getWriter();
		if (pageNow >= pageCount) {
			pageNow = pageCount;
		}
		if (pageNow <= 1) {
			pageNow = 1;
		}

		List<InputSeed> islist = this.inputSeedService.getRuKuLiShiByPage(
				isf.getBegin(), isf.getFinish(), isf.getClientName(),
				isf.getInput(), isf.getClientNumber(), isf.getGoodsName(),
				pageNow, 30);
		if (islist.size() <= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		DecimalFormat df = new DecimalFormat("######0.000");
		if (islist.size() > 0) {
			double ycsum = 0.0;
			double scsum = 0.0;
			for (int i = 0; i < islist.size(); i++) {
				JSONObject obj = new JSONObject();
				obj.put("id", islist.get(i).getInput().getInputId());// 保存入库的总订单的编号
				obj.put("zid", islist.get(i).getIseedId());// 保存入库子订单的编号
				obj.put("huozhu", islist.get(i).getInput().getClient()
						.getClientAbbreviation());// 保存入库货主的简称

				obj.put("yunshu", islist.get(i).getInput().getInputCarryType());// 保存入库的运输的方式
				obj.put("huowu", islist.get(i).getGoods().getGoodsName());// 保存入库货物的名称
				obj.put("kehudanhao", islist.get(i).getInput()
						.getInputClientNumber());// 保存客户单号
				obj.put("zhuangtai", islist.get(i).getIseedOrderStatus());// 保存订单状态
				obj.put("faqishijian", islist.get(i).getInput()
						.getInputCreateTime());// 保存入库的发起时间
				obj.put("zhongliang", islist.get(i).getIseedShouldWeight());// 保存入库库的应手重量
				obj.put("shichuweight",
						islist.get(i).getIseedRealityWeight() == null ? 0
								: islist.get(i).getIseedRealityWeight());// 保存出库的实出的重量
				obj.put("pageNow", pageNow);// 保存当前页
				obj.put("faqiren", islist.get(i).getInput().getInputFaQiRen());//订单发起人
				obj.put("pageCount", pageCount);// 保存总的页数
				obj.put("result", "notnull");
				scsum += (islist.get(i).getIseedRealityWeight() == null ? 0.0
						: islist.get(i).getIseedRealityWeight());// 实出重量相加
				ycsum += islist.get(i).getIseedShouldWeight();// 应出重量相加
				obj.put("ycsum", df.format(ycsum));// 保存应出重量
				obj.put("scsum", df.format(scsum));// 实出重量相加
				array.add(obj);
			}
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		return null;
	}

	// 查询所有的作废入库订单
	public ActionForward getRuKuZuoFei(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		InputSeedForm isf = (InputSeedForm) form;
		if (isf.getBegin() == null) {
			isf.setBegin("");// 起始的日期
		}
		if (isf.getFinish() == null || isf.getFinish().equals("")) {
			isf.setFinish(pt.getTimes());// 结束的日期
		}

		if (isf.getClientName() == null) {
			isf.setClientName("");// 客户的名称，助记符，全拼等
		}
		if (isf.getInput() == null) {
			isf.setInput("");
		}
		if (isf.getClientNumber() == null) {
			isf.setClientNumber("");
		}
		if (isf.getGoodsName() == null) {
			isf.setGoodsName("");
		}
		int pageNow = 1;
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}

		int pageCount = this.inputSeedService.getRuKuZuoFeiByPageCount(
				isf.getBegin(), isf.getFinish(), isf.getClientName(),
				isf.getInput(), isf.getClientNumber(), isf.getGoodsName(), 30);
		JSONArray array = new JSONArray();
		PrintWriter out = response.getWriter();
		if (pageNow >= pageCount) {
			pageNow = pageCount;
		}
		if (pageNow <= 1) {
			pageNow = 1;
		}

		List<InputSeed> islist = this.inputSeedService.getRuKuZuoFeiByPage(
				isf.getBegin() + " 00:00:00", isf.getFinish() + " 23:59:59",
				isf.getClientName(), isf.getInput(), isf.getClientNumber(),
				isf.getGoodsName(), pageNow, 30);

		if (islist.size() <= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}

		if (islist.size() > 0) {
			for (InputSeed is : islist) {
				JSONObject obj = new JSONObject();
				obj.put("id", is.getInput().getInputId());// 保存入库的总订单的编号
				obj.put("zid", is.getIseedId());// 保存入库子订单的编号
				obj.put("huozhu", is.getInput().getClient()
						.getClientAbbreviation());// 保存入库货主的简称

				obj.put("yunshu", is.getInput().getInputCarryType());// 保存入库的运输的方式
				obj.put("huowu", is.getGoods().getGoodsName());// 保存入库货物的名称
				obj.put("kehudanhao", is.getInput().getInputClientNumber());// 保存客户单号
				obj.put("zhuangtai", is.getIseedOrderStatus());// 保存订单状态
				obj.put("faqishijian", is.getInput().getInputCreateTime());// 保存入库的发起时间
				obj.put("zhongliang", is.getIseedShouldWeight());// 保存入库库的应手重量
				obj.put("shichuweight", is.getIseedRealityWeight() == null ? 0
						: is.getIseedRealityWeight());// 保存出库的实出的重量
				obj.put("pageNow", pageNow);// 保存当前页
				obj.put("pageCount", pageCount);// 保存总的页数
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

	// 订单查询中点击查看详细
	public ActionForward getXiangQing(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		InputSeedForm isf = (InputSeedForm) form;
		if (isf != null) {
			InputSeed is = this.inputSeedService.getInputSeedId(URLDecoder
					.decode(isf.getIseedId(), "UTF-8"));
			// 将查询到的类保存到request中
			request.setAttribute("is", is);
			List<InputOperate> iolist = this.inputOperateService
					.getInputSeedId(URLDecoder.decode(isf.getIseedId(), "UTF-8"));
			request.setAttribute("iolist", iolist);
		}

		if (request.getParameter("ff") != null) {
			if (request.getParameter("ff").equals("update")) {
				return mapping.findForward("goUpdate");
			}
			if (request.getParameter("ff").equals("caozuoupdate")) {
				InputOperate io = this.inputOperateService
						.getInputOperateId(URLDecoder.decode(
								request.getParameter("eoid"), "UTF-8"));
				request.getSession().setAttribute("io", io);
				return mapping.findForward("gocaozuoUpdate");
			}
			if (request.getParameter("ff").equals("shoufei")) {
				return mapping.findForward("goShoufei");
			}
			if (request.getParameter("ff").equals("client")) {
				return mapping.findForward("goClient");
			}
		}

		// 返回到对应的页面
		return mapping.findForward("goDDXiangXi");
	}

	// 当调度或者审核点击出库完成的时候一ajax的方式验证出库操作订单的状态是否完成
	public ActionForward RuKuWanChengAjax(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		InputSeedForm isf = (InputSeedForm) form;

		InputSeed is = this.inputSeedService.getInputSeedId(isf.getIseedId());
		// 通过子订单的编号查询操作订单，如果该子订单的下面有操作订单的状态不是未收费或者不是已收费的时候，弹出提醒，是否要出库完成
		List<InputOperate> iolist = this.inputOperateService.getInputSeedId(is
				.getIseedId());// 通过子订单的编号查询入库的操作订单
		// 用遍历的方式查询是否有未完成的操作订单
		JSONArray array = new JSONArray();
		PrintWriter out = response.getWriter();
		int x = 0;
		for (InputOperate io : iolist) {
			if (io.getIoperateDefinedTwo().equals("未收费") == false) {
				if (io.getIoperateDefinedTwo().equals("已收费") == false) {
					x++;
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

	// 当调度或者审核点击出库完成的时候一ajax的方式验证出库操作订单的状态是否完成
	public ActionForward RuKuWanCheng(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		InputSeedForm isf = (InputSeedForm) form;
		String message = "";
		if (isf != null) {
			InputSeed is = this.inputSeedService.getInputSeedId(isf
					.getIseedId());
			if (is != null) {
				if (is.getIseedClientAccounts() != null) {
					if (is.getIseedClientAccounts().equals("现结")
							|| is.getIseedClientAccounts().equals("日结")) {
						is.setIseedOrderStatus("入库完成");
					} else {
						is.setIseedOrderStatus("未收费");
					}
				} else {
					is.setIseedOrderStatus("未收费");
				}
				boolean ok = this.inputSeedService.update(is);
				if (ok) {
					// 记录到日志中
					this.loginLogService.updateLogin(request.getSession()
							.getAttribute("loginlogId").toString(), "入库完成，订单号："
							+ is.getInput().getInputId());
					message = "提交成功！";
				} else {
					message = "提交失败！";
				}
			} else {
				message = "提交失败！";
			}
		} else {
			message = "提交失败！";
		}

		PrintWriter out = response.getWriter();
		out.print("<script>alert('" + message
				+ "');window.history.go(-2);</script>");
		return null;
	}

	// 通过结算方式查询出库订单
	public ActionForward QueryJieSuan(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		InputSeedForm isf = (InputSeedForm) form;
		if (isf.getBegin() == null || isf.getBegin().equals("")) {
			isf.setBegin(pt.getNowJianYi());
		} else {
			isf.setBegin(isf.getBegin());
		}

		if (isf.getFinish() == null || isf.getFinish().equals("")) {
			isf.setFinish(pt.getTimes());
		} else {
			isf.setFinish(isf.getFinish());
		}
		if (isf.getIseedClientAccounts() == null) {
			isf.setIseedClientAccounts("");
		}
		if (isf.getClientName() == null) {
			isf.setClientName("");
		}
		String shoufeiren = "";
		if (request.getParameter("shoufeiren") != null) {
			shoufeiren = request.getParameter("shoufeiren").toString();
		}
		int pageNow = 1;
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}
		int pageCount = this.inputSeedService.getJieSuanQueryByCount(
				isf.getClientName(), isf.getBegin(), isf.getFinish(),
				isf.getIseedClientAccounts(), 30, shoufeiren);
		if (pageNow >= pageCount) {
			pageNow = pageCount;
		} else if (pageNow <= 1) {
			pageNow = 1;
		}
		List<InputSeed> islist = this.inputSeedService.getJieSuanQueryByPage(
				isf.getClientName(), isf.getBegin(), isf.getFinish(),
				isf.getIseedClientAccounts(), pageNow, 30, shoufeiren);

		JSONArray array = new JSONArray();
		PrintWriter out = response.getWriter();
		if (islist.size() <= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		for (int i = 0; i < islist.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("zongid", islist.get(i).getInput().getInputId());// 保存总编号
			obj.put("zid", islist.get(i).getIseedId());// 保存子订单编号
			obj.put("kehudanhao", islist.get(i).getInput()
					.getInputClientNumber());// 保存客户好
			obj.put("kehu", islist.get(i).getInput().getClient()
					.getClientAbbreviation());// 简称
			obj.put("time", islist.get(i).getInput().getInputCreateTime());// 订单生成日期
			obj.put("goods", islist.get(i).getGoods().getGoodsName());// 货物名称
			obj.put("ycweight", islist.get(i).getIseedShouldWeight());// 应出重量
			obj.put("scweight",
					(islist.get(i).getIseedRealityWeight() == null ? 0.0
							: islist.get(i).getIseedRealityWeight()));// 实出重量
			obj.put("yunshu", islist.get(i).getInput().getInputCarryType());// 运输方式
			obj.put("chukucost",
					(islist.get(i).getIseedShouldCost() == null ? 0.0 : islist
							.get(i).getIseedShouldCost()));// 应收出库费
			obj.put("sscost",
					(islist.get(i).getIseedRealityCost() == null ? 0.0 : islist
							.get(i).getIseedRealityCost()));// 实收出库费
			obj.put("jiesuan", islist.get(i).getIseedClientAccounts());// 结算方式
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

	// app中进行查询某个客户的日结费用
	public ActionForward getAppQueryDayCost(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		InputSeedForm isf = (InputSeedForm) form;
		JSONArray array = new JSONArray();
		PrintWriter out = response.getWriter();
		if (isf.getGoodsName() == null) {
			isf.setGoodsName("");
		}
		if (isf.getClientId() == null) {
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
		int pageCount = this.inputSeedService.getAppQueryDayCostByCount(
				isf.getClientId(), isf.getGoodsName(), pageNow, 15);
		if (pageNow >= pageCount) {
			pageNow = pageCount;
		} else if (pageNow <= 1) {
			pageNow = 1;
		}

		List<InputSeed> islist = this.inputSeedService
				.getAppQueryDayCostByPage(isf.getClientId(),
						isf.getGoodsName(), pageNow, 15);
		if (islist.size() <= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "no");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}

		for (int i = 0; i < islist.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("zongid", islist.get(i).getInput().getInputId());// 保存中订单的编号
			obj.put("kehuid", islist.get(i).getInput().getInputClientNumber());// 保存客户的单号
			obj.put("shouldcost", islist.get(i).getIseedShouldCost());// 保存应收的费用
			obj.put("reladycost", islist.get(i).getIseedRealityCost());// 保存实收的费用
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

		String inputSeedId = request.getParameter("ziId");

		// 根据子订单得到类
		InputSeed inputSeed = inputSeedService.getInputSeedId(inputSeedId);
		// 根据子订单id查询出入库操作订单并得到最后操作时间
		List<InputOperate> listInputOperate = inputOperateService
				.getInputSeedId(inputSeed.getIseedId());
		String finishTime = null;
		if (listInputOperate.size() > 0) {
			finishTime = listInputOperate.get(0).getIoperateCollectTime();
		}

		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		if (inputSeed != null) {

			obj.put("ziDingdanId", inputSeed.getIseedId());
			obj.put("changjianTime", inputSeed.getInput().getInputCreateTime());
			obj.put("kehuName", inputSeed.getInput().getClient()
					.getClientFirmName());
			obj.put("kehuDanhao", inputSeed.getInput().getInputClientNumber());
			obj.put("yunshuType", inputSeed.getInput().getInputCarryType());
			obj.put("cheHao", inputSeed.getInput().getInputPlateNumber());
			obj.put("sijiName", inputSeed.getInput().getInputDriverName());

			obj.put("sijiTel", inputSeed.getInput().getInputDriverTel());
			obj.put("pinlei", inputSeed.getGoods().getGoodsCategory()
					.getGoodsCategoryName());
			obj.put("goodsName", inputSeed.getGoods().getGoodsName());
			obj.put("guige", inputSeed.getGoods().getGoodsStandard()
					.getGoodsStandardName());

			obj.put("caizhi", inputSeed.getGoods().getGoodsQuality()
					.getGoodsQualityName());
			obj.put("shuxin", inputSeed.getGoods().getGoodsProperty()
					.getGoodsPropertyName());
			obj.put("chandi", inputSeed.getGoods().getGoodsYieldly()
					.getGoodsYieldlyName());
			obj.put("shouldWeight", inputSeed.getIseedShouldWeight());

			obj.put("yinShoufeiYong", inputSeed.getIseedShouldCost());
			obj.put("shiShoufeiYong", inputSeed.getIseedRealityCost());

			obj.put("dingdanStatus", inputSeed.getIseedOrderStatus());
			obj.put("finishTime", finishTime);
			obj.put("beizhu", inputSeed.getIseedRemark());

		}

		out.print(obj.toString());
		out.flush();
		out.close();

	}

	// app客户有条件查询所有未作废订单
	public void getDingdanShoujiTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		System.out.println("-----------------就看到kjkdjfk");

		PrintWriter out = response.getWriter();
		// 返回行数
		PageRow pageRow = new PageRow();
		// 获取页面传入的值
		// String startTime = request.getParameter("startTime");
		// String endTime = request.getParameter("endTime");
		String kehuDanhao = request.getParameter("kehuDanhao");
		// String goodsName = request.getParameter("goodsName");
		String s_pageNows = request.getParameter("pageNow");

		System.out.println("单号：" + kehuDanhao);

		int clientId = Integer.parseInt(request.getSession()
				.getAttribute("clientId").toString());

		// 获得总页数
		int pageCount = inputSeedService.getRukuCount(clientId, "", "", "",
				kehuDanhao, "", pageRow.getRow());
		// 保存总页数
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

		List<InputSeed> inputSeedList = inputSeedService.getRukuInfo(clientId,
				"", "", "", kehuDanhao, "", pageNow2, pageRow.getRow());

		System.out.println("size:" + inputSeedList.size());

		JSONArray array = new JSONArray();
		for (InputSeed c : inputSeedList) {
			JSONObject obj = new JSONObject();

			obj.put("inputClientNumber", c.getInput().getInputClientNumber());// 客户单号
			obj.put("goodsName", c.getGoods().getGoodsName());// 货物名称
			obj.put("goodsStandardName", c.getGoods()// 货物规格
					.getGoodsStandard().getGoodsStandardName());
			obj.put("goodsYieldlyName", c.getGoods()// 货物产地
					.getGoodsYieldly().getGoodsYieldlyName());
			obj.put("inputCarryType", c.getInput().getInputCarryType());// 运输方式
			obj.put("goodsCategoryName", c.getGoods().getGoodsCategory()
					.getGoodsCategoryName());// 货物品类
			obj.put("goodsQualityName", c.getGoods().getGoodsQuality()
					.getGoodsQualityName());// 货物材质
			obj.put("inputDriverTel", c.getInput().getInputDriverTel());// 联系方式
			// 将子订单id放入隐藏域中的值
			obj.put("inputSeedId", c.getIseedId());
			obj.put("zongId", c.getInput().getInputId());
			// 保存页数
			obj.put("pageCount", pageCount);

			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();

	}

	// app中进行查询某个客户的日结费用
	public ActionForward getAppTodayTheOrder(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		InputSeedForm isf = (InputSeedForm) form;
		JSONArray array = new JSONArray();
		PrintWriter out = response.getWriter();
		if (isf.getClientId() == null) {
			JSONObject obj = new JSONObject();
			obj.put("result", "nofive");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}

		// 待测试完成之后将此代码将到双引号“”（进行替换双引号）
		String begin = pt.getDatesNianYR() + " 00:00:00";
		List<InputSeed> islist = this.inputSeedService.getAppTodayTheOrder(
				isf.getClientId(), begin, pt.getTimes());

		if (islist.size() <= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "no");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}

		for (int i = 0; i < islist.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("zongid", islist.get(i).getZongId());// 保存中订单的编号
			obj.put("ziid", islist.get(i).getIseedId());// 保存客户的单号
			obj.put("time", islist.get(i).getTime());// 保存实收的费用
			obj.put("result", "notnull");
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
		return null;
	}

}
