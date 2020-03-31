package com.xinggang.project.action;
/*
 * 今日钢价action
 * */
import java.io.PrintWriter;
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

import com.xinggang.project.entity.InteriorUser;
import com.xinggang.project.entity.SteelPrice;
import com.xinggang.project.form.SteelPriceForm;
import com.xinggang.project.service.SteelPriceService;
import com.xinggang.project.tools.PageRow;
import com.xinggang.project.tools.PresentTime;

public class SteelPriceAction extends DispatchAction {
	// 今日钢价service
	private SteelPriceService steelPriceService;
	// 时间工具类
	private PresentTime pt = new PresentTime();
	// 编号工具类
	private PageRow pr = new PageRow();

	public void setSteelPriceService(SteelPriceService steelPriceService) {
		this.steelPriceService = steelPriceService;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return super.execute(mapping, form, request, response);
	}

	// 添加今日钢价
	public ActionForward saveSteelPrice(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 获得提交的form值
		SteelPriceForm spf = (SteelPriceForm) form;
		PrintWriter out = response.getWriter();
		String message = "";// 保存消息，是否添加成功
		if (spf != null) {
			// 创建一个实例化对象
			SteelPrice sp = new SteelPrice();
			sp.setSpid(pt.getDatesNotTime() + "-" + pr.getJinRiGangJiaNumbers());// 添加编号
			sp.setSpgoodsName(spf.getSpgoodsName());// 添加货物名称
			sp.setSpgoodsStandard(spf.getSpgoodsStandard());// 添加货物规格
			sp.setSpgoodsQuality(spf.getSpgoodsQuality());// 添加货物材质
			sp.setSpgoodsProperty(spf.getSpgoodsProperty());// 添加货物属性
			sp.setSpgoodsYieldly(spf.getSpgoodsYieldly());// 添加货物产地
			sp.setSpgoodsPrice(spf.getSpgoodsPrice());// 添加货物单价
			InteriorUser iu = (InteriorUser) request.getSession().getAttribute(
					"iulist");// 查询当前登录人的类
			sp.setSpoperator(iu.getIuserName());// 添加人
			sp.setSpoperatorTime(pt.getTimes());// 添加时间
			sp.setSpremark(spf.getSpremark());// 备注
			sp.setSpdefinedOne(spf.getSpdefinedOne());// 预留字段一
			sp.setSpdefinedTwo(spf.getSpdefinedTwo());// 预留字段二
			sp.setSpdefinedThree(spf.getSpdefinedThree());// 预留字段三
			sp.setSpdefinedFour(spf.getSpdefinedFour());// 预留字段四
			sp.setSpdefinedFive(spf.getSpdefinedFive());// 预留字段五
			sp.setSpdefinedSix(spf.getSpdefinedSix());// 预留字段六
			boolean ok = this.steelPriceService.save(sp);
			if (ok) {
				message = "添加成功！";
			} else {
				message = "添加失败！";
			}
		} else {
			message = "添加失败！";
		}
		// 返回到对应的页面
		out.print(message);
		out.flush();
		out.close();
		return null;
	}

	// 修改今日钢价
	public ActionForward updateSteelPrice(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 获得提交的form值
		SteelPriceForm spf = (SteelPriceForm) form;
		PrintWriter out = response.getWriter();
		String message = "";// 保存消息，是否添加成功
		if (spf != null) {
			// 通过对应的编号进行查询
			SteelPrice sp = this.steelPriceService.getSteelPrice(spf.getSpid());
			if (sp != null) {

				sp.setSpgoodsName(spf.getSpgoodsName());// 修改货物名称
				sp.setSpgoodsStandard(spf.getSpgoodsStandard());// 修改货物规格
				sp.setSpgoodsQuality(spf.getSpgoodsQuality());// 修改货物材质
				sp.setSpgoodsProperty(spf.getSpgoodsProperty());// 修改货物属性
				sp.setSpgoodsYieldly(spf.getSpgoodsYieldly());// 修改货物产地
				sp.setSpgoodsPrice(spf.getSpgoodsPrice());// 修改货物单价
				sp.setSpremark(spf.getSpremark());// 备注
				sp.setSpdefinedOne(spf.getSpdefinedOne());// 预留字段一
				sp.setSpdefinedTwo(spf.getSpdefinedTwo());// 预留字段二
				sp.setSpdefinedThree(spf.getSpdefinedThree());// 预留字段三
				sp.setSpdefinedFour(spf.getSpdefinedFour());// 预留字段四
				sp.setSpdefinedFive(spf.getSpdefinedFive());// 预留字段五
				sp.setSpdefinedSix(spf.getSpdefinedSix());// 预留字段六

				boolean ok = this.steelPriceService.update(sp);
				if (ok) {
					message = "修改成功！";
				} else {
					message = "修改失败！";
				}
			} else {
				message = "修改失败！";
			}
		} else {
			message = "修改失败！";
		}
		// 返回到对应的页面
		out.print(message);
		out.flush();
		out.close();
		return null;
	}

	// 查询对应的历史的记录
	public ActionForward querySteelPrice(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SteelPriceForm spf = (SteelPriceForm) form;
		if (spf.getSpgoodsName() == null) {
			spf.setSpgoodsName("");
		}
		if (spf.getBegin() == null || spf.getBegin().equals("")) {
			spf.setBegin(pt.getNowJianYi());
		} else {
			spf.setBegin(spf.getBegin() + " 00:00:00");
		}
		if (spf.getFinish() == null || spf.getFinish().equals("")) {
			spf.setFinish(pt.getTimes());
		} else {
			spf.setFinish(spf.getFinish() + " 23:59:59");
		}
		int pageNow = 1;
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}
		int pageCount = this.steelPriceService.getConditionQueryByPageCount(
				spf.getSpgoodsName(), "", "", "", "", spf.getBegin(),
				spf.getFinish(), 20);
		if (pageNow >= pageCount) {
			pageNow = pageCount;
		} else if (pageNow <= 1) {
			pageNow = 1;
		}
		List<SteelPrice> splist = this.steelPriceService
				.getConditionQueryByPage(spf.getSpgoodsName(), "", "", "", "",
						spf.getBegin(), spf.getFinish(), pageNow, 20);
		JSONArray array = new JSONArray();
		PrintWriter out = response.getWriter();
		if (splist.size() <= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		for (int i = 0; i < splist.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("result", "notnull");
			obj.put("pageNow", pageNow);
			obj.put("pageCount", pageCount);
			obj.put("id", splist.get(i).getSpid());// 保存对应的编号、
			obj.put("goodsName", splist.get(i).getSpgoodsName());// 保存对应的货物名称
			obj.put("guige", splist.get(i).getSpgoodsStandard());// 保存对应的货物的规格
			obj.put("caizhi", splist.get(i).getSpgoodsQuality());// 保存对应的货物的材质
			obj.put("shuxing", splist.get(i).getSpgoodsProperty());// 保存对应的货物的属性
			obj.put("chandi", splist.get(i).getSpgoodsYieldly());// 保存对应额货物的产地
			obj.put("danjia", splist.get(i).getSpgoodsPrice());// 保存对应的货物的单价
			obj.put("caozuoren", splist.get(i).getSpoperator());// 保存对应的操作人，指的是添加人
			obj.put("time", splist.get(i).getSpoperatorTime());// 保存对应的添加时间
			obj.put("remark", splist.get(i).getSpremark());// 保存对应的备注
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
		return null;
	}

	// 添加之前的查询
	public ActionForward saveQuerySteelPrice(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int pageNow = 1;// 当前页
		int rowSize = 10;// 显示的行数
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}
		if (request.getParameter("rowSize") != null
				&& request.getParameter("rowSize").equals("") == false) {
			rowSize = Integer.parseInt(request.getParameter("rowSize"));
		}
		int pageCount = this.steelPriceService.getConditionQueryByPageCount("",
				"", "", "", "", pt.getNowJianYi(), pt.getTimes(), rowSize);
		if (pageNow >= pageCount) {
			pageNow = pageCount;
		} else if (pageNow <= 1) {
			pageNow = 1;
		}
		List<SteelPrice> splist = this.steelPriceService
				.getConditionQueryByPage("", "", "", "", "", pt.getNowJianYi(),
						pt.getTimes(), pageNow, rowSize);
		JSONArray array = new JSONArray();
		PrintWriter out = response.getWriter();
		for (int i = 0; i < splist.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("result", "notnull");
			obj.put("pageNow", pageNow);
			obj.put("pageCount", pageCount);
			obj.put("id", splist.get(i).getSpid());// 保存对应的编号、
			obj.put("goodsName", splist.get(i).getSpgoodsName());// 保存对应的货物名称
			obj.put("guige", splist.get(i).getSpgoodsStandard());// 保存对应的货物的规格
			obj.put("caizhi", splist.get(i).getSpgoodsQuality());// 保存对应的货物的材质
			obj.put("shuxing", splist.get(i).getSpgoodsProperty());// 保存对应的货物的属性
			obj.put("chandi", splist.get(i).getSpgoodsYieldly());// 保存对应额货物的产地
			obj.put("danjia", splist.get(i).getSpgoodsPrice());// 保存对应的货物的单价
			obj.put("caozuoren", splist.get(i).getSpoperator());// 保存对应的操作人，指的是添加人
			obj.put("time", splist.get(i).getSpoperatorTime());// 保存对应的添加时间
			obj.put("remark", splist.get(i).getSpremark());// 保存对应的备注
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
		return null;
	}

	// app中对应的查询查询
	public ActionForward QueryAppSteelPrice(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<SteelPrice> spalllist = this.steelPriceService.getAll();// 查询全部，并且同时间降序
		String time = spalllist.get(0).getSpoperatorTime();// 取出时间
		String subtime = time.substring(0, 10);
		String begin = subtime + " 00:00:00";
		String finish = subtime + " 23:59:59";
		// 取出对应的上一天的数据，进行和今天的数据进行比较，判断相应涨跌
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String lastTime = df.format(new Date(df.parse(time).getTime() - 1 * 24
				* 60 * 60 * 1000));
		String beginTime = lastTime + " 00:00:00";
		String finishTime = lastTime + " 23:59:59";

		int rowSize = spalllist.size();// 显示的行数
		List<SteelPrice> splist = this.steelPriceService
				.getConditionQueryByPage("", "", "", "", "", begin, finish, 1,
						rowSize);
		JSONArray array = new JSONArray();
		PrintWriter out = response.getWriter();
		for (int i = 0; i < splist.size(); i++) {
			List<SteelPrice> lastlist = this.steelPriceService.getLastTimeData(
					splist.get(i).getSpgoodsName(), splist.get(i)
							.getSpgoodsStandard(), splist.get(i)
							.getSpgoodsQuality(), splist.get(i)
							.getSpgoodsProperty(), splist.get(i)
							.getSpgoodsYieldly(), beginTime, finishTime);
			double lastDanJia = 0.0;
			if (lastlist != null) {
				if (lastlist.size() > 0) {
					lastDanJia = lastlist.get(0).getSpgoodsPrice();// 设置上一天的单价
				}
			}
			JSONObject obj = new JSONObject();
			obj.put("result", "notnull");
			obj.put("id", splist.get(i).getSpid());// 保存对应的编号、
			obj.put("goodsName", splist.get(i).getSpgoodsName());// 保存对应的货物名称
			obj.put("guige", splist.get(i).getSpgoodsStandard());// 保存对应的货物的规格
			obj.put("caizhi", splist.get(i).getSpgoodsQuality());// 保存对应的货物的材质
			obj.put("shuxing", splist.get(i).getSpgoodsProperty());// 保存对应的货物的属性
			obj.put("chandi", splist.get(i).getSpgoodsYieldly());// 保存对应额货物的产地
			obj.put("danjia", splist.get(i).getSpgoodsPrice());// 保存对应的货物的单价
			obj.put("lastdanjia", lastDanJia);// 上一天的单价
			obj.put("caozuoren", splist.get(i).getSpoperator());// 保存对应的操作人，指的是添加人
			obj.put("time", splist.get(i).getSpoperatorTime());// 保存对应的添加时间
			obj.put("remark", splist.get(i).getSpremark());// 保存对应的备注
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
		return null;
	}
}
