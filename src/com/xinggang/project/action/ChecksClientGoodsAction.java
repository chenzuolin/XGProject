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

import com.xinggang.project.entity.ChecksClientGoods;
import com.xinggang.project.form.ChecksClientGoodsForm;
import com.xinggang.project.service.BursaryService;
import com.xinggang.project.service.ChecksClientGoodsService;
import com.xinggang.project.service.ClientGoodsService;
import com.xinggang.project.service.ClientService;
import com.xinggang.project.service.GoodsService;
import com.xinggang.project.service.InteriorUserService;
import com.xinggang.project.service.LoginLogService;
import com.xinggang.project.tools.PageRow;
import com.xinggang.project.tools.PresentTime;

/*
 * 盘点action
 * */
public class ChecksClientGoodsAction extends DispatchAction {

	// 盘点客户库存service
	private ChecksClientGoodsService checksClientGoodsService;
	// 客户库存service
	private ClientGoodsService clientGoodsService;
	// 内部人员service
	private InteriorUserService interiorUserService;
	// 货物service
	private GoodsService goodsService;
	// 库房service
	@SuppressWarnings("unused")
	private BursaryService bursaryService;
	// 客户service
	private ClientService clientService;
	// 日志service
	private LoginLogService loginLogService;

	private PageRow pr = new PageRow();
	// 当前时间工具类
	private PresentTime pt = new PresentTime();

	public void setLoginLogService(LoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public void setBursaryService(BursaryService bursaryService) {
		this.bursaryService = bursaryService;
	}

	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}

	public void setInteriorUserService(InteriorUserService interiorUserService) {
		this.interiorUserService = interiorUserService;
	}

	public void setClientGoodsService(ClientGoodsService clientGoodsService) {
		this.clientGoodsService = clientGoodsService;
	}

	public void setChecksClientGoodsService(
			ChecksClientGoodsService checksClientGoodsService) {
		this.checksClientGoodsService = checksClientGoodsService;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return super.execute(mapping, form, request, response);
	}

	// 查询某个客户的库存，在客户库存action中全部查询
	// 发起盘点，对应的客户的库存要相应的去减少或者，增加，判断是盈库，还是，如果是盈库那么就减少客户库存，如果是亏库那么就增加客户库存
	public ActionForward FaQiChecksClientGoods(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ChecksClientGoodsForm ccgf = (ChecksClientGoodsForm) form;
		if (ccgf != null) {
			ChecksClientGoods ccg = new ChecksClientGoods();
			ccg.setCcgoodsId("盘D" + pt.getDatesNotTime() + pr.getNumber());// 设置编号
			ccg.setClient(this.clientService.getClientId(ccgf.getClient()));// 设置客户
			ccg.setGoods(this.goodsService.getGoodsId(ccgf.getGoods()));// 设置货物
			// ccg.setBursary(this.bursaryService.getBursaryId(ccgf.getBursary()));//
			// 设置库区
			ccg.setInteriorUser(this.interiorUserService.getInteriorUserId(ccgf
					.getInteriorUser()));// 设置盘点人员
			ccg.setCcgoodsBeforeWeight(ccgf.getCcgoodsBeforeWeight());// 设置原有的重量
			ccg.setCcgoodsSurplus(ccgf.getCcgoodsSurplus());// 设置盈库重量
			ccg.setCcgoodsLoss(ccgf.getCcgoodsLoss());// 设置亏库的重量
			ccg.setCcgoodsChecksTime(pt.getTimes());// 设置盘点日期
			ccg.setCcgoodsRemark(ccgf.getCcgoodsRemark());// 设置备注
			boolean ok = this.checksClientGoodsService.save(ccg);
			if (ok) {
				this.loginLogService.updateLogin(request.getSession()
						.getAttribute("loginlogId").toString(), "发起盘点客户库存"
						+ ccg.getCcgoodsId());// 记录到日志表中
				// 判断是盈库还是亏库
				// 如果盈库，客户的库存重量减少
				// 如果是亏库，客户的库存重量增加
				// 盈库
				if (ccgf.getCcgoodsSurplus() != null
						&& ccgf.getCcgoodsSurplus() != 0
						&& !ccgf.getCcgoodsSurplus().equals(0)) {
					this.clientGoodsService.JianshaoKucun(ccgf.getClient(),
							ccgf.getGoods(), ccgf.getCcgoodsSurplus());// 相应的库存减少
				} else if (ccgf.getCcgoodsLoss() != null
						&& ccgf.getCcgoodsLoss() != 0
						&& !ccgf.getCcgoodsLoss().equals(0)) {
					this.clientGoodsService.ZengjiaKucun(ccgf.getClient(),
							ccgf.getGoods(), ccgf.getCcgoodsLoss(), 0.0);
				}
				request.getSession()
						.setAttribute("faqipandianMessage", "提交成功！");
			} else {
				request.getSession()
						.setAttribute("faqipandianMessage", "提交失败！");
			}
		} else {
			request.getSession().setAttribute("faqipandianMessage", "提交失败！");
		}
		PrintWriter out = response.getWriter();
		out.print("<script>alert('"
				+ request.getSession().getAttribute("faqipandianMessage")
				+ "');"
				+ "document.location.href='/XGProject/date-page/pandiankehukucun.jsp';</script>");
		return null;
	}

	// 修改盘点订单
	public ActionForward updateChecsCliengGoods(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ChecksClientGoodsForm ccgf = (ChecksClientGoodsForm) form;
		if (ccgf != null) {
			ChecksClientGoods ccg = this.checksClientGoodsService
					.getChecksClientGoodsId(ccgf.getCcgoodsId());// 通过编号查询
			if (ccg != null) {
				ccg.setCcgoodsSurplus(ccgf.getCcgoodsSurplus());// 修改盈库重量
				ccg.setCcgoodsLoss(ccgf.getCcgoodsLoss());// 修改亏库重量
				ccg.setCcgoodsRemark(ccg.getCcgoodsRemark() + ","
						+ ccgf.getCcgoodsRemark());// 添加备注
				boolean ok = this.checksClientGoodsService.update(ccg);
				if (ok) {
					this.loginLogService.updateLogin(request.getSession()
							.getAttribute("loginlogId").toString(),
							"修改盘点客户库存订单" + ccg.getCcgoodsId());// 记录到日志表中
					request.getSession().setAttribute("updateMessage", "修改成功！");
				} else {
					request.getSession().setAttribute("updateMessage", "修改失败！");
				}
			} else {
				request.getSession().setAttribute("updateMessage", "修改失败！");
			}
		} else {
			request.getSession().setAttribute("updateMessage", "修改失败！");
		}
		// 返回到对应的页面
		return super.execute(mapping, form, request, response);
	}

	// 删除盘点订单
	public ActionForward deleteChecksClientGoods(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ChecksClientGoodsForm ccgf = (ChecksClientGoodsForm) form;
		if (ccgf != null) {
			ChecksClientGoods ccg = this.checksClientGoodsService
					.getChecksClientGoodsId(ccgf.getCcgoodsId());// 通过编号查询
			if (ccg != null) {
				boolean ok = this.checksClientGoodsService.delete(ccg);
				if (ok) {
					request.getSession().setAttribute("deleteMessage", "删除成功！");
					// 记录到日志表中
					this.loginLogService.updateLogin(request.getSession()
							.getAttribute("loginlogId").toString(),
							"删除盘点客户库存订单" + ccgf.getCcgoodsId());
				} else {
					request.getSession().setAttribute("deleteMessage", "删除失败！");
				}
			} else {
				request.getSession().setAttribute("deleteMessage", "删除失败！");
			}
		} else {
			request.getSession().setAttribute("deleteMessage", "删除失败！");
		}
		// 返回到对应的页面
		return super.execute(mapping, form, request, response);
	}

	// 查询所有，以模糊分页的方式实现
	public ActionForward getChecksClientGoodsAll(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ChecksClientGoodsForm ccgf = (ChecksClientGoodsForm) form;
		if (ccgf.getDanwei() == null) {
			ccgf.setDanwei("");
		}
		if (ccgf.getJiancheng() == null) {
			ccgf.setJiancheng("");
		}
		if (ccgf.getDanweizhujifu() == null) {
			ccgf.setDanweizhujifu("");
		}
		if (ccgf.getCcgoodsId() == null) {
			ccgf.setCcgoodsId("");
		}
		if (ccgf.getKufangname() == null) {
			ccgf.setKufangname("");
		}
		if (ccgf.getBegin() == null || ccgf.getBegin().equals("")) {
			ccgf.setBegin("2015-6-6 12:12:12");
		}else{
			ccgf.setBegin(ccgf.getBegin()+" 00:00:00");
		}
		if (ccgf.getFinish() == null || ccgf.getFinish().equals("")) {
			ccgf.setFinish(pt.getTimes());
		}else{
			ccgf.setFinish(ccgf.getFinish()+" 23:59:59");
		}
		if (ccgf.getGoodsName() == null) {
			ccgf.setGoodsName("");
		}
		if (ccgf.getGoodsSign() == null) {
			ccgf.setGoodsSign("");
		}

		int pageNow = 1;
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}

		int pageCount = this.checksClientGoodsService.getMoHuByPageCount(
				ccgf.getDanwei(), ccgf.getJiancheng(), ccgf.getDanweizhujifu(),
				ccgf.getCcgoodsId(), ccgf.getBegin(), ccgf.getFinish(),
				ccgf.getGoodsName(), ccgf.getGoodsSign(), ccgf.getKufangname(),
				20);
		if (pageNow >= pageCount) {
			pageNow = pageCount;
		}
		if (pageNow <= 1) {
			pageNow = 1;
		}

		List<ChecksClientGoods> ccglist = this.checksClientGoodsService
				.getMoHuByPage(ccgf.getDanwei(), ccgf.getJiancheng(),
						ccgf.getDanweizhujifu(), ccgf.getCcgoodsId(),
						ccgf.getBegin(), ccgf.getFinish(), ccgf.getGoodsName(),
						ccgf.getGoodsSign(), ccgf.getKufangname(), pageNow,
						20);


		// 插叙盈库的总重量
		double ying = this.checksClientGoodsService.getTongJiYingKu(
				ccgf.getDanwei(), ccgf.getJiancheng(), ccgf.getDanweizhujifu(),
				ccgf.getCcgoodsId(), ccgf.getBegin(), ccgf.getFinish(),
				ccgf.getGoodsName(), ccgf.getGoodsSign(), ccgf.getKufangname());
		// 查询亏库的重量
		double kui = this.checksClientGoodsService.getTongJiKuiKu(
				ccgf.getDanwei(), ccgf.getJiancheng(), ccgf.getDanweizhujifu(),
				ccgf.getCcgoodsId(), ccgf.getBegin(), ccgf.getFinish(),
				ccgf.getGoodsName(), ccgf.getGoodsSign(), ccgf.getKufangname());

		//保存的json中
		JSONArray array = new JSONArray();
		PrintWriter out = response.getWriter();
		if(ccglist.size()<=0){
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		for(int i=0; i<ccglist.size(); i++){
			JSONObject obj = new JSONObject();
			obj.put("id", ccglist.get(i).getCcgoodsId());//保存编号
			obj.put("client", ccglist.get(i).getClient().getClientAbbreviation());//保存客户的简称
			obj.put("pinlei", ccglist.get(i).getGoods().getGoodsCategory().getGoodsCategoryName());//保存货物品类
			obj.put("mingcheng", ccglist.get(i).getGoods().getGoodsName());//保存货物名称
			obj.put("guige", ccglist.get(i).getGoods().getGoodsStandard().getGoodsStandardName());//保存货物规格
			obj.put("caizhi", ccglist.get(i).getGoods().getGoodsQuality().getGoodsQualityName());//保存货物的材质
			obj.put("shuxing", ccglist.get(i).getGoods().getGoodsProperty().getGoodsPropertyName());//保存货物的属性
			obj.put("chandi", ccglist.get(i).getGoods().getGoodsYieldly().getGoodsYieldlyName());//保存货物的产地
			obj.put("yuanweight", ccglist.get(i).getCcgoodsBeforeWeight());//保存原有重量
			obj.put("yingweight", ccglist.get(i).getCcgoodsSurplus());//保存盈库重量
			obj.put("kuiweight", ccglist.get(i).getCcgoodsLoss());//保存亏库重量
			obj.put("time", ccglist.get(i).getCcgoodsChecksTime());//保存盘点时间
			obj.put("pandianren", (ccglist.get(i).getInteriorUser()==null?"无":ccglist.get(i).getInteriorUser().getIuserName()));//保存盘点人
			obj.put("beizhu", ccglist.get(i).getCcgoodsRemark());//保存备注
			obj.put("result", "notnull");
			obj.put("pageNow", pageNow);//保存当前页
			obj.put("pageCount", pageCount);
			obj.put("ying", ying);//保存盈库合计
			obj.put("kui", kui);//保存亏库合计
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();

		// 返回到对应的页面
		return null;
	}
}
