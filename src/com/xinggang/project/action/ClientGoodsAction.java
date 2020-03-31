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

import com.xinggang.project.entity.ClientGoods;
import com.xinggang.project.form.ClientGoodsForm;
import com.xinggang.project.service.ClientGoodsService;
import com.xinggang.project.service.LoginLogService;
import com.xinggang.project.tools.PageRow;

/**
 * 客户货物库存类action
 * 
 * @author Administrator
 * 
 */

public class ClientGoodsAction extends DispatchAction {
	// 客户库存service
	private ClientGoodsService clientGoodsService;
	// 登录日志service
	private LoginLogService loginLogService;

	// 分页行数

	public void setClientGoodsService(ClientGoodsService clientGoodsService) {
		this.clientGoodsService = clientGoodsService;
	}

	public void setLoginLogService(LoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return super.execute(mapping, form, request, response);
	}

	// 冻结该客户的货物库存
	public ActionForward DongJieClientGoods(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ClientGoodsForm cgf = (ClientGoodsForm) form;
		boolean ok = this.clientGoodsService.DongJieClientGoods(cgf);
		if (ok) {
			this.loginLogService.updateLogin(
					request.getSession().getAttribute("loginlogId").toString(),
					"冻结客户库存" + cgf.getCgoodsId());
			request.getSession().setAttribute("dongjieMessage", "冻结成功！");
		} else {
			request.getSession().setAttribute("dongjieMessage", "冻结失败！");
		}
		PrintWriter out = response.getWriter();
		out.print("<script>alert('"
				+ request.getSession().getAttribute("dongjieMessage")
				+ "');document.location.href='/XGProject/date-page/kehukucundongjie.jsp';</script>");
		// 返回到对应的页面
		return null;
	}

	// 解冻该客户的货物库存
	public ActionForward JieDongClientGoods(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ClientGoodsForm cgf = (ClientGoodsForm) form;
		boolean ok = this.clientGoodsService.JieDongClientGoods(cgf);
		if (ok) {
			this.loginLogService.updateLogin(
					request.getSession().getAttribute("loginlogId").toString(),
					"解冻客户库存" + cgf.getCgoodsId());
			request.getSession().setAttribute("jiedongMessage", "解冻成功！");
		} else {
			request.getSession().setAttribute("jiedongMessage", "解冻失败！");
		}

		// 返回到对应的页面
		PrintWriter out = response.getWriter();
		out.print("<script>alert('"
				+ request.getSession().getAttribute("jiedongMessage")
				+ "');document.location.href='/XGProject/date-page/kehukucundongjie.jsp';</script>");
		return null;
	}

	// 质押客户的货物库存
	public ActionForward ZhiYaClientGoods(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ClientGoodsForm cgf = (ClientGoodsForm) form;
		boolean ok = this.clientGoodsService.ZhiYaClientGoods(cgf);
		if (ok) {
			this.loginLogService.updateLogin(
					request.getSession().getAttribute("loginlogId").toString(),
					"质押客户库存" + cgf.getCgoodsId());
			request.getSession().setAttribute("zhiyaMessage", "质押成功！");
		} else {
			request.getSession().setAttribute("zhiyaMessage", "质押失败！");
		}

		// 返回到对应的页面
		return super.execute(mapping, form, request, response);
	}

	// 解除质押客户的货物库存
	public ActionForward JCZhiYaClientGoods(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ClientGoodsForm cgf = (ClientGoodsForm) form;
		boolean ok = this.clientGoodsService.JCZhiYaClientGoods(cgf);
		if (ok) {
			this.loginLogService.updateLogin(
					request.getSession().getAttribute("loginlogId").toString(),
					"解除质押客户库存" + cgf.getCgoodsId());
			request.getSession().setAttribute("jiechuMessage", "解除成功！");
		} else {
			request.getSession().setAttribute("jiechuMessage", "解除失败！");
		}

		// 返回到对应的页面
		return super.execute(mapping, form, request, response);
	}

	// 通过客户查询客户的库存
	public ActionForward getClientGoods(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ClientGoodsForm cgf = (ClientGoodsForm) form;
		// 通过货物的模糊查询
		if (cgf.getGoodsName() == "") {
			cgf.setGoodsName("");// 货物名称
		}
		if (cgf.getGoodsProperty() == null) {
			cgf.setGoodsProperty("");// 货物属性
		}
		if (cgf.getGoodsQuality() == null) {
			cgf.setGoodsQuality("");// 货物材质；
		}
		if (cgf.getGoodsSign() == null) {
			cgf.setGoodsSign("");// 货物助记符
		}
		if (cgf.getGoodsStandard() == null) {
			cgf.setGoodsStandard("");// 货物规格
		}
		Integer clientId = cgf.getClient();

		int pageNow = 1;
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}
		if (clientId != null) {
			int pageCount = this.clientGoodsService.getClientGoodsByPageCount(
					cgf.getGoodsName(), cgf.getGoodsStandard(),
					cgf.getGoodsQuality(), cgf.getGoodsProperty(),
					cgf.getGoodsSign(), clientId, 16);
			if (pageNow >= pageCount) {
				pageNow = pageCount;
			}
			if (pageNow <= 1) {
				pageNow = 1;
			}
			List<ClientGoods> cglist = this.clientGoodsService
					.getClientGoodsByPage(cgf.getGoodsName(),
							cgf.getGoodsStandard(), cgf.getGoodsQuality(),
							cgf.getGoodsProperty(), cgf.getGoodsSign(),
							clientId, pageNow, 16);
			PrintWriter out = response.getWriter();
			JSONArray array = new JSONArray();
			if (cglist.size() <= 0) {
				JSONObject obj = new JSONObject();
				obj.put("result", "no");
				array.add(obj);
				out.print(array.toString());
				out.flush();
				out.close();
				return null;
			}
			for (int i = 0; i < cglist.size(); i++) {
				JSONObject obj = new JSONObject();
				obj.put("result", "yes");
				obj.put("pageNow", pageNow);
				obj.put("pageCount", pageCount);
				obj.put("goodsName", cglist.get(i).getGoods().getGoodsName());
				obj.put("guige", cglist.get(i).getGoods().getGoodsStandard()
						.getGoodsStandardName());
				obj.put("chandi", cglist.get(i).getGoods().getGoodsYieldly()
						.getGoodsYieldlyName());
				obj.put("weight", cglist.get(i).getCgoodsWeight());
				array.add(obj);
			}
			out.print(array.toString());
			out.flush();
			out.close();
			return null;

		}

		return null;
	}

	// 查询全部的客户货物库存，可以通过客户和货物进行模糊的查询，然后进行冻结
	public ActionForward getClientGoodsAll(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ClientGoodsForm cgf = (ClientGoodsForm) form;

		if (cgf.getDanwei() == null) {
			cgf.setDanwei("");
		}
		if (cgf.getJiancheng() == null) {
			cgf.setJiancheng("");
		}
		if (cgf.getZhujifu() == null) {
			cgf.setZhujifu("");
		}

		// 通过货物的模糊查询
		if (cgf.getGoodsName() == "") {
			cgf.setGoodsName("");// 货物名称
		}
		if (cgf.getGoodsProperty() == null) {
			cgf.setGoodsProperty("");// 货物属性
		}
		if (cgf.getGoodsQuality() == null) {
			cgf.setGoodsQuality("");// 货物材质；
		}
		if (cgf.getGoodsSign() == null) {
			cgf.setGoodsSign("");// 货物助记符
		}
		if (cgf.getGoodsStandard() == null) {
			cgf.setGoodsStandard("");// 货物规格
		}
		int pageRow= 30;
		if(request.getParameter("pageRow")!=null){
			pageRow=Integer.parseInt(request.getParameter("pageRow").toString());
		}
		int pageNow = 1;
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}
		int pageCount = this.clientGoodsService.getClientGoodsAllByPageCount(
				cgf.getDanwei(), cgf.getJiancheng(), cgf.getZhujifu(),
				cgf.getGoodsName(), cgf.getGoodsStandard(),
				cgf.getGoodsQuality(), cgf.getGoodsProperty(),
				cgf.getGoodsSign(), pageRow);
		if (pageNow >= pageCount) {
			pageNow = pageCount;
		}
		if (pageNow <= 1) {
			pageNow = 1;
		}

		List<ClientGoods> cglist = this.clientGoodsService
				.getClientGoodsAllByPage(cgf.getDanwei(), cgf.getJiancheng(),
						cgf.getZhujifu(), cgf.getGoodsName(),
						cgf.getGoodsStandard(), cgf.getGoodsQuality(),
						cgf.getGoodsProperty(), cgf.getGoodsSign(), pageNow, pageRow);

		PrintWriter out = response.getWriter();
		JSONArray array = new JSONArray();
		if (cglist.size() <= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		for (int i = 0; i < cglist.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("cgid", cglist.get(i).getCgoodsId());// 保存id
			obj.put("clientId", cglist.get(i).getClient().getClientId());// 保存客户的id
			obj.put("clientName", cglist.get(i).getClient()
					.getClientAbbreviation());// 保存客户的简称
			obj.put("pinlei", cglist.get(i).getGoods().getGoodsCategory()
					.getGoodsCategoryName());// 保存货物品类
			obj.put("goodsName", cglist.get(i).getGoods().getGoodsName());// 保存货物名称
			obj.put("guige", cglist.get(i).getGoods().getGoodsStandard()
					.getGoodsStandardName());// 保存货物规格
			obj.put("caizhi", cglist.get(i).getGoods().getGoodsQuality()
					.getGoodsQualityName());// 保存货物材质
			obj.put("shuxing", cglist.get(i).getGoods().getGoodsProperty()
					.getGoodsPropertyName());// 保存货物属性
			obj.put("chandi", cglist.get(i).getGoods().getGoodsYieldly()
					.getGoodsYieldlyName());// 保存货物产地
			obj.put("goodsId", cglist.get(i).getGoods().getGoodsId());// 保存货物的id
			obj.put("dongjieweight", cglist.get(i).getCgoodsFreezeWeight());// 保存冻结重量
			obj.put("shengyuweight", cglist.get(i).getCgoodsWeight());// 保存现有重量
			obj.put("pageNow", pageNow);
			obj.put("pageCount", pageCount);
			obj.put("result", "notnull");
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();

		// 返回到对应的页面
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

	// app客户查询所有客户货物库存订单
	public ActionForward getClientAllInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 返回行数
		PageRow pageRow = new PageRow();

		// 获取页面传入的值
		String s_pageNows = request.getParameter("pageNow");

		int clientId = Integer.parseInt(request.getSession()
				.getAttribute("clientId").toString());

		// 获得总页数
		int pageCount = clientGoodsService.getClientGoodsAllCount(clientId,
				pageRow.getRow());
		request.setAttribute("pageCount", pageCount);

		int pageNow2 = 1;
		// 获取用户输入页数
		// 当前页
		if (s_pageNows != null) {
			pageNow2 = fenye(s_pageNows, pageCount);
		}
		request.setAttribute("pageNow2", pageNow2);

		List<ClientGoods> clientGoodslist = clientGoodsService
				.getClientGoodsAll(clientId, pageNow2, pageRow.getRow());

		System.out.println("size:" + clientGoodslist.size());

		request.setAttribute("clientGoodslist", clientGoodslist);

		return mapping.findForward("getClientAll");

	}

	// app客户有条件查询所有客户货物库存
	public ActionForward getClientAllTj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		// 返回行数
		int pageRow = 10;
		if(request.getParameter("pageRow")!=null){
			pageRow = Integer.parseInt(request.getParameter("pageRow").toString());
		}

		// 获取页面传入的值
		String pinlei = new String(request.getParameter("pinlei").getBytes("ISO-8859-1"), "utf-8");
		String guige = new String(request.getParameter("guige").getBytes("ISO-8859-1"), "utf-8");
		String chandi = new String(request.getParameter("chandi").getBytes("ISO-8859-1"), "utf-8");
		String shuxin = new String(request.getParameter("shuxin").getBytes("ISO-8859-1"), "utf-8");
		String huowu = new String(request.getParameter("huowu").getBytes("ISO-8859-1"), "utf-8");
		String caizhi = new String(request.getParameter("caizhi").getBytes("ISO-8859-1"), "utf-8");
		String s_pageNows = new String(request.getParameter("pageNow").getBytes("ISO-8859-1"), "utf-8");
		System.out.println(huowu);
		int clientId = Integer.parseInt(request.getSession().getAttribute("clientId").toString());

		// 获得总页数
		int pageCount = clientGoodsService.getClientGoodsCount(clientId, pinlei, huowu, shuxin, guige, chandi, caizhi, pageRow);

		request.setAttribute("pageCount", pageCount);

		int pageNow2 = 1;
		// 获取用户输入页数
		// 当前页
		if (s_pageNows != null) {
			pageNow2 = fenye(s_pageNows, pageCount);
		}

		request.setAttribute("pageNow2", pageNow2);

		List<ClientGoods> clientGoodslist = clientGoodsService.getClientGoodsInfo(clientId, pinlei, huowu, shuxin, guige, chandi, caizhi, pageNow2, pageRow);
		if(request.getParameter("ff")!=null){
			if(request.getParameter("ff").equals("ajax")){
				PrintWriter out = response.getWriter();
				JSONArray array = new JSONArray();
				if(clientGoodslist.size()<=0){
					JSONObject obj = new JSONObject();
					obj.put("result", "null");
					array.add(obj);
					out.print(array.toString());
					out.flush();
					out.close();
					return null;
				}
				for(int i=0; i<clientGoodslist.size(); i++){
					JSONObject obj = new JSONObject();
					obj.put("kehu", clientGoodslist.get(i).getClient().getClientAbbreviation());//保存客户名称
					obj.put("pinlei", clientGoodslist.get(i).getGoods().getGoodsCategory().getGoodsCategoryName());//保存品类
					obj.put("mingcheng", clientGoodslist.get(i).getGoods().getGoodsName());//保存货物名称
					obj.put("guige", clientGoodslist.get(i).getGoods().getGoodsStandard().getGoodsStandardName());//保存货物规格
					obj.put("caizhi", clientGoodslist.get(i).getGoods().getGoodsQuality().getGoodsQualityName());//保存材质名称
					obj.put("shuxing", clientGoodslist.get(i).getGoods().getGoodsProperty().getGoodsPropertyName());//保存属性名称
					obj.put("chandi", clientGoodslist.get(i).getGoods().getGoodsYieldly().getGoodsYieldlyName());//保存货物产地
					obj.put("zhongliang", clientGoodslist.get(i).getCgoodsWeight());//保存重量
					obj.put("DJzhongliang", clientGoodslist.get(i).getCgoodsFreezeWeight());//保存冻结重量
					obj.put("pageCount", pageCount);
					obj.put("result", "notnull");
					array.add(obj);
				}
				out.print(array.toString());
				out.flush();
				out.close();
				return null;
			}
		}
		request.setAttribute("clientGoodslist", clientGoodslist);
		return mapping.findForward("getClientAll");
	}
}
