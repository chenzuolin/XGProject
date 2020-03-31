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

import com.xinggang.project.entity.TarehouseDetail;
import com.xinggang.project.form.TarehouseDetailForm;
import com.xinggang.project.service.GoodsService;
import com.xinggang.project.service.LoginLogService;
import com.xinggang.project.service.TarehouseDetailService;
import com.xinggang.project.tools.PresentTime;

/**
 * 货物批次类action
 * 
 * @author Administrator
 * 
 */
public class TarehouseDetailAction extends DispatchAction {
	// 货物批次service
	private TarehouseDetailService tarehouseDetailService;
	// 货物service
	@SuppressWarnings("unused")
	private GoodsService goodsService;

	// 日志service
	private LoginLogService loginLogService;

	private PresentTime pt = new PresentTime();

	public void setLoginLogService(LoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}

	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}

	public void setTarehouseDetailService(
			TarehouseDetailService tarehouseDetailService) {
		this.tarehouseDetailService = tarehouseDetailService;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return super.execute(mapping, form, request, response);
	}

	// 删除货物批次
	public ActionForward deleteTDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TarehouseDetailForm tdf = (TarehouseDetailForm) form;
		TarehouseDetail td = this.tarehouseDetailService
				.getTarehouseDetailId(tdf.getTdetailId());// 通过编号查询出类
		boolean ok = this.tarehouseDetailService.delete(td);
		if (ok) {
			this.loginLogService.updateLogin(
					request.getSession().getAttribute("loginId").toString(),
					"删除批次");// 将这一操作记录到日志表中
			request.getSession().setAttribute("deleteTDetail", "删除成功！");
		} else {
			request.getSession().setAttribute("deleteTDetail", "删除失败！");
		}
		// 返回到对应的页面
		return super.execute(mapping, form, request, response);
	}

	// 查看批次
	public ActionForward getTDetailAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TarehouseDetailForm tdf = (TarehouseDetailForm) form;
		if (tdf.getGoodsName() == null) {
			tdf.setGoodsName("");
		}
		if (tdf.getGoodsSign() == null) {
			tdf.setGoodsSign("");
		}
		if (tdf.getGoodsStandard() == null) {
			tdf.setGoodsStandard("");
		}
		if (tdf.getGoodsQuality() == null) {
			tdf.setGoodsQuality("");
		}
		if (tdf.getGoodsProperty() == null) {
			tdf.setGoodsProperty("");
		}
		if (tdf.getTdetailId() == null) {
			tdf.setTdetailId("");
		}

		if (tdf.getBegin() == null || tdf.getBegin().equals("")) {
			tdf.setBegin(pt.getNowJianYi());
		} else {
			tdf.setBegin(tdf.getBegin() + " 00:00:00");
		}
		if (tdf.getFinish() == null || tdf.getFinish().equals("")) {
			tdf.setFinish(pt.getTimes());
		} else {
			tdf.setFinish(tdf.getFinish() + " 23:59:59");
		}

		if (tdf.getKuname() == null) {
			tdf.setKuname("");
		}
		int pageNow = 1;
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}
		int pageCount = this.tarehouseDetailService
				.getTarehouseDetailByPageCount(tdf.getGoodsName(),
						tdf.getGoodsStandard(), tdf.getGoodsQuality(),
						tdf.getGoodsProperty(), tdf.getBegin(),
						tdf.getFinish(), tdf.getGoodsSign(),
						tdf.getTdetailId(), tdf.getKuname(), 30);
		if (pageNow >= pageCount) {
			pageNow = pageCount;
		}
		if (pageNow <= 1) {
			pageNow = 1;
		}

		List<TarehouseDetail> tdlist = this.tarehouseDetailService
				.getTarehouseDetailAllByPage(tdf.getGoodsName(),
						tdf.getGoodsStandard(), tdf.getGoodsQuality(),
						tdf.getGoodsProperty(), tdf.getBegin(),
						tdf.getFinish(), tdf.getGoodsSign(),
						tdf.getTdetailId(), tdf.getKuname(), pageNow, 30);
		JSONArray array = new JSONArray();
		PrintWriter out = response.getWriter();
		DecimalFormat df = new DecimalFormat("#############00.000");
		if (tdlist.size() <= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			obj.put("pageNow", pageNow);
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		for (int i = 0; i < tdlist.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("kuwei", tdlist.get(i).getTdetailTarehouse()
					.getTarehouseName());// 库位名称
			obj.put("picihao", tdlist.get(i).getTdetailId());// 批次号
			obj.put("goodsName", tdlist.get(i).getGoods().getGoodsName());// 货物名称
			obj.put("guige", tdlist.get(i).getGoods().getGoodsStandard()
					.getGoodsStandardName());// 规格
			obj.put("caizhi", tdlist.get(i).getGoods().getGoodsQuality()
					.getGoodsQualityName());// 材质
			obj.put("shuxing", tdlist.get(i).getGoods().getGoodsProperty()
					.getGoodsPropertyName());// 属性
			obj.put("chandi", tdlist.get(i).getGoods().getGoodsYieldly()
					.getGoodsYieldlyName());// 产地
			obj.put("rukuweight", tdlist.get(i).getTdetailWeight());// 入库重量
			obj.put("rukunumber", tdlist.get(i).getTdetailNumber());// 入库件数
			obj.put("chukuweight", tdlist.get(i).getTdetailEweight());// 出库重量
			obj.put("chukunumber", tdlist.get(i).getTdetailEnumber());// 出库件数
			obj.put("shenyuweight", Double.parseDouble(df.format((tdlist.get(i)
					.getTdetailWeight() - tdlist.get(i).getTdetailEweight()))));// 剩余重量
			obj.put("shenyunumber", Double.parseDouble(df.format(tdlist.get(i)
					.getTdetailNumber() - tdlist.get(i).getTdetailEnumber())));// 剩余件数
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

	// 提醒给批次即将到期
	public ActionForward TiXingTDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return super.execute(mapping, form, request, response);
	}

	// 通过库位和货物进行同时查询

	public ActionForward getChuKuPiCi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TarehouseDetailForm tdf = (TarehouseDetailForm) form;
		JSONArray array = new JSONArray();
		PrintWriter out = response.getWriter();
		if (tdf == null) {
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		List<TarehouseDetail> tdlist = this.tarehouseDetailService
				.getGoodsTarehouseData(tdf.getKuwei(), tdf.getGoods());// 通过库位和货物进行查询并且的关系
		System.out.println("库位的id是：" + tdf.getKuwei() + "货物的id是："
				+ tdf.getGoods() + "查到的值得大小是：" + tdlist.size());
		if (tdlist.size() <= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		for (TarehouseDetail td : tdlist) {
			JSONObject obj = new JSONObject();
			obj.put("id", td.getTdetailId());// 保存批次的编号
			obj.put("weight", td.getTdetailWeight());// 保存批次的入库重量
			obj.put("Eweight", td.getTdetailEweight());// 保存批次的已出重量
			obj.put("number", td.getTdetailNumber());// 保存批次的入库件数
			obj.put("Enumber", td.getTdetailEnumber());// 保存批次的已出件数
			obj.put("unit", td.getGoods().getGoodsUnit().getGoodsUnitName());// 保存计量单位名称
			obj.put("result", "notnull");
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
		return null;
	}

	// 修改货物批次的重量和件数
	public ActionForward updatePiCi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TarehouseDetailForm tdf = (TarehouseDetailForm) form;
		String message = "";
		if (tdf != null) {
			// 通过编号进行查询
			TarehouseDetail td = this.tarehouseDetailService
					.getTarehouseDetailId(tdf.getTdetailId());
			if (td != null) {
				td.setTdetailWeight(tdf.getTdetailWeight());// 入库重量
				td.setTdetailNumber(tdf.getTdetailNumber());// 入库件数
				td.setTdetailEweight(tdf.getTdetailEweight());// 已出重量
				td.setTdetailEnumber(tdf.getTdetailEnumber());// 已出件数
				boolean ok = this.tarehouseDetailService.update(td);
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
		PrintWriter out = response.getWriter();
		out.print("<script>alert('"
				+ message
				+ "');document.location.href='/XGProject/date-page/huowupicibiao.jsp';</script>");
		out.flush();
		out.close();
		return null;
	}
}
