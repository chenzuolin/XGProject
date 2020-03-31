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

import com.xinggang.project.entity.DuanDao;
import com.xinggang.project.entity.Functions;
import com.xinggang.project.entity.InteriorUser;
import com.xinggang.project.entity.Powers;
import com.xinggang.project.entity.Tarehouse;
import com.xinggang.project.entity.TarehouseDetail;
import com.xinggang.project.entity.TarehouseGoods;
import com.xinggang.project.form.DuanDaoForm;
import com.xinggang.project.service.DuanDaoService;
import com.xinggang.project.service.FunctionsService;
import com.xinggang.project.service.GoodsService;
import com.xinggang.project.service.InteriorUserService;
import com.xinggang.project.service.LoginLogService;
import com.xinggang.project.service.PowersService;
import com.xinggang.project.service.TarehouseDetailService;
import com.xinggang.project.service.TarehouseGoodsService;
import com.xinggang.project.service.TarehouseService;
import com.xinggang.project.tools.PageRow;
import com.xinggang.project.tools.PresentTime;

/*
 * 短倒action
 * */
public class DuanDaoAction extends DispatchAction {
	// 短倒service
	private DuanDaoService duanDaoService;
	// 内部人员service
	private InteriorUserService interiorUserService;
	// 货物service
	private GoodsService goodsService;
	// 登录日志service
	private LoginLogService loginLogService;
	// 库位库存service
	private TarehouseGoodsService tarehouseGoodsService;
	// 批次service
	private TarehouseDetailService tarehouseDetailService;
	// 库位service
	private TarehouseService tarehouseService;
	// 功能service
	private FunctionsService functionsService;
	// 权限service
	private PowersService powersService;
	// 时间工具类
	private PresentTime pt = new PresentTime();
	// 编号工具类
	private PageRow pr = new PageRow();

	public void setPowersService(PowersService powersService) {
		this.powersService = powersService;
	}

	public void setFunctionsService(FunctionsService functionsService) {
		this.functionsService = functionsService;
	}

	public void setTarehouseService(TarehouseService tarehouseService) {
		this.tarehouseService = tarehouseService;
	}

	public void setTarehouseDetailService(
			TarehouseDetailService tarehouseDetailService) {
		this.tarehouseDetailService = tarehouseDetailService;
	}

	public void setTarehouseGoodsService(
			TarehouseGoodsService tarehouseGoodsService) {
		this.tarehouseGoodsService = tarehouseGoodsService;
	}

	public void setLoginLogService(LoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}

	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}

	public void setInteriorUserService(InteriorUserService interiorUserService) {
		this.interiorUserService = interiorUserService;
	}

	public void setDuanDaoService(DuanDaoService duanDaoService) {
		this.duanDaoService = duanDaoService;
	}

	// 发起短倒之前查询数据
	public ActionForward getQueryData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DuanDaoForm df = (DuanDaoForm) form;
		PrintWriter out = response.getWriter();
		String ff = request.getParameter("ff");

		// 发起挪库之前查询挪库的执行人，通过分配的权限进行查询
		if (ff.toString().equals("zhixing")) {
			try {
				JSONArray array = new JSONArray();
				// 首先通过功能名称查询到功能的id
				List<Functions> flist = this.functionsService
						.getFunctionsName("执行短倒");
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

		if (ff != null) {
			JSONArray array = new JSONArray();
			if (ff.toString().equals("yuan")) {
				List<Tarehouse> tlist = this.tarehouseService.getAll();
				if (tlist.size() > 0) {
					for (int i = 0; i < tlist.size(); i++) {
						JSONObject obj = new JSONObject();
						obj.put("id", tlist.get(i).getTarehouseId());// 库位编号
						obj.put("name", tlist.get(i).getTarehouseName());// 库位的名字
						array.add(obj);
					}
					out.print(array.toString());
					out.flush();
					out.close();
					return null;
				}
			}
		}

		if (df != null) {
			if (ff.toString().equals("goods")) {
				JSONArray array = new JSONArray();
				Integer kuweiId = df.getTarehouseByShiftPast();// 取出库位的编号，
				// 通过库位的编号进行查询库位中的库存
				List<TarehouseGoods> tglist = this.tarehouseGoodsService
						.getKuwei(kuweiId);
				if (tglist.size() > 0) {
					for (int i = 0; i < tglist.size(); i++) {
						try {
							JSONObject obj = new JSONObject();
							obj.put("id", tglist.get(i).getGoods().getGoodsId());// 保存id
							obj.put("tarehouse", tglist.get(i).getTarehouse()
									.getTarehouseName());// 保存库位
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
							obj.put("zhongliang", tglist.get(i)
									.getTgoodsWeight());// 保存货物重量
							obj.put("jianshu", tglist.get(i).getTgoodsNumber());// 保存货物件数
							obj.put("result", "notnull");
							array.add(obj);
						} catch (Exception e) {
						}
					}
				} else {
					JSONObject obj = new JSONObject();
					obj.put("result", "null");
					array.add(obj);
				}
				out.print(array.toString());
				out.flush();
				out.close();

			}
			return null;
		}

		return null;
	}

	// 发起短倒
	public ActionForward saveShift(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DuanDaoForm df = (DuanDaoForm) form;
		String mess = "";
		int num = 0;
		for (int i = 0; i < df.getGoodsId().length; i++) {
			DuanDao shift = new DuanDao();
			shift.setShiftId("倒" + pt.getDatesNotTime() + pr.duanDaoNumbers());// 设置短倒编号，挪+当前的系统时间
			shift.setShiftTime(pt.getTimes());// 短倒的发起时间
			shift.setInteriorUserByShiftFounderMember(this.interiorUserService
					.getInteriorUserId(df.getInteriorUserByShiftFounderMember()));// 设置短倒发起人
			shift.setTarehouseByShiftPast(this.tarehouseService
					.getTarehouseId(df.getTarehouseByShiftPast()));// 设置原库位
			shift.setTarehouseByShiftNew(this.tarehouseService
					.getTarehouseId(df.getTarehouseByShiftNew()));// 设置新库位
			shift.setGoods(this.goodsService.getGoodsId(df.getGoodsId()[i]));// 设置挪库的货物
			shift.setWeights(df.getWeight()[i]);// 设置库位原有的重量
			shift.setNumbers(df.getNumber()[i]);// 设置库位原有的件数
			shift.setShiftWeight(df.getShiftWeights()[i]);// 设置挪库的重量
			shift.setShiftNumber(df.getShiftNumbers()[i]);// 设置挪库的件数
			shift.setInteriorUserByShiftExecutor(this.interiorUserService
					.getInteriorUserId(df.getInteriorUserByShiftExecutor()));// 设置挪库执行人
			shift.setShiftDefinedOne("计划短倒");// 设置挪库的状态
			shift.setShiftRemark(df.getRemark()[i]);// 设置挪库备注
			this.duanDaoService.save(shift);// 向数据库中添加数据
			num++;
		}

		if (num == df.getGoodsId().length) {
			mess = "提交成功！";
			// 将这一操作记录在登录日志中
			this.loginLogService.updateLogin(
					request.getSession().getAttribute("loginlogId").toString(),
					"发起挪库");
		} else {
			mess = "提交失败！";
		}

		PrintWriter out = response.getWriter();
		out.print("<script>alert('"
				+ mess
				+ "');window.location.href='/XGProject/cangchu/page/faqiduandao.jsp';</script>");
		out.flush();
		out.close();
		// 返回到相应的界面
		return null;
	}

	// 正在挪库之前的查询
	public ActionForward getZhengZaiFirst(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 查询计划挪库的订单和正在挪库的订单
		List<DuanDao> jslist = this.duanDaoService.getShiftZhuangtai("计划短倒");
		List<DuanDao> zjlist = this.duanDaoService.getShiftZhuangtai("正在短倒");

		for (DuanDao s : zjlist) {
			jslist.add(s);
		}
		// 只有分配了的人才能够看到
		DuanDaoForm sf = (DuanDaoForm) form;
		try {
			for (int i = 0; i < jslist.size(); i++) {
				if (jslist.get(i).getInteriorUserByShiftExecutor() == null) {
					jslist.remove(i);
					i--;
					continue;
				}
				if (!jslist.get(i).getInteriorUserByShiftExecutor()
						.getIuserId()
						.equals(sf.getInteriorUserByShiftExecutor())) {
					jslist.remove(i);
					i--;
					continue;
				}
			}
		} catch (Exception e) {

		}

		PrintWriter out = response.getWriter();
		JSONArray array = new JSONArray();
		if (jslist.size() <= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}

		for (int i = 0; i < jslist.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("id", jslist.get(i).getShiftId());// 保存挪库的编号
			obj.put("yuankuwei", jslist.get(i).getTarehouseByShiftPast()
					.getTarehouseName());// 保存挪库的原库位名称
			obj.put("xinkuwei", jslist.get(i).getTarehouseByShiftNew()
					.getTarehouseName());// 保存挪库的新库位名称
			obj.put("faqiren", jslist.get(i)
					.getInteriorUserByShiftFounderMember().getIuserName());// 保存挪库发起人的名字
			obj.put("pinlei", jslist.get(i).getGoods().getGoodsCategory()
					.getGoodsCategoryName());// 保存货物品类
			obj.put("mingcheng", jslist.get(i).getGoods().getGoodsName());// 保存货物名称
			obj.put("zhujifu", jslist.get(i).getGoods().getGoodsSign());// 保存货物助记符
			obj.put("guige", jslist.get(i).getGoods().getGoodsStandard()
					.getGoodsStandardName());// 保存货物规格
			obj.put("caizhi", jslist.get(i).getGoods().getGoodsQuality()
					.getGoodsQualityName());// 保存货物材质
			obj.put("shuxing", jslist.get(i).getGoods().getGoodsProperty()
					.getGoodsPropertyName());// 保存货物属性
			obj.put("chandi", jslist.get(i).getGoods().getGoodsYieldly()
					.getGoodsYieldlyName());// 保存货物产地
			obj.put("zhongliang", jslist.get(i).getShiftWeight());// 保存货物重量
			obj.put("jianshu", jslist.get(i).getShiftNumber());// 保存货物件数
			obj.put("time", jslist.get(i).getShiftTime());// 保存挪库的发起日期
			obj.put("zhuangtai", jslist.get(i).getShiftDefinedOne());// 保存挪库的状态
			obj.put("remark", jslist.get(i).getShiftRemark());// 报错挪库的备注
			obj.put("result", "notnull");
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();

		return null;
	}

	// 正在短倒
	public ActionForward zhengzaiShift(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DuanDaoForm sf = (DuanDaoForm) form;
		DuanDao shift = this.duanDaoService.getShiftId(URLDecoder.decode(
				sf.getShiftId(), "UTF-8"));// 通过id查询出挪库的订单
		shift.setShiftDefinedOne("正在短倒");// 修改挪库的状态为正在挪库
		InteriorUser it = this.interiorUserService.getInteriorUserId(sf
				.getInteriorUserByShiftExecutor());// 改变执行的作业状态
		it.setIuserWork(0);// 改变为作业状态
		this.interiorUserService.update(it);
		boolean ok = this.duanDaoService.update(shift);
		if (ok) {
			// 将这一操作记录在登录日志中
			this.loginLogService.updateLogin(
					request.getSession().getAttribute("loginlogId").toString(),
					"接受短倒订单" + URLDecoder.decode(sf.getShiftId(), "UTF-8"));
		}

		request.setAttribute("shift", shift);

		// 返回到相应的界面
		return mapping.findForward("goShiftCaozuo");
	}

	// 短倒完成
	public ActionForward wanchengShift(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DuanDaoForm sf = (DuanDaoForm) form;
		if (sf != null) {
			DuanDao shift = this.duanDaoService.getShiftId(sf.getShiftId());// 通过id查询出挪库的订单

			if (shift != null) {
				shift.setShiftDefinedOne("短倒完成");// 修改挪库的状态为挪库完成
				shift.setShiftFinishTime(pt.getTimes());// 挪库完成时间
				shift.setShiftCraneman(sf.getShiftCraneman());// 天车工
				shift.setShiftStevedore(sf.getShiftStevedore());// 装卸工
				shift.setDriverName(sf.getDriverName());// 设置短倒司机
				String beizhu = shift.getShiftRemark();// 取出备注
				if (sf.getShiftRemark() != null
						|| sf.getShiftRemark().equals("") == false) {
					shift.setShiftRemark(beizhu + "," + sf.getShiftRemark());// 重新填写的备注
				}
				InteriorUser it = this.interiorUserService.getInteriorUserId(sf
						.getInteriorUserByShiftExecutor());// 改变执行人的作业状态
				it.setIuserWork(1);// 改变为作业状态为未作业
				this.interiorUserService.update(it);

				// 相应的库存减少
				this.tarehouseGoodsService.jianTGoods(shift
						.getTarehouseByShiftPast().getTarehouseId(), shift
						.getGoods().getGoodsId(), shift.getShiftWeight(), shift
						.getShiftNumber());
				// 相应的库存增加
				this.tarehouseGoodsService.zengGoods(shift
						.getTarehouseByShiftNew().getTarehouseId(), shift
						.getGoods().getGoodsId(), shift.getShiftWeight(), shift
						.getShiftNumber());

				// 批次发生变化
				// 通过库位和货物进行查询批次

				List<TarehouseDetail> tdlist = this.tarehouseDetailService
						.getGoodsTarehouseData(shift.getTarehouseByShiftPast()
								.getTarehouseId(), shift.getGoods()
								.getGoodsId());
				for (int i = 0; i < tdlist.size(); i++) {
					tdlist.get(i).setTdetailTarehouse(
							shift.getTarehouseByShiftNew());// 将批次设置为新的库位
					this.tarehouseDetailService.update(tdlist.get(i));
				}

				boolean ok = this.duanDaoService.update(shift);
				if (ok) {
					request.getSession().setAttribute("nuokuMessage", "提交成功！");
					// 将这一操作记录在登录日志中
					this.loginLogService.updateLogin(request.getSession()
							.getAttribute("loginlogId").toString(),
							"短倒完成" + sf.getShiftId());
				}
			} else {
				request.getSession().setAttribute("nuokuMessage", "提交失败！");
			}
		} else {
			request.getSession().setAttribute("nuokuMessage", "提交失败！");
		}
		PrintWriter out = response.getWriter();
		out.print("<script>alert('"
				+ request.getSession().getAttribute("nuokuMessage")
				+ "');window.history.go(-2);</script>");// document.location.href='/XGProject/cangchu/page/caozuodingdan_main.jsp'
		// 返回到相应的界面
		return null;
	}

	// 查询所有的短倒的订单
	public ActionForward ShiftgetAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DuanDaoForm sf = (DuanDaoForm) form;
		if (sf.getBegin() == null) {
			sf.setBegin(pt.getNowJianYi());
		} else {
			sf.setBegin(sf.getBegin() + " 00:00:00");
		}
		if (sf.getFinish() == null || sf.getFinish().equals("")) {
			sf.setFinish(pt.getTimes());
		} else {
			sf.setFinish(sf.getFinish() + " 23:59:59");
		}
		if (sf.getGoodsName() == null) {
			sf.setGoodsName("");
		}
		if (sf.getGoodsSign() == null) {
			sf.setGoodsSign("");
		}
		if (sf.getGoodsStandard() == null) {
			sf.setGoodsStandard("");
		}
		if (sf.getGoodsQuality() == null) {
			sf.setGoodsQuality("");
		}
		if (sf.getGoodsProperty() == null) {
			sf.setGoodsProperty("");
		}
		if (sf.getKuName() == null) {
			sf.setKuName("");
		}
		int pageNow = 1;
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow")
					.toString());// 获取当前页
		}

		int pageCount = this.duanDaoService.getShiftAllByPageCount(
				sf.getBegin(), sf.getFinish(), sf.getGoodsName(),
				sf.getGoodsStandard(), sf.getGoodsQuality(),
				sf.getGoodsProperty(), sf.getGoodsSign(), sf.getKuName(), 20);
		JSONArray array = new JSONArray();
		PrintWriter out = response.getWriter();
		if (pageNow >= pageCount) {
			pageNow = pageCount;
		}
		if (pageNow <= 1) {
			pageNow = 1;
		}

		List<DuanDao> slist = this.duanDaoService.getShiftAllByPage(
				sf.getBegin(), sf.getFinish(), sf.getGoodsName(),
				sf.getGoodsStandard(), sf.getGoodsQuality(),
				sf.getGoodsProperty(), sf.getGoodsSign(), sf.getKuName(),
				pageNow, 20);

		if (slist.size() <= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}

		for (DuanDao s : slist) {
			JSONObject obj = new JSONObject();
			obj.put("id", s.getShiftId());// 保存挪库的编号
			obj.put("faqiren", s.getInteriorUserByShiftFounderMember()
					.getIuserName());// 保存发起人的名字
			obj.put("time", s.getShiftTime());// 保存发起时间
			obj.put("kuwei", s.getTarehouseByShiftPast().getTarehouseName());// 保存库位的名称
			obj.put("xinkuwei", s.getTarehouseByShiftNew().getTarehouseName());// 保存新库位名称
			obj.put("pageNow", pageNow);// 保存当前页
			obj.put("result", "notnull");
			array.add(obj);
		}

		out.print(array.toString());
		out.flush();
		out.close();
		// 保存request中
		return null;
	}

	// 当点击查看短倒订单的详细的时候调用
	public ActionForward getNuoKuMingXi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DuanDaoForm sf = (DuanDaoForm) form;
		DuanDao shift = this.duanDaoService.getShiftId(URLDecoder.decode(
				sf.getShiftId(), "UTF-8"));// 通过id查询出挪库的订单

		request.setAttribute("shift", shift);

		// 返回到相应的界面
		return mapping.findForward("goNuoKuMingXi");
	}

	// 删除短倒信息
	public ActionForward deleteShift(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DuanDaoForm sf = (DuanDaoForm) form;
		if (sf != null) {
			DuanDao shift = this.duanDaoService.getShiftId(sf.getShiftId());// 通过id查询
			if (shift != null) {
				boolean ok = this.duanDaoService.delete(shift);
				if (ok) {
					request.getSession().setAttribute("deleteShift", "作废成功！");
					// 将这一操作记录在日志表中
					this.loginLogService.updateLogin(request.getSession()
							.getAttribute("loginlogId").toString(), "删除挪库信息"
							+ sf.getShiftId());
				} else {
					request.getSession().setAttribute("deleteShift", "作废失败！");
				}
			} else {
				request.getSession().setAttribute("deleteShift", "作废失败！");
			}
		} else {
			request.getSession().setAttribute("deleteShift", "作废失败！");
		}
		// 返回到相应的界面
		PrintWriter out = response.getWriter();
		out.print("<script>alert('"
				+ request.getSession().getAttribute("deleteShift")
				+ "'); window.history.go(-2);</script>");
		return null;
	}

}
