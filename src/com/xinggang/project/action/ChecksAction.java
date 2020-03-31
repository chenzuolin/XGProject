package com.xinggang.project.action;

import java.io.PrintWriter;
import java.net.URLDecoder;
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

import com.xinggang.project.entity.Checks;
import com.xinggang.project.entity.Functions;
import com.xinggang.project.entity.InteriorUser;
import com.xinggang.project.entity.Powers;
import com.xinggang.project.entity.TarehouseGoods;
import com.xinggang.project.form.ChecksForm;
import com.xinggang.project.service.ChecksService;
import com.xinggang.project.service.FunctionsService;
import com.xinggang.project.service.GoodsService;
import com.xinggang.project.service.InteriorUserService;
import com.xinggang.project.service.LoginLogService;
import com.xinggang.project.service.PowersService;
import com.xinggang.project.service.TarehouseGoodsService;
import com.xinggang.project.service.TarehouseService;
import com.xinggang.project.tools.PageRow;
import com.xinggang.project.tools.PresentTime;

/**
 * 盘库action
 * 
 * @author Administrator
 * 
 */

public class ChecksAction extends DispatchAction {
	// 盘库service
	private ChecksService checksService;
	// 内部人员service
	private InteriorUserService interiorUserService;
	// 库位service
	private TarehouseService tarehouseService;
	// 货物库存service
	private TarehouseGoodsService tarehouseGoodsService;
	// 日志service
	private LoginLogService loginLogService;
	// 货物service
	@SuppressWarnings("unused")
	private GoodsService goodsService;
	// 功能service
	private FunctionsService functionsService;
	// 权限service
	private PowersService powersService;

	//编号工具类
	private PageRow pr = new PageRow();
	//时间格式工具类
	private PresentTime pt = new PresentTime();

	public void setFunctionsService(FunctionsService functionsService) {
		this.functionsService = functionsService;
	}

	public void setPowersService(PowersService powersService) {
		this.powersService = powersService;
	}

	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}

	public void setLoginLogService(LoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}

	public void setTarehouseGoodsService(
			TarehouseGoodsService tarehouseGoodsService) {
		this.tarehouseGoodsService = tarehouseGoodsService;
	}

	public void setTarehouseService(TarehouseService tarehouseService) {
		this.tarehouseService = tarehouseService;
	}

	public void setInteriorUserService(InteriorUserService interiorUserService) {
		this.interiorUserService = interiorUserService;
	}

	public void setChecksService(ChecksService checksService) {
		this.checksService = checksService;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return super.execute(mapping, form, request, response);
	}

	// 发起盘库之前的查询，查询要进行盘库的执行，通过对应的权限进行查询
	public ActionForward getQueryData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		String ff = request.getParameter("ff");
		// 发起挪库之前查询挪库的执行人，通过分配的权限进行查询
		if (ff.toString().equals("zhixing")) {
			try {
				JSONArray array = new JSONArray();
				// 首先通过功能名称查询到功能的id
				List<Functions> flist = this.functionsService
						.getFunctionsName("执行盘库");
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
				return null;
			} catch (Exception e) {
				System.out.println(e);
			}
		}

		return null;
	}

	// 查询出库时的执行出库的操作员
	public ActionForward getChuKuCZY(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		String ff = request.getParameter("ff");
		// 发起挪库之前查询挪库的执行人，通过分配的权限进行查询
		if (ff.toString().equals("zhixing")) {
			try {
				JSONArray array = new JSONArray();
				// 首先通过功能名称查询到功能的id
				List<Functions> flist = this.functionsService
						.getFunctionsName("执行出库");
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
				return null;
			} catch (Exception e) {
				System.out.println(e);
			}
		}

		return null;
	}

	// 发起盘库之前的查询
	public ActionForward getChecksFirst(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 计算当前页
		int pageNow = 1;
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}
		SimpleDateFormat NYRdf = new SimpleDateFormat("yyyy-MM-dd");
		String dangqian = NYRdf.format(new Date());
		String search = "";
		if (request.getParameter("search") != null) {
			search = URLDecoder.decode(request.getParameter("search"), "UTF-8");
		}
		int pageCount = this.tarehouseGoodsService.getChecksDataByPageCount(
				search, dangqian, dangqian, 18);

		if (pageNow > pageCount) {
			JSONArray array = new JSONArray();
			JSONObject obj = new JSONObject();
			obj.put("max", "maxs");
			array.add(obj);
			PrintWriter out = response.getWriter();
			out.print(array.toString());
			out.flush();
			out.close();

			return null;
		}
		if (pageNow >= pageCount) {
			pageNow = pageCount;
		}
		if (pageNow <= 1) {
			pageNow = 1;
		}

		List<TarehouseGoods> tglist = this.tarehouseGoodsService
				.getChecksDataByPage(search, dangqian, dangqian, pageNow, 18);
		// 遍历现有的库存的的集合
		PrintWriter out = response.getWriter();
		String ff = request.getParameter("ff").toString();
		if (ff.equals("goods")) {
			JSONArray array = new JSONArray();
			if (tglist.size() > 0) {

				for (int i = 0; i < tglist.size(); i++) {
					try {
						JSONObject obj = new JSONObject();
						obj.put("id", tglist.get(i).getTgoodsId());// 保存库存的编号
						obj.put("kuweiId", tglist.get(i).getTarehouse()
								.getTarehouseId());// 保存库位的编号
						obj.put("kuwei", tglist.get(i).getTarehouse()
								.getTarehouseName());// 保存库位的名称
						obj.put("pinlei", tglist.get(i).getGoods()
								.getGoodsCategory().getGoodsCategoryName());// 保存货物品类
						obj.put("mingcheng", tglist.get(i).getGoods()
								.getGoodsName());// 保存货物名称
						obj.put("zhujifu", tglist.get(i).getGoods()
								.getGoodsSign());// 保存货物助记符
						obj.put("guige", tglist.get(i).getGoods()
								.getGoodsStandard().getGoodsStandardName());// 保存货物规格
						obj.put("caizhi", tglist.get(i).getGoods()
								.getGoodsQuality().getGoodsQualityName());// 保存货物材质
						obj.put("shuxing", tglist.get(i).getGoods()
								.getGoodsProperty().getGoodsPropertyName());// 保存货物属性
						obj.put("chandi", tglist.get(i).getGoods()
								.getGoodsYieldly().getGoodsYieldlyName());// 保存货物产地
						obj.put("zhongliang", tglist.get(i).getTgoodsWeight());// 保存重量
						obj.put("jianshu", tglist.get(i).getTgoodsNumber());// 保存件数
						obj.put("result", "notnull");
						obj.put("pageNow", pageNow);
						array.add(obj);
					} catch (Exception e) {
					}
				}
			} else if (tglist.size() <= 0 && pageNow != 1) {
				JSONObject obj = new JSONObject();
				obj.put("result", "dangqianye");
				array.add(obj);
			} else {
				JSONObject obj = new JSONObject();
				obj.put("result", "null");
				array.add(obj);
			}

			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}

		// 返回到对应的页面
		return null;
	}

	// 发起盘库,查询对应的库存，以多选的方式实现
	public ActionForward FaQiChecks(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ChecksForm cf = (ChecksForm) form;
		int num = 0;
		if (cf != null) {
			InteriorUser iu = this.interiorUserService.getInteriorUserId(cf
					.getInteriorUserByCheckHuman());// 通过id查询内部人员
			iu.setIuserWork(0);
			this.interiorUserService.update(iu);// 修改保管员的工作状态
			for (int i = 0; i < cf.getTarehouseGoodss().length; i++) {
				try {
					Checks checks = new Checks();
					String id = "盘" + pt.getDatesNotTime()
							+ pr.getPanKuNumber();
					checks.setCheckId(id);// 设置的盘库编号，当前时间的加库存的编号
					checks.setInteriorUserByCheckFounderMember(this.interiorUserService
							.getInteriorUserId(cf
									.getInteriorUserByCheckFounderMember()));// 盘库的发起人
					checks.setCheckTime(pt.getTimes());// 盘库的发起时间
					checks.setTarehouse(this.tarehouseService.getTarehouseId(cf
							.getTarehouses()[i]));// 盘库的库位
					checks.setTarehouseGoods(this.tarehouseGoodsService
							.getTarehouseGoodsId(cf.getTarehouseGoodss()[i]));//
					// 盘库的库存
					checks.setCheckTarehouseWeight(cf.getCheckNumber()[i]);//
					// 设置重量
					checks.setCheckTarehouseNumber(cf.getCheckNumber()[i]);//
					// 设置件数
					checks.setInteriorUserByCheckHuman(this.interiorUserService
							.getInteriorUserId(cf.getInteriorUserByCheckHuman()));//
					// 盘库人，指保管员
					checks.setCheckAuditingTrue("计划盘库");
					checks.setCheckRemark(cf.getCheckRemarks()[i]);// 备注

					this.checksService.save(checks);
					num++;
				} catch (Exception e) {

				}
			}
			request.getSession().setAttribute("FaQiChecks", "发起成功！");
			this.loginLogService.updateLogin(
					request.getSession().getAttribute("loginlogId").toString(),
					"发起盘库");// 记录到日志表中
		} else {
			request.getSession().setAttribute("FaQiChecks", "发起失败！");
		}
		if (num == cf.getTarehouseGoodss().length) {
			request.getSession().setAttribute("FaQiChecks", "发起成功！");
		} else {
			request.getSession().setAttribute("FaQiChecks", "发起失败！");
		}
		PrintWriter out = response.getWriter();
		out.print("<script>alert('"
				+ request.getSession().getAttribute("FaQiChecks")
				+ "'); window.location.href='/XGProject/cangchu/page/faqipanku.jsp'</script>");
		// 返回到对应的页面
		return null;
	}

	// 查询计划盘库和审核未通过的订单
	public ActionForward getChecksJiHua(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ChecksForm cf = (ChecksForm) form;
		List<Checks> jlist = this.checksService.getChecksZhuangtai("计划盘库");// 查询出计划盘库的订单
		List<Checks> wlist = this.checksService.getChecksZhuangtai("审核未通过");// 查询出审核未通过的订单

		// 将盘库未通过的订单添加到集合中
		for (Checks c : wlist) {
			jlist.add(c);
		}

		for (int i = 0; i < jlist.size(); i++) {
			if (!jlist.get(i).getInteriorUserByCheckHuman().getIuserId()
					.equals(cf.getInteriorUserByCheckHuman())) {
				jlist.remove(i);
				i--;
				continue;
			}
		}
		PrintWriter out = response.getWriter();
		JSONArray array = new JSONArray();
		if (jlist.size() <= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}

		request.getSession().setAttribute("CZPanKu", jlist.size());
		for (int i = 0; i < jlist.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("id", jlist.get(i).getCheckId());// 保存盘库编号
			obj.put("faqiren", jlist.get(i)
					.getInteriorUserByCheckFounderMember().getIuserName());// 保存盘库的发起人
			obj.put("time", jlist.get(i).getCheckTime());// 保存盘库的发起时间
			obj.put("kuwei", jlist.get(i).getTarehouse().getTarehouseName());// 保存库位
			obj.put("pinlei", jlist.get(i).getTarehouseGoods().getGoods()
					.getGoodsCategory().getGoodsCategoryName());// 保存货物的品类
			obj.put("mingcheng", jlist.get(i).getTarehouseGoods().getGoods()
					.getGoodsName());// 保存货物的名称
			obj.put("guige", jlist.get(i).getTarehouseGoods().getGoods()
					.getGoodsStandard().getGoodsStandardName());// 保存规格
			obj.put("zhujifu", jlist.get(i).getTarehouseGoods().getGoods()
					.getGoodsSign());// 保存货物助记符
			obj.put("caizhi", jlist.get(i).getTarehouseGoods().getGoods()
					.getGoodsQuality().getGoodsQualityName());// 保存货物材质
			obj.put("shuxing", jlist.get(i).getTarehouseGoods().getGoods()
					.getGoodsProperty().getGoodsPropertyName());// 保存货物属性
			obj.put("chandi", jlist.get(i).getTarehouseGoods().getGoods()
					.getGoodsYieldly().getGoodsYieldlyName());// 保存货物产地
			obj.put("zhongliang", jlist.get(i).getCheckTarehouseWeight());// 保存重量
			obj.put("jianshu", jlist.get(i).getCheckTarehouseNumber());// 保存件数
			obj.put("zhuangtai", jlist.get(i).getCheckAuditingTrue());// 保存订单的状态
			obj.put("remark", jlist.get(i).getCheckRemark());// 保存备注
			obj.put("result", "notnull");
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
		// 返回到对应的页面
		return null;
	}

	// 正在盘库，保管员点击处理的时候调用
	public ActionForward ZhengZaiChecks(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ChecksForm cf = (ChecksForm) form;
		Checks clist = this.checksService.getChecksId(URLDecoder.decode(
				cf.getCheckId(), "UTF-8"));// 通过id查询
		InteriorUser iu = this.interiorUserService.getInteriorUserId(cf
				.getInteriorUserByCheckHuman());// 通过盘库人的编号查询出来
		iu.setIuserWork(0);// 修改作业人员的盘库状态为，作业状态
		this.interiorUserService.update(iu);
		this.loginLogService.updateLogin(
				request.getSession().getAttribute("loginlogId").toString(),
				"执行盘库" + cf.getCheckId());// 记录在日志service中
		request.setAttribute("clist", clist);// 将查询出来的内容保存到request中

		// 返回到对应的页面
		return mapping.findForward("goCheckscaozuo");
	}

	// 盘库完成，填入重量件数
	public ActionForward WanChengChecks(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ChecksForm cf = (ChecksForm) form;

		if (cf != null) {
			Checks checks = this.checksService.getChecksId(cf.getCheckId());// 通过id查询
			// checks.setCheckResultWeight(cf.getCheckResultWeight());//
			// 填入实际的盘库的重量
			checks.setCheckResultNumber(cf.getCheckResultNumber());// 填入实际的盘库的件数
			checks.setCheckAuditingTrue("正在审核");// 设置订单状态为盘库完成
			if (cf.getCheckRemark() != null || !cf.getCheckRemark().equals("")) {
				checks.setCheckRemark(checks.getCheckRemark() + ","
						+ cf.getCheckRemark());// 设置备注
			}
			checks.setCheckDefinedOne(pt.getTimes());// 设置盘库完成的时间
			boolean ok = this.checksService.update(checks);
			if (ok) {
				InteriorUser iu = this.interiorUserService.getInteriorUserId(cf
						.getInteriorUserByCheckHuman());
				iu.setIuserWork(1);
				this.interiorUserService.update(iu);// 修改内部人员的作业状态

				this.loginLogService.updateLogin(request.getSession()
						.getAttribute("loginlogId").toString(), "盘库成功并提交"
						+ checks.getCheckId());// 记录在日志service中
				request.getSession().setAttribute("ZhengZaiChecks", "提交成功！");
			} else {
				request.getSession().setAttribute("ZhengZaiChecks", "提交失败！");
			}
		} else {
			request.getSession().setAttribute("ZhengZaiChecks", "提交失败！");
		}

		// 返回到对应的页面
		PrintWriter out = response.getWriter();
		out.print("<script>alert('"
				+ request.getSession().getAttribute("ZhengZaiChecks")
				+ "');window.history.go(-2);</script>");
		// document.location.href='/XGProject/cangchu/page/caozuodingdan_main.jsp'
		out.flush();
		out.close();
		return null;
	}

	// 查询盘库完成的订单
	public ActionForward getChecksWanCheng(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JSONArray array = new JSONArray();
		PrintWriter out = response.getWriter();
		String kuname = "";
		if (request.getParameter("kuname") != null) {
			kuname = URLDecoder.decode(request.getParameter("kuname"), "UTF-8");
		}

		int pageNow = 1;
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}

		int pageCount = this.checksService.getTarehouseByPageCount(kuname,
				pr.getRow());
		if (pageNow > pageCount) {
			JSONObject obj = new JSONObject();
			obj.put("max", "maxs");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();

			return null;
		}
		if (pageNow >= pageCount) {
			pageNow = pageCount;
		}
		if (pageNow <= 1) {
			pageNow = 1;
		}
		request.getSession().setAttribute("PKSH",
				this.checksService.getTarehouseByPageCount("", 1));
		List<Checks> jlist = this.checksService.getTarehouseByPage(kuname,
				pageNow, pr.getRow());

		if (jlist.size() <= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		for (int i = 0; i < jlist.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("id", jlist.get(i).getCheckId());// 保存盘库编号
			obj.put("faqiren", jlist.get(i)
					.getInteriorUserByCheckFounderMember().getIuserName());// 保存盘库的发起人
			obj.put("time", jlist.get(i).getCheckTime());// 保存盘库的发起时间
			obj.put("kuwei", jlist.get(i).getTarehouse().getTarehouseName());// 保存库位
			obj.put("pinlei", jlist.get(i).getTarehouseGoods().getGoods()
					.getGoodsCategory().getGoodsCategoryName());// 保存货物的品类
			obj.put("mingcheng", jlist.get(i).getTarehouseGoods().getGoods()
					.getGoodsName());// 保存货物的名称
			obj.put("guige", jlist.get(i).getTarehouseGoods().getGoods()
					.getGoodsStandard().getGoodsStandardName());// 保存规格
			obj.put("zhujifu", jlist.get(i).getTarehouseGoods().getGoods()
					.getGoodsSign());// 保存货物助记符
			obj.put("caizhi", jlist.get(i).getTarehouseGoods().getGoods()
					.getGoodsQuality().getGoodsQualityName());// 保存货物材质
			obj.put("shuxing", jlist.get(i).getTarehouseGoods().getGoods()
					.getGoodsProperty().getGoodsPropertyName());// 保存货物属性
			obj.put("chandi", jlist.get(i).getTarehouseGoods().getGoods()
					.getGoodsYieldly().getGoodsYieldlyName());// 保存货物产地
			obj.put("zhongliang", jlist.get(i).getCheckTarehouseWeight());// 保存重量
			obj.put("jianshu", jlist.get(i).getCheckResultNumber());// 保存件数
			obj.put("danwei", jlist.get(i).getTarehouseGoods().getGoods()
					.getGoodsUnit().getGoodsUnitName());
			obj.put("zhuangtai", jlist.get(i).getCheckAuditingTrue());// 保存订单的状态
			obj.put("remark", jlist.get(i).getCheckRemark());// 保存备注
			obj.put("result", "notnull");
			obj.put("pageNow", pageNow);
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
		// 返回到对应的页面
		return null;
	}

	// 正在审核盘库，审核人员点击处理的时候调用
	public ActionForward ZhengZaiShenHe(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ChecksForm cf = (ChecksForm) form;
		Checks clist = this.checksService.getChecksId(URLDecoder.decode(
				cf.getCheckId(), "UTF-8"));// 通过id查询
		request.setAttribute("clist", clist);// 将查询出来的内容保存到request中

		// 返回到对应的页面
		return mapping.findForward("goShenHeCaoZuo");
	}

	// 审核通过
	public ActionForward TongGuoChecks(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ChecksForm cf = (ChecksForm) form;
		if (cf != null) {
			Checks checks = this.checksService.getChecksId(cf.getCheckId());// 通过盘库编号将该盘库单查询出来
			if (checks != null) {
				checks.setInteriorUserByCheckAuditing(this.interiorUserService
						.getInteriorUserId(cf.getInteriorUserByCheckAuditing()));// 设置审核人
				checks.setCheckAuditingTime(pt.getTimes());// 审核的日期
				checks.setCheckAuditingTrue("审核通过");// 审核通过
				if (cf.getCheckRemark() != null
						|| !cf.getCheckRemark().equals("")) {
					checks.setCheckRemark(checks.getCheckRemark()
							+ cf.getCheckRemark());// 备注
				}
				boolean ok = this.checksService.update(checks);
				if (ok) {
					this.loginLogService.updateLogin(request.getSession()
							.getAttribute("loginlogId").toString(), "审核盘库订单，通过"
							+ checks.getCheckId());// 记录到日志中
					request.setAttribute("TongGuoChecks", "审核成功!");
				} else {
					request.setAttribute("TongGuoChecks", "审核失败!");
				}
			} else {
				request.setAttribute("TongGuoChecks", "审核失败!");
			}
		} else {
			request.setAttribute("TongGuoChecks", "审核失败!");
		}

		// 返回到对应的页面
		PrintWriter out = response.getWriter();
		out.print("<script>alert('" + request.getAttribute("TongGuoChecks")
				+ "');window.history.go(-2);</script>");
		return null;
	}

	// 审核未通过
	public ActionForward NotTongGuoChecks(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ChecksForm cf = (ChecksForm) form;
		if (cf != null) {
			Checks checks = this.checksService.getChecksId(cf.getCheckId());// 通过盘库编号将该盘库单查询出来
			if (checks != null) {
				checks.setInteriorUserByCheckAuditing(this.interiorUserService
						.getInteriorUserId(cf.getInteriorUserByCheckAuditing()));// 设置审核人
				checks.setCheckAuditingTime(pt.getTimes());// 审核的日期
				checks.setCheckAuditingTrue("审核未通过");// 审核未通过
				if (cf.getCheckRemark() != null
						|| cf.getCheckRemark().equals("") != false) {
					checks.setCheckRemark(checks.getCheckRemark()
							+ cf.getCheckRemark());// 备注
				}
				boolean ok = this.checksService.update(checks);
				if (ok) {
					this.loginLogService.updateLogin(request.getSession()
							.getAttribute("loginlogId").toString(),
							"审核盘库订单，未通过" + checks.getCheckId());// 记录到日志中
					request.setAttribute("TongGuoChecks", "审核完成!");
				} else {
					request.setAttribute("TongGuoChecks", "审核失败!");
				}
			} else {
				request.setAttribute("TongGuoChecks", "审核失败!");
			}
		} else {
			request.setAttribute("TongGuoChecks", "审核失败!");
		}

		PrintWriter out = response.getWriter();
		out.print("<script>alert('" + request.getAttribute("TongGuoChecks")
				+ "');window.history.go(-2);</script>");
		// 返回到对应的页面
		return null;
	}

	// 查询所有的盘库的订单，通过库位和货物进行模糊的查询
	public ActionForward getChecksAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ChecksForm cf = (ChecksForm) form;
		if (cf.getBegin() == null) {
			cf.setBegin("2015-6-6 12:12:12");
		}
		if (cf.getFinish() == null || cf.getFinish().equals("")) {
			cf.setFinish(pt.getTimes());
		}
		if (cf.getGoodsName() == null) {
			cf.setGoodsName("");
		}
		if (cf.getGoodsSign() == null) {
			cf.setGoodsSign("");
		}
		if (cf.getGoodsStandard() == null) {
			cf.setGoodsStandard("");
		}
		if (cf.getGoodsQuality() == null) {
			cf.setGoodsQuality("");
		}
		if (cf.getGoodsProperty() == null) {
			cf.setGoodsProperty("");
		}
		if (cf.getKuName().equals(null)) {
			cf.setKuName("");
		}

		JSONArray array = new JSONArray();
		PrintWriter out = response.getWriter();
		// 分页显示
		int pageNow = 1;
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}
		int pageCount = this.checksService.getChecksAllByPageCount(
				cf.getBegin(), cf.getFinish() + " 23:59:59", cf.getGoodsName(),
				cf.getGoodsSign(), cf.getGoodsStandard(), cf.getGoodsQuality(),
				cf.getGoodsProperty(), cf.getKuName(), pr.getRow());
		if (pageNow >= pageCount) {
			pageNow = pageCount;
		}
		if (pageNow <= 1) {
			pageNow = 1;
		}

		List<Checks> clist = this.checksService.getChecksAllByPage(
				cf.getBegin(), cf.getFinish() + " 23:59:59", cf.getGoodsName(),
				cf.getGoodsSign(), cf.getGoodsStandard(), cf.getGoodsQuality(),
				cf.getGoodsProperty(), cf.getKuName(), pageNow, pr.getRow());
		if (clist.size() <= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}

		for (Checks c : clist) {
			JSONObject obj = new JSONObject();
			obj.put("id", c.getCheckId());// 保存盘库的编号
			obj.put("faqiren", c.getInteriorUserByCheckFounderMember()
					.getIuserName());// 保存发起人的名字
			obj.put("time", c.getCheckTime());// 保存发起时间
			obj.put("kuwei", c.getTarehouse().getTarehouseName());// 保存库位的名称
			obj.put("zhuangtai", c.getCheckAuditingTrue());// 保存订单状态
			obj.put("pageNow", pageNow);// 保存当前页
			obj.put("result", "notnull");
			array.add(obj);
		}

		out.print(array.toString());
		out.flush();
		out.close();

		// 返回到对应的页面
		return null;
	}

	// 当查询全部的订单，点击查看明细的时候调用
	public ActionForward gePankuMingXi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ChecksForm cf = (ChecksForm) form;
		Checks clist = this.checksService.getChecksId(URLDecoder.decode(
				cf.getCheckId(), "UTF-8"));// 通过id查询
		request.setAttribute("clist", clist);// 将查询出来的内容保存到request中

		// 返回到对应的页面
		return mapping.findForward("goPanKuMingXi");
	}

	// 删除盘库的订单
	public ActionForward deleteChecks(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ChecksForm cf = (ChecksForm) form;
		if (cf != null) {
			Checks checks = this.checksService.getChecksId(cf.getCheckId());// 通过编号查询出来
			if (checks != null) {
				boolean ok = this.checksService.delete(checks);
				if (ok) {
					this.loginLogService.updateLogin(request.getSession()
							.getAttribute("loginlogId").toString(), "删除盘库订单"
							+ checks.getCheckId());// 将这一操作记录在日志表中
					request.getSession().setAttribute("deleteChecks", "删除成功！");
				} else {
					request.getSession().setAttribute("deleteChecks", "删除失败！");
				}
			} else {
				request.getSession().setAttribute("deleteChecks", "删除失败！");
			}
		} else {
			request.getSession().setAttribute("deleteChecks", "删除失败！");
		}
		// 返回到对应的页面.
		PrintWriter out = response.getWriter();
		out.print("<script>alert('"
				+ request.getSession().getAttribute("deleteChecks")
				+ "'); window.history.go(-2);</script>");
		return null;
	}

	// 修改盘库的订单
	public ActionForward updateChecks(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ChecksForm cf = (ChecksForm) form;
		if (cf != null) {
			Checks checks = this.checksService.getChecksId(cf.getCheckId());// 通过编号查询出来
			if (checks != null) {
				System.out.println(cf.getCheckResultNumber());
				checks.setCheckResultNumber(cf.getCheckResultNumber());// 修改盘库的结果
				if (cf.getCheckRemark() != null
						&& !cf.getCheckRemark().equals("")) {
					checks.setCheckRemark(checks.getCheckRemark() + ","
							+ cf.getCheckRemark());// 添加备注
				}
				boolean ok = this.checksService.update(checks);
				if (ok) {
					this.loginLogService.updateLogin(request.getSession()
							.getAttribute("loginlogId").toString(), "修改盘库订单"
							+ checks.getCheckId());// 将这一操作记录在日志表中
					request.getSession().setAttribute("updateChecks", "修改成功！");
				} else {
					request.getSession().setAttribute("updateChecks", "修改失败！");
				}
			} else {
				request.getSession().setAttribute("updateChecks", "修改失败！");
			}
		} else {
			request.getSession().setAttribute("updateChecks", "修改失败！");
		}
		PrintWriter out = response.getWriter();
		out.print("<script>alert('"
				+ request.getSession().getAttribute("updateChecks")
				+ "'); window.history.go(-2);</script>");
		// 返回到对应的页面
		return null;
	}

}
