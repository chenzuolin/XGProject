package com.xinggang.project.tools;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xinggang.project.entity.Accounts;
import com.xinggang.project.entity.Checks;
import com.xinggang.project.entity.ChecksClientGoods;
import com.xinggang.project.entity.ClientGoods;
import com.xinggang.project.entity.DuanDao;
import com.xinggang.project.entity.Export;
import com.xinggang.project.entity.Input;
import com.xinggang.project.entity.LoginLog;
import com.xinggang.project.entity.Shift;
import com.xinggang.project.entity.ShiftStock;
import com.xinggang.project.entity.SteelPrice;
import com.xinggang.project.entity.TarehouseDetail;
import com.xinggang.project.service.AccountsService;
import com.xinggang.project.service.ChecksClientGoodsService;
import com.xinggang.project.service.ChecksService;
import com.xinggang.project.service.ClientGoodsService;
import com.xinggang.project.service.DuanDaoService;
import com.xinggang.project.service.ExportService;
import com.xinggang.project.service.InputService;
import com.xinggang.project.service.LoginLogService;
import com.xinggang.project.service.ShiftService;
import com.xinggang.project.service.ShiftStockService;
import com.xinggang.project.service.SteelPriceService;
import com.xinggang.project.service.TarehouseDetailService;

public class PageRow {

	// 分页查询中显示的行数
	public int getRow() {
		return 5;
	}

	// 分页查询中显示的行数
	public int getClientRow() {
		return 16;
	}

	private static int number = 0;// 盘点客户库存是加加的编号
	private static int chukunumber = 0;// 出库时加加的编号
	private static int picinumber = 0;// 批次编号
	private static int guohunumber = 0;// 过户编号
	private static int rizhinumber = 0;// 日志编号
	private static int zhinajinnumber = 0;// 滞纳金编号
	private static int num = 0;// 入库总订单
	private static int nuokunumber = 0;// 挪库编号
	private static int jia = 0;// 入库订单操作表编号
	private static int pankunumber = 0;// 对应的盘库编号
	private static int kehukucunnumber = 0;// 客户货物库存编号
	private static int h = 0;// 入库操作表2
	private static int jinrigangjia = 0;// 今日的编号的生成
	private static int duandaonumber = 0;// 短倒中的编号的生成

	// 当服务一启动的时候，去查看一下看数据库中的编号
	public static void chakan() {
		PresentTime pt = new PresentTime();
		ApplicationContext ap = new ClassPathXmlApplicationContext(
				"applicationContext.xml");

		// 登录日志service
		LoginLogService loginLogService = (LoginLogService) ap
				.getBean("loginLogService");
		// 盘点客户库存service
		ChecksClientGoodsService checksClientGoodsService = (ChecksClientGoodsService) ap
				.getBean("checksClientGoodsService");
		// 出库service
		ExportService exportService = (ExportService) ap
				.getBean("exportService");

		// 批次service
		TarehouseDetailService tarehouseDetailService = (TarehouseDetailService) ap
				.getBean("tarehouseDetailService");
		// 过户service
		ShiftStockService shiftStockService = (ShiftStockService) ap
				.getBean("shiftStockService");
		// 滞纳金service
		AccountsService accountsService = (AccountsService) ap
				.getBean("accountsService");

		// 入库总订单
		InputService inputService = (InputService) ap.getBean("inputService");

		// 盘库service
		ChecksService checksService = (ChecksService) ap
				.getBean("checksService");

		// 客户货物库存service
		ClientGoodsService clientGoodsService = (ClientGoodsService) ap
				.getBean("clientGoodsService");
		// 今日钢价service
		SteelPriceService spservice = (SteelPriceService) ap
				.getBean("steelPriceService");
		// 短倒service
		DuanDaoService duanDaoService = (DuanDaoService) ap
				.getBean("duanDaoService");

		List<Input> inputlist = inputService.getAll();
		if (inputlist.size() > 0) {
			String inputstr = inputlist.get(0).getInputId();
			int inputnum = Integer.parseInt(inputstr.substring(
					inputstr.length() - 3, inputstr.length()));
			num = inputnum;
		}
		// 挪库service
		ShiftService shiftService = (ShiftService) ap.getBean("shiftService");

		List<ShiftStock> sslist = shiftStockService.getAll();
		if (sslist.size() > 0) {
			String ssstr = sslist.get(0).getSstockId();
			int ssnum = Integer.parseInt(ssstr.substring(ssstr.length() - 3,
					ssstr.length()));
			guohunumber = ssnum;
		}

		List<ChecksClientGoods> ccglist = checksClientGoodsService.getAll();
		System.out.println(ccglist.size());
		if (ccglist.size() > 0) {
			String ccstr = ccglist.get(0).getCcgoodsId();
			int ccnum = Integer.parseInt(ccstr.substring(ccstr.length() - 3,
					ccstr.length()));
			number = ccnum;
		}

		List<Export> elist = exportService.getAll();
		if (elist.size() > 0) {
			String estr = elist.get(0).getExportId();
			int enums = Integer.parseInt(estr.substring(estr.length() - 3,
					estr.length()));
			chukunumber = enums;
		}

		List<TarehouseDetail> tdlist = tarehouseDetailService.getAll();
		if (tdlist.size() > 0) {
			String tdstr = tdlist.get(0).getTdetailId();
			int tdnum = Integer.parseInt(tdstr.substring(tdstr.length() - 3,
					tdstr.length()));
			picinumber = tdnum;
		}

		List<LoginLog> llList = loginLogService.getAll("2010-02-03 12:12:12",
				pt.getTimes(), "");
		if (llList.size() > 0) {
			String strId = llList.get(0).getLlogId();
			int num = Integer.parseInt(strId.substring(strId.length() - 5,
					strId.length()));
			rizhinumber = num;
		}

		List<Accounts> alist = accountsService.getAll();
		if (alist.size() > 0) {
			String strId = alist.get(0).getAccountsId();
			int num = Integer.parseInt(strId.substring(strId.length() - 3,
					strId.length()));
			zhinajinnumber = num;
		}

		List<Shift> slist = shiftService.getAll();
		if (slist.size() > 0) {
			String strId = slist.get(0).getShiftId();
			int num = Integer.parseInt(strId.substring(strId.length() - 3,
					strId.length()));
			nuokunumber = num;
		}

		// 盘库编号的重新查询
		List<Checks> clist = checksService.getAll();
		if (clist.size() > 0) {
			String strId = clist.get(0).getCheckId();
			int num = Integer.parseInt(strId.substring(strId.length() - 3,
					strId.length()));
			pankunumber = num;
		}

		// 客户货物库存service
		List<ClientGoods> cgslist = clientGoodsService.getAll();
		if (cgslist.size() > 0) {
			String strId = cgslist.get(0).getCgoodsId();
			int num = Integer.parseInt(strId.substring(strId.length() - 3,
					strId.length()));
			kehukucunnumber = num;
		}

		// 今日钢价返回最大编号
		List<SteelPrice> splist = spservice.getAll();
		if (splist.size() > 0) {
			String strId = splist.get(0).getSpid();
			int num = Integer.parseInt(strId.substring(strId.length() - 3,
					strId.length()));
			jinrigangjia = num;
		}
		// 短倒中服务重启时返回最好的编号
		List<DuanDao> ddlist = duanDaoService.getAll();
		if (ddlist.size() > 0) {
			String strId = ddlist.get(0).getShiftId();
			int num = Integer.parseInt(strId.substring(strId.length() - 2,
					strId.length()));
			duandaonumber = num;
		}
	}

	public String getNumber() {// 盘点客户库存是调用的方法
		number++;
		String numbers = "";
		if (number < 10) {
			numbers = "00" + number;
		} else if (number >= 10 && number < 100) {
			numbers = "0" + number;
		} else {
			numbers = String.valueOf(number);
		}
		return numbers;
	}

	public void qingling() {// 盘点客户库存编号清零
		number = 0;
	}

	public String getChuKuNumber() {// 出库时调用的编号方法
		chukunumber++;
		String chukunumbers = "";
		if (chukunumber < 10) {
			chukunumbers = "00" + chukunumber;
		} else if (chukunumber >= 10 && chukunumber < 100) {
			chukunumbers = "0" + chukunumber;
		} else {
			chukunumbers = String.valueOf(chukunumber);
		}
		return chukunumbers;
	}

	public void chukuqingling() {// 出库编号清零的方法
		chukunumber = 0;
	}

	// 返回的批次编号
	public String getPiCiNumber() {
		picinumber++;
		String picinumbers = "";
		if (picinumber < 10) {
			picinumbers = "00" + picinumber;
		} else if (picinumber < 100 && picinumber >= 10) {
			picinumbers = "0" + picinumber;
		} else {
			picinumbers = String.valueOf(picinumber);
		}
		return picinumbers;
	}

	// 批次编号清零
	public void piciqingling() {
		picinumber = 0;
	}

	// 返回过户编号
	public String getGuoHuNumber() {
		guohunumber++;
		String guohunumbers = "";
		if (guohunumber < 10) {
			guohunumbers = "00" + guohunumber;
		} else if (guohunumber >= 10 && guohunumber < 100) {
			guohunumbers = "0" + guohunumber;
		} else {
			guohunumbers = String.valueOf(guohunumber);
		}
		return guohunumbers;
	}

	// 过户编号清零
	public void guohuqingling() {
		guohunumber = 0;
	}

	// 返回日志编号
	public String getRiZhiNumber() {

		rizhinumber++;
		String rizhinumbers = "";
		if (rizhinumber < 10) {
			rizhinumbers = "0000" + rizhinumber;
		} else if (rizhinumber < 100 && rizhinumber >= 10) {
			rizhinumbers = "000" + rizhinumber;
		} else if (rizhinumber >= 100 && rizhinumber < 1000) {
			rizhinumbers = "00" + rizhinumber;
		} else if (rizhinumber >= 1000 && rizhinumber < 10000) {
			rizhinumbers = "0" + rizhinumber;
		} else {
			rizhinumbers = String.valueOf(rizhinumber);
		}
		return rizhinumbers;
	}

	// 每24小时清零日志编号
	public void rizhiqingling() {
		rizhinumber = 0;
	}

	// 返回滞纳金编号
	public String getZhiNaJinNumber() {
		zhinajinnumber++;
		String zhinajinnumbers = "";
		if (zhinajinnumber < 10) {
			zhinajinnumbers = "00" + zhinajinnumber;
		} else if (zhinajinnumber >= 10 && zhinajinnumber < 100) {
			zhinajinnumbers = "0" + zhinajinnumber;
		} else {
			zhinajinnumbers = String.valueOf(zhinajinnumber);
		}
		return zhinajinnumbers;
	}

	// 滞纳金编号清零
	public void zhinajinqingling() {
		zhinajinnumber = 0;
	}

	// 入库总订单
	public String getInputNumber() {
		String num2 = null;
		num++;
		if (num < 10) {
			num2 = "00" + num;
		} else if (num >= 10 && num < 100) {
			num2 = "0" + num;
		} else {
			num2 = Integer.toHexString(num);
		}
		return num2;
	}

	// 子订单清空
	public void rukuzongqingkong() {
		num = 0;
	}

	// 返回挪库编号
	public String getNuoKuNumber() {
		nuokunumber++;
		String nuokunumbers = "";
		if (nuokunumber < 10) {
			nuokunumbers = "00" + nuokunumber;
		} else if (nuokunumber >= 10 && nuokunumber < 100) {
			nuokunumbers = "0" + nuokunumber;
		} else {
			nuokunumbers = String.valueOf(nuokunumber);
		}
		return nuokunumbers;
	}

	public void nuokuqingling() {
		nuokunumber = 0;
	}

	// 入库操作编号
	public String getrukuCaozuoNum() {
		String num2 = null;
		jia++;
		if (num < 10) {
			num2 = "00" + jia;
		} else if (jia >= 10 && jia < 100) {
			num2 = "0" + jia;
		} else {
			num2 = Integer.toHexString(jia);
		}
		return num2;
	}

	// 入库操作编号
	public String getrukuCaozuoNum2() {
		String num2 = "";
		if (h < 10) {
			num2 = "0" + h;
		} else {
			num2 = String.valueOf(h);
		}
		return num2;
	}

	// 清空入库操作编号
	public void qingKongRuCaoN() {
		h = 0;
	}

	// 清空入库操作编号
	public void qingKongRuCao() {
		jia = 0;
	}

	// 返回盘库编号
	public String getPanKuNumber() {
		pankunumber++;
		String pankunumbers = "";
		if (pankunumber < 10) {
			pankunumbers = "00" + pankunumber;
		} else if (pankunumber < 100 && pankunumber >= 10) {
			pankunumbers = "0" + pankunumber;
		} else {
			pankunumbers = String.valueOf(pankunumber);
		}
		System.out.println("编号到多少了呢：" + pankunumbers);
		return pankunumbers;
	}

	// 将盘库编号每隔24小时清零
	public void pankuqingling() {
		pankunumber = 0;
	}

	// 返回客户货物库存编号
	public String getKeHuKuCunNumber() {
		kehukucunnumber++;
		String kehukucunnumbers = "";
		if (kehukucunnumber < 10) {
			kehukucunnumbers = "00" + kehukucunnumber;
		} else if (kehukucunnumber >= 10 && kehukucunnumber < 100) {
			kehukucunnumbers = "0" + kehukucunnumber;
		} else {
			kehukucunnumbers = String.valueOf(kehukucunnumber);
		}
		return kehukucunnumbers;
	}

	// 客户货物库存编号24小时清零
	public void kehukucunqingling() {
		kehukucunnumber = 0;
	}

	// 返回今日钢价编号
	public String getJinRiGangJiaNumbers() {
		jinrigangjia++;
		String jinrigangjias = "";
		if (jinrigangjia < 10) {
			jinrigangjias = "00" + jinrigangjia;
		} else if (jinrigangjia >= 10 && jinrigangjia < 100) {
			jinrigangjias = "0" + jinrigangjia;
		} else {
			jinrigangjias = String.valueOf(jinrigangjia);
		}
		return jinrigangjias;
	}

	// 客户货物库存编号24小时清零
	public void JinRiGangJiaQingLing() {
		jinrigangjia = 0;
	}

	// 返回短倒中生成的编号
	public String duanDaoNumbers() {
		duandaonumber++;
		String duandaonumbers = "";
		if (duandaonumber <= 9) {
			duandaonumbers = "0" + duandaonumber;
		} else {
			duandaonumbers = String.valueOf(duandaonumber);
		}
		return duandaonumbers;
	}

	// 短倒中编号的清零
	public void duandaoqingling() {
		duandaonumber = 0;
	}
}
