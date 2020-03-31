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

import com.xinggang.project.entity.DuanDao;
import com.xinggang.project.entity.ExportOperate;
import com.xinggang.project.entity.InputOperate;
import com.xinggang.project.entity.InteriorUser;
import com.xinggang.project.entity.Shift;
import com.xinggang.project.entity.ShiftStockSeed;
import com.xinggang.project.entity.Working;
import com.xinggang.project.form.WorkCountForm;
import com.xinggang.project.service.DuanDaoService;
import com.xinggang.project.service.ExportOperateService;
import com.xinggang.project.service.InputOperateService;
import com.xinggang.project.service.InteriorUserService;
import com.xinggang.project.service.ShiftService;
import com.xinggang.project.service.ShiftStockSeedService;
import com.xinggang.project.service.WorkingService;
import com.xinggang.project.tools.PresentTime;

//通过量统计action
public class WorkCountAction extends DispatchAction {

	// 出库操作订单service
	private ExportOperateService exportOperateService;
	// 入库操作订单service
	private InputOperateService inputOperateService;
	// 过户子订单service
	private ShiftStockSeedService shiftStockSeedService;
	// 挪库订单service
	private ShiftService shiftService;
	// 时间工具类
	private PresentTime pt = new PresentTime();
	// 费系统操作员service
	private WorkingService workingService;
	// 内部人员service
	private InteriorUserService interiorUserService;
	// 短倒service
	private DuanDaoService duanDaoService;

	public void setDuanDaoService(DuanDaoService duanDaoService) {
		this.duanDaoService = duanDaoService;
	}

	public void setWorkingService(WorkingService workingService) {
		this.workingService = workingService;
	}

	public void setInteriorUserService(InteriorUserService interiorUserService) {
		this.interiorUserService = interiorUserService;
	}

	public void setShiftService(ShiftService shiftService) {
		this.shiftService = shiftService;
	}

	public void setShiftStockSeedService(
			ShiftStockSeedService shiftStockSeedService) {
		this.shiftStockSeedService = shiftStockSeedService;
	}

	public void setInputOperateService(InputOperateService inputOperateService) {
		this.inputOperateService = inputOperateService;
	}

	public void setExportOperateService(
			ExportOperateService exportOperateService) {
		this.exportOperateService = exportOperateService;
	}

	// 统计入库的量
	public ActionForward QueryInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WorkCountForm wcf = (WorkCountForm) form;
		if (wcf.getBegin() == null || wcf.getBegin().equals("")) {
			wcf.setBegin(pt.getNowJianYi());
		} else {
			wcf.setBegin(wcf.getBegin() + " 00:00:00");
		}
		if (wcf.getFinish() == null || wcf.getFinish().equals("")) {
			wcf.setFinish(pt.getTimes());
		} else {
			wcf.setFinish(wcf.getFinish() + " 23:59:59");
		}
		if (wcf.getName() == null) {
			wcf.setName("");
		}
		if (wcf.getZhiwu() == null) {
			wcf.setZhiwu("1");
		}

		int pageNow = 1;
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}
		int pageCount = this.inputOperateService.QueryInputWorkByPageCount(
				wcf.getBegin(), wcf.getFinish(), wcf.getZhiwu(), wcf.getName(),
				20);
		System.out.println("总页数是；" + pageCount);
		if (pageNow >= pageCount) {
			pageNow = pageCount;
		}
		if (pageNow <= 1) {
			pageNow = 1;
		}
		List<InputOperate> iolist = this.inputOperateService
				.QueryInputWorkByPage(wcf.getBegin(), wcf.getFinish(),
						wcf.getZhiwu(), wcf.getName(), pageNow, 20);
		PrintWriter out = response.getWriter();
		JSONArray array = new JSONArray();
		if (iolist.size() <= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		String name = wcf.getName();
		Double inputheji = 0.0;
		Double exportheji = 0.0;
		Double shiftstockheji = 0.0;
		Double shiftheji = 0.0;
		DecimalFormat df = new DecimalFormat("###########0.000");

		if (wcf.getName() != null && wcf.getName().equals("") == false
				&& wcf.getZhiwu() != null) {
			try {
				inputheji = this.inputOperateService.QueryInputHeJi(
						wcf.getBegin(), wcf.getFinish(), wcf.getZhiwu(),
						wcf.getName());
				exportheji = this.inputOperateService.QueryExportHeJi(
						wcf.getBegin(), wcf.getFinish(), wcf.getZhiwu(),
						wcf.getName());
				shiftstockheji = this.inputOperateService.QueryShiftStockHeJi(
						wcf.getBegin(), wcf.getFinish(), wcf.getZhiwu(),
						wcf.getName());
				shiftheji = this.inputOperateService.QueryShiftHeJi(
						wcf.getBegin(), wcf.getFinish(), wcf.getZhiwu(),
						wcf.getName());
			} catch (Exception e) {
			}
		}
		for (int i = 0; i < iolist.size(); i++) {
			JSONObject obj = new JSONObject();
			// 判断名字的匹配项
			if (wcf.getZhiwu().equals("1")) {
				name = iolist.get(i).getInteriorUserByIoperateDispatcherId()
						.getIuserName();
			}
			if (wcf.getZhiwu().equals("2")) {
				name = iolist.get(i).getInteriorUserByIoperateStoremanId()
						.getIuserName();
			}
			if (wcf.getZhiwu().equals("3")) {
				name = iolist.get(i)
						.getInteriorUserByIoperatePonderationManId()
						.getIuserName();
			}
			if (wcf.getZhiwu().equals("4")) {
				name = iolist.get(i).getInteriorUserByIoperateAuditingId()
						.getIuserName();
			}
			if (wcf.getZhiwu().equals("5")) {
				name = iolist.get(i).getInteriorUserByIoperateCollectManId()
						.getIuserName();
			}
			if (wcf.getZhiwu().equals("6")) {
				name = iolist.get(i).getIoperateCraneman();
			}
			if (wcf.getZhiwu().equals("7")) {
				name = iolist.get(i).getIoperateStevedore();
			}

			obj.put("name", name);// 保存统计人的名字
			obj.put("time", iolist.get(i).getIoperateDispatcherTime());// 保存订单的日期，调度分配的日期
			obj.put("dingdanhao", iolist.get(i).getInputSeed().getInput()
					.getInputId());// 保存订单号
			obj.put("type", "入库");// 保存订单类型为入库
			obj.put("workweight",
					iolist.get(i).getIoperateRealityWeight() == null ? 0
							: iolist.get(i).getIoperateRealityWeight());// 保存作业重量
			obj.put("worknumber",
					iolist.get(i).getIoperateRealityNumber() == null ? 0
							: iolist.get(i).getIoperateRealityNumber());// 保存作业件数
			obj.put("rukuheji", inputheji == null ? 0.0 : df.format(inputheji));
			obj.put("chukuheji",
					exportheji == null ? 0.0 : df.format(exportheji));
			obj.put("guohuheji",
					shiftstockheji == null ? 0.0 : df.format(shiftstockheji));
			obj.put("nuokuheji", shiftheji == null ? 0.0 : df.format(shiftheji));
			obj.put("result", "notnull");
			obj.put("pageNow", pageNow);
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
		return null;
	}

	// 统计出库的量
	public ActionForward QueryExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WorkCountForm wcf = (WorkCountForm) form;
		if (wcf.getBegin() == null || wcf.getBegin().equals("")) {
			wcf.setBegin(pt.getNowJianYi());
		} else {
			wcf.setBegin(wcf.getBegin() + " 00:00:00");
		}
		if (wcf.getFinish() == null || wcf.getFinish().equals("")) {
			wcf.setFinish(pt.getTimes());
		} else {
			wcf.setFinish(wcf.getFinish() + " 23:59:59");
		}
		if (wcf.getName() == null) {
			wcf.setName("");
		}
		if (wcf.getZhiwu() == null) {
			wcf.setZhiwu("1");
		}

		int pageNow = 1;
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}
		int pageCount = this.exportOperateService.QueryExportWorkByPageCount(
				wcf.getBegin(), wcf.getFinish(), wcf.getZhiwu(), wcf.getName(),
				20);
		if (pageNow >= pageCount) {
			pageNow = pageCount;
		}
		if (pageNow <= 1) {
			pageNow = 1;
		}
		List<ExportOperate> eolist = this.exportOperateService
				.QueryExportWorkByPage(wcf.getBegin(), wcf.getFinish(),
						wcf.getZhiwu(), wcf.getName(), pageNow, 20);
		PrintWriter out = response.getWriter();
		JSONArray array = new JSONArray();
		if (eolist.size() <= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		String name = wcf.getName();
		Double inputheji = 0.0;
		Double exportheji = 0.0;
		Double shiftstockheji = 0.0;
		Double shiftheji = 0.0;
		DecimalFormat df = new DecimalFormat("###########0.000");

		if (wcf.getName() != null && wcf.getName().equals("") == false
				&& wcf.getZhiwu() != null) {
			try {
				inputheji = this.inputOperateService.QueryInputHeJi(
						wcf.getBegin(), wcf.getFinish(), wcf.getZhiwu(),
						wcf.getName());
				exportheji = this.inputOperateService.QueryExportHeJi(
						wcf.getBegin(), wcf.getFinish(), wcf.getZhiwu(),
						wcf.getName());
				shiftstockheji = this.inputOperateService.QueryShiftStockHeJi(
						wcf.getBegin(), wcf.getFinish(), wcf.getZhiwu(),
						wcf.getName());
				shiftheji = this.inputOperateService.QueryShiftHeJi(
						wcf.getBegin(), wcf.getFinish(), wcf.getZhiwu(),
						wcf.getName());
			} catch (Exception e) {
			}
		}
		for (int i = 0; i < eolist.size(); i++) {
			JSONObject obj = new JSONObject();
			// 判断名字的匹配项
			if (wcf.getZhiwu().equals("1")) {
				name = eolist.get(i).getInteriorUserByEoperateDispatcher()
						.getIuserName();
			}
			if (wcf.getZhiwu().equals("2")) {
				name = eolist.get(i).getInteriorUserByEoperateStoreman()
						.getIuserName();
			}
			if (wcf.getZhiwu().equals("3")) {
				name = eolist.get(i).getInteriorUserByEoperatePonderationMan()
						.getIuserName();
			}
			if (wcf.getZhiwu().equals("4")) {
				name = eolist.get(i).getInteriorUserByEoperateAuditing()
						.getIuserName();
			}
			if (wcf.getZhiwu().equals("5")) {
				name = eolist.get(i).getInteriorUserByEoperateCollectMan()
						.getIuserName();
			}
			if (wcf.getZhiwu().equals("6")) {
				name = eolist.get(i).getEoperateCraneman();
			}
			if (wcf.getZhiwu().equals("7")) {
				name = eolist.get(i).getEoperateStevedore();
			}

			obj.put("name", name);// 保存统计人的名字
			obj.put("time", eolist.get(i).getEoperateDispatcherTime());// 保存订单的日期，调度分配的日期
			obj.put("dingdanhao", eolist.get(i).getExportSeed().getExport()
					.getExportId());// 保存订单号
			obj.put("type", "出库");// 保存订单类型为入库
			obj.put("workweight",
					eolist.get(i).getEoperateRealityWeight() == null ? 0
							: eolist.get(i).getEoperateRealityWeight());// 保存作业重量
			obj.put("worknumber",
					eolist.get(i).getEoperateRealityNumber() == null ? 0
							: eolist.get(i).getEoperateRealityNumber());// 保存作业件数
			obj.put("rukuheji", inputheji == null ? 0.0 : df.format(inputheji));
			obj.put("chukuheji",
					exportheji == null ? 0.0 : df.format(exportheji));
			obj.put("guohuheji",
					shiftstockheji == null ? 0.0 : df.format(shiftstockheji));
			obj.put("nuokuheji", shiftheji == null ? 0.0 : df.format(shiftheji));
			obj.put("result", "notnull");
			obj.put("pageNow", pageNow);
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
		return null;
	}

	// 统计过户的量
	public ActionForward QueryShiftStock(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		WorkCountForm wcf = (WorkCountForm) form;
		if (wcf.getBegin() == null || wcf.getBegin().equals("")) {
			wcf.setBegin(pt.getNowJianYi());
		} else {
			wcf.setBegin(wcf.getBegin() + " 00:00:00");
		}
		if (wcf.getFinish() == null || wcf.getFinish().equals("")) {
			wcf.setFinish(pt.getTimes());
		} else {
			wcf.setFinish(wcf.getFinish() + " 23:59:59");
		}
		if (wcf.getName() == null) {
			wcf.setName("");
		}
		if (wcf.getZhiwu() == null) {
			wcf.setZhiwu("1");
		}

		int pageNow = 1;
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}
		int pageCount = this.shiftStockSeedService
				.QueryShiftStockWorkByPageCount(wcf.getBegin(),
						wcf.getFinish(), wcf.getZhiwu(), wcf.getName(), 20);
		if (pageNow >= pageCount) {
			pageNow = pageCount;
		}
		if (pageNow <= 1) {
			pageNow = 1;
		}
		List<ShiftStockSeed> ssslist = this.shiftStockSeedService
				.QueryShiftStockWorkByPage(wcf.getBegin(), wcf.getFinish(),
						wcf.getZhiwu(), wcf.getName(), pageNow, 20);
		PrintWriter out = response.getWriter();
		JSONArray array = new JSONArray();
		if (ssslist.size() <= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		String name = wcf.getName();
		Double inputheji = 0.0;
		Double exportheji = 0.0;
		Double shiftstockheji = 0.0;
		Double shiftheji = 0.0;
		DecimalFormat df = new DecimalFormat("###########0.000");

		if (wcf.getName() != null && wcf.getName().equals("") == false
				&& wcf.getZhiwu() != null) {
			try {
				inputheji = this.inputOperateService.QueryInputHeJi(
						wcf.getBegin(), wcf.getFinish(), wcf.getZhiwu(),
						wcf.getName());
				exportheji = this.inputOperateService.QueryExportHeJi(
						wcf.getBegin(), wcf.getFinish(), wcf.getZhiwu(),
						wcf.getName());
				shiftstockheji = this.inputOperateService.QueryShiftStockHeJi(
						wcf.getBegin(), wcf.getFinish(), wcf.getZhiwu(),
						wcf.getName());
				shiftheji = this.inputOperateService.QueryShiftHeJi(
						wcf.getBegin(), wcf.getFinish(), wcf.getZhiwu(),
						wcf.getName());
			} catch (Exception e) {
			}
		}
		for (int i = 0; i < ssslist.size(); i++) {
			JSONObject obj = new JSONObject();
			// 判断名字的匹配项
			if (wcf.getZhiwu().equals("4")) {
				name = ssslist.get(i).getInteriorUserBySsseedAuditing()
						.getIuserName();
			}
			if (wcf.getZhiwu().equals("5")) {
				name = ssslist.get(i).getInteriorUserBySsseedCollectMan()
						.getIuserName();
			}

			obj.put("name", name);// 保存统计人的名字
			obj.put("time", ssslist.get(i).getSsseedAuditingTime());// 保存订单的日期，调度分配的日期
			obj.put("dingdanhao", ssslist.get(i).getShiftStock().getSstockId());// 保存订单号
			obj.put("type", "过户");// 保存订单类型为入库
			obj.put("workweight", ssslist.get(i).getSsseedWeight() == null ? 0
					: ssslist.get(i).getSsseedWeight());// 保存作业重量
			obj.put("worknumber", 0);// 保存作业件数
			obj.put("rukuheji", inputheji == null ? 0.0 : df.format(inputheji));
			obj.put("chukuheji",
					exportheji == null ? 0.0 : df.format(exportheji));
			obj.put("guohuheji",
					shiftstockheji == null ? 0.0 : df.format(shiftstockheji));
			obj.put("nuokuheji", shiftheji == null ? 0.0 : df.format(shiftheji));
			obj.put("result", "notnull");
			obj.put("pageNow", pageNow);
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
		return null;
	}

	// 统计挪库的量
	public ActionForward QueryShift(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WorkCountForm wcf = (WorkCountForm) form;
		if (wcf.getBegin() == null || wcf.getBegin().equals("")) {
			wcf.setBegin(pt.getNowJianYi());
		} else {
			wcf.setBegin(wcf.getBegin() + " 00:00:00");
		}
		if (wcf.getFinish() == null || wcf.getFinish().equals("")) {
			wcf.setFinish(pt.getTimes());
		} else {
			wcf.setFinish(wcf.getFinish() + " 20:59:59");
		}
		if (wcf.getName() == null) {
			wcf.setName("");
		}
		if (wcf.getZhiwu() == null) {
			wcf.setZhiwu("1");
		}

		int pageNow = 1;
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}
		int pageCount = this.shiftService.QueryShiftWorkByPageCount(
				wcf.getBegin(), wcf.getFinish(), wcf.getZhiwu(), wcf.getName(),
				20);
		if (pageNow >= pageCount) {
			pageNow = pageCount;
		}
		if (pageNow <= 1) {
			pageNow = 1;
		}
		List<Shift> slist = this.shiftService.QueryShiftWorkByPage(
				wcf.getBegin(), wcf.getFinish(), wcf.getZhiwu(), wcf.getName(),
				pageNow, 20);
		PrintWriter out = response.getWriter();
		JSONArray array = new JSONArray();
		if (slist.size() <= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		String name = wcf.getName();
		Double inputheji = 0.0;
		Double exportheji = 0.0;
		Double shiftstockheji = 0.0;
		Double shiftheji = 0.0;
		DecimalFormat df = new DecimalFormat("###########0.000");

		if (wcf.getName() != null && wcf.getName().equals("") == false
				&& wcf.getZhiwu() != null) {
			try {
				inputheji = this.inputOperateService.QueryInputHeJi(
						wcf.getBegin(), wcf.getFinish(), wcf.getZhiwu(),
						wcf.getName());
				exportheji = this.inputOperateService.QueryExportHeJi(
						wcf.getBegin(), wcf.getFinish(), wcf.getZhiwu(),
						wcf.getName());
				shiftstockheji = this.inputOperateService.QueryShiftStockHeJi(
						wcf.getBegin(), wcf.getFinish(), wcf.getZhiwu(),
						wcf.getName());
				shiftheji = this.inputOperateService.QueryShiftHeJi(
						wcf.getBegin(), wcf.getFinish(), wcf.getZhiwu(),
						wcf.getName());
			} catch (Exception e) {
			}
		}
		for (int i = 0; i < slist.size(); i++) {
			JSONObject obj = new JSONObject();
			// 判断名字的匹配项
			if (wcf.getZhiwu().equals("1")) {
				name = slist.get(i).getInteriorUserByShiftFounderMember()
						.getIuserName();
			}
			if (wcf.getZhiwu().equals("2")) {
				name = slist.get(i).getInteriorUserByShiftExecutor()
						.getIuserName();
			}
			if (wcf.getZhiwu().equals("6")) {
				name = slist.get(i).getShiftCraneman();
			}
			if (wcf.getZhiwu().equals("7")) {
				name = slist.get(i).getShiftStevedore();
			}

			obj.put("name", name);// 保存统计人的名字
			obj.put("time", slist.get(i).getShiftTime());// 保存订单的日期，调度分配的日期
			obj.put("dingdanhao", slist.get(i).getShiftId());// 保存订单号
			obj.put("type", "挪库");// 保存订单类型为入库
			obj.put("workweight", slist.get(i).getShiftWeight() == null ? 0
					: slist.get(i).getShiftWeight());// 保存作业重量
			obj.put("worknumber", slist.get(i).getShiftNumber());// 保存作业件数
			obj.put("rukuheji", inputheji == null ? 0.0 : df.format(inputheji));
			obj.put("chukuheji",
					exportheji == null ? 0.0 : df.format(exportheji));
			obj.put("guohuheji",
					shiftstockheji == null ? 0.0 : df.format(shiftstockheji));
			obj.put("nuokuheji", shiftheji == null ? 0.0 : df.format(shiftheji));
			obj.put("result", "notnull");
			obj.put("pageNow", pageNow);
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
		return null;
	}

	// 统计短倒的量
	public ActionForward QueryDuanDao(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WorkCountForm wcf = (WorkCountForm) form;
		if (wcf.getBegin() == null || wcf.getBegin().equals("")) {
			wcf.setBegin(pt.getNowJianYi());
		} else {
			wcf.setBegin(wcf.getBegin() + " 00:00:00");
		}
		if (wcf.getFinish() == null || wcf.getFinish().equals("")) {
			wcf.setFinish(pt.getTimes());
		} else {
			wcf.setFinish(wcf.getFinish() + " 20:59:59");
		}
		if (wcf.getName() == null) {
			wcf.setName("");
		}
		if (wcf.getZhiwu() == null) {
			wcf.setZhiwu("1");
		}

		int pageNow = 1;
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}
		int pageCount = this.duanDaoService.QueryShiftWorkByPageCount(
				wcf.getBegin(), wcf.getFinish(), wcf.getZhiwu(), wcf.getName(),
				20);
		if (pageNow >= pageCount) {
			pageNow = pageCount;
		}
		if (pageNow <= 1) {
			pageNow = 1;
		}
		List<DuanDao> slist = this.duanDaoService.QueryShiftWorkByPage(
				wcf.getBegin(), wcf.getFinish(), wcf.getZhiwu(), wcf.getName(),
				pageNow, 20);
		PrintWriter out = response.getWriter();
		JSONArray array = new JSONArray();
		if (slist.size() <= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		String name = wcf.getName();
		Double inputheji = 0.0;
		Double exportheji = 0.0;
		Double shiftstockheji = 0.0;
		Double shiftheji = 0.0;
		DecimalFormat df = new DecimalFormat("###########0.000");

		if (wcf.getName() != null && wcf.getName().equals("") == false
				&& wcf.getZhiwu() != null) {
			try {
				inputheji = this.inputOperateService.QueryInputHeJi(
						wcf.getBegin(), wcf.getFinish(), wcf.getZhiwu(),
						wcf.getName());
				exportheji = this.inputOperateService.QueryExportHeJi(
						wcf.getBegin(), wcf.getFinish(), wcf.getZhiwu(),
						wcf.getName());
				shiftstockheji = this.inputOperateService.QueryShiftStockHeJi(
						wcf.getBegin(), wcf.getFinish(), wcf.getZhiwu(),
						wcf.getName());
				shiftheji = this.inputOperateService.QueryShiftHeJi(
						wcf.getBegin(), wcf.getFinish(), wcf.getZhiwu(),
						wcf.getName());
			} catch (Exception e) {
			}
		}
		for (int i = 0; i < slist.size(); i++) {
			JSONObject obj = new JSONObject();
			// 判断名字的匹配项
			if (wcf.getZhiwu().equals("1")) {
				name = slist.get(i).getInteriorUserByShiftFounderMember()
						.getIuserName();
			}
			if (wcf.getZhiwu().equals("2")) {
				name = slist.get(i).getInteriorUserByShiftExecutor()
						.getIuserName();
			}
			if (wcf.getZhiwu().equals("6")) {
				name = slist.get(i).getShiftCraneman();
			}
			if (wcf.getZhiwu().equals("7")) {
				name = slist.get(i).getShiftStevedore();
			}
			if (wcf.getZhiwu().equals("8")) {
				name = slist.get(i).getDriverName();
			}

			obj.put("name", name);// 保存统计人的名字
			obj.put("time", slist.get(i).getShiftTime());// 保存订单的日期，调度分配的日期
			obj.put("dingdanhao", slist.get(i).getShiftId());// 保存订单号
			obj.put("type", "挪库");// 保存订单类型为入库
			obj.put("workweight", slist.get(i).getShiftWeight() == null ? 0
					: slist.get(i).getShiftWeight());// 保存作业重量
			obj.put("worknumber", slist.get(i).getShiftNumber());// 保存作业件数
			obj.put("rukuheji", inputheji == null ? 0.0 : df.format(inputheji));
			obj.put("chukuheji",
					exportheji == null ? 0.0 : df.format(exportheji));
			obj.put("guohuheji",
					shiftstockheji == null ? 0.0 : df.format(shiftstockheji));
			obj.put("nuokuheji", shiftheji == null ? 0.0 : df.format(shiftheji));
			obj.put("result", "notnull");
			obj.put("pageNow", pageNow);
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
		return null;
	}

	// 通过相应的角色查询对应的工作了，有8个角色，调度，保管，司磅，审核，收费，天车，装卸，短倒司机
	public ActionForward getJueSeWorkWeight(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		WorkCountForm wcf = (WorkCountForm) form;// 表达上传form
		JSONArray array = new JSONArray();// json数组
		PrintWriter out = response.getWriter();// 输出
		// 起始日期的判断
		if (wcf.getBegin() == null || wcf.getBegin().equals("")) {
			wcf.setBegin(pt.getNowJianYi());
		} else {
			wcf.setBegin(wcf.getBegin());
		}
		// 结束日期的判断
		if (wcf.getFinish() == null || wcf.getFinish().equals("")) {
			wcf.setFinish(pt.getTimes());
		} else {
			wcf.setFinish(wcf.getFinish());
		}

		Double inputheji = 0.0; // 入库合计
		Double exportheji = 0.0;// 出库合计
		Double shiftstockheji = 0.0;// 过户合计
		Double shiftheji = 0.0;// 挪库合计
		Double duandaoheji = 0.0;// 短倒合计
		DecimalFormat df = new DecimalFormat("###########0.000");
		Double sum = 0.0;// 总和
		String zhiwu = "";// 职务的缓存

		// 通过职务进行遍历
		for (int i = 1; i <= 8; i++) {
			switch (i) {
			case 1:
				zhiwu = "调度员";
				break;
			case 2:
				zhiwu = "保管员";
				break;
			case 3:
				zhiwu = "司磅员";
				break;
			case 4:
				zhiwu = "审核员";
				break;
			case 5:
				zhiwu = "收费员";
				break;
			case 6:
				zhiwu = "天车员";
				break;
			case 7:
				zhiwu = "装卸员";
				break;
			case 8:
				zhiwu = "短倒司机";
				break;
			}
			// 通过对应的职务进行统计相应的工作量
			// 统计对应职务入库量的合计
			inputheji = this.workingService.RuKuWorkWeight(wcf.getBegin(),
					wcf.getFinish(), String.valueOf(i));
			// 统计对应职务出库量的合计
			exportheji = this.workingService.ChuKuWorkWeight(wcf.getBegin(),
					wcf.getFinish(), String.valueOf(i));
			// 统计对应职务过户量的合计
			shiftstockheji = this.workingService.GuoHuWorkWeight(
					wcf.getBegin(), wcf.getFinish(), String.valueOf(i));
			// 统计对应职务挪库量的合计
			shiftheji = this.workingService.NuoKuWorkWeight(wcf.getBegin(),
					wcf.getFinish(), String.valueOf(i));
			// 统计对应职务短倒量的合计
			duandaoheji = this.workingService.DuanDaoWorkWeight(wcf.getBegin(),
					wcf.getFinish(), String.valueOf(i));
			// 统计总合计
			sum = inputheji + exportheji + shiftstockheji + shiftheji
					+ duandaoheji;

			JSONObject obj = new JSONObject();
			obj.put("zhiwu", zhiwu);// 保存对应的职务
			obj.put("begin", wcf.getBegin());// 保存起始日期
			obj.put("finish", wcf.getFinish());// 保存结束日期
			obj.put("ruku", df.format(inputheji));// 入库合计
			obj.put("chuku", df.format(exportheji));// 出库合计
			obj.put("guohu", df.format(shiftstockheji));// 过户合计
			obj.put("nuoku", df.format(shiftheji));// 挪库合计
			obj.put("duandao", df.format(duandaoheji));// 短倒合计
			obj.put("sum", df.format(sum));
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();

		return null;
	}

	// 通过单个角色统计工作量，通过姓名，开始时间，结束时间进行统计
	public ActionForward getDanGeJueSeWorkWeight(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		WorkCountForm wcf = (WorkCountForm) form;// 表达上传form
		JSONArray array = new JSONArray();// json数组
		PrintWriter out = response.getWriter();// 输出
		// 起始日期的判断
		if (wcf.getBegin() == null || wcf.getBegin().equals("")) {
			wcf.setBegin(pt.getNowJianYi());
		} else {
			wcf.setBegin(wcf.getBegin());
		}
		// 结束日期的判断
		if (wcf.getFinish() == null || wcf.getFinish().equals("")) {
			wcf.setFinish(pt.getTimes());
		} else {
			wcf.setFinish(wcf.getFinish());
		}
		// 人员姓名
		if (wcf.getName() == null) {
			wcf.setName("");
		}

		/*
		 * String zhiwu = wcf.getZhiwu();
		 * 
		 * Double inputheji = 0.0; // 入库合计 Double exportheji = 0.0;// 出库合计
		 * Double shiftstockheji = 0.0;// 过户合计 Double shiftheji = 0.0;// 挪库合计
		 * Double duandaoheji = 0.0;// 短倒合计 DecimalFormat df = new
		 * DecimalFormat("###########0.000"); Double sum = 0.0;// 总和
		 */
		List<InteriorUser> iu = this.interiorUserService.getName(wcf.getName());
		if (iu != null && iu.size() > 0) {
			for (int i = 0; i < iu.size(); i++) {

			}
		} else {
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
		}
		return null;
	}

	// 通过内部人员和非系统工作人员进行遍历，将统计的结果进行导出
	public ActionForward getExcelCount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WorkCountForm wcf = (WorkCountForm) form;

		// 起始日期的判断
		if (wcf.getBegin() == null || wcf.getBegin().equals("")) {
			wcf.setBegin(pt.getNowJianYi());
		} else {
			wcf.setBegin(wcf.getBegin());
		}
		// 结束日期的判断
		if (wcf.getFinish() == null || wcf.getFinish().equals("")) {
			wcf.setFinish(pt.getTimes());
		} else {
			wcf.setFinish(wcf.getFinish());
		}
		if (wcf.getName() == null) {
			wcf.setName("");
		}

		PrintWriter out = response.getWriter();// 输出
		JSONArray array = new JSONArray();// json

		Double inputheji = 0.0; // 入库合计
		Double exportheji = 0.0;// 出库合计
		Double shiftstockheji = 0.0;// 过户合计
		Double shiftheji = 0.0;// 挪库合计
		Double duandaoheji = 0.0;// 短倒合计
		DecimalFormat df = new DecimalFormat("###########0.000");
		Double sum = 0.0;// 总和
		String zhiwu = "";// 职务的缓存

		// 首先外层通过7ge职务进行遍历
		for (int i = 1; i <= 8; i++) {
			// 遍历是什么职务
			switch (i) {
			case 1:
				zhiwu = "调度员";
				break;
			case 2:
				zhiwu = "保管员";
				break;
			case 3:
				zhiwu = "司磅员";
				break;
			case 4:
				zhiwu = "审核员";
				break;
			case 5:
				zhiwu = "收费员";
				break;
			case 6:
				zhiwu = "天车员";
				break;
			case 7:
				zhiwu = "装卸员";
				break;
			case 8:
				zhiwu = "短倒司机";
				break;
			}
			if (i >= 1 && i <= 5) {
				// 如果是、调度员、保管员、司磅员、审核员、收费员的时候通过内部人员的名字查询，否则通过非系统工作人员查询
				// 首先查询职务是调度的
				// 查询出全部的内部人员用一个人一个人的去统计这个人员的工作量
				// 将职务对应的重量和人名保存起来
				List<InteriorUser> iu = this.interiorUserService.getName(wcf
						.getName());
				for (int j = 0; j < iu.size(); j++) {
					inputheji = this.inputOperateService.QueryInputHeJi(
							wcf.getBegin(), wcf.getFinish(), String.valueOf(i),
							iu.get(j).getIuserName());
					exportheji = this.inputOperateService.QueryExportHeJi(
							wcf.getBegin(), wcf.getFinish(), String.valueOf(i),
							iu.get(j).getIuserName());
					shiftstockheji = this.inputOperateService
							.QueryShiftStockHeJi(wcf.getBegin(),
									wcf.getFinish(), String.valueOf(i),
									iu.get(j).getIuserName());
					shiftheji = this.inputOperateService.QueryShiftHeJi(
							wcf.getBegin(), wcf.getFinish(), String.valueOf(i),
							iu.get(j).getIuserName());
					duandaoheji = this.inputOperateService.QueryDuanDaoHeJi(
							wcf.getBegin(), wcf.getFinish(), String.valueOf(i),
							iu.get(j).getIuserName());
					sum = (inputheji == null ? 0.0 : inputheji)
							+ (exportheji == null ? 0.0 : exportheji)
							+ (shiftstockheji == null ? 0.0 : shiftstockheji)
							+ (shiftheji == null ? 0.0 : shiftheji)
							+ (duandaoheji == null ? 0.0 : duandaoheji);
					if (sum > 0) {
						JSONObject obj = new JSONObject();
						obj.put("zhiwu", zhiwu);// 保存职务，是什么职务
						obj.put("begin", wcf.getBegin());
						obj.put("finish", wcf.getFinish());
						obj.put("name", iu.get(j).getIuserName());// 保存人名
						obj.put("ruku", df.format((inputheji == null ? 0.0
								: inputheji)));// 入库合计
						obj.put("chuku", df.format((exportheji == null ? 0.0
								: exportheji)));// 出库合计
						obj.put("guohu", df
								.format((shiftstockheji == null ? 0.0
										: shiftstockheji)));// 过户合计
						obj.put("nuoku",
								df.format(shiftheji == null ? 0.0 : shiftheji));// 挪库合计
						obj.put("duandao", df.format((duandaoheji == null ? 0.0
								: duandaoheji)));
						obj.put("sum", df.format(sum));// 总和
						array.add(obj);
					}
				}

			} else if (i > 5 && i <= 7) {
				// 通过非系统工作人员查询
				// 查询全部的非工作人员
				List<Working> work = this.workingService.getWorkingName(wcf
						.getName());
				for (int j = 0; j < work.size(); j++) {
					inputheji = this.inputOperateService.QueryInputHeJi(
							wcf.getBegin(), wcf.getFinish(), String.valueOf(i),
							work.get(j).getWorkingName());
					exportheji = this.inputOperateService.QueryExportHeJi(
							wcf.getBegin(), wcf.getFinish(), String.valueOf(i),
							work.get(j).getWorkingName());
					shiftstockheji = this.inputOperateService
							.QueryShiftStockHeJi(wcf.getBegin(), wcf
									.getFinish(), String.valueOf(i), work
									.get(j).getWorkingName());
					shiftheji = this.inputOperateService.QueryShiftHeJi(
							wcf.getBegin(), wcf.getFinish(), String.valueOf(i),
							work.get(j).getWorkingName());
					duandaoheji = this.inputOperateService.QueryDuanDaoHeJi(
							wcf.getBegin(), wcf.getFinish(), String.valueOf(i),
							work.get(j).getWorkingName());
					sum = (inputheji == null ? 0.0 : inputheji)
							+ (exportheji == null ? 0.0 : exportheji)
							+ (shiftstockheji == null ? 0.0 : shiftstockheji)
							+ (shiftheji == null ? 0.0 : shiftheji)
							+ (duandaoheji == null ? 0.0 : duandaoheji);
					if (sum > 0) {
						JSONObject obj = new JSONObject();
						obj.put("zhiwu", zhiwu);// 保存职务，是什么职务
						obj.put("name", work.get(j).getWorkingName());// 保存人名
						obj.put("begin", wcf.getBegin());
						obj.put("finish", wcf.getFinish());
						obj.put("ruku", df.format((inputheji == null ? 0.0
								: inputheji)));// 入库合计
						obj.put("chuku", df.format((exportheji == null ? 0.0
								: exportheji)));// 出库合计
						obj.put("guohu", df
								.format((shiftstockheji == null ? 0.0
										: shiftstockheji)));// 过户合计
						obj.put("nuoku", df.format((shiftheji == null ? 0.0
								: shiftheji)));// 挪库合计
						obj.put("duandao", df.format((duandaoheji == null ? 0.0
								: duandaoheji)));
						obj.put("sum", df.format(sum));// 总和
						array.add(obj);
					}
				}
			} else if (i > 7) {
				// 查询短倒司机
				List<DuanDao> ddlist = this.duanDaoService.ClassDriverName(wcf
						.getName());
				for (int j = 0; j < ddlist.size(); j++) {
					System.out.println(ddlist.get(j).getDriverName());
					inputheji = 0.0;
					exportheji = 0.0;
					shiftstockheji = 0.0;
					shiftheji = 0.0;
					duandaoheji = this.inputOperateService.QueryDuanDaoHeJi(
							wcf.getBegin(), wcf.getFinish(), String.valueOf(i),
							ddlist.get(j).getDriverName());
					sum = (inputheji == null ? 0.0 : inputheji)
							+ (exportheji == null ? 0.0 : exportheji)
							+ (shiftstockheji == null ? 0.0 : shiftstockheji)
							+ (shiftheji == null ? 0.0 : shiftheji)
							+ (duandaoheji == null ? 0.0 : duandaoheji);
					if (sum > 0) {
						JSONObject obj = new JSONObject();
						obj.put("zhiwu", zhiwu);// 保存职务，是什么职务
						obj.put("name", ddlist.get(j).getDriverName());// 保存人名
						obj.put("begin", wcf.getBegin());
						obj.put("finish", wcf.getFinish());
						obj.put("ruku", df.format((inputheji == null ? 0.0
								: inputheji)));// 入库合计
						obj.put("chuku", df.format((exportheji == null ? 0.0
								: exportheji)));// 出库合计
						obj.put("guohu", df
								.format((shiftstockheji == null ? 0.0
										: shiftstockheji)));// 过户合计
						obj.put("nuoku", df.format((shiftheji == null ? 0.0
								: shiftheji)));// 挪库合计
						obj.put("duandao", df.format((duandaoheji == null ? 0.0
								: duandaoheji)));
						obj.put("sum", df.format(sum));// 总和
						array.add(obj);
					}
				}
			}
		}

		out.print(array.toString());
		out.flush();
		out.close();
		return null;
	}

	// 通过职务、人员姓名、起始日期、结束日期统计相应人操作的订单
	public ActionForward getWorkWeightMingXi(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		WorkCountForm wcf = (WorkCountForm) form;

		// 起始日期的判断
		if (wcf.getBegin() == null || wcf.getBegin().equals("")) {
			wcf.setBegin(pt.getNowJianYi());
		} else {
			wcf.setBegin(wcf.getBegin());
		}
		// 结束日期的判断
		if (wcf.getFinish() == null || wcf.getFinish().equals("")) {
			wcf.setFinish(pt.getTimes());
		} else {
			wcf.setFinish(wcf.getFinish());
		}
		if (wcf.getName() == null) {
			wcf.setName("");
		}
		if (wcf.getZhiwu() == null) {
			wcf.setZhiwu("1");
		}
		
		PrintWriter out = response.getWriter();
		JSONArray array = new JSONArray();
		int z = Integer.parseInt(wcf.getZhiwu());
		String name = wcf.getName();
		if(z!=8){
			// 统计出库的订单
			List<ExportOperate> eolist = this.exportOperateService.QueryExportWorkByPage(wcf.getBegin(), wcf.getFinish(),wcf.getZhiwu(), wcf.getName(), 1, 100000);

			for (int i = 0; i < eolist.size(); i++) {
				JSONObject obj = new JSONObject();
				// 判断名字的匹配项
				if (wcf.getZhiwu().equals("1")) {
					name = eolist.get(i).getInteriorUserByEoperateDispatcher().getIuserName();
				}
				if (wcf.getZhiwu().equals("2")) {
					name = eolist.get(i).getInteriorUserByEoperateStoreman().getIuserName();
				}
				if (wcf.getZhiwu().equals("3")) {
					name = eolist.get(i).getInteriorUserByEoperatePonderationMan().getIuserName();
				}
				if (wcf.getZhiwu().equals("4")) {
					name = eolist.get(i).getInteriorUserByEoperateAuditing().getIuserName();
				}
				if (wcf.getZhiwu().equals("5")) {
					name = eolist.get(i).getInteriorUserByEoperateCollectMan().getIuserName();
				}
				if (wcf.getZhiwu().equals("6")) {
					name = eolist.get(i).getEoperateCraneman();
				}
				if (wcf.getZhiwu().equals("7")) {
					name = eolist.get(i).getEoperateStevedore();
				}
				obj.put("dingdanhao", eolist.get(i).getExportSeed().getExport().getExportId());// 保存订单号
				obj.put("name", name);// 保存统计人的名字
				obj.put("faqiTime", eolist.get(i).getExportSeed().getExport().getExportReateTime());// 保存订单的发起日期
				obj.put("client", eolist.get(i).getExportSeed().getExport().getClient().getClientAbbreviation());//保存客户简称
				obj.put("clientNumber", eolist.get(i).getExportSeed().getExport().getExportClientNumber());//客户单号
				obj.put("goodsName", eolist.get(i).getExportSeed().getGoods().getGoodsName());//货物名称
				obj.put("time", eolist.get(i).getEoperateDispatcherTime());// 保存订单的日期，调度分配的日期
				obj.put("type", "出库");// 保存订单类型为入库
				obj.put("workweight",eolist.get(i).getEoperateRealityWeight() == null ? 0: eolist.get(i).getEoperateRealityWeight());// 保存作业重量
				obj.put("worknumber",eolist.get(i).getEoperateRealityNumber() == null ? 0: eolist.get(i).getEoperateRealityNumber());// 保存作业件数
				array.add(obj);
			}
			//统计入库的订单
			List<InputOperate> iolist = this.inputOperateService.QueryInputWorkByPage(wcf.getBegin(), wcf.getFinish(),wcf.getZhiwu(), wcf.getName(), 1, 100000);
			for (int i = 0; i < iolist.size(); i++) {
				JSONObject obj = new JSONObject();
				// 判断名字的匹配项
				if (wcf.getZhiwu().equals("1")) {
					name = iolist.get(i).getInteriorUserByIoperateDispatcherId().getIuserName();
				}
				if (wcf.getZhiwu().equals("2")) {
					name = iolist.get(i).getInteriorUserByIoperateStoremanId().getIuserName();
				}
				if (wcf.getZhiwu().equals("3")) {
					name = iolist.get(i).getInteriorUserByIoperatePonderationManId().getIuserName();
				}
				if (wcf.getZhiwu().equals("4")) {
					name = iolist.get(i).getInteriorUserByIoperateAuditingId().getIuserName();
				}
				if (wcf.getZhiwu().equals("5")) {
					name = iolist.get(i).getInteriorUserByIoperateCollectManId().getIuserName();
				}
				if (wcf.getZhiwu().equals("6")) {
					name = iolist.get(i).getIoperateCraneman();
				}
				if (wcf.getZhiwu().equals("7")) {
					name = iolist.get(i).getIoperateStevedore();
				}
				obj.put("dingdanhao", iolist.get(i).getInputSeed().getInput().getInputId());// 保存订单号
				obj.put("name", name);// 保存统计人的名字
				obj.put("faqiTime", iolist.get(i).getInputSeed().getInput().getInputCreateTime());// 保存订单的发起日期
				obj.put("client", iolist.get(i).getInputSeed().getInput().getClient().getClientAbbreviation());//保存客户简称
				obj.put("clientNumber", iolist.get(i).getInputSeed().getInput().getInputClientNumber());//客户单号
				obj.put("goodsName", iolist.get(i).getInputSeed().getGoods().getGoodsName());//货物名称
				obj.put("time", iolist.get(i).getIoperateDispatcherTime());// 保存订单的日期，调度分配的日期
				obj.put("type", "入库");// 保存订单类型为入库
				obj.put("workweight",iolist.get(i).getIoperateRealityWeight() == null ? 0: iolist.get(i).getIoperateRealityWeight());// 保存作业重量
				obj.put("worknumber",iolist.get(i).getIoperateRealityNumber() == null ? 0: iolist.get(i).getIoperateRealityNumber());// 保存作业件数
				array.add(obj);
			}
		}
		if(z==4 || z==5){
			List<ShiftStockSeed> ssslist = this.shiftStockSeedService.QueryShiftStockWorkByPage(wcf.getBegin(), wcf.getFinish(),wcf.getZhiwu(), wcf.getName(), 1, 100000);
			for (int i = 0; i < ssslist.size(); i++) {
				JSONObject obj = new JSONObject();
				// 判断名字的匹配项
				if (wcf.getZhiwu().equals("4")) {
					name = ssslist.get(i).getInteriorUserBySsseedAuditing().getIuserName();
				}
				if (wcf.getZhiwu().equals("5")) {
					name = ssslist.get(i).getInteriorUserBySsseedCollectMan().getIuserName();
				}

				obj.put("name", name);// 保存统计人的名字
				obj.put("time", ssslist.get(i).getSsseedAuditingTime());// 保存订单的日期，调度分配的日期
				obj.put("faqiTime", ssslist.get(i).getShiftStock().getSstockReateTime());// 保存订单的发起日期
				obj.put("client", ssslist.get(i).getShiftStock().getClientBySstockClientId().getClientAbbreviation());//保存客户简称
				obj.put("clientNumber", ssslist.get(i).getShiftStock().getSstockClientNumber());//客户单号
				obj.put("goodsName", ssslist.get(i).getGoods().getGoodsName());//货物名称
				obj.put("dingdanhao", ssslist.get(i).getShiftStock().getSstockId());// 保存订单号
				obj.put("type", "过户");// 保存订单类型为入库
				obj.put("workweight", ssslist.get(i).getSsseedWeight() == null ? 0: ssslist.get(i).getSsseedWeight());// 保存作业重量
				obj.put("worknumber", 0);// 保存作业件数
				array.add(obj);
			}
		}
		if(z==1 || z==2 || z==6 || z==7){
			List<Shift> slist = this.shiftService.QueryShiftWorkByPage(wcf.getBegin(), wcf.getFinish(), wcf.getZhiwu(), wcf.getName(),1, 100000);
			for (int i = 0; i < slist.size(); i++) {
				JSONObject obj = new JSONObject();
				// 判断名字的匹配项
				if (wcf.getZhiwu().equals("1")) {
					name = slist.get(i).getInteriorUserByShiftFounderMember().getIuserName();
				}
				if (wcf.getZhiwu().equals("2")) {
					name = slist.get(i).getInteriorUserByShiftExecutor().getIuserName();
				}
				if (wcf.getZhiwu().equals("6")) {
					name = slist.get(i).getShiftCraneman();
				}
				if (wcf.getZhiwu().equals("7")) {
					name = slist.get(i).getShiftStevedore();
				}

				obj.put("name", name);// 保存统计人的名字
				obj.put("time", slist.get(i).getShiftTime());// 保存订单的日期，调度分配的日期
				obj.put("faqiTime", slist.get(i).getShiftTime());// 保存订单的发起日期
				obj.put("client", "无");//保存客户简称
				obj.put("clientNumber", "无");//客户单号
				obj.put("goodsName", slist.get(i).getGoods().getGoodsName());//货物名称
				obj.put("dingdanhao", slist.get(i).getShiftId());// 保存订单号
				obj.put("type", "挪库");// 保存订单类型为入库
				obj.put("workweight", slist.get(i).getShiftWeight() == null ? 0: slist.get(i).getShiftWeight());// 保存作业重量
				obj.put("worknumber", slist.get(i).getShiftNumber());// 保存作业件数
				array.add(obj);
			}
		}
		if(z==1 || z==2 || z==6 || z==7 || z==8){
			List<DuanDao> slist = this.duanDaoService.QueryShiftWorkByPage(wcf.getBegin(), wcf.getFinish(), wcf.getZhiwu(), wcf.getName(),1, 100000);
			for (int i = 0; i < slist.size(); i++) {
				JSONObject obj = new JSONObject();
				// 判断名字的匹配项
				if (wcf.getZhiwu().equals("1")) {
					name = slist.get(i).getInteriorUserByShiftFounderMember().getIuserName();
				}
				if (wcf.getZhiwu().equals("2")) {
					name = slist.get(i).getInteriorUserByShiftExecutor().getIuserName();
				}
				if (wcf.getZhiwu().equals("6")) {
					name = slist.get(i).getShiftCraneman();
				}
				if (wcf.getZhiwu().equals("7")) {
					name = slist.get(i).getShiftStevedore();
				}
				if (wcf.getZhiwu().equals("8")) {
					name = slist.get(i).getDriverName();
				}

				obj.put("name", name);// 保存统计人的名字
				obj.put("time", slist.get(i).getShiftTime());// 保存订单的日期，调度分配的日期
				obj.put("faqiTime", slist.get(i).getShiftTime());// 保存订单的发起日期
				obj.put("client", "无");//保存客户简称
				obj.put("clientNumber", "无");//客户单号
				obj.put("goodsName", slist.get(i).getGoods().getGoodsName());//货物名称
				obj.put("dingdanhao", slist.get(i).getShiftId());// 保存订单号
				obj.put("type", "短倒");// 保存订单类型为入库
				obj.put("workweight", slist.get(i).getShiftWeight() == null ? 0: slist.get(i).getShiftWeight());// 保存作业重量
				obj.put("worknumber", slist.get(i).getShiftNumber());// 保存作业件数
				array.add(obj);
			}
		}
		out.print(array.toString());
		out.flush();
		out.close();
		return null;
	}
}
