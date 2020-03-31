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

import com.xinggang.project.entity.TarehouseGoods;
import com.xinggang.project.form.TarehouseGoodsForm;
import com.xinggang.project.service.GoodsService;
import com.xinggang.project.service.LoginLogService;
import com.xinggang.project.service.TarehouseGoodsService;
import com.xinggang.project.service.TarehouseService;
import com.xinggang.project.tools.PageRow;

/**
 * 货物库存类action
 * 
 * @author Administrator
 * 
 */
public class TarehouseGoodsAction extends DispatchAction {
	// 货物库存service
	private TarehouseGoodsService tarehouseGoodsService;
	// 货物明细service
	@SuppressWarnings("unused")
	private GoodsService goodsService;
	// 库位service
	@SuppressWarnings("unused")
	private TarehouseService tarehouseService;
	// 日志service
	private LoginLogService loginLogService;

	private PageRow pr = new PageRow();

	public void setLoginLogService(LoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}

	public void setTarehouseService(TarehouseService tarehouseService) {
		this.tarehouseService = tarehouseService;
	}

	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}

	public void setTarehouseGoodsService(
			TarehouseGoodsService tarehouseGoodsService) {
		this.tarehouseGoodsService = tarehouseGoodsService;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return super.execute(mapping, form, request, response);
	}

	// 修改货物库存
	public ActionForward updateTarehouseGoods(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		TarehouseGoodsForm tgf = (TarehouseGoodsForm) form;
		try {
			TarehouseGoods tarehouseGoods = this.tarehouseGoodsService
					.getTarehouseGoodsId(tgf.getTgoodsId());// 通过id查询
			tarehouseGoods.setTgoodsWeight(tgf.getTgoodsWeight());// 修改的库存重量
			tarehouseGoods.setTgoodsNumber(tgf.getTgoodsNumber());// 修改的库存件数
			/*
			 * tarehouseGoods.setTarehouse(this.tarehouseService
			 * .getTarehouseId(tgf.getTarehouse()));// 修改的库位
			 */
			boolean ok = this.tarehouseGoodsService.update(tarehouseGoods);
			if (ok) {
				this.loginLogService.updateLogin(request.getSession()
						.getAttribute("loginlogId").toString(), "修改货物库存");// 将这一操作记录在日志表中
				request.getSession().setAttribute("updateTarehouseGoods",
						"修改成功！");
			} else {
				request.getSession().setAttribute("updateTarehouseGoods",
						"修改失败！");
			}
		} catch (Exception e) {

		}
		// 返回到对应的页面
		PrintWriter out = response.getWriter();
		out.print("<script>alert('"
				+ request.getSession().getAttribute("updateTarehouseGoods")
				+ "');document.location.href='/XGProject/date-page/kuweiKuCun.jsp';</script>");
		out.flush();
		out.close();
		return null;
	}

	// 查看货物库存，可以通过货物进行模糊查询
	public ActionForward getTarehouseGoodsAll(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		TarehouseGoodsForm tgf = (TarehouseGoodsForm) form;

		if (tgf.getGoodsName().equals(null)) {
			tgf.setGoodsName("");
		}
		if (tgf.getGoodsStandard().equals(null)) {
			tgf.setGoodsStandard("");
		}
		if (tgf.getGoodsSign().equals(null)) {
			tgf.setGoodsSign("");
		}
		if (tgf.getGoodsQuality().equals(null)) {
			tgf.setGoodsQuality("");
		}
		if (tgf.getGoodsProperty().equals(null)) {
			tgf.setGoodsProperty("");
		}
		if (tgf.getKuname().equals(null)) {
			tgf.setKuname("");
		}
		if (tgf.getPinlei() == null) {
			tgf.setPinlei("");
		}
		if (tgf.getChandi() == null) {
			tgf.setChandi("");
		}

		int pageNow = 1;
		int pageCount = this.tarehouseGoodsService
				.getTarehouseGoodsAllByPageCount(tgf.getGoodsName(),
						tgf.getGoodsStandard(), tgf.getGoodsQuality(),
						tgf.getGoodsProperty(), tgf.getGoodsSign(),
						tgf.getKuname(), tgf.getChandi(), tgf.getPinlei(),
						pr.getRow());

		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}
		if (pageNow >= pageCount) {
			pageNow = pageCount;
		}
		if (pageNow <= 1) {
			pageNow = 1;
		}

		List<TarehouseGoods> tglist = this.tarehouseGoodsService
				.getTarehouseGoodsAllByPage(tgf.getGoodsName(),
						tgf.getGoodsStandard(), tgf.getGoodsQuality(),
						tgf.getGoodsProperty(), tgf.getGoodsSign(),
						tgf.getKuname(), tgf.getChandi(), tgf.getPinlei(),
						pageNow, pr.getRow());
		request.setAttribute("tarehouseGoodsList", tglist);// 保存到request中
		// 保存当前页
		request.getSession().setAttribute("pageNow", pageNow);

		// 返回到对应的页面
		return super.execute(mapping, form, request, response);
	}

	// 通过货物进行查询，为了在当客户发起入库或者出库的时候到调度的时候，会自动的将有这样货物的库位给列出
	public ActionForward getTarehouseGoods(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 取得货物的id
		String goodsId = request.getParameter("goodsId");
		List<TarehouseGoods> tglist = this.tarehouseGoodsService
				.getGoods(Integer.parseInt(goodsId));// 通过货物进行查询
		request.setAttribute("tglist", tglist);// 保存到request中
		// 返回到对应的页面
		return super.execute(mapping, form, request, response);
	}

	// 通过货物进行查询，为了在当客户发起入库或者出库的时候到调度的时候，会自动的将有这样货物的库位给列出
	public ActionForward selectChuKuKuWei(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 取得货物的id
		String goodsId = request.getParameter("goodsId");
		List<TarehouseGoods> tglist = this.tarehouseGoodsService
				.getGoods(Integer.parseInt(goodsId));// 通过货物进行查询
		JSONArray array = new JSONArray();
		PrintWriter out = response.getWriter();
		if (tglist.size() > 0) {
			for (TarehouseGoods tg : tglist) {
				JSONObject obj = new JSONObject();
				obj.put("id", tg.getTarehouse().getTarehouseId());// 保存库位的编号
				obj.put("name", tg.getTarehouse().getTarehouseName());// 保存库位的名称
				obj.put("weight", tg.getTgoodsWeight());// 保存该库存的重量
				obj.put("number", tg.getTgoodsNumber());// 保存该库存的件数
				obj.put("unit", tg.getGoods().getGoodsUnit().getGoodsUnitName());// 保存计量单位名称
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

	// ajax查询保管员操作信息
	public void selectHouseGoodsAjax(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/* 设置响应头允许ajax跨域访问 */
		response.setHeader("Access-Control-Allow-Origin", "*");
		/* 星号表示所有的异域请求都可以接受， */
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");

		PrintWriter out = response.getWriter();

		// 获取库位id和货物资料信息
		String kuwei = request.getParameter("kuwei");

		String huowu = request.getParameter("huowu");

		// 获得页数
		String s_pageNows = request.getParameter("pageNow");

		// 返回行数
		// 获得总页数
		int pageCount = tarehouseGoodsService.getKeweiPageCount(kuwei, huowu,
				30);
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

			if (Integer.parseInt(s_pageNows) > pageCount) {
				JSONObject obj = new JSONObject();
				obj.put("qingkong", "clean");
				out.print(obj.toString());
				out.flush();
				out.close();
				return;
			}
		}

		List<TarehouseGoods> listTarehouseGoods = tarehouseGoodsService
				.getKeweiPage(kuwei, huowu, pageNow2, 30);
		// 用JSONObject保存班组
		JSONArray array = new JSONArray();
		for (TarehouseGoods t : listTarehouseGoods) {
			JSONObject obj = new JSONObject();
			obj.put("id", t.getTgoodsId());// 保存对应的库位库存的编号
			obj.put("kuweiName", t.getTarehouse().getTarehouseName());
			obj.put("goodsName", t.getGoods().getGoodsName());
			obj.put("guige", t.getGoods().getGoodsStandard()
					.getGoodsStandardName());
			obj.put("caizhi", t.getGoods().getGoodsQuality()
					.getGoodsQualityName());
			obj.put("shuxin", t.getGoods().getGoodsProperty()
					.getGoodsPropertyName());
			obj.put("chandi", t.getGoods().getGoodsYieldly()
					.getGoodsYieldlyName());
			obj.put("shenYUWeight", t.getTgoodsWeight());
			obj.put("shenYUNumber", t.getTgoodsNumber());

			// 保存最大页数
			obj.put("pageCount", pageCount);

			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
	}

}
