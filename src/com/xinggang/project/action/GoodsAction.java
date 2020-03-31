package com.xinggang.project.action;

import java.io.PrintWriter;
import java.net.URLDecoder;
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
import com.xinggang.project.entity.Goods;
import com.xinggang.project.entity.GoodsCategory;
import com.xinggang.project.entity.GoodsProperty;
import com.xinggang.project.entity.GoodsQuality;
import com.xinggang.project.entity.GoodsStandard;
import com.xinggang.project.entity.GoodsUnit;
import com.xinggang.project.entity.GoodsYieldly;
import com.xinggang.project.form.GoodsForm;
import com.xinggang.project.service.ClientService;
import com.xinggang.project.service.GoodsCategoryService;
import com.xinggang.project.service.GoodsPropertyService;
import com.xinggang.project.service.GoodsQualityService;
import com.xinggang.project.service.GoodsService;
import com.xinggang.project.service.GoodsStandardService;
import com.xinggang.project.service.GoodsUnitService;
import com.xinggang.project.service.GoodsYieldlyService;
import com.xinggang.project.service.LoginLogService;
import com.xinggang.project.tools.PageRow;

/**
 * Goods entity. @author MyEclipse Persistence Tools 货物明细表类action
 */
public class GoodsAction extends DispatchAction {

	// 货物明细service
	private GoodsService goodsService;
	// 货物产地service
	private GoodsYieldlyService goodsYieldlyService;
	// 货物的品类service
	private GoodsCategoryService goodsCategoryService;
	// 登录日志表service
	private LoginLogService loginLogService;
	// 货物属性service
	private GoodsPropertyService goodsPropertyService;
	// 货物材质service
	private GoodsQualityService goodsQualityService;
	// 货物规格service
	private GoodsStandardService goodsStandardService;
	// 货物计量单位service
	private GoodsUnitService goodsUnitService;
	// 客户service
	private ClientService clientService;

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
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

	public void setGoodsUnitService(GoodsUnitService goodsUnitService) {
		this.goodsUnitService = goodsUnitService;
	}

	public void setLoginLogService(LoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}

	public void setGoodsCategoryService(
			GoodsCategoryService goodsCategoryService) {
		this.goodsCategoryService = goodsCategoryService;
	}

	public void setGoodsYieldlyService(GoodsYieldlyService goodsYieldlyService) {
		this.goodsYieldlyService = goodsYieldlyService;
	}

	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}

	// 转到添加货物页面
	public ActionForward goAddGoods(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 查询出货物品类
		List<GoodsCategory> goodsCategory = goodsCategoryService.getAll();
		// 查询出货物产地
		List<GoodsYieldly> goodsYieldly = goodsYieldlyService.getAll();
		// 货物属性
		List<GoodsProperty> goodsProperty = goodsPropertyService.getAll();
		// 货物材质
		List<GoodsQuality> goodsQuality = goodsQualityService.getAll();
		// 货物规格
		List<GoodsStandard> goodsStandard = goodsStandardService.getAll();
		// 货物计量单位
		List<GoodsUnit> goodsUnit = goodsUnitService.getAll();

		// 将货物品类和货物产地保存到request中
		request.setAttribute("goodsCategory", goodsCategory);
		request.setAttribute("goodsYieldly", goodsYieldly);
		request.setAttribute("goodsProperty", goodsProperty);
		request.setAttribute("goodsQuality", goodsQuality);
		request.setAttribute("goodsStandard", goodsStandard);
		request.setAttribute("goodsUnit", goodsUnit);

		return mapping.findForward("goAddGoods");
	}

	// ajax返回json品类
	public void selectGoodsCategory(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		System.out.println("sdkfsklfls");
		
		PrintWriter out = response.getWriter();
		// 查询出货物品类
		List<GoodsCategory> goodsCategory = goodsCategoryService.getAll();
		// 查询出货物产地
		JSONArray array = new JSONArray();
		// 遍历集合中元素，然后转换为JSON格式
		for (GoodsCategory c : goodsCategory) {
			JSONObject obj = new JSONObject();
			obj.put("id", c.getGoodsCategoryId());
			obj.put("name", c.getGoodsCategoryName());
			array.add(obj);
		}
		// 将数据写入到前台
		out.print(array.toString());
		out.flush();
		out.close();
	}

	// ajax当货物品类选定后改变货物的名称等
	public void selectGoods(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		// 根据货物品类id查询货物，进行过滤
		List<Goods> listGoodsName = goodsService.getAllGoods();
		JSONArray array = new JSONArray();
		if (listGoodsName != null) {
			for (Goods c : listGoodsName) {
				JSONObject obj = new JSONObject();
				obj.put("id", c.getGoodsId());
				obj.put("goodsName", c.getGoodsName());
				array.add(obj);
			}
		}

		out.print(array.toString());
		out.flush();
		out.close();

	}
	
	
	public void selectGoodsName(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		System.out.println("huowu------------");
		
		PrintWriter out = response.getWriter();
		// 根据货物品类id查询货物，进行过滤
		List<Goods> listGoodsName = goodsService.getAllGoods();
		
		List<Goods> listG=listGoodsName;
		
		for(int i=0; i<listGoodsName.size(); i++){
			 for(int j=0; j<listG.size(); j++){
				 if(listGoodsName.get(i).getGoodsName().equals(listG.get(j).getGoodsName()) && i!=j){
					 listGoodsName.get(i).setGoodsName("");
				 }
			 }
		 }
		
		JSONArray array = new JSONArray();
		if (listGoodsName != null) {
			for (Goods c : listGoodsName) {
				if(c.getGoodsName()!=""){
					JSONObject obj = new JSONObject();
					obj.put("id", c.getGoodsId());
					obj.put("goodsName", c.getGoodsName());
					array.add(obj);
				}
				
			}
		}

		out.print(array.toString());
		out.flush();
		out.close();

	}

	// ajax返回json规格
	public void selectGoodsStandard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		// 货物规格
		List<GoodsStandard> goodsStandard = goodsStandardService.getAll();
		JSONArray array = new JSONArray();
		for (GoodsStandard c : goodsStandard) {
			JSONObject obj = new JSONObject();
			obj.put("id", c.getGoodsStandardId());
			obj.put("name", c.getGoodsStandardName());
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
	}

	// ajax返回json材质
	public void selectGoodsQuality(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		// 货物材质
		List<GoodsQuality> goodsQuality = goodsQualityService.getAll();
		JSONArray array = new JSONArray();
		for (GoodsQuality c : goodsQuality) {
			JSONObject obj = new JSONObject();
			obj.put("id", c.getGoodsQualityId());
			obj.put("name", c.getGoodsQualityName());
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
	}

	// ajax返回json属性
	public void selectGoodsProperty(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();

		// 货物属性
		List<GoodsProperty> goodsProperty = goodsPropertyService.getAll();
		JSONArray array = new JSONArray();
		for (GoodsProperty c : goodsProperty) {
			JSONObject obj = new JSONObject();
			obj.put("id", c.getGoodsPropertyId());
			obj.put("name", c.getGoodsPropertyName());
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
	}

	// ajax返回json产地
	public void selectGoodsYieldly(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		// 查询出货物品类
		List<GoodsYieldly> goodsYieldly = goodsYieldlyService.getAll();
		JSONArray array = new JSONArray();
		for (GoodsYieldly c : goodsYieldly) {
			JSONObject obj = new JSONObject();
			obj.put("id", c.getGoodsYieldlyId());
			obj.put("name", c.getGoodsYieldlyName());
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
	}

	// ajax返回json计件单位
	public void selectGoodsUnit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		// 查询出货物品类
		List<GoodsUnit> goodsUnit = goodsUnitService.getAll();
		JSONArray array = new JSONArray();
		for (GoodsUnit c : goodsUnit) {
			JSONObject obj = new JSONObject();
			obj.put("id", c.getGoodsUnitId());
			obj.put("name", c.getGoodsUnitName());
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
	}

	// 添加货物
	public void addGoods(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		GoodsForm goodsForm = (GoodsForm) form;
		boolean ok = goodsService.addGoods(goodsForm);
		if (ok) {
			System.out.println("成功！");
			// 获得登录日志编号
			String loginId = (String) request.getSession().getAttribute(
					"loginId");
			String loginName = (String) request.getSession().getAttribute(
					"loginName");
			// 到登录日志中修改信息
			loginLogService.updateLogin(loginId, loginName + "添加货物");
			out.print("<script>alert('添加成功！');window.location.href='goods.do?flag=goSelectGoods';</script>");
		} else {
			out.print("<script>alert('添加失败！');window.location.href='goods.do?flag=goSelectGoods';</script>");
		}

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

		return pageNow;
	}

	// 分页查询货物
	public ActionForward goSelectGoods(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String goods = "";
		if (request.getParameter("goodsNme") != null) {
			goods = URLDecoder.decode(request.getParameter("goodsNme"), "UTF-8");
		}
		// 返回行数
		int pageRow=100;
		if(request.getParameter("pageRow")!=null){
			pageRow=Integer.parseInt(request.getParameter("pageRow").toString());
		}
		// 计算总共分几页，传入一页中要显示的记录数5
		int pageCount = goodsService.getPageCount(goods, pageRow);
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
		request.setAttribute("goodsName", goods);
		// 传入每页需要的记录数5和当前的页数，得到分页后的数据返回给页面
		List<Goods> goodslist = goodsService.getGoodsByPage2(goods, pageNow2,
				pageRow);
		request.setAttribute("goodslist", goodslist);
		PrintWriter out=response.getWriter();
		JSONArray array=new JSONArray();
		if(goodslist.size()<=0){
			JSONObject obj=new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		for(int i=0; i<goodslist.size();i++){
			JSONObject obj=new JSONObject();
			obj.put("result", "notnull");
			obj.put("id", goodslist.get(i).getGoodsId());
			obj.put("cargoName", goodslist.get(i).getGoodsName());
			obj.put("sign", goodslist.get(i).getGoodsSign());
			obj.put("pinlei", goodslist.get(i).getGoodsCategory().getGoodsCategoryName());
			obj.put("guige", goodslist.get(i).getGoodsStandard().getGoodsStandardName());
			obj.put("caizhi", goodslist.get(i).getGoodsQuality().getGoodsQualityName());
			obj.put("shuxing", goodslist.get(i).getGoodsProperty().getGoodsPropertyName());
			obj.put("chandi", goodslist.get(i).getGoodsYieldly().getGoodsYieldlyName());
			obj.put("danwei", goodslist.get(i).getGoodsUnit()==null?'无':goodslist.get(i).getGoodsUnit().getGoodsUnitName());
			obj.put("lilunzhongliang", goodslist.get(i).getGoodsAdjustment()==null?0.0:goodslist.get(i).getGoodsAdjustment());
			obj.put("remark", goodslist.get(i).getGoodsRemark());
			obj.put("pageNow",pageNow2);
			obj.put("pageCount", pageCount);
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
		return null;

	}

	// 带条件分页查询货物
	public ActionForward goSelectGoodsTwo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		GoodsForm goodsForm = (GoodsForm) form;
		PageRow pageRow = new PageRow();

		// 计算总共分几页，传入一页中要显示的记录数16
		int pageCount = goodsService.getPageCountTwo(goodsForm.getGoodsName(),
				goodsForm.getGoodsSign(), goodsForm.getGoodsStandardId(),
				goodsForm.getGoodsQualityId(), goodsForm.getGoodsPropertyId(),
				pageRow.getClientRow());
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
		// 传入每页需要的记录数5和当前的页数，得到分页后的数据返回给页面
		List<Goods> goodslist = goodsService.getGoodsByPage(
				goodsForm.getGoodsName(), goodsForm.getGoodsSign(),
				goodsForm.getGoodsStandardId(), goodsForm.getGoodsQualityId(),
				goodsForm.getGoodsPropertyId(), pageNow2,
				pageRow.getClientRow());

		request.setAttribute("goodslistTwo", goodslist);
		// 保存需要分的页数
		request.setAttribute("pageCountTwo", pageCount);
		return mapping.findForward("goSelectGoods");
	}

	// 修改货物信息
	public void updateGoods(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		GoodsForm goodsForm = (GoodsForm) form;
		boolean ok = goodsService.updateGoods(goodsForm);
		if (ok) {
			// 获得登录日志编号
			String loginId = (String) request.getSession().getAttribute(
					"loginId");
			String loginName = (String) request.getSession().getAttribute(
					"loginName");
			// 到登录日志中修改信息
			loginLogService.updateLogin(loginId, loginName + "修改货物");
			out.print("<script>alert('修改成功！');window.location.href='goods.do?flag=goSelectGoods';</script>");
		} else {
			out.print("<script>alert('修改失败！');window.location.href='goods.do?flag=goSelectGoods';</script>");
		}

	}

	// 停用货物
	public void stopGoods(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));
		Goods goods = goodsService.getGoodsId(id);
		goods.setGoodsDefinedOne("0");
		boolean ok = goodsService.update(goods);
		if (ok) {
			// 获得登录日志编号
			String loginId = (String) request.getSession().getAttribute(
					"loginId");
			String loginName = (String) request.getSession().getAttribute(
					"loginName");
			// 到登录日志中修改信息
			loginLogService.updateLogin(loginId, loginName + "修改货物");
			out.print("<script>alert('停用成功！');window.location.href='goods.do?flag=goSelectGoods';</script>");

		} else {
			out.print("<script>alert('停用失败！');window.location.href='goods.do?flag=goSelectGoods';</script>");
		}
	}

	public void selectGoodsPinlei(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		System.out.println("kkkkkjdsklf");

		PrintWriter out = response.getWriter();
		// 手机端登录，查询客户id
		int clientId = Integer.parseInt(request.getSession()
				.getAttribute("clientId").toString());
		request.setAttribute("clientId", clientId);
		// 查询客户信息
		List<Client> listClient = clientService.getAll();
		request.setAttribute("listClient", listClient);
		// 查询货物品类
		List<GoodsCategory> goodsPinlei = goodsCategoryService.getAll();
		JSONArray array = new JSONArray();
		if (goodsPinlei != null) {
			for (GoodsCategory c : goodsPinlei) {
				JSONObject obj = new JSONObject();
				obj.put("id", c.getGoodsCategoryId());
				obj.put("categoryName", c.getGoodsCategoryName());
				array.add(obj);
			}
		}

		out.print(array.toString());
		out.flush();
		out.close();

	}

	// 手机app ajax当货物品类选定后改变货物的名称等
	public void selectGoodsAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		// 手机端登录，查询客户id
		int clientId = Integer.parseInt(request.getSession()
				.getAttribute("clientId").toString());
		request.setAttribute("clientId", clientId);
		Client client = clientService.getClientId(clientId);
		request.getSession().setAttribute("danwei", client.getClientFirmName());

		// 根据货物品类id查询货物，进行过滤
		List<Goods> listGoodsName = goodsService.getAllGoods();
		JSONArray array = new JSONArray();
		if (listGoodsName != null) {
			for (Goods c : listGoodsName) {
				JSONObject obj = new JSONObject();
				obj.put("goodid", c.getGoodsId());
				obj.put("goodName", c.getGoodsName());
				obj.put("goodPinleiId", c.getGoodsCategory()
						.getGoodsCategoryId());
				obj.put("goodPinleiName", c.getGoodsCategory()
						.getGoodsCategoryName());
				obj.put("goodGuigeId", c.getGoodsStandard()
						.getGoodsStandardId());
				obj.put("goodGuigeName", c.getGoodsStandard()
						.getGoodsStandardName());
				obj.put("goodShuxinId", c.getGoodsProperty()
						.getGoodsPropertyId());
				obj.put("goodShuxinName", c.getGoodsProperty()
						.getGoodsPropertyName());
				obj.put("goodCaizhiId", c.getGoodsQuality().getGoodsQualityId());
				obj.put("goodCaizhiName", c.getGoodsQuality()
						.getGoodsQualityName());
				obj.put("goodChandiId", c.getGoodsYieldly().getGoodsYieldlyId());
				obj.put("goodChandiName", c.getGoodsYieldly()
						.getGoodsYieldlyName());

				array.add(obj);
			}
		}

		out.print(array.toString());
		out.flush();
		out.close();

	}

	// 手机app ajax当货物品类选定后改变货物的名称等
	public ActionForward getAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<Goods> glist = this.goodsService.getAllGoods();
		request.setAttribute("glist", glist);
		return mapping.findForward("goDaochu");
	}

	// ajax当货物品类选定后改变货物的名称等
	public void getAppGoods(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		// 根据货物品类id查询货物，进行过滤
		List<Goods> listGoodsName = goodsService.getAllGoods();
		List<Goods> list = listGoodsName;
		for (int i = 0; i < listGoodsName.size(); i++) {
			for (int j = 0; j < list.size(); j++) {
				if (listGoodsName.get(i).getGoodsName() == null) {
					listGoodsName.remove(i);
					i--;
					break;
				}
				if (listGoodsName.get(i).getGoodsName()
						.equals(list.get(j).getGoodsName())
						&& i != j) {
					listGoodsName.remove(i);
					i--;
					break;
				}
			}
		}
		JSONArray array = new JSONArray();
		if (listGoodsName != null) {
			for (Goods c : listGoodsName) {
				JSONObject obj = new JSONObject();
				obj.put("id", c.getGoodsId());
				obj.put("goodsName", c.getGoodsName());
				array.add(obj);
			}
		}

		out.print(array.toString());
		out.flush();
		out.close();

	}

}
