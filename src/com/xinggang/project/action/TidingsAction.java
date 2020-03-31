package com.xinggang.project.action;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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
import com.xinggang.project.entity.ClientGoods;
import com.xinggang.project.entity.DuanDao;
import com.xinggang.project.entity.ExportSeed;
import com.xinggang.project.entity.Goods;
import com.xinggang.project.entity.GoodsCategory;
import com.xinggang.project.entity.InputSeed;
import com.xinggang.project.entity.InteriorUser;
import com.xinggang.project.entity.Shift;
import com.xinggang.project.entity.ShiftStockSeed;
import com.xinggang.project.form.TidingsForm;
import com.xinggang.project.service.ChecksClientGoodsService;
import com.xinggang.project.service.ChecksService;
import com.xinggang.project.service.ClientGoodsService;
import com.xinggang.project.service.DuanDaoService;
import com.xinggang.project.service.ExportOperateService;
import com.xinggang.project.service.ExportSeedService;
import com.xinggang.project.service.GoodsCategoryService;
import com.xinggang.project.service.GoodsService;
import com.xinggang.project.service.InputOperateService;
import com.xinggang.project.service.InputSeedService;
import com.xinggang.project.service.ShiftService;
import com.xinggang.project.service.ShiftStockSeedService;
import com.xinggang.project.service.UpdateRecordService;
import com.xinggang.project.tools.PresentTime;

public class TidingsAction extends DispatchAction {
	// 出库子订单service
	private ExportSeedService exportSeedService;

	// 入库子订单service
	private InputSeedService inputSeedService;

	// 出库操作订单service
	private ExportOperateService exportOperateService;

	// 入库操作订单service
	private InputOperateService inputOperateService;

	// 挪库service
	private ShiftService shiftService;

	// 盘库service
	private ChecksService checksService;

	// 过户子订单service
	private ShiftStockSeedService shiftStockSeedService;
	// 货物service
	private GoodsService goodsService;
	// 客户库存service
	private ClientGoodsService clientGoodsService;
	// 订单修改审批service
	private UpdateRecordService updateRecordService;
	// 短倒service
	private DuanDaoService duanDaoService;

	// 盘点客户库存service
	private ChecksClientGoodsService checksClientGoodsService;
	//品类service
	private GoodsCategoryService goodsCategoryService;
	
	public void setGoodsCategoryService(GoodsCategoryService goodsCategoryService) {
		this.goodsCategoryService = goodsCategoryService;
	}

	public void setDuanDaoService(DuanDaoService duanDaoService) {
		this.duanDaoService = duanDaoService;
	}

	// 时间工具
	private PresentTime pt = new PresentTime();

	public void setChecksClientGoodsService(ChecksClientGoodsService checksClientGoodsService) {
		this.checksClientGoodsService = checksClientGoodsService;
	}

	public void setExportSeedService(ExportSeedService exportSeedService) {
		this.exportSeedService = exportSeedService;
	}

	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}

	public void setUpdateRecordService(UpdateRecordService updateRecordService) {
		this.updateRecordService = updateRecordService;
	}

	public void setClientGoodsService(ClientGoodsService clientGoodsService) {
		this.clientGoodsService = clientGoodsService;
	}

	public void setInputSeedService(InputSeedService inputSeedService) {
		this.inputSeedService = inputSeedService;
	}

	public void setExportOperateService(
			ExportOperateService exportOperateService) {
		this.exportOperateService = exportOperateService;
	}

	public void setInputOperateService(InputOperateService inputOperateService) {
		this.inputOperateService = inputOperateService;
	}

	public void setShiftService(ShiftService shiftService) {
		this.shiftService = shiftService;
	}

	public void setShiftStockSeedService(
			ShiftStockSeedService shiftStockSeedService) {
		this.shiftStockSeedService = shiftStockSeedService;
	}

	public void setChecksService(ChecksService checksService) {
		this.checksService = checksService;
	}

	/*
	 * private static int DCLchukus = 0; private static int DCLrukus = 0;
	 * private static int CZChuKus = 0; private static int CZRuKus = 0; private
	 * static int CZPanKus = 0; private static int CZNuoKus = 0; private static
	 * int GBChuKus = 0; private static int GBRuKus = 0; private static int
	 * SHChuKus = 0; private static int SHGuoHus = 0; private static int SHRuKus
	 * = 0; private static int PKSHs = 0; private static int SFChuKus = 0;
	 * private static int SFRuKus = 0; private static int SFGuoHus = 0;
	 */

	// 进行统计待处理订单，待操作订单，待过磅订单，待审核订单，待收费订单，待盘库审核订单
	public ActionForward CountOrderForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Integer dengluren = Integer.parseInt(request.getParameter("id").toString());

		List<Checks> jlist = this.checksService.getChecksZhuangtai("计划盘库");// 查询出计划盘库的订单
		List<Checks> wlist = this.checksService.getChecksZhuangtai("审核未通过");// 查询出审核未通过的订单

		// 将盘库未通过的订单添加到集合中
		for (Checks c : wlist) {
			jlist.add(c);
		}

		for (int i = 0; i < jlist.size(); i++) {
			if (!jlist.get(i).getInteriorUserByCheckHuman().getIuserId().equals(dengluren)) {
				jlist.remove(i);
				i--;
				continue;
			}
		}

		List<Shift> jslist = this.shiftService.getShiftZhuangtai("计划挪库");
		List<Shift> zjlist = this.shiftService.getShiftZhuangtai("正在挪库");

		for (Shift s : zjlist) {
			jslist.add(s);
		}
		try {
			for (int i = 0; i < jslist.size(); i++) {
				if (jslist.get(i).getInteriorUserByShiftExecutor() == null) {
					jslist.remove(i);
					i--;
					continue;
				}
				if (!jslist.get(i).getInteriorUserByShiftExecutor().getIuserId().equals(dengluren)) {
					jslist.remove(i);
					i--;
					continue;
				}
			}
		} catch (Exception e) {

		}

		List<DuanDao> jdlist = this.duanDaoService.getShiftZhuangtai("计划短倒");
		List<DuanDao> zdlist = this.duanDaoService.getShiftZhuangtai("正在短倒");

		for (DuanDao s : zdlist) {
			jdlist.add(s);
		}
		try {
			for (int i = 0; i < jdlist.size(); i++) {
				if (jdlist.get(i).getInteriorUserByShiftExecutor() == null) {
					jdlist.remove(i);
					i--;
					continue;
				}
				if (!jdlist.get(i).getInteriorUserByShiftExecutor().getIuserId().equals(dengluren)) {
					jslist.remove(i);
					i--;
					continue;
				}
			}
		} catch (Exception e) {

		}

		// 查询待处理中出库的总条数
		int DCLchuku = this.exportSeedService.getDaiChuLiByPageCount("",pt.getTimes(), "", "", "", "", "", "", "", "", "", "", 1);
		// 查询入库的待处理总条数
		int DCLruku = this.inputSeedService.getRuKuDaiChuLi();

		// 查询出库的分配给保管的操作订单
		int CZChuKu = this.exportOperateService.getZhuangTaiBaoGuan("准备出库",
				dengluren).size();
		// 查询入库的分配给保管的操作订单条数
		int CZRuKu = this.inputOperateService.getBaoguan(dengluren).size();

		// 查询盘库的分配给保管员的订单条数
		int CZPanKu = jlist.size();
		// 查询盘库的分配给保管的订单条数
		int CZNuoKu = jslist.size();
		int CZDuanDao = jdlist.size();

		// 统计出库操作订单中要过磅的订单条数
		int GBChuKu = this.exportOperateService.getGuoBangAll("过磅", "", "", 1,1000).size();

		// 查询入库过磅的订单
		int GBRuKu = this.inputOperateService.getSBCount("准备入库", "", "", 1);

		// 查询出库审核的条数
		int SHChuKu = this.exportOperateService.getZhuangTaiByPageCount("",pt.getTimes(), "", "", "正在审核", 1);
		// 查询过户审核的条数
		int SHGuoHu = this.shiftStockSeedService.getTiHuoDataByPageCount("","", "正在审核", 1);
		// 查询入库的审核条数
		int SHRuKu = this.inputOperateService.count("正在审核", "", "", 1);

		// 查询盘库审核的条数
		int PKSH = this.checksService.getTarehouseByPageCount("", 1);

		// 查询出库中代收费的订单条数
		int SFChuKu = this.exportOperateService.pageCount("", "", "审核通过", 1);
		// 查询入库中待收费的订单条数
		int SFRuKu = this.inputOperateService.count("审核通过", "", "", 1);
		// 查询过户中待收费的订单条数
		int SFGuoHu = this.shiftStockSeedService.getTiHuoDataByPageCount("","", "正在收费", 1);

		// 统计订单修改审批的量
		InteriorUser iu = (InteriorUser) request.getSession().getAttribute("iulist");// 对应的登录的审批人
		int updateshenpi = this.updateRecordService.getShenPi("", "", pt.getTimes(), "审批中", iu.getIuserName(), "").size();

		JSONArray array = new JSONArray();
		PrintWriter out = response.getWriter();

		JSONObject obj = new JSONObject();
		obj.put("daichuli", DCLchuku + DCLruku);// 保存待处理的条数
		obj.put("dingdancaozuo", CZChuKu + CZRuKu + CZNuoKu + CZPanKu+ CZDuanDao);// 保存待操作订单的条数
		obj.put("guobang", GBChuKu + GBRuKu);// 保存带过磅的条数
		obj.put("shenhe", SHChuKu + SHRuKu + SHGuoHu);// 保存待审核的条数
		obj.put("pankushenhe", PKSH);// 保存盘库审核的条数
		obj.put("shoufei", SFChuKu + SFRuKu + SFGuoHu);
		obj.put("updateshenpi", updateshenpi);
		array.add(obj);
		out.print(array.toString());
		out.flush();
		out.close();

		// 返回到对应的界面
		return null;
	}

	// 用来统计鑫港的历史库存
	public ActionForward TongJiXGLSKC(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/* 设置响应头允许ajax跨域访问 */
		response.setHeader("Access-Control-Allow-Origin", "*");
		/* 星号表示所有的异域请求都可以接受， */
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");

		TidingsForm tf = (TidingsForm) form;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 起始日期
		if (tf.getBegin() == null || tf.getBegin().equals("")) {
			tf.setBegin(pt.getNowJianYi());
		} else {
			tf.setBegin(tf.getBegin());
		}
		// 结束日期
		if (tf.getFinish() == null || tf.getFinish().equals("")) {
			tf.setFinish(pt.getTimes());
		} else if (sdf.parse(pt.getTimes()).getTime() > sdf.parse(
				tf.getFinish()).getTime()) {
			tf.setFinish(tf.getFinish());
		} else {
			tf.setFinish(pt.getTimes());
		}
		// 货物的名称等
		if (tf.getGoodsName() == null) {
			tf.setGoodsName("");
		}
		// 品类
		if (tf.getPinlei() == null) {
			tf.setPinlei("");
		}

		int pageNow = 1;// 当前页
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}

		int pageCount = this.goodsService.getZhuanFaCunByCount(tf.getGoodsName(), tf.getPinlei(), 20);
		if (pageNow >= pageCount) {
			pageNow = pageCount;
		}
		if (pageNow <= 1) {
			pageNow = 1;
		}

		JSONArray array = new JSONArray();
		PrintWriter out = response.getWriter();

		List<Goods> glist = this.goodsService.getZhuanFaCunGoods(tf.getGoodsName(), tf.getPinlei(), pageNow, 20);
		Double QCZH = 0.0;
		Double RKZH = 0.0;
		Double CKZH = 0.0;
		Double QMZH = 0.0;
		DecimalFormat df = new DecimalFormat("#########0.000");

		for (int i = 0; i < glist.size(); i++) {
			if (this.exportSeedService.getZhuanFaCunChuKuGeJi(tf.getBegin(),tf.getFinish(), glist.get(i).getGoodsId()).size() > 0 && this.exportSeedService.getZhuanFaCunChuKuGeJi(tf.getBegin(),tf.getFinish(), glist.get(i).getGoodsId()).get(0).getEseedRealityWeight() != null) {
				glist.get(i).setExportHeJi(this.exportSeedService.getZhuanFaCunChuKuGeJi(tf.getBegin(),tf.getFinish(),glist.get(i).getGoodsId()).get(0).getEseedRealityWeight() == null ? 0.0: this.exportSeedService.getZhuanFaCunChuKuGeJi(tf.getBegin(),tf.getFinish(),glist.get(i).getGoodsId()).get(0).getEseedRealityWeight());
			} else {
				glist.get(i).setExportHeJi(0.0);
			}
			if (this.inputSeedService.getZhuanFaCunRuKuQiChu(tf.getBegin(),
					glist.get(i).getGoodsId()).size() > 0
					&& this.inputSeedService
							.getZhuanFaCunRuKuQiChu(tf.getBegin(),
									glist.get(i).getGoodsId()).get(0)
							.getIseedRealityWeight() != null) {
				glist.get(i).setQiChu(
						this.inputSeedService
								.getZhuanFaCunRuKuQiChu(tf.getBegin(),
										glist.get(i).getGoodsId()).get(0)
								.getIseedRealityWeight() == null ? 0.0
								: this.inputSeedService
										.getZhuanFaCunRuKuQiChu(tf.getBegin(),
												glist.get(i).getGoodsId())
										.get(0).getIseedRealityWeight());
			} else {
				glist.get(i).setQiChu(0.0);
			}
			if (this.exportSeedService.getZhuanFaCunChuKuQiChu(tf.getBegin(),
					glist.get(i).getGoodsId()).size() > 0
					&& this.exportSeedService
							.getZhuanFaCunChuKuQiChu(tf.getBegin(),
									glist.get(i).getGoodsId()).get(0)
							.getEseedRealityWeight() != null) {
				glist.get(i).setQiChu(
						glist.get(i).getQiChu()
								- this.exportSeedService
										.getZhuanFaCunChuKuQiChu(tf.getBegin(),
												glist.get(i).getGoodsId())
										.get(0).getEseedRealityWeight());
			}
			if (this.inputSeedService.getZhuanFaCunRuKuGeJi(tf.getBegin(),
					tf.getFinish(), glist.get(i).getGoodsId()).size() > 0
					&& this.inputSeedService
							.getZhuanFaCunRuKuGeJi(tf.getBegin(),
									tf.getFinish(), glist.get(i).getGoodsId())
							.get(0).getIseedRealityWeight() != null) {
				glist.get(i).setInputHeJi(
						this.inputSeedService
								.getZhuanFaCunRuKuGeJi(tf.getBegin(),
										tf.getFinish(),
										glist.get(i).getGoodsId()).get(0)
								.getIseedRealityWeight() == null ? 0.0
								: this.inputSeedService
										.getZhuanFaCunRuKuGeJi(tf.getBegin(),
												tf.getFinish(),
												glist.get(i).getGoodsId())
										.get(0).getIseedRealityWeight());
			} else {
				glist.get(i).setInputHeJi(0.0);
			}

			glist.get(i).setQiMo(
					(glist.get(i).getQiChu() + glist.get(i).getInputHeJi())
							- glist.get(i).getExportHeJi());

			JSONObject obj = new JSONObject();
			obj.put("pinlei", glist.get(i).getGoodsCategory()
					.getGoodsCategoryName());// 保存品类
			obj.put("mingcheng", glist.get(i).getGoodsName());// 保存货物名称
			obj.put("guige", glist.get(i).getGoodsStandard()
					.getGoodsStandardName());// 保存规格的名称
			obj.put("caizhi", glist.get(i).getGoodsQuality()
					.getGoodsQualityName());// 保存材质
			obj.put("shuxing", glist.get(i).getGoodsProperty()
					.getGoodsPropertyName());// 保存属性
			obj.put("chandi", glist.get(i).getGoodsYieldly()
					.getGoodsYieldlyName());// 保存产地
			obj.put("qichu", df.format(glist.get(i).getQiChu()));// 保存期初库存
			obj.put("rukuheji", df.format(glist.get(i).getInputHeJi()));// 保存入库合计
			obj.put("chukuheji", df.format(glist.get(i).getExportHeJi()));// 保存出库合计
			obj.put("qimo", df.format(glist.get(i).getQiMo()));// 保存期末库存
			QCZH += glist.get(i).getQiChu();// 期初库存相加
			RKZH += glist.get(i).getInputHeJi();// 入库重量相加
			CKZH += glist.get(i).getExportHeJi();// 出库重量相加
			QMZH += glist.get(i).getQiMo();// 期末库存相加

			obj.put("QCZH", Double.parseDouble(df.format(QCZH)));
			obj.put("RKZH", Double.parseDouble(df.format(RKZH)));
			obj.put("CKZH", Double.parseDouble(df.format(CKZH)));
			obj.put("QMZH", Double.parseDouble(df.format(QMZH)));
			obj.put("pageNow", pageNow);
			obj.put("pageCount", pageCount);
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();

		return null;
	}

	// 用来统计鑫港的客户的库存
	public ActionForward TongJiXGKHKC(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/* 设置响应头允许ajax跨域访问 */
		response.setHeader("Access-Control-Allow-Origin", "*");
		/* 星号表示所有的异域请求都可以接受， */
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");
		TidingsForm tf = (TidingsForm) form;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (tf.getBegin() == null || tf.getBegin().equals("")) {
			tf.setBegin(pt.getNowJianYi());
		} else {
			tf.setBegin(tf.getBegin());
		}
		if (tf.getFinish() == null || tf.getFinish().equals("")) {
			tf.setFinish(pt.getTimes());
		} else if (sdf.parse(pt.getTimes()).getTime() > sdf.parse(
				tf.getFinish()).getTime()) {
			tf.setFinish(tf.getFinish());
		} else {
			tf.setFinish(pt.getTimes());
		}
		if (tf.getGoodsName() == null) {
			tf.setGoodsName("");
		}

		if (tf.getJiancheng() == null) {
			tf.setJiancheng("");
		}
		// 后期加入规格、材质、属性、产地
		if (tf.getGuige() == null) {
			tf.setGuige("");
		}
		if (tf.getCaizhi() == null) {
			tf.setCaizhi("");
		}
		if (tf.getShuxing() == null) {
			tf.setShuxing("");
		}
		if (tf.getChandi() == null) {
			tf.setChandi("");
		}

		int pageNow = 1;
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}

		// 显示行数
		int pageRow = 30;// 默认显示30
		if (request.getParameter("pageRow") != null
				&& request.getParameter("pageRow").equals("") == false) {
			pageRow = Integer.parseInt(request.getParameter("pageRow"));
		}

		int pageCount = this.clientGoodsService.getKeHuKuCunByPageCount(
				tf.getJiancheng(), tf.getGoodsName(), tf.getGuige(),
				tf.getCaizhi(), tf.getShuxing(), tf.getChandi(), pageRow);
		if (pageNow >= pageCount) {
			pageNow = pageCount;
		}
		if (pageNow <= 1) {
			pageNow = 1;
		}

		JSONArray array = new JSONArray();
		PrintWriter out = response.getWriter();

		List<ClientGoods> cglist = this.clientGoodsService.getKeHuKuCunByPage(
				tf.getJiancheng(), tf.getGoodsName(), tf.getGuige(),
				tf.getCaizhi(), tf.getShuxing(), tf.getChandi(), pageNow,
				pageRow);
		if (cglist.size() <= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		Double QCZH = 0.0;
		Double RKZH = 0.0;
		Double CKZH = 0.0;
		Double QMZH = 0.0;
		DecimalFormat df = new DecimalFormat("#########0.000");

		for (int i = 0; i < cglist.size(); i++) {
			List<ExportSeed> eslist = this.exportSeedService.getKeHuKunCunCKHJ(
					tf.getBegin(), tf.getFinish(), cglist.get(i).getClient()
							.getClientId(), cglist.get(i).getGoods()
							.getGoodsId());
			if (eslist.size() > 0
					&& eslist.get(0).getEseedRealityWeight() != null) {
				cglist.get(i).setExportHeJi(
						eslist.get(0).getEseedRealityWeight() == null ? 0.0
								: eslist.get(0).getEseedRealityWeight());
			} else {
				cglist.get(i).setExportHeJi(0.0);
			}
			List<InputSeed> islist = this.inputSeedService.getKeHuKuCunQCHJ(
					tf.getBegin(), cglist.get(i).getClient().getClientId(),
					cglist.get(i).getGoods().getGoodsId());
			if (islist.size() > 0
					&& islist.get(0).getIseedRealityWeight() != null) {
				cglist.get(i).setQiChu(islist.get(0).getIseedRealityWeight());
			} else {
				cglist.get(i).setQiChu(0.0);
			}
			List<ExportSeed> esqclist = this.exportSeedService
					.getKeHuKunCunQCHJ(tf.getBegin(), cglist.get(i).getClient()
							.getClientId(), cglist.get(i).getGoods()
							.getGoodsId());
			if (esqclist.size() > 0
					&& esqclist.get(0).getEseedRealityWeight() != null) {
				cglist.get(i).setQiChu(
						Double.parseDouble(df.format(cglist.get(i).getQiChu()
								- esqclist.get(0).getEseedRealityWeight())));
			}
			List<InputSeed> isrklist = this.inputSeedService.getKeHuKuCunRKHJ(
					tf.getBegin(), tf.getFinish(), cglist.get(i).getClient()
							.getClientId(), cglist.get(i).getGoods()
							.getGoodsId());
			if (isrklist.size() > 0
					&& isrklist.get(0).getIseedRealityWeight() != null) {
				cglist.get(i).setInputHeJi(
						isrklist.get(0).getIseedRealityWeight() == null ? 0.0
								: isrklist.get(0).getIseedRealityWeight());
			} else {
				cglist.get(i).setInputHeJi(0.0);
			}
			List<ShiftStockSeed> ssslist = this.shiftStockSeedService
					.getKeHuKuCunGHZCHJ(tf.getBegin(), tf.getFinish(), cglist
							.get(i).getClient().getClientId(), cglist.get(i)
							.getGoods().getGoodsId());
			if (ssslist.size() > 0 && ssslist.get(0).getSsseedWeight() != null) {
				cglist.get(i).setExportHeJi(
						Double.parseDouble(df.format(cglist.get(i)
								.getExportHeJi()
								+ ssslist.get(0).getSsseedWeight())));
			}
			List<ShiftStockSeed> ssszulist = this.shiftStockSeedService
					.getKeHuKuCunGHZRHJ(tf.getBegin(), tf.getFinish(), cglist
							.get(i).getClient().getClientId(), cglist.get(i)
							.getGoods().getGoodsId());
			if (ssszulist.size() > 0
					&& ssszulist.get(0).getSsseedWeight() != null) {
				cglist.get(i).setInputHeJi(
						Double.parseDouble(df.format(cglist.get(i)
								.getInputHeJi()
								+ ssszulist.get(0).getSsseedWeight())));
			}
			List<ShiftStockSeed> ssszclist = this.shiftStockSeedService
					.getKeHuKuCunZCQCHJ(tf.getBegin(), cglist.get(i)
							.getClient().getClientId(), cglist.get(i)
							.getGoods().getGoodsId());
			if (ssszclist.size() > 0 && ssszclist.get(0) != null) {
				cglist.get(i).setQiChu(
						Double.parseDouble(df.format(cglist.get(i).getQiChu()
								- (ssszclist.get(0).getSsseedWeight()))));
			}

			List<ShiftStockSeed> sssqczr = this.shiftStockSeedService
					.getKeHuKuCunZRQCHJ(tf.getBegin(), cglist.get(i)
							.getClient().getClientId(), cglist.get(i)
							.getGoods().getGoodsId());
			if (sssqczr.size() > 0 && sssqczr.get(0) != null) {
				cglist.get(i).setQiChu(
						Double.parseDouble(df.format(cglist.get(i).getQiChu()
								+ sssqczr.get(0).getSsseedWeight())));
			}
			double panqichu = this.checksClientGoodsService.getChecksQiChu(
					cglist.get(i).getClient().getClientId(), cglist.get(i)
							.getGoods().getGoodsId(), tf.getBegin());
			cglist.get(i).setQiChu(
					Double.parseDouble(df
							.format((cglist.get(i).getQiChu() == null ? 0.0
									: cglist.get(i).getQiChu()) + panqichu)));// 设置盘点客户库存的期初
			double panqimo = this.checksClientGoodsService.getChecksQiMo(cglist
					.get(i).getClient().getClientId(), cglist.get(i).getGoods()
					.getGoodsId(), tf.getBegin(), tf.getFinish());
			cglist.get(i)
					.setQiMo(
							Double.parseDouble(df.format((cglist.get(i)
									.getQiChu() + cglist.get(i).getInputHeJi())
									- cglist.get(i).getExportHeJi() + panqimo)));

			JSONObject obj = new JSONObject();
			obj.put("kehu", cglist.get(i).getClient().getClientAbbreviation());// 保存客户简称
			obj.put("pinlei", cglist.get(i).getGoods().getGoodsCategory()
					.getGoodsCategoryName());// 保存品类
			obj.put("mingcheng", cglist.get(i).getGoods().getGoodsName());// 保存货物名称
			obj.put("guige", cglist.get(i).getGoods().getGoodsStandard()
					.getGoodsStandardName());// 保存规格的名称
			obj.put("caizhi", cglist.get(i).getGoods().getGoodsQuality()
					.getGoodsQualityName());// 保存材质
			obj.put("shuxing", cglist.get(i).getGoods().getGoodsProperty()
					.getGoodsPropertyName());// 保存属性
			obj.put("chandi", cglist.get(i).getGoods().getGoodsYieldly()
					.getGoodsYieldlyName());// 保存产地
			obj.put("qichu", df.format(cglist.get(i).getQiChu()));// 保存期初库存
			obj.put("rukuheji", df.format(cglist.get(i).getInputHeJi()));// 保存入库合计
			obj.put("chukuheji", df.format(cglist.get(i).getExportHeJi()));// 保存出库合计
			obj.put("qimo", df.format(cglist.get(i).getQiMo()));// 保存期末库存
			QCZH += cglist.get(i).getQiChu();// 期初库存相加
			RKZH += cglist.get(i).getInputHeJi();// 入库重量相加
			CKZH += cglist.get(i).getExportHeJi();// 出库重量相加
			QMZH += cglist.get(i).getQiMo();// 期末库存相加

			obj.put("QCZH", Double.parseDouble(df.format(QCZH)));
			obj.put("RKZH", Double.parseDouble(df.format(RKZH)));
			obj.put("CKZH", Double.parseDouble(df.format(CKZH)));
			obj.put("QMZH", Double.parseDouble(df.format(QMZH)));

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

	// 用来统计收发存所有页数的合计

	public ActionForward TongJiXGKHSFCSum(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		/* 设置响应头允许ajax跨域访问 */
		response.setHeader("Access-Control-Allow-Origin", "*");
		/* 星号表示所有的异域请求都可以接受， */
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");

		TidingsForm tf = (TidingsForm) form;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		if (tf.getBegin() == null || tf.getBegin().equals("")) {
			tf.setBegin(pt.getNowJianYi());
		} else {
			tf.setBegin(tf.getBegin());
		}
		if (tf.getFinish() == null || tf.getFinish().equals("")) {
			tf.setFinish(pt.getTimes());
		} else if (sdf.parse(pt.getTimes()).getTime() > sdf.parse(
				tf.getFinish()).getTime()) {
			tf.setFinish(tf.getFinish());
		} else {
			tf.setFinish(pt.getTimes());
		}
		if (tf.getGoodsName() == null) {
			tf.setGoodsName("");
		}

		if (tf.getJiancheng() == null) {
			tf.setJiancheng("");
		}

		int pageNow = 1;
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}

		// 显示的行数
		int pageRow = 100000;
		if (request.getParameter("pageRow") != null
				&& request.getParameter("pageRow").equals("") == false) {
			pageRow = Integer.parseInt(request.getParameter("pageRow")
					.toString());
		}
		int pageCount = this.clientGoodsService.getKeHuSFCByPageCount(
				tf.getJiancheng(), tf.getGoodsName(), pageRow);
		if (pageNow >= pageCount) {
			pageNow = pageCount;
		}
		if (pageNow <= 1) {
			pageNow = 1;
		}

		JSONArray array = new JSONArray();
		PrintWriter out = response.getWriter();

		List<ClientGoods> cglist = this.clientGoodsService.getKeHuSFCByPage(
				tf.getJiancheng(), tf.getGoodsName(), pageNow, pageRow);

		if (cglist.size() <= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		DecimalFormat df = new DecimalFormat("#########0.000");
		Integer clientId = 0;
		String goodsName = "";
		int len = cglist.size();
		int y = 0;

		for (int i = 0; i < len; i++) {
			if (i == 0) {
				clientId = cglist.get(i).getClient().getClientId();
				goodsName = cglist.get(i).getGoodsName();
			}
			if (cglist.get(i).getClient().getClientId().equals(clientId)
					&& goodsName.equals(cglist.get(i).getGoodsName())) {

				// 统计出库合计
				List<ExportSeed> eslist = this.exportSeedService
						.getKeHuKunCunCKHJSFC(tf.getBegin(), tf.getFinish(),
								cglist.get(i).getClient().getClientId(), cglist
										.get(i).getGoodsName());
				if (eslist.size() > 0
						&& eslist.get(0).getEseedRealityWeight() != null) {
					cglist.get(y)
							.setExportHeJi(
									Double.parseDouble(df
											.format((cglist.get(y)
													.getExportHeJi() == null ? 0.0
													: cglist.get(y)
															.getExportHeJi())
													+ (eslist
															.get(0)
															.getEseedRealityWeight() == null ? 0.0
															: eslist.get(0)
																	.getEseedRealityWeight()))));
				} else {
					cglist.get(y).setExportHeJi(
							(cglist.get(y).getExportHeJi() == null ? 0.0
									: cglist.get(y).getExportHeJi()));
				}
				// 统计出库合计结束------
				// 统计入库的期初
				List<InputSeed> islist = this.inputSeedService
						.getKeHuKuCunQCHJSFC(tf.getBegin(), cglist.get(i)
								.getClient().getClientId(), cglist.get(i)
								.getGoodsName());
				if (islist.size() > 0
						&& islist.get(0).getIseedRealityWeight() != null) {
					cglist.get(y).setQiChu(
							Double.parseDouble(df.format(islist.get(0)
									.getIseedRealityWeight()
									+ (cglist.get(y).getQiChu() == null ? 0.0
											: cglist.get(y).getQiChu()))));
				} else {
					cglist.get(y).setQiChu(
							(cglist.get(y).getQiChu() == null ? 0.0 : cglist
									.get(y).getQiChu()));
				}
				// 统计入库的期初结束
				// 统计出库期初
				List<ExportSeed> esqclist = this.exportSeedService
						.getKeHuKunCunQCHJSFC(tf.getBegin(), cglist.get(i)
								.getClient().getClientId(), cglist.get(i)
								.getGoodsName());
				if (esqclist.size() > 0
						&& esqclist.get(0).getEseedRealityWeight() != null) {
					cglist.get(y)
							.setQiChu(
									Double.parseDouble(df.format(cglist.get(y)
											.getQiChu()
											- esqclist.get(0)
													.getEseedRealityWeight())));
				}
				// 统计出库期初结束
				// 统计入库合计---------------------------------------------------------------------------------------------+++++
				List<InputSeed> isrklist = this.inputSeedService
						.getKeHuKuCunRKHJSFC(tf.getBegin(), tf.getFinish(),
								cglist.get(i).getClient().getClientId(), cglist
										.get(i).getGoodsName());
				if (isrklist.size() > 0
						&& isrklist.get(0).getIseedRealityWeight() != null) {
					cglist.get(y)
							.setInputHeJi(
									(isrklist.get(0).getIseedRealityWeight() == null ? 0.0
											: isrklist.get(0)
													.getIseedRealityWeight())
											+ (cglist.get(y).getInputHeJi() == null ? 0.0
													: cglist.get(y)
															.getInputHeJi()));
				} else {
					cglist.get(y).setInputHeJi(
							cglist.get(y).getInputHeJi() == null ? 0.0 : cglist
									.get(y).getInputHeJi());
				}
				// 统计入库合计结束
				// 过户转出合计，统计到出库合计中
				List<ShiftStockSeed> ssslist = this.shiftStockSeedService
						.getKeHuKuCunGHZCHJSFC(tf.getBegin(), tf.getFinish(),
								cglist.get(i).getClient().getClientId(), cglist
										.get(i).getGoodsName());
				if (ssslist.size() > 0
						&& ssslist.get(0).getSsseedWeight() != null) {
					cglist.get(y).setExportHeJi(
							Double.parseDouble(df.format(cglist.get(y)
									.getExportHeJi()
									+ ssslist.get(0).getSsseedWeight())));
				}

				// 过户转出统计结束
				// 统计过户转入开始
				List<ShiftStockSeed> ssszulist = this.shiftStockSeedService.getKeHuKuCunGHZRHJSFC(tf.getBegin(), tf.getFinish(),cglist.get(i).getClient().getClientId(), cglist.get(i).getGoodsName());
				if (ssszulist.size() > 0&& ssszulist.get(0).getSsseedWeight() != null) {
					cglist.get(y).setInputHeJi(Double.parseDouble(df.format(cglist.get(y).getInputHeJi()+ ssszulist.get(0).getSsseedWeight())));
				}
				// 统计过户转入结束
				// 统计过户转出期初统计
				List<ShiftStockSeed> ssszclist = this.shiftStockSeedService.getKeHuKuCunZCQCHJSFC(tf.getBegin(), cglist.get(i).getClient().getClientId(), cglist.get(i).getGoodsName());
				if (ssszclist.size() > 0 && ssszclist.get(0) != null) {
					cglist.get(y).setQiChu(Double.parseDouble(df.format(cglist.get(y).getQiChu()- (ssszclist.get(0).getSsseedWeight()))));
				}
				// 统计过户转出期初统计结束
				// 统计过户转入期初的统计
				List<ShiftStockSeed> sssqczr = this.shiftStockSeedService.getKeHuKuCunZRQCHJSFC(tf.getBegin(), cglist.get(i).getClient().getClientId(), cglist.get(i).getGoodsName());
				if (sssqczr.size() > 0 && sssqczr.get(0) != null) {
					cglist.get(y).setQiChu(Double.parseDouble(df.format(cglist.get(y).getQiChu()+ sssqczr.get(0).getSsseedWeight())));
				}
				// 统计过户的转入期初结束

				cglist.get(y).setQiMo(Double.parseDouble(df.format((cglist.get(y).getQiChu() + cglist.get(y).getInputHeJi())- cglist.get(y).getExportHeJi())));

				// 统计过户入库
				List<ShiftStockSeed> sssguohulist = this.shiftStockSeedService.getKeHuKuCunGHZRHJSFC(tf.getBegin(), tf.getFinish(),cglist.get(i).getClient().getClientId(), cglist.get(i).getGoodsName());
				if (sssguohulist.size() > 0 && sssguohulist.get(0).getSsseedWeight() != null) {
					cglist.get(y).setGuohuru(Double.parseDouble(df.format((cglist.get(y).getGuohuru() == null ? 0.0 : cglist.get(y).getGuohuru())+ sssguohulist.get(0).getSsseedWeight())));
				} else {
					cglist.get(y).setGuohuru(Double.parseDouble(df.format(cglist.get(y).getGuohuru() == null ? 0.0 : cglist.get(y).getGuohuru())));
				}

				// 统计过户出库
				List<ShiftStockSeed> ssszhuanchulist = this.shiftStockSeedService.getKeHuKuCunGHZCHJSFC(tf.getBegin(), tf.getFinish(),cglist.get(i).getClient().getClientId(), cglist.get(i).getGoodsName());
				if (ssszhuanchulist.size() > 0 && ssszhuanchulist.get(0).getSsseedWeight() != null) {
					cglist.get(y).setGuohuchu(Double.parseDouble(df.format((cglist.get(y).getGuohuchu() == null ? 0.0: cglist.get(y).getGuohuchu())+ ssszhuanchulist.get(0).getSsseedWeight())));
				} else {
					cglist.get(y).setGuohuchu(Double.parseDouble(df.format(cglist.get(y).getGuohuchu() == null ? 0.0 : cglist.get(y).getGuohuchu())));
				}
				// 统计火运出库重量
				List<ExportSeed> huoyunchuku = this.exportSeedService.getSFCYunShu(tf.getBegin(), tf.getFinish(), cglist.get(i).getClient().getClientId(), cglist.get(i).getGoodsName(), "火运");
				if (huoyunchuku.size() > 0 && huoyunchuku.get(0).getEseedRealityWeight() != null) {
					cglist.get(y).setHuochu(Double.parseDouble(df.format((cglist.get(y).getHuochu() == null ? 0.0 : cglist.get(y).getHuochu())+ (huoyunchuku.get(0).getEseedRealityWeight()))));
				} else {
					cglist.get(y).setHuochu(Double.parseDouble(df.format(cglist.get(y).getHuochu() == null ? 0.0 : cglist.get(y).getHuochu())));
				}
				// 统计汽运出库重量
				List<ExportSeed> qiyunchuku = this.exportSeedService.getSFCYunShu(tf.getBegin(), tf.getFinish(), cglist.get(i).getClient().getClientId(), cglist.get(i).getGoodsName(), "汽运");
				if (qiyunchuku.size() > 0 && qiyunchuku.get(0).getEseedRealityWeight() != null) {
					cglist.get(y).setQichechu(Double.parseDouble(df.format((cglist.get(y).getQichechu() == null ? 0.0 : cglist.get(y).getQichechu())+ (qiyunchuku.get(0).getEseedRealityWeight()))));
				} else {
					cglist.get(y).setQichechu(Double.parseDouble(df.format(cglist.get(y).getQichechu() == null ? 0.0 : cglist.get(y).getQichechu())));
				}

				// 统计火运入库重量
				List<InputSeed> huoyunruku = this.inputSeedService.getSFCYunShu(tf.getBegin(), tf.getFinish(), cglist.get(i).getClient().getClientId(), cglist.get(i).getGoodsName(), "火运");
				if (huoyunruku.size() > 0 && huoyunruku.get(0).getIseedRealityWeight() != null) {
					cglist.get(y).setHuoru(Double.parseDouble(df.format((cglist.get(y).getHuoru() == null ? 0.0 : cglist.get(y).getHuoru())+ (huoyunruku.get(0).getIseedRealityWeight()))));
				} else {
					cglist.get(y).setHuoru(Double.parseDouble(df.format(cglist.get(y).getHuoru() == null ? 0.0 : cglist.get(y).getHuoru())));
				}
				// 统计汽运入库重量
				List<InputSeed> qiyunruku = this.inputSeedService.getSFCYunShu(tf.getBegin(), tf.getFinish(), cglist.get(i).getClient().getClientId(), cglist.get(i).getGoodsName(), "汽运");
				if (qiyunruku.size() > 0 && qiyunruku.get(0).getIseedRealityWeight() != null) {
					cglist.get(y).setQiru(Double.parseDouble(df.format((cglist.get(y).getQiru() == null ? 0.0 : cglist.get(y).getQiru())+ (qiyunruku.get(0).getIseedRealityWeight()))));
				} else {
					cglist.get(y).setQiru(Double.parseDouble(df.format((cglist.get(y).getQiru() == null ? 0.0 : cglist.get(y).getQiru()))));
				}

				if (i == (len - 1)) {
					JSONObject obj = new JSONObject();
					obj.put("kehu", cglist.get(i).getClient().getClientAbbreviation());// 保存客户简称
					obj.put("pinlei", cglist.get(i).getPinlei());// 保存品类
					obj.put("mingcheng", cglist.get(i).getGoodsName());// 保存货物名称
					obj.put("qichu", df.format(cglist.get(y).getQiChu()));// 保存期初库存
					obj.put("rukuheji", df.format(cglist.get(y).getInputHeJi()));// 保存入库合计
					obj.put("chukuheji", df.format(cglist.get(y).getExportHeJi()));// 保存出库合计
					obj.put("qimo", df.format(cglist.get(y).getQiMo()));// 保存期末库存
					obj.put("guohuchu", df.format(cglist.get(y).getGuohuchu()));// 保存过户出库重量
					obj.put("guohuru", df.format(cglist.get(y).getGuohuru()));// 保存过户入库重量
					obj.put("huochu", df.format(cglist.get(y).getHuochu()));// 保存火车出库的重量
					obj.put("qichechu", df.format(cglist.get(y).getQichechu()));// 保存汽车出库的重量
					obj.put("huoru", df.format(cglist.get(y).getHuoru()));// 保存火车入库重量
					obj.put("qiru", df.format(cglist.get(y).getQiru()));// 保存汽车入库重量
					obj.put("result", "notnull");
					obj.put("pageCount", pageCount);
					obj.put("pageNow", pageNow);
					array.add(obj);
				}
			} else {
				clientId = cglist.get(i).getClient().getClientId();
				goodsName = cglist.get(i).getGoodsName();
				i--;
				JSONObject obj = new JSONObject();
				obj.put("kehu", cglist.get(i).getClient().getClientAbbreviation());// 保存客户简称
				obj.put("pinlei", cglist.get(i).getPinlei());// 保存品类
				obj.put("mingcheng", cglist.get(i).getGoodsName());// 保存货物名称
				obj.put("qichu", df.format(cglist.get(y).getQiChu()));// 保存期初库存
				obj.put("rukuheji", df.format(cglist.get(y).getInputHeJi()));// 保存入库合计
				obj.put("chukuheji", df.format(cglist.get(y).getExportHeJi()));// 保存出库合计
				obj.put("qimo", df.format(cglist.get(y).getQiMo()));// 保存期末库存
				obj.put("guohuchu", df.format(cglist.get(y).getGuohuchu()));// 保存过户出库重量
				obj.put("guohuru", df.format(cglist.get(y).getGuohuru()));// 保存过户入库重量
				obj.put("huochu", df.format(cglist.get(y).getHuochu()));// 保存火车出库的重量
				obj.put("qichechu", df.format(cglist.get(y).getQichechu()));// 保存汽车出库的重量
				obj.put("huoru", df.format(cglist.get(y).getHuoru()));// 保存火车入库重量
				obj.put("qiru", df.format(cglist.get(y).getQiru()));// 保存汽车入库重量
				obj.put("result", "notnull");
				obj.put("pageCount", pageCount);
				obj.put("pageNow", pageNow);
				array.add(obj);
				y++;
			}
		}
		out.print(array.toString());
		out.flush();
		out.close();

		return null;
	}

	// 用来统计鑫港客户的收发存
	public ActionForward TongJiXGKHSFC(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/* 设置响应头允许ajax跨域访问 */
		response.setHeader("Access-Control-Allow-Origin", "*");
		/* 星号表示所有的异域请求都可以接受， */
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");

		TidingsForm tf = (TidingsForm) form;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		if (tf.getBegin() == null || tf.getBegin().equals("")) {
			tf.setBegin(pt.getNowJianYi());
		} else {
			tf.setBegin(tf.getBegin());
		}
		if (tf.getFinish() == null || tf.getFinish().equals("")) {
			tf.setFinish(pt.getTimes());
		} else if (sdf.parse(pt.getTimes()).getTime() > sdf.parse(tf.getFinish()).getTime()) {
			tf.setFinish(tf.getFinish());
		} else {
			tf.setFinish(pt.getTimes());
		}
		if (tf.getGoodsName() == null) {
			tf.setGoodsName("");
		}

		if (tf.getJiancheng() == null) {
			tf.setJiancheng("");
		}

		int pageNow = 1;
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}

		int pageRow = 20;
		if (request.getParameter("pageRow") != null && request.getParameter("pageRow").equals("") == false) {
			pageRow = Integer.parseInt(request.getParameter("pageRow").toString());
		}

		int pageCount = this.clientGoodsService.getKeHuSFCByPageCount(tf.getJiancheng(), tf.getGoodsName(), pageRow);
		if (pageNow >= pageCount) {
			pageNow = pageCount;
		}
		if (pageNow <= 1) {
			pageNow = 1;
		}

		JSONArray array = new JSONArray();
		PrintWriter out = response.getWriter();

		List<ClientGoods> cglist = this.clientGoodsService.getKeHuSFCByPage(tf.getJiancheng(), tf.getGoodsName(), pageNow, pageRow);

		if (cglist.size() <= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		DecimalFormat df = new DecimalFormat("#########0.000");
		Integer clientId = 0;
		String goodsName = "";
		int len = cglist.size();
		int y = 0;

		for (int i = 0; i < len; i++) {
			if (i == 0) {
				clientId = cglist.get(i).getClient().getClientId();
				goodsName = cglist.get(i).getGoodsName();
			}
			if (cglist.get(i).getClient().getClientId().equals(clientId) && goodsName.equals(cglist.get(i).getGoodsName())) {

				// 统计出库合计
				List<ExportSeed> eslist = this.exportSeedService.getKeHuKunCunCKHJSFC(tf.getBegin(), tf.getFinish(),cglist.get(i).getClient().getClientId(), cglist.get(i).getGoodsName());
				if (eslist.size() > 0 && eslist.get(0).getEseedRealityWeight() != null) {
					cglist.get(y).setExportHeJi(Double.parseDouble(df.format((cglist.get(y).getExportHeJi() == null ? 0.0: cglist.get(y).getExportHeJi())+ (eslist.get(0).getEseedRealityWeight() == null ? 0.0: eslist.get(0).getEseedRealityWeight()))));
				} else {
					cglist.get(y).setExportHeJi((cglist.get(y).getExportHeJi() == null ? 0.0: cglist.get(y).getExportHeJi()));
				}
				// 统计出库合计结束------
				// 统计入库的期初
				List<InputSeed> islist = this.inputSeedService.getKeHuKuCunQCHJSFC(tf.getBegin(), cglist.get(i).getClient().getClientId(), cglist.get(i).getGoodsName());
				if (islist.size() > 0 && islist.get(0).getIseedRealityWeight() != null) {
					cglist.get(y).setQiChu(Double.parseDouble(df.format(islist.get(0).getIseedRealityWeight()+ (cglist.get(y).getQiChu() == null ? 0.0: cglist.get(y).getQiChu()))));
				} else {
					cglist.get(y).setQiChu((cglist.get(y).getQiChu() == null ? 0.0 : cglist.get(y).getQiChu()));
				}
				// 统计入库的期初结束
				// 统计出库期初
				List<ExportSeed> esqclist = this.exportSeedService.getKeHuKunCunQCHJSFC(tf.getBegin(), cglist.get(i).getClient().getClientId(), cglist.get(i).getGoodsName());
				if (esqclist.size() > 0&& esqclist.get(0).getEseedRealityWeight() != null) {
					cglist.get(y).setQiChu(Double.parseDouble(df.format(cglist.get(y).getQiChu()- esqclist.get(0).getEseedRealityWeight())));
				}
				// 统计出库期初结束
				// 统计入库合计---------------------------------------------------------------------------------------------+++++
				List<InputSeed> isrklist = this.inputSeedService.getKeHuKuCunRKHJSFC(tf.getBegin(), tf.getFinish(),cglist.get(i).getClient().getClientId(), cglist.get(i).getGoodsName());
				if (isrklist.size() > 0 && isrklist.get(0).getIseedRealityWeight() != null) {
					cglist.get(y).setInputHeJi((isrklist.get(0).getIseedRealityWeight() == null ? 0.0: isrklist.get(0).getIseedRealityWeight())+ (cglist.get(y).getInputHeJi() == null ? 0.0: cglist.get(y).getInputHeJi()));
				} else {
					cglist.get(y).setInputHeJi(cglist.get(y).getInputHeJi() == null ? 0.0 : cglist.get(y).getInputHeJi());
				}
				// 统计入库合计结束
				// 过户转出合计，统计到出库合计中
				List<ShiftStockSeed> ssslist = this.shiftStockSeedService.getKeHuKuCunGHZCHJSFC(tf.getBegin(), tf.getFinish(),cglist.get(i).getClient().getClientId(), cglist.get(i).getGoodsName());
				if (ssslist.size() > 0&& ssslist.get(0).getSsseedWeight() != null) {
					cglist.get(y).setExportHeJi(Double.parseDouble(df.format(cglist.get(y).getExportHeJi()+ ssslist.get(0).getSsseedWeight())));
				}

				// 过户转出统计结束
				// 统计过户转入开始
				List<ShiftStockSeed> ssszulist = this.shiftStockSeedService.getKeHuKuCunGHZRHJSFC(tf.getBegin(), tf.getFinish(),cglist.get(i).getClient().getClientId(), cglist.get(i).getGoodsName());
				if (ssszulist.size() > 0&& ssszulist.get(0).getSsseedWeight() != null) {
					cglist.get(y).setInputHeJi(Double.parseDouble(df.format(cglist.get(y).getInputHeJi()+ ssszulist.get(0).getSsseedWeight())));
				}
				// 统计过户转入结束
				// 统计过户转出期初统计
				List<ShiftStockSeed> ssszclist = this.shiftStockSeedService.getKeHuKuCunZCQCHJSFC(tf.getBegin(), cglist.get(i).getClient().getClientId(), cglist.get(i).getGoodsName());
				if (ssszclist.size() > 0 && ssszclist.get(0) != null) {
					cglist.get(y).setQiChu(Double.parseDouble(df.format(cglist.get(y).getQiChu()- (ssszclist.get(0).getSsseedWeight()))));
				}
				// 统计过户转出期初统计结束
				// 统计过户转入期初的统计
				List<ShiftStockSeed> sssqczr = this.shiftStockSeedService.getKeHuKuCunZRQCHJSFC(tf.getBegin(), cglist.get(i).getClient().getClientId(), cglist.get(i).getGoodsName());
				if (sssqczr.size() > 0 && sssqczr.get(0) != null) {
					cglist.get(y).setQiChu(Double.parseDouble(df.format(cglist.get(y).getQiChu()+ sssqczr.get(0).getSsseedWeight())));
				}
				// 统计过户的转入期初结束

				cglist.get(y).setQiMo(Double.parseDouble(df.format((cglist.get(y).getQiChu() + cglist.get(y).getInputHeJi())- cglist.get(y).getExportHeJi())));

				// 统计过户入库
				List<ShiftStockSeed> sssguohulist = this.shiftStockSeedService.getKeHuKuCunGHZRHJSFC(tf.getBegin(), tf.getFinish(),cglist.get(i).getClient().getClientId(), cglist.get(i).getGoodsName());
				if (sssguohulist.size() > 0&& sssguohulist.get(0).getSsseedWeight() != null) {
					cglist.get(y).setGuohuru(Double.parseDouble(df.format((cglist.get(y).getGuohuru() == null ? 0.0 : cglist.get(y).getGuohuru())+ sssguohulist.get(0).getSsseedWeight())));
				} else {
					cglist.get(y).setGuohuru(Double.parseDouble(df.format(cglist.get(y).getGuohuru() == null ? 0.0 : cglist.get(y).getGuohuru())));
				}

				// 统计过户出库
				List<ShiftStockSeed> ssszhuanchulist = this.shiftStockSeedService.getKeHuKuCunGHZCHJSFC(tf.getBegin(), tf.getFinish(),cglist.get(i).getClient().getClientId(), cglist.get(i).getGoodsName());
				if (ssszhuanchulist.size() > 0&& ssszhuanchulist.get(0).getSsseedWeight() != null) {
					cglist.get(y).setGuohuchu(Double.parseDouble(df.format((cglist.get(y).getGuohuchu() == null ? 0.0: cglist.get(y).getGuohuchu())+ ssszhuanchulist.get(0).getSsseedWeight())));
				} else {
					cglist.get(y).setGuohuchu(Double.parseDouble(df.format(cglist.get(y).getGuohuchu() == null ? 0.0 : cglist.get(y).getGuohuchu())));
				}
				// 统计火运出库重量
				List<ExportSeed> huoyunchuku = this.exportSeedService.getSFCYunShu(tf.getBegin(), tf.getFinish(), cglist.get(i).getClient().getClientId(), cglist.get(i).getGoodsName(), "火运");
				if (huoyunchuku.size() > 0&& huoyunchuku.get(0).getEseedRealityWeight() != null) {
					cglist.get(y).setHuochu(Double.parseDouble(df.format((cglist.get(y).getHuochu() == null ? 0.0 : cglist.get(y).getHuochu())+ (huoyunchuku.get(0).getEseedRealityWeight()))));
				} else {
					cglist.get(y).setHuochu(Double.parseDouble(df.format(cglist.get(y).getHuochu() == null ? 0.0 : cglist.get(y).getHuochu())));
				}
				// 统计汽运出库重量
				List<ExportSeed> qiyunchuku = this.exportSeedService.getSFCYunShu(tf.getBegin(), tf.getFinish(), cglist.get(i).getClient().getClientId(), cglist.get(i).getGoodsName(), "汽运");
				if (qiyunchuku.size() > 0&& qiyunchuku.get(0).getEseedRealityWeight() != null) {
					cglist.get(y).setQichechu(Double.parseDouble(df.format((cglist.get(y).getQichechu() == null ? 0.0 : cglist.get(y).getQichechu())+ (qiyunchuku.get(0).getEseedRealityWeight()))));
				} else {
					cglist.get(y).setQichechu(Double.parseDouble(df.format(cglist.get(y).getQichechu() == null ? 0.0 : cglist.get(y).getQichechu())));
				}

				// 统计火运入库重量
				List<InputSeed> huoyunruku = this.inputSeedService.getSFCYunShu(tf.getBegin(), tf.getFinish(), cglist.get(i).getClient().getClientId(), cglist.get(i).getGoodsName(), "火运");
				if (huoyunruku.size() > 0 && huoyunruku.get(0).getIseedRealityWeight() != null) {
					cglist.get(y).setHuoru(Double.parseDouble(df.format((cglist.get(y).getHuoru() == null ? 0.0 : cglist.get(y).getHuoru())+ (huoyunruku.get(0).getIseedRealityWeight()))));
				} else {
					cglist.get(y).setHuoru(Double.parseDouble(df.format(cglist.get(y).getHuoru() == null ? 0.0 : cglist.get(y).getHuoru())));
				}
				// 统计汽运入库重量
				List<InputSeed> qiyunruku = this.inputSeedService.getSFCYunShu(tf.getBegin(), tf.getFinish(), cglist.get(i).getClient().getClientId(), cglist.get(i).getGoodsName(), "汽运");
				if (qiyunruku.size() > 0&& qiyunruku.get(0).getIseedRealityWeight() != null) {
					cglist.get(y).setQiru(Double.parseDouble(df.format((cglist.get(y).getQiru() == null ? 0.0 : cglist.get(y).getQiru())+ (qiyunruku.get(0).getIseedRealityWeight()))));
				} else {
					cglist.get(y).setQiru(Double.parseDouble(df.format((cglist.get(y).getQiru() == null ? 0.0 : cglist.get(y).getQiru()))));
				}

				if (i == (len - 1)) {
					JSONObject obj = new JSONObject();
					obj.put("kehu", cglist.get(i).getClient().getClientAbbreviation());// 保存客户简称
					obj.put("pinlei", cglist.get(i).getPinlei());// 保存品类
					obj.put("mingcheng", cglist.get(i).getGoodsName());// 保存货物名称
					obj.put("qichu", df.format(cglist.get(y).getQiChu()));// 保存期初库存
					obj.put("rukuheji", df.format(cglist.get(y).getInputHeJi()));// 保存入库合计
					obj.put("chukuheji", df.format(cglist.get(y).getExportHeJi()));// 保存出库合计
					obj.put("qimo", df.format(cglist.get(y).getQiMo()));// 保存期末库存
					obj.put("guohuchu", df.format(cglist.get(y).getGuohuchu()));// 保存过户出库重量
					obj.put("guohuru", df.format(cglist.get(y).getGuohuru()));// 保存过户入库重量
					obj.put("huochu", df.format(cglist.get(y).getHuochu()));// 保存火车出库的重量
					obj.put("qichechu", df.format(cglist.get(y).getQichechu()));// 保存汽车出库的重量
					obj.put("huoru", df.format(cglist.get(y).getHuoru()));// 保存火车入库重量
					obj.put("qiru", df.format(cglist.get(y).getQiru()));// 保存汽车入库重量
					obj.put("result", "notnull");
					obj.put("pageCount", pageCount);
					obj.put("pageNow", pageNow);
					array.add(obj);
				}
			} else {
				clientId = cglist.get(i).getClient().getClientId();
				goodsName = cglist.get(i).getGoodsName();
				i--;
				JSONObject obj = new JSONObject();
				obj.put("kehu", cglist.get(i).getClient().getClientAbbreviation());// 保存客户简称
				obj.put("pinlei", cglist.get(i).getPinlei());// 保存品类
				obj.put("mingcheng", cglist.get(i).getGoodsName());// 保存货物名称
				obj.put("qichu", df.format(cglist.get(y).getQiChu()));// 保存期初库存
				obj.put("rukuheji", df.format(cglist.get(y).getInputHeJi()));// 保存入库合计
				obj.put("chukuheji", df.format(cglist.get(y).getExportHeJi()));// 保存出库合计
				obj.put("qimo", df.format(cglist.get(y).getQiMo()));// 保存期末库存
				obj.put("guohuchu", df.format(cglist.get(y).getGuohuchu()));// 保存过户出库重量
				obj.put("guohuru", df.format(cglist.get(y).getGuohuru()));// 保存过户入库重量
				obj.put("huochu", df.format(cglist.get(y).getHuochu()));// 保存火车出库的重量
				obj.put("qichechu", df.format(cglist.get(y).getQichechu()));// 保存汽车出库的重量
				obj.put("huoru", df.format(cglist.get(y).getHuoru()));// 保存火车入库重量
				obj.put("qiru", df.format(cglist.get(y).getQiru()));// 保存汽车入库重量
				obj.put("result", "notnull");
				obj.put("pageCount", pageCount);
				obj.put("pageNow", pageNow);
				array.add(obj);
				y++;
			}
		}
		out.print(array.toString());
		out.flush();
		out.close();
		return null;
	}

	// ---------------客户查询收发存

	// 客户统计自己的收发存
	public ActionForward TongJiXGKHSFCClient(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 返回行数
		int pageRow = 10;
		if(request.getParameter("pageRow")!=null){
			pageRow = Integer.parseInt(request.getParameter("pageRow").toString());
		}
		// 获取客户id
		int kehuId = Integer.parseInt(request.getSession().getAttribute("clientId").toString());

		TidingsForm tf = (TidingsForm) form;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (tf.getBegin() == null || tf.getBegin().equals("")) {
			tf.setBegin(pt.getNowJianYi());
		} else {
			tf.setBegin(tf.getBegin());
		}
		if (tf.getFinish() == null || tf.getFinish().equals("")) {
			tf.setFinish(pt.getTimes());
		} else if (sdf.parse(pt.getTimes()).getTime() > sdf.parse(tf.getFinish()).getTime()) {
			tf.setFinish(tf.getFinish());
		} else {
			tf.setFinish(pt.getTimes());
		}
		if (tf.getGoodsName() == null) {
			tf.setGoodsName("");
		}

		if (tf.getJiancheng() == null) {
			tf.setJiancheng("");
		}

		int pageNow = 1;
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}

		int pageCount = this.clientGoodsService.getKeHuSFCByPageCountKH(kehuId, tf.getGoodsName(), pageRow);

		if (pageNow >= pageCount) {
			pageNow = pageCount;
		}
		if (pageNow <= 1) {
			pageNow = 1;
		}

		JSONArray array = new JSONArray();
		PrintWriter out = response.getWriter();

		List<ClientGoods> cglist = this.clientGoodsService.getKeHuSFCByPageKH(kehuId, tf.getGoodsName(), pageNow, pageRow);

		if (cglist.size() <= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}

		DecimalFormat df = new DecimalFormat("#########0.000");
		Integer clientId = 0;
		String goodsName = "";
		int len = cglist.size();
		int y = 0;
		for (int i = 0; i < len; i++) {
			if (i == 0) {
				clientId = cglist.get(i).getClient().getClientId();
				goodsName = cglist.get(i).getGoodsName();
			}
			if (cglist.get(i).getClient().getClientId().equals(clientId)&& goodsName.equals(cglist.get(i).getGoodsName())) {
				// 统计出库合计
				List<ExportSeed> eslist = this.exportSeedService.getKeHuKunCunCKHJSFC(tf.getBegin(), tf.getFinish(),cglist.get(i).getClient().getClientId(), cglist.get(i).getGoodsName());
				if (eslist.size() > 0&& eslist.get(0).getEseedRealityWeight() != null) {
					cglist.get(y).setExportHeJi(Double.parseDouble(df.format((cglist.get(y).getExportHeJi() == null ? 0.0: cglist.get(y).getExportHeJi())+ (eslist.get(0).getEseedRealityWeight() == null ? 0.0: eslist.get(0).getEseedRealityWeight()))));
				} else {
					cglist.get(y).setExportHeJi((cglist.get(y).getExportHeJi() == null ? 0.0: cglist.get(y).getExportHeJi()));
				}
				// 统计出库合计结束------
				// 统计入库的期初
				List<InputSeed> islist = this.inputSeedService.getKeHuKuCunQCHJSFC(tf.getBegin(), cglist.get(i).getClient().getClientId(), cglist.get(i).getGoodsName());
				if (islist.size() > 0&& islist.get(0).getIseedRealityWeight() != null) {
					cglist.get(y).setQiChu(Double.parseDouble(df.format(islist.get(0).getIseedRealityWeight()+ (cglist.get(y).getQiChu() == null ? 0.0: cglist.get(y).getQiChu()))));
				} else {
					cglist.get(y).setQiChu((cglist.get(y).getQiChu() == null ? 0.0 : cglist.get(y).getQiChu()));
				}
				// 统计入库的期初结束
				// 统计出库期初
				List<ExportSeed> esqclist = this.exportSeedService.getKeHuKunCunQCHJSFC(tf.getBegin(), cglist.get(i).getClient().getClientId(), cglist.get(i).getGoodsName());
				if (esqclist.size() > 0&& esqclist.get(0).getEseedRealityWeight() != null) {
					cglist.get(y).setQiChu(Double.parseDouble(df.format(cglist.get(y).getQiChu()- esqclist.get(0).getEseedRealityWeight())));
				}
				// 统计出库期初结束
				// 统计入库合计---------------------------------------------------------------------------------------------+++++
				List<InputSeed> isrklist = this.inputSeedService.getKeHuKuCunRKHJSFC(tf.getBegin(), tf.getFinish(),cglist.get(i).getClient().getClientId(), cglist.get(i).getGoodsName());
				if (isrklist.size() > 0 && isrklist.get(0).getIseedRealityWeight() != null) {
					cglist.get(y).setInputHeJi((isrklist.get(0).getIseedRealityWeight() == null ? 0.0: isrklist.get(0).getIseedRealityWeight())+ (cglist.get(y).getInputHeJi() == null ? 0.0: cglist.get(y).getInputHeJi()));
				} else {
					cglist.get(y).setInputHeJi(cglist.get(y).getInputHeJi() == null ? 0.0 : cglist.get(y).getInputHeJi());
				}
				// 统计入库合计结束
				// 过户转出合计，统计到出库合计中
				List<ShiftStockSeed> ssslist = this.shiftStockSeedService.getKeHuKuCunGHZCHJSFC(tf.getBegin(), tf.getFinish(),cglist.get(i).getClient().getClientId(), cglist.get(i).getGoodsName());
				if (ssslist.size() > 0 && ssslist.get(0).getSsseedWeight() != null) {
					cglist.get(y).setExportHeJi(Double.parseDouble(df.format(cglist.get(y).getExportHeJi()+ ssslist.get(0).getSsseedWeight())));
				}

				// 过户转出统计结束
				// 统计过户转入开始
				List<ShiftStockSeed> ssszulist = this.shiftStockSeedService.getKeHuKuCunGHZRHJSFC(tf.getBegin(), tf.getFinish(),cglist.get(i).getClient().getClientId(), cglist.get(i).getGoodsName());
				if (ssszulist.size() > 0&& ssszulist.get(0).getSsseedWeight() != null) {
					cglist.get(y).setInputHeJi(Double.parseDouble(df.format(cglist.get(y).getInputHeJi()+ ssszulist.get(0).getSsseedWeight())));
				}
				// 统计过户转入结束
				// 统计过户转出期初统计
				List<ShiftStockSeed> ssszclist = this.shiftStockSeedService.getKeHuKuCunZCQCHJSFC(tf.getBegin(), cglist.get(i).getClient().getClientId(), cglist.get(i).getGoodsName());
				if (ssszclist.size() > 0 && ssszclist.get(0) != null) {
					cglist.get(y).setQiChu(Double.parseDouble(df.format(cglist.get(y).getQiChu()- (ssszclist.get(0).getSsseedWeight()))));
				}
				// 统计过户转出期初统计结束
				// 统计过户转入期初的统计
				List<ShiftStockSeed> sssqczr = this.shiftStockSeedService.getKeHuKuCunZRQCHJSFC(tf.getBegin(), cglist.get(i).getClient().getClientId(), cglist.get(i).getGoodsName());
				if (sssqczr.size() > 0 && sssqczr.get(0) != null) {
					cglist.get(y).setQiChu(Double.parseDouble(df.format(cglist.get(y).getQiChu()+ sssqczr.get(0).getSsseedWeight())));
				}
				// 统计过户的转入期初结束

				cglist.get(y).setQiMo(Double.parseDouble(df.format((cglist.get(y).getQiChu() + cglist.get(y).getInputHeJi())- cglist.get(y).getExportHeJi())));

				// 统计过户入库
				List<ShiftStockSeed> sssguohulist = this.shiftStockSeedService.getKeHuKuCunGHZRHJSFC(tf.getBegin(), tf.getFinish(),cglist.get(i).getClient().getClientId(), cglist.get(i).getGoodsName());
				if (sssguohulist.size() > 0 && sssguohulist.get(0).getSsseedWeight() != null) {
					cglist.get(y).setGuohuru(Double.parseDouble(df.format((cglist.get(y).getGuohuru() == null ? 0.0 : cglist.get(y).getGuohuru())+ sssguohulist.get(0).getSsseedWeight())));
				} else {
					cglist.get(y).setGuohuru(Double.parseDouble(df.format(cglist.get(y).getGuohuru() == null ? 0.0 : cglist.get(y).getGuohuru())));
				}

				// 统计过户出库
				List<ShiftStockSeed> ssszhuanchulist = this.shiftStockSeedService.getKeHuKuCunGHZCHJSFC(tf.getBegin(), tf.getFinish(),cglist.get(i).getClient().getClientId(), cglist.get(i).getGoodsName());
				if (ssszhuanchulist.size() > 0&& ssszhuanchulist.get(0).getSsseedWeight() != null) {
					cglist.get(y).setGuohuchu(Double.parseDouble(df.format((cglist.get(y).getGuohuchu() == null ? 0.0: cglist.get(y).getGuohuchu())+ ssszhuanchulist.get(0).getSsseedWeight())));
				} else {
					cglist.get(y).setGuohuchu(Double.parseDouble(df.format(cglist.get(y).getGuohuchu() == null ? 0.0 : cglist.get(y).getGuohuchu())));
				}
				// 统计火运出库重量
				List<ExportSeed> huoyunchuku = this.exportSeedService.getSFCYunShu(tf.getBegin(), tf.getFinish(), cglist.get(i).getClient().getClientId(), cglist.get(i).getGoodsName(), "火运");
				if (huoyunchuku.size() > 0 && huoyunchuku.get(0).getEseedRealityWeight() != null) {
					cglist.get(y).setHuochu(Double.parseDouble(df.format((cglist.get(y).getHuochu() == null ? 0.0 : cglist.get(y).getHuochu())+ (huoyunchuku.get(0).getEseedRealityWeight()))));
				} else {
					cglist.get(y).setHuochu(Double.parseDouble(df.format(cglist.get(y).getHuochu() == null ? 0.0 : cglist.get(y).getHuochu())));
				}
				// 统计汽运出库重量
				List<ExportSeed> qiyunchuku = this.exportSeedService.getSFCYunShu(tf.getBegin(), tf.getFinish(), cglist.get(i).getClient().getClientId(), cglist.get(i).getGoodsName(), "汽运");
				if (qiyunchuku.size() > 0&& qiyunchuku.get(0).getEseedRealityWeight() != null) {
					cglist.get(y).setQichechu(Double.parseDouble(df.format((cglist.get(y).getQichechu() == null ? 0.0 : cglist.get(y).getQichechu())+ (qiyunchuku.get(0).getEseedRealityWeight()))));
				} else {
					cglist.get(y).setQichechu(Double.parseDouble(df.format(cglist.get(y).getQichechu() == null ? 0.0 : cglist.get(y).getQichechu())));
				}

				// 统计火运入库重量
				List<InputSeed> huoyunruku = this.inputSeedService.getSFCYunShu(tf.getBegin(), tf.getFinish(), cglist.get(i).getClient().getClientId(), cglist.get(i).getGoodsName(), "火运");
				if (huoyunruku.size() > 0 && huoyunruku.get(0).getIseedRealityWeight() != null) {
					cglist.get(y).setHuoru(Double.parseDouble(df.format((cglist.get(y).getHuoru() == null ? 0.0 : cglist.get(y).getHuoru())+ (huoyunruku.get(0).getIseedRealityWeight()))));
				} else {
					cglist.get(y).setHuoru(Double.parseDouble(df.format(cglist.get(y).getHuoru() == null ? 0.0 : cglist.get(y).getHuoru())));
				}
				// 统计汽运入库重量
				List<InputSeed> qiyunruku = this.inputSeedService.getSFCYunShu(tf.getBegin(), tf.getFinish(), cglist.get(i).getClient().getClientId(), cglist.get(i).getGoodsName(), "汽运");
				if (qiyunruku.size() > 0 && qiyunruku.get(0).getIseedRealityWeight() != null) {
					cglist.get(y).setQiru(Double.parseDouble(df.format((cglist.get(y).getQiru() == null ? 0.0 : cglist.get(y).getQiru())+ (qiyunruku.get(0).getIseedRealityWeight()))));
				} else {
					cglist.get(y).setQiru(Double.parseDouble(df.format((cglist.get(y).getQiru() == null ? 0.0 : cglist.get(y).getQiru()))));
				}

				if (i == (len - 1)) {
					JSONObject obj = new JSONObject();
					obj.put("kehu", cglist.get(i).getClient().getClientAbbreviation());// 保存客户简称
					obj.put("pinlei", cglist.get(i).getPinlei());// 保存品类
					obj.put("mingcheng", cglist.get(i).getGoodsName());// 保存货物名称
					obj.put("qichu", df.format(cglist.get(y).getQiChu()));// 保存期初库存
					obj.put("rukuheji", df.format(cglist.get(y).getInputHeJi()));// 保存入库合计
					obj.put("chukuheji",df.format(cglist.get(y).getExportHeJi()));// 保存出库合计
					obj.put("qimo", df.format(cglist.get(y).getQiMo()));// 保存期末库存
					obj.put("guohuchu", df.format(cglist.get(y).getGuohuchu()));// 保存过户出库重量
					obj.put("guohuru", df.format(cglist.get(y).getGuohuru()));// 保存过户入库重量
					obj.put("huochu", df.format(cglist.get(y).getHuochu()));// 保存火车出库的重量
					obj.put("qichechu", df.format(cglist.get(y).getQichechu()));// 保存汽车出库的重量
					obj.put("huoru", df.format(cglist.get(y).getHuoru()));// 保存火车入库重量
					obj.put("qiru", df.format(cglist.get(y).getQiru()));// 保存汽车入库重量
					obj.put("result", "notnull");
					obj.put("pageNow", pageNow);
					obj.put("pageCount", pageCount);
					array.add(obj);
				}
			} else {
				clientId = cglist.get(i).getClient().getClientId();
				goodsName = cglist.get(i).getGoodsName();
				i--;
				JSONObject obj = new JSONObject();
				obj.put("kehu", cglist.get(i).getClient().getClientAbbreviation());// 保存客户简称
				obj.put("pinlei", cglist.get(i).getPinlei());// 保存品类
				obj.put("mingcheng", cglist.get(i).getGoodsName());// 保存货物名称
				obj.put("qichu", df.format(cglist.get(y).getQiChu()));// 保存期初库存
				obj.put("rukuheji", df.format(cglist.get(y).getInputHeJi()));// 保存入库合计
				obj.put("chukuheji", df.format(cglist.get(y).getExportHeJi()));// 保存出库合计
				obj.put("qimo", df.format(cglist.get(y).getQiMo()));// 保存期末库存
				obj.put("guohuchu", df.format(cglist.get(y).getGuohuchu()));// 保存过户出库重量
				obj.put("guohuru", df.format(cglist.get(y).getGuohuru()));// 保存过户入库重量
				obj.put("huochu", df.format(cglist.get(y).getHuochu()));// 保存火车出库的重量
				obj.put("qichechu", df.format(cglist.get(y).getQichechu()));// 保存汽车出库的重量
				obj.put("huoru", df.format(cglist.get(y).getHuoru()));// 保存火车入库重量
				obj.put("qiru", df.format(cglist.get(y).getQiru()));// 保存汽车入库重量
				obj.put("result", "notnull");
				obj.put("pageNow", pageNow);
				obj.put("pageCount", pageCount);
				array.add(obj);
				y++;
			}
		}
		out.print(array.toString());
		out.flush();
		out.close();
		return null;
	}

	// 通过客户、起始日期、结束日期、货物名称统计相应的总的收发存
	public ActionForward getSum(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TidingsForm tf = (TidingsForm) form;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		if (tf.getBegin() == null || tf.getBegin().equals("")) {
			tf.setBegin(pt.getNowJianYi());
		} else {
			tf.setBegin(tf.getBegin());
		}
		if (tf.getFinish() == null || tf.getFinish().equals("")) {
			tf.setFinish(pt.getTimes());
		} else if (sdf.parse(pt.getTimes()).getTime() > sdf.parse(
				tf.getFinish()).getTime()) {
			tf.setFinish(tf.getFinish());
		} else {
			tf.setFinish(pt.getTimes());
		}
		if (tf.getGoodsName() == null) {
			tf.setGoodsName("");
		}

		if (tf.getJiancheng() == null) {
			tf.setJiancheng("");
		}
		DecimalFormat df = new DecimalFormat("#################0.000");
		Double ruku = 0.0;// 入库
		Double chuku = 0.0;// 出库
		Double qichu = 0.0;// 期初
		Double qimo = 0.0;// 期末
		Double qichuRu = 0.0;//期初入库合计
		Double qichuChu = 0.0;//期初出库合计
		Double qichuGuoRu = 0.0;//期初过户合计
		Double qichuGuoChu = 0.0;//期初过户出合计
		Double guoRu = 0.0;//过户入合计
		Double guoChu = 0.0;//过户出合计
		
		// 统计相应客户的入库合计
		ruku = this.inputSeedService.getRuKuSum(tf.getBegin(), tf.getFinish(), tf.getJiancheng(), tf.getGoodsName(), "ruku");
		//统计该时间段的出库合计
		chuku = this.inputSeedService.getChuKuSum(tf.getBegin(), tf.getFinish(), tf.getJiancheng(), tf.getGoodsName(), "chuku");
		//统计该入库的期初合计
		qichuRu = this.inputSeedService.getRuKuSum(tf.getBegin(), tf.getFinish(), tf.getJiancheng(), tf.getGoodsName(), "qichu");
		//统计出库的期初合计
		qichuChu = this.inputSeedService.getChuKuSum(tf.getBegin(), tf.getFinish(), tf.getJiancheng(), tf.getGoodsName(), "qichu");
		//统计该入库的期初合计
		qichuGuoRu = this.inputSeedService.getGuoHuSum(tf.getBegin(), tf.getFinish(), tf.getJiancheng(), tf.getGoodsName(), "qiru");
		//统计该入库的期初合计
		qichuGuoChu = this.inputSeedService.getGuoHuSum(tf.getBegin(), tf.getFinish(), tf.getJiancheng(), tf.getGoodsName(), "qichu");
		//统计过户入库的合计
		guoRu = this.inputSeedService.getGuoHuSum(tf.getBegin(), tf.getFinish(), tf.getJiancheng(), tf.getGoodsName(), "ru");
		//统计过户出库的合计
		guoChu = this.inputSeedService.getGuoHuSum(tf.getBegin(), tf.getFinish(), tf.getJiancheng(), tf.getGoodsName(), "chu");
		//计算相应的期初总和
		qichu = (qichuRu + qichuGuoRu) - (qichuChu + qichuGuoChu);
		ruku = ruku + guoRu;
		chuku = chuku + guoChu;
		qimo = qichu + (ruku - chuku);
		
		JSONArray array = new JSONArray();
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		obj.put("ruku", df.format(ruku));
		obj.put("chuku", df.format(chuku));
		obj.put("qichu", df.format(qichu));
		obj.put("qimo", df.format(qimo));
		array.add(obj);
		out.print(array);
		out.flush();
		out.close();
		return null;
	}
	
	//显示图标的数据
	public ActionForward getTuBiao(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONArray array = new JSONArray();
		PrintWriter out = response.getWriter();
		//得到分组年的集合
		List<String> yearList = this.exportSeedService.getGroupByYear();
		String year = "[";
		String rukus = "";
		String chukus = "";
		String guohus = "";
		DecimalFormat df=new DecimalFormat("#############0.000");
		//用循环的方式查询每年每月的出、入、过的量
		for(int i=0; i<yearList.size(); i++){
			String rukuv = "";
			String chukuv = "";
			String guohuv = "";
			rukuv +="[";
			chukuv +="[";
			guohuv +="[";
			for(int d=1; d<=12; d++){
				String ds = "";
				if(d<10){
					ds ="0"+d;
				}else{
					ds=String.valueOf(d);
				}
				rukuv += df.format(this.exportSeedService.getLikeDate(yearList.get(i)+"-"+ds, "ruku"))+",";
				chukuv+=df.format(this.exportSeedService.getLikeDate(yearList.get(i)+"-"+ds, "chuku"))+",";
				guohuv+=df.format(this.exportSeedService.getLikeDate(yearList.get(i)+"-"+ds, "guohu"))+",";
			}
			rukuv +="]";
			chukuv +="]";
			guohuv +="]";
			rukus +=yearList.get(i)+":"+ rukuv+",";
			chukus +=yearList.get(i)+":"+chukuv+",";
			guohus+=yearList.get(i)+":"+guohuv+",";
			year+=yearList.get(i)+",";
		}
		year += "]";
		JSONObject obj = new JSONObject();
		obj.put("year", year.toString());
		obj.put("ruku",rukus.toString());
		obj.put("chuku",chukus.toString());
		obj.put("guohu",guohus.toString());
		array.add(obj);
		out.print(array.toString());
		System.out.println(array.toString());
		out.flush();
		out.close();
		return null;
	}
	
	//通过品类和时间进行统计鑫港出库过的吞吐量
	public ActionForward getChuRuGuoTunTu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TidingsForm tf = (TidingsForm) form;
		//判断起始日期
		if(!(tf.getBegin()!=null && tf.getBegin().equals("")==false)){
			tf.setBegin(pt.getNowJianYi());
		}
		//判断结束日期
		if(!(tf.getFinish()!=null && tf.getFinish().equals("")==false)){
			tf.setFinish(pt.getTimes());
		}
		
		JSONArray array = new JSONArray();
		PrintWriter out = response.getWriter();
		
		Double rukuP = 0.0;
		Double chukuP = 0.0;
		Double guohuP = 0.0;
		
		DecimalFormat df = new DecimalFormat("###############0.000");
		
		
		//查询货物的品类
		List<GoodsCategory> gcList = this.goodsCategoryService.getAll();
		//通过货物品类进行遍历数据
		for(int i=0; i<gcList.size(); i++){
			JSONObject o = new JSONObject();
			// 通过单个品类得出当个品类入库的量
			rukuP = this.inputSeedService.getRuKuSum(tf.getBegin(), tf.getFinish(), "", gcList.get(i).getGoodsCategoryName(), "ruku");
			//通过单个品类得出当个品类出库的量
			chukuP = this.inputSeedService.getChuKuSum(tf.getBegin(), tf.getFinish(),  "", gcList.get(i).getGoodsCategoryName(), "chuku");
			//通过单个品类得出单个品类过户的量
			guohuP = this.inputSeedService.getGuoHuSum(tf.getBegin(), tf.getFinish(), "", gcList.get(i).getGoodsCategoryName(), "sum");
			
			o.put("pinlei", gcList.get(i).getGoodsCategoryName());
			o.put("ru", df.format(rukuP));
			o.put("chu", df.format(chukuP));
			o.put("guo", df.format(guohuP));
			array.add(o);
		}
		out.print(array.toString());
		System.out.println(array.toString());
		out.flush();
		out.close();
		
		return null;
	}
}
